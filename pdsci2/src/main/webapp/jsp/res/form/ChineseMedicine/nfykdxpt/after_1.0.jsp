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
</style>

<script type="text/javascript">
function save(){
	if($("#summaryForm").validationEngine("validate")){
		jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$("#summaryForm").serialize(),function(resp){
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
				window.parent.document.mainIframe.location.reload();
			   jboxClose();
			}				
		},null,true);
	}
}
</script>
</head>
<body>	
	<div class="mainright">
      <div class="content">
      	<form id="summaryForm">
      		<input type="hidden" name="formFileName" value="${formFileName}"/>
			<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
			<input type="hidden" name="roleFlag" value="${roleFlag}"/>
			<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
			<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
			<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
			 <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					 <input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
			 </c:if>
		 	 <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
				 <input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
			 </c:if>
			<h1  style="font-size: 15px; text-align: center;">出科小结表</h1>
      		<label style="padding-left: 17px;">轮转科室：
				<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
					<c:if test="${empty rec.auditStatusId}">
						<input type="text" style="width: 135px;" id="department" class="inputText" name="department" value="${empty formDataMap['department']?schDept.schDeptName:formDataMap['department']}"/>
					</c:if>
					<c:if test="${!(empty rec.auditStatusId)}">
						<label>${schDept.schDeptName }</label>
					</c:if>
				</c:if>
				<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
					<input type="hidden" style="width: 135px;" id="department" class="inputText" name="department" value="${empty formDataMap['department']?schDept.schDeptName:formDataMap['department']}"/>
					<label>${schDept.schDeptName }</label>
				</c:if>
      		</label>
      		<label style="float: right;padding-right: 30px;">日期：
      			<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
      				<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
      					<input type="text" style="width: 135px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="dateTime" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['dateTime']}" class="inputText"/>
      				</c:if>
      				<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
						<label>${formDataMap['dateTime']}</label>
					</c:if>
      			</c:if>
      			<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
      				<input type="hidden" style="width: 135px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="dateTime" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['dateTime']}" class="inputText"/>
      				<label>${formDataMap['dateTime']}</label>
      			</c:if>
      		</label>
    		<table class="basic" width="100%" style="margin-top: 10px;">
    				<tr>
    					<td style="width: 10%;">姓名</td>
    					<td style="width: 20%;">
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
    							<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
    								<input type="text" name="name" style="width: 130px;" value="${empty formDataMap['name']?sessionScope.currUser.userName:formDataMap['name']}" class="inputText">
    							</c:if>
    							<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
    								<label>${formDataMap['name']}</label>
    							</c:if>
    						</c:if>
    						<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<input type="hidden" name="name" style="width: 130px;" value="${empty formDataMap['name']?sessionScope.currUser.userName:formDataMap['name']}" class="inputText">
    							<label>${formDataMap['name']}</label>
    						</c:if>
    					</td>
    					<td style="width: 11%;">所在医院</td>
    					<td style="width: 26%;">
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
	    						<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
	    							<input type="text" style="width: 170px;" name="hospital" value="${empty formDataMap['hospital']?doctor.orgName:formDataMap['hospital']}" class="inputText">
								</c:if>
								<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
    								<label>${formDataMap['hospital']}</label>
    							</c:if>
							</c:if>
							<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
								<input type="hidden" style="width: 170px;" name="hospital" value="${empty formDataMap['hospital']?doctor.orgName:formDataMap['hospital']}" class="inputText">
								<label>${formDataMap['hospital']}</label>
							</c:if>
    					</td>
    					<td style="width: 10%;">培训方向</td>
    					<td style="width: 23%;">
	    					<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
	    						<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
	    							<input type="text" style="width: 130px;" name="trainDirection" value="${formDataMap['trainDirection']}" class="inputText">
	    						</c:if>
	    						<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
    								<label>${formDataMap['trainDirection']}</label>
    							</c:if>
	    					</c:if>
	    					<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
	    					<input type="hidden" style="width: 130px;" name="trainDirection" value="${formDataMap['trainDirection']}" class="inputText">
	    						<label>${formDataMap['trainDirection']}</label>
	    					</c:if>
    					</td>
    				</tr>
    				<tr>
    					<td>年级</td>
    					<td>
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
    								<input type="text" style="width: 130px;" name="grade" value="${empty formDataMap['grade']?doctor.sessionNumber:formDataMap['grade']}" class="inputText">
    							</c:if>
    							<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
    								<label>${formDataMap['grade']}</label>
    							</c:if>
    						</c:if>
    						<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<input type="hidden" style="width: 130px;" name="grade" value="${empty formDataMap['grade']?doctor.sessionNumber:formDataMap['grade']}" class="inputText">
    							<label>${formDataMap['grade']}</label>
    						</c:if>
    					</td>
    					<td>轮转时间</td>
    					<td>
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
	    							<input type="text" name="cycleTimeStart" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['cycleTimeStart']?result.schStartDate:formDataMap['cycleTimeStart']}" style="width: 80px;" class="inputText">
	    						</c:if>
	    						<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
    								<label>${formDataMap['cycleTimeStart']}</label>
    							</c:if>
	    					</c:if>
    						<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<input type="hidden" name="cycleTimeStart" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['cycleTimeStart']?result.schStartDate:formDataMap['cycleTimeStart']}" style="width: 80px;" class="inputText">
    							<label>${formDataMap['cycleTimeStart']}</label>
    						</c:if>
    						
    						<c:if test="${!empty formDataMap['cycleTimeStart'] && !empty formDataMap['cycleTimeEnd']}">
    							-
    						</c:if>
    						
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
    								<input type="text" name="cycleTimeEnd" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['cycleTimeEnd']?result.schEndDate:formDataMap['cycleTimeEnd']}" style="width: 80px;" class="inputText">
    							</c:if>
    							<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
    								<label>${formDataMap['cycleTimeEnd']}</label>
    							</c:if>
    						</c:if>
    						<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<input type="hidden" name="cycleTimeEnd" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['cycleTimeEnd']?result.schEndDate:formDataMap['cycleTimeEnd']}" style="width: 80px;" class="inputText">
    							<label>${formDataMap['cycleTimeEnd']}</label>
    						</c:if>
    					</td>
    					<td>出勤情况</td>
    					<td>
    						请假
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
	    						<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">		
    								<input type="text" name="leaveDays" value="${formDataMap['leaveDays']}" style="width: 50px;" class="inputText">
    							</c:if>
    							<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
    								<label>${formDataMap['leaveDays']}</label>
    							</c:if>
    						</c:if>
    						<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<input type="hidden" name="leaveDays" value="${formDataMap['leaveDays']}" style="width: 50px;" class="inputText">
    							<label>${formDataMap['leaveDays']}</label>
    						</c:if>
    						天<br>
    						脱岗
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">	
    								<input type="text" name="offDutyDays" value="${formDataMap['offDutyDays']}" style="width: 50px;" class="inputText">
    							</c:if>
    							<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
    								<label>${formDataMap['offDutyDays']}</label>
    							</c:if>
    						</c:if>
    						<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<input type="hidden" name="offDutyDays" value="${formDataMap['offDutyDays']}" style="width: 50px;" class="inputText">
    							<label>${formDataMap['offDutyDays']}</label>
    						</c:if>
    						天
    					</td>
    				</tr>
    				<tr>
    					<td colspan="6">轮转时间要求：
    					<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
			    			<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">			
    							 <input type="text" name="requireDay" value="${formDataMap['requireDay']}" class="inputText"/>
    						</c:if>
    						<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
   								<label>${formDataMap['requireDay']}</label>
   							</c:if>
    					</c:if>
    					<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    						<input type="hidden" name="requireDay" value="${formDataMap['requireDay']}" class="inputText"/>
    						<label>${formDataMap['requireDay']}</label>
    					</c:if>
    					个月</td>
    				</tr>
    				<tr>
    					<td colspan="6">
    						文献、综述、读书报告学习及撰写情况：
	    					<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
	    						<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
	    							<textarea class="xltxtarea" name="situation">${formDataMap['situation']}</textarea>
	    						</c:if>
	    						<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
   									<div style="height: 100px;">${formDataMap['situation']}</div>
   								</c:if>
	    					</c:if>
	    					<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
	    						<textarea style="display: none;" class="xltxtarea" name="situation">${formDataMap['situation']}</textarea>
	    						<div style="height: 100px;">${formDataMap['situation']}</div>
	    					</c:if>
    					</td>
    				</tr>
    				<tr>
    					<td colspan="6">
    						本次轮转的收获与不足：
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
    								<textarea class="xltxtarea" name="epitome">${formDataMap['epitome']}</textarea>
    							</c:if>
    							<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
   									<div style="height: 100px;">${formDataMap['epitome']}</div>
   								</c:if>
    						</c:if>
    						<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<textarea style="display: none;" class="xltxtarea" name="epitome">${formDataMap['epitome']}</textarea>
    							<div style="height: 100px;">${formDataMap['epitome']}</div>
    						</c:if>
    						<div style="float: right;">本人签名：
    						<c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<c:if test="${empty rec.auditStatusId && empty rec.headAuditStatusId }">
    								<input type="text" name="doctorName"  value="${empty formDataMap['doctorName']?sessionScope.currUser.userName:formDataMap['doctorName']}" class="validate[required] inputText"/>
    							</c:if>
    							<c:if test="${!(empty rec.auditStatusId && empty rec.headAuditStatusId)}">
   									<label>${formDataMap['doctorName']}</label>
   								</c:if>
    						</c:if>
    						<c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
    							<input type="hidden" name="doctorName"  value="${empty formDataMap['doctorName']?sessionScope.currUser.userName:formDataMap['doctorName']}" class="validate[required] inputText"/>
    							<label>${formDataMap['doctorName']}</label>
    						</c:if>
    							 &#12288;</div>
    					</td>
    				</tr>
				    <c:if test="${param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD}">
	    				<tr>
	    					<td colspan="6">
									<label style="font-weight: normal;color: black;">
									<c:if test="${empty rec.headAuditStatusId}">
										<input type="checkbox" name="isAgree" 
										value="${GlobalConstant.FLAG_Y}" 
										checked="checked"
										/>
										同意出科
									</c:if>
									<c:if test="${!empty rec.headAuditStatusId}">
										<c:if test="${!(formDataMap['isAgree'] eq GlobalConstant.FLAG_Y)}">
											不同意出科
										</c:if> 
										<c:if test="${(formDataMap['isAgree'] eq GlobalConstant.FLAG_Y)}">
											同意出科
										</c:if> 
										<input type="hidden" name="isAgree" value="${formDataMap['isAgree']}"/>
										
									</c:if>
									
								</label>
	    					</td>
	    				</tr>
				    </c:if>
    		</table>
   				<p align="center" style="margin-top: 10px;">
					<c:if test="${empty rec.headAuditStatusId && param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.auditStatusId}">
						[<font color="red">带教老师还未审核，请等待！</font>]
					</c:if>
   					<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && empty rec.headAuditStatusId && empty rec.auditStatusId) ||
   					(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) ||
   					(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId))}">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
      </div>
    </div>
</body>
</html>