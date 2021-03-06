package com.example.epi.servelt;

import com.example.epi.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/LoginServelt")
public class LoginServelt extends HttpServlet {

    @Autowired
    AdminServiceImpl adminServiceImpl;
    /*@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        System.out.println(req.getParameter("uname")+"   1");
        String uname = req.getParameter("uname");
        if("admin".equals(uname)){
            req.getSession().setAttribute("user",uname);
            resp.sendRedirect("index.html");
        }else {
            resp.sendRedirect("login.html");
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("uname")+"    3");
        String uname = req.getParameter("uname");
        if("admin".equals(uname)){
            req.getSession().setAttribute("user",uname);
            resp.sendRedirect("index.html");
        }else {
            resp.sendRedirect("login.html");
        }
        //super.doPost(req, resp);
    }

}
