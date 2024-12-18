<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
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

function selectMenu(menuId){
	$("#"+menuId).find("a").click();
}

$(document).ready(function(){
	init();
	showHosCanvas();
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
		color:['#fc9a75', '#7c9689','#73818a'],
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
function toPage(page){
	shouye(page);
}
function shouye(page){
	 if (page == null || page == undefined || page == '' || isNaN(page)){
		 page=1;
	 }
	var url="<s:url value='/jszy/manage/charge'/>?currentPage="+page;
	window.location.href=url;
}
//医师信息查询
function doctorList(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/jszy/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}
//延期医师查询
function delay(){
	var url = "<s:url value='/jszy/doctor/delay'/>";
	currentJboxLoadNoData("content", url, true);
}
//培训基地清单
function searchOrgInfo(){
	currentJboxLoadNoData("content","<s:url value='/jsp/jszy/global/hospital/specialBaseStatistics.jsp'/>",true);
}
//医师信息统计
function countryBaseStatistics(){
	jboxLoad("content","<s:url value='/jszy/statistic/statisticCountryOrg'/>?trainTypeId=${jszyTrainCategoryEnumChineseMedicine.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
//协同基地信息统计
function jointBaseStatistics(){
	jboxLoad("content","<s:url value='/jszy/statistic/statisticJointOrg'/>?trainTypeId=${jszyTrainCategoryEnumChineseMedicine.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
//基地专业管理
function baseSpecialList(){
	var url = "<s:url value='/jszy/base/orgSpeManage'/>";
	currentJboxLoadNoData("content", url, true);
}
//基地变更查询
function changeBaseMain(){
	var url = "<s:url value='/jszy/manage/changeBase'/>";
	currentJboxLoadNoData("content", url, true);
}
//退培医师查询
function backTrain(){
	var url = "<s:url value='/jszy/doctor/backTrainInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
	currentJboxLoadNoData("content", url, true);
}
//医师专业变更
function speMain(){
	var url = "<s:url value='/jszy/manage/speMain'/>";
	jboxLoad("content", url, true);
}
//师资培训统计
function statisticsTeachTrain(){
	var url = "<s:url value='/jszy/statistic/searchTeachTrain'/>";
	currentJboxLoadNoData("content", url, true);
}
//黑名单管理
function searchBlackInfo(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	var url = "<s:url value='/jszy/blackList/blackListInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>"+"&roleFlag="+roleFlag;
	currentJboxLoadNoData("content", url, true);
}
/*理论成绩*/
function doctorTheoryList(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jszy/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
}
/*技能成绩*/
function doctorSkillList(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/jszy/doctorTheoryScore/doctorSkillList'/>?roleFlag="+roleFlag,true);
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
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
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
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
	if($("#"+"${type.id}").attr("checked")){
		data+="&datas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(data==""){
//		jboxTip("请选择人员类型！");
		return;
	}
	var geturl="<s:url value='/jszy/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize();
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
	var geturl="<s:url value='/jszy/manage/orgSpe'/>?"+$("#orgSpe").serialize();
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
	jboxLoad("schoolNum2","<s:url value='/jszy/manage/graduateNumSearch'/>?workOrgName="+encodeURIComponent(encodeURIComponent(workOrgName))+"&sessionNumber="+schoolSessionNumber);
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
	jboxLoad("orgSpe1","<s:url value='/jszy/manage/orgSpe'/>?"+$("#orgSpe").serialize(),true);
}
function searchInfo()
{
	if($("#trainOrg").val()==""){
		$("#orgFlow").val("");
	}
	console.log($("#orgFlow").val());
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
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
	if($("#"+"${type.id}").attr("checked")){
		data+="&datas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return;
	}
	jboxLoad("doctorNum1","<s:url value='/jszy/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize(),true);
}

	$(document).ready(function(){
		var htmlContent='<table style="text-align: right;font-size: 16px;margin-left:25px">';
		<c:set var="yearpl" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>
		<c:forEach items="${jszyDocTypeEnumList}" var="type">
		htmlContent+='<tr><td style="text-align: right">';
		<c:set value="0"  var="keyll"></c:set>
		<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">
		<c:set value="${doctorCountExtMap[yearpl][type.id]}"  var="keyll"></c:set>
		</c:if>
		var typeName = '${type.name}';
		var keyll = '${keyll}';
		htmlContent+=typeName+'：</td><td>'+keyll+'人&nbsp;</td></tr>';
		</c:forEach>
		htmlContent+='</table>';
		$("#labs").html(htmlContent);
	});
function asseManager(){
	jboxLoad("content","<s:url value='/jszy/asseGlobal/main'/>?roleFlag=${GlobalConstant.USER_LIST_CHARGE}",true);
}
</script>
<style>
body{overflow:hidden;}
 .docTj{
     width: 100%;
 }
.docTj td{
    text-align: center;
    border: none;
    height: 28px;
}
.docTjTypeSpan{
    font-weight: 900;
    line-height: 50px;
}
</style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
<div class="bd_bg">
<div class="yw">

<div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a onclick="shouye();">江苏省中医住院医师规范化培训管理平台</a>
     </h1>
     <div class="account">
       <h2>${sessionScope.currUser.orgName}</h2>
       <div class="head_right">
        <a href="<s:url value='/jszy/manage/charge'/>">首页</a>&#12288;
         <a href="<s:url value='/inx/jszy/logout'/>">退出</a>
       </div>
     </div>
   </div>
 </div>
 <div class="body">
   <div class="container">
     <div class="content_side">
  <%--      <dl class="menu menu_first">
          <dt class="menu_title">
            <i class="icon_menu menu_function"></i>基地信息管理
          </dt>
			   <dd class="menu_item" id="hospitalList"><a onclick="searchOrgInfo();">培训基地清单</a></dd>
        </dl>
        <dl class="menu menu_first">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>医师信息管理
          </dt>
          <dd class="menu_item" ><a onclick="doctorList();">医师信息查询</a></dd>
        </dl>
         <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>培训变更管理
          </dt>
            <dd class="menu_item"><a onclick="changeBaseMain();">基地变更查询</a></dd>
		    <dd class="menu_item"><a onclick="speMain();">医师专业变更</a></dd>
			<dd class="menu_item"><a href="javascript:backTrain();">退培医师查询</a></dd>
			<dd class="menu_item"><a onclick="delay();">延期医师查询</a></dd>
			<dd class="menu_item"><a onclick="searchBlackInfo();">黑名单管理</a></dd>
        </dl>
		 <dl class="menu">
			  <dt class="menu_title">
				<i class="icon_menu menu_statistics"></i>统计分析
			  </dt>
			  <dd class="menu_item"><a href="javascript:countryBaseStatistics();">医师信息统计</a></dd>
			  <dd class="menu_item"><a href="javascript:jointBaseStatistics();">协同基地信息统计</a></dd>
			  <dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>
		 </dl>--%>
         <dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_management"></i>结业管理
             </dt>
             <dd class="menu_item"><a onclick="doctorTheoryList();">理论成绩管理</a></dd>
             <dd class="menu_item"><a onclick="doctorSkillList();">技能成绩管理</a></dd>
			 <dd class="menu_item"><a onclick="asseManager();">结业考核管理</a></dd>
         </dl>
       <%-- <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_setup"></i>设置
          </dt>
           <dd class="menu_item"><a href="javascript:baseSpecialList();">基地专业管理</a></dd>
        </dl>--%>
     </div>
     <div class="col_main" id="content">
       <div class="content_main">
         <div class="index_show">
			 <div class="index_tap_global top_left">
				 <ul class="inner">
					 <li class="index_item_global index_blue">
						 <a href="javascript:selectMenu('hospitalList');">
						  <span class="tap_inner">
						  <span style="float: left;margin-left: 100px">
							<i class="countryOrg"></i>
							<em class="number"><label id="countryOrg">${countryOrgCount}</label></em>
							<strong  class="title">国家基地</strong>
						   </span>
						   <span style="float: left;margin-left: 160px">
							 <i class="jointOrg"></i>
							  <em class="number"><label id="jointOrg">${jointOrgCount}</label></em>
							  <strong  class="title">协同基地</strong>
							</span>
							<%--<span style="float: right;margin-right: 35px">--%>
							  <%--<i class="provinceOrg"></i>--%>
							  <%--<em class="number"><label id="provinceOrg">${provinceOrgCount}</label></em>--%>
							  <%--<strong  class="title">省级基地</strong>--%>
							<%--</span>--%>
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
								<lable class="title" id="labs"></lable>
						</span>
						 </a>
					 </li>
				 </ul>
			 </div>
         </div>
           <div class="main_bd">
               <ul>
                   <li class="score_frame" >
                       <h1 style="background:#f2e9e4;">人员信息概况</h1>
                       <c:set var="currYear" value="${pdfn:getCurrYear()}"></c:set>
                       <div class="grid">
                           <c:forEach step="1" begin="${currYear-2}" end="${currYear}" varStatus="num" var="doctor">
                               <table style="<c:if test='${num.count<3}'>float: left;margin-left:1%;</c:if> <c:if test='${num.count==3}'>margin-left:67%;</c:if>margin-top: 10px;margin-bottom: 10px;border: 1px solid #999;"  cellpadding="0" cellspacing="0" width="32%">
                                   <tbody>
                                   <c:set var="year" value="${currYear-num.count+1}"></c:set>
                                   <c:set var="key" value="${year}pl"></c:set>
                                   <tr>
                                       <th align="center" style="background-color: #e7e6eb;" >${year}级：${doctorCountExtMap[key]["DOCTORCOUNT"]}人</th>
                                   </tr>
                                   <tr>
                                       <td style="border-bottom:1px solid #999;">
                                           <table class="docTj">
                                               <colgroup>
                                                   <col width="28%"/>
                                                   <col width="36%"/>
                                                   <col width="36%"/>
                                               </colgroup>
                                               <tr>
                                                   <td rowspan="2" style="text-align: left;" ><span class="docTjTypeSpan">培训专业：</span></td>
                                                   <td><span>中医</span></td>
                                                   <td><span>中医全科</span></td>
                                               </tr>
                                               <tr>
                                                   <td><span>${doctorCountExtMap[key]["ChineseMedicine"]}人</span></td>
                                                   <td><span>${doctorCountExtMap[key]["TCMGeneral"]}人</span></td>
                                               </tr>
                                           </table>
                                       </td>
                                   </tr>
                                   <tr>
                                       <td style="border-bottom:1px solid #999;">
                                           <table class="docTj">
                                               <colgroup>
                                                   <col width="28%"/>
                                                   <col width="36%"/>
                                                   <col width="36%"/>
                                               </colgroup>
                                               <tr>
                                                   <td rowspan="2" style="text-align: left;" ><span class="docTjTypeSpan">人员类别：</span></td>
                                                   <td><span style="cursor: pointer;" title="本单位人：${doctorCountExtMap[key]["Company"]}；委培单位人：${doctorCountExtMap[key]["CompanyEntrust"]}；社会人：${doctorCountExtMap[key]["Social"]}；">住院医师</span></td>
                                                   <td><span>在校专硕</span></td>
                                               </tr>
                                               <tr>
                                                   <td><span>${doctorCountExtMap[key]["Company"] + doctorCountExtMap[key]["CompanyEntrust"] + doctorCountExtMap[key]["Social"]}人</span></td>
                                                   <td><span>${doctorCountExtMap[key]["Graduate"]}人</span></td>
                                               </tr>
                                                   <%--<tr>--%>
                                                   <%--<td><span>行业人</span></td>--%>
                                                   <%--<td><span>在校专硕</span></td>--%>
                                                   <%--</tr>--%>
                                                   <%--<tr>--%>
                                                   <%--<td><span>${doctorCountExtMap[key]["Social"]}人</span></td>--%>
                                                   <%--<td><span>${doctorCountExtMap[key]["Graduate"]}人</span></td>--%>
                                                   <%--</tr>--%>
                                           </table>
                                       </td>
                                   </tr>
                                       <%--<tr>--%>
                                       <%--<td align="left">--%>
                                       <%--<p>--%>
                                       <%--<span style="float: left;font-weight: 900;line-height: 50px;">基地类型：&#12288;</span>--%>
                                       <%--<span style="float: left">国家基地&#12288;<br/>&#12288;${doctorCountExtMap[key]["CountryOrg"]+doctorCountExtMap[key]["JointOrg"]}人</span>--%>
                                       <%--&lt;%&ndash;<span style="float: left">省级基地<br/>&#12288;${doctorCountExtMap[key]["ProvinceOrg"]}人</span>&ndash;%&gt;--%>
                                       <%--</p>--%>
                                       <%--</td>--%>
                                       <%--</tr>--%>
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
					   <h1 style="background:#f2e9e4;">当前住培情况</h1>
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
								   <div style="float: left;margin-left:80px;height: 10px;width: 10px;background: #fc9a75;"></div><div style="float: left;line-height: 10px;"> ：在培人数</div>
								   <div style="float: left;margin-left:20px;height: 10px;width: 10px;background: #7c9689;"></div><div style="float: left;line-height: 10px;"> ：已考核待结业人数</div>
								   <div style="float: left;margin-left:20px;height: 10px;width: 10px;background: #93818a;"></div><div style="float: left;line-height: 10px;"> ：结业人数</div></td></tr>
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
