package xyz.restinmotion.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by efim on 28.06.16.
 */
public class UserData implements Serializable{
    private UUID uuid = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String sex;
    private List<String> allergies;
    private String brainDamagePreference;

    public UserData() {
        firstName = "";
        lastName = "";
        sex = "";
        brainDamagePreference = "";
        allergies = new ArrayList<String>();
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
