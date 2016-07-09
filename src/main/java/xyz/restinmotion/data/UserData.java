package xyz.restinmotion.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by efim on 28.06.16.
 */
public class UserData implements Serializable{
    private final UUID uuid = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String sex;
    private Date birthDate;
    private Double temperature;
    private List<String> allergies;
    private String brainDamagePreference;

    public UserData() {
        firstName = "";
        lastName = "";
        sex = "";
        brainDamagePreference = "";
        allergies = new ArrayList<String>();
    }

    public UserData(String firstName, String lastName, String sex, Date birthDate,
                    Double temperature, List<String> allergies, String brainDamagePreference) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.temperature = temperature;
        this.allergies = allergies;
        this.brainDamagePreference = brainDamagePreference;
    }


    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getBrainDamagePreference() {
        return brainDamagePreference;
    }

    public void setBrainDamagePreference(String brainDamagePreference) {
        this.brainDamagePreference = brainDamagePreference;
    }
}
