<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
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
<style type="text/css">
	table{ margin:10px 0;border-collapse: collapse;}
	caption,th,td{height:40px;}
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;margin-bottom: 10px;}
	th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
	td{text-align:left; padding-left:5px; }
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
</style>

<script type="text/javascript">
	var isSch = {
			<c:forEach items="${recDocCategoryEnumList}" var="category">
				<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
				<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
					<c:set var="isSchKey" value="res_doctor_category_${category.id}_sch"/>
					"${category.id}":"${sysCfgMap[isSchKey]}",
				</c:if>
			</c:forEach>
	};

	var condition = "._${doctor.doctorCategoryId}._${doctor.trainingSpeId}";
	function saveDoc(){
		if($("#resDoctor").validationEngine("validate")){
			var url = "<s:url value='/res/doc/user/saveDocSimple'/>";
			var getdata = $('#resDoctor').serialize();
			jboxPost(url, getdata, function(data) {
				if('${GlobalConstant.SAVE_SUCCESSED}'==data){
					window.parent.frames['mainIframe'].window.searchUser(); 
					jboxClose();
				}
			},null,true);
		}
	}
	
	$(function(){
		$(".xltext,.xlselect").css("margin-right","0px");
		$("._rotation").hide();
		selRotation();
		
		$("#d").append($(".isOrg"));
	});
	
	function changeDocCategory(){
		selRotation();
		var doctorCategoryId = $("#doctorCategoryId").val();
		changeUserInfo(doctorCategoryId);
	}
	
	function selRotation(){
		$("#rotationFlow").val("");
		$("._rotation").hide();
		var doctorCategoryId = $("#doctorCategoryId").val();
		var trainingSpeId = $("#trainingSpeId").val();
		if(doctorCategoryId && trainingSpeId){
			var selector = "._"+doctorCategoryId+"._"+trainingSpeId;
			$(selector).show();
			if(condition==selector){
				$(selector+"[value='${doctor.rotationFlow}']").attr("selected","selected");
			}else{
				$(selector+":first").attr("selected","selected");
			}
		}
	}
	
	function changeUserInfo(doctorCategoryId){
		$("#workInfo").hide();
		$("#work").hide();
		$(".userNameSpan").html("学员");
		$(".schoolNameSpan").html("在读");
		$(".graduateSpan").hide();
		$(".educationSpan").show();
		$(".degreeSpan").hide();
		$(".deptSpan").hide();
		$(".trainNameSpan").html("培训");
		$(".trainExtralSpan").html("培养");
		if (doctorCategoryId=="${recDocCategoryEnumDoctor.id}" || 
				doctorCategoryId=="${recDocCategoryEnumInDoctor.id}" ||
				doctorCategoryId=="${recDocCategoryEnumOutDoctor.id}" ||
				doctorCategoryId=="${recDocCategoryEnumFieldTrain.id}" ||
				doctorCategoryId=="${recDocCategoryEnumUnderGrad.id}" ||
				doctorCategoryId=="${recDocCategoryEnumSpecialist.id}" ||
				doctorCategoryId=="${recDocCategoryEnumGeneralDoctor.id}") 
		{//住院医师、本院规培、外院规培、外地委培、本科生、专科医师、普通医生
			$(".userNameSpan").html("医师");	
			$(".schoolNameSpan").html("毕业");
			$(".graduateSpan").show();	//增加毕业证号和毕业时间
			$(".degreeSpan").show();	//增加学位
			$("#workInfo").show();	//增加工作单位和职务
			$("#work").show();	//增加工作经历
			$(".deptSpan").hide();
			$(".${recDocCategoryEnumGraduate.id}").hide();
		} else if (doctorCategoryId=="${recDocCategoryEnumScholar.id}") {//进修生
			$(".schoolNameSpan").html("毕业");
			$(".graduateSpan").show();	//增加毕业证号和毕业时间
			$(".degreeSpan").show();	//增加学位
			$(".trainNameSpan").html("进修");
			$("#work").show();	//增加工作经历
			$(".deptSpan").hide();
			$(".${recDocCategoryEnumGraduate.id}").hide();
		} else if (doctorCategoryId=="${recDocCategoryEnumEightYear.id}") {//八年制
			$(".educationSpan").hide();	//隐藏学历
			$(".${recDocCategoryEnumGraduate.id}").hide();
		} else if (doctorCategoryId=="${recDocCategoryEnumIntern.id}") {//实习生
			$(".trainNameSpan").html("实习");
			$(".deptSpan").show();
			$(".${recDocCategoryEnumGraduate.id}").hide();
		}else if (doctorCategoryId=="${recDocCategoryEnumGraduate.id}") {//研究生
			$(".${recDocCategoryEnumGraduate.id}").show();
		}
		
		$("#rotationInfo").toggle("${GlobalConstant.FLAG_N}"!=isSch[doctorCategoryId]);
	}
	
	function checkCondition(){
		if(!$("#doctorCategoryId").val()){
			jboxTip("请选择人员类型!");
			return;
		}
// 		if(!$("#sessionNumber").val()){
// 			jboxTip("请选择年级!");
// 			return;
// 		}
		if(!$("#trainingSpeId").val()){
			jboxTip("请选择专业!");
			return;
		}
	}
	
	$(document).ready(function(){
		changeUserInfo("${doctor.doctorCategoryId}");
		changeTerminat("${doctor.doctorStatusId}");
		loadEdu();
	 	loadWork();
	});

	function loadEdu(){
		jboxLoad("edu","<s:url value='/pub/resume/loadView?type=education&userFlow=${user.userFlow}&editFlag=${GlobalConstant.FLAG_N}&source=resDoc'/>");
	}

	function loadWork(){
		jboxLoad("work","<s:url value='/pub/resume/loadView?type=work&userFlow=${user.userFlow}&editFlag=${GlobalConstant.FLAG_N}&source=resDoc'/>");
	}
	
	function lock(userFlow){
		jboxConfirm("确认锁定该用户吗？锁定后该用户将不能登录系统！",function () {
			var url = "<s:url value='/sys/user/lock?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
				var url="javascript:activate('${user.userFlow}');";
				$("#userStatusSpan").html('${userStatusEnumLocked.name}&#12288;[<a href='+url+'>解锁</a>]');			
			});		
		});
	}
	
	function activate(userFlow){
		jboxConfirm("确认解锁该用户吗？",function () {
			var url = "<s:url value='/sys/user/activate?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
				var url="javascript:lock('${user.userFlow}');";
				$("#userStatusSpan").html('${userStatusEnumActivated.name}&#12288;[<a href='+url+'>锁定</a>]');			
			});
		});
	}
	
	//根据医师状态决定展示终止还是结业内容
	function changeTerminat(doctorStatusId){
		if ("${resDoctorStatusEnumTerminat.id}"==doctorStatusId) {
			$(".${resDoctorStatusEnumTerminat.id}").show();
			$(".${resDoctorStatusEnumGraduation.id}").hide();
		} else if ("${resDoctorStatusEnumGraduation.id}"==doctorStatusId) {
			$(".${resDoctorStatusEnumGraduation.id}").show();
			$(".${resDoctorStatusEnumTerminat.id}").hide();
		} else {
			$(".${resDoctorStatusEnumTerminat.id}").hide();
			$(".${resDoctorStatusEnumGraduation.id}").hide();
		}
	}
	
	function doClose(){
		jboxClose();
	}
	
	function resetPasswd(userFlow){
		jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
			var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
			});		
		});
	}
	function option(obj){
		$("#d").append($(".isOrg"));
		$("#rotationFlow").append($("#d .isOrg."+obj));
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
	<div class="title1 clearfix" style="padding:0; ">
		<form id="resDoctor" style="position: relative;">
		<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
		<input type="hidden" name="userFlow" value="${user.userFlow}"/>
		<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
		
		<table style="width:100%;">
			<caption>个人信息</caption>
			<colgroup>
			<col width="14%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			</colgroup>
			<tr>
				<th>人员类型：</th>
				<td>
					<c:if test="${!rotationInUse}">
						<select name="doctorCategoryId" class="validate[required]" id="doctorCategoryId" onchange="changeDocCategory();">
							<option></option>
							<c:forEach items="${recDocCategoryEnumList}" var="category">
								<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
								<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
								<option value="${category.id}" ${doctor.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
						<font color="red">*</font>
					</c:if>
					<c:if test="${rotationInUse}">
						<label>${doctor.doctorCategoryName}</label>
					</c:if>
				</td>
				<th>工号/学号：</th>
				<td>
					${doctor.doctorCode}
					<input type="hidden" name="doctorCode" value="${doctor.doctorCode}"/>
				</td>
				<th>用户名：</th>
				<td>
					${user.userCode}
					<input type="hidden" name="userCode" value="${user.userCode}"/>
				</td>
			</tr>
			<tr>
				<th>真实姓名：</th>
				<td>
					<input type="text" name="userName" value="${user.userName}" class="validate[required,custom[chinese]]"/>
					<font color="red">*</font>
				</td>
				<th>证件号码：</th>
				<td>
					${user.idNo}
					<input type="hidden" name="idNo" value="${user.idNo}"/>
				</td>
				<th>出生日期：</th>
				<td>
					<input type="text" name="userBirthday" value="${user.userBirthday}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th>性别：</th>
				<td>
					<label><input type="radio" name="sexId" class="validate[required]" <c:if test="${user.sexId eq userSexEnumMan.id}">checked="checked"</c:if>  value="${userSexEnumMan.id}" />男&#12288;
					<label><input type="radio" name="sexId" class="validate[required]" <c:if test="${user.sexId eq userSexEnumWoman.id}">checked="checked"</c:if> value="${userSexEnumWoman.id}" />女</label>
					<font color="red">*</font>
				</td>
				<th>手机：</th>
				<td>
					<input type="text" name="userPhone" value="${user.userPhone}" class="validate[custom[mobile]]"/>
				</td>
				<th >Email：</th>
				<td>
					<input type="text" name="userEmail" value="${user.userEmail}" class="validate[custom[email]]"/>
				</td>
			</tr>
			<tr>
				<th>入院时间：</th>
				<td>
					<input type="text" name="inHosDate" value="${doctor.inHosDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
				</td>
				<th>人员分类：</th>
				<td>
					<select name="doctorTypeId">
						<option value=""></option>
						<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
							<option value="${dict.dictId}" <c:if test="${doctor.doctorTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
						</c:forEach>
					</select>
				</td>
				<th>学历：</th>
				<td>
					<input type="text" name="educationName" value="${user.educationName}" />
				</td>
			</tr>
			<tr>
				<th>学位：</th>
				<td>
					<input type="text" name="degreeName" value="${user.degreeName}" />
				</td>
				<th>最高学历毕业学校：</th>
				<td>
					<input type="text" name="graduatedName" value="${doctor.graduatedName}" />
				</td>
				<th>是否取得执业资格证：</th>
				<td>
					<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">checked="checked"</c:if>  value="Y" />是&#12288;
					<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'N'}">checked="checked"</c:if> value="N" />否</label>
				</td>
			</tr>
		</table>
		<div id="d" style="display: none;">
			
		</div>
		<table id="rotationInfo" style="width:100%;">
			<caption>培训信息</caption>
			<colgroup>
			<col width="14%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			</colgroup>
			<tr>
				<th>年级：</th>
				<td>
					<select name="sessionNumber" class="validate[required]" id="sessionNumber" onchange="selRotation();">
						<option></option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
							<option value="${dict.dictName}" ${doctor.sessionNumber eq dict.dictName?'selected="selected"':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
					<font color="red">*</font>
				</td>
				<th><span class="trainNameSpan trainExtralSpan">培养</span>年限：</th>
				<td>
					<select name="trainingYears" class="validate[required]">
						<option></option>
						<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
							<option value="${dict.dictName}" ${doctor.trainingYears eq dict.dictName?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
					<font color="red">*</font>
				</td>
				<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
				<th><span class="deptSpan">所属部门：</span></th>
				<td>
					<span class="deptSpan">
					<select name="deptFlow" class="validate[required]">
						<option></option>
						<c:forEach items="${deptList}" var="dept">
							<option value="${dept.deptFlow}" ${user.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
						</c:forEach>
					</select>
					<font color="red">*</font>
					</span>
				</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
					<th><span class="trainNameSpan">培训</span>基地：</th>
					<td>
						<select name="orgFlow" class="validate[required]" onchange="option(this.value)">
							<option>
							<c:forEach items="${orgList}" var="org">
								<option value="${org.orgFlow}" ${org.orgFlow eq doctor.orgFlow?'selected':''}>${org.orgName}</option>
							</c:forEach>
						</select>
						<font color="red">*</font>
					</td>
				</c:if>
				<th><span class="trainNameSpan">培训</span>专业：</th>
				<td>
					<c:if test="${!rotationInUse}">
						<select name="trainingSpeId" class="validate[required]" id="trainingSpeId" onchange="selRotation();">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected="selected"':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
						<font color="red">*</font>
					</c:if>
					<c:if test="${rotationInUse}">
						<label>${doctor.trainingSpeName}</label>
					</c:if>
				</td>
				<th><label class="rotationAttr">轮转方案：</label></th>
				<td colspan="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'1':'3'}">
					<c:if test="${!rotationInUse}">
						<select name="rotationFlow" class=" rotationAttr" id="rotationFlow" onclick="checkCondition();">
							<option></option>
							<c:forEach items="${rotationList}" var="rotation">
									<option class="_${rotation.doctorCategoryId} _${rotation.speId} _rotation
									<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag && rotation.isOrgView eq GlobalConstant.RECORD_STATUS_Y}">
										_ isOrg
										<c:forEach items="${map[rotation.rotationFlow]}" var="resRotationOrg">
										_ ${resRotationOrg.orgFlow}
										</c:forEach>
									</c:if>
									" value="${rotation.rotationFlow}" ${(doctor.rotationFlow eq rotation.rotationFlow)?'selected':''}>${rotation.rotationName}</option>
							</c:forEach>
						</select>
						<font class="rotationAttr" color="red"></font>
					</c:if>
					<c:if test="${rotationInUse}">
						<label>${doctor.rotationName}</label>
					</c:if>
				</td>
			</tr>
			<tr>
				<th><span class="userNameSpan">医师</span>状态：</th>
				<td>
					<select name="doctorStatusId" class="validate[required]" onchange="changeTerminat(this.value);">
						<option></option>
						<c:forEach items="${resDoctorStatusEnumList}" var="status">
							<option value="${status.id}" ${doctor.doctorStatusId eq status.id?'selected':''}>${status.name}</option>
						</c:forEach>
					</select>
					<font color="red">*</font>
				</td>
				<th class="${recDocCategoryEnumGraduate.id}">导师姓名：</th>
				<td class="${recDocCategoryEnumGraduate.id}">
					<select name="tutorFlow" onchange="$('#tutorName').val($(':selected',this).text());">
						<option/>
						<c:forEach items="${tutorList}" var="tutor">
							<option value="${tutor.userFlow}" <c:if test="${tutor.userFlow eq doctor.tutorFlow}">selected</c:if>>${tutor.userName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="tutorName" name="tutorName" value="${doctor.tutorName}"/>
				</td>
				<th class="${recDocCategoryEnumGraduate.id}">研究方向：</th>
				<td class="${recDocCategoryEnumGraduate.id}">
					<input type="text" name="researchDirection" value="${doctor.researchDirection}"/>
				</td>
				</tr>
				<tr>
				<th class="${resDoctorStatusEnumTerminat.id}">终止原因：</th>
				<td class="${resDoctorStatusEnumTerminat.id}">
					<input type="text" name="terminatReason" value="${doctor.terminatReason}"/>
				</td>
				<th class="${resDoctorStatusEnumTerminat.id}">终止时间：</th>
				<td class="${resDoctorStatusEnumTerminat.id}">
					<input type="text" name="terminatDate" value="${doctor.terminatDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
				</td>	
				<th class="${resDoctorStatusEnumGraduation.id}">结业证书号：</th>
				<td class="${resDoctorStatusEnumGraduation.id}">
					<input type="text" name="completeNo" value="${doctor.completeNo}"/>
				</td>	
				<th class="${resDoctorStatusEnumGraduation.id}">结业时间：</th>
				<td class="${resDoctorStatusEnumGraduation.id}">
					<input type="text" name="completeDate" value="${doctor.completeDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
				</td>
			</tr>
			<c:if test="${!empty user.userFlow}">
			<tr>
				<th>账号状态：</th>
				<td colspan="5">
					<span id="userStatusSpan">
						${user.statusDesc }&#12288;
						<c:if test="${user.statusId==userStatusEnumActivated.id}">
							[<a href="javascript:lock('${user.userFlow}');" >锁定</a>]
						</c:if> 
						<c:if test="${user.statusId==userStatusEnumLocked.id}">
							[<a href="javascript:activate('${user.userFlow}');" >解锁</a>]
						</c:if>
						[<a href="javascript:resetPasswd('${user.userFlow}');">重置密码</a>]
						
						<c:if test="${(sysCfgMap['res_org_ad_doc'] eq GlobalConstant.FLAG_Y && !(param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL)) || param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							<script type="text/javascript">
								function disabledDocAndUser(){
									jboxConfirm("确认停用该学员？",function(){
										jboxGet("<s:url value='/res/doc/disabledDocAndUser'/>?userFlow=${user.userFlow}",null,function(resp){
											if(resp="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
												top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
												top.document.mainIframe.location.reload();
												jboxClose();
											}
										},null,false);
									},null);
								}
							</script>
							
							[<a onclick="disabledDocAndUser();" style="cursor: pointer;">停用</a>]
						</c:if>
					</span>
				</td>
			</tr>
			</c:if>
		</table>
		</form>
		<p style="text-align: center;">
       		<input type="button" onclick="saveDoc();"  class="search" value="保&#12288;存"/>
       		<c:if test="${!param.isMenu}">
       			<input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
       		</c:if>
       </p>
		</div>
	</div>
</div>
</body>
</html>