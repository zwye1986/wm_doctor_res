<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>湖北省住院医师结业管理系统</title>
	<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
	<link rel="stylesheet" type="text/css" href="<s:url value='/css/slideRight.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/slideRight.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
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
		/*成绩查询*/
		function owenScore(){
			jboxLoad("content","<s:url value='/hbres/singup/owenScore'/>",true);
		}
		//考核申请
		function assessmentApplication(){
			jboxLoad("content","<s:url value='/hbres/doctor/getAsseApplication'/>",true);
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
				<jsp:param value="/hbres/singup/graduate/doctor" name="indexUrl"/>
				<jsp:param value="graduate" name="logName"/>
				<jsp:param value="doctor" name="graduateRole"/>
			</jsp:include>
			<div class="body">
				<div class="container">
					<div class="content_side">
						<dl class="menu menu_first">
							<dt class="menu_title">
								<i class="icon_menu menu_management"></i>结业管理
							</dt>
							<dd class="menu_item"><a onclick="owenScore('');">结业成绩查询</a></dd>
							<dd class="menu_item"><a onclick="assessmentApplication();">考核申请</a></dd>
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