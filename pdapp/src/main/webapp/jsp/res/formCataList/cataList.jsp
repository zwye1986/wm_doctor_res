
		"search":[
	        {
	            "name":${pdfn:toJsonString(recTypeName)},
	            "key":"name",
	            "type":"text"
	        }
	    ],
		"catagories":[
			<c:forEach items="${reqMaps}" var="reqMap" varStatus="status">
				<c:set var="req" value="${reqMap.req}"/>
				<c:set var="auditKey" value="${param.deptFlow}${req.recTypeId}${req.itemId}Audit"/>
				<c:set var="finishedKey" value="${param.deptFlow}${req.recTypeId}${req.itemId}Finished"/>
				<c:set var="progressKey" value="${param.deptFlow}${req.recTypeId}${req.itemId}"/>
				{
					"auditedNum":${perMap[auditKey]+0},
					"cataFlow":${pdfn:toJsonString(req.itemId)},
					"finishedNum":${perMap[finishedKey]+0},
					"labelsNum":3,
					"neededNum":${pdfn:toJsonString(req.reqNum)},
					"progress":${perMap[progressKey]+0},
					"title":${pdfn:toJsonString(req.itemName)}
				}
				<c:if test="${!status.last}">
					,
				</c:if>
			</c:forEach>
		],
		"dataCount": ${dataCount}