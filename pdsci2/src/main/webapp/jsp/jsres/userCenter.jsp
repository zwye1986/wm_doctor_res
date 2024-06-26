<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">

	$(document).ready(function () {
		$('#trainingYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode: 2,
			format: 'yyyy'
		});

		$('#graduationTime').datepicker({
			startView: 1,
			maxViewMode: 1,
			minViewMode: 1,
			format: 'yyyy-mm'
		});

		$('#workingTime').datepicker({
			startView: 1,
			maxViewMode: 1,
			minViewMode: 1,
			format: 'yyyy-mm'
		});

		/*var deptId = '${teacher.deptFlow}';
		$("#deptFlow").empty();
		$("#deptFlow").append("<option value=''>请选择</option>");
		<c:forEach var="dept" items="${deptList}">
		var deptFlow = '${dept.deptFlow}';
		var deptName = '${dept.deptName}';
		if(deptFlow == deptId) {
			$("#deptFlow").append("<option selected value='" + deptFlow + "'>" + deptName + "</option>");
		}else{
			$("#deptFlow").append("<option value='" + deptFlow + "'>" + deptName + "</option>");
		}
		</c:forEach>*/
	});

	function getOrgName() {
		$("#orgName").val($("#orgFlow option:selected").text());
	}

	function searchDeptList(orgFlow){
		jboxPost("<s:url value='/jsres/teacher/searchDeptList'/>?orgFlow="+orgFlow, null, function (resp) {
			$("#deptFlow").empty();
			$("#deptFlow").append("<option value=''>请选择</option>");
			if (null != resp && resp.length > 0) {
				for(var i= 0;i<resp.length;i++){
					$("#deptFlow").append("<option value='" + resp[i].deptFlow + "'>" + resp[i].deptName + "</option>");
				}
			}
		},null,false);
	}

	function checkFile(file){
		var filePath = file.value;
		var types = ".jpg,.png,.jpeg".split(",");
		var regStr = "/";
		for(var i = 0 ;i<types.length;i++){
			if(types[i]){
				if(i==(types.length-1)){
					regStr = regStr+"\\"+types[i]+'$';
				}else{
					regStr = regStr+"\\"+types[i]+'$|';
				}
			}
		}
		regStr = regStr+'/i';
		regStr = eval(regStr);
		if($.trim(filePath)!="" && !regStr.test(filePath)){
			file.value = "";
			jboxTip("请上传&nbsp;.jpg,.png,.jpeg格式的图片");
		}
	}

	function delImg(recordFlow){
		jboxConfirm("确认删除？", function(){
			var url = "<s:url value='/jsres/statistic/deleteCerfiticateImg'/>?recordFlow=" + recordFlow;
			jboxPost(url,null,function(resp){
				if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
					$("input[name='certificateUrl']").val("");
					reuploadImg();
					jboxTip("删除图片成功！");
				}
			},null,true);
		});
	}
	function delImgOne(recordFlow){
		jboxConfirm("确认删除成果附件？", function(){
			var url = "<s:url value='/jsres/statistic/deleteAchievementImg'/>?recordFlow=" + recordFlow;
			jboxPost(url,null,function(resp){
				if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
					$("input[name='achievementUrl']").val("");
					reuploadImgOne();
					jboxTip("删除成果附件成功！");
				}
			},null,true);
		});
	}

	function reuploadImg(){
		$("#viewImgLink").hide();
		$("#delImgLink").hide();
		$("#reuploadImgLink").hide();
		$("#newFile").show();
	}

	function reuploadImgOne(){
		$("#viewImgLinkOne").hide();
		$("#delImgLinkOne").hide();
		$("#reuploadImgLinkOne").hide();
		$("#newFileOne").show();
	}

	function checkUploadFile(){
		var recordFlow = $("#recordFlow").val();
		var userPhone = $("#userPhone").val();
		if ($("#excelForm").validationEngine("validate")) {
			$("#speName").val($("#speId option:selected").text());
			$("#deptName").val($("#deptFlow option:selected").text());
			jboxStartLoading();
			$(":file.auto:hidden").attr("disabled",true);
			jboxPost("<s:url value='/jsres/statistic/checkUserPhone'/>?userPhone="+userPhone+"&recordFlow="+recordFlow, null, function (resp) {
				jboxTip(resp);
				if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
					$("#excelForm").submit();
				}
				jboxEndLoading();
			},null,false);
		}
	}

	function teacherApplication() {
		var recordFlow = $("#recordFlow").val();
		var roleId = $("#roleId").val();
		if ($("#excelForm").validationEngine("validate")) {
			$("#excelForm").submit();
			var url = "<s:url value='/jsres/statistic/teacherApplication'/>?recordFlow=" + recordFlow + "&roleId=" + roleId;
			jboxOpen(url, "师资申请", 800, 350);
		}
	}

	function applicationStatus() {
		var recordFlow = $("#recordFlow").val();
		var url = "<s:url value='/jsres/statistic/applicationStatus'/>?recordFlow=" + recordFlow;
		jboxOpen(url, "申请进度", 800, 350);
	}

	function toDeptFlow(deptFlow) {
		$("#deptFlow").val(deptFlow);
	}


	/**
	 *模糊查询加载
	 */
	(function($){
		$.fn.likeSearchInit = function(option){
			option.clickActive = option.clickActive || null;

			var searchInput = this;
			searchInput.on("keyup focus",function(){
				$("#boxHome").show();
				if($.trim(this.value)){
					$("#boxHome .item").hide();
					var items = $("#boxHome .item[value*='"+this.value+"']").show();
					if(!items){
						$("#boxHome").hide();
					}
				}else{
					$("#boxHome .item").show();
				}
			});
			searchInput.on("blur",function(){
				if(!$("#boxHome.on").length){
					$("#boxHome").hide();
				}
			});
			$("#boxHome").on("mouseenter mouseleave",function(){
				$(this).toggleClass("on");
			});
			$("#boxHome .item").click(function(){
				$("#boxHome").hide();
				var value = $(this).attr("value");
				$("#itemName").val(value);
				searchInput.val(value);
				if(option.clickActive){
					option['clickActive']($(this).attr("flow"));
				}
			});
		};
	})(jQuery);


	//页面加载完成时调用
	$(function(){
		$("#orgSel").likeSearchInit({});
	});
