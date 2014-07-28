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


public class TokenFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String servletPath = req.getServletPath();
        if(servletPath.contains("consultant/login") || servletPath.contains("user/login")){
        	TokenUtil.createCookie(resp);
        	chain.doFilter(req, resp); 
            }else{
            	 Boolean isValid = TokenUtil.isValidToken(req);
            	 if(isValid){
            		 TokenUtil.saveTokenString(req,resp);
                	 chain.doFilter(request, response);
                 }else{
                	 return;
                 }
            } 
    }
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}










