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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.sevenbits.dao.MessageDao;

@Controller
public class MessagesController {
    @RequestMapping(value = "/messages.html", method = RequestMethod.GET)
    public ModelAndView viewMessages(
            @RequestParam(value = "titleId", required = true) final Long titleId,
            @RequestParam(value = "page", required = true) final Long page
    )
    {
        //TODO:check parameters for null

        ModelAndView modelAndView = new ModelAndView("messages");
        append(modelAndView, page, titleId);

        return  modelAndView;
    }

    //TODO:check parameters for null
    @RequestMapping(value = "/messages.html", method = RequestMethod.POST)
    public ModelAndView viewMessages(
        @Valid @ModelAttribute("sendMessageForm") final SendMessageForm sendMessageForm,
        final BindingResult result
    ) {
        ModelAndView modelAndView = new ModelAndView("messages");
        Long page = sendMessageForm.getPage();
        Long titleId = sendMessageForm.getTitleId();
        append(modelAndView, page, titleId);
        Message message = new Message(titleDao.getTitleById(titleId), "asdasd");
        messageDao.create(message, titleId);
        /*if(!result.hasErrors())
        {
            Long page = sendMessageForm.getPage();
            Long titleId = sendMessageForm.getTitleId();


        }
        else
        {
            modelAndView = new ModelAndView("messages");
            modelAndView.addObject()
        }*/

        return modelAndView;
    }

    private void append (ModelAndView modelAndViewMessages, final Long page, final Long titleId) {
        List<Message> list = messageDao.getAll(titleId);
        List<Message> listMessage = new ArrayList<>();
        int countRow = ControllerUtils.getCountRow(getClass());
        for (int i = page.intValue() * countRow; i < list.size() && i < (page.intValue() + 1) * countRow; ++i) {
            listMessage.add(list.get(i));
        }

        int pagePrev = page.intValue() - 1;
        int pageNext = page.intValue() + 1;
        if (pagePrev < -1)
            pagePrev = -1;
        if ((page.intValue() + 1) * countRow >= list.size())
            pageNext = 0;
        modelAndViewMessages.addObject("pagePrev", pagePrev);
        modelAndViewMessages.addObject("pageNext", pageNext);
        modelAndViewMessages.addObject("messages", listMessage);
        modelAndViewMessages.addObject("titleId", titleId);
        modelAndViewMessages.addObject("title", titleDao.getTitleById(titleId).getName());
        modelAndViewMessages.addObject("page", page);
    }

    @Resource(name="messageDao")
    private MessageDao messageDao;
    @Resource(name="titleDao")
    private TitleDao titleDao;
}
