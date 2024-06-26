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
	<input type="hidden" id="pageName" name="pageName" value="step4" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
	
	<font style="font-size: 14px; font-weight:bold;color: #333;">四、工作基础和条件</font>
	<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
		<tr>
			<th class="theader">1、承担单位概况，拥有知识产权状况</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="workBasicCondition1">${resultMap.workBasicCondition1}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">2、本项目现有的研究工作基础</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="workBasicCondition2">${resultMap.workBasicCondition2}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">3、项目负责人以往承担国家、省级、市级等各类科技计划项目完成情况</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="workBasicCondition3">${resultMap.workBasicCondition3}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">4、项目实施具备的人才队伍、经费配套投入能力及科技服务管理能力</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="workBasicCondition4">${resultMap.workBasicCondition4}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">5、本项目实施可能存在的医疗安全风险及预防控制方案</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="workBasicCondition5">${resultMap.workBasicCondition5}</textarea>
	     	</td>
	    </tr>
	</table>
</form>
	
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
	</div>
</c:if>	