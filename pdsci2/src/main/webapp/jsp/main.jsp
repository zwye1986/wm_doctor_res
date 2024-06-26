<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<c:if test="${sessionScope.jsresUserRoleFlag == GlobalConstant.RECORD_STATUS_Y and sessionScope.currWsId==GlobalConstant.EVAL_WS_ID }">
		<title>基地评估管理系统</title>
	</c:if>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/AmazeUI/js/amazeui.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
$(document).ready(function(){
	$('#fullscreen').click(function(e){
		$.AMUI.fullscreen.toggle();
	});
	//待办事项-桌面提醒
	if(sessionStorage.getItem("tip") != null){
		sessionStorage.setItem("tip", setInterval(tip,600000));
	}
	//广医学籍工作站，密码定时提醒
	if(${sessionScope.currWsId eq 'gycmis' && !empty sessionScope.currUser.tipPassword}){
		jboxInfoBasic("密码已过3个月，请至安全中心更改！",260);
	}
//广东东华医院临床技能中心-弹出系统提示
    if(${sessionScope.currWsId eq 'lcjn' && applicationScope.sysCfgMap['lcjn_show_notice'] eq 'Y' && param.showFlag eq 'Y'}) {
        var url = "<s:url value='/jsp/notice.jsp'/>" + "?time=" + new Date();
        jboxOpen(url, "系统通知", 800, 600, true);
    }
});
function tip(){
	jboxInfoBasic("您有未办理事项，请及时处理！",260);
}

$(function(){
    changeWH();
});
function changeWH(){
    var w = document.documentElement.clientWidth || document.body.clientWidth;
    var h = document.documentElement.clientHeight || document.body.clientHeight;
	if("${applicationScope.sysCfgMap['sys_skin']}"=='Blue'){
		$("#mainIframe").height(h-159);
	}else {
		$("#mainIframe").height(h-111);
	}
	$("#mainIframe").width(w-220);

}
window.onresize=function(){
    changeWH();
}
</script>
<script language="JavaScript">
<!-- //
function move(){
	$("#left").toggle();
	var img = $("#barimg");
	if(img.attr('src')=='<s:url value='/css/skin/${skinPath}/images/left_arrow.png'/>'){
		img.attr('src','<s:url value='/css/skin/${skinPath}/images/right_arrow.png'/>');
	}else{
		img.attr('src','<s:url value='/css/skin/${skinPath}/images/left_arrow.png'/>');
	}
}
function showMenuSet(menuSetId) {
	$(".menuSetClass").each(function(){ 
		if($(this).attr('id')== menuSetId){
			$(this).children('h1').addClass('active');
			$(this).children('span').removeClass('no');
		}else{
			$(this).children('h1').removeClass('active');
			$(this).children('span').addClass('no');
		}
	});
}
function setMenuSetName(menuSetName){
	$("#menuSetName").text(menuSetName);
	setMenuName("");
}
function setMenuName(menuName){
	if(""!=menuName){
		menuName = "-- "+menuName;
	}
	$("#menuName").text(menuName);
}
function highlight(menuId){
	$(".aClass").each(function(){ 
		if($(this).attr('id')== menuId){
			$(this).addClass("current");
		}else{
			$(this).removeClass("current");
		}
	});
}
function loadMain(src, openType){//openType为弹窗标识
	jboxCloseMessager();
	jboxStartLoading();
	if(src.indexOf('isMainFrame')==-1){
		if(src.indexOf('?')>-1){
			src = src+"&isMainFrame=Y&time="+new Date();
		}else{
			src = src+"?isMainFrame=Y&time="+new Date();
		}		
	}else{
		if(src.indexOf('?')>-1){
			src = src+"?time="+new Date();
		}else{
			src = src+"&time="+new Date();
		}
	}
	if(openType == 1){//openType为1弹出新窗口
		window.open(src);
		jboxEndLoading();
	}else{
		window.frames["mainIframe"].location.href= src;
	}
}
function loadModule(src){
	jboxStartLoading();
	src = src+"&time="+new Date()+"&hbPortals=${param.hbPortals}";
	window.parent.location.href= src;
}
function refresh(){
	jboxStartLoading();
	window.frames['mainIframe'].location.reload(true); 
	jboxEndLoading();
}
function home(){
	jboxStartLoading();
	window.location.reload(true);
	jboxEndLoading();
}
function goback(){
	jboxStartLoading();
	window.frames['mainIframe'].history.back(-1); 
	jboxEndLoading();
}
function modPasswd(userFlow,showClose) {
	jboxOpen("<s:url value='/sys/user/modPasswd?userFlow='/>"
			+ userFlow, "修改密码", 500, 300,showClose);
}
function selectProj(){
	jboxOpen("<s:url value='/edc/proj/userProjList'/>", "选择项目", 800, 600,false);
}
function selectStation(){
	jboxOpen("<s:url value='/main/select'/>", "切换工作站", 780, 600);
	//window.location.href="<s:url value='/main'/>"; 
}
function showSelect(){
	jboxOpen("<s:url value='/jsp/hbres/select.jsp'/>", "系统切换", 780, 600);
}

