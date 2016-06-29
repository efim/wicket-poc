package xyz.restinmotion.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by efim on 29.06.16.
 */
public class Repository {
    private HashMap<UUID, UserData> users;

    private Repository() {
        users = new HashMap<>();
    }

    public void addUserData(UserData record) {
        users.put(record.getUuid(), record);
    }

    public List<UserData> getUserList() {
        return new ArrayList<UserData>(users.values());
    }

    public Repository getRepository() {
        return RepoHolder.INSTANCE;
    }

    private static class RepoHolder {
        public static Repository INSTANCE = new Repository();
    }
}
