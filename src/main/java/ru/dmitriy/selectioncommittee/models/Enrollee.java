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
public class Enrollee {

    @Id
    @Column(name = "enrollee_id", unique = true, nullable = false)
    @GenericGenerator(name = "enrollee", strategy = "increment")
    @GeneratedValue(generator = "enrollee")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "patronymic")
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

}
