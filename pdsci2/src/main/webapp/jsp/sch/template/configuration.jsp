
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

			function save(rotationFlow) {
				var url = "<s:url value='/sys/cfg/save'/>";
				var data = $('#saveCfgForm'+rotationFlow).serialize();
				jboxPost(url, data,function(resp){

				},null,true);
			}

		</script>
	</head>
<body>
<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
<div class="mainright" align="center" style="height: 100%;background-color: white;">
	<div class="content">
		<div class="title1 clearfix" align="left">
			<table class="basic" width="100%" style="margin-bottom: 10px;">
				<tr>
					<td width="45%" style="text-align: left;padding-left: 10px;"><font style="font-weight: bolder;">培训方案名称 ：</font>${rotation.rotationName}</td>
					<td width="25%" style="text-align: left;padding-left: 10px;"><font style="font-weight: bolder;">培训专业：</font>${rotation.speName}</td>
					<td width="30%" style="text-align: left;padding-left: 10px;" colspan="2"><font style="font-weight: bolder;">年限：</font>${rotation.rotationYear}</td>
				</tr>
				<tr>
					<td width="45%" style="text-align: left;padding-left: 10px;"><font style="font-weight: bolder;">标准科室 ：</font>${rotationDept.standardDeptName}</td>
					<td width="25%" style="text-align: left;padding-left: 10px;"><font style="font-weight: bolder;">轮转时间(${applicationScope[unitKey].name})：</font>${rotationDept.schMonth}</td>
					<td style="text-align: left;padding-left: 10px;"><font style="font-weight: bolder;">是否必轮：</font>${rotationDept.isRequired eq GlobalConstant.FLAG_Y?'是':'否'}</td>
					<c:if test="${rotationDept.isRequired eq GlobalConstant.FLAG_N}">
						<td style="text-align: left;padding-left: 10px;"><font style="font-weight: bolder;">组合名称：</font>${groupMap[rotationDept.groupFlow].groupName}</td>
					</c:if>
				</tr>
			</table>
			<div><font color="red">&#12288;&#12288;选择后，需刷新内存才能生效!!!</font></div>
			<div style="width: 100%;height: 400px;">
				<table class="basic" width="100%" style="margin-bottom: 10px;">
					<tr>
						<th width="50%" style="text-align: center">数据类型</th>
						<th width="50%" style="text-align: center">表单</th>
					</tr>
					<c:if test="${typeId eq jszyTCMPracticEnumN.id}">
						<c:forEach items="${registryTypeEnumList}" var="registryType">
							<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y
							&& GlobalConstant.FLAG_Y eq registryType.haveReq
							&& pdfn:findChineseOrWestern(rotation.rotationTypeId,registryType.id)
							}">
								<tr>
									<td>${registryType.name}</td>
									<td>
										<form id="saveCfgForm${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}">
											<c:set var="key" value="res_form_category_${rotation.rotationFlow}_${rotationDept.recordFlow}_${registryType.id}"/>
											<input type="hidden" name="cfgCode" value="${key}">
											<select id="${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}Form" name="${key}" onchange="save('${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}');">
												<option></option>
												<c:forEach items="${applicationScope.resFormDictList}" var="formDict">
													<option data-is-dept-form="${formDict.hasDeptForm}" value="${formDict.dictId}" <c:if test="${sysCfgMap[key] eq formDict.dictId}">selected</c:if>>${formDict.dictDesc}</option>
												</c:forEach>
											</select>
											<input type="hidden" name="${key}_ws_id"  value="res">
											<input type="hidden" name="${key}_desc"  value="${rotation.rotationName}${rotationDept.standardDeptName}${registryType.name}表单来源">
										</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
						<c:forEach items="${practicRegistryTypeEnumList}" var="registryType">
							<c:set value="practic_registry_type_${registryType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y && GlobalConstant.FLAG_Y eq registryType.haveReq
							&& pdfn:findChineseOrWestern(rotation.rotationTypeId,registryType.id)}">
								<tr>
									<td>${registryType.name}</td>
									<td>
										<form id="saveCfgForm${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}">
											<c:set var="key" value="res_form_category_${rotation.rotationFlow}_${rotationDept.recordFlow}_${registryType.id}"/>
											<input type="hidden" name="cfgCode" value="${key}">
											<select id="${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}Form" name="${key}" onchange="save('${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}');">
												<option></option>
												<c:forEach items="${applicationScope.resFormDictList}" var="formDict">
													<option data-is-dept-form="${formDict.hasDeptForm}" value="${formDict.dictId}" <c:if test="${sysCfgMap[key] eq formDict.dictId}">selected</c:if>>${formDict.dictDesc}</option>
												</c:forEach>
											</select>
											<input type="hidden" name="${key}_ws_id"  value="res">
											<input type="hidden" name="${key}_desc"  value="${rotation.rotationName}${rotationDept.standardDeptName}${registryType.name}表单来源">
										</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
						<c:forEach items="${theoreticalRegistryTypeEnumList}" var="registryType">
							<c:set value="theoretical_registry_type_${registryType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y && GlobalConstant.FLAG_Y eq registryType.haveReq
							&& pdfn:findChineseOrWestern(rotation.rotationTypeId,registryType.id)}">
								<tr>
									<td>${registryType.name}</td>
									<td>
										<form id="saveCfgForm${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}">
											<c:set var="key" value="res_form_category_${rotation.rotationFlow}_${rotationDept.recordFlow}_${registryType.id}"/>
											<input type="hidden" name="cfgCode" value="${key}">
											<select id="${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}Form" name="${key}" onchange="save('${rotation.rotationFlow}${rotationDept.recordFlow}${registryType.id}');">
												<option></option>
												<c:forEach items="${applicationScope.resFormDictList}" var="formDict">
													<option data-is-dept-form="${formDict.hasDeptForm}" value="${formDict.dictId}" <c:if test="${sysCfgMap[key] eq formDict.dictId}">selected</c:if>>${formDict.dictDesc}</option>
												</c:forEach>
											</select>
											<input type="hidden" name="${key}_ws_id"  value="res">
											<input type="hidden" name="${key}_desc"  value="${rotation.rotationName}${rotationDept.standardDeptName}${registryType.name}表单来源">
										</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>