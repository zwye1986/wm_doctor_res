<form id="${param.formId}" method="post">
	<input type="hidden" name="formId" value="${param.formId}">
	<input type="hidden" name="scoreFlow" value="${param.scoreFlow}">
	<c:if test="${score.fromTypeId eq '0'}">
		<c:set var="itemTotalScore" value="0"></c:set>
		<c:set var="totalScore" value="0"></c:set>
		<c:forEach items="${titleFormList}" var="title">
			<c:set var="itemSubTotalScore" value="0"></c:set>
			<c:set var="subTotalScore" value="0"></c:set>
			<div class="cont_list" style="margin-top:15px;">
				<div class="left">
					评分项目：<span class="name" style="width:150px;">${title.name}</span>
					评分子项目：<span class="name" style="width:150px;">${title.typeName}</span>
				</div>
			</div>
			<div style="padding-top: 0px;">
				<table class="xllist"  cellpadding="0" cellspacing="0" style="width: 100%">
					<colgroup>
						<col width="80%"/>
						<col width="10%"/>
						<col width="10%"/>
					</colgroup>
					<tr>
						<th style="text-align:center;">评分要素</th>
						<th style="text-align:center;">分数</th>
						<th style="text-align:center;">得分</th>
					</tr>
					<c:forEach items="${title.itemList}" var="item" varStatus="status">
						<tr>
							<td>${item.name}</td>
							<td>${item.score}</td>
							<td>${dataMap[item.id]}</td>
							<c:set var="itemSubTotalScore" value="${itemSubTotalScore + item.score}"></c:set>
							<c:set var="subTotalScore" value="${subTotalScore + dataMap[item.id]}"></c:set>
						</tr>
					</c:forEach>
					<c:set var="itemTotalScore" value="${itemTotalScore + itemSubTotalScore}"></c:set>
					<c:set var="totalScore" value="${totalScore + subTotalScore}"></c:set>
					<c:choose>
						<c:when test="${empty title.itemList}">
							<tr>
								<td colspan="3">无记录！</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>子项总分</td>
								<td>${pdfn:toFormatString(itemSubTotalScore)}</td>
								<c:if test="${totalScore > 0 }">
									<td>${pdfn:toFormatString(subTotalScore)}</td>
								</c:if>
								<c:if test="${totalScore <= 0 }">
									<td>0</td>
								</c:if>

							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		</c:forEach>
		<table class="xllist" cellpadding="0" cellspacing="0" style="width: 100%;margin-top: 15px;">
			<colgroup>
				<col width="80%"/>
				<col width="10%"/>
				<col width="10%"/>
			</colgroup>
			<tr>
				<td style="text-align:center;">总分</td>
				<td style="text-align:center;">${pdfn:toFormatString(itemTotalScore)}</td>
				<c:if test="${totalScore > 0 }">
					<td style="text-align:center;">${pdfn:toFormatString(totalScore)}</td>
				</c:if>
				<c:if test="${totalScore <= 0 }">
					<td style="text-align:center;">0</td>
				</c:if>
			</tr>
		</table>
	</c:if>
	<c:if test="${score.fromTypeId eq '1'}">
		<jsp:include page='/${score.fromUrl}'/>
	</c:if>
	<c:if test="${score.fromTypeId eq 'N'}">
		<div style="text-align:center;margin:50px 0px;font:18px bold;">考官打分：${score.examScore}</div>
	</c:if>
</form>
