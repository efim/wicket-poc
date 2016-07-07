package xyz.restinmotion.view.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import xyz.restinmotion.data.Repository;
import xyz.restinmotion.data.UserData;

import java.util.List;

/**
 * Created by efim on 29.06.16.
 */
public class UserDisplayPanel extends Panel {

    public UserDisplayPanel(String id) {
        super(id);

        List<UserData> userDataList = Repository.getRepository().getUserList();
        ListDataProvider<UserData> listDataProvider = new ListDataProvider<>(userDataList);

        DataView<UserData> userDataView = new DataView<UserData>("user_table_rows", listDataProvider) {
            @Override
            protected void populateItem(Item<UserData> item) {
                UserData data = item.getModelObject();

                RepeatingView repeatingView = new RepeatingView("user_data_row");
                repeatingView.add(new Label(repeatingView.newChildId(), data.getFirstName()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getLastName()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getSex()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getBirthDate().toString()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getTemperature().toString()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getAllergies().toString()));
                repeatingView.add(new Label(repeatingView.newChildId(), data.getBrainDamagePreference()));
                item.add(repeatingView);
            }
        };

        userDataView.setItemsPerPage(20);

        this.add(userDataView);
        this.add(new PagingNavigator("user_data_paging_navigator", userDataView));
    }
}
