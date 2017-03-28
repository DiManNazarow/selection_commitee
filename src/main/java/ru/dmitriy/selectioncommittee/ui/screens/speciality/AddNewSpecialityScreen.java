package ru.dmitriy.selectioncommittee.ui.screens.speciality;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.SpecialityPresenter;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class AddNewSpecialityScreen extends Screen<VerticalLayout, SpecialityPresenter> {

    public static final String ADD_NEW_SPECIALITY_SCREEN = "add_new_speciality_screen";

    private TextField specialityNumber;

    private TextField specialityName;

    private Button saveButton;

    private Speciality speciality;

    public AddNewSpecialityScreen() {
        super(new VerticalLayout());
        speciality = new Speciality();
    }

    @Override
    public void buildScreen() {

        specialityNumber = new TextField("Номер специальности");
        specialityName = new TextField("Название специальности");

        saveButton = new Button("Сохранить");
        saveButton.addClickListener(clickEvent -> {
            save();
        });
        mainLayout.addComponents(specialityNumber, specialityName, saveButton);
        addComponent(mainLayout);
    }

    private void save(){
        if (speciality.getId() != null){
            speciality = new Speciality();
        }
        speciality.setSpecialNumber(specialityNumber.getValue());
        speciality.setName(specialityName.getValue());
        String id = ServiceProvider.instance().getSpecialityService().saveSpeciality(speciality);
        if (TextUtils.isEmpty(id)){
            speciality.setId(Long.parseLong(id));
            Notification.show("Сохранено");
        } else {
            Notification.show("Ошибка");
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
