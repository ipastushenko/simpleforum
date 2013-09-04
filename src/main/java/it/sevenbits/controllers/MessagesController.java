package it.sevenbits.controllers;

import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.forms.SendMessageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import it.sevenbits.dao.MessageDao;

/**
 * MessagesController
 * @author  Ivan Pastushenko @ sevenbits
 * @version 1.0.0 30.08.2013
 */
@Controller
public class MessagesController {
    @Resource(name="messageDao")
    private MessageDao messageDao;
    @Resource(name="titleDao")
    private TitleDao titleDao;
    @Autowired
    private Validator validator;

    @RequestMapping(value = "/messages/{titleId}/{page}", method = RequestMethod.GET)
    public ModelAndView viewMessages(
        @PathVariable final Long titleId,
        @PathVariable final Long page
    ) {
        ModelAndView modelAndView = createModelMessages(page, titleId);
        return  modelAndView;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ModelAndView addMessage(
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

        ModelAndView modelAndView = createModelMessages(page, titleId);

        return modelAndView;
    }

    @RequestMapping(value = "/removeMessage/{messageId}/{page}", method = RequestMethod.GET)
    public ModelAndView deleteMessage(
            @PathVariable final Long messageId,
            @PathVariable final Long page
    ) {
        Long titleId = new Long(0);
        if (messageId != null) {
            MessageEntity messageEntity = messageDao.getMessageById(messageId);
            if (messageEntity != null) {
                titleId = messageEntity.getTitleEntity().getId();
                messageDao.delete(messageEntity);
            }
        }
        ModelAndView modelAndView = createModelMessages(page, titleId);

        return  modelAndView;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    private ModelAndView createModelMessages(final Long page, final Long titleId) {
        ModelAndView modelAndView = null;
        if (titleId == null) {
            modelAndView = new ModelAndView("redirect:/titles/1");
        }
        else {
            Long currentPage = ControllerUtils.getCurrentPage(page);
            modelAndView = new ModelAndView("messages");
            List<MessageEntity> list = messageDao.getByTitleId(titleId);
            int countRow = ControllerUtils.getCountRow(getClass());

            modelAndView.addObject("pagePrev", ControllerUtils.getPagePrev(currentPage.intValue()));
            modelAndView.addObject("pageNext", ControllerUtils.getPageNext(currentPage.intValue(), countRow, list.size()));
            modelAndView.addObject("messages", ControllerUtils.<MessageEntity>getListEntity(list, countRow, currentPage.intValue()));
            modelAndView.addObject("titleId", titleId);
            modelAndView.addObject("title", titleDao.getTitleById(titleId).getName());
            modelAndView.addObject("page", currentPage);
        }

        return modelAndView;
    }
}
