package com.seyrancom.consulting.app.web;

import com.seyrancom.consulting.app.domain.model.Student;
import com.seyrancom.consulting.core.web.AppController;
import com.seyrancom.consulting.core.web.common.AbstractController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AppController
@RequestMapping("/student")
public class StudentController extends AbstractController{

    @RequestMapping("/hello/{player}")
    @ResponseBody
    public ResponseEntity<String> message(@PathVariable String player) {

        ResponseEntity<String> re = new ResponseEntity<String>("Hello ".concat(player).toString(), HttpStatus.OK);
        return re;
    }
    /*
     * This method will serve as default GET handler.
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "enroll";
    }

    /*
     * This method will be called on form submission, handling POST request
     * It also validates the user input
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveRegistration(@Valid Student student, BindingResult result, ModelMap model){

        if(result.hasErrors()) {
            return "enroll";
        }

        model.addAttribute("success", "Dear "+ student.getFirstName()+" , your Registration completed successfully");
        return "success";
    }

    /*
     * Method used to populate the Section list in view.
     * Note that here you can call external systems to provide real data.
     */
    @ModelAttribute("sections")
    public Map<String, List<String>> initializeSections() {
        List<String> sections = new ArrayList<String>();
        sections.add("Graduate");
        sections.add("Post Graduate");
        sections.add("Research");

        Map<String, List<String>> sectionData = new HashMap<>();
        sectionData.put("sections", sections);

        return sectionData;
    }

    /*
     * Method used to populate the country list in view.
     * Note that here you can call external systems to provide real data.
     */
    @ModelAttribute("countries")
    public List<String> initializeCountries() {

        List<String> countries = new ArrayList<String>();
        countries.add("USA");
        countries.add("CANADA");
        countries.add("FRANCE");
        countries.add("GERMANY");
        countries.add("ITALY");
        countries.add("OTHER");
        return countries;
    }

    /*
     * Method used to populate the subjects list in view.
     * Note that here you can call external systems to provide real data.
     */
    @ModelAttribute("subjects")
    public List<String> initializeSubjects() {

        List<String> subjects = new ArrayList<String>();
        subjects.add("Physics");
        subjects.add("Chemistry");
        subjects.add("Life Science");
        subjects.add("Political Science");
        subjects.add("Computer Science");
        subjects.add("Mathmatics");
        return subjects;
    }

}