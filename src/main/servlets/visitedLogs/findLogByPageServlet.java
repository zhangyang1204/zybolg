package main.servlets.visitedLogs;

import main.domain.PageBean;
import main.domain.VisitLog;
import main.service.VisitLogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findLogByPageServlet")
public class findLogByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        PageBean<VisitLog> pb = new VisitLogService().findLogBypage(currentPage);
        request.setAttribute("pb",pb);
        request.setAttribute("logs",pb.getList());
        request.getRequestDispatcher(request.getContextPath()+"/visitLog.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
