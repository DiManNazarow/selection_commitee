package ru.dmitriy.selectioncommittee.services.inpl;

import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.repositories.InstitutionRepository;
import ru.dmitriy.selectioncommittee.services.InstituteService;
import ru.dmitriy.selectioncommittee.utils.JsonUtils;

import java.util.ArrayList;

/**
 * Created by Dmitriy Nazarow on 12.03.17.
 */
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    private InstitutionRepository institutionRepository;

    private JsonParser parser;

    public InstituteServiceImpl(){
        parser = new JsonParser();
    }

    @Override
    public String getAllInstitute() {
        return JsonUtils.asJSONArray((ArrayList<Institution>)institutionRepository.findAll()).toString();
    }

    @Override
    public String addInstitute(String instituteJson) {
        return institutionRepository.save(Institution.PARCEL.fromJSONObject(parser.parse(instituteJson).getAsJsonObject())).getId().toString();
    }

    @Override
    public String getInstituteById(String idJson) {
        Long id = parser.parse(idJson).getAsJsonObject().get(Institution.JsonFieldName.ID).getAsLong();
        return institutionRepository.findOne(id).asJSON().toString();
    }

    @Override
    public String findInstituteByName(String nameJson) {
        String name = parser.parse(nameJson).getAsJsonObject().get(Institution.JsonFieldName.INSTITUTION_NAME).getAsString();
        return JsonUtils.asJSONArray(institutionRepository.findByNameIgnoreCaseContains(name)).toString();
    }
}
