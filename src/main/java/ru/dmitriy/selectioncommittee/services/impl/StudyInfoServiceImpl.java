package ru.dmitriy.selectioncommittee.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.StudyInfo;
import ru.dmitriy.selectioncommittee.repositories.StudyInfoRepository;
import ru.dmitriy.selectioncommittee.services.StudyInfoService;

import java.util.List;

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

}
