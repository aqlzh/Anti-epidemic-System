package com.example.epi.service.impl;

import com.example.epi.mapper.EpidemicInfoMapper;
import com.example.epi.model.EpidemicInfo;
import com.example.epi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    EpidemicInfoMapper epidemicInfoMapper;
    //查询所有疫情信息
    public Map<Integer, EpidemicInfo> findAllEpidemicInfo(){
        return epidemicInfoMapper.findAllEpidemicInfo();
    };
    //通过姓名查询疫情信息
    public Map<Integer, EpidemicInfo> findByName(String name){
        return epidemicInfoMapper.findByName(name);
    };
    //通过学号查询疫情信息
    public EpidemicInfo findByIdCard(long id_card){
        return epidemicInfoMapper.findByIdCard(id_card);
    }

    @Override
    public void deleteEpidemicInfoByName(String name) {
        epidemicInfoMapper.deleteEpidemicInfoByName(name);
    }

    @Override
    public void addEpidemicInfo(EpidemicInfo epidemicInfo) {
        epidemicInfoMapper.addEpidemicInfo(epidemicInfo);
    }

    @Override
    public void adminTest() {
        System.out.println("this is a test method");
    }

    ;
    //删除疫情信息
    public void deleteEpidemicInfo(String name){
        epidemicInfoMapper.deleteEpidemicInfoByName(name);
    };


}
