package main.servlets.visitedTimes;

import main.domain.PageVisitedTimes;
import main.service.PageBeVisitedTimesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pageVisitedTimesServlet")
public class pageVisitedTimesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user==null){
            return;
        }
        PageBeVisitedTimesService service = new PageBeVisitedTimesService();
        List<PageVisitedTimes> records = service.findAll();
        req.setAttribute("records",records);
        req.getRequestDispatcher(req.getContextPath()+"/pageBeVisitedTimes.jsp").forward(req,resp);
    }
}
