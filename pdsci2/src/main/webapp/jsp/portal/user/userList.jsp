<script>

	function deleteUser(userFlow){
		jboxConfirm("确认删除该用户吗？",function () {
			var url = "<s:url value='/sys/user/delete?userFlow='/>"+userFlow+"&wsid=${sessionScope.currWsId}";
			jboxGet(url,null,function(){
				search();
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

	function selRole(userFlow, roleFlow) {
		var url = "<s:url value='/res/manager/selRole'/>?userFlow=" + userFlow + "&roleFlow=" + roleFlow + "&wsId=${GlobalConstant.PORTALS_WS_ID}";
		jboxGet(url, null, function (obj) {
			if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
				check(userFlow);
			}
		}, null, true);
	}
</script>
<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<tr>
			<th>姓名</th>
			<th>用户名</th>
			<th>角色</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list }" var="userExt">
		<tr>
			<td>${userExt.userName }</td>
			<td>${userExt.userCode }</td>
			<td>
				<c:if test="${!empty applicationScope.sysCfgMap['portals_user_role_flow']}">
					<label style="width: 35%;display: inline-block;">
						<input type="checkbox" onclick="selRole('${userExt.userFlow}',this.value)"
							   value="${applicationScope.sysCfgMap['portals_user_role_flow'] }"
							   <c:if test="${pdfn:contain(applicationScope.sysCfgMap['portals_user_role_flow'],sysUserRoleMap[userExt.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['portals_user_role_flow']]?sysRoleMap[sysCfgMap['portals_user_role_flow']].roleName:'用户'}
					</label>
				</c:if>
				<c:if test="${!empty applicationScope.sysCfgMap['portals_cityCharge_role_flow']}">
					<label style="width: 35%;display: inline-block;">
						<input type="checkbox" onclick="selRole('${userExt.userFlow}',this.value)"
							   value="${applicationScope.sysCfgMap['portals_cityCharge_role_flow'] }"
							   <c:if test="${pdfn:contain(applicationScope.sysCfgMap['portals_cityCharge_role_flow'],sysUserRoleMap[userExt.userFlow]) }">checked='checked'</c:if>/>${!empty sysRoleMap[sysCfgMap['portals_cityCharge_role_flow']]?sysRoleMap[sysCfgMap['portals_cityCharge_role_flow']].roleName:'市局'}
					</label>
				</c:if>
			</td>
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