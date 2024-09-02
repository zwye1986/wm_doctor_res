<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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

<style>
.infoAudit{padding:20px;background:#fff;}
.infoAudit h1{ line-height:40px;  text-align:center; font-size:16px; color:#333;}
.infoAudit h2{line-height:40px; border-bottom:2px solid #54B2E5;padding-left:10px; color:#54B2E5; text-indend:10px;font-size:14px;}
.orgTable{ margin:10px 0;border-collapse: collapse;}
.orgCaption,.orgTh,.orgTd{height:40px;}
.orgCaption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
.orgTh{text-align:right;font-weight:500;padding-right:5px; color:#333;}
.orgTd{text-align:left; padding-left:5px; color:#999;}
.orgTable a{ color:#00f;}
.btn{padding-left:22px; padding-right:22px;}
.blue-btn{ background:#54B2E5; border: 1px solid #3ea543; color:#fff;}
.blue-btn:hover{ background:#3ea543;}
.red-btn{background:#f90; border: 1px solid #f49200; color:#fff;}
.red-btn:hover{ background:#f60;}
.h-btn{background:#eee;border: 1px solid #ddd; }
.h-btn:hover{ background:#ddd;}
textarea{ width:100%; height:150px;padding:0; resize:none; outline:none;text-indent:28px; line-height:24px; font-family:'微软雅黑'; }
</style>

<script type="text/javascript">
	function saveForm(){
		if($("#personalSummary").val()){
			var personalSummary=$("#personalSummary").val().trim();
			$("#personalSummary").html(personalSummary);
		}
		var info = "确认提交?<c:if test="${(param.roleFlag ne GlobalConstant.RES_ROLE_SCOPE_DOCTOR and param.roleFlag ne GlobalConstant.RES_ROLE_SCOPE_HEAD)}">一旦提交将无法修改!</c:if>";
		if($("#summaryForm").validationEngine("validate")){
			autoValue($("#summaryForm"),"autoValue");
			jboxConfirm(info,function(){
				jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$('#summaryForm').serialize(),function(){
					<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER || param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
						top.document.mainIframe.search();
					</c:if>
					jboxClose();
				},null,true);
			});
		}
	}

	<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId and (cfg13 ne 'Y' or  sysCfgMap['res_isGlobalSch_flag'] eq 'Y')}">
	function valiAsses(oldinf){
		jboxCustomSqlData([
			{
				id:'res_evaluation_1.0_getGradeDatas_cap[teacher]',
				values:['${resRecTypeEnumTeacherGrade.id}','${param.processFlow}']
			},
			{
				id:'res_evaluation_1.0_getGradeDatas_cap[dept]',
				values:['${resRecTypeEnumDeptGrade.id}','${param.processFlow}']
			}
		],function(resp){
			var scoreMap = resp['res_evaluation_1.0_getGradeDatas_cap'];
			var tl = scoreMap.teacher.length;
			var dl = scoreMap.dept.length;
			if((tl+dl)<2){
				<c:if test="${param.viewFlag ne 'Y'}">
				var s=5;
				setInterval(function(){
					document.body.innerHTML="<span style='color: red;font-size: 22px;'>请填写对带教老师和科室评价后再填写出科小结！"+s+"秒后自动关闭...</span>";
					s--;
					if(s<0){
					jboxClose();
					}
				},1000)
				</c:if>
				<c:if test="${param.viewFlag eq 'Y'}">
				document.body.innerHTML = oldinf;
				</c:if>
			}else{
				document.body.innerHTML = oldinf;
			}
		});
	}
	$(function(){
		var oldinf = document.body.innerHTML;
		document.body.innerHTML = "";
		valiAsses(oldinf);
	});
	</c:if>

	function jboxPrint(id) {
		jboxTip("正在准备打印…")
		setTimeout(function(){
			$("#title").show();
			var newstr = $("#"+id).html();
			var oldstr = document.body.innerHTML;
			document.body.innerHTML = newstr;
			window.print();
			document.body.innerHTML = oldstr;
			$("#title").hide();
			jboxEndLoading();
			return false;
		},2000);
	}
</script>
</head>
<body>	
	<div class="mainright">
      <div class="content">
		<div class="infoAudit">
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 12px;display: none;">
					出科小结
				</div>
			<div style="width: 90%;margin-right: auto;margin-left: auto;">
				<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
					姓名：${rec.operUserName}
				</c:if>
			</div>

			<c:set var="docSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && empty rec.auditStatusId && empty rec.headAuditStatusId}"/>
			<c:set var="teaSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}"/>
			<%--<c:set var="headSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId && (not empty rec.auditStatusId)}"/>--%>
			<c:set var="headSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)}"/>
			<c:set var="showMsg" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId &&  empty rec.auditStatusId}"/>

	 		<form id="summaryForm" method="post">
		 		<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${empty rec.recFlow?param.recFlow:rec.recFlow}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}">
				<input type="hidden" name="recTypeId" value="${param.recTypeId }">
				<input type="hidden" name="processFlow" value="${param.processFlow }">
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}">
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}">
				</c:if>

				<input type="hidden" name="summaryDocName" value="${empty formDataMap.summaryDocName?currUser.userName:formDataMap.summaryDocName}">

				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color:#459ae9;">个人小结</caption>
					<tr class="orgTr">
						<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
							<c:if test="${docSub}">
								<textarea id="personalSummary" name="personalSummary" class="validate[required]">${formDataMap['personalSummary']}</textarea>
							</c:if>
							<c:if test="${empty rec && !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
								暂无出科小结!
							</c:if>
							<c:if test="${!docSub}">
								&#12288;${formDataMap['personalSummary']}
								<input type="hidden" name="personalSummary" value="${formDataMap['personalSummary']}"/>
							</c:if>
						</td>
					</tr>
				</table>

				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER || !empty rec.auditStatusId}">
				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color:#459ae9;">带教评价</caption>
					<tr class="orgTr">
						<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
							<c:if test="${teaSub}">
								<textarea name="deptAppraise" class="validate[required]"></textarea>
							</c:if>
							<c:if test="${!teaSub}">
								&#12288;${formDataMap['deptAppraise']}
								<input type="hidden" name="deptAppraise" value="${formDataMap['deptAppraise']}"/>
							</c:if>
						</td>
					</tr>
				</table>
				</c:if>

				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD || param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color:#459ae9;">
						科室评价&#12288;
						<label style="font-weight: normal;color: black;">
								同意出科：
								<label>
									<input type="radio" class="autoValue" checked="checked" disabled="disabled" value="${GlobalConstant.FLAG_Y}"/> 是
								</label>
								<input type="hidden" name="isAgree" value="${GlobalConstant.FLAG_Y}"/>
						</label>
					</caption>
				</table>
				</c:if>
	         </form>
	 		</div>

			<div style="text-align: center;margin-top: 5px;width: 90%;margin-left: auto;margin-right: auto;">
				<c:if test="${docSub || teaSub}">
					<input type="button" value="提&#12288;交" class="search" onclick="saveForm();"/>
				</c:if>
				<c:if test="${(GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag or 'manage'eq param.roleFlag) && not empty rec.auditStatusId}">
					<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
				</c:if>
				<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
			</div>
			</div>
	      </div>
	      </div>
</body>
</html>