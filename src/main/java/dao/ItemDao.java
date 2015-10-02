package dao;

import model.Item;
import exception.*;

import java.util.List;

public interface ItemDao {

    void insert(Item item) throws DBSystemException;

    void deleteById(Long id) throws DBSystemException;

    List<Item> selectAll() throws DBSystemException;

    List<Item> selectGroupItems(int idGroup) throws DBSystemException;
}
