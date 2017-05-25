package ru.dmitriy.selectioncommittee.ui.screens.pulpits;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.PulpitScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.views.InputTextLayout;
import ru.dmitriy.selectioncommittee.utils.GuiUtils;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by diman on 20.03.17.
 */
public class AddNewPulpitScreen extends Screen<VerticalLayout, PulpitScreensPresenter> implements PulpitScreensPresenter.SpecialityAddListener{

    public static final String ADD_NEW_PULPIT_SCREEN = "add_new_pulpit_screen";

    private Pulpit pulpit;

    private InputTextLayout pulpitName;

    private Button pulpitSaveButton;

    private Button addSpecialityButton;

    private Grid<Speciality> specialityList;

    private List<Speciality> specialities;

    public AddNewPulpitScreen() {
        super(new VerticalLayout());
        pulpit = new Pulpit();
    }

    @Override
    protected void onPresenterSet(){
        super.onPresenterSet();
        getPresenter().setSpecialityAddListener(this);
    }

    @Override
    public void buildScreen(){
        pulpitName = new InputTextLayout("Название кафедры*");

        addSpecialityButton = new Button("Добавить специальности");
        addSpecialityButton.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(AddSpecialityToPulpitScreen.ADD_SPECIALITY_TO_PULPIT_SCREEN);
        });

        pulpitSaveButton = new Button("Сохранить");

        pulpitSaveButton.addClickListener(clickEvent -> {
            save();
        });

        specialityList = new Grid<>();
        specialityList.addColumn(Speciality::getName).setCaption("Специальность");

        mainLayout.addComponents(pulpitName, specialityList, addSpecialityButton, pulpitSaveButton);
        addComponent(mainLayout);
    }

    public void save(){
        pulpit.setName(pulpitName.getText());

        if (!checkField()){
            return;
        }

        String id = ServiceProvider.instance().getPulpitService().savePulpit(pulpit);
        if (!TextUtils.isEmpty(id)){
            pulpit.setId(Long.parseLong(id));
            updateSpecialities(pulpit.getSpecialities());
            Notification.show("Сохранено");
            ScreenManager.getInstance().navigateBack();
            clear();
        } else {
            Notification.show("Ошибка");
        }
    }

    private void clear(){
        pulpitName.clear();
        specialityList.setItems(Collections.emptyList());
        pulpit = null;
    }

    private boolean checkField(){
        boolean allFill = true;
        if (pulpitName.isTextEmpty()){
            pulpitName.showError();
            scrollTo(pulpitName);
            showError();
            allFill = false;
        } else if (specialities == null || specialities.size() == 0){
            scrollTo(specialityList);
            showEmptySpecialities();
        }
        return allFill;
    }

    private void showError(){
        GuiUtils.showErrorMessage("Заполните обязательные поля");
    }

    private void showEmptySpecialities(){
        GuiUtils.showErrorMessage("Добавьте специальности");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        if (pulpit == null){
            pulpit = new Pulpit();
        }
        if (pulpit.getSpecialities() != null) {
            specialityList.setItems(pulpit.getSpecialities());
        }
    }

    @Override
    public void specialityAdded(List<Speciality> specialities) {
        this.specialities = specialities;
        if (pulpit != null){
            pulpit.setSpecialities(specialities);
            pulpit.getSpecialities().forEach(speciality -> speciality.setPulpit(pulpit));
            specialityList.setItems(specialities);
        }
    }

    private void updateSpecialities(List<Speciality> specialities){
        if (specialities != null && specialities.size() > 0){
            ServiceProvider.instance().getSpecialityService().saveSpecialities(specialities);
        }
    }
}
