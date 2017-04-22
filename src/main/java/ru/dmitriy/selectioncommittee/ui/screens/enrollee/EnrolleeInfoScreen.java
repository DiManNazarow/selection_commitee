package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.StudyInfo;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.views.InputTextLayout;

import java.util.Locale;

/**
 * Created by diman on 12.04.17.
 */
public class EnrolleeInfoScreen extends Screen<VerticalLayout, EnrolleeScreensPresenter> implements EnrolleeScreensPresenter.ShowEnrolleeListener, EnrolleeScreensPresenter.UpdateStudyInfo{

    public static final String ENROLLEE_INFO_SCREEN = "enrollee_info_screen";

    private TextField name;

    private TextField surname;

    private TextField patronymic;

    private TextField phone;

    private TextField city;

    private TextField street;

    private TextField address;

    private TextField personalDocNumber;

    private TextField school;

    private TextField schoolDocNumber;

    private Grid<StudyInfo> studyInfoListGrid;

    private Enrollee enrollee;

    public EnrolleeInfoScreen() {
        super(new VerticalLayout());
    }

    @Override
    protected void onPresenterSet(){
        super.onPresenterSet();
        getPresenter().setShowEnrolleeListener(this);
    }

    @Override
    public void buildScreen() {

        name = new TextField("Имя*");
        surname = new TextField("Фамилия*");
        patronymic = new TextField("Отчество");
        phone = new TextField("Телефон*");
        city = new TextField("Город*");
        street = new TextField("Улица*");
        address = new TextField("Дом*");
        personalDocNumber = new TextField("Номер паспорта*");
        school = new TextField("Школа*");
        schoolDocNumber = new TextField("Номер аттестата*");

        studyInfoListGrid = new Grid<>();
        studyInfoListGrid.addColumn(studyInfo -> studyInfo.getInstitution().getName()).setCaption("Университет");
        studyInfoListGrid.addColumn(studyInfo -> studyInfo.getPulpit().getName()).setCaption("Кафедра");
        studyInfoListGrid.addColumn(studyInfo -> String.format(Locale.getDefault(), "%s %s", studyInfo.getSpeciality().getSpecialNumber(), studyInfo.getSpeciality().getName())).setCaption("Специальность");
        studyInfoListGrid.addItemClickListener(itemClick -> {
            getPresenter().editStudyInfo(itemClick.getItem());
            ScreenManager.getInstance().navigateTo(EditStudyInfoScreen.EDIT_STUDY_INFO_SCREEN);
        });

        mainLayout.addComponents(name, surname, patronymic, phone, city, street, address, personalDocNumber, school, schoolDocNumber, studyInfoListGrid);
        addComponent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        refresh();
    }

    @Override
    public void showEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
        refresh();
    }

    public void refresh(){
        name.setValue(enrollee.getName());
        surname.setValue(enrollee.getName());
        patronymic.setValue(enrollee.getName());
        phone.setValue(enrollee.getName());
        city.setValue(enrollee.getName());
        street.setValue(enrollee.getName());
        address.setValue(enrollee.getName());
        personalDocNumber.setValue(enrollee.getName());
        school.setValue(enrollee.getName());
        schoolDocNumber.setValue(enrollee.getName());
        studyInfoListGrid.setItems(enrollee.getStudyInfo());
    }

    @Override
    public void updateStudyInfo() {
        studyInfoListGrid.setItems(ServiceProvider.instance().getStudyInfoService().getEnrolleeStudyInfo(enrollee));
    }
}
