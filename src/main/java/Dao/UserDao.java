package Dao;


import Model.User;
import Exception.*;

public interface UserDao {

    void insert(User user) throws DBSystemException;

    String identification(String login, String password) throws DBSystemException;

}
