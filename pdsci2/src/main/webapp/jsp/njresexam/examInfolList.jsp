<div class="search_table" id="baseInfo">
	<table border="0" cellpadding="0" cellspacing="0" class="base_info">
		<colgroup>
			<col width="15%"/>
			<col width="20%"/>
			<col width="20%"/>
			<col width="10%"/>
			<col width="20%"/>
			<col width="15%"/>
		</colgroup>
		<tr>
			<th style="text-align:center;">专业</th>
			<th style="text-align:center;">考试地点</th>
			<th style="text-align:center;">考试时间</th>
			<th style="text-align:center;">考点联系电话</th>
			<th style="text-align:center;">准考证标题</th>
			<th style="text-align:center;">编辑信息</th>
		</tr>
		<c:forEach items="${tjExamInfos}" var="exam" varStatus="extStatus">
			<tr>
				<td>${exam.speName}</td>
				<td>${exam.examAddress}</td>
				<td>${exam.examTime}</td>
				<td>${exam.sitePhone}</td>
				<td>${exam.title}</td>
<%--				<td>[<a onclick="editExamInfo('${exam.recordFlow}')">编辑信息</a>][<a onclick="setExamInfo('${exam.recordFlow}');">分配</a>]<br>[<a onclick="selExamInfo('${exam.speName}');">已分配人员</a>][<a onclick="delExamInfo('${exam.recordFlow}');">删除</a>][<a onclick="examUserImport('${exam.recordFlow}');">导入</a>]</td>--%>
				<td>[<a onclick="setExamInfo('${exam.recordFlow}');">分配</a>][<a onclick="delExamInfo('${exam.recordFlow}');">删除</a>][<a onclick="examUserImport('${exam.recordFlow}');">导入</a>]</td>
			</tr>
		</c:forEach>
		<c:if test="${empty tjExamInfos}">
			<tr>
				<td colspan="6" style="text-align: center;">无记录！</td>
			</tr>
		</c:if>
	</table>
</div>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(tjExamInfos)}"
		   scope="request"></c:set>
	<pd:pagination-jszy toPage="toPage"/>
</div>