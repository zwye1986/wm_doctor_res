<div class="search_table">
   <table class="grid" > 
		<thead>
		<tr>
			<th style="width: 10%;">科室代码</th>
			<th style="width: 20%;">科室名称</th>
			<th style="width: 28%;">标准科室</th>
			<th style="width: 12%;">出科考试关联</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${deptList}" var="dept">
		<tr>
			<td>${dept.deptCode}</td>
			<td>${dept.deptName}</td>
			<td>${dept.standardDeptName}</td>
			<td>
				<c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
					<c:if test="${empty dept.recordFlow}"><a href="javascript:modifyCfg('${dept.deptFlow}','');" class="btn">未关联</a></c:if>
					<c:if test="${not empty dept.recordFlow}"><a href="javascript:modifyCfg('${dept.deptFlow}','${dept.recordFlow}');" class="btn">已关联</a></c:if>
				</c:if>
				<c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_N }">
					<c:if test="${empty dept.recordFlow}">未关联</c:if>
					<c:if test="${not empty dept.recordFlow}">已关联</c:if>
				</c:if>
			</td>
			<td>
				<c:if test="${!empty dept.speFlow}">
					<a href="javascript:void(0)" onclick="showDept('${dept.deptFlow}','${dept.deptName}','','${dept.speFlow}');" class="btn">查看</a>
				</c:if>
				<c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
					<a href="javascript:edit('${dept.deptFlow}');" class="btn">编辑</a>
					<a href="javascript:del('${dept.deptFlow}','${dept.deptName}','${GlobalConstant.RECORD_STATUS_N}');" class="btn">停用</a>
					<a href="javascript:publicOpen('${dept.deptFlow}');" class="btn">对外开放</a>
				</c:if>
				<c:if test="${dept.recordStatus == GlobalConstant.RECORD_STATUS_N }">
					<a href="javascript:del('${dept.deptFlow}','','${GlobalConstant.RECORD_STATUS_Y}');" class="btn">启用</a>
				</c:if>
			</td>
		</tr>
	   </c:forEach>
	   <c:if test="${empty deptList}"> 
			<tr> 
				<td align="center" colspan="10">无记录</td>
			</tr>
		</c:if>
	   </tbody>
	</table>
</div>
<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(deptList)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>	 
</div>
