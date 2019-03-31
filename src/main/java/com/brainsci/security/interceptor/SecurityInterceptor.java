package com.brainsci.security.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //在请求处理之前进行调用（Controller方法调用之前)
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        @SuppressWarnings("unchecked")
        String url = httpServletRequest.getServletPath();
        if (httpServletRequest.getSession().getAttribute("username") == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "当前用户未登陆");
            return false;
        }
        return true;
        //根据url，从数据库中获取当前资对应的角色列表
//        List<RoleEntity> resourceRoles = roleService.roleOfResource(url);
//        for (RoleEntity i :userRoles) {
//            for (RoleEntity j : resourceRoles) {
//                if (i.getId().equals(j.getId())) {
//                    return true;
//                }
//            }
//        }
//        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "权限不足，无法访问");
//        System.out.println(url + "被拦截");
//        return false;    //如果false，停止流程，api被拦截
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
//        logger.trace("{} {} {} {} user:{}",
//                httpServletRequest.getHeader("X-Real-IP"),
//                httpServletRequest.getMethod(),
//                httpServletRequest.getRequestURI(),
//                httpServletResponse.getStatus(),
//                httpServletRequest.getSession().getAttribute("username"));
    }
}
