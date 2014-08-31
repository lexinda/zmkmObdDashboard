package com.obd.dashboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.obd.model.Award;
import com.obd.model.AwardDetail;
import com.obd.model.Order;
import com.obd.service.AwardService;
import com.obd.service.OrderService;
import com.obd.service.UserService;
import com.obd.util.AjaxUtils;
import com.obd.util.DateUtil;
import com.obd.util.ImageUploadUtils;
import com.obd.util.Inputs;
import com.obd.util.JsonUtil;

/**
 * AdminController 提供针对系统管理员增、删、改、查，登录、退出等操作
 * 
 * @version 1.0 at 2013/07/10
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/award")
public class AwardController {

	private final double EARTH_RADIUS = 6378.137;//地球半径
	
	private static Logger logger = Logger.getLogger(AwardController.class);
	
	private final String imagePath = "/home/bswlkjnb8s8w0lukbj/wwwroot/WEB-INF/images/upload";
	//private final String imagePath="E:\\renren\\workspace\\obd-dashboard\\src\\main\\webapp\\WEB-INF\\images\\upload";
	
	@Autowired
	private AwardService awardService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String Add(HttpServletRequest req,
			             Model model){
		model.addAttribute("imagePath", imagePath);
		
		return "/awardAdd";
	
		
	}
	
	@RequestMapping(value = "/doAdd", method = RequestMethod.POST)
	public String doAdd(@RequestParam("uploadImg") String awardImage,
						@RequestParam("awardName") String awardName,
						@RequestParam("awardContent") String awardContent,
						@RequestParam("awardInfo") String awardInfo,
						@RequestParam("awardNumber") String awardNumber,
						@RequestParam("awardType") int awardType,
						@RequestParam("awardAddress") String awardAddress,
						@RequestParam("awardMap") String awardMap,
						@RequestParam("awardPhone") String awardPhone,
						@RequestParam("awardStart") String awardStart,
						@RequestParam("awardEnd") String awardEnd,
						@RequestParam("awardSecret") String awardSecret,
						@RequestParam("awardProvide") String awardProvide,
						@RequestParam("awardRate") int awardRate,
						HttpServletRequest request,
	            		HttpServletResponse response,
	            		Model model) throws ParseException{
		model.addAttribute("message", "");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = sdf.parse(awardStart);
		
		Date endDate = sdf.parse(awardEnd);
		
		Award award = getAward(-1,awardImage,awardName,awardContent,awardInfo,awardNumber,awardType, awardAddress, awardMap, awardPhone, startDate, endDate, awardSecret, awardProvide, awardRate);
		
		int result = awardService.addAward(award);
		
		if(result >0 ){
			model.addAttribute("sucessMSG", "添加成功！");
		}else{
			model.addAttribute("errorMSG", "添加失败！");
		}
		
		System.out.println(award.toString());
		
		return "/awardAdd";
	
		
	}
	
	@RequestMapping(value = "/appAddress", method = RequestMethod.GET)
	public String appAddress(HttpServletRequest req,
			             Model model){
		
		String appAddress = awardService.queryAppAddressById();
		model.addAttribute("appAddress", appAddress);
		return "/appAddress";
	
		
	}
	
	@RequestMapping(value = "/doAddAddress", method = RequestMethod.POST)
	public String doAddAddress(@RequestParam("appAddress") String appAddress,
						HttpServletRequest request,
	            		HttpServletResponse response,
	            		Model model) throws ParseException{
		
		int result = awardService.updateAppAddress(appAddress);
		model.addAttribute("appAddress", appAddress);
		if(result >0 ){
			model.addAttribute("sucessMSG", "添加成功！");
		}else{
			model.addAttribute("errorMSG", "添加失败！");
		}
		
		return "/appAddress";
	
		
	}
	
	@RequestMapping(value = "/queryAppAddressById", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String queryAppAddressById() throws UnsupportedEncodingException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String appAddress = awardService.queryAppAddressById();
		
		resultMap.put("appAddress", appAddress);
		
		return JsonUtil.getSuccessJsonFromMap(resultMap, "success");
	}
	
	@RequestMapping(value = "/setAward", method = RequestMethod.GET)
	public String edit(@RequestParam("awardId") String awardId,HttpServletRequest req,
			             Model model){
		
		Award award = awardService.getAwardById(Integer.parseInt(awardId));
		
		model.addAttribute("id", award.getId());
		
		model.addAttribute("awardImage", award.getAwardImage());
		model.addAttribute("awardName", award.getAwardName());
		model.addAttribute("awardContent", award.getAwardContent());
		model.addAttribute("awardInfo", award.getAwardInfo());
		model.addAttribute("awardNumber",award.getAwardNumber());
		model.addAttribute("awardType", award.getAwardType());
		model.addAttribute("awardAddress", award.getAwardAddress());
		model.addAttribute("awardMap", award.getAwardMap());
		model.addAttribute("awardPhone", award.getAwardPhone());
		String startDate = DateUtil.getChinaDate(award.getAwardStart());
		model.addAttribute("awardStart", startDate);
		model.addAttribute("startDate", award.getAwardStart());
		String endDate = DateUtil.getChinaDate(award.getAwardStart());
		model.addAttribute("awardEnd", endDate);
		model.addAttribute("endDate", award.getAwardEnd());
		model.addAttribute("awardSecret", award.getAwardSecret());
		model.addAttribute("awardProvide", award.getAwardProvide());
		model.addAttribute("awardRate", award.getAwardRate());
		
		return "/awardEdit";
	}
	
	@RequestMapping(value = "/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam("awardId") int awardId,
						@RequestParam("uploadImg") String awardImage,
						@RequestParam("awardName") String awardName,
						@RequestParam("awardContent") String awardContent,
						@RequestParam("awardInfo") String awardInfo,
						@RequestParam("awardNumber") String awardNumber,
						@RequestParam("awardType") int awardType,
						@RequestParam("awardAddress") String awardAddress,
						@RequestParam("awardMap") String awardMap,
						@RequestParam("awardPhone") String awardPhone,
						@RequestParam("awardStart") String awardStart,
						@RequestParam("awardEnd") String awardEnd,
						@RequestParam("awardSecret") String awardSecret,
						@RequestParam("awardProvide") String awardProvide,
						@RequestParam("awardRate") int awardRate,
						HttpServletRequest request,
	            		HttpServletResponse response,
	            		Model model) throws ParseException{
		model.addAttribute("message", "");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = sdf.parse(awardStart.replace("年", "-").replace("月","-").replace("日", ""));
		
		Date endDate = sdf.parse(awardEnd.replace("年", "-").replace("月","-").replace("日", ""));
		
		Award award = getAward(awardId,awardImage,awardName,awardContent,awardInfo,awardNumber,awardType, awardAddress, awardMap, awardPhone, startDate, endDate, awardSecret, awardProvide, awardRate);
		
		int result = awardService.updateAward(award);
		
		if(result >0 ){
			return "redirect:/award/list";
		}else{
			model.addAttribute("errorMSG", "修改失败！");
		}
		
		return "/awardEdit";
		
	}
	
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(@RequestParam("awardId") int awardId,
							HttpServletRequest req,
							HttpServletResponse response,
				            Model model){
		
		int result = awardService.delete(awardId);
		
		if(result >0 ){
			model.addAttribute("successMSG", "删除成功！");
		}else{
			model.addAttribute("errorMSG", "删除失败！");
		}
		
		return "/awardList";
		
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest req,
						HttpServletResponse response,
			             Model model){
		model.addAttribute("message", "");
		return "/awardList";
	
		
	}
	
	@RequestMapping(value = "/showList", method = RequestMethod.POST)
	public void showList(@RequestParam("awardNumber") String awardNumber,
							@RequestParam("awardType") int awardType,
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
		
		paramMap.put("awardNumber", awardNumber);
		paramMap.put("awardType", awardType);
		paramMap.put("startNumber",startNumber);
		paramMap.put("pageSize",pageSize);
		list = awardService.getAwardList(paramMap);
		int total = awardService.getAllAwardList(paramMap).size();
		if(list.size() <= 0){
			jsonMap.put("success", true);
			
		}else{
			for(Award award:list){
				
				Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", award.getId());
					map.put("awardImage", award.getAwardImage());
					map.put("awardName", award.getAwardName());
					map.put("awardContent", award.getAwardContent());
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
					String upateDate = DateUtil.getChinaDate(award.getUpdateTime());
					map.put("updateTime", upateDate);
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
	
	@RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
	 public void uploadImg(@RequestParam("selectImg") CommonsMultipartFile file,
							@RequestParam(value="oldImg",required=false) String oldImg,
							 HttpServletRequest request,
				             HttpServletResponse response,
				             Model model){
	  Map<String, Object> rtn = new HashMap<String, Object>();
	 
	  if(ImageUploadUtils.isValidFormats(file)){
		  
		  rtn.put("status", 0);
	   
		  AjaxUtils.printJson(rtn, response);
		  
	  }
	 
	  if(StringUtils.isNotBlank(oldImg)){
			 
			 String fileUrl = imagePath+"/"+oldImg.replace("images/upload/", "");
			 //String fileUrl = imagePath+"\\"+oldImg.replace("images/upload/", "");
			 
			 System.out.println(fileUrl);
			 
			 File fileData = new File(fileUrl);  
			    // 路径为文件且不为空则进行删除  
			    if (fileData.isFile() && fileData.exists()) {  
			    	fileData.delete();  
			    }  
		 }
	  
	  String filePath = ImageUploadUtils.uploadImg(file, imagePath,"pimg_", 0);
	  
	  System.out.println(filePath);
	  
	  filePath = StringUtils.substringAfter(filePath, imagePath);
	  
	  System.out.println(filePath);
	  
	  rtn.put("status", 1);
	  
	  rtn.put("imgUrl", "images/upload"+filePath);
	 
	  AjaxUtils.printJson(rtn, response);
	  
	 }
	
	public Award getAward(int awardId,String awardImage,String awardName,String awardContent,String awardInfo,String awardNumber,int awardType,String awardAddress,String awardMap,String awardPhone,Date awardStart,
			Date awardEnd,String awardSecret,String awardProvide,int awardRate){
		
		Award award = new Award();
		
		if(awardId != -1){
			award.setId(awardId);
		}
		
		award.setAwardImage(awardImage);
		
		award.setAwardName(awardName);
		
		award.setAwardContent(awardContent);
		
		award.setAwardInfo(awardInfo);
		
		award.setAwardNumber(awardNumber);
		
		award.setAwardType(awardType);
		
		award.setAwardAddress(awardAddress);
		
		award.setAwardMap(awardMap);
		
		award.setAwardPhone(awardPhone);
		
		award.setAwardStart(awardStart);
		
		award.setAwardEnd(awardEnd);
		
		award.setAwardSecret(awardSecret);
		
		award.setAwardProvide(awardProvide);
		
		award.setAwardRate(awardRate);
		
		return award;
		
	}
	
	@RequestMapping(value = "/getNearAward", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String getNearAward(@RequestParam(value="lat1",required=false) String lat1,@RequestParam(value="lng1",required=false) String lng1) throws UnsupportedEncodingException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		List< Map<String,Object>> resultData = new ArrayList< Map<String,Object>>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		paramMap.put("awardNumber", "");
		
		paramMap.put("awardType", -1);
		
		List<Award> awardList = awardService.getAllAwardList(paramMap);
		
		List<Award> awardInfoList = new ArrayList<Award>();
		
		int newAward = 0;
		
		int superAward = 0;
		
		if(awardList.size()>0){
			
			for(Award award:awardList){
				
				String mapInfo = award.getAwardMap();
				
				String[] mapInfos = mapInfo.split(",");
				
				
				double lat2 = Double.parseDouble(mapInfos[0]);
				
				double lng2 = Double.parseDouble(mapInfos[1]);
				
				double lat = Double.parseDouble(lat1);
				
				double lng = Double.parseDouble(lng1);
				
				double result = GetDistance(lat,lng,lat2,lng2);
				
//				if(result <= 500){
					
					if(award.getAwardType() == 0){
						if(newAward<3){
							
							Map<String,Object> awardMap = new HashMap<String,Object>();
							  
							  awardMap.put("id", award.getId());
							  
							  awardMap.put("awardImage", award.getAwardImage());
							  
							  awardMap.put("awardName", award.getAwardName());
							  
							  awardMap.put("awardContent", award.getAwardContent());
							  
							  awardMap.put("awardInfo", award.getAwardInfo());
							  
							  awardMap.put("awardNumber", award.getAwardNumber());
							  
							  awardMap.put("awardAddress", award.getAwardAddress());
							  
							  awardMap.put("awardPhone", award.getAwardPhone());
							  
							  String newStart = sdf.format(award.getAwardStart());
							  
							  awardMap.put("awardStart", newStart);
							  
							  String newEnd = sdf.format(award.getAwardEnd());
							  
							  awardMap.put("awardEnd", newEnd);
							  
							  awardMap.put("awardSecret", award.getAwardSecret());
							  
							  awardMap.put("awardProvide", award.getAwardProvide());
							  
							  awardMap.put("awardMap", award.getAwardMap());
							  
							  awardMap.put("awardRate", award.getAwardRate());
							  
							  awardMap.put("createTime", award.getCreateTime());
							  
							  awardMap.put("updateTime", award.getUpdateTime());
							  
							  awardMap.put("awardType", award.getAwardType());
							  
							  resultData.add(awardMap);
						}
						
						newAward++;
						
					}else if(award.getAwardType() == 1){
						if(superAward<6){
							
							Map<String,Object> awardMap = new HashMap<String,Object>();
							  
							  awardMap.put("id", award.getId());
							  
							  awardMap.put("awardImage", award.getAwardImage());
							  
							  awardMap.put("awardName", award.getAwardName());
							  
							  awardMap.put("awardContent", award.getAwardContent());
							  
							  awardMap.put("awardInfo", award.getAwardInfo());
							  
							  awardMap.put("awardNumber", award.getAwardNumber());
							  
							  awardMap.put("awardAddress", award.getAwardAddress());
							  
							  awardMap.put("awardPhone", award.getAwardPhone());
							  
							  String newStart = sdf.format(award.getAwardStart());
							  
							  awardMap.put("awardStart", newStart);
							  
							  String newEnd = sdf.format(award.getAwardEnd());
							  
							  awardMap.put("awardEnd", newEnd);
							  
							  awardMap.put("awardSecret", award.getAwardSecret());
							  
							  awardMap.put("awardProvide", award.getAwardProvide());
							  
							  awardMap.put("awardMap", award.getAwardMap());
							  
							  awardMap.put("awardRate", award.getAwardRate());
							  
							  awardMap.put("createTime", award.getCreateTime());
							  
							  awardMap.put("updateTime", award.getUpdateTime());
							  
							  awardMap.put("awardType", award.getAwardType());
							  
							  resultData.add(awardMap);
						
						}
						superAward++;
					}
					
					
//				}
//				Map<String,Object> awardMap = new HashMap<String,Object>();
//				  
//				  awardMap.put("id", award.getId());
//				  
//				  awardMap.put("awardImage", award.getAwardImage());
//				  
//				  awardMap.put("awardName", award.getAwardName());
//				  
//				  awardMap.put("awardContent", award.getAwardContent());
//				  
//				  awardMap.put("awardInfo", award.getAwardInfo());
//				  
//				  awardMap.put("awardNumber", award.getAwardNumber());
//				  
//				  awardMap.put("awardAddress", award.getAwardAddress());
//				  
//				  awardMap.put("awardPhone", award.getAwardPhone());
//				  
//				  String newStart = sdf.format(award.getAwardStart());
//				  
//				  awardMap.put("awardStart", newStart);
//				  
//				  String newEnd = sdf.format(award.getAwardEnd());
//				  
//				  awardMap.put("awardEnd", newEnd);
//				  
//				  awardMap.put("awardSecret", award.getAwardSecret());
//				  
//				  awardMap.put("awardProvide", award.getAwardProvide());
//				  
//				  awardMap.put("awardMap", award.getAwardMap());
//				  
//				  awardMap.put("awardRate", award.getAwardRate());
//				  
//				  awardMap.put("createTime", award.getCreateTime());
//				  
//				  awardMap.put("updateTime", award.getUpdateTime());
//				  
//				  awardMap.put("awardType", award.getAwardType());
//				  
//				  resultData.add(awardMap);
			}
			
			resultMap.put("awardList", resultData);
			
		}else{
			
		}
		
		return JsonUtil.getSuccessJsonFromMap(resultMap, "success");
	}
	
	@RequestMapping(value = "/getMotionAward", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String getMotionAward(@RequestParam("userId") int userId,@RequestParam(value="lat1",required=false) String lat1,@RequestParam(value="lng1",required=false) String lng1) throws UnsupportedEncodingException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int motionNumber = 0;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("awardNumber", "");
		
		paramMap.put("awardType", -1);
		
		List<Award> awardList = awardService.getAllAwardList(paramMap);
		
		List<Award> awardInfoList = new ArrayList<Award>();
		
		List<Award> awardNearList = new ArrayList<Award>();
		
		
		
		if(awardList.size()>0){
			
			for(Award award:awardList){
				
				String mapInfo = award.getAwardMap();
				
				String[] mapInfos = mapInfo.split(",");
				
				
				double lat2 = Double.parseDouble(mapInfos[0]);
				
				double lng2 = Double.parseDouble(mapInfos[1]);
				
				if(Inputs.trimToNull(lat1)==null||Inputs.trimToNull(lng1)==null){
					awardNearList.add(award);
				}else{
					double lat = Double.parseDouble(lat1);
					
					double lng = Double.parseDouble(lng1);
					
					double result = GetDistance(lat,lng,lat2,lng2);
					
					if(result<500){
						awardNearList.add(award);
					}
				}
			}
			
			if(awardNearList.size()>0){
				for(Award award:awardNearList){
					
					try{
						motionNumber = userService.queryMotionNumberByUserId(userId);
					}catch(Exception e){
						Map<String, Object> paramMaps = new HashMap<String, Object>();
						paramMaps.put("userId", userId);
						paramMaps.put("motionNumber", 1);
						userService.addUserMotionNumber(paramMaps);
						motionNumber = 1;
					}
					
					if(award.getAwardRate()<= motionNumber){
						awardInfoList.add(award);
					}
					
				}
			}else{
				for(Award award:awardList){
					
					try{
						motionNumber = userService.queryMotionNumberByUserId(userId);
					}catch(Exception e){
						Map<String, Object> paramMaps = new HashMap<String, Object>();
						paramMaps.put("userId", userId);
						paramMaps.put("motionNumber", 1);
						userService.addUserMotionNumber(paramMaps);
						motionNumber = 1;
					}
					
					if(award.getAwardRate()<= motionNumber){
						awardInfoList.add(award);
					}
					
				}
			}
			
			
			List<Award> resultAward = new ArrayList<Award>();
			
			List< Map<String,Object>> resultData = new ArrayList< Map<String,Object>>();
			
			if(awardInfoList.size()>0){
				  int r=(int) (Math.random()*awardInfoList.size()); 
				  Award award = awardInfoList.get(r); 
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				  
				  Map<String,Object> awardMap = new HashMap<String,Object>();
				  
				  awardMap.put("id", award.getId());
				  
				  awardMap.put("awardImage", award.getAwardImage());
				  
				  awardMap.put("awardName", award.getAwardName());
				  
				  awardMap.put("awardContent", award.getAwardContent());
				  
				  awardMap.put("awardInfo", award.getAwardInfo());
				  
				  awardMap.put("awardNumber", award.getAwardNumber());
				  
				  awardMap.put("awardAddress", award.getAwardAddress());
				  
				  awardMap.put("awardPhone", award.getAwardPhone());
				  
				  String newStart = sdf.format(award.getAwardStart());
				  
				  awardMap.put("awardStart", newStart);
				  
				  String newEnd = sdf.format(award.getAwardEnd());
				  
				  awardMap.put("awardEnd", newEnd);
				  
				  awardMap.put("awardSecret", award.getAwardSecret());
				  
				  awardMap.put("awardProvide", award.getAwardProvide());
				  
				  awardMap.put("awardMap", award.getAwardMap());
				  
				  awardMap.put("awardRate", award.getAwardRate());
				  
				  awardMap.put("createTime", award.getCreateTime());
				  
				  awardMap.put("updateTime", award.getUpdateTime());
				  
				  awardMap.put("awardType", award.getAwardType());
				  
				  Map<String,Object> dataMap = new HashMap<String,Object>();
				  
				  dataMap.put("userId", userId);
				  
				  int addResult = userService.queryByUserAwardFree(userId);
					
					if(addResult>0){
						
						dataMap.put("isFree", 0);						
						Map<String, Object> paramMaps = new HashMap<String, Object>();
						paramMaps.put("userId", userId);
						paramMaps.put("motionNumber", motionNumber+1);
						userService.updateUserMotionNumber(paramMaps);
						
					}else{
						
						dataMap.put("isFree", 1);
						
					}
				  
				  dataMap.put("awardId", award.getId());
				  
				  orderService.addAward(dataMap);
				  
				  resultData.add(awardMap);
			}
			
			resultMap.put("awardList", resultData);
			
		}else{
			
		}
		
		return JsonUtil.getSuccessJsonFromMap(resultMap, "success");
	}
	
	@RequestMapping(value = "/getNomalMotionAward", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String getNomalMotionAward() throws UnsupportedEncodingException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("awardNumber", "");
		
		paramMap.put("awardType", -1);
		
		List<Award> awardList = awardService.getAllAwardList(paramMap);
		
			List<Award> resultAward = new ArrayList<Award>();
			
			List< Map<String,Object>> resultData = new ArrayList< Map<String,Object>>();
			
			if(awardList.size()>0){
				  int r=(int) (Math.random()*awardList.size()); 
				  Award award = awardList.get(r); 
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				  
				  Map<String,Object> awardMap = new HashMap<String,Object>();
				  
				  awardMap.put("id", award.getId());
				  
				  awardMap.put("awardImage", award.getAwardImage());
				  
				  awardMap.put("awardName", award.getAwardName());
				  
				  awardMap.put("awardContent", award.getAwardContent());
				  
				  awardMap.put("awardInfo", award.getAwardInfo());
				  
				  awardMap.put("awardNumber", award.getAwardNumber());
				  
				  awardMap.put("awardAddress", award.getAwardAddress());
				  
				  awardMap.put("awardPhone", award.getAwardPhone());
				  
				  String newStart = sdf.format(award.getAwardStart());
				  
				  awardMap.put("awardStart", newStart);
				  
				  String newEnd = sdf.format(award.getAwardEnd());
				  
				  awardMap.put("awardEnd", newEnd);
				  
				  awardMap.put("awardSecret", award.getAwardSecret());
				  
				  awardMap.put("awardProvide", award.getAwardProvide());
				  
				  awardMap.put("awardMap", award.getAwardMap());
				  
				  awardMap.put("awardRate", award.getAwardRate());
				  
				  awardMap.put("createTime", award.getCreateTime());
				  
				  awardMap.put("updateTime", award.getUpdateTime());
				  
				  awardMap.put("awardType", award.getAwardType());
				  
				  Map<String,Object> dataMap = new HashMap<String,Object>();
				  
				  resultData.add(awardMap);
			
			resultMap.put("awardList", resultData);
			
		}
		
		return JsonUtil.getSuccessJsonFromMap(resultMap, "success");
	}
	
	@RequestMapping(value = "/getUserAward", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String getUserAward(@RequestParam("userId") int userId) throws UnsupportedEncodingException, ParseException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		float userAmount = getUserAmounts(userId);
		
		paramMap.put("userId", userId);
		
		List<Award> awardList = awardService.getAwardOrderList(paramMap);
		
		List<AwardDetail> awardDetailList = new ArrayList<AwardDetail>();
		
		for(Award award:awardList){
			
			AwardDetail awardDetail = new AwardDetail();
			awardDetail.setId(award.getId());
			awardDetail.setAwardName(award.getAwardName());
			awardDetail.setAwardContent(award.getAwardContent());
			awardDetail.setAwardImage(award.getAwardImage());
			awardDetail.setAwardInfo(award.getAwardInfo());
			awardDetail.setAwardNumber(award.getAwardNumber());
			awardDetail.setAwardAddress(award.getAwardAddress());
			awardDetail.setAwardPhone(award.getAwardPhone());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			String start = sdf.format(award.getAwardStart());
			awardDetail.setAwardStart(start);
			String end = sdf.format(award.getAwardEnd());
			awardDetail.setAwardEnd(end);
			awardDetail.setAwardSecret(award.getAwardSecret());
			awardDetail.setAwardProvide(award.getAwardProvide());
			awardDetail.setAwardMap(award.getAwardMap());
			awardDetail.setAwardRate(award.getAwardRate());
			awardDetail.setAwardType(award.getAwardType());
			awardDetailList.add(awardDetail);
		}
		
		int motionNumber = userService.queryMotionNumberByUserId(userId);
		
		if(motionNumber==0){
			
		}else{
			motionNumber=1;
		}
		
		resultMap.put("userAward", awardDetailList);
		
		resultMap.put("motionNumber", motionNumber);
		
		resultMap.put("userAmount", userAmount);
		
		return JsonUtil.getSuccessJsonFromMap(resultMap, "success");
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
	
	@RequestMapping(value = "/getAwardById", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	public @ResponseBody String getAwardById(@RequestParam("awardId") String awardId,HttpServletRequest req,
			             Model model){
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Award award = awardService.getAwardById(Integer.parseInt(awardId));
		
		paramMap.put("id", award.getId());
		
		paramMap.put("awardImage", award.getAwardImage());
		paramMap.put("awardName", award.getAwardName());
		paramMap.put("awardContent", award.getAwardContent());
		paramMap.put("awardInfo", award.getAwardInfo());
		paramMap.put("awardNumber",award.getAwardNumber());
		paramMap.put("awardType", award.getAwardType());
		paramMap.put("awardAddress", award.getAwardAddress());
		paramMap.put("awardMap", award.getAwardMap());
		paramMap.put("awardPhone", award.getAwardPhone());
		String startDate = DateUtil.getChinaDate(award.getAwardStart());
		paramMap.put("awardStart", startDate);
		paramMap.put("startDate", award.getAwardStart());
		String endDate = DateUtil.getChinaDate(award.getAwardStart());
		paramMap.put("awardEnd", endDate);
		paramMap.put("endDate", award.getAwardEnd());
		paramMap.put("awardSecret", award.getAwardSecret());
		paramMap.put("awardProvide", award.getAwardProvide());
		paramMap.put("awardRate", award.getAwardRate());
		
		return JsonUtil.getSuccessJsonFromMap(paramMap, "success");
	}
	
	private double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}

	public double GetDistance(double lat1, double lng1, double lat2, double lng2)
	{
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);

	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
	    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	}
	
}
