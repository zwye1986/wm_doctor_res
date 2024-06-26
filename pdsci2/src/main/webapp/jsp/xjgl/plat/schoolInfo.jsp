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

<script type="text/javascript">
	function edit(){
		  jboxOpen("<s:url value='/jsp/xjgl/student/infoMaintain.jsp'/>","课程编辑",850,500);
		}
	function check(){
		var conditionStatus = $("#conditionStatus").val();
		$("#conditionStatus").val(!(conditionStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
		conditionStatus = $("#conditionStatus").val();
		$(".spreadOut").toggle(conditionStatus=="${GlobalConstant.FLAG_Y}");
	}
	$(function(){
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
		var url="<s:url value='/xjgl/user/eduUserFormEdit/${from}'/>?isFeeMaster=${isFeeMaster}&isSzkMaster=${isSzkMaster}&userFlow="+flow+"&head=true";
		if('${flag}' == 'view'){
			url= "<s:url value='/xjgl/user/userCertificateInfo?flag=xj'/>&isSzkMaster=${isSzkMaster}&userFlow="+flow;
		}
		jboxLoad("detail",url,false);
		$("#detail").rightSlideOpen();
	}
	function del(flow){
		jboxConfirm("是否确认删除",function(){
			jboxPost("<s:url value='/xjgl/user/delEduUser'/>?recordFlow="+flow+"&recordStatus=N",null,function(obj){
				if(obj=="${GlobalConstant.SAVE_SUCCESSED}"){
					search();
				}
			});
		});
	}

	function importSchoolRoll(){
		var url = "<s:url value='/xjgl/user/importSchoolRoll'/>";
		jboxOpen(url, "导入",600,200);

	}
	function exportExcel(){
		jboxConfirm("是否确定导出？",function(){
			var url = "<s:url value='/xjgl/user/exportExcel'/>";
			jboxTip("导出中…………");
			jboxSubmit($("#searchForm"), url,null,null,false);
			jboxEndLoading();
		});
	}
	function exportExcel2(){
		jboxConfirm("是否确定导出？",function(){
			var url = "<s:url value='/xjgl/user/exportExcel2'/>";
			jboxTip("导出中…………");
			jboxSubmit($("#searchForm"), url,null,null,false);
			jboxEndLoading();
		});
	}
	function eduOption(){
		var url = "<s:url value='/xjgl/user/eduOption'/>?from=${from}&isFeeMaster=${isFeeMaster}";
		jboxOpen(url, "学籍分类操作",940,500);
	}
	function search(){
		jboxStartLoading();
		if($("#orgName").val() != ""){
			$("#orgFlow").val($("#orgName").attr("flow"));
		}else{
			$("#orgFlow").val("");
		}
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
		var dates = $(':text',$(obj).closest("span"));
		if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
			jboxTip("起始日期不能大于结束日期！");
			obj.value = "";
		}
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
		var url="<s:url value='/xjgl/course/manage/sysCfgUpdate'/>?startCode="+startCode+"&startValue="+startValue+"&endCode="+endCode+"&endValue="+endValue;
		 jboxPost(url,null,function(){

		},null,true);
	}
	function searchPartStatus(userFlow){
		var url = "<s:url value='/xjgl/user/searchPartStatus'/>?userFlow="+userFlow;
		jboxOpen(url, "学籍信息状态查询",800,500);
	}
	$(function(){
		$("#orgName").likeSearchInit({});
	});
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
	<form id="searchForm" action="<s:url value="/xjgl/user/eduUserList"/>" method="post">
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
										<input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value" flow="${param.orgFlow}"/>&#12288;
										<div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:555px;">
											<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
												<c:forEach items="${orgList}" var="org">
													<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
												</c:forEach>
											</div>
										</div>
										<input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow}"/>
									</c:otherwise>
								</c:choose>
								<input type="hidden" id="conditionStatus" name="conditionStatus" value="${param.conditionStatus}">
								<input type="button" class="search" onclick="search();" value="查&#12288;询">
								<c:if test="${!isFeeMaster}">
									<c:if test="${flag eq 'view'}">
										<input type="button" class="search" value="学籍信息导出" onclick="exportExcel();"/>
										<input type="button" class="search" value="学位信息导出" onclick="exportExcel2();"/>
									</c:if>
									<c:if test="${GlobalConstant.USER_LIST_GLOBAL == from && flag eq 'edit'}">
										<br/>&nbsp<input type="button" class="search" value="新&#12288;增" onclick="add('');"/>
										<c:if test="${applicationScope.sysCfgMap['xjgl_imp_student'] == GlobalConstant.FLAG_Y}">
											<input type="button" class="search" value="批量导入" onclick="importSchoolRoll();"/>
										</c:if>
									</c:if>
								</c:if>
								<c:if test="${flag eq 'set'}">
									<input type="button" class="search" value="学籍分类操作" onclick="eduOption();"/>
								</c:if>
							</div>
							<div style="display:${flag eq 'view' or flag eq 'edit' or from ne 'global'?';':'none;'}">
								&#12288;
								<a onclick="check();" style="cursor: pointer; color: blue;" >[更多检索条件]</a>
							</div>
						</div>
						<div class="spreadOut"  style="display: none;">&#12288;
							在校状态：<select  class="open" name="atSchoolStatusId" style="width:141px;">
							<option/>
							<c:forEach items="${dictTypeEnumAtSchoolStatusList}" var="atSchool">
								<option value="${atSchool.dictId}" ${param.atSchoolStatusId eq atSchool.dictId?'selected':''}>${atSchool.dictName}</option>
							</c:forEach>
							</select>&#12288;
							<span style="padding-left:26px;"></span>
							授予学位：<select name="awardDegreeFlag" style="width: 141px;">
								<option/>
								<option value="${GlobalConstant.FLAG_Y}" ${param.awardDegreeFlag eq GlobalConstant.FLAG_Y?'selected':''}>是</option>
								<option value="${GlobalConstant.FLAG_N}" ${param.awardDegreeFlag eq GlobalConstant.FLAG_N?'selected':''}>否</option>
							</select>&#12288;&#12288;&#12288;&#12288;
							班级：<select style="width: 141px;" name="classId">
								<option/>
								<c:forEach items="${dictTypeEnumXjClassList}" var="xjclass">
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
							&#12288;&#12288;&#12288;&#12288;专业：<select style="width: 141px;" name="majorId">
								<option/>
								<c:forEach items="${dictTypeEnumMajorList}" var="major">
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
								<c:forEach items="${dictTypeEnumTrainCategoryList}" var="train">
									<option value="${train.dictId}" ${param.trainCategoryId eq train.dictId?'selected':''}>${train.dictName}</option>
								</c:forEach>
							</select>
							&#12288;&#12288;&#12288;&#12288;民族：<select style="width: 141px;" name="nationId">
								<option/>
								<c:forEach items="${userNationEnumList}" var="nation">
								<option value="${nation.id}" ${sysUser.nationId eq nation.id?'selected':''}>${nation.name}</option>
							</c:forEach>
							</select>
							<span style="padding-left:26px;"></span>
							出生年月：<span><input type="text" name="startBirthday"   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.startBirthday }" style="width: 80px;" onchange="checkDay(this);"> - <input type="text" name="endBirthday" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.endBirthday }" style="width: 80px;" onchange="checkDay(this);"></span>
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
							&#12288;&#12288;&#12288;&#12288;导师：<input type="text" style="width: 137px;" name="teacherName" value="${param.teacherName }">
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
								<c:forEach items="${dictTypeEnumAdmitTypeList}" var="admitType">
									<option value="${admitType.dictId}" ${param.admitTypeId eq admitType.dictId?'selected':''}>${admitType.dictName}</option>
								</c:forEach>
							</select>&#12288;
							<span style="padding-left:26px;"></span>
							培养层次：<select style="width: 141px;" name="trainTypeId">
							<option/>
							<c:forEach items="${dictTypeEnumTrainTypeList}" var="trainType">
								<c:if test="${trainType.dictId ne '3'}">
									<option value="${trainType.dictId}" ${param.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
								</c:if>
							</c:forEach>
							</select>
							&#12288;&#12288;&#12288;&#12288;破格：<select style="width: 141px;" name="isExceptionalId">
							<option/>
							<c:forEach items="${dictTypeEnumIsExceptionalList }" var="dict">
								<option value="${dict.dictId }" <c:if test="${param.isExceptionalId eq dict.dictId }">selected</c:if>>${dict.dictName }</option>
							</c:forEach>
							</select>
							<span style="padding-left:26px;"></span>
							考生来源：<select style="width: 183px;" name="studentSourceId">
								<option/>
								<c:forEach items="${dictTypeEnumStudentSourceList}" var="studentSource">
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
								<c:forEach items="${dictTypeEnumIsRecommendList }" var="dict">
								    <option value="${dict.dictId }" <c:if test="${param.isRecommendId eq dict.dictId }">selected</c:if>>${dict.dictName }</option>
								</c:forEach>
							</select>
							&#12288;&#12288;&#12288;&#12288;报到：<select style="width: 141px;" name="isReported">
								<option/>
								<option value="${GlobalConstant.FLAG_Y}" ${param.isReported eq GlobalConstant.FLAG_Y?'selected':''}>是</option>
								<option value="${GlobalConstant.FLAG_N}" ${param.isReported eq GlobalConstant.FLAG_N?'selected':''}>否</option>
							</select>

							<c:if test="${GlobalConstant.USER_LIST_GLOBAL == from or  GlobalConstant.USER_LIST_CHARGE == from or 'dept' == from}">
								<c:set var="exitFlag" value="Y" />
								学位分委员会：<c:choose>
								<c:when test="${GlobalConstant.USER_LIST_CHARGE == from}"><font color="blue">${sessionScope.currUser.deptName}</font></c:when>
								<c:otherwise>
									<select style="width: 183px;" name="trainOrgId">
										<option></option>
										<c:forEach items="${deptList }" var="dept">
											<option value="${dept.deptFlow}" <c:if test="${param.trainOrgId eq dept.deptFlow or trainOrgId==dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
										</c:forEach>
									</select>
								</c:otherwise>
							</c:choose>
							</c:if>
						</div>
					<div class="spreadOut" style="display: none;">&#12288;
						学习形式：<select style="width: 141px;" name="studyFormId">
							<option/>
							<c:forEach items="${dictTypeEnumStudyFormList}" var="studyForm">
								<option value="${studyForm.dictId}" ${param.studyFormId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
							</c:forEach>
						</select><span style="padding-left:13px;"></span>
						授予学位年份：<input type="text" name="awardDegreeTimeYear" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.awardDegreeTimeYear}" style="width: 137px;" >
						授予学位年月：<input type="text" name="awardDegreeTimeYM" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${param.awardDegreeTimeYM}" style="width: 137px;" >
						&#12288;预毕业时间：<span><input type="text" name="startGraduateDate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.startGraduateDate}" style="width: 80px;" onchange="checkDay(this);"> - <input type="text" name="endGraduateDate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.endGraduateDate}" style="width: 80px;" onchange="checkDay(this);"></span>
					</div>
					<div class="spreadOut" style="display: none;">&#12288;
						毕业年份：<input type="text" name="graduateTimeYear" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.graduateTimeYear}" style="width: 137px;" >&#12288;
						<span style="padding-left:26px;"></span>
						毕业年月：<input type="text" name="graduateTimeYM" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${param.graduateTimeYM}" style="width: 137px;" >
					</div>
				</td>
			</tr>
		</table>
		</form>

		<div class="resultDiv">
			<table class="basic" width="100%">
				<tr style="font-weight: bold;">
				<td style="text-align: center;padding: 0px;min-width: 55px;">入学年级</td>
				<td style="text-align: center;padding: 0px;">班级</td>
				<td style="text-align: center;padding: 0px;min-width: 55px;">培养层次</td>
				<td style="text-align: center;padding: 0px;min-width: 55px;">培养类型</td>
				<td style="text-align: center;padding: 0px;">学号</td>
				<td style="text-align: center;padding: 0px;min-width: 40px;">姓名</td>
				<td style="text-align: center;padding: 0px;min-width: 30px;">性别</td>
				<td style="text-align: center;padding: 0px;">专业名称</td>
				<td style="text-align: center;padding: 0px;">学位分委员会</td>
				<td style="text-align: center;padding: 0px;">培养单位</td>
				<td style="text-align: center;padding: 0px;">导师</td>
				<td style="text-align: center;padding: 0px;line-height: 130%;">学籍注册状态</td>
				<c:if test="${flag ne 'set'}">
					<c:if test="${flag eq 'edit'}">
						<td style="text-align: center;padding: 0px;min-width: 55px;">确认状态</td>
					</c:if>
					<td style="text-align: center;padding: 0px;min-width:30px;">操作</td>
				</c:if>
			</tr>
			<c:forEach items="${eduUserList}" var="eduUser">
			<c:set var="user" value="${userMap[eduUser.userFlow]}"/>
			<c:set var="doctor" value="${resDoctorMap[eduUser.userFlow]}"/>
				<tr>
					<td style="text-align: center;padding: 0px;">${eduUser.period }</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">${eduUser.className }</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">${eduUser.trainTypeName }</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">${eduUser.trainCategoryName}</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">${eduUser.sid }</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">${user.userName }</td>
					<td style="text-align: center;padding: 0px;">${user.sexName }</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">[${eduUser.majorId }]${eduUser.majorName }</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">${eduUser.trainOrgName }</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">${doctor.orgName }</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">
						${!empty eduUser.firstTeacher?eduUser.firstTeacher:''}
						<c:if test="${!empty eduUser.secondTeacher }">
							${!empty eduUser.firstTeacher?'，':''}${eduUser.secondTeacher }
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px;line-height: 140%;">${eduUser.schoolRollStatusName}</td>
					<c:if test="${flag ne 'set'}">
						<c:if test="${flag eq 'edit'}">
							<td style="text-align: center;padding: 0px;"><a onclick="searchPartStatus('${eduUser.userFlow}');" style="cursor: pointer;color: blue;">查看</a></td>
						</c:if>
						<td style="text-align: center;padding: 0px;">
							<a onclick="add('${eduUser.userFlow}');" style="cursor: pointer;color: blue;">${flag eq 'view'?'查看':'编辑'}</a>
						</td>
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