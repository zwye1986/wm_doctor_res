<%@ taglib prefix="cP" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt " %>--%>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;
		
		var searchInput = this;
		var spaceId = this[0].id;
		searchInput.on("keyup focus",function(){
			var boxHome = $("#"+spaceId+"Sel");
			boxHome.show();
			 var pDiv=$(boxHome).parent();
			 $(pDiv).css("left",$("#train").outerWidth());
			 var w=$(this).css("marginTop").replace("px","");
			 w=w-0+$(this).outerHeight()+6+"px";
			 $(pDiv).css("top",w);
			if($.trim(this.value)){
				$("p."+spaceId+".item",boxHome).hide();
				var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
				if(!items){
					boxHome.hide();
				}
				changeOrgFlow(this);
			}else{
				$("p."+spaceId+".item",boxHome).show();
			}
		});
		searchInput.on("blur",function(){
			var boxHome = $("#"+ spaceId+"Sel");
			if(!$("."+spaceId+".boxHome.on").length){
				boxHome.hide();
			}
		});
		$("."+spaceId+".boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		
		$("."+spaceId+".boxHome .item").click(function(){
			var boxHome = $("."+spaceId+".boxHome");
			boxHome.hide();
			var value = $(this).attr("value");
			var input = $("#"+spaceId);
			input.val(value);
			if(option.clickActive){
				option.clickActive($(this).attr("flow"));
				$("#orgFlow").val($(this).attr("flow"));
			}
		});
	};
})(jQuery);
function changeOrgFlow(obj){
	var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
	var flow=$(items).attr("flow") || '';
	$("#orgFlow").val(flow);
 }
$(function(){
	if($("#trainOrg").length){
		$("#trainOrg").likeSearchInit({
			 clickActive:function(flow){
				 $("."+flow).show();
			}
		});
	}
});
function toPage(page) {
	var currentPage="";
	if(!page||page!=undefined){
		currentPage=page;
	}
	if(page==undefined||page==""){
		currentPage=1;
	}
	$("#currentPage").val(currentPage);
	search();
}
function changeStatus(){
	 if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		 $("#orgFlow").val("");
	 }
}
//黑名单信息查询
function search(){
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	if($('#jointOrg').get(0) != undefined){
		$('#jointOrg').get(0).checked==true?$('#jointOrg').val("checked"):$('#jointOrg').val("");
	}
	var url="<s:url value='/hbzy/blackList/blackListInfo'/>";
	jboxPostLoad("content",url,$("#searchForm").serialize(),true);
}
//黑名单移除
function removeBlack(userFlow,recordStatus,recordflow){
	jboxConfirm("确认移除该记录吗？",function () {
		var url = "<s:url value='/hbzy/blackList/removeBlack?userFlow='/>" + userFlow + "&recordStatus=" + recordStatus +"&recordFlow=" + recordflow;
		jboxGet(url,null,function(){
			search();
		});
	});
}
//展示原因详情
function showDetail(resp1,resp2){
//	jboxConfirm(resp1);
	jboxOpenContent(resp1, "退培原因", 300, 100, true);
}
//添加黑名单
function addBlackList(){
	jboxOpen("<s:url value='/hbzy/blackList/addBlackList'/>","黑名单添加", 500, 400);
}

function change(){
	$("#trainOrg").val("");
}
</script>
<body>
<c:if test="${GlobalConstant.USER_LIST_GLOBAL ne sessionScope.userListScope}">
	<div class="main_hd">
		<h2 class="underline">黑名单查询</h2>
	</div>
</c:if>
<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
	<div class="main_hd">
		<h2 class="underline">黑名单管理</h2>
	</div>
