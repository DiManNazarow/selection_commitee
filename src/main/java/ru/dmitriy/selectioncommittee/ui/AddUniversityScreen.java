package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.services.InstituteService;
import ru.dmitriy.selectioncommittee.services.PulpitService;

import java.util.Set;

/**
 * Created by diman on 20.03.17.
 */
public class AddUniversityScreen extends Screen<VerticalLayout>{

    private TextField institutionName;
    private TextField institutionCity;
    private TextField institutionStreet;
    private TextField institutionAddress;
    private ListSelect<String> institutionTypeSelect;

    private Button addPulpitButton;

    Button saveButton;

    private InstituteService instituteService;

    private PulpitService pulpitService;

    private Institution institution;

    public AddUniversityScreen(InstituteService instituteService, PulpitService pulpitService){
        this.instituteService = instituteService;
        this.pulpitService = pulpitService;
        institution = new Institution();
    }

    public VerticalLayout buildScreen(){
        VerticalLayout mainLayout = new VerticalLayout();
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
        addPulpitButton = new Button("Добавить кафедру");
        addPulpitButton.addClickListener(clickEvent -> {
            AddSavedPulpitScreen addSavedPulpitScreen = new AddSavedPulpitScreen(pulpitService, institution);
        });
        mainLayout.addComponent(institutionName);
        mainLayout.addComponent(institutionCity);
        mainLayout.addComponent(institutionAddress);
        mainLayout.addComponent(institutionStreet);
        mainLayout.addComponent(institutionTypeSelect);
        mainLayout.addComponent(saveButton);
        return mainLayout;
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
        instituteService.addInstitute(institution);
    }
}
