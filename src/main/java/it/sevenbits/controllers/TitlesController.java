package it.sevenbits.controllers;

import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/titles.html", method = RequestMethod.GET)
    public ModelAndView viewMessages(@RequestParam(value = "page", required = true) final Long page) {
        ModelAndView modelAndView = new ModelAndView("titles");
        //TODO:check parameters for null
        List<TitleEntity> list = titleDao.getAll();

        List<TitleEntity> listTitle = new ArrayList<>();
        int countRow = ControllerUtils.getCountRow(getClass());
        for (int i = page.intValue() * countRow; i < list.size() && i < (page.intValue() + 1) * countRow; ++i) {
            listTitle.add(list.get(i));
        }

        int pagePrev = page.intValue() - 1;
        int pageNext = page.intValue() + 1;
        if (pagePrev < -1)
            pagePrev = -1;
        if ((page.intValue() + 1) * countRow >= list.size())
            pageNext = 0;
        modelAndView.addObject("pagePrev", pagePrev);
        modelAndView.addObject("pageNext", pageNext);
        modelAndView.addObject("titles", listTitle);

        return  modelAndView;
    }

    @Resource(name="titleDao")
    private TitleDao titleDao;
}
