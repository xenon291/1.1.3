package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    // создать таблицу
    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, lastname VARCHAR(255) NOT NULL, age TINYINT)";

        try (Connection conn = Util.getConnection()){
            Statement statement = conn.createStatement();
            statement.execute(sqlCommand);


        } catch (SQLException e) {

            throw new RuntimeException(e);

        }


    }
    //удалить таблицу

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users";
        try (Connection conn = Util.getConnection();
            Statement statement = conn.createStatement()){
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveUser(String name, String lastname, byte age) {
        //PreparedStatement statement = null;
        String sqlUsers = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        if (name == null || name.isBlank() || lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Имя и фамилия не должны быть пустыми.");
        }

        try (Connection conn = Util.getConnection();
             PreparedStatement statement = conn.prepareStatement(sqlUsers)) {

            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setByte(3, age);

            int r = statement.executeUpdate();

            if (r > 0) {
                System.out.printf("Пользователь %s %s %d добавлен\n", name, lastname, age);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String sqlUser = "DELETE FROM users WHERE id = ?";
        try(Connection conn = Util.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlUser)) {
            statement.setLong(1, id);
            statement.executeUpdate();


        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        String sqlUsers = "SELECT * FROM users";
        List<User> allUsers = new ArrayList<>();
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlUsers);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");
                allUsers.add(new User(id, name, lastname, age));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (User user : allUsers) {
            System.out.println(user);
        }

        return allUsers;

    }

    public void cleanUsersTable() {
        String sqlUsers = "DELETE FROM users";
        try (Connection conn = Util.getConnection();
            Statement statement = conn.createStatement()){
            statement.executeUpdate(sqlUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
