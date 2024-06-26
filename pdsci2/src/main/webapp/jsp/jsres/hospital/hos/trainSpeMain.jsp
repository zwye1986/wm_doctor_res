<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 去掉basic，不导入jquery,注：已被上层jsp导入过了，只导一次，不重复导入了，防止impromptu绑方法失败 --%>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="bootstrapSelect" value="true"/>
	<jsp:param name="impromptu" value="true"/>
</jsp:include>
<%--<script type="text/javascript"
		src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<link rel="stylesheet" type="text/css"
	  href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<style>
	.text{
		margin-left: 0;
		width: auto;
		height: auto;
		line-height: inherit;
		color: black;
	}
	.selected a{
		padding: 0;
		background: #459ae9;
	}
	.btn{
		height: 28px !important;
		border: 1px solid #e7e7eb
	}
	.body{
		width: 90%;
		margin-left: auto;
		margin-right: auto;
		padding: 0 0 88px;
	}
	.container{
		padding-left: 0;
		padding-right: 0;
	}
	.btn-default{
		background-color: #fff;
	}
</style>
<script type="text/javascript">
	var curSessionNumber = '${sessionNumber}';
	$(document).ready(function(){
		$('#sessionNumber').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode: 2,
			format: 'yyyy'
		});

		$("#speOpt").selectpicker({});
		$("#speBaseManage").css("display", "");

		getInfo();

		$('#sessionNumber').datepicker().on("changeDate.datepicker.amui", function(event) {
			changeSpeOpt();
		});
	});

	function changeSpeOpt() {
		var searchSessionNumber = $("#sessionNumber").val();
		if(curSessionNumber != searchSessionNumber) { // 换年份的话，要重新查协同单位
			curSessionNumber= searchSessionNumber;
			jboxPost("<s:url value='/jsres/base/searchJointOrgList'/>", {orgFlow: '${orgFlow}', sessionNumber: $("#sessionNumber").val()},
					function (resp) {
						$("#speOpt").empty();
						if(resp && resp.length) { // 有协同单位
							$("#speOpt").append("<option value='" + resp[0].orgFlow + "' selected>" + resp[0].orgName + "</option>");
							resp.forEach(function (item) {
								$("#speOpt").append("<option value='" + item.jointOrgFlow + "'>" + item.jointOrgName + "</option>");
							});
							$("#speOpt").selectpicker("refresh");
							$("#speOptDiv").css("display", "inline-block");
						}else {
							$("#speOptDiv").css("display", "none");
						}
					}, function (msg) {
					}, false);
		}
	}

	// 点查询按钮，根据主协基地选择（如果有），年份，专业查询专业基地信息
	function getInfo() {
		var selectedSpe = $("#speOpt option:selected");
		if($("#speOpt").is(":visible") && !selectedSpe.length) {
			jboxTip("请至少选择一家主协基地！");
			return;
		}
		if(!$("#sessionNumber").val()) {
			jboxTip("请选择年份！");
			return;
		}
		jboxStartLoading();
		jboxPostLoad("doctorListZi", "<s:url value='/jsres/base/trainSpeMainInfo'/>", $("#searchForm").serialize(), false);
	}
