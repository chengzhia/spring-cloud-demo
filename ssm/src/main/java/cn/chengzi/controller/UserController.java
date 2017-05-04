package cn.chengzi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.chengzi.entity.User;
import cn.chengzi.service.UserService;
@RequestMapping("user")
@Controller
public class UserController {
	@Resource
	private UserService userService;
	
	
	/**
	 * 	shiro进行登录
	 * <P>UserController.Login()<P>;
	 * <P>Author :  ruanchengzhi </P>  
	 * <P>Date : 2016年7月18日 </P>
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("login")
	public String Login(User user,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		//使用UsernamePasswordToken封装登录用户的用户名和密码
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
		try {
			token.setRememberMe(true);	//使用shiro来验证
			subject.login(token);		//进行用户的登录和授权
			User use = (User) subject.getPrincipal();	
			System.out.println(use.getName());
			System.out.println(use.getPassword());
			request.getSession().setAttribute("user",use);
			return "redirect:/index.jsp";
		} catch (AuthenticationException e) {
			System.out.println("用户名或者密码错误，请重新登录");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * SSO
	 * <P>UserController.LoginSSO()<P>;
	 * <P>Author :  ruanchengzhi </P>  
	 * <P>Date : 2016年7月18日 </P>
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("LoginSSO")
	public String LoginSSO(User user,HttpServletRequest request,HttpServletResponse response){
		
		String token = this.userService.Login(user);
		if (token!=null) {
			request.getSession().setAttribute("user",user);
		}
		Cookie cookie = new Cookie("token",token);
		cookie.setMaxAge(3600);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/index.jsp";
	}

	@RequestMapping("queryToken")
	public String queryToken(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				String token = cookie.getValue();
				if (token==null) {
					return "redirect:login.jsp";
				}
				User user = this.userService.queryToken(token);
				if (user==null) {
					return "redirect:login.jsp";
				}
				request.getSession().setAttribute("user",user);
				return "redirect:index.jsp";
			}
		}
		return null;
	}
	@RequestMapping("jsonp")
	@ResponseBody
	public JSONPObject returnJsonp(String callback){
		JSONObject value = new JSONObject();
		List<String> list = new ArrayList<String>();
		list.add("qq");  list.add("ww");
		list.add("ee");  list.add("rr");
		value.put("list",list);
		JSONPObject object = new JSONPObject(callback, value);
		return object;
	}
	@RequestMapping("path")
	public void Test(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write("");
		response.getWriter().flush();
		response.getWriter().close();
	}
	@RequestMapping("tuturnJsp")
	public String returnJsp(){
		
		return "qq";
	}
}
