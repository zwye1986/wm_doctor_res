 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<!--<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>-->
<style type="text/css">
.ith{height: 40px;line-height: 40px;padding-left: 10px;}
</style>
<script type="text/javascript">
	function saveAudit(auditResult){
		var $form = $("#auditForm");
		if($form.validationEngine("validate")){
			jboxConfirm("确认审核?",function(){

				jboxSubmit($("#auditForm"),"<s:url value='/res/teacher/audit'/>?auditResult="+auditResult,function(resp){
					jboxTip(resp);
					if(resp=="${GlobalConstant.OPRE_SUCCESSED}"){
						location.reload();
					}
				},function(resp){
					jboxTip("${GlobalConstant.OPRE_FAIL}");
				},false);
				<%--var url = "<s:url value='/res/teacher/audit'/>?auditResult="+auditResult;--%>
				<%--var requestData = $form.serialize();--%>
				<%--jboxPost(url,requestData,function(resp){--%>
					<%--if(resp=="${GlobalConstant.OPRE_SUCCESSED}"){--%>
						<%--//top.document.mainIframe.location.reload();--%>
						<%--location.reload();--%>
<%--// 						if(window.parent.frames["jbox-message-iframe"] == undefined){--%>
<%--// 							window.parent.frames["mainIframe"].window.$(".selectTag a").click();--%>
<%--// 						}else{--%>
<%--// 							window.parent.frames["jbox-message-iframe"].window.recReLoad();--%>
<%--// 						}--%>
<%--// 						jboxClose();--%>
					<%--}--%>
				<%--},null,true);--%>
			},null);
		}
	}
	
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
  
  <form id="auditForm" enctype="multipart/form-data" method="post">
    <div class="i-trend">
      <table class="i-trend-table" style="width:100%;">
         <tr>
	        <th class="ith" style="border-top:1px solid #e1e1e1;" colspan="2"><span>带教老师评价</span></th>
         </tr>
         <tr>
            <td colspan="2">
            <c:choose>
            <c:when test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER and  (empty rec.auditStatusId)}">
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
        </tr>
        <c:if test="${(param.recTypeId eq resRecTypeEnumCaseRegistry.id)||(param.recTypeId eq resRecTypeEnumEmergencyCase.id)||(param.recTypeId eq resRecTypeEnumHospitalizationLog.id)}">
              <tr>
                  <td style="width:100px;">
                     批改附件：
                  </td>
                  <td >
                      <c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && (not empty rec.auditStatusId)}">
                          <c:if test="${not empty formDataMap['teaFile_Flow']}">
                              <c:set var="teaFileFlows" value="${pdfn:split(formDataMap['teaFile_Flow'],',')}"/>
                              <c:set var="teaFileNames" value="${pdfn:split(formDataMap['teaFile'],',')}"/>
                              <c:forEach var="fileFlow" items="teaFileFlows" varStatus="status">
                                  <a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${teaFileFlows[status.index]}">【${teaFileNames[status.index]}】</a>
                              </c:forEach>
                          </c:if>
                      </c:if>
                      <c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && (empty rec.auditStatusId))}">
                          <c:if test="${not empty formDataMap['teaFile_Flow']}">
                              <c:set var="teaFileFlows" value="${pdfn:split(formDataMap['teaFile_Flow'],',')}"/>
                              <c:set var="teaFileNames" value="${pdfn:split(formDataMap['teaFile'],',')}"/>
                              <c:forEach var="fileFlow" items="teaFileFlows" varStatus="status">
                                  <a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${teaFileFlows[status.index]}">【${teaFileNames[status.index]}】</a>
                              </c:forEach>
                          </c:if>
                        <c:if test="${empty formDataMap['teaFile_Flow'] }">
                             <input type="file" name="teaFile"/>
                        </c:if>
                      </c:if>
                  </td>
              </tr>
        </c:if>

          <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_SECRETARY }">
              <c:if test="${role eq 'secretary'}">
              <tr>
                <th class="ith" style="border-top:1px solid #e1e1e1;" colspan="2"><span>科室秘书评价</span></th>
              </tr>
              <tr>
                <td colspan="2">
                    <c:choose>
                        <c:when test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_SECRETARY and  (empty rec.headAuditStatusId)}">
                            <textarea name="auditAppraise" style="width:98%;height: 120px;border: none;padding:0 1%;" placeholder="请填写评价" >${formDataMap['deptHeadAutograth']}</textarea>
                        </c:when>
                        <c:otherwise>
                            <fieldset style="border:none;margin:0 5px;">
                                <c:out value="${formDataMap['deptHeadAutograth']}" default="未评价"/>
                            </fieldset>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${!empty param.isReport && param.isReport}">
                        <c:out value="${formDataMap['deptHeadAutograth']}" default="未评价"/>
                    </c:if>

                    <%--<textarea name="auditAppraise" style="width:98%;height: 120px;border: none;padding:0 1%;" placeholder="请填写评价" >${formDataMap['deptHeadAutograth']}</textarea>--%>
                    <%--<c:if test="${!empty param.isReport && param.isReport}">--%>
                        <%--<c:out value="${formDataMap['deptHeadAutograth']}" default="未评价"/>--%>
                    <%--</c:if>--%>
                </td>
              </tr>
              </c:if>
          </c:if>
</table>
	<input type="hidden" name="roleFlag" value="${param.roleFlag}">
	<input type="hidden" name="recTypeId" value="${param.recTypeId }">
	<input type="hidden" name="recFlow" value="${param.recFlow }">
	<input type="hidden" name="processFlow" value="${param.processFlow }">
	<div style="text-align:center;margin-top: 20px;">
        <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && (empty rec.auditStatusId)}">
            <input type="button" value="审核通过"  class="search" onclick="saveAudit('${recStatusEnumTeacherAuditY.id}');"/>
            &#12288;
            <input type="button" value="审核不通过"  class="search" onclick="saveAudit('${recStatusEnumTeacherAuditN.id}');"/>
        </c:if>

        <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_SECRETARY && (empty rec.headAuditStatusId)}">
            <c:if test="${role eq 'secretary'}">
            <input type="button" value="审核通过"  class="search" onclick="saveAudit('${recStatusEnumSecretaryAuditY.id}');"/>
            &#12288;
            <input type="button" value="审核不通过"  class="search" onclick="saveAudit('${recStatusEnumSecretaryAuditN.id}');"/>
            </c:if>
        </c:if>
	<div>

	</div>
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