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
		.side1{margin:0;}
	</style>

	<script type="text/javascript">
		function search(){
			$("#doctorForm").submit();
		}

		//选择医师加载排班
		function selDoc(tr,doctorFlow){
			$(".selDoc").removeClass("selDoc");
			$(tr).addClass("selDoc");

			jboxLoad("rosteringHand","<s:url value='/schTwo/arrange/courseMaintDept'/>?doctorFlow="+doctorFlow,true);

		}
	</script>
</head>
<body>
<div class="main_fix" style="overflow: hidden; top: 0">
	<div id="main">
		<div class="side" style="width: 20%;">
			<form id="doctorForm" method="post" action="<s:url value='/schTwo/arrange/courseMaint'/>">
				<table class="xllist" style="margin-bottom: 20px;">
					<tr style="display: none;"><td></td></tr>
					<%--<tr>--%>
						<%--<td colspan="2" style="text-align: left;padding-left: 10px;">--%>
							<%--人员类型：--%>
							<%--<select name="doctorCategoryId" style="width: 60%;" onchange="search();">--%>
								<%--<option/>--%>
								<%--<c:forEach items="${recDocCategoryEnumList}" var="category">--%>
									<%--<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>--%>
									<%--<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">--%>
										<%--<option value="${category.id}" ${doctor.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>--%>
									<%--</c:if>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tbody class="byDoctor">
					<tr>
						<td colspan="2" style="text-align: left;padding-left: 10px;">
							年&#12288;&#12288;级：
							<select name="sessionNumber" style="width: 60%;" onchange="search();">
								<option/>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
									<option value="${dict.dictName}" ${doctor.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td colspan="2" style="text-align: left;padding-left: 10px;">
							培训专业：
							<select name="trainingSpeId" style="width: 60%;" onchange="search();">
								<option/>
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td colspan="2" style="text-align: left;padding-left: 10px;">
							姓&#12288;&#12288;名：
							<input type="text" name="doctorName" value="${doctor.doctorName}" style="width: 58%;" onchange="search();"/>
						</td>
					</tr>
					</tbody>
					<tr>
						<th>姓名</th>
						<th>专业方向</th>
					</tr>
					<c:forEach items="${doctorList}" var="doctor">
						<tr style="cursor: pointer;" onclick="selDoc(this,'${doctor.doctorFlow}');" orgFlow="${doctor.orgFlow}" rotationFlow="${doctor.rotationFlow}" rotationName="${doctor.rotationName}"
							secondRotationFlow="${doctor.secondRotationFlow}" secondRotationName="${doctor.secondRotationName}">
							<td>
								<c:if test="${(doctor.selDeptFlag ne GlobalConstant.FLAG_Y) && not empty doctor.rotationFlow}">
									<font id="${doctor.doctorFlow}WaitSel" color="red">*</font>
								</c:if>
									${doctor.doctorName}
							</td>
							<td>${doctor.standardDeptName}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty doctorList}">
						<tr><td colspan="2">无记录</td></tr>
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
