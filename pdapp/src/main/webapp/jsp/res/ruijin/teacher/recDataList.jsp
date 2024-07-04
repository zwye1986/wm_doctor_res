<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)},
	<c:if test="${resultId eq '200' }">
	,
	<c:set var="finishkey" value="${param.processFlow}${param.recType}Finished"></c:set>
	<c:set var="reqkey" value="${param.processFlow}${param.recType}ReqNum"></c:set>
	"finishNum":"${processPerMap[finishkey]}",
	"reqNum":"${processPerMap[reqkey]}",
	"notAuditNum": ${notAuditNum},
	"pageIndex": ${param.pageIndex},
	"pageSize": ${param.pageSize},
	"dataCount": ${dataCount},
 		<c:if test="${param.recType eq 'CaseRegistry'}">
	    "head":[
	    	{
	            "headId": "disease_pName",
	            "label": "疾病名称"
	        },
	        {
	            "headId": "mr_no",
	            "label": "病历号"
	        },
	        {
	            "headId": "operTime",
	            "label": "填写日期"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="mrData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(mrData['dataFlow'])},
		            "disease_pName": ${pdfn:toJsonString(mrData['disease_pName'])},
		            "mr_no": ${pdfn:toJsonString(mrData['mr_no'])},
		            "cataFlow": ${pdfn:toJsonString(mrData['cataFlow'])},
					"operTime":"${pdfn:transDate(mrData['operTime'])}",
					"auditId": <c:if test="${not empty mrData['auditId']}">"isAudit"</c:if><c:if test="${empty mrData['auditId']}">"notAudit"</c:if>,
					"auditName":<c:if test="${not empty mrData['auditId']}">"已审核"</c:if><c:if test="${empty mrData['auditId']}">"未审核"</c:if>,
					"deptFlow":${pdfn:toJsonString(mrData['deptFlow'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.recType eq 'DiseaseRegistry'}">
	    "head":[
			{
				"headId":"disease_diagName",
				"label":"病种名称"
			},
			{
				"headId":"disease_isCharge",
				"label":"是否主管"
			},
			{
				"headId":"disease_isRescue",
				"label":"是否抢救"
			},
	        {
	            "headId": "operTime",
	            "label": "填写日期"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="diseaseData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(diseaseData['dataFlow'])},
		            "disease_isCharge": ${pdfn:toJsonString(diseaseData['disease_isCharge'])},
		            "disease_diagName": ${pdfn:toJsonString(diseaseData['disease_diagName'])},
		            "disease_isCharge": ${pdfn:toJsonString(diseaseData['disease_isCharge'])},
		            "disease_isRescue": ${pdfn:toJsonString(diseaseData['disease_isRescue'])},
		            "disease_diagType": ${pdfn:toJsonString(diseaseData['disease_diagType'])},
					"cataFlow": ${pdfn:toJsonString(diseaseData['cataFlow'])},
		            "operTime": ${pdfn:toJsonString(diseaseData['operTime'])},
					"auditId": <c:if test="${not empty diseaseData['auditId']}">"isAudit"</c:if><c:if test="${empty diseaseData['auditId']}">"notAudit"</c:if>,
					"auditName":<c:if test="${not empty diseaseData['auditId']}">"已审核"</c:if><c:if test="${empty diseaseData['auditId']}">"未审核"</c:if>,
					"deptFlow":${pdfn:toJsonString(diseaseData['deptFlow'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.recType eq 'SkillRegistry'}">
	    "head":[
			{
				"headId":"skill_mrNo",
				"label":"病历号"
			},
			{
				"headId":"skill_operName",
				"label":"操作名称"
			},
			{
				"headId":"skill_result",
				"label":"是否成功"
			},
	        {
	            "headId": "operTime",
	            "label": "填写日期"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="skillData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(skillData['dataFlow'])},
		            "skill_operName": ${pdfn:toJsonString(skillData['skill_operName'])},
		            "skill_mrNo": ${pdfn:toJsonString(skillData['skill_mrNo'])},
		            "skill_result": ${pdfn:toJsonString(skillData['skill_result'])},
					"cataFlow": ${pdfn:toJsonString(skillData['cataFlow'])},
		            "operTime": ${pdfn:toJsonString(skillData['operTime'])},
					"auditId": <c:if test="${not empty skillData['auditId']}">"isAudit"</c:if><c:if test="${empty skillData['auditId']}">"notAudit"</c:if>,
					"auditName":<c:if test="${not empty skillData['auditId']}">"已审核"</c:if><c:if test="${empty skillData['auditId']}">"未审核"</c:if>,
					"deptFlow":${pdfn:toJsonString(skillData['deptFlow'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.recType eq 'OperationRegistry'}">
	    "head":[
			{
				"headId":"operation_operName",
				"label":"手术名称"
			},
			{
				"headId":"operation_mrNo",
				"label":"病历号"
			},
			{
				"headId":"operation_operRole",
				"label":"术中职务"
			},
	        {
	            "headId": "operTime",
	            "label": "填写日期"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="operationData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(operationData['dataFlow'])},
		            "operation_operRole": ${pdfn:toJsonString(operationData['operation_operRole'])},
		            "operation_operName": ${pdfn:toJsonString(operationData['operation_operName'])},
		            "operation_mrNo": ${pdfn:toJsonString(operationData['operation_mrNo'])},
		            "operation_operDate": ${pdfn:toJsonString(operationData['operation_operDate'])},
					"cataFlow": ${pdfn:toJsonString(operationData['cataFlow'])},
					"operTime": ${pdfn:toJsonString(operationData['operTime'])},
					"auditId": <c:if test="${not empty operationData['auditId']}">"isAudit"</c:if><c:if test="${empty operationData['auditId']}">"notAudit"</c:if>,
					"auditName":<c:if test="${not empty operationData['auditId']}">"已审核"</c:if><c:if test="${empty operationData['auditId']}">"未审核"</c:if>,
					"deptFlow":${pdfn:toJsonString(operationData['deptFlow'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.recType eq 'CampaignNoItemRegistry'}">
	    "head":[
	    	{
	            "headId": "activity_speaker",
	            "label": "主讲人"
	        },
	        {
	            "headId": "activity_way",
	            "label": "活动形式"
	        },
			{
				"headId":"activity_period",
				"label":"学时"
			},
	        {
	            "headId": "operTime",
	            "label": "填写日期"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="activityData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(activityData['dataFlow'])},
		            "activity_speaker": ${pdfn:toJsonString(activityData['activity_speaker'])},
		            "activity_way": ${pdfn:toJsonString(activityData['activity_way'])},
		            "activity_period": ${pdfn:toJsonString(activityData['activity_period'])},
					"cataFlow": ${pdfn:toJsonString(activityData['cataFlow'])},
		            "operTime": ${pdfn:toJsonString(activityData['operTime'])},
		            "auditId": <c:if test="${not empty activityData['auditId']}">"isAudit"</c:if><c:if test="${empty activityData['auditId']}">"notAudit"</c:if>,
					"auditName":<c:if test="${not empty activityData['auditId']}">"已审核"</c:if><c:if test="${empty activityData['auditId']}">"未审核"</c:if>,
					"deptFlow":${pdfn:toJsonString(activityData['deptFlow'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
</c:if>
}