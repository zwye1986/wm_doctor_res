<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script>
		require.config({
			paths: {
				echarts: "<s:url value='/js/echarts'/>"
			}
		});

		require(['echarts','echarts/chart/pie'],function(ec){
			var myChart = ec.init(document.getElementById('hosChart'));
			option = {
				tooltip : {
					trigger: 'item',
					formatter: "{b}"
				},
				title : {
					text: '数据自2014年起',
					x:'left',
					y:'bottom'
				},
				series : [
					{

						type:'pie',
						radius : '65%',
						itemStyle : {
							normal : {
								label : {
									show : true
								},
								labelLine : {
									show : true
								}
							}
						},
						data:[
							{value:"${currDocSumBef2014['_20count']}", name:'在培人数：${currDocSumBef2014['_20count']}人'},
							{value:"${currDocSumBef2014['_22count']}", name:'已考核待结业人数：${currDocSumBef2014['_22count']}人'},
							{value:"${currDocSumBef2014['_21count']}", name:'结业人数：${currDocSumBef2014['_21count']}人'}
						]
					}
				]
			};
			// 为echarts对象加载数据
			myChart.setOption(option);
		});
	</script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
	$(document).ready(function(){
		$(".menu_item a").click(function(){
			$(".select").removeClass("select");
			$(this).addClass("select");
		});
		setBodyHeight();
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
	function getNeedReducation(orgFlow,callBack){
		var url = "<s:url value='/jsres/manage/getNeedReducation'/>";
		jboxPost(url,{orgFlow:orgFlow},function(resp){
			callBack(resp);
		},null,false);
	}
	//医师信息查询
	function doctorList(){
		var roleFlag="${GlobalConstant.USER_LIST_UNIVERSITY}";
		jboxLoad("content","<s:url value='/jsres/doctorRecruit/universityDoctorList'/>?roleFlag="+roleFlag,true);
	}
	//基地变更查询
	function changeBaseMain(){
		var url = "<s:url value='/jsres/manage/changeBaseMainForUni'/>";
		jboxLoad("content", url, true);
	}
	//专业变更查询
	function speMain(){
		var url = "<s:url value='/jsres/manage/searchChangeSpeForUni'/>";
		jboxLoad("content", url, true);
	}
	//延期医师查询
	function delay(){
		var url = "<s:url value='/jsres/doctor/delayForUni'/>";
		jboxLoad("content", url, false);
	}
	//退培医师查询
	function backTrain(){
		var url = "<s:url value='/jsres/doctor/backTrainInfoForUni?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
		jboxLoad("content", url, true);
	}
	//医师轮转查询
	function cycle(data){
		var url = "<s:url value='/jsres/doctorRecruit/cycleForUni'/>";
		jboxStartLoading();
		jboxPost(url,data,function(resp){
			$("#content").html(resp);
			jboxEndLoading();
		},null,false);
	}
	//医师出科成绩查询
	function cycleResults(data){
		var url = "<s:url value='/jsres/doctorRecruit/cycleResultsForUni'/>";
		jboxStartLoading();
		jboxPost(url,data,function(resp){
			$("#content").html(resp);
			jboxEndLoading();
		},null,false);
	}
	//双向评价
	function gradeSearch(){
		var form = $("#gradeSearchForm").serialize() || {"gradeRole":"teacher"};
		var url = "<s:url value='/jsres/manage/gradeSearchForUni'/>";
		jboxStartLoading();
		jboxPost(url,form,function(resp){
			jboxEndLoading();
			$("#content").html(resp);
		},null,false);
	}
	//医师工作量查询
	function docWorkSearch(){
		jboxLoad("content","<s:url value='/jsres/base/docWorkQueryForUni'/>?isMenu=Y",true);
	}
	//基地带教审核情况
	function orgTeaAuditInfo(){
		jboxLoad("content","<s:url value='/jsres/base/uni/orgTeaAuditInfo'/>",true);
	}
	//学员手册查询
	function manualSearch(){
		jboxLoad("content","<s:url value='/jsres/doctor/manualSearchForUni'/>",true);
	}
	//学员考勤查询
	function attendanceSearch(form){
		var url="<s:url value='/jsres/teacher/attendanceSearchForUni'/>";
		jboxStartLoading();
		jboxPost(url,form,function(resp){
			$("#content").html(resp);
			jboxEndLoading();
		},null,false);
	}
	//住培意见反馈
	function opinions(data){
		var url = "<s:url value='/jsres/training/getOpinionsForUni'/>";
		jboxStartLoading();
		jboxPost(url,data,function(resp){
			$("#content").html(resp);
			jboxEndLoading();
		},null,false);
	}
	//住培指南
	function guides(){
		var url = "<s:url value='/jsres/training/getGuidesForUni'/>";
		jboxStartLoading();
		jboxPost(url,null,function(resp){
			$("#content").html(resp);
			jboxEndLoading();
		},null,false);
	}
	//证书申请查询
	function doctorScoreApplyList(){
		jboxLoad("content","<s:url value='/jsres/doctorScoreApply/provinceDoctorListForUni'/>",true);
	}
	/*结业合格人员*/
	function theoryPassedList(){
		var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
		jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/theoryPassedListForUni'/>",true);
	}
	//医师结业成绩查询
	function doctorTheoryList(){
		var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
		jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorSkillListForUni'/>",true);
	}
	//医师结业证书查询
	function CertificateManagement(){
		jboxLoad("content","<s:url value='/jsres/doctorScoreApply/certificateManagementForUni'/>",true);
	}

	$(document).ready(function(){
		searchInfo();
		setTimeout(searchInfo2, 3000);
	});
	function doctorActivityStatistics(){
		jboxLoad("content","<s:url value='/jsres/doctorActivityStatistics/main'/>?roleFlag=university&baseFlag=1",true);
	}
	function activityQuery(){
		var roleFlag="university";
		jboxLoad("content","<s:url value='/jsres/activityQuery/main'/>?roleFlag="+roleFlag,true);
	}
	//统计查询1
	function searchInfo()
	{
		var geturl="<s:url value='/jsres/manage/doctorNumForUni1'/>?"+$("#doctorNumSearch").serialize();
		$.ajax({
			type : "get",
			url : geturl,
			cache : false,
			beforeSend : function(){
				jboxStartLoading();
			},
			success : function(resp) {
				$("#doctorNum1").html(resp);
			},
			error : function() {
			},
			complete : function(){
				jboxEndLoading();
			}
		});
	}
	//统计查询2
	function searchInfo2()
	{
		var geturl="<s:url value='/jsres/manage/doctorNumForUni2'/>?"+$("#doctorNumSearch2").serialize();
		$.ajax({
			type : "get",
			url : geturl,
			cache : false,
			beforeSend : function(){
				jboxStartLoading();
			},
			success : function(resp) {
				$("#doctorNum2").html(resp);
			},
			error : function() {
			},
			complete : function(){
				jboxEndLoading();
			}
		});
	}

	function daochu() {
		var url = "<s:url value='/jsres/manage/daochu'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#doctorNumSearch"), url, null, null, false);
		jboxEndLoading();
	}
	function daochu2() {
		var url = "<s:url value='/jsres/manage/daochu2'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#doctorNumSearch2"), url, null, null, false);
		jboxEndLoading();
	}
	//月报
	function monthlyReport(){
		window.open("<s:url value='/jsres/monthlyReportUniversity/main'/>","_blank")
	}
    /*app使用情况月报*/
    function appUse(){
        jboxLoad("content","<s:url value='/res/monthlyReportGlobal/appUseInfo'/>",false);
        <%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
    }
    function jiaoxueActive(){
        jboxLoad("content","<s:url value='/res/monthlyReportGlobal/teachActiveInfo'/>",false);
        <%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
    }
    function doctorOutOffice() {
        jboxLoad("content","<s:url value='/res/monthlyReportGlobal/doctorOutOfficeInfo'/>",false);
    }
    function rotationStatistics(){
        var roleFlag="${GlobalConstant.USER_LIST_UNIVERSITY}";
        jboxLoad("content","<s:url value='/jsres/manage/rotationStatistics'/>?roleFlag="+roleFlag,true);
    }
    function personStatistic(){
        var roleFlag="${GlobalConstant.USER_LIST_UNIVERSITY}";
        jboxLoad("content","<s:url value='/jsres/manage/personStatistic'/>?roleFlag="+roleFlag,true);
    }

    // 带教工作量查询
	function teaWorkSearch(){
        jboxLoad("content","<s:url value='/jsres/universityMgr/teacherWorkload'/>",true);
	}
	function statisticsAppUser() {
		jboxLoad("content", "<s:url value='/jsres/universityMgr/main?userListScope=university'/>", false);
	}

	function activityStatistics() {
		jboxLoad("content","<s:url value='/jsres/activityQuery/activityStatistics'/>",true);
	}

