<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="true"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<script type="text/javascript">
		function nextOpt(step){
			if(false==$("#projForm").validationEngine("validate")){
				return false;
			}
			var form = $('#projForm');
			var action = form.attr('action');
			action+="?nextPageName="+step;
			form.attr("action" , action);
			form.submit();
		}

	</script>
	<style type="text/css">
		.bs_tb tbody td input{text-align: left;margin-left: 10px;}
	</style>
</head>
<body>
<div id="main">
	<div class="mainright">
		<div class="content">
			<div style="margin-top: 10px;">
				<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
					<input type="hidden" id="pageName" name="pageName" value="step2" />
					<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
					<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
					<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

					<font style="font-size: 14px; font-weight:bold;color: #333; ">一、领军人才（创新团队带头人）个人概况</font>
					<table class="basic" style="width: 100%; margin-top: 10px;">
						<tr>
							<th colspan="6" style="text-align: left;padding-left: 20px;">个人情况</th>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>姓名：</td>
							<td><input type="text" name="name" value="${resultMap.name}" class="inputText validate[required]" style="width: 80%"/></td>
							<td style="text-align: right;"><font color="red">*</font>性别：</td>
							<td>
								<select name="sexId" class="inputText validate[required]" style="width: 80%;">
									<option value="">请选择</option>
									<c:forEach var="sex" items="${userSexEnumList}">
										<c:if test="${sex.name != userSexEnumUnknown.name}">
											<option value="${sex.name}"
												<c:if test="${resultMap.sexId eq sex.name}">selected="selected"</c:if>>${sex.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;"><font color="red">*</font>民族：</td>
							<td><input type="text" name="nation" value="${resultMap.nation}" class="inputText validate[required]" style="width: 80%"/></td>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>出生年月：</td>
							<td><input class="inputText ctime validate[required]" type="text" name="birthday" value="${resultMap.birthday}" onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 80%"/></td>
							<td style="text-align: right;"><font color="red">*</font>最终学位：</td>
							<td>
								<select name="finalDegreeId" class="inputText validate[required]" style="width: 80%;">
									<option value="">请选择</option>
									<c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
										<option value="${dict.dictName}"
											<c:if test="${resultMap.finalDegreeId eq dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;"><font color="red">*</font>最终学位授予时间：</td>
							<td><input class="inputText ctime validate[required]" type="text" name="awardTime" value="${resultMap.awardTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 80%"/></td>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>最终学位授予单位：</td>
							<td><input type="text" name="awardOrg" value="${resultMap.awardOrg}" class="inputText validate[required]" style="width: 80%"/></td>
							<td style="text-align: right;">目前从事专业：</td>
							<td><input type="text" name="engageMajor" value="${resultMap.engageMajor}" class="inputText" style="width: 80%"/></td>
							<td style="text-align: right;">是否为研究生导师：</td>
							<td colspan="3">
								<input type="radio" name="isTutor" id="tutor_bd" value="博导" class="inputText" <c:if test="${resultMap.isTutor eq '博导'}">checked="checked"</c:if>/>
								<label for="tutor_bd">&nbsp;博导</label>&nbsp;
								<input type="radio" name="isTutor" id="tutor_sd" value="硕导" class="inputText" <c:if test="${resultMap.isTutor eq '硕导'}">checked="checked"</c:if>/>
								<label for="tutor_sd">&nbsp;硕导</label>
								<input type="radio" name="isTutor" id="tutor_none" value="否" class="inputText" <c:if test="${resultMap.isTutor eq '否'}">checked="checked"</c:if>/>
								<label for="tutor_none">&nbsp;否</label>&nbsp;
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">专业技术职称：</td>
							<td>
								<select name="technologyTitle" class="inputText" style="width: 80%;">
									<option value="">请选择</option>
									<c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
										<option value="${dict.dictName }"
											<c:if test="${resultMap.technologyTitle eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;">政治面貌：</td>
							<td><input type="text" name="political" value="${resultMap.political}" class="inputText" style="width: 80%"/></td>
							<td style="text-align: right;">行政职务：</td>
							<td><input type="text" name="administrativePost" value="${resultMap.administrativePost}" class="inputText" style="width: 80%"/></td>
						</tr>
						<tr>
							<td style="text-align: right;" colspan="2">是否为省级或省级以上人才培养对象：</td>
							<td colspan="4"><input type="text" name="cultivateObject" value="${resultMap.cultivateObject}" class="inputText" style="width: 80%"/></td>
						</tr>
						<tr>
							<th colspan="6" style="text-align: left;padding-left: 20px;">联系方式</th>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>电话：</td>
							<td><input type="text" name="telephone" value="${resultMap.telephone}" class="inputText validate[required,custom[phone]]" style="width: 80%"/></td>
							<td style="text-align: right;">传真：</td>
							<td><input type="text" name="fax" value="${resultMap.fax}" class="inputText" style="width: 80%"/></td>
							<td style="text-align: right;"><font color="red">*</font>Email：</td>
							<td><input type="text" name="mailbox" value="${resultMap.mailbox}" class="inputText validate[required,custom[email]]" style="width: 80%"/></td>
						</tr>
						<tr>
							<th colspan="6" style="text-align: left;padding-left: 20px;">所在单位</th>
						</tr>
						<tr>
							<td style="text-align: right;">单位名称：</td>
							<td colspan="2"><input type="text" name="companyPlace" value="${resultMap.companyPlace}" class="inputText" style="width: 80%"/></td>
							<td style="text-align: right;">隶属关系：</td>
							<td colspan="2"><input type="text" name="subordination" value="${resultMap.subordination}" class="inputText" style="width: 80%"/></td>
						</tr>
						<tr>
							<td style="text-align: right;">传真：</td>
							<td colspan="2"><input type="text" name="companyPlaceFax" value="${resultMap.companyPlaceFax}" class="inputText" style="width: 80%"/></td>
							<td style="text-align: right;">邮编：</td>
							<td colspan="2"><input type="text" name="postcode" value="${resultMap.postcode}" class="inputText" style="width: 80%"/></td>
						</tr>
						<tr>
							<td style="text-align: right;">通讯地址：</td>
							<td colspan="5"><input type="text" name="postalAddress" value="${resultMap.postalAddress}" class="inputText" style="width: 80%"/></td>
						</tr>
						<tr>
							<th colspan="6" style="text-align: left;padding-left: 20px;"><font color="red">*</font>个人简介（业务能力、学术地位等）
							</th>
						</tr>
						<tr>
							<td colspan="6"><textarea class="validate[required]" name="personalInfo" style="width:98%;height: 150px;">${resultMap.personalInfo}</textarea>
							</td>
						</tr>
					</table>

				</form>
				<div class="button" style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
					<input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
					<input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>