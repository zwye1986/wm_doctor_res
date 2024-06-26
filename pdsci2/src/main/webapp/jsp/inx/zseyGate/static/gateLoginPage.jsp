<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>中山大学孙逸仙纪念医院住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/inx/zseyGate/htmlhead-zsey.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script>
function reloadVerifyCode()
{
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkForm(){
    var src = "<s:url value='/jsp/inx/zseyGate/static/exam_pic.png'/>";
    var a = window.open();
    a.document.write('<img src="'+src+'"/>');
    var aa = a.document.body.querySelector('img');
    aa.style.width = '100%';
    <%--if("${param.menuId}"==""||"${param.modelId}"==""||"${param.wsId}"==""){--%>
		<%--$(".log_tip").html("请选择功能菜单!");--%>
		<%--return false;--%>
	<%--}--%>
	<%--if($("#userCode").val()==""){--%>
		<%--$(".log_tip").html("用户名不能为空!");--%>
		<%--return false;--%>
	<%--}--%>
	<%--if($("#userPasswd").val()==""){--%>
		<%--$(".log_tip").html("密码不能为空!");--%>
		<%--return false;--%>
	<%--}--%>
	<%--if($("#verifyCode").val()==""){--%>
		<%--$(".log_tip").html("验证码不能为空!");--%>
		<%--return false;--%>
	<%--}--%>

    <%--jboxStartLoading();--%>
    <%--jboxSubmit($("#loginForm"),"<s:url value='/inx/zseyGate/gateLogin'/>",function(resp){--%>
        <%--jboxEndLoading();--%>
        <%--var json=eval("("+resp+")");--%>
        <%--console.log(json);--%>
        <%--console.log(json.code);--%>
        <%--if(json.code==200)--%>
        <%--{--%>
           <%--var src = "<s:url value='/main/${param.wsId}/${param.modelId}'/>?menuId=${param.menuId}&time="+new Date();--%>
            <%--console.log(src);--%>
           <%--top.window.location.href= src;--%>
        <%--}else{--%>
            <%--$("#verifyCode").val("");--%>
            <%--jboxTip(json.msg);--%>
            <%--reloadVerifyCode();--%>
        <%--}--%>
    <%--},function(resp){--%>
        <%--jboxEndLoading();--%>
        <%--jboxTip("登录失败！");--%>
        <%--reloadVerifyCode();--%>
    <%--},false);--%>
}

</script>
</head>
<body style="height: 100%;">
                 <div class="mainlogin" style="float: none;margin-top: 35px;margin-left: 50px;width:400px; background: url(<s:url value='/jsp/inx/zsey/images/login_bg2.png'/>) no-repeat;">
                    <form id="loginForm" action="<s:url value='/inx/zseyGate/gateLogin'/>" method="post">
                        <input type="hidden" name="menuId" value="${param.menuId}">
                        <input type="hidden" name="modelId" value="${param.modelId}">
                        <input type="hidden" name="wsId" value="${param.wsId}">
                        <table class="logintb" border="0" cellpadding="0" cellspacing="0">
                              <tbody>
                                  <tr onclick="jboxTip('您没有考试的权限');"><td height="50px;" align="center">用户登录</td></tr>
                                  <tr>
                                    <td height="40px"><span class="username"><i></i><input type="text" class="loginsr" id="userCode" name="userCode" style="width:202px;" placeholder="用户名/手机号" value=""></span></td>
                                  </tr>
                                  <tr><td height="12px"></td></tr>
                                  <tr>
                                    <td height="40px"><span class="password"><i></i><input type="password" class="loginsr" id="userPasswd" style="width:202px;" placeholder="密码" name="userPasswd" value=""/></span></td>
                                  </tr>
                                  <tr><td height="12px"></td></tr>
                                  <tr>
                                    <td height="40px" class="logintb_td">
                                        <span class="hbVerifyCode">
                                            <div style="float: left;">
                                                <i></i><input type="text" style="width:100px;" class="loginsr_yz" id="verifyCode" name="verifyCode" placeholder="验证码" value="">
                                            </div>
                                            <div style="float: right;">
                                                <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;margin-right: 0px;" height="40px" width="100px" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                                            </div>
                                        </span>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td height="50px" class="log_tips">
                                    <font class="log_tip red fl">
                                    </font>
                                  </tr>
                                  <tr>
                                    <td valign="top">
                                      <button class="btn_login" type="button" style="width:260px;" onclick="checkForm();">登&nbsp;&nbsp;录</button>&nbsp;
                                    </td>
                                  </tr>
                            </tbody>
                        </table>
                    </form>
              </div>
</body>
</html>