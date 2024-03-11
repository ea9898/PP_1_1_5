package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl methodsForUsers = new UserServiceImpl();

        methodsForUsers.createUsersTable();
        methodsForUsers.saveUser("Екатерина", "Иванова", (byte) 24);
        System.out.println("User с именем — " + methodsForUsers.getAllUsers().get(0).getName() +  " добавлен в базу данных");
        methodsForUsers.saveUser("Максим", "Рябов", (byte) 25);
        System.out.println("User с именем — " + methodsForUsers.getAllUsers().get(1).getName() +  " добавлен в базу данных");
        methodsForUsers.saveUser("Вадим", "Рябов", (byte) 19);
        System.out.println("User с именем — " + methodsForUsers.getAllUsers().get(2).getName() +  " добавлен в базу данных");
        methodsForUsers.saveUser("Ральф", "Рябов", (byte) 4);
        System.out.println("User с именем — " + methodsForUsers.getAllUsers().get(3).getName() +  " добавлен в базу данных");

        for (User user : methodsForUsers.getAllUsers()) {
            System.out.println(user);
        }
        methodsForUsers.cleanUsersTable();
        methodsForUsers.dropUsersTable();
    }
}








