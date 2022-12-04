package jm.task.core.jdbc;

import com.mysql.cj.xdevapi.Client;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        Util util = new Util();

        util.getConnection();

        UserDao userDao = new UserDaoJDBCImpl();

        User user = new User();

        userDao.dropUsersTable();

        userDao.createUsersTable();

        userDao.saveUser("jack", "Richer", (byte) 45);

        //userService.cleanUsersTable();

        //userService.removeUserById(1);

        //userService.removeUserById(2);

        List<User> userList = userDao.getAllUsers();
        for(User  u: userList){
            System.out.println(u.getId() + " " + u.getName() + " " + u.getLastName() + " " + u.getAge());
        }

    }
}



