package cn.yz;

import cn.yz.domain.User;
import cn.yz.service.GoodService;
import cn.yz.service.OrderService;
import cn.yz.service.UserService;
import cn.yz.service.imp.GoodServiceImpl;
import cn.yz.service.imp.OrderServiceImpl;
import cn.yz.service.imp.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号");
        String username = sc.next();
        System.out.println("请输入密码");
        String password = sc.next();
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        if (user != null){
            System.out.println("登陆成功");
            String level = user.getLevel();
            while (true){
                switch (level){
                    case "1" -> manager();
                    case "2" -> cashier();

                }
            }

        }
    }

    private static void userManager() throws SQLException{
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.新增用户");
            System.out.println("2.按用户名查询用户");

            String choice = sc.next();

            switch (choice){
                case "1" -> USave();
                case "2" -> UFindByUsername();
                default -> {
                    return;
                }
            }
        }
    }
    private static void UFindByUsername() throws SQLException{
        UserService userService = new UserServiceImpl();
        userService.findByName();
    }
    private static void USave() throws SQLException{
        UserService userService = new UserServiceImpl();
        userService.save();
    }
    private static void goodManager() throws SQLException{
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1.新增商品");
            System.out.println("2.查询所有商品");
            System.out.println("3.按编号查询商品");
            System.out.println("4.商品入库");
            System.out.println("5.商品出库");

            String choice = sc.next();

            switch (choice){
                case "1" -> MInsert();
                case "2" -> MList();
                case "3" -> MFindById();
                case "4" -> MSave();
                case "5" -> MIncrease();
                default -> {
                    return;
                }
            }
        }
    }
    private static void manager() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.用户管理");
        System.out.println("2.商品管理");
        String choice = sc.next();
        switch (choice){
            case "1" -> userManager();
            case "2" -> goodManager();

        }


    }
    private static void cashier(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.扫描商品");
            System.out.println("2.修改购买数量");
            System.out.println("3.结算");

            String choice = sc.next();

            switch (choice){
                case "1" -> SSave();
                case "2" -> SUpdate();
                case "3" -> SPay();
            }
        }
    }
    private static void SSave(){
        OrderService orderService = new OrderServiceImpl();
        orderService.save();
    }
    private static void SPay(){
        OrderService orderService = new OrderServiceImpl();
        orderService.pay();
    }
    private static void SUpdate(){
        OrderService orderService = new OrderServiceImpl();
        orderService.update();
    }
    private static void MInsert() throws SQLException {
        GoodService goodService = new GoodServiceImpl();
        goodService.insert();
    }
    private static void MList() throws SQLException{
        GoodService goodService = new GoodServiceImpl();
        goodService.list();
    }
    private static void MFindById() throws SQLException{
        GoodService goodService = new GoodServiceImpl();
        goodService.findById();
    }
    private static void MSave() throws SQLException{
        GoodService goodService = new GoodServiceImpl();
        goodService.save();
    }
    private static void MIncrease() throws  SQLException{
        GoodService goodService = new GoodServiceImpl();
        goodService.increase();
    }
}