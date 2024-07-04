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
.infoAudit{background:#fff;}
.infoAudit h1{ line-height:40px;  text-align:center; font-size:16px; color:#333;}
.infoAudit h2{line-height:40px; border-bottom:2px solid #44b549;padding-left:10px; color:#44b549; text-indend:10px;font-size:14px;}
.orgTable{ margin:10px 0;border-collapse: collapse;}
.orgCaption,.orgTh,.orgTd{height:40px;}
.orgCaption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
.orgTh{text-align:right;font-weight:500;padding-right:5px; color:#333;}
.orgTd{text-align:left; padding-left:5px; color:black;}
.orgTable a{ color:#00f;}
.btn{padding-left:22px; padding-right:22px;}
.blue-btn{ background:#44b549; border: 1px solid #3ea543; color:#fff;}
.blue-btn:hover{ background:#3ea543;}
.red-btn{background:#f90; border: 1px solid #f49200; color:#fff;}
.red-btn:hover{ background:#f60;}
.h-btn{background:#eee; }
.h-btn:hover{ background:#ddd;}
textarea{ width:100%; height:150px;padding:0; resize:none; outline:none;text-indent:28px; line-height:24px; font-family:'微软雅黑'; }
</style>

<script type="text/javascript">
	function saveForm(){
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

	<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId}">
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
      <div class="content" style="padding: 0;">
		<div class="infoAudit" style="color:black;">
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 12px;display: none;">
					出科小结
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
				</c:if>
				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}">
				</c:if>

				<input type="hidden" name="summaryDocName" value="${empty formDataMap.summaryDocName?currUser.userName:formDataMap.summaryDocName}">

				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color:#459ae9;">轮转信息</caption>
					<tr class="orgTr" style="margin-top: 10px;">
						<th class="orgTd" style="text-align: right;width: 80px;">
							姓&#12288;&#12288;名：
						</th>
						<td class="orgTd" style="">
							<c:if test="${docSub}">
								${operUser.userName}
								<input type="hidden" name="doctorName" value="${operUser.userName}"/>
							</c:if>
							<c:if test="${!docSub}">
								${formDataMap['doctorName']}
								<input type="hidden" name="doctorName" value="${formDataMap['doctorName']}"/>
							</c:if>

						</td>
						<th class="orgTd" style="text-align: right;width: 80px;">
							科&#12288;&#12288;室：
						</th>
						<td class="orgTd" colspan="" style="">
							<c:if test="${docSub}">
								${schDept.schDeptName}
								<input type="hidden" name="schDeptName" value="${schDept.schDeptName}"/>
							</c:if>
							<c:if test="${!docSub}">
								${formDataMap['schDeptName']}
								<input type="hidden" name="schDeptName" value="${formDataMap['schDeptName']}"/>
							</c:if>
						</td>
						<th class="orgTd" style="text-align: right;width: 80px;">
							专&#12288;&#12288;业：
						</th>
						<td class="orgTd" colspan="" style="">
							<c:if test="${docSub}">
								${doctor.trainingSpeName}
								<input type="hidden" name="trainingSpeName" value="${doctor.trainingSpeName}"/>
							</c:if>
							<c:if test="${!docSub}">
								${formDataMap['trainingSpeName']}
								<input type="hidden" name="trainingSpeName" value="${formDataMap['trainingSpeName']}"/>
							</c:if>
						</td>
					</tr>
					<tr class="orgTr">
						<th class="orgTd" style="text-align: right;width: 80px;">
							医&#12288;&#12288;院：
						</th>
						<td class="orgTd" style="">
							<c:if test="${docSub}">
								${doctor.orgName}
								<input type="hidden" name="orgName" value="${doctor.orgName}"/>
							</c:if>
							<c:if test="${!docSub}">
								${formDataMap['trainingSpeName']}
								<input type="hidden" name="orgName" value="${formDataMap['orgName']}"/>
							</c:if>
						</td>
						<th class="orgTd" style="text-align: right;width: 80px;">
							入培时间：
						</th>
						<td class="orgTd" colspan="3" style="">
							<c:if test="${docSub}">
								${doctor.inHosDate}
								<input type="hidden" name="inHosDate" value="${doctor.inHosDate}"/>
							</c:if>
							<c:if test="${!docSub}">
								${formDataMap['inHosDate']}
								<input type="hidden" name="inHosDate" value="${formDataMap['inHosDate']}"/>
							</c:if>
						</td>
					</tr>
					<tr class="orgTr">
						<th class="orgTd" style="text-align: right;width: 80px;">
							总结时段：
						</th>
						<td class="orgTd" colspan="" style="width: 230px;">
							<c:if test="${docSub}">
								${result.schStartDate}至${result.schEndDate}，共${result.schMonth}月
								<input type="hidden" name="schStartDate" value="${result.schStartDate}"/>
								<input type="hidden" name="schEndDate" value="${result.schEndDate}"/>
								<input type="hidden" name="schMonth" value="${result.schMonth}"/>
							</c:if>
							<c:if test="${!docSub}">
								${formDataMap['schStartDate']}至${formDataMap['schEndDate']}，共${formDataMap['schMonth']}月
								<input type="hidden" name="schStartDate" value="${formDataMap['schStartDate']}"/>
								<input type="hidden" name="schEndDate" value="${formDataMap['schEndDate']}"/>
								<input type="hidden" name="schMonth" value="${formDataMap['schMonth']}"/>
							</c:if>
						</td>
						<th class="orgTd" style="text-align: right;width: 80px;">
							出勤情况：
						</th>
						<td class="orgTd" colspan="3" style="">
							<c:if test="${docSub}">
								满勤（<input style="width: 30px;"class="inputText validate[required,custom[integer,min[0]]" type="text"  name="manqin" value="${formDataMap['manqin']}"/>）天&#12288;&#12288;
								缺勤（<input style="width: 30px;"class="inputText validate[required,custom[integer,min[0]]]" type="text"  name="queqin" value="${formDataMap['queqin']}"/>）天
							</c:if>
							<c:if test="${!docSub}">
								满勤（${formDataMap['manqin']}）天&#12288;&#12288;
								缺勤（${formDataMap['queqin']}）天
								<input type="hidden" name="manqin" value="${formDataMap['manqin']}"/>
								<input type="hidden" name="queqin" value="${formDataMap['queqin']}"/>
							</c:if>
						</td>
					</tr>
				</table>
				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color:#459ae9;">个人总结</caption>
					<tr class="orgTr">
						<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
							<c:if test="${docSub}">
								<textarea name="personalSummary" placeholder="（主要反映《培训细则》中对本阶段的培训要求的完成情况）" class="validate[required]">${formDataMap['personalSummary']}</textarea>
							</c:if>
							<c:if test="${empty rec && !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
								暂无个人总结!
							</c:if>
							<c:if test="${!docSub}">
								&#12288;${formDataMap['personalSummary']}
								<input type="hidden" name="personalSummary" value="${formDataMap['personalSummary']}"/>
							</c:if>
						</td>
					</tr>
					<c:if test="${docSub}">
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								住院医师签名：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${operUser.userName}
								<input type="hidden" name="doctorSiginName" value="${operUser.userName}"/>
							</td>
						</tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								签名日期：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${pdfn:getCurrDate()}
								<input type="hidden" name="doctorSiginDate" value="${pdfn:getCurrDate()}"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${!docSub}">
						<tr style="width:30px"><td colspan="2">&#12288;</td></tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								住院医师签名：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${formDataMap['doctorSiginName']}
								<input type="hidden" name="doctorSiginName" value="${formDataMap['doctorSiginName']}"/>
							</td>
						</tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								签名日期：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${formDataMap['doctorSiginDate']}
								<input type="hidden" name="doctorSiginDate" value="${formDataMap['doctorSiginDate']}"/>
							</td>
						</tr>
					</c:if>
				</table>

				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER || !empty rec.auditStatusId}">
				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color:#459ae9;">带教意见</caption>
					<tr class="orgTr">
						<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
							<c:if test="${teaSub}">
								<textarea name="deptAppraise" class="validate[required]">${formDataMap['deptAppraise']}</textarea>
							</c:if>
							<c:if test="${!teaSub}">
								&#12288;${formDataMap['deptAppraise']}
								<input type="hidden" name="deptAppraise" value="${formDataMap['deptAppraise']}"/>
							</c:if>
						</td>
					</tr>
					<c:if test="${teaSub}">
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								带教老师签名：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${sessionScope.currUser.userName}
								<input type="hidden" name="teacherSiginName" value="${sessionScope.currUser.userName}"/>
							</td>
						</tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								签名日期：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${pdfn:getCurrDate()}
								<input type="hidden" name="teacherSiginDate" value="${pdfn:getCurrDate()}"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${!teaSub}">
						<tr style="width:30px"><td colspan="2>&#12288;</td></tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								带教老师签名：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${formDataMap['teacherSiginName']}
								<input type="hidden" name="teacherSiginName" value="${formDataMap['teacherSiginName']}"/>
							</td>
						</tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								签名日期：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${formDataMap['teacherSiginDate']}
								<input type="hidden" name="teacherSiginDate" value="${formDataMap['teacherSiginDate']}"/>
							</td>
						</tr>
					</c:if>
				</table>
				</c:if>

				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD || !empty rec.headAuditStatusId}">
					<input type="hidden" name="isAgree" value="${GlobalConstant.FLAG_Y}"/>
				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color:#459ae9;">
						教学主任意见
					</caption>
					<tr class="orgTr">
						<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
							<c:if test="${headSub}">
								<textarea name="deptHeadAutograth" class="validate[required]">${formDataMap['deptHeadAutograth']}</textarea>
							</c:if>
							<c:if test="${!headSub}">
								&#12288;${formDataMap['deptHeadAutograth']}
								<input type="hidden" name="deptHeadAutograth" value="${formDataMap['deptHeadAutograth']}"/>
							</c:if>
						</td>
					</tr>
					<c:if test="${headSub}">
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								教学主任签名：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${sessionScope.currUser.userName}
								<input type="hidden" name="headSiginName" value="${sessionScope.currUser.userName}"/>
							</td>
						</tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								签名日期：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${pdfn:getCurrDate()}
								<input type="hidden" name="headSiginDate" value="${pdfn:getCurrDate()}"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${!headSub}">
						<tr style="width:30px"><td colspan="2">&#12288;</td></tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								教学主任签名：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${formDataMap['headSiginName']}
								<input type="hidden" name="headSiginName" value="${formDataMap['headSiginName']}"/>
							</td>
						</tr>
						<tr>
							<td class="orgTd" colspan="2" style="height:30px;text-align: right;width: 80%">
								签名日期：
							</td>
							<td class="orgTd" colspan="2" style="height:30px;text-align: left">
									${formDataMap['headSiginDate']}
								<input type="hidden" name="headSiginDate" value="${formDataMap['headSiginDate']}"/>
							</td>
						</tr>
					</c:if>
				</table>
				</c:if>
	         </form>
	 		</div>

			<div style="text-align: center;margin-top: 5px;width: 90%;margin-left: auto;margin-right: auto;">
				<c:if test="${showMsg}">
					[<font color="red">带教老师还未审核，请等待！</font>]
				</c:if>
				<c:if test="${docSub || teaSub || headSub}">
					<input type="button" value="提&#12288;交" class="search" onclick="saveForm();"/>
				</c:if>
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && not empty rec.auditStatusId}">
					<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
				</c:if>
				<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
			</div>
			</div>
	      </div>
	      </div>
</body>
</html>