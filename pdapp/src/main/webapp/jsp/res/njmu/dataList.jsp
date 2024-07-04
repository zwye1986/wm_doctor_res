<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    "pageIndex": ${param.pageIndex},
    "pageSize": ${param.pageSize},
    "dataCount": ${dataCount},
	    <c:if test="${param.dataType eq 'mr'}">
	    "head":[
	    	{
	            "headId": "mr_pName",
	            "label": "病人姓名"
	        },
	        {
	            "headId": "mr_no",
	            "label": "病历号"
	        }
	    ],
	    "datas": [
	        <c:forEach items="${dataList}" var="mrData" varStatus="status">
		        {
		            "dataFlow": ${pdfn:toJsonString(mrData['dataFlow'])},
		            "mr_pName": ${pdfn:toJsonString(mrData['mrPName'])},
		            "mr_no": ${pdfn:toJsonString(mrData['mrNo'])},
		            "statusId": ${pdfn:toJsonString(mrData['statusId'])},
		            "statusDesc": ${pdfn:toJsonString(mrData['statusDesc'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.dataType eq 'disease'}">
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
		            "disease_pName": ${pdfn:toJsonString(diseaseData['diseasePName'])},
		            "disease_mrNo": ${pdfn:toJsonString(diseaseData['diseaseMrNo'])},
		            "disease_pDate": ${pdfn:toJsonString(diseaseData['diseasePDate'])},
		            "statusId": ${pdfn:toJsonString(diseaseData['statusId'])},
		            "statusDesc": ${pdfn:toJsonString(diseaseData['statusDesc'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.dataType eq 'skill'}">
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
		            "skill_pName": ${pdfn:toJsonString(skillData['skillPName'])},
		            "skill_mrNo": ${pdfn:toJsonString(skillData['skillMrNo'])},
		            "skill_operDate": ${pdfn:toJsonString(skillData['skillOperDate'])},
		            "statusId": ${pdfn:toJsonString(skillData['statusId'])},
		            "statusDesc": ${pdfn:toJsonString(skillData['statusDesc'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.dataType eq 'operation'}">
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
		            "operation_pName": ${pdfn:toJsonString(operationData['operationPName'])},
		            "operation_mrNo": ${pdfn:toJsonString(operationData['operationMrNo'])},
		            "operation_operDate": ${pdfn:toJsonString(operationData['operationOperDate'])},
		            "statusId": ${pdfn:toJsonString(operationData['statusId'])},
		            "statusDesc": ${pdfn:toJsonString(operationData['statusDesc'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.dataType eq 'activity'}">
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
		            "activity_speaker": ${pdfn:toJsonString(activityData['activitySpeaker'])},
		            "activity_way": ${pdfn:toJsonString(activityData['activityWay'])},
		            "activity_date": ${pdfn:toJsonString(activityData['activityDate'])},
		            "statusId": ${pdfn:toJsonString(activityData['statusId'])},
		            "statusDesc": ${pdfn:toJsonString(activityData['statusDesc'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
	    <c:if test="${param.dataType eq 'summary'}">
	    "head":[
	    	{
	            "headId": "summary_content",
	            "label": "出院小结"
	        },
	        {
	            "headId": "teacher_comment",
	            "label": "带教老师意见"
	        },
	        {
	            "headId": "deptHead_comment",
	            "label": "科主任意见"
	        }
	    ],
	    "datas": [
	        <c:if test="${dataList.size()>0}">
	        {
	        	"dataFlow":${pdfn:toJsonString(dataList[0]['dataFlow'])},
	        	"summary_content":${pdfn:toJsonString(dataList[0]['summary_content'])},
	        	"teacher_comment":${pdfn:toJsonString(dataList[0]['teacher_comment'])},
	        	"deptHead_comment":${pdfn:toJsonString(dataList[0]['deptHead_comment'])},
				"statusId": ${pdfn:toJsonString(dataList[0]['statusId'])},
                "statusDesc": ${pdfn:toJsonString(dataList[0]['statusDesc'])}
	        }
	   	 	</c:if>
	    ]
	    </c:if>
    </c:if>
}
