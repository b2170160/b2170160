package com.example.labo_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(1)
public class SignBarrierFilter implements Filter {
    @Autowired
    private HttpSession httpSession;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
        var httpServletRequest=(HttpServletRequest)servletRequest;
        var path=httpServletRequest.getServletPath();
        var method=httpServletRequest.getMethod();

        System.out.println("パス:"+path+",HTTP命令:"+method);
        var userId=httpSession.getAttribute("userId");
        System.out.println(httpSession.getId()+"のuserId:"+userId);

        if(!path.endsWith("toukouuu")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        else if("/toukou".equals(path)&&"POST".equals(method)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(Objects.isNull(userId)){
           var dispatcher=servletRequest.getRequestDispatcher("SignIn");
           dispatcher.forward(servletRequest,servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}