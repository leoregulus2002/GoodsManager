package cn.yz.service;

import cn.yz.domain.Good;

import java.sql.SQLException;

public interface GoodService {

    void save() throws SQLException;

    void insert() throws SQLException;

    void increase() throws SQLException;

    void list() throws SQLException;

    Good findById() throws SQLException;
    Good findById(int id) throws SQLException;
}
