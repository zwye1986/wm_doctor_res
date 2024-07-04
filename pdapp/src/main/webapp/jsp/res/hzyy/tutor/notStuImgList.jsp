
	"pageIndex": ${param.pageIndex},
	"pageSize": ${param.pageSize},
	"dataCount":${dataCount},
    "datas": [
        <c:forEach items="${datas}" var="bean" varStatus="status">
			{
				<c:set var="fileUrl" value="${baseUrl}/${bean.fileUrl}"></c:set>
				"fileFlow":${pdfn:toJsonString(bean.fileFlow)},
				"toturFlow":${pdfn:toJsonString(bean.toturFlow)},
				"fileUrl":${pdfn:toJsonString(fileUrl)},
				"fileSuffix":${pdfn:toJsonString(pdfn:getFileSuffix(fileUrl))},
				"fileName":${pdfn:toJsonString(bean.fileName)},
				"dateTime":${pdfn:toJsonString(bean.dateTime)}
	        }
	        <c:if test='${not status.last}'>
	        ,
	        </c:if>
        </c:forEach>
	]