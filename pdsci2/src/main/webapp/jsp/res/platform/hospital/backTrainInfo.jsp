<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
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
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
	.doctorTypeDiv {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeLabel {
		border: 0px;
		float: left;
		width: 96px;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeContent {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
</style>
<script type="text/javascript">
/**
 *模糊查询加载
 */
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;

		var searchInput = this;
		searchInput.on("keyup focus",function(){
			$("#boxHome").show();
			if($.trim(this.value)){
				$("#boxHome .item").hide();
				var items = $("#boxHome .item[value*='"+this.value+"']").show();
				if(!items){
					$("#boxHome").hide();
				}
			}else{
				$("#boxHome .item").show();
			}
		});
		searchInput.on("blur",function(){
			if(!$("#boxHome.on").length){
				$("#boxHome").hide();
			}
		});
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);
//======================================
//页面加载完成时调用
$(function(){
	$("#orgSel").likeSearchInit({
		clickActive:function(flow){
			$("."+flow).show();
		}
	});
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
	searchRecInfo();
}
function searchRecInfo(){
	jboxStartLoading();
	$("#searchForm").submit();
}
function exportDoc(){
	if(${empty resRecList}){
		jboxTip("当前无记录!");
		return;
	}
	var url = "<s:url value='/res/doc/exportBack?roleFlag=local'/>";
	jboxTip("导出中…………");
	jboxExp($("#searchForm"),url);
}
</script>
</head>
<body>
<%--<c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y}">--%>
	<%--<div class="main_hd">--%>
	    <%--<h2 class="underline">医师退培管理</h2> --%>
	<%--</div>--%>
<%--</c:if>--%>
<div class="mainright" style="min-height: 300px;">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/doc/backTrainInfo?roleFlag=local" />" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" value="${param.doctorName}" name="doctorName" class="qtext"/>
						</div>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select name="sessionNumber" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv" style="min-width: 275px;max-width: 275px;">
							<label class="qlable">退培主要原因：</label>
							<select class="qselect" id="reasonId" name="reasonId">
								<option value="">请选择</option>
								<option value="1"<c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>辞职</option>
								<option value="2"<c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>考研</option>
								<option value="3"<c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>其他</option>
							</select>
						</div>
						<div class="inputDiv" style="text-align: left;">
							<label style="cursor: pointer;" id='jointOrg'>&#12288;&#12288;&nbsp;&nbsp;<input type="checkbox" id="jointOrgFlag"  <c:if test="${param.jointOrg eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrg" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
						</div>
						<div class="inputDiv">
							<label class="qlable">退培类型：</label>
							<select  class="qselect" id="policyId" name="policyId">
								<option value="">请选择</option>
								<option value="1"<c:if test="${param.policyId eq 1}">selected="selected"</c:if>>协议退培</option>
								<option value="2"<c:if test="${param.policyId eq 2}">selected="selected"</c:if>>违约退培</option>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">培训专业：</label>
							<input type="text"  value="${param.trainingSpeName}" name="trainingSpeName" class="qtext"/>
						</div>
						<div class="doctorTypeDiv">
							<div class="doctorTypeLabel">学员类型：</div>
							<div class="doctorTypeContent">
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
									<c:set var="docType" value="${type.dictId},"/>
									<label><input type="checkbox" name="datas" value="${type.dictId}" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""}>${type.dictName}</label>
								</c:forEach>
							</div>
						</div>
						<div class="lastDiv" style="min-width: 183px;max-width: 183px;">
							<input class="searchInput" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>
							<input type="button" class="searchInput" value="导&#12288;出" onclick="exportDoc();" title="导出国家医师协会名册"/>
						</div>
					</div>
				</c:if>
				<c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
					<div class="queryDiv" style="min-width: 800px;max-width: 800px;">
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" value="${param.doctorName}" name="doctorName" class="qtext"/>
						</div>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select name="sessionNumber" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv" style="min-width: 275px;max-width: 275px;">
							<label class="qlable">退培主要原因：</label>
							<select class="qselect" id="reasonId" name="reasonId">
								<option value="">请选择</option>
								<option value="1"<c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>辞职</option>
								<option value="2"<c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>考研</option>
								<option value="3"<c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>其他</option>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">培训专业：</label>
							<input type="text"  value="${param.trainingSpeName}" name="trainingSpeName" class="qtext"/>
						</div>
						<div class="inputDiv">
							<label class="qlable">退培类型：</label>
							<select  class="qselect" id="policyId" name="policyId">
								<option value="">请选择</option>
								<option value="1"<c:if test="${param.policyId eq 1}">selected="selected"</c:if>>协议退培</option>
								<option value="2"<c:if test="${param.policyId eq 2}">selected="selected"</c:if>>违约退培</option>
							</select>
						</div>
						<div class="lastDiv" style="min-width: 185px;max-width: 185px;">
							<input class="searchInput" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>
							<input type="button" class="searchInput" value="导&#12288;出" onclick="exportDoc();" title="导出国家医师协会名册"/>
						</div>
					</div>
				</c:if>
		 </form>
	 	</div>
        <table class="xllist">
			<colgroup>
				<col width="6%"/>
				<col width="5%"/>
				<col width="13%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="11%"/>
				<col width="7%"/>
				<c:if test="${'N' ne sysCfgMap['res_return_audit']}">
				<col width="8%"/>
				<col width="10%"/>
				</c:if>
			</colgroup>
        	<tr>
				<th>姓名</th>
				<th>年级</th>
				<th>培训基地</th>
        		<th>专业</th>
        		<th>退培主要原因</th>
        		<th>退培类型</th>
        		<th>学员去向</th>
        		<th>备注(培训基地意见)</th>
				<th>附件</th>
				<c:if test="${'N' ne sysCfgMap['res_return_audit']}">
				<th>省厅审核结果</th>
				<th>省厅审核意见</th>
				</c:if>
        	</tr>
			<c:forEach items="${resRecList}" var="rec">
				<tr>
					<td>${rec.doctorName}</td>
					<td>${rec.sessionNumber}</td>
					<td title="${rec.orgName}">
							${pdfn:cutString(rec.orgName,10,true,3)}
					</td>
					<td title="${rec.trainingSpeName}">
							${pdfn:cutString(rec.trainingSpeName,10,true,3) }
					</td>
					<td>${rec.reasonName}
						<label title="${rec.reason}">
							<c:if test="${not empty rec.reason }">
								(${pdfn:cutString(rec.reason,2,true,3) })
							</c:if>
						</label>
					</td>
					<td>${rec.policyName}
						<label title="${rec.policy }">
							<c:if test="${not empty rec.policy }">
								(${pdfn:cutString(rec.policy,2,true,3) })
							</c:if>
						</label>
					</td>
					<td><label title="${rec.dispositon}">${pdfn:cutString(rec.dispositon,5,true,3) }</label></td>
					<td><label title="${rec.remark}">${pdfn:cutString(rec.remark,8,true,3) }</label></td>
					<td>
						<c:forEach items="${fileMaps[rec.recordFlow]}" var="file">
							<a href="<s:url value='/res/doc/fileDown'/>?fileFlow=${file.fileFlow}">[下载附件]</a>
						</c:forEach>
					</td>
					<c:if test="${'N' ne sysCfgMap['res_return_audit']}">
					<td>${rec.auditStatusName}<c:if test="${empty rec.auditStatusName}">省厅未添加意见</c:if></td>
					<td title="${rec.auditOpinion}">${pdfn:cutString(rec.auditOpinion,8,true,3)}<c:if test="${empty rec.auditOpinion}">省厅未添加意见</c:if></td>
					</c:if>
				</tr>
			</c:forEach>
        	<c:if test="${empty resRecList}">
        		<tr>
        			<td colspan="11">无记录</td>
        		</tr>
        	</c:if>
        </table>
		<c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
	</div>
</body>
</html>