package dao;

import model.Item;
import utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.*;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItemDaoJdbc implements ItemDao{

    private static final String DELETE_BY_ID_SQL = "DELETE FROM ITEMS WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM ITEMS";
    private static final String INSERT_INTO_SQL = "INSERT INTO ITEMS VALUES(NULL,?,?)";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String ITEM_GROUP = "item_group";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_ONE = "image_1";
    private static final String IMAGE_TWO = "image_2";

    private static final Logger LOGGER = Logger.getLogger(ItemDaoJdbc.class.getName());

    private static Connection getConnection() throws DBSystemException {
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

    @Override
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
            LOGGER.error(stringError(INSERT_INTO_SQL), e);
            throw new DBSystemException(stringError(INSERT_INTO_SQL));
        }finally {
            JdbcUtils.closeQuietly(ps);
        }
    }

    @Override
    public void deleteById(Long id) throws DBSystemException {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(DELETE_BY_ID_SQL);
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            LOGGER.error(stringError(DELETE_BY_ID_SQL), e);
            throw new DBSystemException(stringError(DELETE_BY_ID_SQL));
        }finally {
            JdbcUtils.closeQuietly(ps);
        }
    }

    @Override
    public List<Item> selectAll() throws DBSystemException{
        Connection connection = getConnection();
        Statement st;
        ResultSet rs;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(SELECT_ALL_SQL);
            ArrayList<Item> list = new ArrayList<Item>();
            while (rs.next()){
                Item i = new Item();
                i.setId(rs.getLong(ID));
                i.setName(rs.getString(NAME));
                i.setPrice(rs.getInt(PRICE));
                i.setItemGroup(rs.getLong(ITEM_GROUP));
                i.setDescription(rs.getString(DESCRIPTION));
                i.setImageOne(rs.getString(IMAGE_ONE));
                i.setImageTwo(rs.getString(IMAGE_TWO));
                list.add(i);
            }
            return list;
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            LOGGER.error(stringError(SELECT_ALL_SQL), e);
            throw new DBSystemException(stringError(SELECT_ALL_SQL));
        }
    }

    @Override
    public List<Item> selectGroupItems(int idGroup) throws DBSystemException{
        Connection connection = getConnection();
        Statement st;
        ResultSet rs;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(SELECT_ALL_SQL);
            ArrayList<Item> list = new ArrayList<Item>();
            while (rs.next()){
                if (rs.getLong(ITEM_GROUP) == idGroup){
                    Item i = new Item();
                    i.setId(rs.getLong(ID));
                    i.setName(rs.getString(NAME));
                    i.setPrice(rs.getInt(PRICE));
                    i.setItemGroup(rs.getLong(ITEM_GROUP));
                    i.setDescription(rs.getString(DESCRIPTION));
                    i.setImageOne(rs.getString(IMAGE_ONE));
                    i.setImageTwo(rs.getString(IMAGE_TWO));
                    list.add(i);
                }
            }
            return list;
        } catch (SQLException e){
            JdbcUtils.rollbackQuietly(connection);
            LOGGER.error(stringError(SELECT_ALL_SQL), e);
            throw new DBSystemException(stringError(SELECT_ALL_SQL));
        }
    }

    private static String stringError(String requestSQL){
        return "Can't execute SQL request:" + requestSQL;
    }

}
