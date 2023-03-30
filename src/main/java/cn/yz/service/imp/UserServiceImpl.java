package cn.yz.service.imp;

import cn.yz.dao.UserDao;
import cn.yz.dao.impl.UserDaoImpl;
import cn.yz.domain.User;
import cn.yz.service.UserService;
import cn.yz.utils.MD5Util;
import cn.yz.utils.StringDateFormat;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceImpl implements UserService {

    public static String username;
    public static String level;
    @Override
    public User login(String empNO, String password){
        password = MD5Util.MD5_32(password,"utf-8");
        UserDao userDao = new UserDaoImpl();
        User user = null;
        try {
            user = userDao.login(empNO,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        username = user.getUsername();
        level = user.getLevel();
        return user;
    }

    @Override
    public void save() {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入用户名");
        String username = sc.next();
        System.out.println("请输入密码");
        String password = sc.next();
        System.out.println("请输入用户等级");
        String level = sc.next();

        User employee = new User();
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setLevel(level);

        UserDao userDao = new UserDaoImpl();
        try {
            userDao.save(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void list() {

    }

    @Override
    public void findByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        UserDao userDao = new UserDaoImpl();
        User user = null;
        try {
            user = userDao.findByUserName(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user != null){
            System.out.println("id\tusername\tlevel");
            System.out.println(
                    user.getId()+"\t\t"+
                    user.getUsername()+"\t\t"+
                    user.getLevel()+"\t"
                    );
        }else {
            System.out.println("用户名不存在");
        }
    }

    @Override
    public User findByName(String username) {
        UserDao userDao = new UserDaoImpl();
        User user = null;
        try {
            user = userDao.findByUserName(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void remove() {

    }

    @Override
    public void update() {

    }


}
