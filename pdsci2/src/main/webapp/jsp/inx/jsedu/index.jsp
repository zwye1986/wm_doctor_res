<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>江苏省住院医师规范化培训 公共科目学习平台</title>
<link type="text/css" rel="stylesheet" href="<s:url value='/jsp/inx/jsedu/css/font.css'/>" />
    <script type="text/javascript" src="<s:url value='/js/jquery-1.9.1.min.js'/>"></script>
    <script type="text/javascript">
        function reloadVerifyCode()
        {
            $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
        }
        function checkForm(){
            if(false==$("#loginForm").validationEngine("validate")){
                return false;
            }
            $("#loginForm").submit();;
        }
    </script>
</head>
<body onload="tick()">
<div class="top_box">
	<div class="top" id="currDate">&#12288;</div>
</div>
<div class="banner_box">
	<div class="banner"></div>
    <div class="logo"><img class="title" src="<s:url value='/'/>jsp/inx/jsedu/images/title_2.png" /></div>
     <div class="login_bg" style="display: none">
           <div class="weixin">
                 <a class="db login_title" >用户登录</a>
                 <div class="login">
                      <img src="<s:url value='/'/>jsp/inx/jsedu/images/login_pic1.png">
                      <input value="" placeholder="用户名">    
                 </div>
                 <div class="mima">
                      <img src="<s:url value='/'/>jsp/inx/jsedu/images/login_pic2.png">
                      <input type="password" value="" placeholder="密码">
                 </div>
                 <div class="yanzm">
                     <input name="verifyCode" value="" placeholder="验证码" />
                     <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                 </div>
                 <a class="red fl">${message}</a>
                 <a href="" class="denglu db">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</a>
           </div>
      </div>
</div>
<div class="study"><img src="<s:url value='/'/>jsp/inx/jsedu/images/process.png" /></div>
<div class="build"><img src="<s:url value='/'/>jsp/inx/jsedu/images/building.png" /></div>
<div class="footer_box">
	<a class="footer db">主管单位：${sysCfgMap['the_competent_unit']}<br />
技术支持：南京品德网络信息技术有限公司 &#12288;电话：025-69815356  69815357
</a>
</div>
</body>
</html>
<script>
    function showLocale(objD){
        var str,colorhead,colorfoot;
        var yy = objD.getYear();
        if(yy<1900) yy = yy+1900;
        var MM = objD.getMonth()+1;
        if(MM<10) MM = '0' + MM;
        var dd = objD.getDate();
        if(dd<10) dd = '0' + dd;
        var hh = objD.getHours();
        if(hh<10) hh = '0' + hh;
        var mm = objD.getMinutes();
        if(mm<10) mm = '0' + mm;
        var ss = objD.getSeconds();
        if(ss<10) ss = '0' + ss;
        if (hh <= 11) var amOrPm =" AM";
        if (hh > 11) var amOrPm =" PM";
        if (hh > 12) hh = hh - 12;
        if (hh == 0) hh = 12;

        var ww = objD.getDay();
        if  (ww==0)  ww="星期日";
        if  (ww==1)  ww="星期一";
        if  (ww==2)  ww="星期二";
        if  (ww==3)  ww="星期三";
        if  (ww==4)  ww="星期四";
        if  (ww==5)  ww="星期五";
        if  (ww==6)  ww="星期六";
//        str = yy + "-" + MM + "-" + dd + "  " + ww + " " + hh + ":" + mm + ":" + ss + amOrPm;
        str = yy + "-" + MM + "-" + dd + "  " + ww;
        return(str);
    }
    function tick(){
        var today;
        today = new Date();
        document.getElementById("currDate").innerHTML = showLocale(today);
        window.setTimeout("tick()", 1000);
    }
</script>