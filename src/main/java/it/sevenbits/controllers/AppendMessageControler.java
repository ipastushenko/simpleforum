package it.sevenbits.controllers;

/**
 * Simple forum
 * User: Ivan Pastushenko @ sevenbits
 * Date: 8/30/13
 * Time: 10:13 AM
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

@Controller
@RequestMapping("/messages.html")
public class AppendMessageControler {

    private static Logger logger = LoggerFactory.getLogger(AppendMessageControler.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewMessages(
            @RequestParam(value = "message", required = false) final String message
    )
    {
        ModelAndView modelAndView = new ModelAndView("messages");
        //if (message == null)

        ArrayList<String> listString = new ArrayList<>();
        for (int i = 0; i < 10; ++i)
        {
            listString.add("qwdqwd");
        }
        modelAndView.addObject("messages", listString);

        logger.info("Test!!!");

        return modelAndView;
    }

}
