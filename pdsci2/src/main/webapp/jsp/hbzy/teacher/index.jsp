<%@ page import="com.pinde.sci.util.jszy.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script>
function accounts(){
	jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
}
function shouye(page){
	 if (page == null || page == undefined || page == '' || isNaN(page)){
		 page=1;
	 }
	var url="<s:url value='/hbzy/teacher/index'/>?currentPage="+page;
	window.location.href=url;
}
function recruitAuditSearch(form){
	var url="<s:url value='/jsres/teacher/recruitAuditSearch'/>";
	jboxStartLoading();
	jboxPost(url,form,function(resp){
		$("#content").html(resp);
		jboxEndLoading();
	},null,false);
}
function attendanceSearch(form){
    var url="<s:url value='/jsres/teacher/attendanceSearch/teacher'/>";
    jboxStartLoading();
    jboxPost(url,form,function(resp){
        $("#content").html(resp);
        jboxEndLoading();
    },null,false);
}
function vettedAuditSearch(form){
	var url="<s:url value='/jsres/teacher/vettedAuditSearch'/>";
	jboxStartLoading();
	jboxPost(url,form,function(resp){
		$("#content").html(resp);
		jboxEndLoading();
	},null,false);
}

window.onresize = function(){
	setIndexHeight();
};

$(function(){
	setIndexHeight();
});

function setIndexHeight(){
	$("#indexBody").css("height",document.documentElement.clientHeight);
}
function selectMenu(menuId){
    $("#"+menuId).find("a").click();
}
</script>
<style>
body{overflow:hidden;}
</style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
<div class="bd_bg">
<div class="yw" style="height: 90%;">
<div class="head">
   <div class="head_inner">
     <h1 class="logo">
         <img onclick="shouye();" src="<s:url value='/jsp/inx/hbzy/img/hbzy_head.png'/>" style="margin-top: 30px;"/>
     </h1>
     <div class="account">
       <h2 class="head_right">${sessionScope.currUser.orgName }-${sessionScope.currUser.userName }老师</h2>
       <div class="head_right">
<!--        引入切换角色功能 -->
       	<jsp:include page="/jsp/jsres/changeRole.jsp" flush="true"></jsp:include>
        <a onclick="shouye();">首页</a>&#12288;
         <a href="<s:url value='/inx/hbzy/logout'/>">退出</a>
       </div>
     </div>
   </div>
 </div>


 <div class="body" style="height: 90%;">
   <div class="container" >
     <div class="content_side" >
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>医师培训管理
          </dt>
          <dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">培训数据审核</a></dd>
          <dd class="menu_item" id="attendanceSearch"><a onclick="attendanceSearch();">学员考勤审核</a></dd>
           <dd class="menu_item" id="vettedAuditSearch"><a onclick="vettedAuditSearch();">临床技能考核</a></dd>
        </dl>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_setup"></i>设置
          </dt>
           <dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
        </dl>
     </div>
     <div class="col_main" id="content" style="height: 50%;">
       <div class="content_main">
         <div class="index_show">
          <div class="index_tap top_left">
            <ul class="inner">
              <li class="index_item index_blue">
                <a href="javascript:selectMenu('recruitAuditSearch');">
                  <span class="tap_inner">
                    <i class="message"></i>
                    <em class="number">${noAuditNumber}</em>
                    <strong  class="title">培训数据待审核</strong>
                  </span>
                </a>
              </li>
              <li class="index_item index_blue">
                <a href="#">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number">${dShenHe}</em>
                    <strong  class="title">当前轮转学员数</strong>
                  </span>
                </a>
              </li>
            </ul>
          </div>
          <div class="index_tap top_right">
            <ul class="inner">
              <li class="index_item index_green">
                <a href="#">
                  <span class="tap_inner">
                    <i class="crowd"></i>
                    <em class="number">${studentNum}</em>
                    <strong  class="title">总学员数</strong>
                  </span>
                </a>
              </li>
            </ul>
          </div>
        </div>
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
	  	     <pd:pagination-jsres toPage="toPage"/>	 
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
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
 
 

</body>
</html>
 