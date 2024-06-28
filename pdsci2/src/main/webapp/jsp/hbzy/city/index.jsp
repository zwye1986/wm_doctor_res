<%@ page import="com.pinde.sci.util.jszy.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
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
	var url="<s:url value='/hbzy/manage/charge'/>?currentPage="+page;
	window.location.href=url;
}
//医师信息查询
function doctorList(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	jboxLoad("content","<s:url value='/hbzy/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}
//延期医师查询
function delay(){
	var url = "<s:url value='/hbzy/doctor/delay'/>";
	currentJboxLoadNoData("content", url, true);
}
//培训基地清单
function searchOrgInfo(){
	currentJboxLoadNoData("content","<s:url value='/jsp/hbzy/global/hospital/specialBaseStatistics.jsp'/>",true);
}
//医师信息统计
function countryBaseStatistics(){
	jboxLoad("content","<s:url value='/hbzy/statistic/statisticCountryOrg'/>?trainTypeId=${jszyTrainCategoryEnumChineseMedicine.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
//协同基地信息统计
function jointBaseStatistics(){
	jboxLoad("content","<s:url value='/hbzy/statistic/statisticJointOrg'/>?trainTypeId=${jszyTrainCategoryEnumChineseMedicine.id}&sessionNumber=${pdfn:getCurrYear()}",true);
}
//基地专业管理
function baseSpecialList(){
	var url = "<s:url value='/hbzy/base/orgSpeManage'/>";
	currentJboxLoadNoData("content", url, true);
}
//基地变更查询
function changeBaseMain(){
	var url = "<s:url value='/hbzy/manage/changeBase'/>";
	currentJboxLoadNoData("content", url, true);
}
//退培医师查询
function backTrain(){
	var url = "<s:url value='/hbzy/doctor/backTrainInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
	currentJboxLoadNoData("content", url, true);
}
//医师专业变更
function speMain(){
	var url = "<s:url value='/hbzy/manage/speMain'/>";
	jboxLoad("content", url, true);
}
//师资培训统计
function statisticsTeachTrain(){
	var url = "<s:url value='/hbzy/statistic/searchTeachTrain'/>";
	currentJboxLoadNoData("content", url, true);
}
//黑名单管理
function searchBlackInfo(){
	var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
	var url = "<s:url value='/hbzy/blackList/blackListInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>"+"&roleFlag="+roleFlag;
	currentJboxLoadNoData("content", url, true);
}
/*理论成绩*/
function doctorTheoryList(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/hbzy/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
}
/*技能成绩*/
function doctorSkillList(){
    var roleFlag="${GlobalConstant.USER_LIST_CHARGE}";
    jboxLoad("content","<s:url value='/hbzy/doctorTheoryScore/doctorSkillList'/>?roleFlag="+roleFlag,true);
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
		 <img onclick="shouye();" src="<s:url value='/jsp/inx/hbzy/img/hbzy_head.png'/>" style="margin-top: 30px;"/>
     </h1>
     <div class="account">
       <h2>${sessionScope.currUser.orgName}</h2>
       <div class="head_right">
        <a href="<s:url value='/hbzy/manage/charge'/>">首页</a>&#12288;
         <a href="<s:url value='/inx/hbzy/logout'/>">退出</a>
       </div>
     </div>
   </div>
 </div>
 <div class="body">
   <div class="container">
     <div class="content_side">
        <%--<dl class="menu menu_first">
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
</div>
  <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
 <div class="foot">
   <div class="foot_inner">
      主管单位：湖北省卫生和计划生育委员会科教处   |  技术支持：南京品德网络信息技术有限公司
   </div>
 </div>
 
</div>

</body>
</html>