</script>
<body>
<div class="div_table">
	<div>
		<div style="width: 4px;height: 20px;background-color: #44b549"></div>
		<div style="margin-left: 12px;margin-top: -20px;color: #000000; font: 15px 'Microsoft Yahei'; font-weight: 500;">专业基地</div>
	</div>
	<div style="margin: -25px 10px 20px 0px; text-align: right;">
		<form id="searchForm">
			<input type="hidden" name="orgFlow" value="${orgFlow}">
			<input type="hidden" name="ishos" value="${ishos}">
			<div id="speOptDiv" style="display:${empty resJointOrgList ? 'none' : 'inline-block'};">
				<label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">主协基地选择：</label>
				<select name="speOpt" id="speOpt" class="show-menu-arrow" multiple title="请至少选择一项">
					<c:if test='${not empty resJointOrgList}'>
						<option value="${resJointOrgList[0].orgFlow}" selected>${resJointOrgList[0].orgName}</option>
						<c:forEach items="${resJointOrgList}" var="resJointOrg">
							<option value="${resJointOrg.jointOrgFlow}">${resJointOrg.jointOrgName}</option>
						</c:forEach>
					</c:if>
				</select>
			</div>
			<label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">年份：</label>
			<input class="input" name="sessionNumber" id="sessionNumber" style="width: 100px;height: 29px;padding: 0px;"<%-- onchange="getInfo()"--%>
				   value="${param.sessionNumber==null?pdfn:getCurrYear():param.sessionNumber}"/>
			<label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">专业：</label>
			<select name="speId" id="speId" class="select" style="width: 100px;">
				<option value="">全部</option>
				<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
					<option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
				</c:forEach>
			</select>
			<input type="button" class="btn btn_green" onclick="getInfo()" value="查询" />
		</form>
	</div>

	<div id="doctorListZi" style="width: 100%;">

	</div>


<%--	<div style=" display: flex;flex-wrap: wrap;">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="status">

			<div style="height: 278px;background-color: #e8e9ec;width: 31%;margin: 5px;padding: 5px">
				<div style="background-color: white;border-radius: 15px;">
					<div style="background-color: #409eff;border-radius: 15px;color: white">
						<div class="oneleft">
							<div style="padding-left: 20px;padding-top: 15px;font-size: 16px">
								<div>${dict.dictName}&nbsp;&nbsp;(${dict.dictId})</div>
								<div style="padding-left: 25px;font-size: 14px;text-overflow: ellipsis;overflow: hidden;height: 40px">
									<c:forEach items="${deptMap}" var="d" varStatus="de">
										<c:if test="${dict.dictId eq d.speId}">
											<span>${d.schDeptName}</span>
										</c:if>
									</c:forEach>
								</div>
							</div>

						</div>
						<div class="oneright">
							<div style="padding-left: 15px;padding-top: 17px">
								<div class="fatherdiv">
									<div class="persentdiv" id="persentdiv${dict.dictId}">
										<div class="persentleft" id="persentleft${dict.dictId}"></div>
										<div class="persentright wth0" id="persentright${dict.dictId}"></div>
									</div>
									<div class="num" id="num${dict.dictId}">
										0%
									</div>
								</div>
							</div>
						</div>
						<hr style="width: 90%">
						<div>
							<div class="sideTwo" style="border-right:1px solid white">
								<div class="divonetop">
									<c:set var="infoMsg" value="zyys${dict.dictId}"></c:set>
									<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>
									<div>住院医师</div>
								</div>
							</div>
							<div class="side1Two" style="border-left:1px solid white ">
								<div class="divonetop">
									<c:set var="infoMsg" value="sz${dict.dictId}"></c:set>
									<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>
									<div>导师师资</div>
								</div>

							</div>
							<div class="mainTwo" style="padding-top: 8px;height: 75px;width: 33%;text-align: center;line-height: 30px">
								<c:set var="infoMsg" value="zxzs${dict.dictId}"></c:set>
								<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>
								<div>在校专硕</div>
							</div>
						</div>

					</div>
					<c:set var="infoMsg" value="speRespName${dict.dictId}"></c:set>
					<div style="height: 20px;margin-top: 10px"><i class="icon_menu menu_360check icls"></i>专业基地负责人： ${resultMap[infoMsg]}</div>
					<hr style="width: 90%;color:#f8f8f8;">
					<c:set var="infoMsg" value="speDirName${dict.dictId}"></c:set>
					<div style="height: 20px"><i class="icon_menu menu_360check icls"></i>教学主任：${resultMap[infoMsg]}</div>
					<hr style="width: 90%;color:#f8f8f8;">
					<c:set var="infoMsg" value="speSceName${dict.dictId}"></c:set>
					<div style="height: 25px"><i class="icon_menu menu_360check icls"></i>教学秘书：${resultMap[infoMsg]}</div>
				</div>
			</div>

		</c:forEach>
	</div>--%>
</div>
</body>