package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {
    Logger logger = Logger.getLogger(UserDaoHibernateImpl.class.getName());
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name VARCHAR(64) NOT NULL, lastname VARCHAR(64) NOT NULL, age BIGINT NOT NULL)").executeUpdate();
            transaction.commit();
            logger.info("Была создана таблица");
        } catch (HibernateException e) {
            logger.info("Ошибка при создании таблицы");
            if (transaction != null) transaction.rollback();
        }
    }


    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            logger.info("Таблица была удалена");

        } catch (HibernateException e) {
            logger.info("Произошла ошибка при удалении таблицы");
            if (transaction!= null) transaction.rollback();

        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));
            logger.info("был создан пользовательн: " + name);
            transaction.commit();

        } catch (HibernateException e) {
            logger.info("ошибка при создании "+e);
            if (transaction != null) transaction.rollback();
        }



    }


    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            logger.info("Пользователь удален:  " + id);
            transaction.commit();

        } catch (HibernateException e) {
            logger.info("Произошла ошибка при удалении пользователя");
            if (transaction != null) transaction.rollback();
        }


    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            users = session.createQuery("FROM User", User.class).list();
            session.beginTransaction().commit();
        } catch (HibernateException e) {
            logger.info("Произошла ошибка");



        }
        return users;









    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.createSQLQuery("Truncate table users").executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                logger.info("Произошла ошибка");
                transaction.rollback();
            }
        }
    }

}