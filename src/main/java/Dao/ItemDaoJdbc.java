package Dao;

import Model.Item;
import Utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Exception.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItemDaoJdbc implements ItemDao{

    public static final String DELETE_BY_ID_SQL = "DELETE FROM ITEMS WHERE id = ?";
    public static final String SELECT_ALL_SQL = "SELECT * FROM ITEMS";
    public static final String INSERT_INTO_SQL = "INSERT INTO ITEMS VALUES(NULL,?,?)";

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

            List<Item> list = new ArrayList<Item>();
            while (rs.next()){

                Item i = new Item();
                i.setId(rs.getLong("id"));
                i.setName(rs.getString("name"));
                i.setPrice(rs.getInt("price"));
                i.setItemGroup(rs.getLong("item_group"));
                i.setDescription(rs.getString("description"));
                i.setImage_1(rs.getString("image_1"));
                i.setImage_2(rs.getString("image_2"));
                list.add(i);

            }
            return list;
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            throw new DBSystemException("Can't execute SQL = " + SELECT_ALL_SQL);
        }
    }


    public List<Item> selectGroupItems(int idGroup) throws DBSystemException{
        Connection connection = getConnection();
        Statement st;
        ResultSet rs = null;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(SELECT_ALL_SQL);

            ArrayList<Item> list = new ArrayList<Item>();
            while (rs.next()){
                if (rs.getLong("item_group") == idGroup){
                    Item i = new Item();
                    i.setId(rs.getLong("id"));
                    i.setName(rs.getString("name"));
                    i.setPrice(rs.getInt("price"));
                    i.setItemGroup(rs.getLong("item_group"));
                    i.setDescription(rs.getString("description"));
                    i.setImage_1(rs.getString("image_1"));
                    i.setImage_2(rs.getString("image_2"));
                    list.add(i);
                }
            }
            return list;
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            throw new DBSystemException("Can't execute SQL = " + SELECT_ALL_SQL);
        }
    }

}
