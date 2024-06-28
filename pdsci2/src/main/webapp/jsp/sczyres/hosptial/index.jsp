<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script>
$(document).ready(function(){
	$(".menu_item a").click(function(){
		$(".select").removeClass("select");
		$(this).addClass("select");
	});
	setBodyHeight();
});

function show(){
	var url = "<s:url value='/sczyres/hosptial/orgInfo'/>";
	jboxLoad("content", url, true);
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

function audit(statusId,key,currentPage){
	if(!currentPage){
		currentPage = 1;
	}
	$("#auditItem").addClass("select");
	var data="statusId="+statusId+"&currentPage="+currentPage+"&key="+key;
	jboxPostLoad("content","<s:url value='/sczyres/hosptial/audit'/>",data,true);
}
function recruitDoctor(){
	jboxLoad("content","<s:url value='/sczyres/hosptial/recruitDoctor'/>",true);
}
function recruitDoctorForJointOrg(){
	jboxLoad("content","<s:url value='/sczyres/hosptial/recruitDoctorForJointOrg'/>",true);
}
function recruitDoctorForSwap(){
	jboxLoad("content","<s:url value='/sczyres/hosptial/recruitDoctorForSwap'/>",true);
}
function swap(){
    jboxLoad("content","<s:url value='/sczyres/manage/swap'/>?isLocal=Y",true);
}
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	window.location.href="<s:url value='/sczyres/hosptial/home'/>?currentPage="+$("#currentPage").val();
} 
/**
 * 招录概况
 */
 function recruitList(assignYear){
	 jboxLoad("content","<s:url value='/sczyres/hosptial/orgRecruitInfo'/>",true);
}
/**
 * 结业概况
 */
function showMsg() {
    var url = "<s:url value='/sczyres/manage/graduationListOrg'/>?role=hospital";
    jboxLoad("content", url, true);
}
//招录概况
function showRecruitMsg(orgFlow) {
    var url = "<s:url value='/sczyres/manage/showDocRecruit'/>?orgFlow="+orgFlow+"&roleFlag=hospital";
    jboxLoad("content", url, true);
}
/**
 * 安全中心
 */
function accounts(){
    jboxLoad("content","<s:url value='/sczyres/hosptial/accounts'/>",true);
}
/**
 * 结业审核
 */
function graduation(){
    var url = "<s:url value='/sczyres/hosptial/graduationList'/>?role=hospital";
    jboxLoad("content", url, true);
}
//医师信息查询
function doctorList(){
    jboxLoad("content","<s:url value='/sczyres/manage/provinceDoctorList'/>?roleFlag=hospital",true);
}
/**
 * 公告
 */
