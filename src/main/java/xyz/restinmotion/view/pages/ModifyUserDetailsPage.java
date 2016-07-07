package xyz.restinmotion.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;
import xyz.restinmotion.view.panels.ModifyUserPanel;

import java.util.UUID;

/**
 * Created by efim on 07.07.16.
 */
public class ModifyUserDetailsPage extends BaseLayout {
    private UserData userData;

    public ModifyUserDetailsPage(PageParameters parameters) {
        super(parameters);

        StringValue userId = parameters.get("userId");

        if (userId.isEmpty()) {
            replace(new Label(CONTENT_ID, "User to modify not found"));
            return;
        }

        userData = Repository.getRepository().getById(UUID.fromString(userId.toString()));

        replace(new ModifyUserPanel(CONTENT_ID, userData));

    }
}
