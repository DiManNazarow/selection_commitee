package ru.dmitriy.selectioncommittee.services.impl;

import org.jsoup.select.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.StudyInfo;
import ru.dmitriy.selectioncommittee.repositories.StudyInfoRepository;
import ru.dmitriy.selectioncommittee.services.StudyInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmitriy Nazarow on 17.03.17.
 */
public class StudyInfoServiceImpl implements StudyInfoService {

    @Autowired
    private StudyInfoRepository studyInfoRepository;

    @Override
    public List<StudyInfo> getEnrolleeStudyInfo(Enrollee enrollee){
        return enrollee.getStudyInfo();
    }

    @Override
    public Long save(StudyInfo studyInfo) {
        return studyInfoRepository.save(studyInfo).getId();
    }

    @Override
    public void save(List<StudyInfo> studyInfoList) {
        studyInfoRepository.save(studyInfoList);
    }

    @Override
    public List<StudyInfo> getAll() {
        return (ArrayList<StudyInfo>)studyInfoRepository.findAll();
    }

    @Override
    public List<StudyInfo> findByUniversity(String university) {
        ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        return studyInfos.stream().filter(s -> s.getInstitution().getName().toLowerCase().contains(university.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<StudyInfo> findBySpeciality(String speciality) {
        ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        return studyInfos.stream().filter(s -> s.getSpeciality().getName().toLowerCase().contains(speciality.toLowerCase())).collect(Collectors.toList());
    }

}
