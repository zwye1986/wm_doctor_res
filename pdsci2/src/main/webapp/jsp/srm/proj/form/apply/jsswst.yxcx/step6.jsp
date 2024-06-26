<c:if test="${param.view != GlobalConstant.FLAG_Y}">

	<script type="text/javascript">
		function nextOpt(step) {
			if (false == $("#projForm").validationEngine("validate")) {
				return false;
			}
			var form = $('#projForm');
			form.append("<input name='nextPageName' value='" + step + "' type='hidden'/>");
			$('#nxt').attr({"disabled": "disabled"});
			$('#prev').attr({"disabled": "disabled"});
			jboxStartLoading();
			form.submit();
		}
	</script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" id="pageName" name="pageName" value="step6"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

	<font style="font-size: 14px; font-weight:bold;color: #333;">五、创新团队主攻方向</font>
	<table width="100%" cellspacing="0" cellpadding="0" class="basic">
		<tr>
			<th style="text-align: left;">
				&#12288;&#12288;团队必须围绕一个研究方向、或一种专科疾病、或一项关键技术开展研究。概述本团队今后5年在应用基础研究、临床研究方面的建设目标、研究内容、预期成果、年度安排等。（限2000字内）。
			</th>
		</tr>
		<tr>
			<td><textarea name="researchContent" style="width:98%;height: 150px;"
						  class="validate[maxSize[2000]]">${resultMap.researchContent}</textarea></td>
		</tr>

	</table>

	<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
			<input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
			<input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="下一步"/>
		</div>
	</c:if>
</form>
