<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId},
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    ,
		"dataCount": ${dataCount},
    "datas": [
		<c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
	     	{
				"lectureFlow":"${lecture.lectureFlow}",
				"registFlow":"${lecture.registFlow}",
				"lectureTrainDate":"${lecture.lectureTrainDate}",
				"lectureStartTime":"${lecture.lectureStartTime}",
				"lectureEndTime":"${lecture.lectureEndTime}",

			<c:if test="${empty lecture.coursewareUrl}">
				<c:set var="coursewareUrl" value=""/>
			</c:if>
			<c:if test="${not empty lecture.coursewareUrl}">
				<c:set var="coursewareUrl" value="${uploadBaseUrl}/${lecture.coursewareUrl}"/>
			</c:if>
				"coursewareUrl":${pdfn:toJsonString(coursewareUrl)},
				"fileSuffix":${pdfn:toJsonString(pdfn:getFileSuffix(lecture.coursewareUrl))},
				<c:set var="fileName" value="${lecture.lectureContent}-课件"></c:set>
				"fileName":${pdfn:toJsonString(fileName)},
				"lectureTypeId":"${lecture.lectureTypeId}",
				"evalTypeId":${pdfn:toJsonString(evalTypeMap[lecture.lectureFlow])},
				"lectureTypeName":"${lecture.lectureTypeName}",
				"lectureContent":"${lecture.lectureContent}",
				"lectureTrainPlace":"${lecture.lectureTrainPlace}",
				"lectureTeacherName":"${lecture.lectureTeacherName}",
				"lectureDescription":"${lecture.lectureDescription}",
				"lectureLevelId":"${lecture.lectureLevelId}",
				"lectureLevelName":"${lecture.lectureLevelName}",
				"isRegist":"${lecture.isRegist}",
				"isCan":"${lecture.isCan}",
				"evaEndTime":"${lecture.evaEndTime}",
				"startTime":"${lecture.startTime}",
				"endTime":"${lecture.endTime}",
				"nowTime":"${lecture.nowTime}",
				"isClosed":"${lecture.isClosed}",
				"evaFlow":"${lecture.evaFlow}",
				<c:if test="${!empty lecture.lectureDesc}">
					"lectureDesc":"${lecture.lectureDesc}",
				</c:if>
				<c:if test="${empty lecture.lectureDesc}">
					"lectureDesc":"无",
				</c:if>
			"operId":
			<c:choose>
				<c:when test="${(empty evaDetailMap[lecture.lectureFlow])&&(evaMap[lecture.lectureFlow]>=0)&&(!empty scanMap[lecture.lectureFlow])}">
					"Evaluate",
				</c:when>
				<c:when test="${(!empty evaDetailMap[lecture.lectureFlow])&&(!empty scanMap[lecture.lectureFlow])}">
					"ShowLecture",
				</c:when>
				<c:when test="${(evaMap[lecture.lectureFlow]<0)&&(!empty scanMap[lecture.lectureFlow])&&(empty evaDetailMap[lecture.lectureFlow])}">
					"IsClosed",
				</c:when>
				<c:when test="${(empty scanMap[lecture.lectureFlow])}">
					"NoScan",
				</c:when>
				<c:otherwise>
					"",
				</c:otherwise>
			</c:choose>

			"operName":
			<c:choose>
				<c:when test="${(empty evaDetailMap[lecture.lectureFlow])&&(evaMap[lecture.lectureFlow]>=0)&&(!empty scanMap[lecture.lectureFlow])}">
					"评价"
				</c:when>
				<c:when test="${(!empty evaDetailMap[lecture.lectureFlow])&&(!empty scanMap[lecture.lectureFlow])}">
					"查看评价"
				</c:when>
				<c:when test="${(evaMap[lecture.lectureFlow]<0)&&(!empty scanMap[lecture.lectureFlow])&&(empty evaDetailMap[lecture.lectureFlow])}">
					"评价已关闭"
				</c:when>
				<c:when test="${(empty scanMap[lecture.lectureFlow])}">
					"未扫码"
				</c:when>
				<c:otherwise>
					"",
				</c:otherwise>
			</c:choose>

			}
			<c:if test="${not s.last }">
				,
			</c:if>
		</c:forEach>
    ]
    </c:if>
}
