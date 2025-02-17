<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>江苏省住院医师规范化培训管理平台</title>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic_bootstrap" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_cxselect" value="true"/>
	</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
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
		function archiveDoctorList(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?isArchive=Y&roleFlag="+roleFlag,true);
		}
		function asseAudit(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/asse/main'/>?roleFlag="+roleFlag+"&tabTag=WaitAudit",true);
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
			init();
			showHosCanvas();
			showDoctorCanvas();
		});

		function showHosCanvas(){
			var myChart = echarts.init(document.getElementById('docChartForIndex'));
			option = {
				tooltip : {
					trigger: 'item',
					formatter: "{b} ({d}%)"
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
			var url = "<s:url value='/jsres/doctor/backTrainInfo?currentPage=1'/>";
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
		function docWorkQuery(){
			jboxLoad("content","<s:url value='/jsres/docWork/docWorkQuery?roleFlag=charge'/>",true);
		}
		function statisticsTeachTrain(){
			var url = "<s:url value='/jsres/statistic/searchTeachTrain'/>";
			currentJboxLoadNoData("content", url, true);
		}
		/*理论成绩*/
		function doctorTheoryList(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
		}
		/*技能成绩*/
		function doctorSkillList(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorSkillList'/>?roleFlag="+roleFlag,true);
		}
		/*结业合格人员*/
		function theoryPassedList(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/theoryPassedList'/>?roleFlag="+roleFlag,true);
		}
		/*公共科目成绩*/
		function doctorPublicList(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorPublicList'/>?roleFlag="+roleFlag,true);
		}
		function CertificateManage(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/certificateManage/main'/>?roleFlag="+roleFlag,true);
		}
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
			searchGraduates();
			setTimeout(bindFunc, 3000);
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
			$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
			var itemSelectFuntion = function(){
				$("#orgFlow2").val(this.id);
				searchInfo2();
			};
			$.selectSuggest('trainOrg2',datas,itemSelectFuntion,"orgFlow2",true);
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
		function searchGraduates()
		{
			var sessionNumber=$("#schoolSessionNumber").val();
			if(sessionNumber==""){
				jboxTip("年份不能为空！");
				return;
			}
			var workOrgName=$("#workOrgName").val();
			var schoolSessionNumber=$("#schoolSessionNumber").val();
			jboxLoad("schoolNum2","<s:url value='/jsres/manage/graduateNumSearch'/>?workOrgName="+encodeURIComponent(encodeURIComponent(workOrgName))+"&sessionNumber="+schoolSessionNumber);
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
			$("[name='docType']").unbind("change").bind("change",searchInfo);

		}
		function changeOrg(obj)
		{
			var cityid=$("#cityId").val();
			var typeid=$("#orgLevelId").val();
			$("#trainOrg").val("");
			initOrg(cityid,typeid);
		}
		function searchInfo2()
		{
			if($("#trainOrg2").val()==""){
				$("#orgFlow2").val("");
			}
			jboxLoad("orgSpe1","<s:url value='/jsres/manage/orgSpe'/>?"+$("#orgSpe").serialize(),true);
		}
		function searchInfo()
		{
			if($("#trainOrg").val()==""){
				$("#orgFlow").val("");
			}
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
		function zlDocQuery(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/main'/>?roleFlag="+roleFlag,true);
		}
		function zltjOrg(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/zltjOrgMain'/>?tabId=doctorType&roleFlag="+roleFlag,true);
		}
		function signUpmain(typeId){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag="+roleFlag+"&typeId="+typeId,true);
		}
		function scoreManage() {
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/scoreManage/main'/>?roleFlag="+roleFlag,true);
		}
		function monthlyReport(){
			window.open("<s:url value='/jsres/monthlyReportCity/main'/>","_blank")
		}
		//考核资格审查
		function asseAudit(){
			var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
			jboxLoad("content","<s:url value='/jsres/asse/main'/>?roleFlag="+roleFlag+"&tabTag=WaitAudit",true);
		}
	</script>
	<style>

	</style>
</head>

<body>
<div  id="indexBody">
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
								<i class="icon_menu menu_recruit"></i>招录信息管理
							</dt>
							<dd class="menu_item"><a onclick="zlDocQuery();">招录学员查询</a></dd>
							<dd class="menu_item"><a onclick="zltjOrg();">招录统计-基地</a></dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_doctor"></i>异动学员查询
							</dt>
							<dd class="menu_item"><a onclick="changeBaseMain();">基地变更查询</a></dd>
							<dd class="menu_item"><a onclick="speMain();">专业变更查询</a></dd>
							<dd class="menu_item"><a href="javascript:backTrain();">退培学员查询</a></dd>
							<dd class="menu_item"><a onclick="delay();">延期学员查询</a></dd>
							<dd class="menu_item"><a onclick="searchBlackInfo();">黑名单管理</a></dd>
						</dl>
						<dl class="menu menu_first">
							<dt class="menu_title">
								<i class="icon_menu menu_management_train_query"></i>学员培训查询
							</dt>
							<!-- <dd class="menu_item" id="auditDoctors"><a onclick="auditDoctors();">医师信息审核</a></dd> -->
							<dd class="menu_item" ><a onclick="javascript:doctorList();">学员信息查询</a></dd>
							<%--<dd class="menu_item"><a href="javascript:archiveDoctorList();">存档学员查询</a></dd>--%>
							<dd class="menu_item"><a onclick="gradeSearch();">出科评价查询</a></dd>
							<dd class="menu_item"><a href="javascript:docWorkQuery();">学员工作量查询</a></dd>

						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_management_complete"></i>结业管理
							</dt>
							<dd class="menu_item"><a onclick="asseAudit();">资格审核</a></dd>
							<dd class="menu_item"><a href="javascript:signUpmain();">补考审核</a></dd>
							<dd class="menu_item"><a onclick="scoreManage()">成绩管理</a></dd>
							<dd class="menu_item"><a onclick="CertificateManage();">证书管理</a></dd>
						</dl>
						<%--<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_management"></i>结业信息管理
							</dt>
							<dd class="menu_item"><a onclick="asseAudit();">考核资格审查</a></dd>
							<dd class="menu_item"><a href="javascript:signUpmain('Theory');">理论补考查询</a></dd>
							<dd class="menu_item"><a href="javascript:signUpmain('Skill');">技能补考查询</a></dd>
							<dd class="menu_item"><a onclick="doctorPublicList();">公共科目管理</a></dd>
							<dd class="menu_item"><a onclick="doctorTheoryList();">理论成绩查询</a></dd>
							<dd class="menu_item"><a onclick="doctorSkillList();">技能成绩管理</a></dd>
							<dd class="menu_item"><a onclick="theoryPassedList();">结业合格人员查询</a></dd>
							<dd class="menu_item" id="doctorScoreApplyList"><a onclick="doctorScoreApplyList();">证书申请查询</a></dd>
							<dd class="menu_item"><a onclick="CertificateManagement();">证书查询</a></dd>
							<dd class="menu_item"><a onclick="CertificateManage();">证书信息查询</a></dd>
						</dl>--%>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_statis"></i>统计分析
							</dt>
							<dd class="menu_item"><a href="javascript:countryBaseStatistics();">学员信息统计</a></dd>
							<dd class="menu_item"><a href="javascript:jointBaseStatistics();">协同基地信息统计</a></dd>
							<dd class="menu_item"><a href="javascript:statisticsAppUser();">APP使用情况统计</a></dd>
							<dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>
							<dd class="menu_item"><a href="javascript:monthlyReport();">培训月报查询</a></dd>
						</dl>
						<dl class="menu">
							<dt class="menu_title">
								<i class="icon_menu menu_system"></i>设置
							</dt>
							<dd class="menu_item"><a href="javascript:baseSpecialList();">基地专业管理</a></dd>
							<dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
						</dl>
					</div>
					<div class="col_main" id="content">
						<div class="content_main">
							<div class="index_show">
								<div class="index_tap_global top_left">
									<ul class="inner">
										<li class="index_item_global index_blue">
											<a href="javascript:selectMenu('hospitalList');">
											  <span class="tap_inner">
											  <span style="float: left;margin-left: 35px">
												<i class="countryOrg"></i>
												<em class="number"><label id="countryOrg">${countryOrgCount}</label></em>
												<strong  class="title">国家基地</strong>
											   </span>
											   <span style="float: left;margin-left: 95px">
												 <i class="jointOrg"></i>
												  <em class="number"><label id="jointOrg">${jointOrgCount}</label></em>
												  <strong  class="title">协同基地</strong>
												</span>
												<span style="float: right;margin-right: 35px">
												  <i class="provinceOrg"></i>
												  <em class="number"><label id="provinceOrg">${provinceOrgCount}</label></em>
												  <strong  class="title">省级基地</strong>
												</span>
											  </span>
											</a>
										</li>
									</ul>
								</div>
								<div class="index_tap_global top_right">
									<ul class="inner">
										<li class="index_item" style="width:50%; float:left;">
											<a href="javascript:selectMenu('auditDoctors');">
											  <span class="tap_inner">
												<i class="crowd"></i>
												<c:set var="key" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>
												<em class="number"><label id="trainDoctor">${doctorCountExtMap[key]["DOCTORCOUNT"]}人</label></em>
												<strong  class="title">${sysCfgMap['jsres_doctorCount_sessionNumber']}
													<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">届</c:if>培训医师数</strong>
											  </span>
											</a>
										</li>
										<li class="index_item" style="width:50%; float:left;">
											<a href="javascript:void(0);">
												<span class="tap_inner tab_second">
													<c:set var="yearpl" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>
													<c:forEach items="${resDocTypeEnumList}" var="type">
														<c:set value="0"  var="keyll"></c:set>
														<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">
															<c:set value="${doctorCountExtMap[yearpl][type.id]}"  var="keyll"></c:set>
														</c:if>
														<lable class="title">${type.name}:${keyll}人&nbsp;<br/></lable>
													</c:forEach>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
							<div class="main_bd">
								<ul>
									<li class="score_frame" >
										<h1 style="background:#e7f5fc;">人员信息概况</h1>
										<c:set var="currYear" value="${pdfn:getCurrYear()}"></c:set>
										<div class="grid">
											<c:forEach step="1" begin="${currYear-2}" end="${currYear}" varStatus="num" var="doctor">
												<table style="<c:if test='${num.count<3}'>float: left;margin-left:1%;</c:if> <c:if test='${num.count==3}'>margin-left:67%;</c:if>margin-top: 10px;margin-bottom: 10px;border: 1px solid #ccc;"  cellpadding="0" cellspacing="0" width="32%">
													<tbody>
													<c:set var="year" value="${currYear-num.count+1}"></c:set>
													<c:set var="key" value="${year}pl"></c:set>
													<tr>
														<th align="center" style="background-color: #e7e7eb;" >${year}届&#12288;${doctorCountExtMap[key]["DOCTORCOUNT"]}人</th>
													</tr>
													<tr>
														<td align="left">
															<p style="width:100%">
																<span style="float: left;font-weight: 900;width:33%">培训类别：</span>
																<span style="float: left;width:33%;text-align:center;">住院医师<br/>${doctorCountExtMap[key]["DoctorTrainingSpe"]}人</span>
																<span style="float: left;width:33%;text-align:center;">助理全科<br/>${doctorCountExtMap[key]["AssiGeneral"]}人</span>
															</p>
														</td>
													</tr>
													<tr>
														<td align="left">
															<p style="width:100%">
																<span style="float: left;font-weight: 900;width:30%">人员类型：</span>
																<span style="float: left;width:35%;text-align:center;">本单位人<br/>${doctorCountExtMap[key]["Company"]}人</span>
																<span style="float: left;width:35%;text-align:center;">委培单位人<br/>${doctorCountExtMap[key]["CompanyEntrust"]}人</span>
															</p>
															<p style="width:100%">
																<span style="float: left;font-weight: 900;width:30%">&#12288;</span>
																<span style="float: left;width:35%;text-align:center;">社会人<br/>${doctorCountExtMap[key]["Social"]}人</span>
																<span style="float: left;width:35%;text-align:center;">在校专硕<br/>${doctorCountExtMap[key]["Graduate"]}人</span>
															</p>
														</td>
													</tr>
													<tr>
														<td align="left">
															<p style="width:100%">
																<span style="float: left;font-weight: 900;width:33%;">基地类型：</span>
																<span style="float: left;width:33%;text-align:center;">国家基地<br/>（含协同）<br/>${doctorCountExtMap[key]["CountryOrg"]+doctorCountExtMap[key]["JointOrg"]}人</span>
																<span style="float: left;width:33%;text-align:center;">省级基地<br/>${doctorCountExtMap[key]["ProvinceOrg"]}人</span>
															</p>
														</td>
													</tr>
													</tbody>
												</table>
											</c:forEach>
										</div>
									</li>
								</ul>
							</div>
							<div class="main_bd">
								<ul>
									<li class="score_frame">
										<h1 style="background:#e7f5fc;">当前住培情况</h1>
										<table class="in_table" style="float: left;width: 48%; margin-top: 10px;margin: 10px;margin-bottom: 10px;border: 1px solid #ccc;" cellpadding="0" cellspacing="0" width="100%">
											<tbody>
											<tr>
												<td>
													<div id="docChartForIndex" style="height: 200px;"></div>
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
												<tr style="height: 60px;"><td colspan="4">
													<div style="float: left;margin-left:80px;height: 10px;width: 10px;background: rgba(255,120,50,1);"></div><div style="float: left;line-height: 10px;"> ：在培人数</div>
													<div style="float: left;margin-left:20px;height: 10px;width: 10px;background: rgba(100,200,255,1);"></div><div style="float: left;line-height: 10px;"> ：已考核待结业人数</div>
													<div style="float: left;margin-left:20px;height: 10px;width: 10px;background: rgba(255,120,255,1);"></div><div style="float: left;line-height: 10px;"> ：结业人数</div></td></tr>
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
													<%--<td style="text-align: center">系统总访问量：${count}次</td>--%>
												<%--</tr>--%>
											<%--</table>--%>
										<%--</div>--%>
									</li>
								</ul>
							</div>
							<div class="main_bd" style="height:auto;">
								<ul>
									<li class="score_frame">
										<h1 style="background:#e7f5fc;">医师信息概况</h1>
										<div class="" style="margin-top: 15px;">
											<form id="doctorNumSearch">
												<table cellspacing="8px">
													<tr>
														<td hidden="hidden">&#12288;地&#12288;&#12288;区：</td>
														<td hidden="hidden">
															<div id="provCityAreaId" style="width:150px;">
																<select id="cityId" name="cityId" style="width:150px;" class="city select" data-value="" data-first-title="选择市" onchange="changeOrg(this);"></select>
															</div>
														</td>
														<td>&#12288;培训基地：</td>
														<td>
															<div style="width:150px;">
																<input id="trainOrg" class="input" type="text"  style="margin-left: 0px;width: 150px" autocomplete="off" />
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
														<td>&#12288;年&#12288;&#12288;级：</td>
														<td>
															<input type="text" id="sessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchInfo();}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 150px;margin-left: 0px" />
														</td>
														<td>&#12288;人员类型：</td>
														<td colspan="2">
															<c:forEach items="${resDocTypeEnumList}" var="type">
																<label><input name="docType" type="checkbox" id="${type.id}" name="${type.id}" value="${type.id}"/>${type.name}&nbsp;</label>
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
										<h1 style="background:#e7f5fc;">高校在校专硕信息概况</h1>
										<div class="" style="margin-top: 15px;">
											<form id="graduateNumSearch" method="post">
												<table cellspacing="8px">
													<tr>
														<td>&#12288;年级：</td>
														<td>
															<input type="text" id="schoolSessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchGraduates();}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 150px;margin-left: 0px" />
														</td>
														<td>&#12288;医学院校：</td>
														<td>
															<select name="workOrgName" id="workOrgName" class="select" style="width:150px;" onchange="searchGraduates();">
																<option value="">请选择</option>
																<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
																	<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
																</c:forEach>
															</select>
														</td>
													</tr>
													<tr><td>&nbsp;</td></tr>
													<tr>
														<td></td>
														<td></td>
														<td></td>
														<%--<td><input class="btn_green" type="button" value="查询" onclick="searchInfo();"/></td>--%>
													</tr>
												</table>
											</form>
										</div>
										<div class="index_table" id="schoolNum2"style="height: 550px;width:100%;margin-top: 50px;">

										</div>
									</li>
								</ul>
							</div>
							<div class="main_bd" style="height:auto;">
								<ul>
									<li class="score_frame">
										<h1 style="background:#e7f5fc;">专业基地概况</h1>
										<div class="" style="margin-top: 15px;">
											<form id="orgSpe">
												<table cellspacing="8px">
													<tr>
														<td>&#12288;培训基地：</td>
														<td>
															<div style="width:150px;">
																<input id="trainOrg2"  class="input"  type="text"  style="margin-left: 0px;width: 150px" autocomplete="off"/>
																<input id="orgFlow2" name="orgFlow"  class="input" type="text" hidden  style="margin-left: 0px;width: 150px" />
															</div>
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
