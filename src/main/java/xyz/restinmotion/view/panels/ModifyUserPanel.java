package xyz.restinmotion.view.panels;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import xyz.restinmotion.data.UserData;

/**
 * Created by efim on 07.07.16.
 */
public class ModifyUserPanel extends UserDataFormPanel {

    public ModifyUserPanel(String id) {
        super(id);
    }

    public ModifyUserPanel(String id, UserData userData) {
        super(id, userData);

        replace(new Label(HEADER_ID, "Modify user data"));
    }

    @Override
    protected void onSubmit() {
        this.notifyUserDataFormSubmission();
    }

    @Override
    protected void notifyUserDataFormSubmission() {
        Session session = this.getSession();
        session.info("User data is updated only when you press this button. Why not instantaneously?");
    }
}
