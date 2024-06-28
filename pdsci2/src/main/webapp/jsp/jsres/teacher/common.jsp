<html>
<head>
<style type="text/css">
.ith{height: 40px;line-height: 40px;padding-left: 10px;}
</style>
<script type="text/javascript">
	$(function(){
		<c:if test="${!empty param.isReport && param.isReport}">
			$("td *").remove();
			$(":button").remove();
			$("table").after($('<div align="center"></div>').css("margin-top","10px")
					.append($('<input type="button" value="关&#12288;闭" class="search" />')
					.click(function(){
						jboxClose();
					})));
			$("td").wrapInner('<fieldset style="border:none;margin:0 5px;"></fieldset>');
			$("#recRecord").empty();
		</c:if>
		
		$("#recRecord :button").remove();
		$("#recRecord").find(".mainright,.content,.title1.clearfix,form").unwrap();
	});
	
	function saveAudit(auditResult){
		jboxConfirm("确认审核?",function(){
			var url = "<s:url value='/jsres/teacher/audit'/>?auditResult="+auditResult+"&recFlow=${param.recFlow}"+"&recType=${param.recTypeId}";
			top.jboxPost(url,null,function(data){
				if(data=='${GlobalConstant.OPRE_SUCCESSED_FLAG}'){
					location.reload();
					top.jboxTip('${GlobalConstant.OPRE_SUCCESSED}');
				}else{
					jboxTip('${GlobalConstant.OPRE_FAIL}');
				}
			},null,false);
		},null);
	};
	 
</script>
</head>
<body>
<div class="mainright">
	<div class="content" style="padding:0 10px 10px 0;">
<div class="ui-container">
  <div class="i-content" style="width:100%">
  
  <div id="recRecord" style="display: none;">
  	<jsp:include page="/jsp/${jspPath}.jsp" flush="true">
  		<jsp:param value="true" name="noHead"/>
  	</jsp:include>
  </div>
  
  <form id="auditForm">
    <div class="i-trend">
      <table class="i-trend-table" style="width:100%;">
      <tbody>
<%--   <tr>
	<th class="ith" style="border-top:1px solid #e1e1e1;">
	<span>带教老师评价</span>
	</th>
  </tr>
  <tr>
    <td>
    <c:choose>
    	<c:when test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && (empty rec.auditStatusId)}">
    	<textarea name="auditAppraise" style="width:98%;height: 120px;border: none;padding:0 1%;" placeholder="请填写评价" >${formDataMap['deptAppraise']}</textarea>
    	</c:when>
    	<c:otherwise>
    	<fieldset style="border:none;margin:0 5px;">
    	<c:out value="${formDataMap['deptAppraise']}" default="未评价"/>
    	</fieldset>
    	</c:otherwise>
    </c:choose>
    <c:if test="${!empty param.isReport && param.isReport}">
    	<c:out value="${formDataMap['deptAppraise']}" default="未评价"/>
    </c:if>
    </td>
  </tr> --%>
  <%-- <c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD }">
  <tr>
	<th class="ith" style="border-top:1px solid #e1e1e1;"><span>科主任评价</span></th>
  </tr>
  <tr>
    <td>
	   	<textarea name="auditAppraise" style="width:98%;height: 120px;border: none;padding:0 1%;" placeholder="请填写评价" >${formDataMap['deptHeadAutograth']}</textarea>
	   	<c:if test="${!empty param.isReport && param.isReport}">
	   		<c:out value="${formDataMap['deptHeadAutograth']}" default="未评价"/>
	   	</c:if>
    </td>
  </tr>
  </c:if> --%>
</table>
	<input type="hidden" name="roleFlag" value="${param.roleFlag}">
	<input type="hidden" name="recTypeId" value="${param.recTypeId }">
	<input type="hidden" name="recFlow" value="${param.recFlow }">
	<input type="hidden" name="processFlow" value="${param.processFlow }">
	<div style="text-align:center;margin-top: 20px;">
	<!--<c:choose>
		<c:when test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD }">
		<input type="button" value="审核通过"  class="search" onclick="saveAudit('${recStatusEnumHeadAuditY.id}');"/>&#12288;<input type="button" value="审核不通过"  class="search" onclick="saveAudit('${recStatusEnumHeadAuditN.id}');"/>
		</c:when>
		<c:otherwise>
		<input type="button" value="审核通过"  class="search" onclick="saveAudit('${recStatusEnumTeacherAuditY.id}');"/>&#12288;<input type="button" value="审核不通过"  class="search" onclick="saveAudit('${recStatusEnumTeacherAuditN.id}');"/>
		</c:otherwise>
	</c:choose>
	<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/> -->
	</div>
  </div>
  </form>
 </div>
  </div>
  </div>
</div>


</body>
</html>