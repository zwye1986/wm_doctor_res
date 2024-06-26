<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript">
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<colgroup>
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="12%"/>
			<col width="10%"/>
		</colgroup>
		<thead>
		<tr>
			<th>项目名称</th>
			<th>客户名称</th>
			<th>立项时间</th>
			<th>参与人</th>
			<th>预计完成日</th>
			<th>实际完成日</th>
			<th>项目状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${empty list }">
		<tr>
			<td colspan="8">无记录</td>
		</tr>
		</c:if>
		<c:forEach items="${list }" var="manage">
		<tr>
			<td>${manage.productName }</td>
			<td>${manage.consumerName }</td>
			<td>${manage.approvalTime }</td>
			<td>
				<c:set var="users" value="${userMap[manage.manageFlow]}"></c:set>
				<c:forEach items="${users}" var="u">
					${u.userName}<br/>
				</c:forEach>
			</td>
			<td>${manage.etcTime }</td>
			<td>${manage.completeTime }</td>
			<td>${manage.statusName }</td>
			<td>
				<c:if test="${manage.statusId eq 'NotStart'}">
					<a  href="javascript:add('${manage.manageFlow }');">编辑</a>
					<a  href="javascript:del('${manage.manageFlow }');">删除</a>
					<a  href="javascript:manageStart('${manage.manageFlow }');">项目启动</a>
				</c:if>
				<c:if test="${manage.statusId eq 'Processing'}">
					<a  href="javascript:completeProduct('${manage.manageFlow }','Complete');">项目完成</a>
				</c:if>
				<c:if test="${manage.statusId ne 'NotStart' and (not empty manage.statusId)}">
					<a  href="javascript:productProcessList('${manage.manageFlow }');">项目过程</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>