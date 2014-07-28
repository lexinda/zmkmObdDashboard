package com.obd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**   
 * @Description: 日期相关工具类 
 * @author Junwu Zheng(junwu.zheng@boco.jp)
 * @date 2013-7-23 下午2:48:13 
 * @version V1.0   
 */
public class DateUtil {
	
	public static final String FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_SHORT = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_CHINA = "yyyy年MM月dd日 HH时mm分ss秒";
	
	public static String getCurrentTime() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(FORMAT_NORMAL);      
		return sDateFormat.format(new java.util.Date());  
	}
	
	/**
	 * 把日期转换成字符串，按照yyyy-MM-dd HH:mm:ss格式
	 * @param date
	 * @return
	 */
	public static String getNormalTime(Date date) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(FORMAT_NORMAL);
		if(null != date){
			return sDateFormat.format(date);  
		}
		return null;
	}
	
	/**
	 * 把日期转换成XX年XX月XX日的形式
	 * @param date
	 * @return
	 */
	public static String getChinaDate(Date date) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(FORMAT_CHINA);
		if(null != date){
			return sDateFormat.format(date);  
		}
		return null;
	}
	
	//Add by DX
	public static String getShortTime(Date date) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(FORMAT_SHORT);
		sDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		if(null != date){
			return sDateFormat.format(date);  
		}
		return null;
	}
	
	public static boolean isAfterCurrent(Date d) {
		return d.after(new Date());
	}
	
	public static Date getDate( String timestr , String format) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(format);    
		try {
			return sDateFormat.parse(timestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 计算小时数
     * 
     * @param start
     * @param end
     * @return
     */
    public static long calculateHours(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("start or end could not be null");
        }

        if (start.after(end)) {
            return 0;
        }
        long result = end.getTime() - start.getTime();

        return result / (1000 * 60 * 60);
    }
    
    /**
     * 时间加减操作
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date,int days){  
    	 Date time=null;  
    	 Calendar cal=Calendar.getInstance();  
    	 cal.add(Calendar.DAY_OF_MONTH, days);  
    	 time=cal.getTime();  
    	 return time;  
    }  
}
