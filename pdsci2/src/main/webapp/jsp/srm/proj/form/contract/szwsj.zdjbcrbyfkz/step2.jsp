
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
		   
		
		<table  class="basic" style="width: 100%;margin-top: 10px;">
			<tr><th style="text-align: left;padding-left: 20px;">一、项目研究内容、目标（包括阶段性目标、年度目标和最终目标）及考核指标（科研、技术、效益）</th></tr>
	        <tr>
		     	<td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
		     		<c:choose>
					    <c:when test="${param.view == GlobalConstant.FLAG_Y}">
						    ${resultMap.attackDirection}      
						</c:when>
					    <c:otherwise>
		     		<textarea  class="xltxtarea" style="height: 400px;" name="attackDirection">${resultMap.attackDirection}</textarea>
		     	   </c:otherwise>
		     	   </c:choose>
		     	</td>
		    </tr>
		    
			<tr><th style="text-align: left;padding-left: 20px;">二、计划进度（项目总期限，进度及分年度计划）</th></tr>
	        <tr>
		     	<td style="text-align:left;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;">
		     		<c:choose>
					    <c:when test="${param.view == GlobalConstant.FLAG_Y}">
						    ${resultMap.mainTarget}      
						</c:when>
					    <c:otherwise>
		     		<textarea style="height: 400px;"  class="xltxtarea" name="mainTarget">${resultMap.mainTarget}</textarea>
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
		