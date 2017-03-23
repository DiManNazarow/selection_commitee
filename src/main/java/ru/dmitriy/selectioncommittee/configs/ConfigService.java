package ru.dmitriy.selectioncommittee.configs;

import org.springframework.context.annotation.Bean;
import ru.dmitriy.selectioncommittee.services.*;
import ru.dmitriy.selectioncommittee.services.impl.*;

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
    public PulpitService pulpitService(){
        return new PulpitServiceImpl();
    }

    @Bean
    public StudyInfoService studyInfoService(){
        return new StudyInfoServiceImpl();
    }

    @Bean
    public SpecialityService specialityService(){
        return new SpecialityServiceImpl();
    }

}
