package com.example.epi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@Component
@WebFilter("/*")*/
public class LoginAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter init ......");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("我执行过滤了...");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String url = req.getRequestURI();
        if(url.contains("login.html")){
            chain.doFilter(request,response);
            return;
        }
        if(url.contains("/static/js")||(url.contains("/css"))||(url.contains("image"))){
            chain.doFilter(request,response);
            return;
        }
        if(url.contains("/login")){
            chain.doFilter(request,response);
            return;
        }

        String username = (String)req.getSession().getAttribute("user");
        if(username != null){
            chain.doFilter(request,response);
            return;
        }

        resp.sendRedirect("login.html");
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy.....");
    }
}
