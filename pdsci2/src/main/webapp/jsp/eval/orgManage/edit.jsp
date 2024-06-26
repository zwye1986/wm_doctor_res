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
	function saveOrg() {
		if(false==$("#sysOrgForm").validationEngine("validate")){
			return ;
		}
		var orgProvNameText = $("#orgProvId option:selected").text();
		if(orgProvNameText=="选择省"){
			orgProvNameText = "";
		}
		var orgCityNameText = $("#orgCityId option:selected").text();
		if(orgCityNameText=="选择市"){
			orgCityNameText = "";
		}
		var orgAreaNameText = $("#orgAreaId option:selected").text();
		if(orgAreaNameText=="选择地区"){
			orgAreaNameText = "";
		}
		$("#orgProvName").val(orgProvNameText);
		$("#orgCityName").val(orgCityNameText);
		$("#orgAreaName").val(orgAreaNameText);
		var url = "<s:url value='/eval/orgManage/save'/>";
		var data = $('#sysOrgForm').serialize();
		jboxPost(url, data, function(resp) {
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
				window.parent.frames['mainIframe'].window.toPage('${currentPage}');
				jboxClose();
			}
		},null,true);
	}
	function sendSchoolName(exp){
		$("#sendSchoolName").val($(exp).text());
	}
</script>
</head>
<body>
<form id="sysOrgForm" style="height: 100px;" >
<input name="orgFlow" value="${param.orgFlow}" type="hidden"/>
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0" style="height: 250px">
				<table width="800" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<th width="20%">基地代码：</th>
						<td width="30%"><input class="validate[required] xltext" name="orgCode" type="text" value="${sysOrg.orgCode }"/></td>
						<th width="20%">基地名称：</th>
						<td width="30%"><input class="validate[required] xltext" name="orgName" type="text" value="${sysOrg.orgName }"/></td>
					</tr>
					<tr>
						<th>基地类型：</th>
						<td id="typeTd">
							<script type="text/javascript">
								function selLevel(typeId){
									var condition = typeId=="${orgTypeEnumHospital.id}";
									$(".level").toggle(condition);
									$("#typeTd").attr("colspan",condition?"1":"3");
								}
								$(function(){
									$("[name='orgTypeId']").change();
								});
							</script>
							<select name="orgTypeId" class="xlselect" onchange="selLevel(this.value);">
								<option value="">请选择</option>
								<c:forEach var="orgType" items="${orgTypeEnumList}">
									<c:if test="${(orgType.id eq orgTypeEnumHospital.id)}">
										<option value="${orgType.id}" <c:if test="${sysOrg.orgTypeId==orgType.id}">selected="selected"</c:if>>${orgType.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th class="level">基地等级：</th>
						<td class="level">
							<select name="orgLevelId" class="xlselect">
								<option value="">请选择</option>
								<c:forEach items="${orgLevelEnumList}" var="level">
									<option value="${level.id}" <c:if test="${level.id eq sysOrg.orgLevelId}">selected</c:if>>${level.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>基地电话：</th>
						<td colspan="3"><input class="validate[custom[phone]] xltext" name="orgPhone" type="text" value="${sysOrg.orgPhone}"/></td>
					</tr>
					<tr>
						<th>所属地区：</th>
						<td colspan="3">
							<div id="provCityAreaId">
								<select id="orgProvId" name="orgProvId" class="province xlselect" data-value="${sysOrg.orgProvId}" data-first-title="选择省"></select>
								<select id="orgCityId" name="orgCityId" class="city xlselect" data-value="${sysOrg.orgCityId}" data-first-title="选择市"></select>
								<select id="orgAreaId" name="orgAreaId" class="area xlselect" data-value="${sysOrg.orgAreaId}" data-first-title="选择地区"></select>
							</div>
							<input id="orgProvName" name="orgProvName" type="hidden" value="${sysOrg.orgProvName}">
							<input id="orgCityName" name="orgCityName" type="hidden" value="${sysOrg.orgCityName}">
							<input id="orgAreaName" name="orgAreaName" type="hidden" value="${sysOrg.orgAreaName}">
							<script type="text/javascript">
								// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件 
								$.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>"; 
								$.cxSelect.defaults.nodata = "none"; 
	
								$("#provCityAreaId").cxSelect({ 
								    selects : ["province", "city", "area"], 
								    nodata : "none",
									firstValue : ""
								}); 
							</script>
						</td>
					</tr>
					<tr>
						<th>基地地址：</th>
						<td colspan="3"><input style="width: 560px;" class="xltext" name="orgAddress" type="text" value="${sysOrg.orgAddress}"/></td>
					</tr>
					<tr>
						<th>排序码：</th>
						<td colspan="3">
						    <input type="text" name="ordinal" value="${sysOrg.ordinal}" class="validate[maxSize[5] , custom[integer]] xltext"/>
						</td>
					</tr>	
				</table>
				<div class="button" style="width: 800px;">
					<input class="search" type="button" value="保&#12288;存" onclick="saveOrg();" />
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>