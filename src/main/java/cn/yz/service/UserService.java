package cn.yz.service;

import cn.yz.domain.User;

public interface UserService {
     User login(String username, String password);
     public void save();
     public void list();
     public void findByName();
     public User findByName(String Name);
     public void remove();
     public void update();

}
