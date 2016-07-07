package xyz.restinmotion.view.panels;

import org.apache.wicket.Session;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.StringValidator;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;
import xyz.restinmotion.view.pages.MainPage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

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
                Repository.getRepository().addUserData(userData);
                notifyUserDataFormSubmission();
                setResponsePage(MainPage.class);
            }
        };

        final RequiredTextField<String> tFirstName = new RequiredTextField<>("first_name", userDataModel.<String>bind("firstName"));
        tFirstName.add(StringValidator.maximumLength(20));

        final RequiredTextField<String> tLastName = new RequiredTextField<>("last_name", userDataModel.<String>bind("lastName"));
        tFirstName.add(StringValidator.maximumLength(20));

        final DropDownChoice<String> dsSexSeletion = new DropDownChoice<>("sex_selection",
                userDataModel.<String>bind("sex"), SEX_VALUES);

        final RequiredTextField<Double> tTemperature = new RequiredTextField<>("temperature",
                userDataModel.<Double>bind("temperature"));
        final IValidator<Double> temperatureValidator = new IValidator<Double>() {
            @Override
            public void validate(IValidatable<Double> validatable) {
                Double submittedTemperature = validatable.getValue();
                Double lowerBoundTemperature = 0.0;
                Double higherBoundTemperature = 43.0;
                if (submittedTemperature < lowerBoundTemperature
                        || submittedTemperature > higherBoundTemperature) {

                    ValidationError error = new ValidationError("Submitted temperature is incorrect. ");
                    error.setVariable("lowerBound", lowerBoundTemperature.toString());
                    error.setVariable("higherBound", higherBoundTemperature.toString());
                    validatable.error(error);
                }
            }
        };
        tTemperature.add(temperatureValidator);

        final DateTextField tBirthDate = new DateTextField("birth_date",
                userDataModel.<Date>bind("birthDate"), new StyleDateConverter("M-", true));
        final IValidator<Date> birthDateValidator = new IValidator<Date>() {
            @Override
            public void validate(IValidatable<Date> validatable) {
                Date chosenBirthDate = validatable.getValue();
                Calendar lowerBoundDate = Calendar.getInstance();
                lowerBoundDate.add(Calendar.YEAR, -150);

                if (chosenBirthDate.after(new Date())
                        || chosenBirthDate.before(lowerBoundDate.getTime()) )
                {
                    ValidationError error = new ValidationError("Birth date is incorrect. ");
                    error.setVariable("lowerBoundDate", lowerBoundDate.getTime().toString());
                    validatable.error(error);
                }
            }
        };
        tBirthDate.add(birthDateValidator);

        final CheckBoxMultipleChoice<String> cbmcAllergies = new CheckBoxMultipleChoice<String>("allergies_selection",
                userDataModel.<List<String>>bind("allergies"), ALLERGY_VALUES);

        final DatePicker birthDateDatePicker = new DatePicker() {
            @Override
            protected String getAdditionalJavaScript()
            {
                return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
            }
        };
        birthDateDatePicker.setShowOnFieldClick(true);
        birthDateDatePicker.setAutoHide(true);
        tBirthDate.add(birthDateDatePicker);

        final RadioChoice<String> rcBrainDamagePreference = new RadioChoice<>("brain_damage_selection",
                userDataModel.<String>bind("brainDamagePreference"), BRAIN_DAMAGE_PREFERENCE_VALUES);
        rcBrainDamagePreference.setRequired(Boolean.TRUE);

        this.add(userDataForm);
        userDataForm.add(tFirstName);
        userDataForm.add(tLastName);
        userDataForm.add(dsSexSeletion);
        userDataForm.add(rcBrainDamagePreference);
        userDataForm.add(cbmcAllergies);
        userDataForm.add(tBirthDate);
        userDataForm.add(tTemperature);
    }

    private void notifyUserDataFormSubmissionDetails() {
        Session session = this.getSession();
        session.info("form is submitted!");
        session.info("first name : " + userData.getFirstName());
        session.info("last name : " + userData.getLastName());
        session.info("sex : " + userData.getSex());
        session.info("allergies : " + userData.getAllergies().toString());
        session.info("brain damage preference : " + userData.getBrainDamagePreference().toString());
    }

    private void notifyUserDataFormSubmission() {
        Session session = this.getSession();
        session.info("New user with" +
                " name : " + userData.getFirstName() + " " + userData.getLastName()
                + "and id : " + userData.getUuid().toString() + " has been added.");
    }
}
