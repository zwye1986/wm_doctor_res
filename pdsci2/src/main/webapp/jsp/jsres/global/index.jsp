<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
					text: '基地分布',
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
							{value:"${countryOrgCount}", name:'国家基地数：${countryOrgCount}'},
							{value:"${jointOrgCount}", name:'协同基地数：${jointOrgCount}'},
							{value:"${provinceOrgCount}", name:'省保留基地数：${provinceOrgCount}'}
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
//	$('#sessionNumber').datepicker({
//		startView: 2,
//		maxViewMode: 2,
//		minViewMode:2,
//		format:'yyyy',
//		onSelect: function(selectedDate) {//选择日期后执行的操作
//			alert(selectedDate);
//		}
//	});
	$(".menu_item a").click(function(){
		$(".select").removeClass("select");
		$(this).addClass("select");
	});
	setBodyHeight();
	if("${speFlag}"!="0"){
		$("#speFlag").show();
	}
});

function setBodyHeight(){
	initOrg(null,null);
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

function accounts(){
	jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
}

function searchBaseInfo(auditFlag){
	jboxLoad("content","<s:url value='/jsres/base/findBaseInfo'/>?role=${GlobalConstant.USER_LIST_PROVINCE}&auditFlag="+auditFlag,true);
}

function auditHospitals(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/hospital/auditHospitals.jsp'/>",true);
}

function editHosSpecials(){
	jboxLoad("content","<s:url value='/jsp/jsres/system/hospital/editHosSpecials.jsp'/>",true);
}

function doctorList(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}
/*理论成绩*/
function doctorTheoryList(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
}
/*技能成绩*/
function doctorSkillList(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorSkillList'/>?roleFlag="+roleFlag,true);
}
/*公共科目成绩*/
function doctorPublicList(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorPublicList'/>?roleFlag="+roleFlag,true);
}
function doctorScoreApplyList(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/jsres/doctorScoreApply/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}
function CertificateManagement(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/jsres/doctorScoreApply/certificateManagement'/>?roleFlag="+roleFlag,true);
}

function auditDoctors(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/doctor/auditDoctors.jsp'/>",true);
}

function timeoutRegisterHoses(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/hospital/timeoutRegisterHoses.jsp'/>",false);
}

/* 培训基地评估 */
function baseEvaluation(){
	currentJboxLoadNoData("content","<s:url value='/jsp/jsres/global/hospital/baseEvaluationTitle.jsp'/>",true);
}

/* 结业考核管理 */

function gradeInput(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/gradeInput.jsp'/>?source=province",false);
}

function graduateManage(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/graduateManage.jsp'/>",false);
}

function graduateUserList(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/graduateUserList.jsp'/>",false);
}

function auditGraduate(){
	jboxLoad("content","<s:url value='/jsp/jsres/hospital/doctor/auditGraduates.jsp'/>?source=province",false);
}

function theoResultsImport(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/theoResultsImport.jsp'/>",false);
}

function skillsResults(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/skillsResults.jsp'/>",false);
}

function skillsResultsAudit(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/skillsResultsAudit.jsp'/>",false);
}

function doctorResults(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/doctorResults.jsp'/>",false);
}

function personalResults(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/graduate/personalResults.jsp'/>",false);
}

/* 统计分析 */
function trainBaseStatistics(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/statistics/trainBaseStatistics.jsp'/>",false);
}

function specialBaseStatistics(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/statistics/specialBaseStatistics.jsp'/>",false);
}

function hosBaseStatistics(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/statistics/hosBaseStatistics.jsp'/>",false);
}

function doctorOverView(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/statistics/doctorOverView.jsp'/>",false);
}

function doctorStatistics(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/statistics/doctorStatistics.jsp'/>",false);
}

function generalDoctorStatistics(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/statistics/generalDoctorStatistics.jsp'/>",false);
}

function specialDoctorStatistics(){
	jboxLoad("content","<s:url value='/jsp/jsres/province/statistics/specialDoctorStatistics.jsp'/>",false);
}

/* 设置 */
function schoolList(){
	jboxLoad("content","<s:url value='/jsp/jsres/system/sys/schoolList.jsp'/>",false);
}

function sysDeptList(){
	jboxLoad("content","<s:url value='/jsp/jsres/system/sys/sysDeptList.jsp'/>",false);
}

function specialList(){
	jboxLoad("content","<s:url value='/jsp/jsres/system/sys/specialList.jsp'/>",false);
}

function baseSpecialList(){
	//var url = "<s:url value='/jsp/jsres/system/sys/baseSpecialList.jsp'/>";
	var url = "<s:url value='/jsres/base/orgSpeManage'/>";
	currentJboxLoadNoData("content", url, true);
}

function baseLog(){
	jboxLoad("content","<s:url value='/jsp/jsres/system/sys/baseLog.jsp'/>",false);
}

function selectMenu(menuId){
	$("#"+menuId).find("a").click();
}

$(document).ready(function(){
	init();
	showHosCanvas();
	//showDoctorCanvas();

});
function searchOrgInfo(){
	currentJboxLoadNoData("content","<s:url value='/jsp/jsres/global/hospital/specialBaseStatistics.jsp'/>",true);
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
function showHosCanvas(){


}

function showDoctorCanvas(){
    var myChart = echarts.init(document.getElementById('doctorChart')); 
    var lineLabel = ['2013','2014','2015'];
 	var lineValue = ['80','98','100','119'];
 	option = {
 		    title : {
 		        text: '近3年学员人数',
//  		        subtext: "医师注册总数：356，审核通过总数：320"
 		    },
 		    tooltip : {
 		        trigger: 'axis'
 		    },
 		    legend: {
 		        data:['学员人数']
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
function reportDept() {
	jboxLoad("content", "<s:url value='/jsres/report/toDept'/>", true);
}

function reportDoc() {
	jboxLoad("content", "<s:url value='/jsres/report/toDoc'/>", true);
}
function countryBaseStatistics(){
	jboxLoad("content","<s:url value='/jsres/statistic/statisticCountryOrg'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
function jointBaseStatistics(){
	jboxLoad("content","<s:url value='/jsres/statistic/statisticJointOrg'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
function accountManage(){
	jboxLoad("content","<s:url value='/jsres/doctor/accountManage'/>?source=hospital",true);
}
function statisticsAppUser(){
	jboxLoad("content","<s:url value='/jsres/appUseInfo/main?userListScope=global'/>",false);
	<%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
}
function changeSpeMain(){
	var url = "<s:url value='/jsres/manage/changeSpeMain'/>";
	currentJboxLoadNoData("content", url, true);
}
function changeBaseMain(){
	var url = "<s:url value='/jsres/manage/changeBase'/>";
	currentJboxLoadNoData("content", url, true);
}
function searchChangeSpe(){
	var url = "<s:url value='/jsres/manage/searchChangeSpe'/>?viewFlag=Y";
	jboxLoad("content", url, true);
}
function speMain(){
	var url = "<s:url value='/jsres/manage/speMain'/>";
	jboxLoad("content", url, true);
}
function backTrain(){
	var url = "<s:url value='/jsres/doctor/backTrainInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
	currentJboxLoadNoData("content", url, true);
}
function delay(){
	var url = "<s:url value='/jsres/doctor/delay'/>";
	currentJboxLoadNoData("content", url, true);
}
function statisticsTeachTrain(){
	var url = "<s:url value='/jsres/statistic/searchTeachTrain'/>";
	currentJboxLoadNoData("content", url, true);
}
function gradeSearch(){
	var form = $("#gradeSearchForm").serialize() || {"gradeRole":"teacher"};
	var url = "<s:url value='/jsres/manage/gradeSearch'/>?role=${GlobalConstant.USER_LIST_GLOBAL}";
	jboxStartLoading();
	jboxPost(url,form,function(resp){
		jboxEndLoading();
		$("#content").html(resp);
	},null,false);
}

function getCityArea(){
	var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
	var provIds = "320000";
	jboxGet(url,null, function(json) {
		// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
		var newJsonData=new Array();
		var j=0;
		for(var i=0;i<json.length;i++){
			if(provIds==json[i].v){
				var citys=json[i].s;
				for(var k=0;k<citys.length;k++){
					newJsonData[j++]=citys[k];
				}
			}
		}
		$.cxSelect.defaults.url = newJsonData;
		$.cxSelect.defaults.nodata = "none";
		$("#provCityAreaId").cxSelect({
			selects : ["city"/* ,"area" */],
			nodata : "none",
			firstValue : ""
		});
		$("#cityAreaId").cxSelect({
			selects : ["city"/* ,"area" */],
			nodata : "none",
			firstValue : ""
		});

	},null,false);
}
function init()
{
	<c:forEach items="${resDocTypeEnumList}" var="type">
		$("#"+"${type.id}").attr("checked",true);
	</c:forEach>
	getCityArea();
	initOrg(null,null);
	getSpeInitData(getDocInitData);
	setTimeout(bindFunc, 3000);
}
function getDocInitData(fun)
{
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("年份不能为空！");
		return;
	}
	var trainTypeId=$("#trainTypeId").val();
	if(trainTypeId==""){
		jboxTip("培训类别不能为空！");
		return;
	}
	var data="";
	<c:forEach items="${resDocTypeEnumList}" var="type">
	if($("#"+"${type.id}").attr("checked")){
		data+="&datas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return;
	}
	var geturl="<s:url value='/jsres/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize();
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
function getSpeInitData(fun)
{
	var geturl="<s:url value='/jsres/manage/orgSpe'/>?"+$("#orgSpe").serialize();
	$.ajax({
		type : "get",
		url : geturl,
		cache : false,
		beforeSend : function(){
			jboxStartLoading();
		},
		success : function(resp) {
			$("#orgSpe1").html(resp);
		},
		error : function() {
		},
		complete : function(){
			jboxEndLoading();
			fun();
		}
	});
}
function bindFunc()
{
	//专业基地关系
	$("#orgLevelId2").unbind("change").bind("change",searchInfo2);
	$("#cityId2").unbind("change").bind("change",searchInfo2);


	$("#cityId").unbind("change").bind("change",function(obj){changeOrg(obj);searchInfo();});
	$("#orgLevelId").unbind("change").bind("change",function(obj){changeOrg(obj);searchInfo();});
	//$("#orgFlow").unbind("change").bind("change",searchInfo);
	$("#trainTypeId").unbind("change").bind("change",searchInfo);
	//$("#sessionNumber").unbind("change").bind("change",searchInfo);
	$("[name='datas']").unbind("change").bind("change",searchInfo);

}
	function initOrg(cityId,typeId)
	{
		var datas=[];
		<c:forEach items="${orgs}" var="org">
			var d={};
			if(!(cityId!=null&&cityId!="")&&!(typeId!=null&&typeId!=""))
			{
				d.id="${org.orgFlow}";
				d.text="${org.orgName}";
				datas.push(d);
			}else if((cityId!=null&&cityId!="")&&!(typeId!=null&&typeId!="")){
				if("${org.orgCityId}"==cityId)
				{
					d.id="${org.orgFlow}";
					d.text="${org.orgName}";
					datas.push(d);
				}
			}else if(!(cityId!=null&&cityId!="")&&(typeId!=null&&typeId!="")){
				if("${org.orgLevelId}"==typeId)
				{
					d.id="${org.orgFlow}";
					d.text="${org.orgName}";
					datas.push(d);
				}
			}else{
				if("${org.orgLevelId}"==typeId&&"${org.orgCityId}"==cityId)
				{
					d.id="${org.orgFlow}";
					d.text="${org.orgName}";
					datas.push(d);
				}
			}
		</c:forEach>
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
			searchInfo();
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,'orgFlow',true);
	}
function changeOrg(obj)
{
	var cityid=$("#cityId").val();
	var typeid=$("#orgLevelId").val();
	initOrg(cityid,typeid);
}
function searchInfo2()
{
	jboxLoad("orgSpe1","<s:url value='/jsres/manage/orgSpe'/>?"+$("#orgSpe").serialize(),true);
}
	function searchInfo()
	{
		var sessionNumber=$("#sessionNumber").val();
		if(sessionNumber==""){
			jboxTip("年份不能为空！");
			return;
		}
		var trainTypeId=$("#trainTypeId").val();
		if(trainTypeId==""){
			jboxTip("培训类别不能为空！");
			return;
		}
		var data="";
		<c:forEach items="${resDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
		}
		</c:forEach>
		if(data==""){
			jboxTip("请选择人员类型！");
			return;
		}
		jboxLoad("doctorNum1","<s:url value='/jsres/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize(),true);
	}
//黑名单信息查询
function searchBlackInfo(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	var url = "<s:url value='/jsres/blackList/blackListInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>"+"&roleFlag="+roleFlag;
	currentJboxLoadNoData("content", url, true);
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
       <a href="<s:url value='/jsres/manage/global'/>"><%=JsresUtil.getTitle(request,response,application)%></a>
     </h1>
     <div class="account">
       <h2 class="head_right">${sessionScope.currUser.orgName }</h2>
       <div class="head_right">
        <a href="<s:url value='/jsres/manage/global'/>">首页</a>&#12288;
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
<!--           <dd class="menu_item" id="auditHospitals"><a onclick="auditHospitals();">基地信息审核</a></dd> -->
<!--           <dd class="menu_item" id="hospitalList"><a onclick="searchBaseInfo();">基地信息查询</a></dd> -->
          <dd class="menu_item" id="hospitalList"><a onclick="searchOrgInfo();">培训基地清单</a></dd>
          <!-- <dd class="menu_item"><a href="javascript:timeoutRegisterHoses();">超时注册名单</a></dd> -->
          <dd class="menu_item" ><a onclick="baseEvaluation();">培训基地评估</a></dd>
        </dl>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>医师信息管理
          </dt>
<!--           <dd class="menu_item" id="auditDoctors"><a onclick="auditDoctors();">医师信息审核</a></dd> -->
          <dd class="menu_item"><a href="javascript:doctorList();">医师信息查询</a></dd>
          <%--<c:if test="${sysCfgMap['jsres_charge_grade'] eq GlobalConstant.FLAG_Y}">--%>
         	 <%--<dd class="menu_item"><a onclick="gradeSearch();">出科评价查看</a></dd>--%>
          <%--</c:if>--%>
        </dl>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>培训变更管理
          </dt>
           <dd class="menu_item"><a onclick="changeBaseMain();">基地变更查询</a></dd>
          <dd class="menu_item"><a onclick="speMain();">医师专业变更
           <%--<img  id="speFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
		  	</a>
          </dd>
		  <dd class="menu_item"><a href="javascript:backTrain();">退培医师查询</a></dd>
          <dd class="menu_item"><a onclick="delay();">延期医师查询</a></dd>
<!--              <dd class="menu_item"><a onclick="searchChangeSpe();">专业变更查询</a></dd> -->
		  <dd class="menu_item"><a onclick="searchBlackInfo();">黑名单管理</a></dd>
        </dl>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_management"></i>结业管理
			 </dt>
			 <dd class="menu_item"><a onclick="doctorTheoryList();">理论成绩</a></dd>
			 <dd class="menu_item"><a onclick="doctorSkillList();">技能成绩</a></dd>
			 <dd class="menu_item"><a onclick="doctorPublicList();">公共科目成绩</a></dd>
			 <dd class="menu_item" id="doctorScoreApplyList"><a onclick="doctorScoreApplyList();">证书申请审核</a></dd>
			 <dd class="menu_item"><a onclick="CertificateManagement();">证书查询</a></dd>
			 <dd class="menu_item"><a onclick="CertificateManagement();">证书信息管理</a></dd>
		 </dl>
       <!--  <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>结业考核管理
          </dt>
          <dd class="menu_item" id="auditGraduate"><a onclick="auditGraduate();">考核申请审核</a></dd>
          <dd class="menu_item"><a href="javascript:gradeInput();">成绩管理</a></dd>
          <dd class="menu_item"><a href="javascript:graduateUserList();">待结业名单</a></dd>
          <dd class="menu_item"><a href="javascript:graduateManage();">结业管理</a></dd>
          <dd class="menu_item"><a href="javascript:theoResultsImport();">理论成绩导入</a></dd>
          <dd class="menu_item"><a href="javascript:skillsResults();">理论成绩查询</a></dd>
          <dd class="menu_item"><a href="javascript:skillsResults();">技能成绩查询</a></dd>
          <dd class="menu_item"><a href="javascript:skillsResultsAudit();">技能成绩审核</a></dd>
          <dd class="menu_item"><a href="javascript:gradeInput();">分数线</a></dd>
          <dd class="menu_item"><a href="javascript:doctorResults();">医师成绩查询</a></dd>
          <dd class="menu_item"><a href="javascript:personalResults();">个人成绩查询</a></dd>
        </dl> -->
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_statistics"></i>统计分析
          </dt>
          <dd class="menu_item"><a href="javascript:countryBaseStatistics();">医师信息统计</a></dd>
          <dd class="menu_item"><a href="javascript:jointBaseStatistics();">协同基地信息统计</a></dd>
          <dd class="menu_item"><a href="javascript:statisticsAppUser();">APP使用情况统计</a></dd>
          <dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>
<!--           <dd class="menu_item"><a href="javascript:trainBaseStatistics();">培训基地统计</a></dd> -->
<!--           <dd class="menu_item"><a href="javascript:specialBaseStatistics();">专业基地统计</a></dd> -->
<!--           <dd class="menu_item"><a href="javascript:hosBaseStatistics();">基地花名册</a></dd> -->
<!--           <dd class="menu_item"><a href="javascript:doctorOverView();">在培医师总览</a></dd> -->
<!--           <dd class="menu_item"><a href="javascript:doctorStatistics();">在培医师统计</a></dd> -->
<!--           <dd class="menu_item"><a href="javascript:generalDoctorStatistics();">全科在培医师统计</a></dd> -->
<!--           <dd class="menu_item"><a href="javascript:specialDoctorStatistics();">专科在培医师统计</a></dd> -->
        </dl>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_setup"></i>设置
          </dt>
           <!-- <dd class="menu_item"><a href="javascript:doctorManage();">医师用户管理</a></dd> -->
           <!-- <dd class="menu_item"><a href="javascript:schoolList();">学校管理</a></dd> -->
           <!-- <dd class="menu_item"><a href="javascript:sysDeptList();">职能科室管理</a></dd>
           <dd class="menu_item"><a href="javascript:specialList();">专业管理</a></dd> -->
           <dd class="menu_item"><a href="javascript:accountManage();">医师账号管理</a></dd>
           <dd class="menu_item"><a href="javascript:baseSpecialList();">基地专业管理</a></dd>
           <!-- <dd class="menu_item"><a href="javascript:baseLog();">基地操作日志</a></dd> -->
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
                    <em class="number"><label id="trainDoctor">${doctorCount}</label></em>
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
                    <strong  class="title">结业资格审核</strong>
                  </span>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="main_bd">
		    <ul>
		        <li class="score_frame">
		            <h1>信息概况</h1>
		            <div class="index_table" >
		                <table class="in_table" cellpadding="0" cellspacing="0" width="100%">
		                <colgroup>
		                  <col width="50%"/>
		                  <col width="50%"/>
		                </colgroup>
		                <tbody>
		                <tr>
		                	<td>
			                	<h3>基地概况</h3>
			                	<dl class="gray">
								<dd class="left">国家基地数：${countryOrgCount }家</dd>
								<dd class="left">协同基地数： ${jointOrgCount}家</dd>
								<dd class="left">省保留基地数：${provinceOrgCount}家</dd>
								</dl>
			                </td>
			                <td>
			                <div id="hosChart" style="height: 200px;"></div>
			                </td>
		                </tr>
		                </tbody>
		                </table><br/>
		                <table class="in_table" cellpadding="0" cellspacing="0" width="100%" style="margin: 0;">
		                <colgroup>
		                  <col width="35%"/>
		                  <col width="65%"/>
		                </colgroup>
<!-- 		                <tbody> -->
<!-- 		                <tr> -->
<!-- 		                	<td> -->
<!-- 			                	<h3>医师概况</h3> -->
<!-- 			                	<dl class="gray"> -->
<%-- 								<dd class="left">本年度国家基地学员人数：${yearConDocCount}</dd> --%>
<!-- 								<dd class="left">本年度协同基地学员人数：22222</dd> -->
<!-- 								<dd class="left">本年度省保留基地学员人数：2222</dd> -->
<!-- 								</dl> -->
<!-- 			                </td> -->
<!-- 			                <td> -->
<!-- 			                	<div id="doctorChart" style="height: 250px;width:100%;margin: 20px 0 0 0;"></div> -->
<!-- 			                </td> -->
<!-- 		                </tr> -->
<!-- 		                </tbody> -->
		                </table>
		            </div>
		        </li>
		    </ul>
		</div>
	    <div class="main_bd" style="height:auto;">
			   <ul>
				   <li class="score_frame">
					   <h1>医师信息概况</h1>
					   <div class="" style="margin-top: 15px;">
						   <form id="doctorNumSearch">
						   <table cellspacing="8px">
							   <tr>
								   <td>&#12288;地&#12288;&#12288;区：</td>
								   <td>
									   <div id="provCityAreaId" style="width:150px;">
									   		<select id="cityId" name="cityId" style="width:150px;" class="city select" data-value="" data-first-title="选择市" onchange="changeOrg(this);"></select>
								   		</div>
								   </td>
								   <td>&#12288;基地类型：</td>
								   <td>
									   <select name="orgLevel" id="orgLevelId" class="select" style="width:150px;" onchange="changeOrg(this);">
										   <option value="">请选择</option>
										   <c:forEach items="${orgLevelEnumList}" var="level">
											   <option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
													   <c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
											   >${level.name}</option>
										   </c:forEach>
									   </select>
								   </td>
								   <td>&#12288;培训基地：</td>
								   <td>
									   <div style="width:150px;">
										   <input id="trainOrg"  class="input" type="text"  style="margin-left: 0px;width: 150px" />
										   <input id="orgFlow" name="orgFlow"  class="input" type="text" hidden  style="margin-left: 0px;width: 150px" />
									   </div>
								   </td>
								   <td>&#12288;培训类别：</td>
								   <td>
									   <select name="trainTypeId" id="trainTypeId" class="select" style="width:150px;">
										   <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
											   <option value="${trainCategory.id}" <c:if test="${trainCategoryEnumDoctorTrainingSpe.id eq trainCategory.id}">selected</c:if>
											   <c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>
											   >${trainCategory.name}</option>
										   </c:forEach>
									   </select>
								   </td>
							   </tr>
							   <tr><td>&nbsp;</td></tr>
								<tr>
									<td>&#12288;届&#12288;&#12288;别：</td>
									<td>
										<input type="text" id="sessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchInfo();}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 150px;margin-left: 0px" />
									</td>
									<td>&#12288;人员类型：</td>
									<td colspan="2">
										<c:forEach items="${resDocTypeEnumList}" var="type">
											<label><input name="datas" type="checkbox" id="${type.id}" name="${type.id}" value="${type.id}"/>${type.name}&nbsp;</label>
										</c:forEach>
									</td>
									<td></td>
									<td></td>
									<td></td>
									<%--<td><input class="btn_green" type="button" value="查询" onclick="searchInfo();"/></td>--%>
								</tr>
						   </table>
						   </form>
					   </div>
					   <div class="index_table" id="doctorNum1"style="height: 550px;width:100%;margin-top: 50px;">

					   </div>
				   </li>
			   </ul>
		   </div>
		   <div class="main_bd" style="height:auto;">
			   <ul>
				   <li class="score_frame">
					   <h1>专业基地概况</h1>
					   <div class="" style="margin-top: 15px;">
						   <form id="orgSpe">
							   <table cellspacing="8px">
								   <tr>
									   <td>&#12288;地&#12288;&#12288;区：</td>
									   <td>
										   <div id="cityAreaId" style="width:150px;">
											   <select id="cityId2" name="cityId" style="width:150px;" class="city select" data-value="" data-first-title="选择市"></select>
										   </div>
									   </td>
									   <td>&#12288;基地类型：</td>
									   <td>
										   <select name="orgLevel" id="orgLevelId2" class="select" style="width:150px;" >
											   <option value="">请选择</option>
											   <c:forEach items="${orgLevelEnumList}" var="level">
												   <option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
														   <c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
												   >${level.name}</option>
											   </c:forEach>
										   </select>
									   </td>
									   <td>&#12288;&#12288;&#12288;&#12288;</td>
									   <td>&#12288;&#12288;&#12288;&#12288;</td>
									   <td>&#12288;&#12288;&#12288;&#12288;</td>
									   <td></td>
								   </tr>

							   </table>
						   </form>
					   </div>
					   <div class="index_table" id="orgSpe1"style="height: 550px;width:100%;margin-top: 30px;">

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
