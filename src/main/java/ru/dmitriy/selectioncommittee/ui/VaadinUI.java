package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.services.*;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.screens.StartScreen;

/**
 * Created by Dmitriy Nazarow on 17.03.17.
 */

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    private InstituteService instituteService;

    private PulpitService pulpitService;

    private StudyInfoService studyInfoService;

    private EnrolleeService enrolleeService;

    private SpecialityService specialityService;

    private Navigator navigator;

    @Autowired
    public VaadinUI(InstituteService instituteService, PulpitService pulpitService, EnrolleeService enrolleeService, StudyInfoService studyInfoService, SpecialityService specialityService) {
        this.instituteService = instituteService;
        this.pulpitService = pulpitService;
        this.enrolleeService = enrolleeService;
        this.studyInfoService = studyInfoService;
        this.specialityService = specialityService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        navigator = new Navigator(this, this);
        initRepository();
        ScreenManager.init(navigator);
        ScreenManager.getInstance().navigateTo(StartScreen.START_SCREEN);;
    }

    private void initRepository(){
        ServiceProvider.instance().setEnrolleeService(enrolleeService);
        ServiceProvider.instance().setPulpitService(pulpitService);
        ServiceProvider.instance().setInstituteService(instituteService);
        ServiceProvider.instance().setStudyInfoService(studyInfoService);
        ServiceProvider.instance().setSpecialityService(specialityService);
    }
}
