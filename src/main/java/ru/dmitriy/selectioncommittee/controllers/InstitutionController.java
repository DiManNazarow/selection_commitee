package ru.dmitriy.selectioncommittee.controllers;

import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.repositories.InstitutionRepository;
import ru.dmitriy.selectioncommittee.services.InstituteService;
import ru.dmitriy.selectioncommittee.utils.JsonUtils;

import java.util.ArrayList;

/**
 * Created by Dmitriy Nazarow on 12.03.17.
 */

@Controller
@RequestMapping(path = "/institute")
public class InstitutionController {

    @Autowired
    private InstituteService instituteService;

    @GetMapping(path = "/getAll")
    public @ResponseBody String getAllInstitute(){
        return instituteService.getAllInstitute();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String addInstitute(@RequestBody String requestBody){
        return instituteService.addInstitute(requestBody);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getById")
    public @ResponseBody String getInstituteById(@RequestBody String requestBody){
        return instituteService.getInstituteById(requestBody);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/findByName")
    public @ResponseBody String findInstituteByName(@RequestBody String requestBody){
        return instituteService.findInstituteByName(requestBody);
    }
}
