package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoJDBCImpl impl = new UserDaoJDBCImpl();

    public void createUsersTable() {
        impl.createUsersTable();
    }

    public void dropUsersTable() {
        impl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        impl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        impl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return impl.getAllUsers();
    }

    public void cleanUsersTable() {
        impl.cleanUsersTable();
    }
}
