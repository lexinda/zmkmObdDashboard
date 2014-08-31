package com.obd.util;

import sun.misc.BASE64Decoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**   
 * @Description: 通用工具类 
 * @author Junwu Zheng(junwu.zheng@boco.jp)
 * @date 2013-7-18 下午3:05:38 
 * @version V1.0   
 */
public class CommonUtil {

	/**
     * 标准32位md5加密码的算法
     *
     * @param plainText
     * @return
     */
    public static String md5(String plainText) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 标准16位md5加密码的算法
     *
     * @param plainText
     * @return
     */
    public static String Md16(String plainText ) { 
    	 String result = null;
    	try { 
    	MessageDigest md = MessageDigest.getInstance("MD5"); 
    	md.update(plainText.getBytes()); 
    	byte b[] = md.digest(); 

    	int i; 

    	StringBuffer buf = new StringBuffer(""); 
    	for (int offset = 0; offset < b.length; offset++) { 
    	i = b[offset]; 
    	if(i<0) i+= 256; 
    	if(i<16) 
    	buf.append("0"); 
    	buf.append(Integer.toHexString(i)); 
    	} 
    	//16位的加密 
    	result = buf.toString().substring(8,24);

    	} catch (NoSuchAlgorithmException e) { 
    	
    	e.printStackTrace(); 
    	} 
    	 return result;
    	} 

    
     /**
      * base64 加密
      * @param b
      * @return
      */
    /*
     public static String getBASE64(byte[] b) {
      String s = null;
      if (b != null) {
       s = new sun.misc.BASE64Encoder().encode(b);
      }
      return s;
     }
     
     public static byte[] getFromBASE64(String s) {
      byte[] b = null;
      if (s != null) {
       BASE64Decoder decoder = new BASE64Decoder();
       try {
        b = decoder.decodeBuffer(s);
        return b;
       } catch (Exception e) {
        e.printStackTrace();
       }
      }
      return b;
     }
     */
     /**
      * 
      * @param l
      * @return
      */
    /*
     public static byte[] longToByte(long l) { 
         byte[] b = new byte[8]; 
         for (int i = 0; i < b.length; i++) { 
             b[i] = new Long(l).byteValue(); 
             l = l >> 8; 
         } 
         return b; 
     } 
     */
    }


