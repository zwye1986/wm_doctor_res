<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "dataType":${pdfn:toJsonString(param.dataType)},
    "inputs": [
    	<c:if test="${param.dataType eq 'mr'}">
    		<jsp:include page="inputMr.jsp"></jsp:include>
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
            "inputId": "mr_diagType",
            "value": ${pdfn:toJsonString(resultData.mr_diagType)}
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
            "value": ${pdfn:toJsonString(resultData.disease_diagName)}
        },
        {
            "inputId": "disease_diagType",
            "value": ${pdfn:toJsonString(resultData.disease_diagType)}
        },
        {
            "inputId": "disease_isCharge",
            "value": ${pdfn:toJsonString(resultData.disease_isCharge)}
        },
        {
            "inputId": "disease_isRescue",
            "value": ${pdfn:toJsonString(resultData.disease_isRescue)}
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
            "value": ${pdfn:toJsonString(resultData.skill_operName)}
        },
        {
            "inputId": "skill_result",
            "value": ${pdfn:toJsonString(resultData.skill_result)}
        },
        {
            "inputId": "skill_memo",
            "value": ${pdfn:toJsonString(resultData.skill_memo)}
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
            "value": ${pdfn:toJsonString(resultData.operation_operName)}
        },
        {
            "inputId": "operation_operRole",
            "value": ${pdfn:toJsonString(resultData.operation_operRole)}
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
            "inputId": "activity_way",
            "value": ${pdfn:toJsonString(resultData.activity_way)}
        },
        {
            "inputId": "activity_period",
            "value": ${pdfn:toJsonString(resultData.activity_period)}
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
