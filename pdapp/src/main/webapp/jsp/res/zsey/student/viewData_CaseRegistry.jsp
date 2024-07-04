<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"action":{
		<c:if test="${sSwitch && !isAudit}">
			"save":"保存", 
			"del":"删除"
		</c:if>
		<c:if test="${tSwitch && !isAudit}">
			"save":"审核"
		</c:if>
	},
	"inputs":[
		{
			"inputId": "mr_pName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "mr_no",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_pName",
			"label": "疾病名称",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "mr_diagType",
			"label": "诊断类型",
			"required":true,
			"inputType": "select",
			"options": [
				{
					"optionId": "1",
					"optionDesc": "主要诊断"
				},
				{
					"optionId": "2",
					"optionDesc": "次要诊断"
				},
				{
					"optionId": "3",
					"optionDesc": "并行诊断"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"mr_pName",
			"value":${pdfn:toJsonString(formDataMap.mr_pName)}
		},
		{
			"inputId":"mr_no",
			"value":${pdfn:toJsonString(formDataMap.mr_no)}
		},
		{
			"inputId":"disease_pName",
			"value":${pdfn:toJsonString(formDataMap.disease_pName)}
		},
		{
			"inputId":"mr_diagType",
			"value":${pdfn:toJsonString(empty formDataMap.mr_diagType_id ?'1':formDataMap.mr_diagType_id)}
		}
	],
	"hos":[
		<c:forEach items="${hos}" var="rec" varStatus="status">
			{
				<c:if test="${rec.recTypeId eq 'CaseRegistry'}">
					"index":${pdfn:toJsonString(status.index+1)},
					"mr_pName":${pdfn:toJsonString(rec.mr_pName)},
					"mr_no":${pdfn:toJsonString(rec.mr_no)},
					"create_time":${pdfn:toJsonString(rec.createTime)}
				</c:if>
				<c:if test="${rec.recTypeId eq 'DiseaseRegistry'}">
					"index":${pdfn:toJsonString(status.index+1)},
					"mr_pName":${pdfn:toJsonString(rec.disease_pName)},
					"mr_no":${pdfn:toJsonString(rec.disease_mrNo)},
					"create_time":${pdfn:toJsonString(rec.createTime)}
				</c:if>
				<c:if test="${rec.recTypeId eq 'SkillRegistry'}">
					"index":${pdfn:toJsonString(status.index+1)},
					"mr_pName":${pdfn:toJsonString(rec.skill_pName)},
					"mr_no":${pdfn:toJsonString(rec.skill_mrNo)},
					"create_time":${pdfn:toJsonString(rec.createTime)}
				</c:if>
				<c:if test="${rec.recTypeId eq 'OperationRegistry'}">
					"index":${pdfn:toJsonString(status.index+1)},
					"mr_pName":${pdfn:toJsonString(rec.operation_pName)},
					"mr_no":${pdfn:toJsonString(rec.operation_mrNo)},
					"create_time":${pdfn:toJsonString(rec.createTime)}
				</c:if>
			}
			<c:if test="${!status.last}">
				,
			</c:if>
		</c:forEach>
	]