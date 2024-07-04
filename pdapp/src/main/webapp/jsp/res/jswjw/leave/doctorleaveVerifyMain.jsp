<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
"resultId": ${resultId},
"resultType": ${pdfn:toJsonString(resultType)}
<c:if test="${resultId eq '200' }">
    ,
    "isAddLeave":"${isAddLeave}",
    "leaveTypes": [
    <c:forEach items="${typeList}" var="b" varStatus="s">
        {
        "typeId":"${b.typeId}",
        "typeName":"${b.typeName}"
        }
        <c:if test="${not s.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    ,
    "status": [
    {
    "typeId":"",
    "typeName":"全部"
    }
    ,
    {
    "typeId":"Auditing",
    "typeName":"请假申请中"
    }
    ,
    {
    "typeId":"ManagerPass",
    "typeName":"请假审核通过"
    }
    ,
    {
    "typeId":"ManagerUnPass",
    "typeName":"请假审核不通过"
    }
    ,
    {
    "typeId":"Revokeing",
    "typeName":"销假申请中"
    }
    ,
    {
    "typeId":"RevokeManagerPass",
    "typeName":"销假审核通过"
    }
    ,
    {
    "typeId":"BackLeave",
    "typeName":"已撤销"
    }
    ]
    ,
    "appealTypes": [
    <c:forEach items="${appealTypes}" var="b" varStatus="s">
        {
        "typeId":"${b.dictId}",
        "typeName":"${b.dictName}"
        }
        <c:if test="${not s.last }">
            ,
        </c:if>
    </c:forEach>
    ]
    ,
    "processes": [
    <c:forEach items="${deptList}" var="b" varStatus="s">
        {
        "processFlow":"${b.processFlow}",
        "schDeptFlow":"${b.schDeptFlow}",
        "schDeptName":"${b.schDeptName}",
        "schStartDate":"${b.schStartDate}",
        "schEndDate":"${b.schEndDate}"
        }
        <c:if test="${not s.last }">
            ,
        </c:if>
    </c:forEach>
    ]
</c:if>
}
