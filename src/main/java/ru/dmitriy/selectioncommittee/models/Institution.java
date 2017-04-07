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
 * Модель таблицы "Учебные заведения"
 */

@Entity
@Table(name = "Institution")
public class Institution implements JsonUtils.JSONPresentable{

    @Id
    @Column(name = "institution_id", unique = true, nullable = false)
    @GenericGenerator(name = "institute", strategy = "increment")
    @GeneratedValue(generator = "institute")
    private Long id;

    @Column(name = "institution_name", unique = true, nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "institution_type", nullable = false)
    private InstitutionType type;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Pulpit> pulpits;

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

    public InstitutionType getType() {
        return type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }

    public List<Pulpit> getPulpits() {
        return pulpits;
    }

    public void setPulpits(List<Pulpit> pulpits) {
        this.pulpits = pulpits;
    }

    @Override
    public JsonObject asJSON() {
        JsonObject instituteJson = new JsonObject();
        instituteJson.addProperty(JsonFieldName.INSTITUTION_NAME, name);
        instituteJson.addProperty(JsonFieldName.CITY, city);
        instituteJson.addProperty(JsonFieldName.STREET, street);
        instituteJson.addProperty(JsonFieldName.ADDRESS, address);
        instituteJson.addProperty(JsonFieldName.INSTITUTION_TYPE, type.type);
        instituteJson.addProperty(JsonFieldName.PULPITS, JsonUtils.asJSONArray(pulpits).toString());
        return instituteJson;
    }

    public static final JsonUtils.JSONParcel<Institution> PARCEL = new JsonUtils.JSONParcel<Institution>() {
        @Override
        public Institution fromJSONObject(JsonObject institutionJson) {
            Institution institution = new Institution();
            institution.setId(institutionJson.get(JsonFieldName.ID).getAsLong());
            institution.setName(institutionJson.get(JsonFieldName.INSTITUTION_NAME).getAsString());
            institution.setCity(institutionJson.get(JsonFieldName.CITY).getAsString());
            institution.setStreet(institutionJson.get(JsonFieldName.STREET).getAsString());
            institution.setAddress(institutionJson.get(JsonFieldName.ADDRESS).getAsString());
            try {
                institution.setType(InstitutionType.getByTag(institutionJson.get(JsonFieldName.INSTITUTION_TYPE).getAsString()));
            } catch (TypeNotFoundException e) {
                institution.setType(null);
                e.printStackTrace();
            }
            institution.setPulpits(JsonUtils.asList(institutionJson.getAsJsonArray(JsonFieldName.PULPITS), Pulpit.PARCEL));
            return institution;
        }
    };


    public enum InstitutionType{
        UNIVERSITY("Уиверситет"),
        COLLEGE("Техикум");

        private String type;

        InstitutionType(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }

        public static InstitutionType getByTag(String type) throws TypeNotFoundException{
            switch (type){
                case "Уиверситет": return UNIVERSITY;
                case "Техикум": return COLLEGE;
                default: throw new TypeNotFoundException();
            }
        }
    }

    public static class TypeNotFoundException extends Exception{
        TypeNotFoundException(){
            super("Institution type not found");
        }
    }

    public static class JsonFieldName{
        public static final String ID = "id";
        public static final String INSTITUTION_NAME = "institution_name";
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String ADDRESS = "address";
        public static final String INSTITUTION_TYPE = "institution_type";
        public static final String PULPITS = "pulpits";
    }

    public static final String DEFAULT_INSTITUTION_JSON = "{\n" +
            "  \"institution_name\": \"PenzGTU\",\n" +
            "  \"city\": \"Penza\",\n" +
            "  \"street\": \"Gagarina\",\n" +
            "  \"address\": \"5\",\n" +
            "  \"institution_type\": \"Уиверситет\"\n" +
            "}";

}
