package ru.dmitriy.selectioncommittee.ui.manager;

import com.vaadin.navigator.Navigator;
import ru.dmitriy.selectioncommittee.ui.presenter.UniversityPresenter;
import ru.dmitriy.selectioncommittee.ui.screens.*;
import ru.dmitriy.selectioncommittee.ui.screens.enrollee.AddNewEnrolleeScreen;
import ru.dmitriy.selectioncommittee.ui.screens.enrollee.EnrolleeListScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.AddNewPulpitScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitListScreen;
import ru.dmitriy.selectioncommittee.ui.screens.speciality.AddNewSpecialityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.speciality.SpecialityListScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.AddNewUniversityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.AddPulpitToUniversityScreen;
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
        UniversityPresenter universityPresenter = new UniversityPresenter();
        universityPresenter.setAddNewUniversityScreen(addNewUniversityScreen);
        universityPresenter.setAddPulpitToUniversityScreen(addPulpitToUniversityScreen);
        universityPresenter.setUniversityListScreen(universityListScreen);
        universityListScreen.setPresenter(universityPresenter);
        addPulpitToUniversityScreen.setPresenter(universityPresenter);
        addNewUniversityScreen.setPresenter(universityPresenter);

        navigator.addView(UniversityListScreen.UNIVERSITY_SCREEN_LIST, universityListScreen);
        navigator.addView(PulpitListScreen.PULPIT_SCREEN_LIST, new PulpitListScreen());
        navigator.addView(SpecialityListScreen.SPECIALITY_LIST_SCREEN, new SpecialityListScreen());
        navigator.addView(EnrolleeListScreen.ENROLLEE_LIST_SCREEN, new EnrolleeListScreen());
        navigator.addView(AddNewUniversityScreen.ADD_NEW_UNIVERSITY_SCREEN, addNewUniversityScreen);
        navigator.addView(AddPulpitToUniversityScreen.ADD_PULPIT_TO_UNIVERSITY_SCREEN, addPulpitToUniversityScreen);
        navigator.addView(AddNewPulpitScreen.ADD_NEW_PULPIT_SCREEN, new AddNewPulpitScreen());
        navigator.addView(AddNewSpecialityScreen.ADD_NEW_SPECIALITY_SCREEN, new AddNewSpecialityScreen());
        navigator.addView(AddNewEnrolleeScreen.ADD_NEW_ENROLLEE_SCREEN, new AddNewEnrolleeScreen());
    }

    public void navigateTo(String screenName){
        navigator.navigateTo(screenName);
    }

    public static class ScreenNotInitException extends Exception{
        public ScreenNotInitException(){
            super("Screen Manger not initialised. Call init() before getInstance()");
        }
    }

}
