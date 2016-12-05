package app.models;

import database.Collection;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class Model {
    abstract Model mapValue(ResultSet resultSet) throws SQLException;

    abstract Collection mapValues(ResultSet resultSet) throws SQLException;
}
