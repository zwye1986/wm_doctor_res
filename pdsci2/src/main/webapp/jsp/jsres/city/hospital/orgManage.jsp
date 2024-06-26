<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$(":radio").attr("disabled",true);
		$(":input").attr("readonly",true);
	});
function auditStatus(baseFlow,status){
	var s="通过";
	if(status=='${GlobalConstant.FLAG_N}'){
		s="不通过";
	}
	jboxConfirm("确认"+s+"？",function(){
		var data={
				"baseFlow":baseFlow,
				"status":status
			};
		jboxPost("<s:url value='/jsres/base/baseAudit'/>" , data , function(resp){
			if("${GlobalConstant.OPRE_SUCCESSED}"==resp){
				setTimeout(function(){
					window.parent.auditHospitals();
					loadInfo('${GlobalConstant.ORG_MANAGE}','${param.baseFlow}');
				},1000);
			}
		} , null , true);
	});
}
</script>
</head>
<body>
	<div class="infoAudit"  >
		<div class="div_table">
		  <h4>组织管理（住院医师培训组织管理机构成员及职责）</h4>
		  <input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
		   <table border="0" cellspacing="0" cellpadding="0" class="grid">
		   <colgroup>
		     <col width="10%"/>
		     <col width="10%"/>
		     <col width="10%"/>
		     <col width="10%"/>
		     <col width="10%"/>
		     <col width="20%"/>
		     <col width="10%"/>
		     <col width="10%"/>
		     <col width="10%"/>
		   </colgroup>
		      <tr>
		       <th>姓名</th>
		       <th>性别</th>
		       <th>年龄</th>
		       <th>专业</th>
		       <th>最高学位</th>
		       <th>科室</th>
		       <th>职务</th>
		       <th>专职/兼职</th>
		       <th>联系电话</th>
		     </tr>
		      <c:forEach var="person" items="${organizationManage.organizationPersonList}" varStatus="status">
		     <tr>
		    	<td>${person.name }</td>
		    	<td>${person.sex }</td>
		    	<td>${person.age }</td>
		    	<td>${person.profession }</td>
		    	<td>${person.tallStudy }</td>
		    	<td>${person.dept }</td>
		    	<td>${person.job }</td>
		    	<td>${person.partOrAllJob }</td>
		    	<td>${person.telephone }</td>
		     </tr>
		     </c:forEach>
		      <c:if test="${ empty organizationManage.organizationPersonList}">
		      <tr>
		      	<td colspan="10">无记录</td>
		      </tr>
		      </c:if>
		     <tr>
		       <td colspan="9" style="text-align:left;">现有住院医师培训相关规章制度、培训实施计划、考试考核等（请列出具体名称）：</td>
		     </tr>
		      <tr>
		        <td colspan="10" ><textarea readonly="readonly">${organizationManage.info}</textarea></td>
		      </tr>
 			</table>
			<h4>附件列表</h4>
			<table id="filesTable" border="0" cellpadding="0" cellspacing="0" class="grid" id="table3" >

				<c:forEach items="${files}" var="file">
					<c:if test="${file.fileUpType == 'files'}">
						<tr>
							<td style="text-align: left;padding-left: 10px;">
								<a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${file.fileName}</a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${empty files}">
					<tr>
						<td style="text-align: left;padding-left: 10px;">
							暂未上传文件
						</td>
					</tr>
				</c:if>
			</table>
			<h4>培训机构</h4>
			<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table4">
				<colgroup>
					<col width="30%"/>
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
				</colgroup>
				<tr>
					<td>培训领导小组</td>
					<td>
						<label><input name="organizationManage.leaderGroup" type="radio" value="${ organizationManage.leaderGroup}" <c:if test="${organizationManage.leaderGroup ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.leaderGroup" type="radio" value="${ organizationManage.leaderGroup}" <c:if test="${organizationManage.leaderGroup ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
					<td>专家委员会</td>
					<td>
						<label><input name="organizationManage.expertCommittee" type="radio" value="${ organizationManage.expertCommittee}" <c:if test="${organizationManage.expertCommittee ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.expertCommittee" type="radio" value="${ organizationManage.expertCommittee}" <c:if test="${organizationManage.expertCommittee ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td>培训管理职能部门</td>
					<td>
						<label><input name="organizationManage.managementDepartment" type="radio" value="${ organizationManage.managementDepartment}" <c:if test="${organizationManage.managementDepartment ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.managementDepartment" type="radio" value="${ organizationManage.managementDepartment}" <c:if test="${organizationManage.managementDepartment ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
					<td>专职管理人员</td>
					<td>
						<label><input name="organizationManage.managementPersonnel" type="radio" value="${ organizationManage.managementPersonnel}" <c:if test="${organizationManage.managementPersonnel ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.managementPersonnel" type="radio" value="${ organizationManage.managementPersonnel}" <c:if test="${organizationManage.managementPersonnel ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td>专业基地管理</br>主任负责制</td>
					<td>
						<label><input name="organizationManage.directorResponsibility" type="radio" value="${ organizationManage.directorResponsibility}" <c:if test="${organizationManage.directorResponsibility ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.directorResponsibility" type="radio" value="${ organizationManage.directorResponsibility}" <c:if test="${organizationManage.directorResponsibility ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
					<td>专业基地管理</br>专/兼职秘书</td>
					<td>
						<label><input name="organizationManage.secretary" type="radio" value="${ organizationManage.secretary}" <c:if test="${organizationManage.secretary ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.secretary" type="radio" value="${ organizationManage.secretary}" <c:if test="${organizationManage.secretary ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						住院医师规范化培训组织管理机构及职责：
						<label><input name="organizationManage.orgManagement" type="radio" value="${ organizationManage.orgManagement}" <c:if test="${organizationManage.orgManagement ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.orgManagement" type="radio" value="${ organizationManage.orgManagement}" <c:if test="${organizationManage.orgManagement ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
			</table>
			<h4>培训制度</h4>
			<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table5">
				<colgroup>
					<col width="20%"/>
					<col width="50%"/>
					<col width="30%"/>
				</colgroup>
				<tr>
					<td colspan="3" style="text-align: left">请提供现有住院医师规范化培训相关规章制度，包括培训管理、培训考核、奖惩制度、人事管理制度、经费管理制度、培训工作会议记录、培训管理职能部门工作记录、培训方案、考核资料、档案、住培工作纳入培训基地(医院)绩效考核体系</td>
				</tr>
				<tr>
					<th>序号</th>
					<th>规章制度名称</th>
					<th>上传附件</th>
				</tr>
				<c:forEach var="trainingSystem" items="${organizationManage.organizationTrainingSystemList}" varStatus="status">
					<tr>
						<td>${trainingSystem.id }</td>
						<td>
							<c:set var="file" value="${fileMap[trainingSystem.appendix]}"></c:set>
							<c:if test="${not empty file}">
								<a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${trainingSystem.rulesAndRegulations }</a>
							</c:if>
							<c:if test="${empty file}">
								${trainingSystem.rulesAndRegulations }
							</c:if>
						</td>
						<td>
							<c:if test="${not empty file}">已上传</c:if>
							<c:if test="${empty file}">暂未上传</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${ empty organizationManage.organizationTrainingSystemList}">
					<tr>
						<td colspan="10">无记录</td>
					</tr>
				</c:if>
			</table>
			<h4>工作情况</h4>
			<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table6">
				<colgroup>
					<col width="20%"/>
					<col width="50%"/>
					<col width="30%"/>
				</colgroup>
				<tr>
					<td colspan="3" style="text-align: left">近三年住院医师培训工作计划、总结、培训名单、人数及考核成绩，本院住院医师培训率，接收外单位培训任务。</td>
				</tr>
				<tr>
					<th>序号</th>
					<th>文件名称</th>
					<th>上传附件</th>
				</tr>
				<c:forEach var="workCondition" items="${organizationManage.organizationWorkConditionList}" varStatus="status">
					<tr>
						<td>${workCondition.id }</td>
						<td>
							<c:set var="file" value="${fileMap[workCondition.appendix]}"></c:set>
							<c:if test="${not empty file}">
								<a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}" target="_blank">${workCondition.fileName }</a>
							</c:if>
							<c:if test="${empty file}">
								${workCondition.fileName }
							</c:if>
						</td>
						<td>
							<c:if test="${not empty file}">已上传</c:if>
							<c:if test="${empty file}">暂未上传</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${ empty organizationManage.organizationWorkConditionList}">
					<tr>
						<td colspan="10">无记录</td>
					</tr>
				</c:if>
			</table>
			<h4>住院医师规范化培训工作经验</h4>
			<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table7">
				<colgroup>
					<col width="30%"/>
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
				</colgroup>
				<tr>
					<td style="text-align: left" colspan="4">不同人员对住院医师培训工作效果的评价与反馈意见：</td>
				</tr>
				<tr>
					<td>培训对象</td>
					<td>
						<label><input name="organizationManage.trainees" type="radio" value="${ organizationManage.trainees}" <c:if test="${organizationManage.trainees ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.trainees" type="radio" value="${ organizationManage.trainees}" <c:if test="${organizationManage.trainees ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
					<td>指导医师</td>
					<td>
						<label><input name="organizationManage.instructor" type="radio" value="${ organizationManage.instructor}" <c:if test="${organizationManage.instructor ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.instructor" type="radio" value="${ organizationManage.instructor}" <c:if test="${organizationManage.instructor ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td>专业基地</td>
					<td>
						<label><input name="organizationManage.professionalBase" type="radio" value="${ organizationManage.professionalBase}" <c:if test="${organizationManage.professionalBase ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.professionalBase" type="radio" value="${ organizationManage.professionalBase}" <c:if test="${organizationManage.professionalBase ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
					<td>用人单位</td>
					<td>
						<label><input name="organizationManage.employer" type="radio" value="${ organizationManage.employer}" <c:if test="${organizationManage.employer ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.employer" type="radio" value="${ organizationManage.employer}" <c:if test="${organizationManage.employer ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td style="text-align: left" colspan="4">不同人员对指导医师带教质量的评价与反馈意见：</td>
				</tr>
				<tr>
					<td>培训对象</td>
					<td>
						<label><input name="organizationManage.trainees1" type="radio" value="${ organizationManage.trainees1}" <c:if test="${organizationManage.trainees1 ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.trainees1" type="radio" value="${ organizationManage.trainees1}" <c:if test="${organizationManage.trainees1 ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
					<td>上级部门</td>
					<td>
						<label><input name="organizationManage.superiorDepartment" type="radio" value="${ organizationManage.superiorDepartment}" <c:if test="${organizationManage.superiorDepartment ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.superiorDepartment" type="radio" value="${ organizationManage.superiorDepartment}" <c:if test="${organizationManage.superiorDepartment ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td>同行</td>
					<td>
						<label><input name="organizationManage.peer" type="radio" value="${ organizationManage.peer}" <c:if test="${organizationManage.peer ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.peer" type="radio" value="${ organizationManage.peer}" <c:if test="${organizationManage.peer ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
			</table>
			<h4>其他相关措施</h4>
			<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table8">
				<colgroup>
					<col width="50%"/>
					<col width="50%"/>
				</colgroup>
				<tr>
					<td>培训基地能够提供给培训对象的生活补助</td>
					<td style="text-align: left">
						<label><input name="organizationManage.livingAllowance" type="radio" value="${ organizationManage.livingAllowance}" <c:if test="${organizationManage.livingAllowance ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.livingAllowance" type="radio" value="${ organizationManage.livingAllowance}" <c:if test="${organizationManage.livingAllowance ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
						&#12288;（金额：${organizationManage.allowanceMoney}元/人/月）
					</td>
				</tr>
				<tr>
					<td>与培训对象签订培训协议</td>
					<td style="text-align: left">
						<label><input name="organizationManage.trainingAgreement" type="radio" value="${ organizationManage.trainingAgreement}" <c:if test="${organizationManage.trainingAgreement ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.trainingAgreement" type="radio" value="${ organizationManage.trainingAgreement}" <c:if test="${organizationManage.trainingAgreement ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td>协助解决招收社会学员的档案和工龄</td>
					<td style="text-align: left">
						<label><input name="organizationManage.archivesAndAge" type="radio" value="${ organizationManage.archivesAndAge}" <c:if test="${organizationManage.archivesAndAge ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.archivesAndAge" type="radio" value="${ organizationManage.archivesAndAge}" <c:if test="${organizationManage.archivesAndAge ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td>协助解决招收社会学员的社会保障</td>
					<td style="text-align: left">
						<label><input name="organizationManage.socialSecurity" type="radio" value="${ organizationManage.socialSecurity}" <c:if test="${organizationManage.socialSecurity ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.socialSecurity" type="radio" value="${ organizationManage.socialSecurity}" <c:if test="${organizationManage.socialSecurity ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td>解决培训对象住宿</td>
					<td style="text-align: left">
						<label><input name="organizationManage.stay" type="radio" value="${ organizationManage.stay}" <c:if test="${organizationManage.stay ==GlobalConstant.SOLVE_ALL }">checked="checked"</c:if> />&#12288;全部解决&#12288;</label>
						<label><input name="organizationManage.stay" type="radio" value="${ organizationManage.stay}" <c:if test="${organizationManage.stay ==GlobalConstant.SOLVE_PART }">checked="checked"</c:if> />&#12288;部分解决&#12288;</label>
						<label><input name="organizationManage.stay" type="radio" value="${ organizationManage.stay}" <c:if test="${organizationManage.stay ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<td>协助解决培训对象的医师资格考试和执业注册</td>
					<td style="text-align: left">
						<label><input name="organizationManage.registrationForPractitioners" type="radio" value="${ organizationManage.registrationForPractitioners}" <c:if test="${organizationManage.registrationForPractitioners ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="organizationManage.registrationForPractitioners" type="radio" value="${ organizationManage.registrationForPractitioners}" <c:if test="${organizationManage.registrationForPractitioners ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
			</table>
		</div>
		<div class="btn_info">
		 	<jsp:include page='/jsp/jsres/province/hospital/passBtn.jsp'/>
	    </div>
	</div>
  </body>
</html>