package com.gethobby.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gethobby.service.domain.User;

public class LogonCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("\n[ LogonCheckInterceptor start........] \n");
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		
		/*
		if ( user == null ) {
			
			if ( user == null ) {
				if ( uri.indexOf("noLogonUser") != -1 && queryString.split("=")[1].equals("steam") ) {
					ModelAndView mav = new ModelAndView("/user/captcha");
					mav.addObject("redirectUrl", "http://127.0.0.1:8080/searchhobbyclass/getSearchHobbyClassList.jsp");
					
					throw new ModelAndViewDefiningException(mav);
				}
				else if ( uri.indexOf("noLogonUser") != -1 && queryString.split("&")[0].split("=")[1].equals("intro") ) {
					ModelAndView mav = new ModelAndView("/user/captcha");
					mav.addObject("redirectUrl", "http://127.0.0.1:8080/searchHobbyClass/getSearchHobbyClassIntro?" + queryString.split("&")[1]);
					
					throw new ModelAndViewDefiningException(mav);
				}
				else if ( uri.indexOf("noLogonUser") != -1 && queryString.split("&")[0].split("=")[1].equals("purchase") ) {
					ModelAndView mav = new ModelAndView("/user/captcha");
					mav.addObject("redirectUrl", "http://127.0.0.1:8080/searchHobbyClass/getSearchHobbyClassIntro?" + queryString.split("&")[1]);
					
					throw new ModelAndViewDefiningException(mav);
				}
				else if ( uri.indexOf("noLogonUser") != -1 && queryString.split("&")[0].split("=")[1].equals("steamHobbyClass")) {
					ModelAndView mav = new ModelAndView("/user/captcha");
					mav.addObject("redirectUrl", "http://127.0.0.1:8080/searchHobbyClass/getSearchHobbyClassIntro?" + queryString.split("&")[1]);
					
					throw new ModelAndViewDefiningException(mav);
				}
			}
			
		}
		*/
		
		// 로그인 안한 경우 
		if ( user == null ) {
			// 로그인 jsp로 이동시키 위한 ModelAndView => ModelAndViewDefiningException으로 다시 forward / redirect 가능, 단 ajax는 안됨 
			ModelAndView mav = new ModelAndView("/user/captcha");
			
			// 로그인하지 않은 회원일 경우 noLogonUser uri로 이동
			if ( uri.indexOf("noLogonUser") != 1 ) {
				
				if ( queryString.split("=")[1].equals("steam") ) {
					// 클래스 검색 jsp에서 찜하기를 할 경우 
					mav.addObject("redirectUrl", "http://127.0.0.1:8080/searchhobbyclass/getSearchHobbyClassList.jsp");
					
					throw new ModelAndViewDefiningException(mav);
				}
				else if ( queryString.split("&")[0].split("=")[1].equals("intro") ) {
					// 클래스 상세보기에서 뭔가 로그인 필요한 기능을 했을 경우 
					mav.addObject("redirectUrl", "http://127.0.0.1:8080/searchHobbyClass/getSearchHobbyClassIntro?" + queryString.split("&")[1]);
					
					throw new ModelAndViewDefiningException(mav);
				}
			}
		}
		return true;
	}
	
	
	
}
