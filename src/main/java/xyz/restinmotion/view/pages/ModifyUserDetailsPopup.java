package xyz.restinmotion.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;
import xyz.restinmotion.view.panels.ModifyUserPanel;

import java.util.UUID;

/**
 * Created by efim on 09.07.16.
 */
public class ModifyUserDetailsPopup extends WebPage {
    private UserData userData;
    protected static final String CONTENT_ID = "contentComponent";

    public ModifyUserDetailsPopup(PageParameters parameters) {
        super(parameters);

        this.add(new FeedbackPanel("feedback"));

        StringValue userId = parameters.get("userId");

        if (userId.isEmpty()) {
            this.add(new Label(CONTENT_ID, "User to modify not found"));
            return;
        }

        userData = Repository.getRepository().getById(UUID.fromString(userId.toString()));
        this.add(new ModifyUserPanel(CONTENT_ID, userData));

    }
}
