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
</script>
</c:if>
<style type="text/css">
.title_sp{padding-left: 10px;font-size: 14px; margin-bottom:10px; font-weight: bold;color: #333;}
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step3"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
	<table width="100%" cellspacing="0" cellpadding="0" class="basic">
	    <tr>
		    <th style="text-align: left;padding-left: 15px;font-size: 14px; font-weight:bold;">三、引进团队简介（500字以内）<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></th>
		</tr>
		<tr>
		    <td>
				<p>主要描述拟引进的团队的基本情况。主要包括：引进团队的带头人情况、团队构成情况、学科团队在全国的排名情况等。</p>
		    <textarea name="importTeamIntro" class="xltxtarea validate[required,maxSize[500]]" style="height: 150px;">${resultMap.importTeamIntro}</textarea>
		    </td>
		</tr>
	</table>
	<table width="100%" cellspacing="0" cellpadding="0" class="basic">
		<tr>
			<th style="text-align: left;padding-left: 15px;font-size: 14px; font-weight:bold;">四、依托科室简介（500字以内）<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></th>
		</tr>
		<tr>
			<td>
				<p>主要描述依托科室的基本情况。主要包括：学科带头人情况、学科梯队情况、学科在全市的临床和学术地位等情况。</p>
				<textarea name="supportDeptIntro" class="xltxtarea validate[required,maxSize[500]]" style="height: 150px;">${resultMap.supportDeptIntro}</textarea>
			</td>
		</tr>
	</table>
	<c:if test="${param.view != GlobalConstant.FLAG_Y}">
	    <div align="center" style="margin-top: 10px; width: 100%">
	        <input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
        	 <input id="nxt" type="button" onclick="nextOpt('step4')" class="search" value="下一步"/>
	    </div>
	</c:if>
</form>
