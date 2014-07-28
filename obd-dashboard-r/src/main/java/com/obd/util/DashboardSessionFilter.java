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

import org.apache.log4j.Logger;

import com.obd.service.AdminServiceImpl;


public class DashboardSessionFilter implements Filter {
	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(DashboardSessionFilter.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		logger.debug("Begin:...");
		
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String servletPath = req.getServletPath();
        if(servletPath.contains("login.jsp") ||servletPath.contains("admin/login")||servletPath.contains(".jpg")  || servletPath.contains(".bmp") 
                || servletPath.contains("images") || servletPath.contains("css") || servletPath.contains("js")|| servletPath.contains("getNearAward") 
               ){
        	chain.doFilter(req, resp); 
        	logger.debug("end:...");
            }else{ 
            	if(req.getSession().getAttribute("admin")==null){
            		req.getSession().invalidate();
            		java.io.PrintWriter out = response.getWriter();   
            		   out.println("<script>");   
            	       out.println("alert('超时，请重新登录！');");
            	       out.println("window.open('../login.jsp','_top');");
            	       out.println("</script>"); 
            	}else{
            		chain.doFilter(req, resp); 
            	}
            } 
    }
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}










