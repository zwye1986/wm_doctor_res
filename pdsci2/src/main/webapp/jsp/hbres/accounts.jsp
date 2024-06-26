<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>安全中心</title>
<script>
function modPasswd(userFlow){
	//jboxOpenContent($('#modPasswd').html() , "修改密码" , 500 , 300 , true);
	var url = "<s:url value='/hbres/singup/modPasswd'/>?userFlow="+userFlow;
	jboxOpen(url , "修改密码" , 500 , 300 , true);
}

function getMailContent(obj) {
	$("#mailContent").html("");
	var userEmail = $(obj).val();
	if (userEmail == "") {
		jboxTip("请输入邮箱！");
		return;
	}
	var url = "<s:url value='/hbres/singup/getMailContent'/>?userEmail="+userEmail;
	jboxGet(url , null , function(resp){
		if(resp == '${GlobalConstant.OPRE_FAIL}') {
			jboxTip("用户不存在或者该用户已邮箱激活！");
		} else {
			$("#mailContent").html(resp);
		}
	} , null , false);
}

function emailMain(userFlow){
	jboxLoad("content","<s:url value='/hbres/singup/user/emailMain?userFlow='/>"+ userFlow,true);
}

function phoneMain(userFlow){
	jboxLoad("content","<s:url value='/hbres/singup/user/phoneMain?userFlow='/>"+ userFlow,true);
}
</script>
</head>
<body>
        <div class="main_hd">
          <h2>安全中心</h2>
        </div>
        <div class="main_bd">
          <ul class="safe_list">
            <li class="safe_item">
              <dl>
                <dt class="title">邮箱</dt>
                <dd>
                 <span class="safe_xx">你的帐号已绑定邮箱：${sessionScope.currUser.userEmail }</span>
                 <span class="safe_xq">
                 <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['user_edit_mail']}">
                 	<a href="javascript:emailMain('${currUser.userFlow}');">修改</a>
                 </c:if>
                 </span>
                </dd>
              </dl>
            </li>
            <li class="safe_item">
              <dl>
                <dt class="title">手机号</dt>
                <dd>
                <span class="safe_xx">您当前使用的手机号：${sessionScope.currUser.userPhone }</span>
                <span class="safe_xq">
                <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['user_edit_phone']}">
                	<a href="javascript:phoneMain('${currUser.userFlow}');">修改</a>
                </c:if>
                </span>
                </dd>
              </dl>
            </li>
            <li class="safe_item">
              <dl>
                <dt class="title">操作日志</dt>
                <dd>
                <span class="safe_xx">最近操作：${pdfn:transDateTime(log.logTime) } &nbsp; ${log.operName }</span>
                </dd>
              </dl>
            </li>
             <li class="safe_item">
              <dl>
                <dt class="title">修改密码</dt>
                <dd>
                <span class="safe_xx"><a href="javascript:modPasswd('${currUser.userFlow}');">修改</a></span>
                </dd>
              </dl>
            </li>
            <!-- 
            <li class="safe_item">
              <dl>
                <dt class="title">邀请链接</dt>
                <dd>
                <span class="safe_xx">
                	邮箱：<input type="text" value="" onblur="getMailContent(this);"/><br/>
                	邮件内容：<br/><span id="mailContent" class="mailContent"></span>
                </span>
                </dd>
              </dl>
            </li>
             -->
          </ul>
        </div>
</body>
</html>
