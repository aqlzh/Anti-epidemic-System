package com.example.epi.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    AdminServiceImpl adminServiceImpl;
    @Test
    void adminTest() {
        adminServiceImpl.adminTest();
        //Test.class.getResource("/").toString();
        //System.out.println(Test.class.getResource("/"));
    }
    /*@Test
    void findEpidemicInfoByNameTest(String name){
        adminServiceImpl.findByName(name);

    }*/
}
