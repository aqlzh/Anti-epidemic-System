package com.example.epi.service;

import com.example.epi.model.EpidemicInfo;

import java.util.Map;


public interface AdminService {
    /*@Autowired
    EpidemicInfoMapper epidemicInfoMapper = null;*/
    //查询所有疫情信息
    public Map<Integer,EpidemicInfo> findAllEpidemicInfo();
    //通过姓名查询疫情信息
    public Map<Integer,EpidemicInfo> findByName(String name);
    //通过学号查询疫情信息
    public EpidemicInfo findByIdCard(long id_card);
    //删除疫情信息
    public void deleteEpidemicInfoByName(String name);
    //增加疫情信息
    public void addEpidemicInfo(EpidemicInfo epidemicInfo);


    //测试方法
    public void adminTest();


}
