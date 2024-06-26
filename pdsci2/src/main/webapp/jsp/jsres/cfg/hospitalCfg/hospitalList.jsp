
<script type="text/javascript">
	$(document).ready(function(){
		initCheck();
	});

</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<%--<colgroup>--%>
			<%--<col width="20%" />--%>
			<%--<col width="15%" />--%>
			<%--<col width="10%" />--%>
			<%--<col width="10%" />--%>
			<%--<col width="15%" />--%>
			<%--<col width="15%" />--%>
			<%--<col width="15%" />--%>
		<%--</colgroup>--%>
		<tr>
			<th>培训基地名称</th>
			<th>基地代码</th>
			<th>过程管理</th>
			<th>数据导入权限</th>
			<th>出科考核试卷下载</th>
			<th>自主出卷</th>
			<th>付费功能时效设置</th>
			<th>功能管理</th>
			<th>是否提交
				<br>
				<input type="checkbox" name="isSubmitName"/>
			</th>
			<th>审核状态</th>
		</tr>
		<c:forEach items="${sysOrgList }" var="sysOrg">
			<tr>
				<td>${sysOrg.orgName }</td>
				<td>${sysOrg.orgCode }</td>
				<td>
					<c:set var="key1" value="jsres_${sysOrg.orgFlow }_guocheng"/>
					<input id="${sysOrg.orgFlow }_guocheng" name="guoCheng" <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_guocheng');"/>
				</td>
				<td>
					<c:set var="key1" value="jsres_${sysOrg.orgFlow }_daoru"/>
					<input id="${sysOrg.orgFlow }_daoru" name="daoru" <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_daoru');"/>
				</td>
				<td>
					<c:set var="key1" value="jsres_${sysOrg.orgFlow }_downExamFile"/>
					<input id="${sysOrg.orgFlow }_downExamFile" name="downExamFile" <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_downExamFile');"/>
				</td>
				<td>
					<c:set var="key1" value="jsres_${sysOrg.orgFlow }_createExam"/>
					<input id="${sysOrg.orgFlow }_createExam" name="createExam" <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_createExam');"/>
				</td>
				<td>
					<a orgflow="${sysOrg.orgFlow }" style="cursor:default;color: blue;" onclick="getOrgDate('${sysOrg.orgFlow}')">时效设置</a>
				</td>
				<td>
					<a id="${sysOrg.orgFlow }_MENU"  orgflow="${sysOrg.orgFlow }" style="cursor:default;color: grey;"  onclick="">功能配置</a>
				</td>
				<td>
					<c:if test="${sysOrg.isSubmitId eq 'NotSubmit' or empty sysOrg.isSubmitId }">
						<c:set var="key1" value="jsres_${sysOrg.orgFlow }_guocheng"/>
						<c:set var="key2" value="jsres_${sysOrg.orgFlow }_daoru"/>
						<c:set var="key3" value="jsres_${sysOrg.orgFlow }_downExamFile"/>
						<c:set var="key4" value="jsres_${sysOrg.orgFlow }_createExam"/>
						<c:if test="${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y or pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y
									or pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y or pdfn:jsresPowerCfgMap(key4)==GlobalConstant.FLAG_Y}">
							<input type="button" value="提交" class="" onclick="updateOrgSubmitOne('${sysOrg.orgFlow}')"/>
							<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${sysOrg.orgFlow}" style="display: none"/>
						</c:if>

					</c:if>
					<c:if test="${sysOrg.isSubmitId eq 'Submit' and sysOrg.checkStatusId ne 'UnPassed'}">
						已提交
					</c:if>
					<c:if test="${sysOrg.isSubmitId eq 'Submit' and sysOrg.checkStatusId eq 'UnPassed'}">
						<input type="button" value="重新提交" class="" onclick="updateOrgSubmitOne('${sysOrg.orgFlow}')"/>
						<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${sysOrg.orgFlow}" style="display: none"/>
					</c:if>
				</td>
				<td>${sysOrg.checkStatusName }</td>
			</tr>
		</c:forEach>
		<c:if test="${empty sysOrgList}">
		<tr>
			<td colspan="9" style="border:none;">暂无记录！</td>
		</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(sysOrgList)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>