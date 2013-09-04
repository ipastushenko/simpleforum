package it.sevenbits.controllers;

import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.TitleEntity;
import it.sevenbits.forms.AddTitleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;
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

    @RequestMapping(value = "/titles/{page}", method = RequestMethod.GET)
    public ModelAndView viewTitles(@PathVariable final Long page) {
        ModelAndView modelAndView = createModelTitles(page);

        return  modelAndView;
    }

    @RequestMapping(value = "/removeTitle/{titleId}/{page}", method = RequestMethod.GET)
    public ModelAndView deleteTitle(@PathVariable final Long titleId, @PathVariable final Long page) {
        if (titleId != null) {
            TitleEntity title = titleDao.getTitleById(titleId);
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
        Long page = addTitleForm.getPage();
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    private ModelAndView createModelTitles(final Long page)
    {
        Long currentPage = ControllerUtils.getCurrentPage(page);
        ModelAndView modelAndView = new ModelAndView("titles");
        List<TitleEntity> list = titleDao.getAll();
        int countRow = ControllerUtils.getCountRow(getClass());

        modelAndView.addObject("pagePrev", ControllerUtils.getPagePrev(currentPage.intValue()));
        modelAndView.addObject("pageNext", ControllerUtils.getPageNext(currentPage.intValue(), countRow, list.size()));
        modelAndView.addObject("titles", ControllerUtils.<TitleEntity>getListEntity(list, countRow, currentPage.intValue()));
        modelAndView.addObject("page", currentPage);

        return modelAndView;
    }
}
