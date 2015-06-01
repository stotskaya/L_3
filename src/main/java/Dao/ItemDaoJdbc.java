package Dao;

import Model.Item;
import Utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Exception.*;

public class ItemDaoJdbc implements ItemDao{

    public static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    public static final String LOGIN = "system";
    public static final String PASSWORD = "1";
    public static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";

    public static final String DELETE_BY_ID_SQL = "DELETE FROM ITEM WHERE id = ?";
    public static final String SELECT_ALL_SQL = "SELECT * FROM ITEM";
    public static final String INSERT_INTO_SQL = "INSERT INTO ITEM VALUES(NULL,?,?)";

    static {
        JdbcUtils.initDriver(DRIVER_CLASS_NAME);
    }

    private Connection getConnection() throws DBSystemException {
        try {
            return DriverManager.getConnection(JDBC_URL,LOGIN,PASSWORD);
        } catch (SQLException e) {
            throw new DBSystemException("Can't create connection" + e);
        }
    }

    public void insert(Item item) throws DBSystemException{
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(INSERT_INTO_SQL);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
            ps.execute();
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            throw new DBSystemException("Can't execute SQL = " + INSERT_INTO_SQL);
        }finally {
            JdbcUtils.closeQuietly(ps);
        }
    }

    public void deleteById(Long id) throws DBSystemException {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(DELETE_BY_ID_SQL);
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            throw new DBSystemException("Can't execute SQL = " + DELETE_BY_ID_SQL);
        }finally {
            JdbcUtils.closeQuietly(ps);
        }
    }

    public List<Item> selectAll() throws DBSystemException{
        Connection connection = getConnection();
        Statement st;
        ResultSet rs = null;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(SELECT_ALL_SQL);
            ArrayList<Item> list = new ArrayList<Item>();
            while (rs.next()){
                Item i = new Item();
                i.setId(rs.getLong("id"));
                i.setName(rs.getString("name"));
                i.setPrice(rs.getInt("price"));
                list.add(i);
            }
            return list;
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            throw new DBSystemException("Can't execute SQL = " + SELECT_ALL_SQL);
        }
    }
}
