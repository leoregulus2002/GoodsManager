package cn.yz.service.imp;

import cn.yz.dao.GoodDao;
import cn.yz.dao.impl.GoodDaoImpl;
import cn.yz.domain.Good;
import cn.yz.service.GoodService;
import cn.yz.utils.StringDateFormat;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GoodServiceImpl implements GoodService {
    @Override
    public void save() throws SQLException {
        Scanner sc = new Scanner(System.in);
        GoodService goodService = new GoodServiceImpl();
        System.out.println("请输入商品编号");
        int id = sc.nextInt();
        Good good = goodService.findById(id);
        if (good==null){
            System.out.println("无此商品");
            return;
        }

        System.out.println("请输入商品数量");
        int inventory = sc.nextInt();

        good.setInventory(inventory);
        good.setId(id);
        GoodDao employeeDao = new GoodDaoImpl();
        try {
            employeeDao.save(good);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("入库成功");
    }

    @Override
    public void insert(){
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入商品名称");
        String name = sc.next();
        System.out.println("请输入商品价格");
        Double price = sc.nextDouble();
        System.out.println("请输入单位");
        String unit = sc.next();
        System.out.println("请输入商品数量");
        int inventory = sc.nextInt();

        Good good = new Good();
        good.setName(name);
        good.setPrice(price);
        good.setUnit(unit);
        good.setInventory(inventory);

        GoodDao goodDao = new GoodDaoImpl();
        try {
            goodDao.insert(good);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void increase() throws SQLException {
        Scanner sc = new Scanner(System.in);
        GoodService goodService = new GoodServiceImpl();
        System.out.println("请输入商品编号");
        int id = sc.nextInt();
        Good good = goodService.findById(id);
        if (good==null){
            System.out.println("无此商品");
            return;
        }

        System.out.println("请输入减少商品数量");
        int inventory = sc.nextInt();
        if (good.getInventory() > inventory){
            good.setInventory(inventory);
            good.setId(id);
            GoodDao employeeDao = new GoodDaoImpl();
            try {
                employeeDao.increase(good);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("出库成功");
        }else {
            System.out.println("出库失败");
            goodService.findById(id);

        }


    }

    @Override
    public void list() throws SQLException {
        GoodDao goodDao = new GoodDaoImpl();
        List<Good> goods = null;
        try {
            goods = goodDao.list();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Id\tName\tPrice\tunit\tinventory");
        for (Good good : goods) {
            System.out.println(good.getId()+"\t"+
                    good.getName()+"\t\t"+
                    good.getPrice()+"\t\t"+
                    good.getUnit()+"\t"+
                    good.getInventory());
        }

    }

    @Override
    public Good findById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入商品编号");
        int id = scanner.nextInt();
        GoodDao goodDao = new GoodDaoImpl();
        Good good = null;
        try {
            good = goodDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (good!=null){
            System.out.println("Id\tName\tPrice\tunit\tinventory");
            System.out.println(good.getId()+"\t"+
                    good.getName()+"\t\t"+
                    good.getPrice()+"\t\t"+
                    good.getUnit()+"\t"+
                    good.getInventory());
        }else {
            System.out.println("无此商品");
            list();
        }
        return good;
    }
    @Override
    public Good findById(int id) throws SQLException {

        GoodDao goodDao = new GoodDaoImpl();
        Good good = null;
        try {
            good = goodDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return good;
    }
}
