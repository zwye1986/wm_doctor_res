<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="consult" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
$(document).ready(function(){
	$(".menu_item a").click(function(){
		$(".select").removeClass("select");
		$(this).addClass("select");
	});
	setBodyHeight();
    loadTheRoles();
    $("#changeRole").on("mouseenter mouseleave",function(){
        $("#roleContent").toggle();
    });
});

function loadTheRoles(){
    var name = "住院医师";
    var name2 = "助理全科";
    var root ="<s:url value='/'/>";
    /*if('/' == root){
        root = "/pdsci/";
    }*/
    var url = root+"jsres/manage/cityChangePhyAcc?type=phy";
    var url2 = root+"jsres/manage/cityChangePhyAcc?type=acc";
    var roleItem = $('<div/>').addClass("selRole").attr("url",url).text(name).click(function(){
        location.href = $(this).attr("url");
    });
    var roleItem2 = $('<div/>').addClass("selRole").attr("url",url2).text(name2).click(function(){
        location.href = $(this).attr("url");
    });
    $("#roleHome").append(roleItem);
    $("#roleHome").append(roleItem2);
}

function delay(){
	var url = "<s:url value='/jsres/doctor/delay'/>";
	currentJboxLoadNoData("content", url, true);
}
function accounts(){
	jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
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
function accounts(){
	jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
}

function searchBaseInfo(auditFlag){
	jboxLoad("content","<s:url value='/jsres/base/findBaseInfo'/>?role=${GlobalConstant.USER_LIST_CHARGE}&auditFlag="+auditFlag+"",true);
}

function editHosSpecials(){
	jboxLoad("content","<s:url value='/jsp/jsres/system/hospital/editHosSpecials.jsp'/>",true);
}

function doctorList(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}
function doctorScoreApplyList(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/doctorScoreApply/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}

function auditDoctors(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/doctor/auditDoctors.jsp'/>?source=city",true);
}

function doctorManage(){
	jboxLoad("content","<s:url value='/jsp/jsres/city/doctorManage.jsp'/>",false);
}

function gradeInput(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/gradeInput.jsp'/>?source=city",false);
}

function auditGraduate(){
	jboxLoad("content","<s:url value='/jsp/jsres/hospital/doctor/auditGraduates.jsp'/>?source=city",false);
}

function selectMenu(menuId){
	$("#"+menuId).find("a").click();
}

$(document).ready(function(){
	showHosCanvas();
	showDoctorCanvas();
});

function showHosCanvas(){
	var myChart = echarts.init(document.getElementById('hosChart')); 
	option = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{b}"
		    },
		    title : {
 		        text: '市局基地审核概况',
 		        x:'center',
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
     		                {value:6, name:'待审核：6'},
     		                {value:8, name:'审核通过：8'},
     		                {value:1, name:'审核不通过：1'}
     		            ]
		        }
		    ]
		};
// 为echarts对象加载数据 
myChart.setOption(option);
}

function showDoctorCanvas(){
    var myChart = echarts.init(document.getElementById('doctorChart')); 
    var lineLabel = ['2012','2013','2014','2015'];
 	var lineValue = ['80','98','100','119'];
 	option = {
 		    title : {
 		        text: '医师注册数走向',
 		        subtext: "医师注册总数：356，审核通过总数：320"
 		    },
 		    tooltip : {
 		        trigger: 'axis'
 		    },
 		    legend: {
 		        data:['审核通过数']
 		    },
 		    calculable : true,
 		    xAxis : [
 		        {
 		            type : 'category',
 		            boundaryGap: true,
 		            data : lineLabel
 		        }
 		    ],
 		    yAxis : [
 		        {
 		            type : 'value',
 		            axisLabel : {
 		                formatter: '{value}'
 		            }
 		        }
 		    ],
 		    series : [
 		        {
 		            name:'审核通过数',
 		            type:'line',
 		            data:lineValue,
 		            itemStyle: {
	                    normal: {
	                        label : {
	                            show: true, position: 'top'
	                        }
	                    }
               		}
 		        }
 		    ]
 		};
     // 为echarts对象加载数据 
     myChart.setOption(option); 
}
function toPage(page){
	shouye(page);
}
function shouye(page){
	 if (page == null || page == undefined || page == '' || isNaN(page)){
		 page=1;
	 }
	var url="<s:url value='/jsres/manage/charge'/>?currentPage="+page;
	window.location.href=url;
}
/* 培训基地评估 */
function baseEvaluation(){
	currentJboxLoadNoData("content","<s:url value='/jsp/jsres/global/hospital/baseEvaluationTitle.jsp'/>",true);
}
function searchOrgInfo(){
	currentJboxLoadNoData("content","<s:url value='/jsp/jsres/global/hospital/specialBaseStatistics.jsp'/>",true);
}

