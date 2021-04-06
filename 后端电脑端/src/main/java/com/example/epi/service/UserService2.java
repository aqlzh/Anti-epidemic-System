package com.example.epi.service;


import com.example.epi.model.Users;

import java.util.List;


public interface UserService2 {

    void adduser(Users user);

    List<Users> findUserAll();

    Users findUserById(Integer id);

    void modifyUser(Users users);

    void dropUser(Integer id);

    //分页显示学生信息
    List<Users> querySomeStudent(List<Users> studentList, int pageNum, int offset);
}
