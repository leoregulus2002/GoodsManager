package cn.yz.dao;

import cn.yz.domain.Good;

import java.sql.SQLException;
import java.util.List;

public interface GoodDao {

    int save(Good good) throws SQLException;

    int insert(Good good) throws SQLException;

    int increase(Good good) throws SQLException;

    List<Good> list() throws SQLException;

    Good findById(int id) throws SQLException;
}
