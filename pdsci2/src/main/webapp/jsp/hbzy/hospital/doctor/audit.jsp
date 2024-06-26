<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('#graduationYear').datepicker({
		startDate:"${doctor.sessionNumber}",
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
function audit(recordFlow,doctorFlow,flag){
	var info = "通过";
	if(flag!="${GlobalConstant.FLAG_Y}"){
		info="不通过";
		if($("#admitNotice").val()==""){
			jboxTip("请填写审核意见！");
			return false;
		}
	}
	var graduationYear=$("#graduationYear").val();
	if(graduationYear<"${doctor.sessionNumber}"){
		jboxTip("结业考核年份不能小于当前届别！");
		return false;
	}
	jboxConfirm("确认"+info+"?",function(){
		jboxPost("<s:url value='/hbzy/manage/saveAudit'/>?flag="+flag , $("#submitForm").serialize() , function(resp){
			setTimeout(function(){
				window.parent.changeSpeMain();
				jboxClose();
			},1000);
		} , null , true);
	});
}
</script>
<div class="infoAudit" style="overflow: auto;max-height: 400px;">
<form id="submitForm">
	<div class="div_table">
	<input type="hidden" name="recordFlow" value="${orgHistory.recordFlow}"/>
	<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
		 <table border="0" cellpadding="0" cellspacing="0" class="base_info">
		 	<colgroup>
				<col width="15%"/>
				<col width="30%"/>
				<col width="15%"/>
				<col width="21%"/>
				<col width="19%"/>
			</colgroup>
		 	<tr>
		 		<th>姓名：</th><td>${doctor.doctorName}</td>
		 		<th>培训基地：</th><td>${orgHistory.historyOrgName}</td>
		 	</tr>
		 	<tr>
				<c:if test="${jszyTrainCategoryEnumWMSecond.id != recruit.catSpeId}">
					<th>原培训类型：</th><td>${orgHistory.historyTrainingTypeName }</td>
					<th>变更后类型：</th><td>${orgHistory.trainingTypeName }</td>
				</c:if>
		 		<c:if  test="${jszyTrainCategoryEnumWMSecond.id eq recruit.catSpeId}">
					<th>当前培养类型：</th><td colspan="3">${recruit.catSpeName}</td>
				</c:if>
		 	</tr>
		 	<tr>
		 		<th>原培训专业：</th><td>${orgHistory.historyTrainingSpeName }</td>
		 		<th>变更后专业：</th><td>${orgHistory.trainingSpeName }</td>
		 	</tr>
		 	<tr>
		 		<th>培训届别：</th><td>${doctor.sessionNumber}</td>
		 		<th>已培训时间：</th>
		 		<td> 
	 				 <c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}">
	                    <c:if test="${recruit.yetTrainYear eq dict.dictId}">
	                       ${dict.dictName }
	                    </c:if>
	                  </c:forEach>
               </td>
		 	</tr>
		 	<tr>
		 		<th>培养年限：</th>
		 		<td>
		 			 <c:forEach items="${jszyResTrainYearEnumList }" var="trainYear">
	                     <c:if test="${recruit.trainYear eq trainYear.id}">${trainYear.name }</c:if>
	                 </c:forEach>
		 		</td>
		 		<th>变更申请表：</th>
		 		<td>
		 			<c:if test="${not empty orgHistory.speChangeApplyFile }">
		 				[<a href="${sysCfgMap['upload_base_url']}/${orgHistory.speChangeApplyFile }" target="_blank">查看图片</a>]&nbsp;
		 			</c:if>
		 		</td>
		 	</tr>
	 		<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
		 		<tr>
					<th>结业考核年份：</th>		 			
		 			<td colspan="3">
		 				<input type="text" class="input" id=graduationYear name="graduationYear"value="${recruit.graduationYear}" class="input" readonly="readonly"style="width: 70px;height: 25px"/>
		 			</td>
		 		</tr>
		 	</c:if>
		 	<c:if test="${orgHistory.changeStatusId != jszyResChangeApplySpeEnumBaseWaitingAudit.id}">
			 	<tr>
			 		<th style="color: red">历史意见：</th>
			 		<td colspan="3" style="color: red">
			 			${orgHistory.auditOpinion }
			 		</td>
			 	</tr>
		 	</c:if>
		 	<tr>
	 		   <th colspan="6" style="text-align: left;">审核意见：<label style="color: red;">(学员变更专业，将作为培训基地不良信息记录在案,全科、儿科、精神等紧缺专业变更为其他专业，请严控)</label></th>
		 	</tr>
		 	<tr>
		 		<td colspan="6">
					<textarea class="xltxtarea" style=" margin: 0px; padding: 0px;border: 0px" id="admitNotice" name="auditOpinion"placeholder="请输入审核意见"></textarea>
               </td>
		 	</tr>
		 </table>
	</div>
	<div class="btn_info">
		<input type="button" style="width:110px;" onmouseout="" class="btn_brown" onclick="audit('${orgHistory.recordFlow}','${doctor.doctorFlow}','Y')" value="审核通过"></input>
		&nbsp;&nbsp;
		<input type="button" style="width:110px;" onmouseout="" class="btn_red" onclick="audit('${orgHistory.recordFlow}','${doctor.doctorFlow}','N')" value="审核不通过"></input>
	</div>
	</form>
</div>
