
<div  class="search_table">
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<%--<colgroup>
			<col width="20%"/>
			<col width="30%"/>
			<col width="15%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="8%"/>
			<col width="7%"/>
		</colgroup>--%>
		<tbody id="tbody">
		<tr>
			<th><input type="checkbox" id="checkAll" onclick="allCheck()"/></th>
			<th>姓名</th>
			<th>手机号</th>
			<th>证件号码</th>
			<th>专业</th>
			<th>注册帐号</th>
			<%--<th>锁定</th>--%>
<c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
			<th width="70px">还原密码</th>
</c:if>
			<th>解锁记录</th>
			<th>账号状态</th>
<c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
			<th>手机解绑</th>
			<th>账号解锁</th>
			<th>操作</th>
</c:if>
		</tr>
		<c:forEach items="${doctorExtList}" var="doctorExt">
			<tr>
				<td><input type="checkbox"  class="check" value="${doctorExt.sysUser.userFlow}" onclick="checkSingel(this)"></td>
				<td>${doctorExt.sysUser.userName}</td>
				<td>${doctorExt.sysUser.userPhone}</td>
				<td>${doctorExt.sysUser.idNo}</td>
				<td>${doctorExt.trainingSpeName}</td>
				<td>${doctorExt.sysUser.userCode}</td>
				<c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
				<td>
					<a style="color: blue;cursor: pointer; padding-left: 10px;" onclick="resetPasswd('${doctorExt.sysUser.userFlow}')">还原密码</a>
				</td>
				</c:if>
				<td style="text-align: center;padding: 0px;">
					<a style="color: blue;cursor: pointer;" onclick="lokeStudentUntiedRecording('${doctorExt.sysUser.userFlow}');">查看</a>
				</td>
				<td style="text-align: center;padding: 0px;">
					<c:choose>
						<c:when test="${'Locked' eq  doctorExt.sysUser.statusId}"> <font style="color: red">已停用</font></c:when>
						<c:when test="${'SysLocked' eq  doctorExt.sysUser.statusId}"> <font style="color: red">已锁定</font></c:when>
						<c:otherwise><font style="color: #54B2E5">已激活</font></c:otherwise>
					</c:choose>
				</td>
				<c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
				<td style="text-align: center;padding: 0px;">
					<c:choose>
						<c:when test="${not empty macidMap[doctorExt.sysUser.userFlow] and not empty macidMap[doctorExt.sysUser.userFlow].macId}">
							<a style="color: blue;cursor: pointer;" onclick="removeBind('${doctorExt.sysUser.userFlow}');">解绑</a>
						</c:when>
						<c:otherwise> <font style="color: #8a8a8a">解绑</font> </c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: center;padding: 0px;">
					<c:choose>
						<c:when test="${'SysLocked' eq  doctorExt.sysUser.statusId}">
							<a style="color: blue;cursor: pointer; " onclick="unLock('${doctorExt.sysUser.userFlow}');">解锁</a>
						</c:when>
						<c:otherwise><font style="color: #8a8a8a">解锁</font></c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: center;padding: 0px;">
					<c:choose>
						<c:when test="${'Locked' eq  doctorExt.sysUser.statusId}">
							<a style="color: blue;cursor: pointer; " onclick="activate('${doctorExt.sysUser.userFlow}');">启用</a>
						</c:when>
						<c:otherwise>
							<a style="color: blue;cursor: pointer; " onclick="stop('${doctorExt.sysUser.userFlow}');">停用</a>
						</c:otherwise>
					</c:choose>
				</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
		<c:if test="${empty doctorExtList}">
			<tr >
				<td colspan="99">无记录</td>
			</tr>
		</c:if>
	</table>
</div>

<div class="page" style="padding-right: 40px;">
	<c:set var="pageView" value="${pdfn:getPageView(doctorExtList)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>	 
</div>
