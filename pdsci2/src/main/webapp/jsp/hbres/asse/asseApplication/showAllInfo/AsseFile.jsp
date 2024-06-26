<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style>
	.grid th{
		border:1px solid #e7e7eb;
	}
	.grid td{
		border:1px solid #e7e7eb;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	jboxEndLoading();
});
function catalogue(processFlow,resultFlow,userFlow){
	var url = "<s:url value='/hbres/doctor/doctorRecruit/catalogue'/>?processFlow=" + processFlow+"&resultFlow="+resultFlow+"&userFlow="+userFlow;
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,"登记详情",1000,400);
}
</script>
<script type="text/javascript">
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
		if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
			return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
		} else {
			return this.replace(reallyDo, replaceWith);
		}
	}
</script>
<div class="main_bd" id="div_table_0" >
    <div id="" style="max-height: 420px;overflow: auto;">
		<div class="div_table">
			<h4>各科室轮转情况</h4>
        	<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%;padding:0px;margin: 0px;">
            <tr>
                <th style="width: 100px;min-width: 100px;max-width: 100px;">轮转类型</th>
                <th style="width: 100px;min-width: 100px;max-width: 100px;">标准科室</th>
                <th style="width: 100px;min-width: 100px;max-width: 100px;">要求时间/实<br>际时间(月)</th>
				<th style="width: 100px;min-width: 100px;max-width: 100px;">轮转科室</th>
				<th style="width: 200px;min-width: 200px;max-width: 200px;">轮转时间</th>
                <th style="width: 400px;min-width: 400px;max-width: 400px;">出科考核表/出科小结表</th>
				<th style="width: 60px;min-width: 60px;max-width: 60px;">总比例</th>
				<th style="width: 60px;min-width: 60px;max-width: 60px;">补填比例</th>
				<th style="width: 60px;min-width: 60px;max-width: 60px;">审核比例</th>
            </tr>

				<c:set value="" var="lastGroupFlow"></c:set>
				<c:set value="" var="lastDeptFlow"></c:set>
				<c:set value="0" var="allSchMonth"></c:set>
				<c:set value="0" var="allRealMonth"></c:set>
				<c:forEach items="${groupList}" var="group" varStatus="groupStatus">
					<c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept" varStatus="deptStatus">
						<c:set value="${allSchMonth+0+dept.schMonth}" var="allSchMonth"></c:set>

						<c:set var="resultKey" value="${group.groupFlow}${dept.standardDeptId}"/>
						<c:set value="${allRealMonth+0+(empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey])}" var="allRealMonth"></c:set>

						<c:if test="${not empty resultMap[resultKey]}">
							<c:forEach items="${resultMap[resultKey]}" var="result" varStatus="resultStatus">
								<c:set var="bi" value="${biMap[result.resultFlow]}"></c:set>
								<c:set var="process" value="${processMap[result.resultFlow]}"></c:set>
								<c:choose>
									<c:when test="${!(lastGroupFlow eq group.groupFlow) and
								!(lastDeptFlow eq dept.recordFlow) and resultStatus.first
								}">
										<tr>
											<td rowspan="${(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))<=0?0:(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))}">
												<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
													${group.schStageName}：
												</c:if>
													${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】
												<br>(轮转时间：${group.schMonth}月
												<c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">
													&#12288;${group.selTypeName}：${group.deptNum}<c:if test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if>
												</c:if>)
											</td>
											<td rowspan="${deptRowSpan[resultKey]}">
													${dept.standardDeptName}
											</td>
											<td rowspan="${deptRowSpan[resultKey]}">${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}</td>
											<td>${result.schDeptName}</td>
											<td>${result.schStartDate}~${result.schEndDate}</td>
											<td>
												<c:set var="evalFile" value="${evaluationFileMap[result.resultFlow]}"></c:set>
												<c:set var="summarFile" value="${summaryFileMap[result.resultFlow]}"></c:set>
												<div style="margin-top:10px; ">
													<c:if test="${not empty evalFile.filePath}">
														<a href="${sysCfgMap['upload_base_url']}/${evalFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}"></a>&nbsp;
													</c:if>
													<c:if test="${not empty summarFile.filePath}">
														<a href="${sysCfgMap['upload_base_url']}/${summarFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}"></a>&nbsp;
													</c:if>
												</div>
											</td>
											<td><a onclick="catalogue('${process.processFlow}','${result.resultFlow}','${result.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a>
											</td>
											<td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
											<td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
										</tr>
									</c:when>
									<c:when test="${(lastGroupFlow eq group.groupFlow) and
								!(lastDeptFlow eq dept.recordFlow) and resultStatus.first
								}">
										<tr>
											<td rowspan="${deptRowSpan[resultKey]}">
													${dept.standardDeptName}
											</td>
											<td rowspan="${deptRowSpan[resultKey]}">${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}</td>
											<td>${result.schDeptName}</td>
											<td>${result.schStartDate}~${result.schEndDate}</td>
											<td>
												<c:set var="evalFile" value="${evaluationFileMap[result.resultFlow]}"></c:set>
												<c:set var="summarFile" value="${summaryFileMap[result.resultFlow]}"></c:set>
												<div style="margin-top:10px; ">
													<c:if test="${not empty evalFile.filePath}">
														<a href="${sysCfgMap['upload_base_url']}/${evalFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}"></a>&nbsp;
													</c:if>
													<c:if test="${not empty summarFile.filePath}">
														<a href="${sysCfgMap['upload_base_url']}/${summarFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}"></a>&nbsp;
													</c:if>
												</div>
											</td>
											<td><a onclick="catalogue('${process.processFlow}','${result.resultFlow}','${result.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a></td>
											<td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
											<td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
										</tr>
									</c:when>
									<c:when test="${(lastGroupFlow eq group.groupFlow) and
								(lastDeptFlow eq dept.recordFlow) and !(resultStatus.first)}">
										<tr>
											<td>${result.schDeptName}</td>
											<td>${result.schStartDate}~${result.schEndDate}</td>
											<td>
												<c:set var="evalFile" value="${evaluationFileMap[result.resultFlow]}"></c:set>
												<c:set var="summarFile" value="${summaryFileMap[result.resultFlow]}"></c:set>
												<div style="margin-top:10px; ">
													<c:if test="${not empty evalFile.filePath}">
														<a href="${sysCfgMap['upload_base_url']}/${evalFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${evalFile.filePath}"></a>&nbsp;
													</c:if>
													<c:if test="${not empty summarFile.filePath}">
														<a href="${sysCfgMap['upload_base_url']}/${summarFile.filePath}" target="_blank"><img width="80px" height="80px"  src="${sysCfgMap['upload_base_url']}/${summarFile.filePath}"></a>&nbsp;
													</c:if>
												</div>
											</td>
											<td><a onclick="catalogue('${process.processFlow}','${result.resultFlow}','${result.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a></td>
											<td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
											<td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>

										</tr>
									</c:when>
								</c:choose>
								<c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>
								<c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>
							</c:forEach>
						</c:if>
						<c:if test="${empty resultMap[resultKey]}">
							<tr>
								<c:if test="${deptStatus.first}">
									<td rowspan="${(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))<=0?0:(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))}">
										<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
											${group.schStageName}：
										</c:if>
											${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】
										<br>(轮转时间：${group.schMonth}月
										<c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">
											&#12288;${group.selTypeName}：${group.deptNum}<c:if test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if>
										</c:if>)
									</td>
								</c:if>
								<td>
										${dept.standardDeptName}
								</td>
								<td>${dept.schMonth}/0</td>
								<td></td>
								<td></td>
								<td>

								</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</c:if>
						<c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>
						<c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>
					</c:forEach>
				</c:forEach>
			<tr>
				<td colspan="2">合计时间</td>
				<td colspan="">${allSchMonth}/<fmt:formatNumber type="number" value="${allRealMonth}" maxFractionDigits="2"/></td>
				<td colspan="3"><%--平均比例--%></td>
				<td ><%--${empty avgBiMap.avgComplete ? 0:avgBiMap.avgComplete}%--%></td>
				<td ><%--${empty avgBiMap.avgOutComplete ? 0:avgBiMap.avgOutComplete}%--%></td>
				<td ><%--${empty avgBiMap.avgAudit ? 0: avgBiMap.avgAudit}%--%></td>
			</tr>
        </table>
    	</div>
		<div class="div_table">
			<h4>毕业证书</h4>
			<div style="width: 100%;height: 400px">
				<c:if test="${not empty jsresGraduationApply.certificateUri}">
					<a href="${sysCfgMap['upload_base_url']}/${jsresGraduationApply.certificateUri}" target="_blank">
						<img src="${sysCfgMap['upload_base_url']}/${jsresGraduationApply.certificateUri}" style="width: 100%;height: 100%;">
					</a>
				</c:if>
				<c:if test="${empty jsresGraduationApply.certificateUri}">
					暂未上传
				</c:if>
			</div>
    	</div>
		<div class="div_table">
				<h4>执业医师资格证书</h4>
				<div style="width: 100%;height: 400px">
					<c:if test="${not empty jsresGraduationApply.graduationMaterialUri}">
						<a href="${sysCfgMap['upload_base_url']}/${jsresGraduationApply.graduationMaterialUri}" target="_blank">
							<img src="${sysCfgMap['upload_base_url']}/${jsresGraduationApply.graduationMaterialUri}" style="width: 100%;height: 100%;">
						</a>
					</c:if>
					<c:if test="${empty jsresGraduationApply.graduationMaterialUri}">
						暂未上传
					</c:if>
				</div>
			</div>
		</div>
    </div>
</div>
    
