<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"resultId": ${resultId},
	"resultType": ${pdfn:toJsonString(resultType)}
	<c:if test="${resultId eq '200' }">
,
	"action":{
		<c:if test="${empty eval}">
			"save":"提交"
		</c:if>
	},
	"inputs":[
		<c:if test="${item.itemTypeId ne 'JXCFAP'}">
			{
				"inputId":"planTime",
				"inputType":"text",
				"label":"时间",
				"readonly":true,
				"required":false
			},
			{
				"inputId":"userName",
				"inputType":"text",
				"label":"姓名",
				"readonly":true,
				"required":false
			},
			{
				"inputId":"orgName",
				"inputType":"text",
				"label":"基地名称",
				"readonly":true,
				"required":false
			},
			{
				"inputId":"name",
				"inputType":"text",
				"label":"课程名称",
				"readonly":${not empty eval},
				"required":true
			},
			{
				"inputId":"teaName",
				"inputType":"text",
				"label":"授课教师",
				"readonly":true,
				"required":false
			},
			{
				"inputId":"teaTitle",
				"inputType":"text",
				"label":"职称",
				"readonly":true,
				"required":false
			},
			{
				"inputId":"deptName",
				"inputType":"text",
				"label":"科室",
				"readonly":true,
				"required":false
			},
		</c:if>
		<c:forEach items="${titleFormList}" var="assess" varStatus="assessStatus">
			{
				"inputId":${pdfn:toJsonString(assess.id)},
				"label":${pdfn:toJsonString(assess.name)},
				"tip":${pdfn:toJsonString(assess.name)},
				"inputType":"title"
			}
			<c:set var="itemList" value="${assess.itemList}"/>
			<c:if test="${empty itemList}">
				<c:if test="${!assessStatus.last}">
					,
				</c:if>
			</c:if>
			<c:if test="${!empty itemList}">
				,
				<c:forEach items="${itemList}" var="item" varStatus="itemStatus">
					<c:set var="scoreInputId" value="${item.id}_score"/>
					<c:set var="lostReasonInputId" value="${item.id}_lostReason"/>
					
					{
						"inputId":${pdfn:toJsonString(item.id)},
						"label":${pdfn:toJsonString(item.name)},
						"tip":${pdfn:toJsonString(item.name)},
						"inputType":"title"
					},
					{
						"inputId":${pdfn:toJsonString(scoreInputId)},
						"label":"分数",
						"inputType":"text",
						"required":true,
						"placeholder": "0~${item.score}",
						"readonly":${not empty eval},
						"verify": {
				            "max": ${pdfn:toJsonString(item.score)},
				            "type": "int"
			            }
					}
					<c:if test="${!itemStatus.last}">
						,
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
		<c:if test="${item.itemTypeId eq 'JXCFAP'}">
		,
		{
			"inputId":"shouHuo",
			"label":"查房后，收获",
			"inputType":"select",
			"required":true,
			"readonly":${not empty eval},
			"options":[
				{
					"optionId": "很大",
					"optionDesc": "很大"
				},
				{
					"optionId": "有收获",
					"optionDesc": "有收获"
				},
				{
					"optionId": "有点收获",
					"optionDesc": "有点收获"
				},
				{
					"optionId": "收获不大",
					"optionDesc": "收获不大"
				}
			]
		}
		</c:if>
		,
		{
			"inputId":"evalContent",
			"label":"意见或建议",
			"readonly":${not empty eval},
			"inputType":"textarea",
			"required":true
		}
	]
	,
	"values":[
		<c:if test="${item.itemTypeId ne 'JXCFAP'}">
			{
				"inputId":"planTime",
				"value":${pdfn:toJsonString(item.planTime)}
			},
			{
				"inputId":"userName",
				"value":${pdfn:toJsonString(user.userName)}
			},
			{
				"inputId":"orgName",
				"value":${pdfn:toJsonString(doctor.orgName)}
			},
			{
				"inputId":"name",
				"value":${pdfn:toJsonString(gradeMap['name'])}
			},
			{
				"inputId":"teaName",
				"value":${pdfn:toJsonString(joinUser.userName)}
			},
			{
				"inputId":"teaTitle",
				"value":${pdfn:toJsonString(joinUser.postName)}
			},
			{
				"inputId":"deptName",
				"value":${pdfn:toJsonString(joinUser.deptName)}
			},
		</c:if>
		<c:forEach items="${titleFormList}" var="assess" varStatus="assessStatus">
			<c:set var="itemList" value="${assess.itemList}"/>
			<c:if test="${!empty itemList}">
				<c:forEach items="${itemList}" var="item" varStatus="itemStatus">
					{

						"inputId":"${item.id}_score",
						"value":${pdfn:toJsonString(gradeMap[item.id]['score'])}
					},
				</c:forEach>
			</c:if>
		</c:forEach>
		<c:if test="${item.itemTypeId eq 'JXCFAP'}">
			{
				"inputId":"shouHuo",
				"value":${pdfn:toJsonString(gradeMap['shouHuo'])}
			},
		</c:if>
			{
				"inputId":"evalContent",
				"value":${pdfn:toJsonString(eval.evalContent)}
			}
	]
	</c:if>
}