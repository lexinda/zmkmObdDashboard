package com.obd.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
public class SmsSend {

	
	/**
	 * 
	 * 文件作用：http接口使用实例
	 * 
	 * 创建时间：2012-08-01
	 * 
	 * 
		111 发送成功
		100 发送失败
		101 发送过快
		102 系统暂停
		103 网络不通
		104 不合法文字
		105 登录账户错误
		106 参数为空
		107 错误号码
		108 号码过多
		109 内容太长
		110 内部错误
		112	扩展号错
		113	时流量到
		114	参数错误
		115	账号禁用
		116 余额不足
		117	发送时间错误
		999 未知
	 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	public static String send(String mobile,String content) throws UnsupportedEncodingException{
		String inputline ="";		
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.3wxt.cn/servlet/SendSMS?method=single");
		// 向StringBuffer追加用户名
		sb.append("&username=zs00432");
		// 向StringBuffer追加密码
		sb.append("&password="+MD5Encode("yhkrb200"));//MD5加密
		// 向StringBuffer追加手机号码
		sb.append("&mobiles="+mobile);
		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&contents="+URLEncoder.encode(content,"utf-8"));
		try {
			// 创建url对象
			URL url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// 返回发送结果
			 inputline = in.readLine();
			 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputline;
	}
	
	public static String sendBatch(String mobile,String content) throws UnsupportedEncodingException{
		String inputline ="";		
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.3wxt.cn/servlet/SendSMS?method=batch");
		// 向StringBuffer追加用户名
		sb.append("&username=********");
		// 向StringBuffer追加密码
		sb.append("&password=MD5Encode(密码)");//MD5加密
		// 向StringBuffer追加手机号码
		sb.append("&mobiles="+mobile);
		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&contents="+URLEncoder.encode(content,"utf-8"));
		try {
			// 创建url对象
			URL url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// 返回发送结果
			 inputline = in.readLine();
			 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputline;
	}

	//测试
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str =send("18627089237", "sdk短信接口测试");
		System.out.println(str);
	}

	//MD5加密方式
	public static String MD5Encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}
}
