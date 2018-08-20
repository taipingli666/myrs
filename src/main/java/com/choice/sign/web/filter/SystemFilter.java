
package com.choice.sign.web.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.choice.domain.entity.user.ChannelUser;



public class SystemFilter implements Filter {
	
	static String[] EXCEPTURL = new String[]{"hisLogin","login.do","checkUser.do","iphoneupimg.do"}; //
	
	public void init(FilterConfig arg0) throws ServletException {
		
		long daySpan = 24 * 60 * 60 * 1000;
		Date startTime;
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		String showId = "";
		String showPId = "";
		showId = request.getParameter("showId");
		showPId = request.getParameter("showPId");
		
		if(showId!=null && !showId.equals(""))
		{
			session.setAttribute("showId", showId);
			session.setAttribute("showPId", showPId);
		}
		
		if(checkUrl(req)){
			ChannelUser user = (ChannelUser) session.getAttribute("loginUser");
			
			if (user == null) {
				String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/login.do";
				resp.sendRedirect(basePath);
			} else
			{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
		System.out.println("------->url"+req.getRequestURI());
		
	}

	boolean checkUrl(HttpServletRequest req)
	{
		for(String obj:EXCEPTURL)
		{
			if(req.getRequestURI().indexOf(obj)>0)
			{
				return false;
			}
		}
		return true;
	}
}