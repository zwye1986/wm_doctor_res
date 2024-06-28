<%@ page import="com.pinde.sci.util.jszy.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
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
			var myChart = ec.init(document.getElementById('docChartForIndex'));
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
		});

		$(document).ready(function(){
			var htmlContent='<table style="text-align: right;font-size: 16px;margin-left:25px">';
			<c:set var="yearpl" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>
			<c:forEach items="${jszyResDocTypeEnumList}" var="type">
			htmlContent+='<tr><td style="text-align: right">';
			<c:set value="0"  var="keyll"></c:set>
			<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">
			<c:set value="${doctorCountExtMap[yearpl][type.id]}"  var="keyll"></c:set>
			</c:if>
			var typeName ='${type.name}';

			htmlContent=htmlContent+typeName+'：</td><td>'+'${keyll}人&nbsp;</td></tr>';

			</c:forEach>
			htmlContent+='</table>';
			$("#labs").html(htmlContent);
		})

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
	if("${speFlag}"!="0"){
		$("#speFlag").show();
	}
	if ("${changeOrgFlag}" != "0") {
		$("#changeOrgFlag").show();
	}
	if("${delayFlag}"!="0"){
		$("#delayFlag").show();
	}
	if ("${backFlag}" != "0") {
		$("#backFlag").show();
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
//医师信息查询
function doctorList(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/hbzy/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}
//在培学员统计
function zpxytj(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/hbzy/doctorRecruit/zpxytj'/>?roleFlag="+roleFlag,true);
}
//招录学员信息查询
function doctorListZl(){
    var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
    jboxLoad("content","<s:url value='/hbzy/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag+"&zlFlag=Y",true);
}
function noticeManage(resNoticeSysId,typeId){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/hbzy/notice/main'/>?roleFlag="+roleFlag+"&sysId="+resNoticeSysId+"&typeId="+typeId,true);
}
//基地专业管理
function baseSpecialList(){
	var url = "<s:url value='/hbzy/base/orgSpeManage'/>";
	currentJboxLoadNoData("content", url, true);
}

/*理论成绩*/
function doctorTheoryList(){
    var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
    jboxLoad("content","<s:url value='/hbzy/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
}
/*技能成绩*/
function doctorSkillList(){
    var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
    jboxLoad("content","<s:url value='/hbzy/doctorTheoryScore/doctorSkillList'/>?roleFlag="+roleFlag,true);
}
/*参数配置*/
function parameterConfig(){
    jboxLoad("content","<s:url value='/hbzy/sysCfg/parameterConfigMain'/>",true);
}

function selectMenu(menuId){
	$("#"+menuId).find("a").click();
}

$(document).ready(function(){
	init();;
});
//培训基地清单
function searchOrgInfo(){
	currentJboxLoadNoData("content","<s:url value='/jsp/hbzy/global/hospital/specialBaseStatistics.jsp'/>",true);
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
//医师信息统计
function countryBaseStatistics(){
	jboxLoad("content","<s:url value='/hbzy/statistic/statisticCountryOrg'/>?trainTypeId=${jszyTrainCategoryEnumChineseMedicine.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
//协同基地信息统计
function jointBaseStatistics(){
	jboxLoad("content","<s:url value='/hbzy/statistic/statisticJointOrg'/>?trainTypeId=${jszyTrainCategoryEnumChineseMedicine.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}

//学员减免管理
function reductionTab(){
	var url = "<s:url value='/hbzy/reduction/reductionTab?roleId=${GlobalConstant.USER_LIST_GLOBAL}'/>";
	currentJboxLoadNoData("content", url, true);
}

//基地变更管理
function changeBaseTab(){
	var url = "<s:url value='/hbzy/manage/changeBaseTab?roleId=${GlobalConstant.USER_LIST_GLOBAL}'/>";
	currentJboxLoadNoData("content", url, true);
}
//医师专业变更
function speMain(){
	var url = "<s:url value='/hbzy/manage/changeSpeTab?roleId=${GlobalConstant.USER_LIST_GLOBAL}'/>";
	jboxLoad("content", url, true);
}
//退培医师查询
function backTrain(){
	var url = "<s:url value='/hbzy/delayReturn/backTrainTab?roleId=${GlobalConstant.USER_LIST_GLOBAL}'/>";
	currentJboxLoadNoData("content", url, true);
}
//延期医师查询
function delayInfoManage(){
	var url = "<s:url value='/hbzy/delayReturn/delayTab?roleId=${GlobalConstant.USER_LIST_GLOBAL}'/>";
	currentJboxLoadNoData("content", url, true);
}
//师资培训统计
function statisticsTeachTrain(){
	var url = "<s:url value='/hbzy/statistic/searchTeachTrain'/>";
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
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
		$("#"+"${type.id}").attr("checked",true);
	</c:forEach>
//	getCityArea();
//	initOrg(null,null);
//	searchGraduates();
//	getSpeInitData(getDocInitData);
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
function getSpeInitData(fun)
{
	var geturl="<s:url value='/hbzy/manage/orgSpe'/>?"+$("#orgSpe").serialize();
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
	$("#trainTypeId").unbind("change").bind("change",searchInfo);
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
	$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
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
	jboxLoad("orgSpe1","<s:url value='/hbzy/manage/orgSpe'/>?"+$("#orgSpe").serialize(),true);
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
function searchGraduates()
{
	var sessionNumber=$("#schoolSessionNumber").val();
	if(sessionNumber==""){
		jboxTip("年份不能为空！");
		return;
	}
	var workOrgName=$("#workOrgName").val();
	var schoolSessionNumber=$("#schoolSessionNumber").val();
	jboxLoad("schoolNum2","<s:url value='/hbzy/manage/graduateNumSearch'/>?workOrgName="+encodeURIComponent(encodeURIComponent(workOrgName))+"&sessionNumber="+schoolSessionNumber);
}
//黑名单信息查询
function searchBlackInfo(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	var url = "<s:url value='/hbzy/blackList/blackListInfo?currentPage=1'/>"+"&roleFlag="+roleFlag;
	currentJboxLoadNoData("content", url, true);
}
function resMain(){
	window.open("<s:url value='/hbzy/singup/login'/>?userFlow=${sessionScope.currUser.userFlow}");
}
//住院医师招录统计
function zlxytj(){
	var url = "<s:url value='/hbzy/statistic/zlxytj'/>";
	jboxLoad("content", url, true);
}
//招录学员统计
function zlxytj2(){
	var url = "<s:url value='/hbzy/statistic/zlxytj2'/>?trainTypeId=${jszyTrainCategoryEnumChineseMedicine.id}";
	jboxLoad("content", url, true);
}
//招录学员统计-专业
function zlxytj3(){
	var url = "<s:url value='/hbzy/statistic/zlxytj3'/>";
	jboxLoad("content", url, true);
}
//招录学员统计-汇总
function zlxytj4(){
	var url = "<s:url value='/hbzy/statistic/zlxytj4'/>";
	jboxLoad("content", url, true);
}
//招录学员统计（此数据为固定数据）
function zlxytj5(){
    var url = "<s:url value='/hbzy/statistic/zlxytj5'/>";
    jboxLoad("content", url, true);
}
//存档学员查询
function archiveDoctorList(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/hbzy/doctorRecruit/provinceDoctorList'/>?isArchive=Y&roleFlag="+roleFlag,true);
}
function CertificateManage(){
	var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
	jboxLoad("content","<s:url value='/hbzy/certificateManage/main'/>?roleFlag="+roleFlag,true);
}
function graduationManager(pageType,tabType){
	jboxLoad("content","<s:url value='/hbzy/graduationManager/main'/>?roleFlag=${GlobalConstant.USER_LIST_GLOBAL}&pageType="+pageType+"&tabType="+tabType,true);
}
function asseManager(){
	jboxLoad("content","<s:url value='/hbzy/asseGlobal/main'/>?roleFlag=${GlobalConstant.USER_LIST_GLOBAL}",true);
}
function shouye(){
	var url="<s:url value='/hbzy/manage/global'/>";
	window.location.href=url;
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
<div id="indexBody">
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
        <a href="<s:url value='/hbzy/manage/global'/>">首页</a>&#12288;
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
            <i class="icon_menu menu_function"></i>基地信息管理
          </dt>
          <dd class="menu_item" id="hospitalList"><a onclick="searchOrgInfo();">培训基地清单</a></dd>
        </dl>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_management"></i>招录管理
			 </dt>
			 <dd class="menu_item"><a onclick="zlxytj5();">招录学员统计</a></dd>
			 <dd class="menu_item"><a onclick="doctorListZl();">招录学员查询</a></dd>
			 <%--<dd class="menu_item"><a onclick="zlxytj4();">招录统计-汇总</a></dd>--%>
			 <dd class="menu_item"><a onclick="zlxytj();">招录统计-地区</a></dd>
			 <dd class="menu_item"><a onclick="zlxytj2();">招录统计-基地</a></dd>
			 <%--<dd class="menu_item"><a onclick="zlxytj3();">招录统计-专业</a></dd>--%>
		 </dl>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>学员信息管理
          </dt>
          <dd class="menu_item"><a href="javascript:zpxytj();">在培学员统计</a></dd>
          <dd class="menu_item"><a href="javascript:doctorList();">学员信息查询</a></dd>
		  <dd class="menu_item"><a href="javascript:archiveDoctorList();">存档学员查询</a></dd>
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
           <dd class="menu_item"><a onclick="changeBaseTab();">基地变更管理
			   <img  id="changeOrgFlag" style="display: none;" src="<s:url value="/jsp/hbzy/images/gantanhao.png" />"/></a>
		   </dd>
          <dd class="menu_item"><a onclick="speMain();">专业变更管理
           <img  id="speFlag" style="display: none;" src="<s:url value="/jsp/hbzy/images/gantanhao.png" />"/></a>
          </dd>
		  <dd class="menu_item"><a href="javascript:backTrain();">退培学员管理
			  <img  id="backFlag" style="display: none;" src="<s:url value="/jsp/hbzy/images/gantanhao.png" />"/></a>
		  </dd>
          <dd class="menu_item"><a onclick="delayInfoManage();">延期学员管理
			  <img id="delayFlag" style="display: none;" src="<s:url value="/jsp/hbzy/images/gantanhao.png" />"/></a>
		  </dd>
		  <dd class="menu_item"><a onclick="searchBlackInfo();">黑名单管理</a></dd>
        </dl>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_statistics"></i>过程
			 </dt>
			 <dd class="menu_item"><a href="javascript:resMain();">过程管理</a></dd>
		 </dl>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_management"></i>结业管理
			 </dt>
             <dd class="menu_item"><a onclick="doctorTheoryList();">理论成绩管理</a></dd>
             <dd class="menu_item"><a onclick="doctorSkillList();">技能成绩管理</a></dd>
         <%--<dd class="menu_item"><a onclick="CertificateManage();">证书发放</a></dd>--%>
			 <dd class="menu_item"><a onclick="asseManager();">理论考核管理</a></dd>
			 <dd class="menu_item"><a onclick="graduationManager('Import','');">结业学员导入</a></dd>
			 <dd class="menu_item"><a onclick="graduationManager('Audit','GlobalAudit');">结业学员确认</a></dd>
			 <dd class="menu_item"><a onclick="graduationManager('Query','');">证书信息查询</a></dd>
		 </dl>
        <%--<dl class="menu">--%>
          <%--<dt class="menu_title">--%>
            <%--<i class="icon_menu menu_statistics"></i>统计分析--%>
          <%--</dt>--%>
          <%--<dd class="menu_item"><a href="javascript:countryBaseStatistics();">学员信息统计</a></dd>--%>
          <%--<dd class="menu_item"><a href="javascript:jointBaseStatistics();">协同基地信息统计</a></dd>--%>
          <%--<dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>--%>
        <%--</dl>--%>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_setup"></i>设置
          </dt>
           <dd class="menu_item"><a href="javascript:baseSpecialList();">基地专业管理</a></dd>
            <%--<dd class="menu_item"><a href="javascript:parameterConfig();">参数配置</a></dd>--%>
        </dl>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_function"></i>门户资讯管理
          </dt>
           <dd class="menu_item"><a href="javascript:noticeManage('${GlobalConstant.RES_NOTICE_SYS_ID}','LM05');">系统公告</a></dd>
           <dd class="menu_item"><a href="javascript:noticeManage('${GlobalConstant.ZL_NOTICE_SYS_ID}','LM06');">政策法规</a></dd>
           <dd class="menu_item"><a href="javascript:noticeManage('${GlobalConstant.ZL_NOTICE_SYS_ID}','LM07');">专题报道</a></dd>
           <dd class="menu_item"><a href="javascript:noticeManage('${GlobalConstant.ZL_NOTICE_SYS_ID}','LM08');">学术专区</a></dd>
           <dd class="menu_item"><a href="javascript:noticeManage('${GlobalConstant.ZL_NOTICE_SYS_ID}','LM09');">经验分享</a></dd>
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
						  <span style="float: left;margin-left: 205px">
							<i class="countryOrg"></i>
							<em class="number"><label id="countryOrg">${countryOrgCount}个</label></em>
							<strong  class="title">国家基地</strong>
						   </span>
						   <%--<span style="float: left;margin-left: 160px">--%>
							 <%--<i class="jointOrg"></i>--%>
							  <%--<em class="number"><label id="jointOrg">${jointOrgCount}</label></em>--%>
							  <%--<strong  class="title">协同基地</strong>--%>
							<%--</span>--%>
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
								<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">级</c:if>培训学员数</strong>
						  </span>
						 </a>
					 </li>
					 <li class="index_item" style="width:50%; float:left;">
						 <a href="javascript:void(0);">
						<span class="tap_inner tab_second" style="margin-top: -10px;">
							<%--<c:set var="yearpl" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>--%>
							<%--<c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
								<%--<c:set value="0"  var="keyll"></c:set>--%>
								<%--<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">--%>
									<%--<c:set value="${doctorCountExtMap[yearpl][type.id]}"  var="keyll"></c:set>--%>
								<%--</c:if>--%>
								<lable class="title" id="labs"></lable>
							<%--</c:forEach>--%>
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
													<td><span  style="cursor: pointer;" title="本单位人：${doctorCountExtMap[key]["Company"]}；委培单位人：${doctorCountExtMap[key]["CompanyEntrust"]}；社会人：${doctorCountExtMap[key]["Social"]}；">住院医师</span></td>
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
									<div style="float: left;margin-left:100px;height: 10px;width: 10px;background: #fc9a75;"></div><div style="float: left;line-height: 10px;"> ：在培人数</div>
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
			<%--<div class="main_bd" style="height:auto;">--%>
				<%--<ul>--%>
					<%--<li class="score_frame">--%>
						<%--<h1 style="background:#f2e9e4;">学员信息概况</h1>--%>
						<%--<div class="" style="margin-top: 15px;">--%>
							<%--<form id="doctorNumSearch">--%>
								<%--<table cellspacing="8px">--%>
									<%--<tr>--%>
										<%--<td>&#12288;地&#12288;&#12288;区：</td>--%>
										<%--<td>--%>
											<%--<div id="provCityAreaId" style="width:150px;">--%>
												<%--<select id="cityId" name="cityId" style="width:150px;" class="city select" data-value="" data-first-title="选择市" onchange="changeOrg(this);"></select>--%>
											<%--</div>--%>
										<%--</td>--%>
										<%--<td>&#12288;基地类型：</td>--%>
										<%--<td>--%>
											<%--<select name="orgLevel" id="orgLevelId" class="select" style="width:150px;" onchange="changeOrg(this);">--%>
												<%--<option value="">请选择</option>--%>
												<%--<c:forEach items="${orgLevelEnumList}" var="level">--%>
													<%--<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>--%>
															<%--<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>--%>
													<%-->${level.name}</option>--%>
												<%--</c:forEach>--%>
											<%--</select>--%>
										<%--</td>--%>
										<%--<td>&#12288;培训基地：</td>--%>
										<%--<td>--%>
											<%--<div style="width:150px;">--%>
												<%--<input id="trainOrg"  class="input" type="text"  style="margin-left: 0px;width: 150px" autocomplete="off"/>--%>
												<%--<input id="orgFlow" name="orgFlow"  class="input" type="text" hidden  style="margin-left: 0px;width: 150px" />--%>
											<%--</div>--%>
										<%--</td>--%>
										<%--<td>&#12288;培训类别：</td>--%>
										<%--<td>--%>
											<%--<select name="trainTypeId" id="trainTypeId" class="select" style="width:150px;">--%>
												<%--<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">--%>
													<%--<option value="${trainCategory.id}" <c:if test="${jszyTrainCategoryEnumDoctorTrainingSpe.id eq trainCategory.id}">selected</c:if>--%>
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
												<%--<label><input name="datas" type="checkbox" id="${type.id}" name="${type.id}" value="${type.id}"/>${type.name}&nbsp;</label>--%>
											<%--</c:forEach>--%>
										<%--</td>--%>
										<%--<td></td>--%>
										<%--<td></td>--%>
										<%--<td></td>--%>
									<%--</tr>--%>
								<%--</table>--%>
							<%--</form>--%>
						<%--</div>--%>
						<%--<div class="index_table" id="doctorNum1"style="height: 550px;width:100%;margin-top: 50px;">--%>

						<%--</div>--%>
					<%--</li>--%>
				<%--</ul>--%>
			<%--</div>--%>
			<%--<div class="main_bd" style="height:auto;">--%>
				<%--<ul>--%>
					<%--<li class="score_frame">--%>
						<%--<h1 style="background:#f2e9e4;">高校在校专硕信息概况</h1>--%>
						<%--<div class="" style="margin-top: 15px;">--%>
							<%--<form id="graduateNumSearch" method="post">--%>
								<%--<table cellspacing="8px">--%>
									<%--<tr>--%>
										<%--<td>&#12288;年级：</td>--%>
										<%--<td>--%>
											<%--<input type="text" id="schoolSessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchGraduates();}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 150px;margin-left: 0px;" />--%>
										<%--</td>--%>
										<%--<td>&#12288;医学院校：</td>--%>
										<%--<td>--%>
											<%--<select name="workOrgName" id="workOrgName" class="select" style="width:150px;" onchange="searchGraduates();">--%>
												<%--<option value="">请选择</option>--%>
												<%--<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">--%>
													<%--<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>--%>
												<%--</c:forEach>--%>
											<%--</select>--%>
										<%--</td>--%>
									<%--</tr>--%>
								<%--</table>--%>
							<%--</form>--%>
						<%--</div>--%>
						<%--<div class="index_table" id="schoolNum2"style="height: 550px;width:100%;margin-top: 50px;">--%>

						<%--</div>--%>
					<%--</li>--%>
				<%--</ul>--%>
			<%--</div>--%>
			<%--<div class="main_bd" style="height:auto;">--%>
				<%--<ul>--%>
					<%--<li class="score_frame">--%>
						<%--<h1 style="background:#f2e9e4;">专业基地概况</h1>--%>
						<%--<div class="" style="margin-top: 15px;">--%>
							<%--<form id="orgSpe">--%>
								<%--<table cellspacing="8px">--%>
									<%--<tr>--%>
										<%--<td>&#12288;地&#12288;&#12288;区：</td>--%>
										<%--<td>--%>
											<%--<div id="cityAreaId" style="width:150px;">--%>
												<%--<select id="cityId2" name="cityId" style="width:150px;" class="city select" data-value="" data-first-title="选择市"></select>--%>
											<%--</div>--%>
										<%--</td>--%>
										<%--<td>&#12288;基地类型：</td>--%>
										<%--<td>--%>
											<%--<select name="orgLevel" id="orgLevelId2" class="select" style="width:150px;" >--%>
												<%--<option value="">请选择</option>--%>
												<%--<c:forEach items="${orgLevelEnumList}" var="level">--%>
													<%--<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>--%>
															<%--<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>--%>
													<%-->${level.name}</option>--%>
												<%--</c:forEach>--%>
											<%--</select>--%>
										<%--</td>--%>
										<%--<td>&#12288;&#12288;&#12288;&#12288;</td>--%>
										<%--<td>&#12288;&#12288;&#12288;&#12288;</td>--%>
										<%--<td>&#12288;&#12288;&#12288;&#12288;</td>--%>
										<%--<td></td>--%>
									<%--</tr>--%>

								<%--</table>--%>
							<%--</form>--%>
						<%--</div>--%>
						<%--<div class="index_table" id="orgSpe1"style="height: 550px;width:100%;margin-top: 30px;">--%>

						<%--</div>--%>
					<%--</li>--%>
				<%--</ul>--%>
			<%--</div>--%>
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
       主管单位：湖北省中医药局   |  技术支持：南京品德网络信息技术有限公司
   </div>
 </div>

</div>

</body>
</html>
