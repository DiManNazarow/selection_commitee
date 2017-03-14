package ru.dmitriy.selectioncommittee.json.Enrolle;

import com.google.gson.*;
import ru.dmitriy.selectioncommittee.models.Enrollee;

import java.lang.reflect.Type;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public class EnrolleDeserializer implements JsonDeserializer<Enrollee> {
    @Override
    public Enrollee deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject enrolleeAsJson = (JsonObject)jsonElement;
        Enrollee enrollee = new Enrollee();
        enrollee.setName(enrolleeAsJson.get(Enrollee.JsonFieldName.NAME).getAsString());
        enrollee.setSurname(enrolleeAsJson.get(Enrollee.JsonFieldName.SURNAME).getAsString());
        enrollee.setPatronymic(enrolleeAsJson.get(Enrollee.JsonFieldName.PATRONYMIC).getAsString());
        enrollee.setPhone(enrolleeAsJson.get(Enrollee.JsonFieldName.PHONE).getAsString());
        enrollee.setCity(enrolleeAsJson.get(Enrollee.JsonFieldName.CITY).getAsString());
        enrollee.setStreet(enrolleeAsJson.get(Enrollee.JsonFieldName.STREET).getAsString());
        enrollee.setAddress(enrolleeAsJson.get(Enrollee.JsonFieldName.ADDRESS).getAsString());
        enrollee.setPersonalDocNumber(enrolleeAsJson.get(Enrollee.JsonFieldName.PERSONAL_DOC_NUMBER).getAsString());
        enrollee.setSchool(enrolleeAsJson.get(Enrollee.JsonFieldName.SCHOOL).getAsString());
        enrollee.setSchoolDocNumber(enrolleeAsJson.get(Enrollee.JsonFieldName.SCHOOL_DOC_NUMBER).getAsString());
        return enrollee;
    }
}
