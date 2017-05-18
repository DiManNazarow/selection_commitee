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

    List<StudyInfo> findBySpecialityName(String speciality);

    List<StudyInfo> findBySpecialityNumber(String number);

    List<StudyInfo> searchByPulpit(String pulpit);

    List<StudyInfo> searchByInitials(String initials);

    List<StudyInfo> chooseByUniversityAndStudyState(String university, StudyInfo.Status studyState);

    List<StudyInfo> chooseBySpecialityNameAndStudyState(String speciality, StudyInfo.Status studyState);

    List<StudyInfo> chooseBySpecialityNumberAndStudyState(String speciality, StudyInfo.Status studyState);

    void delete(StudyInfo studyInfo);

    int enrollCountOfEndedUniversity(String universityName);

    long averageAgeOfSpeciality(int specialityCode);

    long averageAgeOfSpeciality(String specialityName);
}
