<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
	</jsp:include>
	<style>
		#cnzz_stat_icon_1260790632 a {color: #fff;font-size: 11px;cursor: default;}
	</style>
	<script>
		$(function(){
			if(top!=self){top.location = self.location;}
			//获取当前星期几
			var weekDate = '星期'+'日一二三四五六'.charAt(new Date().getDay());
			$("#weekDateSpan").html(weekDate);
		});

		$(function(){
			var pwd = $("#placepwd");
			var password = $("#userPasswd");
			if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
				password.hide();
				pwd.show();

				pwd.focus(function(){
					pwd.hide();
					password.show().focus();
				});

				password.focusout(function(){
					if(password.val().trim() === ""){
						password.hide();
						pwd.show();
					}
				});
			}

		});
		function checkForm(){
			if($("#userCode").val()==""){
				$(".log_tips").html("用户名不能为空!");
				return false;
			}
			if($("#userPasswd").val()==""){
				$(".log_tips").html("密码不能为空!");
				return false;
			}
			return true;
		}

	</script>
</head>
<body>
<div class="content global">
	<div class="banner">
		<div class="logo_index">
			<div class="load fr">
				<form id="loginForm" action="<s:url value='/app/login'/>" method="post">
					<div class="con">
						<div class="user_load"><input type="text" id="userCode" name="userCode" class="text" placeholder="用户名" value="${param.userCode }" /></div>
						<div class="pass"><input type="text" id="placepwd" class="text" style="display: none;" placeholder="密&nbsp;&nbsp;码" value=""/><input  type="password" id="userPasswd" name="userPasswd" class="text" value="" placeholder="密&nbsp;&nbsp;码"/></div>
						<div class="ts">
							<div class="fl">
								<font class="log_tips">
									<c:if test="${not empty loginErrorMessage}">
										登录失败：${loginErrorMessage}
									</c:if>
								</font>
								<c:if test="${empty loginErrorMessage}">&#12288;</c:if>
							</div>
						</div>
						<div>
							<input type="submit" onclick="return checkForm();" class="butt_load" style="width:300px; " value="登&nbsp;&nbsp;录" />
						</div>
						<div class="hint">最低分辨率支持：1200*800px</div>
					</div>
				</form>
			</div>

		</div>
	</div>
</div>
</body>
</html>