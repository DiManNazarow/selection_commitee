package ru.dmitriy.selectioncommittee.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */

@Entity
@Table( name = "StudyInfo")
public class StudyInfo {

    @Id
    @Column(name = "speciality_id", unique = true, nullable = false)
    @GenericGenerator(name = "studyInfo", strategy = "increment")
    @GeneratedValue(generator = "studyInfo")
    private Long id;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Enrollee enrollee;

    @OneToOne(fetch = FetchType.EAGER)
    private Institution institution;

    @OneToOne(fetch = FetchType.EAGER)
    private Pulpit pulpit;

    @OneToOne(fetch = FetchType.EAGER)
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

    public Status getStudyState() {
        return Status.getByState(status);
    }

    public void setStudyState(Status status) {
        this.status = status.getState();
    }

    public enum Status {
        ENTER(0, "Поступает"),
        STUDY(1, "Обучается"),
        ENDED(2, "Закочил");

        private int state;

        private String name;

        Status(int state, String name){
            this.state = state;
            this.name = name;
        }

        public int getState() {
            return state;
        }

        public String getName() {
            return name;
        }

        public static Status getByName(String name){
            Status studyState = null;
            switch (name){
                case "Поступает": studyState = ENTER; break;
                case "Обучается": studyState = STUDY; break;
                case "Закочил": studyState = ENDED; break;
            }
            return studyState;
        }

        public static Status getByState(int state){
            Status studyState = null;
            switch (state){
                case 0: studyState = ENTER; break;
                case 1: studyState = STUDY; break;
                case 2: studyState = ENDED; break;
            }
            return studyState;
        }

        public static int getSize(){
            return Status.values().length;
        }

        public static List<String> getStudyStateNames(){
            List<String> studyStates = new ArrayList<>();
            studyStates.add(ENTER.getName());
            studyStates.add(STUDY.getName());
            studyStates.add(ENDED.getName());
            return studyStates;
        }
    }
}
