package Dao;

import Model.User;
import Exception.*;
import Utils.JdbcUtils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao{

    public static final String DELETE_BY_ID_SQL = "DELETE FROM ITEM WHERE id = ?";
    public static final String SELECT_WHERE_SQL = "SELECT * FROM USERS WHERE LOGIN = ?";
    public static final String INSERT_INTO_SQL = "INSERT INTO USERS VALUES(?,?,?,?,?,?)";

    private Connection getConnection() throws DBSystemException {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/appname");
            return ds.getConnection();
        } catch (NamingException e) {
            throw new DBSystemException("Can't create connection" + e);
        } catch (SQLException e) {
            throw new DBSystemException("Can't create connection" + e);
        }
    }

    public void insert(User user) throws DBSystemException {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(INSERT_INTO_SQL);
            ps.setString(1, user.getLastName());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getNumber());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getLogin());
            ps.setString(6, "");
            ps.execute();
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            throw new DBSystemException("Can't execute SQL = " + INSERT_INTO_SQL);
        } finally {

            JdbcUtils.closeQuietly(ps);
            JdbcUtils.closeQuietly(connection);
        }
    }

    public String identification(String login, String password) throws DBSystemException {
        Connection connection = getConnection();
        try{
            PreparedStatement prep = connection.prepareStatement(SELECT_WHERE_SQL);
            prep.setString(1, login);
            ResultSet rs = prep.executeQuery();
            while (rs.next()){
                if (rs.getString("password").equals(password)){
                    return "Success";
                }
            }
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            throw new DBSystemException("Can't execute SQL = " + INSERT_INTO_SQL);
        }
        return "Error";
    }
}
