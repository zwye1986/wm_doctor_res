<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="true" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
		<jsp:param name="ueditor" value="true"/>
		<jsp:param name="font" value="true"/>
	</jsp:include>
    <style>
        .tbBorder td{
            border-right: 1px solid #e7e7eb;
        }
        .tbBorder td:last-child{
            border-right: none;
        }
		html,body{
			height: 100%;
		}
		.search_table{
			height: calc(100% - 40px);
			overflow: auto;
		}
    </style>
	<script type="text/javascript">
        function exportManage(){
            var url = '<s:url value="/jsres/activityQuery/exportInfo?activityFlows=${activityFlows}"/>';
            jboxExp(null,url);
        }
	</script>
</head>
<body>
<div align="center" style="margin-bottom: 10px;padding-left: 30px;text-align: left;">
	<input type="button" class="btn_green" onclick="exportManage();" value="导&#12288;出"/>
</div>
<div class="search_table">
	<table border="0" cellpadding="0" cellspacing="0" class="grid tbBorder">
		<colgroup>
			<col width="24%" />
			<col width="8%" />
			<col width="20%" />
			<col width="8%" />
			<col width="10%" />
			<col width="20%" />
			<col width="10%" />
		</colgroup>
		<tr>
			<th>活动名称</th>
			<th>活动形式</th>
			<th>活动地点</th>
			<th>主讲人</th>
			<th>所在科室</th>
			<th>活动时间</th>
			<th>实际主讲人</th>
		</tr>
		<c:forEach items="${activityInfos}" var="info">
			<tr>
				<td>${info.activityName}</td>
				<td>${info.activityTypeName}</td>
				<td>${info.activityAddress}</td>
				<td>${info.userName}</td>
				<td>${info.deptName}</td>
				<td>${info.startTime}~<br>${info.endTime}</td>
				<td>${info.realitySpeaker}</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
