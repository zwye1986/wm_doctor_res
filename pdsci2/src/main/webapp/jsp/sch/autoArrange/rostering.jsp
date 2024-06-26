<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="true"/>
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
function checkCondition()
{
	if(!$("#sessionNumber").val())
	{
		jboxTip("请选择需要排班的年级！");
		return false;
	}
	var url ="<s:url value='/sch/autoArrange/checkPrefixCondition?sessionNumber='/>"+$("#sessionNumber").val();
	jboxLoad("resultDiv",url,true);
}
function clearDiv()
{
	$("#resultDiv").html("");
}
</script>
</head>
<body>
<div class="mainright">
	<div class="content" style="height: 90%">
		<div class="title1 clearfix">	
			<form id="searchForm"  method="post">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<select name="sessionNumber" id="sessionNumber" class="qselect" onchange="clearDiv()">
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictName}" ${dict.dictName eq sessionNumber?'selected="selected"':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="lastDiv">
						<input type="button" class="searchInput" value="开始校验" style="margin-left: 10px;" onclick="checkCondition();"/>
					</div>
				</div>
				<div class="queryDiv" style="margin-top: 0px;">
					<div class="" style="text-align: left;height: auto;margin-left: 30px;">
					使用此功能需要注意以下两点：<br/>
						&#12288;&#12288;1、完成所有方案配置<br/>
						&#12288;&#12288;2、所有学员都已完成方案分配
					</div>
				</div>
			</form>
		</div>
		<div class="resultDiv" id="resultDiv" style="">

		</div>
	</div> 
</div>
</body>
</html>