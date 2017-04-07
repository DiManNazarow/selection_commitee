package ru.dmitriy.selectioncommittee.ui.presenter;

import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Presenter;
import ru.dmitriy.selectioncommittee.ui.screens.speciality.AddNewSpecialityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.speciality.SpecialityInfoScreen;
import ru.dmitriy.selectioncommittee.ui.screens.speciality.SpecialityListScreen;

/**
 * Created by diman on 28.03.17.
 */
public class SpecialityScreensPresenter extends Presenter {

    private AddNewSpecialityScreen addNewSpecialityScreen;

    private SpecialityInfoScreen specialityInfoScreen;

    private SpecialityListScreen specialityListScreen;

    public interface OnShowSpecialityListener{
        void showSpeciality(Speciality speciality);
    }

    private OnShowSpecialityListener showSpecialityListener;

    public void setShowSpecialityListener(OnShowSpecialityListener showSpecialityListener) {
        this.showSpecialityListener = showSpecialityListener;
    }

    public AddNewSpecialityScreen getAddNewSpecialityScreen() {
        return addNewSpecialityScreen;
    }

    public void setAddNewSpecialityScreen(AddNewSpecialityScreen addNewSpecialityScreen) {
        this.addNewSpecialityScreen = addNewSpecialityScreen;
    }

    public SpecialityInfoScreen getSpecialityInfoScreen() {
        return specialityInfoScreen;
    }

    public void setSpecialityInfoScreen(SpecialityInfoScreen specialityInfoScreen) {
        this.specialityInfoScreen = specialityInfoScreen;
    }

    public SpecialityListScreen getSpecialityListScreen() {
        return specialityListScreen;
    }

    public void setSpecialityListScreen(SpecialityListScreen specialityListScreen) {
        this.specialityListScreen = specialityListScreen;
    }

    public void showSpeciality(Speciality speciality){
        if (showSpecialityListener != null){
            showSpecialityListener.showSpeciality(speciality);
        }
    }
}
