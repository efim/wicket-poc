package xyz.restinmotion.view.panels;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;

import java.util.List;

/**
 * Created by efim on 29.06.16.
 */
public class UsersDisplayTablePanel extends Panel {
    private Panel userPanel;

    public UsersDisplayTablePanel(String id) {
        super(id);

        //List<UserData> userDataList = Repository.getRepository().getUserList();
        //other way is to have this as local cache and update on AJAX calls - probably much better, but not a priority a priori
        ListDataProvider<UserData> listDataProvider = new ListDataProvider<UserData>() {
            @Override
            protected List<UserData> getData() {
                return Repository.getRepository().getUserList();
            }
        };

        MarkupContainer usersTableContainer = new WebMarkupContainer("users_table_container");

        DataView<UserData> userDataView = new DataView<UserData>("user_table_rows", listDataProvider) {
            @Override
            protected void populateItem(Item<UserData> item) {
                final UserData data = item.getModelObject();

                RepeatingView repeatingView = new RepeatingView("user_data_row");

                repeatingView.add(new Label(repeatingView.newChildId(), data.getFirstName()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getLastName()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getSex()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getBirthDate().toString()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getTemperature().toString()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getAllergies().toString()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getBrainDamagePreference()));

                AjaxLink modifyUserLink = new AjaxLink(repeatingView.newChildId()) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        if (userPanel != null) {
                            System.out.println("appending userData: " + data.getFirstName().toString() + " " + data.getLastName().toString());
                            //userPanel.setUserData(data);
                            target.add(userPanel);
                        }
                    }
                };

                repeatingView.add(modifyUserLink);
                modifyUserLink.setBody(Model.of("[modify]"));

                item.add(repeatingView);
            }
        };

        userDataView.setItemsPerPage(20);

        usersTableContainer.setOutputMarkupId(true);
        usersTableContainer.add(userDataView);
        usersTableContainer.add(new PagingNavigator("user_data_paging_navigator", userDataView));

        this.add(usersTableContainer);

        this.add(userPanel = new InsertUserPanel("user_data_panel"));
        userPanel.setOutputMarkupId(true);
        userPanel.replace(new Label(UserDataFormPanel.HEADER_ID));

        this.add(new AjaxSubmitLink("user_data_ajax_submit", (Form) userPanel.get("new_user_form")) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                target.add(usersTableContainer);
            }
        });
    }
}
