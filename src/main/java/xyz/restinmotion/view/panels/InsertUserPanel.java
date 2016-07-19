package xyz.restinmotion.view.panels;

import org.apache.wicket.markup.html.basic.Label;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;

/**
 * Created by efim on 28.06.16.
 */
public class InsertUserPanel extends UserDataFormPanel {

    public InsertUserPanel(String id) {
        super(id);

        this.replace(new Label(HEADER_ID, "Add new user to contents"));
    }

    @Override
    protected void onSubmit() {
        Repository.getRepository().addUserData(userData);
        notifyUserDataFormSubmission();
        userData = new UserData();
        //setResponsePage(MainPage.class);
    }
}
