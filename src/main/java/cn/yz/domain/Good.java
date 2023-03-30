package cn.yz.domain;

public class Good {
    private int id;
    private String name;
    private Double price;
    private String unit;
    private int inventory;

    public Good() {
    }

    public Good(int id, String name, Double price, String unit, int inventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.inventory = inventory;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
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
     * @return inventory
     */
    public int getInventory() {
        return inventory;
    }

    /**
     * 设置
     * @param inventory
     */
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String toString() {
        return "Goods{id = " + id + ", name = " + name + ", price = " + price + ", unit = " + unit + ", inventory = " + inventory + "}";
    }
}
