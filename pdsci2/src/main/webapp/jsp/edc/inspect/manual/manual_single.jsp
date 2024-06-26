
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
</head> 
<body>
<c:set  var="visitFlow" value="${param.visitFlow}"/>
<c:set  var="commAttrCode" value="${param.commAttrCode }"/>
<c:set  var="elementCode" value="${param.elementCode }"/>
<c:set var="edcPatientVisit" value="${patientSubmitVisitMap[visitFlow].edcPatientVisit }"/>
<c:set  var="columnCount" value="${param.columnCount }"/>
<!-- 设置宽度 -->
<c:set  var="attrWidth" value="120px"/>
<c:set  var="attrCodeWidth" value="150px"/>
<c:if test="${ empty columnCount || (columnCount == '1')}">
	<c:set  var="attrWidth" value=""/>
</c:if>
<table>
<c:forEach items="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode] }"  varStatus="status">
	<c:if test="${empty columnCount}">
		<c:set  var="columnCount" value="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode].size() }"/>
	</c:if>
	<c:if test="${status.index % columnCount eq 0}">
		<tr>
		 <c:forEach items="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode] }" var="attr" begin='${status.index}' end="${status.index+columnCount-1}">
			<c:set var="value" value="${pdfn:getVisitData(attr.attrCode,patientCrfDataMap[visitFlow][elementCode]['1'],edcPatientVisit.inputOperFlow,edcPatientVisit) }"></c:set>
			<c:set  var="emptyDataKey" value="${edcPatientVisit.recordFlow }_${param.moduleCode}_${elementCode }_1_${attr.attrCode}"/>
			<c:set var="recordFlow" value="${patientCrfDataMap[visitFlow][elementCode]['1'][attr.attrCode].recordFlow }"/>
			<c:if test="${empty recordFlow }"> 
				<c:set var="recordFlow" value="${emptyDataKey}"/>
			</c:if>
			<c:if test="${sessionScope.projDescForm.attrMap[attr.attrCode].isViewName eq GlobalConstant.FLAG_Y}">
				<td style="width:${attrWidth};text-align: right ;border: none;" id="${recordFlow }">
					${sessionScope.projDescForm.attrMap[attr.attrCode].attrName }：
				</td>
			</c:if>	
			<td style="width:${attrCodeWidth};text-align: left ;border: none;"  class="manualDiv">
				${pdfn:getAttrValue(sessionScope.projDescForm,attr.attrCode,value) }
			<c:set var="queryTitle" value="${sessionScope.projDescForm.visitMap[visitFlow].visitName}_${sessionScope.projDescForm.moduleMap[param.moduleCode].moduleName }_${sessionScope.projDescForm.elementMap[elementCode].elementName }
			_1_${sessionScope.projDescForm.attrMap[attr.attrCode].attrName}_${pdfn:getAttrValue(sessionScope.projDescForm,attr.attrCode,value) }"/>
			
			<div style="display:none;float:right;margin-top:5%;"> 
				<input type="checkbox" queryTitle="${queryTitle }"  name="recordFlow" value="${recordFlow}" onclick="addSelectedClass(this);"/>
			</div>
			</td>
		</c:forEach>
		</tr>
	</c:if>
</c:forEach>
</table>
 </body>