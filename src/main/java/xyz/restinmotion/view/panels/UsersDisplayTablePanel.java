package xyz.restinmotion.view.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;
import xyz.restinmotion.view.pages.ModifyUserDetailsPopup;

import java.util.List;

/**
 * Created by efim on 29.06.16.
 */
public class UsersDisplayTablePanel extends Panel {

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

                Link modifyUserLink = new Link(repeatingView.newChildId()) {
                    @Override
                    public void onClick() {
                        PageParameters params = new PageParameters();
                        params.add("userId", data.getUuid().toString());
                        this.setResponsePage(ModifyUserDetailsPopup.class, params);
                    }
                };
                PopupSettings popupSettings = new PopupSettings();
                modifyUserLink.setPopupSettings(popupSettings);
                repeatingView.add(modifyUserLink);
                modifyUserLink.setBody(Model.of("[modify]"));

                item.add(repeatingView);
            }
        };

        userDataView.setItemsPerPage(20);

        this.add(userDataView);
        this.add(new PagingNavigator("user_data_paging_navigator", userDataView));
    }
}
