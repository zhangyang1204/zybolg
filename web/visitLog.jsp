<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>网站访问日志</title>
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/pageStyle.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!--jQuery 的 cookie插件-->
    <script src="js/jquery.cookie.js"></script>
    <!--  Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#firstCb").click(function () {
                var cbs = $("input[name='lid']");
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            })
            $("#delSelectLog").click(function () {
                if (${user!='young'}) {
                    alert('抱歉，只有管理员才能使用删除功能 :)')
                    return
                }
                if (confirm("确定要删除选中的条目吗？")) {
                    var cbs = document.getElementsByName("lid");
                    var flag = false;
                    //判断选择条目是否为空
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            flag = true;
                            break;
                        }
                    }
                    //选择条目非空，提交表单
                    if (flag) {
                        document.getElementById("lform").submit();
                    }
                }
            })
        })

        function deleteLog(lid) {
            if (${user!='young'}) {
                alert('抱歉，只有管理员才能使用删除功能 :)')
                return
            }
            location.href = "${pageContext.request.contextPath}/deleteLogServlet?lid=" + lid;
        }
        function logout() {
            if(confirm("确定要退出登录吗？")){
                location.href="${pageContext.request.contextPath}/logoutServlet"
            }}
    </script>
</head>
<body>
<%
    request.setAttribute("isDisabledStyle", "");
    /*if (pageContext.getSession().getAttribute("user")!="young") {
        request.setAttribute("isDisabledStyle","disabled");
    }*/
%>
<ul class="nav nav-tabs success" style="opacity: 0.8;background-color: gainsboro;">
    <li>
        <div style="float:left;margin-left: 30px;height: 40px;padding-top: 6px">
            <img src="img/用户头像.png" style="width: 25px;height: 25px"/>
        </div>
        <div class="${colorClass}"
             style="padding-top: 6px;margin-left: 60px;height:40px;font-size: 17px;font-weight: bolder">${username}</div>
    </li>
    <li class="text-primary" style="float: left;margin-left:38%;margin-top: 4px;font-size:21px;font-weight: bolder;font-family:  黑体, Arial, Verdana, arial, serif">
            访客记录
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
<%--<div style="color:white;width: 100% ;margin-top: 30px;text-align: center;font-size:26px;font-weight: bolder">--%>
<%--    <span>网站访问记录</span>--%>
<%--</div>--%>
<div style="float: left;margin-left: 10%;margin-top: 60px;margin-bottom: 5px">
    <a class="btn btn-primary text-info " href="${pageContext.request.contextPath}/manageIndex.jsp">&laquo; 返回菜单</a>
</div>
<div style="float: right;margin-right: 10%;margin-top: 60px;margin-bottom: 5px">
    <a class="  btn btn-primary text-warning ${isDisabledStyle}" href="javascript:void(0)" id="delSelectLog">删除选中</a>
</div>
<form id="lform" style="clear: both" action="${pageContext.request.contextPath}/delSelectLogServlet"
      method="post">
    <table border="1px" class="table table-bordered table-hover" style="clear: both;width: 80%;margin-left: 10%">
        <tr style="background-color: rgb(66,139,202);color: white">
            <th style="width: 5%"><input type="checkbox" id="firstCb"></th>
            <th style="width: 5%">编号</th>
            <th style="width: 20%">ip地址</th>
            <th style="width: 30%">访问时间</th>
            <th style="width: 20%">访问页面ID</th>
            <th style="width:10% ">操作</th>
        </tr>
        </tr>
        <c:set var="i" value="0"></c:set>
        <c:forEach items="${logs}" var="log" varStatus="s">
            <c:if test="${i%2==0}">
                <tr class="bg-success">
                    <th><input type="checkbox" name="lid" value="${log.id}"></th>
                    <td>${s.count}</td>
                    <td class="text-success">${log.ip}</td>
                    <td class="text-danger">${log.visitTime}</td>
                    <td class="text-primary">${log.pageIDs}</td>
                    <td>
                        <a class="btn btn-default btn-sm text-danger ${isDisabledStyle}"
                           href="javascript:deleteLog(${log.id})">删除</a>
                    </td>
                </tr>
            </c:if>
            <c:if test="${i%2==1}">
                <tr class="bg-warning">
                    <th><input type="checkbox" name="lid" value="${log.id}"></th>
                    <td>${s.count}</td>
                    <td class="text-success">${log.ip}</td>
                    <td class="text-danger">${log.visitTime}</td>
                    <td class="text-primary">${log.pageIDs}</td>
                    <td>
                        <a class="btn btn-default btn-sm text-danger ${isDisabledStyle}"
                           href="javascript:deleteLog(${log.id})">删除</a>
                    </td>
                </tr>
            </c:if>
            <c:set var="i" value="${i+1}"></c:set>
        </c:forEach>
        </tr>
    </table>
</form>
<div style="text-align: center;">
    <nav aria-label=" Page navigation ">
        <ul class="pagination">
            <li>
                <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=1"
                   aria-label="Previous">
                    <span aria-hidden="true">第一页</span>
                </a>
            </li>
            <c:if test="${pb.currentPage == 1}">
                <li class="disabled">
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pb.currentPage != 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${pb.currentPage - 1}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pb.totalPage>10}">
                <%--                    页面数大于10，左边和右边各留5个页面--%>
                <c:if test="${pb.totalPage-pb.currentPage+1>10}">
                    <%--                    当剩余页面数大于10时 左右各留5各，中间的用...表示--%>
                    <c:forEach begin="${pb.currentPage}" end="${pb.totalPage}" var="i">
                        <c:if test="${i<pb.currentPage+5||i>pb.totalPage-5}">
                            <c:if test="${pb.currentPage == i}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${i}">${i}</a>
                                </li>
                            </c:if>
                            <c:if test="${pb.currentPage != i}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${i}">${i}</a>
                                </li>
                            </c:if>
                        </c:if>

                        <c:if test="${i==pb.currentPage+5}">
                            <li>
                                <a href="#">...</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${pb.totalPage-pb.currentPage+1<=10}">
                    <%--                    当剩余页面数不大于10时，就显示最后的10个页面--%>
                    <li>
                        <a href="#">...</a>
                    </li>
                    <c:forEach begin="${pb.totalPage-9}" end="${pb.totalPage}" var="i">
                        <c:if test="${pb.currentPage == i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${i}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${i}">${i}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:if>
            <c:if test="${pb.totalPage<=10}">
                <%--                总页面数不大于10，从第一页显示到最后--%>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${i}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${i}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${pb.currentPage!=pb.totalPage&&pb.totalPage!=0}">
                <li>
                    <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${pb.currentPage + 1}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pb.currentPage==pb.totalPage||pb.totalPage==0}">
                <li class="disabled">
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <li>
                <a href="${pageContext.request.contextPath}/findLogByPageServlet?currentPage=${pb.totalPage}"
                   aria-label="Next">
                    <span aria-hidden="true">最后一页</span>
                </a>
            </li>
            <span class="text-primary" style="font-size: 25px;margin-left: 5px;font-weight: bolder">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
            </span>

        </ul>
    </nav>
</div>
</body>
</html>
