<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	table.basic th,table.basic td{text-align: center;padding: 0;}
</style>

<script type="text/javascript">
	$(function () {
		$("#doctorCategory").change(function(){
			changeSpe();
		});
		changeSpe();
		heightFiexed();
	});
	function changeSpe()
	{
		var doctorCategory=$("#doctorCategory").val()||"";
		$("#spe").empty();
		$("#spe").append($("."+doctorCategory).clone());
	}

	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}
	function search(){
		$("#searchForm").submit();
	}
	function exportInfo(){
		jboxExpLoading($("#searchForm"),"<s:url value='/sch/arrangeResult/exportDocStaticts'/>");
	}
	function exportInfo2(){
		jboxExpLoading($("#searchForm"),"<s:url value='/sch/arrangeResult/exportArrangeInfo'/>");
	}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	$(function(){
		changeView();
	});
	function adjustResults() {
		$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
		$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	}
	function heightFiexed(){
		var height = document.body.clientHeight-110;
		$("#tableContext").css("height",height+"px");	
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
	}
	
	onresize = function(){
		heightFiexed();
	};
	
	//切换展示形式
	function changeView(){
		var viewBox = $("#viewBox")[0];
		if(viewBox){
			$(".docCountView").toggle(!viewBox.checked);
			$(".docNameView").toggle(viewBox.checked);
		}
		heightFiexed();
	}
	function allCheck(){
		if($("#checkAll").is(':checked')){
			$(".check").prop("checked",true);
		}else{
			$(".check").prop("checked",false);
		}
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value='/sch/arrangeResult/docStaticts'/>" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<input id="orgFlow" type="hidden" name="orgFlow" value="${orgFlow}"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">培训类别：</label>
						<select id="doctorCategory" name="doctorCategoryId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${recDocCategoryEnumList}" var="category">
								<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
								<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
									<option value="${category.id}" ${param.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select id="spe" name="trainingSpeId" class="qselect">
							<option></option>
						</select>
						<div style="display: none;">
							<option
									class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								${recDocCategoryEnumWMFirst.id} |
								${recDocCategoryEnumWMSecond.id} |
								${recDocCategoryEnumAssiGeneral.id} |
								${recDocCategoryEnumChineseMedicine.id} |
								${recDocCategoryEnumTCMGeneral.id} |
								${recDocCategoryEnumTCMAssiGeneral.id} |
								" value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option
										class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
								<option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
								<option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
								<option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
								<option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
								<option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
								<option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</div>
					</div>
					<div class="inputDiv" id="secondSpe" style="display: none;">
						<label class="qlable">二级专业：</label>
						<select id="secondSpeId" name="secondSpeId" class="qselect">
							<option></option>
							<c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="dict">
								<option  value="${dict.dictId}" ${param.secondSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<select name="sessionNumber" class="qselect" >
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
								<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">开始时间：</label>
						<input name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" type="text" value="${empty param.startDate?startDate:param.startDate}" class="qtext validate[required]"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">结束时间：</label>
						<input name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" type="text" value="${empty param.endDate?endDate:param.endDate}" class="qtext validate[required]"/>
					</div>
					<div class="lastDiv" style="min-width: 382px;max-width: 382px;">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>&#12288;
						<input type="button" value="导&#12288;出" class="searchInput" onclick="exportInfo();"/>&#12288;
						<c:if test="${sysCfgMap['sch_inRes_flag'] ne GlobalConstant.FLAG_Y}">
						<input type="button" value="导出模板" class="searchInput" onclick="exportInfo2();"/>
						</c:if>
					</div>

				</div>
			</form>
						
			<div id="tableContext" style="width:100%; margin-top: 10px;margin-bottom: 10px;overflow: auto;" onscroll="tableFixed(this);">
				<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="basic">
						<tr>
							<th style="width: 120px;min-width: 120px;max-width: 120px;">
								姓名
							</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">
								年级
							</th>
							<th style="width: 150px;min-width: 150px;max-width: 150px;">
								培训专业
							</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">
								培养年限
							</th>
							<c:forEach items="${titleDate}" var="title">
								<th style="width: 130px;min-width: 130px;">${title}</th>
							</c:forEach>
						</tr>
					</table>
				</div>
				<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="basic" style="min-width: 150px;max-width: 150px;">
						<tr>
							<th style="width: 120px;min-width: 120px;max-width: 120px;">
								姓名
							</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">
								年级
							</th>
							<th style="width: 150px;min-width: 150px;max-width: 150px;">
								培训专业
							</th>
							<th style="width: 100px;min-width: 100px;max-width: 100px;">
								培养年限
							</th>
						</tr>
						<c:forEach items="${doctorList}" var="doctor">
							<tr>
								<c:if test="${sysCfgMap['sch_inRes_flag'] eq GlobalConstant.FLAG_Y}">
								<td style="background: white;text-align: left;" class="toFiexdDept">
									&#12288;&#12288;<input type="checkbox" class="check" value="${doctor.doctorFlow}" onclick="checkSingel(this)"/>
									${doctor.sysUser.userName}</td>
								</c:if>
								<c:if test="${sysCfgMap['sch_inRes_flag'] ne GlobalConstant.FLAG_Y}">
								<td style="background: white;" class="toFiexdDept">${doctor.sysUser.userName}</td>
								</c:if>
								<td style="background: white;" class="toFiexdDept">${doctor.sessionNumber}</td>
								<td style="background: white;" class="toFiexdDept">${doctor.trainingSpeName}</td>
								<td style="background: white;" class="toFiexdDept">${doctor.trainingYears}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
				<table class="basic">
					<tr>
						<th style="width: 120px;min-width: 120px;max-width: 120px;">
							<c:if test="${sysCfgMap['sch_inRes_flag'] eq GlobalConstant.FLAG_Y}">
								<input type="checkbox" id="checkAll" onclick="allCheck()"/>
							</c:if>
							姓名
						</th>
						<th style="width: 100px;min-width: 100px;max-width: 100px;">
							年级
						</th>
						<th style="width: 150px;min-width: 150px;max-width: 150px;">
							培训专业
						</th>
						<th style="width: 100px;min-width: 100px;max-width: 100px;">
							培养年限
						</th>
					</tr>
				</table>
				</div>
				<table class="basic">
					<tr>
						<th style="width: 120px;min-width: 120px;max-width: 120px;">
							姓名
						</th>
						<th style="width: 100px;min-width: 100px;max-width: 100px;">
							年级
						</th>
						<th style="width: 150px;min-width: 150px;max-width: 150px;">
							培训专业
						</th>
						<th style="width: 100px;min-width: 100px;max-width: 100px;">
							培养年限
						</th>
						<c:forEach items="${titleDate}" var="title">
							<th style="width: 130px;min-width: 130px;">${title}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${doctorList}" var="doctor">
						<tr>
							<td class="fixedBy">${doctor.sysUser.userName}</td>
							<td class="fixedBy">${doctor.sessionNumber}</td>
							<td class="fixedBy">${doctor.trainingSpeName}</td>
							<td class="fixedBy">${doctor.trainingYears}</td>
							<c:forEach items="${titleDate}" var="title">
								<c:set var="key" value="${doctor.doctorFlow}${title}"/>
								<td class="nameTitle" style="line-height: 18px;">
									<c:if test="${!empty resultListMap[key]}">
										<c:if test="${fn:length(resultListMap[key])>1}">
											<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
												<div class="docNameView" title="${result.schStartDate}~${result.schEndDate}"
													 style="width: 64px;word-wrap:break-word;height: 35px;line-height: 35px;
															 float: left;<c:if test="${!status.last}">border-right: 1px solid #cccccc;</c:if>">${result.schDeptName}</div>
											</c:forEach>
										</c:if>
										<c:if test="${fn:length(resultListMap[key])<=1}">
											<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
												<div class="docNameView" title="${result.schStartDate}~${result.schEndDate}"
													 style="width: 128px;word-wrap:break-word;height: 35px;line-height: 35px;
															 float: left;">${result.schDeptName}</div>
											</c:forEach>
										</c:if>
									</c:if>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div>
				<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
	</div>
</div>
</div>
</body>
</html>