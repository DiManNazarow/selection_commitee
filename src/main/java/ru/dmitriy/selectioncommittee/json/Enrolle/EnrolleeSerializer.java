package ru.dmitriy.selectioncommittee.json.Enrolle;

import com.google.gson.*;
import ru.dmitriy.selectioncommittee.models.Enrollee;

import java.lang.reflect.Type;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public class EnrolleeSerializer implements JsonSerializer<Enrollee>{
    @Override
    public JsonElement serialize(Enrollee enrollee, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject enrolleeJson = new JsonObject();
        enrolleeJson.addProperty(Enrollee.JsonFieldName.NAME, enrollee.getName());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.SURNAME, enrollee.getSurname());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.PATRONYMIC, enrollee.getPatronymic());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.PHONE, enrollee.getPhone());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.CITY, enrollee.getCity());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.STREET, enrollee.getStreet());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.ADDRESS, enrollee.getAddress());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.PERSONAL_DOC_NUMBER, enrollee.getPersonalDocNumber());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.SCHOOL, enrollee.getSchool());
        enrolleeJson.addProperty(Enrollee.JsonFieldName.SCHOOL_DOC_NUMBER, enrollee.getSchoolDocNumber());
        return enrolleeJson;
    }
}
