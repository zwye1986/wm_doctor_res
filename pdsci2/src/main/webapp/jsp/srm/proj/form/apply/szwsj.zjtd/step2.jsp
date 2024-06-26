
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
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step2"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
	<div style="width: 70%;margin-left:10%;border:1px dashed">
	<div style="width: 80%;margin-left: 10%;margin-top: 20px;margin-bottom: 20px">
	<h3 style="font-size:16px">一、承诺书</h3>
	<p style="line-height: 25px;font-size:16px">&#12288;&#12288;本单位和引进团队带头人及核心成员承诺遵守《苏州市“临床医学专家团队”引进计划实施方案》规定，并自愿作出以下声明：</p>
	<p style="line-height: 25px;font-size:16px">&#12288;&#12288;1.本单位（人）对本申请材料（包括本申请书及其附件）的合法性、真实性、准确性和完整性负责。如有虚假，依法承担相应的法律责任。</p>
	<p style="line-height: 25px;font-size:16px">&#12288;&#12288;2.如获得苏州市“临床医学专家团队”专项资金资助，团队成员将保持稳定，引进成员在苏州工作时间年均须累计在240天以上。</p>
	<p style="line-height: 25px;font-size:16px">&#12288;&#12288;3.本单位（人）同意将本申请材料向依法审批工作人员和评审专家公开，对依法审批或者评审过程中泄露的信息，苏州市卫生计生委免予承担责任。</p>
	<p style="line-height: 25px;font-size:16px">&#12288;&#12288;4.本申请材料仅为申请苏州市引进“临床医学专家团队”专项奖励资金立项制作并已自行备份，不再要求退还。</p>
	<p style="line-height: 25px;font-size:16px">&#12288;&#12288;5.本承诺书为团队带头人及核心成员亲自签名确认，如有虚假或者冒签的，视为团队自动放弃资助资格。如本团队带头人及核心成员违反以上承诺的，将承担相应的法律责任并赔偿损失，用人单位承担连带责任。</p>
	<p style="line-height: 25px;font-size:16px">&#12288;&#12288;特此承诺。</p>
	</div>
	</div>
</form>
					 
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 20px">
		<input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
	</div>
</c:if>	
