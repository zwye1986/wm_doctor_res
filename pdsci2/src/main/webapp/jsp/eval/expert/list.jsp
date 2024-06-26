<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>

<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
	<colgroup>
		<col width="10%"/>
		<col width="10%"/>
		<col width="20%"/>
		<col width="5%"/>
		<col width="15%"/>
		<col width="10%"/>
		<col width="10%"/>
		<col width="20%"/>
	</colgroup>
	<thead>
	<tr>
		<th>姓名</th>
		<th>登录名</th>
		<th>专业</th>
		<th>性别</th>
		<th>工作单位</th>
		<th>手机号</th>
		<th>是否组长</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:if test="${empty userList }">
		<tr>
			<td colspan="14">无记录</td>
		</tr>
	</c:if>
	<c:forEach items="${userList }" var="user">
		<tr >
			<td >${user.userName }</td>
			<td >${user.userCode }</td>
			<td >${user.resTrainingSpeName }</td>
			<td >${user.sexName }</td>
			<td >${user.workOrgName }</td>
			<td >${user.userPhone }</td>
			<td >
				<c:if test="${'Y' == user.srmExpertFlag}">是</c:if>
				<c:if test="${'N' == user.srmExpertFlag}">否</c:if>
			</td>
			<td >
				<c:if test="${user.statusId==userStatusEnumActivated.id}">
					[<a style="color:#449bcd;" href="javascript:add('${user.userFlow}');" >编辑</a>] |
					[<a style="color:#449bcd;" href="javascript:resetPasswd('${user.userFlow}');" >重置密码</a>] |
					[<a style="color:#449bcd;" href="javascript:lock('${user.userFlow}');" >停用</a>]
				</c:if>
				<c:if test="${user.statusId==userStatusEnumLocked.id}">
					[<a style="color:#449bcd;" href="javascript:activate('${user.userFlow}');" >启用</a>]
				</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div>
	<c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"/>
	<pd:pagination toPage="toPage"/>
</div>