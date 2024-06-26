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
	<input type="hidden" id="pageName" name="pageName" value="step3" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
	
	<font style="font-size: 14px; font-weight:bold;color: #333;">二、研究内容</font>
	<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
		<tr>
			<th class="theader">1、具体研究开发内容和要重点解决的关键技术问题</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="researchContent1">${resultMap.researchContent1}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">2、项目的特色和创新之处</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" name="researchContent2">${resultMap.researchContent2}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">3、要达到的主要技术、经济指标及社会、经济效益</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" name="researchContent3">${resultMap.researchContent3}</textarea>
	     	</td>
	    </tr>
	</table>
	
	</br></br>
	<font style="font-size: 14px; font-weight:bold;color: #333;">三、研究试验方法、技术路线以及工艺流程</font>
	<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="testMethod">${resultMap.testMethod}</textarea>
	     	</td>
	    </tr>
	</table>
</form>
	
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step4')" class="search" value="下一步"/>
	</div>
</c:if>	