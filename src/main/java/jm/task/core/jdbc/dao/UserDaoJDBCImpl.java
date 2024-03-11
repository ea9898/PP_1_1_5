package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoJDBCImpl extends Util implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "    id SERIAL NOT NULL PRIMARY KEY," +
                "    name VARCHAR(45) NOT NULL," +
                "    lastName VARCHAR(45) NOT NULL," +
                "    age INT NOT NULL" +
                ");";

        Util.executeUpdate(sql);
    }

    public void dropUsersTable() throws SQLException {
        Util.executeUpdate("DROP TABLE IF EXISTS users;");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        executeUpdate(
                "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)",
                Map.of(1, name, 2, lastName, 3, age));
    }

    public void removeUserById(long id) throws SQLException {
        Util.executeUpdate(
                "DELETE FROM users WHERE id = ?;",
                Map.of(1, id));
    }

    public List<User> getAllUsers() throws SQLException {

        return Util.executeQuery("SELECT * FROM users",
                resultSet -> {
                    List<User> allUsers = new ArrayList<>();
                    try {
                        while (resultSet.next()) {
                            User user = new User();
                            user.setId(resultSet.getLong("id"));
                            user.setName(resultSet.getString("name"));
                            user.setLastName(resultSet.getString("lastName"));
                            user.setAge(resultSet.getByte("age"));

                            allUsers.add(user);
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    return allUsers;

                });
    }

    public void cleanUsersTable() throws SQLException {
        Util.executeUpdate("DELETE FROM users");
    }
}
