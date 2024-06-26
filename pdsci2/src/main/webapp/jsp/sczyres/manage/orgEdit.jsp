<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
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
		var orgLevelNameText = $("#orgLevelId option:selected").text();
		var sendSchoolName = $("#sendSchoolId option:selected").text();

		$("#orgProvName").val(orgProvNameText);
		$("#orgCityName").val(orgCityNameText);
		$("#orgAreaName").val(orgAreaNameText);
		$("#orgLevelName").val(orgLevelNameText);
		$("#sendSchoolName").val(sendSchoolName);
		var url = "<s:url value='/sys/org/save4sczy'/>";
		var data = $('#sysOrgForm').serialize();
		jboxPost(url, data, function() {
			window.parent.search();
			jboxClose();
		});
	}
</script>
<style>
	.base_info th,.base_info td{height:45px;}
	.input{height:30px;}
</style>
</head>
<body>
<form id="sysOrgForm">
<input name="orgFlow" value="${param.orgFlow}" type="hidden"/>
<input name="orgTypeId" value="${empty sysOrg.orgTypeId?'Hospital':sysOrg.orgTypeId}" type="hidden"/>
	<div class="main_bd">
		<div class="div_table">
			<table width="800"  border="0" cellpadding="0" cellspacing="0" class="base_info">
				<tr>
					<th width="20%">机构代码：</th>
					<td width="30%"><input class="input" name="orgCode" type="text" value="${sysOrg.orgCode }"/></td>
					<th width="20%">机构名称：</th>
					<td width="30%"><input class="validate[required] input" name="orgName" type="text" value="${sysOrg.orgName }"/></td>
				</tr>
				<tr>
					<th class="level">基地类型：</th>
					<td class="level">
						<select name="orgLevelId" id="orgLevelId" class="select">
							<option/>
							<c:forEach items="${sczyResOrgLevelEnumList}" var="level">
								<option value="${level.id}" <c:if test="${level.id eq sysOrg.orgLevelId}">selected</c:if>>${level.name}</option>
							</c:forEach>
						</select>
						<input id="orgLevelName" name="orgLevelName" type="hidden" value="${sysOrg.orgLevelName}">
					</td>
					<th class="level">基地等级：</th>
					<td class="level">
						<select name="sendSchoolId" id="sendSchoolId" class="select">
							<option/>
							<c:forEach items="${dictTypeEnumBaseAttributeList}" var="level">
								<option value="${level.dictId}" <c:if test="${level.dictId eq sysOrg.sendSchoolId}">selected</c:if>>${level.dictName}</option>
							</c:forEach>
						</select>
						<input id="sendSchoolName" name="sendSchoolName" type="hidden" value="${sysOrg.sendSchoolName}">
					</td>
				</tr>
				<tr>
					<th>所属地区：</th>
					<td colspan="3" style="padding-left:10px; ">
						<c:if test="${empty param.orgFlow}">
							<div id="provCityAreaId">
								<select id="orgProvId" name="orgProvId" class="province select" data-value="${sessionScope.currWsId == GlobalConstant.EDC_WS_ID?'':applicationScope.sysCfgMap['srm_default_orgProvId']}" data-first-title="选择省"></select>
								<select id="orgCityId" name="orgCityId" class="city select" data-value="${sessionScope.currWsId == GlobalConstant.EDC_WS_ID?'':applicationScope.sysCfgMap['srm_default_orgCityId']}" data-first-title="选择市"></select>
								<select id="orgAreaId" name="orgAreaId" class="area select" data-value="" data-first-title="选择地区"></select>
							</div>
						</c:if>
						<c:if test="${not empty param.orgFlow}">
							<div id="provCityAreaId">
								<select id="orgProvId" name="orgProvId" class="province select" data-value="${sysOrg.orgProvId}" data-first-title="选择省"></select>
								<select id="orgCityId" name="orgCityId" class="city select" data-value="${sysOrg.orgCityId}" data-first-title="选择市"></select>
								<select id="orgAreaId" name="orgAreaId" class="area select" data-value="${sysOrg.orgAreaId}" data-first-title="选择地区"></select>
							</div>
						</c:if>
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
					<th>机构地址：</th>
					<td colspan="3"><input style="width: 560px;" class="input" name="orgAddress" type="text" value="${sysOrg.orgAddress}"/></td>
				</tr>
				<%--<tr>--%>
					<%--<th>排序码：</th>--%>
					<%--<td colspan="3">--%>
						<%--<input type="text" name="ordinal" value="${sysOrg.ordinal}" class="validate[maxSize[5] , custom[integer]] input"/>--%>
					<%--</td>--%>
				<%--</tr>--%>
			</table>
			<div class="button" style="width: 800px;">
				<input class="btn_blue" type="button" value="保&#12288;存" onclick="saveOrg();" />
			</div>
		</div>
	</div>
</form>
</body>
</html>