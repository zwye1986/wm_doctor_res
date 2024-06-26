<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_sortable" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>

<style type="text/css">
	.selDoc{color: red;}
	#main{
	   width: 100%;
	   height: 98%;
	   padding-top: 1%;
	 }
</style>

<script type="text/javascript">

	//按条件查询
	function search(){
		$("#doctorForm").submit();
	}

	
	//选择医师加载排班
	function selDoc(tr,doctorFlow){

		$(".selDoc").removeClass("selDoc");
		$(tr).addClass("selDoc");

		var doctorName = $(tr).find("td:first").text();
		jboxLoad("rosteringHand","<s:url value='/czyyjxres/hospital/rosteringHandDept'/>?doctorFlow="+doctorFlow+"&doctorName="+encodeURI(encodeURI(doctorName)),true);

	}

</script>
</head>
<body>
<div class="main_fix" style="overflow:auto; top: 0">
		<div id="main">
		<div class="side" style="width: 20%;">
			<form id="doctorForm" method="post" action="<s:url value='/czyyjxres/hospital/rosteringHand'/>">
			<table class="xllist" style="margin-bottom: 20px;">
				<tr style="display: none;"><td></td></tr>
				<tbody class="byDoctor">
					<tr>
						<td colspan="2" style="text-align: left;padding-left: 10px;">
							进修批次：
							<select name="batchFlow" class="select" style="width:60%;" onchange="search();">
								<option value="">全部</option>
								<c:forEach items="${batchList}" var="dict">
									<option value="${dict.batchFlow}" <c:if test="${param.batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left;padding-left: 10px;">
							姓&#12288;&#12288;名：
							<input type="text" name="userName" value="${param.userName}" style="width: 58%;" onchange="search();"/>
						</td>
					</tr>
				</tbody>
				<tr>
					<th width="32%">姓名</th>
					<th width="68%">进修专业（进修时间）</th>
				</tr>
				<c:if test="${!(param.rosteringType eq GlobalConstant.FLAG_Y)}">
					<c:forEach items="${stuUserList}" var="stuUser">
						<c:set var="speFormList" value="${extInfoMap[stuUser.resumeFlow].speFormList}"></c:set>
					<tr style="cursor: pointer;" onclick="selDoc(this,'${stuUser.sysUser.userFlow}');">
							<td>
								${stuUser.sysUser.userName}
							</td>

							<td>
								<c:set var="flag" value="false" />
								<c:forEach items="${speFormList}" var="speForm" >
									<c:if test="${flag}">,</c:if>
									${speForm.speName}（${speForm.stuTimeId}个月）
									<c:set var="flag" value="true"/>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty stuUserList}">
						<tr><td colspan="2">无记录</td></tr>
					</c:if>
				</c:if>
			</table>
			</form>
		</div>
		<div id="rosteringHand" style="width: 78%;position: absolute;right: 0px;" class="side">
			<table class="basic" style="margin-left: 10px;width: 98%;">
				<tr>
					<td>
							请选择医师！
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>