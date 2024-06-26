<style>
	.auditPass{height:30px;width: 100px}
</style>
<script>
</script>
<div class="btn_info">
	<c:if test="${GlobalConstant.FLAG_Y eq param.auditFlag}">
		<input type="button"  class="btn_green auditPass"  onclick="auditStatus(${param.baseFlow},'Y');" value="通过"  <c:if test="${resBase.baseStatusId eq  baseStatusEnumChargePassed.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id }">style="display: none;"</c:if> ></input>
		<input type="button"  class="btn_red auditPass" onclick="auditStatus(${param.baseFlow},'N');" value="不通过" <c:if test="${resBase.baseStatusId eq baseStatusEnumChargePassed.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id}">style="display: none;"</c:if>  ></input>
  	</c:if>
	<input type="button"  class="btn_green auditPass" onclick="javascript:jboxClose();" value="关&#12288;闭"  <c:if test="${empty param.openType}"> style="display: none;"</c:if> ></input>
	<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and  param.viewFlag != GlobalConstant.FLAG_Y}">
		<input type="button" class="btn_green"  value="编&#12288;辑"onclick="editInfo('${baseInfoName}','${sessionScope.currUser.orgFlow}');"  <c:if test="${resBase.baseStatusId eq baseStatusEnumAuditing.id}">style="display: none;"</c:if>></input>
  	</c:if>
</div>