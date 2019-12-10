package tictactoe.dao;

import java.sql.*;
import java.util.*;

/**
 * General data access object
 * @author salojuur
 */
public interface Dao<T, K> {
    void create(T object) throws SQLException;
    T update(T object) throws SQLException;
    void delete(T object) throws SQLException;
    List<T> list() throws SQLException;
}
