package com.example.epi.service.impl;

import com.example.epi.mapper.UserMapper;
import com.example.epi.model.User;
import com.example.epi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public User getUserInfo(String username, String password) {
        return userMapper.getUserInfo(username, password);
    }

}
