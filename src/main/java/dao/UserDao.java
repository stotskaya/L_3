package dao;


import model.User;
import exception.*;

public interface UserDao {

    void insert(User user) throws DBSystemException;

    String identification(String login, String password) throws DBSystemException;

}
