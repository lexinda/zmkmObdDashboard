package com.obd.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenUtil { 
	    
	 public static Boolean createCookie(HttpServletResponse response) {
		 String nowTime = String.valueOf(System.currentTimeMillis());
		 String uuid = UUID.randomUUID().toString()+","+nowTime;
//		 String id = request.getParameter("id");
		 Cookie cookie1;
//		 Cookie cookie2;
//		 Cookie cookie3;
		 cookie1=new Cookie("uuid",uuid); 
//		 cookie2=new Cookie("time",nowTime);
//		 cookie3=new Cookie("id",id); 
		 cookie1.setMaxAge(60*60*12);
//		 cookie2.setMaxAge(60*60*12);
//		 cookie3.setMaxAge(60*60*12);
		 cookie1.setPath("/");
//		 cookie2.setPath("/");
//		 cookie3.setPath("/");
		 response.addCookie(cookie1); 
//		 response.addCookie(cookie2);
//		 response.addCookie(cookie3); 
		 return true;
	 }
        /**
         * 得到cookie中的time userId 和struuid
         * @param req
         * @return
         */
       public static Map<String, Object> getToken(HttpServletRequest req) { 
    	Map<String, Object> map = new HashMap<String, Object>();
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {  
    	   for(int i=0;i<cookies.length;i++){
    		   if("uuid"==cookies[i].getName()|| "uuid".equals(cookies[i].getName())){
    			   String uuid = cookies[i].getValue();
    			   String[] u = uuid.split(",");
    			   map.put("uuid", u[0]);
    			   map.put("time", u[1]);
    		   }
//    		   else if("id"==cookies[i].getName()|| "id".equals(cookies[i].getName())){
//    			   map.put("id", cookies[i].getValue());
//    		   }
    		   
    	   }
    	   return map;
       }else{ 
           return null;         
       }  
      
    }
    /**
     * 保存Token
     * @param tokenStr
     * @param session
     * @return 
     */
       public static Boolean saveTokenString(HttpServletRequest req,HttpServletResponse resp) {  
    	       
    	        String nowTime = String.valueOf(System.currentTimeMillis());
    	        String uuid = UUID.randomUUID().toString()+","+nowTime;
    	        Cookie[] cookies = req.getCookies();
    	        if (cookies != null) {  
    	     	   for(int i=0;i<cookies.length;i++){
    	     		   if("uuid"==cookies[i].getName()|| "uuid".equals(cookies[i].getName())){
    	     			  cookies[i].setValue(uuid);
    	     			  resp.addCookie(cookies[i]);   
    	     		   }
    	     	   }
    	     	  return true;
    	        }
    	        return false;
    	}  
   
    // 判断Token是否有效
    public static boolean isValidToken(HttpServletRequest req) {
    	
    	 Map<String,  Object> tokenMap = getToken(req);
    	 String strUUID = req.getParameter("struuid");
    	 
         if(tokenMap!= null){  
    	             Long time= Long.valueOf((String) tokenMap.get("time"));  
    	             Long normal = System.currentTimeMillis();  
    	             if(normal - time <=10000000*1000){
    	            //  if(tokenMap.get("id").equals(id) || userId==tokenMap.get(userId)){
    	           		 if(tokenMap.get("uuid").equals(strUUID)){
    	            		 return true;  
    	            	 }
    	           //  }
    	         }
        }
        return false; 
    }
//    public static void add(HttpServletRequest req,HttpServletResponse resp,String time,String uuid) {
//    	 //启动线程，让数据产生者单独运行
//    	  try {  
//    		  OutputStream os = resp.getOutputStream();
//    		  String str="ni";
//    		   byte[] bs = str.getBytes();
//    	    	for(int i = 0; i < str.length(); i++) {   
//    	    		os.write(bs[i]);
//    	    		os.flush();
//    	  } 
//    	   String str1 = InputStreamClass3(req.getInputStream());
//    	   byte[] bs1 = str1.getBytes();
//	    	for(int i = 0; i <= str.length(); i++) {   
//	    		os.write(bs1[i]);
//	    		os.flush();
//	  } 
//    	  }catch(IOException e) {    
//    	     e.printStackTrace();   
//    	     }    
//     	    // 也可以使用线程来进行并行处理
//    	
//    }
//    public static String InputStreamClass3(InputStream in){
//    String str="\"struuid\"：\"11111111111\"";
//    byte[] bs = new byte[1024];    
//    int len;   
//    try{   
//    while((len = in.read(bs)) != -1) {    
//    for(int i = 0; i < len; i++) {   
//             str+=bs[i];    
//            }   
//          }     
//        }catch(IOException e) {   
//         e.printStackTrace();    
//        }
//    return str;
//    }
   
}

