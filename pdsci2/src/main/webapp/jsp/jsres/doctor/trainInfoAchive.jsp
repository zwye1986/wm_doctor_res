<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and param.studyFlag eq GlobalConstant.FLAG_Y && doctorRecruit.orgFlow eq currUser.orgFlow}">
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
</c:if>

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
//	var timer=setInterval(showButton,1000);



	$(document).ready(function(){
//		clearInterval(timer);
//		showButton();
//		timer=setInterval(showButton,1000);
	});


	$(function(){
		$("#file").live("change",function(){
			uploadFile();
		});
		var tabCourse = $('.icon-head');
		var tab=$(".icon");
		//tabCourse.on('click',function(e){e.stopPropagation();});
		tabCourse.on('mouseover',function(){
			$("#changInfo").show();$("#changSpe").hide();
			<c:if test="${param.openType=='open'}">
			$("#changInfo").css("right", "180px");//箭头右偏移
			</c:if>
		});
		tab.on('mouseover',function(){
			$("#changSpe").show();$("#changInfo").hide();
			<c:if test="${param.openType=='open'}">
			$("#changSpe").css("right", "180px");//箭头右偏移
			</c:if>
		});
		$(document).on('click',function(){$("#changInfo").hide();});
		$(document).on('click',function(){$("#changSpe").hide();});
		showUploadFileTr();
		$("#returnNotice").hide();
	});

	function showUploadFileTr(){
		var trainYear = "${doctorRecruit.trainYear}";
		if("" == trainYear || "${jsResTrainYearEnumThreeYear.id}" == trainYear){
			$("#proveFileUrlTr").hide();
		}else{
			var catSpeId = "${doctorRecruit.catSpeId}";
			if("${trainCategoryEnumDoctorTrainingSpe.id}" == catSpeId){
				$("#proveFileUrlTr").show();
			}else{
				$("#proveFileUrlTr").hide();
			}
		}
	}


