<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
table.basic b {text-decoration: none;cursor: text;}
	.se:HOVER {
		background: #E7E7EB;
	}
	table th,table td {border:1px solid #F4F5F9; height:35px;}
	th { color:#333; height:35px; text-align:right; padding-right:10px; background:#F4F5F9;border:1px solid #F4F5F9;}
	td { text-align:left; padding-left:10px; line-height:35px;border: 1px solid #E7E7EB;}
	.btn_green{display: inline-block;overflow: visible;padding: 0 20px;height: 30px;line-height: 28px;vertical-align: middle;text-align: center;text-decoration: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;font-size: 14px;cursor: pointer;font-family: "Microsoft YaHei";}
	.btn_green{background-color: #54B2E5;color: #fff;border:none;}
	.btn_green:hover{background-color: #2f9833;}
</style>

<script type="text/javascript">
    // $(document).ready(function() {
    //
    // }
        $(function(){
			var one="${empty resRecList?'':resRecList[0].recFlow}";
			if(one!=null&&one!=""){
				auditRec(one);
			};
			var yan="${empty resRecList?'':resRecList[0].auditStatusId}";
			if(one!=null&&one!=""){
				yanZhen(yan);
			};
	});
	//重新载入
	function reloadRecType(typeId){
		var url="<s:url value='/jsres/teacher/details'/>?doctorFlow=${user.userFlow}"+"&typeId="+typeId+"&processFlow=${param.processFlow}"+"&resultFlow=${param.resultFlow}";
		location.href = url;
	};
	 function auditRec(recFlow){
		 if(recFlow!=null){
			$(".se").css("background","");
			$('[bianYan="'+recFlow+'"]').css("background","#E7E7EB");
			var url = "<s:url value='/jsres/teacher/showAudit'/>?recFlow="+recFlow+"&recTypeId=${param.typeId}"; 
			/* jboxLoad("auditContent",url,false); */
			top.jboxPost(url,null,function(resp){
				$("#auditContent").html(resp);
				$("#auditContent .basic").removeClass("basic");
			},null,false);
		 }
	};
	function yanZhen(auditStatusId){
		if(auditStatusId!="${recStatusEnumTeacherAuditY.id}" && auditStatusId!="${recStatusEnumTeacherAuditN.id}"){
			$(".yan").show();
		};
		if(auditStatusId=="${recStatusEnumTeacherAuditY.id}" || auditStatusId=="${recStatusEnumTeacherAuditN.id}"){
			$(".yan").hide();
		};
	}
	function guanBi(){
		top.toPage();
		top.jboxClose();
	}
	function oneKeyBack(auditResult){
        jboxConfirm("确认一键退回?",function(){
            var url = "<c:url value="/jsres/teacher/batchAuditBack?auditResult="/>"+auditResult+"&docFlow=${user.userFlow}&processFlow=${param.processFlow}&recType="+$('#recType').val();
            top.jboxPost(url,null,function(data){
                if(data=='1'){
                    location.reload();
                    top.jboxTip('操作成功！');
                }else{
                    jboxTip('操作失败！');
                }
            },null,false);
        },null);
	}
	function oneKeyAudit(auditResult){
		jboxConfirm("确认一键审核?",function(){
			var url = "<c:url value="/jsres/teacher/batchAudit?auditResult="/>"+auditResult+"&docFlow=${user.userFlow}&processFlow=${param.processFlow}&recType="+$('#recType').val();
			top.jboxPost(url,null,function(data){
				if(data=='1'){
					location.reload();
					top.jboxTip('操作成功！');
				}else{
					jboxTip('操作失败！');
				}
			},null,false);
		},null);
	};
</script>
</head>
<body id="div1">
<div class="mainright" style="overflow: hidden;">
	<div class="content">
		<div class="title1 clearfix">
			<table width="100%">
				<tr>
					<td>
						住院医师：<b>${user.userName}</b>
						&#12288;
						轮转科室：<b>${deptName}</b>
						&#12288;
						类别：
						<select id="recType" onchange="reloadRecType(this.value);">
							<c:forEach items="${registryTypeEnumList}" var="registryType">
								<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
								<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
									<option <c:if test="${param.typeId eq registryType.id}">selected</c:if> value="${registryType.id}">${registryType.name}</option>
								</c:if>
							</c:forEach>
						</select>
						&#12288;
						完成数：<b id="finish">${complete}</b>
							<c:if test="${GlobalConstant.FLAG_Y eq pdfn:getRegistryTypeEnumById(param.recTypeId).haveAppeal}">
								&#12288;
								申述数：<b id="appeal">0</b>
							</c:if>
						&#12288;
                        <c:if test="${'Passed' eq statusId}">
                            <input type="button" class="btn_green" value="一键退回" onclick="oneKeyBack('');" style="float: right;margin-top: 4px;margin-right: 10px;">
                            <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['res_doc_key_audit']}">
                                <input type="button" class="btn_green" value="一键审核" onclick="oneKeyAudit('${recStatusEnumTeacherAuditY.id}');" style="float: right;margin-top: 4px;margin-right: 10px;">
                            </c:if>
                        </c:if>
					</td>
				</tr>
			</table>
			<div  style=" width: 100%;max-height:430px; margin-top: 10px;padding-bottom:10px; overflow-x:auto;">
				<div  style="width: 30%;max-height:410px;margin: 10px;float: left;overflow-x:auto;">
					<table style="width: 100%;">
						<tr>
							<th width="65%;" style="text-align: center;">填写时间</th>
							<th width="35%;"  style="text-align: center;">审核情况</th>
						</tr>
						<c:forEach items="${resRecList}" var="resRec">
							<tr style="cursor: pointer;" class="se" bianYan="${resRec.recFlow}" onclick="auditRec('${resRec.recFlow}');yanZhen('${resRec.auditStatusId}');">
								<td width="65%;" style="text-align: center;">${pdfn:transDate(resRec.operTime)}</td>
								<td class="${empty resRec.auditStatusId?'needAudit':''}">
											<c:if test="${empty resRec.auditStatusId}">
												待审核
											</c:if>
											<c:if test="${resRec.auditStatusId eq recStatusEnumTeacherAuditY.id}">
												审核通过
											</c:if>
											<c:if test="${resRec.auditStatusId eq recStatusEnumTeacherAuditN.id}">
												审核不通过
											</c:if>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty resRecList}">
								<tr><td colspan="2" style="text-align: center;">无记录</td></tr>
						</c:if>
					</table>
				</div>
				<div id="auditContent" style="float: right;height: 410px;width: 65%;margin: 10px;">
				</div>
			</div>
		</div>
		
		<div style="margin-top: 10px;width: 100%;float: left;text-align: center;">
                <c:if test="${'Passed' ne statusId}">
                    <input type="button" value="无操作权限，请联系基地管理员"  class="btn_green yan" style="display: none;" />
                </c:if>
                <c:if test="${'Passed' eq statusId}">
                    <input type="button" value="审核通过"  class="btn_green yan" style="display: none;" onclick="saveAudit('${recStatusEnumTeacherAuditY.id}');"/>
                </c:if>
				<%-- <input type="button" value="审核不通过"  class="search yan" style="display: none;" onclick="saveAudit('${recStatusEnumTeacherAuditN.id}');"/> --%>
				&#12288;<input type="button" class="btn_green" value="关&#12288;闭" onclick="guanBi();"/>
		</div>
	</div>
</div>
</body>
</html>