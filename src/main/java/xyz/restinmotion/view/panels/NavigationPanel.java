package xyz.restinmotion.view.panels;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import xyz.restinmotion.view.pages.MainPage;
import xyz.restinmotion.view.pages.UserDisplayPage;

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

        Link userDisplayPageLink = new Link("user_display_link") {
            @Override
            public void onClick() {
                this.setResponsePage(UserDisplayPage.class);
            }
        };

        this.add(homePageLink);
        this.add(userDisplayPageLink);
    }
}
