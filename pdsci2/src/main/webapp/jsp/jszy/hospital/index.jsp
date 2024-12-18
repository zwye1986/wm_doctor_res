<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
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
	var url = "<s:url value='/jszy/manage/getNeedReducation'/>";
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
	window.open("<s:url value='/jszy/singup/login'/>?userFlow=${sessionScope.currUser.userFlow}");
}
onresize=function(){
	setBodyHeight();
}
//医师信息查询
function doctorList(){
	var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
	jboxLoad("content","<s:url value='/jszy/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
}
//医师信息审核
function recruitAuditSearch(currentPage){
	if(currentPage == undefined){
		currentPage =1;
	}
	var url = "<s:url value='/jszy/manage/province/doctor/recruitAuditSearch'/>?source=hospital";
	jboxLoad("content", url, true);
}
function selectMenu(menuId){
	$("#"+menuId).find("a").click();
}
//基地变更管理
function changeBaseTab(){
	var url = "<s:url value='/jszy/manage/changeBaseTab?roleId=${GlobalConstant.USER_LIST_LOCAL}'/>";
	currentJboxLoadNoData("content", url, true);
}
function shouye(page){
	 if (page == null || page == undefined || page == '' || isNaN(page)){
		 page=1;
	 }
	var url="<s:url value='/jszy/manage/local'/>?currentPage="+page;
	window.location.href=url;
}
//方案减免维护
function reductionRotationOper(){
	data = $("#searchFormReduction").serialize() || {
		degreeType : "${GlobalConstant.FLAG_Y}",
		currentPage : 1,
	};
	var url = "<s:url value='/jszy/manage/reductionRotationOper'/>";
	jboxStartLoading();
	jboxPost(url,data,function(resp){
		jboxEndLoading();
		$("#content").html(resp);
	},null,false);
	var orgFlow=$("#orgFlow").val();
	getNeedReducation(orgFlow,function(result){
		if(result==0){
			$("#reducationCount").hide();
		}
	});
}

function toPage(page){
	if(!page){
		$("#currentPage").val(page);			
	}
	shouye(page);
}
//学员减免管理
function reductionTab(){
	var url = "<s:url value='/jszy/reduction/reductionTab?roleId=${GlobalConstant.USER_LIST_LOCAL}'/>";
	currentJboxLoadNoData("content", url, true);
}
//学员专业变更
function speMain(){
	var url = "<s:url value='/jszy/manage/changeSpeTab?roleId=${GlobalConstant.USER_LIST_LOCAL}'/>";
	jboxLoad("content", url, true);
}
//学员退培管理
function backTrain(){
	var url = "<s:url value='/jszy/delayReturn/backTrainQuery4Local'/>";
	currentJboxLoadNoData("content", url, true);
}
//师资培训统计
function statisticsTeachTrain(){
	var url = "<s:url value='/jszy/statistic/searchTeachTrain'/>";
	currentJboxLoadNoData("content", url, true);
}
//延期学员查询
function delay(){
	var url = "<s:url value='/jszy/delayReturn/delayQuery4Local'/>";
	currentJboxLoadNoData("content", url, true);
}
//黑名单信息查询
function searchBlackInfo(){
	var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
	var url = "<s:url value='/jszy/blackList/blackListInfo'/>?sessionNumber=${pdfn:getCurrYear()}&currentPage=1&roleFlag="+roleFlag;
	jboxLoad("content", url, true);
}

function graduationManager(pageType,tabType){
	jboxLoad("content","<s:url value='/jszy/graduationManager/main'/>?roleFlag=${GlobalConstant.USER_LIST_LOCAL}&pageType="+pageType+"&tabType="+tabType,true);
}
function asseManager(){
	jboxLoad("content","<s:url value='/jszy/asseGlobal/main'/>?roleFlag=${GlobalConstant.USER_LIST_LOCAL}",true);
}
/*理论成绩*/
function doctorTheoryList(){
    var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
    jboxLoad("content","<s:url value='/jszy/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
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
		 <img onclick="shouye();" src="<s:url value='/css/skin/Yellow/images/jszy_head2.png'/>" style="margin-top: 30px;"/>
     </h1>
     <div class="account">
       <h2 class="head_right">${sessionScope.currUser.orgName }</h2>
       <div class="head_right">
        <a onclick="shouye();">首页</a>&#12288;
         <a href="<s:url value='/inx/jszy/logout'/>">退出</a>
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
          <dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">医师信息审核</a></dd>
		  <dd class="menu_item" id="doctorList"><a onclick="doctorList();">医师信息查询</a></dd>
		  <dd class="menu_item" id="reductionRotationOper"><a onclick="reductionRotationOper();">方案减免维护
		  	<img  id="reducationCount" style="display: none;" src="<s:url value="/jsp/jszy/images/gantanhao.png" />"/></a>
		  </dd>
          <%--<dd class="menu_item"><a onclick="changeBaseMain();">基地变更审核--%>
          	 <%--<img  id="baseFlag" style="display: none;" src="<s:url value="/jsp/jszy/images/gantanhao.png" />"/></a>--%>
		  <%--</dd>--%>
          <%--<dd class="menu_item"><a onclick="speMain();">医师专业变更--%>
          	 <%--<img  id="speFlag" style="display: none;" src="<s:url value="/jsp/jszy/images/gantanhao.png" />"/>--%>
          <%--</a></dd>--%>
          <%--<dd class="menu_item"><a onclick="delay();">延期医师查询</a></dd>--%>
          <%--<dd class="menu_item"><a href="javascript:backTrain();">医师退培管理</a></dd>--%>
        </dl>
		 <dl class="menu">
			 <dt class="menu_title">
				 <i class="icon_menu menu_management"></i>培训变更管理
			 </dt>
			 <dd class="menu_item">
				 <a onclick="reductionTab();">学员减免管理
					 <img id="reductionFlag" style="display: none;"
						  src="<s:url value="/jsp/jszy/images/gantanhao.png" />"/>
				 </a>
			 </dd>
			 <dd class="menu_item">
				 <a onclick="changeBaseTab();">基地变更管理
					 <img id="baseFlag" style="display: none;"
						  src="<s:url value="/jsp/jszy/images/gantanhao.png" />"/>
				 </a>
			 </dd>
			 <dd class="menu_item">
				 <a onclick="speMain();">专业变更管理
					 <img id="speFlag" style="display: none;" src="<s:url value="/jsp/jszy/images/gantanhao.png" />"/>
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
             <dd class="menu_item"><a onclick="asseManager();">结业考核管理</a></dd>
			 <dd class="menu_item"><a onclick="graduationManager('Audit','LocalAudit');">结业学员确认</a></dd>
			 <dd class="menu_item"><a onclick="graduationManager('Query','');">证书信息查询</a></dd>
		 </dl>
		 <%--<dl class="menu">--%>
			 <%--<dt class="menu_title">--%>
				 <%--<i class="icon_menu menu_statistics"></i>过程--%>
			 <%--</dt>--%>
				 <%--<dd class="menu_item"><a href="javascript:resMain();">过程管理</a></dd>--%>
		 <%--</dl>--%>
	    <dl class="menu">
          <dt class="menu_title">
            <i class="icon_menu menu_statistics"></i>统计分析
          </dt>
          <dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>
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
                    <em class="number">${waitCount}</em>
                    <strong  class="title">培训信息待审核</strong>
                  </span>
                </a>
              </li>
              <li class="index_item index_blue">
                <a href="#">
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
                    <strong  class="title">待结业资格审核</strong>
                  </span>
                </a>
              </li>
            </ul>
          </div>
        </div>
        
        <c:set value="jswjw_${currUser.orgFlow}_P001" var="orgFlow"/>
        <c:if test="${sysCfgMap[orgFlow] eq GlobalConstant.RECORD_STATUS_Y}">
	        <div class="index_form" style="margin-bottom: 10px;">
	        	<script>
	        		var okList;
	        		var currFlag = false;
	        		var viewLength = 5;
	        		var likeSearchColumn = 3;
	        		
	        		$(function(){
	        			okList = $('#willOutTable .willOutData');
	        			toOperData();
	        		});
	        		
	        		function toOperData(){
	        			var docCount = okList.length || 0;
	        			if(docCount>viewLength){
        					okList.filter(':gt('+(viewLength-1)+')').toggle(currFlag);
	        				$('#s').toggle(!currFlag);
	        				$('#h').toggle(currFlag);
	        				$('#docCount').text(docCount-viewLength);
	        			}else{
	        				$('#s,#h').hide();
	        			}
	        			$('#noData').toggle(docCount==0);
	        		}
	        		
		        	function more(flag){
		        		currFlag = flag;
		        		okList.filter(':gt('+(viewLength-1)+')').toggle(currFlag);
		        		$('#s,#h').toggle();
		        	}
		        	
		        	function searchMethod(theAttr){
		        		okList = $('#willOutTable .willOutData');
		        		theAttr = $.trim(theAttr);
		        		if(theAttr!=''){
		        			okList.hide();
		        			okList = okList.filter(':has(td:lt('+likeSearchColumn+'):contains("'+theAttr+'"))').show();
			        		toOperData();
		        		}else{
		        			okList.show();
		        			toOperData();
		        		}
		        	}
	        	</script>
	          <table id="willOutTable" border="0" cellpadding="0" cellspacing="0" class="grid">
	         	<colgroup>
					<col width="20%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="15%"/>
					<col width="20%"/>
					<col width="15%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th colspan="7" style="text-align: left;padding-left: 10px;">
						当月即将出科学员查询
						<div style="float: right;">
							搜索：
							<input type="text" class="input" onkeyup="searchMethod(this.value);" placeholder="轮转科室/姓名/届别"/>
						</div>
					</th>
				</tr>
	          	<tr>
	          		<th>轮转科室</th>
	          		<th>姓名</th>
	          		<th>届别</th>
	          		<th>培训专业</th>
	          		<th>轮转时间</th>
	          		<th>带教老师</th>
	          		<th>出科状态</th>
	          	</tr>
	          	<c:forEach items="${trainingList}" var="training">
	          		<tr class="willOutData">
						<c:set var="status" value="${training.doctorFlow}${training.schDeptFlow}"></c:set>
						<td>${training.schDeptName}</td>
						<td>${training.doctorName}</td>
						<td>${training.sessionNumber}</td>
						<td>${training.trainingSpeName}</td>
						<td>${training.schStartDate} 至 ${training.schEndDate}</td>
						<td>${training.teacherUserName}</td>
						<td>${stateMap[status]}</td>
	          		</tr>
	          	</c:forEach>
	         	
	   		  <tr>
		   		 <td id="noData" colspan="7" style="text-align: center;<c:if test="${!empty trainingList}">display: none;</c:if>">无记录!</td>
		 	 </tr>
		      
		       <c:if test="${not empty trainingList}">
		      	 <tr id="s" style="display: none;">
			   		 <td  colspan="7"> 
			   		 	<a onclick="more(true);">还有<font id="docCount"></font>个学员，点击查看</a>
			   		 </td>
			 	 </tr>
	        	<tr id="h" style="display: none;">
			   		 <td  colspan="7"> <a onclick="more(false);">收起</a></td>
			 	 </tr>
	       	   </c:if>
	          </table>
	        </div>
        </c:if>
        
        <div class="index_form" style="margin-bottom: 10px;">
          <h3>通知公告</h3>
          	<ul class="form_main">
          		<c:forEach items="${infos}" var="msg">
           		 <li>
           		 <strong><a href="<s:url value='/inx/jszy/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
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
       主管单位：江苏省中医药局   |  技术支持：南京品德网络信息技术有限公司
   </div>
 </div>
 </div>
  <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
 
 

</body>
</html>
 