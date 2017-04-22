package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.StudyInfo;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.screens.university.UniversityInfoScreen;
import ru.dmitriy.selectioncommittee.ui.views.InputTextLayout;
import ru.dmitriy.selectioncommittee.utils.GuiUtils;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class AddNewEnrolleeScreen extends Screen<VerticalLayout, EnrolleeScreensPresenter> implements EnrolleeScreensPresenter.OnStudyInfoBuildListener{

    public static final String ADD_NEW_ENROLLEE_SCREEN = "add_new_enrollee_screen";

    private InputTextLayout name;

    private InputTextLayout surname;

    private InputTextLayout patronymic;

    private InputTextLayout phone;

    private InputTextLayout city;

    private InputTextLayout street;

    private InputTextLayout address;

    private InputTextLayout personalDocNumber;

    private InputTextLayout school;

    private InputTextLayout schoolDocNumber;

    private Grid<StudyInfo> studyInfoListGrid;

    private Button addUniversity;

    private Button saveButton;

    private Enrollee enrollee;

    private List<StudyInfo> studyInfoList;

    public AddNewEnrolleeScreen() {
        super(new VerticalLayout());
        enrollee = new Enrollee();
        studyInfoList = new ArrayList<>();
    }

    @Override
    protected void onPresenterSet(){
        super.onPresenterSet();
        getPresenter().setStudyInfoBuildListener(this);
    }

    @Override
    public void buildScreen() {
        name = new InputTextLayout("Имя*");
        surname = new InputTextLayout("Фамилия*");
        patronymic = new InputTextLayout("Отчество");
        phone = new InputTextLayout("Телефон*");
        city = new InputTextLayout("Город*");
        street = new InputTextLayout("Улица*");
        address = new InputTextLayout("Дом*");
        personalDocNumber = new InputTextLayout("Номер паспорта*");
        school = new InputTextLayout("Школа*");
        schoolDocNumber = new InputTextLayout("Номер аттестата*");

        studyInfoListGrid = new Grid<>();

        studyInfoListGrid.addColumn(studyInfo -> studyInfo.getInstitution().getName()).setCaption("Университет");
        studyInfoListGrid.addColumn(studyInfo -> studyInfo.getPulpit().getName()).setCaption("Кафедра");
        studyInfoListGrid.addColumn(studyInfo -> String.format(Locale.getDefault(), "%s %s", studyInfo.getSpeciality().getSpecialNumber(), studyInfo.getSpeciality().getName())).setCaption("Специальность");

//        studyInfoListGrid.addItemClickListener(clickEvent -> {
//            getPresenter().editStudyInfo(clickEvent.getItem());
//            ScreenManager.getInstance().navigateTo(EditStudyInfoScreen.EDIT_STUDY_INFO_SCREEN);
//        });

        addUniversity = new Button("Добавить уиверситет");
        addUniversity.addClickListener(clickEvent -> {
            getPresenter().addEnrollee(enrollee);
            ScreenManager.getInstance().navigateTo(BindUniversityScreen.BIND_UNIVERSITY_SCREEN);
        });

        saveButton = new Button("Сохранить");
        saveButton.addClickListener(clickEvent -> {
            save();
        });

        mainLayout.addComponents(name, surname, patronymic, phone, city, street, address, personalDocNumber, school, schoolDocNumber, addUniversity, studyInfoListGrid, saveButton);
        addComponent(mainLayout);
    }

    private void save(){
        if (enrollee.getId() != null){
            enrollee = new Enrollee();
        }
        enrollee.setName(name.getText());
        enrollee.setSurname(surname.getText());
        enrollee.setPatronymic(patronymic.getText());
        enrollee.setPhone(phone.getText());
        enrollee.setCity(city.getText());
        enrollee.setStreet(street.getText());
        enrollee.setAddress(address.getText());
        enrollee.setPersonalDocNumber(personalDocNumber.getText());
        enrollee.setSchool(school.getText());
        enrollee.setSchoolDocNumber(schoolDocNumber.getText());

        if (!checkField()){
            return;
        }

        String id = ServiceProvider.instance().getEnrolleeService().saveEnrollee(enrollee);
        if (!TextUtils.isEmpty(id)){
            enrollee.setId(Long.parseLong(id));

            ServiceProvider.instance().getStudyInfoService().save(studyInfoList);

            enrollee.setStudyInfo(studyInfoList);

            ServiceProvider.instance().getEnrolleeService().saveEnrollee(enrollee);

            Notification.show("Сохранено");
        } else {
            Notification.show("Ошибка");
        }
    }

    private boolean checkField(){
        boolean allFill = true;
        if (name.isTextEmpty()){
            name.showError();
            scrollTo(name);
            showError();
            allFill = false;
        } else if (surname.isTextEmpty()){
            surname.showError();
            scrollTo(surname);
            showError();
            allFill = false;
        } else if (phone.isTextEmpty()) {
            phone.showError();
            scrollTo(phone);
            showError();
            allFill = false;
        } else if (city.isTextEmpty()) {
            city.showError();
            scrollTo(city);
            showError();
            allFill = false;
        } else if (street.isTextEmpty()) {
            street.showError();
            scrollTo(street);
            showError();
            allFill = false;
        } else if (address.isTextEmpty()) {
            address.showError();
            scrollTo(address);
            showError();
            allFill = false;
        } else if (personalDocNumber.isTextEmpty()) {
            personalDocNumber.showError();
            scrollTo(personalDocNumber);
            showError();
            allFill = false;
        } else if (school.isTextEmpty()) {
            school.showError();
            scrollTo(school);
            showError();
            allFill = false;
        } else if (schoolDocNumber.isTextEmpty()) {
            schoolDocNumber.showError();
            scrollTo(schoolDocNumber);
            showError();
            allFill = false;
        } else if (studyInfoList == null || studyInfoList.size() == 0){
            showEmptyUniversity();
            scrollTo(studyInfoListGrid);
            allFill = false;
        }
        return allFill;
    }

    private void showError(){
        GuiUtils.showErrorMessage("Заполните обязательные поля");
    }

    private void showEmptyUniversity(){
        GuiUtils.showErrorMessage("Добавьте учебные заведения");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @Override
    public void studyInfoBuild(StudyInfo studyInfo) {
        studyInfoList.add(studyInfo);
        studyInfoListGrid.setItems(studyInfoList);
    }
}
