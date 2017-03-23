package ru.dmitriy.selectioncommittee.ui.manager;

import ru.dmitriy.selectioncommittee.services.*;

/**
 * Created by Dmitriy Nazarow on 23.03.17.
 */
public class ServiceProvider {

    private static ServiceProvider instance;

    private EnrolleeService enrolleeService;

    private InstituteService instituteService;

    private PulpitService pulpitService;

    private StudyInfoService studyInfoService;

    private SpecialityService specialityService;

    public static ServiceProvider instance(){
        if (instance == null){
            instance = new ServiceProvider();
        }
        return instance;
    }

    private ServiceProvider(){

    }

    public EnrolleeService getEnrolleeService() {
        return enrolleeService;
    }

    public void setEnrolleeService(EnrolleeService enrolleeService) {
        this.enrolleeService = enrolleeService;
    }

    public InstituteService getInstituteService() {
        return instituteService;
    }

    public void setInstituteService(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    public PulpitService getPulpitService() {
        return pulpitService;
    }

    public void setPulpitService(PulpitService pulpitService) {
        this.pulpitService = pulpitService;
    }

    public StudyInfoService getStudyInfoService() {
        return studyInfoService;
    }

    public void setStudyInfoService(StudyInfoService studyInfoService) {
        this.studyInfoService = studyInfoService;
    }

    public SpecialityService getSpecialityService() {
        return specialityService;
    }

    public void setSpecialityService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }
}
