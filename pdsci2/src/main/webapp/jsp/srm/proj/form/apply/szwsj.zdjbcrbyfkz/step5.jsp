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
<style>
	.xltxtarea{height: 200px;}
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
	<input type="hidden" id="pageName" name="pageName" value="step5" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
	
	<font style="font-size: 14px; font-weight:bold;color: #333;">五、项目研究预期成果及效益</font>
	<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="expectAchievement">${resultMap.expectAchievement}</textarea>
	     	</td>
	    </tr>
	</table>
</form>
	
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
	</div>
</c:if>	