package xyz.restinmotion.view.panels;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;
import xyz.restinmotion.view.pages.MainPage;
import xyz.restinmotion.view.pages.ModifyUserDetailsPage;
import xyz.restinmotion.view.pages.UsersDisplayPage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
                this.setResponsePage(UsersDisplayPage.class);
            }
        };

        Link modifyRandomUserLink = new Link("modify_random_user_link") {
            @Override
            public void onClick() {
                PageParameters params = new PageParameters();
                String randomUserUUID = "";
                if (!Repository.getRepository().getUserList().isEmpty()) {
                    List<UserData> repoList = Repository.getRepository().getUserList();
                    randomUserUUID = repoList.get(ThreadLocalRandom.current().nextInt(repoList.size()))
                            .getUuid().toString();
                }
                params.add("userId", randomUserUUID);

                this.setResponsePage(ModifyUserDetailsPage.class, params);
            }
        };

        this.add(homePageLink);
        this.add(userDisplayPageLink);
        this.add(modifyRandomUserLink);
    }
}
