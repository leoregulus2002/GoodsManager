package cn.yz.dao.impl;

import cn.yz.dao.OrderDao;
import cn.yz.domain.Order;
import cn.yz.utils.JDBCUtils;

import java.sql.*;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int save(Order order) throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO `order` values(?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,order.getId());
        ps.setDouble(2,order.getTotal());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }

    @Override
    public Order queryById(int id) throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM `order` WHERE id=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Order order = null;
        if (rs.next()){
            order = new Order();
            order.setId(rs.getInt("id"));
            order.setTotal(rs.getDouble("total"));

        }
        jdbcUtils.release(rs,ps,connection);
        return order;
    }

    @Override
    public int update(int id,double price) throws SQLException {
        String sql = "UPDATE `order` SET total = ? WHERE id=?";
        Object[] params = {
                price,
                id
        };
        return this.update(sql,params);
    }


}
