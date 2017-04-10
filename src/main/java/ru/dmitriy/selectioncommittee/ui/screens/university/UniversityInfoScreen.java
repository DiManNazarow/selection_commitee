package ru.dmitriy.selectioncommittee.ui.screens.university;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.event.GlobalEvent;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.presenter.UniversityScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitInfoScreen;
import ru.dmitriy.selectioncommittee.ui.views.InputTextLayout;

/**
 * Created by Dmitriy Nazarow on 06.04.17.
 */
public class UniversityInfoScreen extends Screen<VerticalLayout, UniversityScreensPresenter> implements UniversityScreensPresenter.OnShowUniversityListener{

    public static final String UNIVERSITY_INFO_SCREEN = "university_info_screen";

    private TextField institutionName;
    private TextField institutionCity;
    private TextField institutionStreet;
    private TextField institutionAddress;
    private TextField institutionType;
    private Grid<Pulpit> universityPulpitList;

    private Institution institution;

    public UniversityInfoScreen() {
        super(new VerticalLayout());
    }

    @Override
    protected void onPresenterSet(){
        super.onPresenterSet();
        getPresenter().setShowUniversityListener(this);
    }

    @Override
    public void buildScreen() {
        institutionName = new TextField("Название университета");
        institutionCity = new TextField("Город");
        institutionStreet = new TextField("Улица");
        institutionAddress = new TextField("Дом");
        institutionType = new TextField("Тип университета");
        universityPulpitList = new Grid<>();
        universityPulpitList.setCaption("Кафедра");
        universityPulpitList.addColumn(Pulpit::getName).setCaption("Кафедра");
        universityPulpitList.addItemClickListener(itemClick -> {
            getPresenter().showPulpit(itemClick.getItem());
            ScreenManager.getInstance().navigateTo(PulpitInfoScreen.PULPIT_INFO_SCREEN);
        });
        mainLayout.addComponents(institutionName, institutionCity, institutionStreet, institutionAddress, institutionType, universityPulpitList);
        addComponent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        refresh();
    }

    @Override
    public void showUniversity(Institution institution) {
        this.institution = institution;
        refresh();
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    private void refresh(){
        if (institution != null){
            institutionName.setValue(institution.getName());
            institutionCity.setValue(institution.getCity());
            institutionStreet.setValue(institution.getStreet());
            institutionAddress.setValue(institution.getAddress());
            institutionType.setValue(institution.getType().getType());
            if (institution.getPulpits() != null && institution.getPulpits().size() > 0) {
                universityPulpitList.setItems(institution.getPulpits());
            }
        }
    }
}
