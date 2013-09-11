package it.sevenbits.controllers;

import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.TitleEntity;
import it.sevenbits.forms.AddTitleForm;
import it.sevenbits.jsonmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Validator;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.*;

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

    @RequestMapping(value = "/json/titles/{date:\\d+}/{count:\\d+}/{order:\\d+}", method = RequestMethod.GET)
    public @ResponseBody JsonBase getListTitles(
            @PathVariable final Long date, @PathVariable final Long count, @PathVariable final Long order
    ) {
        String currentOrder;
        if (order.intValue() == 0) {
            currentOrder = "createDate";
        }
        else if (order.intValue() == 1) {
            currentOrder = "lastUpdateDate";
        }
        else {
            List<String> errors = new ArrayList<>();
            errors.add("incorrect order");
            return new JsonError(errors);
        }

        Long beginDate;
        if (date.intValue() == 0) {
            beginDate = (new Date()).getTime();
        }
        else {
            beginDate = date;
        }

        List<TitleEntity> listTitleEntities = titleDao.findByLimitByOrder(count + 1, beginDate, currentOrder);
        Long endDate = beginDate;
        long time = 0;
        if (listTitleEntities.size() <= count)
            time = -1;
        if (!listTitleEntities.isEmpty())
        {
            if (order == 0) {
                endDate = listTitleEntities.get(listTitleEntities.size() - 1).getCreateDate() + time;
            }
            else {
                endDate = listTitleEntities.get(listTitleEntities.size() - 1).getLastUpdateDate() + time;
            }
        }
        else {
            endDate = (long)0;
        }
        return new JsonPage<TitleEntity>(endDate, listTitleEntities);
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
