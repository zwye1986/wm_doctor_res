<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
function nextOpt(step , targetFormId){
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
    <input type="hidden" id="pageName" name="pageName" value="step2"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
	<table width="100%" cellspacing="0" cellpadding="0" class="basic">
	    <tr>
		    <th style="text-align: left;padding-left: 15px;font-size: 14px;">一、团队名称<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></th>
		</tr>
		<tr>
		    <td>
		    <input type="text" name="teamNameDetail" style="width: 97%" class="validate[required] xltext" value="${resultMap.teamNameDetail}" >
		    </td>
		</tr>
	</table>
	<table width="100%" cellspacing="0" cellpadding="0" class="basic">
		<tr>
			<th style="text-align: left;padding-left: 15px;font-size: 14px;">二、团队合作简介（300字以内）<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></th>
		</tr>
		<tr>
			<td>
				<p>主要描述单位引进团队后，将合作开展的工作及达到的预期目标</p>
				<textarea name="teamCooperation" class="validate[required,maxSize[300]] xltxtarea" style="width: 97%;height: 300px;">${resultMap.teamCooperation}</textarea>
			</td>
		</tr>
	</table>
	<c:if test="${param.view != GlobalConstant.FLAG_Y}">
	    <div align="center" style="margin-top: 10px; width: 100%">
	            <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
        	    <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
	    </div>
	</c:if>
</form>
