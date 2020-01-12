package main.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/manageIndex.jsp")
public class managePageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        Object user = request.getSession().getAttribute("user");
        if(user!=null){
//          用户已登陆过
            chain.doFilter(req,resp);
        }else{
            req.setAttribute("login_msg","您还未登录，请先登录！");
            req.getRequestDispatcher("/login.jsp").forward(request,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
