package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import com.vaadin.navigator.ViewChangeListener;
import ru.dmitriy.selectioncommittee.models.*;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;


/**
 * Created by Dmitriy Nazarow on 22.04.17.
 */
public class EditStudyInfoScreen extends BindUniversityScreen implements EnrolleeScreensPresenter.EditStudyInfoListener{

    public static final String EDIT_STUDY_INFO_SCREEN = "edit_study_info_screen";

    public EditStudyInfoScreen() {
        super();
    }

    @Override
    protected void onPresenterSet(){
        getPresenter().setEditStudyInfoListener(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        institutionList.setItems(institution);
        institutionList.select(institution);
        pulpitList.setItems(institution.getPulpits());
        pulpitList.select(pulpit);
        specialityList.setItems(pulpit.getSpecialities());
        specialityList.select(speciality);
    }

    @Override
    public void editStudyInfo(StudyInfo studyInfo) {
        this.studyInfo = studyInfo;
        enrollee = studyInfo.getEnrollee();
        institution = studyInfo.getInstitution();
        pulpit = studyInfo.getPulpit();
        studyState = studyInfo.getStudyState();
    }

    @Override
    protected void buildStudyInfo(){
        if (enrollee != null) {
            studyInfo.setEnrollee(enrollee);
        }
        if (institution != null){
            studyInfo.setInstitution(institution);
        }
        if (pulpit != null){
            studyInfo.setPulpit(pulpit);
        }
        if (speciality != null){
            studyInfo.setSpeciality(speciality);
        }
        if (studyState != null){
            studyInfo.setStudyState(studyState);
        }
        ServiceProvider.instance().getStudyInfoService().save(studyInfo);
    }
}
