package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/pred_progect1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException e) {
            System.out.println("Connection ERROR");
        }
        return connection;
    }

    public static void executeUpdate(String query) throws SQLException {
        executeUpdate(query, Collections.emptyMap());
    }

    public static void executeUpdate(String query, Map<Integer, Object> queryArguments) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            fillPreparedStatement(preparedStatement, queryArguments).executeUpdate();
        }
    }

    public static <T> T executeQuery(String query, Function<ResultSet, T> resultSetHandler) throws SQLException {
        return executeQuery(query, Collections.emptyMap(), resultSetHandler);
    }

    public static <T> T executeQuery(String query, Map<Integer, Object> queryArguments, Function<ResultSet, T> resultSetHandler) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = fillPreparedStatement(preparedStatement, queryArguments).executeQuery()) {
            return resultSetHandler.apply(resultSet);
        }
    }

    private static PreparedStatement fillPreparedStatement(PreparedStatement preparedStatement, Map<Integer, Object> queryArguments) throws SQLException {

        for (Map.Entry<Integer, Object> entry : queryArguments.entrySet()) {
            int key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Long) {
                preparedStatement.setLong(key, (Long) value);
            } else if (value instanceof String) {
                preparedStatement.setString(key, (String) value);
            } else if (value instanceof Byte) {
                preparedStatement.setByte(key, (Byte) value);
            } else if (value instanceof Integer) {
                preparedStatement.setInt(key, (Integer) value);
            } else {
                preparedStatement.setObject(key, value);
            }
        }

        return preparedStatement;
    }
}