function teachTrainMain() {
    var url = "<s:url value='/jsres/statistic/searchChargeTeachTrainMain'/>?roleFlag=charge";
    currentJboxLoadNoData("content", url, true);
}

function statisticsTeachTrainMain() {
    var url = "<s:url value='/jsres/statistic/statisticsChargeTeachTrainMain'/>?roleFlag=charge";
    currentJboxLoadNoData("content", url, true);
}

function searchOldTeachTrain() {
    var url = "<s:url value='/jsres/statistic/searchChargeOldTeachTrain'/>?roleFlag=charge";
    currentJboxLoadNoData("content", url, true);
}

//学员填写量
function doctorDataMain() {
    var url = "<s:url value='/jsres/statistic/doctorDataMain'/>?roleFlag=charge";
    currentJboxLoadNoData("content", url, true);
}

//带教审核量
function teacherAuditMain() {
    var url = "<s:url value='/jsres/statistic/teacherAuditMain'/>?roleFlag=charge";
    currentJboxLoadNoData("content", url, true);
}

function countryBaseStatistics(){
	jboxLoad("content","<s:url value='/jsres/statistic/statisticCountryOrg'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
function jointBaseStatistics(){
	jboxLoad("content","<s:url value='/jsres/statistic/statisticJointOrg'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
function statisticsAppUser(){

	jboxLoad("content","<s:url value='/jsres/appUseInfo/main?userListScope=charge'/>",false);
	<%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
}
function baseSpecialList(){
	//var url = "<s:url value='/jsp/jsres/system/sys/baseSpecialList.jsp'/>";
	var url = "<s:url value='/jsres/base/orgSpeManage'/>";
	currentJboxLoadNoData("content", url, true);
}
function accountManage(){
	jboxLoad("content","<s:url value='/jsres/doctor/accountManage'/>?source=hospital",true);
}
function changeBaseMain(){
	var url = "<s:url value='/jsres/manage/changeBase'/>";
	currentJboxLoadNoData("content", url, true);
}
function searchChangeSpe(){
	var url = "<s:url value='/jsres/manage/searchChangeSpe'/>?viewFlag=Y";
	jboxLoad("content", url, true);
}
function backTrain(){
	<%--var url = "<s:url value='/jsres/doctor/backTrainInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";--%>
    var url = "<s:url value='/jsres/doctor/changeBackTrainInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
	currentJboxLoadNoData("content", url, true);
}
function speMain(){
	var url = "<s:url value='/jsres/manage/speMain'/>";
	jboxLoad("content", url, true);
}
function gradeSearch(){
	var form = $("#gradeSearchForm").serialize() || {"gradeRole":"teacher"};
	var url = "<s:url value='/jsres/manage/gradeSearch'/>?role=${GlobalConstant.USER_LIST_CHARGE}";
	jboxStartLoading();
	jboxPost(url,form,function(resp){
		jboxEndLoading();
		$("#content").html(resp);
	},null,false);
}
function statisticsTeachTrain(){
	var url = "<s:url value='/jsres/statistic/searchTeachTrain'/>";
	currentJboxLoadNoData("content", url, true);
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
//考核资格审查
function asseAudit(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/asse/main'/>?roleFlag="+roleFlag+"&tabTag=WaitAudit",true);
}
function asseWaitAudit(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/asse/main'/>?roleFlag="+roleFlag+"&tabTag=FristWait&catSpeId=DoctorTrainingSpe",true);
}
function asseWaitAudit2(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/asse/main'/>?roleFlag="+roleFlag+"&tabTag=FristWait2&catSpeId=AssiGeneral",true);
}
function asseAuditList(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/asse/asseAuditListMain'/>?roleFlag="+roleFlag+"&tabTag=FristList&catSpeId=DoctorTrainingSpe",true);
}
function asseAuditList2(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/asse/asseAuditListMain'/>?roleFlag="+roleFlag+"&tabTag=FristList2&catSpeId=AssiGeneral",true);
}
<%--function searchAsseList(){--%>
<%--    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";--%>
<%--    jboxLoad("content","<s:url value='/jsres/asse/main'/>?roleFlag="+roleFlag+"&tabTag=Audit",true);--%>
<%--}--%>
/*理论成绩*/
function doctorTheoryList(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
}
/*技能考核管理*/
function skillManage() {
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/skillTimeConfig/skillManage'/>?roleFlag="+roleFlag,true);
}
/*技能成绩*/
function doctorSkillList(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorSkillList'/>?roleFlag="+roleFlag,true);
}
/*公共科目成绩*/
function doctorPublicList(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorPublicList'/>?roleFlag="+roleFlag,true);
}
/*function CertificateManage(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/certificateManage/freeMain'/>?roleFlag="+roleFlag,true);
}*/
function CertificateManagement(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/doctorScoreApply/certificateManagement'/>?roleFlag="+roleFlag,true);
}
//黑名单信息查询
function searchBlackInfo(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	var url = "<s:url value='/jsres/blackList/blackListInfo?currentPage=1'/>"+"&roleFlag="+roleFlag;
	currentJboxLoadNoData("content", url, true);
}
function zlDocQuery(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/mainGlobal'/>?roleFlag="+roleFlag,true);
}
function zltjOrg(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/zltjOrgMain'/>?tabId=doctorType&roleFlag="+roleFlag,true);
}
function signUpmain(typeId){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/examSignUp/examsignupmain'/>?roleFlag="+roleFlag+"&typeId="+typeId,true);
    <%--jboxLoad("content","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag="+roleFlag+"&typeId="+typeId,true);--%>
}
function scoreManage() {
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/scoreManage/main'/>?roleFlag="+roleFlag+"&catSpeId=DoctorTrainingSpe",true);
}
function scoreManage2() {
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/scoreManage/main'/>?roleFlag="+roleFlag+"&catSpeId=AssiGeneral",true);
}
function CertificateManage(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/certificateManage/main'/>?roleFlag="+roleFlag+"&catSpeId=DoctorTrainingSpe",true);
}
function CertificateManage2(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/certificateManage/main'/>?roleFlag="+roleFlag+"&catSpeId=AssiGeneral",true);
}
function rotationStatistics(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/manage/rotationStatistics'/>?roleFlag="+roleFlag,true);
}
function personStatistic(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jsres/manage/personStatistic'/>?roleFlag="+roleFlag,true);
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
       <h2>${sessionScope.currUser.orgName}</h2>
       <div class="head_right">
           <span id="changeRole">
	        <div id="roleContent" style="width: 0px;height: 0px;overflow: visible;float: right;display: none;">
		        <div id="roleHome" style="background: #6eb0dd;width: 120px;text-align: left;padding: 0px 5px;top:21px;
		        border-radius:3px;position: relative;right: 165px;border: 1px solid #459fd0;cursor: pointer;"></div>
	        </div>
	        <a><c:out value="${param.actionName}" default="切换"/></a>&#12288;
            </span>
           <a href="<s:url value='/jsres/manage/charge'/>">首页</a>&#12288;
         <a href="<s:url value='/inx/jsres/logout'/>">退出</a>
       </div>
     </div>
   </div>
 </div>
 <div class="body">
   <div class="container">
     <div class="content_side">
        <dl class="menu menu_first">
          <dt class="menu_title">
            <i class="icon_menu menu_function"></i>基地信息管理
          </dt>
          <%-- <dd class="menu_item" id="auditHospitals"><a onclick="searchBaseInfo('${GlobalConstant.FLAG_Y}');">基地信息审核</a></dd> --%>
<!--           <dd class="menu_item" id="hospitalList"><a onclick="searchBaseInfo();">基地信息查询</a></dd> -->
			   <dd class="menu_item" id="hospitalList"><a onclick="searchOrgInfo();">培训基地清单</a></dd>
			   <dd class="menu_item" ><a onclick="baseEvaluation();">培训基地评估</a></dd>
        </dl>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_management"></i>招录信息管理
			 </dt>
			 <dd class="menu_item"><a onclick="zlDocQuery();">招录学员查询</a></dd>
			 <%--<dd class="menu_item"><a onclick="zltjOrg();">招录统计-基地</a></dd> --%>
		 </dl>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_management"></i>异动学员查询
			 </dt>
			 <dd class="menu_item"><a onclick="changeBaseMain();">基地变更查询</a></dd>
			 <dd class="menu_item"><a onclick="speMain();">专业变更查询</a></dd>
<%--			 <dd class="menu_item"><a href="javascript:backTrain();">退培学员查询</a></dd>--%>
<%--			 <dd class="menu_item"><a href="javascript:backTrain();">退培学员管理</a></dd>--%>
			 <dd class="menu_item"><a onclick="delay();">延期学员查询</a></dd>
			 <dd class="menu_item"><a onclick="searchBlackInfo();">黑名单管理</a></dd>
		 </dl>
        <dl class="menu menu_first">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>学员培训查询
          </dt>
          <!-- <dd class="menu_item" id="auditDoctors"><a onclick="auditDoctors();">医师信息审核</a></dd> -->
          <dd class="menu_item" ><a onclick="javascript:doctorList();">学员信息查询</a></dd>
           <c:set value="jswjw_${sessionScope.currUser.orgFlow}_P003" var="orgFlow"/>
      	  <c:if test="${sysCfgMap[orgFlow] eq GlobalConstant.RECORD_STATUS_Y}">
         	 <dd class="menu_item"><a onclick="gradeSearch();">出科评价查询</a></dd>
          </c:if>
        </dl>
		 <%--<dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_management"></i>结业信息管理
			 </dt>
			 <dd class="menu_item"><a onclick="asseAudit();">考核资格审查</a></dd>
			 <dd class="menu_item"><a href="javascript:signUpmain('Theory');">理论补考查询</a></dd>
			 <dd class="menu_item"><a href="javascript:signUpmain('Skill');">技能补考查询</a></dd>
			 <dd class="menu_item"><a onclick="doctorPublicList();">公共科目管理</a></dd>
			 <dd class="menu_item"><a onclick="doctorTheoryList();">理论考核查询</a></dd>
			 <dd class="menu_item"><a onclick="doctorSkillList();">技能考核管理</a></dd>
			 &lt;%&ndash;<dd class="menu_item" id="doctorScoreApplyList"><a onclick="doctorScoreApplyList();">证书申请查询</a></dd>&ndash;%&gt;
			 &lt;%&ndash;<dd class="menu_item"><a onclick="CertificateManagement();">证书查询</a></dd>&ndash;%&gt;
			 <dd class="menu_item"><a onclick="CertificateManage();">证书信息查询</a></dd>
		 </dl>--%>
         <dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_management_train_query"></i>师资管理
             </dt>
             <dd class="menu_item"><a onclick="teachTrainMain()">师资信息管理</a></dd>
             <dd class="menu_item"><a onclick="statisticsTeachTrainMain()">师资培训统计</a></dd>
             <dd class="menu_item"><a onclick="searchOldTeachTrain()">师资历史数据</a></dd>
         </dl>
         <dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_management"></i>绩效管理
             </dt>
             <dd class="menu_item"><a onclick="doctorDataMain()">学员填写量统计</a></dd>
             <dd class="menu_item"><a onclick="teacherAuditMain()">带教审核量统计</a></dd>
         </dl>
          <dl class="menu">
              <dt class="menu_title">
                <i class="icon_menu menu_statistics"></i>统计分析
              </dt>
              <dd class="menu_item"><a href="javascript:countryBaseStatistics();">学员信息统计</a></dd>
              <dd class="menu_item"><a href="javascript:jointBaseStatistics();">协同基地信息统计</a></dd>
              <dd class="menu_item"><a href="javascript:statisticsAppUser();">APP使用情况统计</a></dd>
<%--          <dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>--%>

                 <%--报表，16版本暂时先注掉--%>
          <%--<dd class="menu_item"><a href="javascript:rotationStatistics();">学员轮转查询</a></dd>--%>
          <%--<dd class="menu_item"><a href="javascript:personStatistic();">人员情况统计</a></dd>--%>
          <%--<dd class="menu_item"><a href="javascript:appUse();">app使用情况月报</a></dd>--%>
          <%--<dd class="menu_item"><a href="javascript:jiaoxueActive();">教学活动统计</a></dd>--%>
          <%--<dd class="menu_item"><a href="javascript:doctorOutOffice();">学员出科情况统计</a></dd>--%>
         </dl>
         <dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_management"></i>住院医师结业管理
             </dt>
             <%--<dd class="menu_item"><a onclick="asseAudit();">考核资格审核</a></dd>--%>
             <dd class="menu_item"><a onclick="asseWaitAudit();">结业考核资格审核</a></dd>
<%--             <dd class="menu_item"><a onclick="searchAsseList();">考核资格查询</a></dd>--%>
             <%--<dd class="menu_item"><a href="javascript:signUpmain();">补考审核</a></dd>--%>
             <dd class="menu_item"><a onclick="asseAuditList();">结业考核资格查询</a></dd>
             <%--<dd class="menu_item"><a onclick="skillManage()">技能考试管理</a></dd>--%>
             <dd class="menu_item"><a onclick="scoreManage()">结业成绩管理</a></dd>
             <dd class="menu_item"><a onclick="CertificateManage();">结业证书管理</a></dd>
         </dl>
         <%--<dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_management"></i>助理全科结业管理
             </dt>
             <dd class="menu_item"><a onclick="asseWaitAudit2();">结业考核资格审核</a></dd>
             <dd class="menu_item"><a onclick="asseAuditList2();">结业考核资格查询</a></dd>
             <dd class="menu_item"><a onclick="scoreManage2()">结业成绩管理</a></dd>
             <dd class="menu_item"><a onclick="CertificateManage2();">结业证书管理</a></dd>
         </dl>--%>
        <!-- <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>结业考核管理
          </dt>
          <dd class="menu_item" id="auditGraduate"><a onclick="auditGraduate();">考核申请审核</a></dd>
          <dd class="menu_item"><a onclick="gradeInput();">成绩管理</a></dd>
        </dl> -->
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_setup"></i>设置
          </dt>
           <!-- <dd class="menu_item"><a href="javascript:doctorManage();">医师用户管理</a></dd> -->
<!--            <dd class="menu_item"><a href="javascript:accountManage();">医师账号管理</a></dd> -->
           <dd class="menu_item"><a href="javascript:baseSpecialList();">基地专业管理</a></dd>
           <dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
        </dl>
     </div>
     <div class="col_main" id="content">
       <div class="content_main">
         <div class="index_show">
          <div class="index_tap top_left">
            <ul class="inner">
              <li class="index_item index_blue">
                <a href="javascript:selectMenu('hospitalList');">
                  <span class="tap_inner">
                    <span style="float: left;margin-left: 60px">
	                    <i class="message"></i>
	                    <em class="number"><label id="countryOrg">${countryOrgCount}</label></em>
	                    <strong  class="title">国家基地</strong>
                    </span>
                    <span style="float: right;margin-right: 60px">
	                     <i class="message"></i>
	                      <em class="number"><label id="jointOrg">${jointOrgCount}</label></em>
	                      <strong  class="title">协同基地</strong>
                    </span>
                  </span>
                </a>
              </li>
              <li class="index_item index_blue">
                <a href="javascript:selectMenu('auditDoctors');">
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
                    <strong  class="title">待结业人数</strong>
                  </span>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="main_bd">
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
<!-- 		    <ul> -->
<!-- 		        <li class="score_frame"> -->
<!-- 		            <h1>信息概况</h1> -->
<!-- 		            <div class="index_table" > -->
<!-- 		                <table class="in_table" cellpadding="0" cellspacing="0" width="100%"> -->
<!-- 		                <colgroup> -->
<!-- 		                  <col width="50%"/> -->
<!-- 		                  <col width="50%"/> -->
<!-- 		                </colgroup> -->
<!-- 		                <tbody> -->
<!-- 		                <tr> -->
<!-- 		                	<td> -->
<!-- 			                	<h3>基地概况</h3> -->
<!-- 			                	<dl class="gray"> -->
<!-- 								<dd class="left">基地数&#12288;：<span class="right">毕教委审核通过数：4，全科中心审核通过数：3</span></dd> -->
<!-- 								<dd class="left">基地审核：<span class="right">审核中：8，审核通过：7，审核不通过：3</span></dd> -->
<!-- 								</dl> -->
<!-- 			                </td> -->
<!-- 			                <td> -->
<!-- 			                <div id="hosChart" style="height: 200px;"></div> -->
<!-- 			                </td> -->
<!-- 		                </tr> -->
<!-- 		                </tbody> -->
<!-- 		                </table><br/> -->
<!-- 		                <table class="in_table" cellpadding="0" cellspacing="0" width="100%" style="margin: 0;"> -->
<!-- 		                <colgroup> -->
<!-- 		                  <col width="35%"/> -->
<!-- 		                  <col width="65%"/> -->
<!-- 		                </colgroup> -->
<!-- 		                <tbody> -->
<!-- 		                <tr> -->
<!-- 		                	<td> -->
<!-- 			                	<h3>医师概况</h3> -->
<!-- 			                	<dl class="gray"> -->
<!-- 								<dd class="left">医师数&#12288;：<span class="right">毕教委审核通过数：328</span></dd> -->
<!-- 								<dd class="left">&#12288;&#12288;&#12288;&#12288;&#12288;<span class="right">全科中心审核通过数：246</span></dd> -->
<!-- 								<dd class="left">注册审核：<span class="right">审核中：75，审核通过：574</span></dd> -->
<!-- 								<dd class="left">&#12288;&#12288;&#12288;&#12288;&#12288;<span class="right">审核不通过：23</span></dd> -->
<!-- 								</dl> -->
<!-- 			                </td> -->
<!-- 			                <td> -->
<!-- 			                	<div id="doctorChart" style="height: 250px;width:100%;margin: 20px 0 0 0;"></div> -->
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
