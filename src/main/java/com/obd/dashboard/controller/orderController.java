package com.obd.dashboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.obd.model.Order;
import com.obd.model.User;
import com.obd.service.AwardService;
import com.obd.service.OrderService;
import com.obd.service.UpmpService;
import com.obd.service.UserService;
import com.obd.util.DateUtil;
import com.obd.util.JsonUtil;
import com.obd.util.UpmpConfig;

/**
 * AdminController 提供针对系统管理员增、删、改、查，登录、退出等操作
 * 
 * @version 1.0 at 2013/07/10
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/order")
public class orderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AwardService awardService;
	
	@RequestMapping(value = "/getIphoneOrderInfo", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	//@RequestParam(value = "userId",required = false) int userId,@RequestParam(value = "awardId",required = false) int awardId
	public @ResponseBody String getIphoneOrderInfo(@RequestParam(value = "userId",required = false) int userId,@RequestParam(value = "orderAmount",required = false) String orderAmount) throws UnsupportedEncodingException{
		
		try{
		
			// 请求要素
			Map<String, String> req = new HashMap<String, String>();
			req.put("version", UpmpConfig.VERSION);// 版本号
			req.put("charset", UpmpConfig.CHARSET);// 字符编码
			req.put("transType", "01");// 交易类型
			req.put("merId", UpmpConfig.MER_ID);// 商户代码
			req.put("backEndUrl", UpmpConfig.MER_BACK_END_URL);// 通知URL
			req.put("frontEndUrl", UpmpConfig.MER_FRONT_END_URL);// 前台通知URL(可选)
			req.put("orderDescription", orderAmount+"元充值卡");// 订单描述(可选)
			req.put("orderTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// 交易开始日期时间yyyyMMddHHmmss
			req.put("orderTimeout", "");// 订单超时时间yyyyMMddHHmmss(可选)
			req.put("orderNumber", generateOrdrNo());//订单号(商户根据自己需要生成订单号)
			int amount = Integer.parseInt(orderAmount)*100;
			req.put("orderAmount", String.valueOf(amount));// 订单金额
	        req.put("orderCurrency", "156");// 交易币种(可选)
	        req.put("reqReserved", "透传信息");// 请求方保留域(可选，用于透传商户信息)
	        
	       
	                
	        // 保留域填充方法
	        Map<String, String> merReservedMap = new HashMap<String, String>();
	        merReservedMap.put("test", "test");
	        req.put("merReserved", UpmpService.buildReserved(merReservedMap));// 商户保留域(可选)
			
			Map<String, String> resp = new HashMap<String, String>();
			boolean validResp = UpmpService.trade(req, resp);
			System.out.println("req----------:"+req.toString());
			System.out.println("resp---------:"+resp.toString());
			
	        // 商户的业务逻辑
	        if (validResp){
	            // 服务器应答签名验证成功
	        	
	        	 Map<String, Object> resultMap = new HashMap<String, Object>();
	             resultMap.put("orderDescription", req.get("orderDescription"));
	             resultMap.put("orderTime", req.get("orderTime"));
	             resultMap.put("orderNumber", req.get("orderNumber"));
	             resultMap.put("orderAmount", req.get("orderAmount"));
	             
	        	Order order = new Order();
	        	
	        	order.setOrderTn(resp.get("tn"));
	        	order.setMerId(req.get("merId"));
	        	order.setTransType(req.get("transType"));;
	        	order.setOrderNumber(req.get("orderNumber"));
	        	order.setOrderDescription(req.get("orderDescription"));
	        	order.setOrderAmount(Integer.parseInt(req.get("orderAmount")));
	        	order.setIsSuccess(0);
	        	order.setUserId(userId);
	        	orderService.addOrder(order);
	        	
	        	return resp.get("tn");
	        }else {
	            // 服务器应答签名验证失败
	        	return JsonUtil.failure("error");
	        }
		}catch(Exception e){
			return JsonUtil.failure("error"); 
		}
		
	}
	
	@RequestMapping(value = "/addAwardOrder", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	//@RequestParam(value = "userId",required = false) int userId,@RequestParam(value = "awardId",required = false) int awardId
	public @ResponseBody String addAwardOrder(@RequestParam(value = "userId",required = false) int userId,@RequestParam(value = "awardId",required = false) String awardId) throws UnsupportedEncodingException{
		
		try{
			
			Map<String,Object> dataMap = new HashMap<String,Object>();
			
			dataMap.put("userId", userId);
			
			int addResult = userService.queryByUserAwardFree(userId);
			
			if(addResult>0){
				
				dataMap.put("isFree", 0);
				
			}else{
				
				dataMap.put("isFree", 1);
				
			}
			
			
			dataMap.put("awardId", awardId);
			
			int result = orderService.addAward(dataMap);
			if(result > 0){
				return JsonUtil.success("success");
			}else{
				return JsonUtil.failure("error"); 
			}
			
		
		}catch(Exception e){
			return JsonUtil.failure("error"); 
		}
		
	}
	
	
	@RequestMapping(value = "/getAndroidOrderInfo", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	//@RequestParam(value = "userId",required = false) int userId,@RequestParam(value = "awardId",required = false) int awardId
	public @ResponseBody String getAndroidOrderInfo(@RequestParam(value = "userId",required = false) int userId,@RequestParam(value = "orderAmount",required = false) String orderAmount) throws UnsupportedEncodingException{
		try{
			// 请求要素
						Map<String, String> req = new HashMap<String, String>();
						req.put("version", UpmpConfig.VERSION);// 版本号
						req.put("charset", UpmpConfig.CHARSET);// 字符编码
						req.put("transType", "01");// 交易类型
						req.put("merId", UpmpConfig.MER_ID);// 商户代码
						req.put("backEndUrl", UpmpConfig.MER_BACK_END_URL);// 通知URL
						req.put("frontEndUrl", UpmpConfig.MER_FRONT_END_URL);// 前台通知URL(可选)
						req.put("orderDescription", orderAmount+"元充值卡");// 订单描述(可选)
						req.put("orderTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));// 交易开始日期时间yyyyMMddHHmmss
						req.put("orderTimeout", "");// 订单超时时间yyyyMMddHHmmss(可选)
						req.put("orderNumber", generateOrdrNo());//订单号(商户根据自己需要生成订单号)
						int amount = Integer.parseInt(orderAmount)*100;
						req.put("orderAmount", String.valueOf(amount));// 订单金额
				        req.put("orderCurrency", "156");// 交易币种(可选)
				        req.put("reqReserved", "透传信息");// 请求方保留域(可选，用于透传商户信息)
				        
				       
				                
				        // 保留域填充方法
				        Map<String, String> merReservedMap = new HashMap<String, String>();
				        merReservedMap.put("test", "test");
				        req.put("merReserved", UpmpService.buildReserved(merReservedMap));// 商户保留域(可选)
						
						Map<String, String> resp = new HashMap<String, String>();
						boolean validResp = UpmpService.trade(req, resp);
					       
						System.out.println(resp.toString());
						
				        // 商户的业务逻辑
				        if (validResp){
				            // 服务器应答签名验证成功
				        	
				        	 Map<String, Object> resultMap = new HashMap<String, Object>();
				             resultMap.put("orderDescription", req.get("orderDescription"));
				             resultMap.put("orderTime", req.get("orderTime"));
				             resultMap.put("orderNumber", req.get("orderNumber"));
				             resultMap.put("orderAmount", req.get("orderAmount"));
				             
				        	Order order = new Order();
				        	
				        	order.setOrderTn(resp.get("tn"));
				        	order.setMerId(req.get("merId"));
				        	order.setTransType(req.get("transType"));;
				        	order.setOrderNumber(req.get("orderNumber"));
				        	order.setOrderDescription(req.get("orderDescription"));
				        	order.setOrderAmount(Integer.parseInt(req.get("orderAmount")));
				        	order.setIsSuccess(0);
				        	order.setUserId(userId);
				        	orderService.addOrder(order);
	        	Map<String,Object> jsonMap = new HashMap<String,Object>();
	        	jsonMap.put("respCode", resp.get("respCode"));
	        	jsonMap.put("tn", resp.get("tn"));
	        	jsonMap.put("signMethod", resp.get("signMethod"));
	        	jsonMap.put("transType", resp.get("transType"));
	        	jsonMap.put("charset", resp.get("charset"));
	        	jsonMap.put("reqReserved", resp.get("reqReserved"));
	        	jsonMap.put("signature", resp.get("signature"));
	        	jsonMap.put("version", resp.get("version"));
	        	return JsonUtil.getSuccessJsonFromMap(jsonMap, "success");
	        }else {
	            // 服务器应答签名验证失败
	        	return JsonUtil.failure("error");
	        }
		}catch(Exception e){
			return JsonUtil.failure("error"); 
		}
	}
	
	@RequestMapping(value = "/updateOrderStatus", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String updateOrderStatus(@RequestParam("tn") String tn) throws UnsupportedEncodingException{
		 // 请求要素
        	Map<String,Object> jsonMap = new HashMap<String,Object>();
        	jsonMap.put("orderTn", tn);
        	int result = orderService.updateOrder(jsonMap);
        	if(result>0){
        		return JsonUtil.success("success");
        	}else{
        		return JsonUtil.failure("error");
        	}
		
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest req,
						HttpServletResponse response,
			             Model model){
		model.addAttribute("message", "");
		return "/orderList";
	
		
	}
	
	@RequestMapping(value = "/showList", method = RequestMethod.POST)
	public void showList(@RequestParam("orderTn") String tn,
							@RequestParam("userAccount") String userAccount,
							@RequestParam("isSuccess") int isSuccess,
							@RequestParam("startNumber") int startNumber, 
            				@RequestParam("pageSize") int pageSize,
            				HttpServletRequest req,
            				HttpServletResponse response,
			                Model model){
		model.addAttribute("message", "");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Order> list = new ArrayList<Order>();
		List<Map<String, Object>> l = new ArrayList<Map<String,Object>>();
		
		paramMap.put("orderTn", tn);
		paramMap.put("isSuccess", isSuccess);
		paramMap.put("startNumber",startNumber);
		paramMap.put("pageSize",pageSize);
		list = orderService.getOrderList(paramMap);
		int total = orderService.getAllOrderList(paramMap).size();
		if(list.size() <= 0){
			jsonMap.put("success", true);
			
		}else{
			for(Order order:list){
				
				Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", order.getId());
					map.put("orderTn", order.getOrderTn());
					map.put("transType", order.getTransType());
					map.put("merId",order.getMerId());
					map.put("orderNumber", order.getOrderNumber());
					map.put("orderDescription", order.getOrderDescription());
					map.put("orderAmount", order.getOrderAmount());
					map.put("isSuccess", order.getIsSuccess());
					String updateTime = DateUtil.getChinaDate(order.getUpdateTime());
					map.put("updateTime", updateTime);
					User u=userService.getUserById(order.getUserId());
					map.put("userAccount", u.getUserAccount());
					map.put("userName", u.getUserName());
					if(!userAccount.equals("")){
						if(u.getUserAccount().contains(userAccount)){
							l.add(map);
						}
					}else{
						l.add(map);
					}
					
					
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
	
	/**
	 * 订单号生成，该生产订单号仅用于测试，商户根据自己需要生成订单号
	 * @return
	 */
	public static String generateOrdrNo(){
		DateFormat formater = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuilder sb = new StringBuilder(formater.format(new Date()));
        return sb.toString();
	}
	
	
}
