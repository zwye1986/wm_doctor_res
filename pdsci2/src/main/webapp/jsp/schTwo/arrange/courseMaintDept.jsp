<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<style type="text/css">
	table.basic th,table.basic td{text-align: center;padding: 0;}
	.highLight{background: rgba(255,0,0,0.8);}
	.lastOper{background: pink;}
	.moveColor{background: rgba(225, 238, 245,0.95);}
</style>


<script type="text/javascript">
var width = 600,height=250;
//新增、编辑
function addDocRostering(doctorFlow,resultFlow){
	jboxOpen("<s:url value='/schTwo/arrange/addCourseMaint?doctorFlow='/>"+doctorFlow+'&resultFlow='+resultFlow,"新增B阶段课程维护信息", width,height);
}
//删除
function delDocRostering(resultFlow,doctorFlow){
//	var msg;
//	if(resultFlow=='' || resultFlow==null){
//		msg="确认删除该学员下的全部记录?";
//	}else{
//		msg="确认删除此条记录?";
//	}
	jboxConfirm("确认删除此条记录?", function(){
		var url = "<s:url value='/schTwo/arrange/delPeriodRel?resultFlow='/>"+resultFlow+'&doctorFlow='+doctorFlow;
		jboxPost(url, null, function(resp){
			if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
				selDoc('',doctorFlow);
			}
		}, null, false);
	});

}
</script>
</head>
<body>
	<table class="basic" style="margin-left: 10px;width: 98%;">
		<tr>
			<td style="text-align: left;padding-left: 10px;">
			<div>
				<%--<input type="button" value="删  除" class="search" style="float: right;margin-right: 10px;margin-top: 4px;" onclick="delDocRostering('','${doctor.doctorFlow}','');">--%>
				<input type="button" value="新  增" class="search" style="float: right;margin-right: 10px;margin-top: 4px;" onclick="addDocRostering('${doctor.doctorFlow}','');">
			</div>
			</td>
		</tr>
	</table>
	<table class="basic" style="width: 98%;margin-top: 10px;margin-left: 10px;">
		<tr>
			<th width="5%">序号</th>
			<th width="14%">培训基地</th>
			<th width="10%">轮转科室</th>
			<th width="10%">方案组</th>
			<th width="15%">标准科室</th>
			<th width="8%">带教老师</th>
			<th width="8%">科主任</th>
			<th width="10%">开始时间</th>
			<th width="10%">结束时间</th>
			<th width="10%">操作</th>
		</tr>
		<tbody id="">

		<c:forEach items="${arrangeResultList}" var="result" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${result.orgName}</td>
				<td>${result.schDeptName}</td>
				<td>${result.schDeptName}</td>
				<td>${result.standardDeptName}</td>
				<td>${processMap[result.resultFlow].teacherUserName}</td>
				<td>${processMap[result.resultFlow].headUserName}</td>
				<td>${result.schStartDate}</td>
				<td>${result.schEndDate}</td>
				<td><a onclick="addDocRostering('${doctor.doctorFlow}','${result.resultFlow}')" style="cursor: pointer;color: blue">编辑</a>&#12288;|<a onclick="delDocRostering('${result.resultFlow}','${doctor.doctorFlow}')" style="cursor: pointer;color: blue">&#12288;删除</a> </td>
			</tr>
		</c:forEach>
		<c:if test="${empty arrangeResultList}">
			<tr><td colspan="99">暂无记录</td></tr>
		</c:if>
		</tbody>

	</table>
</body>
</html>