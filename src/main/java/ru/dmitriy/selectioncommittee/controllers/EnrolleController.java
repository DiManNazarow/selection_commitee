package ru.dmitriy.selectioncommittee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dmitriy.selectioncommittee.repositories.EnrolleRepository;
import ru.dmitriy.selectioncommittee.services.EnrolleeService;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */

@Controller
@RequestMapping(path = "/enrollee")
public class EnrolleController {

    @Autowired
    private EnrolleeService enrolleeService;

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public @ResponseBody String addEnrollee(@RequestBody String enrolleeJson){
        return enrolleeService.addEnrollee(enrolleeJson);
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody String getAllEnrollee(){
        return enrolleeService.getAllEnrollee();
    }

    @RequestMapping(path = "/findByInitials")
    public @ResponseBody String findByInitials(@RequestBody String initials){
        return enrolleeService.findEnrolleeByInitials(initials);
    }

}
