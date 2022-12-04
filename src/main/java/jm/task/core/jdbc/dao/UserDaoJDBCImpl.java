package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection con = getConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createString = "create table storetable " + "(id int auto_increment primary key, " + "name varchar(30) null, " + "lastName varchar(30) null, " + "age int null)";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS storetable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM storetable");
            uprs.moveToInsertRow();
            uprs.updateString("name", name);
            uprs.updateString("lastName", lastName);
            uprs.updateInt("age", age);
            uprs.insertRow();
            uprs.beforeFirst();
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM storetable WHERE ID=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM storetable";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        List<User> users = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastName"));
            user.setAge((byte) resultSet.getInt("age"));
            users.add(user);
        }
        return users;
    }



    public void cleanUsersTable() {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE storetable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
