package xyz.restinmotion;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by efim on 29.06.16.
 */
public class UserDisplayPage extends BaseLayout {
    public UserDisplayPage(PageParameters parameters) {
        super(parameters);

        this.replace(new UserDisplayPanel(CONTENT_ID));
    }
}
