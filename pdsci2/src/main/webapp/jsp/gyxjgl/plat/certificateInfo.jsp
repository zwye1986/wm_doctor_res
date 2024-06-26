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

<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
//更多检索条件伸缩展示
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
//查看学籍信息
function edit(flow){
	jboxStartLoading();
	var url= "<s:url value='/gyxjgl/user/userCertificateInfo'/>?userFlow="+flow;
	jboxLoad("detail",url,false);
	$("#detail").rightSlideOpen();
}
//查看证书
function certificatInfo(userFlow,certificateTypeId){
//	if($('#certificateType').val() == ""){
//		jboxTip("请先选择证书");
//		return;
//	}
	jboxOpen("<s:url value='/gyxjgl/user/certificateDetail?roleFlag=school&certificateType='/>"+certificateTypeId+"&userFlow="+userFlow ,"证书信息",700, (window.screen.height) * 0.65);
}
//查询
function search(){
	$("#orgFlow").val("");
	if($("#orgName").val()!=""){
		$("#orgFlow").val($("#orgName").attr("flow"));
	}
	jboxStartLoading();
	$("#searchForm").submit();
}
//导出统计
function exportCertificate(){
	$("#orgFlow").val("");
	var period=$("input[name='period']").val();
	if(period==""){
		jboxTip("请选择年级后导出！");
		return;
	}
	if($("#orgName").val()!=""){
		$("#orgFlow").val($("#orgName").attr("flow"));
	}
	var url = "<s:url value='/gyxjgl/user/certificateCountExport'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
//学年控制
function checkYear(obj){
	var startDate=$("[name='startDate']").val();
	var endDate=$("[name='endDate']").val();
	if(startDate && endDate &&  startDate>endDate){
		obj.value="";
		jboxTip("前后时间不符");
	}
	return startDate && endDate &&  startDate>endDate;
	
}
//出生年月控制
function checkDay(obj){
	var startBirthday=$("[name='startBirthday']").val();
	var endBirthday=$("[name='endBirthday']").val();
	if(startBirthday && endBirthday && startBirthday>endBirthday){
		obj.value="";	
		alert("前后时间不符");
	}
	return startBirthday && endBirthday && startBirthday>endBirthday;
}
//分页
function toPage(page) {
	if(page){
		$("#currentPage").val(page);			
	}
	search();
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
			searchInput.attr("flow",$(this).attr("flow"));
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);
$(function(){
	$("#orgName").likeSearchInit({
	});
});
	function bindZsColor(value){
		$(".zsFlag").each(function(i,o){
			var remark = $(o).attr("fileRemark");
			var path = $(o).attr("filePath");
			var suffix = $(o).attr("fileSuffix");
			var product = $(o).attr("productType");
			var color = "";
			if((value == "1" && remark != "")||(value == "2" && path != "")||(value == "3" && suffix != "")||(value == "4" && product != "")){
				color = "blue";
			}
			$(".zsFlag").css("color",color);
		})
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/gyxjgl/user/certificate"/>" method="post">
		<table class="basic" style="width: 100%;margin: 10px 0px 5px -20px;border: none;">
			<tr>
				<td style="border: none;">
					 <input id="currentPage" type="hidden" name="currentPage" value=""/>
						<div>
							<div>&#12288;
								学&#12288;&#12288;号：<input class="year"  type="text" style="width: 137px;" name="sid" value="${param.sid}" >
								&#12288;<span style="padding-left:26px;"></span>培养单位：<input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value" flow="${param.orgFlow}" style="width: 137px;"/>
								<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:330px;">
									<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 141px;border-top: none;position: relative;display: none;">
										<c:forEach items="${orgList}" var="org">
											<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
										</c:forEach>
									</div>
								</div>
								<input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow }"/>

								<%--<select  name="orgFlow" style="width:141px;">--%>
									<%--<option></option>--%>
									<%--<c:forEach items="${orgList }" var="org">--%>
										<%--<option value="${org.orgFlow }" <c:if test="${org.orgFlow==param.orgFlow}">selected</c:if>>${org.orgName }</option>--%>
									<%--</c:forEach>--%>
								<%--</select>--%>
								&#12288;姓名：<input type="text" style="width: 137px;" name="userName" value="${param.userName }">
							<%--</div>--%>
							<%--<div>--%>
								<%--&#12288;--%>
								<%--选择证书：<select id="certificateType" style="width:141px" name="certificateType" onchange="bindZsColor(this.value)">--%>
									<%--<option></option>--%>
									<%--<option value="1" ${param.certificateType eq '1'?'selected':''}>学位证书</option>--%>
									<%--<option value="2" ${param.certificateType eq '2'?'selected':''}>毕业证书</option>--%>
									<%--<option value="3" ${param.certificateType eq '3'?'selected':''}>规培证书</option>--%>
									<%--<option value="4" ${param.certificateType eq '4'?'selected':''}>执医证书</option>--%>
								<%--</select>&#12288;<span style="padding-left:20px;"></span>--%>
								<input type="hidden" id="conditionStatus" name="conditionStatus" value="${param.conditionStatus}">
								<input type="button" class="search" onclick="search();" value="查&#12288;询">
								<input type="button" class="search" onclick="exportCertificate();" value="导&#12288;出" title="证明上传统计导出">
							</div>
							<div>
								&#12288;
								<a onclick="check();" style="cursor: pointer; color: blue;" >[更多检索条件]</a>&#12288;
							</div>
							
						</div>
					<%--778899--%>
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
							<c:if test="${trainType.dictId eq '1' || trainType.dictId eq '2'}">
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
						学习形式：<select style="width: 141px;" name="studyFormId">
							<option/>
							<c:forEach items="${dictTypeEnumGyStudyFormList}" var="studyForm">
								<option value="${studyForm.dictId}" ${param.studyFormId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
							</c:forEach>
						</select>
						<%--<c:if test="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}">--%>
							<%--&#12288;--%>
							<%--<span style="padding-left:9px;"></span>登记表情况：<select style="width:141px;" name="registerFlag">--%>
							<%--<option/>--%>
							<%--<option value="${GlobalConstant.FLAG_Y}" ${param.registerFlag eq GlobalConstant.FLAG_Y?'selected':''}>已填</option>--%>
							<%--<option value="${GlobalConstant.FLAG_N}" ${param.registerFlag eq GlobalConstant.FLAG_N?'selected':''}>未填</option>--%>
							<%--</select>--%>
						<%--</c:if>--%>
					</div>
					<%--778899--%>
				</td>
			</tr>
		</table>
	</form>
		<div class="resultDiv">
			<table class="basic" width="100%">
				<tr style="font-weight: bold;">
				<td style="text-align: center;padding: 0px;width:90px">入学年级</td>
				<td style="text-align: center;padding: 0px;width:100px">班级</td>
				<td style="text-align: center;padding: 0px;width:100px">培养层次</td>
				<td style="text-align: center;padding: 0px;width:100px">培养类型</td>
				<td style="text-align: center;padding: 0px;width:100px">学号</td>
				<td style="text-align: center;padding: 0px;width:100px">姓名</td>
				<td style="text-align: center;padding: 0px;width:80px">性别</td>
				<td style="text-align: center;padding: 0px;width:100px">专业名称</td>
				<td style="text-align: center;padding: 0px;width:100px">学位分委员会</td>
				<td style="text-align: center;padding: 0px;width:120px">培养单位</td>
				<td style="text-align: center;padding: 0px;width:120px;">导师</td>
				<td style="text-align: center;padding: 0px;width:100px">学籍注册状态</td>
				<td width="126" style="text-align: center;padding: 0px;">查看</td>
			</tr>
			<c:forEach items="${eduUserList}" var="eduUser">
				<c:set var="user" value="${userMap[eduUser.userFlow]}"/>
				<c:set var="doctor" value="${resDoctorMap[eduUser.userFlow]}"/>
				<c:set var="file" value="${fileMap[eduUser.userFlow]}"/>
				<tr>
					<td style="text-align: center;padding: 0px;">${eduUser.period }</td>
					<td style="text-align: center;padding: 0px;">${eduUser.className }</td>
					<td style="text-align: center;padding: 0px;">${eduUser.trainTypeName }</td>
					<td style="text-align: center;padding: 0px;">${eduUser.trainCategoryName}</td>
					<td style="text-align: center;padding: 0px;">${eduUser.sid }</td>
					<td style="text-align: center;padding: 0px;">${user.userName }</td>
					<td style="text-align: center;padding: 0px;">${user.sexName }</td>
					<td style="text-align: center;padding: 0px;line-height:180%;">[${eduUser.majorId }]${eduUser.majorName }</td>
					<td style="text-align: center;padding: 0px;line-height:180%;">${eduUser.trainOrgName }</td>
					<td style="text-align: center;padding: 0px;line-height:180%;">${doctor.orgName }</td>
					<td style="text-align: center;padding: 0px;">
						${!empty eduUser.firstTeacher?eduUser.firstTeacher:''}
						<c:if test="${!empty eduUser.secondTeacher }">
							${!empty eduUser.firstTeacher?'，':''}${eduUser.secondTeacher }
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px;">${eduUser.schoolRollStatusName}</td>
					<td style="text-align: center;padding: 0px;line-height:180%;">
						<%--<a onclick="edit('${eduUser.userFlow}');" style="cursor: pointer;color: blue;">查看</a>--%>
						<a class="zsFlag" fileRemark="${file.fileRemark}" filePath="${file.filePath}" fileSuffix="${file.fileSuffix}" productType="${file.productType}" onclick="certificatInfo('${eduUser.userFlow}','1');" style="cursor: pointer;color:
							${not empty file.fileRemark?'blue':''}"
						>学位证书</a>
						<a class="zsFlag" fileRemark="${file.fileRemark}" filePath="${file.filePath}" fileSuffix="${file.fileSuffix}" productType="${file.productType}" onclick="certificatInfo('${eduUser.userFlow}','2');" style="cursor: pointer;color:
							${not empty file.filePath?'blue':''}"
						>毕业证书</a>
						<a class="zsFlag" fileRemark="${file.fileRemark}" filePath="${file.filePath}" fileSuffix="${file.fileSuffix}" productType="${file.productType}" onclick="certificatInfo('${eduUser.userFlow}','3');" style="cursor: pointer;color:
							${not empty file.fileSuffix?'blue':''}"
						>规培证书</a>
						<a class="zsFlag" fileRemark="${file.fileRemark}" filePath="${file.filePath}" fileSuffix="${file.fileSuffix}" productType="${file.productType}" onclick="certificatInfo('${eduUser.userFlow}','4');" style="cursor: pointer;color:
							${not empty file.productType?'blue':''}"
						>执医证书</a>
					</td>
				</tr>
			</c:forEach>
				<c:if test="${empty eduUserList}">
					<td colspan="15" style="text-align: center;padding: 0px;">无记录！</td>
				</c:if>
		</table>
			<c:set var="pageView" value="${pdfn:getPageView(eduUserList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>	
</div>
<div id="detail" style="background: white;"></div>
</body>	
</html>