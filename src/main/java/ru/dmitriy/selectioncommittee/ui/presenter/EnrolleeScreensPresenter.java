package ru.dmitriy.selectioncommittee.ui.presenter;

import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.StudyInfo;
import ru.dmitriy.selectioncommittee.ui.Presenter;
import ru.dmitriy.selectioncommittee.ui.screens.enrollee.AddNewEnrolleeScreen;
import ru.dmitriy.selectioncommittee.ui.screens.enrollee.BindUniversityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.enrollee.EnrolleeInfoScreen;
import ru.dmitriy.selectioncommittee.ui.screens.enrollee.EnrolleeListScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.UniversityInfoScreen;

/**
 * Created by diman on 28.03.17.
 */
public class EnrolleeScreensPresenter extends Presenter {

    private AddNewEnrolleeScreen addNewEnrolleeScreen;

    private BindUniversityScreen bindUniversityScreen;

    private EnrolleeListScreen enrolleeListScreen;

    private UniversityInfoScreen universityInfoScreen;

    private EnrolleeInfoScreen enrolleeInfoScreen;

    public interface OnEnrolleeAddListener{
        void addEnrollee(Enrollee enrollee);
    }

    public interface OnStudyInfoBuildListener{
        void studyInfoBuild(StudyInfo studyInfo);
    }

    public interface ShowEnrolleeListener{
        void showEnrollee(Enrollee enrollee);
    }

    private OnEnrolleeAddListener enrolleeAddListener;

    private OnStudyInfoBuildListener studyInfoBuildListener;

    private ShowEnrolleeListener showEnrolleeListener;

    public void setEnrolleeAddListener(OnEnrolleeAddListener enrolleeAddListener) {
        this.enrolleeAddListener = enrolleeAddListener;
    }

    public void setStudyInfoBuildListener(OnStudyInfoBuildListener studyInfoBuildListener) {
        this.studyInfoBuildListener = studyInfoBuildListener;
    }

    public void setShowEnrolleeListener(ShowEnrolleeListener showEnrolleeListener) {
        this.showEnrolleeListener = showEnrolleeListener;
    }

    public AddNewEnrolleeScreen getAddNewEnrolleeScreen() {
        return addNewEnrolleeScreen;
    }

    public void setAddNewEnrolleeScreen(AddNewEnrolleeScreen addNewEnrolleeScreen) {
        this.addNewEnrolleeScreen = addNewEnrolleeScreen;
    }

    public BindUniversityScreen getBindUniversityScreen() {
        return bindUniversityScreen;
    }

    public void setBindUniversityScreen(BindUniversityScreen bindUniversityScreen) {
        this.bindUniversityScreen = bindUniversityScreen;
    }

    public EnrolleeListScreen getEnrolleeListScreen() {
        return enrolleeListScreen;
    }

    public void setEnrolleeListScreen(EnrolleeListScreen enrolleeListScreen) {
        this.enrolleeListScreen = enrolleeListScreen;
    }

    public UniversityInfoScreen getUniversityInfoScreen() {
        return universityInfoScreen;
    }

    public void setUniversityInfoScreen(UniversityInfoScreen universityInfoScreen) {
        this.universityInfoScreen = universityInfoScreen;
    }

    public EnrolleeInfoScreen getEnrolleeInfoScreen() {
        return enrolleeInfoScreen;
    }

    public void setEnrolleeInfoScreen(EnrolleeInfoScreen enrolleeInfoScreen) {
        this.enrolleeInfoScreen = enrolleeInfoScreen;
    }

    public void addEnrollee(Enrollee enrollee){
        if (enrolleeAddListener != null){
            enrolleeAddListener.addEnrollee(enrollee);
        }
    }

    public void buildStudyInfo(StudyInfo studyInfo){
        if (studyInfoBuildListener != null){
            studyInfoBuildListener.studyInfoBuild(studyInfo);
        }
    }

    public void showEnrollee(Enrollee enrollee){
        if (showEnrolleeListener != null){
            showEnrolleeListener.showEnrollee(enrollee);
        }
    }
}
