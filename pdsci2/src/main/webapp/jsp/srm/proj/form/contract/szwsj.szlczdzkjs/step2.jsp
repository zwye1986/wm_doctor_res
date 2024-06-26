
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
var form = $('#projForm');
form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
$('#nxt').attr({"disabled":"disabled"});
$('#prev').attr({"disabled":"disabled"});
jboxStartLoading();
form.submit();
}
</script>
</c:if>

<style type="text/css">
 .bs_tb tbody td input{text-align: left;margin-left: 10px;}
</style>

	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
		id="projForm" style="position: relative;">
		<input type="hidden" id="pageName" name="pageName" value="step2" /> 
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
		<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
		   
		<font style="font-size: 14px; font-weight:bold;color: #333; ">基本情况</font>
		
		<table  class="basic" style="width: 100%;margin-top: 10px;">
			<tr><th style="text-align: left;padding-left: 20px;">一、建设内容</th></tr>
	        <tr>
		     	<td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
		     		<c:choose>
					    <c:when test="${param.view == GlobalConstant.FLAG_Y}">
						    ${resultMap.attackDirection}      
						</c:when>
					    <c:otherwise>
		     		<textarea placeholder="必须明确1-2个主攻方向，分若干领域开展研究。"  class="xltxtarea" name="attackDirection">${resultMap.attackDirection}</textarea>
		     	   </c:otherwise>
		     	   </c:choose>
		     	</td>
		    </tr>
		    
			<tr><th style="text-align: left;padding-left: 20px;">二、主要目标</th></tr>
	        <tr>
		     	<td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
		     		<c:choose>
					    <c:when test="${param.view == GlobalConstant.FLAG_Y}">
						    ${resultMap.mainTarget}      
						</c:when>
					    <c:otherwise>
		     		<textarea placeholder="围绕主攻方向概述今后3年在临床服务、科技创新、人才培养等方面的建设目标、预期成果、年度安排等。"  class="xltxtarea" name="mainTarget">${resultMap.mainTarget}</textarea>
		     	    </c:otherwise>
		     	    </c:choose>
		     	</td>
		    </tr>
		    
			<tr><th style="text-align: left;padding-left: 20px;">三、考核指标</th></tr>
	        <tr>
		     	<td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
		     		<c:choose>
					    <c:when test="${param.view == GlobalConstant.FLAG_Y}">
						    ${resultMap.assessIndicators}      
						</c:when>
					    <c:otherwise>
		     		<textarea placeholder="绕主要目标，提出可以量化和考核的年度和最终考核指标。包含临床服务、技术创新、人才培养三个部分。临床服务包含专科发展、特色和优势技术开展、高难度病种诊治数、三、四级手术量、门急诊和住院业务量等。技术创新包括：新技术引进、成果奖、SCI录用及核心期刊论文发表等。人才培养包括：省、市领军和重点人才培养、学会任职、住院和专科医师培养等。考核指标只填答本单位本专业人员产生的指标。"  class="xltxtarea" name="assessIndicators">${resultMap.assessIndicators}</textarea>
		     	   </c:otherwise>
		     	   </c:choose>
		     	</td>
		    </tr>
		    
			<tr><th style="text-align: left;padding-left: 20px;">四、承建单位支撑计划</th></tr>
	        <tr>
		     	<td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
		     		<c:choose>
					    <c:when test="${param.view == GlobalConstant.FLAG_Y}">
						    ${resultMap.supportPlanning}      
						</c:when>
					    <c:otherwise>
		     		<textarea placeholder="须列出具体支持措施，含业务条件改善、人才梯队建设、配套资金落实等。"  class="xltxtarea" name="supportPlanning">${resultMap.supportPlanning}</textarea>
		     	    </c:otherwise>
		     	   </c:choose>
		     	</td>
		    </tr>
			
		</table>
		</form>
    <div class="button" style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
		<input id="prev" type="button" onclick="nextOpt('homePage')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('subjectUserList')" class="search" value="下一步"/>
    </div>
		