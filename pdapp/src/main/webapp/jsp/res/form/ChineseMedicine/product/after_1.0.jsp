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
.infoAudit h2{line-height:40px; border-bottom:2px solid #44b549;padding-left:10px; color:#44b549; text-indend:10px;font-size:14px;}
.orgTable{ margin:10px 0;border-collapse: collapse;}
.orgCaption,.orgTh,.orgTd{height:40px;}
.orgCaption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
.orgTh{text-align:right;font-weight:500;padding-right:5px; color:#333;}
.orgTd{text-align:left; padding-left:5px; color:#999;}
.orgTable a{ color:#00f;}
.btn{padding-left:22px; padding-right:22px;}
.blue-btn{ background:#44b549; border: 1px solid #3ea543; color:#fff;}
.blue-btn:hover{ background:#3ea543;}
.red-btn{background:#f90; border: 1px solid #f49200; color:#fff;}
.red-btn:hover{ background:#f60;}
.h-btn{background:#eee;border: 1px solid #ddd; }
.h-btn:hover{ background:#ddd;}
textarea{ width:100%; height:150px;padding:0; resize:none; outline:none;text-indent:28px; line-height:24px; font-family:'微软雅黑'; }
</style>

<script type="text/javascript">
	$(function(){
		$("#detail").slideInit({
			width:800,
			speed:500,
			outClose:true
		});
	});
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
	function loadDetail(){
		var url = "<s:url value='/res/rec/showRegistryFormNew'/>"+
		"?recFlow=${evaluation.recFlow}"+
		"&schDeptFlow=${param.schDeptFlow}"+
		"&rotationFlow=${param.rotationFlow}"+
		"&recTypeId=${evaluation.recTypeId}"+
		"&roleFlag=${param.roleFlag}";
		if ("${param.roleFlag}"=="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}" || "${param.isRead}"=="true") {
			url += "&openType=messager";
			var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='1100px' height='500px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
			top.jboxMessager(iframe,'出科考核表',1100,500);
		} else {
			url += "&isLoad=true";
			jboxStartLoading();
			jboxGet(url,null,function(resp){
				$("#detail").html(resp);
				$("#detail").rightSlideOpen();
				end();
			},function(){end();},false);
		}
	}
	
	function end(){
		jboxEndLoading();
	}
	//====================滑动=========================

	function saveForm(){
			if($("#summaryForm").validationEngine("validate")){
				jboxConfirm("确认提交?一旦提交将无法修改!",function(){
					jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$('#summaryForm').serialize(),function(){
						window.parent.frames['mainIframe'].window.$(".recTypeTag.active").click();
						jboxClose();
					},null,true);
				});
			}
	}
	
	function submit(){
		jboxConfirm("确认提交?",function(){
			jboxPost("<s:url value='/res/rec/opreResRec'/>",
					{
					"recFlow":"${rec.recFlow}",
					"statusId":"${recStatusEnumSubmit.id}",
					"recTypeId":"${rec.recTypeId}"
					},
					function(resp){
						window.parent.frames['mainIframe'].window.$(".recTypeTag.active").click();
					},null,true);
		});
	}
	
	function evaluation(){
		var url = "<s:url value='/res/rec/showRegistryFormNew'/>"+
				"?recFlow=${evaluation.recFlow}"+
				"&schDeptFlow=${param.schDeptFlow}"+
				"&rotationFlow=${param.rotationFlow}"+
				"&recTypeId=${evaluation.recTypeId}"+
				"&roleFlag=${param.roleFlag}";
		window.open(url,"_blank");
	}
	
	function audit(auditResult){
		if($("#summaryForm").validationEngine("validate")){
			jboxConfirm("确认提交?一旦提交将无法修改!",function(){
				var url = "<s:url value='/res/teacher/audit'/>?auditResult="+auditResult;
				jboxPost(url,$('#summaryForm').serialize(),function(resp){
					if(resp=="${GlobalConstant.OPRE_SUCCESSED}"){
// 					if(window.parent.frames["jbox-message-iframe"] == undefined){
// 						window.parent.frames["mainIframe"].window.$(".selectTag a").click();
// 					}else{
// 						window.parent.frames["jbox-message-iframe"].window.recReLoad();
// 					}
						<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
							window.parent.frames["mainIframe"].window.$(".selectTag a").click();
						</c:if>
						<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
							//back();
							window.parent.frames['mainIframe'].location.reload(true);
// 							location.reload(true);
						</c:if>
						jboxClose();
					}
				},null,true);
			},null);
		};
	}
	
	function goBack(){
		var parent = "${param.isView?'showView':'audit'}";
		location.href = "<s:url value='/res/teacher/"+parent+"/${param.roleFlag}'/>?currentPage=${param.currentPage}";
	}
</script>
</head>
<body>	
	<div class="mainright">
      <div class="content">
		<div class="infoAudit">
			<div id="printDiv">
			<div style="width: 90%;margin-right: auto;margin-left: auto;">
				<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
					姓名：${rec.operUserName}
				</c:if>
			</div>
			
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
				
				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color: #399ce0;">个人小结
					<%--	<c:if test="${!empty evaluation}">
							<!-- <input type="button" value="出科考核表" class="search" style="float: right;margin-bottom: 5px;" onclick="evaluation();"/> -->
							<input type="button" value="出科考核表" class="search" style="float: right;margin-bottom: 5px;" onclick="loadDetail();"/>
						</c:if>--%>
					</caption>
					<tr class="orgTr">
						<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
							<c:if test="${empty rec && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
								<textarea placeholder="不少于200字" name="personalSummary" class="validate[required]"></textarea>
							</c:if>
							<c:if test="${empty rec && !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
								暂无出科小结!
							</c:if>
							<c:if test="${!empty rec}">
								&#12288;${formDataMap['personalSummary']}
								<input type="hidden" name="personalSummary" value="${formDataMap['personalSummary']}"/>
							</c:if>
						</td>
					</tr>
				</table>
				<c:if test="${!empty rec}">
				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color: #399ce0;">带教老师评价</caption>
					<tr class="orgTr">
						<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
							<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty formDataMap['deptAppraise']}">
								<textarea name="deptAppraise" class="validate[required]"></textarea>
							</c:if>
							<c:if test="${!empty formDataMap['deptAppraise']}">
								&#12288;${formDataMap['deptAppraise']}
								<input type="hidden" name="deptAppraise" value="${formDataMap['deptAppraise']}"/>
							</c:if>
						</td>
					</tr>
				</table>
				
				<table class="orgTable" border="0" width="90%" cellspacing="0" cellpadding="0" style="margin-left: auto;margin-right: auto;">
					<caption class="orgCaption" style="color: #399ce0;">
						科主任签名&#12288;
						<label style="font-weight: normal;color: black;">
							<input type="checkbox" name="isAgree" 
							value="${GlobalConstant.FLAG_Y}" 
							<c:if test="${(empty formDataMap['deptHeadAutograth'] && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD) || (formDataMap['isAgree'] eq GlobalConstant.FLAG_Y)}">
								checked
							</c:if>
							<c:if test="${!empty formDataMap['deptHeadAutograth'] || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
								disabled
							</c:if>
							/>
							同意出科
							<c:if test="${!empty formDataMap['deptHeadAutograth']}">
								<input type="hidden" name="isAgree" value="${formDataMap['isAgree']}"/> 
							</c:if>
						</label>
						<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
				         <font color="red">(如同意出科请选中)</font>
				         </c:if>
				         <c:if test="${empty rec}">
					         <input type="hidden" name="isAgree" value="${formDataMap['isAgree']}"/>
				         </c:if>
					</caption>
					<tr class="orgTr">
						<td class="orgTd" colspan="4" style="padding-top:10px;padding-left:0;">
							<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty formDataMap['deptHeadAutograth']}">
								<textarea name="deptHeadAutograth" class="validate[required]"></textarea>
							</c:if>
							<c:if test="${!empty formDataMap['deptHeadAutograth']}">
								&#12288;${formDataMap['deptHeadAutograth']}
								<input type="hidden" name="deptHeadAutograth" value="${formDataMap['deptHeadAutograth']}"/>
							</c:if>
						</td>
					</tr>
				</table>
				</c:if>
			         	<div align="right" style="margin-top: 10px;width: 90%;margin-right: auto;margin-left: auto;">
					         	主任：
					         	<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD}">
									<input type="text" class="toggleView3 validate[required] inputText" name="head" style="width: 100px;" value="${empty formDataMap['head']?sessionScope.currUser.userName:formDataMap['head']}"/>
									<label style="display: none;" class="toggleView3">${formDataMap['head']}</label>
								</c:if>
								<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_HEAD }">
									<label>${formDataMap['head']}</label>
									<input type="hidden" name="head" value="${formDataMap['head']}"/>
								</c:if>		
					         	&#12288;日期：
					         	<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD}">
									<input type="text" class="toggleView3 inputText validate[required] ${GlobalConstant.RES_ROLE_SCOPE_DOCTOR} ctrlInput" name="date" value="${empty formDataMap['date']?pdfn:getCurrDate():formDataMap['date']}" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
									<label style="display: none;" class="toggleView3">${formDataMap['date']}</label>
								</c:if>
								<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_HEAD }">
									<label>${formDataMap['date']}</label>
									<input type="hidden" name="date" value="${formDataMap['date']}"/>
								</c:if>
					         </div>
	         </form>
			</div>
	         	<div style="text-align: center;margin-top: 5px;width: 90%;margin-left: auto;margin-right: auto;">
	         		<c:if test="${empty rec && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
	         			<input type="button" value="提&#12288;交" class="search" onclick="saveForm();"/>
	         		</c:if>
			         <c:if test="${!empty param.roleFlag && !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
						<c:if test="${!empty rec && (empty formDataMap['deptHeadAutograth'] && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty formDataMap['deptAppraise'])}">
						[<font color="red">带教老师还未审核，请等待！</font>]
						</c:if>
			         	<c:if test="${!empty rec && ((empty formDataMap['deptAppraise'] && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER) ||
			         	 (empty formDataMap['deptHeadAutograth'] && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD &&( not empty formDataMap['deptAppraise'])))}">
				         	<input type="button" value="提&#12288;交" class="search" onclick="audit('${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD?recStatusEnumHeadAuditY.id:recStatusEnumTeacherAuditY.id}');"/>
			         	</c:if>
			         	<c:if test="${!param.isManager}">
			         		<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
			         	</c:if>
			         </c:if>
			         <c:if test="${param.isManager}">
			         	<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
			         </c:if>
			         <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">

						 <!-- 提交之后才显示打印-->
						 <c:if test="${not empty rec}">
							 <input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
						 </c:if>
			         	<input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();"/>
			         </c:if>
		         </div>
	 		</div>
	         <div id="detail"></div>
	      </div>
	      </div>
</body>
</html>