package xyz.restinmotion;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by efim on 28.06.16.
 */
public class NavigationPanel extends Panel {
    public NavigationPanel(String id) {
        super(id);
        Link homePageLink = new Link("home_link") {
            @Override
            public void onClick() {
                this.setResponsePage(MainPage.class);
            }
        };

        this.add(homePageLink);
    }
}
