package com.example.epi.mapper;

import com.example.epi.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUserInfo(@Param(value = "userName") String userName, @Param(value = "password") String password);

    User getUser(@Param(value = "userId") int userId, @Param(value = "userName") String userName);
}
