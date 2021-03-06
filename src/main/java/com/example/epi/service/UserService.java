package com.example.epi.service;

import com.example.epi.model.User;

public interface UserService {
    User getUserInfo(String username, String password);
}
