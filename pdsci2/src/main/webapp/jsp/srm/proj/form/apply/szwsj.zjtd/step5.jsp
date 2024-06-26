
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

$(document).ready(function(){
	if($("#completeTb tr").length<=0){
		add('complete');
	}
});
function add(templateId) {
    if (templateId) {
        if ($('.' + templateId + ' .toDel').length < 10) {
            $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
            reSeq(templateId);
            //projCount(templateId);
        } else {
            jboxTip('该项最多新增10条！');
        }
    }
}

function del(templateId) {
    if (templateId) {
        if (!$('.' + templateId + ' .toDel:checked').length) {
            return jboxTip('请选择需要删除的项目！');
        }
        jboxConfirm('确认删除？', function () {
            $('.' + templateId + ' .toDel:checked').closest('tr').remove();
            reSeq(templateId);
            //projCount(templateId);
        }, null);
    }
}

function reSeq(templateId) {
    $('.' + templateId + ' .seq').each(function (i, n) {
        $(n).text(i + 1);
    });
}

</script>
</c:if>

<font style="font-size: 14px; font-weight: bold; color: #333;">四、团队基本情况</font>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step5" />
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" />
	<input type="hidden" name="recTypeId" value="${param.recTypeId}" />
	<table class="basic" style="width: 100%;margin-top: 10px;">
		<colgroup>
			<col width="13%"/>
			<col width="15%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="7%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
		</colgroup>
		<tr>
			<th rowspan="2">团队名称</th>
			<th>中&#12288;文</th>
			<td colspan="6"><input type="text" class="inputText" name="teamNameCN" value="${resultMap.teamNameCN}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>外&#12288;文</th>
			<td colspan="6"><input type="text" class="inputText" name="teamNameEN" value="${resultMap.teamNameEN}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th rowspan="2">研究方向或专业方向</th>
			<th>中&#12288;文</th>
			<td colspan="6"><input type="text" class="inputText" name="teamResearchDirectionCN" value="${resultMap.teamResearchDirectionCN}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>外&#12288;文</th>
			<td colspan="6"><input type="text" class="inputText" name="teamResearchDirectionEN" value="${resultMap.teamResearchDirectionEN}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>联系人</th>
			<td><input type="text" class="inputText" name="teamLinkman" value="${resultMap.teamLinkman}" style="width: 80%" /></td>
			<th>联系电话</th>
			<td colspan="2"><input type="text" class="inputText" name="teamLinkTel" value="${resultMap.teamLinkTel}" style="width: 80%" /></td>
			<th>传&#12288;真</th>
			<td colspan="2"><input type="text" class="inputText" name="teamFax" value="${resultMap.teamFax}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>电子邮箱</th>
			<td colspan="4"><input type="text" class="inputText" name="teamEmail" value="${resultMap.teamEmail}" style="width: 80%" /></td>
			<th>团队总人数</th>
			<td colspan="2"><input type="text" class="inputText" name="teamTotalNumber" value="${resultMap.teamTotalNumber}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th rowspan="2">其&#12288;中</th>
			<th colspan="2">正高人数</th>
			<td><input type="text" class="inputText" name="teamSeniorNum" value="${resultMap.teamSeniorNum}" style="width: 80%" /></td>
			<th colspan="3">平均年龄</th>
			<td><input type="text" class="inputText" name="teamAvgAge" value="${resultMap.teamAvgAge}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>博&#12288;士</th>
			<td><input type="text" class="inputText" name="teamDoctorNum" value="${resultMap.teamDoctorNum}" style="width: 80%" /></td>
			<th>硕&#12288;士</th>
			<td colspan="2"><input type="text" class="inputText" name="teamMasterNum" value="${resultMap.teamMasterNum}" style="width: 80%" /></td>
			<th>本科及以下</th>
			<td><input type="text" class="inputText" name="teamOtherNum" value="${resultMap.teamOtherNum}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th colspan="3">所在团队专科在全国排名情况（列出排名依据、年度及名次）</th>
			<td colspan="5"><input type="text" class="inputText" name="teamRanking" value="${resultMap.teamRanking}" style="width: 80%" /></td>
		</tr>
	</table>
	<table class="basic" style="width: 100%; margin-top: 10px;">
		<tr>
			<th colspan="9" class="theader" style="text-align: left">
				&#12288;&#12288;团队核心成员基本信息（含团队带头人，至少填写3个核心成员信息）
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('membersList')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('membersList');"/>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
			<td style="font-weight: bold;text-align: center" width="7%"></td>
</c:if>
			<td style="font-weight: bold;text-align: center" width="8%">序&#12288;号</td>
			<td style="font-weight: bold;text-align: center" width="15%">姓&#12288;名</td>
			<td style="font-weight: bold;text-align: center" width="15%">出生年月</td>
			<td style="font-weight: bold;text-align: center" width="12%">到苏工作时间</td>
			<td style="font-weight: bold;text-align: center" width="15%">年均在苏工作时间（天）</td>
			<td style="font-weight: bold;text-align: center" width="27%">主要行政/学术职务</td>
		</tr>
		<tbody class="membersList">
		<c:forEach var="membersList" items="${resultMap.membersList}" varStatus="status">
			<tr>
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<td style="text-align: center"><input class="toDel" type="checkbox" /></td>
				</c:if>
				<td style="text-align: center;" class="seq">${status.count}</td>
				<td><input type="text" class="validate inputText" name="membersList_name" value="${membersList.objMap.membersList_name}" style="width: 90%;" /></td>
				<td>
					<input type="text" style="width: 90%;" name="membersList_birth" value="${membersList.objMap.membersList_birth}" onClick="WdatePicker({dateFmt:'yyyy年MM月'})"  class="inputText ctime" readonly="readonly"/>
				</td>
				<td>
					<input type="text" class="inputText" name="membersList_inSZworkTime" value="${membersList.objMap.membersList_inSZworkTime}" style="width: 90%;" />
				</td>
				<td><input type="text" class="inputText" name="membersList_annualWorkingTime" value="${membersList.objMap.membersList_annualWorkingTime}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="membersList_mainPost" value="${membersList.objMap.membersList_mainPost}" style="width: 90%;" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<table class="basic" style="width: 100%; margin-top: 10px;">
		<tr>
			<th style="text-align: left">&#12288;&#12288;团队简介（1000字以内）</th>
		</tr>
		<tr>
			<td>
				<p>主要介绍团队研究方向、主要研究内容及近三年取得的重要业绩成果，介绍团队合作的稳定性和紧密性。</p>
				<textarea class="xltxtarea validate[maxSize[1000]]" style="width:98%;height: 150px;" name="team_profile">${resultMap.team_profile}</textarea>
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

<div style="display: none;">
	<!-- 模板 -->
	<table >
		<tr id="membersList">
			<td style="text-align: center"><input class="toDel" type="checkbox" /></td>
			<td style="text-align: center;" class="seq"></td>
			<td><input type="text" class="validate inputText" name="membersList_name" style="width: 90%;" /></td>
			<td>
				<input type="text" style="width: 90%;" name="membersList_birth" onClick="WdatePicker({dateFmt:'yyyy年MM月'})"  class="inputText ctime" readonly="readonly"/>
			</td>
			<td><input type="text" class="inputText" name="membersList_inSZworkTime" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="membersList_annualWorkingTime" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="membersList_mainPost" style="width: 90%;" /></td>
		</tr>
	</table>
</div>
