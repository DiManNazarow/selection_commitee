package ru.dmitriy.selectioncommittee.services;

/**
 * Created by Dmitriy Nazarow on 12.03.17.
 */
public interface InstituteService {

    String getAllInstitute();

    String addInstitute(String instituteJson);

    String getInstituteById(String jsonId);

    String findInstituteByName(String name);

}
