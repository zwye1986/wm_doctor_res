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

	$(document).ready(function(){
		heightFiexed();
	});
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	function adjustResults() {
		$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
		$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	}
	function heightFiexed(){
		var height = document.body.clientHeight-310;
		if(height<300)
		{
			height=300;
		}
		$("#tableContext").css("height",height+"px");	
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
	}
	function showResult(page)
	{
		page=page||1;
		$("#currentPage").val(page);
		var url ="<s:url value='/sch/autoArrange/arrangeResult'/>?"+$("#searchForm2").serialize();
		jboxLoad("resultDiv",url,true);
	}
	onresize = function(){
		heightFiexed();
	};
	function exportArrangeResult(){
		var url = "<s:url value='/sch/autoArrange/exportArrangeResult'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm2"), url, null, null, false);
		jboxEndLoading();
	}

	function reStartArrange()
	{
		jboxConfirm("确认重新排班？重新排班将删除已排班结果",function () {
			var url2 = "<s:url value='/sch/autoArrange/reStartAutoArrange'/>?sessionNumber=${param.sessionNumber}";
			jboxPost(url2,null,function(resp){
				if(resp == '排班成功！'){
					showResult(1);
				}
			},null,true);
		});
	}
	function lastStartArrange()
	{
		var url2 = "<s:url value='/sch/autoArrange/checkPrefixLastCondition'/>?sessionNumber=${param.sessionNumber}";
		jboxPost(url2,null,function(resp){
			if(resp == ''){
				lastStart();
			}else{
				try{
					var count=parseInt(resp);
					jboxConfirm("已有"+count+"名学员存在轮转信息，排班后将删除轮转信息，确认排班？",function () {
						lastStart();
					});
				}catch(e){
					jboxTip(resp);
				}
			}
		},null,false);
	}

	function lastStart()
	{
		setTimeout(function(){
			jboxStartLoading();
			var url2 = "<s:url value='/sch/autoArrange/startAutoArrange'/>?flag=Y&sessionNumber=${param.sessionNumber}";
			jboxPostNoLoad(url2,null,function(resp){
				jboxEndLoading();
				if(resp == '排班成功！'){
					showResult(1);
				}
			},null,true);
		},500);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm2" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
				<input id="sessionNumber" type="hidden" name="sessionNumber" value="${param.sessionNumber}"/>
				<div class=""style="width: 100%;text-align: right;">
						<input type="radio" name="type" value="dept" id="dept" <c:if test="${'dept' eq param.type}">checked</c:if> onchange="showResult(1);"/><label for="dept">科室维度</label>
						<input type="radio" name="type" value="doctor" id="doctor" <c:if test="${'dept' ne param.type}">checked</c:if> onchange="showResult(1);"/><label for="doctor">学员维度</label>
						<input type="button" value="导&#12288;&#12288;出" class="search" onclick="exportArrangeResult();"/>
						<input type="button" value="重新排班" class="search" onclick="reStartArrange();"/>
						<c:if test="${need > 0}">
							<span>还有${need}名学员未排班，点击<a href="javascript:void(0);" style="cursor: pointer;color: blue;" onclick="lastStartArrange();">此处进行排班</a></span>
						</c:if>
				</div>
			</form>
			<c:if test="${'dept' eq param.type}">
				<div id="tableContext" style="width:100%; margin-top: 10px;margin-bottom: 10px;overflow: auto;" onscroll="tableFixed(this);">
					<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
						<table class="basic">
							<tr>
								<th style="width: 200px;min-width: 200px;max-width: 200px;">
									轮转科室
								</th>
								<c:forEach items="${titles}" var="title">
									<th style="width: 90px;min-width: 90px;">${title}</th>
								</c:forEach>
							</tr>
						</table>
					</div>
					<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
						<table class="basic" style="min-width: 200px;max-width: 200px;">
							<tr>
								<th style="width: 200px;width: 200px;min-width: 200px;">
									轮转科室
								</th>
							</tr>
							<c:forEach items="${schDeptList}" var="schDept">
								<tr>
									<td style="background: white;" class="toFiexdDept">${schDept.schDeptName}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
					<table class="basic">
						<tr>
							<th style="min-width: 200px;max-width: 200px;">轮转科室</th>
						</tr>
					</table>
					</div>
					<table class="basic">
						<tr>
							<th style="width: 200px;min-width: 200px;">
								轮转科室
							</th>
							<c:forEach items="${titles}" var="title">
								<th style="width: 90px;min-width: 90px;">${title}</th>
							</c:forEach>
						</tr>
						<c:forEach items="${schDeptList}" var="schDept">
							<tr>
								<td class="fixedBy">${schDept.schDeptName}</td>
								<c:forEach items="${titles}" var="title">
									<c:set var="key" value="${schDept.schDeptFlow}${title}"/>
									<td class="nameTitle" style="line-height: 18px;">
										<div class="docCountView"
												<c:if test="${!empty resultListMap[key]}">
													title="
													<table>
													<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
														<tr><td>${result.doctorName}<br/>(${result.schStartDate}~${result.schEndDate})</td></tr>
													</c:forEach>
													</table>
													"
												</c:if>>
											${resultListMap[key].size()+0}
										</div>
										<c:if test="${!empty resultListMap[key]}">
											<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
												<div class="docNameView" style="display: none;width: 90px;min-width: 90px;word-wrap:break-word;">${result.doctorName}</div>
											</c:forEach>
										</c:if>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div>
					<c:set var="pageView" value="${pdfn:getPageView(schDeptList)}" scope="request"></c:set>
					<pd:pagination toPage="showResult"/>
				</div>
			</c:if>
			<c:if test="${'dept' ne param.type}">
				<div id="tableContext" style="width:100%; margin-top: 10px;margin-bottom: 10px;overflow: auto;" onscroll="tableFixed(this);">
					<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
						<table class="basic">
							<tr>
								<th style="width: 200px;min-width: 200px;max-width: 200px;">
									姓名
								</th>
								<c:forEach items="${titles}" var="title">
									<th style="width: 90px;min-width: 90px;">${title}</th>
								</c:forEach>
							</tr>
						</table>
					</div>
					<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
						<table class="basic" style="min-width: 200px;max-width: 200px;">
							<tr>
								<th style="width: 200px;width: 200px;min-width: 200px;">
									姓名
								</th>
							</tr>
							<c:forEach items="${doctorList}" var="doctor">
								<tr>
									<td style="background: white;" class="toFiexdDept">${doctor.userName}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
					<table class="basic">
						<tr>
							<th style="min-width: 200px;max-width: 200px;">姓名</th>
						</tr>
					</table>
					</div>
					<table class="basic">
						<tr>
							<th style="width: 200px;min-width: 200px;">
								姓名
							</th>
							<c:forEach items="${titles}" var="title">
								<th style="width: 90px;min-width: 90px;">${title}</th>
							</c:forEach>
						</tr>
						<c:forEach items="${doctorList}" var="doctor">
							<tr>
								<td class="fixedBy">${doctor.userName}</td>
								<c:forEach items="${titles}" var="title">
									<c:set var="key" value="${doctor.userFlow}${title}"/>
									<td class="nameTitle" style="line-height: 18px;">
										<c:if test="${!empty resultListMap[key]}">
											<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
												<div class="docNameView" style="width: 90px;min-width: 90px;word-wrap:break-word;">${result.schDeptName}</div>
											</c:forEach>
										</c:if>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div>
					<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
					<pd:pagination toPage="showResult"/>
				</div>
			</c:if>
	</div>
</div>
</div>
</body>
</html>