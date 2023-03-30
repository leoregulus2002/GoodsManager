package cn.yz.service.imp;

import cn.yz.dao.GoodDao;
import cn.yz.dao.OrderDao;
import cn.yz.dao.OrderDetailsDao;
import cn.yz.dao.impl.GoodDaoImpl;
import cn.yz.dao.impl.OrderDaoImpl;
import cn.yz.dao.impl.OrderDetailsDaoImpl;
import cn.yz.domain.Good;
import cn.yz.domain.Order;
import cn.yz.domain.OrderDetails;
import cn.yz.service.GoodService;
import cn.yz.service.OrderService;

import java.sql.SQLException;
import java.util.*;

public class OrderServiceImpl implements OrderService {

    private static int order_id;


    @Override
    public void save() {
        Scanner sc = new Scanner(System.in);
        GoodService goodService = new GoodServiceImpl();
        Map<Integer,Integer> map = new HashMap<>();
        double total = 0;

        while (true){
            System.out.println("请输入商品编号");
            int goods_id = sc.nextInt();
            if (goods_id == -1 ){
                break;
            }
            try {
                Good good = goodService.findById(goods_id);
                if (good != null){
                    if (map.containsKey(goods_id)){
                        Integer count = map.get(goods_id);
                        map.put(goods_id,count+1);
                    }else {
                        map.put(goods_id,1);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        OrderDetailsDao orderDetailsDao = new OrderDetailsDaoImpl();

        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            try {
                total += i.getValue() * goodService.findById(i.getKey()).getPrice();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        OrderDao orderDao = new OrderDaoImpl();
        Order order = new Order();
        order.setTotal(total);

        try {
            this.order_id = orderDao.save(order);
            System.out.println(order_id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            try {
                Good good = goodService.findById(i.getKey());
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setId(order_id);
                orderDetails.setGoodsId(good.getId());
                orderDetails.setUnit(good.getUnit());
                orderDetails.setPrice(good.getPrice());
                orderDetails.setCount(i.getValue());
                orderDetails.setTotal(i.getValue() * good.getPrice());
                orderDetails.setGoodsName(good.getName());
                orderDetailsDao.save(orderDetails);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        List<OrderDetails> list;
        try {
            System.out.println(orderDao.queryById(order_id));
            list = orderDetailsDao.list(order_id);
            System.out.println("商品编号\t单位\t单价\t数量\t金额");
            for (OrderDetails orderDetails : list) {
                System.out.println(orderDetails.getGoodsId()+"\t"
                                +orderDetails.getUnit()+"\t"
                        +orderDetails.getPrice()+"\t"
                        +orderDetails.getCount()+"\t"
                        +orderDetails.getTotal()
                        );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        OrderDetailsDao orderDetailsDao = new OrderDetailsDaoImpl();
        System.out.println("请输入商品编号");
        int goodId = sc.nextInt();
        GoodDao goodDao = new GoodDaoImpl();
        Good good;
        try {
            good = goodDao.findById(goodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (good != null) {
            System.out.println("请输入修改数量");
            int num = sc.nextInt();
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setId(this.order_id);
            orderDetails.setGoodsId(goodId);
            orderDetails.setCount(num);
            orderDetails.setTotal(num*good.getPrice());
            System.out.println(this.order_id);
            try {
                orderDetailsDao.update(orderDetails);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("无此商品");
        }
        List<OrderDetails> list;
        try {
            list = orderDetailsDao.list(order_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        double maxTotal =0;

        System.out.println("商品编号\t单位\t单价\t数量\t金额");
        for (OrderDetails orderDetails : list) {
            System.out.println(orderDetails.getGoodsId()+"\t"
                    +orderDetails.getUnit()+"\t"
                    +orderDetails.getPrice()+"\t"
                    +orderDetails.getCount()+"\t"
                    +orderDetails.getTotal()
            );
            maxTotal += orderDetails.getTotal();
        }
        OrderDao orderDao = new OrderDaoImpl();
        try {
            orderDao.update(order_id,maxTotal);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void pay() {
        OrderDao orderDao = new OrderDaoImpl();
        Scanner sc = new Scanner(System.in);
        double total;
        try {
            total = orderDao.queryById(order_id).getTotal();
            System.out.println("总金额为"+ total);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("是否是会员");
        String isVip = sc.next();
        if ("yes".equals(isVip)){
            if ("4".equals(UserServiceImpl.level)){
                //TODO Vip
            }

        }else {
            System.out.println("输入支付金额");
            double money = sc.nextDouble();
            double more = money-total;
            if (more>=0){
                System.out.println("找零"+more);
                OrderDetailsDao orderDetailsDao = new OrderDetailsDaoImpl();
                List<OrderDetails> list;
                try {
                     list = orderDetailsDao.list(order_id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("收银员号\t小票号\t商品编号\t商品单位\t单价\t数量\t金额\t应收\t实收\t找零\t系统时间");
                for (OrderDetails details : list) {
                    System.out.println(details.getGoodsId()+"\t"
                            +UserServiceImpl.username+"\t"
                            +order_id+"\t"
                            +details.getUnit()+"\t"
                            +details.getPrice()+"\t"
                            +details.getCount()+"\t"
                            +details.getTotal()+"\t"
                            +total+"\t"
                            +money+"\t"
                            +more
                    );
                }


            }


        }

    }
}
