<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
<script type="text/javascript">
	function nextOpt(step){
		if(false==$("#projForm").validationEngine("validate")){
			return false;
		}
		var form = $('#projForm');
		var action = form.attr('action');
		action+="?nextPageName="+step;
		form.attr("action" , action);
		form.submit();
	}
</script>
</c:if>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
	<input type="hidden" id="pageName" name="pageName" value="step2" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

	<font style="font-size: 14px; font-weight:bold;color: #333;">一、简介</font>
	<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
		<tr>
			<th class="theader">概述创新平台（实验室）主要技术特色、优势，规模、国内外学术地位等（限1000字内）<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></th>
		</tr>
		<tr>
			<td style="text-align:left;">
				<textarea placeholder=""  class="validate[required,maxSize[4000]] xltxtarea" style="height: 350px;padding:5px;" name="mainRemark">${resultMap.mainRemark}</textarea>
			</td>
		</tr>
	</table>

	</br></br>
	<font style="font-size: 14px; font-weight:bold;color: #333;">二、主攻方向</font>
	<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
		<tr>
			<th class="theader">围绕2-3个主攻方向，概述创新平台（实验室）今后5年在应用基础研究、临床研究方面的建设目标、研究内容、预期成果、年度安排等。（限2000字内）。<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></th>
		</tr>
		<tr>
			<td style="text-align:left;">
				<textarea placeholder=""  class="validate[required,maxSize[4000]] xltxtarea" style="height: 350px;padding:5px;" name="mainDirection">${resultMap.mainDirection}</textarea>
			</td>
		</tr>
	</table>
</form>
	
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
	</div>
</c:if>	