package com.seyrancom.consulting.app.web;


import com.seyrancom.consulting.core.web.AppController;
import com.seyrancom.consulting.core.web.common.AbstractController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@AppController
@RequestMapping
public class MainController extends AbstractController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(Locale locale, Model model) {
        return new ModelAndView("/index.html");
        //return new ModelAndView("/webapp/index.html");
    }

    @RequestMapping(value = "/ping")
    @ResponseBody
    public String securedPing() {
        return "All good. You only get this message if you're authenticated";
    }

    /**
     * HealthCheck to verify if Resources are up
     */
    @RequestMapping(value = {"/status",}, method = RequestMethod.GET)
    //@RequestMapping(value = { "", "/", }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> healthCheck(Locale locale) {
        logger.info("Welcome home. The client locale is {}", locale);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(new Date());
        return new ResponseEntity<String>("Welcome, SpringMVC Rest Demo is running. The time on the server is: "
                + formattedDate, HttpStatus.OK);
    }
}
