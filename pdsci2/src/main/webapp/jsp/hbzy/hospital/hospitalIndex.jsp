<%@ page import="com.pinde.sci.util.jszy.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
	$(document).ready(function(){
		init();
		showHosCanvas();
//		searchInfo();
	});
	function showHosCanvas(){
		var myChart = echarts.init(document.getElementById('docChartForIndex'));
		option = {
			tooltip : {
				trigger: 'item',
				formatter: "{b} ({d}%)"
			},
			title : {
				text: '数据自2015年起',
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
		function init()
		{
			<c:forEach items="${jszyResDocTypeEnumList}" var="type">
			$("#"+"${type.id}").attr("checked",true);
			</c:forEach>
			initOrg(null,null);
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
			<c:forEach items="${jszyResDocTypeEnumList}" var="type">
			if($("#"+"${type.id}").attr("checked")){
				data+="&datas="+$("#"+"${type.id}").val();
			}
			</c:forEach>
			if(data==""){
				jboxTip("请选择人员类型！");
				return;
			}
			var geturl="<s:url value='/hbzy/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize();
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
		function bindFunc()
		{
			$("#trainTypeId").unbind("change").bind("change",searchInfo);
			//$("#sessionNumber").unbind("change").bind("change",searchInfo);
			$("[name='datas']").unbind("change").bind("change",searchInfo);

		}
		function searchInfo()
		{
			if($("#trainOrg").val()==""){
				$("#orgFlow2").val("");
			}
			var sessionNumber=$("#sessionNumber").val();
			if(sessionNumber==""){
				jboxTip("年份不能为空！");
				return;
			}
//			var trainTypeId=$("#trainTypeId").val();
//			if(trainTypeId==""){
//				jboxTip("培训类别不能为空！");
//				return;
//			}
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
			jboxLoad("doctorNum1","<s:url value='/hbzy/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize(),true);
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
				$("#orgFlow2").val(this.id);
				searchInfo();
			};
			$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow2",true);
		}
	</script>
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
	<c:if test="${ param.needScorll =='Y'}">
		var container = $('#indexBody'),
				scrollTo =$("#indexForm");
		container.scrollTop(scrollTo.offset().top - container.offset().top + container.scrollTop());
	</c:if>
});
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
	var url = "<s:url value='/hbzy/manage/getNeedReducation'/>";
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

function resMain(){
	window.open("<s:url value='/hbzy/singup/login'/>?userFlow=${sessionScope.currUser.userFlow}");
}
onresize=function(){
	setBodyHeight();
}
//学员信息查询
function doctorList(){
	var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
	jboxLoad("content","<s:url value='/hbzy/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}
//学员信息审核
function recruitAuditSearch(currentPage){
	if(currentPage == undefined){
		currentPage =1;
	}
	var url = "<s:url value='/hbzy/manage/province/doctor/recruitAuditSearch'/>?source=hospital";
	jboxLoad("content", url, true);
}
function selectMenu(menuId){
	$("#"+menuId).find("a").click();
}

//学员减免管理
function reductionTab(){
	var url = "<s:url value='/hbzy/reduction/reductionTab?roleId=${GlobalConstant.USER_LIST_LOCAL}'/>";
	currentJboxLoadNoData("content", url, true);
}
//基地变更管理
function changeBaseTab(){
	var url = "<s:url value='/hbzy/manage/changeBaseTab?roleId=${GlobalConstant.USER_LIST_LOCAL}'/>";
	currentJboxLoadNoData("content", url, true);
}

function shouye(page,needScorll){
	 if (page == null || page == undefined || page == '' || isNaN(page)){
		 page=1;
	 }
	var url="<s:url value='/hbzy/manage/local'/>?currentPage="+page+"&needScorll="+needScorll;
	window.location.href=url;
}
//方案减免维护
function reductionRotationOper(){
	data = $("#searchFormReduction").serialize() || {
		degreeType : "${GlobalConstant.FLAG_Y}",
		currentPage : 1,
	};
	var url = "<s:url value='/hbzy/manage/reductionRotationOper'/>";
	jboxStartLoading();
	jboxPost(url,data,function(resp){
		jboxEndLoading();
		$("#content").html(resp);
	},null,false);
	var orgFlow=$("#orgFlow").val();
	getNeedReducation("${orgFlow}",function(result){
		if(result==0){
			$("#reducationCount").hide();
		}
	});
}

function toPage(page){
	if(!page){
		$("#currentPage").val(page);			
	}
	shouye(page,'Y');
}
//学员专业变更
function speMain(){
	var url = "<s:url value='/hbzy/manage/changeSpeTab?roleId=${GlobalConstant.USER_LIST_LOCAL}'/>";
	jboxLoad("content", url, true);
}
//学员退培管理
function backTrain(){
	var url = "<s:url value='/hbzy/delayReturn/backTrainQuery4Local'/>";
	currentJboxLoadNoData("content", url, true);
}
//师资培训统计
function statisticsTeachTrain(){
	var url = "<s:url value='/hbzy/statistic/searchTeachTrain'/>";
	currentJboxLoadNoData("content", url, true);
}
//延期学员查询
function delay(){
	var url = "<s:url value='/hbzy/delayReturn/delayQuery4Local'/>";
	currentJboxLoadNoData("content", url, true);
}
//黑名单信息查询
function searchBlackInfo(){
	var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
	var url = "<s:url value='/hbzy/blackList/blackListInfo'/>?sessionNumber=${pdfn:getCurrYear()}&currentPage=1&roleFlag="+roleFlag;
	jboxLoad("content", url, true);
}
//证书信息查询
function certificateManage(){
	var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
	jboxLoad("content","<s:url value='/hbzy/certificateManage/main'/>?roleFlag="+roleFlag,true);
}
function graduationManager(pageType,tabType){
	jboxLoad("content","<s:url value='/hbzy/graduationManager/main'/>?roleFlag=${GlobalConstant.USER_LIST_LOCAL}&pageType="+pageType+"&tabType="+tabType,true);
}
function asseManager(){
	jboxLoad("content","<s:url value='/hbzy/asseGlobal/main'/>?roleFlag=${GlobalConstant.USER_LIST_LOCAL}",true);
}
/*结业成绩查询*/
function doctorTheoryList(){
    var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
    jboxLoad("content","<s:url value='/hbzy/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
}
$(document).ready(function(){
	var htmlContent='<table style="text-align: right;font-size: 16px;margin-left:25px">';
	<c:set var="yearpl" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
	<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">
	htmlContent+='<tr><td style="text-align: right">';
	var keyll ='${doctorCountExtMap[yearpl][type.id]}';
	</c:if>
	var typeName = '${type.name}';

	if(keyll=='' ||keyll==null){
		keyll=0;
	}
	htmlContent=htmlContent+typeName+'：</td><td>'+keyll+'人&nbsp;</td></tr>';
	</c:forEach>
	htmlContent+='</table>';
	$("#labs").html(htmlContent);
})

function noticeManage(resNoticeSysId,typeId){
    var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
    jboxLoad("content","<s:url value='/hbzy/notice/main'/>?roleFlag="+roleFlag+"&sysId="+resNoticeSysId+"&typeId="+typeId,true);
}

</script>
<style>
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
		 <img onclick="shouye();" src="<s:url value='/jsp/inx/hbzy/img/hbzy_head.png'/>" style="margin-top: 30px;"/>
     </h1>
     <div class="account">
       <h2 class="head_right">${sessionScope.currUser.orgName }</h2>
       <div class="head_right">
        <a onclick="shouye();">首页</a>&#12288;
         <a href="<s:url value='/inx/hbzy/logout'/>">退出</a>
       </div>
     </div>
   </div>
 </div>

 <div class="body">
   <div class="container">
	   <div class="content_side">
		   <dl class="menu menu_first">
			   <dt class="menu_title">
				   <i class="icon_menu menu_management"></i>学员信息管理
			   </dt>
			   <dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">学员信息审核</a></dd>
			   <dd class="menu_item" id="doctorList"><a onclick="doctorList();">学员信息查询</a></dd>
			   <dd class="menu_item" id="reductionRotationOper"><a onclick="reductionRotationOper();">方案减免维护
				   <img id="reducationCount" style="display: none;"
						src="<s:url value="/jsp/hbzy/images/gantanhao.png" />"/></a>
			   </dd>
		   </dl>
		   <dl class="menu">
			   <dt class="menu_title">
				   <i class="icon_menu menu_management"></i>培训变更管理
			   </dt>
			   <dd class="menu_item">
				   <a onclick="reductionTab();">学员减免管理
					   <img id="reductionFlag" style="display: none;"
							src="<s:url value="/jsp/hbzy/images/gantanhao.png" />"/>
				   </a>
			   </dd>
			   <dd class="menu_item">
				   <a onclick="changeBaseTab();">基地变更管理
					   <img id="baseFlag" style="display: none;"
							src="<s:url value="/jsp/hbzy/images/gantanhao.png" />"/>
				   </a>
			   </dd>
			   <dd class="menu_item">
				   <a onclick="speMain();">专业变更管理
					   <img id="speFlag" style="display: none;" src="<s:url value="/jsp/hbzy/images/gantanhao.png" />"/>
				   </a>
			   </dd>
			   <dd class="menu_item"><a onclick="delay();">延期学员管理</a></dd>
			   <dd class="menu_item"><a href="javascript:backTrain();">退培学员管理</a></dd>
			   <dd class="menu_item"><a href="javascript:searchBlackInfo();">黑名单查询</a></dd>
		   </dl>
		   <dl class="menu">
			   <dt class="menu_title">
				   <i class="icon_menu menu_statistics"></i>结业管理
			   </dt>
               <dd class="menu_item"><a onclick="doctorTheoryList();">结业成绩查询</a></dd>
			   <dd class="menu_item"><a onclick="asseManager();">理论考核管理</a></dd>
			   <dd class="menu_item"><a onclick="graduationManager('Audit','LocalAudit');">结业学员确认</a></dd>
			   <dd class="menu_item"><a onclick="graduationManager('Query','');">证书信息查询</a></dd>
		   </dl>
		   <dl class="menu">
			   <dt class="menu_title">
				   <i class="icon_menu menu_statistics"></i>过程
			   </dt>
			   <dd class="menu_item"><a href="javascript:resMain();">过程管理</a></dd>
		   </dl>
           <dl class="menu">
               <dt class="menu_title">
                   <i class="icon_menu menu_function"></i>资讯管理
               </dt>
               <dd class="menu_item"><a href="javascript:noticeManage('${GlobalConstant.RES_NOTICE_SYS_ID}','LM10');">基地公告</a></dd>
           </dl>
		   <%--<dl class="menu">--%>
		   <%--<dt class="menu_title">--%>
		   <%--<i class="icon_menu menu_statistics"></i>统计分析--%>
		   <%--</dt>--%>
		   <%--<dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>--%>
		   <%--</dl>--%>
	   </div>
     <div class="col_main" id="content">
       <div class="content_main">
           <div class="index_show">
			 <div class="index_tap_global top_left">
				 <ul class="inner">
					 <li class="index_item_global index_blue">
						 <a href="javascript:selectMenu('recruitAuditSearch');">
                  <span class="tap_inner">
                    <i class="wait"></i>
                    <em class="number">${waitCount}条</em>
                    <strong  class="title">培训信息待审核</strong>
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
							<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">级</c:if>培训学员数</strong>
					  </span>
						 </a>
					 </li>
					 <li class="index_item" style="width:50%; float:left;">
						 <a href="javascript:void(0);">
                  	<span class="tap_inner tab_second" style="margin-top: -10px;">
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
									   <th align="center" style="background-color: #e7e6eb;" >${year}级&#12288;${doctorCountExtMap[key]["DOCTORCOUNT"]}人</th>
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
												   <td><span title="本单位人：${doctorCountExtMap[key]["Company"]}；委培单位人：${doctorCountExtMap[key]["CompanyEntrust"]}；社会人：${doctorCountExtMap[key]["Social"]}；">住院医师</span></td>
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
					   <table class="in_table" style="float: left;width: 48%; margin-top: 10px;margin: 10px;margin-bottom: 10px;border: 1px solid #999;" cellpadding="0" cellspacing="0" width="100%">
						   <tbody>
						   <tr>
							   <td>
								   <div id="docChartForIndex" style="height: 200px;"></div>
							   </td>
						   </tr>
						   </tbody>
					   </table>
					   <div class="grid" style="overflow: auto;height: 200px;width: auto;margin: 10px;border:1px solid #999;">
						   <table class="in_table" style="border:none;border-bottom:1px solid #999;" cellpadding="0" cellspacing="0" width="100%">
							   <colgroup>
								   <col width="25%"/>
								   <col width="25%"/>
								   <col width="25%"/>
								   <col width="25%"/>
							   </colgroup>
							   <tr style="height: 60px;"><td colspan="4" style="border-bottom:1px solid #999;">
								   <div style="float: left;margin-left:80px;height: 10px;width: 10px;background: #fc9a75;"></div><div style="float: left;line-height: 10px;"> ：在培人数</div>
								   <div style="float: left;margin-left:20px;height: 10px;width: 10px;background: #7c9689;"></div><div style="float: left;line-height: 10px;"> ：已考核待结业人数</div>
								   <div style="float: left;margin-left:20px;height: 10px;width: 10px;background: #93818a;"></div><div style="float: left;line-height: 10px;"> ：结业人数</div></td></tr>
							   <c:forEach items="${currDocDetailMaps}" var="currDocDetail">
								   <tr style="height:50px;">
									   <td style="text-align: center;border-bottom:1px solid #999;">${currDocDetail['SESSIONNUMBER']}级</td>
									   <td style="text-align: center;border-bottom:1px solid #999;">${currDocDetail['20'] ==null? 0:currDocDetail['20']}人</td>
									   <td style="text-align: center;border-bottom:1px solid #999;">${currDocDetail['22'] ==null? 0:currDocDetail['22']}人</td>
									   <td style="text-align: center;border-bottom:1px solid #999;">${currDocDetail['21'] ==null? 0:currDocDetail['21']}人</td>
								   </tr>
							   </c:forEach>
						   </table>
					   </div>
				   </li>
			   </ul>
		   </div>
		   <%--<div class="main_bd" style="height:auto;">--%>
			   <%--<ul>--%>
				   <%--<li class="score_frame">--%>
					   <%--<h1 style="background:#f2e9e4;">学员信息概况</h1>--%>
					   <%--<div class="" style="margin-top: 15px;">--%>
						   <%--<form id="doctorNumSearch">--%>
							   <%--<table cellspacing="8px">--%>
								   <%--<tr>--%>
									   <%--<td <c:if test="${countryOrg != 'countryOrg'}">hidden="hidden"</c:if>>&#12288;培训基地：</td>--%>
									   <%--<td <c:if test="${countryOrg != 'countryOrg'}">hidden="hidden"</c:if>>--%>
										   <%--<div style="width:150px;">--%>
											   <%--<input id="trainOrg" class="input" type="text"  style="margin-left: 0px;width: 150px" autocomplete="off"/>--%>
											   <%--<input id="orgFlow2" name="orgFlow"  class="input" type="text" hidden  style="margin-left: 0px;width: 150px" />--%>
										   <%--</div>--%>
									   <%--</td>--%>
									   <%--<td>&#12288;培训类别：</td>--%>
									   <%--<td>--%>
										   <%--<select onchange="searchInfo();" name="trainTypeId" id="trainTypeId" class="select" style="width:150px;">--%>
											   <%--<option value="">全部</option>--%>
											   <%--<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">--%>
												   <%--<option value="${trainCategory.id}"--%>
														   <%--<c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>--%>
												   <%-->${trainCategory.name}</option>--%>
											   <%--</c:forEach>--%>
										   <%--</select>--%>
									   <%--</td>--%>
								   <%--</tr>--%>
								   <%--<tr><td>&nbsp;</td></tr>--%>
								   <%--<tr>--%>
									   <%--<td>&#12288;年&#12288;&#12288;级：</td>--%>
									   <%--<td>--%>
										   <%--<input type="text" id="sessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchInfo();}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 150px;margin-left: 0px" />--%>
									   <%--</td>--%>
									   <%--<td>&#12288;人员类型：</td>--%>
									   <%--<td colspan="2">--%>
										   <%--<c:forEach items="${jszyResDocTypeEnumList}" var="type">--%>
											   <%--<label><input onchange="searchInfo();" name="datas" type="checkbox" id="${type.id}" name="${type.id}" value="${type.id}"/>${type.name}&nbsp;</label>--%>
										   <%--</c:forEach>--%>
									   <%--</td>--%>
									   <%--<td></td>--%>
									   <%--<td></td>--%>
									   <%--<td></td>--%>
									   <%--&lt;%&ndash;<td><input class="btn_green" type="button" value="查询" onclick="searchInfo();"/></td>&ndash;%&gt;--%>
								   <%--</tr>--%>
							   <%--</table>--%>
						   <%--</form>--%>
					   <%--</div>--%>
					   <%--<div class="index_table" id="doctorNum1"style="height: 550px;width:100%;margin-top: 50px;">--%>

					   <%--</div>--%>
				   <%--</li>--%>
			   <%--</ul>--%>
		   <%--</div>--%>
        <c:set value="jswjw_${currUser.orgFlow}_P001" var="orgFlow"/>
        <div class="index_form" style="margin-bottom: 10px;" id="indexForm">
          <h3 style="background:#f2e9e4;">通知公告</h3>
          	<ul class="form_main">
          		<c:forEach items="${infos}" var="msg">
           		 <li>
           		 <strong><a href="<s:url value='/inx/hbzy/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
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
	  	     <pd:pagination-jszy toPage="toPage"/>
           </span>
        </div>
			</div>
		</div>
		</div>
       </div>
     </div>
   </div>
   <div class="foot">
   <div class="foot_inner">
       主管单位：湖北省中医药局   |  技术支持：南京品德网络信息技术有限公司
   </div>
 </div>
 </div>
  <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp"></jsp:include>
	</c:if>
 
 

</body>
</html>
 