package ru.dmitriy.selectioncommittee.configs;

import org.springframework.context.annotation.Bean;
import ru.dmitriy.selectioncommittee.services.EnrolleeService;
import ru.dmitriy.selectioncommittee.services.InstituteService;
import ru.dmitriy.selectioncommittee.services.StudyInfoService;
import ru.dmitriy.selectioncommittee.services.inpl.EnrolleeServicesImpl;
import ru.dmitriy.selectioncommittee.services.inpl.InstituteServiceImpl;
import ru.dmitriy.selectioncommittee.services.inpl.StudyInfoServiceImpl;

/**
 * Created by Dmitriy Nazarow on 12.03.17.
 */
public class ConfigService {

    @Bean
    public EnrolleeService enrolleeService(){
        return new EnrolleeServicesImpl();
    }

    @Bean
    public InstituteService instituteService(){ return new InstituteServiceImpl(); }

    @Bean
    public StudyInfoService studyInfoService(){
        return new StudyInfoServiceImpl();
    }

}
