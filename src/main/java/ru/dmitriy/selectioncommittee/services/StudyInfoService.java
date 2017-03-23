package ru.dmitriy.selectioncommittee.services;

import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.StudyInfo;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 17.03.17.
 */
public interface StudyInfoService {

    public List<StudyInfo> getEnrolleeStudyInfo(Enrollee enrollee);

}