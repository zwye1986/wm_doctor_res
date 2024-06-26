<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>安全中心</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script>
function modPasswd(userFlow){
	var url = "<s:url value='/jsres/manage/modPasswd'/>?userFlow="+userFlow;
	jboxOpen(url , "修改密码" , 600 , 300 , true);
}
function authenPhone() {
    var url = "<s:url value='/jsres/manage/authenPhone'/>";
    var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
    jboxMessager(iframe, '认证手机号', 550, 200);
}
function modifyPhone() {
    var url = "<s:url value='/jsres/manage/modifyPhone'/>";
    var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
    jboxMessager(iframe, '修改手机号', 550, 200);
}
</script>
</head>

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
                 <span class="safe_xq"><a></a></span>
               
                </dd>
              </dl>
            </li>
              <li class="safe_item">
                  <dl>
                      <dt class="title">手机号</dt>
                      <dd>
                          <span class="safe_xx">您当前使用的手机号：${sessionScope.currUser.userPhone }</span>
                          <span style="margin-left: 10px">
                        <c:if test="${sessionScope.currUser.isVerify eq 'Y' }">
                            <a onclick="modifyPhone()">修改</a>
                        </c:if>
                          <c:if test="${sessionScope.currUser.isVerify ne 'Y' }">
                              <a onclick="authenPhone()" >去认证</a>
                          </c:if>
                    </span>
                          <span class="safe_xq"><a></a></span>
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
          </ul>
        </div>
