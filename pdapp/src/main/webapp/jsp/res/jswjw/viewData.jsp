<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "dataType":${pdfn:toJsonString(param.dataType)},
    "inputs": [
   	 <c:if test="${fn:length(resultList)>0}">
    	{
            "inputId": "resultFlow",
            "label": "轮转科室",
            "required":true,
            "inputType": "select",
            "options": [

                     {
                         "optionId": "",
                         "optionDesc":"请选择..."
                     }
                     <c:if test="${not empty resultList }">
                         ,
                     </c:if>
	                <c:forEach items="${resultList}" var="result" varStatus="status">
						{
		                    "optionId": ${pdfn:toJsonString(result.resultFlow)},
		                    "optionDesc":"${result.schDeptName}(${result.schStartDate}~${result.schEndDate})"
		                }
						<c:if test="${not status.last }">
							,
						</c:if>
					</c:forEach>
	            ]
        },
       </c:if>
     	<c:if test="${param.dataType eq 'mr'}">
    		<jsp:include page="inputMr.jsp">
                <jsp:param name="resultData" value="${resultData}"></jsp:param>
                <jsp:param name="isChargeOrg" value="${isChargeOrg}"></jsp:param>
            </jsp:include>
    	</c:if>
        <c:if test="${param.dataType eq 'languageTeaching'}">
            <jsp:include page="inputLan.jsp">
                <jsp:param name="resultData" value="${resultData}"></jsp:param>
            </jsp:include>
        </c:if>
    	<c:if test="${param.dataType eq 'disease'}">
    		<jsp:include page="inputDisease.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'skill'}">
    		<jsp:include page="inputSkill.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'operation'}">
    		<jsp:include page="inputOperation.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'activity'}">
    		<jsp:include page="inputActivity.jsp"></jsp:include>
    	</c:if>
    	<c:if test="${param.dataType eq 'summary'}">
    		<jsp:include page="inputSummary.jsp"></jsp:include>
    	</c:if>
    ],
    "values": [
   		<c:if test="${fn:length(resultList)>0}">
	   	    {
	            "inputId": "resultFlow",
	            "value": ${pdfn:toJsonString(resultData.resultFlow)}
	        },
        </c:if>
    	<c:if test="${param.dataType eq 'mr' and not empty resultData}">
    	{
            "inputId": "mr_pName",
            "value": ${pdfn:toJsonString(resultData.mr_pName)}
        },
        {
            "inputId": "mr_no",
            "value": ${pdfn:toJsonString(resultData.mr_no)}
        },
        {
            "inputId": "disease_pName",
            "value": ${pdfn:toJsonString(resultData.disease_pName)}
        },
        {
            "inputId": "mr_diagType",
            "value": ${pdfn:toJsonString(resultData.mr_diagType_id)}
        }
    	</c:if>
        <c:if test="${param.dataType eq 'languageTeaching' and not empty resultData}">
            {
            "inputId": "research_content",
            "value": ${pdfn:toJsonString(resultData.research_content)}
            },
            {
            "inputId": "research_num",
            "value": ${pdfn:toJsonString(resultData.research_num)}
            }
        </c:if>
    	<c:if test="${param.dataType eq 'disease' and not empty resultData}">
    	{
            "inputId": "disease_pDate",
            "value": ${pdfn:toJsonString(resultData.disease_pDate)}
        },
    	{
            "inputId": "disease_pName",
            "value": ${pdfn:toJsonString(resultData.disease_pName)}
        },
        {
            "inputId": "disease_mrNo",
            "value": ${pdfn:toJsonString(resultData.disease_mrNo)}
        },
        {
            "inputId": "disease_diagName",
            "value": ${pdfn:toJsonString(empty resultData.disease_diagName_id ? resultData.disease_diagName : resultData.disease_diagName_id)}
        },
        {
            "inputId": "disease_diagType",
            "value": ${pdfn:toJsonString(resultData.disease_diagType_id)}
        },
        {
            "inputId": "disease_isCharge",
            "value": ${pdfn:toJsonString(resultData.disease_isCharge_id)}
        },
        {
            "inputId": "disease_isRescue",
            "value": ${pdfn:toJsonString(resultData.disease_isRescue_id)}
        },
        {
            "inputId": "disease_caseType",
            "value": ${pdfn:toJsonString(resultData.disease_caseType_id)}
        },
        {
            "inputId": "disease_treatStep",
            "value": ${pdfn:toJsonString(resultData.disease_treatStep)}
        }
    	
    	</c:if>
    	<c:if test="${param.dataType eq 'skill' and not empty resultData}">
    	{
            "inputId": "skill_operDate",
            "value": ${pdfn:toJsonString(resultData.skill_operDate)}
        },
    	{
            "inputId": "skill_pName",
            "value": ${pdfn:toJsonString(resultData.skill_pName)}
        },
        {
            "inputId": "skill_mrNo",
            "value": ${pdfn:toJsonString(resultData.skill_mrNo)}
        },
        {
            "inputId": "skill_operName",
            "value": ${pdfn:toJsonString(empty resultData.skill_operName_id ? resultData.skill_operName : resultData.skill_operName_id)}
        },
        {
            "inputId": "skill_result",
            "value": ${pdfn:toJsonString(resultData.skill_result_id)}
        },
        {
            "inputId": "fail_reason",
            "value": ${pdfn:toJsonString(resultData.fail_reason)}
        }
    	</c:if>
    	<c:if test="${param.dataType eq 'operation' and not empty resultData}">
    	{
            "inputId": "operation_operDate",
            "value": ${pdfn:toJsonString(resultData.operation_operDate)}
        },
    	{
            "inputId": "operation_pName",
            "value": ${pdfn:toJsonString(resultData.operation_pName)}
        },
        {
            "inputId": "operation_mrNo",
            "value": ${pdfn:toJsonString(resultData.operation_mrNo)}
        },
        {
            "inputId": "operation_operName",
            "value": ${pdfn:toJsonString(empty resultData.operation_operName_id ? resultData.operation_operName : resultData.operation_operName_id)}
        },
        {
            "inputId": "operation_operRole",
            "value": ${pdfn:toJsonString(resultData.operation_operRole_id)}
        },
        {
            "inputId": "operation_memo",
            "value": ${pdfn:toJsonString(resultData.operation_memo)}
        }
    	</c:if>
    	<c:if test="${param.dataType eq 'activity' and not empty resultData}">
    	{
            "inputId": "activity_date",
            "value": ${pdfn:toJsonString(resultData.activity_date)}
        },
    	{
            "inputId": "activity_content",
            "value": ${pdfn:toJsonString(resultData.activity_content)}
        },
        {
            "inputId": "activity_address",
            "value": ${pdfn:toJsonString(resultData.activity_address)}
        },
        {
            "inputId": "activity_way",
            "value": ${pdfn:toJsonString(resultData.activity_way_id)}
        },
        {
            "inputId": "activity_period",
            "value": ${pdfn:toJsonString(resultData.activity_period_id)}
        },
        {
            "inputId": "activity_speaker",
            "value": ${pdfn:toJsonString(resultData.activity_speaker)}
        }
    	</c:if>
    	<c:if test="${param.dataType eq 'summary' and not empty resultData}">
    	{
            "inputId": "summary_content",
            "value": ${pdfn:toJsonString(resultData.summary_content)}
        }
    	</c:if>
    ]
    </c:if>
}
