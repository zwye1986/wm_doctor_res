<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
	</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script>
		$(document).ready(function(){
			$(".menu_item a").click(function(){
				$(".select").removeClass("select");
				$(this).addClass("select");
			});
			setBodyHeight();

			getNeedReducation("${orgFlow}",function(result){
				if(result){
					jboxInfo("还有<font color='blue'>"+result+"</font>人需要维护缩减方案！");
					$("#reducationCount").show();
				}
			});
			if("${baseFlag}"!="0"){
				$("#baseFlag").show();
			}
			if("${speFlag}"!="0"){
				$("#speFlag").show();
			}

		});
		function pjjgcx(role){
			var url = "<s:url value='/res/evaluateHospitalResult/base?gradeRole=doctor&role='/>"+role;
			currentJboxLoadNoData("content", url, true);
		}
		function currentJboxLoadNoData(id,geturl,showLoading){
			currentJboxLoad(id,geturl,null,showLoading);
		}
		function currentJboxLoad(id,geturl,data,showLoading){
			if(showLoading){
				jboxStartLoading();
			}
			jboxPost(geturl,data,function(resp){
				if(showLoading){
					jboxEndLoading();
				}
				$('#'+id).html(resp);
			},null,false);
		}
		function getNeedReducation(orgFlow,callBack){
			var url = "<s:url value='/jsres/manage/getNeedReducation'/>";
			jboxPost(url,{orgFlow:orgFlow},function(resp){
				callBack(resp);
			},null,false);
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

		onresize=function(){
			setBodyHeight();
		}
		function hosMain(baseFlow){
			var url =  "<s:url value='/jsres/base/main'/>?baseFlow="+baseFlow;
			currentJboxLoadNoData("content",url,true);
		}
		//社区培训基地维护
		function commuHospital(){
			jboxLoad("content","<s:url value='/jsp/jsres/hospital/hos/commuHospital.jsp'/>",false);
		}
		//审核进度查询
		function auditHosProcess(){
			jboxLoad("content","<s:url value='/jsp/jsres/hospital/hos/auditHosProcess.jsp'/>",false);
		}

		function accounts(){
			jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
		}

		function doctorList(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
		}
		function doctorScoreApplyList(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/doctorScoreApply/provinceDoctorList'/>?roleFlag="+roleFlag,true);
		}
		/*理论成绩*/
		function doctorTheoryList(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
		}
		/*考核资格审查*/
		function asseGraduation(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseGraduationForHos'/>?roleFlag="+roleFlag,true);
		}
        function asseAuditForJointOrg(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseAuditForJointOrgMain'/>?roleFlag="+roleFlag+"&tabTag=FristWait&catSpeId=DoctorTrainingSpe",true);
        }
        function asseAuditForJointOrg2(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseAuditForJointOrgMain'/>?roleFlag="+roleFlag+"&tabTag=FristWait2&catSpeId=AssiGeneral",true);
        }
        function asseAuditList(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseAuditListMain'/>?roleFlag="+roleFlag+"&tabTag=FristList&catSpeId=DoctorTrainingSpe",true);
        }
        function asseAuditList2(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseAuditListMain'/>?roleFlag="+roleFlag+"&tabTag=FristList2&catSpeId=AssiGeneral",true);
        }
        /*技能考核管理*/
        function skillTestManage() {
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/skillTimeConfig/skillTestManage'/>?roleFlag="+roleFlag,true);
        }
		function scoreManage() {
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/scoreManage/main'/>?roleFlag="+roleFlag+"&catSpeId=DoctorTrainingSpe",true);
		}
        function scoreManage2() {
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/scoreManage/main'/>?roleFlag="+roleFlag+"&catSpeId=AssiGeneral",true);
        }
		/*考核资格审查*/
		function asseGraduationForHos(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseGraduationForHos'/>?roleFlag="+roleFlag,true);
		}
		function CertificateManagement(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/doctorScoreApply/certificateManagement'/>?roleFlag="+roleFlag,true);
		}
		function cycle(data){
			var docTypes="";
			<c:forEach items="${jsResDocTypeEnumList}" var="type">
			if(docTypes=="")
			{
				docTypes+="docTypes="+"${type.id}";
			}else{
				docTypes+="&docTypes="+"${type.id}";
			}
			</c:forEach>
			var url = "<s:url value='/jsres/doctorRecruit/cycle'/>?"+docTypes;
			jboxStartLoading();
			jboxPost(url,data,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}
		function cycleResults(data){
			var url = "<s:url value='/jsres/doctorRecruit/cycleResults'/>";
			jboxStartLoading();
			jboxPost(url,data,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}
		function opinions(data){
			var url = "<s:url value='/jsres/training/getOpinions'/>";
			jboxStartLoading();
			jboxPost(url,data,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}

		function guides(){
			var url = "<s:url value='/jsres/training/getGuides/manager'/>";
			jboxStartLoading();
			jboxPost(url,null,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}

		function lectures(data){
			var url = "<s:url value='/jsres/lecture/getLectures'/>";
			jboxStartLoading();
			jboxPost(url,data,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}

		function hsxxgl(){
			jboxLoad("content","<s:url value='/res/nurse/list'/>",true);
		}

		function recruitAuditSearch(currentPage){
			if(currentPage == undefined){
				currentPage =1;
			}
			var url = "<s:url value='/jsres/manage/local/doctor/recruitAuditSearch'/>?source=hospital";
			jboxLoad("content", url, true);
		}
		function recruitSignupSearch(currentPage){
			if(currentPage == undefined){
				currentPage =1;
			}
			var url = "<s:url value='/jsres/manage/province/doctor/recruitSignupSearch'/>?source=hospital";
			jboxLoad("content", url, true);
		}
		//考核申请审核
		function auditGraduate(){
			jboxLoad("content","<s:url value='/jsp/jsres/hospital/doctor/auditGraduates.jsp'/>?source=hospital",false);
		}
		//医师帐号解锁
		function doctorAccountUnlock(){
			jboxLoad("content","<s:url value='/jsp/jsres/hospital/doctor/doctorAccountUnlock.jsp'/>",false);
		}
		//轮转情况查询
		function schDeptInfo(){
			jboxLoad("content","<s:url value='/jsp/jsres/hospital/doctor/schDeptInfo.jsp'/>",false);
		}

		function accountManage(){
			jboxLoad("content","<s:url value='/jsres/doctor/accountManage'/>?source=hospital",true);
		}
		function cfgManage(){
			jboxLoad("content","<s:url value='/jsres/cfgManager/main'/>",true);
		}
		function selectMenu(menuId){
			$("#"+menuId).find("a").click();
		}

		function changeBaseMain(){
			var url = "<s:url value='/jsres/manage/changeBaseMain'/>";
			currentJboxLoadNoData("content", url, true);
		}
		function changeSpeMain(){
			var url = "<s:url value='/jsres/manage/changeSpeMain'/>";
			jboxLoad("content", url, false);
		}

		function doctorTrendMain(){
			jboxLoad("content","<s:url value='/jsres/doctorRecruit/doctorRecruitList'/>",false);
		}
		function shouye(page){
			if (page == null || page == undefined || page == '' || isNaN(page)){
				page=1;
			}
			var url="<s:url value='/jsres/manage/local'/>?currentPage="+page;
			window.location.href=url;
		}
		//培训信息查询
		function trainInfo(){
			jboxLoad("content","<s:url value='/jsres/doctorRecruit/trainInfo'/>",true);
		}

		function reductionRotationOper(){
			data = $("#searchFormReduction").serialize() || {
				degreeType : "${GlobalConstant.FLAG_Y}",
				currentPage : 1,
			};
			var url = "<s:url value='/jsres/manage/reductionRotationOper'/>";
			jboxStartLoading();
			jboxPost(url,data,function(resp){
				jboxEndLoading();
				$("#content").html(resp);
			},null,false);
			var orgFlow=$("#orgFlow2").val();
			getNeedReducation(orgFlow,function(result){
				if(result==0){
					$("#reducationCount").hide();
				}
			});
		}

		function toPage(page){
			if(!page){
				$("#currentPage").val(page);
			}
			shouye(page);
		}
		function statisticsAppUser(){
			<%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUser?userListScope=local'/>",true);--%>
			jboxLoad("content","<s:url value='/jsres/appUseInfo/main?userListScope=local'/>",false);
		}
		//培训基地评估
		function searchEvaluationInfo(){
			currentJboxLoadNoData("content","<s:url value='/jsres/evaluation/showMain'/>",true);
		}
		function baseInfo(){
			var url = "<s:url value='/jsres/manage/baseInfo'/>";
			jboxLoad("content", url, true);
		}
		function searchChangeSpe(){
			var url = "<s:url value='/jsres/manage/searchChangeSpe'/>?viewFlag=Y";
			jboxLoad("content", url, true);
		}
		function speMain(){
			var url = "<s:url value='/jsres/manage/speMain'/>";
			jboxLoad("content", url, true);
		}

		function gradeSearch(){
			var form = $("#gradeSearchForm").serialize() || {"gradeRole":"teacher"};
			var url = "<s:url value='/jsres/manage/gradeSearch'/>?role=${GlobalConstant.USER_LIST_LOCAL}";
			jboxStartLoading();
			jboxPost(url,form,function(resp){
				jboxEndLoading();
				$("#content").html(resp);
			},null,false);
		}
		function backTrain(){
			var url = "<s:url value='/jsres/doctor/backTrainInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
			currentJboxLoadNoData("content", url, true);
		}
		function deptRotationSearch(){
			jboxLoad("content","<s:url value='/jsres/base/doc/schDept'/>",true);
		}
		function docWorkSearch(){
			jboxLoad("content","<s:url value='/jsres/base/docWorkQuery'/>",true);
		}
		function monReportSearch(){
			jboxLoad("content","<s:url value='/jsres/base/showMonthlyReportList'/>",true);
		}
		function teacherWorkload(form){
			var url = "<s:url value='/jsres/manage/teacherWorkload'/>";
			jboxStartLoading();
			jboxPost(url,form,function(resp){
				jboxEndLoading();
				$("#content").html(resp);
			},null,false);
		}
		function statisticsTeachTrain(){
			var url = "<s:url value='/jsres/statistic/searchTeachTrain'/>?roleFlag=local";
			currentJboxLoadNoData("content", url, true);
		}

		function teachTrainMain() {
			var url = "<s:url value='/jsres/statistic/searchTeachTrainMain'/>?roleFlag=local";
			currentJboxLoadNoData("content", url, true);
		}

		function statisticsTeachTrainMain() {
			var url = "<s:url value='/jsres/statistic/statisticsTeachTrainMain'/>?roleFlag=local";
			currentJboxLoadNoData("content", url, true);
		}

		function searchOldTeachTrain() {
			var url = "<s:url value='/jsres/statistic/searchOldTeachTrain'/>?roleFlag=local";
			currentJboxLoadNoData("content", url, true);
		}

        function localAppUse() {
            jboxLoad("content","<s:url value='/jsres/manage/localAppUseNew'/>?roleFlag=local",true);
        }
        function localDoctorDataMonthReport() {
            jboxLoad("content","<s:url value='/jsres/manage/localDoctorDataMonthReport'/>",true);
        }
        function localJiaoxueActive() {
            <%--jboxLoad("content","<s:url value='/jsres/manage/localJiaoxueActive'/>",true);--%>
            jboxLoad("content","<s:url value='/jsres/manage/localActivity'/>",true);
        }
        function doctorOutDept() {
            currentJboxLoadNoData("content","<s:url value='/jsres/manage/doctorOutDept'/>",true);
        }
        function localDoctorException() {
            <%--jboxLoad("content","<s:url value='/jsres/manage/localDoctorException'/>",true);--%>
            jboxLoad("content","<s:url value='/jsres/manage/localDoctorExceptionNew'/>",true);
        }
		function delay(){
			var url = "<s:url value='/jsres/doctor/delay'/>";
			currentJboxLoadNoData("content", url, true);
		}
		//理论考试资格审查信息
		function examInfoCheck(){
			var url = "<s:url value='/jsres/doctor/searchExamInfo'/>?sessionNumber=${pdfn:getCurrYear()}";
			currentJboxLoadNoData("content", url, true);
		}
		//黑名单信息查询
		function searchBlackInfo(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			var url = "<s:url value='/jsres/blackList/blackListInfo'/>?sessionNumber=${pdfn:getCurrYear()}&currentPage=1&roleFlag="+roleFlag;
			jboxLoad("content", url, true);
		}
		//查看考勤信息
		function attendanceSearch(form){
			var url="<s:url value='/jsres/teacher/attendanceSearch/hospital'/>";
			jboxStartLoading();
			jboxPost(url,form,function(resp){
				$("#content").html(resp);
				jboxEndLoading();
			},null,false);
		}
		function docProcessEval(form){
			var url="<s:url value='/jsres/hospital/docProcessEvalMain'/>?isQuery=Y";
			jboxLoad("content",url,true);
		}
		function CertificateManage(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/certificateManage/freeMain'/>?roleFlag="+roleFlag,true);
		}
		function certificateManage(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/certificateManage/mainNew'/>?roleFlag="+roleFlag+"&tabTag=CertificateSearch&catSpeId=DoctorTrainingSpe",true);
		}
        function certificateManage2(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/certificateManage/main'/>?roleFlag="+roleFlag+"&catSpeId=AssiGeneral",true);
        }
		function zlDocQuery(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/main'/>?roleFlag="+roleFlag,true);
		}
		function zltjOrg(){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/zltjOrgLocal'/>?roleFlag="+roleFlag,true);
		}
		function signUpmain(typeId){
			var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
			jboxLoad("content","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag="+roleFlag+"&typeId="+typeId,true);
		}
        function signSetUp() {
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/hospital/signMain'/>",true);
        }
		function afterDataDel(){
			jboxLoad("content","<s:url value='/jsres/afterDataManager/afterDataDel'/>",true);
		}
		function afterDataBack(){
			jboxLoad("content","<s:url value='/jsres/afterDataManager/afterDataBack'/>",true);
		}
		function baseAllocationDetails(){
			var url = "<s:url value='/res/resFunds/queryBaseAllocationDetails'/>?role=hospital";
			currentJboxLoadNoData("content", url, true);
		}
        function supervisioPlanMain(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/supervisio/planMain'/>?roleFlag="+roleFlag,true);
        }

		function zljh(){
			<%--var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";--%>
			var orgFlow = "${sessionScope.currUser.orgFlow}";
			jboxLoad("content","<s:url value='/jsres/message/plan?'/>"+"orgFlow="+orgFlow,true);
		}
		
		/* 住培招录计划 */
		function planList(assignYear,currentPage){
			var orgFlow = "${sessionScope.currUser.orgFlow}";
			jboxLoad("content","<s:url value='/jsres/message/plan'/>?assignYear="+assignYear+"&currentPage="+currentPage
					+"&orgFlow="+orgFlow+"&source=${GlobalConstant.USER_LIST_LOCAL}",true);
		}

		/* 公开的住培招录计划 */
		function fakePlanList(assignYear,currentPage){
			var orgFlow = "${sessionScope.currUser.orgFlow}";
			jboxLoad("content","<s:url value='/jsres/message/plan'/>?assignYear="+assignYear+"&currentPage="+currentPage
					+"&orgFlow="+orgFlow+"&fakeFlag=Y&source=${GlobalConstant.USER_LIST_LOCAL}",true);
		}

		function bmsh(){
			<%--var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";--%>
			jboxLoad("content","<s:url value='/jsres/message/localSignupConfirm'/>",true);
		}

		function xycjgl(){//学员成绩管理
			jboxLoad("content","<s:url value='/jsres/hospital/achievementsDetails'/>",true);
		}

		function zlxygl(){//招录学员管理
			jboxLoad("content","<s:url value='/jsres/hospital/achievements?sortType=Total'/>",true);
		}

		function mscjgl(){//招录成绩管理
			jboxLoad("content","<s:url value='/jsres/hospital/achievements?achievementType=interview'/>",true);
		}

		$(function () {
			isVerify = '${sessionScope.currUser.isVerify}';
			if(isVerify != 'Y'){
				jboxConfirm("认证手机号码，享受更便捷的登录体验，快去认证吧" , function(){
					accounts();
				});
			}
		});
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
						<h2 class="head_right">${sessionScope.currUser.orgName }</h2>
						<div class="head_right">
							<a onclick="shouye();">首页</a>&#12288;
							<a href="<s:url value='/inx/jsres/logout'/>">退出</a>
						</div>
					</div>
				</div>
			</div>

			<div class="body">
				<div class="container">
					<div class="content_side">
						<input  type="hidden" id="orgFlow2" value='${orgFlow}'/>
						<c:set value="jswjw_${currUser.orgFlow}_P001" var="rotationSwitch"/>
						<dl class="menu menu_first">
							<dt class="menu_title">
								<i class="icon_menu menu_function"></i>基地信息管理
							</dt>
							<dd class="menu_item"><a href="javascript:baseInfo();">科室师资维护</a></dd>
							<dd class="menu_item"><a href="javascript:baseAllocationDetails();">基地拨付详情</a></dd>
							<dd class="menu_item"><a href="javascript:supervisioPlanMain();">基地督导评估</a></dd>
						</dl>
						<%--<dl class="menu">--%>
						<%--<dt class="menu_title">--%>
						<%--<i class="icon_menu menu_setup"></i>360考核评价--%>
						<%--</dt>--%>
						<%--<dd class="menu_item"><a href="javascript:pjjgcx('global');">评价结果查询</a></dd>--%>
						<%--</dl>--%>
						<%--<dl class="menu">--%>
						<%--<dt class="menu_title">--%>
						<%--<i class="icon_menu menu_management"></i>人员管理--%>
						<%--</dt>--%>
						<%--<dd class="menu_item"><a onclick="hsxxgl();">护士信息管理</a></dd>--%>
						<%--</dl>--%>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_management"></i>招录信息管理
							</dt>
							<c:set value="jsres_hospital_xzzljh_${sessionScope.currUser.orgFlow }" var="key"/>
							<c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
								<dd class="menu_item"><a onclick="zljh();">招录计划管理</a></dd>
							</c:if>
							<dd class="menu_item"><a onclick="bmsh();">招录报名审核</a></dd>
							<dd class="menu_item"><a onclick="xycjgl();">招录成绩管理</a></dd>
							<dd class="menu_item"><a onclick="zlxygl();">招录录取管理</a></dd>
							<%--<dd class="menu_item" id="recruitSignupSearch"><a onclick="recruitSignupSearch();">学员报到审核</a></dd>--%>
							<%--<dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">学员信息审核</a></dd>--%>
							<dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">学员报到审核</a></dd>
							<dd class="menu_item"><a onclick="zlDocQuery();">招录学员查询</a></dd>
							<dd class="menu_item"><a onclick="zltjOrg();">招录学员统计</a></dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_management"></i>异动学员管理
							</dt>
							<dd class="menu_item"><a onclick="changeBaseMain();">基地变更审核
								<%--<img  id="baseFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
							</a>
							</dd>
							<dd class="menu_item"><a onclick="speMain();">专业变更管理
								<%--<img  id="speFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
							</a></dd>
							<dd class="menu_item"><a onclick="delay();">延期学员查询</a></dd>
							<dd class="menu_item"><a href="javascript:backTrain();">退培学员查询</a></dd>
							<dd class="menu_item"><a href="javascript:searchBlackInfo();">黑名单查询</a></dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_management"></i>入科信息管理
							</dt>
							<dd class="menu_item" id="reductionRotationOper"><a onclick="reductionRotationOper();">方案减免维护
								<%--<img  id="reducationCount" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
							</a>
							</dd>
							</dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_management"></i>培训过程查询
							</dt>
							<dd class="menu_item" id="doctorList"><a onclick="doctorList();">学员信息查询</a></dd>
							</dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_statistics"></i>住院医师结业信息管理
							</dt>
							<%--<dd class="menu_item"><a href="javascript:asseGraduation();">资格审查</a></dd>--%>
							<dd class="menu_item"><a href="javascript:asseAuditForJointOrg();">结业考核资格审核</a>
							<dd class="menu_item"><a onclick="asseAuditList();">结业考核资格查询</a></dd>
							<%--<dd class="menu_item"><a href="javascript:skillTestManage();">技能考核管理</a></dd>--%>
							<dd class="menu_item"><a onclick="scoreManage()">结业成绩管理</a></dd>
							<%--<dd class="menu_item"><a href="javascript:signUpmain();">补考审核</a></dd>--%>
							<%--<dd class="menu_item"><a onclick="signSetUp();">签名图片维护</a></dd>--%>
							<dd class="menu_item"><a onclick="certificateManage();">结业证书管理</a></dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_statistics"></i>助理全科结业信息管理
							</dt>
							<dd class="menu_item"><a href="javascript:asseAuditForJointOrg2();">结业考核资格审核</a>
							<dd class="menu_item"><a onclick="asseAuditList2();">结业考核资格查询</a></dd>
							<%--<dd class="menu_item"><a href="javascript:skillTestManage();">技能考核管理</a></dd>--%>
							<dd class="menu_item"><a onclick="scoreManage2()">结业成绩管理</a></dd>
							<%--<dd class="menu_item"><a onclick="signSetUp();">签名图片维护</a></dd>--%>
							<dd class="menu_item"><a onclick="certificateManage2();">结业证书管理</a></dd>
						</dl>
						<%-- <dl class="menu">
                             <dt class="menu_title">
                                 <i class="icon_menu menu_statistics"></i>结业信息管理
                             </dt>
                             <dd class="menu_item"><a href="javascript:asseGraduationForHos();">考核资格审查</a></dd>
                             <dd class="menu_item"><a href="javascript:signUpmain('Theory');">理论补考查询</a></dd>
                             <dd class="menu_item"><a href="javascript:signUpmain('Skill');">技能补考查询</a></dd>
                             <dd class="menu_item" id="doctorScoreApplyList"><a onclick="doctorScoreApplyList();">证书申请查询</a></dd>
                             <dd class="menu_item"><a href="javascript:doctorTheoryList();">结业成绩查询</a></dd>
                             <dd class="menu_item"><a href="javascript:CertificateManagement();">学员结业证书查询</a></dd>
                             <dd class="menu_item"><a onclick="CertificateManage();">证书信息查询</a></dd>
                         </dl>--%>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_management"></i>师资管理
							</dt>
							<dd class="menu_item"><a onclick="teachTrainMain()">师资信息管理</a></dd>
							<dd class="menu_item"><a onclick="statisticsTeachTrainMain()">师资培训统计</a></dd>
							<dd class="menu_item"><a onclick="searchOldTeachTrain()">师资历史数据</a></dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_statistics"></i>统计分析
							</dt>
<%--							<dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>--%>
							<dd class="menu_item"><a onclick="localAppUse()">app使用情况月报</a></dd>
							<dd class="menu_item"><a onclick="localDoctorDataMonthReport()">学员轮转数据月报</a></dd>
							<dd class="menu_item"><a onclick="localJiaoxueActive()">教学活动开展统计</a></dd>
							<dd class="menu_item"><a href="javascript:doctorOutDept();">学员出科情况统计</a></dd>
							<dd class="menu_item"><a onclick="localDoctorException()">学员轮转异常统计</a></dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_setup"></i>设置
							</dt>
							<c:set value="jsres_${sessionScope.currUser.orgFlow }_daoru" var="orgFlow"/>
							<c:if test="${pdfn:jsresPowerCfgMap(orgFlow) eq GlobalConstant.RECORD_STATUS_Y}">
								<dd class="menu_item"><a href="javascript:cfgManage();">参数配置</a></dd>
							</c:if>
							<dd class="menu_item"><a href="javascript:accountManage();">学员账号管理</a></dd>
							<dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
						</dl>
					</div>
					<div class="col_main" id="content">
						<div class="content_main">
							<div class="index_show">
								<div class="index_tap top_left">
									<ul class="inner">
										<li class="index_item index_blue">
											<a href="javascript:selectMenu('recruitAuditSearch');">
                  <span class="tap_inner">
                    <i class="message"></i>
                    <em class="number">${waitCount}</em>
                    <strong  class="title">培训信息待审核</strong>
                  </span>
											</a>
										</li>
										<li class="index_item index_blue">
											<a href="#">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number">${passCount}</em>
                    <strong  class="title">${sysCfgMap['jsres_doctorCount_sessionNumber']}
     					<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">届</c:if>培训医师数</strong>
                  </span>
											</a>
										</li>
									</ul>
								</div>
								<div class="index_tap top_right">
									<ul class="inner">
										<li class="index_item index_green">
											<a href="javascript:selectMenu('doctorScoreApplyList');">
                  <span class="tap_inner">
                    <i class="crowd"></i>
                    <em class="number">${waitPassCount}</em>
                    <strong  class="title">待结业资格审核</strong>
                  </span>
											</a>
										</li>
									</ul>
								</div>
							</div>

							<c:set value="jswjw_${currUser.orgFlow}_P0015" var="orgFlow"/>
							<c:if test="${sysCfgMap[orgFlow] eq GlobalConstant.RECORD_STATUS_Y}">
								<div class="index_form" style="margin-bottom: 10px;">
									<script>
										var okList;
										var currFlag = false;
										var viewLength = 5;
										var likeSearchColumn = 3;

										$(function(){
											okList = $('#willOutTable .willOutData');
											toOperData();
										});

										function toOperData(){
											var docCount = okList.length || 0;
											if(docCount>viewLength){
												okList.filter(':gt('+(viewLength-1)+')').toggle(currFlag);
												$('#s').toggle(!currFlag);
												$('#h').toggle(currFlag);
												$('#docCount').text(docCount-viewLength);
											}else{
												$('#s,#h').hide();
											}
											$('#noData').toggle(docCount==0);
										}

										function more(flag){
											currFlag = flag;
											okList.filter(':gt('+(viewLength-1)+')').toggle(currFlag);
											$('#s,#h').toggle();
										}

										function searchMethod(theAttr){
											okList = $('#willOutTable .willOutData');
											theAttr = $.trim(theAttr);
											if(theAttr!=''){
												okList.hide();
												okList = okList.filter(':has(td:lt('+likeSearchColumn+'):contains("'+theAttr+'"))').show();
												toOperData();
											}else{
												okList.show();
												toOperData();
											}
										}
									</script>
									<table id="willOutTable" border="0" cellpadding="0" cellspacing="0" class="grid">
										<colgroup>
											<col width="20%"/>
											<col width="10%"/>
											<col width="10%"/>
											<col width="15%"/>
											<col width="20%"/>
											<col width="15%"/>
											<col width="10%"/>
										</colgroup>
										<tr>
											<th colspan="7" style="text-align: left;padding-left: 10px;">
												当月即将出科学员查询
												<div style="float: right;">
													搜索：
													<input type="text" class="input" onkeyup="searchMethod(this.value);" placeholder="轮转科室/姓名/届别"/>
												</div>
											</th>
										</tr>
										<tr>
											<th>轮转科室</th>
											<th>姓名</th>
											<th>届别</th>
											<th>培训专业</th>
											<th>轮转时间</th>
											<th>带教老师</th>
											<th>出科状态</th>
										</tr>
										<c:forEach items="${trainingList}" var="training">
											<tr class="willOutData">
												<c:set var="status" value="${training.doctorFlow}${training.schDeptFlow}"></c:set>
												<td>${training.schDeptName}</td>
												<td>${training.doctorName}</td>
												<td>${training.sessionNumber}</td>
												<td>${training.trainingSpeName}</td>
												<td>${training.schStartDate} 至 ${training.schEndDate}</td>
												<td>${training.teacherUserName}</td>
												<td>${stateMap[status]}</td>
											</tr>
										</c:forEach>

										<tr>
											<td id="noData" colspan="7" style="text-align: center;<c:if test="${!empty trainingList}">display: none;</c:if>">无记录!</td>
										</tr>

										<c:if test="${not empty trainingList}">
											<tr id="s" style="display: none;">
												<td  colspan="7">
													<a onclick="more(true);">还有<font id="docCount"></font>个学员，点击查看</a>
												</td>
											</tr>
											<tr id="h" style="display: none;">
												<td  colspan="7"> <a onclick="more(false);">收起</a></td>
											</tr>
										</c:if>
									</table>
								</div>
							</c:if>

							<div class="index_form" style="margin-bottom: 10px;">
								<h3>通知公告</h3>
								<ul class="form_main">
									<c:forEach items="${infos}" var="msg">
										<li>
											<strong><a href="<s:url value='/inx/jsres/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
												<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
													<i class="new1"></i>
												</c:if>
											</strong>
											<span>${pdfn:transDate(msg.createTime)}</span>
										</li>
									</c:forEach>
									<c:if test="${empty infos}">
										<li>
											<strong>无记录!</strong>
										</li>
									</c:if>
								</ul>
							</div>
							<div class="page">
           <span>
             <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
             <c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
	  	     <pd:pagination-jsres toPage="toPage"/>
           </span>
							</div>
						</div>
					</div>
				</div>
				<!--         <div class="main_bd"> -->
				<!-- 		    <ul> -->
				<!-- 		        <li class="score_frame"> -->
				<!-- 		            <h1>基地信息概况</h1> -->
				<!-- 		            <div class="index_table" > -->
				<!-- 		                <table cellpadding="0" cellspacing="0" width="100%"> -->
				<!-- 		                <colgroup> -->
				<!-- 		                  <col width="50%"/> -->
				<!-- 		                  <col width="50%"/> -->
				<!-- 		                </colgroup> -->
				<!-- 		                <tbody> -->
				<!-- 		                <tr> -->
				<!-- 			                <td class="up" colspan="2"> -->
				<!-- 			                	<h3>基地审核进度</h3> -->
				<!-- 								<div class="flowsteps" id="icn"> -->
				<!-- 								  <ol class="num4"> -->
				<!-- 							        <li class="first done"> -->
				<!-- 							          <span><i>1</i><em>基地申请</em></span> -->
				<!-- 							        </li> -->
				<!-- 							        <li class="done"> -->
				<!-- 							          <span><i>2</i><em>市局审核</em></span> -->
				<!-- 							        </li> -->
				<!-- 							        <li class="current"> -->
				<!-- 							          <span><i>3</i><em>毕教委审核</em></span> -->
				<!-- 							        </li> -->
				<!-- 							        <li class="last"> -->
				<!-- 							          <span><i>4</i><em>完成</em></span> -->
				<!-- 							        </li> -->
				<!-- 							      </ol> -->
				<!-- 								</div> -->
				<!-- 			                </td> -->
				<!-- 		                </tr> -->
				<!-- 		                <tr> -->
				<!-- 		                	<td class="td_first"> -->
				<!-- 			                	<h3><a href="javascript:selectMenu('doctorList');" style="color: black;">注册概况</a></h3> -->
				<!-- 			                	<dl class="gray"> -->
				<!-- 								<dd class="left">2015届<span class="right">注册医师：65，审核通过：13</span></dd> -->
				<!-- 								<dd class="left">2014届<span class="right">注册医师：61，审核通过：60</span></dd> -->
				<!-- 								<dd class="left">2013届<span class="right">注册医师：59，审核通过：59</span></dd> -->
				<!-- 								<dd class="left">:</dd> -->
				<!-- 								</dl> -->
				<!-- 			                </td> -->
				<!-- 			                <td class="td_last"> -->
				<!-- 			                	<h3><a href="javascript:selectMenu('auditGraduate');" style="color: black;">结业考核</a></h3> -->
				<!-- 			                	<dl class="gray"> -->
				<!-- 								<dd class="left">考核申请数： <span class="right">12</span></dd> -->
				<!-- 								<dd class="left">待审核数&#12288;：<span class="right">3</span></dd> -->
				<!-- 								<dd class="left">审核通过数：<span class="right">9</span></dd> -->
				<!-- 								</dl> -->
				<!-- 			                </td> -->
				<!-- 		                </tr> -->
				<!-- 		                </tbody> -->
				<!-- 		                </table> -->
				<!-- 		            </div> -->
				<!-- 		        </li> -->
				<!-- 		    </ul> -->
				<!-- 		</div> -->
			</div>
		</div>
	</div>
	<div class="foot">
		<div class="foot_inner">
			主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司
		</div>
	</div>
</div>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
	<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
</c:if>



</body>
</html>
