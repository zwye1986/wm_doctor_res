<c:if test="${param.view != GlobalConstant.FLAG_Y}">
	<script type="text/javascript">
		function nextOpt(step) {
			if (false == $("#projForm").validationEngine("validate")) {
				return false;
			}
			var form = $('#projForm');
			form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
			$('#nxt').attr({"disabled": "disabled"});
			$('#prev').attr({"disabled": "disabled"});
			jboxStartLoading();
			form.submit();
		}
	</script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" id="pageName" name="pageName" value="step11"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

	<font style="font-size: 14px; font-weight:bold;color: #333;">十一、本单位协作任务相关材料</font>
	<table width="100%" cellspacing="0" cellpadding="0" class="basic">
		<tr>
			<th style="text-align: left;">
				&#12288;&#12288;概述协作任务，限1000字。
			</th>
		</tr>
		<tr>
			<td><textarea name="teamWork" style="width:98%;height: 350px;"  class="validate[maxSize[1000]]">${resultMap.teamWork}</textarea></td>
		</tr>
	</table>

	<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
			<input id="prev" type="button" onclick="nextOpt('step10')" class="search" value="上一步"/>
			<input id="nxt" type="button" onclick="nextOpt('step12')" class="search" value="下一步"/>
		</div>
	</c:if>
</form>
