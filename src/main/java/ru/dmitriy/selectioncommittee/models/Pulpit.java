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
 * Модель таблицы "Кафедра"
 */

@Entity
@Table(name = "Pulpit")
public class Pulpit implements JsonUtils.JSONPresentable{

    @Id
    @Column(name = "pulpit_id", unique = true, nullable = false)
    @GenericGenerator(name = "pulpit", strategy = "increment")
    @GeneratedValue(generator = "pulpit")
    private Long id;

    @Column(name = "pulpit_name", nullable = false)
    private String name;

    @OneToMany
    private List<Speciality> specialities;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public JsonObject asJSON() {
        JsonObject pulpitAsJson = new JsonObject();
        pulpitAsJson.addProperty(JsonFieldName.NAME, name);
        pulpitAsJson.addProperty(JsonFieldName.SPECIALITIES, JsonUtils.asJSONArray(specialities).toString());
        return pulpitAsJson;
    }

    public static final JsonUtils.JSONParcel<Pulpit> PARCEL = pulpitAsJson -> {
        Pulpit pulpit = new Pulpit();
        pulpit.setName(pulpitAsJson.get(JsonFieldName.NAME).getAsString());
        pulpit.setSpecialities(JsonUtils.asList(pulpitAsJson.getAsJsonArray(JsonFieldName.SPECIALITIES), Speciality.PARCEL));
        return pulpit;
    };

    public static class JsonFieldName{
        public static final String NAME = "pulpit_name";
        public static final String SPECIALITIES = "specialities";
    }
}
