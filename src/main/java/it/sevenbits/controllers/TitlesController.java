package it.sevenbits.controllers;

import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.TitleEntity;
import it.sevenbits.forms.AddTitleForm;
import it.sevenbits.forms.SendMessageForm;
import it.sevenbits.jsonmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import javax.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * TitlesController
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 30.08.2013
 */
@Controller
public class TitlesController {
    @Resource(name="titleDao")
    private TitleDao titleDao;
    @Autowired
    private Validator validator;

    @RequestMapping(value = "/singlepage", method = RequestMethod.GET)
    public String viewSingletonPage(Model model) {
        model.addAttribute("countElements", ControllerUtils.getCountRow(TitlesController.class));
        return "singlepage";
    }

    @RequestMapping(value = "/json/titles/{page:\\d+}", method = RequestMethod.GET)
    public @ResponseBody JsonBase getListTitles(@PathVariable final Long page) {
        JsonBase jsonModel;
        if (page.intValue() == 0) {
            List<String> listErrors = new ArrayList<>();
            listErrors.add("page must be not zero");
            jsonModel = new JsonError(listErrors);
        }
        else {
            List<TitleEntity> listTitleEntities = titleDao.findAll();
            int countRows = ControllerUtils.getCountRow(TitlesController.class);
            int countPages = ControllerUtils.getCountPages(listTitleEntities.size(), countRows);
            int currentPage = ControllerUtils.getCurrentPage(page, countPages);
            List<String> listElements = new ArrayList<>();
            List<Long> listElementIds = new ArrayList<>();
            for (int i = (currentPage - 1) * countRows; i < currentPage * countRows && i < listTitleEntities.size(); ++i) {
                listElements.add(listTitleEntities.get(i).getName());
                listElementIds.add(listTitleEntities.get(i).getId());
            }
            jsonModel = new JsonPage(currentPage, countPages, listElements, listElementIds);
        }
        return jsonModel;
    }

    @RequestMapping(value = "/json/removeTitle/{titleId:\\d+}", method = RequestMethod.GET)
    public @ResponseBody JsonBase removeTitle(@PathVariable final Long titleId) {
        JsonBase jsonModel;
        List<String> listErrors;
        TitleEntity titleEntity = titleDao.findById(titleId);
        if (titleEntity == null) {
            listErrors = new ArrayList<>();
            listErrors.add("Title does not exist");
            jsonModel = new JsonError(listErrors);
        }
        else {
            try {
                titleDao.delete(titleEntity);
                jsonModel = new JsonRemoveElement();
            }
            catch (DAOException e) {
                listErrors = new ArrayList<>();
                listErrors.add("Error remove this title");
                jsonModel = new JsonError(listErrors);
            }
        }
        return jsonModel;
    }

    @RequestMapping(value = "/json/titles", method = RequestMethod.POST)
    public @ResponseBody JsonBase addNewTitle(@RequestBody final AddTitleForm addTitleForm, final HttpServletResponse response) {
        JsonBase jsonModel;
        List<String> listErrors;
        Set<ConstraintViolation<AddTitleForm>> failures = validator.validate(addTitleForm);
        if (failures.isEmpty()) {
            try {
                Title title = new Title(addTitleForm.getName());
                titleDao.create(title);
                jsonModel = new JsonAddElement();
            }
            catch (DAOException e) {
                listErrors = new ArrayList<>();
                listErrors.add("Error create new title");
                jsonModel = new JsonError(listErrors);
            }
        }
        else {
            listErrors = new ArrayList<>();
            Iterator<ConstraintViolation<AddTitleForm>> iterator = failures.iterator();
            while(iterator.hasNext())
                listErrors.add(iterator.next().getMessage());
            jsonModel = new JsonError(listErrors);
        }

        return jsonModel;
    }

    //TODO: TO REMOVE FUNCTIONS ***
    @RequestMapping(value = "/titles/{page}", method = RequestMethod.GET)
    public ModelAndView viewTitles(@PathVariable final Long page) {
        return createModelTitles(page);
    }

    @RequestMapping(value = "/removeTitle/{titleId}/{page}", method = RequestMethod.GET)
    public ModelAndView deleteTitle(@PathVariable final Long titleId, @PathVariable final Long page) {
        if (titleId != null) {
            TitleEntity title = titleDao.findById(titleId);
            if (title != null)
                try {
                    titleDao.delete(title);
                } catch (DAOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
        }

        return createModelTitles(page);
    }

    @RequestMapping(value = "/titles", method = RequestMethod.POST)
    public ModelAndView addTitle(
            @Valid final AddTitleForm addTitleForm,
            final BindingResult result
    ) {
        Long page = (long) 1;
        if (!result.hasErrors()) {
            Title title = new Title(addTitleForm.getName());
            try {
                titleDao.create(title);
            } catch (DAOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        else {
            //TODO:get error message for user
        }

        return createModelTitles(page);
    }
    //TODO: TO REMOVE FUNCTIONS /***

    /*@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    } */

    //TODO: TO REMOVE FUNCTIONS ***
    private ModelAndView createModelTitles(final Long page)
    {
        Long currentPage = ControllerUtils.getCurrentPage(page);
        ModelAndView modelAndView = new ModelAndView("titles");
        List<TitleEntity> list = titleDao.findAll();
        int countRow = ControllerUtils.getCountRow(getClass());

        modelAndView.addObject("pagePrev", ControllerUtils.getPagePrev(currentPage.intValue()));
        modelAndView.addObject("pageNext", ControllerUtils.getPageNext(currentPage.intValue(), countRow, list.size()));
        modelAndView.addObject("titles", ControllerUtils.<TitleEntity>getListEntity(list, countRow, currentPage.intValue()));
        modelAndView.addObject("page", currentPage);

        return modelAndView;
    }
    //TODO: TO REMOVE FUNCTIONS /***
}
