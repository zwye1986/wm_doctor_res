<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
	function checkGroup(rotationFlow){
		$(".group").hide();
		$(".group."+ rotationFlow).show();
	}
	function checkDept(groupFlow){
		$(".dept").hide();
		$(".dept."+ groupFlow).show();
	}
	function tongBu(){
		if(!$("#synchRequire").validationEngine("validate")){
			return;
		}
		jboxConfirm("确认同步要求？",function(){
			var relRecordFlow = $("#standardDeptId").val();
			jboxPost("<s:url value='/sch/template/synchronize'/>?relRecordFlow="+relRecordFlow+"&currRelRecordFlow=${param.relRecordFlow}",
				null,
				function(resp){
					if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
						top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
						var a=window.parent.frames['mainIframe'].$("#tags .selectTag");
						a.click();
						window.parent.frames['mainIframe'].location.href=$("a",a)[0].href;
						jboxClose();
					}
				},null,false);
		});
	}
</script>
</head>
<body>
	<form id="synchRequire">
		<table class="basic" style="width: 100%;margin-top: 10px;">
			<tr>
				<th style="width: 30%;">培训方案名称:</th>
				<td style="width: 70%;">
					<select name="rotationFlow" class="validate[required]" onchange="checkGroup(this.value);">
						<option/>
						<c:forEach items="${rotationList }" var="rotation">
							<option value="${rotation.rotationFlow }">${rotation.rotationName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th style="width: 30%;">组合名称:</th>
				<td style="width: 70%;">
					<select name="groupFlow" style="width: 137px;" class="validate[required]" onchange="checkDept(this.value);">
						<option/>
						<c:forEach items="${standardRotationGroupList}" var="rotationGroup">
							<option style="display: none;" class="${rotationGroup.rotationFlow } group" value="${rotationGroup.groupFlow }">${rotationGroup.groupName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th style="width: 30%;">标准科室:</th>
				<td style="width: 70%;">
					<select id="standardDeptId" name="standardDeptId" class="validate[required]" style="width: 137px;">
						<option/>
						<c:forEach items="${standardRotationDeptList}" var="rotationDept">
							<option style="display: none;" class="${rotationDept.groupFlow } dept" value="${rotationDept.recordFlow }">${rotationDept.standardDeptName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		</form>
		<p style="text-align: center; margin-top: 10px;">
			<input type="button" class="search" onclick="tongBu();" value="同&#12288;步"/>
			<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
		</p>
</body>
</html>