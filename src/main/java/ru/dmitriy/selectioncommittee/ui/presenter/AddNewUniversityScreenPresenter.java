package ru.dmitriy.selectioncommittee.ui.presenter;

import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.ui.Presenter;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.screens.university.AddNewUniversityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.AddPulpitToUniversityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.UniversityListScreen;

import java.util.List;

/**
 * Created by diman on 28.03.17.
 */
public class AddNewUniversityScreenPresenter extends Presenter{

    private AddNewUniversityScreen addNewUniversityScreen;

    private AddPulpitToUniversityScreen addPulpitToUniversityScreen;

    private UniversityListScreen universityListScreen;

    public interface PulpitsAddListener{
        void pulpitsAdded(List<Pulpit> pulpitList);
    }

    private PulpitsAddListener pulpitsAddListener;

    public AddNewUniversityScreen getAddNewUniversityScreen() {
        return addNewUniversityScreen;
    }

    public void setAddNewUniversityScreen(AddNewUniversityScreen addNewUniversityScreen) {
        this.addNewUniversityScreen = addNewUniversityScreen;
    }

    public AddPulpitToUniversityScreen getAddPulpitToUniversityScreen() {
        return addPulpitToUniversityScreen;
    }

    public void setAddPulpitToUniversityScreen(AddPulpitToUniversityScreen addPulpitToUniversityScreen) {
        this.addPulpitToUniversityScreen = addPulpitToUniversityScreen;
    }

    public UniversityListScreen getUniversityListScreen() {
        return universityListScreen;
    }

    public void setUniversityListScreen(UniversityListScreen universityListScreen) {
        this.universityListScreen = universityListScreen;
    }

    public void setPulpitsAddListener(PulpitsAddListener pulpitsAddListener){
        this.pulpitsAddListener = pulpitsAddListener;
    }

    public void addPulpits(List<Pulpit> pulpits){
        if (pulpitsAddListener != null){
            pulpitsAddListener.pulpitsAdded(pulpits);
        }
    }

}
