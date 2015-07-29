package Dao;

import Model.User;
import Exception.*;
import Utils.JdbcUtils;

import java.sql.*;

public class UserDaoJdbc implements UserDao{

    public static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    public static final String LOGIN = "system";
    public static final String PASSWORD = "1";
    public static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";

    public static final String DELETE_BY_ID_SQL = "DELETE FROM ITEM WHERE id = ?";
    public static final String SELECT_WHERE_SQL = "SELECT * FROM USERS WHERE LOGIN = ?";
    public static final String INSERT_INTO_SQL = "INSERT INTO USERS VALUES(?,?,?,?,?,?)";

    static {
        JdbcUtils.initDriver(DRIVER_CLASS_NAME);
    }

    private Connection getConnection() throws DBSystemException {
        try {
            return DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
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
