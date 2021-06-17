package io.github.kickit123.TranquilBot.models;

import org.bson.types.ObjectId;

import java.util.List;

public class Economy {
    private ObjectId id;
    private long server_id;
    private List<EconomyUser> users;

    public Economy() {
    }

    public Economy(long server_id, List<EconomyUser> users) {
        this.server_id = server_id;
        this.users = users;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getServer_id() {
        return server_id;
    }

    public void setServer_id(long server_id) {
        this.server_id = server_id;
    }

    public List<EconomyUser> getUsers() {
        return users;
    }

    public void addUser(EconomyUser user) {
        users.add(user);
    }

    public void removeUser(EconomyUser user) {
        users.remove(user);
    }
}