</script>
<style>
</style>
</head>

<body>
<div id="indexBody">
<div class="bd_bg">
<div class="yw">

<div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="<s:url value='/jsres/manage/university'/>"><%=JsresUtil.getTitle(request,response,application)%></a>
     </h1>
     <div class="account">
       <h2 class="head_right">${sessionScope.currUser.orgName } &#12288; ${sessionScope.currUser.school }</h2>
       <div class="head_right">
		   <!--        引入切换角色功能 -->
		   <jsp:include page="/jsp/jsres/changeSchool.jsp"></jsp:include>
        <a href="<s:url value='/jsres/manage/university'/>">首页</a>&#12288;
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
            <i class="icon_menu menu_management"></i>医师信息管理
          </dt>
          <dd class="menu_item"><a href="javascript:doctorList();">医师信息查询</a></dd>
		  <dd class="menu_item">
			  <a onclick="changeBaseMain();">基地变更查询
				<%--<img style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
			  </a>
		  </dd>
		  <dd class="menu_item">
			  <a onclick="speMain();">专业变更查询
				<%--<img  style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
			  </a>
		  </dd>
		  <dd class="menu_item"><a onclick="delay();">延期医师查询</a></dd>
		  <dd class="menu_item"><a href="javascript:backTrain();">退培医师查询</a></dd>
        </dl>
		 <c:set var="key" value="jsres_sendSchool_gc_${org.sendSchoolId}"/>
		 <c:if test="${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y and dict.checkStatusId == 'Passed'}">
			 <c:set var="key1" value="jsres_sendSchool_yslccx_${org.sendSchoolId}"/>
			 <c:set var="key2" value="jsres_sendSchool_ysckdjcx_${org.sendSchoolId}"/>
			 <c:set var="key3" value="jsres_sendSchool_sxpjck_${org.sendSchoolId}"/>
			 <c:set var="key4" value="jsres_sendSchool_ysgzlcx_${org.sendSchoolId}"/>
			 <c:set var="key5" value="jsres_sendSchool_xykqcx_${org.sendSchoolId}"/>
			 <c:set var="key6" value="jsres_sendSchool_jddjshqk_${org.sendSchoolId}"/>
			 <c:set var="key7" value="jsres_sendSchool_xysccx_${org.sendSchoolId}"/>
			 <c:set var="key10" value="jsres_sendSchool_djgzlcx_${org.sendSchoolId}"/>
			<c:if test="${(pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y) or(pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y) or(pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y) or
			(pdfn:jsresPowerCfgMap(key4)==GlobalConstant.FLAG_Y) or(pdfn:jsresPowerCfgMap(key5)==GlobalConstant.FLAG_Y) or(pdfn:jsresPowerCfgMap(key6)==GlobalConstant.FLAG_Y)or(pdfn:jsresPowerCfgMap(key7)==GlobalConstant.FLAG_Y)or(pdfn:jsresPowerCfgMap(key10)==GlobalConstant.FLAG_Y) }">
				 <dl class="menu">
					<dt class="menu_title">
					  <i class="icon_menu menu_management"></i>培训过程管理
					</dt>
					 <c:if test="${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y }">
						<dd class="menu_item"><a onclick="cycle(null);">医师轮转查询</a> </dd>
					</c:if>
					<c:if test="${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y}">
						<dd class="menu_item"><a onclick="cycleResults(null);">医师出科成绩查询</a> </dd>
					</c:if>
					<c:if test="${pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y}">
						<dd class="menu_item" id="gradeSearch"><a onclick="gradeSearch();">双向评价查询</a></dd>
					</c:if>
					<c:if test="${pdfn:jsresPowerCfgMap(key4)==GlobalConstant.FLAG_Y}">
						<dd class="menu_item"><a onclick="docWorkSearch();">医师工作量查询</a></dd>
					</c:if>
					<c:if test="${pdfn:jsresPowerCfgMap(key10)==GlobalConstant.FLAG_Y}">
						<dd class="menu_item"><a onclick="teaWorkSearch();">带教工作量查询</a></dd>
					</c:if>
					<c:if test="${pdfn:jsresPowerCfgMap(key5)==GlobalConstant.FLAG_Y}">
						<dd class="menu_item"><a href="javascript:attendanceSearch();">学员考勤查询</a></dd>
					</c:if>
					<c:if test="${pdfn:jsresPowerCfgMap(key6)==GlobalConstant.FLAG_Y}">
						<dd class="menu_item"><a href="javascript:orgTeaAuditInfo();">基地带教审核情况</a></dd>
					</c:if>
					<c:if test="${pdfn:jsresPowerCfgMap(key7)==GlobalConstant.FLAG_Y}">
						<dd class="menu_item"><a href="javascript:manualSearch();">学员手册查询</a></dd>
					</c:if>
				</dl>
			</c:if>

			 <c:set var="key8" value="jsres_sendSchool_zpyjfk_${org.sendSchoolId}"/>
			 <c:set var="key9" value="jsres_sendSchool_zpzngl_${org.sendSchoolId}"/>
			 <c:if test="${(pdfn:jsresPowerCfgMap(key8)==GlobalConstant.FLAG_Y) or (pdfn:jsresPowerCfgMap(key9)==GlobalConstant.FLAG_Y)}">
			 <dl class="menu">
				 <dt class="menu_title">
					 <i class="icon_menu menu_statistics"></i>住培信息管理
				 </dt>
				 <c:if test="${pdfn:jsresPowerCfgMap(key8)==GlobalConstant.FLAG_Y}">
					 <dd class="menu_item"><a onclick="opinions(null)">住培意见反馈</a></dd>
				 </c:if>
				 <c:if test="${pdfn:jsresPowerCfgMap(key9)==GlobalConstant.FLAG_Y}">
					 <dd class="menu_item"><a onclick="guides()">住培指南管理</a></dd>
				 </c:if>
			</dl>
			 </c:if>
		 </c:if>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_statistics"></i>统计信息查询
			 </dt>
