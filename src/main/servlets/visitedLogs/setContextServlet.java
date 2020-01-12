package main.servlets.visitedLogs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setContextServlet")
public class setContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("访问到了setContextServlet");
//        request.setCharacterEncoding("utf-8");
//        synchronized (this){
//        ServletContext servletContext = request.getServletContext();
//        String ids = (String) servletContext.getAttribute("ids");
//        String id = request.getParameter("id");
//        servletContext.setAttribute("ids",ids+" "+id);
////        System.out.println(servletContext.getAttribute("ids"));
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
