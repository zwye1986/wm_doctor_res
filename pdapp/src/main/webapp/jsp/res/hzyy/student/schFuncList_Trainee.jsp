<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
	"funcList":[
    	{
    		"funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
			"funcFlow": "0001",
            "funcName": "入科教育",
			"disabled": false,
			"disabledTip": "",
			<c:if test='${empty deptEdu}'>
			"option":{"save":true},
			</c:if>
			"img":"Education"
    	},
    	{
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
			"funcFlow": "0002",
            "funcName": "病种与操作",
            <c:choose>
	            <c:when test="${empty deptEdu}">
				"disabled": true,
				"disabledTip": "请阅读入科教育文档！",
	            </c:when>
            	<c:when test="${deptEdu.state eq '0'}">
				"disabled": true,
				"disabledTip": "带教老师尚未确认报到，请及时联系带教老师！",
	            </c:when>
	            <c:otherwise>
	            "disabled": false,
				"disabledTip": "",
	            </c:otherwise>
            </c:choose>
			"option":{"save":true ,"add":true,"del":true},
			"img":"Study"
        },
        {
            "funcTypeId": "dataInput1N",
            "funcTypeName": "数据列表",
			"funcFlow": "0003",
            "funcName": "病例与管床",
			<c:choose>
	            <c:when test="${empty deptEdu}">
				"disabled": true,
				"disabledTip": "请阅读入科教育文档！",
	            </c:when>
            	<c:when test="${deptEdu.state eq '0'}">
				"disabled": true,
				"disabledTip": "带教老师尚未确认报到，请及时联系带教老师！",
	            </c:when>
	            <c:otherwise>
	            "disabled": false,
				"disabledTip": "",
	            </c:otherwise>
            </c:choose>
			"option":{"save":true , "add": true,"del":true},
			"img":"Clinical" 
        },
		{
            "funcTypeId": "dataInput1N",
            "funcTypeName": "教学活动",
			"funcFlow": "0004",
            "funcName": "教学活动",
            <c:choose>
	            <c:when test="${empty deptEdu}">
				"disabled": true,
				"disabledTip": "请阅读入科教育文档！",
	            </c:when>
            	<c:when test="${deptEdu.state eq '0'}">
				"disabled": true,
				"disabledTip": "带教老师尚未确认报到，请及时联系带教老师！",
	            </c:when>
	            <c:otherwise>
	            "disabled": false,
				"disabledTip": "",
	            </c:otherwise>
            </c:choose>
            "option":{"save":true},
            "img":"Teaching",
            "searchList":[
                {
                    "searchName":"未参加",
                    "searchKey":"join",
                    "searchValue":"NotEntered",
                    "searchType":"tab"
                    
                },
                {
                    "searchName":"未评分",
                    "searchKey":"join",
                    "searchValue":"NotScore",
                    "searchType":"tab"
                },
                {
                    "searchName":"已评分",
                    "searchKey":"join",
                    "searchValue":"Score",
                    "searchType":"tab"
                },
                {
                    "searchName":"主讲人",
                    "searchKey":"mainSpeaker",
                    "searchType":"text"
                }
            ]
        },
        <%--{--%>
            <%--"funcTypeId": "dataInput11",--%>
            <%--"funcTypeName": "数据输入",--%>
			<%--"funcFlow": "0008",--%>
            <%--"funcName": "DOPS评估表",--%>
			<%--<c:choose>--%>
	            <%--<c:when test="${empty deptEdu}">--%>
				<%--"disabled": true,--%>
				<%--"disabledTip": "请阅读入科教育文档！",--%>
	            <%--</c:when>--%>
            	<%--<c:when test="${deptEdu.state eq '0'}">--%>
				<%--"disabled": true,--%>
				<%--"disabledTip": "带教老师尚未确认报到，请及时联系带教老师！",--%>
	            <%--</c:when>--%>
	            <%--<c:when test="${deptEdu.state eq '1' and empty outDops}">--%>
				<%--"disabled": true,--%>
				<%--"disabledTip": "无DOPS评估表，不能操作！",--%>
	            <%--</c:when>--%>
	            <%--<c:otherwise>--%>
	            <%--"disabled": false,--%>
				<%--"disabledTip": "",--%>
	            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
			<%--<c:if test="${outDops['DOPS_State'] eq 0 }">--%>
			<%--"option":{"save":true},--%>
			<%--</c:if>--%>
            <%--"img":"Education"--%>
        <%--},--%>
        <%--{--%>
            <%--"funcTypeId": "dataInput11",--%>
            <%--"funcTypeName": "数据输入",--%>
			<%--"funcFlow": "0009",--%>
            <%--"funcName": "Mini-CEX评估表",--%>
			<%--<c:choose>--%>
	            <%--<c:when test="${empty deptEdu}">--%>
				<%--"disabled": true,--%>
				<%--"disabledTip": "请阅读入科教育文档！",--%>
	            <%--</c:when>--%>
            	<%--<c:when test="${deptEdu.state eq '0'}">--%>
				<%--"disabled": true,--%>
				<%--"disabledTip": "带教老师尚未确认报到，请及时联系带教老师！",--%>
	            <%--</c:when>	            --%>
	            <%--<c:when test="${deptEdu.state eq '1' and empty outMiniCex}">--%>
				<%--"disabled": true,--%>
				<%--"disabledTip": "无Mini-CEX评估表，不能操作！",--%>
	            <%--</c:when>--%>
	            <%--<c:otherwise>--%>
	            <%--"disabled": false,--%>
				<%--"disabledTip": "",--%>
	            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
			<%--<c:if test="${outMiniCex['Mini_State'] eq 0 }">--%>
			<%--"option":{"save":true},--%>
			<%--</c:if>--%>
            <%--"img":"Education"--%>
        <%--},--%>
		{
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
			"funcFlow": "0005",
            "funcName": "评价科室",
            <c:choose>
	            <c:when test="${empty deptEdu}">
				"disabled": true,
				"disabledTip": "请阅读入科教育文档！",
	            </c:when>
            	<c:when test="${deptEdu.state eq '0'}">
				"disabled": true,
				"disabledTip": "带教老师尚未确认报到，请及时联系带教老师！",
	            </c:when>
	            <c:otherwise>
	            "disabled": false,
				"disabledTip": "",
	            </c:otherwise>
            </c:choose>
            <c:if test='${marksDept.size()==0}'>
			"option":{"save":true},
			</c:if>
            "img":"Department"
        },
		{
            "funcTypeId": "dataInput11",
            "funcTypeName": "数据输入",
			"funcFlow": "0006",
            "funcName": "评价老师",
            <c:choose>
	            <c:when test="${empty deptEdu}">
				"disabled": true,
				"disabledTip": "请阅读入科教育文档！",
	            </c:when>
            	<c:when test="${deptEdu.state eq '0'}">
				"disabled": true,
				"disabledTip": "带教老师尚未确认报到，请及时联系带教老师！",
	            </c:when>
	            <c:otherwise>
	            "disabled": false,
				"disabledTip": "",
	            </c:otherwise>
            </c:choose>
            <c:if test='${marksTec.size()==0}'>
			"option":{"save":true},
			</c:if>
            "img":"Teacher"
        },
        {
            "funcTypeId":"dataInput11",
            "funcTypeName":"数据输入",
            "funcFlow":"0007",
            "funcName":"自我评价",
            <c:choose>
	            <c:when test="${empty deptEdu}">
				"disabled": true,
				"disabledTip": "请阅读入科教育文档！",
	            </c:when>
            	<c:when test="${deptEdu.state eq '0'}">
				"disabled": true,
				"disabledTip": "带教老师尚未确认报到，请及时联系带教老师！",
	            </c:when>
	            <c:otherwise>
	            "disabled": false,
				"disabledTip": "",
	            </c:otherwise>
            </c:choose>
            "option":{"save":true},
            "img":"Self"
        }
    ]
