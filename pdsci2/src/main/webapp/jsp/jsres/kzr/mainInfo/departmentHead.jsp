<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	.div_table h4 {
		color: #000000;
		font: 15px 'Microsoft Yahei';
		font-weight: 500;
	}
	.base_info th {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 500;
	}
	.base_info td,.base_info td label, .base_info td input, .base_info td textarea {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 400;
	}
</style>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<form id='BaseInfoForm' style="position:relative;">
	<input type="hidden" name="resBaseSpeDept.orgFlow" value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
	<input type="hidden" name="resBaseSpeDept.deptFlow" value="${deptFlow}"/>
	<input type="hidden" name="flag" value="${GlobalConstant.DEPARTMENT_HEAD}"/>
	<div class="main_bd"
			<c:if test="${isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;height: 600px" </c:if>
			<c:if test="${isJoin eq 'Y'}"> style="position: relative;overflow-y: auto;height: 625px" </c:if>  >
		<div class="div_table">
			<h4>科室负责人情况</h4>
			<c:if test="${viewFlag ne 'Y'}">
				<img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('DepartmentHead','${deptFlow}');"
					 style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
			</c:if>
			<table border="0" cellspacing="0" cellpadding="0" class="base_info">
				<colgroup>
					<col width="26%"/>
					<col width="12%"/>
					<col width="8%"/>
					<col width="12%"/>
					<col width="8%"/>
					<col width="12%"/>
					<col width="8%"/>
					<col width="12%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>姓名：</th>
					<td>${departmentHeadForm.userName}</td>
					<th>性别：</th>
					<td>
						<c:if test="${departmentHeadForm.userSex ==GlobalConstant.FLAG_Y }">男</c:if>
						<c:if test="${departmentHeadForm.userSex ==GlobalConstant.FLAG_N }">女</c:if>
					</td>
					<th>年龄：</th>
					<td>${departmentHeadForm.userAge}</td>
					<th>学历：</th>
					<td>${departmentHeadForm.xl}</td>
				</tr>
				<tr>
					<th>学位：</th>
					<td>${departmentHeadForm.xw}</td>
					<th>职称：</th>
					<td>${departmentHeadForm.zc}</td>
					<th>职务：</th>
					<td colspan="3">${departmentHeadForm.zw}</td>
				</tr>
				<tr>
					<th>导师情况：</th>
					<td colspan="7">
						<c:if test="${departmentHeadForm.dsqk ==GlobalConstant.FLAG_Y }">&#12288;硕导&#12288;</c:if>
						<c:if test="${departmentHeadForm.dsqk ==GlobalConstant.FLAG_N }">&#12288;博导&#12288;</c:if>
						<c:if test="${departmentHeadForm.dsqk ==GlobalConstant.FLAG_F }">&#12288;其他&#12288;</c:if>
						${departmentHeadForm.dsqkContext}
					</td>
				</tr>
				<tr>
					<th>从事住院医师规范化培训工作年限：</th>
					<td colspan="7">
						${departmentHeadForm.cszy}
					</td>
				</tr>
				<tr>
					<th>从事本专业临床医疗、科研和教学工作经验：</th>
					<td colspan="7">
						${departmentHeadForm.cspx}
					</td>
				</tr>
				<tr>
					<th>教学简历：</th>
					<td colspan="7"><textarea readonly>${departmentHeadForm.jxjl}</textarea></td>
				</tr>
				<tr>
					<th>工作简历：</th>
					<td colspan="7"><textarea readonly>${departmentHeadForm.gzjl}</textarea></td>
				</tr>
				<th colspan="8" style="text-align: left;padding-left: 5px;">获得的省、部级以上教学成果奖名称、级别及获奖年度（近三年）</th>
				<tr>
					<td colspan="8" style="padding-left: 0px;"><textarea readonly>${departmentHeadForm.jx}</textarea></td>
				</tr>
				<th colspan="8" style="text-align: left;padding-left: 5px;">获得的省、部级以上科研成果奖名称、级别及获奖年度（近三年）</th>
				<tr>
					<td colspan="8" style="padding-left: 0px;"><textarea readonly>${departmentHeadForm.ky}</textarea></td>
				</tr>
				<th colspan="8" style="text-align: left;padding-left: 5px;">承担的省、部级以上本专业的临床教学、科研项目（近三年）</th>
				<tr>
					<td colspan="8" style="padding-left: 0px;"><textarea readonly>${departmentHeadForm.lc}</textarea></td>
				</tr>
				<th colspan="8" style="text-align: left;padding-left: 5px;">参加住院医师规范化培训相关工作情况（包括标准制定、基地评估与考核等）</th>
				<tr>
					<td colspan="8" style="padding-left: 0px;"><textarea readonly>${departmentHeadForm.px}</textarea></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
<%--	<c:if test="${viewFlag ne 'Y'}">
		<div class="btn_info">
			<input type="button" class="btn_green"  value="编&#12288;辑"onclick="editInfo('DepartmentHead','${deptFlow}');"></input>
		</div>
	</c:if>--%>

</form>
