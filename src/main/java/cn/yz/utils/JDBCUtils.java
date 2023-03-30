package cn.yz.utils;

import java.sql.*;

public class JDBCUtils {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai", "root", "13462a!!!");

        return connection;
    }
    public void release(PreparedStatement ps, Connection connection) {
        release(null,ps,connection);
    }
    public void release(ResultSet rs, PreparedStatement ps, Connection connection){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                if (ps != null){
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }finally {
                        if (connection != null){
                            try {
                                connection.close();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }
}
