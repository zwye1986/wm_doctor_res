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
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<script type="text/javascript">
function edit(){
	  jboxOpen("<s:url value='/jsp/gyxjgl/student/infoMaintain.jsp'/>","课程编辑",850,500);
	}
function check(){
	var conditionStatus = $("#conditionStatus").val();
	$("#conditionStatus").val(!(conditionStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
	conditionStatus = $("#conditionStatus").val();
	$(".spreadOut").toggle(conditionStatus=="${GlobalConstant.FLAG_Y}");
}	
$(function(){
	var datas = [];
	var selected = []
	<c:forEach items="${dictTypeEnumGyStudyFormList}" var="studyForm">
		var arry = {"id":"${studyForm.dictId}","text":"${studyForm.dictName}"};
		datas.push(arry);
		<c:if test="${pdfn:contains(param.studyFormName,studyForm.dictName)}">
			selected.push(arry);
		</c:if>
	</c:forEach>
	if(datas.length!=0){
		$.itemSelect("studyFormId",datas,null,null,selected,88,200);
	}
	$("#detail").slideInit({
		width:1000,
		speed:500,
		outClose:true,
		haveZZ:true
	});
	$(".spreadOut").toggle("${param.conditionStatus eq GlobalConstant.FLAG_Y}"=="true");
});

function add(flow){
	jboxStartLoading();
	<c:if test="${from != GlobalConstant.USER_LIST_GLOBAL}">
		url = "<s:url value='/gyxjgl/user/eduUserFormEdit/doctor?head=true&flag=N&qrztFlag=N&isFeeMaster=${isFeeMaster}&flow='/>"+flow;
	</c:if>
	<c:if test="${from == GlobalConstant.USER_LIST_GLOBAL}">
		url="<s:url value='/gyxjgl/user/eduUserFormEdit/${GlobalConstant.RES_ROLE_SCOPE_GLOBAL}'/>?isFeeMaster=${isFeeMaster}&flow="+flow+"&head=true&flag=Y";
	</c:if>
	jboxLoad("detail",url,false);
	$("#detail").rightSlideOpen();
}
function del(flow){
	jboxConfirm("是否确认删除",function(){
		jboxPost("<s:url value='/gyxjgl/user/delEduUser'/>?recordFlow="+flow+"&recordStatus=N",null,function(obj){
			if(obj=="${GlobalConstant.SAVE_SUCCESSED}"){
				search();
			}
		});
	});
}
	
function importSchoolRoll(){
	var url = "<s:url value='/gyxjgl/user/importSchoolRoll'/>";
	jboxOpen(url, "导入",600,200);
	
}
function exportExcel(){
	jboxConfirm("是否确定导出？",function(){
		var url = "<s:url value='/gyxjgl/user/exportExcel'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url,null,null,false);
		jboxEndLoading();
	});
}
function eduOption(){
	var url = "<s:url value='/gyxjgl/user/eduOption'/>?from=${from}&isFeeMaster=${isFeeMaster}";
	jboxOpen(url, "学籍分类操作",940,500);
}
function search(){
	jboxStartLoading();
	$("#searchForm").submit();
}
function saveCfg(status) {
	var url = "<s:url value='/sys/cfg/save'/>";
	var data = $('#saveCfgForm'+status).serialize();
	jboxPost(url, data,null,null,false);
}
function checkYear(obj){
	var startDate=$("[name='startDate']").val();
	var endDate=$("[name='endDate']").val();
	if(startDate && endDate &&  startDate>endDate){
		obj.value="";
		jboxTip("前后时间不符");
	}
	return startDate && endDate &&  startDate>endDate;
	
}
function checkDay(obj){
	var startBirthday=$("[name='startBirthday']").val();
	var endBirthday=$("[name='endBirthday']").val();
	if(startBirthday && endBirthday && startBirthday>endBirthday){
		obj.value="";
		jboxTip("前后时间不符");
	}
	return startBirthday && endBirthday && startBirthday>endBirthday;
}

function toPage(page) {
	if(page){
		$("#currentPage").val(page);			
	}
	search();
}
function sysCfgUpdate(startCode,endCode){
	var startValue=$("#start").val();
	var endValue=$("#end").val();
	var url="<s:url value='/gyxjgl/course/manage/sysCfgUpdate'/>?startCode="+startCode+"&startValue="+startValue+"&endCode="+endCode+"&endValue="+endValue;
	 jboxPost(url,null,function(){
		
	},null,true); 
}
function searchPartStatus(userFlow){
	var url = "<s:url value='/gyxjgl/user/searchPartStatus'/>?userFlow="+userFlow;
	jboxOpen(url, "学籍信息状态查询",800,500);
}
	function registerForm(userFlow){
		var url = "<s:url value='/gyxjgl/user/schoolRegister?roleFlag=${from}&userFlow='/>"+userFlow;
		jboxOpen(url, "登记表",980,560);
	}
	function batchExpTbl(){
		var url = "<s:url value='/gyxjgl/user/batchExpXjTbl'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url,null,null,false);
		jboxEndLoading();
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
	<form id="searchForm" action="<s:url value="/gyxjgl/user/eduUserList"/>" method="post">
		<table class="basic" style="width:100%;min-width: 1080px;margin: 10px 0px 5px -20px;border: none">
			<tr>
				<td style="border: none;">
					 <input id="currentPage" type="hidden" name="currentPage"/>
					<input type="hidden" name="flag" value="${flag}"/>
						<div>
							<div>&#12288;
							学&#12288;&#12288;号：<input class="year"  type="text" style="width: 137px;" name="sid" value="${param.sid}" >
							&#12288;<span style="padding-left:26px;"></span>姓&#12288;&#12288;名：<input type="text" style="width: 137px;" name="userName" value="${param.userName }">
							<input type="hidden"  name="from" value="${from}">
								&#12288;
							培养单位：<c:choose>
								<c:when test="${GlobalConstant.USER_LIST_LOCAL== from}"><font color="blue">${ sessionScope.currUser.orgName}</font></c:when>
								<c:otherwise>
										<select  name="orgFlow" style="width: 120px;">
											<option></option>
											<c:forEach items="${orgList }" var="org">
												<option value="${org.orgFlow }" <c:if test="${org.orgFlow==param.orgFlow or orgFlow==org.orgFlow }">selected</c:if>>${org.orgName }</option>
											</c:forEach>
										</select>
								</c:otherwise>
							</c:choose>
								<input type="hidden" id="conditionStatus" name="conditionStatus" value="${param.conditionStatus}">
								<input type="button" class="search" onclick="search();" value="查&#12288;询">
								<c:if test="${!isFeeMaster}">
									<c:if test="${flag eq 'view' or from ne 'global'}">
										<input type="button" class="search" value="学籍信息导出" onclick="exportExcel();"/>
									</c:if>
									<c:if test="${applicationScope.sysCfgMap['xjgl_imp_student'] != GlobalConstant.FLAG_Y && GlobalConstant.USER_LIST_GLOBAL == from && flag eq 'edit'}">
										<input type="button" class="search" value="新&#12288;增" onclick="add('');"/>
									</c:if>
									<c:if test="${applicationScope.sysCfgMap['xjgl_imp_student'] == GlobalConstant.FLAG_Y &&  GlobalConstant.USER_LIST_GLOBAL == from && flag eq 'edit'}">
										<br/>&nbsp<input type="button" class="search" value="新&#12288;增" onclick="add('');"/>
										<input type="button" class="search" value="批量导入" onclick="importSchoolRoll();"/>
									</c:if>
								</c:if>
								<c:if test="${flag eq 'set'}">
									<input type="button" class="search" value="学籍分类操作" onclick="eduOption();"/>
								</c:if>
								<input type="button" class="search" onclick="batchExpTbl();" value="学籍表批量导出">
							</div>
							<div style="display:${flag eq 'view' or flag eq 'edit' or from ne 'global'?';':'none;'}">
								&#12288;
								<a onclick="check();" style="cursor: pointer; color: blue;" >[更多检索条件]</a>
								<span style="color:red;padding-left:15px;display:${empty param.atSchoolStatusId?'none':''};">在校状态：共有<c:forEach items="${dictTypeEnumGyAtSchoolStatusList}" var="sta">${param.atSchoolStatusId eq sta.dictId?sta.dictName:''}</c:forEach>${empty eduUserList[0].zxStatusCount?0:eduUserList[0].zxStatusCount}人</span>
								<span style="color:red;padding-left:15px;display:${empty param.schoolRollStatusId?'none':''};">学籍注册状态：共有<c:forEach items="${dictTypeEnumGySchoolRollStatusList}" var="sta">${param.schoolRollStatusId eq sta.dictId?sta.dictName:''}</c:forEach>${empty eduUserList[0].xjzcStatusCount?0:eduUserList[0].xjzcStatusCount}人</span>
							</div>
						</div>
						<div class="spreadOut"  style="display: none;">&#12288;
							在校状态：<select  class="open" name="atSchoolStatusId" style="width:141px;">
							<option/>
							<c:forEach items="${dictTypeEnumGyAtSchoolStatusList}" var="atSchool">
								<option value="${atSchool.dictId}" ${param.atSchoolStatusId eq atSchool.dictId?'selected':''}>${atSchool.dictName}</option>
							</c:forEach>
							</select>&#12288;
							<span style="padding-left:26px;"></span>
							授予学位：<select name="awardDegreeFlag" style="width: 141px;">
								<option/>
								<option value="${GlobalConstant.FLAG_Y}" ${param.awardDegreeFlag eq GlobalConstant.FLAG_Y?'selected':''}>是</option>
								<option value="${GlobalConstant.FLAG_N}" ${param.awardDegreeFlag eq GlobalConstant.FLAG_N?'selected':''}>否</option>
							</select>&#12288;
							班级：<select style="width: 141px;" name="classId">
								<option/>
								<c:forEach items="${dictTypeEnumGyXjClassList}" var="xjclass">
									<option value="${xjclass.dictId}" ${param.classId eq xjclass.dictId?'selected':''}>${xjclass.dictName}</option>
								</c:forEach>
							</select>
							<span style="padding-left:26px;"></span>
							学&#12288;&#12288;年：<input class="year" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" type="text" style="width: 80px;" name="startDate" value="${param.startDate}" onchange="checkYear(this)"> -
							<input class="year" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="endDate" type="text" style="width: 80px;" value="${param.endDate}" onchange="checkYear(this)"/>
							</div>
						<div class="spreadOut"  style="display: none;">&#12288;
							年&#12288;&#12288;级：<input class="year" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" type="text" name="period" value="${param.period }" style="width: 137px;">
							<span style="padding-left:14px;"></span>
							5+3培养模式：<select style="width: 141px;" name="is5plus3">
								<option/>
								<option value="${GlobalConstant.FLAG_Y}" ${param.is5plus3 eq GlobalConstant.FLAG_Y?'selected':''}>是</option>
								<option value="${GlobalConstant.FLAG_N}" ${param.is5plus3 eq GlobalConstant.FLAG_N?'selected':''}>否</option>
							</select>
							<%--<c:if test="${exitFlag ne 'Y'}"><span style="padding-left:132px;"></span></c:if>--%>
							&#12288;专业：<select style="width: 141px;" name="majorId">
								<option/>
								<c:forEach items="${dictTypeEnumGyMajorList}" var="major">
									<option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>[${major.dictId}]${major.dictName}</option>
								</c:forEach>
							</select>
							<span style="padding-left:26px;"></span>
							学号范围：<input type="text" name="startSid" value="${param.startSid }" style="width: 80px;">
								 -
								  <input type="text" name="endSid" value="${param.endSid }" style="width: 80px;">
						</div>
						<div class="spreadOut" style="display: none;">&#12288;
							性&#12288;&#12288;别：<select style="width: 141px;" name="sexId">
								<option/>
									<option value="${userSexEnumMan.id}" ${param.sexId eq userSexEnumMan.id?'selected':''}>${userSexEnumMan.name}</option>
									<option value="${userSexEnumWoman.id}" ${param.sexId eq userSexEnumWoman.id?'selected':''}>${userSexEnumWoman.name}</option>
							</select>&#12288;
							<span style="padding-left:26px;"></span>
							培养类型：<select style="width: 141px;" name="trainCategoryId">
								<option/>
								<c:forEach items="${dictTypeEnumGyTrainCategoryList}" var="train">
									<option value="${train.dictId}" ${param.trainCategoryId eq train.dictId?'selected':''}>${train.dictName}</option>
								</c:forEach>
							</select>
							&#12288;民族：<select style="width: 141px;" name="nationId">
								<option/>
								<c:forEach items="${userNationEnumList}" var="nation">
								<option value="${nation.id}" ${sysUser.nationId eq nation.id?'selected':''}>${nation.name}</option>
							</c:forEach>
							</select>
							<span style="padding-left:26px;"></span>
							出生年月：<input type="text" name="startBirthday"   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.startBirthday }" style="width: 80px;" onchange="checkDay(this);"> - <input type="text" name="endBirthday" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.endBirthday }" style="width: 80px;" onchange="checkDay(this);">
						</div>
						<div class="spreadOut" style="display: none;">
						&#12288;
							政治面貌：<select style="width: 141px;" name="politicsStatusId">
								<option/>
								<c:forEach items="${politicsAppearanceEnumList}" var="politics">
								<option value="${politics.id}" ${param.politicsStatusId eq politics.id?'selected':''}>${politics.name}</option>
							</c:forEach>
							</select>&#12288;
							<span style="padding-left:26px;"></span>
							婚姻状况：<select style="width: 141px;" name="maritalId">
								<option></option>
								<c:forEach items="${marriageTypeEnumList}" var="marital">
								<option value="${marital.id}" ${param.maritalId eq marital.id?'selected':''}>${marital.name}</option>
							</c:forEach>
							</select>
							&#12288;导师：<input type="text" style="width: 137px;" name="teacherName" value="${param.teacherName }">
							<span style="padding-left:26px;"></span>
							确认状态：<select style="width: 84px;" name="partId">
								<option/>
								<c:forEach items="${xjPartStatusEnumList}" var="part">
									<option value="${part.id}" ${param.partId eq part.id?'selected':''}>${part.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:10px;"></span>
							<select style="width: 84px;" name="partStatus">
								<option/>
								<option value="N" ${param.partStatus eq 'N'?'selected':''}>未确认</option>
								<option value="Y" ${param.partStatus eq 'Y'?'selected':''}>已确认</option>
							</select>
						</div>
						<div class="spreadOut" style="display: none;">
							&#12288;
							录取类别：<select style="width: 141px;" name="admitTypeId">
								<option/>
								<c:forEach items="${dictTypeEnumGyAdmitTypeList}" var="admitType">
									<option value="${admitType.dictId}" ${param.admitTypeId eq admitType.dictId?'selected':''}>${admitType.dictName}</option>
								</c:forEach>
							</select>&#12288;
							<span style="padding-left:26px;"></span>
							培养层次：<select style="width: 141px;" name="trainTypeId">
							<option/>
							<c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
								<c:if test="${trainType.dictId ne '3'}">
									<option value="${trainType.dictId}" ${param.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
								</c:if>
							</c:forEach>
							</select>
							&#12288;破格：<select style="width: 141px;" name="isExceptionalId">
							<option/>
							<c:forEach items="${dictTypeEnumGyIsExceptionalList }" var="dict">
								<option value="${dict.dictId }" <c:if test="${param.isExceptionalId eq dict.dictId }">selected</c:if>>${dict.dictName }</option>
							</c:forEach>
							</select>
							<span style="padding-left:26px;"></span>
							考生来源：<select style="width: 183px;" name="studentSourceId">
								<option/>
								<c:forEach items="${dictTypeEnumGyStudentSourceList}" var="studentSource">
									<option value="${studentSource.dictId}" ${param.studentSourceId eq studentSource.dictId?'selected':''}>${studentSource.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="spreadOut" style="display: none;">
							&#12288;
							硕博连读：<select style="width: 141px;" name="isMbaDba">
								<option/>
								<option value="${GlobalConstant.FLAG_Y}" ${param.isMbaDba eq GlobalConstant.FLAG_Y?'selected':''}>是</option>
								<option value="${GlobalConstant.FLAG_N}" ${param.isMbaDba eq GlobalConstant.FLAG_N?'selected':''}>否</option>
							</select>&#12288;
							<span style="padding-left:39px;"></span>
							推免生：<select style="width: 141px;" name="isRecommendId">
								<option/>
								<c:forEach items="${dictTypeEnumGyIsRecommendList }" var="dict">
								    <option value="${dict.dictId }" <c:if test="${param.isRecommendId eq dict.dictId }">selected</c:if>>${dict.dictName }</option>
								</c:forEach>
							</select>
							&#12288;报到：<select style="width: 141px;" name="isReported">
								<option/>
								<option value="${GlobalConstant.FLAG_Y}" ${param.isReported eq GlobalConstant.FLAG_Y?'selected':''}>是</option>
								<option value="${GlobalConstant.FLAG_N}" ${param.isReported eq GlobalConstant.FLAG_N?'selected':''}>否</option>
							</select>

							<c:if test="${GlobalConstant.USER_LIST_GLOBAL == from or  GlobalConstant.USER_LIST_CHARGE == from or 'dept' == from}">
								<c:set var="exitFlag" value="Y" />
								学位分委员会：<c:choose>
								<c:when test="${GlobalConstant.USER_LIST_CHARGE == from}"><input style="width: 183px;color: blue;border: none;" value="${sessionScope.currUser.deptName}"/></c:when>
								<c:otherwise>
									<select style="width: 183px;" name="trainOrgId">
										<option></option>
										<c:forEach items="${deptList }" var="dept">
											<option value="${dept.deptFlow}" <c:if test="${dept.deptFlow==param.trainOrgId or trainOrgId==dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
										</c:forEach>
									</select>
								</c:otherwise>
							</c:choose>
							</c:if>
						</div>
					<div class="spreadOut" style="display: none;">&#12288;
						学习形式：
						<%--<select style="width: 141px;" name="studyFormId">--%>
							<%--<option/>--%>
							<%--<c:forEach items="${dictTypeEnumGyStudyFormList}" var="studyForm">--%>
								<%--<option value="${studyForm.dictId}" ${param.studyFormId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
						<input id="studyFormId" name="studyFormName" style="width:138px;margin-left:-3px;" type="text" readonly="readonly" />
						&#12288;
						<span style="padding-left:9px;"></span>登记表情况：<select style="width:141px;" name="registerFlag">
							<option/>
							<option value="${GlobalConstant.FLAG_Y}" ${param.registerFlag eq GlobalConstant.FLAG_Y?'selected':''}>已填</option>
							<option value="${GlobalConstant.FLAG_N}" ${param.registerFlag eq GlobalConstant.FLAG_N?'selected':''}>未填</option>
						</select>
						&#12288;<span style="display:inline-block;width:180px;"></span>
						<span style="padding-left:0px;"></span>学籍注册状态：<select style="width:183px;" name="schoolRollStatusId">
							<option/>
							<c:forEach items="${dictTypeEnumGySchoolRollStatusList}" var="sta">
								<option value="${sta.dictId}" ${param.schoolRollStatusId eq sta.dictId?'selected':''}>${sta.dictName}</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
		</table>
		</form>

		<div class="resultDiv">
			<table class="basic" width="100%">
				<tr style="font-weight: bold;">
				<td style="text-align: center;padding: 0px;">入学年级</td>
				<td style="text-align: center;padding: 0px;">班级</td>
				<td style="text-align: center;padding: 0px;">培养层次</td>
				<td style="text-align: center;padding: 0px;">培养类型</td>
				<td style="text-align: center;padding: 0px;">学号</td>
				<td style="text-align: center;padding: 0px;">姓名</td>
				<td style="text-align: center;padding: 0px;">性别</td>
				<td style="text-align: center;padding: 0px;">专业名称</td>
				<td style="text-align: center;padding: 0px;">学位分委员会</td>
				<td style="text-align: center;padding: 0px;">培养单位</td>
				<td style="text-align: center;padding: 0px;">导师</td>
				<td style="text-align: center;padding: 0px;">学籍注册状态</td>
				<c:if test="${flag eq 'edit' or from ne 'global'}">
					<td style="text-align: center;padding: 0px;">操作</td>
				</c:if>
				<c:if test="${flag eq 'view' or from ne 'global'}">
					<td style="text-align: center;padding: 0px;">确认状态</td>
				</c:if>
			</tr>
			<c:forEach items="${eduUserList}" var="eduUser">
			<c:set var="user" value="${userMap[eduUser.userFlow]}"/>
			<c:set var="doctor" value="${resDoctorMap[eduUser.userFlow]}"/>
				<tr>
					<td style="text-align: center;padding: 0px;">${eduUser.period }</td>
					<td style="text-align: center;padding: 0px;">${eduUser.className }</td>
					<td style="text-align: center;padding: 0px;">${eduUser.trainTypeName }</td>
					<td style="text-align: center;padding: 0px;">${eduUser.trainCategoryName}</td>
					<td style="text-align: center;padding: 0px;">${eduUser.sid }</td>
					<td style="text-align: center;padding: 0px;">${user.userName }</td>
					<td style="text-align: center;padding: 0px;">${user.sexName }</td>
					<td style="text-align: center;padding: 0px;">[${eduUser.majorId }]${eduUser.majorName }</td>
					<td style="text-align: center;padding: 0px;">${eduUser.trainOrgName }</td>
					<td style="text-align: center;padding: 0px;">${doctor.orgName }</td>
					<td style="text-align: center;padding: 0px;">
						${!empty eduUser.firstTeacher?eduUser.firstTeacher:''}
						<c:if test="${!empty eduUser.secondTeacher }">
							${!empty eduUser.firstTeacher?'，':''}${eduUser.secondTeacher }
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px;">${eduUser.schoolRollStatusName}</td>
					<c:if test="${flag eq 'edit' or from ne 'global'}">
						<td style="text-align: center;padding: 0px;">
							<a onclick="add('${eduUser.userFlow}');" style="cursor: pointer;color: blue;">
								<c:out value="${from != GlobalConstant.USER_LIST_GLOBAL?'查看':'编辑' }"/>
							</a>
							<a onclick="registerForm('${eduUser.userFlow}');" style="cursor: pointer;color: blue;">登记表</a>
						</td>
					</c:if>
					<c:if test="${flag eq 'view' or from ne 'global'}">
						<td style="text-align: center;padding: 0px;"><a onclick="searchPartStatus('${eduUser.userFlow}');" style="cursor: pointer;color: blue;">查看</a></td>
					</c:if>
			</tr>
			</c:forEach>
			<c:if test="${empty eduUserList}">
				<tr>
					<td colspan="99" style="text-align: center;">无记录！</td>
				</tr>
			</c:if>
			</table>
			   	<c:set var="pageView" value="${pdfn:getPageView(eduUserList)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
	</div>	
	</div>
		<div id="detail" style="background: white;">
       	</div>
</body>	
</html>