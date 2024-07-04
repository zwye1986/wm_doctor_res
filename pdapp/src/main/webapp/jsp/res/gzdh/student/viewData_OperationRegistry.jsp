<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
	<c:set var="inputType" value="select"/>
	<c:if test="${isOther}">
		<c:set var="inputType" value="text"/>
	</c:if>
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
			"inputId": "operation_operDate",
			"label": "手术日期",
			"required":true,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "operation_pName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "operation_mrNo",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "operation_operName",
			"inputType":${pdfn:toJsonString(inputType)},
			"label": "手术名称",
			"required":true
			<c:if test="${!isOther}">
				,
				"options": [
					{
						"optionId": ${pdfn:toJsonString(req.itemName)},
						"optionDesc": ${pdfn:toJsonString(req.itemName)}
					}
				]
			</c:if>
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "operation_operRole",
			"label": "术中职务",
			"required":true,
			"inputType": "radio",
			"options": [
				{
					"optionId": "0",
					"optionDesc": "术者"
				},
				{
					"optionId": "1",
					"optionDesc": "一助"
				},
				{
					"optionId": "2",
					"optionDesc": "二助"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "operation_memo",
			"label": "备注",
			"required":false,
			"inputType": "textarea"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"operation_operDate",
			"value":${pdfn:toJsonString(formDataMap.operation_operDate)}
		},
		{
			"inputId":"operation_pName",
			"value":${pdfn:toJsonString(formDataMap.operation_pName)}
		},
		{
			"inputId":"operation_mrNo",
			"value":${pdfn:toJsonString(formDataMap.operation_mrNo)}
		},
		{
			"inputId":"operation_operName",
			<c:if test="${!isOther}">
				"value": ${pdfn:toJsonString(req.itemName)}
			</c:if>
			<c:if test="${isOther}">
				"value":${pdfn:toJsonString(formDataMap.operation_operName)}
			</c:if>
		},
		{
			"inputId":"operation_operRole",
			"value":${pdfn:toJsonString(empty formDataMap.operation_operRole_id ?'1': formDataMap.operation_operRole_id)}
		},
		{
			"inputId":"operation_memo",
			"value":${pdfn:toJsonString(formDataMap.operation_memo)}
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