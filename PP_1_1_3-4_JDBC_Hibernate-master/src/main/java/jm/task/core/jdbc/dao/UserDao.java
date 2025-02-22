package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();    // создать таблицу

    void dropUsersTable(); //удалить таблицу

    void saveUser(String name, String lastName, byte age);  //добавить юзера

    void removeUserById(long id); // удалить узера по id

    List<User> getAllUsers(); // получить таблицу всех юзеров

    void cleanUsersTable();  // очистить таблицу
}
