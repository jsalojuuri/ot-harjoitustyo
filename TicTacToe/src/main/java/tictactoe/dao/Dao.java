package tictactoe.dao;

import java.sql.*;
import java.util.*;

/**
 * General data access object
 * @author salojuur
 */
public interface Dao<T, K> {
    void create(T object) throws Exception;
    T update(T object) throws Exception;
    void delete(T object) throws Exception;
    List<T> list() throws Exception;
}
