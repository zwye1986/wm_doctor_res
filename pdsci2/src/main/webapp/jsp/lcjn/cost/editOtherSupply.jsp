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
	<c:if test="${!empty param.recordFlow}">
	$(function(){
		var v= $("#dictId").val();
		$("."+v).css("display","");
	});
	</c:if>
	function changePrice(flow){
		$(".price").css("display","none");
		$("."+flow).css("display","");
	}
	function save(param){
		if(!$("#editForm").validationEngine("validate")){
			return;
		}
		var skillName =$("#skillFlow").find("option:selected").text();
		$("#skillName").val(skillName);
		var dictName =$("#dictId").find("option:selected").text();
		$("#dictName").val(dictName);
		jboxConfirm(param=='N'?"确认删除？":"确认保存？",function(){
			jboxPost('<s:url value="/lcjn/cost/saveOtherSupply"/>?recordStatus='+param,$("#editForm").serialize(),
				function(resp){
				jboxEndLoading();
				if(resp==1) jboxTip("操作成功！");
				if(resp==0) jboxTip("操作失败！");
				window.parent.frames['mainIframe'].window.$("#costButton").click();
				jboxClose();
			},null,false);
		});
	}
</script>
<style type="text/css">
</style>
</head>
<body>
	<form id="editForm">
		<input type="hidden" name="recordFlow" value="${param.recordFlow}">
		<input type="hidden" name="courseFlow" value="${param.courseFlow}">
		<table class="basic" style="width: 100%">
			<tr>
				<td>
					技能名称：
					<c:if test="${empty param.recordFlow}">
					<select class="xlselect validate[required]" name="skillFlow" id="skillFlow" style="width:137px;">
						<option/>
						<c:forEach items="${courseSkills}" var="courseSkill">
							<option value="${courseSkill.skillFlow}">${courseSkill.skillName}</option>
						</c:forEach>
					</select>
					<input type="hidden" name="skillName" id="skillName"/>
					</c:if>
					<c:if test="${!empty param.recordFlow}">
					${courseSupply.skillName}
					<input type="hidden" name="skillFlow" value="${courseSupply.skillFlow}"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					耗材名称：
					<select id="dictId" class="xlselect validate[required]" name="dictId" style="width: 137px;" onchange="changePrice(this.value)">
						<option/>
						<c:forEach items="${suppliesList}" var="supply">
							<option value="${supply.dictId}"
								<c:if test="${courseSupply.dictId eq supply.dictId}">selected</c:if>
							>${supply.dictName}</option>
						</c:forEach>
					</select>
					<input type="hidden" name="dictName" id="dictName"/>
				</td>
			</tr>
			<tr>
				<td>
					耗材单价：
					<c:forEach items="${priceMap}" var="item">
					<span class="price ${item.key}" style="display: none">${item.value}</span>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>
					耗材数量：
					<input type="text" name="useNum" value="${courseSupply.useNum}" class="validate[required] " style="width: 133px;">
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<input type="button" value="保存" class="search" onclick="save('Y')">
					<c:if test="${!empty param.recordFlow}">
					<input type="button" value="删除" class="search" onclick="save('N')">
					</c:if>
					<input type="button" value="取消" class="search" onclick="jboxClose();">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>