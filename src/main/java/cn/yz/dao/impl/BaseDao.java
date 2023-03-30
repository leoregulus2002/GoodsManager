package cn.yz.dao.impl;

import cn.yz.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDao {
    protected int update(String sql,Object... params) throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i+1,params[i]);
        }
        System.out.println(ps);
        int num = ps.executeUpdate();
        jdbcUtils.release(ps,connection);
        return num;
    }
}
