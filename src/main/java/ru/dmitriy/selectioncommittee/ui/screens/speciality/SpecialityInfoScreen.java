package ru.dmitriy.selectioncommittee.ui.screens.speciality;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.presenter.SpecialityScreensPresenter;

/**
 * Created by Dmitriy Nazarow on 06.04.17.
 */
public class SpecialityInfoScreen extends Screen<VerticalLayout, SpecialityScreensPresenter> implements SpecialityScreensPresenter.OnShowSpecialityListener{

    public static final String SPECIALITY_INFO_SCREEN = "speciality_info_screen";

    private TextField specialityCode;

    private TextField specialityName;

    private TextField pulpit;

    private Speciality speciality;

    public SpecialityInfoScreen() {
        super(new VerticalLayout());
    }

    @Override
    protected void onPresenterSet(){
        super.onPresenterSet();
        getPresenter().setShowSpecialityListener(this);
    }

    @Override
    public void buildScreen() {
        specialityCode = new TextField("Код специальости");
        specialityName = new TextField("Название");
        pulpit = new TextField("Уиверситет");
        mainLayout.addComponents(specialityCode, specialityName, pulpit);
        addComponent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        refresh();
    }


    @Override
    public void showSpeciality(Speciality speciality) {
        this.speciality = speciality;
        refresh();
    }

    public void refresh(){
        if (speciality != null){
            specialityCode.setValue(speciality.getSpecialNumber());
            specialityName.setValue(speciality.getName());
            if (speciality.getPulpit() != null){
                pulpit.setValue(speciality.getPulpit().getName());
            }
        }
    }
}
