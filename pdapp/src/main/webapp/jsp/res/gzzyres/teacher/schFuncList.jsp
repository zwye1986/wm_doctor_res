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
            "img":"Education"
    	},
    	</c:if>
    	<c:if test='${doctor.roleFlow == 4}'>
    		{
	            "funcTypeId": "dataInput1N",
	            "funcTypeName": "数据列表",
				"funcFlow": "0002",
	            "funcName": "填报数据查看",
				"disabled": false,
				"disabledTip": "",
				"funcList":[

				    {
				        "funcFlow":"00021",
				        "funcName":"病种与操作"
				    },
				    {
				        "funcFlow":"00022",
				        "funcName":"病例与管床"
				    },
				    {
				        "funcFlow":"00023",
				        "funcName":"教学活动"
				    }
				],
				"img":"Education"
	        },
		</c:if>
		<c:if test='${doctor.roleFlow != 4}'>
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
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
			"funcFlow": "0003",
            "funcName": "过程评价",
			"disabled": false,
			"disabledTip": "",
			"option":{"save":true},
            "img":"Education"
        },
        <%--{--%>
            <%--"funcTypeId": "dataInput11",--%>
            <%--"funcTypeName": "数据输入",--%>
			<%--"funcFlow": "0005",--%>
            <%--"funcName": "DOPS评估表",--%>
			<%--"disabled": false,--%>
			<%--"disabledTip": "",--%>
			<%--<c:if test="${empty outDops or outDops['DOPS_State'] eq '0' }">--%>
			<%--"option":{"save":true},--%>
			<%--</c:if>--%>
            <%--"img":"Education"--%>
        <%--},--%>
        {
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
			"funcFlow": "0006",
            "funcName": "Mini-CEX评估表",
			"disabled": false,
			"disabledTip": "",
			<c:if test="${empty outMiniCex or outMiniCex['Mini_State'] eq '0' }">
			"option":{"save":true},
			</c:if>
            "img":"Education"
        },
		{
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
            "funcFlow": "0004",
            "funcName": "日常考核",
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
            "img":"Education"
        },
		{
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据输入",
			"funcFlow": "0007",
            "funcName": "活动确认",
            "disabled": false,
			"disabledTip": "",
			"option":{"save":true},
            "img":"Study"
        }
    	</c:if>
    ]
    </c:if>
}
