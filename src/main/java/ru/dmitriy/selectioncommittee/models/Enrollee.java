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
 * Модель таблицы "Абитуриенты"
 */

@Entity
@Table( name = "Enrollee")
public class Enrollee implements JsonUtils.JSONPresentable{

    @Id
    @Column(name = "enrollee_id", unique = true, nullable = false)
    @GenericGenerator(name = "enrollee", strategy = "increment")
    @GeneratedValue(generator = "enrollee")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "personal_doc_number", unique = true, nullable = false)
    private String personalDocNumber;

    @Column(name = "school", nullable = false)
    private String school;

    @Column(name = "school_doc_number", unique = true, nullable = false)
    private String schoolDocNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudyInfo> studyInfo;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonalDocNumber() {
        return personalDocNumber;
    }

    public void setPersonalDocNumber(String personalDocNumber) {
        this.personalDocNumber = personalDocNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchoolDocNumber() {
        return schoolDocNumber;
    }

    public void setSchoolDocNumber(String schoolDocNumber) {
        this.schoolDocNumber = schoolDocNumber;
    }

    public List<StudyInfo> getStudyInfo() {
        return studyInfo;
    }

    public void setStudyInfo(List<StudyInfo> studyInfo) {
        this.studyInfo = studyInfo;
    }

    public static final JsonUtils.JSONParcel<Enrollee> PARCEL = enrolleeAsJson -> {
        Enrollee enrollee = new Enrollee();
        enrollee.setName(enrolleeAsJson.get(JsonFieldName.NAME).getAsString());
        enrollee.setSurname(enrolleeAsJson.get(JsonFieldName.SURNAME).getAsString());
        enrollee.setPatronymic(enrolleeAsJson.get(JsonFieldName.PATRONYMIC).getAsString());
        enrollee.setPhone(enrolleeAsJson.get(JsonFieldName.PHONE).getAsString());
        enrollee.setCity(enrolleeAsJson.get(JsonFieldName.CITY).getAsString());
        enrollee.setStreet(enrolleeAsJson.get(JsonFieldName.STREET).getAsString());
        enrollee.setAddress(enrolleeAsJson.get(JsonFieldName.ADDRESS).getAsString());
        enrollee.setPersonalDocNumber(enrolleeAsJson.get(JsonFieldName.PERSONAL_DOC_NUMBER).getAsString());
        enrollee.setSchool(enrolleeAsJson.get(JsonFieldName.SCHOOL).getAsString());
        enrollee.setSchoolDocNumber(enrolleeAsJson.get(JsonFieldName.SCHOOL_DOC_NUMBER).getAsString());
        //enrollee.setStudyInfo(JsonUtils.asList(enrolleeAsJson.get(JsonFieldName.STUDY_INFORMATIONS).getAsJsonArray(), StudyInfo.PARCEL));
        return enrollee;
    };

    @Override
    public JsonObject asJSON() {
        JsonObject enrolleeJson = new JsonObject();
        enrolleeJson.addProperty(JsonFieldName.NAME, name);
        enrolleeJson.addProperty(JsonFieldName.SURNAME, surname);
        enrolleeJson.addProperty(JsonFieldName.PATRONYMIC, patronymic);
        enrolleeJson.addProperty(JsonFieldName.PHONE, phone);
        enrolleeJson.addProperty(JsonFieldName.CITY, city);
        enrolleeJson.addProperty(JsonFieldName.STREET, street);
        enrolleeJson.addProperty(JsonFieldName.ADDRESS, address);
        enrolleeJson.addProperty(JsonFieldName.PERSONAL_DOC_NUMBER, personalDocNumber);
        enrolleeJson.addProperty(JsonFieldName.SCHOOL, school);
        enrolleeJson.addProperty(JsonFieldName.SCHOOL_DOC_NUMBER, schoolDocNumber);
        enrolleeJson.addProperty(JsonFieldName.STUDY_INFORMATIONS, JsonUtils.asJSONArray(studyInfo).toString());
        return enrolleeJson;
    }

    public static class JsonFieldName{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String PATRONYMIC = "patronymic";
        public static final String PHONE = "phone";
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String ADDRESS = "address";
        public static final String PERSONAL_DOC_NUMBER = "personal_doc_number";
        public static final String SCHOOL = "school";
        public static final String SCHOOL_DOC_NUMBER = "school_doc_number";
        public static final String STUDY_INFORMATIONS = "study_informations";
        public static final String INITIALS = "initials";
    }


    public static final String DEFAULT_ENROLLEE_JSON = "{\n" +
            "  \"name\": \"Dmitriy\",\n" +
            "  \"surname\": \"Nazarow\",\n" +
            "  \"patronymic\": \"Alex\",\n" +
            "  \"phone\": \"+79374153010\",\n" +
            "  \"city\": \"Penza\",\n" +
            "  \"street\": \"Dolgor\",\n" +
            "  \"address\": \"100\",\n" +
            "  \"personal_doc_number\": \"569301562356\",\n" +
            "  \"school\": \"Nic School\",\n" +
            "  \"school_doc_number\": \"6556\"\n" +
            "}";
}
