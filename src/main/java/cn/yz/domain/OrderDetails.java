package cn.yz.domain;

public class OrderDetails {
    private int id;
    private int goodsId;
    private double price;
    private String goodsName;
    private String unit;
    private int count;
    private double total;

    public OrderDetails() {
    }

    public OrderDetails(int id, int goodsId, double price, String goodsName, String unit, int count, double total) {
        this.id = id;
        this.goodsId = goodsId;
        this.price = price;
        this.goodsName = goodsName;
        this.unit = unit;
        this.count = count;
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
     * @return goodsId
     */
    public int getGoodsId() {
        return goodsId;
    }

    /**
     * 设置
     * @param goodsId
     */
    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 获取
     * @return goodsName
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 获取
     * @return total
     */
    public double getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    public String toString() {
        return "OrderDetails{id = " + id + ", goodsId = " + goodsId + ", price = " + price + ", goodsName = " + goodsName + ", unit = " + unit + ", count = " + count + ", total = " + total + "}";
    }
}
