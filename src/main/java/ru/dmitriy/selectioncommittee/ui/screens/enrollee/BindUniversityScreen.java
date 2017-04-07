package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.*;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 07.04.17.
 */
public class BindUniversityScreen extends Screen<VerticalLayout, EnrolleeScreensPresenter> implements EnrolleeScreensPresenter.OnEnrolleeAddListener {

    public static final String BIND_UNIVERSITY_SCREEN = "bind_university_screen";

    private Grid<Institution> institutionList;

    private Grid<Pulpit> pulpitList;

    private Grid<Speciality> specialityList;

    private Button save;

    private Enrollee enrollee;

    private StudyInfo studyInfo;

    private Institution institution;

    private Pulpit pulpit;

    private Speciality speciality;

    public BindUniversityScreen() {
        super(new VerticalLayout());
    }

    @Override
    protected void onPresenterSet(){
        super.onPresenterSet();
        getPresenter().setEnrolleeAddListener(this);
    }

    @Override
    public void buildScreen() {
        institutionList = new Grid<>();
        pulpitList = new Grid<>();
        specialityList = new Grid<>();
        save = new Button("Сохранить");
        save.addClickListener(clickEvent -> {
            buildStudyInfo();
        });

        institutionList.setSelectionMode(Grid.SelectionMode.SINGLE);
        pulpitList.setSelectionMode(Grid.SelectionMode.SINGLE);
        specialityList.setSelectionMode(Grid.SelectionMode.SINGLE);

        institutionList.addColumn(Institution::getName).setCaption("Университет");
        institutionList.setItems(ServiceProvider.instance().getInstituteService().getAllInstitute());

        pulpitList.addColumn(Pulpit::getName).setCaption("Кафедра");
        specialityList.addColumn(Speciality::getName).setCaption("Специальность");

        institutionList.addItemClickListener(itemClick -> {
            institution = itemClick.getItem();
            showPulpit(itemClick.getItem().getPulpits());
        });
        pulpitList.addItemClickListener(itemClick -> {
            pulpit = itemClick.getItem();
            showSpeciality(itemClick.getItem().getSpecialities());
        });
        specialityList.addItemClickListener(itemClick -> {
            speciality = itemClick.getItem();
        });
        mainLayout.addComponents(institutionList, pulpitList, specialityList, save);
        addComponent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @Override
    public void addEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
        studyInfo = new StudyInfo();
        institutionList.setItems(ServiceProvider.instance().getInstituteService().getAllInstitute());
    }

    private void showPulpit(List<Pulpit> pulpits){
        pulpitList.setItems(pulpits);
    }

    private void showSpeciality(List<Speciality> specialities){
        specialityList.setItems(specialities);
    }

    private void buildStudyInfo(){
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
        getPresenter().buildStudyInfo(studyInfo);
    }
}
