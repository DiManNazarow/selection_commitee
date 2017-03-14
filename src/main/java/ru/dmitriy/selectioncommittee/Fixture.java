package ru.dmitriy.selectioncommittee;

import com.google.gson.JsonParser;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.models.Speciality;

import java.util.Arrays;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public class Fixture {

    public Enrollee getEnrollee(){
        JsonParser parser = new JsonParser();
        return Enrollee.PARCEL.fromJSONObject(parser.parse(Enrollee.DEFAULT_ENROLLEE_JSON).getAsJsonObject());
    }

    public Institution getPenzGTUInstitute(){
        Institution institution = new Institution();
        institution.setName("ПензГТУ");
        institution.setStreet("Гагарина");
        institution.setCity("Пенза");
        institution.setAddress("11");
        institution.setType(Institution.InstitutionType.UNIVERSITY);
        //institution.setPulpits(Arrays.asList(getPenzGTUVmis(), getPenzGTUITS()));
        return institution;
    }

    public Institution getPGUInstitute(){
        Institution institution = new Institution();
        institution.setName("ПГУ");
        institution.setStreet("Красная");
        institution.setCity("Пенза");
        institution.setAddress("40");
        institution.setType(Institution.InstitutionType.UNIVERSITY);
        //institution.setPulpits(Arrays.asList(getPGUInf(), getPGUStudy()));
        return institution;
    }

    public Pulpit getPenzGTUVmis(){
        Pulpit pulpit = new Pulpit();
        pulpit.setName("ВМИС");
        //pulpit.setInstitution(getPenzGTUInstitute());
        //pulpit.setSpecialities(Arrays.asList(getHardPenzGTU(), getInformPenzGTU()));
        return pulpit;
    }

    public Pulpit getPenzGTUITS(){
        Pulpit pulpit = new Pulpit();
        pulpit.setName("ИТС");
        //pulpit.setInstitution(getPenzGTUInstitute());
        //pulpit.setSpecialities(Arrays.asList(getHardPenzGTU(), getInformPenzGTU()));
        return pulpit;
    }

    public Pulpit getPGUInf(){
        Pulpit pulpit = new Pulpit();
        pulpit.setName("ИТС");
        //pulpit.setInstitution(getPGUInstitute());
        //pulpit.setSpecialities(Arrays.asList(getHardPGU(), getInformPGU()));
        return pulpit;
    }

    public Pulpit getPGUStudy(){
        Pulpit pulpit = new Pulpit();
        pulpit.setName("ИТС");
        //pulpit.setInstitution(getPGUInstitute());
        //pulpit.setSpecialities(Arrays.asList(getHardPGU(), getInformPGU()));
        return pulpit;
    }

    public Speciality getInformPenzGTU(){
        Speciality speciality = new Speciality();
        speciality.setName("Информатика и вычислительная техника");
        speciality.setSpecialNumber("09.03.01");
        //speciality.setPulpit(getPenzGTUVmis());
        return speciality;
    }

    public Speciality getHardPenzGTU(){
        Speciality speciality = new Speciality();
        speciality.setName("Разработка железок");
        speciality.setSpecialNumber("59.33.08");
        //speciality.setPulpit(getPenzGTUVmis());
        return speciality;
    }

    public Speciality getInformPGU(){
        Speciality speciality = new Speciality();
        speciality.setName("Информатика и вычислительная техника ПГУ");
        speciality.setSpecialNumber("569.222.08.0");
        //speciality.setPulpit(getPGUInf());
        return speciality;
    }

    public Speciality getHardPGU(){
        Speciality speciality = new Speciality();
        speciality.setName("Разработка железок ПГУ");
        speciality.setSpecialNumber("128.032.09.9");
        //speciality.setPulpit(getPGUStudy());
        return speciality;
    }

}
