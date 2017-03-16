package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

/**
 * Created by Dmitriy Nazarow on 17.03.17.
 */

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
       buildUI();
    }

    private void buildUI(){
        VerticalLayout mainLayout = new VerticalLayout();
        Label label = new Label("Добро пожаловать на сайт приемной комиссии!");
        mainLayout.addComponent(label);
        mainLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        Button continueButton = new Button("Продолжить");
        continueButton.addClickListener(l -> {
            Notification.show("В разработке. Совсем скоро!");
        });
        mainLayout.addComponent(continueButton);
        mainLayout.setComponentAlignment(continueButton, Alignment.BOTTOM_CENTER);
        setContent(mainLayout);
    }
}
