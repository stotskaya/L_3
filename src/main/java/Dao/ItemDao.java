package Dao;

import Model.Item;
import Exception.*;

import java.util.List;

public interface ItemDao {

    void insert(Item item) throws DBSystemException;

    void deleteById(Long id) throws DBSystemException;

    List<Item> selectAll() throws DBSystemException;

    List<Item> selectGroupItems(int idGroup) throws DBSystemException;
}
