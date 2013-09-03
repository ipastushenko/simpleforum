package it.sevenbits.controllers;

import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import it.sevenbits.entity.Title;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 8/30/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class TitlesController {
    @RequestMapping(value = "/titles/{page}", method = RequestMethod.GET)
    public ModelAndView viewTitles(@PathVariable final Long page) {
        Long currentPage = page;
        if (currentPage == null) {
            currentPage = new Long(1);
        }
        ModelAndView modelAndView = createModelTitles(currentPage);

        return  modelAndView;
    }

    @RequestMapping(value = "/removeTitle/{titleId}/{page}", method = RequestMethod.GET)
    public ModelAndView deleteTitles(
        @PathVariable final Long titleId,
        @PathVariable final Long page
    ) {

        Long currentPage = page;
        if (currentPage == null)
            currentPage = new Long(1);
        if (titleId != null) {
            TitleEntity title = titleDao.getTitleById(titleId);
            titleDao.delete(title);
        }
        ModelAndView modelAndView = createModelTitles(currentPage);

        return  modelAndView;
    }

    private ModelAndView createModelTitles(Long currentPage)
    {
        ModelAndView modelAndView = new ModelAndView("titles");

        List<TitleEntity> list = titleDao.getAll();
        List<TitleEntity> listTitle = new ArrayList<>();
        int countRow = ControllerUtils.getCountRow(getClass());
        for (int i = (currentPage.intValue() - 1) * countRow; i < list.size() && i < currentPage.intValue() * countRow; ++i) {
            listTitle.add(list.get(i));
        }

        int pagePrev = currentPage.intValue() - 1;
        int pageNext = currentPage.intValue() + 1;
        if (pagePrev < 0)
            pagePrev = 0;
        if (currentPage.intValue() * countRow >= list.size())
            pageNext = 1;
        modelAndView.addObject("pagePrev", pagePrev);
        modelAndView.addObject("pageNext", pageNext);
        modelAndView.addObject("titles", listTitle);
        modelAndView.addObject("page", currentPage);

        return modelAndView;
    }

    @Resource(name="titleDao")
    private TitleDao titleDao;
}
