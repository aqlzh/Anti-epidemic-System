package com.example.epi.controller;

import com.example.epi.model.EpidemicInfo;
import com.example.epi.service.impl.AdminServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceImpl adminServiceImpl;
    //对adminServiceImpl初始化。
    public AdminController(AdminServiceImpl adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }
    //查询所有疫情信息
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public String queryAll(Model model){
        List<Object> epidemicInfos = new ArrayList<>();
        Map<Integer, EpidemicInfo> epidemicInfoMap = adminServiceImpl.findAllEpidemicInfo();
        for (Object epidemicInfo: epidemicInfoMap.values()) {
            epidemicInfos.add(epidemicInfo);
        }
        model.addAttribute("epidemicInfos",epidemicInfos);
        System.out.println("所有疫情信息打印成功！");
        return "ShowAllEpidemicInfo";
    }
    //添加疫情信息
    @RequestMapping(value = "/addEpidemicInfo",method = RequestMethod.POST)
    public String addEpidemicInfo(@RequestBody String json) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(json);
        EpidemicInfo epidemicInfo = (EpidemicInfo) JSONObject.toBean(jsonObject, EpidemicInfo.class);
        Date date = new Date();
        epidemicInfo.setRegtime(date);
        try {
            adminServiceImpl.addEpidemicInfo(epidemicInfo);
        }catch (Exception e) {
            System.out.println("输入信息与数据库信息冲突");
            return "Demo";
        }
        System.out.println(epidemicInfo);

        return "SearchResult";
    }
    //删除疫情信息
    @RequestMapping("/deleteEpidemicInfo")
    public String deleteEpidemicInfo(HttpServletRequest request){
        String name = request.getParameter("name");
        adminServiceImpl.deleteEpidemicInfoByName(name);
        System.out.println(name+"的信息被删！");
        return "redirect:/admin/queryAll";
    }
    //根据姓名查询疫情信息
    @RequestMapping(value = "/findByName",method = RequestMethod.POST)
    public String findByName(Model model,HttpServletRequest request){
        String name = request.getParameter("name");
        if(name==null){
            return "SearchResult";
        }
        List<Object> epidemicInfos = new ArrayList<>();
        Map<Integer, EpidemicInfo>epidemicInfo = adminServiceImpl.findByName(name);
        for (EpidemicInfo value : epidemicInfo.values()) {
            epidemicInfos.add(value);
        }
        model.addAttribute("members",epidemicInfos);
        System.out.println(name+"的信息成功查询，并展示给管理员！");
        return "QueryPersonEpidemicInfo";
    }
    //根据身份证号码查询疫情信息
    @RequestMapping(value = "/findByIdCard")
    public String findByIdCard(){
        return "SearchResult";
    }

}
