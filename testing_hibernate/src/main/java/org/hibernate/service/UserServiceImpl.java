package org.hibernate.service;




import org.hibernate.dao.UserDao;
import org.hibernate.dao.UserDaoHibernateImpl;
import org.hibernate.entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {




    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age)  {
        userDao.saveUser(name, lastName, age);
    }
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }




    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    public void cleanUsersTable()  {
        userDao.cleanUsersTable();
    }
}



