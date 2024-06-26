<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <title>${sysCfgMap['jx_top_name']}</title>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script>
        function reloadVerifyCode(){
            $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
        }$(document).keydown(function(event){
            if(event.keyCode == 13){ //绑定回车
                checkForm(); ///自动/触发登录按钮
            }
        });
        function checkForm(){
            if(false==$("#loginForm").validationEngine("validate")){
                return false;
            }
            $("#loginForm").submit();
        }
    </script>
</head>
<body>
    <div class="loginbg">
        <div class="loginlogo">
            <img src="<s:url value='/jsp/inx/xzjx/images/logo1.png'/>">
        </div>
        <div class="login">
            <form id="loginForm" action="<s:url value='/inx/xzjx/login'/>" method="post">
        	<table>
            	<th>用户登录</th>
            	<tr>
                    <td>
                    <img src="<s:url value='/jsp/inx/xzjx/images/user.png'/>">
                    <input type="text" class="loginsr validate[required]" name="userCode" placeholder="用户名" >
                    </td>
                </tr>
                <tr>
                    <td>
                    <img src="<s:url value='/jsp/inx/xzjx/images/password.png'/>">
                    <input type="password" class="loginsr validate[required]" name="userPasswd" placeholder="密码">
                    </td>
                </tr>
            </table>
			<div class="yanzm">
                 <input style="width:240px; height:48px; border:#c5c5c5 1px solid; border-radius:4px;" type="text" class="yanzm  validate[required]" name="verifyCode" placeholder="验证码" >
                 <img style="cursor: pointer" id="verifyImage" src="<s:url value='/captcha'/>"
                      onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张">
            </div>

            <div class="btn_y">
                <c:if test="${not empty loginErrorMessage}">
                    <font color="red" size="4" style="margin-left:-335px;">${loginErrorMessage}</font>
                </c:if>
                <a class="dl" onclick="checkForm();">  登&nbsp;录</a>
                <a class="zc" href='<s:url value="/jsp/inx/xzjx/register.jsp"/>' >  注&nbsp;册</a>
			</div>
            </form>
        </div>
    
    
        <div class="loginbottom">
        	<span>主办单位：徐州市中心医院&nbsp;&nbsp;地址：徐州市解放南路199号</span><br>
            <a href="http://www.njpdkj.com/">技术支持：南京品德科技有限责任公司&nbsp;&nbsp;联系电话：025-69815757</a>
        </div>
    </div>
</body>
</html>
