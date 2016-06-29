package xyz.restinmotion;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by efim on 28.06.16.
 */
public class InsertUserPanel extends Panel {
    private static final List<String> SEX_VALUES = Arrays.asList(new String[] {"male", "female", "other"});
    private static final List<String> ALLERGY_VALUES = Arrays.asList(new String[] {"Nuts", "Flowers", "Babies", "Rainbows"});
    private static final List<String> BRAIN_DAMAGE_PREFERENCE_VALUES =
            Arrays.asList(new String[] {"Keep alive", "Terminate", "Family decides"});
    private UserData userData = new UserData();

    public InsertUserPanel(String id) {
        super(id);

        final CompoundPropertyModel<UserData> userDataModel = new CompoundPropertyModel<>(userData);
        final Form<UserData> userDataForm = new Form<UserData>("new_user_form",
                userDataModel) {
            @Override
            protected void onSubmit() {
                Session sesstion = this.getSession();
                sesstion.info("form is submitted!");
                sesstion.info("first name : " + userData.getFirstName());
                sesstion.info("last name : " + userData.getLastName());
                sesstion.info("sex : " + userData.getSex());
                sesstion.info("allergies : " + userData.getAllergies().toString());
                sesstion.info("brain damage preference : " + userData.getBrainDamagePreference().toString());
            }
        };

        final TextField<String> tFirstName = new TextField<>("first_name", userDataModel.<String>bind("firstName"));

        final TextField<String> tLastName = new TextField<>("last_name", userDataModel.<String>bind("lastName"));

        final DropDownChoice<String> dsSexSeletion = new DropDownChoice<>("sex_selection",
                userDataModel.<String>bind("sex"), SEX_VALUES);

        final CheckBoxMultipleChoice<String> cbmcAllergies = new CheckBoxMultipleChoice<String>("allergies_selection",
                userDataModel.<List<String>>bind("allergies"), ALLERGY_VALUES);

        final RadioChoice<String> rcBrainDamagePreference = new RadioChoice<>("brain_damage_selection",
                userDataModel.<String>bind("brainDamagePreference"), BRAIN_DAMAGE_PREFERENCE_VALUES);

        this.add(userDataForm);
        userDataForm.add(tFirstName);
        userDataForm.add(tLastName);
        userDataForm.add(dsSexSeletion);
        userDataForm.add(rcBrainDamagePreference);
        userDataForm.add(cbmcAllergies);


    }
}
