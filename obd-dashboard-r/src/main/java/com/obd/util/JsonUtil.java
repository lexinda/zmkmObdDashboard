package com.obd.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

/**
 * @Description: Json工具类
 * @author Junwu Zheng(junwu.zheng@boco.jp)
 * @date 2013-7-19 下午3:33:10
 * @version V1.0
 */
public class JsonUtil {

	private static Gson gson = new Gson();

	public static final int CODE_SUCCESS = 0;

	public static final int CODE_FAILURE = -1;

	/**
	 * 返回成功的Json串
	 * 
	 * @param msg
	 *            传入的成功信息
	 * @return {"message":"成功的msg","code":CODE_SUCCESS}
	 */
	public static String success(String msg) {
		return getJsonStr(msg, CODE_SUCCESS);
	}

	/**
	 * 返回失败的Json串
	 * 
	 * @param msg
	 *            传入的失败信息
	 * @return {"message":"失败msg","code":CODE_FAILURE}
	 */
	public static String failure(String msg) {
		return getJsonStr(msg, CODE_FAILURE);
	}

	/**
	 * 根据传入的List<Map<String,Object>>返回对应json串,并增加成功的message和code信息
	 * 
	 * @param jsonList
	 * @param msg
	 * @return 
	 *         {"message":"msg","body":[{"name1":0,"name2":123},{"name3":0,"name4"
	 *         :123}],"code":1}
	 */
	public static String getSuccessJsonFromList(
			List<Map<String, Object>> jsonList, String msg) {
		return getJsonFromList(jsonList, msg, CODE_SUCCESS);
	}

	/**
	 * 根据传入的List<Map<String,Object>>返回对应json串,并增加失败的message和code信息
	 * 
	 * @param jsonList
	 * @param msg
	 * @return 
	 *         {"message":"msg","body":[{"name1":0,"name2":123},{"name3":0,"name4"
	 *         :123}],"code":0}
	 */
	public static String getFailureJsonFromList(
			List<Map<String, Object>> jsonList, String msg) {
		return getJsonFromList(jsonList, msg, CODE_FAILURE);
	}
	
	/**
	 * 根据传入的Map<String,Object>返回对应json串,并增加成功的message和code信息
	 * @param jsonMap
	 * @param msg
	 * @return {"message":"成功消息","body":{"name1":"value1","name2":"value2"},"code":0}
	 */
	public static String getSuccessJsonFromMap(Map<String, Object> jsonMap,String msg){
		return getJsonFromMap(jsonMap, msg, CODE_SUCCESS);
	}
	
	/**
	 * 根据传入的Map<String,Object>返回对应json串,并增加成功的message和code信息
	 * @param jsonMap
	 * @param msg
	 * @return {"message":"失败消息","body":{"name1":"value1","name2":"value2"},"code":1}
	 */
	public static String getFailureJsonFromMap(Map<String, Object> jsonMap,String msg){
		return getJsonFromMap(jsonMap, msg, CODE_FAILURE);
	}

	private static String getJsonFromMap(Map<String, Object> map, String msg,
			int code) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("message", msg);
		jsonMap.put("code", code);
		jsonMap.put("body", map);
		return gson.toJson(jsonMap);
	}

	private static String getJsonFromList(List<Map<String, Object>> jsonList,
			String msg, int code) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("message", msg);
		jsonMap.put("code", code);
		jsonMap.put("body", jsonList);
		return gson.toJson(jsonMap);
	}

	private static String getJsonStr(String msg, int code) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("message", msg);
		jsonMap.put("code", code);
		return gson.toJson(jsonMap);
	}
	
	/**
	 * 获取xmpp推送消息的json形式的字符串
	 * @param type
	 * @param fromUserId
	 * @param timestamp
	 * @param message
	 * @return
	 */
	public static String getXmppSendMessageOnJson(int type,String fromUserId,long timestamp,String message){

		Map<String, Object> jsonMap =new HashMap<String, Object>();
		jsonMap.put("type", type);
		jsonMap.put("from_userid", fromUserId);
		jsonMap.put("timestamp", timestamp);
		jsonMap.put("content", message);
		Gson gson = new Gson();

		return gson.toJson(jsonMap);
	}

}
