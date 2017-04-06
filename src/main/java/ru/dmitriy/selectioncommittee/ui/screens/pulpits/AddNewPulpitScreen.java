package ru.dmitriy.selectioncommittee.ui.screens.pulpits;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.AddNewPulpitScreenPresenter;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

import java.util.List;

/**
 * Created by diman on 20.03.17.
 */
public class AddNewPulpitScreen extends Screen<VerticalLayout, AddNewPulpitScreenPresenter> implements AddNewPulpitScreenPresenter.SpecialityAddListener{

    public static final String ADD_NEW_PULPIT_SCREEN = "add_new_pulpit_screen";

    private Pulpit pulpit;

    private TextField pulpitName;

    private Button pulpitSaveButton;

    private Button addSpecialityButton;

    private Grid<Speciality> specialityList;

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
        pulpitName = new TextField("Название кафедры");

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
        if (pulpit.getId() != null){
            pulpit = new Pulpit();
        }
        pulpit.setName(pulpitName.getValue());
        String id = ServiceProvider.instance().getPulpitService().savePulpit(pulpit);
        if (!TextUtils.isEmpty(id)){
            pulpit.setId(Long.parseLong(id));
            updateSpecialities(pulpit.getSpecialities());
            Notification.show("Сохранено");
        } else {
            Notification.show("Ошибка");
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        if (pulpit.getSpecialities() != null) {
            specialityList.setItems(pulpit.getSpecialities());
        }
    }

    @Override
    public void specialityAdded(List<Speciality> specialities) {
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
