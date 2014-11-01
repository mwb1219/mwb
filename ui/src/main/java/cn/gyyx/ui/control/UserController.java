package cn.gyyx.ui.control;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.gyyx.logic.beans.UserInfo;
import cn.gyyx.logic.beans.UserLogin;
import cn.gyyx.logic.service.UserService;
import cn.gyyx.ui.filter.UserLoginFilter;

/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：mawenbin
* 联系方式：mawenbin@gyyx.cn
* 创建时间： 2014年10月29日
* 版本号：v1.0
* 本类主要用途描述：
* 对用户信息进行服务器与客户端的交互控制
-------------------------------------------------------------------------*/
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	/**
	 * 转入登录页面
	 * @param model 数据绑定实体
	 * */
	@RequestMapping(value="/tologin",method=RequestMethod.GET)
	public String toLogin(Model model, @CookieValue(value="username",required=false)String username, HttpServletRequest request){
		//Cookie cookie = getCookie(request,"username");
		//System.out.println(request.getRequestURI());
		model.addAttribute("username",username);
		return "user/login";
	}
	/**
	 * 转入注册页面
	 * */
	@RequestMapping("/toregesiter")
	public String toRegesiter(){
		return "user/regesiter";
	}
	/**
	 * 完成注册功能
	 * @param model 数据绑定实例
	 * 		  userInfo 用户信息实例
	 * 		  userLogin 用户登录实例
	 * */
	@RequestMapping(value="/regesiter", method=RequestMethod.POST)
	public String regesiter(Model model,UserInfo userInfo,UserLogin userLogin){
		UserService.regesiter(userInfo, userLogin);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("userLogin", userLogin);
		return "user/showUser";
	}
	/**
	 * 完成登录功能
	 * @param model 数据绑定实例
	 * 		  response 传入的response对象
	 * 		  username 用户名
	 * 		  password 密码
	 * */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model,HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Cookie cookie = UserLoginFilter.getCookie(request, "id");
		//通过查询memCached中登录信息是否存在
		if(cookie != null && UserService.loginByMemCached(username,password,Integer.parseInt(cookie.getValue()))){
			//验证成功，将cookie的最长寿命复原
			cookie.setMaxAge(300);
			UserLoginFilter.getCookie(request, "username").setMaxAge(300);
			//记录日志
			UserService.writeLog(username, request.getRequestURL().toString(), "login");
			model.addAttribute("user", username);
			logger.info("memcached");
			
		}else{
			//通过查询数据库中登录信息是否存在
			UserLogin userLogin = UserService.loginByDB(username,password);
			if(userLogin == null){
				return "user/loginerror";
			}
			//验证成功将用户名跟主键code加入cookie中
			Cookie idCookie = new Cookie("id", String.valueOf(userLogin.getCode()));
			Cookie unameCookie = new Cookie("username", userLogin.getUsername());
			idCookie.setMaxAge(300);
			unameCookie.setMaxAge(300);
			response.addCookie(idCookie);
			response.addCookie(unameCookie);
			//记录日志
			UserService.writeLog(username, request.getRequestURL().toString(), "login");
			model.addAttribute("user", username);
		}
		return "user/loginsuccess";
	}
	/*
	 * 获取指定的Cookie
	 * @param name cookie名
	 * 		  request request对象
	 * */
	/* public Cookie getCookie(HttpServletRequest request,String name){
		 Cookie cookie = null;
		 if(request.getCookies().length != 0){
			 for(Cookie tempCookie:request.getCookies()){
				 if(tempCookie.getName().equals(name)){
					 cookie = tempCookie;
					 break;
				 }
			 }
		 }
		 return cookie;
	 }*/
}
