package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import com.vaadin.data.HasValue;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.*;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmitriy Nazarow on 07.04.17.
 */
public class BindUniversityScreen extends Screen<VerticalLayout, EnrolleeScreensPresenter> implements EnrolleeScreensPresenter.OnEnrolleeAddListener {

    public static final String BIND_UNIVERSITY_SCREEN = "bind_university_screen";

    protected Grid<Institution> institutionList;

    protected Grid<Pulpit> pulpitList;

    protected Grid<Speciality> specialityList;

    protected ListSelect<String> studyStateList;

    private Button save;

    protected Enrollee enrollee;

    protected StudyInfo studyInfo;

    protected Institution institution;

    protected Pulpit pulpit;

    protected Speciality speciality;

    protected StudyInfo.StudyState studyState;

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
            ScreenManager.getInstance().navigateBack();
        });

        institutionList.setSelectionMode(Grid.SelectionMode.SINGLE);
        pulpitList.setSelectionMode(Grid.SelectionMode.SINGLE);
        specialityList.setSelectionMode(Grid.SelectionMode.SINGLE);

        institutionList.addColumn(Institution::getName).setCaption("Университет");
        institutionList.setItems(ServiceProvider.instance().getInstituteService().getAllInstitute());

        pulpitList.addColumn(Pulpit::getName).setCaption("Кафедра");
        specialityList.addColumn(Speciality::getName).setCaption("Специальность");

        studyStateList = new ListSelect<>("Состояние обучающегося*");
        studyStateList.setItems(StudyInfo.StudyState.getStudyStateNames());
        studyStateList.setHeight(StudyInfo.StudyState.getSize() + 2, Unit.EM);

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

        studyStateList.addValueChangeListener(new HasValue.ValueChangeListener<Set<String>>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<Set<String>> valueChangeEvent) {
                studyState = StudyInfo.StudyState.getByName(valueChangeEvent.getValue().stream().findFirst().get());
            }
        });

        mainLayout.addComponents(institutionList, pulpitList, specialityList, studyStateList, save);
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
        getPresenter().buildStudyInfo(studyInfo);
    }
}