function notice(currentPage){
    if(!currentPage){
        currentPage = 1;
    }
    jboxLoad("content","<s:url value='/sczyres/manage/noticemanage?currentPage='/>"+currentPage,true);
}
//学员减免管理
function reductionTab(){
    var url = "<s:url value='/sczy/reduction/reductionTab?roleId=${GlobalConstant.USER_LIST_LOCAL}'/>";
    jboxLoad("content", url, true);
}
function searchBlackInfo(){
    var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
    var url = "<s:url value='/sczyres/manage/blackListInfo'/>?sessionNumber=${pdfn:getCurrYear()}&currentPage=1&roleFlag="+roleFlag;
    jboxLoad("content", url, true);
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
<jsp:include page="/jsp/sczyres/head.jsp" flush="true"/>
 <div class="body">
   <div class="container">
     <div class="content_side">
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_management"></i>报名管理<input type="hidden" id="subMenuId" value=""/>
          </dt>
          <dd class="menu_item" id="main"><a onclick="audit('${regStatusEnumPassing.id }','','');">报名审核</a></dd>
          <dd class="menu_item"><a onclick="recruitDoctor();">学员录取</a></dd>
          <c:if test='${showJointOrg}'>
              <dd class="menu_item"><a onclick="recruitDoctorForJointOrg();">协同基地</a></dd>
           </c:if>
            <dd class="menu_item"><a onclick="doctorList();">学员信息查询</a></dd>
            <%--<dd class="menu_item"><a onclick="swap();">调剂名单</a></dd>--%>
           <%--<dd class="menu_item" ><a onclick="recruitDoctorForSwap();">调&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;剂</a></dd>--%>
        </dl>
         <dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_management"></i>学员异动管理
             </dt>
             <dd class="menu_item"><a onclick="searchBlackInfo();">黑名单管理</a></dd>
         </dl>
         <dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_management"></i>信息变更管理<input type="hidden" id="subMenuId" value=""/>
             </dt>
             <dd class="menu_item"><a onclick="reductionTab();">学员减免管理</a></dd>
         </dl>
         <dl class="menu">
             <dt class="menu_title" >
                 <i class="icon_menu menu_function"></i>结业管理<input type="hidden" value=""/>
             </dt>
             <dd class="menu_item"><a onclick="graduation()">结业审核</a></dd>
         </dl>
         <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_statistics"></i>统计查询<input type="hidden" id="subMenuId" value=""/>
          </dt>
          <dd class="menu_item" id="main"><a onclick="showRecruitMsg('${sessionScope.currUser.orgFlow}');">招录概况</a></dd>
          <dd class="menu_item"><a onclick="showMsg();">结业概况</a></dd>
        </dl>
        
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_setup"></i>设置
          </dt>
            <dd class="menu_item"><a href="javascript:notice();">公告维护</a></dd>
          	<dd class="menu_item"><a onclick="show()">基地简介</a></dd>
            <dd class="menu_item"><a onclick="accounts();">安全中心</a></dd>
        </dl>
     </div>
     <div class="col_main" id="content">
       <div class="content_main">
         <div class="index_show">
          <div class="index_tap top_left">
            <ul class="inner">
              <li class="index_item index_blue">
                <a href="#" onclick="audit('${regStatusEnumPassing.id}','','')">
                  <span class="tap_inner">
                    <i class="message"></i>
                    <em class="number">${passingCount}</em>
                    <strong  class="title">待审核</strong>
                  </span>
                </a>
              </li>
              <li class="index_item index_blue">
                <a href="#" style="cursor: default;">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number">${singupCount}</em>
                    <strong  class="title">已报名</strong>
                  </span>
                </a>
              </li>
            </ul>
          </div>
          <div class="index_tap top_right">
            <ul class="inner">
              <li class="index_item index_green">
                <a href="#" onclick="audit('${regStatusEnumPassed.id}','','')">
                  <span class="tap_inner tab_third">
                    <i class="crowd"></i>
                    <em class="number">${passedCount}</em>
                    <strong  class="title">审核通过</strong>
                  </span>
                </a>
              </li>
            </ul>
          </div>
        </div>
        
		
		<div class="index_form">
          <h3>系统公告</h3>
          <ul class="form_main">
          	<c:forEach items="${messages}" var="msg">
            <li>
            <a>
            <strong><a href="<s:url value='/inx/sczyres/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle }</a>
            <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.infoTime))<=7}">
            	<i class="new1"></i>
            </c:if>
            </strong>
            <span>${pdfn:transDate(msg.infoTime)}</span>
            </a>
            </li>
            </c:forEach>
          </ul>
        </div>
        <div class="page" style="padding-right: 40px;">
       	 <input id="currentPage" type="hidden" name="currentPage" value=""/>
           <c:set var="pageView" value="${pdfn:getPageView(messages)}" scope="request"></c:set>
	  		 <pd:pagination-sczyres toPage="toPage"/>	 
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
<!-- 
<a href="javascript:test();">test</a>
<script>
    function test(){
    	var user = {"userName":"aaa" , "orgFlow":"123"};
    	$.ajax({
    		url:"<s:url value='/sczyres/hosptial/test'/>",
    		data:JSON.stringify(user),
    		type: "POST",
    		dataType:"json",
    		contentType:"application/json",
    		success:function(resp){
    			alert(resp);
    		}
    	});
    }
</script>
 -->
<jsp:include page="/jsp/sczyres/foot.jsp"  flush="true"/>
</div>
</body>
</html>
