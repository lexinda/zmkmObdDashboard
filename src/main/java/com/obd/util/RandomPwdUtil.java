package com.obd.util;

import java.util.Random;

/**
 * 随机产生N密码
 * 
 * @author shuebi.liu
 * @version 1.0 at 2013-7-29
 * @since 1.0
 *
 */
public class RandomPwdUtil {
	

 public static String getRandomPassword(int pwdLength){
	 int i;
     int count = 0;
	 char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				    'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	 StringBuffer pwd = new StringBuffer();
	 Random r = new Random();
	 while(count < pwdLength){
	 i=Math.abs(r.nextInt(36));
	 pwd.append(str[i]);
	 count++;
 }
	 return pwd.toString();
 }
 /**
  * 随机产生字母
  * @param pwdLength
  * @return
  */
 public static String getRandomCapital(int pwdLength){
	 int i;
     int count = 0;
	 char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				    'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				    'X', 'Y', 'Z',};
	 StringBuffer pwd = new StringBuffer();
	 Random r = new Random();
	 while(count < pwdLength){
	 i=Math.abs(r.nextInt(26));
	 pwd.append(str[i]);
	 count++;
 }
	 return pwd.toString();
 }
 /**
  * 随机产生数字
  * @param pwdLength
  * @return
  */
 public static String getRandomNumber(int pwdLength){
	 int i;
     int count = 0;
	 char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	 StringBuffer pwd = new StringBuffer();
	 Random r = new Random();
	 while(count < pwdLength){
	 i=Math.abs(r.nextInt(10));
	 pwd.append(str[i]);
	 count++;
 }
	 return pwd.toString();
 }
}
