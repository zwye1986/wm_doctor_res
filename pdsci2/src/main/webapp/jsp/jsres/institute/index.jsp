<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
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
function selectMenu(menuId){
	$("#"+menuId).find("a").click();
}
$(document).ready(function(){
	showHosCanvas();
});
function showHosCanvas(){
	var myChart = echarts.init(document.getElementById('hosChart')); 
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

//医师信息查询
function doctorList(){
	jboxLoad("content","<s:url value='/jsres/institute/provinceDoctorList'/>?roleFlag=institute",true);
}

//医师退赔查询
function backTrain(){
	var url = "<s:url value='/jsres/institute/backTrainInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
	currentJboxLoadNoData("content", url, true);
}

//出科评价查看
function gradeSearch(){
	var form = $("#gradeSearchForm").serialize() || {"gradeRole":"teacher"};
	var url = "<s:url value='/jsres/institute/gradeSearch'/>";
	jboxStartLoading();
	jboxPost(url,form,function(resp){
		jboxEndLoading();
		$("#content").html(resp);
	},null,false);
}

//基地变更查询
function changeBaseMain(){
	var url = "<s:url value='/jsres/institute/changeBase'/>";
	currentJboxLoadNoData("content", url, true);
}

//专业变更查询
function searchChangeSpe(){
	var url = "<s:url value='/jsres/institute/searchChangeSpe'/>?viewFlag=Y";
	jboxLoad("content", url, true);
}

//延期医师查询
function delay(){
	var url = "<s:url value='/jsres/institute/delay'/>";
	currentJboxLoadNoData("content", url, true);
}

//医师信息统计
function countryBaseStatistics(){
	jboxLoad("content","<s:url value='/jsres/institute/statisticCountryOrg'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}

//协同基地信息统计
function jointBaseStatistics(){
	jboxLoad("content","<s:url value='/jsres/institute/statisticJointOrg'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}

//APP使用情况统计
function statisticsAppUser(){
	jboxLoad("content","<s:url value='/jsres/institute/statisticsAppUserForOrg'/>",true);
}

//师资培训统计
function statisticsTeachTrain(){
	var url = "<s:url value='/jsres/institute/searchTeachTrain'/>";
	currentJboxLoadNoData("content", url, true);
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
       <a href="<s:url value='/jsres/manage/institute'/>"><%=JsresUtil.getTitle(request,response,application)%></a>
     </h1>
     <div class="account">
       <h2 class="head_right">${sessionScope.currUser.userName }</h2>
       <div class="head_right">
        <a href="<s:url value='/jsres/manage/institute'/>">首页</a>&#12288;
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
            <i class="icon_menu menu_management"></i>医师信息管理
          </dt>
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
			 <dd class="menu_item"><a onclick="searchChangeSpe();">专业变更查询</a></dd>
			 <dd class="menu_item"><a href="javascript:backTrain();">退培医师查询</a></dd>
			 <dd class="menu_item"><a onclick="delay();">延期医师查询</a></dd>
		 </dl>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_statistics"></i>统计分析
			 </dt>
			 <dd class="menu_item"><a href="javascript:countryBaseStatistics();">医师信息统计</a></dd>
			 <dd class="menu_item"><a href="javascript:jointBaseStatistics();">协同基地信息统计</a></dd>
			 <dd class="menu_item"><a href="javascript:statisticsAppUser();">APP使用情况统计</a></dd>
			 <dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>
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
                <a href="javascript:selectMenu('auditGraduate');">
                  <span class="tap_inner">
                    <i class="crowd"></i>
                    <em class="number">0</em>
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
