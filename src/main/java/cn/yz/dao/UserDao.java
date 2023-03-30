package cn.yz.dao;

import cn.yz.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User login(String username, String password) throws SQLException;

    int save(User user) throws SQLException;

    int deleteByUserName(String username) throws SQLException;

    int update(User user) throws SQLException;

    List<User> list() throws SQLException;

    User findByUserName(String username) throws SQLException;
}
