package com.songbo.dicshop.filter;

import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.service.DsUserService;
import com.songbo.dicshop.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName MyFilter
 * @Description TODO
 * @Author songbo
 * @Date 19-10-19 下午4:22
 **/
//@WebFilter(urlPatterns = "/admin/*")
@Slf4j
public class MyFilter extends HttpFilter {

    @Autowired
    private DsUserService dsUserService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException(request + " not HttpServletRequest");
        } else if (!(response instanceof HttpServletResponse)) {
            throw new ServletException(request + " not HttpServletResponse");
        } else {
            this.doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
        }
    }
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.warn("filter");

        String token = request.getHeader("ACCESS_TOKEN");
        String userId = request.getHeader("ACCESS_USERID");
        log.warn(token);
        if (token == null || userId == null){
            response.sendError(404);
            log.error("filter: token is null");
            return;
        }
        Map map = JwtUtil.validateToken(token);
        int id = Integer.parseInt(userId);
        if ((int)map.get("code")==200 ){
            DsUser user = dsUserService.getUserByName((String) map.get("name"));
            /*String username = user.getDsUserName();
            if ( username.equals(map.get("username"))){
                log.warn("filter into");
                chain.doFilter(request, response);
            } else {
                response.sendError(404);
                log.error("filter: token is fake");
                return;
            }*/
            if (user != null && user.getDsUserId() == id) {
                log.warn("filter into: {}", userId);
                chain.doFilter(request, response);
            } else {
                response.sendError(404);
                log.error("filter: token is fake");
                return;
            }
        } else {
            response.sendError(404);
            log.error("filter: token is err");
            return;
        }




        //chain.doFilter(request, response);

    }


}
