package cn.yz.domain;

public class User {
    private int id;
    private String username;
    private String password;

    private String level;


    public User() {
    }

    public User(int id, String username, String password, String level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", level = " + level + "}";
    }
}