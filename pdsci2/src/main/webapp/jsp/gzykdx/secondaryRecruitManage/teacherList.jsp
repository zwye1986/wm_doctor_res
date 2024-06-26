<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
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
		function search(){
			$("#serchForm").submit();
		}
		function changeTea(){
			jboxConfirm("确认更换录取导师？",function(){
				var url = "<s:url value='/gzykdx/secondaryRecruit/secondaryEditDocTeaRec'/>";
				var userFlow = $("input[type='radio']:checked ").val();
				var userName = $("input[type='radio']:checked ").attr("userName");
				var speId = $("input[type='radio']:checked ").attr("speId");
				var speName = $("input[type='radio']:checked ").attr("speName");
				var researchAreaId = $("input[type='radio']:checked ").attr("researchAreaId");
				var researchAreaName = $("input[type='radio']:checked ").attr("researchAreaName");
				var isAcademic = $("input[type='radio']:checked ").attr("isAcademic");
				var degreeTypeId = "";
				var degreeTypeName = "";
				if(isAcademic=="Y"){
					degreeTypeId = "${gzykdxDegreeTypeEnumAcademicType.id}";
					degreeTypeName = "${gzykdxDegreeTypeEnumAcademicType.name}";
				}else{
					degreeTypeId = "${gzykdxDegreeTypeEnumProfessionType.id}";
					degreeTypeName = "${gzykdxDegreeTypeEnumProfessionType.name}";
				}
				var data = {"recordFlow":'${recordFlow}',
							"finalUserFlow":userFlow,
							"finalUserName":userName,
							"finalSpeId":speId,
							"finalSpeName":speName,
							"finalResearchAreaId":researchAreaId,
							"finalResearchAreaName":researchAreaName,
							"finalDegreeTypeId":degreeTypeId,
							"finalDegreeTypeName":degreeTypeName
				}
				jboxPost(url,data,function(resp){
					if(resp==1) {
						jboxTip("操作成功！");
						window.parent.frames['mainIframe'].location.reload(true);
						jboxClose();
					}
					if(resp==-1) {
						jboxTip("操作失败！导师剩余名额为0！");
					}
				},null,false)
			})
		}
	</script>
</head>
<body>

<div>
	<div>
		<form id="serchForm" action='<s:url value="/gzykdx/secondaryRecruit/changeTeacher"/>' method="post">
		姓名：<input class="xltext" type="text" value="${param.userName}" name="userName" onchange="search()"		>
		<input type="hidden" name="recordFlow" value="${recordFlow}">
		</form>
	</div>
	<div style="margin-top: 15px;width: 100%;">
		<c:forEach items="${teacherTargetApplyList}" var="teacher">
			<div style="float: left;width: 24.5%;position:relative" title="${teacher.researchDirection}">
				<label><input type="radio" name="1" value="${teacher.userFlow}"  userName="${teacher.userName}"
				speId="${teacher.speId}"	speName="${teacher.speName}" isAcademic="${teacher.isAcademic}"
				researchAreaId	="${teacher.researchDirectionId}"	researchAreaName="${teacher.researchDirection}"
				>${teacher.userName}&#12288;${teacher.speName}
				</label>
			</div>
		</c:forEach>
	</div>
	<div style="text-align: center;padding-top: 15px;clear:both;">
		<c:if test="${!empty teacherTargetApplyList}">
		<input type="button" value="确&#12288;认" onclick="changeTea()" class="search">
		</c:if>
		<input type="button" value="取&#12288;消" onclick="top.jboxClose();" class="search">
	</div>
</div>
</body>
</html>