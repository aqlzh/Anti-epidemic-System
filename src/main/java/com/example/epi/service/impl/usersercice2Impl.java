package com.example.epi.service.impl;

import com.example.epi.mapper.UsersMapper2;
import com.example.epi.model.Users;
import com.example.epi.model.UsersExample;
import com.example.epi.service.UserService;
import com.example.epi.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 * 用户管理业务层
 * */
@Service
public class usersercice2Impl implements UserService2 {
    @Autowired
    private UsersMapper2 usersMapper;


    @Override
    @Transactional
    public void adduser(Users user) {
        this.usersMapper.insert(user);
    }

    @Override
    public List<Users> findUserAll() {
        UsersExample usersExample = new UsersExample();
        return usersMapper.selectByExample(usersExample);
    }

    @Override
    public Users findUserById(Integer id) {
        return this.usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public void modifyUser(Users users) {
        this.usersMapper.updateByPrimaryKey(users);
    }

    @Override
    public void dropUser(Integer id) {
        this.usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Users> querySomeStudent(List<Users> studentList, int pageNum, int offset) {
        List<Users> list = new ArrayList<Users>();
        int cnt = (pageNum - 1) * offset;
        int num = 0;
        for (Users li : studentList) {
            if (cnt != 0) {
                cnt--;
                continue;
            }
            list.add(li);
            num++;
            if (num == offset) {
                break;
            }
        }
        return list;
    }
}
