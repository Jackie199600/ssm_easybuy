package com.xcl.repository;

import com.xcl.entity.Address;
import com.xcl.entity.User;

import java.util.Date;
import java.util.List;


public interface UserRepository {
    public User login(String loginname,String passWord);
    public void register(User user);
    public List<Address> findAllAddressByUserId(Integer id);
    public void save(Integer userid, String address, Date createtime, String remark);

}
