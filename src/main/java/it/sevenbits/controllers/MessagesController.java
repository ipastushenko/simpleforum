package it.sevenbits.controllers;

import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
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

    @RequestMapping(value = "/json/messages/{titleId:\\d+}/{page:\\d+}", method = RequestMethod.GET)
    public @ResponseBody JsonBase getListMessages(@PathVariable final Long titleId,@PathVariable final Long page) {
        JsonBase jsonModel = null;
        List<String> listErrors = null;
        TitleEntity titleEntity = titleDao.findById(titleId);
        if (titleEntity == null || page.intValue() == 0) {
            listErrors = new ArrayList<>();
            if (titleEntity == null) {
                listErrors.add("Title is not exist");
            }
            if (page.intValue() == 0) {
                listErrors.add("page must be not zero");
            }
            jsonModel = new JsonError(listErrors);
        }
        else {
            List<MessageEntity> listMessageEntities = messageDao.findByTitleId(titleId);
            int countRows = ControllerUtils.getCountRow(MessagesController.class);
            int countPages = ControllerUtils.getCountPages(listMessageEntities.size(), countRows);
            int currentPage = ControllerUtils.getCurrentPage(page, countPages);
            List<String> listElements = new ArrayList<>();
            List<Long> listElementIds = new ArrayList<>();
            for (int i = (currentPage - 1) * countRows; i < currentPage * countRows && i < listMessageEntities.size(); ++i) {
                listElements.add(listMessageEntities.get(i).getTextMessage());
                listElementIds.add(listMessageEntities.get(i).getId());
            }
            jsonModel = new JsonPage(currentPage, countPages, listElements, listElementIds);
        }
        return jsonModel;
    }

    @RequestMapping(value = "/json/messages", method = RequestMethod.POST)
    public @ResponseBody JsonBase addNewMessage(@Valid final SendMessageForm sendMessageForm, final BindingResult result) {
        JsonBase jsonModel = null;
        List<String> listErrors = null;
        Long titleId = sendMessageForm.getTitleId();

        if (!result.hasErrors()) {

            try {
                TitleEntity titleEntity = titleDao.findById(titleId);
                if (titleEntity == null) {
                    listErrors = new ArrayList<>();
                    listErrors.add("Title is not exist");
                    jsonModel = new JsonError(listErrors);
                }
                else {
                    Message message = new Message(titleEntity, sendMessageForm.getTextMessage());
                    messageDao.create(message, titleId);
                    jsonModel = new JsonAddElement();
                }
            }
            catch (DataAccessException e) {
                listErrors = new ArrayList<>();
                listErrors.add("Error create new message");
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

    @RequestMapping(value = "/json/removeMessage/{messageId:\\d+}", method = RequestMethod.GET)
    public @ResponseBody JsonBase removeMessage(@PathVariable final Long messageId) {
        JsonBase jsonModel = null;
        List<String> listErrors = null;
        MessageEntity messageEntity = messageDao.findById(messageId);
        if (messageEntity == null) {
            listErrors = new ArrayList<>();
            listErrors.add("Message is not exist");
            jsonModel = new JsonError(listErrors);
        }
        else {
            try {
                messageDao.delete(messageEntity);
                jsonModel = new JsonRemoveElement();
            }
            catch (DataAccessException e) {
                listErrors = new ArrayList<>();
                listErrors.add("Error remove this message");
                jsonModel = new JsonError(listErrors);
            }
        }
        return jsonModel;
    }


    //TODO:TO REMOVE FUNCTIONS ***
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
        Long page = new Long(1);
        Long titleId = sendMessageForm.getTitleId();
        if (!result.hasErrors()) {
            Message message = new Message(titleDao.findById(titleId), sendMessageForm.getTextMessage());
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
            MessageEntity messageEntity = messageDao.findById(messageId);
            if (messageEntity != null) {
                titleId = messageEntity.getTitleEntity().getId();
                messageDao.delete(messageEntity);
            }
        }
        ModelAndView modelAndView = createModelMessages(page, titleId);

        return  modelAndView;
    }
    //TODO:TO REMOVE FUNCTIONS /***

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    //TODO:TO REMOVE FUNCTIONS ***
    private ModelAndView createModelMessages(final Long page, final Long titleId) {
        ModelAndView modelAndView = null;
        if (titleId == null) {
            modelAndView = new ModelAndView("redirect:/titles/1");
        }
        else {
            Long currentPage = ControllerUtils.getCurrentPage(page);
            modelAndView = new ModelAndView("messages");
            List<MessageEntity> list = messageDao.findByTitleId(titleId);
            int countRow = ControllerUtils.getCountRow(getClass());

            modelAndView.addObject("pagePrev", ControllerUtils.getPagePrev(currentPage.intValue()));
            modelAndView.addObject("pageNext", ControllerUtils.getPageNext(currentPage.intValue(), countRow, list.size()));
            modelAndView.addObject("messages", ControllerUtils.<MessageEntity>getListEntity(list, countRow, currentPage.intValue()));
            modelAndView.addObject("titleId", titleId);
            modelAndView.addObject("title", titleDao.findById(titleId).getName());
            modelAndView.addObject("page", currentPage);
        }

        return modelAndView;
    }
    //TODO:TO REMOVE FUNCTIONS /***
}