</script>
<style>
	.icon-head{ /* vertical-align: middle; */}
	.changeinfo{position:absolute; background-color:#fff; padding:10px; border:1px solid  #dcdcdc; width:550px;}
	.changeinfoContent{position:absolute; background-color:#fff; padding:10px; border:1px solid  #dcdcdc; width:650px;}
	.icon_up{background-image:url("<s:url value='/css/skin/${skinPath}/images/up2.png'/>"); background-repeat:no-repeat; background-position: top center; padding:5px;position: absolute;top: -6px;left: 225px;}
	.xllist caption{padding-bottom: 10px;font-weight: bold;font-size: 15px;color: #3d91d5;}
	.pxxx{position: relative;top:30px; right: 175px;}
	.pyyy{position: relative;top:30px; right: 175px;}
</style>
<!-- <div class="infoAudit"> -->
<div>
	<div class="${'open' eq param.openType?'infoAudit':'main_bd' }">
		<div class="div_table">
			<!-- 		 <input type="hidden" id="proveFileUrlFlag"/> -->
			<!-- 	     <input type="hidden" id="completeFileUrlFlag"/> -->
			<input type="hidden" id="upFileId"/>
			<h4>培训信息</h4>
			<c:set var="auditNotPassed" value="${jsResDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}"/>
			<form id="infoForm" style="position: relative;" method="post">
				<table border="0" cellpadding="0" cellspacing="0" class="base_info">
					<colgroup>
						<col width="15%"/>
						<col width="30%"/>
						<col width="15%"/>
						<col width="40%"/>
					</colgroup>
					<tbody>
					<tr>
						<th>当前学位类别：</th>
						<td>${doctorRecruit.currDegreeCategoryName }</td>
						<th style="${auditNotPassed?'color: red':'' }">审核状态：</th>
						<c:set var="modifyTime" value="${pdfn:transDateTime(doctorRecruit.modifyTime)}"></c:set>
						<c:if test="${jsResDoctorAuditStatusEnumNotSubmit.id eq doctorRecruit.auditStatusId
											or jsResDoctorAuditStatusEnumAuditing.id eq doctorRecruit.auditStatusId}">
							<td class="${auditNotPassed?'red':'' }">${doctorRecruit.auditStatusName}</td>
						</c:if>
						<c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId
											or jsResDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}">
							<td class="${auditNotPassed?'red':'' }">${doctorRecruit.auditStatusName}<label>&#12288;${modifyTime}</label></td>
						</c:if>
					</tr>
					<tr>
						<th>规培起始日期：</th>
						<td>${doctorRecruit.recruitDate}</td>
						<th>届别：</th>
						<td>${doctorRecruit.sessionNumber }</td>
					</tr>
					<tr>
						<th>所在地区：</th>
						<td>${doctorRecruit.placeName}</td>
						<th>
							<!--培训记录 ： 最新 && 审核通过 -->
							<c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId}">
								<!-- 变更申请记录 -->
								<c:if test="${!empty docOrgHistoryList}">
									<div class="pxxx" id="changInfo" style="display:none; ">
										<div class="changeinfo" id="changeinfoContent">
											<i class="icon_up"></i>
											<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
												<caption>变更基地申请记录</caption>
												<thead>
												<tr>
													<th style="text-align: center;">转入前培训基地</th>
													<th style="text-align: center;">申请转入培训基地</th>
													<th style="text-align: center;">审核状态</th>
													<th style="text-align: center;">审核意见</th>
													<th style="text-align: center;">审核时间</th>
												</tr>
												</thead>
												<tbody>
												<c:forEach items="${docOrgHistoryList }" var="docOrgHistory">
													<tr>
														<td style="text-align: center;">${docOrgHistory.historyOrgName}</td>
														<td style="text-align: center;">${docOrgHistory.orgName}</td>
														<td style="text-align: center;">${jsResChangeApplyStatusEnumInApplyWaiting.id == docOrgHistory.changeStatusId?'转出审核通过':docOrgHistory.changeStatusName}</td>
														<td style="text-align: center;"><label title="${docOrgHistory.auditOpinion}">${pdfn:cutString(docOrgHistory.auditOpinion,10,true,3) }</label></td>
														<td style="text-align: center;">
															<c:if test="${!empty docOrgHistory.inDate }">${pdfn:transDate(docOrgHistory.inDate)}</c:if>
															<c:if test="${empty docOrgHistory.inDate }">${pdfn:transDate(docOrgHistory.outDate)}</c:if>
														</td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<img id="changeIcon" class="icon-head" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
								</c:if>
							</c:if>
							培训基地：
						</th>
						<td>
							${doctorRecruit.orgName}
							<!--培训记录 ： 最新 && 审核通过 -->
							<c:if test="${isLatest && jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId && not empty doctorRecruit.orgFlow}">
								<!-- 最新变更记录：  (非待转出审核 && 非待转入审核) && 当前用户-->
								<c:set var="showChangBtn" value="${(jsResChangeApplyStatusEnumOutApplyWaiting.id != latestDocOrgHistory.changeStatusId && jsResChangeApplyStatusEnumInApplyWaiting.id != latestDocOrgHistory.changeStatusId ) && doctorRecruit.doctorFlow == sessionScope.currUser.userFlow}"/>
								<c:if test="${showChangBtn}">
									&nbsp;<a id="changeButton" onclick="validateChange('${jsResChangeTypeEnumBaseChange.id}')" class="btn">变更基地申请</a>
								</c:if>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>培训类别：</th>
						<td id="catSpeNameTd">${doctorRecruit.catSpeName}</td>
						<th class="trainSpe">
							<c:if test="${ jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId}">
								<c:if test="${!empty changeSpeList}">
									<div class="pyyy" id="changSpe" style="display:none; ">
										<div class="changeinfoContent">
											<i class="icon_up"></i>
											<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
												<caption>变更专业申请记录</caption>
												<thead>
												<tr>
													<th style="text-align: center;">变更前培训类型</th>
													<th style="text-align: center;">变更后培训类型</th>
													<th style="text-align: center;">变更前专业</th>
													<th style="text-align: center;">变更后专业</th>
													<th style="text-align: center;">审核状态</th>
													<th style="text-align: center;">审核时间</th>
													<th style="text-align: center;">审核意见</th>
												</tr>
												</thead>
												<tbody>
												<c:forEach items="${changeSpeList }" var="changeSpe">
													<tr>
														<td style="text-align: center;">${changeSpe.historyTrainingTypeName}</td>
														<td style="text-align: center;">${changeSpe.trainingTypeName}</td>
														<td style="text-align: center;">${changeSpe.historyTrainingSpeName}</td>
														<td style="text-align: center;">${changeSpe.trainingSpeName}</td>
														<td style="text-align: center;">${changeSpe.changeStatusName}</td>
														<td style="text-align: center;">
															<c:if test="${!empty changeSpe.inDate }">${pdfn:transDate(changeSpe.inDate)}</c:if>
															<c:if test="${empty changeSpe.inDate }">${pdfn:transDate(changeSpe.outDate)}</c:if>
														</td>
														<td style="text-align: center;"><label title="${changeSpe.auditOpinion}">${pdfn:cutString(changeSpe.auditOpinion,10,true,3) }</label></td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<img id="changeIcon" class="icon" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
								</c:if>
							</c:if>
							培训专业：</th>
						<td id="speNameTd">
							${doctorRecruit.speName}
							<c:if test="${isLatest && jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId && not empty doctorRecruit.orgFlow}">
								<c:set var="showChangBtn" value="${(jsResChangeApplySpeEnumBaseWaitingAudit.id != lastChangeSpe.changeStatusId
    		          		&& jsResChangeApplySpeEnumGlobalWaitingAudit.id != lastChangeSpe.changeStatusId)
    		          		&& doctorRecruit.doctorFlow == sessionScope.currUser.userFlow}"/>
								<c:if test="${showChangBtn}">
									&nbsp;<a id="changeSpeButton" onclick="validateChange('${jsResChangeTypeEnumSpeChange.id}')" class="btn">变更专业申请</a>
								</c:if>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>培训年限：</th>
						<td>
							<c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
								<c:if test="${doctorRecruit.trainYear eq trainYear.id}">${trainYear.name }</c:if>
							</c:forEach>
						</td>
						<th>已培训年限：</th>
						<td>
							<c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}">
								<c:if test="${doctorRecruit.yetTrainYear eq dict.dictId}">
									${dict.dictName }
								</c:if>
							</c:forEach>
						</td>
					</tr>

					<tr id="proveFileUrlTr" style="display:none;">
						<th>减免培训年限证明：</th>
						<td colspan="3">
							<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and param.studyFlag==GlobalConstant.FLAG_Y}">
								<c:if test="${doctorRecruit.orgFlow eq currUser.orgFlow}">
								<span id="proveFileUrlSpan" style="display:${!empty doctorRecruit.proveFileUrl?'':'none'} ">
									[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]&nbsp;
								</span>
									<a id="proveFileUrl" href="javascript:chooseFile('proveFileUrl');" class="btn">${empty doctorRecruit.proveFileUrl?'':'重新'}上传</a>&nbsp;
									<a id="proveFileUrlDel" href="javascript:delFile('proveFileUrl');" class="btn" style="${empty doctorRecruit.proveFileUrl?'display:none':''}">删除</a>
									<input type="hidden" id="proveFileUrlValue" name="proveFileUrl" value="${doctorRecruit.proveFileUrl}"/>
								</c:if>
								<c:if test="${!(doctorRecruit.orgFlow eq currUser.orgFlow)}">
									<c:if test="${!empty doctorRecruit.proveFileUrl}">
										[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope||param.auditFlag eq  GlobalConstant.FLAG_Y|| param.info eq GlobalConstant.FLAG_Y|| param.change eq GlobalConstant.FLAG_Y}">
								<c:if test="${!empty doctorRecruit.proveFileUrl}">
									[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]
								</c:if>
							</c:if>
						</td>
					</tr>

					<tr>
						<th>医师状态：</th>
						<td>${doctorRecruit.doctorStatusName}</td>
						<th>医师走向：</th>
						<td>${doctorRecruit.doctorStrikeName}</td>
					</tr>
					<tr>
						<th>结业考核年份：</th>
						<td colspan="3">${doctorRecruit.graduationYear}&#12288;<font color="red">(结业考核年份=当前届别+培训年限)</font></td>
					</tr>
					<!-- 审核通过 && 非二阶段 -->
					<c:if test="${doctorRecruit.auditStatusId eq jsResDoctorAuditStatusEnumPassed.id && not empty doctorRecruit.completeFileUrl && not empty doctorRecruit.completeCertNo}">
						<tr>
							<th>结业证书附件：</th>
							<td>
								<c:if test="${not empty doctorRecruit.completeFileUrl }">
									[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.completeFileUrl}" target="_blank">查看图片</a>]&nbsp;
								</c:if>
							</td>
							<th>结业证书编号：</th>
							<td>
									${doctorRecruit.completeCertNo}
							</td>
						</tr>
					</c:if>

					<!-- 审核意见 -->
					<%-- 	           <c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId or auditNotPassed}"> --%>
					<c:if test="${not empty doctorRecruit.admitNotice}">
						<tr>
							<th style="color: red;"><c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope}">历史</c:if>审核意见：</th>
							<td colspan="3" class="red">${doctorRecruit.admitNotice}</td>
						</tr>
					</c:if>
					<%-- 	           </c:if> --%>

					</tbody>
				</table>
			</form>
			<c:if test="${!(param.studyFlag eq GlobalConstant.FLAG_Y)}">
				<div>
					<h4>诚信声明</h4>
					<table border="0" cellpadding="0" cellspacing="0" width="100%" class="base_info">
						<colgroup>
							<col width="15%"/>
							<col width="85%"/>
						</colgroup>
						<tr>
							<th>诚信声明：</th>
							<td>
								<c:if test="${not empty doctorRecruit.doctorAuth}">
									[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.doctorAuth}" target="_blank">查看图片</a>]
								</c:if>
								<c:if test="${empty doctorRecruit.doctorAuth}">
									暂未提交
								</c:if>
							</td>
						</tr>
					</table>
				</div>
			</c:if>
			<c:if test="${GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope && doctorRecruit.doctorStatusId eq '21'}">
				<form id="editForm" style="position: relative;" method="post">
					<input type="hidden" name="recruitFlow" value="${doctorRecruit.recruitFlow}"/>
					<div>
						<h4>结业信息</h4>
						<table border="0" cellpadding="0" cellspacing="0" width="100%" class="base_info">
							<colgroup>
								<col width="15%"/>
								<col width="30%"/>
								<col width="15%"/>
								<col width="40%"/>
							</colgroup>
							<tr>
								<th>
									<c:if test="${doctorRecruit.catSpeId eq trainCategoryEnumWMSecond.id}">
										专培结业证书附件：
									</c:if>
									<c:if test="${doctorRecruit.catSpeId ne trainCategoryEnumWMSecond.id}">
										住培结业证书附件：
									</c:if>
								</th>
								<td>
									<input type="hidden" id="specialFileUrlValue" name="specialFileUrl" value="${doctorRecruit.specialFileUrl}"/>
									<span id="specialFileUrlSpan" style="display:${!empty doctorRecruit.specialFileUrl?'':'none'}">
										[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.specialFileUrl}" target="_blank">查看图片</a>]&nbsp;
									</span>
									&nbsp;
								</td>
								<th>
									<c:if test="${doctorRecruit.catSpeId eq trainCategoryEnumWMSecond.id}">
										专培结业证书编号：
									</c:if>
									<c:if test="${doctorRecruit.catSpeId ne trainCategoryEnumWMSecond.id}">
										住培结业证书编号：
									</c:if>
								</th>
								<td>
									<input name="specialCertNo" value="${doctorRecruit.specialCertNo}"  class="input"/>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</c:if>
			<c:if test="${GlobalConstant.USER_LIST_PERSONAL ne sessionScope.userListScope && doctorRecruit.doctorStatusId eq '21'}">
				<div>
					<h4>结业信息</h4>
					<table border="0" cellpadding="0" cellspacing="0" width="100%" class="base_info">
						<colgroup>
							<col width="15%"/>
							<col width="30%"/>
							<col width="15%"/>
							<col width="40%"/>
						</colgroup>
						<tr>
							<th>
								<c:if test="${doctorRecruit.catSpeId eq trainCategoryEnumWMSecond.id}">
									专培结业证书附件：
								</c:if>
								<c:if test="${doctorRecruit.catSpeId ne trainCategoryEnumWMSecond.id}">
									住培结业证书附件：
								</c:if>
							</th>
							<td>
								<span style="display:${!empty doctorRecruit.specialFileUrl?'':'none'}">
									[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.specialFileUrl}" target="_blank">查看图片</a>]&nbsp;
								</span>
							</td>
							<th>
								<c:if test="${doctorRecruit.catSpeId eq trainCategoryEnumWMSecond.id}">
									专培结业证书编号：
								</c:if>
								<c:if test="${doctorRecruit.catSpeId ne trainCategoryEnumWMSecond.id}">
									住培结业证书编号：
								</c:if>
							</th>
							<td>
									${doctorRecruit.specialCertNo}
							</td>
						</tr>
					</table>
				</div>
			</c:if>
			<div style="display: none" id="msg">
				<%--<font color="red" >${sessionScope.reason}<br/>${sessionScope.reasonYj }</font>--%>
			</div>
		</div>
	</div>
</div>
<!--  用于显示的content -->
<!-- <div id="returnData" style="display: none;"> -->
<!-- 	<label style="font-size: 5;margin: 5px">退回意见：</label> -->
<!-- 	<textarea id="admitNotice" name="admitNotice" style="margin: 5px;width: 338px" class="validate[required] xltxtarea"></textarea> -->
<%-- 	<input type="button" style="width:80px;margin-left: 130px"class="btn_blue" onclick="queding('${doctorRecruit.recruitFlow}')" value="确定"></input> --%>
<!-- </div> -->
<!--  </div> -->