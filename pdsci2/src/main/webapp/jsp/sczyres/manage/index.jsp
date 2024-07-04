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

$(document).ready(function(){
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

/**
 * 基地录取情况
 */

function showAdmit (){
	var url = "<s:url value='/sczyres/manage/showDocRecruit'/>";
	jboxLoad("content", url, true);
}

function showRecruit(){
	jboxLoad("content" , "<s:url value='/sczyres/manage/showRecruit'/>" , true);
}

function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	window.location.href="<s:url value='/sczyres/manage/home'/>?currentPage="+$("#currentPage").val();
}
//医师信息查询
function doctorList(){
    jboxLoad("content","<s:url value='/sczyres/manage/provinceDoctorList'/>",true);
}
function searchBlackInfo(){
    var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
    var url = "<s:url value='/sczyres/manage/blackListInfo?currentPage=1'/>"+"&roleFlag="+roleFlag;
    jboxLoad("content", url, true);
}
function swap(){
	jboxLoad("content","<s:url value='/sczyres/manage/swap'/>",true);
}

/**
 * 招录设置
 */
function cfg() {
	jboxLoad("content","<s:url value='/sczyres/manage/datecfg'/>",true);
}
/**
 * 招录计划
 */
function planList(assignYear){
	jboxLoad("content","<s:url value='/sczyres/manage/plan'/>?assignYear="+assignYear,true);
}
/**
 * 招录计划编辑
 */
function planListEdit(assignYear){
    jboxLoad("content","<s:url value='/sczyres/manage/planEdit'/>?assignYear="+assignYear,true);
}
/**
 * 结业统计
 */
function graduationStatistics(graduationYear){
    jboxLoad("content","<s:url value='/sczyres/manage/graduationStatistics'/>?graduationYear="+graduationYear,true);
}
/**
 * 基地维护
 */
function org(isY,id){
	jboxLoad("content","<s:url value='/sczyres/manage/hospitalList'/>",true);
}
/**
 * 专业维护
 */
function spe(){
	jboxLoad("content","<s:url value='/sczyres/manage/spe'/>",true);
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
/**
 * 安全中心
 */
function accounts(){
    jboxLoad("content","<s:url value='/sczyres/hosptial/accounts'/>",true);
}
function graduation(){
    var url = "<s:url value='/sczyres/hosptial/graduationList'/>?role=charge";
    jboxLoad("content", url, true);
}
function ticket(){
    var url = "<s:url value='/sczyres/manage/ticket'/>";
    jboxLoad("content", url, true);
}
//学员减免管理
function reductionTab(){
    var url = "<s:url value='/sczy/reduction/reductionTab?roleId=${GlobalConstant.USER_LIST_GLOBAL}'/>";
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
            <i class="icon_menu menu_setup"></i>招录管理
          </dt>
          	 <dd class="menu_item"><a onclick="swap();">学员调剂</a></dd>
          	 <dd class="menu_item"><a onclick="doctorList();">学员信息查询</a></dd>
        </dl>
         <dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_setup"></i>学员异动管理
             </dt>
             <dd class="menu_item"><a onclick="searchBlackInfo();">黑名单管理</a></dd>
         </dl>
         <dl class="menu">
             <dt class="menu_title">
                 <i class="icon_menu menu_setup"></i>信息变更管理
             </dt>
             <dd class="menu_item"><a onclick="reductionTab();">学员减免管理</a></dd>
         </dl>
         <dl class="menu">
             <dt class="menu_title" >
                 <i class="icon_menu menu_function"></i>结业管理<input type="hidden" value=""/>
             </dt>
             <dd class="menu_item"><a onclick="graduation()">结业审核</a></dd>
             <dd class="menu_item"><a onclick="ticket()">准考证管理</a></dd>
         </dl>
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_statistics"></i>统计查询<input type="hidden" id="subMenuId" value=""/>
          </dt>
	           <c:set value="${sysCfgMap['res_reg_year']}" var="year"/>
	           <c:set value="${sysCfgMap['res_graduation_year']}" var="graduationYear"/>
          <dd class="menu_item" id="main"><a onclick="planList('${year}');">招录概况</a></dd>
          <%--<dd class="menu_item"><a onclick="showAdmit();">基地录取人员一览表</a></dd>--%>
          <%--<dd class="menu_item"><a onclick="showRecruit();">基地录取情况一览表</a></dd>--%>
          <dd class="menu_item"><a onclick="graduationStatistics('${graduationYear}');">结业概况</a></dd>
        </dl>
        
        
        
        <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_setup"></i>系统设置
          </dt>
            <dd class="menu_item"><a href="javascript:notice();">公告维护</a></dd>
            <dd class="menu_item"><a href="javascript:org('','');">基地维护</a></dd>
            <dd class="menu_item"><a href="javascript:spe();">专业维护</a></dd>
            <dd class="menu_item"><a href="javascript:planListEdit('${year}');">指标维护</a></dd>
            <dd class="menu_item"><a href="javascript:cfg();">参数设置</a></dd>
            <dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
        </dl>
     </div>
     <div class="col_main" id="content">
       <div class="content_main">
         <div class="index_show">
          <div class="index_tap top_left">
            <ul class="inner">
              <li class="index_item index_blue">
                <a href="#" style="cursor: default;">
                  <span class="tap_inner">
                    <i class="message"></i>
                    <em class="number" style="font-size: 25px;">${singupCount}</em>
                    <strong  class="title">报名人数</strong>
                  </span>
                </a>
              </li>
              <li class="index_item index_blue">
                <a href="#" style="cursor: default;">
                  <span class="tap_inner tab_second">
                    <i class="people"></i>
                    <em class="number" style="font-size: 25px;">${passedCount}</em>
                    <strong  class="title">审核通过</strong>
                  </span>
                </a>
              </li>
            </ul>
          </div>
          <div class="index_tap top_right">
            <ul class="inner">
              <li class="index_item index_green">
                <a href="#" style="cursor: default;">
                  <span class="tap_inner tab_third">
                    <i class="crowd"></i>
                    <em class="number">${recruitCount}</em>
                    <strong  class="title">录取人数</strong>
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
<jsp:include page="/jsp/sczyres/foot.jsp"  flush="true"/>
</div>
</body>
</html>
