package ru.dmitriy.selectioncommittee.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.repositories.PulpitRepository;
import ru.dmitriy.selectioncommittee.services.PulpitService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diman on 20.03.17.
 */
public class PulpitServiceImpl implements PulpitService {

    @Autowired
    private PulpitRepository pulpitRepository;

    @Override
    public List<Pulpit> getAllPulpit() {
        return (ArrayList<Pulpit>)pulpitRepository.findAll();
    }

    @Override
    public String savePulpit(Pulpit pulpit) {
        return pulpitRepository.save(pulpit).getId().toString();
    }
}
