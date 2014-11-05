package cn.gyyx.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserLoginFilter implements Filter{
	private String encoding;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String url = httpRequest.getRequestURI();
		//PrintWriter out = httpResponse.getWriter();
		System.out.println(url);
		if(!url.equals("/test/tologin")&&!url.equals("/test/toregesiter")
				&&!url.equals("/test/regesiter")&&!url.equals("/test/login")){
			Cookie cookie = getCookie(httpRequest,"id");
			if(cookie == null || cookie.getValue() == null || cookie.getValue().equals("")){
				//out.println("<script>alter('您还没有登录！')</script>");
				//out.println("window.location='/test/login'");
				httpResponse.sendRedirect("/test/tologin");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.encoding = "utf-8";
		
	}
	/*
	 * 获取指定的Cookie
	 * @param name cookie名
	 * 		  request request对象
	 * */
	 public static Cookie getCookie(HttpServletRequest request,String name){
		 Cookie cookie = null;
		 Cookie[] cookies = request.getCookies();
		 if(cookies != null){
			 for(Cookie tempCookie:request.getCookies()){
				 if(tempCookie.getName().equals(name)){
					 cookie = tempCookie;
					 break;
				 }
			 }
		 }
		 return cookie;
	 }
}
