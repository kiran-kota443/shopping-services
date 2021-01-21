/**
 * 
 */
package com.shopping.kart.interceptor;

import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Kiran Kumar Kota
 *
 * Jan 21, 2021 11:53:18 AM
 *
 * TODO
 *
 * 
 */
@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		   
		long startTime = System.currentTimeMillis();
		long transactionId = ThreadLocalRandom.current().nextLong(100000000000L, 10000000000000L);

		request.setAttribute("startTime", startTime);
		request.setAttribute("transactionId", transactionId);

		logger.info("Transaction " + transactionId + " started for " + request.getRequestURI());
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		long startTime = (Long) request.getAttribute("startTime");
		long transactionId = (Long) request.getAttribute("transactionId");
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;

		logger.info("Transaction " + transactionId + " executed for " + request.getRequestURI() + " in " + executionTime
				+ " ms");
	}
}