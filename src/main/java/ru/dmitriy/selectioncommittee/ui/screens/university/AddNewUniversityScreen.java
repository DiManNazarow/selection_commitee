package ru.dmitriy.selectioncommittee.ui.screens.university;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.UniversityScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.views.InputTextLayout;
import ru.dmitriy.selectioncommittee.utils.GuiUtils;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

import java.util.List;

/**
 * Created by diman on 20.03.17.
 */
public class AddNewUniversityScreen extends Screen<VerticalLayout, UniversityScreensPresenter> implements UniversityScreensPresenter.PulpitsAddListener {

    public static final String ADD_NEW_UNIVERSITY_SCREEN = "add_university_screen";

    private InputTextLayout institutionName;
    private InputTextLayout institutionCity;
    private InputTextLayout institutionStreet;
    private InputTextLayout institutionAddress;
    private ListSelect<String> institutionTypeSelect;
    private Grid<Pulpit> universityPulpitList;
    private Button addPulpitsButton;

    private Button saveButton;

    private Institution institution;

    private List<Pulpit> pulpits;

    public AddNewUniversityScreen() {
        super(new VerticalLayout());
        institution = new Institution();
    }

    public void buildScreen(){
        institutionName = new InputTextLayout("Название университета*");
        institutionCity = new InputTextLayout("Город*");
        institutionStreet = new InputTextLayout("Улица*");
        institutionAddress = new InputTextLayout("Дом*");

        institutionTypeSelect = new ListSelect<>("Тип университета*");
        institutionTypeSelect.setItems("Уиверситет", "Техикум");
        institutionTypeSelect.setHeight(Institution.InstitutionType.getSize() + 2, Unit.EM);

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
        institution.setName(institutionName.getText());
        institution.setCity(institutionCity.getText());
        institution.setAddress(institutionAddress.getText());
        institution.setStreet(institutionStreet.getText());
        try {
            institution.setType(Institution.InstitutionType.getByTag(institutionTypeSelect.getValue().stream().findFirst().get()));
        } catch (Exception e) {
            GuiUtils.showErrorMessage("Выберете тип");
            e.printStackTrace();
        }

        if (!checkField()){
            return;
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

    private boolean checkField(){
        boolean allFill = true;
        if (institutionName.isTextEmpty()){
            institutionName.showError();
            scrollTo(institutionName);
            showError();
            allFill = false;
        } else if (institutionCity.isTextEmpty()){
            institutionCity.showError();
            scrollTo(institutionCity);
            showError();
            allFill = false;
        } else if (institutionAddress.isTextEmpty()) {
            institutionAddress.showError();
            scrollTo(institutionAddress);
            showError();
            allFill = false;
        } else if (institutionStreet.isTextEmpty()) {
            institutionStreet.showError();
            scrollTo(institutionStreet);
            showError();
            allFill = false;
        } else if (institution.getType()==null){
            scrollTo(institutionTypeSelect);
            showError();
            allFill = false;
        } else if (pulpits == null || pulpits.size() == 0){
            scrollTo(universityPulpitList);
            showEmptyPulpits();
            allFill = false;
        }
        return allFill;
    }

    private void showError(){
        GuiUtils.showErrorMessage("Заполните обязательные поля");
    }

    private void showEmptyPulpits(){
        GuiUtils.showErrorMessage("Добавьте кафедры");
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
        this.pulpits = pulpitList;
        if (institution != null){
            institution.setPulpits(pulpitList);
            institution.getPulpits().forEach(pulpit -> { pulpit.setInstitution(institution); });
            universityPulpitList.setItems(pulpitList);
        }
    }

    private void updatePulpits(List<Pulpit> pulpits){
        if (pulpits != null && pulpits.size() > 0) {
            ServiceProvider.instance().getPulpitService().savePulpits(pulpits);
        }
    }
}
