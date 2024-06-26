<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#1461c0;margin-bottom: 10px;}
	th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
	td{text-align:left; padding-left:5px; }
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
	.fuck:hover {
		background: black;
	}
</style>

<script type="text/javascript">
	//单选
	function single(box){
		var curr=box.checked;
		if(curr){
			var name=box.name;
			$(":checkbox[name='"+name+"']").attr("checked",false);
		}
		box.checked = curr;
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
					var items = $("#boxHome .item[value*='"+this.value+"']").show();//模糊显示
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
				searchInput.val(value);
				var deptId = $(this).attr("flow");
				$("#standardDeptId").val(deptId);
				if(option.clickActive){
					option['clickActive']($(this).attr("flow"));

				}
			});
		};
	})(jQuery);



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
			var idNo=$("#idNo").val();
			if(idNo!=""){
				if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) &&!(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
					jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
					return false;
				}
			}

	getUserResumeExtName();
	getFantasticFour();
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
	function showId(radio){
		if(radio=='Y') $(".qualifiedNoTd, .qualifiedNoTh").show();
		else $(".qualifiedNoTd, .qualifiedNoTh").hide();
	}

	function calculation(){
		var y;
		if($("#trainingYears").val()=='1') y=1;
		else if($("#trainingYears").val()=='2') y=2;
		else if($("#trainingYears").val()=='3') y=3;
		else y=0;
		var year = parseInt($("#sessionNumber").val())+parseInt(y);
		if(year!=0&&!isNaN(year)&&year>'${doctor.graduationYear}'){
			$("#graduationYear").val(year);
			$("#graduationYearLebal").text(year);
		}
	}

	$(function(){
		$(".xltext,.xlselect").css("margin-right","0px");
		$("._rotation").hide();
		selRotation();
		<c:if test="${param.editButtonFlag eq 'no'}">
		$(":input[type='button']").hide();
        $("#close").show();
		</c:if>
		if('${doctor.graduationYear}'==""||'${doctor.graduationYear}'==null){
			calculation();
		}
		$("#d").append($(".isOrg"));
		if('${doctor.doctorLicenseFlag}'=='N') $(".qualifiedNoTd, .qualifiedNoTh").hide();

		if('${role}'=='hospital'){
			$("#trainingSpeId").css("background-color","#ccc");
			$("#sessionNumber").css("background-color","#ccc");
			$("#rotationFlow").css("background-color","#ccc");
			$("#inHosDate").css("background-color","#ccc");
			$("#trainingSpeId").attr("disabled",true);
			$("#sessionNumber").attr("disabled",true);
			$("#rotationFlow").attr("disabled",true);
			$("#inHosDate").attr("disabled",true);
		}
	});
	
	function cutBirthday(idNo){	
		$("[name='userBirthday']").val(idNo.substr(6,4)+"-"+idNo.substr(10,2)+"-"+idNo.substr(12,2));
	}

	function selRotation(){
		$("#rotationFlow").val("");
		$("._rotation").hide();
		var doctorCategoryId = '${recDocCategoryEnumIntern.id}';
		var trainingSpeId = $("#trainingSpeId").val();
		var workOrgId = $("#workOrgId").val();
		if(doctorCategoryId && trainingSpeId){
			var selector = "._"+doctorCategoryId+"._"+trainingSpeId;
			if(workOrgId!="")
			{
				 selector = "._"+doctorCategoryId+"._"+trainingSpeId+"._work"+workOrgId;
			}
			$(selector).show();
			if(condition==selector){
				$(selector+"[value='${doctor.rotationFlow}']").attr("selected","selected");
			}else{
				$(selector+":first").attr("selected","selected");
			}
		}
	}
	
	function changeUserInfo(){
		var doctorCategoryId='${recDocCategoryEnumIntern.id}';
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
		if(!$("#workOrgId").val()){
			jboxTip("请选择派送院校!");
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
		changeUserInfo();
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
	}
	
	function doClose(){
		jboxCloseMessager();
	}
	
	function resetPasswd(userFlow){
		jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
			var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
				//searchUser();			
			});		
		});
	}
	function option(obj){
		$("#d").append($(".isOrg"));
		$("#rotationFlow").append($("#d .isOrg."+obj));
	}



	$(document).ready(function(){
		var selDepts = $(".box:checked");
		selDepts.each(function(){
			dataView(this.value);
		});

		$(".treeMain,.sTreeMain").each(function(){
			if($("[class^='tree'] :checked",this).length){
				$("[class^='op']:first",this).click();
			}
		});
	});


