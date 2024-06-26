
<script type="text/javascript">
	$(document).ready(function(){
		initCheck();
	});
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<tr>
			<th>培训基地</th>
			<th>所在科室</th>
			<th>姓名</th>
			<th>用户名</th>
			<th>APP权限
				<br>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="appLogin" onclick="choose('appLogin','appLogins','jsres_teacher_app_login_');"/>
			</th>
			<th>是否提交
				<br>
				<input type="checkbox" name="isSubmitName"/>
			</th>
			<th>审核状态</th>
		</tr>
		<c:forEach items="${list }" var="userExt">
		<tr>
			<td>${userExt.orgName }</td>
			<td>${userExt.deptName }</td>
			<td>${userExt.userName }</td>
			<td>${userExt.userCode }</td>
			<td>
				<c:set var="key" value="jsres_teacher_app_login_${userExt.userFlow}"/>
				<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="jsres_teacher_app_login_${userExt.userFlow}" name="appLogins" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','jsres_teacher_app_login_');"/>
			</td>
			<td>
				<c:if test="${userExt.isSubmitId eq 'NotSubmit' or empty userExt.isSubmitId }">
					<c:set var="key" value="jsres_teacher_app_login_${userExt.userFlow}"/>

					<c:if test="${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y}">
						<input type="button" value="提交" class="" onclick="updateTeaSubmitOne('${userExt.userFlow}')"/>
						<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${userExt.userFlow}" style="display: none"/>
					</c:if>

				</c:if>
				<c:if test="${userExt.isSubmitId eq 'Submit' and userExt.checkStatusId ne 'UnPassed'}">
					已提交
				</c:if>
				<c:if test="${userExt.isSubmitId eq 'Submit' and userExt.checkStatusId eq 'UnPassed'}">
					<input type="button" value="重新提交" class="" onclick="updateTeaSubmitOne('${userExt.userFlow}')"/>
					<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${userExt.userFlow}" style="display: none"/>
				</c:if>
			</td>
			<td>${userExt.checkStatusName }</td>
		</tr>
		</c:forEach>
		<c:if test="${empty list}">
		<tr>
			<td colspan="10" style="border:none;">暂无记录！</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>