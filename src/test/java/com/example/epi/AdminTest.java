package com.example.epi;

import com.example.epi.model.EpidemicInfo;
import com.example.epi.service.impl.AdminServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {
    @Autowired
    AdminServiceImpl adminServiceImpl;



    public void test(){
        adminServiceImpl.adminTest();
    }
    @Test
    public void main() {
        /*SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        EpidemicInfo epidemicInfo = new EpidemicInfo();
        epidemicInfo.setName("小明");
        epidemicInfo.setAdress("江西省");
        epidemicInfo.setAge(18);
        epidemicInfo.setId_card(1909105023);
        epidemicInfo.setId_type("中华人民共和国身份证");
        epidemicInfo.setInNative("是");
        epidemicInfo.setSex(1);
        epidemicInfo.setPhone(18470778808L);
        epidemicInfo.setRegtime(date);
        //System.out.println(epidemicInfo);
        adminServiceImpl.addEpidemicInfo(epidemicInfo);*/
        /*String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();*/
        //System.out.println(springBootVersion);
        /*Map<Integer, EpidemicInfo> allEpidemicInfo = adminServiceImpl.findAllEpidemicInfo();
        for (Object epidemicInfo: allEpidemicInfo.values()) {
            System.out.println(epidemicInfo);
        }*/
    }
}
