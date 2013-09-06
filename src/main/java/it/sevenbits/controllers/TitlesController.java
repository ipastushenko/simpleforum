package it.sevenbits.controllers;

import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import it.sevenbits.forms.AddTitleForm;
import it.sevenbits.forms.SendMessageForm;
import it.sevenbits.jsonmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/json/titles/{page:\\d+}", method = RequestMethod.GET)
    public @ResponseBody
    JsonBase getListTitles(@PathVariable final Long page) {
        JsonBase jsonModel = null;
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
    public @ResponseBody JsonBase removeMessage(@PathVariable final Long titleId) {
        JsonBase jsonModel = null;
        List<String> listErrors = null;
        TitleEntity titleEntity = titleDao.findById(titleId);
        if (titleEntity == null) {
            listErrors = new ArrayList<>();
            listErrors.add("Title is not exist");
            jsonModel = new JsonError(listErrors);
        }
        else {
            try {
                titleDao.delete(titleEntity);
                jsonModel = new JsonRemoveElement();
            }
            catch (DataAccessException e) {
                listErrors = new ArrayList<>();
                listErrors.add("Error remove this title");
                jsonModel = new JsonError(listErrors);
            }
        }
        return jsonModel;
    }

    @RequestMapping(value = "/json/titles", method = RequestMethod.POST)
    public @ResponseBody JsonBase addNewMessage(@Valid final AddTitleForm addTitleForm, final BindingResult result) {
        JsonBase jsonModel = null;
        List<String> listErrors = null;
        if (!result.hasErrors()) {
            try {
                Title title = new Title(addTitleForm.getName());
                titleDao.create(title);
                jsonModel = new JsonAddElement();
            }
            catch (DataAccessException e) {
                listErrors = new ArrayList<>();
                listErrors.add("Error create new title");
                jsonModel = new JsonError(listErrors);
            }
        }
        else {
            listErrors = new ArrayList<>();
            List<ObjectError> errors = result.getAllErrors();
            for (int i = 0; i < errors.size(); ++i) {
                listErrors.add(errors.get(i).getDefaultMessage());
            }
            jsonModel = new JsonError(listErrors);
        }

        return jsonModel;
    }

    //TODO: TO REMOVE FUNCTIONS ***
    @RequestMapping(value = "/titles/{page}", method = RequestMethod.GET)
    public ModelAndView viewTitles(@PathVariable final Long page) {
        ModelAndView modelAndView = createModelTitles(page);

        return  modelAndView;
    }

    @RequestMapping(value = "/removeTitle/{titleId}/{page}", method = RequestMethod.GET)
    public ModelAndView deleteTitle(@PathVariable final Long titleId, @PathVariable final Long page) {
        if (titleId != null) {
            TitleEntity title = titleDao.findById(titleId);
            if (title != null)
                titleDao.delete(title);
        }
        ModelAndView modelAndView = createModelTitles(page);

        return  modelAndView;
    }

    @RequestMapping(value = "/titles", method = RequestMethod.POST)
    public ModelAndView addTitle(
            @Valid final AddTitleForm addTitleForm,
            final BindingResult result
    ) {
        Long page = new Long(1);
        if (!result.hasErrors()) {
            Title title = new Title(addTitleForm.getName());
            titleDao.create(title);
        }
        else {
            //TODO:get error message for user
        }

        ModelAndView modelAndView = createModelTitles(page);

        return modelAndView;
    }
    //TODO: TO REMOVE FUNCTIONS /***

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

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
