package ru.dmitriy.selectioncommittee.ui.screens;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

/**
 * Created by diman on 20.03.17.
 */
public class AddNewUniversityScreen extends Screen<VerticalLayout> {

    public static final String ADD_NEW_UNIVERSITY_SCREEN = "add_university_screen";

    private TextField institutionName;
    private TextField institutionCity;
    private TextField institutionStreet;
    private TextField institutionAddress;
    private ListSelect<String> institutionTypeSelect;

    private Button saveButton;

    private Institution institution;

    public AddNewUniversityScreen() {
        super(new VerticalLayout());
        institution = new Institution();
    }

    public void buildScreen(){
        institutionName = new TextField("Название университета");
        institutionCity = new TextField("Город");
        institutionStreet = new TextField("Улица");
        institutionAddress = new TextField("Дом");
        institutionTypeSelect = new ListSelect<>("Тип университета");
        institutionTypeSelect.setItems("Уиверситет", "Техикум");
        saveButton = new Button("Сохранить");
        saveButton.addClickListener( clickEvent -> {
            saveInstitute();
        });
        mainLayout.addComponents(institutionName, institutionCity, institutionAddress, institutionStreet, institutionTypeSelect, saveButton);
        addComponent(mainLayout);
    }

    public void saveInstitute(){
        if (institution.getId() != null){
            institution = new Institution();
        }
        institution.setName(institutionName.getValue());
        institution.setCity(institutionCity.getValue());
        institution.setAddress(institutionAddress.getValue());
        institution.setStreet(institutionStreet.getValue());
        try {
            institution.setType(Institution.InstitutionType.getByTag(institutionTypeSelect.getValue().stream().findFirst().get()));
        } catch (Institution.TypeNotFoundException e) {
            e.printStackTrace();
        }
        String id = ServiceProvider.instance().getInstituteService().addInstitute(institution);
        if (!TextUtils.isEmpty(id)){
            institution.setId(Long.parseLong(id));
            Notification.show("Сохранено");
        } else {
            Notification.show("Ошибка");
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
