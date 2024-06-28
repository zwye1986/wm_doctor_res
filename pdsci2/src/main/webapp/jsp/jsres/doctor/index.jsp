	<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="consult" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="login" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
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
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script>
		$(document).ready(function(){
			$(".menu_item a").click(function(){
				$(".select").removeClass("select");
				$(this).addClass("select");
			});
			setBodyHeight();
			<%--if("${recordCount !=0}"=="true"){--%>
			var result=0;
			if("${jsResDocTypeEnumCompany.id}"=="${doctor.doctorTypeId}"&&"单位人"=="${doctor.doctorTypeName}"){
				<%--if("${doctor.workOrgId}"==""){--%>
				result=1;
// 			}
				<%--if("${doctor.workOrgName}"==""){--%>
				result=1;
//			}
			}
			<%--if("${jsResDocTypeEnumGraduate.id}"=="${doctor.doctorTypeId}"){--%>
			<%--if("${GlobalConstant.FLAG_N}"=="${school}"){--%>
			<%--result=1;--%>
			<%--}--%>
			<%--}--%>
			<%--if("${GlobalConstant.FLAG_N}"=="${benkeResult}"){--%>
			<%--result=1;--%>
			<%--}--%>
			<%--if("${GlobalConstant.FLAG_N}"=="${result}"){--%>
			<%--result=1;--%>
			<%--}--%>
			<%--if("${doctor.isYearGraduate}"==""){--%>
			<%--result=1;--%>
			<%--}--%>
			if(result==1){
				jboxOpen("<s:url value='/jsres/doctor/saveForLack'/>",null,650,380);
			}
//	}
		});

		function accounts(){
			jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
		}

		/* 培训信息 */
		function main(recruitFlow){
			var url = "<s:url value='/jsres/doctor/main'/>";
			if(recruitFlow != ""){
				url = url + "?recruitFlow="+ recruitFlow;
			}
			jboxLoad("content", url, true);
		}

		function doctorInfo(){
			jboxLoad("content","<s:url value='/jsp/jsres/doctor/doctorInfo.jsp'/>",true);
		}

		function trainMain(){
			jboxLoad("content","<s:url value='/jsp/jsres/doctor/trainMain.jsp'/>",true);
		}

		/* 结业信息 */
		function graduateMain(){
			jboxLoad("content","<s:url value='/jsp/jsres/doctor/graduate/main.jsp'/>",false);
		}

		/* function registerRecord(){
            jboxLoad("content","<s:url value='/jsp/jsres/doctor/registerRecord.jsp'/>",false);
} */

		/* function addTrainRecord(){
            jboxLoad("content","<s:url value='/jsp/jsres/doctor/trainRecord.jsp'/>",false);
} */

		function opinions(){
			var url = "<s:url value='/jsres/training/opinions'/>";
			jboxStartLoading();
			jboxPost(url,null,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}

		function guides(data){
			var url = "<s:url value='/jsres/training/getGuides/doctor'/>";
			jboxStartLoading();
			jboxPost(url,data,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}
		function lectures(data){
			var url = "<s:url value='/jsres/lecture/doctorLectureView'/>?roleId=doctor";
			jboxStartLoading();
			jboxPost(url,data,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}

		function temporaryOutSearch(form) {
			var url = "<s:url value='/res/manager/temporaryOutSearch'/>?roleId=user&biaoJi=Y";
			jboxStartLoading();
			jboxPost(url, form, function (resp) {
				$("#content").html(resp);
				jboxEndLoading();
			}, null, false);
		}
		function lecturesAppointment(data) {
			var url = "<s:url value='/res/manager/doctorLectureView'/>?roleId=doctor&tabType=new&isNew=Y";
			jboxStartLoading();
			jboxPost(url, data, function (resp) {
				$("#content").html(resp);
				jboxEndLoading();
			}, null, false);
		}
		function lecturesEva(data) {
			var url = "<s:url value='/res/manager/doctorLectureView'/>?roleId=doctor&tabType=history&isEval=Y";
			jboxStartLoading();
			jboxPost(url, data, function (resp) {
				$("#content").html(resp);
				jboxEndLoading();
			}, null, false);
		}
		function selectMenu(menuId,subMenuId){
			$("#"+menuId).find("a").click();
			$(".select").removeClass("select");
			$("#"+menuId).find("a").addClass("select");
			$("#subMenuId").val(subMenuId);
		}
		function msg(){
			var url = "<s:url value='/inx/jsres/allNotice'/>?flag=Y";
			jboxLoad("content", url, false);
		}
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
		function shouye(){
			var url = "<s:url value='/jsres/doctor/index'/>";
			window.location.href=url;
		}
		onresize=function(){
			setBodyHeight();
		}
		function detail(){
			jboxLoad("content","<s:url value='/jsp/jsres/doctor/detail.jsp'/>",false);
		}
		/* function process(){
            jboxLoad("content","<s:url value='/jsres/doctor/process'/>",true);
} */
		function trainRegister(){
			jboxLoad("content","<s:url value='/jsres/doctor/trainRegister?roleFlag=doctor'/>",true);
		}
		function owenScore(){
			jboxLoad("content","<s:url value='/jsres/doctor/owenScore'/>",true);
		}
        //结业证书申请申请
        function certificateApply(){
            jboxLoad("content","<s:url value='/jsres/doctor/certificateManageMain?tabTag=certificateApply'/>",true);
        }
		//考核申请
		function assessmentApplication(data){
            <%--jboxLoad("content","<s:url value='/jsres/doctor/asseApplicationMain'/>",true);--%>
            jboxLoad("content","<s:url value='/jsres/doctor/mainNew?tabTag=FirstTest'/>",true);

			<%--jboxLoad("content","<s:url value='/jsres/doctor/asseApplicationMain'/>",true);--%>

            <%--var url = "<s:url value='/jsres/doctor/asseApplicationMain'/>";--%>
            <%--jboxStartLoading();--%>
            <%--jboxPost(url, data, function (resp) {--%>
                <%--$("#content").html(resp);--%>
                <%--jboxEndLoading();--%>
            <%--}, null, false);--%>
		}
		//补考报名
		function examination(typeId){
			jboxLoad("content","<s:url value='/jsres/examSignUp/main'/>?typeId="+typeId,true);
		}

		//技能考核
		function toOsca(){
			window.open("<s:url value='/jsres/doctor/toOsca'/>");
		}

		//年度理论考试
		function theoreticalExam(){
			jboxLoad("content","<s:url value='/jsres/examCfg/theoreticalExam'/>",true);
		}
		function  showZS()
		{
			jboxLoad("content","<s:url value='/jsres/doctorScoreApply/doctorShowCertificate'/>"+"?doctorFlow="+"${doctor.doctorFlow}",true);
		}
		function toPage(page){
			var currentPage;
			if(page != undefined){
				currentPage=page;
			}
			var url = "<s:url value='/jsres/doctor/index'/>?currentPage="+currentPage;
			window.location.href=url;
		}
		function CertificateManage(){
			var roleFlag="doctor";
			jboxLoad("content","<s:url value='/jsres/certificateManage/freeMain'/>?roleFlag="+roleFlag,true);
		}
		function activityQuery(){
			var roleFlag="doctor";
			jboxLoad("content","<s:url value='/jsres/activityQuery/main'/>?roleFlag="+roleFlag,true);
		}
		// 请假管理
		function leaveSearch(form) {
			jboxLoad("content", "<s:url value='/jsres/attendanceNew/doctorleaveVerifyMain'/>", true);
		}
		function inProcesses(){
			var url = "<s:url value='/jsres/kzr/doctorDeptList'/>";
			jboxLoad("content", url, true);
		}
		function userCenter() {
			jboxLoad("content", "<s:url value='/jsres/manage/userCenter'/>", true);
		}
		// 技能考核预约
        function skillExamOrder(){
            var url = "<s:url value='/jsres/skillTimeConfig/list'/>?isLocal=N&flag=kh";
            jboxLoad("content", url, true);
		}
		//我的技能考核预约
		function mySkillExamOrder() {
            var url = "<s:url value='/jsres/skillTimeConfig/searchOrderedRecord'/>?isLocal=N&flag=mine";
            jboxLoad("content", url, true);
        }
		/**
		 * 结业理论模拟考核
		 */
		function toJYTest(doctorFlow){
			var url = "<s:url value='/jsres/graduation/toJYTest'/>?doctorFlow="+doctorFlow;
			jboxLoad("content", url, true);
		}
		console.error("后台更新属性操作步骤：");
		console.error("第一步点击一下弹框");
		console.error("第二步按键盘输入   上、上、下、下、左、右、左、右、B、A、B、A");
		console.error("输入密码间隔需在0.5秒之内！");
		var code = '383840403739373966656665';//3840403739373966656665上38、上38、下40、下40、左37、右39、左37、右39、B66、A65、B66、A65
		var currCode = '';
		var timeoutObj ;
		function zljh(){
			<%--var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";--%>
			jboxLoad("content","<s:url value='/jsres/message/doctorPlan'/>",true);
		}

		$(function(){
			$(document).on('keyup',function(e){
				currCode+=e.keyCode;
				clearTimeout(timeoutObj);
				timeoutObj = setTimeout(function(){
					currCode = '';
				},500);
				if(currCode==code){
					jboxOpen("<s:url value='/jsres/doctor/saveForLack'/>",null,650,380);
					currCode = '';
				}
			});
		});
		function grpjcxD(){
			var url = "<s:url value='/res/evaDoctorResult/main'/>";
			jboxLoad("content", url, false);
		}

		function teachEva(){
			var url = "<s:url value='/res/evaDoctorResult/teachMain'/>";
			jboxLoad("content", url, false);
		}

		function patientEva(){
			var url = "<s:url value='/res/evaDoctorResult/patientMain'/>";
			jboxLoad("content", url, false);
		}

		$(function () {
			isVerify = '${sessionScope.currUser.isVerify}';
			if(isVerify != 'Y'){
				jboxConfirm("认证手机号码，享受更便捷的登录体验，快去认证吧" , function(){
					accounts();
				});
			}
		});
		console.log()

		//加载报考记录
		function bkjl(){
			jboxLoad("content","<s:url value='/jsres/message/doctorRegister'/>",true);
		}
		//加载报考信息
		function bkxx(recruitFlow){
			var url = "<s:url value='/jsres/message/main'/>";
			if(recruitFlow != ""){
				url = url + "?recruitFlow="+ recruitFlow;
			}
			jboxLoad("content", url, true);
		}
		function reductionApplyMain() {
			jboxLoad("content", "<s:url value='/jsres/reduction/reductionApplyMain'/>", true);
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

			<div class="head">
				<div class="head_inner">
					<h1 class="logo">
						<a onclick="shouye();"><%=JsresUtil.getTitle(request,response,application)%></a>
					</h1>
					<div class="account">
						<h2 style="text-align: right;">您好，<span id="topUserName">${sessionScope.currUser.userName }</span></h2>
						<div class="head_right">
							<a onclick="shouye();">首页</a>&#12288;
							<a onclick="msg();">公告</a>&#12288;
							<a href="<s:url value='/inx/jsres/logout'/>">退出</a>
						</div>
					</div>
				</div>
			</div>

			<div class="body">
				<div class="container">
					<div class="content_side">
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_doctor_info"></i>医师信息<input type="hidden" id="subMenuId" value=""/>
							</dt>
							<dd class="menu_item" id="main"><a onclick="main('');">培训信息</a></dd>
							<dd class="menu_item" id="main"><a onclick="trainRegister();">培训记录</a></dd>
							<c:if test="${sysCfgMap['jsres_is_reduction'] eq GlobalConstant.RECORD_STATUS_Y}">
								<dd class="menu_item"><a onclick="reductionApplyMain();">减免申请</a></dd>
							</c:if>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_doctor_info"></i>招录信息<input type="hidden" id="subMenuId2" value=""/>
							</dt>
							<dd class="menu_item"><a onclick="bkxx('');">报考信息</a></dd>
							<dd class="menu_item"><a onclick="zljh();">学员报名</a></dd>
							<dd class="menu_item"><a onclick="bkjl();">报考记录</a></dd>

							<!-- <dd class="menu_item" id="main"><a onclick="applyChangeList();">培训变更</a></dd> -->
							<!--
                             -->
							<!-- <dd class="menu_item" id="graduateMain"><a onclick="graduateMain();">结业考核申请</a></dd> -->
							<!-- <dd class="menu_item" id="doctorInfo"><a onclick="doctorInfo();">基本信息</a></dd>
                            <dd class="menu_item" id="registerRecord"><a onclick="registerRecord();">注册记录</a></dd>
                            <dd class="menu_item" id="addTrainRecord"><a onclick="addTrainRecord();">添加培训记录</a></dd> -->
						</dl>
						<%--<c:set value="jswjw_${doctor.orgFlow}_P001" var="rotationSwitch"/>--%>
						<%--<c:if test="${sysCfgMap[rotationSwitch] eq GlobalConstant.RECORD_STATUS_Y}">--%>

						<c:set var="key" value="jsres_doctor_app_menu_${doctor.doctorFlow}"/>
						<c:if test="${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y}">
							<dl class="menu">
								<dt class="menu_title">
									<i class="icon_menu menu_process_management"></i>过程管理
								</dt>
<%--								<dd class="menu_item"><a onclick="lectures(null)">讲座信息查询</a></dd>--%>
								<dd class="menu_item"><a onclick="temporaryOutSearch()">临时出科查询</a></dd>
								<dd class="menu_item"><a onclick="lecturesAppointment(null)">讲座活动预约</a></dd>
								<dd class="menu_item"><a onclick="lecturesEva(null)">讲座活动评价</a></dd>
								<dd class="menu_item"><a onclick="opinions();">意见反馈</a></dd>
								<dd class="menu_item"><a onclick="guides(null);">住培指南</a></dd>
							</dl>
							<dl class="menu">
								<dt class="menu_title">
									<i class="icon_menu menu_into_education"></i>入科教育模块
								</dt>
								<dd class="menu_item"><a href="javascript:inProcesses();">入科教育信息规范</a></dd>
							</dl>
							<dl class="menu">
								<dt class="menu_title">
									<i class="icon_menu menu_teach_activity"></i>教学活动管理
								</dt>
								<dd class="menu_item"><a href="javascript:activityQuery();">教学活动查询</a></dd>
							</dl>
						</c:if>
						<c:set value="jsres_hospital_xykqcx_${sessionScope.currUser.orgFlow }" var="key"/>
						<c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
							<dl>
								<dt class="menu_title">
									<i class="icon_menu menu_process_management"></i>考勤管理
								</dt>
								<dd class="menu_item"><a onclick="leaveSearch();">请假管理</a></dd>
							</dl>
						</c:if>
						<c:set value="${not empty doctor.secondOrgFlow ?doctor.secondOrgFlow: doctor.orgFlow}" var="orgFlow"/>
						<c:set value="jsres_hospital_ndkhsz_${orgFlow}" var="key1"/>
						<c:if test="${pdfn:jsresPowerCfgMap(key1) eq GlobalConstant.RECORD_STATUS_Y}">
							<dl class="menu">
								<dt class="menu_title">
									<i class="icon_menu menu_year_check"></i>年度考核<input type="hidden"/>
								</dt>
								<dd class="menu_item"><a onclick="theoreticalExam();">年度理论考试</a></dd>
							</dl>
						</c:if>
						<c:if test="${isPassed}">
							<dl class="menu">
								<dt class="menu_title">
									<i class="icon_menu menu_management_complete"></i>结业管理<input type="hidden" id="subMenuId" value=""/>
								</dt>
								<%--<dd class="menu_item"><a onclick="assessmentApplication(null);">结业申请</a></dd>--%>
								<dd class="menu_item"><a onclick="assessmentApplication(null);">结业考试申请</a></dd>
								<%--<dd class="menu_item"><a onclick="examination('Theory');">补考报名</a></dd>--%>
								<dd class="menu_item"><a onclick="owenScore('');">结业成绩查询</a></dd>
								<dd class="menu_item"><a onclick="certificateApply('');">结业证书管理</a></dd>
								<%--<dd class="menu_item"><a onclick="skillExamOrder('');">技能考核预约</a></dd>--%>
								<%--<dd class="menu_item"><a onclick="mySkillExamOrder('');">我的技能考核</a></dd>--%>
								<c:set value="jsres_doctor_graduation_exam_${sessionScope.currUser.userFlow }" var="key"/>
								<c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
									<dd class="menu_item"><a onclick="toJYTest('${doctor.doctorFlow}');">结业理论模拟考核</a></dd>
								</c:if>
							</dl>
						</c:if>
						<%--<c:if test="${isPassed}">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_management"></i>结业管理<input type="hidden" id="subMenuId" value=""/>
                                </dt>
                                <c:set value="jsres_doctor_graduation_exam_${sessionScope.currUser.userFlow }" var="key"/>
                                <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                    <dd class="menu_item"><a onclick="toJYTest('${doctor.doctorFlow}');">结业理论模拟考核</a></dd>
                                </c:if>
                                <dd class="menu_item"><a onclick="assessmentApplication();">考核申请</a></dd>
                                <dd class="menu_item"><a onclick="examination('Theory');">理论补考报名</a></dd>

                                <dd class="menu_item"><a onclick="toOsca();">技能考核</a></dd>
                                <dd class="menu_item"><a onclick="examination('Skill');">技能补考报名</a></dd>
                                <dd class="menu_item" id="main"><a onclick="owenScore('');">成绩查询</a></dd>
                                <dd class="menu_item"><a onclick="CertificateManage();">证书信息查询</a></dd>
                                <dd class="menu_item" id="main"><a onclick="showZS();">证书查询</a></dd>
                            </dl>
                        </c:if>--%>
						<c:if test="${cfgValue eq 'Y'}">
							<dl class="menu">
								<dt class="menu_title">
									<i class="icon_menu menu_360check"></i>360评价考核
								</dt>
								<dd class="menu_item"><a href="javascript:grpjcxD();">个人评价查询</a></dd>
								<dd class="menu_item"><a href="javascript:teachEva();">带教评价查询</a></dd>
<%--								<dd class="menu_item"><a href="javascript:patientEva();">病人评价查询</a></dd>--%>
							</dl>
						</c:if>



						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_system"></i>设置
							</dt>
							<dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
							<c:if test="${doctor.doctorStatusId == '21'}">
								<dd class="menu_item"><a id="userCenter" href="javascript:userCenter();">个人信息</a></dd>
							</c:if>
						</dl>
					</div>
					<div class="col_main" id="content">
						<div class="content_main">
							<div class="index_show">
								<div class="index_tap top_left">
									<ul class="inner">
										<li class="index_item index_blue">
											<!--                 <a href="javascript:selectMenu('main','doctorInfo');"> -->
											<a href="#">
                  <span class="tap_inner">
                    <i class="message"></i>
                     <em class="number" id="newMsg">${newMsg+0}</em>
                    <strong  class="title">新消息</strong>
                  </span>
											</a>
										</li>
										<li class="index_item index_blue">
											<a href="javascript:selectMenu('main','doctorInfo');">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number" style="font-size: 37px;">${recordCount+0}</em>
                    <strong  class="title">培训记录</strong>
                  </span>
											</a>
										</li>
									</ul>
								</div>
								<div class="index_tap top_right">
									<ul class="inner">
										<li class="index_item index_green">
											<!--                 <a href="javascript:selectMenu('main','trainInfo');"> -->
											<a href="#">
                  <span class="tap_inner">
                    <i class="crowd"></i>
                    <em class="number">
                    ${doctor.doctorStatusName}
                    <c:if test="${ empty doctor.doctorStatusName}">
						<span class="number">无</span>
					</c:if>
                    </em>
                    <strong  class="title">医师状态</strong>
                  </span>
											</a>
										</li>
									</ul>
								</div>
							</div>
							<script>
								function viewMsg(dl,msgFlow){
									var isSelect = dl.className.indexOf("msgselect")<0;
									$(".msgselect").removeClass("msgselect").find("dd").addClass("none");
									if(isSelect){
										$(dl).addClass("msgselect").find("dd").removeClass("none");
									}
									if(dl.className.indexOf("readed")<0){
										jboxPost("<s:url value='/jsres/doctor/readMsg'/>?msgFlow="+msgFlow+"&receiveFlag=${GlobalConstant.FLAG_Y}",null,function(resp){
											if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
												$(dl).addClass("readed");
												var msgNum = parseInt($("#newMsg").text());
												var num=--msgNum;
												if(num<0){
													num=0;
												}
												$("#newMsg").text(num);
											}
										},null,false);
									};
								}
							</script>
							<div class="index_form" style="margin-bottom: 10px;">
								<h3>通知中心</h3>
								<div class="main_bd">
									<div id="notification" class="notificationCenterPage">
										<c:forEach items="${msgList}" var="msg">
											<dl class="notify_item <c:if test="${msg.receiveFlag eq GlobalConstant.FLAG_Y}"> readed</c:if>" onclick="viewMsg(this,'${msg.msgFlow}');" style="lin">
												<dt>
													<a class="notify_title_wrapper">
								<span class="notify_status">
									<span class="notify_time">
											${pdfn:transDate(msg.msgTime)}
									</span>
									<i class="noticearrow"></i>
								</span>
														<span class="notify_title">
									<i class="dot">●</i>
									${msg.msgTitle}
								</span>
													</a>
												</dt>
												<dd class="none" >
														${msg.msgContent}
												</dd>
											</dl>
										</c:forEach>
										<c:if test="${empty msgList}">
											<dl class="notify_item" style="text-align: left;padding-left: 50px;">
												<dt>
													暂无通知！
												</dt>
											</dl>
										</c:if>
									</div>
								</div>
							</div>
							<c:if test="${!empty msgList}">
								<div class="page" style="padding-right: 40px;">
									<input id="currentPage" type="hidden" name="currentPage" value=""/>
									<c:set var="pageView" value="${pdfn:getPageView(msgList)}" scope="request"></c:set>
									<pd:pagination-jsres toPage="toPage"/>
								</div>
							</c:if>
							<!--           </ul> -->
							<!-- 		    <ul> -->
							<!-- 		        <li class="score_frame"> -->
							<!-- 		            <h1>医师信息概况</h1> -->
							<!-- 		            <div class="index_table" > -->
							<!-- 		                <table cellpadding="0" cellspacing="0" width="100%"> -->
							<!-- 		                <colgroup> -->
							<!-- 		                  <col width="50%"/> -->
							<!-- 		                  <col width="50%"/> -->
							<!-- 		                </colgroup> -->
							<!-- 		                <tbody> -->
							<!-- 		                <tr> -->
							<!-- 			                <td class="up" colspan="2"> -->
							<!-- 			                	<h3>培训信息审核进度</h3> -->
							<!-- 								<div class="flowsteps" id="icn"> -->
							<!-- 								  <ol class="num5"> -->
							<!-- 							        <li class="first done"> -->
							<!-- 							          <span><i style="width: 34px;">1</i><em>注册</em></span> -->
							<!-- 							        </li> -->
							<!-- 							        <li class="done"> -->
							<!-- 							          <span><i>2</i><em>基地审核</em></span> -->
							<!-- 							        </li> -->
							<!-- 							        <li class="current"> -->
							<!-- 							          <span><i>3</i><em>市局审核</em></span> -->
							<!-- 							        </li> -->
							<!-- 							        <li> -->
							<!-- 							          <span><i>4</i><em>毕教委审核</em></span> -->
							<!-- 							        </li> -->
							<!-- 							        <li class="last"> -->
							<!-- 							          <span><i>5</i><em>完成</em></span> -->
							<!-- 							        </li> -->
							<!-- 							      </ol> -->
							<!-- 								</div> -->
							<!-- 			                </td> -->
							<!-- 		                </tr> -->
							<!-- 		                <tr> -->
							<!-- 		                	<td class="td_first"> -->
							<!-- 			                	<h3><a href="javascript:selectMenu('main','trainInfo');" style="color: black;">培训信息</a></h3> -->
							<!-- 			                	<dl class="gray"> -->
							<!-- 								<dd class="left">已培养年限修改：<span class="right">等待基地审核...</span></dd> -->
							<!-- 								<dd class="left">结业考核&#12288;&#12288;&#12288;：<span class="right">等待结业考核...</span></dd> -->
							<!-- 								<dd class="left">基地变更申请&#12288;：<span class="right">等待转入基地审核...</span></dd> -->
							<!-- 								</dl> -->
							<!-- 			                </td> -->
							<!-- 			                <td class="td_last"> -->
							<!-- 			                	<h3><a href="javascript:selectMenu('main','trainInfo');" style="color: black;">轮转培训记录</a></h3> -->
							<!-- 			                	<dl class="gray"> -->
							<!-- 								<dd class="left">苏州市广济医院 <span class="right">消化内科&#12288;</span></dd> -->
							<!-- 								<dd class="left">苏州市九龙医院<span class="right">儿科&#12288;</span></dd> -->
							<!-- 								<dd class="left">苏州市九龙医院<span class="right">神经内科&#12288;</span></dd> -->
							<!-- 								</dl> -->
							<!-- 			                </td> -->
							<!-- 		                </tr> -->
							<!-- 		                </tbody> -->
							<!-- 		                </table> -->
							<!-- 		            </div> -->
							<!-- 		        </li> -->
							<!-- 		    </ul> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
	<div class="foot">
		<div class="foot_inner">
			主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司
		</div>
	</div>

</div>

</body>
</html>
