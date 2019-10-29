package com.xcl.service.Impl;

import com.xcl.entity.Address;
import com.xcl.entity.User;
import com.xcl.repository.UserRepository;
import com.xcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User login(String loginName, String passWord) {
        return userRepository.login(loginName, passWord);
    }

    @Override
    public void register(User user) {
         userRepository.register(user);
    }

    @Override
    public List<Address> findAllAddressByUserId(Integer id) {
        return userRepository.findAllAddressByUserId(id);
    }

    @Override
    public void save(Integer userid, String address, String remark) {
        userRepository.save(userid,address,new Date(),remark);
    }


}
