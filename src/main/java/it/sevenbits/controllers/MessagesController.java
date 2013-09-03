package it.sevenbits.controllers;

/**
 * Simple forum
 * User: Ivan Pastushenko @ sevenbits
 * Date: 8/30/13
 * Time: 10:13 AM
 */

import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.sevenbits.dao.MessageDao;

@Controller
public class MessagesController {
    @RequestMapping(value = "/messages/{titleId}/{page}", method = RequestMethod.GET)
    public ModelAndView viewMessages(
        @PathVariable final Long titleId,
        @PathVariable final Long page
    ) {
        ModelAndView modelAndView = append(page, titleId);
        return  modelAndView;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ModelAndView viewMessages(
        @Valid final SendMessageForm sendMessageForm,
        final BindingResult result
    ) {
        Long page = sendMessageForm.getPage();
        Long titleId = sendMessageForm.getTitleId();
        if (!result.hasErrors()) {
            Message message = new Message(titleDao.getTitleById(titleId), sendMessageForm.getTextMessage());
            messageDao.create(message, titleId);
        }
        else {
            //TODO:get error message for user
        }

        ModelAndView modelAndView = append(page, titleId);

        return modelAndView;
    }

    private ModelAndView append (final Long page, final Long titleId) {
        ModelAndView modelAndView = null;
        if (page == null || titleId == null) {
            modelAndView = new ModelAndView("redirect:/titles/0");
        }
        else {
            modelAndView = new ModelAndView("messages");
            List<Message> list = messageDao.getAll(titleId);
            List<Message> listMessage = new ArrayList<>();
            int countRow = ControllerUtils.getCountRow(getClass());
            for (int i = (page.intValue() - 1) * countRow; i < list.size() && i < page.intValue() * countRow; ++i) {
                listMessage.add(list.get(i));
            }

            int pagePrev = page.intValue() - 1;
            int pageNext = page.intValue() + 1;
            if (pagePrev < 0)
                pagePrev = 0;
            if (page.intValue() * countRow >= list.size())
                pageNext = 1;
            modelAndView.addObject("pagePrev", pagePrev);
            modelAndView.addObject("pageNext", pageNext);
            modelAndView.addObject("messages", listMessage);
            modelAndView.addObject("titleId", titleId);
            modelAndView.addObject("title", titleDao.getTitleById(titleId).getName());
            modelAndView.addObject("page", page);
        }

        return modelAndView;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @Resource(name="messageDao")
    private MessageDao messageDao;
    @Resource(name="titleDao")
    private TitleDao titleDao;
    @Autowired
    private Validator validator;
}
