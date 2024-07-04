<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
	"temp":"${doctor.roleFlow}",
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
     <c:if test="${resultId eq '200' }">
    ,
    "funcList":[
    	<c:if test="${empty deptStatus or deptStatus['DeptStatusId'] eq '0'}">
    	{
    		"funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
			"funcFlow": "0001",
            "funcName": "入科确认",
			"disabled": false,
			"disabledTip": "",
			"option":{"save":true},
            "img":"dataAction"
    	},
    	</c:if>
    	<c:if test="${deptStatus['DeptStatusId'] eq '1'}">
		{
			"funcTypeId": "dataAudit",
			"funcTypeName": "数据列表",
			"funcFlow": "dataAudit",
			"funcName": "数据审核",
			"disabled": false,
			"disabledTip": "",
			"img":"dataAudit"
		},
		{
			"funcTypeId": "skillExam",
			"funcTypeName": "技能考核",
			"funcFlow": "skillExam",
			"funcName": "技能考核",
			"disabled": false,
			"disabledTip": "",
			"img":"skillExam"
		},
		<c:if test="${isNeed eq 'Y'}">
		{
			"funcTypeId": "receivePatient",
			"funcTypeName": "接诊病人",
			"funcFlow": "receivePatient",
			"funcName": "接诊病人",
			"disabled": false,
			"disabledTip": "",
			"img":"receivePatient"
		},
		</c:if>
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
			"funcFlow": "0003",
            "funcName": "过程评价",
			"disabled": false,
			"disabledTip": "",
			"option":{"save":true},
            "img":"resEval"
        },
		{
			"funcTypeId": "theoryScore",
			"funcTypeName": "理论成绩",
			"funcFlow": "theoryScore",
			"funcName": "理论成绩",
			"disabled": false,
			"disabledTip": "",
			"img":"theoryScore"
		},
		{
            "funcTypeId": "AfterEvaluation",
            "funcTypeName": "数据输入",
            "funcFlow": "AfterEvaluation",
            "funcName": "出科考核",
            <c:if test='${empty outSecBrief}'>
			"disabled": true,
			"disabledTip": "该学员尚未提交出科小结",
			</c:if>
            <c:if test='${not empty outSecBrief}'>
			"disabled": false,
			"disabledTip": "",
			</c:if>
            <c:if test="${outSecBrief['CheckStatus'] eq '0' }">
			"option":{"save":true},
			</c:if>
            "img":"after"
        },
		{
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据输入",
			"funcFlow": "0007",
            "funcName": "活动确认",
            "disabled": false,
			"disabledTip": "",
			"option":{"save":true},
            "img":"activity"
        }
    	</c:if>
    ]
    </c:if>
}
