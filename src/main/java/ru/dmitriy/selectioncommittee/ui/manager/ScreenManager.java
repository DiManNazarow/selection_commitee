package ru.dmitriy.selectioncommittee.ui.manager;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.presenter.PulpitScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.presenter.SpecialityScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.presenter.UniversityScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.screens.*;
import ru.dmitriy.selectioncommittee.ui.screens.enrollee.*;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.AddNewPulpitScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.AddSpecialityToPulpitScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitInfoScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitListScreen;
import ru.dmitriy.selectioncommittee.ui.screens.speciality.AddNewSpecialityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.speciality.SpecialityInfoScreen;
import ru.dmitriy.selectioncommittee.ui.screens.speciality.SpecialityListScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.AddNewUniversityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.AddPulpitToUniversityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.UniversityInfoScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.UniversityListScreen;

/**
 * Created by diman on 20.03.17.
 */
public class ScreenManager {

    private static ScreenManager instance;

    private Navigator navigator;

    private ScreenManager(Navigator navigator){
        this.navigator = navigator;
        addScreens();
    }

    public static ScreenManager init(Navigator navigator){
        instance = new ScreenManager(navigator);
        return instance;
    }

    public static ScreenManager getInstance() {
        if (instance == null){
            try {
                throw new ScreenNotInitException();
            } catch (ScreenNotInitException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private void addScreens(){
        navigator.addView("", new StartScreen());
        navigator.addView("/", new StartScreen());
        navigator.addView(StartScreen.START_SCREEN, new StartScreen());

        UniversityListScreen universityListScreen = new UniversityListScreen();
        AddPulpitToUniversityScreen addPulpitToUniversityScreen = new AddPulpitToUniversityScreen();
        AddNewUniversityScreen addNewUniversityScreen = new AddNewUniversityScreen();
        UniversityInfoScreen universityInfoScreen = new UniversityInfoScreen();
        UniversityScreensPresenter universityPresenter = new UniversityScreensPresenter();
        universityPresenter.setAddNewUniversityScreen(addNewUniversityScreen);
        universityPresenter.setAddPulpitToUniversityScreen(addPulpitToUniversityScreen);
        universityPresenter.setUniversityListScreen(universityListScreen);
        universityPresenter.setUniversityInfoScreen(universityInfoScreen);
        universityListScreen.setPresenter(universityPresenter);
        addPulpitToUniversityScreen.setPresenter(universityPresenter);
        addNewUniversityScreen.setPresenter(universityPresenter);
        universityInfoScreen.setPresenter(universityPresenter);

        AddNewPulpitScreen addNewPulpitScreen = new AddNewPulpitScreen();
        AddSpecialityToPulpitScreen addSpecialityToPulpitScreen = new AddSpecialityToPulpitScreen();
        PulpitInfoScreen pulpitInfoScreen = new PulpitInfoScreen();
        PulpitListScreen pulpitListScreen = new PulpitListScreen();
        PulpitScreensPresenter pulpitScreensPresenter = new PulpitScreensPresenter();
        pulpitScreensPresenter.setAddNewPulpitScreen(addNewPulpitScreen);
        pulpitScreensPresenter.setAddSpecialityToPulpitScreen(addSpecialityToPulpitScreen);
        pulpitScreensPresenter.setPulpitInfoScreen(pulpitInfoScreen);
        pulpitScreensPresenter.setPulpitListScreen(pulpitListScreen);
        addNewPulpitScreen.setPresenter(pulpitScreensPresenter);
        addSpecialityToPulpitScreen.setPresenter(pulpitScreensPresenter);
        pulpitInfoScreen.setPresenter(pulpitScreensPresenter);
        pulpitListScreen.setPresenter(pulpitScreensPresenter);

        SpecialityScreensPresenter specialityScreensPresenter = new SpecialityScreensPresenter();

        AddNewSpecialityScreen addNewSpecialityScreen = new AddNewSpecialityScreen();

        SpecialityInfoScreen specialityInfoScreen = new SpecialityInfoScreen();

        SpecialityListScreen specialityListScreen = new SpecialityListScreen();

        specialityScreensPresenter.setAddNewSpecialityScreen(addNewSpecialityScreen);
        specialityScreensPresenter.setSpecialityInfoScreen(specialityInfoScreen);
        specialityScreensPresenter.setSpecialityListScreen(specialityListScreen);

        addNewSpecialityScreen.setPresenter(specialityScreensPresenter);
        specialityListScreen.setPresenter(specialityScreensPresenter);
        specialityInfoScreen.setPresenter(specialityScreensPresenter);

        EnrolleeScreensPresenter enrolleeScreensPresenter = new EnrolleeScreensPresenter();

        AddNewEnrolleeScreen addNewEnrolleeScreen = new AddNewEnrolleeScreen();
        BindUniversityScreen bindUniversityScreen = new BindUniversityScreen();
        EnrolleeListScreen enrolleeListScreen =  new EnrolleeListScreen();
        EnrolleeInfoScreen enrolleeInfoScreen = new EnrolleeInfoScreen();
        EditStudyInfoScreen editStudyInfoScreen = new EditStudyInfoScreen();

        enrolleeScreensPresenter.setAddNewEnrolleeScreen(addNewEnrolleeScreen);
        enrolleeScreensPresenter.setBindUniversityScreen(bindUniversityScreen);
        enrolleeScreensPresenter.setEnrolleeListScreen(enrolleeListScreen);
        enrolleeScreensPresenter.setEnrolleeInfoScreen(enrolleeInfoScreen);
        enrolleeScreensPresenter.setEditStudyInfoScreen(editStudyInfoScreen);

        addNewEnrolleeScreen.setPresenter(enrolleeScreensPresenter);
        bindUniversityScreen.setPresenter(enrolleeScreensPresenter);
        enrolleeListScreen.setPresenter(enrolleeScreensPresenter);
        enrolleeInfoScreen.setPresenter(enrolleeScreensPresenter);
        editStudyInfoScreen.setPresenter(enrolleeScreensPresenter);

        navigator.addView(UniversityListScreen.UNIVERSITY_SCREEN_LIST, universityListScreen);;
        navigator.addView(PulpitListScreen.PULPIT_SCREEN_LIST, pulpitListScreen);
        navigator.addView(AddNewUniversityScreen.ADD_NEW_UNIVERSITY_SCREEN, addNewUniversityScreen);
        navigator.addView(UniversityInfoScreen.UNIVERSITY_INFO_SCREEN, universityInfoScreen);
        navigator.addView(AddPulpitToUniversityScreen.ADD_PULPIT_TO_UNIVERSITY_SCREEN, addPulpitToUniversityScreen);
        navigator.addView(AddNewPulpitScreen.ADD_NEW_PULPIT_SCREEN, addNewPulpitScreen);
        navigator.addView(AddSpecialityToPulpitScreen.ADD_SPECIALITY_TO_PULPIT_SCREEN, addSpecialityToPulpitScreen);
        navigator.addView(PulpitInfoScreen.PULPIT_INFO_SCREEN, pulpitInfoScreen);
        navigator.addView(EditStudyInfoScreen.EDIT_STUDY_INFO_SCREEN, editStudyInfoScreen);

        navigator.addView(AddNewSpecialityScreen.ADD_NEW_SPECIALITY_SCREEN, addNewSpecialityScreen);
        navigator.addView(SpecialityInfoScreen.SPECIALITY_INFO_SCREEN, specialityInfoScreen);
        navigator.addView(SpecialityListScreen.SPECIALITY_LIST_SCREEN, specialityListScreen);

        navigator.addView(AddNewEnrolleeScreen.ADD_NEW_ENROLLEE_SCREEN, addNewEnrolleeScreen);
        navigator.addView(BindUniversityScreen.BIND_UNIVERSITY_SCREEN, bindUniversityScreen);
        navigator.addView(EnrolleeListScreen.ENROLLEE_LIST_SCREEN, enrolleeListScreen);
        navigator.addView(EnrolleeInfoScreen.ENROLLEE_INFO_SCREEN, enrolleeInfoScreen);
    }

    public void navigateTo(String screenName){
        navigator.navigateTo(screenName);
    }

    public void navigateBack(){
        Page.getCurrent().getJavaScript().execute("history.back()");
    }

    public static class ScreenNotInitException extends Exception{
        public ScreenNotInitException(){
            super("Screen Manger not initialised. Call init() before getInstance()");
        }
    }

}
