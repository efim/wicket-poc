package xyz.restinmotion.view.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import xyz.restinmotion.view.panels.UserDisplayPanel;

/**
 * Created by efim on 29.06.16.
 */
public class UsersDisplayPage extends BaseLayout {
    public UsersDisplayPage(PageParameters parameters) {
        super(parameters);

        this.replace(new UserDisplayPanel(CONTENT_ID));
    }
}
