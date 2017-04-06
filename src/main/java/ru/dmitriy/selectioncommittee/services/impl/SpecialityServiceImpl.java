package ru.dmitriy.selectioncommittee.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.repositories.SpecialityRepository;
import ru.dmitriy.selectioncommittee.services.SpecialityService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public String saveSpeciality(Speciality speciality) {
        return specialityRepository.save(speciality).getId().toString();
    }

    @Override
    public List<Speciality> getAllSpecialities() {
        return (ArrayList<Speciality>)specialityRepository.findAll();
    }

    @Override
    public void saveSpecialities(List<Speciality> specialities) {
        specialityRepository.save(specialities);
    }
}
