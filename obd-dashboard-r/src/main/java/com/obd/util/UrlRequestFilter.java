package com.obd.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UrlRequestFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request; 
        HttpServletResponse resp = (HttpServletResponse) response; 
        String conString = ""; 
        conString = req.getHeader("REFERER");
        //获取父url如果不是直接输入的话就是先前的访问过来的页面，要是用户输入了，这个父url是不存在的  
        if("".equals(conString) || null==conString){
            //判断如果上一个目录为空的话，说明是用户直接输入url访问的  
            String servletPath = req.getServletPath();
            //当前请求url，去掉几个可以直接访问的页面  
            if(servletPath.contains("/") || servletPath.contains("user/login")){ 
            //跳过index.html和登陆Login.html  
                chain.doFilter(request, response); 
            } else { 
            	//跳回首页  
                resp.sendRedirect("/user/login?userid=100&password=11111");
            } 
        } else { 
            chain.doFilter(request, response); 
        } 
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}










