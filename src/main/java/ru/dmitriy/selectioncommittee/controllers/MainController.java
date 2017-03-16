package ru.dmitriy.selectioncommittee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dmitriy.selectioncommittee.ui.VaadinUI;

/**
 * Created by Dmitriy Nazarow on 17.03.17.
 */

@Controller
public class MainController {

    @GetMapping(path = "/main")
    public @ResponseBody String getMain(){
        return "Добро пожаловать на сайт приемной комиссий";
    }

}
