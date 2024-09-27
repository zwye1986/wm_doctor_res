<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="false"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/artDialog/css/ui-dialog.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/artDialog/dialog-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/artDialog/dialog-plus-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/common-art.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	.div_table h4 {
		color: #000000;
		font: 15px 'Microsoft Yahei';
		font-weight: 500;
	}
	.base_info th {
		border: 1px solid white;
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 500;
	}
	.base_info td {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 400;
	}
</style>
<script type="text/javascript">
	<%--$(document).ready(function () {--%>
		<%--toPage(1);--%>
	<%--});--%>

	<%--function toPage(page) {--%>
		<%--if(page!=undefined){--%>
			<%--$("#currentPage").val(page);--%>
		<%--}--%>
		<%--searchInfo();--%>
	<%--}--%>
	<%--function searchInfo() {--%>
		<%--var url="<s:url value='/jsres/kzr/guidingPhysicianList'/>";--%>
		<%--jboxPostLoad("doctorListZi",url , $("#BaseInfoForm").serialize(), false);--%>
	<%--}--%>


	function viewAttachment(recordFlow){
		var url = "<s:url value='/jsres/manage/attachment'/>?recFlow="+recordFlow + "&recType=szzsAttach&readonly=Y";
		jboxOpen(url, '证书附件',600,550);
	}
</script>
<form id='BaseInfoForm'
		<c:if test="${isJoin ne 'Y' and isglobal ne 'Y'}"> style="position: relative;" </c:if>
		<c:if test="${isJoin eq 'Y'}"> style="position: relative;overflow-y: auto;height: 605px" </c:if>
		<c:if test="${isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;height: 580px" </c:if>
>
	<input type="hidden" name="deptFlow" value="${deptFlow}"/>
	<input type="hidden" name="orgFlow" value="${orgFlow}"/>
	<input id="currentPage" type="hidden" name="currentPage" value=""/>
	<div class="div_table">
		<table cellspacing="0" cellpadding="0" class="base_info">
			<colgroup>
				<col width="7%"/>
				<col width="7%"/>
				<col width="7%"/>
				<col width="7%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="8%"/>
				<col width="13%"/>
				<col width="9%"/>
				<col width="13%"/>
				<col width="9%"/>
			</colgroup>
			<tr>
				<th rowspan="2" style="text-align: center;border-left: none;">姓名</th>
				<th rowspan="2" style="text-align: center;">性别</th>
				<th rowspan="2" style="text-align: center;">年龄</th>
				<th rowspan="2" style="text-align: center;">学历</th>
				<th rowspan="2" style="text-align: center;">所在科室</th>
				<th colspan="3" style="text-align: center;">工作经历</th>
				<th colspan="3" style="text-align: center;border-right: none;">参加省级及以上住院医师规范化培训师资培训</th>
			</tr>
			<tr>
				<th rowspan="1" style="text-align: center;">专业技术职务</th>
				<th rowspan="1" style="text-align: center;">任现职务年限</th>
				<th rowspan="1" style="text-align: center;">从事本专业临床工作年限</th>
				<th colspan="1" style="text-align: center;">证书等级</th>
				<th colspan="1" style="text-align: center;">证书取得时间</th>
				<th colspan="1" style="text-align: center;">证书</th>
			</tr>
			<c:forEach items="${list}" var="t">
				<tr>
					<td style="text-align: center">${t.doctorName}</td>
					<td style="text-align: center">${t.sexName}</td>
					<td style="text-align: center">${t.doctorAge}</td>
					<td style="text-align: center">${t.doctorEdu}</td>
					<td style="text-align: center">${t.deptName}</td>
					<td style="text-align: center">${t.technicalTitle}</td>
					<td style="text-align: center">${t.officeYear}</td>
					<td style="text-align: center">${t.workYear}</td>

					<td style="text-align: center">
						<c:forEach items="${teachingCertLevelEnumList}" var="certLevel">
							<c:if test="${t.teachingCertLevel eq certLevel.code}">${certLevel.name}</c:if>
						</c:forEach>
					</td>
					<td style="text-align: center">
							${t.certGrantedDate}
					</td>
					<td style="text-align: center">
						<a href="javascript:viewAttachment('${t.recordFlow}');" class="btn">查看</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr><td colspan="11" style="text-align: center">暂无数据</td></tr>
			</c:if>
		</table>
	</div>
	<%--<div id="doctorListZi">--%>

	<%--</div>--%>
</form>
