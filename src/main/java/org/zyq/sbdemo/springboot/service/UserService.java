package org.zyq.sbdemo.springboot.service;

import org.zyq.sbdemo.springboot.model.User;

public interface UserService {

    void insert(User user);

  User findByToken(String token);
}
