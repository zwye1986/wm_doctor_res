<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="false" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
	</jsp:include>
	<style>
		.doctorTypeDiv {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeLabel {
			border: 0px;
			float: left;
			width: 96px;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeContent {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			<c:forEach items="${studentTypes}" var="data">
				$("#"+"${data}").attr("checked","checked");
			</c:forEach>
			heightFiexed();
			$("#doctorCategory").change(function(){
				$("#spe").empty();
				$("#spe").append($("."+this.value).clone());
				if(this.value=='${recDocCategoryEnumIntern.id}'){
					$("#work").show();
				}else{
					$("#work").hide();
					$("#workOrgId").val("");
				}
			}).change();
		});
		function tableFixed(div){
			$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
			$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
		}
		function heightFiexed(){

			//修正高度
			var maxheight=-1;
			var headTrTh=$(".headTrTh");
			$(headTrTh).each(function(i){

				$(headTrTh[i]).find(".fixedBy").each(function(){
					if($(this).height()>maxheight) maxheight=$(this).height();
				});
			});
			if(maxheight!=-1) {
				$(".toFiexdDept").each(function(){
					$(this).height(maxheight+1);
				});
			}
			var fixTrTd = $(".fixTrTd");
			var fixTrTh = $(".fixTrTh");
			$(fixTrTd).each(function(i)
			{
				var maxheight=-1;
				$(fixTrTd[i]).find(".by").each(function(){
					if($(this).height()>maxheight) maxheight=$(this).height();
				});
				if(maxheight!=-1) {
					$(fixTrTh[i]).find(".fix").each(function(){
						$(this).height(maxheight+1);
					});
				}
			});
		}

		onresize = function(){
			heightFiexed();
		};
		function search(page) {
			var startDate=$("#startDate").val();
			var endDate=$("#endDate").val();
			if(startDate==""||startDate==undefined)
			{
				jboxTip("开始时间不得为空！！");
				return false;
			}
			if(endDate==""||endDate==undefined)
			{
				jboxTip("结束时间不得为空！！");
				return false;
			}
			if(startDate!=""&&endDate!=""&&endDate<startDate)
			{
				jboxTip("开始时间不得大于结束时间！！");
				return false;
			}
			jboxStartLoading();
			if (!page) {
				page = 1;
			}
			$("#currentPage").val(page);
			<%--var data = $("#searchForm").serialize();--%>
			<%--var url = "<s:url value='/res/manager/doctorRecruit/cycle'/>";--%>
			<%--jboxPost(url,data,function(resp){--%>
				<%--$("#content").html(resp);--%>
			<%--},null,false);--%>
			$("#searchForm").submit();
		}
		function toPage(page) {
			search(page);
		}
		function showDetails(resultFlow,schStartDate,schEndDate,doctorFlow){
			jboxStartLoading();
			var url ="<s:url value='/res/manager/doctorRecruit/cycleDeptDetails'/>?resultFlow="+resultFlow+"&schStartDate="+schStartDate+"&schEndDate="+schEndDate+"&doctorFlow="+doctorFlow;
			var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
			jboxMessager(iframe,'详细信息',700,250);
			jboxEndLoading();
		}
		function exportExcel(){
			var url = "<s:url value='/res/manager/doctorRecruit/exportCycle'/>";
			jboxTip("导出中…………");
			jboxSubmit($("#searchForm"), url, null, null, false);
			jboxEndLoading();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value='/res/manager/doctorRecruit/cycle'/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
				<input type="hidden" name="role" value="${param.role}">
				<div class="queryDiv">
					<div class="inputDiv">
                        <c:if test="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jszy'}">
                            <label class="qlable">培训专业：</label>
                        </c:if>
                        <c:if test="${applicationScope.sysCfgMap['sys_index_url'] ne '/inx/jszy'}">
                            <label class="qlable">培训类别：</label>
                        </c:if>
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
                        <c:if test="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jszy'}">
                            <label class="qlable">对应专业：</label>
                        </c:if>
                        <c:if test="${applicationScope.sysCfgMap['sys_index_url'] ne '/inx/jszy'}">
                            <label class="qlable">培训专业：</label>
                        </c:if>
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
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>

						<input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
						<%--<select id="sessionNumber" name="sessionNumber" class="qselect">--%>
							<%--<option value="all">全部</option>--%>
							<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">--%>
								<%--<option value="${dict.dictId}" ${(param.sessionNumber eq dict.dictId or sessionNumber eq dict.dictId)?'selected':''}>${dict.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					</div>
					<div class="inputDiv" style="min-width: 317px;max-width: 317px;">
						<label class="qlable">时间区间：</label>
						<input name="startDate" id="startDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" style="width: 94px;"
							   value="${empty param.startDate?startDate:param.startDate}" class="qtext"/>~
						<input name="endDate" id="endDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 94px;"
							   value="${empty param.endDate?endDate:param.endDate}" class="qtext"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" name="userName" class="qtext" value="${param.userName}"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">证件号码：</label>
						<input type="text" name="idNo" value="${param.idNo}" class="qtext"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">培训年限：</label>
						<select name="trainingYears" class="qselect">
							<option value="">全部</option>
							<option value="1" <c:if test="${param.trainingYears eq '1'}">selected</c:if>>一年</option>
							<option value="2" <c:if test="${param.trainingYears eq '2'}">selected</c:if>>两年</option>
							<option value="3" <c:if test="${param.trainingYears eq '3'}">selected</c:if>>三年</option>
						</select>
					</div>
					<c:if test="${param.role eq 'pt'}">
						<div class="inputDiv">
							<label class="qlable">培训基地：</label>
							<select name="orgFlow"  id="orgFlow" class="qselect" >
								<option value="">全部</option>
								<c:forEach items="${orgList}" var="org">
									<option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<c:if test="${param.role ne 'pt'}">
						<div class="inputDiv">
							<label class="qlable">培训基地：</label>
							<select name="orgFlow"  id="orgFlow" class="qselect" >
								<c:forEach items="${orgList}" var="org">
									<option  value="${org.orgFlow}" ${empty param.orgFlow?(orgFlow eq org.orgFlow?'selected':''):(param.orgFlow eq org.orgFlow?'selected':'')}>${org.orgName}</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<c:if test="${param.role ne 'gx'}">
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
								<input id="${dict.dictId}" type="checkbox" name="studentTypes" value="${dict.dictId}" <c:if test="${empty param.studentTypes}">checked</c:if>/>&nbsp;${dict.dictName}&nbsp;
							</c:forEach>
						</div>
					</div>
					</c:if>
					<div class="lastDiv" style="min-width: 182px;max-width: 182px;">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search(1);"/>
						<input type="button" class="searchInput" onclick="exportExcel();" value="导&#12288;出">
					</div>
				</div>
			</form>
		</div>

		<!--表格  -->
		<div id="tableContext" class="basic" style="width:100%;border: 0px solid #e3e3e3;overflow: auto;" onscroll="tableFixed(this);">
			<!--第一次 -->
			<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
				<table class="grid"style="width: auto;" >
					<tr class="headTrTh">
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="toFiexdDept">姓名</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;" class="toFiexdDept">培训专业</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;" class="toFiexdDept">对应专业</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="toFiexdDept">年级</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="toFiexdDept">培训年限</th>
						<c:forEach items="${titleDate}" var="title">
							<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">
								<c:set var="year" value="${title.substring(0,4)}"/>
								<c:set var="week" value="${title.substring(5)}"/>
								<c:set var="title" value="${year}年<br/>${week}周"/>
							</c:if>
							<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width:auto;min-width: 150px;" class="fixedBy">${title}</th>
						</c:forEach>
					</tr>
				</table>
			</div>
			<!--第二次 -->
			<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
				<table class="grid" style="width: auto;">
					<tr class="headTrTh">
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="toFiexdDept">姓名</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;" class="toFiexdDept">培训专业</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;" class="toFiexdDept">对应专业</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="toFiexdDept">年级</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="toFiexdDept">培训年限</th>
					</tr>
					<c:forEach items="${docCycleList}" var="docCycle">
						<tr class="fixTrTh">
							<td style="height: 40px;background: white; text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="fix">${docCycle.userName}</td>
							<td style="height: 40px;background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="fix">
								<c:if test="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jszy'}">
									${pdfn:cutString(docCycle.trainingTypeName,5,true,3)}
								</c:if>
								<c:if test="${applicationScope.sysCfgMap['sys_index_url'] ne '/inx/jszy'}">
									${docCycle.doctorCategoryName}
								</c:if>
							</td>
							<td style="height: 40px;background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="fix">${docCycle.trainingSpeName}</td>
							<td style="height: 40px;background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="fix">${docCycle.sessionNumber}</td>
							<td style="height: 40px;background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" class="fix">
								<c:if test="${'1' eq docCycle.trainingYears}">一年</c:if>
								<c:if test="${'2' eq docCycle.trainingYears}">两年</c:if>
								<c:if test="${'3' eq docCycle.trainingYears}">三年</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty docCycleList}">
						<tr class="fixTrTh">
							<td class="fix" colspan="10" style="background: white;border: 1px solid #e3e3e3;border-right: 0px">无记录！</td>
						</tr>
					</c:if>
				</table>
			</div>
			<!--第三次  -->
			<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
				<table class="grid" style="width: auto;" >
					<tr class="headTrTh">
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;"class="toFiexdDept">姓名</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;"class="toFiexdDept">培训专业</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;"class="toFiexdDept">对应专业</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;"class="toFiexdDept">年级</th>
						<th style="background: white;text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;"class="toFiexdDept">培训年限</th>
					</tr>
				</table>
			</div>
			<table border="0"  style="width: auto;" cellpadding="0" cellspacing="0" class="grid">
				<tr class="headTrTh">
					<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;"class="toFiexdDept">姓名</th>
					<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;"class="toFiexdDept">培训专业</th>
					<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;"class="toFiexdDept">对应专业</th>
					<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;"class="toFiexdDept">年级</th>
					<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;"class="toFiexdDept">培训年限</th>
					<c:forEach items="${titleDate}" var="title">
						<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">
							<c:set var="year" value="${title.substring(0,4)}"/>
							<c:set var="week" value="${title.substring(5)}"/>
							<c:set var="title" value="${year}年<br/>${week}周"/>
						</c:if>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width:auto;min-width: 150px;" class="fixedBy">${title}</th>
					</c:forEach>
				</tr>
				<c:forEach items="${docCycleList}" var="docCycle">
					<tr class="fixTrTd">
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;"  >${docCycle.userName}</td>
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;" >${docCycle.doctorCategoryName}</td>
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 150px;" >${docCycle.trainingSpeName}</td>
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" >${docCycle.sessionNumber}</td>
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width: 100px;min-width: 100px;" >
							<c:if test="${'1' eq docCycle.trainingYears}">一年</c:if>
							<c:if test="${'2' eq docCycle.trainingYears}">两年</c:if>
							<c:if test="${'3' eq docCycle.trainingYears}">三年</c:if>
						</td>
						<c:forEach items="${titleDate}" var="title">
							<c:set var="key" value="${docCycle.doctorFlow}${title}"/>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;padding-right: 0px;width:auto;min-width: 150px;" class="by">
								<c:if test="${!empty resultListMap[key]}">
									<table style="width: 100%;border: 0px solid #e3e3e3;text-align: center;">
										<tr style="border: 0px solid #e3e3e3;text-align: center;">
											<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
											<c:set var="color" value="${recMap[result.resultFlow]}"></c:set>
											<c:set var="lastCount" value="${(status.count % 2) }"></c:set>
											<td onclick="showDetails('${result.resultFlow}','${result.schStartDate}','${result.schEndDate}','${result.doctorFlow}');"
												style="border: 0px solid #e3e3e3;text-align: center;padding-left: 0px;padding-right: 0px;font-color:black;width:auto;min-width: 74px;
												<c:if test="${ lastCount eq '0'}">
														border-left: 1px solid #e3e3e3;
												</c:if>
												<c:if test="${ status.count gt 2}">
														border-top: 1px solid #e3e3e3;
												</c:if>
												<c:if test="${color eq '0'or empty color}">
														background-color:#97dafb;
												</c:if>
												<c:if test="${color eq '1'}">
														background-color: #c9e4d5;
												</c:if>
												<c:if test="${color eq '2'}">
														background-color: #f8e5c0;
												</c:if>
												<c:if test="${color eq '3'}">
														background-color: #fdc4c3;
														</c:if>"
													<c:if test="${color eq '3'}">
														class="nameTitle"
														<c:set var="ss" value="${result.resultFlow}_rec"></c:set>
														title="${recMap[ss]}"
													</c:if>
											>
													${result.schDeptName}
											</td>
											<c:if test="${ lastCount eq '0'}">
										</tr><tr style="border: 0px solid #e3e3e3;text-align: center;">
										</c:if>
										</c:forEach>
									</tr>
									</table>
								</c:if>
								<c:if test="${empty resultListMap[key]}">
									--
								</c:if>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
				<c:if test="${empty docCycleList}">
					<tr>
						<td colspan="20">无记录！</td>
					</tr>
				</c:if>
			</table>
		</div>
		<br>
		<div style="padding-left: 40px;width:50%">
			<span style="background-color: #97dafb">&#12288;&nbsp;</span><span>表示:按计划未入科的科室</span><br/>
			<span style="background-color: #c9e4d5">&#12288;&nbsp;</span><span>表示:按计划出科且科室状态为已出科的科室</span><br/>
			<span style="background-color: #f8e5c0">&#12288;&nbsp;</span><span>表示:按计划正在轮转的科室</span><br/>
			<span style="background-color: #fdc4c3">&#12288;&nbsp;</span><span>表示:按计划应出科，但科室状态不是已出科的科室</span><br/>
		</div>
		<div>
			<c:set var="pageView" value="${pdfn:getPageView(docCycleList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
		</div>
	</div>
</div>
</body>
</html>

