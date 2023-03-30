package cn.yz.dao;

import cn.yz.domain.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDao {

    int save(OrderDetails orderDetails) throws SQLException;

    List<OrderDetails> list(int orderId) throws SQLException;

    int update(OrderDetails orderDetails) throws SQLException;
}
