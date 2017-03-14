package ru.dmitriy.selectioncommittee.models;

import com.google.gson.JsonObject;
import org.hibernate.annotations.GenericGenerator;
import ru.dmitriy.selectioncommittee.utils.JsonUtils;

import javax.persistence.*;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */

@Entity
@Table( name = "StudyInfo")
public class StudyInfo implements JsonUtils.JSONPresentable {

    @Id
    @Column(name = "speciality_id", unique = true, nullable = false)
    @GenericGenerator(name = "studyInfo", strategy = "increment")
    @GeneratedValue(generator = "studyInfo")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Enrollee enrollee;

    @OneToOne(fetch = FetchType.LAZY)
    private Institution institution;

    @OneToOne(fetch = FetchType.LAZY)
    private Pulpit pulpit;

    @OneToOne(fetch = FetchType.LAZY)
    private Speciality speciality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Pulpit getPulpit() {
        return pulpit;
    }

    public void setPulpit(Pulpit pulpit) {
        this.pulpit = pulpit;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public JsonObject asJSON() {
        JsonObject studyInfoAsJson = new JsonObject();
        studyInfoAsJson.addProperty(JsonFieldName.ENROLLEE, enrollee.asJSON().toString());
        studyInfoAsJson.addProperty(JsonFieldName.INSTITUTE, institution.asJSON().toString());
        studyInfoAsJson.addProperty(JsonFieldName.PULPIT, pulpit.asJSON().toString());
        studyInfoAsJson.addProperty(JsonFieldName.SPECIALITY, speciality.asJSON().toString());
        studyInfoAsJson.addProperty(JsonFieldName.ENROLLEE, enrollee.asJSON().toString());
        return studyInfoAsJson;
    }

    public static final JsonUtils.JSONParcel<StudyInfo> PARCEL = studyInfoAsJson -> {
        StudyInfo studyInfo = new StudyInfo();
        studyInfo.setEnrollee(Enrollee.PARCEL.fromJSONObject(studyInfoAsJson.get(JsonFieldName.ENROLLEE).getAsJsonObject()));
        studyInfo.setInstitution(Institution.PARCEL.fromJSONObject(studyInfoAsJson.get(JsonFieldName.INSTITUTE).getAsJsonObject()));
        studyInfo.setPulpit(Pulpit.PARCEL.fromJSONObject(studyInfoAsJson.get(JsonFieldName.PULPIT).getAsJsonObject()));
        studyInfo.setSpeciality(Speciality.PARCEL.fromJSONObject(studyInfoAsJson.get(JsonFieldName.SPECIALITY).getAsJsonObject()));
        //studyInfo.setEnrollee(JsonUtils.asList(studyInfoAsJson.getAsJsonArray(JsonFieldName.ENROLLEE), Enrollee.PARCEL));
        return studyInfo;
    };

    public static class JsonFieldName{
        public static final String ENROLLEE = "enrollee";
        public static final String INSTITUTE = "institute";
        public static final String SPECIALITY = "speciality";
        public static final String PULPIT = "pulpit";
    }
}
