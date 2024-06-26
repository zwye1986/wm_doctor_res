<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
</jsp:include>
<script type="text/javascript">
	/**
	 * 对Date的扩展，将 Date 转化为指定格式的String
	 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
	 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
	 * eg:
	 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
	 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
	 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
	 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
	 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
	 */
	Date.prototype.pattern=function(fmt) {
		var o = {
			"M+" : this.getMonth()+1, //月份
			"d+" : this.getDate(), //日
			"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
			"H+" : this.getHours(), //小时
			"m+" : this.getMinutes(), //分
			"s+" : this.getSeconds(), //秒
			"q+" : Math.floor((this.getMonth()+3)/3), //季度
			"S" : this.getMilliseconds() //毫秒
		};
		var week = {
			"0" : "\u65e5",
			"1" : "\u4e00",
			"2" : "\u4e8c",
			"3" : "\u4e09",
			"4" : "\u56db",
			"5" : "\u4e94",
			"6" : "\u516d"
		};
		if(/(y+)/.test(fmt)){
			fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		}
		if(/(E+)/.test(fmt)){
			fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);
		}
		for(var k in o){
			if(new RegExp("("+ k +")").test(fmt)){
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
			}
		}
		return fmt;
	}
	function getNowFormatDate() {
		var date = new Date();
		return date.pattern("yyyy-MM-dd HH:mm:ss");
//		var seperator1 = "-";
//		var seperator2 = ":";
//		var month = date.getMonth() + 1;
//		var strDate = date.getDate();
//		var m=""+ month.toString();
//		if (parseInt(month)  <= 9) {
//			m = "0" + month.toString();
//		}
//		var d=""+strDate.toString();
//		if (parseInt(strDate) <= 9) {
//			d=strDate = "0" + strDate.toString();
//		}
//		var currentdate = date.getFullYear() + seperator1 + m + seperator1 + d
//				+ " " + date.getHours() + seperator2 + date.getMinutes()
//				+ seperator2 + date.getSeconds();
//		return currentdate;
	}
	var timer=setInterval(showButton,1000);
	function showButton()
	{
		var nottime=getNowFormatDate();
//		console.log(nottime);
		var maxTime="2017-03-26 00:00:00";
		if(nottime>=maxTime)
		{

			<%--<c:if test="${GlobalConstant.USER_LIST_GLOBAL ne sessionScope.userListScope}">--%>
			<%--$(".audit").hide();--%>
			<%--</c:if>--%>
			clearInterval(timer);
		}
	}


	$(document).ready(function(){
		clearInterval(timer);
		showButton();
		timer=setInterval(showButton,1000);
	});
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
		jboxPost("<s:url value='/jsres/manage/saveAudit'/>?flag="+flag , $("#submitForm").serialize() , function(resp){
			setTimeout(function(){
				window.parent.changeSpeMain();
				jboxClose();
			},1000);
		} , null , true);
	});
}
</script>
<div class="infoAudit">
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
				<c:if test="${trainCategoryEnumWMSecond.id != recruit.catSpeId}">
					<th>原培训类型：</th><td>${orgHistory.historyTrainingTypeName }</td>
					<th>变更后类型：</th><td>${orgHistory.trainingTypeName }</td>
				</c:if>
		 		<c:if  test="${trainCategoryEnumWMSecond.id eq recruit.catSpeId}">
					<th>当前培养类型：</th><td colspan="3">${recruit.catSpeName}</td>
				</c:if>
		 	</tr>
		 	<tr>
		 		<th>原培训专业：</th><td>${orgHistory.historyTrainingSpeName }</td>
		 		<th>变更后专业：</th><td>${orgHistory.trainingSpeName }</td>
		 	</tr>
		 	<tr>
		 		<th>培训届别：</th><td>${recruit.sessionNumber}</td>
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
		 			 <c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
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
		 	<c:if test="${orgHistory.changeStatusId != jsResChangeApplySpeEnumBaseWaitingAudit.id}">
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
	<div class="btn_info audit">
		<input type="button" style="width:110px;" onmouseout="" class="btn_blue" onclick="audit('${orgHistory.recordFlow}','${doctor.doctorFlow}','Y')" value="审核通过"></input>
		&nbsp;&nbsp;
		<input type="button" style="width:110px;" onmouseout="" class="btn_red" onclick="audit('${orgHistory.recordFlow}','${doctor.doctorFlow}','N')" value="审核不通过"></input>
	</div>
	</form>
</div>
