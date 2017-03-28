package ru.dmitriy.selectioncommittee.ui.screens.university;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.UniversityPresenter;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

import java.util.List;

/**
 * Created by diman on 20.03.17.
 */
public class AddNewUniversityScreen extends Screen<VerticalLayout, UniversityPresenter> implements UniversityPresenter.PulpitsAddListener {

    public static final String ADD_NEW_UNIVERSITY_SCREEN = "add_university_screen";

    private TextField institutionName;
    private TextField institutionCity;
    private TextField institutionStreet;
    private TextField institutionAddress;
    private ListSelect<String> institutionTypeSelect;
    private Grid<Pulpit> universityPulpitList;
    private Button addPulpitsButton;

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
        universityPulpitList = new Grid<>();
        universityPulpitList.addColumn(Pulpit::getName).setCaption("Кафедра");
        addPulpitsButton = new Button("Добавить кафедры");
        addPulpitsButton.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(AddPulpitToUniversityScreen.ADD_PULPIT_TO_UNIVERSITY_SCREEN);
        });
        saveButton = new Button("Сохранить");
        saveButton.addClickListener( clickEvent -> {
            saveInstitute();
        });
        mainLayout.addComponents(institutionName, institutionCity, institutionAddress, institutionStreet, institutionTypeSelect, universityPulpitList, addPulpitsButton, saveButton);
        addComponent(mainLayout);
    }

    private void saveInstitute(){
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
            updatePulpits(institution.getPulpits());
            Notification.show("Сохранено");
        } else {
            Notification.show("Ошибка");
        }
    }

    @Override
    protected void onPresenterSet(){
        super.onPresenterSet();
        getPresenter().setPulpitsAddListener(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        if (institution.getPulpits() != null) {
            universityPulpitList.setItems(institution.getPulpits());
        }
    }

    @Override
    public void pulpitsAdded(List<Pulpit> pulpitList) {
        if (institution != null){
            institution.setPulpits(pulpitList);
            institution.getPulpits().forEach(pulpit -> { pulpit.setInstitution(institution); });
            universityPulpitList.setItems(pulpitList);        }
    }

    private void updatePulpits(List<Pulpit> pulpits){
        if (pulpits != null && pulpits.size() > 0) {
            ServiceProvider.instance().getPulpitService().savePulpits(pulpits);
        }
    }
}
