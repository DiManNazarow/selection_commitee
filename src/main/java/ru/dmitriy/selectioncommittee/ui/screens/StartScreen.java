package ru.dmitriy.selectioncommittee.ui.screens;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;

/**
 * Created by Dmitriy Nazarow on 23.03.17.
 */
public class StartScreen extends Screen<VerticalLayout> {

    public static final String START_SCREEN = "start_screen";

    private Label starTextField;

    private Button showInstitute;

    private Button showPulpits;

    private Button showSpecialities;

    private Button showEnrollee;

    private Button addInstitute;

    private Button addPulpit;

    private Button addSpecialities;

    private Button addEnrollee;

    public StartScreen() {
        super(new VerticalLayout());
    }

    @Override
    public void buildScreen() {

        starTextField = new Label("Приемная комиссия");

        HorizontalLayout upLayout = new HorizontalLayout();
        upLayout.addComponent(starTextField);

        HorizontalLayout centerLayout = new HorizontalLayout();

        VerticalLayout leftLayout = new VerticalLayout();

        showInstitute = new Button("Показать университеты");
        showPulpits = new Button("Показать кафедры");
        showSpecialities = new Button("Показать специальности");
        showEnrollee = new Button("Показать абитуриентов");
        showInstitute.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(UniversityListScreen.UNIVERSITY_SCREEN_LIST);
        });
        showPulpits.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(PulpitListScreen.PULPIT_SCREEN_LIST);
        });
        showSpecialities.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(SpecialityListScreen.SPECIALITY_LIST_SCREEN);
        });
        showEnrollee.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(EnrolleeListScreen.ENROLLEE_LIST_SCREEN);
        });
        leftLayout.addComponents(showEnrollee, showInstitute, showPulpits, showSpecialities);

        VerticalLayout rightLayout = new VerticalLayout();

        addInstitute = new Button("Добавить университеты");
        addPulpit = new Button("Добавить кафедры");
        addSpecialities = new Button("Добавить специальности");
        addEnrollee = new Button("Добавить абитуриентов");
        addInstitute.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(AddNewUniversityScreen.ADD_NEW_UNIVERSITY_SCREEN);
        });
        addPulpit.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(AddNewPulpitScreen.ADD_NEW_PULPIT_SCREEN);
        });
        addSpecialities.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(AddNewSpecialityScreen.ADD_NEW_SPECIALITY_SCREEN);
        });
        addEnrollee.addClickListener(clickEvent -> {
            ScreenManager.getInstance().navigateTo(AddNewEnrolleeScreen.ADD_NEW_ENROLLEE_SCREEN);
        });
        rightLayout.addComponents(addEnrollee, addInstitute, addPulpit, addSpecialities);

        centerLayout.addComponents(leftLayout, rightLayout);
        centerLayout.setComponentAlignment(leftLayout, Alignment.MIDDLE_LEFT);
        centerLayout.setComponentAlignment(rightLayout, Alignment.MIDDLE_RIGHT);

        mainLayout.addComponent(upLayout);
        mainLayout.setComponentAlignment(upLayout, Alignment.TOP_CENTER);
        mainLayout.addComponent(centerLayout);
        mainLayout.setComponentAlignment(centerLayout, Alignment.MIDDLE_CENTER);
        addComponent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
