package app.models;

import database.Collection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Model {
    private Integer id;
    private String provider;
    private Integer providerId;
    private String name;
    private String email;
    private String avatar;
    private Boolean isAdmin;
    private Boolean isTrusted;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsTrusted() {
        return isTrusted;
    }

    public void setIsTrusted(Boolean isTrusted) {
        this.isTrusted = isTrusted;
    }

    private User map(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setProvider(resultSet.getString("provider"));
        user.setProviderId(resultSet.getInt("provider_id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setAvatar(resultSet.getString("avatar"));
        user.setIsAdmin(resultSet.getBoolean("admin"));
        user.setIsTrusted(resultSet.getBoolean("trusted"));

        return user;
    }

    @Override
    public Model mapValue(ResultSet resultSet) throws SQLException {
        User user = null;

        while (resultSet.next()) {
            user = map(resultSet);
        }

        return user;
    }

    @Override
    public Collection mapValues(ResultSet resultSet) throws SQLException {
        Collection<User> collection = new Collection<>(new ArrayList<User>());

        while (resultSet.next()) {
            collection.addItem(map(resultSet));
        }

        return collection;
    }
}
