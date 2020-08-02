package com.mm.poc.multitenant.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TenantRequestInterceptor extends HandlerInterceptorAdapter {
  private static final Logger logger = LoggerFactory.getLogger(TenantRequestInterceptor.class);
  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object object) throws Exception {
    logger.debug("In preHandle we are Intercepting the Request");
    String requestURI = request.getRequestURI();
    String tenantID = request.getHeader("X-TenantID");
    logger.debug("RequestURI::" + requestURI +" || Search for X-TenantID  :: " + tenantID);
    if (tenantID == null) {
      response.getWriter().write("X-TenantID not present in the Request Header");
      response.setStatus(400);
      return false;
    }
    TenantContext.setCurrentTenant(tenantID);
    return true;
  }
  
  @Override
  public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
      throws Exception {
    TenantContext.clear();
  }
  
}