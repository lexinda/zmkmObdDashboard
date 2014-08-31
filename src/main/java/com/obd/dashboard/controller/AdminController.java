package com.obd.dashboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.obd.model.Admin;
import com.obd.service.AdminService;
import com.obd.util.CommonUtil;

/**
 * AdminController 提供针对系统管理员增、删、改、查，登录、退出等操作
 * 
 * @version 1.0 at 2013/07/10
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	// local variables
	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(AdminController.class);
	/**
	 * service
	 */
	@Autowired
	private AdminService service;
	
	// Logic
	/**
	 * 根据id查询系统管理员信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody String get(@PathVariable("id") int id, Model model) throws Exception {
		logger.debug("Begin: get(" + id + ")...");
		Admin result = this.service.get(id);
		model.addAttribute("admin", result);
		logger.debug("End: result = " + result);
		return result.toString();
	}

	/**
	 * 保存系统管理员信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody int save(@RequestParam("name") String name, @RequestParam("account") String account,
			@RequestParam("status") int status) throws Exception {
		logger.debug("Begin: get(" + name + ", " + account + ", " + status + ")...");
		Admin admin = new Admin();
		admin.setAccount(account);
		admin.setName(name);
		admin.setStatus(status);
		int result = this.service.save(admin);
		return result;
	}
	/**
	 * 跳转重设密码页面 
	 * @return
	 */
	@RequestMapping(value = "/goset", method = RequestMethod.GET)
	public String goSet() {
		logger.debug("Begin: login()...");
		String result = "/set";
        logger.debug("End: result = " + result);
		return result;
	}
	/**
	 * 管理员登陆
	 * 
	 * @param adminId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("adminId")int adminId,
			                          @RequestParam("password") String password,
			                          HttpServletRequest req,
			                          Model model) {
		logger.debug("Begin: login(" +adminId+","+ password + ")...");
		String pw =CommonUtil.md5(password);
		Admin a = new Admin();
		a.setId(adminId);
		a.setPassword(pw);
		Admin admin = service.login(a);
		if(admin!=null){
			HttpSession session = req.getSession();
			session.setAttribute("admin", admin);
			session.setAttribute("adminName", admin.getName());
			session.setMaxInactiveInterval(60*60*1);
			model.addAttribute("admin",admin);
			return "/main";
		}else{
			//登陆失败返回错误信息
			model.addAttribute("message", "用户名或密码不正确请重新输入");
			return "/login";
		}
    }
	
	/**
	 * 修改管理员密码
	 * 
	 * @param adminId
	 * @param newPassword
	 * @param oldPassword
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public  String changePassword(@RequestParam("adminId")int adminId,
			                                   @RequestParam("newPassword") String newPassword,
			                                   @RequestParam("oldPassword") String oldPassword,
			                                   Model model) {
		logger.debug("Begin: changePassword(" +adminId+","+ newPassword +","+oldPassword+ ")...");
		String result = "/set";
		String newpw =CommonUtil.md5(newPassword);
		String old = CommonUtil.md5(oldPassword);
		Admin admin = new Admin();
		admin.setId(adminId);
		admin.setPassword(old);
		Admin r = service.login(admin);
		if(r!=null){
			Admin a = new Admin();
			a.setId(adminId);
			a.setAccount(r.getAccount());
			a.setStatus(r.getStatus());
			a.setName(r.getName());
			a.setPassword(newpw);
			int i = service.save(a);
		}else{
			model.addAttribute("message", "修改密码失败！");
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req,
			             Model model){
		req.getSession().invalidate();
		model.addAttribute("message", "");
		return "/login";
	
		
	}
	
	@RequestMapping(value = "/BackEndUrl", method = RequestMethod.POST)
	public @ResponseBody String backEndUrl(HttpServletRequest req,Model model,
			@RequestParam(value="orderTime",required=false) String orderTime,
			@RequestParam(value="settleDate",required=false) String settleDate,
			@RequestParam(value="orderNumber",required=false) String orderNumber,
			@RequestParam(value="exchangeRate",required=false) String exchangeRate,
			@RequestParam(value="signature",required=false) String signature,
			@RequestParam(value="settleCurrency",required=false) String settleCurrency,
			@RequestParam(value="signMethod",required=false) String signMethod,
			@RequestParam(value="transType",required=false) String transType,
			@RequestParam(value="respCode",required=false) String respCode,
			@RequestParam(value="charset",required=false) String charset,
			@RequestParam(value="sysReserved",required=false) String sysReserved,
			@RequestParam(value="version",required=false) String version,
			@RequestParam(value="settleAmount",required=false) String settleAmount,
			@RequestParam(value="transStatus",required=false) String transStatus,
			@RequestParam(value="reqReserved",required=false) String reqReserved,
			@RequestParam(value="merId",required=false) String merId,
			@RequestParam(value="qn",required=false) String qn,
			@RequestParam(value="acqCode",required=false) String acqCode,
			@RequestParam(value="traceNumber",required=false) String traceNumber,
			@RequestParam(value="traceTime",required=false) String traceTime
			) throws Exception {
		System.out.println("post orderTime="+orderTime+
			"&settleDate="+settleDate+
			"&orderNumber="+orderNumber+
			"&exchangeRate="+exchangeRate+
			"&signature="+signature+
			"&settleCurrency="+settleCurrency+
			"&signMethod="+signMethod+
			"&transType="+transType+
			"&respCode="+respCode+
			"&charset="+charset+
			"&sysReserved={acqCode="+acqCode+"&traceNumber="+traceNumber+"&traceTime="+traceTime+"}"+
			"&version="+version+
			"&settleAmount="+settleAmount+
			"&transStatus="+transStatus+
			"&reqReserved="+reqReserved+
			"&merId="+merId+
			"&qn="+qn);
		System.out.println("post sysReserved"+sysReserved);
		return "success";
	}
	@RequestMapping(value = "/BackEndUrl", method = RequestMethod.GET)
	public @ResponseBody String getBackEndUrl(HttpServletRequest req,Model model) throws Exception {
		String servletPath = req.getServletPath()+req.getQueryString();
		System.out.println("get----------:"+servletPath);
		return "success";
	}
	@RequestMapping(value = "/FrontEndUrl", method = RequestMethod.POST)
	public @ResponseBody String FrontEndUrl(HttpServletRequest req,Model model) throws Exception {
		String servletPath = req.getServletPath()+req.getQueryString();
		System.out.println("post----------:"+servletPath);
		return "success";
	}
	@RequestMapping(value = "/FrontEndUrl", method = RequestMethod.GET)
	public @ResponseBody String getFrontEndUrl(HttpServletRequest req,Model model) throws Exception {
		String servletPath = req.getServletPath()+req.getQueryString();
		System.out.println("get----------:"+servletPath);
		return "success";
	}
}
