package ru.dmitriy.selectioncommittee.ui.screens.speciality;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.SpecialityScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.views.InputTextLayout;
import ru.dmitriy.selectioncommittee.utils.GuiUtils;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class AddNewSpecialityScreen extends Screen<VerticalLayout, SpecialityScreensPresenter> {

    public static final String ADD_NEW_SPECIALITY_SCREEN = "add_new_speciality_screen";

    private InputTextLayout specialityNumber;

    private InputTextLayout specialityName;

    private Button saveButton;

    private Speciality speciality;

    public AddNewSpecialityScreen() {
        super(new VerticalLayout());
        speciality = new Speciality();
    }

    @Override
    public void buildScreen() {

        specialityNumber = new InputTextLayout("Номер специальности*");
        specialityName = new InputTextLayout("Название специальности*");

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
        speciality.setSpecialNumber(specialityNumber.getText());
        speciality.setName(specialityName.getText());

        if (!checkField()){
            return;
        }

        String id = ServiceProvider.instance().getSpecialityService().saveSpeciality(speciality);
        if (!TextUtils.isEmpty(id)){
            speciality.setId(Long.parseLong(id));
            Notification.show("Сохранено");
        } else {
            Notification.show("Ошибка");
        }
    }

    private boolean checkField() {
        boolean allFill = true;
        if (specialityNumber.isTextEmpty()) {
            specialityNumber.showError();
            scrollTo(specialityNumber);
            showError();
            allFill = false;
        } else if (specialityName.isTextEmpty()) {
            specialityName.showError();
            scrollTo(specialityName);
            showError();
            allFill = false;
        }
        return allFill;
    }

    private void showError(){
        GuiUtils.showErrorMessage("Заполните обязательные поля");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
