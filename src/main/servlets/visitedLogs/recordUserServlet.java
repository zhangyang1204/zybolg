package main.servlets.visitedLogs;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashSet;

@WebServlet("/recordUserServlet")
public class recordUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        用户离开网页时访问该servlet并发送用户的访问信息
//        System.out.println("访问到了recorduserservlet");
        try {
//            等待几秒，若在这段时间内用户再次访问了网页，则前端会做出识别(基于cookie)并发送该用户id给setContextServlet,
//            再由setContextservlet在ServletContext域中添加该用户的id
//            最后通过比对servletContext域中是否包含该servlet收到的id就可判断出用户是否刷新了页面
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        设置字符集
        request.setCharacterEncoding("utf-8");

//        获取请求的json中的参数id,用于判断用户是否刷新了页面
        String id = request.getParameter("id");
//        获取ServletContext中的ids,用于判断其是否包含id
        String ids =null;
        ServletContext servletContext =request.getServletContext();;
//        删除servletContext数据(初始化操作)
//            servletContext.removeAttribute("ids");
//            ids = (String) servletContext.getAttribute("ids");
//           System.out.println(ids);
//        flag判断是否应该将数据提交后台
        boolean flag;
//        ids为空，用户没有刷新页面(直接关闭），则为退出了，应该记录到数据库
        if (ids == null) {
            flag = false;}else{
            //ids不为空，则有用户刷新了页面，根据判断ids是否包含该id来确定该用户是否刷新了页面
         flag = ids.contains(id);
        }
        if (flag) {
//            用户刷新了页面

//            删除字符串ids中包含的id，否则会影响该用户退出时数据的提交
//            仅删除ids中包含的一个id，防止用户快速刷新时出现问题
            synchronized (this){
                int i = ids.indexOf(id);
                ids = ids.substring(0, i) + ids.substring(i + id.length());
//            覆盖原来的ids
                servletContext.setAttribute("ids", ids);
            }
        } else {
//              获取客户端ip
            String ipAddr = getIpAddr(request);
            String visitTime = request.getParameter("visitTime");
            String second = request.getParameter("second");
            HashSet PageIDs = (HashSet) servletContext.getAttribute(ipAddr);
            synchronized (this){
                servletContext.removeAttribute(ipAddr);
            }
            System.out.println(ipAddr+" "+visitTime+" "+second+" "+PageIDs);
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
