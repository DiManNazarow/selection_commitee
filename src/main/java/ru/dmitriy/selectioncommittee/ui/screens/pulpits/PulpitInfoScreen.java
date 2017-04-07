package ru.dmitriy.selectioncommittee.ui.screens.pulpits;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.event.GlobalEvent;
import ru.dmitriy.selectioncommittee.ui.presenter.PulpitScreensPresenter;

/**
 * Created by Dmitriy Nazarow on 06.04.17.
 */
public class PulpitInfoScreen extends Screen<VerticalLayout, PulpitScreensPresenter> implements PulpitScreensPresenter.OnPulpitShowListener, GlobalEvent.Subscriber {

    public static final String PULPIT_INFO_SCREEN = "pulpit_info_screen";

    public static final int SHOW_PULPIT_CODE = 5489;

    private TextField name;

    private TextField university;

    private Grid<Speciality> listSpecialities;

    private Pulpit pulpit;

    public PulpitInfoScreen() {
        super(new VerticalLayout());
    }

    @Override
    protected void onPresenterSet(){
        super.onPresenterSet();
        getPresenter().setPulpitShowListener(this);
        GlobalEvent.instance().subscribe(this);
    }

    @Override
    public void buildScreen() {
        name = new TextField("Кафедра");
        university = new TextField("Учебное заведение");
        listSpecialities = new Grid<>();
        listSpecialities.setCaption("Специальости");
        listSpecialities.addColumn(Speciality::getSpecialNumber).setCaption("Код");
        listSpecialities.addColumn(Speciality::getName).setCaption("Название");
        mainLayout.addComponents(name, university, listSpecialities);
        addComponent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        refresh();
    }

    public void refresh(){
        if (pulpit != null){
            name.setValue(pulpit.getName());
            if (pulpit.getInstitution() != null){
                university.setValue(pulpit.getInstitution().getName());
            }
            if (pulpit.getSpecialities() != null && pulpit.getSpecialities().size() > 0){
                listSpecialities.setItems(pulpit.getSpecialities());
            }
        }
    }

    @Override
    public void showPulpit(Pulpit pulpit) {
        this.pulpit = pulpit;
        refresh();
    }

    @Override
    public void event(int code, GlobalEvent.EventData eventData) {
        switch (code){
            case SHOW_PULPIT_CODE:{
                showPulpit((Pulpit) eventData.getData());
                break;
            }
        }
    }
}
