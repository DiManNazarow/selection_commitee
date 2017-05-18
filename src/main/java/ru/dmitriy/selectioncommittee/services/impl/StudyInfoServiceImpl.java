package ru.dmitriy.selectioncommittee.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.StudyInfo;
import ru.dmitriy.selectioncommittee.repositories.EnrolleRepository;
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

    @Autowired
    private EnrolleRepository enrolleRepository;

    @Override
    public List<StudyInfo> getEnrolleeStudyInfo(Enrollee enrollee){
        ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        return studyInfos.stream().filter(s -> s.getEnrollee().getId().equals(enrollee.getId())).collect(Collectors.toList());
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
        return studyInfoRepository.findByInstitutionNameContaining(university);
        //ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        //return studyInfos.stream().filter(s -> s.getInstitution().getName().toLowerCase().contains(university.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<StudyInfo> findBySpeciality(String speciality) {
        return studyInfoRepository.findBySpecialityNameContaining(speciality);
        //ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        //return studyInfos.stream().filter(s -> s.getSpeciality().getName().toLowerCase().contains(speciality.toLowerCase()) || s.getSpeciality().getSpecialNumber().toLowerCase().contains(speciality.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<StudyInfo> searchByPulpit(String pulpit) {
        return studyInfoRepository.findByPulpitNameContaining(pulpit);
        //ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        //return studyInfos.stream().filter(s -> s.getPulpit().getName().toLowerCase().contains(pulpit.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<StudyInfo> searchByInitials(String initials) {
        return studyInfoRepository.findByEnrolleeNameOrEnrolleeSurnameOrEnrolleePatronymicContaining(initials, initials, initials);
    }

    @Override
    public List<StudyInfo> chooseByUniversityAndStudyState(String university, StudyInfo.Status studyState) {
        return studyInfoRepository.findByInstitutionNameContainingAndStatus(university, studyState.getState());
        //ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        //return studyInfos.stream().filter(s -> s.getInstitution().getName().toLowerCase().contains(university.toLowerCase())
        //    && s.getStudyState().equals(studyState)).collect(Collectors.toList());
    }

    @Override
    public List<StudyInfo> chooseBySpecialityNameAndStudyState(String speciality, StudyInfo.Status studyState) {
        return studyInfoRepository.findBySpecialityNameContainingAndStatus(speciality, studyState.getState());
        //ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        //return studyInfos.stream().filter(s -> s.getSpeciality().getName().toLowerCase().contains(speciality.toLowerCase())
        //    && s.getStudyState().equals(studyState)).collect(Collectors.toList());
    }

    @Override
    public List<StudyInfo> chooseBySpecialityNumberAndStudyState(String speciality, StudyInfo.Status studyState) {
        return studyInfoRepository.findBySpecialitySpecialNumberContainingAndStatus(speciality, studyState.getState());
        //ArrayList<StudyInfo> studyInfos = (ArrayList<StudyInfo>) studyInfoRepository.findAll();
        //return studyInfos.stream().filter(s -> s.getSpeciality().getName().toLowerCase().contains(speciality.toLowerCase())
        //    && s.getStudyState().equals(studyState)).collect(Collectors.toList());
    }

    @Override
    public void delete(StudyInfo studyInfo) {

        Enrollee enrollee = studyInfo.getEnrollee();
        enrollee.getStudyInfo().remove(studyInfo);

        enrollee = enrolleRepository.save(enrollee);

        studyInfo.setEnrollee(null);
        studyInfoRepository.save(studyInfo);

        studyInfoRepository.delete(studyInfo.getId());

        if (enrollee.getStudyInfo().isEmpty()){
            enrolleRepository.delete(enrollee);
        }
    }

    @Override
    public int enrollCountOfEndedUniversity(String universityName) {
        List<StudyInfo> infoList = (ArrayList<StudyInfo>)studyInfoRepository.findAll();
        return (int)infoList.stream().filter(i -> i.getInstitution().getName().toLowerCase().contains(universityName.toLowerCase()) && i.getStudyState().equals(StudyInfo.Status.ENDED)).count();
    }

    @Override
    public long averageAgeOfSpeciality(int specialityCode) {
        List<StudyInfo> infoList = (ArrayList<StudyInfo>)studyInfoRepository.findAll();
        infoList = infoList.stream().filter(i -> i.getSpeciality().getSpecialNumber().toLowerCase().contains(String.valueOf(specialityCode)) && i.getStudyState().equals(StudyInfo.Status.ENDED)).collect(Collectors.toList());
        List<Long> ages = new ArrayList<>();
        for (StudyInfo studyInfo : infoList){
            ages.add(studyInfo.getEnrollee().getAge());
        }
        return calculateAverage(ages);
    }

    private long calculateAverage(List<Long> ages){
        long sum = 0;
        for (Long value : ages){
            sum +=value;
        }
        return sum/ages.size();
    }

    @Override
    public long averageAgeOfSpeciality(String specialityName) {
        List<StudyInfo> infoList = (ArrayList<StudyInfo>)studyInfoRepository.findAll();
        infoList = infoList.stream().filter(i -> i.getSpeciality().getName().toLowerCase().contains(specialityName.toLowerCase()) && i.getStudyState().equals(StudyInfo.Status.ENDED)).collect(Collectors.toList());
        List<Long> ages = new ArrayList<>();
        for (StudyInfo studyInfo : infoList){
            ages.add(studyInfo.getEnrollee().getAge());
        }
        return calculateAverage(ages);
    }
}
