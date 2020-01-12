package main.servlets.visitedLogs;

import main.domain.VisitLog;
import main.service.PageBeVisitedTimesService;
import main.service.VisitLogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recordVisitLogServlet")
public class recordVisitLogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        if(request.getSession().getAttribute("user")!=null){
//            return;
//        }
        String flag = request.getParameter("flag");
        String visitTime = request.getParameter("visitTime");
        String pageIDs = request.getParameter("pageIDs");
        String ipAddr = getIpAddr(request);
        VisitLog log = new VisitLog();
        log.setIp(ipAddr);
        log.setPageIDs(pageIDs);
        log.setVisitTime(visitTime);
        VisitLogService service = new VisitLogService();
        PageBeVisitedTimesService pbvt_Service = new PageBeVisitedTimesService();
        if(flag.equals("0")){
//            System.out.println("首次访问,执行插入数据库操作");
            service.addVisitLog(log);
            pbvt_Service.addOneByLog(pageIDs);
        }else {
//            System.out.println("用户未退出，执行更新数据操作");
            service.updateVisitLog(log);
            String newPageID=request.getParameter("newPageID");
            pbvt_Service.addOneByLog(newPageID);
        }
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
