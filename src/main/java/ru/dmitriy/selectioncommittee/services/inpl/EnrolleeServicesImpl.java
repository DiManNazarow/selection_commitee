package ru.dmitriy.selectioncommittee.services.inpl;

import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.repositories.EnrolleRepository;
import ru.dmitriy.selectioncommittee.services.EnrolleeService;
import ru.dmitriy.selectioncommittee.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmitriy Nazarow on 12.03.17.
 */
public class EnrolleeServicesImpl implements EnrolleeService {

    @Autowired
    private EnrolleRepository enrolleRepository;

    private JsonParser parser;

    public EnrolleeServicesImpl(){
        parser = new JsonParser();
    }

    @Override
    public String addEnrollee(String enrolleeJson) {
        Enrollee enrollee = Enrollee.PARCEL.fromJSONObject(parser.parse(enrolleeJson).getAsJsonObject());
        return enrolleRepository.save(enrollee).getId().toString();
    }

    @Override
    public List<Enrollee> getAllEnrollee() {
        return (ArrayList<Enrollee>)enrolleRepository.findAll();
    }

    @Override
    public String getEnrolleeById(String idJson) {
        Long id = parser.parse(idJson).getAsJsonObject().get(Enrollee.JsonFieldName.ID).getAsLong();
        return enrolleRepository.findOne(id).asJSON().toString();
    }

    @Override
    public List<Enrollee> findEnrolleeByInitials(String initials) {
        //String initials = parser.parse(initialsJson).getAsJsonObject().get(Enrollee.JsonFieldName.INITIALS).getAsString();
        ArrayList<Enrollee> enrollees = (ArrayList<Enrollee>)enrolleRepository.findAll();
        return enrollees.stream().filter(
                e -> e.getName().toLowerCase().contains(initials.toLowerCase()) ||
        e.getSurname().toLowerCase().contains(initials.toLowerCase()) ||
        e.getPatronymic().toLowerCase().contains(initials.toLowerCase())).collect(Collectors.toList());
    }
}
