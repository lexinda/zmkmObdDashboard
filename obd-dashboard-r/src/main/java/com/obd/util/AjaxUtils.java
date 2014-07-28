package com.obd.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AjaxUtils {
	
	private static Gson gson = new Gson();
	
	public static void printJson(Object dataMap,HttpServletResponse response) {
    	String result = gson.toJson(dataMap);
    	response.setContentType("application/Json");
        response.setCharacterEncoding("UTF-8");  
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out;
        try { 
            out = response.getWriter();  
            out.print(result);
            // 用于返回对象参数 
       } catch (IOException e) {  
            e.printStackTrace();  
       }
    }
	
	public static void printJson(int code, String message,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code); 
		map.put("message", message);
		String result = gson.toJson(map);
		response.setContentType("application/Json");
        response.setCharacterEncoding("UTF-8");  
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out;
        try { 
            out = response.getWriter();  
            out.print(result);
            // 用于返回对象参数 
       } catch (IOException e) {  
            e.printStackTrace();  
       }
	}
	
	public static void printErrorJson(String message,  HttpServletRequest request,HttpServletResponse response){
		printJson(-1, message, response);//-1表通用的error
		
	}
}
