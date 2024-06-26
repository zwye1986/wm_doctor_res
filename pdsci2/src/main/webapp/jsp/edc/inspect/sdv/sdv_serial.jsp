
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
</head>
<body>
<c:set  var="patientFlow" value="${param.patientFlow }"/>
<c:set  var="visitFlow" value="${param.visitFlow }"/>
<c:set  var="commAttrCode" value="${param.commAttrCode }"/>
<c:set  var="elementCode" value="${param.elementCode }"/>
 <table width="100%" cellspacing="0" cellpadding="0" class="bs_tb">	
         <tr>
         		<th width="50px">序号</th> 
         		<c:forEach items="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode] }" var="attr">
            	<th >${sessionScope.projDescForm.attrMap[attr.attrCode].attrName }</th>
              </c:forEach>
         </tr>
          <tbody id="${elementCode }_TBODY">
        <c:set var="seqValueMap" value="${elementSerialSeqValueMap[elementCode]}"></c:set>
        <c:choose>
           	<c:when test="${fn:length(seqValueMap)>0}">
           <c:forEach items="${seqValueMap }" var="valueMapRecord">
	           <tr >
	           		<td><div>${valueMapRecord.key }</div></td>
					<c:forEach items="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode] }" var="attr">
					<c:set var="dataItem" value="${sessionScope.projDescForm.elementMap[elementCode].elementName }.${sessionScope.projDescForm.attrMap[attr.attrCode].attrName }"/>
					<c:set var="emptyDataKey" value="${patientVisitForm.edcPatientVisit.recordFlow }_${param.moduleCode}_${elementCode }_${valueMapRecord.key }_${attr.attrCode}"/>
					<c:set var="value" value="${pdfn:getVisitData(attr.attrCode,valueMapRecord.value,patientVisitForm.edcPatientVisit.inputOperFlow ,patientVisitForm.edcPatientVisit) }"></c:set>
					
					<!-- 转换代码 -->
					<c:set var="value" value="${pdfn:getAttrValue(sessionScope.projDescForm,attr.attrCode,value) }"/>
					<c:if test="${empty value }">
							<c:set var="value" value="&#12288;"/>
					</c:if>
					
					<c:set var="recordFlow" value="${valueMapRecord.value[attr.attrCode].recordFlow }"/>
					<c:if test="${empty recordFlow }"> 
						<c:set var="recordFlow" value="${emptyDataKey}"/>
					</c:if>
					<c:set  var="attrCodeColor" value=""/>
					<c:set  var="key" value="${patientFlow}_${visitFlow}_${ attr.attrCode}_${valueMapRecord.key }"/>
					<c:if test="${attrSDVQueryMap[key] }">
					<c:set  var="attrCodeColor" value="pink"/>
					</c:if>
					<td  id="${key}" bgcolor="${attrCodeColor}"  class="sdvDiv ">
	 					${value }
	 					<div  style="display:none;float:right;text-align:center;margin:5% 5%;" > 
							<img  src="<s:url value='/css/skin/${skinPath}/images/sdv.png'/>"  style="cursor: pointer; vertical-align:middle;" onclick="doSdv('${recordFlow }','${emptyDataKey }','${key }','${dataItem }');"/>
						</div>
					</td>				
					</c:forEach>	           	
	           </tr>
	           </c:forEach>
	           </c:when>
	           <c:otherwise><!-- 默认存在一条空记录 -->
	           	<tr>
	           		<td><div>1</div></td>
					<c:forEach items="${sessionScope.projDescForm.visitElementAttributeMap[commAttrCode] }" var="attr">
					<c:set var="dataItem" value="${sessionScope.projDescForm.elementMap[elementCode].elementName }.${sessionScope.projDescForm.attrMap[attr.attrCode].attrName }"/>
					<c:set  var="emptyDataKey" value="${patientVisitForm.edcPatientVisit.recordFlow }_${param.moduleCode}_${elementCode }_1_${attr.attrCode}"/>
					<c:set var="value" value="&#12288;"></c:set>
					
					<c:set var="recordFlow" value="${valueMapRecord.value[attr.attrCode].recordFlow }"/>
					<c:if test="${empty recordFlow }"> 
						<c:set var="recordFlow" value="${emptyDataKey}"/>
					</c:if>
					<c:set  var="attrCodeColor" value=""/>
					<c:set  var="key" value="${patientFlow}_${visitFlow}_${ attr.attrCode}_1"/>
					<c:if test="${attrSDVQueryMap[key] }">
					<c:set  var="attrCodeColor" value="pink"/>
					</c:if>
					<td  id="${key}" bgcolor="${attrCodeColor}"  class="sdvDiv ">
	 					${value }
	 					<div  style="display:none;float:right;text-align:center;margin:5% 5%;" > 
							<img  src="<s:url value='/css/skin/${skinPath}/images/sdv.png'/>"  style="cursor: pointer; vertical-align:middle;" onclick="doSdv('${recordFlow }','${emptyDataKey }','${key }','${dataItem }');"/>
						</div>
					</td>				
					</c:forEach>	           	
	           </tr>
	           </c:otherwise>
	           </c:choose>
         </tbody>
 </table>
 </body>