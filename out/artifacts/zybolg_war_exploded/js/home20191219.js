function iframeAutoHeight() {
    var iframe = document.getElementById("midFraim");
    if (navigator.userAgent.indexOf("MSIE") > 0 || navigator.userAgent.indexOf("rv:11") > 0 || navigator.userAgent.indexOf("Firefox") > 0) {
        iframe.height = iframe.contentWindow.document.body.scrollHeight;
    } else {
        iframe.height = iframe.contentWindow.document.documentElement.scrollHeight;
    }
}

function changeImg1() {
    //			改变pc页面背景
    var i = Math.random();
    var a = Math.ceil(i * 8);
    var s = "背景" + a + ".jpg";
    $("body").css("background-image", "url(img/" + s + ")");
}

function changeImg2() {
    //change phone page background
    var i = Math.random();
    var a = Math.ceil(i * 5);
    var s = "背景ph" + a + ".jpg";
    $("body").css("background-image", "url(img/" + s + ")");
}
function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if(r != null) {
        return decodeURI(r[2]);
    }
    return null;
}
window.onload=function () {
     var msg=getUrlParam("msg")
    // alert(msg)
    // alert(decodeURI('%270%27'))
    //不能这样写
    //if(msg=='0') false
    if(msg==decodeURI('%270%27'))
        alert("非法访问！");
}


