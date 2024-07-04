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
	            "headId": "mr_pName",
	            "label": "病人姓名"
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
		            "mr_pName": ${pdfn:toJsonString(mrData['mr_pName'])},
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
	            "headId": "disease_pName",
	            "label": "病人姓名"
	        },
	        {
	            "headId": "disease_mrNo",
	            "label": "病历号"
	        },
	        {
	            "headId": "disease_pDate",
	            "label": "日期"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="diseaseData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(diseaseData['dataFlow'])},
		            "disease_pName": ${pdfn:toJsonString(diseaseData['disease_pName'])},
		            "disease_mrNo": ${pdfn:toJsonString(diseaseData['disease_mrNo'])},
		            "disease_pDate": ${pdfn:toJsonString(diseaseData['disease_pDate'])},
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
	    <c:if test="${param.recType eq 'CampaignRegistry'}">
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
	            "headId": "activity_date",
	            "label": "日期"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="activityData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(activityData['dataFlow'])},
		            "activity_speaker": ${pdfn:toJsonString(activityData['activity_speaker'])},
		            "activity_way": ${pdfn:toJsonString(activityData['activity_way'])},
		            "activity_date": ${pdfn:toJsonString(activityData['activity_date'])},
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
	    <c:if test="${param.recType eq 'Grave'}">
	    "head":[
	    	{
	            "headId": "name",
	            "label": "姓名"
	        },
	        {
	            "headId": "caseNo",
	            "label": "病历号"
	        },
	        {
	            "headId": "time",
	            "label": "操作时间"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="graveData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(graveData['dataFlow'])},
		            "name": ${pdfn:toJsonString(graveData['name'])},
		            "caseNo": ${pdfn:toJsonString(graveData['caseNo'])},
		            "time": ${pdfn:toJsonString(graveData['time'])},
					"cataFlow": ${pdfn:toJsonString(graveData['cataFlow'])},
		            "operTime": ${pdfn:toJsonString(graveData['operTime'])},
		            "auditId": <c:if test="${not empty graveData['auditId']}">"isAudit"</c:if><c:if test="${empty graveData['auditId']}">"notAudit"</c:if>,
					"auditName":<c:if test="${not empty graveData['auditId']}">"已审核"</c:if><c:if test="${empty graveData['auditId']}">"未审核"</c:if>,
					"deptFlow":${pdfn:toJsonString(graveData['deptFlow'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.recType eq 'CaseRecord'}">
	    "head":[
	    	{
	            "headId": "caseType",
	            "label": "病例类型"
	        },
	        {
	            "headId": "diseaseName",
	            "label": "疾病名称"
	        },
	        {
	            "headId": "caseNumber",
	            "label": "病历号"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="caseRecordData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(caseRecordData['dataFlow'])},
		            "caseType": ${pdfn:toJsonString(caseRecordData['case'])},
		            "diseaseName": ${pdfn:toJsonString(caseRecordData['diseaseName'])},
		            "caseNumber": ${pdfn:toJsonString(caseRecordData['caseNumber'])},
					"cataFlow": ${pdfn:toJsonString(caseRecordData['cataFlow'])},
		            "operTime": ${pdfn:toJsonString(caseRecordData['operTime'])},
		            "auditId": <c:if test="${not empty caseRecordData['auditId']}">"isAudit"</c:if><c:if test="${empty caseRecordData['auditId']}">"notAudit"</c:if>,
					"auditName":<c:if test="${not empty caseRecordData['auditId']}">"已审核"</c:if><c:if test="${empty caseRecordData['auditId']}">"未审核"</c:if>,
					"deptFlow":${pdfn:toJsonString(caseRecordData['deptFlow'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
</c:if>
}