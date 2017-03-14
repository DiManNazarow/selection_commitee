package ru.dmitriy.selectioncommittee.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dmitriy.selectioncommittee.Fixture;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.repositories.EnrolleRepository;
import ru.dmitriy.selectioncommittee.repositories.InstitutionRepository;
import ru.dmitriy.selectioncommittee.repositories.PulpitRepository;
import ru.dmitriy.selectioncommittee.repositories.SpecialityRepository;
import ru.dmitriy.selectioncommittee.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */


@Controller
@RequestMapping(path = "/test")
public class TestController {

    private String response = "Id абитуриента %s, Id уиверситета %s";

    @Autowired
    private EnrolleRepository enrolleRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private PulpitRepository pulpitRepository;
    @Autowired
    private SpecialityRepository specialityRepository;

    @GetMapping(path = "/test")
    public @ResponseBody String test(){

        Fixture fixture = new Fixture();

        Institution penzGTU = fixture.getPenzGTUInstitute();

        //Pulpit pulpit = fixture.getPenzGTUVmis();

        Institution PGU = fixture.getPGUInstitute();

        Enrollee enrollee = fixture.getEnrollee();

        //Speciality speciality = fixture.getHardPenzGTU();


        //specialityRepository.save(speciality);

        //Speciality s = specialityRepository.findOne(1l);

        //s.setPulpit(pulpit);

        //pulpitRepository.save(pulpit);
        //specialityRepository.save(s);
        enrolleRepository.save(enrollee);
        institutionRepository.save(penzGTU);
        institutionRepository.save(PGU);


        return "Success";

    }

}
