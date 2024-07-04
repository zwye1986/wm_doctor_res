<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
	"deptBriefing":${pdfn:toJsonString(info.deptBriefing)}
    "members": [
		<c:forEach items="${members}" var="b" varStatus="s">
	     	{
			<c:forEach items="${dictTypeEnumUserPostList}" var="post">
				<c:if test="${b.memberPostId eq post.dictId }">
					"memberPost":${pdfn:toJsonString(post.dictName)}
				</c:if>
			</c:forEach>
				"memberName": ${pdfn:toJsonString(b.memberName)},
				"memberPhone": ${pdfn:toJsonString(b.memberPhone)},
			<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
				<c:if test="${b.memberTitleId eq title.dictId }">
						"memberTitle": ${pdfn:toJsonString(title.dictName)},
				</c:if>
			</c:forEach>
			}
			<c:if test="${not s.last }">
				,
			</c:if>
    	 </c:forEach>
    ],
	"deptFramework":${pdfn:toJsonString(info.deptFramework)},
	"workEnvironment":${pdfn:toJsonString(info.workEnvironment)},
	"workScope":${pdfn:toJsonString(info.workScope)},
	"address1":${pdfn:toJsonString(arrangeMap['address1'])},
	"content1":${pdfn:toJsonString(arrangeMap['content1'])},
	"address2":${pdfn:toJsonString(arrangeMap['address2'])},
	"content2":${pdfn:toJsonString(arrangeMap['content2'])},
	"address3":${pdfn:toJsonString(arrangeMap['address3'])},
	"content3":${pdfn:toJsonString(arrangeMap['content3'])},
	"address4":${pdfn:toJsonString(arrangeMap['address4'])},
	"content4":${pdfn:toJsonString(arrangeMap['content4'])},
	"address5":${pdfn:toJsonString(arrangeMap['address5'])},
	"content5":${pdfn:toJsonString(arrangeMap['content5'])},
	"address6":${pdfn:toJsonString(arrangeMap['address6'])},
	"content6":${pdfn:toJsonString(arrangeMap['content6'])},
	"address7":${pdfn:toJsonString(arrangeMap['address7'])},
	"content7":${pdfn:toJsonString(arrangeMap['content7'])},
	"attendanceInfo":${pdfn:toJsonString(info.attendanceInfo)},
    </c:if>
}
