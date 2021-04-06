package com.example.epi.controller;

import com.example.epi.model.Users;
import com.example.epi.service.UserService2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    /*
     * 添加用户
     * */
    @Autowired
    private UserService2 userService;


    @RequestMapping("/Dele/welcome")
    public String welcome(){
        return "welcome";

    }

    @PostMapping("/addUser")
    public String addUser(Users user) {
        try {
            System.out.println(user);
            this.userService.adduser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


        return "redirect:/Dele/welcome";
    }

    /*
     * 查询用户
     * */

    @GetMapping("/findUserAll")
    public String findUserAll(Model model) {
        List<Users> list = null;
        try {
            list = this.userService.findUserAll();
            model.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "showUser";
    }

    /*
     * 预更新用户的查询
     * */

    @GetMapping("/preUpdateUser")
    public String preUpdateUser(Integer id, org.springframework.ui.Model model) {
        try {
            Users user = this.userService.findUserById(id);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(Users users) {
        try {
            this.userService.modifyUser(users);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/success";
    }


    @GetMapping("/Dele/{pageNum}")
    public ModelAndView deleteUser(@PathVariable(value = "pageNum") int pageNum,
                                   ModelAndView mv, HttpSession session) {

        try {
            int offset = 5;     //每页显示的数量
            int total = userService.findUserAll().size(); //学生信息总数
            int totalPage = total / offset;
            if (total % offset != 0) {
                totalPage++;
            }
            if (totalPage == 0) {
                totalPage = 1;
            }
            mv.addObject("totalPage", totalPage);
            mv.addObject("pageNum", pageNum);
            java.util.List<Users> studentList = new ArrayList<Users>();
            studentList = userService.querySomeStudent(userService.findUserAll(),
                    pageNum, offset);
            mv.addObject("studentList", studentList);
            mv.setViewName("deleteStu");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return mv;

    }

    /*
     * 删除用户
     * */
    @GetMapping("/deleteUser")
    public String delete(Integer id) {
        userService.dropUser(id);
        return "redirect:/Dele/1";
    }

}
