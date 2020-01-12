package main.servlets.visitedLogs;

import main.service.FindUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String verifycode = request.getParameter("verifycode");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if (!verifycode.equalsIgnoreCase(checkcode_server)) {
            request.setAttribute("login_msg", "验证码输入错误！请重试");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        FindUserService findUserService = new FindUserService();
        String identity = findUserService.findUser(username,password);
        if(identity!=null&&identity!=""){
            HttpSession session = request.getSession();
            session.setAttribute("user",identity);
            response.sendRedirect(request.getContextPath()+"/manageIndex.jsp");
            session.setAttribute("login","1");
        }
        else{
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
