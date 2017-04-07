package ru.dmitriy.selectioncommittee.models;

/**
 * Created by Dmitriy Nazarow on 12.02.17.
 */

import com.google.gson.JsonObject;
import org.hibernate.annotations.GenericGenerator;
import ru.dmitriy.selectioncommittee.utils.JsonUtils;

import javax.persistence.*;
import java.util.List;

/**
 * Модель таблицы "Специальность"
 */

@Entity
@Table(name = "Speciality")
public class Speciality implements JsonUtils.JSONPresentable{

    @Id
    @Column(name = "speciality_id", unique = true, nullable = false)
    @GenericGenerator(name = "speciality", strategy = "increment")
    @GeneratedValue(generator = "speciality")
    private Long id;

    @Column(name = "special_number", nullable = false, unique = true)
    private String specialNumber;

    @Column(name = "speciality_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pulpit pulpit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialNumber() {
        return specialNumber;
    }

    public void setSpecialNumber(String specialNumber) {
        this.specialNumber = specialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pulpit getPulpit() {
        return pulpit;
    }

    public void setPulpit(Pulpit pulpit) {
        this.pulpit = pulpit;
    }

    @Override
    public JsonObject asJSON() {
        JsonObject specialityJson = new JsonObject();
        specialityJson.addProperty(JsonFieldName.NAME, name);
        specialityJson.addProperty(JsonFieldName.SPECIALITY_NUMBER, specialNumber);
        specialityJson.addProperty(JsonFieldName.PULPIT, pulpit.asJSON().toString());
        return specialityJson;
    }

    public static final JsonUtils.JSONParcel<Speciality> PARCEL = specialityAsJson -> {
        Speciality speciality = new Speciality();
        speciality.setName(specialityAsJson.get(JsonFieldName.NAME).getAsString());
        speciality.setSpecialNumber(specialityAsJson.get(JsonFieldName.SPECIALITY_NUMBER).getAsString());
        speciality.setPulpit(Pulpit.PARCEL.fromJSONObject(specialityAsJson.get(JsonFieldName.PULPIT).getAsJsonObject()));
        return speciality;
    };

    public static class JsonFieldName{
        public static final String NAME = "name";
        public static final String SPECIALITY_NUMBER = "special_number";
        public static final String PULPIT = "pulpit";
    }
}
