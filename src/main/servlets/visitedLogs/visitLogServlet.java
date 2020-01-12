package main.servlets.visitedLogs;

import main.domain.VisitLog;
import main.service.VisitLogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/visitLogServlet")
public class visitLogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user==null){
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
        }
        List<VisitLog> logs = new VisitLogService().findAllLogs();
        request.setAttribute("logs",logs);
        request.getRequestDispatcher(request.getContextPath()+"/visitLog.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
