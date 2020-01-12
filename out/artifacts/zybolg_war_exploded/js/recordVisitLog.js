
$(function () {
    var pid = $("input[name='pageID']").val();
    if ($.cookie("visitTime") != null) {
        //用户未关闭浏览器
        var visitTime = $.cookie("visitTime");
        var pageIDs = $.cookie("pageIDs");

        if (pageIDs.indexOf(pid) == -1) {
            //pageIDs不包含pid 需要向后端发送数据
            pageIDs = pageIDs +" "+pid;
            //更新cookie
            $.cookie('pageIDs',pageIDs,{path:'/'});
            var js={
                'visitTime':visitTime,
                'pageIDs':pageIDs,
                'flag':"1",
                'newPageID':pid
            };
            //servlet通过flag来判断是插入数据或更新数据
            $.post("recordVisitLogServlet",js);
        }
    } else {
        //用户首次访问
        //获取访问时间
        var visitTime = getnowtime();
        var js={
            'visitTime':visitTime,
            'pageIDs':pid,
            'flag':'0',
            'newPageID':'-1'
        };
        $.cookie("visitTime",visitTime,{path:'/'});
        $.cookie("pageIDs",pid,{path:'/'});
        $.post('recordVisitLogServlet',js);
    }
})

function getnowtime() {
    var nowtime = new Date();
    var year = nowtime.getFullYear();
    var month = padleft0(nowtime.getMonth() + 1);
    var day = padleft0(nowtime.getDate());
    var hour = padleft0(nowtime.getHours());
    var minute = padleft0(nowtime.getMinutes());
    var second = padleft0(nowtime.getSeconds());
    return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
}

//补齐两位数
function padleft0(obj) {
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);
}
/*
function getReferrer() {
    var referrer = '';
    try {
        referrer = window.top.document.referrer;
    } catch (e) {
        if (window.parent) {
            try {
                referrer = window.parent.document.referrer;
            } catch (e2) {
                referrer = '';
            }
        }
    }
    if (referrer === '') {
        referrer = document.referrer;
    }
    return referrer;
}*/
