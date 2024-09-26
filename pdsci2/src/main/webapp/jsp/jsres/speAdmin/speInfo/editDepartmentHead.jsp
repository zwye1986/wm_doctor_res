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
<script type="text/javascript">

	$(document).ready(function () {
		if ($("#dsqk").prop('checked')){
			$("#dsqkContext").attr("readonly",false);
		}else {
			$("#dsqkContext").attr("readonly","readonly");
		}
	});

	function toEdit(obj,cls,type) {
		if ($("#"+obj).prop('checked')){
			$("#"+cls).attr("readonly",false);
		}else {
			$("#"+cls).attr("readonly","readonly");
		}
		if (type=='N'){
			$("#"+cls).val('');
		}
	}

	function saveBaseInfo() {
		if (!$("#BaseInfoForm").validationEngine("validate")) {
			$("#indexBody").scrollTop("0px");
			return false;
		}

		jboxPost("<s:url value='/jsres/speAdmin/saveBase'/>", $("#BaseInfoForm").serialize(), function (resp) {
			if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
				setTimeout(function(){
					$("#submitBtn").show();
					loadInfo('${GlobalConstant.DEPARTMENT_HEAD}','${deptFlow}');
				},1000);
			}
		}, null, true);
	}
</script>
   <input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
   <form id='BaseInfoForm' style="position:relative;">
	   <input type="hidden" name="resBaseSpeDept.orgFlow"
			  value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
	   <input type="hidden" name="resBaseSpeDept.speFlow" value="${speFlow}"/>
	   <input type="hidden" name="resBaseSpeDept.sessionNumber" value="${sessionNumber}"/>
	   <input type="hidden" name="flag" value="${GlobalConstant.DEPARTMENT_HEAD}"/>
		<div class="main_bd">
			<div class="div_table">
				<h4><span class="red">*</span>专业基地负责人情况</h4>
				<table border="0" cellspacing="0" cellpadding="0" class="base_info">
					<colgroup>
						<col width="25%"/>
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
							<th><span style="color: red">*</span>&nbsp;姓名：</th>
							<td><input type="text"  class="input validate[required]" name="departmentHeadForm.userName" style="width:100px;"
									   value="${departmentHeadForm.userName}"/></td>
							<th><span style="color: red">*</span>&nbsp;性别：</th>
							<td>
								<label><input name="departmentHeadForm.userSex" type="radio" value="${GlobalConstant.FLAG_Y}" class='validate[required]'
											  <c:if test="${departmentHeadForm.userSex ==GlobalConstant.FLAG_Y }">checked="checked"</c:if>
								/>&#12288;男&#12288;</label>
								<label><input name="departmentHeadForm.userSex" type="radio" value="${GlobalConstant.FLAG_N}" class='validate[required]'
											  <c:if test="${departmentHeadForm.userSex ==GlobalConstant.FLAG_N }">checked="checked"</c:if>
								/>&#12288;女</label>
							</td>
							<th><span style="color: red">*</span>&nbsp;年龄：</th>
							<td><input type="text"  class='input validate[required,custom[integer],min[0]]' style="width:100px;" name="departmentHeadForm.userAge"  value="${departmentHeadForm.userAge}"/></td>
							<th><span style="color: red">*</span>&nbsp;学历：</th>
							<td><input type="text"  class="input validate[required]" style="width:100px;" name="departmentHeadForm.xl"  value="${departmentHeadForm.xl}"/></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>&nbsp;学位：</th>
							<td><input type="text" class="input validate[required]"  name="departmentHeadForm.xw" style="width:100px;" value="${departmentHeadForm.xw}"/></td>
							<th><span style="color: red">*</span>&nbsp;职称：</th>
							<td><input type="text" class="input validate[required]" style="width:100px;" name="departmentHeadForm.zc"  value="${departmentHeadForm.zc}"/></td>
							<th><span style="color: red">*</span>&nbsp;职务：</th>
							<td colspan="3"><input type="text" class="input validate[required]" style="width:100px;" name="departmentHeadForm.zw"  value="${departmentHeadForm.zw}"/></td>
						</tr>
						<tr>
							<th><span class="red">*</span>导师情况：</th>
							<td colspan="7">
								<label><input name="departmentHeadForm.dsqk"  class='validate[required]' type="radio" onclick="toEdit('dsqk','dsqkContext','N');" value="${GlobalConstant.FLAG_Y}" <c:if test="${departmentHeadForm.dsqk ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;硕导&#12288;</label>
								<label><input name="departmentHeadForm.dsqk"  class='validate[required]' type="radio" onclick="toEdit('dsqk','dsqkContext','N');" value="${GlobalConstant.FLAG_N}" <c:if test="${departmentHeadForm.dsqk ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;博导&#12288;</label>
								<label><input name="departmentHeadForm.dsqk"  class='validate[required]' id="dsqk" onclick="toEdit('dsqk','dsqkContext','Y');" type="radio" value="${GlobalConstant.FLAG_F}" <c:if test="${departmentHeadForm.dsqk ==GlobalConstant.FLAG_F }">checked="checked"</c:if> />&#12288;其他</label>
								<input type="text"  class='input'style="width:100px;" id="dsqkContext"
									   name="departmentHeadForm.dsqkContext" value="${departmentHeadForm.dsqkContext}"/>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>从事住院医师规范化培训工作年限：</th>
							<td colspan="7">
								<input type="text"  class='input validate[custom[integer],min[0]]' style="width:100px;" name="departmentHeadForm.cszy"  value="${departmentHeadForm.cszy}"/>年
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>从事本专业临床医疗、科研和教学工作经验：</th>
							<td colspan="7">
								<input type="text"  class='input validate[custom[integer],min[0]]' style="width:100px;" name="departmentHeadForm.cspx"  value="${departmentHeadForm.cspx}"/>年
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>教学简历：</th>
							<td colspan="7"><textarea name="departmentHeadForm.jxjl" class="validate[required]">${departmentHeadForm.jxjl}</textarea></td>
						</tr>
						<tr>
							<th><span class="red">*</span>工作简历：</th>
							<td colspan="7"><textarea name="departmentHeadForm.gzjl" class="validate[required]">${departmentHeadForm.gzjl}</textarea></td>
						</tr>
						<th colspan="8" style="text-align: left;padding-left: 5px;">
							获得的省、部级以上教学成果奖名称、级别及获奖年度（近3年）
						</th>
						<tr>
							<td colspan="8" style="padding-left: 0px;"><textarea name="departmentHeadForm.jx" >${departmentHeadForm.jx}</textarea></td>
						</tr>
						<th colspan="8" style="text-align: left;padding-left: 5px;">
							获得的省、部级以上科研成果奖名称、级别及获奖年度（近3年）
						</th>
						<tr>
							<td colspan="8" style="padding-left: 0px;"><textarea name="departmentHeadForm.ky" >${departmentHeadForm.ky}</textarea></td>
						</tr>
						<th colspan="8" style="text-align: left;padding-left: 5px;">
							承担的省、部级以上本专业的临床教学、科研项目（近3年）
						</th>
						<tr>
							<td colspan="8" style="padding-left: 0px;"><textarea name="departmentHeadForm.lc" >${departmentHeadForm.lc}</textarea></td>
						</tr>
						<th colspan="8" style="text-align: left;padding-left: 5px;">
							参加住院医师规范化培训相关工作情况（包括标准制定、基地评估与考核等）
						</th>
						<tr>
							<td colspan="8" style="padding-left: 0px;"><textarea name="departmentHeadForm.px" >${departmentHeadForm.px}</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="btn_info">
			<input class="btn_green"  onclick="saveBaseInfo()" type="button" value="保&#12288;存"/>
		</div>
   </form>
