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
				"lectureTrainDate":"${lecture.lectureTrainDate}",
				"lectureStartTime":"${lecture.lectureStartTime}",
				"lectureEndTime":"${lecture.lectureEndTime}",
				"lectureTypeName":"${lecture.lectureTypeName}",
				"lectureContent":${pdfn:toJsonString(lecture.lectureContent)},
				"lectureTrainPlace":${pdfn:toJsonString(lecture.lectureTrainPlace)},
				"lectureTeacherName":"${lecture.lectureTeacherName}",
				<c:if test="${!empty lecture.lectureDesc}">
					"lectureDesc":${pdfn:toJsonString(lecture.lectureDesc)},
				</c:if>
				<c:if test="${empty lecture.lectureDesc}">
					"lectureDesc":"无",
				</c:if>
				"operId":
						<c:choose>
							<c:when test="${empty registMap[lecture.lectureFlow] or (!empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist != 'Y' ))}">
								"NoSign",
							</c:when>
							<c:when test="${!empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist eq 'Y' )}">
								"IsSign",
							</c:when>
							<c:otherwise>
								"NoSign",
							</c:otherwise>
						</c:choose>

				"operName":
						<c:choose>
							<c:when test="${empty registMap[lecture.lectureFlow] or (!empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist != 'Y'  ))}">
								"报名",
							</c:when>
							<c:when test="${!empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist eq 'Y' )}">
								"已报名",
							</c:when>
							<c:otherwise>
								"报名",
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
