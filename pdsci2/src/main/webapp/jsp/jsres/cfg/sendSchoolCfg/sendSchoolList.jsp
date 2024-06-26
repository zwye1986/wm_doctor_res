
<script type="text/javascript">
	$(document).ready(function(){
		initCheck();
	});
</script>
	<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
		<%--<colgroup>--%>
			<%--<col width="40%" />--%>
			<%--<col width="30%" />--%>
			<%--<col width="30%" />--%>
		<%--</colgroup>--%>
		<tr>
			<th>派送学校名称</th>
			<th>过程管理</th>
			<th>功能管理</th>
			<th>是否提交
				<br>
				<input type="checkbox" name="isSubmitName"/>
			</th>
			<th>审核状态</th>
		</tr>
		<c:forEach items="${dictList}" var="dict" varStatus="num">
			<tr id="${dict.dictFlow}">
				<td>${dict.dictName}</td>
				<td>
					<c:set var="key" value="jsres_sendSchool_gc_${dict.dictId}"/>
					<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="guoCheng"  id="jsres_sendSchool_gc_${dict.dictId }" value="${dict.dictId}" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''}
						   onchange="operPerm(this,'${dict.dictFlow }','${dict.dictId }','jsres_sendSchool_gc_');"/>
				</td>
				<td>
					<c:set value="openMenuPermission('${dict.dictId}')" var="func"></c:set>
					<a id="${dict.dictId }_MENU"  dictId="${dict.dictId }"style="cursor:default;color: ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ? 'blue':'grey'};"
					   onclick="${pdfn:jsresPowerCfgMap(key) ? func:""}" >功能配置</a>
				</td>
				<td>
					<c:if test="${dict.isSubmitId eq 'NotSubmit' or empty dict.isSubmitId }">
						<c:set var="key1" value="jsres_sendSchool_gc_${dict.dictId}"/>

						<c:if test="${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y}">
							<input type="button" value="提交" class="" onclick="updateSchoolSubmitOne('${dict.dictFlow}')"/>
							<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${dict.dictFlow}" style="display: none"/>
						</c:if>

					</c:if>
					<c:if test="${dict.isSubmitId eq 'Submit' and dict.checkStatusId ne 'UnPassed'}">
						已提交
					</c:if>
					<c:if test="${dict.isSubmitId eq 'Submit' and dict.checkStatusId eq 'UnPassed'}">
						<input type="button" value="重新提交" class="" onclick="updateSchoolSubmitOne('${dict.dictFlow}')"/>
						<input type="checkbox" id="isSubmitId" name="isSubmitId" value="${dict.dictFlow}" style="display: none"/>
					</c:if>
				</td>
				<td>${dict.checkStatusName }</td>
			</tr>
		</c:forEach>
		<c:if test="${empty dictList}">
			<tr>
				<td colspan="6" style="border:none;">暂无记录！</td>
			</tr>
		</c:if>
	</table>
	<div>
		<c:set var="pageView" value="${pdfn:getPageView(dictList)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</div>