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
	<input type="hidden" id="pageName" name="pageName" value="step2" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
	
	<font style="font-size: 14px; font-weight:bold;color: #333;">一、立项依据</font>
	<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
		<tr>
			<th class="theader">1、本项目国内外科技创新发展概况和最新发展趋势</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="projectBasis1">${resultMap.projectBasis1}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">2、本项目研究的目的、意义</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="projectBasis2">${resultMap.projectBasis2}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">3、本项目研究现有起点科技水平及已存在的知识产权情况</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" name="projectBasis3">${resultMap.projectBasis3}</textarea>
	     	</td>
	    </tr>
		<tr>
			<th style="text-align: left;padding-left: 20px;">4、本项目研究国内外竞争情况及行业发展前景</th>
		</tr>
        <tr>
	     	<td style="text-align:left;">
	     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="projectBasis4">${resultMap.projectBasis4}</textarea>
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