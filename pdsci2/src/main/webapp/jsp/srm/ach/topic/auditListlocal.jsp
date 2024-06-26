<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
<script type="text/javascript">
var height=(window.screen.height)*0.7;
var width=(window.screen.width)*0.7;
function search(){
	jboxStartLoading();
	$("#searchForm").submit();
}

function audit(topicFlow){
	jboxStartLoading();
	jboxOpen("<s:url value='/srm/ach/topic/audit?topicFlow='/>"+topicFlow, "审核", 950, 650);
}

function view(topicFlow){
	jboxStartLoading();
	jboxOpen("<s:url value='/srm/ach/topic/edit?topicFlow='/>"+ topicFlow+"&editFlag=${GlobalConstant.FLAG_N}", "查看论文信息",width, height);
}

function toPage(page){
	var form = $("#searchForm");
	$("#currentPage").val(page);
	jboxStartLoading();
	form.submit();
}

function lookFactor(issnCode){
	jboxStartLoading();
	jboxOpen("<s:url value = '/sys/impactorFactor/getImpactorFactorByISSN2?issn='/>"+ issnCode, "最新SCI收录期刊影响因子" , 1000, 400);
}
function expert() {
    var url = "<s:url value='/srm/ach/topic/exportTopicExcel/local'/>";
    jboxSubmit($('#searchForm'), url, null, null);
    jboxEndLoading();
}
$(function(){
    var url = "<s:url value='/srm/ach/topic/searchDept'/>";
    var selectedFlow="${param.deptFlow}";
    jboxPost(url,null,function(resp){
        $.each(resp,function(i,n){
            if(selectedFlow == n.deptFlow){
                $("#selectDept").append("<option selected='selected' value='"+ n.deptFlow +"'>"+ n.deptName +"</option>");
            }else {
                $("#selectDept").append("<option  value='" + n.deptFlow + "'>" + n.deptName + "</option>");
            }
        });
    },null,false);
});
</script>
</head>
<body>

 <div class="mainright">
    <div class="content">
	<div class="title1 clearfix">
	<form id="searchForm" action="<s:url value="/srm/ach/topic/auditList/local"/>" method="post">
		<p>
	 		&#12288;课题名称：
	 		<input type="text" name="topicName" value="${param.topicName }" class="xltext"/>
	 		&#12288;年&#12288;&#12288;度：
	 		<input class="xltext ctime" type="text" name="topicStartTime" value="${param.topicStartTime }" style="width: 157px;" onClick="WdatePicker({dateFmt:'yyyy'})"  readonly="readonly"/>
 		</p>
		<p>
			&#12288;&#12288;申请人：
			<input type="text" class="xltext" name="applyUserName" value="${param.applyUserName}"/>
 			&#12288;科&#12288;&#12288;室：
	 	 	<select name="deptFlow" class="xlselect" id = "selectDept">
				<option value="">请选择</option>
             </select>
			&#12288;审核结果：
			<input type="radio" name="operStatusId" id="achStatusEnumAll"  
				<c:if test="${param.operStatusId eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y}" /><label for="achStatusEnumAll">全部</label> 
			<input type="radio" name="operStatusId" id="achStatusEnum_Submit" 
				<c:if test="${empty param.operStatusId }">checked="checked"</c:if> <c:if test="${param.operStatusId eq achStatusEnumSubmit.id}">checked="checked"</c:if> value="${achStatusEnumSubmit.id }" /><label for="achStatusEnum_Submit">${achStatusEnumSubmit.name}</label> 
			<input type="radio"  name="operStatusId" id="achStatusEnum_FirstAudit"
				<c:if test="${param.operStatusId eq achStatusEnumFirstAudit.id }">checked="checked"</c:if> value="${achStatusEnumFirstAudit.id }" /><label for="achStatusEnum_FirstAudit">${achStatusEnumFirstAudit.name}</label> 
			&#12288;&#12288;
			<input type="button" class="search" onclick="search();" value="查&#12288;询">
            <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
		</p>
		</form>
		</div>
		
		<table class="xllist">
			<thead>
				<tr>
                    <c:if test="${applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">
                        <th>序号</th>
                    </c:if>
                    <th width="10%">年度</th>
					<th>课题名称</th>
					<th>课题申请人</th>
					<th>科室</th>
					<th>主管部门</th>
					<th>审核状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${topicList}" var="topic" varStatus="topicNum">
			<tr>
                <c:if test="${applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">
                    <td>${topicNum.count}</td>
                </c:if>
                <td>${fn:substring(topic.topicStartTime, 0, 4)}</td>
                <td>${topic.topicName}</td>
                <td>${topic.applyUserName}</td>
                <td>${topic.deptName}</td>
                <td>${topic.chargeOrg}</td>
				<td>${topic.operStatusName}</td>
				<td>
					<c:if test="${topic.operStatusId eq achStatusEnumSubmit.id }">
						<a href="javascript:void(0)" onclick="audit('${topic.topicFlow}');">[审核]</a>
					</c:if>
					<c:if test="${topic.operStatusId eq achStatusEnumFirstAudit.id }">
						<a href="javascript:void(0)" onclick="view('${topic.topicFlow}');">[查看]</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
			</table>
			<%-- <p>
				<input type="hidden" id="currentPage" name="currentPage">
			    <c:set var="pageView" value="${pdfn:getPageView2(topicList , 10)}" scope="request"></c:set>
				<pd:pagination toPage="toPage"/>
			</p> --%>
	</div>
</div> 	
</body>
</html>