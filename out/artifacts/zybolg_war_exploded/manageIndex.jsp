<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>后台管理页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1 ">
    <meta charset="UTF-8">
    <meta name="keywords" content="技术博客,前端语言,后端语言,编程语言笔记,编程学习随笔,计算机,网络,知识分享,Young的博客小站,inlightcone.cn,光锥之内"/>
    <meta name="description" content="一个记录了个人学习总结心得的博客网站"/>
    <title>Young的博客小站</title>
    <!--BootStrap的 css文件-->
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/pageStyle.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!--jQuery 的 cookie插件-->
    <script>
        window.onload = function () {
            if (${login=='1'&&user=="young"} )
            {
                <%
                    pageContext.getSession().removeAttribute("login");
                    %>
                alert("登录成功！欢迎您，管理员 :）")
            }
            else if (${login=='1'&&user!='young'})
            {
                <%
                    pageContext.getSession().removeAttribute("login");
                    %>
                alert("登录成功！欢迎您，游客 :）")
            }

        }

        function showVisitLog() {
            location.href = "${pageContext.request.contextPath}/findLogByPageServlet";
        }

        function showVisitTimes() {
            location.href = "${pageContext.request.contextPath}/pageVisitedTimesServlet";
        }

        function noRecord() {
            var hours = $("#select1").val();
            if(hours==-1)
                return
            $.post("setNoRecordTimeServlet", {"hours": hours})
            alert("设置成功");
        }

        function getUrlParam(name) {
            //构造一个含有目标参数的正则表达式对象
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            //匹配目标参数
            var r = window.location.search.substr(1).match(reg);
            //返回参数值
            if (r != null) {
                return decodeURI(r[2]);
            }
            return null;
        }
        function logout() {
            if(confirm("确定要退出登录吗？")){
                location.href="${pageContext.request.contextPath}/logoutServlet"
            }

        }
    </script>
    <script src="js/jquery.cookie.js"></script>
    <!--  Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
<%
    String name= (String) request.getSession().getAttribute("user");
    if (name == "young") {
        request.getSession().setAttribute("username", "管理员_young");
        request.getSession().setAttribute("colorClass","text-success");

    } else {
        request.getSession().setAttribute("username", "游客_"+name);
        request.getSession().setAttribute("colorClass","text-danger");
    }
%>
<ul class="nav nav-tabs ">
    <li>
        <a href="${pageContext.servletContext.contextPath}/index.html">网站首页</a>
    </li>
</ul>

<div style="width: 100%;height: 100%;padding-top:70px">
    <div class="container " style="width: 550px;height: 550px;padding-top: 30px;background-color: white;">
<%--        <div class="main">--%>
<%--        <div style="float: left;width: 100%;height: 40px;background-color: white;opacity: 0.7;"></div>--%>
        <div style="width: 100% ;height: 40px">
            <div style="font-family: 黑体, Arial, Verdana, arial;float: left;width:160px;height:100%;padding-top: 5px;font-size: 20px;font-weight: bolder"
                 class="text-success">
                <div style="float:left;margin-left: 10px;height: 40px;padding-top: 0px"><img src="img/用户头像.png"
                                                                                             style="width: 25px;height: 23px"/>
                </div>
                <div class="${colorClass}" style="margin-left: 40px;height:40px;font-size: 17px;">${username}</div>
            </div>
<%--            <div class="text-success" style="float:left;width:35%;height: 40px;margin-left:50px;font-size:23px;font-weight: bolder;font-family: 黑体, Arial, Verdana, arial">
                后台管理
                </div>--%>
<%--            <c:if test="${user!='young'}">--%>
<%--                <div style="float: right;margin-right:10px;margin-top: 0px;margin-bottom: 15px">--%>
<%--                    <a class=" btn-sm btn btn-info text-success "--%>
<%--                       href="${pageContext.request.contextPath}/login.jsp">管理员登录</a>--%>
<%--                </div>--%>
<%--            </c:if>--%>
<%--            <c:if test="${user=='young'}">--%>
                <div style="float: right;margin-right:10px;margin-top: 0px;margin-bottom: 10px">
                    <a class=" btn-sm btn btn-warning text-success "
                       href="javascript:logout()">退出登录</a>
                </div>
<%--            </c:if>--%>
        </div>
    <hr/>
<%--        <div style="width: 100%;height: 1px;border:solid 1px white;margin-top: 15px"></div>--%>
    <div style="font-size:17px;float:left;margin-left: 60px;font-weight: bolder;">查看记录:</div>
    <div style="clear:both;text-align: center;margin-top: 65px;">

            <button type="button"
                    style="width:180px;font-weight: bold;letter-spacing: 1.1px;font-family: 宋体, 黑体, Arial, Verdana, arial;"
                    class="btn btn-success" onclick="showVisitLog()">访客记录
            </button>
            <br/>
        </div>
        <div style="text-align: center;padding-top: 20px;">
            <button type="button"
                    style="font-weight: bold;letter-spacing: 1.1px;font-family: 宋体, 黑体, Arial, Verdana, arial;"
                    class="btn btn-success" onclick="showVisitTimes()">各网页被访问次数统计
            </button>
            <br/>
        </div>
        <c:set var="f_display" value="block"></c:set>
        <c:if test="${user!='young'}">
            <c:set var="f_display" value="none"></c:set>
        </c:if>
        <div style="text-align: center;display:${f_display};padding-top: 50px;font-size: 17px;">
            <div style="float:left;margin-left: 60px;font-weight: bolder;padding-bottom: 30px">管理员选项:</div>
            <div style="font-size: 16px;clear: both" class="">将此设备加入白名单并设定时长:
            <select id="select1" style="width:120px;margin-left: 0px;font-family: 宋体, 黑体, Arial, Verdana, arial;">
                <option value="-1">--请选择--</option>
                <option value="0">从白名单移除</option>
                <option value="1">1小时</option>
                <option value="3">3小时</option>
                <option value="6">6小时</option>
                <%--                <option value="30">一个月</option>--%>
                <%--                <option value="360">一年</option>--%>
            </select></div><br/>
            <button style="width: 180px;" type="button" class="btn btn-success" onclick="noRecord()">设置</button>
        </div>
<%--    </div>--%>
    </div>
</div>

</body>
</html>
