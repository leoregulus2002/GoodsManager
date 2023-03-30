package cn.yz.dao.impl;

import cn.yz.dao.GoodDao;
import cn.yz.domain.Good;
import cn.yz.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoImpl extends BaseDao implements GoodDao {

    @Override
    public int save(Good good) throws SQLException {
        String sql = "UPDATE good SET inventory = inventory + ? WHERE id=?";
        Object[] params = {
                good.getInventory(),
                good.getId()

        };
        return this.update(sql,params);
    }

    @Override
    public int insert(Good good) throws SQLException {
        String sql = "INSERT INTO good values(?,?,?,?,?)";
        Object[] params = {
                good.getId(),
                good.getName(),
                good.getPrice(),
                good.getUnit(),
                good.getInventory(),
        };
        return this.update(sql,params);
    }

    @Override
    public int increase(Good good) throws SQLException {
        String sql = "UPDATE good SET inventory = inventory - ? WHERE id=?";
        Object[] params = {
                good.getInventory(),
                good.getId()

        };
        return this.update(sql,params);
    }

    @Override
    public List<Good> list() throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM good");
        ResultSet rs = ps.executeQuery();
        List<Good> employees = new ArrayList<>();
        while (rs.next()){
            Good good = new Good();
            good.setId(rs.getInt("id"));
            good.setPrice(rs.getDouble("price"));
            good.setUnit(rs.getString("unit"));
            good.setName(rs.getString("name"));
            good.setInventory(rs.getInt("inventory"));
            employees.add(good);
        }
        jdbcUtils.release(rs,ps,connection);
        return employees;
    }

    @Override
    public Good findById(int id) throws SQLException {
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection connection = jdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM good WHERE id=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Good good = null;
        if (rs.next()){
            good = new Good();
            good.setId(rs.getInt("id"));
            good.setPrice(rs.getDouble("price"));
            good.setUnit(rs.getString("unit"));
            good.setName(rs.getString("name"));
            good.setInventory(rs.getInt("inventory"));
        }
        jdbcUtils.release(rs,ps,connection);
        return good;
    }
}
