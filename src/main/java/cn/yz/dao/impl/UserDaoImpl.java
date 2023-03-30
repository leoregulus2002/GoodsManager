package cn.yz.dao.impl;

import cn.yz.dao.UserDao;
import cn.yz.domain.User;
import cn.yz.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User login(String username, String password) throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
        ps.setString(1,username);
        ps.setString(2,password);

        ResultSet rs = ps.executeQuery();
        User user = null;
        if (rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(username);
            user.setLevel(rs.getString("level"));
        }
        jdbcUtils.release(rs,ps,connection);
        return user;
    }

    @Override
    public int save(User user) throws SQLException {
        String sql = "INSERT INTO user values(?,?,?,?)";
        Object[] params = {
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getLevel(),

        };
        return this.update(sql,params);
    }

    @Override
    public int deleteByUserName(String username) throws SQLException {
        return this.update("DELETE FROM emp WHERE username=?", username);
    }

    @Override
    public int update(User user) throws SQLException {
        String sql = "UPDATE emp SET password=?,level=? WHERE username=?";
        Object[] params = {
                user.getPassword(),
                user.getLevel(),
                user.getUsername()
        };
        return this.update(sql,params);
    }

    @Override
    public List<User> list() throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM emp");
        ResultSet rs = ps.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setLevel(rs.getString("level"));
            users.add(user);
        }
        jdbcUtils.release(rs,ps,connection);
        return users;
    }

    @Override
    public User findByUserName(String username) throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE username=?");
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        User user = null;
        if (rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setLevel(rs.getString("level"));
        }
        jdbcUtils.release(rs,ps,connection);
        return user;
    }

}
