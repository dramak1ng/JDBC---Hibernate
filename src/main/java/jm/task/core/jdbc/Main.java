package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("da","da", (byte) 12);
        userDao.saveUser("qw","wq", (byte) 22);
        userDao.saveUser("ew","re", (byte) 32);
        userDao.saveUser("re","re", (byte) 42);
        System.out.println(userDao.getAllUsers());
    }
}
