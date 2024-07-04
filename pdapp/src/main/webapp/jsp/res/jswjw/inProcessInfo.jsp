<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
    info:{
        "deptName":"${info.deptName}",
        "deptBriefing":"${info.deptBriefing}",
        "deptFramework":"${info.deptFramework}",
        "workEnvironment":"${info.workEnvironment}",
        "workScope":"${info.workScope}",
        "attendanceInfo":"${info.attendanceInfo}",
        "members":[
                <c:forEach  items="${members}" var="member" varStatus="s">
                    {
                        "memberPostName":"${member.memberPostName}",
                        "memberName":"${member.memberName}",
                        "memberPhone":"${member.memberPhone}",
                        "memberTitleName":"${member.memberTitleName}"
                    }
                    <c:if test="${!s.last}">
                        ,
                    </c:if>
                </c:forEach>
            ],
        "days":[
                <c:forEach  items="${days}" var="day" varStatus="s">
                    {
                        "address":"${day.address}",
                        "content":"${day.content}",
                        "dayName":"${day.dayName}"
                    }
                    <c:if test="${!s.last}">
                        ,
                    </c:if>
                </c:forEach>
            ],
        "files":[
                <c:forEach  items="${files}" var="file" varStatus="s">
                     <c:set var="filePath" value="${uploadBaseUrl}${file.filePath}"/>
                    {
                        "fileFlow":"${file.fileFlow}",
                        "fileName":"${file.fileName}",
                        "fileSuffix":"${file.fileSuffix}",
                        "filePath":${pdfn:toJsonString(filePath)}
                    }
                    <c:if test="${!s.last}">
                        ,
                    </c:if>
                </c:forEach>
            ]
        }
    </c:if>
}