</c:if>
<div class="main_bd">
	<div class="div_search">
		<form id="searchForm">
			<input type="hidden" name="currentPage" id="currentPage" value=""/>
			<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope || GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope }">
				<table class="searchTable">
					<tr>
						<td class="td_left"><label>培训基地：</label></td>
						<td>
							<input id="trainOrg" oncontextmenu="return false" value="${param.name}" name="name"
								   class="toggleView input" type="text" autocomplete="off"
								   style="margin-left: 0px;width: 100px" onkeydown="changeStatus();"
								   onkeyup="changeStatus();" onchange="change();"/>
							<div id="pDiv"
								 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
								<div class="boxHome trainOrg" id="trainOrgSel"
									 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
									<c:forEach items="${orgs}" var="org">
										<p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
										   value="${org.orgName}"
										   <c:if test="${empty org.orgLevelId}">type="allOrg" </c:if>
											   <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
										   style="line-height: 20px; padding:5px 0;cursor: default;"
										   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
										>${org.orgName}</p>
									</c:forEach>
								</div>
								<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}"
									   style="display: none;"/>
							</div>
						</td>
						<td class="td_left">姓&#12288;&#12288;名：</td>
						<td>
							<input type="text" value="${param.userName}" class="input" name="userName"
								   style="width: 100px;"/>
						</td>
						<td class="td_left">年&#12288;&#12288;级：</td>
						<td colspan="3">
							<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
								   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
						</td>
					</tr>
					<tr>
						<td class="td_left">培训专业：</td>
						<td>
							<input type="text" value="${param.trainingSpeName}" class="input" name="trainingSpeName"
								   style="width: 100px;"/>
						</td>
						<td colspan="6">
							<input class="btn_brown" type="button" onclick="search()" value="查&#12288;询"/>&#12288;
							<input class="btn_brown" type="button" onclick="addBlackList()" value="添&#12288;加"/>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope}">
				<table class="searchTable">
					<tr>
						<td class="td_left">姓&#12288;&#12288;名：</td>
						<td>
							<input type="text" value="${param.userName}" class="input" name="userName"
								   style="width: 100px;"/>
						</td>
						<td class="td_left">年&#12288;&#12288;级：</td>
						<td>
							<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
								   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
						</td>
						<td class="td_left">培训专业：</td>
						<td>
							<input type="text" value="${param.trainingSpeName}" class="input" name="trainingSpeName"
								   style="width: 100px;"/>
						</td>
						<td colspan="2">
							<c:if test="${countryOrgFlag eq 'Y'}">
								<input type="checkbox" id="jointOrg" name="jointOrg" <c:if
									test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label for="jointOrg">&nbsp;协同基地</label>&#12288;
							</c:if>
							<input class="btn_brown" type="button" onclick="search()" value="查&#12288;询"/>
						</td>
					</tr>
				</table>
			</c:if>
		</form>
	</div>
	<div style="padding-bottom: 20px;">
		<div class="search_table">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<tr>
					<th>姓名</th>
					<th>证件号</th>
					<th>原培训基地</th>
					<th>原培训专业</th>
					<th>原年级</th>
					<th>创建时间</th>
					<th>原因</th>
					<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
						<th>操作</th>
					</c:if>
				</tr>
				<c:forEach items="${blackLists}" var="black">
					<tr>
						<td>${black.userName}</td>
						<td>${black.idNo}</td>
							<%--<td>${black.orgName}</td>--%>
						<td class="titleName" title="${black.orgName}">${pdfn:cutString(black.orgName,10,true,3) }</td>
							<%--<td>${black.trainingSpeName}</td>--%>
						<td class="titleName"
							title="${black.trainingSpeName}">${pdfn:cutString(black.trainingSpeName,6,true,3) }</td>
						<td>${black.sessionNumber}</td>
						<td>${pdfn: transDateTime(black.createTime)}</td>
							<%--<td><fmt:formatDate value="${black.createTime}"  type="date" dateStyle="default"/></td>--%>
							<%--<td>${black.createTime}</td>--%>
							<%--<td class="titleName" title="${black.reason}">${pdfn:cutString(black.reason,1,true,3) }</td>--%>
						<td>
							<a class="btn" onclick="showDetail('${black.reason}','${black.reasonYj}');">详情</a>
						</td>
						<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
							<td>
								<a class="btn"
								   onclick="removeBlack('${black.userFlow}','${GlobalConstant.RECORD_STATUS_N}','${black.recordFlow}');">移除</a>
							<td>
						</c:if>
					</tr>
				</c:forEach>
				<c:if test="${empty blackLists}">
					<tr>
						<td colspan="8">无记录</td>
					</tr>
				</c:if>
			</table>
		</div>
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(blackLists)}" scope="request"></c:set>
			<pd:pagination-jszy toPage="toPage"/>
		</div>
	</div>
</div>
</body>
