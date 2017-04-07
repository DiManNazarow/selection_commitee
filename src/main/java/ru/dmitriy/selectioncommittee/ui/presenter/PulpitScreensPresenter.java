package ru.dmitriy.selectioncommittee.ui.presenter;

import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Presenter;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.AddNewPulpitScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.AddSpecialityToPulpitScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitInfoScreen;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitListScreen;

import java.util.List;

/**
 * Created by diman on 28.03.17.
 */
public class PulpitScreensPresenter extends Presenter {

    private AddNewPulpitScreen addNewPulpitScreen;

    private AddSpecialityToPulpitScreen addSpecialityToPulpitScreen;

    private PulpitInfoScreen pulpitInfoScreen;

    private PulpitListScreen pulpitListScreen;

    public interface SpecialityAddListener{
        void specialityAdded(List<Speciality> specialities);
    }

    public interface OnPulpitShowListener{
        void showPulpit(Pulpit pulpit);
    }

    private SpecialityAddListener specialityAddListener;

    private OnPulpitShowListener pulpitShowListener;

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

    public PulpitInfoScreen getPulpitInfoScreen() {
        return pulpitInfoScreen;
    }

    public void setPulpitInfoScreen(PulpitInfoScreen pulpitInfoScreen) {
        this.pulpitInfoScreen = pulpitInfoScreen;
    }

    public void setAddSpecialityToPulpitScreen(AddSpecialityToPulpitScreen addSpecialityToPulpitScreen) {
        this.addSpecialityToPulpitScreen = addSpecialityToPulpitScreen;
    }

    public PulpitListScreen getPulpitListScreen() {
        return pulpitListScreen;
    }

    public void setPulpitListScreen(PulpitListScreen pulpitListScreen) {
        this.pulpitListScreen = pulpitListScreen;
    }

    public void setPulpitShowListener(OnPulpitShowListener pulpitShowListener) {
        this.pulpitShowListener = pulpitShowListener;
    }

    public void addSpeciality(List<Speciality> specialities){
        if (specialityAddListener != null){
            specialityAddListener.specialityAdded(specialities);
        }
    }

    public void showPulpit(Pulpit pulpit){
        if (pulpitShowListener != null){
            pulpitShowListener.showPulpit(pulpit);
        }
    }
}
