package com.seyrancom.consulting.app.web;

import com.seyrancom.consulting.app.domain.model.Student;
import com.seyrancom.consulting.app.domain.model.Greeting;
import com.seyrancom.consulting.core.web.AppController;
import com.seyrancom.consulting.core.web.common.AbstractController;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.atomic.AtomicLong;

@AppController
public class GreetingController extends AbstractController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ResponseBody
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public ModelAndView sayHello() {
        /*model.addObject("greeting", "Hello World from welcome.jsp");
        return "/WEB-INF/views/welcome.jsp";*/
        return new ModelAndView("welcome", "greeting", "Hello World from welcome.jsp");
    }

    @RequestMapping(value = "/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from welcome.html");
        return "/WEB-INF/views/welcome.html";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("/WEB-INF/views/student.jsp", "command", new Student());
    }

    @RequestMapping(value = "/student/result", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("command") Student student,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error";
        }
        //ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("name", student.getFirstName());
        model.addAttribute("sex", student.getSex());
        model.addAttribute("id", student.getId());
        model.addAttribute("student", student);

        return "/WEB-INF/views/result.jsp";
    }
}