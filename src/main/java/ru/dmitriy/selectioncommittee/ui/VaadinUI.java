package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.services.EnrolleeService;
import ru.dmitriy.selectioncommittee.services.StudyInfoService;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 17.03.17.
 */

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    private EnrolleeService enrolleeService;

    private StudyInfoService studyInfoService;

    private Grid<Enrollee> listEnrollee;

    @Autowired
    public VaadinUI(EnrolleeService enrolleeService, StudyInfoService studyInfoService){
        this.enrolleeService = enrolleeService;
        this.studyInfoService = studyInfoService;
        listEnrollee = new Grid<>(Enrollee.class);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
       buildMainScreen();
    }

    private void buildMainScreen(){
        VerticalLayout mainLayout = new VerticalLayout();
        Label label = new Label("Добро пожаловать на сайт приемной комиссии!");
        mainLayout.addComponent(label);
        mainLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        Button continueButton = new Button("Продолжить");
        continueButton.addClickListener(l -> {
            buildFindEnrolleeScreen();
            //Notification.show("В разработке. Совсем скоро!");
        });
        mainLayout.addComponent(continueButton);
        mainLayout.setComponentAlignment(continueButton, Alignment.BOTTOM_CENTER);
        setContent(mainLayout);
    }

    private void buildFindEnrolleeScreen(){
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout findLayout = new HorizontalLayout();
        TextField findTextField = new TextField("ФИО");
        findTextField.addValueChangeListener(event -> {
            setEnrolleeList(event.getValue());
        });
        findLayout.addComponent(findTextField);
        mainLayout.addComponent(findLayout);
        mainLayout.addComponent(listEnrollee);
        //listEnrollee.setColumns("Имя", "Фамилия", "Отчество");
        setContent(mainLayout);
    }

    private void setEnrolleeList(String initials){
        if (initials == null || initials.isEmpty()){
            listEnrollee.setItems(enrolleeService.getAllEnrollee());
        } else {
            List<Enrollee> enrollees = enrolleeService.findEnrolleeByInitials(initials);
            listEnrollee.setItems(enrolleeService.findEnrolleeByInitials(initials));
        }
    }

}
