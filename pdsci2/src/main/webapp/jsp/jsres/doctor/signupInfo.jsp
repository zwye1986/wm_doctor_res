
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
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
//			$("#changeSpeButton").hide();
			clearInterval(timer);
		}
	}


	$(document).ready(function(){
		clearInterval(timer);
		showButton();
		timer=setInterval(showButton,1000);
	});
	function submitRecruit(recruitFlow){
		var url = "<s:url value='/jsres/doctor/checkUserInfoInBlack'/>?userCode=${user.userCode}&userEmail=${user.userEmail}&userFlow=${user.userFlow}&userPhone=${user.userPhone}&idNo=${user.idNo}";
		jboxPost(url, null, function(resp){
			var msg1=resp.split("&pdnpc;");
			if("${GlobalConstant.USER_INFO_IN_BLACK}"== msg1[2])
			{
				$("#msg").html("<font color='red' >"+msg1[0]+"<br/>"+msg1[1]+"</font>");
				$("#msg").show();
//				return false;
			}else{
				jboxConfirm("确认提交？提交后培训信息将不可修改",  function(){
					$("#submitBtn").attr("disabled",true);
					var url = "<s:url value='/jsres/doctor/submitResDoctorRecruit'/>?auditStatusId=${jsResDoctorAuditStatusEnumAuditing.id}&recruitFlow="+recruitFlow;
					jboxPost(url, null, function(resp){
						if("${GlobalConstant.OPRE_FAIL}" != resp){
							jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
							setTimeout(function(){
								main("${doctorRecruit.recruitFlow}");
							}, 1000);
						}
					}, null, false);
				});
			}
		}, null, false);
		<%--if("${user.idNo}"=="320123199304203428")--%>
		<%--{--%>
		<%--$("#msg").show();--%>
		<%--return false;--%>
		<%--}--%>

	}
	function save(recruitFlow){
		jboxConfirm('确认保存?',function(){
			jboxPost("<s:url value='/jsres/doctorRecruit/saveTrainInfo'/>?recruitFlow="+recruitFlow , $("#infoForm").serialize() , function(resp){
				setTimeout(function(){
					jboxClose();
				},1000);
			} , null , true);
		});
	}
	function delDoctorRecruit(recruitFlow){
		jboxConfirm("确认删除这条培训信息?",  function(){
			var url= "<s:url value='/jsres/doctorRecruit/delDoctorRecruit'/>?recruitFlow="+recruitFlow;
			jboxPost(url, null, function(resp){
				if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
					main('');
				}
				top.jboxTip(resp);
			});
		});
	}

	function resetStatus(recruitFlow,doctorFlow,flag){
		var info = "修改";
		var auditStatusId = "${jsResDoctorAuditStatusEnumNotPassed.id}";
		if(flag=='Y'){
			info="基地重新审核";
			auditStatusId = "${jsResDoctorAuditStatusEnumAuditing.id}";
		}
		jboxConfirm("确认将该学员退回"+info+"?",  function(){
			var url = "<s:url value='/jsres/doctor/backResDoctorRecruit'/>?recruitFlow="+recruitFlow +"&auditStatusId=" + auditStatusId+"&doctorFlow="+doctorFlow;
			jboxPost(url, null, function(resp){
				if("${GlobalConstant.OPRE_FAIL}" != resp){
					window.parent.toPage();
					jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					setTimeout(function(){
						jboxClose();
					}, 1000);
				}
			}, null, false);
		});
	}
	function chargeResetOrg(recruitFlow,doctorFlow){
		if($("#returnNotice").is(":hidden")){
			$("#returnNotice").show();
			$('#indexBody').scrollTop( $('#indexBody')[0].scrollHeight );
		}else{
			var admitNotice=$("#admitNotice").val();
			if(""==$.trim(admitNotice)){
				jboxTip("请填写退回意见！");return false;
			}
			var auditStatusId="${jsResDoctorAuditStatusEnumAuditing.id}";
			jboxConfirm("确认将该学员退回重审?",  function(){
				var url = "<s:url value='/jsres/doctor/backResDoctorRecruit'/>?autitType=1&doctorFlow="+doctorFlow+"&auditStatusId="+auditStatusId+"&recruitFlow="+recruitFlow ;
				jboxPost(url, {"admitNotice":admitNotice}, function(resp){
					if("${GlobalConstant.OPRE_FAIL}" != resp){
						window.parent.toPage();
						jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
						setTimeout(function(){
							jboxClose();
						}, 1000);
					}
				}, null, false);
			});
		}
	}
	function chargeResetPerson(recruitFlow,doctorFlow){
		if($("#returnNotice").is(":hidden")){
			$("#returnNotice").show();
		}else{
			var admitNotice=$("#admitNotice").val();
			if(""==$.trim(admitNotice)){
				jboxTip("请填写退回意见！");return false;
			}
			var auditStatusId="${jsResDoctorAuditStatusEnumNotPassed.id}";
			jboxConfirm("确认将该学员退回修改?",  function(){
				var url = "<s:url value='/jsres/doctor/backResDoctorRecruit'/>?autitType=2&doctorFlow="+doctorFlow+"&auditStatusId="+auditStatusId+"&recruitFlow="+recruitFlow ;
				jboxPost(url,  {"admitNotice":admitNotice}, function(resp){
					if("${GlobalConstant.OPRE_FAIL}" != resp){
						window.parent.toPage();
						jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
						setTimeout(function(){
							jboxClose();
						}, 1000);
					}
				}, null, false);
			});
		}
// 	jboxOpenContent2($("#returnData").html(),"提示",350,230,true);
	}
	function applyChangeBase(){
		var url = "<s:url value='/jsres/doctor/applyChangeBase'/>?doctorFlow=${doctorRecruit.doctorFlow}&recruitFlow=${doctorRecruit.recruitFlow}&catSpeId=${doctorRecruit.catSpeId}&speId=${doctorRecruit.speId}&orgFlow=${doctorRecruit.orgFlow}";
		jboxOpen(url, "申请变更培训基地", 600, 320);
	}

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

	function uploadFile2(type,typeName) {
		var url = "<s:url value='/jsres/doctor/uploadFile'/>?userFlow=${user.userFlow}&operType="+type;
		jboxOpen(url, "上传"+typeName, 525, 200);
	}

	/**
	 * 审核
	 */
	function saveAuditRecruit(recruitFlow, doctorFlow, auditFlag){
		var admitNotice = $("#admitNotice").val().trim();
		var	title = "通过";
		var	auditStatusId = "${jsResDoctorAuditStatusEnumPassed.id}";
		if("${GlobalConstant.FLAG_N}" == auditFlag){
			title = "不通过";
			auditStatusId = "${jsResDoctorAuditStatusEnumNotPassed.id}";
			if("" == admitNotice){
				jboxTip("请填写审核意见！");
				return false;
			}
		}
		var data = {
			recruitFlow : recruitFlow,
			doctorFlow : doctorFlow,
			auditStatusId : auditStatusId,
			admitNotice : admitNotice
		};
		jboxConfirm("确认审核"+title+"?" ,  function(){
			var url = "<s:url value='/jsres/manage/province/doctor/saveAuditRecruit'/>";
			jboxPost(url, data, function(resp){
				if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
					window.parent.recruitAuditSearch();
					setTimeout(function(){
						jboxClose();
					},1000);
				}
			}, null , true);
		});
	}
	function chooseFile(id){
		$("#upFileId").val(id);
		return $("#file").click();
	}
	function uploadFile(){
		if(false == $("#fileForm").validationEngine("validate")){
			return false;
		}
		jboxStartLoading();
		var checkResult = checkFile($("#file")[0]);
		if(!checkResult){
			jboxEndLoading();
			return false;
		}
		var url = "<s:url value='/jsres/doctor/uploadTrainYearFile'/>";
		jboxSubmit($("#fileForm"),url,function(resp){
			if("${GlobalConstant.UPLOAD_FAIL}" != resp){
				jboxEndLoading();
				var index = resp.indexOf("/");
				if(index != -1){
					returnUrl(resp);
				}else{//验证文件信息
					jboxInfo(resp);
				}
			}
		}, null, false);
	}

	/**
	 * 检查文件格式
	 */
	function checkFile(file){
		var filePath = file.value;
		var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");
		var regStr = "/";
		for(var i = 0 ;i<types.length;i++){
			if(types[i]){
				if(i==(types.length-1)){
					regStr = regStr+"\\"+types[i]+'$';
				}else{
					regStr = regStr+"\\"+types[i]+'$|';
				}
			}
		}
		regStr = regStr+'/i';
		regStr = eval(regStr);
		if($.trim(filePath)!="" && !regStr.test(filePath)){
			file.value = "";
			jboxTip("请上传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片");
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 返回文件URL
	 */
	function returnUrl(filePath){
		var fileUrlId = $("#upFileId").val();
		$('#'+fileUrlId).text("重新上传");
		$('#'+fileUrlId+'Value').val(filePath);
		var filePath = "${sysCfgMap['upload_base_url']}/" + filePath;
		$('#'+fileUrlId+'Del').show();
		$('#'+fileUrlId+'Span').show();
		$('#'+fileUrlId+'Span').find("a").attr('href',filePath);
	}

	/**
	 * 删除文件
	 */
	function delFile(fileUrlId) {
		jboxConfirm("确认删除？" , function(){
			$("#"+fileUrlId+"Del").hide();
			$("#"+fileUrlId+"Span").hide();
			$("#"+fileUrlId).text("上传");
			$("#"+fileUrlId+"Value").val("");
		});
	}
	function validateChange(type){
		var url = "<s:url value='/jsres/doctor/validateChange'/>?type="+type+"&doctorFlow=${doctorRecruit.doctorFlow}&orgFlow=${doctorRecruit.orgFlow}";
		jboxPost(url, null, function(resp){
			if("${GlobalConstant.FLAG_Y}" == resp){
				if(type=="${jsResChangeTypeEnumBaseChange.id}"){
					applyChangeBase();
				}else{
					applyChangeSpe();
				}
			}else{
				if(type=="${jsResChangeTypeEnumBaseChange.id}"){
					jboxTip("您正在申请专业变更，暂不可申请基地变更！");
				}else{
					jboxTip("您正在申请基地变更，暂不可申请专业变更！");
				}
			}
		}, null , false);
	}
	function applyChangeSpe(){
		var url = "<s:url value='/jsres/doctor/applyChangeSpe'/>?doctorFlow=${doctorRecruit.doctorFlow}&recruitFlow=${doctorRecruit.recruitFlow}&catSpeId=${doctorRecruit.catSpeId}&speId=${doctorRecruit.speId}&orgFlow=${doctorRecruit.orgFlow}";
		jboxOpen(url, "申请变更培训专业", 700, 300);
	}
	function validate(){
		if(false==$("#editForm").validationEngine("validate")){
			return false;
		}
		var specialFileUrl = $("#specialFileUrlValue").val();
		if(!specialFileUrl){
			jboxTip("请填上传结业证书附件！");
			return false;
		}
		var specialCertNo = $("input[name=specialCertNo]").val();
		if(!specialCertNo){
			jboxTip("请填写结业证书编号！");
			return false;
		}
		jboxStartLoading();
		var url = "<s:url value='/jsres/doctor/saveGraduationInfo'/>";
		var data = $("#editForm").serialize() + "&" +$("#infoForm").serialize();
		debugger
		jboxConfirm("确认保存结业信息？",function(){
			jboxPost(url, data, function(resp){
				jboxEndLoading();
				jboxTip(resp);
			}, null, false);
		},function(){
			jboxEndLoading();
		});
	}
	function sFile(){
		$("#CXfile").click();
	}
	function checkCXFile(obj){

		var id = obj.id;
		$.ajaxFileUpload({
			url:"<s:url value='/res/rec/resRecImg'/>",
			secureuri:false,
			fileElementId:id,
			dataType: 'json',
			success: function (data){
				data=eval("("+data+")");
				console.log("data:"+data)
				if(data){

					var status=data.status;
					if(status=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
						$(".imgc").attr("href","${sysCfgMap['upload_base_url']}"+"/"+data.url);
						jboxTip("上传成功！");

					}else{
						jboxTip(data.error);
					};
				};
			},
			error: function (data, status, e){
				jboxTip('${GlobalConstant.UPLOAD_FAIL}');
			},
			complete:function(){
			}
		});
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
						<th>所在地区：</th>
						<td>${doctorRecruit.placeName}</td>
						<th>届别：</th>
						<td>${doctorRecruit.sessionNumber }</td>
					</tr>
					<tr>
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
							<c:if test="${isLatest && jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId && not empty doctorRecruit.orgFlow &&( applyFlag )}">
								<!-- 最新变更记录：  (非待转出审核 && 非待转入审核) && 当前用户-->
								<c:set var="showChangBtn" value="${((jsResChangeApplyStatusEnumOutApplyWaiting.id != latestDocOrgHistory.changeStatusId
								&& jsResChangeApplyStatusEnumInApplyWaiting.id != latestDocOrgHistory.changeStatusId
								&& jsResChangeApplyStatusEnumGlobalApplyWaiting.id != latestDocOrgHistory.changeStatusId ) ) && doctorRecruit.doctorFlow == sessionScope.currUser.userFlow}"/>
								<c:if test="${showChangBtn and haveReturn ne 'Y'}">
									&nbsp;<a id="changeButton" onclick="validateChange('${jsResChangeTypeEnumBaseChange.id}')" class="btn">变更基地申请</a>
								</c:if>
							</c:if>
						</td>
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
							<c:if test="${isLatest && jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId && not empty doctorRecruit.orgFlow &&( applyFlag )}">
								<c:set var="showChangBtn" value="${((jsResChangeApplySpeEnumBaseWaitingAudit.id != lastChangeSpe.changeStatusId
    		          		&& jsResChangeApplySpeEnumGlobalWaitingAudit.id != lastChangeSpe.changeStatusId) )
    		          		&& doctorRecruit.doctorFlow == sessionScope.currUser.userFlow}"/>
								<c:if test="${showChangBtn and haveReturn ne 'Y'}">
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
						<c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.doctorStatusId}">
							<th>报到审核状态：</th>
							<td>
								${doctorRecruit.auditStatusName}
							</td>
						</c:if>
						<c:if test="${jsResDoctorAuditStatusEnumPassed.id ne doctorRecruit.doctorStatusId}">
							<th>审核状态：</th>
							<td>
								${doctorRecruit.doctorStatusName}
							</td>
						</c:if>
					</tr>

					<tr id="proveFileUrlTr" style="display:none;">
						<th>减免培训年限证明：</th>
						<td colspan="3">
							<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and param.studyFlag==GlobalConstant.FLAG_Y}">
								<c:if test="${doctorRecruit.orgFlow eq currUser.orgFlow}">
									<c:if test="${!empty doctorRecruit.proveFileUrl}">
										<span id="proveFileUrlSpan" style="">
											[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]&nbsp;
										</span>
										<a id="proveFileUrl" href="javascript:chooseFile('proveFileUrl');" class="btn">${empty doctorRecruit.proveFileUrl?'':'重新'}上传</a>&nbsp;
										<a id="proveFileUrlDel" href="javascript:delFile('proveFileUrl');" class="btn" style="${empty doctorRecruit.proveFileUrl?'display:none':''}">删除</a>
										<input type="hidden" id="proveFileUrlValue" name="proveFileUrl" value="${doctorRecruit.proveFileUrl}"/>
									</c:if>
									<c:if test="${empty doctorRecruit.proveFileUrl}">
										<span id="proveFileUrlSpan" style="display:'none'">
											[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]&nbsp;
										</span>
											<a id="proveFileUrl" href="javascript:chooseFile('proveFileUrl');" class="btn">${empty doctorRecruit.proveFileUrl?'':'重新'}上传</a>&nbsp;
											<a id="proveFileUrlDel" href="javascript:delFile('proveFileUrl');" class="btn" style="${empty doctorRecruit.proveFileUrl?'display:none':''}">删除</a>
											<input type="hidden" id="proveFileUrlValue" name="proveFileUrl" value="${doctorRecruit.proveFileUrl}"/>
									</c:if>
								</c:if>
								<c:if test="${doctorRecruit.orgFlow ne currUser.orgFlow}">
									<c:if test="${!empty doctorRecruit.proveFileUrl}">
										[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]
									</c:if>
								</c:if>
							</c:if>
							<c:if test="${GlobalConstant.USER_LIST_LOCAL ne sessionScope.userListScope or param.studyFlag ne GlobalConstant.FLAG_Y}">
								<c:if test="${GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope||param.auditFlag eq  GlobalConstant.FLAG_Y|| param.info eq GlobalConstant.FLAG_Y|| param.change eq GlobalConstant.FLAG_Y}">
									<c:if test="${!empty doctorRecruit.proveFileUrl}">
										[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]
									</c:if>
								</c:if>
							</c:if>
						</td>
					</tr>

					<%--<tr>--%>
						<%--<th>结业考核年份：</th>--%>
						<%--<td colspan="3">${doctorRecruit.graduationYear}&#12288;<font color="red">(结业考核年份=当前届别+培训年限)</font></td>--%>
					<%--</tr>--%>
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

					<!-- 填写审核意见 -->
					<c:if test="${param.auditFlag == GlobalConstant.FLAG_Y and ((jsResDoctorAuditStatusEnumAuditing.id eq doctorRecruit.auditStatusId and param.exType ne 'view') or param.exType eq 'reExamine') and doctorRecruit.orgFlow eq sessionScope.currUser.orgFlow}">
						<tr>
							<th>审核意见：</th>
							<td colspan="3">
								<textarea class="validate[required] xltxtarea" style=" margin: 0px; padding: 0px;" id="admitNotice" name="admitNotice"></textarea>
							</td>
						</tr>
					</c:if>
					<c:if test="${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope ||GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope }">
						<tr id="returnNotice">
							<th>退回意见：</th>
							<td colspan="3">
								<textarea class="validate[required] xltxtarea" id="admitNotice"></textarea>
							</td>
						</tr>
					</c:if>
					</tbody>
				</table>
			</form>
				<%--<div>--%>
					<%--<h4>诚信声明</h4>--%>
					<%--<table border="0" cellpadding="0" cellspacing="0" width="100%" class="base_info">--%>
						<%--<colgroup>--%>
							<%--<col width="15%"/>--%>
							<%--<col width="85%"/>--%>
						<%--</colgroup>--%>
						<%--<tr>--%>
							<%--<th>诚信声明：</th>--%>
							<%--<td>--%>
								<%--<c:if test="${not empty resRec.imageUrl}">--%>
									<%--[<a class="imgc" href="${sysCfgMap['upload_base_url']}/${resRec.imageUrl}" target="_blank">查看图片</a>]--%>
									<%--<c:if test="${GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope}">--%>
										<%--[<a onclick="sFile();" target="_blank">重新上传</a>]--%>
									<%--</c:if>--%>
									<%--<input id="CXfile" style="display: none;" type="file" name="checkFile" onchange="checkCXFile(this);"  class="validate[required]"/>--%>
								<%--</c:if>--%>
								<%--<c:if test="${empty resRec.imageUrl}">--%>
									<%--暂未提交--%>
								<%--</c:if>--%>
							<%--</td>--%>
						<%--</tr>--%>
					<%--</table>--%>
				<%--</div>--%>
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
								<%--<td>
									<input  id="specialFileUrlValue" name="doctorRecruit.specialFileUrl" value="${doctorRecruit.specialFileUrl}"/>
									<span id="specialFileUrlSpan" style="display:${!empty doctorRecruit.specialFileUrl?'':'none'}">
										[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.specialFileUrl}" target="_blank">查看图片</a>]&nbsp;
									</span>
									<a id="specialFileUrl" href="javascript:chooseFile('specialFileUrl');" class="btn">${empty doctorRecruit.specialFileUrl?'':'重新'}上传</a>&nbsp;
									<a id="specialFileUrlDel" href="javascript:delFile('specialFileUrl');" class="btn" style="${empty doctorRecruit.specialFileUrl?'display:none':''}">删除</a>
									&nbsp;
								</td>--%>
								<td >
									<input type="hidden" id="specialFileUrlValue" name="specialFileUrl" value="${doctorRecruit.specialFileUrl}"/>
									<span id="specialFileUrlSpan" style="display:${!empty doctorRecruit.specialFileUrl?'':'none'} ">
									[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.specialFileUrl}" target="_blank">查看图片</a>]&nbsp;
								</span>
									<a id="specialFileUrl" href="javascript:uploadFile2('specialFileUrl','附件');" class="btn">${empty doctorRecruit.specialFileUrl?'':'重新'}上传</a>&nbsp;
									<a id="specialFileUrlDel" href="javascript:delFile('specialFileUrl');" class="btn" style="${empty doctorRecruit.specialFileUrl?'display:none':''}">删除</a>&nbsp;
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
			<div align="center" style="margin-top: 20px; margin-bottom:20px;">
				<c:if test="${GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope && doctorRecruit.doctorStatusId eq '21'}">
					<input type="button" id="saveBtn" class="btn_green" onclick="validate();" value="保存结业信息"/>&nbsp;
				</c:if>
			</div>
			<form id="fileForm" method="post" enctype="multipart/form-data">
				<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
			</form>
			<c:if test="${empty param.isRetrunShow}">
				<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and param.studyFlag eq 'Y' && doctorRecruit.orgFlow eq currUser.orgFlow}">
					<c:if test="${ doctorRecruit.catSpeId==trainCategoryEnumDoctorTrainingSpe.id and (jsResTrainYearEnumThreeYear.id ne doctorRecruit.trainYear or empty doctorRecruit.trainYear )}">
						<div class="btn_info">
							<input type="button" class="btn_green" style="width:100px;" onclick='save("${doctorRecruit.recruitFlow}");' value="保存">
						</div>
					</c:if>
				</c:if>
				<c:if test="${(empty doctorRecruit.auditStatusId or jsResDoctorAuditStatusEnumNotSubmit.id eq doctorRecruit.auditStatusId or auditNotPassed )and (haveReturn != 'Y')and param.change != GlobalConstant.FLAG_Y}">
					<div align="center" style="margin-top: 20px; margin-bottom:20px;">
						<c:if test="${param.exType ne 'view'}">
							<input type="button" id="submitBtn" class="btn_red" onclick="submitRecruit('${doctorRecruit.recruitFlow}');" value="提交"/>&nbsp;
							<input type="button" class="btn_green" onclick="editDoctorRecruit('${doctorRecruit.recruitFlow}');" value="编辑"/>&nbsp;
							<input type="button" class="btn_green" onclick="delDoctorRecruit('${doctorRecruit.recruitFlow}');" value="删除"/>
						</c:if>
						<c:if test="${param.exType ne null}">
							<input type="button" style="width:100px;" class="btn_green" onclick="jboxClose();" value="关闭"/>
						</c:if>
					</div>
				</c:if>
				<c:if test="${jsResDoctorAuditStatusEnumAuditing.id eq doctorRecruit.auditStatusId and GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and doctorRecruit.orgFlow eq sessionScope.currUser.orgFlow}">
					<div class="btn_info">
						<c:if test="${param.exType eq 'examine'}">
							<input type="button" style="width:100px;" class="btn_green" onclick="saveAuditRecruit('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}','${GlobalConstant.FLAG_Y}')" value="通过"></input>
							<input type="button" style="width:100px;" class="btn_red" onclick="saveAuditRecruit('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}','${GlobalConstant.FLAG_N}')" value="不通过"></input>
						</c:if>
						<c:if test="${param.exType ne null}">
							<input type="button" style="width:100px;" class="btn_green" onclick="jboxClose();" value="关闭"></input>
						</c:if>
					</div>
				</c:if>
				<c:if test="${jszyResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId and GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and doctorRecruit.orgFlow eq sessionScope.currUser.orgFlow}">
					<div class="btn_info">
						<c:if test="${param.exType eq 'reExamine'}">
							<input type="button" style="width:100px;" class="btn_red" onclick="saveAuditRecruit('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}','${GlobalConstant.FLAG_N}')" value="不通过"/>
						</c:if>
						<c:if test="${param.exType ne null}">
							<input type="button" style="width:100px;" class="btn_green" onclick="top.jboxClose();" value="关闭"/>
						</c:if>
					</div>
				</c:if>
				<div id="auditDiv">
					<c:if test="${!(doctor.graduationStatusId eq 'GrantCertf')}">
						<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['jsres_global_return']}">
							<c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId and GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope && param.backFlag == 'true'}">
								<div class="btn_info">
									<input type="button" style="width:100px;" onmouseout="" class="btn_green" onclick="chargeResetOrg('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}')" value="退回重审"></input>
									&nbsp;&nbsp;
									<input type="button" style="width:100px;" onmouseout="" class="btn_green" onclick="chargeResetPerson('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}')" value="退回修改"></input>
								</div>
							</c:if>
						</c:if>
						<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['jsres_charge_return']}">
							<c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId and GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope && param.backFlag == 'true'}">
								<div class="btn_info">
									<input type="button" style="width:100px;" onmouseout="" class="btn_green" onclick="chargeResetOrg('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}')" value="退回重审"></input>
									&nbsp;&nbsp;
									<input type="button" style="width:100px;" onmouseout="" class="btn_green" onclick="chargeResetPerson('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}')" value="退回修改"></input>
								</div>
							</c:if>
						</c:if>
					</c:if>
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
<%-- 	<input type="button" style="width:80px;margin-left: 130px"class="btn_green" onclick="queding('${doctorRecruit.recruitFlow}')" value="确定"></input> --%>
<!-- </div> -->
<!--  </div> -->