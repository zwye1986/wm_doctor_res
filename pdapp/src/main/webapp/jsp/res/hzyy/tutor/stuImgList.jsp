
    "datas": [
        <c:forEach items="${times}" var="bean" varStatus="status">
			{
				"yearMonth":"${bean}",
				"monthFirstDay":"${pdfn:getFirstDayOfMonth(bean)}",
				"monthLastDay":"${pdfn:getLastDayOfMonth(bean)}",
				"imgList":[
					<c:forEach items="${monthStuImg[bean]}" var="bean2" varStatus="status2">
						{
							<c:set var="fileUrl" value="${baseUrl}/${bean2.fileUrl}"></c:set>
							"fileFlow":${pdfn:toJsonString(bean2.fileFlow)},
							"toturFlow":${pdfn:toJsonString(bean2.toturFlow)},
							"fileUrl":${pdfn:toJsonString(fileUrl)},
							"fileSuffix":${pdfn:toJsonString(pdfn:getFileSuffix(fileUrl))},
							"fileName":${pdfn:toJsonString(bean2.fileName)},
							"dateTime":${pdfn:toJsonString(bean2.dateTime)}
						}
						<c:if test='${not status2.last}'>
						,
						</c:if>
					</c:forEach>
				]
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]