function showSelect2(){
	jboxOpen("<s:url value='/jsp/jsres/global/select.jsp'/>", "系统切换", 780, 600);
}
<c:if test="${sessionScope.currWsId==GlobalConstant.EDC_WS_ID and not empty sessionScope.currModuleId and sessionScope.currModuleId!='edc-xtpz' and sessionScope.currModuleId!='edc-sfgl' and sessionScope.currModuleId!='edc-mkk' and empty sessionScope.edcCurrProj}"> 
$(document).ready(function(){
	selectProj();
});
</c:if> 
<c:if test="${sessionScope.currWsId==GlobalConstant.GCP_WS_ID and not empty sessionScope.currModuleId and sessionScope.currModuleId=='gcp-sszgl' and empty sessionScope.edcCurrProj}"> 
$(document).ready(function(){
	selectProj();
});
</c:if> 
<c:if test="${applicationScope.sysCfgMap['req_complex_passwd']== GlobalConstant.FLAG_Y}">
<c:if test="${sessionScope.currUser.userPasswd==pdfn:encryptPassword(sessionScope.currUser.userFlow,pdfn:getInitPass())}">
$(document).ready(function(){
modPasswd('${sessionScope.currUser.userFlow}',false);
});
</c:if>
</c:if>
<c:if test="${not empty sessionScope.mainFrameSrc and empty param.menuId }">
$(document).ready(function(){
	loadMain('${sessionScope.mainFrameSrc}');
});							
</c:if>
<%-- 当前工作所拥有的模块数量 --%>
<c:set var="moduleListSize" value="0" scope="page"></c:set>
<c:forEach items="${applicationScope.workStationList}" var="workStation">
	<c:forEach items="${workStation.moduleList}" var="module" varStatus="status">
		<c:if test="${sessionScope.currWsId==workStation.workStationId and pdfn:contain(module.moduleId, sessionScope.currUserModuleIdList)}">
			<c:set var="moduleListSize" value="${moduleListSize+1}" scope="page"></c:set>
		</c:if>
	</c:forEach>
</c:forEach>
<c:if test="${ sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
<c:set var="moduleListSize" value="1000" scope="page"></c:set>
</c:if>

function resizeWin() {
	var leftClientHeight  ;
	var secClientHeight  ;
	if(${applicationScope.sysCfgMap['sys_skin']=='LightBlue'}){
		<c:if test="${ moduleListSize>1}">
		leftClientHeight = 80;    
		secClientHeight = 80;
		</c:if>
		<c:if test="${ moduleListSize<2}">
		leftClientHeight = 45;
		secClientHeight = 45;
		</c:if>
		
	}else if(${applicationScope.sysCfgMap['sys_skin']=='Yellow'}){
		<c:if test="${ moduleListSize>1}">
		leftClientHeight = 80;    
		secClientHeight = 80;
		</c:if>
		<c:if test="${ moduleListSize<2}">
		leftClientHeight = 45;
		secClientHeight = 45;
		</c:if>
		
	}else if(${applicationScope.sysCfgMap['sys_skin']=='Blue' || empty applicationScope.sysCfgMap['sys_skin']}){
		<c:if test="${ moduleListSize>1}">
		leftClientHeight = 120;
		secClientHeight = 120;
		</c:if>
		<c:if test="${ moduleListSize<2}">
		leftClientHeight = 73;
		secClientHeight = 73;
		</c:if>
	}
	
    $("#left").height(eval(document.body.clientHeight - leftClientHeight) + "px");
    $("#menu_sec").height(eval(document.body.clientHeight - secClientHeight) + "px");
    $("#zip").height(eval(document.body.clientHeight - leftClientHeight) + "px");
    $("#tdNotice").height(eval(document.body.clientHeight - leftClientHeight) + "px");
    if ($("#left :hidden").length > 0) {
        $("#tdNotice").width(eval(document.body.clientWidth) + "px");
    }
    else {
        $("#tdNotice").width(eval(document.body.clientWidth - 210) + "px");
    }

 }
 $(window).ready(function () {
     resizeWin();
 });
 $(window).resize(resizeWin);
