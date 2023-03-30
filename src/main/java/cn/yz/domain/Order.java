package cn.yz.domain;

public class Order {
    private int id;
    private double total;

    public Order() {
    }

    public Order(int id, double total) {
        this.id = id;
        this.total = total;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return price
     */
    public double getTotal() {
        return total;
    }

    
    public void setTotal(double total) {
        this.total = total;
    }

    public String toString() {
        return "Order{id = " + id + ", total = " + total + "}";
    }
}
