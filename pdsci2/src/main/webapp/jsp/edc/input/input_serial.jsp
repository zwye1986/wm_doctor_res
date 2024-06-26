
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
</head>
<body>
	<c:set var="columnNum" value="${sysCfgMap['edc_serial_attr_count'] }"/>
	<c:set var="commAttrCode" value="${param.commAttrCode }" />
	<c:set var="elementCode" value="${param.elementCode }" />
	<table  class="reptb" >
		<tr>
			<th style="width: 80px;" class="${elementCode }_tbody"><img title="新增"
				src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
				style="cursor: pointer;" onclick="addTr('${elementCode}');" /> <img
				title="删除"
				src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
				onclick="delTr('${elementCode}')" style="cursor: pointer;" />
			</th>
			<th style="width: 50px;">序号</th>
			<c:forEach items="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode] }" var="attr">
				<th width="150px;">${sessionScope.projDescForm.attrMap[attr.attrCode].attrName}</th>
			</c:forEach>
			<c:forEach  begin="1" end="${columnNum- fn:length(sessionScope.projDescForm.visitElementAttributeMap[commAttrCode])}" >
				<th  width="150px;"></th>
			</c:forEach>
		</tr>
		<tbody id="${elementCode }_TBODY">
			<c:set var="seqValueMap" value="${elementSerialSeqValueMap[elementCode]}"></c:set>
			<c:choose>
				<c:when test="${fn:length(elementSerialSeqValueMap[elementCode])>0}">
					<c:forEach items="${seqValueMap }" var="valueMapRecord">
						<tr id="serialSeqTr">
							<td style="width: 50px;">
								<input type="checkbox" name="elementSerialSeq" value="${valueMapRecord.key }" />
							</td>
							<td style="width: 50px;"><div>${valueMapRecord.key }</div></td>
							<c:forEach items="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode] }" var="attr">
								<c:set var="commCodeFlow" value="${commAttrCode }_${ attr.attrCode}"></c:set>
								<c:set var="value" value="${pdfn:getVisitData(attr.attrCode,valueMapRecord.value,param.inputOperFlow,edcPatientVisit) }"></c:set>
								<c:set var="commNormalValueKey" value="${patient.orgFlow }_${elementCode }" />
								<c:set var="attrType" value="" />
								<!-- 单位 -->
								<c:if test="${empty value && sessionScope.projDescForm.attrMap[attr.attrCode].attrVarName == GlobalConstant.DEFAULT_ATTR_UNIT_VAR_NAME}">
									<c:set var="value" value="${sessionScope.projDescForm.elementStandardUnitMap[elementCode] }" />
								</c:if>
								<td>
									<!-- 文本/日期-->
									<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumText.id or 
										empty sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId or
										sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumDate.id }">
										<c:set var="dateClass" value="" />
										<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].attrVarName == GlobalConstant.DEFAULT_ATTR_UNIT_VAR_NAME}">
											<c:set var="attrType" value="${GlobalConstant.DEFAULT_ATTR_UNIT_VAR_NAME }" />
										</c:if>
										<span title="${pdfn:getCodeTitle(attr.attrCode,commAttrCode) }">
											<input type="text"  
											style="width: 80px;padding-left: 5px;padding-right: 5px;"
											name="${attr.attrCode }_${valueMapRecord.key }"
											attrCode="${ attr.attrCode }" value="${value }"
											codeValues="${pdfn:getCodeValues(attr.attrCode,commAttrCode) }" attrType="${attrType }"
											isSerialSeq="${GlobalConstant.FLAG_Y}"
											<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumDate.id }">
											  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											  onblur="doSave('${GlobalConstant.FLAG_Y }','${attr.attrCode }',this);" 
											    <c:set var="dateClass" value="dateClass"/>
											</c:if>
											class="${dateClass} validate[${pdfn:getCondition(attr.attrCode,commAttrCode,commNormalValueKey,patient.sexId) }] input serial_input"
											onchange="doSave('${GlobalConstant.FLAG_Y }','${attr.attrCode }',this);" />
										</span>
									</c:if>
									<!-- 单选 /复选-->
									<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumRadio.id or 
												  sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumCheckbox.id}">
										<c:set var="inputTypeId" value="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId }" />
										<c:forEach items="${ sessionScope.projDescForm.visitAttrCodeMap[commCodeFlow]}" var="code">
											<c:set var="edcAttrCode" value="${sessionScope.projDescForm.codeMap[code.codeFlow]}" />
											<input type="${inputTypeId }" 
												name="${attr.attrCode }_${valueMapRecord.key }"
												attrCode="${ attr.attrCode }" isSerialSeq="${GlobalConstant.FLAG_Y}"
												<c:if test="${fn:indexOf(value,edcAttrCode.codeValue)>-1 }">checked="checked"</c:if>
												onchange="doSave('${GlobalConstant.FLAG_Y }','${attr.attrCode }',this);"
												value="${edcAttrCode.codeValue }"
												id="${attr.attrCode }_${edcAttrCode.codeValue }_${valueMapRecord.key}"
												class="input" attrType="${attrType }" />
											<label for="${attr.attrCode }_${edcAttrCode.codeValue }_${valueMapRecord.key}">${edcAttrCode.codeName }&#12288;</label>
										</c:forEach>
									</c:if>
									<!-- 下拉-->
									<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumSelect.id }">
										<select name="${attr.attrCode }_${valueMapRecord.key }"  style="width: 80px;padding-left: 5px;padding-right: 5px;"
											attrCode="${ attr.attrCode }" isSerialSeq="${GlobalConstant.FLAG_Y}"
											onchange="doSave('${GlobalConstant.FLAG_Y }','${attr.attrCode }',this);"
											class="input serial_select" attrType="${attrType }">
											<option value=""></option>
											<c:forEach items="${ sessionScope.projDescForm.visitAttrCodeMap[commCodeFlow]}" var="code">
												<c:set var="edcAttrCode" value="${sessionScope.projDescForm.codeMap[code.codeFlow]}" />
												<option value="${edcAttrCode.codeValue }"
													<c:if test="${edcAttrCode.codeValue == value}">selected</c:if>>${edcAttrCode.codeName}
												</option>
											</c:forEach>
										</select>
									</c:if>
								</td>
							</c:forEach>
							<c:forEach  begin="1" end="${columnNum- fn:length(sessionScope.projDescForm.visitElementAttributeMap[commAttrCode])}" >
								<td ></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<!-- 默认存在一条空记录 -->
					<tr id="serialSeqTr">
						<td><input type="checkbox" name="elementSerialSeq" value="1" /></td>
						<td><div>1</div></td>
						<c:forEach items="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode] }" var="attr">
							<c:set var="commCodeFlow" value="${commAttrCode }_${ attr.attrCode}"></c:set>
							<c:set var="value" value=""></c:set>
							<c:set var="commNormalValueKey" value="${patient.orgFlow }_${elementCode }" />
							<c:set var="attrType" value="" />
							<!-- 单位 -->
							<c:if test="${empty value && sessionScope.projDescForm.attrMap[attr.attrCode].attrVarName == GlobalConstant.DEFAULT_ATTR_UNIT_VAR_NAME}">
								<c:set var="value" value="${sessionScope.projDescForm.elementStandardUnitMap[elementCode] }" />
							</c:if>
							<td>
								<!-- 文本 /日期-->
								<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumText.id or 
									empty sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId or 
									sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumDate.id }">
									<c:set var="dateClass" value="" />
									<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].attrVarName == GlobalConstant.DEFAULT_ATTR_UNIT_VAR_NAME}">
										<c:set var="attrType" value="${GlobalConstant.DEFAULT_ATTR_UNIT_VAR_NAME }" />
									</c:if>
									<span title="${pdfn:getCodeTitle(attr.attrCode,commAttrCode) }">
										<input type="text"
										name="${attr.attrCode }_1" attrCode="${ attr.attrCode }" isSerialSeq="${GlobalConstant.FLAG_Y}"
										value="${value }" codeValues="${pdfn:getCodeValues(attr.attrCode,commAttrCode) }"
										attrType="${attrType }"   style="width: 80px;padding-left: 5px;padding-right: 5px;"
										<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumDate.id }">
										  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
										  onblur="doSave('${GlobalConstant.FLAG_Y }','${attr.attrCode }',this);"
										  <c:set var="dateClass" value="dateClass"/>
										</c:if>
										class="validate[${pdfn:getCondition(attr.attrCode,commAttrCode,commNormalValueKey,patient.sexId) }] input serial_input"
										onchange="doSave('${GlobalConstant.FLAG_Y }','${attr.attrCode }',this);" />
									</span>
								</c:if>
								<!-- 单选/复选 -->
								<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumRadio.id or 
											  sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumCheckbox.id}">
									<c:set var="inputTypeId" value="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId }" />
									<c:forEach items="${ sessionScope.projDescForm.visitAttrCodeMap[commCodeFlow]}" var="code">
										<c:set var="edcAttrCode" value="${sessionScope.projDescForm.codeMap[code.codeFlow]}" />
										<input type="${inputTypeId }" name="${attr.attrCode }_1"
											attrCode="${ attr.attrCode }" isSerialSeq="${GlobalConstant.FLAG_Y}"
											value="${edcAttrCode.codeValue }"
											onchange="doSave('${GlobalConstant.FLAG_Y }','${attr.attrCode }',this);"
											id="${attr.attrCode }_${edcAttrCode.codeValue }_${valueMapRecord.key}"
											class="input" attrType="${attrType }" />
										<label for="${attr.attrCode }_${edcAttrCode.codeValue }_${valueMapRecord.key}">${edcAttrCode.codeName }&#12288;</label>
									</c:forEach>
								</c:if>
								<!-- 下拉-->
								<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].inputTypeId == attrInputTypeEnumSelect.id }">
									<select name="${attr.attrCode }_1" style="width: 80px;padding-left: 5px;padding-right: 5px;"
										attrCode="${ attr.attrCode }"	isSerialSeq="${GlobalConstant.FLAG_N}"
										onchange="doSave('${GlobalConstant.FLAG_N }','${attr.attrCode }',this);"
										class="input serial_select" attrType="${attrType }">
										<option value=""></option>
										<c:forEach items="${ sessionScope.projDescForm.visitAttrCodeMap[commCodeFlow]}" var="code">
											<c:set var="edcAttrCode" value="${sessionScope.projDescForm.codeMap[code.codeFlow]}" />
											<option value="${edcAttrCode.codeValue }">${edcAttrCode.codeName}</option>
										</c:forEach>
									</select>
								</c:if>
							</td>
						</c:forEach>
						<c:forEach  begin="1" end="${columnNum- fn:length(sessionScope.projDescForm.visitElementAttributeMap[commAttrCode])}" >
								<td ></td>
							</c:forEach>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>