package ru.dmitriy.selectioncommittee.services;

import ru.dmitriy.selectioncommittee.models.Institution;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 12.03.17.
 */
public interface InstituteService {

    List<Institution> getAllInstitute();

    String addInstitute(Institution institute);

    String getInstituteById(String jsonId);

    String findInstituteByName(String name);

}
