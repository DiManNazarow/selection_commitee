package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleePresenter;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class AddNewEnrolleeScreen extends Screen<VerticalLayout, EnrolleePresenter> {

    public static final String ADD_NEW_ENROLLEE_SCREEN = "add_new_enrollee_screen";

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

    private Button saveButton;

    private Enrollee enrollee;

    public AddNewEnrolleeScreen() {
        super(new VerticalLayout());
        enrollee = new Enrollee();
    }

    @Override
    public void buildScreen() {
        name = new TextField("Имя");
        surname = new TextField("Фамилия");
        patronymic = new TextField("Отчество");
        phone = new TextField("Телефон");
        city = new TextField("Город");
        street = new TextField("Улица");
        address = new TextField("Дом");
        personalDocNumber = new TextField("Номер паспорта");
        school = new TextField("Школа");
        schoolDocNumber = new TextField("Номер аттестата");

        saveButton = new Button("Сохранить");
        saveButton.addClickListener(clickEvent -> {
            save();
        });

        mainLayout.addComponents(name, surname, patronymic, phone, city, street, address, personalDocNumber, school, schoolDocNumber, saveButton);
        addComponent(mainLayout);
    }

    private void save(){
        if (enrollee.getId() != null){
            enrollee = new Enrollee();
        }
        enrollee.setName(name.getValue());
        enrollee.setSurname(surname.getValue());
        enrollee.setPatronymic(patronymic.getValue());
        enrollee.setPhone(phone.getValue());
        enrollee.setCity(city.getValue());
        enrollee.setStreet(street.getValue());
        enrollee.setAddress(address.getValue());
        enrollee.setPersonalDocNumber(personalDocNumber.getValue());
        enrollee.setSchool(school.getValue());
        enrollee.setSchoolDocNumber(schoolDocNumber.getValue());

        String id = ServiceProvider.instance().getEnrolleeService().saveEnrollee(enrollee);
        if (!TextUtils.isEmpty(id)){
            enrollee.setId(Long.parseLong(id));
            Notification.show("Сохранено");
        } else {
            Notification.show("Ошибка");
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
