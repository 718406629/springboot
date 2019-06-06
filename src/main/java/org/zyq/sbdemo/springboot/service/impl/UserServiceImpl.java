package org.zyq.sbdemo.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyq.sbdemo.springboot.mapper.UserMapper;
import org.zyq.sbdemo.springboot.model.User;
import org.zyq.sbdemo.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
   private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
