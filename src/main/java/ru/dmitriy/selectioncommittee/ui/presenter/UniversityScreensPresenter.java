package ru.dmitriy.selectioncommittee.ui.presenter;

import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.ui.Presenter;
import ru.dmitriy.selectioncommittee.ui.event.GlobalEvent;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitInfoScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.AddNewUniversityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.AddPulpitToUniversityScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.UniversityInfoScreen;
import ru.dmitriy.selectioncommittee.ui.screens.university.UniversityListScreen;

import java.util.List;

/**
 * Created by diman on 28.03.17.
 */
public class UniversityScreensPresenter extends Presenter{

    private AddNewUniversityScreen addNewUniversityScreen;

    private AddPulpitToUniversityScreen addPulpitToUniversityScreen;

    private UniversityListScreen universityListScreen;

    private UniversityInfoScreen universityInfoScreen;

    public interface PulpitsAddListener{
        void pulpitsAdded(List<Pulpit> pulpitList);
    }

    public interface OnShowUniversityListener{
        void showUniversity(Institution institution);
    }

    private PulpitsAddListener pulpitsAddListener;

    private OnShowUniversityListener showUniversityListener;

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

    public UniversityInfoScreen getUniversityInfoScreen() {
        return universityInfoScreen;
    }

    public void setUniversityInfoScreen(UniversityInfoScreen universityInfoScreen) {
        this.universityInfoScreen = universityInfoScreen;
    }

    public void setPulpitsAddListener(PulpitsAddListener pulpitsAddListener){
        this.pulpitsAddListener = pulpitsAddListener;
    }

    public void setShowUniversityListener(OnShowUniversityListener showUniversityListener) {
        this.showUniversityListener = showUniversityListener;
    }

    public void addPulpits(List<Pulpit> pulpits){
        if (pulpitsAddListener != null){
            pulpitsAddListener.pulpitsAdded(pulpits);
        }
    }

    public void showUniversity(Institution institution){
        if (showUniversityListener != null){
            showUniversityListener.showUniversity(institution);
        }
    }

    public void showPulpit(Pulpit pulpit){
        GlobalEvent.instance().event(PulpitInfoScreen.SHOW_PULPIT_CODE, new GlobalEvent.EventData(pulpit));
    }

}
