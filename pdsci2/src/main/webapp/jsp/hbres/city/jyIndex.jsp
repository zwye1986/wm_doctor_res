<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>湖北省住院医师结业管理系统</title>
	<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
	</jsp:include>
	<script>
		$(document).ready(function(){
			$(".menu_item a").click(function(){
				$(".select").removeClass("select");
				$(this).addClass("select");
			});
			setBodyHeight();
			$('.menu_item a:first').click();
		});
		function setBodyHeight(){
			if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
				if(navigator.appVersion.indexOf("MSIE 7.0")>-1){
					$("#indexBody").css("height",window.innerHeight+"px");
				}else if(navigator.appVersion.indexOf("MSIE 8.0")>-1){
					$("#indexBody").css("height",document.documentElement.offsetHeight+"px");
				}else{
					$("#indexBody").css("height",window.innerHeight+"px");
				}
			} else {
				$("#indexBody").css("height",window.innerHeight+"px");
			}
		}
		onresize=function(){
			setBodyHeight();
		};
		function showSelect(){
			jboxOpen("<s:url value='/jsp/hbres/select.jsp'/>", "系统切换", 780, 600);
		}
		/*理论成绩*/
		function doctorTheoryList(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/hbres/singup/doctorTheoryList'/>?roleFlag="+roleFlag,true);
		}
		/*技能成绩*/
		function doctorSkillList(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/hbres/singup/doctorSkillList'/>?roleFlag="+roleFlag,true);
		}
        function asseAudit(){
            var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
            jboxLoad("content","<s:url value='/hbres/asse/main'/>?roleFlag="+roleFlag+"&tabTag=WaitAudit",true);
        }
		function attachmentMain(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/hbres/asse/attachmentMain'/>?roleFlag="+roleFlag,true);
		}
	</script>
	<style>
		body{overflow:hidden;}
	</style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
	<div class="bd_bg">
		<div class="yw">
			<jsp:include page="/jsp/hbres/head.jsp" flush="true">
				<jsp:param value="/hbres/singup/graduate/manage/charge" name="indexUrl"/>
				<jsp:param value="graduate" name="logName"/>
				<jsp:param value="${AuditGraduationRole}" name="AuditGraduationRole"/>
			</jsp:include>
			<div class="body">
				<div class="container">
					<div class="content_side">
						<dl class="menu menu_first">
							<dt class="menu_title">
								<i class="icon_menu menu_management"></i>结业管理
							</dt>
							<dd class="menu_item"><a onclick="doctorTheoryList();">理论成绩管理</a></dd>
							<dd class="menu_item"><a onclick="doctorSkillList();">技能成绩管理</a></dd>
							<dd class="menu_item"><a onclick="asseAudit();">考核资格审查</a></dd>
							<dd class="menu_item"><a onclick="attachmentMain();">年度考核成绩材料</a></dd>
						</dl>
					</div>
					<div class="col_main" id="content">
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
	<jsp:include page="/jsp/hbres/foot.jsp"  flush="true"/>
</div>
</body>
</html>
