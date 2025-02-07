package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao user = new UserDaoHibernateImpl();
        user.createUsersTable();
        user.saveUser("da","da", (byte) 12);
        user.saveUser("qw","wq", (byte) 22);
        user.saveUser("ew","re", (byte) 32);
        user.saveUser("re","re", (byte) 42);
        System.out.println(user.getAllUsers());
    }
}
