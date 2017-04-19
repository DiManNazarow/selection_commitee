package ru.dmitriy.selectioncommittee.services;

import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.StudyInfo;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 17.03.17.
 */
public interface StudyInfoService {

    List<StudyInfo> getEnrolleeStudyInfo(Enrollee enrollee);

    Long save(StudyInfo studyInfo);

    void save(List<StudyInfo> studyInfoList);

    List<StudyInfo> getAll();

    List<StudyInfo> findByUniversity(String university);

    List<StudyInfo> findBySpeciality(String speciality);
}
