<%@ page import="com.pinde.sci.util.jszy.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script>
function accounts(){
	jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
}
function shouye(page){
	 if (page == null || page == undefined || page == '' || isNaN(page)){
		 page=1;
	 }
	var url="<s:url value='/jszy/kzr/index'/>?currentPage="+page;
	window.location.href=url;
}
function recruitAuditSearch(form){
	var url="<s:url value='/jsres/kzr/recruitAuditSearch'/>";
	jboxStartLoading();
	jboxPost(url,form,function(resp){
		$("#content").html(resp);
		jboxEndLoading();
	},null,false);
}
function evaluateSearch(form){
	var url="<s:url value='/jsres/kzr/evaluateSearch'/>";
	jboxStartLoading();
	jboxPost(url,form,function(resp){
		jboxEndLoading();
		$("#content").html(resp);
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
         <img onclick="shouye();" src="<s:url value='/css/skin/Yellow/images/jszy_head2.png'/>" style="margin-top: 30px;"/>
     </h1>
     <div class="account">
       <h2 class="head_right">${sessionScope.currUser.orgName}-${sessionScope.currUser.userName}</h2>
       <div class="head_right">
       <!--        引入切换角色功能 -->
       	<jsp:include page="/jsp/jsres/changeRole.jsp" flush="true"></jsp:include>
        <a onclick="shouye();">首页</a>&#12288;
         <a href="<s:url value='/inx/jszy/logout'/>">退出</a>
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
          <dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">培训数据查询</a></dd>
          <dd class="menu_item" id="evaluateSearch"><a onclick="evaluateSearch();">评价查询</a></dd>
        </dl>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_setup"></i>设置
          </dt>
           <dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
        </dl>
     </div>
     <div class="col_main" id="content">
       <div class="content_main">
         <div class="index_show">
          <div class="index_tap top_left">
            <ul class="inner">
              <li class="index_item index_blue">
                <a href="javascript:selectMenu('recruitAuditSearch');">
                  <span class="tap_inner">
                    <i class="message"></i>
                    <em class="number">${ComingStudentNum}</em>
                    <strong  class="title">即将出科人数</strong>
                  </span>
                </a>
              </li>
              <li class="index_item index_blue">
                <a href="#">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number">${currStudentHe}</em>
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
			</div>
		</div>
		</div>

       </div>
     </div>
   </div>
   <div class="foot">
   <div class="foot_inner">
       主管单位：江苏省中医药局   |  技术支持：南京品德网络信息技术有限公司
   </div>
 </div>
 </div>
  <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
 
 

</body>
</html>
 