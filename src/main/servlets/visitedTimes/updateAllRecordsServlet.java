package main.servlets.visitedTimes;

import main.service.PageBeVisitedTimesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateAllRecordsServlet")
public class updateAllRecordsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        if(user!="young"){
            return;
        }
        PageBeVisitedTimesService service = new PageBeVisitedTimesService();
        service.updatePageBeVisitedTimes();
        request.getRequestDispatcher(request.getContextPath()+"/pageVisitedTimesServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
