package xyz.restinmotion.view.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import xyz.restinmotion.view.panels.InsertUserPanel;

/**
 * Created by efim on 28.06.16.
 */
public class MainPage extends BaseLayout {
    public MainPage(PageParameters parameters) {
        super(parameters);

        this.replace(new InsertUserPanel(CONTENT_ID));
    }
}
