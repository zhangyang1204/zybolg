package main.servlets.visitedLogs;

import main.service.VisitLogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectLogServlet")
public class delSelectLogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String referer = request.getHeader("referer");
        if(referer==null){
           /* request.setAttribute("login_msg","非法访问！请先验证身份");
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
            */
            response.sendRedirect(request.getContextPath()+"/index.html?msg='0'");
           return;
        }
        if(!referer.startsWith("http://localhost/findLogByPageServlet")&&!referer.startsWith("http://zybolg.xyz/findLogByPageServlet")&&
                !referer.startsWith("http://inlightcone.cn/findLogByPageServlet")&&!referer.startsWith("http://光锥之内.com/findLogByPageServlet")){
            /*request.setAttribute("login_msg","非法访问！请先验证身份");
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);*/
            response.sendRedirect(request.getContextPath()+"/index.html?msg='0'");
            return;
        }
        String[] lids = request.getParameterValues("lid");
        VisitLogService service = new VisitLogService();
        service.delSelectLog(lids);
        response.sendRedirect(request.getContextPath()+"findLogByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
