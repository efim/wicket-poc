package xyz.restinmotion;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by efim on 28.06.16.
 */
public class MainPage extends BaseLayout {
    public MainPage(PageParameters parameters) {
        super(parameters);

        this.replace(new InsertUserPanel(CONTENT_ID));
    }
}
