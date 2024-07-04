
"cfgFlow":${pdfn:toJsonString(evalCfg.cfgFlow)},
"cfgName":${pdfn:toJsonString(evalCfg.cfgName)},
"evalYear":${pdfn:toJsonString(evalCfg.evalYear)},
"isFile":${pdfn:toJsonString(evalCfg.isFile)},
"isPublish":${pdfn:toJsonString(evalCfg.isPublish)},
"filePath":${pdfn:toJsonString(evalCfg.filePath)},
"parentCfgFlow":${pdfn:toJsonString(evalCfg.parentCfgFlow)},
"levelId":${pdfn:toJsonString(evalCfg.levelId)},
"isExpert":${pdfn:toJsonString(evalCfg.isExpert)},
"typeId":${pdfn:toJsonString(evalCfg.typeId)},
"actionTypeId":${pdfn:toJsonString(evalCfg.actionTypeId)},
"actionTypeName":${pdfn:toJsonString(evalCfg.actionTypeName)},
"speId":${pdfn:toJsonString(evalCfg.speId)},
"speName":${pdfn:toJsonString(evalCfg.speName)},
"hasResult":"${empty resultMap[evalCfg.cfgFlow]?'N':'Y'}",
"subList":[
		<c:if test="${fn:length( childrenMap[evalCfg.cfgFlow]) > 0}">
			<c:forEach items="${childrenMap[evalCfg.cfgFlow]}" var="c">
				{
					<c:set var="evalCfg" value="${c}"  scope="request" ></c:set>
					<c:import url="readCfg.jsp"></c:import>
				}
			</c:forEach>
		</c:if>
]