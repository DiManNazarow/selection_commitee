package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.services.InstituteService;
import ru.dmitriy.selectioncommittee.services.PulpitService;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;

/**
 * Created by Dmitriy Nazarow on 17.03.17.
 */

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    private InstituteService instituteService;

    private PulpitService pulpitService;

    private Navigator navigator;

    @Autowired
    public VaadinUI(InstituteService instituteService, PulpitService pulpitService) {
        this.instituteService = instituteService;
        this.pulpitService = pulpitService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        navigator = new Navigator(this, this);
        ScreenManager.init(navigator);
//        buildUI();
    }

//    private void buildUI(){
//        VerticalLayout mainLayout = new VerticalLayout();
//        Label label = new Label("Добро пожаловать на сайт приемной комиссии!");
//        mainLayout.addComponent(label);
//        mainLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
//        Button continueButton = new Button("Продолжить");
//        continueButton.addClickListener(l -> {
//            AddUniversityScreen addUniversityScreen = new AddUniversityScreen(instituteService, pulpitService);
//            setContent(addUniversityScreen.buildScreen());
//            //Notification.show("В разработке. Совсем скоро!");
//        });
//        mainLayout.addComponent(continueButton);
//        mainLayout.setComponentAlignment(continueButton, Alignment.BOTTOM_CENTER);
//        setContent(mainLayout);
//    }
}
