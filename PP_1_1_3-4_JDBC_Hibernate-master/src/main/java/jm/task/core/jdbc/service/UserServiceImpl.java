package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Statement;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();



    public void createUsersTable() {
        userDao.createUsersTable();


    }
    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();

    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        //System.out.println(name + " " + lastName + " " + age);

    }
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();

    }
}
