package com.xcl.service;

import com.xcl.entity.Address;
import com.xcl.entity.User;

import java.util.List;

public interface UserService {
    public User login(String loginName,String passWord);
    public void register(User user);
    public List<Address> findAllAddressByUserId(Integer id);
    public void save(Integer userid, String address,String remark);
}
