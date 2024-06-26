
<div  class="search_table">
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<colgroup>
			<col width="75%"/>
			<col width="10%"/>
			<col width="15%"/>
		</colgroup>
		<tbody id="tbody">
		<tr>
			<th>标题</th>
			<th>发布日期</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${infos}" var="info">
			<tr>
				<td style="text-align: left;">
						${info.infoTitle}
					<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.infoTime))<=7}">
						<img src="<s:url value='/css/skin/new.png'/>"/>
					</c:if>
				</td>
				<td>${pdfn:transDate(info.infoTime)}</td>
				<td>
					<a href="javascript:edit('${info.infoFlow}');" style="color: gray;">编辑</a> |
					<a href="<s:url value='/inx/jsres/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank" style="color: gray;">查看</a> |
					<a href="javascript:delNotice('${info.infoFlow}');" style="color: gray;">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		<c:if test="${empty infos}">
			<tr>
				<td colspan="4">无记录</td>
			</tr>
		</c:if>
	</table>
</div>

<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>
