<div class="search_table" id="baseInfo">
	<table border="0" cellpadding="0" cellspacing="0" class="base_info">
		<colgroup>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
		</colgroup>
		<tr>
			<th style="text-align:center;">姓名</th>
			<th style="text-align:center;">证件号码</th>
			<th style="text-align:center;">培训专业</th>
			<th style="text-align:center;">准考证号</th>
			<th style="text-align:center;">考试地点</th>
			<th style="text-align:center;">考试时间</th>
			<th style="text-align:center;">考生联系电话</th>
			<th style="text-align:center;">考点联系电话</th>
			<th style="text-align:center;">准考证标题</th>
			<th style="text-align:center;">编辑信息</th>
		</tr>
		<c:forEach items="${extList}" var="ext" varStatus="extStatus">
			<tr>
				<td>${ext.userName}</td>
				<td>${ext.idNo}</td>
				<td>${ext.speName}</td>
				<td>${ext.ticketNum}</td>
				<td>${ext.address}</td>
				<td>${ext.examtime}</td>
				<td>${ext.userPhone}</td>
				<td>${ext.sitephone}</td>
				<td>${ext.title}</td>
<%--				<td><a onclick="editExamCard('${ext.userFlow}','${ext.idNo}')">编辑</a></td>--%>
				<td><a onclick="deleteRecord('${ext.userFlow}','${ext.idNo}')">删除</a></td>
			</tr>
		</c:forEach>
		<c:if test="${empty extList}">
			<tr>
				<td colspan="9" style="text-align: center;">无记录！</td>
			</tr>
		</c:if>
	</table>
</div>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(extList)}" scope="request"></c:set>
	<pd:pagination-jszy toPage="toPage"/>
</div>