//	function dataView(value){
//		var reg = /\./g;
//		var regValue = value.replace(reg,"\\.");
//		var inputVal = $("#schDeptName"+regValue).val();
//		var selDepts = $(".box:checked");
//		console.log(selDepts.length);
//		if(selDepts.length>0){
//			selDepts.each(function(){
//				$("#standardDeptSel").val(inputVal);
//			});
//		}else{
//			$("#standardDeptSel").val("");
//		}
//
//	}

	$(function(){
		$("#standardDeptName").likeSearchInit({

		});
	});
	//切换树
	function treeFunc(id){
		var reg = /\./g;
		id = id.replace(reg,"\\.");
		$(".tree"+id).toggle();
		$(".op"+id).toggle();
	}

function getFantasticFour(){
	if($(':radio[name="userResumeExt.isDoctor"]:checked').val()=='${GlobalConstant.FLAG_Y}'){
		$("#uEducationName").val("博士");
		$("#dGraduatedName").val($("#bsbyyx").val());
		$("#dSpecialized").val($("#bsbyzy").val());
		$("#dGraduationTime").val($("#bsbysj").val());
	}else if($(':radio[name="userResumeExt.isMaster"]:checked').val()=='${GlobalConstant.FLAG_Y}'){
		$("#uEducationName").val("硕士");
		$("#dGraduatedName").val($("#byyx").val());
		$("#dSpecialized").val($("#ssbyzy").val());
		$("#dGraduationTime").val($("#ssbysj").val());
	}else{
		$("#uEducationName").val($("#degree  option:selected").text());
		$("#dGraduatedName").val($("#bkbyyx").val());
		$("#dSpecialized").val($("#bkbyzy").val());
		$("#dGraduationTime").val($("#bkbysj").val());
	}
}
function getUserResumeExtName(){
	var technologyQualificationId = $("#technologyQualificationId").val();
	var technologyQualificationName = "";
	if(technologyQualificationId != ""){
		technologyQualificationName = $(	"#technologyQualificationId :selected").text();
	}
	$("#technologyQualificationName").val(technologyQualificationName);

	var qualificationMaterialId = $("#qualificationMaterialId").val();
	var qualificationMaterialName = "";
	if(qualificationMaterialId != ""){
		qualificationMaterialName = $("#qualificationMaterialId :selected").text();
	}
	$("#qualificationMaterialName").val(qualificationMaterialName);

	var practicingCategoryId = $("#practicingCategoryId").val();
	var practicingCategoryName = "";
	if(practicingCategoryId != ""){
		practicingCategoryName = $("#practicingCategoryId :selected").text();
	}
	$("#practicingCategoryName").val(practicingCategoryName);

	var practicingScopeId = $("#practicingScopeId").val();
	var practicingScopeName= "";
	if(practicingScopeId != ""){
		practicingScopeName = $("#practicingScopeId :selected").text();
	}
	$("#practicingScopeName").val(practicingScopeName);
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
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="16%"/>
			<col width="20%"/>
			</colgroup>
			<tr>
				<input name="doctorCategoryId" type="hidden" value="${recDocCategoryEnumIntern.id}"/>
				<th>
					<c:if test="${empty user}">
						<font color="red">*</font>
					</c:if>用户名：</th>
				<td>
					<c:if test="${empty user}">
						<input type="text" name="userCode" value="${user.userCode}" class="validate[required,custom[userCode]]">
					</c:if>
					<c:if test="${!empty user}">
						${user.userCode}
						<input type="hidden" name="userCode" value="${user.userCode}"/>
					</c:if>
				</td>
				<th><font color="red">*</font>真实姓名：</th>
				<td>
					<input type="text" name="userName" value="${user.userName}" class="validate[required]"/>
				</td>
				<th><font color="red">*</font>性别：</th>
				<td>
					<label><input type="radio" name="sexId" class="validate[required]" <c:if test="${user.sexId eq userSexEnumMan.id}">checked="checked"</c:if>  value="${userSexEnumMan.id}" />男</label>&#12288;
					<label><input type="radio" name="sexId" class="validate[required]" <c:if test="${user.sexId eq userSexEnumWoman.id}">checked="checked"</c:if> value="${userSexEnumWoman.id}" />女</label>

				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>年龄：</th>
				<td>
					<input type="text" name="age" value="${extInfo.age}" class="validate[required]"/>

				</td>
				<th><font color="red">*</font>证件类型：</th>
				<td>
					<select name="cretTypeId" class="validate[required]">
						<c:forEach items="${certificateTypeEnumList}" var="certType">
							<option value="${certType.id}"
									<c:if test="${user.cretTypeId eq certType.id}">selected</c:if>>${certType.name}</option>
						</c:forEach>
					</select>
				</td>
				<th><font color="red">*</font>证件号码：</th>
				<td>
					<input type="text" name="idNo" id="idNo" value="${user.idNo}" class="validate[required]">
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>民族：</th>
				<td>
					<input type="hidden" id="nationName" name="nationName" value="">
					<select id="nationId" name="nationId" class="validate[required]" onchange="changeNationName();">
						<option/>
						<c:forEach items="${userNationEnumList}" var="nation">
							<option value="${nation.id}" ${user.nationId eq nation.id?'selected':''}>${nation.name}</option>
						</c:forEach>
					</select>
				</td>
				<script>
					function changeNationName (){
						var nationName=$("#nationId").find("option:selected").text()
						$("#nationName").val(nationName);
					}
				</script>
					<th><font color="red">*</font>手机：</th>
					<td>
						<input type="text" name="userPhone" value="${user.userPhone}" class="validate[required,custom[mobile]]"/>
					</td>
				<th ><font color="red">*</font>邮箱：</th>
				<td>
					<input type="text" name="userEmail" value="${user.userEmail}" class="validate[required,custom[email]]"/>
					<input type="hidden" name="doctorTypeId" value="${recDocCategoryEnumGraduate.id}"  id="doctorTypeId" />
					<input name="doctorStatusId" type="hidden" value="${resDoctorStatusEnumTraining.id}"/>
				</td>
			</tr>
		</table>
		<div id="d" style="display: none;">
			
		</div>
		<table id="rotationInfo" style="width:100%;">
			<caption>培训信息</caption>
			<colgroup>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="16%"/>
			<col width="20%"/>
			</colgroup>
			<tr>
				<th><font color="red">*</font>年&#12288;&#12288;级：</th>
				<td>
					<select name="sessionNumber" id="sessionNumber" onchange="selRotation();calculation();">
						<option></option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
							<option value="${dict.dictName}" ${doctor.sessionNumber eq dict.dictName?'selected="selected"':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<th><font color="red">*</font>实习年限：</th>
				<td>
					<select name="trainingYears" id="trainingYears" onchange="calculation();">
						<option></option>
						<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
							<option value="${dict.dictId}" ${doctor.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>

				</td>
				<th><font color="red">*</font>派送院校：</th>
				<td>
					<select name="workOrgId" id="workOrgId" onchange="selRotation();" class="validate[required]">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
							<option value="${dict.dictId}" ${doctor.workOrgId eq dict.dictId?'selected="selected"':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
					<th><font color="red">*</font>实习基地：</th>
					<td>
						<select name="orgFlow" class="validate[required]" onchange="option(this.value)">
							<option>
							<c:forEach items="${orgList}" var="org">
								<option value="${org.orgFlow}" ${org.orgFlow eq doctor.orgFlow?'selected':''}>${org.orgName}</option>
							</c:forEach>
						</select>

					</td>
				</c:if>
				<c:if test="${GlobalConstant.USER_LIST_GLOBAL != param.roleFlag}">
						<input name="orgFlow" type="hidden" value="${doctor.orgFlow}"/>
				</c:if>
				<th><font color="red">*</font>实习专业：</th>
				<td>
					<c:if test="${!rotationInUse}">
						<select name="trainingSpeId" id="trainingSpeId" onchange="selRotation();">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected="selected"':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${rotationInUse}">
						<label>${doctor.trainingSpeName}</label>
					</c:if>
				</td>
				<th><font color="red">*</font><label class="rotationAttr">轮转方案：</label></th>
				<td colspan="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'1':'3'}">
					<c:if test="${!rotationInUse}">
						<select name="rotationFlow" class=" rotationAttr" id="rotationFlow" onclick="checkCondition();">
							<option></option>
							<c:forEach items="${rotationList}" var="rotation">
									<option class="_${rotation.doctorCategoryId} _${rotation.speId} _work${rotation.workOrgId} _rotation
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
				<c:if test="${GlobalConstant.USER_LIST_GLOBAL != param.roleFlag}">
					<th></th>
					<td></td>
				</c:if>
			</tr>
			<c:if test="${(not empty user.userFlow) and (param.editButtonFlag ne 'no')}">
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
		<form id="returnFrom">
			<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
			<input type="hidden" name="typeId" value="${resRecTypeEnumReturnTraining.id}"/>
			<input type="hidden" name="typeName" value="${resRecTypeEnumReturnTraining.name}"/>
			<table id="returnTable" style="width:100%;display: none;">
				<caption>退培信息</caption>
				<colgroup>
					<col width="20%"/>
					<col width="20%"/>
					<col width="60%"/>
				</colgroup>
				<tr>
					<th>
						退培类型：
					</th>
					<td>
						<select class="validate[required]"  name="policyId" style="width: 90%;">
							<option value="">请选择</option>
							<option value="1">协议退培</option>
							<option value="2">违约退培</option>
						</select>
					</td>
					<td>
						<input name="policy" value="" class="validate[required]" style="width: 86%;height: 70%" /><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>
						退培主要原因：
					</th>
					<td>
						<select class="validate[required]" name="reasonId" style="width: 90%;" >
							<option value="">请选择</option>
							<option value="1">辞职</option>
							<option value="2">考研</option>
							<option value="3">其他</option>
						</select>
					</td>
					<td>
						<input name="reason" value="" class="validate[required]" style="width: 86%;height: 70%" /><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>学员去向：</th>
					<td colspan="2">
						<input name="dispositon" value="" class="validate[required]" style="width: 90%;height: 70%"/><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>备注（培训基地意见）：</th>
					<td colspan="2">
						<input name="remark"  value="" class="validate[required]" style="width: 90%;height: 70%"/><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>
						上传附件：
					</th>
					<td colspan="2">
						<input type="file" name="file" class="validate[required]"/><span class="red">*</span>
					</td>
				</tr>
			</table>
		</form>
		<form id="delayForm">
			<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
			<table id="delayTable" style="width:100%;display: none;">
				<caption>延期信息</caption>
				<colgroup>
					<col width="10%"/>
					<col width="40%"/>
					<col width="10%"/>
					<col width="30%"/>
				</colgroup>
				<tr>
					<th>延期原因：</th>
					<td colspan="3">
						<input class="validate[required]"  id="delayreason" name="delayreason" style="width: 91%;height: 70%;" value=""><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>上传附件：</th>
					<td colspan="3">
						<input type="file" id="file" name="file" class="validate[required]"/><span class="red">*</span>
				</tr>
				<tr>
					<th>结业考核年份：</th>
					<td style="text-align: left">
						<input class="validate[required]" name="delayYear" id="delayYear" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});"
							   style="width: 20%;height: 70%;" value=""><span class="red">*</span>
					</td>
				</tr>

			</table>
		</form>
		<p style="text-align: center;">
			<c:if test="${!(waitReturn eq 'waitReturn')}">
       		<input type="button" onclick="saveDoc();" id="saveButton1" class="search" value="保&#12288;存"/>
			</c:if>
			<input type="button" onclick="saveRede();" id="saveButton2" style="display: none" class="search2" value="确认退培"/>
			<input type="button" onclick="saveDelay();" id="saveButton3" style="display: none" class="search2" value="确认延期"/>
       		<c:if test="${!param.isMenu}">
       			<input type="button" onclick="doClose();" id="close" class="search" value="关&#12288;闭"/>
       		</c:if>
			<c:if test="${role eq 'hospital'}">
			<c:if test="${sysCfgMap['res_org_delay_return'] eq 'Y'}">
				<script>
					function reCheck(obj) {
						$(obj).hide();
						$("#down").hide();
						$("#file").show();
					}
					function saveDelay(){
						if($("#delayForm").validationEngine("validate")){
							if(!$("#delayreason").val())
							{
								jboxTip("请输入延期原因!");
								return false;
							}
							if(!$("#file").val())
							{
								jboxTip("请上传附件!");
								return false;
							}
							if("${doctor.graduationYear}">=$("#delayYear").val()){
								jboxTip("延期结业年份应大于现结业年份");
								return false;
							}
							jboxConfirm("确认延期？",function(){
								var url='<s:url value="/res/doc/delayDoc"/>';
								jboxSubmit($("#delayForm"),url,function(resp){
									if(resp==1) {
										jboxTip("延期成功!");
										window.parent.frames['mainIframe'].search();
										jboxClose();
									}else if(resp ==3){
										jboxTip("上传附件不得大于10M!");
									}
									else  jboxTip("延期失败!");
								},null,false)
							})
						}
					}
					function saveRede(){
						if($("#returnFrom").validationEngine("validate")){
							jboxConfirm("确认退培？",function(){
								var url='<s:url value="/res/doc/returnDoc"/>';
								jboxSubmit($("#returnFrom"),url,function(resp){
									if(resp==1) {
										jboxTip("退培成功!");
										window.parent.frames['mainIframe'].search();
										jboxClose();
									}else if(resp ==3){
										jboxTip("上传附件不得大于10M!");
									}
									else jboxTip("退培失败!");
								},null,false)
							})
						}
					}
					var count = 0;
					var count2 = 0;
					function returnModel(){
						count2 = 0;
						$("#delayButton").val("延    期");
						$("#delayTable").hide();
						$("#saveButton3").hide();
						$("#returnTable").toggle();
						$("#saveButton2").toggle();
						if(count%2==0){
							$("#returnButton").val("取消退培");
							$("#saveButton1").hide();
						}else{
							$("#returnButton").val("退    培");
							$("#saveButton1").show();
						}
						count+=1;
					}

					function delayModel(){
						count = 0;
						$("#returnButton").val("退    培");
						$("#returnTable").hide();
						$("#saveButton2").hide();
						$("#delayTable").toggle();
						$("#saveButton3").toggle();
						if(count2%2==0){
							$("#delayButton").val("取消延期");
							$("#saveButton1").hide();
						}else{
							$("#delayButton").val("延    期");
							$("#saveButton1").show();
						}
						count2+=1;
					}
				</script>
				<c:if test="${!(waitReturn eq 'waitReturn')}">
					<input type="button" id="delayButton" onclick="delayModel();" class="search2" value="延&#12288;期"/>
					<input type="button" id="returnButton" onclick="returnModel();" class="search2" value="退&#12288;培"/>
				</c:if>
				<c:if test="${waitReturn eq 'waitReturn'}">
					<span style="font-size: inherit;color:red">退培待省厅审核中</span>
				</c:if>
			</c:if>
			</c:if>
       </p>
		</div>
	</div>
</div>
</body>
</html>