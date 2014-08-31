package com.obd.dashboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.obd.model.Award;
import com.obd.model.Order;
import com.obd.model.User;
import com.obd.service.AwardService;
import com.obd.service.OrderService;
import com.obd.service.UserService;
import com.obd.util.DateUtil;
import com.obd.util.JsonUtil;

/**
 * AdminController 提供针对系统管理员增、删、改、查，登录、退出等操作
 * 
 * @version 1.0 at 2013/07/10
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	private final static String imagePath = "/img";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AwardService awardService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String Add(HttpServletRequest req,
			             Model model){
		model.addAttribute("message", "");
		return "/userAdd";
	
		
	}
	
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	public String doAdd(@RequestParam("userAccount") String userAccount,
						@RequestParam("userPassword") String userPassword,
						@RequestParam("userName") String userName,
						@RequestParam("userGender") int userGender,
						@RequestParam("userPhone") String userPhone,
						@RequestParam("userEmail") String userEmail,
						HttpServletRequest request,
	            		HttpServletResponse response,
	            		Model model) throws ParseException{
		model.addAttribute("message", "");
		
		User user = getUser(-1,userAccount,userPassword,userName,userGender, userPhone, userEmail);
		
		int result = userService.addUser(user);
		
		if(result >0 ){
			model.addAttribute("sucessMSG", "添加成功！");
		}else{
			model.addAttribute("errorMSG", "添加失败！");
		}
		
		System.out.println(user.toString());
		
		return "/userAdd";
	
		
	}
	
	@RequestMapping(value = "/userAdd", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String userAdd(@RequestParam("userAccount") String userAccount,
						@RequestParam("userPassword") String userPassword/*,
						@RequestParam(value="userName",required=false) String userName,
						@RequestParam(value="userGender",required=false) Integer userGender,
						@RequestParam(value="userPhone",required=false) String userPhone,
						@RequestParam(value="userEmail",required=false) String userEmail*/) throws ParseException, UnsupportedEncodingException{
		
		userAccount =  new String(userAccount.getBytes("iso-8859-1"), "utf-8");
		
		userPassword = new String(userPassword.getBytes("iso-8859-1"), "utf-8");
//		if(StringUtils.isNotBlank(userName)){
//			userName = new String(userName.getBytes("iso-8859-1"), "utf-8");
//		}
//		
//		if(StringUtils.isNotBlank(userEmail)){
//			userEmail = new String(userEmail.getBytes("iso-8859-1"), "utf-8");
//		}
//		User user = getUser(-1,userAccount,userPassword,userName,userGender, userPhone, userEmail);
		User user = getUser(-1,userAccount,userPassword,null,0, null, null);
		
		int result = userService.addUser(user);
		
		if(result >0 ){
			return JsonUtil.success("新增成功！");
		}else{
			return JsonUtil.failure("新增失败！");
		}
		
	}
	
	@RequestMapping(value = "/userModify", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String userModify(@RequestParam("userId") int userId,
						@RequestParam("userName") String userName,
						@RequestParam("userGender") int userGender,
						@RequestParam("userPhone") String userPhone,
						@RequestParam("userEmail") String userEmail) throws ParseException, UnsupportedEncodingException{
		
		userName = new String(userName.getBytes("iso-8859-1"), "utf-8");
		
		userEmail = new String(userEmail.getBytes("iso-8859-1"), "utf-8");
		
		User user = getUser(userId,null,null,userName,userGender, userPhone, userEmail);
		
		int result = userService.userModify(user);
		
		if(result >0 ){
			return JsonUtil.success("修改成功！");
		}else{
			return JsonUtil.failure("修改失败！");
		}
		
	}
	
	@RequestMapping(value = "/userModifyPassword", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String userModifyPassword(
						@RequestParam("userAccount") String userAccount,
						@RequestParam("userPassword") String userPassword,
						@RequestParam("newUserPassword") String newUserPassword) throws ParseException{
		
		

		int result = loginValid(userAccount,userPassword);
		
		if(result>0){
			
			Map<String,Object> dataMap = new HashMap<String,Object>();
			
			dataMap.put("userAccount", userAccount);
			
			dataMap.put("newUserPassword", newUserPassword);
			
			int resultData = userService.userModifyPassword(dataMap);
			
			if(resultData >0 ){
				return JsonUtil.success("修改成功！");
			}else{
				return JsonUtil.failure("修改失败！");
			}
			
		}else{
			return JsonUtil.failure("修改失败！");
		}
		
	}
	
	@RequestMapping(value = "/deleteAward", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String deleteAward(@RequestParam("userId") String userId,
						@RequestParam("awardId") String awardId,
						HttpServletRequest request,
	            		HttpServletResponse response,
	            		Model model) throws ParseException{
		model.addAttribute("message", "");
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("userId", userId);
		
		dataMap.put("awardId", awardId);
		
		int result = userService.deleteAward(dataMap);
		
		if(result >0 ){
			return JsonUtil.success("删除成功！");
		}else{
			return JsonUtil.failure("删除失败！");
		}
		
	}
	
	@RequestMapping(value = "/deleteAwards", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public  String deleteAwards(@RequestParam("userId") String userId,
						@RequestParam("awardId") String awardId,
						HttpServletRequest request,
	            		HttpServletResponse response,
	            		Model model) throws ParseException{
		model.addAttribute("message", "");
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("userId", userId);
		
		dataMap.put("awardId", awardId);
		
		int result = userService.deleteAward(dataMap);
		
		model.addAttribute("userId", userId);
		
		return "/userAwardList";
		
	}
	
	@RequestMapping(value = "/userAddShare", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String userAddShare(@RequestParam("userId") String userId,
						@RequestParam("awardId") String awardId,
						@RequestParam("targetPhone") String targetPhone,
						HttpServletRequest request,
	            		HttpServletResponse response,
	            		Model model) throws ParseException{
		model.addAttribute("message", "");
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("userId", userId);
		
		dataMap.put("awardId", awardId);
		
		dataMap.put("targetPhone", targetPhone);
		
		int result =0;// userService.addAward(dataMap);
		
		if(result >0 ){
			return JsonUtil.success("操作成功！");
		}else{
			return JsonUtil.failure("操作失败！");
		}
		
	}
	
	@RequestMapping(value = "/queryAwardByUserId", method = RequestMethod.GET)
	public String queryAwardByUserId(@RequestParam("userId")int userId){
		
		List<Award> awardList = userService.queryAwardByUserId(userId);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		if(awardList.size()>0){
			
			resultMap.put("awardList", awardList);
			
			return JsonUtil.getSuccessJsonFromMap(resultMap, "success");
			
		}else{
			
			return JsonUtil.getFailureJsonFromMap(resultMap, "error");
			
		}
		
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest req,
						HttpServletResponse response,
			             Model model){
		model.addAttribute("message", "");
		return "/userList";
	
		
	}
	
	@RequestMapping(value = "/showList", method = RequestMethod.POST)
	public void showList(@RequestParam("userAccount") String userAccount,
							@RequestParam("userPhone") String userPhone,
							@RequestParam("startNumber") int startNumber, 
            				@RequestParam("pageSize") int pageSize,
            				HttpServletRequest req,
            				HttpServletResponse response,
			                Model model) throws UnsupportedEncodingException{
		model.addAttribute("message", "");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<User> list = new ArrayList<User>();
		List<Map<String, Object>> l = new ArrayList<Map<String,Object>>();
		
		paramMap.put("userPhone", userPhone);
		paramMap.put("userAccount", userAccount);
		paramMap.put("startNumber",startNumber);
		paramMap.put("pageSize",pageSize);
		list = userService.getUserList(paramMap);
		int total = userService.getAllUserList(paramMap).size();
		if(list.size() <= 0){
			jsonMap.put("success", true);
			
		}else{
			for(User user:list){
				
				Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", user.getId());
					float userAmount = getUserAmounts(user.getId());
					map.put("userAmount", userAmount);
					map.put("userAccount", user.getUserAccount());
					map.put("userName", user.getUserName());
					map.put("userGender",user.getUserGender());
					map.put("userPhone",user.getUserPhone());
					map.put("userEmail",user.getUserEmail());
					String startDate = DateUtil.getChinaDate(user.getCreateTime());
					map.put("createTime",startDate);
					String updateDate = DateUtil.getChinaDate(user.getUpdateTime());
					map.put("updateTime",updateDate);
					l.add(map);
			}
			jsonMap.put("success", true);
		}   
		Gson gson = new Gson();
        jsonMap.put("list", l);
        jsonMap.put("total", total);
        String list1 = gson.toJson(jsonMap);
        response.setContentType("application/Json");
        response.setCharacterEncoding("UTF-8");  
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out;
        try { 
            out = response.getWriter();  
            out.print(list1);
            // 用于返回对象参数 
       } catch (IOException e) {  
            e.printStackTrace();  
       }
		
	}
	
	@RequestMapping(value = "/userAwardList", method = RequestMethod.GET)
	public String userAwardList(@RequestParam("userId") int userId,
						HttpServletRequest req,
						HttpServletResponse response,
			             Model model){
		model.addAttribute("userId", userId);
		return "/userAwardList";
	
		
	}
	
	@RequestMapping(value = "/showListByUserId", method = RequestMethod.POST)
	public void showListByUserId(@RequestParam("userId") int userId,
							@RequestParam("startNumber") int startNumber,
            				@RequestParam("pageSize") int pageSize,
            				HttpServletRequest req,
            				HttpServletResponse response,
			                Model model){
		model.addAttribute("message", "");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Award> list = new ArrayList<Award>();
		List<Map<String, Object>> l = new ArrayList<Map<String,Object>>();
		
		paramMap.put("userId", userId);
		paramMap.put("startNumber",startNumber);
		paramMap.put("pageSize",pageSize);
		list = userService.queryAwardByUserIdP(paramMap);
		int total = userService.queryAwardByUserId(userId).size();
		if(list.size() <= 0){
			jsonMap.put("success", true);
			
		}else{
			for(Award award:list){
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", userId);
				map.put("id", award.getId());
				map.put("awardImage", award.getAwardImage());
				map.put("awardInfo", award.getAwardInfo());
				map.put("awardNumber",award.getAwardNumber());
				map.put("awardType", award.getAwardType());
				map.put("awardAddress", award.getAwardAddress());
				map.put("awardMap", award.getAwardMap());
				map.put("awardPhone", award.getAwardPhone());
				String startDate = DateUtil.getChinaDate(award.getAwardStart());
				map.put("awardStart", startDate);
				String endDate = DateUtil.getChinaDate(award.getAwardStart());
				map.put("awardEnd", endDate);
				map.put("awardSecret", award.getAwardSecret());
				map.put("awardProvide", award.getAwardProvide());
				map.put("awardRate", award.getAwardRate());
				String createDate = DateUtil.getChinaDate(award.getCreateTime());
				map.put("createTime", createDate);
				if(award.getUpdateTime()!=null){
					String upateDate = DateUtil.getChinaDate(award.getUpdateTime());
					map.put("updateTime", upateDate);
				}else{
					map.put("updateTime", "");
				}
				
				l.add(map);
			}
			jsonMap.put("success", true);
		}   
		Gson gson = new Gson();
        jsonMap.put("list", l);
        jsonMap.put("total", total);
        String list1 = gson.toJson(jsonMap);
        response.setContentType("application/Json");
        response.setCharacterEncoding("UTF-8");  
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out;
        try { 
            out = response.getWriter();  
            out.print(list1);
            // 用于返回对象参数 
       } catch (IOException e) {  
            e.printStackTrace();  
       }
		
	}
	
	
	@RequestMapping(value = "/motionNumber", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String motionNumber(@RequestParam("userId") int userId){
		
		int motionNumber = 0;
		try{
			motionNumber = userService.queryMotionNumberByUserId(userId);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("motionNumber", motionNumber+1);
			
			int updateResult = userService.updateUserMotionNumber(paramMap);
			
			if(updateResult>0){
				return JsonUtil.success("success");
			}else{
				return JsonUtil.failure("error");
			}
			
		}catch(Exception e){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("motionNumber", 1);
			int addResult = userService.addUserMotionNumber(paramMap);
			if(addResult>0){
				return JsonUtil.success("success");
			}else{
				return JsonUtil.failure("error");
			}
		}
		
	}
	
	@RequestMapping(value = "/getFreeUserAward", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String getFreeUserAward(@RequestParam("userId") int userId){
		int addResult = userService.queryByUserAwardFree(userId);
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		if(addResult>0){
			
			jsonMap.put("isFree", 0);
			
			return JsonUtil.getSuccessJsonFromMap(jsonMap, "success");
		}else{
			
			jsonMap.put("isFree", 1);
			
			return JsonUtil.getSuccessJsonFromMap(jsonMap, "success");
		}
		
	}
	
	@RequestMapping(value = "/getUserAmount", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	//@RequestParam(value = "userId",required = false) int userId,@RequestParam(value = "awardId",required = false) int awardId
	public @ResponseBody String getUserAmount(@RequestParam(value = "userId",required = false) int userId) throws UnsupportedEncodingException{
		
		try{
			
			//获取用户获得奖品
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			
			paramMap.put("userId", userId);
			
			List<Order> orderList = orderService.getOrderByUserId(paramMap);
			
			int orderAmount = 0;
			
			for(Order order:orderList){
				orderAmount += order.getOrderAmount();
			}
			
			//获取用户的订单
			
			List<Award> awardList = awardService.getAllAwardOrderList(paramMap);
			
			float awardAmount = (float) (awardList.size()*0.2);
			
			float result = orderAmount/100-awardAmount;
			
			Map<String,Object> amountMap = new HashMap<String,Object>();
			
			amountMap.put("resultAmount", result);
			
			return JsonUtil.getSuccessJsonFromMap(amountMap, "success"); 
			
		}catch(Exception e){
			return JsonUtil.failure("error"); 
		}
		
	}
	
	public float getUserAmounts(int userId){
		
		try{
			
			//获取用户获得奖品
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			
			paramMap.put("userId", userId);
			
			List<Order> orderList = orderService.getOrderByUserId(paramMap);
			
			int orderAmount = 0;
			
			for(Order order:orderList){
				orderAmount += order.getOrderAmount();
			}
			
			//获取用户的订单
			
			List<Award> awardList = awardService.getAllAwardOrderList(paramMap);
			
			float awardAmount = (float) (awardList.size()*0.2);
			
			float result = orderAmount/100-awardAmount;
			
			
			return result; 
			
		}catch(Exception e){
			return 0; 
		}
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String login(@RequestParam("userAccount") String userAccount,@RequestParam("userPassword") String userPassword){
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("userAccount", userAccount);
		
		dataMap.put("userPassword", userPassword);
		
		User user = userService.login(dataMap);
		
		if(user != null){
			Map<String,Object> userResult = new HashMap<String,Object>();
			
			Map<String,Object> resultMap = new HashMap<String,Object>();
			
			
			resultMap.put("id", user.getId());
			
			resultMap.put("userAccount", user.getUserAccount());
			
			resultMap.put("userPassword", user.getUserPassword());
			
			resultMap.put("userName", user.getUserName());
			
			resultMap.put("userGender", user.getUserGender());
			
			resultMap.put("userPhone", user.getUserPhone());
			
			resultMap.put("userEmail", user.getUserEmail());
			
			resultMap.put("createTime", user.getCreateTime());
			
			resultMap.put("updateTime", user.getUpdateTime());
			
			float userAmount = getUserAmounts(user.getId());
			
			resultMap.put("userAmount", userAmount);
			
			userResult.put("userInfo", resultMap);
			
			return JsonUtil.getSuccessJsonFromMap(userResult, "success");
		}else{
			return JsonUtil.getFailureJsonFromMap(null, "error");
		}
		
	}
	
public int loginValid(String userAccount,String userPassword){
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("userAccount", userAccount);
		
		dataMap.put("userPassword", userPassword);
		
		User user = userService.login(dataMap);
		
		if(user != null){
			
			return 1;
		}else{
			return -1;
		}
		
	}
	
	public User getUser(int id,String userAccount,String userPassword,String userName,int userGender,String userPhone,String userEmail){
		
		User user = new User();
		
		user.setId(id);
		
		user.setUserAccount(userAccount);
		
		user.setUserPassword(userPassword);
		
		user.setUserName(userName);
		
		user.setUserGender(userGender);
		
		user.setUserPhone(userPhone);
		
		user.setUserEmail(userEmail);
		
		return user;
		
	}
	
}
