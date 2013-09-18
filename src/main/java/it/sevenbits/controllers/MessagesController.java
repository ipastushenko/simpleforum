package it.sevenbits.controllers;

import javax.servlet.http.HttpServletResponse;
import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import it.sevenbits.forms.SendMessageForm;
import it.sevenbits.jsonmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

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

    @RequestMapping(value = "/json/messages/{titleId:\\d+}/{date:\\d+}/{count:\\d+}", method = RequestMethod.GET)
    public @ResponseBody JsonBase getListMessages(
            @PathVariable final Long titleId, @PathVariable final Long date, @PathVariable final Long count
    ) {
        String currentOrder = "createDate";
        Long beginDate;
        if (date.intValue() == 0) {
            beginDate = (new Date()).getTime();
        }
        else {
            beginDate = date;
        }

        List<MessageEntity> listMessageEntities = messageDao.findByTitleIdByLimitByOrder(
                titleId, count, beginDate, currentOrder
        );

        Long endDate = beginDate;
        if (!listMessageEntities.isEmpty()) {
            endDate = listMessageEntities.get(listMessageEntities.size() - 1).getCreateDate();
        }
        List<MessageEntity> listMessageEndDateEntities = messageDao.findByTitleIdByDate(titleId, endDate, currentOrder);
        int countEndDate = 1;
        for (int i = listMessageEntities.size() - 1; i > 0; --i)
            if (listMessageEntities.get(i).getCreateDate().equals(listMessageEntities.get(i - 1).getCreateDate()))
                ++countEndDate;

        for (int i = countEndDate; i < listMessageEndDateEntities.size(); ++i)
            listMessageEntities.add(listMessageEndDateEntities.get(i));

        if (!listMessageEntities.isEmpty())
        {
            endDate = listMessageEntities.get(listMessageEntities.size() - 1).getCreateDate() - 1;
        }

        return new JsonPage<MessageEntity>(endDate, listMessageEntities);
    }

    @RequestMapping(value = "/json/messages", method = RequestMethod.POST)
    public @ResponseBody JsonBase addNewMessage( @RequestBody final SendMessageForm sendMessageForm,final HttpServletResponse result) {
        JsonBase jsonModel = null;
        List<String> listErrors = null;
        Long titleId = sendMessageForm.getTitleId();
        Set<ConstraintViolation<SendMessageForm>> failures = validator.validate(sendMessageForm);
        if (failures.isEmpty()) {

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
            catch (DAOException e) {
                listErrors = new ArrayList<>();
                listErrors.add("Error create new message");
                jsonModel = new JsonError(listErrors);
            }
        }
        else {
            Iterator<ConstraintViolation<SendMessageForm>> iterator = failures.iterator();
            listErrors = new ArrayList<>();
            while(iterator.hasNext())
                listErrors.add(iterator.next().getMessage());
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
            catch (DAOException e) {
                listErrors = new ArrayList<>();
                listErrors.add("Error remove this message");
                jsonModel = new JsonError(listErrors);
            }
        }
        return jsonModel;
    }
}
