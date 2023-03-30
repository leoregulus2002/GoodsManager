package cn.yz.dao;

import cn.yz.domain.Order;

import java.sql.SQLException;

public interface OrderDao {
    int save(Order order) throws SQLException;

    Order queryById(int id) throws SQLException;

    int update(int id,double price) throws SQLException;


}
