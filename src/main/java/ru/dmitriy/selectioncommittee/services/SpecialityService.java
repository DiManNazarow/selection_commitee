package ru.dmitriy.selectioncommittee.services;

import ru.dmitriy.selectioncommittee.models.Speciality;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public interface SpecialityService {

    String saveSpeciality(Speciality speciality);

    List<Speciality> getAllSpecialities();

}
