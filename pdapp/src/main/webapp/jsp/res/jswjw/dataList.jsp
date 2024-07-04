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
		            "statusId": "",
		            "dataFlow": ${pdfn:toJsonString(mrData['dataFlow'])},
		            "mr_pName": ${pdfn:toJsonString(mrData['mr_pName'])},
		            "mr_no": ${pdfn:toJsonString(mrData['mr_no'])}
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
					"statusId": "",
		            "dataFlow": ${pdfn:toJsonString(diseaseData['dataFlow'])},
		            "disease_pName": ${pdfn:toJsonString(diseaseData['disease_pName'])},
		            "disease_mrNo": ${pdfn:toJsonString(diseaseData['disease_mrNo'])},
		            "disease_pDate": ${pdfn:toJsonString(diseaseData['disease_pDate'])}
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
		            "statusId": "",
		            "dataFlow": ${pdfn:toJsonString(skillData['dataFlow'])},
		            "skill_pName": ${pdfn:toJsonString(skillData['skill_pName'])},
		            "skill_mrNo": ${pdfn:toJsonString(skillData['skill_mrNo'])},
		            "skill_operDate": ${pdfn:toJsonString(skillData['skill_operDate'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
		<c:if test="${param.dataType eq 'languageTeaching'}">
			"head":[
			{
			"headId": "research_content",
			"label": "培养内容"
			},
			{
			"headId": "research_num",
			"label": "学习数量"
			}
			],
			"datas": [
			<c:forEach items="${dataList}" var="lanData" varStatus="status">
				{
				"statusId": "",
				"dataFlow": ${pdfn:toJsonString(lanData['dataFlow'])},
				"research_content": ${pdfn:toJsonString(lanData['research_content'])},
				"research_num": ${pdfn:toJsonString(lanData['research_num'])}
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
		            "statusId": "",
		            "dataFlow": ${pdfn:toJsonString(operationData['dataFlow'])},
		            "operation_pName": ${pdfn:toJsonString(operationData['operation_pName'])},
		            "operation_mrNo": ${pdfn:toJsonString(operationData['operation_mrNo'])},
		            "operation_operDate": ${pdfn:toJsonString(operationData['operation_operDate'])}
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
					<c:if test="${empty activityData['isJoin']}">
						"statusId": "",
					</c:if>
					<c:if test="${not empty activityData['isJoin']}">
						"statusId": "Audited",
					</c:if>
		            "dataFlow": ${pdfn:toJsonString(activityData['dataFlow'])},
		            "activity_speaker": ${pdfn:toJsonString(activityData['activity_speaker'])},
		            "activity_way": ${pdfn:toJsonString(activityData['activity_way'])},
		            "activity_date": ${pdfn:toJsonString(activityData['activity_date'])}
		        }
		        <c:if test="${not status.last }">
		        ,
		        </c:if>
	        </c:forEach>
	    ]
	    </c:if>
    </c:if>
}