</script>
<div class="main_hd">
	<h2 class="underline">个人信息</h2>
</div>
<div class="search_table" style="margin-top: 10px;">
	<c:if test="${not empty roleId && (roleId == GlobalConstant.RES_ROLE_SCOPE_TEACHER || roleId == 'student')}">
		<form id="excelForm"  method="post" action="<s:url value='/jsres/statistic/saveTeacherTrainingInfo'/>" enctype="multipart/form-data">
			<input  type="text" name="roleId" id="roleId"  value="${roleId}" style="display: none;"/>
			<input  type="text" name="recordFlow" id="recordFlow"  value="${teacher.recordFlow}" style="display: none;"/>
			<input  type="text" name="speName" id="speName" value="${teacher.speName}" style="text-align: left;display: none;width: 150px;"/>
<%--			<input  type="text" name="deptName" id="deptName" value="${teacher.deptName}" style="text-align: left;display: none;width: 150px;"/>--%>
			<table  cellpadding="0" cellspacing="0" class="grid">
				<tr>
					<th width="20%"><font color="red" >*</font>姓名</th>
					<td width="30%" style="text-align: left;">
						<input  type="text" name="doctorName" readonly class="select validate[required]" value="${teacher.doctorName}" style="text-align: left;width: 150px;"/>
					</td>
					<th width="20%"><font color="red" >*</font>联系方式</th>
					<td width="30%" style="text-align: left;">
						<input  type="text" id="userPhone" name="userPhone" <c:if test="${!empty teacher.userPhone}">readonly="readonly"</c:if> class="select validate[required]" value="${teacher.userPhone}" style="text-align: left;width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>年龄</th>
					<td style="text-align: left;">
						<input  type="text" name="doctorAge" class="select validate[required,custom[integer],min[0]]" value="${teacher.doctorAge}" style="text-align: left;width: 150px;"/>
					</td>
					<th><font color="red" >*</font>性别</th>
					<td style="text-align: left;">
						<select name="sexName" id="sexName" class="select validate[required]" style="width: 150px;" >
							<option value=""></option>
							<option value="男"<c:if test="${teacher.sexName=='男' }">selected="selected"</c:if>>男</option>
							<option value="女"<c:if test="${teacher.sexName=='女' }">selected="selected"</c:if>>女</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>证件号码</th>
					<td style="text-align: left;">
						<input  type="text" name="idNo" <c:if test="${!empty sysUser.idNo}">readonly="readonly"</c:if> class="select validate[custom[integer],min[0]]" value="${sysUser.idNo}" style="text-align: left;width: 150px;"/>
					</td>
					<th>电子邮箱</th>
					<td style="text-align: left;">
						<input  type="text" name="userEmail" class="select" value="${sysUser.userEmail}" style="text-align: left;width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>师资培训会议名称</th>
					<td style="text-align: left;">
						<input  type="text" name="meetingName" class="select validate[required]" value="${teacher.meetingName}"  style="text-align: left;width: 150px;"/>
					</td>
					<th><font color="red" >*</font>证书编号</th>
					<td style="text-align: left;">
						<input  type="text" id="certificateNo" name="certificateNo" class="select validate[required]" value="${teacher.certificateNo}"  style="text-align: left;width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>证书级别</th>
					<td style="text-align: left;">
						<select name="certificateLevel" class="validate[required] select" style="width: 150px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumCertificatelevelList}" var="title">
								<option value="${title.dictId}"
										<c:if test='${teacher.certificateLevel==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th><font color="red" >*</font>证书时间</th>
					<td style="text-align: left;">
						<input name="certificateTime"  style="margin-left: 0px;width: 145px;"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="certificateTime"
							   value="${teacher.certificateTime}" class="input">
					</td>
				</tr>
				<tr>
					<c:if test="${roleId != 'student'}">
						<th><font color="red" >*</font>师资类型</th>
						<td style="text-align: left;">
							<input  type="text" name="teacherLevelId" class="select validate[required]" hidden value="${empty teacher.teacherLevelId ? 'GeneralFaculty' : teacher.teacherLevelId}"  style="text-align: left;width: 150px;"/>
							<input  type="text" name="teacherLevelName" class="select validate[required]" readonly value="${empty teacher.teacherLevelName ? '一般师资' : teacher.teacherLevelName}"  style="text-align: left;width: 150px;"/>
						</td>
					</c:if>
					<th><font color="red" >*</font>基地</th>
					<td style="text-align: left;">
						<select name="orgFlow" id="orgFlow" class="select validate[required]" style="width: 150px;" onchange="getOrgName();" >
							<option value="">全部</option>
							<c:forEach items="${orgs}" var="org">
								<option value="${org.orgFlow}"<c:if test="${(teacher.orgFlow==org.orgFlow) or (teacher.orgName==org.orgName) }">selected="selected"</c:if>>${org.orgName}</option>
							</c:forEach>
						</select>
						<input  type="text" name="orgName" id="orgName" class="validate[required]" value="${teacher.orgName}" style="text-align: left;display: none;width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>技术职称</th>
					<td style="text-align: left;">
						<input  type="text" name="technicalTitle" class="select validate[required]" value="${teacher.technicalTitle}" style="text-align: left;width: 150px"/>
					</td>
					<th><font color="red" >*</font>毕业学校</th>
					<td  style="text-align: left;">
						<input  type="text" name="graduationSchool" class="select validate[required]" value="${teacher.graduationSchool}" style="text-align: left;width: 150px"/>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>毕业时间</th>
					<td  style="text-align: left;">
						<input type="text" value="${teacher.graduationTime}" class="select validate[required]" name="graduationTime" id="graduationTime" style="width: 150px;"/>
					</td>
					<th><font color="red" >*</font>学历</th>
					<td style="text-align: left;">
						<input  type="text" name="doctorEdu" class="select" value="${teacher.doctorEdu}" style="text-align: left;width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>科室</th>
					<td style="text-align: left;">
						<%--<select name="deptFlow" id="deptFlow" class="select validate[required]" style="width: 150px;" ></select>--%>
							<input type="hidden" id="deptFlow" name="deptFlow" value="${teacher.deptFlow}">
							<input id="orgSel" class="toggleView input" type="text" style="width: 220px;margin-left: 0px;" name="deptName"
								   value="${teacher.deptName}" autocomplete="off" title="${teacher.deptName}"  placeholder="请选择科室"
								   onmouseover="this.title = this.value"/>
							<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:0px;">
								<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 220px;border-top: none;position: relative;display: none;">
									<c:forEach items="${deptList}" var="dept">
										<p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" onclick="toDeptFlow('${dept.deptFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${dept.deptName}</p>
									</c:forEach>
								</div>
							</div>
					</td>
					<th><font color="red" >*</font>专业</th>
					<td style="text-align: left;">
						<select class="select validate[required]" id="speId" name="speId" style="width: 150px">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option <c:if test="${teacher.speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>培训年份</th>
					<td style="text-align: left;">
						<input type="text" value="${teacher.trainingYear}" class="select validate[required]" name="trainingYear" id="trainingYear" style="width: 150px;"/>
					</td>
					<th><font color="red" >*</font>工作时间</th>
					<td  style="text-align: left;">
						<input type="text" value="${teacher.workingTime}" class="select validate[required]" name="workingTime" id="workingTime" style="width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th>任现职务年限</th>
					<td style="text-align: left;">
						<input  type="text" name="officeYear" class="select validate[custom[integer],min[0]]" value="${teacher.officeYear}" style="text-align: left;width: 150px;"/>
					</td>
					<th>从事本专业临床工作年限</th>
					<td  style="text-align: left;">
						<input  type="text" name="workYear" class="select validate[custom[integer],min[0]]" value="${teacher.workYear}" style="text-align: left;width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th>带实习生年限</th>
					<td style="text-align: left;">
						<input  type="text" name="internYear" class="select validate[custom[integer],min[0]]" value="${teacher.internYear}" style="text-align: left;width: 150px;"/>
					</td>
					<th>带实习生近3年累计人数</th>
					<td style="text-align: left;">
						<input  type="text" name="threeInternYear" class="select validate[custom[integer],min[0]]" value="${teacher.threeInternYear}" style="text-align: left;width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th>带住院医师年限</th>
					<td style="text-align: left;">
						<input  type="text" name="hosYear" class="select validate[custom[integer],min[0]]" value="${teacher.hosYear}" style="text-align: left;width: 150px;"/>
					</td>
					<th>带住院医师近3年累计人数</th>
					<td style="text-align: left;">
						<input  type="text" name="threeHosYear" class="select validate[custom[integer],min[0]]" value="${teacher.threeHosYear}" style="text-align: left;width: 150px;"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">参加省级及以上住院医师规范化培训师资培训</th>
					<td  style="text-align: left;" colspan="2">
						<label><input name="isTrain" type="radio" value="${GlobalConstant.FLAG_Y}"
									  <c:if test="${teacher.isTrain ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
						<label><input name="isTrain" type="radio" value="${GlobalConstant.FLAG_N}"
									  <c:if test="${teacher.isTrain ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
					</td>
				</tr>
				<tr>
					<th>证书附件</th>
					<c:if test="${empty teacher.certificateUrl}" >
						<td colspan="2">
							<input type="file" name="uploadFile" style="margin-right: 115px" onchange="checkFile(this);"/>
						</td>
					</c:if>
					<c:if test="${not empty teacher.certificateUrl}" >
						<td colspan="2">
							<input type="hidden" name="certificateUrl" value="${teacher.certificateUrl}"/>
							<input type="file" id="newFile" name="uploadFile" style="display: none;margin-right: 115px" onchange="checkFile(this);"/>
							<a style="margin-right: 115px" id="viewImgLink" href="${sysCfgMap['upload_base_url']}/${teacher.certificateUrl}" target="_blank" title="点击查看大图"><img src="${sysCfgMap['upload_base_url']}/${teacher.certificateUrl}" width="80px" height="80px"/></a>
							<a id="delImgLink" href="javascript:void(0)" onclick="delImg('${teacher.recordFlow}')" >[删除图片]</a>
							<a id="reuploadImgLink" href="javascript:void(0)" onclick="reuploadImg()">[重新上传图片]</a>
						</td>
					</c:if>
					<td style="text-align: left;padding-left: 10px;">允许上传后缀格式：.jpg,.png,.jpeg</td>
				</tr>
				<tr>
					<th>成果附件</th>
					<c:if test="${empty teacher.achievementUrl}" >
						<td colspan="2">
							<input type="file" name="uploadFileOne" style="margin-right: 115px" onchange="checkFile(this);"/>
						</td>
					</c:if>
					<c:if test="${not empty teacher.achievementUrl}" >
						<td colspan="2">
							<input type="hidden" name="achievementUrl" value="${teacher.achievementUrl}"/>
							<input type="file" id="newFileOne" name="uploadFileOne" style="display: none;margin-right: 115px" onchange="checkFile(this);"/>
							<a style="margin-right: 115px" id="viewImgLinkOne" href="${sysCfgMap['upload_base_url']}/${teacher.achievementUrl}" target="_blank" title="点击查看大图"><img src="${sysCfgMap['upload_base_url']}/${teacher.achievementUrl}" width="80px" height="80px"/></a>
							<a id="delImgLinkOne" href="javascript:void(0)" onclick="delImgOne('${teacher.recordFlow}')" >[删除图片]</a>
							<a id="reuploadImgLinkOne" href="javascript:void(0)" onclick="reuploadImg()">[重新上传图片]</a>
						</td>
					</c:if>
					<td style="text-align: left;padding-left: 10px;">允许上传后缀格式：.jpg,.png,.jpeg</td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; margin-top: 10px;">
			<input type="button" onclick="checkUploadFile();" class="btn_green" value="保&#12288;存"/>
			<c:if test="${teacher.teacherLevelId != 'KeyFaculty'}">
				<input type="button" onclick="teacherApplication();" class="btn_green" value="师资申请"/>
			</c:if>
			<c:if test="${not empty teacher.applicationTeacherLevelId}">
				<input type="button" onclick="applicationStatus();" class="btn_green" value="申请进度"/>
			</c:if>
		</div>
	</c:if>
	<c:if test="${not empty roleId && roleId != GlobalConstant.RES_ROLE_SCOPE_TEACHER && roleId != 'student'}">
		<table  cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th width="20%">登录名：</th>
				<td width="30%">
			 		${sysUser.userCode}
				</td>
				<th width="20%">身份证号：</th>
				<td width="30%">
					${sysUser.idNo}
				</td>
			</tr>
			<tr>
				<th>姓名：</th>
				<td>
					${sysUser.userName}
				</td>
				<th>性别：</th>
				<td><c:if test="${userSexEnumMan.id == sysUser.sexId}">${userSexEnumMan.name}</c:if>
					<c:if test="${userSexEnumWoman.id == sysUser.sexId}">${userSexEnumWoman.name }</c:if>
				</td>
			</tr>
			<tr>
				<th>手机号：</th>
				<td>
					${sysUser.userPhone}
				</td>
				<th>电子邮箱：</th>
				<td>${sysUser.userEmail}
				</td>
			</tr>
			<tr>
				<th>基地名称：</th>
				<td>&nbsp;${sessionScope.currUser.orgName}</td>
				<th>证书编号：</th>
				<td>
					${sysUser.certificateId}
				</td>
			</tr>
			<tr>
				<th>科室名称：</th>
				<td colspan="3">
					<c:forEach items="${sysDeptList}" var="dept">
						<c:if test='${sysUser.deptFlow==dept.deptFlow}'>
							<div id="${dept.deptFlow}View" style="float: left;margin-left: 10px;width: 100px;">
								<label>
										${dept.deptName}
								</label>
							</div>
						</c:if>
					</c:forEach>
					<c:if test="${sysCfgMap['sys_user_dept_flag'] eq GlobalConstant.RECORD_STATUS_Y}">
						<c:forEach items="${sysDeptList}" var="dept">
							<c:if test="${!empty userDeptMap[dept.deptFlow]}">
								<div id="${dept.deptFlow}View" style="float: left;margin-left: 10px;width: 100px;<c:if test="${sysUser.deptFlow eq dept.deptFlow}">display: none;</c:if>">
									<label>
										${dept.deptName}
									</label>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
				</td>
			</tr>
		</table>
	</c:if>
</div>