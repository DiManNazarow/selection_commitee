package ru.dmitriy.selectioncommittee.ui.presenter;

import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Presenter;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.AddNewPulpitScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.AddSpecialityToPulpitScreen;

import java.util.List;

/**
 * Created by diman on 28.03.17.
 */
public class AddNewPulpitScreenPresenter extends Presenter {

    private AddNewPulpitScreen addNewPulpitScreen;

    private AddSpecialityToPulpitScreen addSpecialityToPulpitScreen;

    public interface SpecialityAddListener{
        void specialityAdded(List<Speciality> specialities);
    }

    private SpecialityAddListener specialityAddListener;

    public void setSpecialityAddListener(SpecialityAddListener specialityAddListener) {
        this.specialityAddListener = specialityAddListener;
    }

    public AddNewPulpitScreen getAddNewPulpitScreen() {
        return addNewPulpitScreen;
    }

    public void setAddNewPulpitScreen(AddNewPulpitScreen addNewPulpitScreen) {
        this.addNewPulpitScreen = addNewPulpitScreen;
    }

    public AddSpecialityToPulpitScreen getAddSpecialityToPulpitScreen() {
        return addSpecialityToPulpitScreen;
    }

    public void setAddSpecialityToPulpitScreen(AddSpecialityToPulpitScreen addSpecialityToPulpitScreen) {
        this.addSpecialityToPulpitScreen = addSpecialityToPulpitScreen;
    }

    public void addSpeciality(List<Speciality> specialities){
        if (specialityAddListener != null){
            specialityAddListener.specialityAdded(specialities);
        }
    }
}
