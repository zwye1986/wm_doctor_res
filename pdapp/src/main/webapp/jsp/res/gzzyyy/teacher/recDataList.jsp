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
				"headId":"diseaseName",
				"label":"疾病名称"
			},
			{
				"headId":"hospitalNumbers",
				"label":"住院号"
			},
			{
				"headId":"treatmentWay",
				"label": "治疗方法"
			}
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="mrData" varStatus="status">
		        {
					"dataFlow":${pdfn:toJsonString(mrData['dataFlow'])},
					"diseaseName":${pdfn:toJsonString(mrData['diseaseName'])},
					"hospitalNumbers":${pdfn:toJsonString(mrData['hospitalNumbers'])},
					"treatmentWay":${pdfn:toJsonString(mrData['treatmentWay'])},
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
 		<c:if test="${param.recType eq 'CaseRegistryHandwriting'}">
	    "head":[
	    	{
				"headId":"diseaseName",
				"label":"疾病名称"
			},
			{
				"headId":"hospitalNumbers",
				"label":"住院号"
			},
			{
				"headId":"treatmentWay",
				"label": "治疗方法"
			}
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="mrData" varStatus="status">
		        {
					"dataFlow":${pdfn:toJsonString(mrData['dataFlow'])},
					"diseaseName":${pdfn:toJsonString(mrData['diseaseName'])},
					"hospitalNumbers":${pdfn:toJsonString(mrData['hospitalNumbers'])},
					"treatmentWay":${pdfn:toJsonString(mrData['treatmentWay'])},
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
 		<c:if test="${param.recType eq 'EmergencyCase'}">
	    "head":[
			{
				"headId":"diseaseName",
				"label":"疾病名称"
			},
			{
				"headId":"cases",
				"label":"例次"
			},
			{
				"headId":"date",
				"label":"日期"
			}
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="mrData" varStatus="status">
		        {
					"dataFlow":${pdfn:toJsonString(mrData['dataFlow'])},
					"diseaseName":${pdfn:toJsonString(mrData['diseaseName'])},
					"cases":${pdfn:toJsonString(mrData['cases'])},
					"date":${pdfn:toJsonString(mrData['date'])},
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
					"headId":"patientName",
					"label":"病人姓名"
				},
				{
					"headId":"caseNo",
					"label":"病历号"
				},
				{
					"headId":"inHosDate",
					"label":"入院日期"
				}
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="diseaseData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(diseaseData['dataFlow'])},
		            "patientName": ${pdfn:toJsonString(diseaseData['patientName'])},
		            "caseNo": ${pdfn:toJsonString(diseaseData['caseNo'])},
		            "inHosDate": ${pdfn:toJsonString(diseaseData['inHosDate'])},
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
	            "headId": "skill_pName",
	            "label": "病人姓名"
	        },
	        {
	            "headId": "skill_mrNo",
	            "label": "病历号"
	        },
		        {
	            "headId": "skill_operDate",
	            "label": "操作日期"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="skillData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(skillData['dataFlow'])},
		            "skill_pName": ${pdfn:toJsonString(skillData['skill_pName'])},
		            "skill_mrNo": ${pdfn:toJsonString(skillData['skill_mrNo'])},
		            "skill_operDate": ${pdfn:toJsonString(skillData['skill_operDate'])},
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
	            "headId": "operation_pName",
	            "label": "病人姓名"
	        },
	        {
	            "headId": "operation_mrNo",
	            "label": "病历号"
	        },
	        {
	            "headId": "operation_operDate",
	            "label": "操作日期"
			}
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="operationData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(operationData['dataFlow'])},
		            "operation_pName": ${pdfn:toJsonString(operationData['operation_pName'])},
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
	    <c:if test="${param.recType eq 'SkillAndOperationRegistry'}">
	    "head":[
	    	{
				"headId":"skillName",
				"label":"技术操作及手术名称"
			},
			{
				"headId":"date",
				"label":"日期"
			},
			{
				"headId":"status",
				"label":"住院号"
			}
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="operationData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(operationData['dataFlow'])},
		            "skillName": ${pdfn:toJsonString(operationData['skillName'])},
		            "date": ${pdfn:toJsonString(operationData['date'])},
		            "status": ${pdfn:toJsonString(operationData['status'])},
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
	    <c:if test="${param.recType eq 'HospitalizationLog'}">
	    "head":[
	    	{
				"headId":"diseaseName",
				"label":"疾病名称"
			},
			{
				"headId":"hospitalNumbers",
				"label":"住院号"
			},
			{
				"headId":"inHosDate",
				"label":"入院时间"
			}
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="operationData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(operationData['dataFlow'])},
		            "diseaseName": ${pdfn:toJsonString(operationData['diseaseName'])},
		            "hospitalNumbers": ${pdfn:toJsonString(operationData['hospitalNumbers'])},
		            "inHosDate": ${pdfn:toJsonString(operationData['inHosDate'])},
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
				"headId":"title",
				"label":"题目"
			},
			{
				"headId":"articleTitle",
				"label":"文章题目"
			},
			{
				"headId":"teaching",
				"label":"教学"
			}
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="activityData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(activityData['dataFlow'])},
		            "title": ${pdfn:toJsonString(activityData['title'])},
		            "articleTitle": ${pdfn:toJsonString(activityData['articleTitle'])},
		            "teaching": ${pdfn:toJsonString(activityData['teaching'])},
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