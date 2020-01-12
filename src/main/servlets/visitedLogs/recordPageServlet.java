package main.servlets.visitedLogs;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@WebServlet("/recordPageServlet")
public class recordPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("访问到了recordPageServlet");
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        System.out.println(id);
        String ip = getIpAddr(request);
        ServletContext servletContext = request.getServletContext();
        HashSet pageIDs = (HashSet) servletContext.getAttribute(ip);
        if(pageIDs==null) pageIDs = new HashSet();
            pageIDs.add(id);
        System.out.println(pageIDs);
        servletContext.setAttribute(ip,pageIDs);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(" x-forwarded-for ");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;

    }
}
