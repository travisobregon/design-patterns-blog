package database;

import app.models.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QueryBuilder {
    private DatabaseManager databaseManager;
    private StringBuilder query;

    public QueryBuilder() {
        this.databaseManager = new DatabaseManager();
        this.query = new StringBuilder();
    }

    public Model find(String table, int id, Class modelClass) {
        try (Connection connection = this.databaseManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + table + " where id = ? limit 1");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Model model = (Model) modelClass.newInstance();
            Method method = model.getClass().getMethod("mapValue", ResultSet.class);

            return (Model) method.invoke(model, resultSet);
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public QueryBuilder select() {
        return this.select("*");
    }

    public QueryBuilder select(String fields) {
        this.query.append("select ").append(fields).append(" ");

        return this;
    }

    public QueryBuilder from(String table) {
        this.query.append("from ").append(table).append(" ");

        return this;
    }

    public QueryBuilder where(String field, String condition, Object value) {
        value = checkForStringAndNull(value);

        this.query
            .append("where ").append(field)
            .append(" ").append(condition)
            .append(" ").append(value)
            .append(" ");

        return this;
    }

    public QueryBuilder andWhere(String field, String condition, Object value) {
        value = checkForStringAndNull(value);

        this.query
                .append("and ").append(field)
                .append(" ").append(condition)
                .append(" ").append(value)
                .append(" ");

        return this;
    }

    public QueryBuilder orderBy(String field, String direction) {
        this.query
            .append("order by ").append(field)
            .append(" ").append(direction)
            .append(" ");

        return this;
    }

    public Model first(Class modelClass) {
        try (Connection connection = this.databaseManager.connect()) {
            this.query.append("limit 1");

            ResultSet resultSet = connection.createStatement().executeQuery(this.query.toString());
            Model model = (Model) modelClass.newInstance();
            Method method = model.getClass().getMethod("mapValue", ResultSet.class);

            return (Model) method.invoke(model, resultSet);
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Collection get(Class modelClass) {
        try (Connection connection = this.databaseManager.connect()) {
            ResultSet resultSet = connection.createStatement().executeQuery(this.query.toString());

            Model model = (Model) modelClass.newInstance();
            Method method = model.getClass().getMethod("mapValues", ResultSet.class);

            return (Collection) method.invoke(model, resultSet);
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insert(String table, List<Object> values) {
        this.query.append("insert into ").append(table).append(" values (null,");

        for (Object value: values) {
            value = checkForStringAndNull(value);

            this.query.append(value).append(",");
        }

        this.query = new StringBuilder(this.query.toString().replaceAll(",$", ""));
        this.query.append(")");

        try (Connection connection = this.databaseManager.connect()) {
            connection.createStatement().executeUpdate(this.query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String table, String set, String where) {
        this.query.append("update ").append(table).append(" ")
                  .append("set ").append(set).append(" ")
                  .append("where ").append(where);

        try (Connection connection = this.databaseManager.connect()) {
            connection.createStatement().executeUpdate(this.query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getQuery() {
        return this.query.toString();
    }

    private Object checkForStringAndNull(Object value) {
        if (value == null) {
            return "null";
        }

        return value instanceof String ? "'" + value + "'" : value;
    }
}
