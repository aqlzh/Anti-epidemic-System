package com.example.epi.controller;

import com.example.epi.model.EpidemicInfo;
import com.example.epi.service.impl.AdminServiceImpl;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.springframework.beans.BeanUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceImpl adminServiceImpl;

    public EpidemicInfo jsonToEpidemicInfo(String json) {
        EpidemicInfo epidemicInfo = new EpidemicInfo();
        JSONObject jsonObject = JSONObject.fromObject(json);

        return epidemicInfo;
    }

    public AdminController(AdminServiceImpl adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }

    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public String queryAll(Model model){
        List<Object> epidemicInfos = new ArrayList<>();
        Map<Integer, EpidemicInfo> epidemicInfoMap = adminServiceImpl.findAllEpidemicInfo();
        for (Object epidemicInfo: epidemicInfoMap.values()) {
            epidemicInfos.add(epidemicInfo);
        }
        model.addAttribute("epidemicInfos",epidemicInfos);
        return "show_all_info";
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/addEpidemicInfo",method = RequestMethod.POST)
    public String addEpidemicInfo(@RequestBody String json,Model model){
        //JSONObject str = {"name":"张三","age":"21","gender":"男","identy":"student","temp":"36","inNative":"on","adress":"江西省赣州市于都县","regtime":"2021-3-4  23:45:26","id_type":"中华人民共和国身份证","id_card":"360731200011215658","phone":"18470778808"};
        JSONObject jsonObject = JSONObject.fromObject(json);
        String name = jsonObject.getString("name");
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd HH:mm:ss"}) );
        EpidemicInfo epidemicInfo = (EpidemicInfo) JSONObject.toBean(jsonObject, EpidemicInfo.class);
        adminServiceImpl.addEpidemicInfo(epidemicInfo);
        //model.addAttribute(epidemicInfo);
        System.out.println(epidemicInfo);
        return "Demo";
    }
    @RequestMapping("/test")
    public String test(){
        return "Demo";
    }

    /*@RequestMapping(value = {"/t", "/temp", "/template"}, method = RequestMethod.POST)
    public String showIndexHtml(Model model){
        return "Demo";
    }*/

    @RequestMapping("/deleteEpidemicInfo")
    public String deleteEpidemicInfo(@RequestBody String name){
        System.out.println(name);
        adminServiceImpl.deleteEpidemicInfoByName(name);
        return "Demo";
    }
    /*@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String messageNotReadable(HttpMessageNotReadableException exception, HttpServletResponse response){
        ErrorManager log = null;
        log.error("请求参数不匹配。", exception,200);
        return "";
    }*/


    @RequestMapping(value = "/findByName",method = RequestMethod.POST)
    //@ResponseBody
    public String findByName(Model model,@Param("name") String name){
        String sname="小明";
        EpidemicInfo epidemicInfo = adminServiceImpl.findByName(name);
        model.addAttribute("member",epidemicInfo);
        return "show_info";
    }

    @RequestMapping(value = "/findByIdCard")
    public String findByIdCard(){

        return "Demo";
    }

    @RequestMapping("/findAllEpidemicInfo")
    public String findAllEpidemicInfo(){

        return "Demo";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