//-->

function changeDept(){
	jboxGet("<s:url value='/sys/user/changeDept'/>?deptFlow="+$("#deptFlow").val(),null,function(){
		refresh();
	},null,true);
}


function checkFinishInfo(){
	var finishInfoFlag = "${finishUserInfoFlag}";
//	alert(finishInfoFlag);
	if(finishInfoFlag =="Y"){
		var url = "<s:url value='/sys/user/finishInfo?userFlow=${sessionScope.currUser.userFlow}'/>";
		jboxOpen(url, "完善个人信息", 800, 400, false);
	}
}

$(function(){
	<c:if test='${sysCfgMap["srm_expert_need_finish_info"] eq "Y"}'>
	checkFinishInfo();
	</c:if>
});
</script>

<style>
	.tLightBlue{
		background-position: 190px 20px;
	}
	.tright.tLightBlue p{
		padding-left: 32px;
		box-sizing: border-box;
		background-position: 3px 10px;
		padding-right: 24px;
		line-height: normal;
		height: 100%;
	}
	.menu1{
		height: 100%;
	}
	.menu1 a{
		height: 100%;
		line-height: normal;
	}
	.tright .uName{
		float: right;
		width: 110px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
		height: 100%;
		text-align: left;
		line-height: normal;
	}
</style>
</head>
<body id="body">
	<table width="100%" height="100%" cellspacing="0" cellpadding="0">
		<!--header-->
		<tr>
			<td colspan="3">
				<div class="header">
					<div class="top">
						<p class="tleft">
							<c:if test="${param.hbPortals eq 'Y'}">
								<img src="<s:url value='/css/skin/${skinPath}/images/hbPortals_head.png'/>" />
							</c:if>
							<c:if test="${sessionScope.jsresUserRoleFlag == GlobalConstant.RECORD_STATUS_Y  and sessionScope.currWsId==GlobalConstant.EVAL_WS_ID }">
								<img src="<s:url value='/css/skin/${skinPath}/images/eval_head.png'/>" />
							</c:if>
							<c:if test="${(sessionScope.jsresUserRoleFlag == GlobalConstant.RECORD_STATUS_Y  and sessionScope.currWsId!=GlobalConstant.EVAL_WS_ID)||
							 (sessionScope.jsresUserRoleFlag != GlobalConstant.RECORD_STATUS_Y or sessionScope.currWsId!=GlobalConstant.EVAL_WS_ID) }">
								<c:if test="${empty extAddress}">
									<c:if test="${sessionScope.currWsId==GlobalConstant.EVAL_WS_ID and sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
										<img src="<s:url value='/css/skin/${skinPath}/images/eval_head.png'/>" />
									</c:if>
										<c:if test="${!(sessionScope.currWsId==GlobalConstant.EVAL_WS_ID and sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE)}">
											<c:if test="${sessionScope.currWsId ne 'res' && sessionScope.currWsId ne 'portals' && sessionScope.currWsId ne 'study' && sessionScope.currWsId ne 'recruit'}">
												<c:if test="${!(sessionScope.currWsId eq 'njmuedu' and applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx')}">
													<img src="<s:url value='/css/skin/${skinPath}/images/${sessionScope.currWsId}_head.png'/>" />
												</c:if>
												<c:if test="${sessionScope.currWsId eq 'njmuedu' and applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}">
													<img src="<s:url value='/css/skin/${skinPath}/images/${sessionScope.currWsId}_gy_head.png'/>" />
												</c:if>
											</c:if>
											<c:if test="${(sessionScope.currWsId eq 'res' || sessionScope.currWsId eq 'portals' || sessionScope.currWsId eq 'study'|| sessionScope.currWsId eq 'recruit')&&param.hbPortals ne 'Y'}">
											<img src="<s:url value='/css/skin/${skinPath}/images/${applicationScope.sysCfgMap["sys_login_img"]}_head.png'/>" />
											</c:if>
										</c:if>
								</c:if>
								<c:if test="${!(empty extAddress)}">
									<img style="margin-left: 15px;" src="<s:url value='/css/skin/${skinPath}/images/${extAddress}'/>" />
								</c:if> 
							</c:if>
						</p>
						<c:if test="${applicationScope.sysCfgMap['sys_skin']=='Blue' || empty applicationScope.sysCfgMap['sys_skin']}">
						 <div class="tright" style="width:400px;">
							<span
									<%--<c:if test="${fn:length(sessionScope.currUserWorkStationIdList) eq 1 and sessionScope.currUser.userCode ne GlobalConstant.ROOT_USER_CODE}">style="padding-left: 100px"</c:if>--%>
							>
								<p>
									&nbsp;欢迎您 ${sessionScope.currUser.userName}
									<%--,<a href="javascript:modPasswd('${sessionScope.currUser.userFlow}',true)">修改密码</a>--%>
									<%-- 当前所在项目 --%>
									<c:if
										test="${sessionScope.currWsId==GlobalConstant.EDC_WS_ID or
										sessionScope.currWsId==GlobalConstant.GCP_WS_ID
										 and not empty sessionScope.edcCurrProj}">
										<a href="javascript:selectProj();" title="${sessionScope.edcCurrProj.projName}">当前项目：${pdfn:cutString(sessionScope.edcCurrProj.projName,10,true,3 )}</a>
									</c:if>
								</p>
								<p>									
									<c:if test="${fn:length(sessionScope.currUserWorkStationIdList)>1 or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
										<c:if test="${sessionScope.hbUserRoleFlag != GlobalConstant.RECORD_STATUS_Y and
										 sessionScope.jsresUserRoleFlag != GlobalConstant.RECORD_STATUS_Y }">
						        			<a class="change" href="javascript:selectStation();">切换工作站</a>
										</c:if>
						        	</c:if>
									<c:if test="${sessionScope.currWsId==GlobalConstant.EDC_WS_ID}">
						        		<a class="shuaxin" href="javascript:selectProj();">切换项目</a>
						        	</c:if>
									<c:if test="${sessionScope.hbUserRoleFlag == GlobalConstant.RECORD_STATUS_Y }">
										<a class="change" href="javascript:showSelect();">系统切换</a>
									</c:if>
									<c:if test="${sessionScope.jsresUserRoleFlag == GlobalConstant.RECORD_STATUS_Y }">
										<a class="change" href="javascript:showSelect2();">系统切换</a>
									</c:if>
									<%--<c:if test="${applicationScope.sysCfgMap['sys_index_url']!='/inx/hbres'}">--%>
										<%--<a class="shuaxin" href="javascript:refresh();">刷新</a> <a--%>
											<%--class="houtui" href="javascript:goback();">后退</a>--%>
									<%--</c:if>--%>
									<a class="zhuye" href="javascript:home();">主页</a>
									<%-- <a class="help" href="#">帮助</a>--%>
									<a class="zhuxiao" href="<s:url value='/logout.do?wsId=${workStationId}'/>">注销</a>
								</p>
							</span>
						</div>
						</c:if>
						<c:if test="${applicationScope.sysCfgMap['sys_skin']=='LightBlue' or applicationScope.sysCfgMap['sys_skin']=='Blue2018' || applicationScope.sysCfgMap['sys_skin']=='Yellow'}">
						 <div class="tright tLightBlue">
          					 <div class="menu1">  
 							 <ul style="width: 100%;height: 100%;">
   								 <li style="height: 100%;"><a href="#"><p><span style="margin-top: 15px;line-height: normal">欢迎您</span><span class="uName"> ${sessionScope.currUser.userName}</span></p></a>
     								<!--[if IE 7]><!--></a><!--<![endif]-->  
     								<!--[if lte IE 6]><table><tr><td><![endif]-->  
     								 <ul>  
       									 <li><a class="home" href="javascript:home();"><img src="<s:url value='/css/skin/${skinPath}/images/home.png'/>" />主页</a></li>
       									 <c:if test="${fn:length(sessionScope.currUserWorkStationIdList)>1 or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
											 <c:if test="${sessionScope.hbUserRoleFlag != GlobalConstant.RECORD_STATUS_Y and
										 sessionScope.jsresUserRoleFlag != GlobalConstant.RECORD_STATUS_Y }">
											<li><a href="javascript:selectStation();"><img src="<s:url value='/css/skin/${skinPath}/images/check.png'/>" />切换工作站</a></li>
											 </c:if>
						        	    </c:if>
									    <c:if test="${sessionScope.currWsId==GlobalConstant.EDC_WS_ID}">
						        		<li><a href="javascript:selectProj();"><img src="<s:url value='/css/skin/${skinPath}/images/change-xm.png'/>" />切换项目</a></li>        	
						        	    </c:if>
										 <c:if test="${sessionScope.hbUserRoleFlag == GlobalConstant.RECORD_STATUS_Y }">
										 <li><a href="javascript:showSelect();"><img src="<s:url value='/css/skin/${skinPath}/images/check.png'/>" />系统切换</a></li>
										 </c:if>
										 <c:if test="${sessionScope.jsresUserRoleFlag == GlobalConstant.RECORD_STATUS_Y }">
										 <li><a href="javascript:showSelect2();"><img src="<s:url value='/css/skin/${skinPath}/images/check.png'/>" />系统切换</a></li>
										 </c:if>
										 <%--<c:if test="${applicationScope.sysCfgMap['sys_index_url']!='/inx/hbres'}">--%>
											 <%--<li><a href="javascript:refresh();"><img src="<s:url value='/css/skin/${skinPath}/images/sx.png'/>" />刷新</a></li>--%>
											 <%--<li><a href="javascript:goback();"><img src="<s:url value='/css/skin/${skinPath}/images/ht.png'/>" />后退</a></li>--%>
											 <%--<li><a href="javascript:modPasswd('${sessionScope.currUser.userFlow}',true);"><img src="<s:url value='/css/skin/${skinPath}/images/lock1.png'/>" />修改密码</a></li>--%>
										 <%--</c:if>--%>
       		 							 <li><a href="<s:url value='/logout.do?wsId=${workStationId}'/>"><img src="<s:url value='/css/skin/${skinPath}/images/login out.png'/>" />注销</a></li>
      								</ul>  
      								<!--[if lte IE 6]></td></tr></table></a><![endif]-->  
   								 </li>  
 		 					</ul>         
							</div>
        				</div>	
        				</c:if>
					</div>
				</div>
			</td>
		</tr>		
		<tr <c:if test="${moduleListSize<2}">style="display: none;"</c:if>>
			<td colspan="3">		
				<div class="menu">
					<ul class="clearfix">
						<c:forEach items="${applicationScope.workStationList}"
							var="workStation">
							<c:if test="${sessionScope.currWsId==workStation.workStationId}">
								<c:forEach items="${workStation.moduleList}" var="module" varStatus="status">
									<c:if test="${status.first}">
										<c:set var="firstModule" value="${module.moduleId}"/>
									</c:if>
									<c:set var="canShowModule" value="false" scope="page"></c:set>
									<%-- 判断是否开关控制 --%>
									<c:if test="${sessionScope.currWsId=='srm' }">
										<c:set var="canShowModule" value="${pdfn:canShowByVersion(module.version)}" scope="page"></c:set>
									</c:if>										
									<c:if test="${sessionScope.currWsId=='edc' }">
										<c:choose>
											<c:when test="${module.moduleId == 'edc-sjh' and (!empty sessionScope.edcCurrProjParam and sessionScope.edcCurrProjParam.isRandom!='Y' )}">
												<c:set var="canShowModule" value="false" scope="page"></c:set>
											</c:when>
											<c:otherwise>
												<c:set var="canShowModule" value="true" scope="page"></c:set>												
											</c:otherwise>
										</c:choose>
									</c:if>	
									<c:if test="${sessionScope.currWsId!='srm' and sessionScope.currWsId!='edc' }">
										<c:set var="canShowModule" value="true" scope="page"></c:set>
									</c:if>	
									<c:if test="${sessionScope.currWsId=='res'}">
										<c:set var="canShowModule" value="true" scope="page"></c:set>
									</c:if>
									<c:if test="${canShowModule == true }">
									<%-- 判断是否权限控制 --%>
										<c:if test="${pdfn:contain(module.moduleId, sessionScope.currUserModuleIdList) or (sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE and fn:indexOf(module.moduleId, '-xtpz')>0)}">
											<li
												class="
												<c:choose>
													<c:when test="${sessionScope.currModuleId==module.moduleId}">
														yes									
														<c:set var="currModuleName" value="${module.moduleName}" scope="session" />															
														<c:set var="currModuleView" value="${module.view}" scope="session" />
													</c:when>
													<c:otherwise>not</c:otherwise></c:choose>">
												<a
												href="javascript:loadModule('<s:url value='/main/${sessionScope.currWsId}/${module.moduleId}'/>?showFlag=N');"
												ondblclick="">
												<%-- 模块图标 --%>
												<c:if test="${sessionScope.currWsId=='srm' ||sessionScope.currWsId=='irb'||sessionScope.currWsId=='edc'
												||  sessionScope.currWsId=='sch'||sessionScope.currWsId=='gcp'}">
												<img src="<s:url value="/css/skin/${skinPath}/images/module/${module.moduleId}.png" />" />
												</c:if>	
												${module.moduleName}</a>
																	
											</li>	
											<%-- 如果当前模块没选中，默认显示第一个模块 --%>
											<c:if test="${empty sessionScope.currModuleId}">
												<c:set var="currModuleId" value="${module.moduleId}" scope="session" />
												<script type="text/javascript">
													$(document).ready(function(){
														loadModule('<s:url value='/main/${sessionScope.currWsId}/${module.moduleId}'/>?showFlag=Y');
													});
												</script>
											</c:if>							
										</c:if>	
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</td>
		</tr>
		<tr>
			<c:set var="hiddenLeft" value="false" scope="page"></c:set>
			<c:forEach items="${applicationScope.workStationList}" var="workStation">
				<c:if test="${sessionScope.currWsId==workStation.workStationId}">
					<c:forEach items="${workStation.moduleList}" var="module">
						<c:if test="${sessionScope.currModuleId==module.moduleId}">	
							<c:if test="${module.hiddenLeft=='Y'}">
							<c:set var="hiddenLeft" value="true" scope="page"></c:set>
							</c:if>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>

			<c:if test="${hiddenLeft == false}">
			<!--menulist-->
			<td  id="left" width="203" height="100%" valign="top">
			<div class="menu_sec" id="menu_sec">
				<%--<div class="info">功能列表</div>--%>
				<div id="menu" class="menu_list">	
    					
    				<c:set var="defaultMenuSet" value="true"/>				
					<c:forEach items="${applicationScope.workStationList}" var="workStation">
						<c:if test="${sessionScope.currWsId==workStation.workStationId}">
							<c:forEach items="${workStation.moduleList}" var="module">
								<c:if test="${sessionScope.currModuleId==module.moduleId}">				
									<c:forEach items="${module.menuSetList}" var="menuSet" varStatus="status">	                            	
					                    <c:set var="canShowMemuSet" value="false" scope="page"></c:set>
										<c:if test="${sessionScope.currWsId=='srm' }">
											<c:set var="canShowMemuSet" value="${pdfn:canShowByVersion(menuSet.version)}" scope="page"></c:set>
										</c:if>
										<c:if test="${sessionScope.currWsId!='srm' }">
											<c:set var="canShowMemuSet" value="true" scope="page"></c:set>
										</c:if>
										<c:if test="${canShowMemuSet == true }">
											<c:if test="${pdfn:contain(menuSet.setId, sessionScope.currUserMenuSetIdList) or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
													<c:if test="${defaultMenuSet}">
													<script>
														$(document).ready(function(){
															showMenuSet('${menuSet.setId}');
															setMenuSetName('${menuSet.setName}');
														});
													</script>
													</c:if>
													<div class="menuSetClass" id="${menuSet.setId}">
														<h1 class="active" align="left"
															onclick="showMenuSet('${menuSet.setId}');setMenuSetName('${menuSet.setName}');">
														<a href="javascript:void(0)">${menuSet.setName}</a>
													</h1>
													<span class="no">
														<ul>
					                            	<c:forEach items="${menuSet.menuList}" var="menu">
					                            		<c:set var="canShowMemu" value="false" scope="page"></c:set>
					                            		<c:if test="${sessionScope.currWsId=='srm' }">
															<c:set var="canShowMemu" value="${pdfn:canShowByVersion(menu.version)}" scope="page"></c:set>
														</c:if>		
														<c:if test="${sessionScope.currWsId!='srm' }">
															<c:set var="canShowMemu" value="true" scope="page"></c:set>
														</c:if>
														<c:if test="${canShowMemu == true }">
															<c:if test="${pdfn:contain(menu.menuId, sessionScope.currUserMenuIdList) or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">

																<c:if test="${(menu.menuId eq 'res-zyys-lzpx-zyysxk' and  resDoctor.doctorCategoryId eq recDocCategoryEnumGraduate.id and  resDoctor.sessionNumber >='2017')
																	or menu.menuId ne 'res-zyys-lzpx-zyysxk' or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
																	<c:if test="${empty  viewUrl && firstModule eq module.moduleId}" >
																		<c:set var="viewUrl" value="${menu.menuUrl}" scope="session"/>
																	</c:if>
																	<c:set var="canShowMenu" value="true" scope="page"></c:set>
																	<c:if test="${(menu.menuId == 'res-yygly-jxgl-sxpjgl' ||
																							menu.menuId == 'res-yygly-jxgl-kspjzbwh' ||
																							menu.menuId == 'res-kzr-jxgl-scjcdf' ||
																							menu.menuId == 'res-km-jxgl-scjcdf' ||
																							menu.menuId == 'res-yygly-ndllkhgl-osceCtrl' ||
																							menu.menuId == 'res-yygly-pbgl-pbdr'||
																							menu.menuId == 'res-yygly-ckgl-thckpjhz'
																							) and cfg13 ne 'Y' }">
																		<c:set var="canShowMenu" value="false" scope="page"></c:set>
																	</c:if>
																	<c:if test="${canShowMenu}">
																		<li>
																		<c:choose>
																			<c:when test="${menu.menuId eq 'gycmis-xy-grxxgl-xjdjb'}">
																				<a class="aClass" id="${menu.menuId}" href="javascript:highlight('${menu.menuId}');loadMain('<s:url value='${menu.menuUrl}'/>');setMenuName('${menu.menuName}');"><font color="red">${menu.menuName}</font></a>
																			</c:when>
																			<c:otherwise>
																				<a class="aClass" id="${menu.menuId}" href="javascript:highlight('${menu.menuId}');<c:if test='${not empty menu.menuUrl}'>loadMain('<s:url value='${menu.menuUrl}'/>','${menu.openType}');</c:if>setMenuName('${menu.menuName}');">${menu.menuName}</a>
																			</c:otherwise>
																		</c:choose>
																		</li>
																	</c:if>
																</c:if>
															</c:if>
														</c:if>
													</c:forEach>
														</ul>
													</span>
													</div>
													<c:set var="defaultMenuSet" value="false"/>
											</c:if>
										</c:if>
									</c:forEach>
								</c:if>  
							</c:forEach>
						</c:if>     	
					</c:forEach>
				<c:if test="${sessionScope.currWsId!='res'and sessionScope.currWsId!='sch'and sessionScope.currWsId!='study'and sessionScope.currWsId!='recruit'}">
					<div class="menuSetClass" id="sys-personal-module-center">
						<h1 align="left" class="active"
							onclick="showMenuSet('sys-personal-module-center');setMenuSetName('个人中心');">
						<a href="javascript:void(0)">个人中心</a>
					</h1>
					<span>
						<ul>
                         	<li><a class="aClass" id="sys-personal-menu-view" href="javascript:highlight('sys-personal-menu-view');loadMain('<s:url value='/sys/user/view'/>?&editFlag=${GlobalConstant.FLAG_Y}');setMenuName('个人信息');">个人信息</a></li>
                         	<li><a class="aClass" id="sys-personal-menu-sec" href="javascript:highlight('sys-personal-menu-sec');loadMain('<s:url value='/sys/user/security'/>');setMenuName('安全中心');">安全中心</a> </li>
						</ul>
					</span>
					</div>
				</c:if>
				</div>
			</div>
			</td>			
			<td id="zip" width="7" height="100%">
				<table width="100%"	height="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td height="100%">
							<div id="bar" onclick="move()" style="height: 20px;cursor:pointer;vertical-align: center;">
								<img id="barimg" width="7px;" src="<s:url value='/css/skin/${skinPath}/images/left_arrow.png'/>"/>
							</div>
						</td>			
					</tr>
				</table>								
		    </td>
		    </c:if>
			<!--notice-->
			<td id="tdNotice"  height="100%" valign="top">
				<table width="100%" height="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" height="37" width="100%">
							<div class="htop">
								<span><b>当前位置：</b><c:if test="${applicationScope.sysCfgMap['sys_index_url']!='/inx/hbres' and 1==2}">${sessionScope.currModuleName }--</c:if><span id="menuSetName"></span><span id="menuName" style="padding-left:5px;"></span></span>
								<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
									<span style="float: right;"><a href='javascript:openCusSer();'>在线客服</a>&#12288;&#12288;&#12288;&#12288;</span>
								</c:if>
								<c:if test="${not empty sessionScope.currModuleView }">
									<%--<span style="float: right;"><a href='<s:url value="${sessionScope.currModuleView }"/>' target="mainIframe">概&#12288;况</a>&#12288;&#12288;</span>						--%>
								</c:if>
								<span style="float: right;"><a id="fullscreen" href='#'>全&#12288;屏</a>&#12288;&#12288;</span>	
								
								<c:if test="${sessionScope.currWsId=='erp' }">
									<c:if test="${!empty sessionScope.currDeptList  && fn:length(sessionScope.currDeptList )>1}">
										<span style="float: right;">
									切换部门：<select id="deptFlow" onchange="changeDept();">
												<c:forEach items="${ sessionScope.currDeptList}" var="userDept">
													<option value="${userDept.deptFlow }"  <c:if test="${ userDept.deptFlow == sessionScope.currUser.deptFlow}">selected</c:if>>${userDept.deptName }</option>
												</c:forEach>								
										</select>&#12288;	&#12288;
										</span>
									</c:if>		
								</c:if>		
							</div>
						</td>
					</tr>
					<tr>
						<td height="100%" valign="top">
							<c:if test="${empty sessionScope.currModuleView }">
								<c:set var="currModuleView" value="/jsp/center.jsp" scope="session" />							
							</c:if>
							<script>

								<c:if test="${not empty param.menuId }">
									$(document).ready(function(){
										$("#${param.menuId}").parents("span").prev().trigger("click");
										document.getElementById("${param.menuId}").click();
									});
								</c:if>
								<c:if test="${empty param.menuId }">
									$(document).ready(function(){
										var src="<s:url value="${currModuleView}"/>";
										if(src.indexOf('?')>-1){
											src = src+"&isMainFrame=Y";
										}else{
											src = src+"?isMainFrame=Y";
										}
										$("#mainIframe")[0].src=src;
									});
								</c:if>
							</script>
							<iframe
									scrolling="yes" frameborder="0" id="mainIframe" name="mainIframe"
									allowtransparency="no" src="<s:url value="/jsp/center.jsp"/>" marginheight="0" marginwidth="0"></iframe></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<c:if test="${applicationScope.sysCfgMap['show_res_new_notice']=='Y'}">
		<jsp:include page="/res/doc/newNoticeList2">
			<jsp:param name="fromSch" value="N"></jsp:param>
		</jsp:include>
	</c:if>
	<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="service.jsp"></jsp:include>
	</c:if>
</body>
</html>
