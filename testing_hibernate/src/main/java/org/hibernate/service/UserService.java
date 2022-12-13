package org.hibernate.service;

import org.hibernate.entity.User;

import javax.transaction.SystemException;
import java.util.List;

public interface UserService {
    void createUsersTable() throws SystemException;

    void dropUsersTable() throws SystemException;

    void saveUser(String name, String lastName, byte age) throws SystemException;

    void removeUserById(long id) throws SystemException;

    List<User> getAllUsers();

    void cleanUsersTable() throws SystemException;
}