package org.hibernate;

import org.hibernate.dao.UserDao;
import org.hibernate.dao.UserDaoHibernateImpl;
import org.hibernate.entity.User;


import java.util.List;


public class Main {
    public static void main(String[] args){


        UserDao userDao = new UserDaoHibernateImpl();


        User user = new User();

        userDao.dropUsersTable();

        userDao.createUsersTable();

        userDao.saveUser("jack", "Richer", (byte) 45);

        userDao.saveUser("Ramesh", "Fadatare", (byte) 25);


        List<User> userList = userDao.getAllUsers();
        for(User  u: userList){
        System.out.println(u.getId() + " " + u.getName() + " " + u.getLastName() + " " + u.getAge());
        }
    }
}
