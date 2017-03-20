package ru.dmitriy.selectioncommittee.configs;

import org.springframework.context.annotation.Bean;
import ru.dmitriy.selectioncommittee.services.EnrolleeService;
import ru.dmitriy.selectioncommittee.services.InstituteService;
import ru.dmitriy.selectioncommittee.services.PulpitService;
import ru.dmitriy.selectioncommittee.services.impl.EnrolleeServicesImpl;
import ru.dmitriy.selectioncommittee.services.impl.InstituteServiceImpl;
import ru.dmitriy.selectioncommittee.services.impl.PulpitServiceImpl;

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

}
