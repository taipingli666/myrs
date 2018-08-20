package com.choice.sign.web.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.choice.domain.entity.user.ChannelUser;
import com.choice.domain.service.common.CommonMenuService;
import com.choice.domain.service.user.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private UserService userService;
	
	@Resource
	CommonMenuService commonMenuService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            
            //没有声明需要权限,或者声明不验证权限
            if(authPassport == null || authPassport.validate() == false){
            	 return true;
            }else{                
                //在这里实现自己的权限验证逻辑
            	String url = request.getRequestURI();
            	String[] subString =  url.split("/");
            	String subUrl  = url.substring(subString[1].length()+1);
            	Integer menuId = commonMenuService.getMenuIdFromURL(subUrl);
            	boolean flag = false;
            	if(menuId!=null){
            		ChannelUser user = (ChannelUser) request.getSession().getAttribute("loginUser");
            		if(user!=null){
            			List<Integer> roleList = userService.getRoleIdList(user.getUserId());
            			String[] strs = null;
            			String temp = "";
            			List<Map> list = null;
            			if(roleList.size()==1){
            				list = commonMenuService.getMenuForLogin(roleList.get(0), strs);
            			}else{
            				for (int i = 0; i < roleList.size(); i++) {
            					if(temp==""){
            						temp = String.valueOf(roleList.get(i));
            					}else{
            						temp +="," + String.valueOf(roleList.get(i));
            					}
            				}
            				list = commonMenuService.getMenuForLogin(roleList.get(0), temp.split(","));
            			}
            			for (int i = 0; i < list.size(); i++) {
        					if(menuId.equals(list.get(i).get("menuId"))){
        						flag=true;break;
        					}
        				}
            			
            		}
            	}
                if(flag)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                    return true;
                else {
                    //如果验证失败,返回到登录界面
                	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/login.do";
                    response.sendRedirect(basePath);
                    return false;
                }       
            }
        }
        else
            return true;   
     }
}
