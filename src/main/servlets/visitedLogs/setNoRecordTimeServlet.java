package main.servlets.visitedLogs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/setNoRecordTimeServlet")
public class setNoRecordTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int hours = Integer.parseInt(request.getParameter("hours"));
        HttpSession session = request.getSession();
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(hours*60*60);
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
