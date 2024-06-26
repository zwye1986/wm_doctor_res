<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="false" />
		<jsp:param name="jquery_ui_tooltip" value="false" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="false" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
	</jsp:include>
<style type="text/css">

</style>

<script type="text/javascript">
	$(document).ready(function(){

	});

	//新增、入科
	function add(flag,resultFlow,doctorFlow, deptFlow, doctorName, deptName,schStartDate,schEndDate) {
		jboxOpen("<s:url value='/res/head/addArrange?deptFlow='/>"+deptFlow+"&doctorFlow="+doctorFlow+"&doctorName="+encodeURI(encodeURI(doctorName))+"&deptName="+encodeURI(encodeURI(deptName))+"&schStartDate="+schStartDate+"&resultFlow="+resultFlow+"&schEndDate="+schEndDate+"&flag="+flag,"新增入科信息", 900, 300);
	}
	//编辑
	function edit(flag,resultFlow,doctorFlow, deptFlow, doctorName, deptName,schStartDate,schEndDate) {
		jboxOpen("<s:url value='/res/head/addArrange?deptFlow='/>"+deptFlow+"&doctorFlow="+doctorFlow+"&doctorName="+encodeURI(encodeURI(doctorName))+"&deptName="+encodeURI(encodeURI(deptName))+"&schStartDate="+schStartDate+"&resultFlow="+resultFlow+"&schEndDate="+schEndDate+"&flag="+flag,"编辑入科信息", 900, 300);
	}

	//删除
	function del(){
		if($(".resultList:checked").length>0){
			var flows ="";
			$(".resultList:checked").each(function(){
				flows+=$(this).val()+",";
			});
			flows = flows.substring(0,flows.length-1);
			jboxConfirm("确认删除此条记录?", function(){
				var url = "<s:url value='/res/head/del?resultFlows='/>"+flows;
				jboxPost(url, null, function(resp){
					if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
						parent.window.frames["mainIframe"].window.location.reload(true);
					}
				}, null, false);
			});
		}else{
			jboxTip("请选择要删除的项！");
			return;
		}
	}
	//返回上一页
	function goback(){
		history.back();
	}
</script>
</head>
<body>
	<div style="float: left;margin-top:10px;margin-bottom: 5px;margin-left: 10px">
		<h3>入科</h3>
	</div>
	<div style="float: right;margin-top:10px;margin-bottom: 5px;margin-right:10px">
		<input type="button" value="新&#12288;增" class="search" onclick="add('','','${doctorFlow}','${deptFlow}','${doctorName}','${deptName}','${schStartDate}','${schEndDate}')"/>
		<input type="button" value="删&#12288;除" class="search" onclick="del()"/>
		<input type="button" value="返&#12288;回" class="search" onclick="goback()"/>
	</div>
	<table class="xllist" style="width: 98%;margin-top: 10px;margin-left: 10px;">
		<thead>
			<tr>
				<th width="10%">选择</th>
				<th width="20%">轮转科室</th>
				<th width="15%">开始时间</th>
				<th width="15%">结束时间</th>
				<th width="15%">带教老师</th>
				<th width="15%">科主任</th>
				<th width="10%">操作</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${processList}" var="process">
				<tr>
					<td><input type="checkbox"  class="resultList" value="${process.schResultFlow}"></td>
					<td>${process.schDeptName}</td>
					<td>${process.schStartDate}</td>
					<td>${process.schEndDate}</td>
					<td>${process.teacherUserName}</td>
					<td>${process.headUserName}</td>
					<td>
						<a style="cursor: pointer;color: #0a8cd2" onclick="add('Y','${process.schResultFlow}','${doctorFlow}','${deptFlow}','${doctorName}','${deptName}','','')">入科</a>
						|
						<a style="cursor: pointer;color: #0a8cd2" onclick="edit('edit','${process.schResultFlow}','${doctorFlow}','${deptFlow}','${doctorName}','${deptName}','','')">编辑</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty processList}">
				<tr>
					<td colspan="7">无记录</td>
				</tr>
			</c:if>
		</tbody>
	</table>

</body>
</html>