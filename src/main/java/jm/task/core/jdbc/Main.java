package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl methodsForUsers = new UserServiceImpl();

        methodsForUsers.createUsersTable();
        methodsForUsers.saveUser("Екатерина", "Иванова", (byte) 24);
        methodsForUsers.saveUser("Максим", "Рябов", (byte) 25);
        methodsForUsers.saveUser("Вадим", "Рябов", (byte) 19);
        methodsForUsers.saveUser("Ральф", "Рябов", (byte) 4);

        methodsForUsers.removeUserById(1);

        List<User> allUsers = methodsForUsers.getAllUsers();

        for (User user : allUsers) {
            System.out.println(user);
        }
        methodsForUsers.cleanUsersTable();
        methodsForUsers.dropUsersTable();
    }
}