<%--			 <dd class="menu_item"><a onclick="monthlyReport();">培训月报查询</a></dd>--%>
<%--			 <dd class="menu_item"><a href="javascript:appUse();">app使用情况月报</a></dd>--%>
<%--			 <dd class="menu_item"><a href="javascript:jiaoxueActive();">教学活动统计</a></dd>--%>
<%--			 <dd class="menu_item"><a href="javascript:doctorOutOffice();">学员出科情况统计</a></dd>--%>
<%--			 <dd class="menu_item"><a href="javascript:rotationStatistics();">学员轮转查询</a></dd>--%>
<%--			 <dd class="menu_item"><a href="javascript:personStatistic();">人员情况统计</a></dd>--%>
			 <dd class="menu_item"><a href="javascript:statisticsAppUser();"
									  onclick="statisticsAppUser()">APP使用情况统计</a></dd>
			 <dd class="menu_item"><a href="javascript:activityStatistics();"
									  onclick="activityStatistics()">教学活动统计</a></dd>
		 </dl>
	<%--	 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_statistics"></i>教学活动管理
			 </dt>
			 <dd class="menu_item"><a href="javascript:activityQuery();">教学活动查询</a></dd>
			 <dd class="menu_item"><a href="javascript:doctorActivityStatistics();">学员活动统计</a></dd>
		 </dl>--%>
		<dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_statistics"></i>结业管理
			 </dt>
			 <dd class="menu_item" id="doctorScoreApplyList"><a onclick="doctorScoreApplyList();">证书申请查询</a></dd>
			 <dd class="menu_item"><a href="javascript:doctorTheoryList();">医师结业成绩查询</a></dd>
			 <dd class="menu_item"><a href="javascript:CertificateManagement();">医师结业证书查询</a></dd>
			 <dd class="menu_item"><a onclick="theoryPassedList();">结业合格人员查询</a></dd>
		</dl>
     </div>
     <div class="col_main" id="content">
       <div class="content_main">
        <div class="index_show">
          <div class="index_tap_global"style="width:100%">
            <ul class="inner">
              <li class="index_item index_green_university">
                <a href="javascript:selectMenu('hospitalList');">
                  <span class="tap_inner">
					  <c:forEach step="1" begin="0" end="2" varStatus="num">
						  <c:set var="year" value="${sysCfgMap['jsres_doctorCount_sessionNumber']-num.count+1}"></c:set>
						  <c:set var="key" value="${year}pl"></c:set>
						  <span class="tap_inner" style="width:33%; float: left;padding-bottom: 26px;overflow: hidden;">
							<i class="crowd"></i>
							<em class="number"><label>${doctorCountExtMap[key]["PEOPLE_NUM"]}人</label></em>
							<strong  class="title">${sysCfgMap['jsres_doctorCount_sessionNumber']-num.count+1}
							<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber']}">届</c:if>在校专硕</strong>
					  		</span>
					  </c:forEach>
                  </span>
                </a>
              </li>
            </ul>
          </div>
        </div>
		<div class="main_bd">
			   <ul>
				   <li class="score_frame">
					   <h1 style="background:#e7f5fc;">当前住培情况</h1>
					   <table class="in_table" style="float: left;width: 48%; margin-top: 10px;margin: 10px;margin-bottom: 10px;border: 1px solid #ccc;" cellpadding="0" cellspacing="0" width="100%">
						   <tbody>
						   <tr>
							   <td>
								   <div id="hosChart" style="height: 200px;">

								   </div>
							   </td>
						   </tr>
						   </tbody>
					   </table>
					   <div class="grid" style="overflow: auto;height: 200px;width: auto;margin: 10px">
						   <table class="in_table" style="border: 1px solid #ccc;" cellpadding="0" cellspacing="0" width="100%">
							   <colgroup>
								   <col width="25%"/>
								   <col width="25%"/>
								   <col width="25%"/>
								   <col width="25%"/>
							   </colgroup>
							   <tr style="height: 60px;">
								   <td colspan="4"><div style="float: left;margin-left:80px;height: 10px;width: 10px;background: rgba(255,120,50,1);"></div><div style="float: left;line-height: 10px;"> ：在培人数</div>
								   <div style="float: left;margin-left:20px;height: 10px;width: 10px;background: rgba(100,200,255,1);"></div><div style="float: left;line-height: 10px;"> ：已考核待结业人数</div>
								   <div style="float: left;margin-left:20px;height: 10px;width: 10px;background: rgba(255,120,255,1);"></div><div style="float: left;line-height: 10px;"> ：结业人数</div></td>
							   </tr>
							   <c:forEach items="${currDocDetailMaps}" var="currDocDetail">
								   <tr>
									   <td style="text-align: center">${currDocDetail['SESSIONNUMBER']}届</td>
									   <td style="text-align: center">${currDocDetail['20'] ==null? 0:currDocDetail['20']}人</td>
									   <td style="text-align: center">${currDocDetail['22'] ==null? 0:currDocDetail['22']}人</td>
									   <td style="text-align: center">${currDocDetail['21'] ==null? 0:currDocDetail['21']}人</td>
								   </tr>
							   </c:forEach>
						   </table>
					   </div>
					   <%--<div style="background-color: #e7f5fc;height: 50px;margin:10px ; text-align: center;font-size: large">--%>
						   <%--<table class="in_table" style="background-color:#e7f5fc;" cellpadding="0" cellspacing="0"  width="100%">--%>
							   <%--<tr style="height: 12px"></tr>--%>
							   <%--<tr>--%>
								   <%--<td style="text-align: center">填写数据总量：${sumCountAudit['SUMCOUNT']}条</td>--%>
								   <%--<td style="text-align: center">带教审核总量：${sumCountAudit['SUMCOUNTRES']}条</td>--%>
							   <%--</tr>--%>
						   <%--</table>--%>
					   <%--</div>--%>
				   </li>
			   </ul>
		</div>
	    <div class="main_bd" style="height:auto;">
			   <ul>
				   <li class="score_frame">
					   <h1 style="background:#e7f5fc;">专业学员概况</h1>
					   <div class="" style="margin-top: 15px;">
						   <form id="doctorNumSearch">
						   <table cellspacing="8px">
							   <tr>
								   <td>&#12288;年&#12288;&#12288;级：</td>
								   <td>
									   <input type="text" id="sessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy'})"
											  value="${pdfn:getCurrYear()}" class="input" readonly="readonly" style="width: 106px;margin-left: 0px" />
								   </td>
								   <td>&#12288;培训基地：</td>
								   <td>
									   <select class="select" style="width: 106px" id="orgFlow" name="orgFlow">
										   <option></option>
										   <c:forEach items="${orgs}" var="org" varStatus="status">
											   <option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
										   </c:forEach>
									   </select>
								   </td>
								   <td>&#12288;培训类别：</td>
								   <td>
									   <select name="trainTypeId" id="trainTypeId" class="select" style="width:106px;">
											   <option value="DoctorTrainingSpe"  <c:if test="${param.trainTypeId=='DoctorTrainingSpe'}">selected="selected"</c:if>>住院医师</option>
											   <option value="AssiGeneral"  <c:if test="${param.trainTypeId=='AssiGeneral'}">selected="selected"</c:if>>助理全科</option>
									   </select>
								   </td>
									<td></td>
									<td></td>
									<td></td>
									<td>&#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="searchInfo();"/>&#12288;</td>
									<td>&#12288;<input class="btn_green" type="button" value="导&#12288;出" onclick="daochu();"/></td>
								</tr>
						   </table>
						   </form>
					   </div>
					   <div class="index_table" id="doctorNum1"style="height: 550px;width:100%;margin-top: 50px;">

					   </div>
					   <h1 style="background:#e7f5fc;">基地学员概况</h1>
					   <div class="" style="margin-top: 15px;">
						   <form id="doctorNumSearch2">
							   <table cellspacing="8px">
								   <tr>
									   <td>&#12288;年&#12288;&#12288;级：</td>
									   <td>
										   <input type="text" id="sessionNumber2" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy'})"
												  value="${pdfn:getCurrYear()}" class="input" readonly="readonly" style="width: 106px;margin-left: 0px" />
									   </td>
									   <td>&#12288;培训类别：</td>
									   <td>
										   <select name="trainTypeId" id="trainTypeId2" class="select" style="width:106px;">
											   <option value="DoctorTrainingSpe"  <c:if test="${param.trainTypeId=='DoctorTrainingSpe'}">selected="selected"</c:if>>住院医师</option>
											   <option value="AssiGeneral"  <c:if test="${param.trainTypeId=='AssiGeneral'}">selected="selected"</c:if>>助理全科</option>
										   </select>
									   </td>
									   <td></td>
									   <td></td>
									   <td></td>
									   <td>&#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="searchInfo2();"/>&#12288;</td>
									   <td>&#12288;<input class="btn_green" type="button" value="导&#12288;出" onclick="daochu2();"/></td>
								   </tr>
							   </table>
						   </form>
					   </div>
					   <div class="index_table" id="doctorNum2"style="max-height:600px;overflow:auto;width:100%;margin-top: 50px;">

					   </div>
				   </li>
			   </ul>
		   </div>
       </div>
     </div>
   </div>
 </div>
</div>
</div>
  <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp"></jsp:include>
	</c:if>
 <div class="foot">
   <div class="foot_inner">
       主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司
   </div>
 </div>

</div>

</body>
</html>
