
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="ueditor" value="true"/>
	</jsp:include>
	<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
    var flag = 1;
    var ues = ['basicConditionBuild' , 'techAbilityBuild' , 'learnAbilityBuild','talentTeamBuild','personTrainingBuild'];
    flag = checkImg(ues);
    if(flag) {
        var form = $('#projForm');
        form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
        $('#nxt').attr({"disabled": "disabled"});
        $('#prev').attr({"disabled": "disabled"});
        jboxStartLoading();
        form.submit();
    }
}
$(document).ready(function(){
    initUEditer("basicConditionBuild");
    initUEditer("techAbilityBuild");
    initUEditer("learnAbilityBuild");
    initUEditer("talentTeamBuild");
    initUEditer("personTrainingBuild");
});
</script>
</c:if>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step6" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" /> 
	<input type="hidden" name="recTypeId" value="${param.recTypeId}" />
	<font style="font-size: 14px; font-weight: bold; color: #333;">五、引进团队工作开展情况</font>
	<table class="basic" style="width: 100%;margin-top: 10px;text-align: left">
		<thead>
			<th colspan="2">
				&#12288;&#12288;（一）引进团队学科建设总体目标（有明确的可量化考核指标）
			</th>
		</thead>
		<tbody>
		<tr>
			<th width="20%">基础条件建设</th>
			<td style="text-align:left;padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
				<c:choose>
					<c:when test="${param.view==GlobalConstant.FLAG_Y}">
						${resultMap.basicConditionBuild}
					</c:when>
					<c:otherwise>
						<script id="basicConditionBuild" name="basicConditionBuild" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.basicConditionBuild}</script>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>技术能力建设</th>
			<td style="text-align:left;padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
				<c:choose>
					<c:when test="${param.view==GlobalConstant.FLAG_Y}">
						${resultMap.techAbilityBuild}
					</c:when>
					<c:otherwise>
						<script id="techAbilityBuild" name="techAbilityBuild" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.techAbilityBuild}</script>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>学术能力建设</th>
			<td style="text-align:left;padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
				<c:choose>
					<c:when test="${param.view==GlobalConstant.FLAG_Y}">
						${resultMap.learnAbilityBuild}
					</c:when>
					<c:otherwise>
						<script id="learnAbilityBuild" name="learnAbilityBuild" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.learnAbilityBuild}</script>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>人才队伍建设</th>
			<td style="text-align:left;padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
				<c:choose>
					<c:when test="${param.view==GlobalConstant.FLAG_Y}">
						${resultMap.talentTeamBuild}
					</c:when>
					<c:otherwise>
						<script id="talentTeamBuild" name="talentTeamBuild" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.talentTeamBuild}</script>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>人才培养/能力建设</th>
			<td style="text-align:left;padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
				<c:choose>
					<c:when test="${param.view==GlobalConstant.FLAG_Y}">
						${resultMap.personTrainingBuild}
					</c:when>
					<c:otherwise>
						<script id="personTrainingBuild" name="personTrainingBuild" type="text/plain" style="width:100%;height:150px;margin-right: 10px;">${resultMap.personTrainingBuild}</script>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</tbody>
	</table>

</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="下一步"/>
	</div>
</c:if>	

