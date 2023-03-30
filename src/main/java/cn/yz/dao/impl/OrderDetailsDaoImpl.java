package cn.yz.dao.impl;

import cn.yz.dao.OrderDetailsDao;
import cn.yz.domain.OrderDetails;
import cn.yz.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDaoImpl extends BaseDao implements OrderDetailsDao {
    @Override
    public int save(OrderDetails orderDetails) throws SQLException {
        String sql = "INSERT INTO order_details values(?,?,?,?,?,?,?)";
        Object[] params = {
                orderDetails.getId(),
                orderDetails.getGoodsId(),
                orderDetails.getUnit(),
                orderDetails.getPrice(),
                orderDetails.getCount(),
                orderDetails.getTotal(),
                orderDetails.getGoodsName(),
        };
        return this.update(sql,params);
    }

    @Override
    public List<OrderDetails> list(int orderId) throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM order_details where id = ?");
        ps.setInt(1,orderId);
        ResultSet rs = ps.executeQuery();
        List<OrderDetails> orderDetails = new ArrayList<>();
        while (rs.next()){
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setId(rs.getInt("id"));
            orderDetail.setPrice(rs.getDouble("goods_price"));
            orderDetail.setUnit(rs.getString("goods_unit"));
            orderDetail.setGoodsId(rs.getInt("goods_id"));
            orderDetail.setTotal(rs.getDouble("total"));
            orderDetail.setCount(rs.getInt("goods_num"));
            orderDetails.add(orderDetail);
        }
        jdbcUtils.release(rs,ps,connection);
        return orderDetails;
    }

    @Override
    public int update(OrderDetails orderDetails) throws SQLException {
        String sql = "UPDATE order_details SET goods_num = ?,total = ? WHERE id=? AND goods_id=?";
        Object[] params = {
                orderDetails.getCount(),
                orderDetails.getTotal(),
                orderDetails.getId(),
                orderDetails.getGoodsId()
        };
        return this.update(sql,params);
    }
}
