package com.example.epi.mapper;

import com.example.epi.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUserInfo(@Param(value = "username") String username, @Param(value = "password") String password);
}
