<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>网页被访问次数记录</title>
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/pageStyle.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!--  Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function updateRecord() {
            <%--if (${user!="young"}) {--%>
            <%--    alert("请以管理员身份登录后即可使用该功能 :)")--%>
            <%--    return--%>
            <%--}--%>
            location.href = "${pageContext.request.contextPath}/updateAllRecordsServlet"
            alert("更新已完成 :)")
        }
        function logout() {
            if(confirm("确定要退出登录吗？")){
                location.href="${pageContext.request.contextPath}/logoutServlet"
            }
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs success" style="opacity: 0.8;background-color: gainsboro;">
    <li>
        <div style="float:left;margin-left: 30px;height: 40px;padding-top: 6px">
            <img src="img/用户头像.png" style="width: 25px;height: 25px"/>
        </div>
        <div class="${colorClass}"
             style="padding-top: 6px;margin-left: 60px;height:40px;font-size: 17px;font-weight: bolder">${username}</div>
    </li>
    <li class="text-primary" style="float: left;margin-left:38%;margin-top: 4px;font-size:21px;font-weight: bolder;font-family:  黑体, Arial, Verdana, arial, serif">
        网页被访问次数统计
    </li>
<%--    <c:if test="${user!='young'}">--%>
<%--        <li style="float: right;margin-right:10px;margin-top: 0px;">--%>
<%--            <a style="font-weight: bolder" class="  btn btn-primary text-success " href="${pageContext.request.contextPath}/login.jsp">--%>
<%--                管理员登录--%>
<%--            </a>--%>
<%--        </li>--%>
<%--    </c:if>--%>
<%--    <c:if test="${user=='young'}">--%>
<%--        <li style="float: right;margin-right:10px;margin-top: 0px;">--%>
<%--            <a style="font-weight: bolder" class="  btn btn-primary text-success " href="javascript:logout()">--%>
<%--                退出登录--%>
<%--            </a>--%>
<%--        </li>--%>
<%--    </c:if>--%>
</ul>
<%--<div  style="color:white;width: 100% ;margin-top: 30px;text-align: center;font-size:26px;font-weight: bolder">--%>
<%--    <span >网页被访问次数记录</span>--%>
<%--</div>--%>
<div style="float: left;margin-left: 15%;margin-top: 70px;margin-bottom: 5px">
    <a  class="btn btn-primary text-info " href="${pageContext.request.contextPath}/manageIndex.jsp">&laquo; 返回菜单</a>
</div>
<c:if test="${user=='young'}">
    <div style="float: right;margin-right: 15%;margin-top: 70px;margin-bottom: 5px">
        <a class="btn btn-primary text-info "
           href="javascript:updateRecord()">更新所有记录</a>
    </div>

</c:if>
<table border="1px" class="table table-bordered table-hover"
       style="clear: both;width: 70%;margin-left: 15%;">
    <tr style="background-color: rgb(66,139,202);color: white">
        <th style="width: 15%">页面ID</th>
        <th style="width: 65%">页面名称</th>
        <th style="width: 20%">被访问次数</th>
    </tr>
    </tr>
    <c:set var="i" value="0"></c:set>
    <c:forEach items="${records}" var="record" varStatus="s">
        <c:if test="${i%2==0}">
            <tr class="bg-success">
                <td class="text-primary">${record.id}</td>
                <td class="text-success">${record.name}</td>
                <td class="text-danger">${record.beVisitedTimes}</td>
            </tr>
        </c:if>
        <c:if test="${i%2==1}">
            <tr class="bg-warning">
                <td class="text-primary">${record.id}</td>
                <td class="text-success">${record.name}</td>
                <td class="text-danger">${record.beVisitedTimes}</td>
            </tr>
        </c:if>
        <c:set var="i" value="${i+1}"></c:set>
    </c:forEach>
    </tr>
</table>

</body>
</html>
