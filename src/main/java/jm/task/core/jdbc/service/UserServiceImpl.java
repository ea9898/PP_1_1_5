package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        daoJDBC.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        daoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        daoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        daoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return daoJDBC.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        daoJDBC.cleanUsersTable();
    }
}
