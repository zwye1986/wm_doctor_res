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
			"inputId":"skillName",
			"inputType":${pdfn:toJsonString(inputType)},
			"label":"操作名称",
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
			"inputId":"dept",
			"inputType":"select",
			"label":"科室",
			 "options":[{
				"optionId":${pdfn:toJsonString(result.schDeptName)},
				"optionDesc":${pdfn:toJsonString(result.schDeptName)}
			}],
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"operateDate",
			"inputType":"date",
			"label":"时间",
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		},
		{
			"inputId":"status",
			"inputType":"checkbox",
			"label":"操作方式",
			"options":[{
				"optionId":"1",
				"optionDesc":"亲自"
			},{
				"optionId":"2",
				"optionDesc":"一助"
			},{
				"optionId":"3",
				"optionDesc":"二助"
			},{
				"optionId":"4",
				"optionDesc":"参观"
			}],
			"required":true
			<c:if test="${cannotOperSwitch}">
				,
				"readonly":true
			</c:if>
		}
	],
	"values":[
		{
			"inputId":"skillName",
			"value":${pdfn:toJsonString(formDataMap.skillName)}
		},
		{
			"inputId":"dept",
			"value":${pdfn:toJsonString(formDataMap.dept)}
		},
		{
			"inputId":"operateDate",
			"value":${pdfn:toJsonString(formDataMap.operateDate)}
		},
		{
			"inputId":"status",
			"values": [${pdfn:parseMultipleVal(formDataMap.status_id,',')}]
		}
	]