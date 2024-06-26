<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
	var form = $("#projForm");
	form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
	$('#nxt').attr({"disabled":"disabled"});
	$('#prev').attr({"disabled":"disabled"});
	jboxStartLoading();
	form.submit();
}

$(document).ready(function(){
    initUEditer("yearTotalTarget");
    initUEditer("year1");
    initUEditer("year2");
    initUEditer("year3");
    initUEditer("year4");
    initUEditer("year5");
});
</script>
</c:if>
<style type="text/css">
.title_sp{padding-left: 10px;font-size: 14px; margin-bottom:10px; font-weight: bold;color: #333;}
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step5"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
		<span style="font-size: 16px;font-weight: bold">（二）年度目标</span><br/>
		&#12288;&#12288;根据周期目标，列出在合作周期内（5年）每年度的年度工作目标，年度工作目标包括年度总体目标及年度具体目标，需分年度列出。<br/>
		<div style="border: 1px solid #e3e3e3">
		<span style="font-size: 14px;font-weight: bold">&#12288;&#12288;1.2017年度目标：</span><br/>
		<p>
			&#12288;&#12288;（1）年度总体目标（描述依托科室引进该团队后年度预期达到的目标，要有具体指向目标）
		&#12288;&#12288;责任人：<input type="text" name="yearTargetUser" style="width: 100px" class="inputText" value="${resultMap.yearTargetUser}" >
			引进团队带头人：<input type="text" name="teamLeaderName" style="width: 100px" class="inputText" value="${resultMap.teamLeaderName}" >
			依托科室负责人：<input type="text" name="supportDeptApply" style="width: 100px" class="inputText" value="${resultMap.supportDeptApply}" >
		</p>
			<c:choose>
				<c:when test="${param.view==GlobalConstant.FLAG_Y}">
					${resultMap.yearTotalTarget}
				</c:when>
				<c:otherwise>
					<script id="yearTotalTarget" name="yearTotalTarget" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.yearTotalTarget}</script>
				</c:otherwise>
			</c:choose>
		<p style="margin-top: 20px">
			&#12288;&#12288;（2）年度具体目标<br/>
			&#12288;&#12288;需明确在每个合作方向上，当年度的医疗、教学、科研三方面的具体目标。例：<br/>
			&#12288;&#12288;方向1：名称<br/>
			&#12288;&#12288;医疗：需明确合作内容、举措及预期目标（须用该方向关键技术的临床核心可量化指标体现）。<br/>
			&#12288;&#12288;教学：需明确合作内容、举措及预期目标（须用在该方向的人才培养、学术交流、学术地位等核心可量化指标体现）。<br/>
			&#12288;&#12288;科研：需明确合作内容、举措及预期目标（须用在该方向的科研课题、论文、科技奖励等核心可量化指标体现）。<br/>
			&#12288;&#12288;责任人：技术指导：***（引进团队带头人或骨干）、学科骨干：***（依托科室学科骨干）<br/>
		</p>
			<c:choose>
				<c:when test="${param.view==GlobalConstant.FLAG_Y}">
					${resultMap.year1}
				</c:when>
				<c:otherwise>
					<script id="year1" name="year1" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.year1}</script>
				</c:otherwise>
			</c:choose>
	</div>
	<div style="border: 1px solid #e3e3e3">
		<span style="font-size: 14px;font-weight: bold">&#12288;&#12288;2.2018年度目标</span><br/>
		<c:choose>
			<c:when test="${param.view==GlobalConstant.FLAG_Y}">
				${resultMap.year2}
			</c:when>
			<c:otherwise>
				<script id="year2" name="year2" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.year2}</script>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="border: 1px solid #e3e3e3">
		<span style="font-size: 14px;font-weight: bold">&#12288;&#12288;3.2019年度目标</span><br/>
		<c:choose>
			<c:when test="${param.view==GlobalConstant.FLAG_Y}">
				${resultMap.year3}
			</c:when>
			<c:otherwise>
				<script id="year3" name="year3" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.year3}</script>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="border: 1px solid #e3e3e3">
		<span style="font-size: 14px;font-weight: bold">&#12288;&#12288;4.2020年度目标</span><br/>
		<c:choose>
			<c:when test="${param.view==GlobalConstant.FLAG_Y}">
				${resultMap.year4}
			</c:when>
			<c:otherwise>
				<script id="year4" name="year4" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.year4}</script>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="border: 1px solid #e3e3e3">
		<span style="font-size: 14px;font-weight: bold">&#12288;&#12288;5.2021年度目标</span><br/>
		<c:choose>
			<c:when test="${param.view==GlobalConstant.FLAG_Y}">
				${resultMap.year5}
			</c:when>
			<c:otherwise>
				<script id="year5" name="year5" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.year5}</script>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${param.view != GlobalConstant.FLAG_Y}">
	    <div align="center" style="margin-top: 10px;width: 100%">
	         <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
        	 <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
		</div>
	</c:if>
</form>
