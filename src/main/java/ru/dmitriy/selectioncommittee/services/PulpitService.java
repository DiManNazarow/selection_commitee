package ru.dmitriy.selectioncommittee.services;

import ru.dmitriy.selectioncommittee.models.Pulpit;

import java.util.List;

/**
 * Created by diman on 20.03.17.
 */
public interface PulpitService {

    List<Pulpit> getAllPulpit();

    String savePulpit(Pulpit pulpit);

    void savePulpits(List<Pulpit> pulpits);

}
