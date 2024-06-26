<script>

	function deleteUser(userFlow){
		jboxConfirm("确认删除该用户吗？",function () {
			var url = "<s:url value='/sys/user/delete?userFlow='/>"+userFlow+"&wsid=${sessionScope.currWsId}";
			jboxGet(url,null,function(){
				searchUser();
			});
		});
	}

	function resetPasswd(userFlow){
		jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
			var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
				//searchUser();
			});
		});
	}
</script>
<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<tr>
			<th>用户名</th>
			<th>系统管理</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="userExt">
		<tr>
			<td>${userExt.userCode }</td>
			<td>${userExt.roleName }</td>
			<td>
				[<a href="javascript:add('${userExt.userFlow}');" >编辑</a>] |
				[<a href="javascript:deleteUser('${userExt.userFlow}');" >删除</a>]
				[<a href="javascript:resetPasswd('${userExt.userFlow}');" class="btn">重置密码</a>]
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty list}">
		<tr>
			<td colspan="8" style="border:none;">暂无记录！</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>