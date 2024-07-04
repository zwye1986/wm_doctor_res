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
			"inputId": "disease_pDate",
			"label": "日期",
			"required":true,
			"inputType": "date"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_pName",
			"label": "病人姓名",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_mrNo",
			"label": "病历号",
			"required":true,
			"inputType": "text"
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_diagName",
			"inputType":${pdfn:toJsonString(inputType)},
			"label": "病种名称",
			"required":true,
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
			"inputId": "disease_diagType",
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
		},
		{
			"inputId": "disease_isCharge",
			"label": "是否主管",
			"required":true,
			"inputType": "radio",
			"options": [
				{
					"optionId": "1",
					"optionDesc": "是"
				},
				{
					"optionId": "0",
					"optionDesc": "否"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_isRescue",
			"label": "是否抢救",
			"required":true,
			"inputType": "radio",
			"options": [
				{
					"optionId": "1",
					"optionDesc": "是"
				},
				{
					"optionId": "0",
					"optionDesc": "否"
				}
			]
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId": "disease_treatStep",
			"label": "转归情况",
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
			"inputId":"disease_pDate",
			"value":${pdfn:toJsonString(formDataMap.disease_pDate)}
		},
		{
			"inputId":"disease_pName",
			"value":${pdfn:toJsonString(formDataMap.disease_pName)}
		},
		{
			"inputId":"disease_mrNo",
			"value":${pdfn:toJsonString(formDataMap.disease_mrNo)}
		},
		{
			"inputId":"disease_diagName",
			<c:if test="${!isOther}">
				"value": ${pdfn:toJsonString(req.itemName)}
			</c:if>
			<c:if test="${isOther}">
				"value":${pdfn:toJsonString(formDataMap.disease_diagName)}
			</c:if>
		},
		{
			"inputId":"disease_diagType",
			"value":${pdfn:toJsonString(empty formDataMap.disease_diagType_id? '1': formDataMap.disease_diagType_id)}
		},
		{
			"inputId":"disease_isCharge",
			"value":${pdfn:toJsonString(empty formDataMap.disease_isCharge_id? '1': formDataMap.disease_isCharge_id)}
		},
		{
			"inputId":"disease_isRescue",
			"value":${pdfn:toJsonString(empty formDataMap.disease_isRescue_id? '0': formDataMap.disease_isRescue_id)}
		},
		{
			"inputId":"disease_treatStep",
			"value":${pdfn:toJsonString(formDataMap.disease_treatStep)}
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