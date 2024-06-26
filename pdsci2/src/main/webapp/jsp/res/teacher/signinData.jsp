<table class="xllist">
	<tr>
		<th colspan="99" style="text-align:left;padding-left: 10px; font-size:14px;font-weight:normal;font-weight: bold;">
			签到信息
		</th>
	</tr>
	<tr>
	<th style="width: 100px;">姓名</th>
	<th style="width: 200px;">签到时间</th>
	<th style="width: 200px;">轮转科室</th>
		<c:forEach items="${signInMaps}" var="sign" varStatus="status">
			<c:set var="imgUrl" value="${sysCfgMap.upload_base_url}/${sign.userHeadImg}"/>
			<tr>
				<td 
					<%--<c:if test="${!status.first}">--%>
						title="<img class='whenError' src='${imgUrl}' style='width: 90px;height: 120px;' onerror='errorImg(this);'/>"
						style="cursor: pointer;"
					<%--</c:if>--%>
				>
					<%--<c:if test="${status.first}">--%>
						<%--<img class="whenError" src="${imgUrl}" style="width: 90%;height: 120px;margin-top: 10px;" onerror="errorImg(this);"/>--%>
					<%--</c:if>--%>
					<div>${sign.userName}</div>
				</td>
				<td>${sign.signDate}</td>
				<td>${sign.schDeptName}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty signInMaps}">
			<tr><td colspan="99">暂无签到信息</td></tr>
		</c:if>
</table>
		