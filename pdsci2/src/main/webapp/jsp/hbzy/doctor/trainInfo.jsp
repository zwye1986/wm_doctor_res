<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and param.studyFlag eq GlobalConstant.FLAG_Y && doctorRecruit.orgFlow eq currUser.orgFlow}">
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
</c:if>
<style type="text/css">
	.infoAudit h4{
		border-left: 4px solid #dc781d;
	}
	.icon-head{ /* vertical-align: middle; */}
	.changeinfo{position:absolute; background-color:#fff; padding:10px; border:1px solid  #dcdcdc; width:550px;}
	.changeinfoContent{position:absolute; background-color:#fff; padding:10px; border:1px solid  #dcdcdc; width:650px;}
	.icon_up{background-image:url("<s:url value='/css/skin/${skinPath}/images/up2.png'/>"); background-repeat:no-repeat; background-position: top center; padding:5px;position: absolute;top: -6px;left: 225px;}
	.xllist caption{padding-bottom: 10px;font-weight: bold;font-size: 15px;color: #3d91d5;}
	.pxxx{position: relative;top:30px; right: 175px;}
	.pyyy{position: relative;top:30px; right: 175px;}
	.centerInfo th{text-align: center;}
	.centerInfo td{text-align: center;}
</style>
<script type="text/javascript">
function submitRecruit(recruitFlow){
	if($("select[name=secondSpeId]").hasClass("validate[required]")){
		if(!$("select[name=secondSpeId]").val()){
			jboxTip("请填写二级专业！");
			return;
		}
	}
		var url = "<s:url value='/hbzy/doctor/checkUserInfoInBlack'/>?userCode=${user.userCode}&userEmail=${user.userEmail}&userFlow=${user.userFlow}&userPhone=${user.userPhone}&idNo=${user.idNo}";
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
					var url = "<s:url value='/hbzy/doctor/submitResDoctorRecruit'/>?auditStatusId=${jszyResDoctorAuditStatusEnumAuditing.id}&recruitFlow="+recruitFlow;
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
		jboxPost("<s:url value='/hbzy/doctorRecruit/saveTrainInfo'/>?recruitFlow="+recruitFlow , $("#infoForm").serialize() , function(resp){
			setTimeout(function(){
				window.parent.refreshCount();
				jboxClose();
			},1000);
		} , null , true);
	});
}
function save2(recruitFlow){

	var secondSpeName = $("select[name=secondSpeId] :selected").text();
	if(secondSpeName=="请选择"){
		secondSpeName = "";
	}
	$("#secondSpeName").val(secondSpeName);
	if(false==$("#infoForm").validationEngine("validate")){
		return false;
	}
	jboxConfirm('请确认二级专业信息，一旦保存无法修改二级专业信息?',function(){
		jboxPost("<s:url value='/hbzy/doctorRecruit/saveSecondSpe'/>?recruitFlow="+recruitFlow , $("#infoForm").serialize() , function(resp){
			setTimeout(function(){
				getDoctorRecruit(recruitFlow,'${doctorRecruit.doctorFlow}');
				jboxClose();
			},1000);
		} , null , true);
	});
}
function delDoctorRecruit(recruitFlow){
	jboxConfirm("确认删除这条培训信息?",  function(){
		 var url= "<s:url value='/hbzy/doctorRecruit/delDoctorRecruit'/>?recruitFlow="+recruitFlow;
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
	var auditStatusId = "${jszyResDoctorAuditStatusEnumNotPassed.id}";
	if(flag=='Y'){
		info="基地重新审核";
		auditStatusId = "${jszyResDoctorAuditStatusEnumAuditing.id}";
	}
	jboxConfirm("确认将该学员退回"+info+"?",  function(){
		var url = "<s:url value='/hbzy/doctor/backResDoctorRecruit'/>?recruitFlow="+recruitFlow +"&auditStatusId=" + auditStatusId+"&doctorFlow="+doctorFlow;
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
		var auditStatusId="${jszyResDoctorAuditStatusEnumAuditing.id}";
		jboxConfirm("确认将该学员退回重审?",  function(){
			var url = "<s:url value='/hbzy/doctor/backResDoctorRecruit'/>?doctorFlow="+doctorFlow+"&auditStatusId="+auditStatusId+"&recruitFlow="+recruitFlow ;
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
		var auditStatusId="${jszyResDoctorAuditStatusEnumNotPassed.id}";
		jboxConfirm("确认将该学员退回修改?",  function(){
			var url = "<s:url value='/hbzy/doctor/backResDoctorRecruit'/>?doctorFlow="+doctorFlow+"&auditStatusId="+auditStatusId+"&recruitFlow="+recruitFlow ;
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
	var url = "<s:url value='/hbzy/doctor/applyChangeBase'/>?doctorFlow=${doctorRecruit.doctorFlow}&recruitFlow=${doctorRecruit.recruitFlow}&catSpeId=${doctorRecruit.catSpeId}&speId=${doctorRecruit.speId}&orgFlow=${doctorRecruit.orgFlow}";
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
	if("" == trainYear || "${jszyResTrainYearEnumThreeYear.id}" == trainYear){
		$("#proveFileUrlTr").hide();
	}else{
		$("#proveFileUrlTr").show();
	}
}

/**
 * 审核
 */
function saveAuditRecruit(recruitFlow, doctorFlow,sessionNumber, auditFlag){
	var admitNotice = $("#admitNotice").val().trim();
	var	title = "通过";
	var	auditStatusId = "${jszyResDoctorAuditStatusEnumPassed.id}";
	if("${GlobalConstant.FLAG_N}" == auditFlag){
		title = "不通过";
		auditStatusId = "${jszyResDoctorAuditStatusEnumNotPassed.id}";
		if("" == admitNotice){
			jboxTip("请填写审核意见！");
			return false;
		}
	}
	var data = {
			recruitFlow : recruitFlow,
			doctorFlow : doctorFlow,
			auditStatusId : auditStatusId,
			admitNotice : admitNotice,
            sessionNumber:sessionNumber
	};
	jboxConfirm("确认审核"+title+"?" ,  function(){
		var url = "<s:url value='/hbzy/manage/province/doctor/saveAuditRecruit'/>";
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
	var url = "<s:url value='/hbzy/doctor/uploadTrainYearFile'/>";
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
	var url = "<s:url value='/hbzy/doctor/validateChange'/>?type="+type+"&doctorFlow=${doctorRecruit.doctorFlow}&orgFlow=${doctorRecruit.orgFlow}";
	jboxPost(url, null, function(resp){
		if("${GlobalConstant.FLAG_Y}" == resp){
			if(type=="${jszyResChangeTypeEnumBaseChange.id}"){
				applyChangeBase();
			}else{
				applyChangeSpe();
			}
		}else{
			if(type=="${jszyResChangeTypeEnumBaseChange.id}"){
				jboxTip("您正在申请专业变更，暂不可申请基地变更！");
			}else{
				jboxTip("您正在申请基地变更，暂不可申请专业变更！");
			}
		}
	}, null , false);
}
function applyChangeSpe(){
	var url = "<s:url value='/hbzy/doctor/applyChangeSpe'/>?doctorFlow=${doctorRecruit.doctorFlow}&recruitFlow=${doctorRecruit.recruitFlow}&catSpeId=${doctorRecruit.catSpeId}&speId=${doctorRecruit.speId}&orgFlow=${doctorRecruit.orgFlow}";
	jboxOpen(url, "申请变更培训专业", 700, 380);
}
</script>
<!-- <div class="infoAudit"> -->
<div>
<div class="${'open' eq param.openType?'infoAudit':'main_bd' }">
<div class="div_table">
<!-- 		 <input type="hidden" id="proveFileUrlFlag"/> -->
<!-- 	     <input type="hidden" id="completeFileUrlFlag"/> -->
	     <input type="hidden" id="upFileId"/>
        <h4>培训信息</h4>
        	<c:set var="auditNotPassed" value="${jszyResDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}"/>
			<form id="infoForm" style="position: relative;" method="post">
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
              <col width="17%"/>
              <col width="30%"/>
              <col width="17%"/>
              <col width="36%"/>
            </colgroup>
	           <tbody>
	           <tr>
	             <th>当前学位类别：</th>
	             <td>${doctorRecruit.currDegreeCategoryName }</td>
	             <th style="${auditNotPassed?'color: red':'' }">审核状态：</th>
	             <td class="${auditNotPassed?'red':'' }">${doctorRecruit.auditStatusName }</td>
	           </tr>
	           <tr>
	               <th>规培起始日期：</th>
	               <td>${doctorRecruit.recruitDate}</td>
	               <th>年&#12288;&#12288;级：</th>
	               <td>${doctorRecruit.sessionNumber }</td>
	           </tr>
	           <tr>
	               <th>所在地区：</th>
	               <td>${doctorRecruit.placeName}</td>
                   <c:if test="${zlFlag  == GlobalConstant.FLAG_Y}">
                       <th>培训基地：</th>
                       <td>${doctorRecruit.orgName }</td>
                   </c:if>
                   <c:if test="${zlFlag  != GlobalConstant.FLAG_Y}">
                       <th>
                           <!--培训记录 ： 最新 && 审核通过 -->
                           <c:if test="${jszyResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId}">
                               <!-- 变更申请记录 -->
                               <c:if test="${!empty docOrgHistoryList}">
                                   <div class="pxxx" id="changInfo" style="display:none; ">
                                       <div class="changeinfo" id="changeinfoContent">
                                           <i class="icon_up"></i>
                                           <table border="0" cellpadding="0" cellspacing="0" class="grid centerInfo" style="width: 100%">
                                               <caption>变更基地申请记录</caption>
                                               <thead>
                                               <tr>
                                                   <th>转入前培训基地</th>
                                                   <th>申请转入培训基地</th>
                                                   <th>审核状态</th>
                                                   <th>审核意见</th>
                                                   <th>审核时间</th>
                                               </tr>
                                               </thead>
                                               <tbody>
                                               <c:forEach items="${docOrgHistoryList }" var="docOrgHistory">
                                                   <tr>
                                                       <td>${docOrgHistory.historyOrgName}</td>
                                                       <td>${docOrgHistory.orgName}</td>
                                                       <td>${jszyResChangeApplyStatusEnumInApplyWaiting.id == docOrgHistory.changeStatusId?'转出审核通过'
                                                               :jszyResChangeApplyStatusEnumGlobalApplyWaiting.id == docOrgHistory.changeStatusId?'转入审核通过':docOrgHistory.changeStatusName}</td>
                                                       <td><label title="${docOrgHistory.auditOpinion}">${pdfn:cutString(docOrgHistory.auditOpinion,10,true,3) }</label></td>
                                                       <td>
                                                               <%--<c:if test="${!empty docOrgHistory.inDate }">${pdfn:transDate(docOrgHistory.inDate)}</c:if>--%>
                                                               <%--<c:if test="${empty docOrgHistory.inDate }">${pdfn:transDate(docOrgHistory.outDate)}</c:if>--%>
                                                           <c:if test="${docOrgHistory.changeStatusId ==  jszyResChangeApplyStatusEnumGlobalApplyUnPass.id or
													docOrgHistory.changeStatusId ==  jszyResChangeApplyStatusEnumGlobalApplyPass.id}">
                                                               ${pdfn:transDate(docOrgHistory.modifyTime)}
                                                           </c:if>
                                                           <c:if test="${docOrgHistory.changeStatusId ==  jszyResChangeApplyStatusEnumInApplyUnPass.id or
													docOrgHistory.changeStatusId ==  jszyResChangeApplyStatusEnumGlobalApplyWaiting.id}">
                                                               ${pdfn:transDate(docOrgHistory.inDate)}
                                                           </c:if>
                                                           <c:if test="${docOrgHistory.changeStatusId ==  jszyResChangeApplyStatusEnumOutApplyUnPass.id or
													docOrgHistory.changeStatusId ==  jszyResChangeApplyStatusEnumInApplyWaiting.id}">
                                                               ${pdfn:transDate(docOrgHistory.outDate)}
                                                           </c:if>
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
                           <c:if test="${isLatest && jszyResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId && not empty doctorRecruit.orgFlow}">
                               <!-- 最新变更记录：  (非待转出审核 && 非待转入审核&& 非待省厅审核) && 当前用户-->
                               <c:set var="showChangBtn" value="${(jszyResChangeApplyStatusEnumOutApplyWaiting.id != latestDocOrgHistory.changeStatusId
    		          		&& jszyResChangeApplyStatusEnumInApplyWaiting.id != latestDocOrgHistory.changeStatusId
    		          		&& jszyResChangeApplyStatusEnumGlobalApplyWaiting.id != latestDocOrgHistory.changeStatusId )
    		          		&& doctorRecruit.doctorFlow == sessionScope.currUser.userFlow}"/>
                               <c:if test="${showChangBtn}">
                                   &nbsp;<a id="changeButton" onclick="validateChange('${jszyResChangeTypeEnumBaseChange.id}')" class="btn" style="float: right;margin-right: 10px;">变更基地申请</a>
                               </c:if>
                           </c:if>
                       </td>
                   </c:if>
	           </tr>
	           <tr>
	               <th>培训专业：</th>
	               <td id="catSpeNameTd">${doctorRecruit.catSpeName}</td>
                   <c:if test="${zlFlag  == GlobalConstant.FLAG_Y}">
                       <th>对应专业：</th>
                       <td>${doctorRecruit.speName}</td>
                   </c:if>
                   <c:if test="${zlFlag  != GlobalConstant.FLAG_Y}">
                       <th class="trainSpe">
                           <c:if test="${ jszyResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId}">
                               <c:if test="${!empty changeSpeList}">
                                   <div class="pyyy" id="changSpe" style="display:none; ">
                                       <div class="changeinfoContent">
                                           <i class="icon_up"></i>
                                           <table border="0" cellpadding="0" cellspacing="0" class="grid centerInfo"
                                                  style="width: 100%">
                                               <caption>变更专业申请记录</caption>
                                               <thead>
                                               <tr>
                                                   <th>序号</th>
                                                   <th>节点</th>
                                                   <th>培训专业</th>
                                                   <th>对应专业</th>
                                                   <th>二级专业</th>
                                                   <th>审核状态</th>
                                                   <th>审核时间</th>
                                                   <th>审核意见</th>
                                               </tr>
                                               </thead>
                                               <tbody>
                                               <c:forEach items="${changeSpeList }" var="changeSpe" varStatus="num">
                                                   <tr>
                                                       <td rowspan="2">${num.count}</td>
                                                       <td>变更前</td>
                                                       <td>${changeSpe.historyTrainingTypeName}</td>
                                                       <td title="${changeSpe.historyTrainingSpeName}">${pdfn:cutString(changeSpe.historyTrainingSpeName,4,true,3)}</td>
                                                       <td title="${changeSpe.historySecondSpeName}">${pdfn:cutString(changeSpe.historySecondSpeName,4,true,3)}</td>
                                                       <td rowspan="2">${changeSpe.changeStatusName}</td>
                                                       <td rowspan="2">
                                                           <c:if test="${!empty changeSpe.inDate }">${pdfn:transDate(changeSpe.inDate)}</c:if>
                                                           <c:if test="${empty changeSpe.inDate }">${pdfn:transDate(changeSpe.outDate)}</c:if>
                                                       </td>
                                                       <td rowspan="2"><label
                                                               title="${changeSpe.auditOpinion}">${pdfn:cutString(changeSpe.auditOpinion,10,true,3) }</label>
                                                       </td>
                                                   </tr>
                                                   <tr>
                                                       <td>变更后</td>
                                                       <td>${changeSpe.trainingTypeName}</td>
                                                       <td title="${changeSpe.trainingSpeName}">${pdfn:cutString(changeSpe.trainingSpeName,4,true,3)}</td>
                                                       <td title="${changeSpe.secondSpeName}">${pdfn:cutString(changeSpe.secondSpeName,4,true,3)}</td>
                                                   </tr>
                                               </c:forEach>
                                               </tbody>
                                           </table>
                                       </div>
                                   </div>
                                   <img id="changeIcon" class="icon" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
                               </c:if>
                           </c:if>
                           对应专业：
                       </th>
                       <td id="speNameTd">
                               ${doctorRecruit.speName}
                               <%--中医助理全科不提供变更专业--%>
                           <c:if test="${isLatest && jszyResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId && not empty doctorRecruit.orgFlow}">
                               <c:set var="showChangBtn" value="${(jszyResChangeApplySpeEnumBaseWaitingAudit.id != lastChangeSpe.changeStatusId
    		          		&& jszyResChangeApplySpeEnumGlobalWaitingAudit.id != lastChangeSpe.changeStatusId)
    		          		&& doctorRecruit.doctorFlow == sessionScope.currUser.userFlow && doctorRecruit.catSpeId ne jszyTrainCategoryEnumTCMAssiGeneral.id}"/>
                               <c:if test="${showChangBtn}">
                                   &nbsp;<a id="changeSpeButton" onclick="validateChange('${jszyResChangeTypeEnumSpeChange.id}')" class="btn" style="float: right;margin-right: 10px;">变更专业申请</a>
                               </c:if>
                           </c:if>
                       </td>
                   </c:if>

	           </tr>
			   <c:if test="${doctorRecruit.catSpeId ne jszyTrainCategoryEnumTCMAssiGeneral.id and doctorRecruit.catSpeId ne jszyTrainCategoryEnumTCMGeneral.id}">
	           <tr>
	               <th>二级专业：</th>
	               <td>
	               ${doctorRecruit.secondSpeName}
					   <c:if test="${empty doctorRecruit.secondSpeId and GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope}">
						   <select name="secondSpeId" class="<c:if test="${doctorRecruit.secondSpeId eq '3501' or doctorRecruit.secondSpeId eq '3601'}"> validate[required]</c:if> select" style="width: 160px;">
							   <option value="">请选择</option>
							   <c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="spe">
								   <option value="${spe.dictId}" ${doctorRecruit.secondSpeId eq spe.dictId?'selected':''}>${spe.dictName}</option>
							   </c:forEach>
						   </select>&#12288;<span class="red" style="<c:if test="${doctorRecruit.secondSpeId ne '3501' and doctorRecruit.secondSpeId ne '3601'}">display:none</c:if>">*</span>
						   <input type="hidden" id="secondSpeName" name="secondSpeName" value="${doctorRecruit.secondSpeName}"/>
					   </c:if>
	               </td>
	               <th></th>
	               <td>
	               </td>
	           </tr>
			   </c:if>
	           <tr>
	               <th>培养年限：</th>
	               <td>
	                 <c:forEach items="${jszyResTrainYearEnumList }" var="trainYear">
	                     <c:if test="${doctorRecruit.trainYear eq trainYear.id}">${trainYear.name }</c:if>
	                 </c:forEach>
	               </td>
	               <th>已培养年限：</th>
	               <td>
	                  <c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}">
	                    <c:if test="${doctorRecruit.yetTrainYear eq dict.dictId}">
	                       ${dict.dictName }
	                    </c:if>
	                  </c:forEach>
	               </td>
	           </tr>
	           
				<%--<tr id="proveFileUrlTr" style="display:none;">--%>
					<%--<th>减免培养年限证明：</th>--%>
					<%--<td colspan="3">--%>
						<%--<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and param.studyFlag==GlobalConstant.FLAG_Y}">--%>
							<%--<c:if test="${doctorRecruit.orgFlow eq currUser.orgFlow}">--%>
								<%--<span id="proveFileUrlSpan" style="display:${!empty doctorRecruit.proveFileUrl?'':'none'} ">--%>
									<%--[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]&nbsp;--%>
								<%--</span>--%>
								<%--<a id="proveFileUrl" href="javascript:chooseFile('proveFileUrl');" class="btn">${empty doctorRecruit.proveFileUrl?'':'重新'}上传</a>&nbsp;--%>
								<%--<a id="proveFileUrlDel" href="javascript:delFile('proveFileUrl');" class="btn" style="${empty doctorRecruit.proveFileUrl?'display:none':''}">删除</a>--%>
								<%--<input type="hidden" id="proveFileUrlValue" name="proveFileUrl" value="${doctorRecruit.proveFileUrl}"/>--%>
							<%--</c:if>--%>
							<%--<c:if test="${!(doctorRecruit.orgFlow eq currUser.orgFlow)}">--%>
								<%--<c:if test="${!empty doctorRecruit.proveFileUrl}">--%>
									<%--[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]--%>
								<%--</c:if>--%>
							<%--</c:if>--%>
						<%--</c:if>--%>
						<%--<c:if test="${GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope||param.auditFlag eq  GlobalConstant.FLAG_Y|| param.info eq GlobalConstant.FLAG_Y|| param.change eq GlobalConstant.FLAG_Y}">--%>
							<%--<c:if test="${!empty doctorRecruit.proveFileUrl}">--%>
								<%--[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]--%>
							<%--</c:if>--%>
						<%--</c:if>--%>
					<%--</td>--%>
				<%--</tr>--%>
	           
	           <tr>
	               <th>医师状态：</th>
	               <td>${doctorRecruit.doctorStatusName}</td>
	               <th>医师走向：</th>
	               <td>${doctorRecruit.doctorStrikeName}</td>
	           </tr>
	            <tr>
	           		<th>结业考核年份：</th>
	           		<td colspan="3">${doctorRecruit.graduationYear}&#12288;<font color="red">(结业考核年份=当前年级+培养年限)</font></td>
	           </tr>
	           <!-- 审核通过 && 非二阶段 -->
	           <c:if test="${doctorRecruit.auditStatusId eq jszyResDoctorAuditStatusEnumPassed.id && not empty doctorRecruit.completeFileUrl && not empty doctorRecruit.completeCertNo}">
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
	            <c:if test="${param.auditFlag == GlobalConstant.FLAG_Y and jszyResDoctorAuditStatusEnumAuditing.id eq doctorRecruit.auditStatusId and doctorRecruit.orgFlow eq sessionScope.currUser.orgFlow}">
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
			<c:if test="${!(param.studyFlag eq GlobalConstant.FLAG_Y)}">
           <div style="padding-top: 20px;">
			<h4>诚信声明</h4>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="base_info">
	            <colgroup>
	              <col width="17%"/>
	              <col width="83%"/>
	            </colgroup>
	        	<tr>
	        		<th>诚信声明：</th>
	        		<td>
  						<c:if test="${not empty imageUrl}">
							[<a href="${sysCfgMap['upload_base_url']}/${imageUrl}" target="_blank">查看图片</a>]
						</c:if>
  						<c:if test="${empty imageUrl}">
							暂未提交
						</c:if>
	        		</td>
	        	</tr>
            </table>
			</div>	
            </c:if>
          	<form id="fileForm" method="post" enctype="multipart/form-data">
				<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
			</form>
			
			 <c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and param.studyFlag eq 'Y' && doctorRecruit.orgFlow eq currUser.orgFlow}">
	           	<div class="btn_info">
	    			<input type="button" class="btn_brown" style="width:100px;" onclick='save("${doctorRecruit.recruitFlow}");' value="保&#12288;存">
	    		</div>
    		</c:if>
		<c:if test="${(empty doctorRecruit.auditStatusId or jszyResDoctorAuditStatusEnumNotSubmit.id eq doctorRecruit.auditStatusId or auditNotPassed )and param.change != GlobalConstant.FLAG_Y}">
			<div align="center" style="margin-top: 20px; margin-bottom:20px;">
				<input type="button" id="submitBtn" class="btn_red" onclick="submitRecruit('${doctorRecruit.recruitFlow}');" value="提交"/>&nbsp;
				<input type="button" class="btn_brown" onclick="editDoctorRecruit('${doctorRecruit.recruitFlow}');" value="编辑"/>&nbsp;
				<input type="button" class="btn_brown" onclick="delDoctorRecruit('${doctorRecruit.recruitFlow}');" value="删除"/>
			</div>
		</c:if>
		<c:if test="${doctorRecruit.catSpeId ne jszyTrainCategoryEnumTCMAssiGeneral.id and doctorRecruit.catSpeId ne jszyTrainCategoryEnumTCMGeneral.id}">
			<c:if test="${empty doctorRecruit.secondSpeId and GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope and jszyResDoctorAuditStatusEnumNotSubmit.id ne doctorRecruit.auditStatusId}">
			<div align="center" style="margin-top: 20px; margin-bottom:20px;">
				<input type="button" class="btn_brown" onclick="save2('${doctorRecruit.recruitFlow}');" value="保&#12288;存"/>&nbsp;
			</div>
			</c:if>
		</c:if>
		 <c:if test="${jszyResDoctorAuditStatusEnumAuditing.id eq doctorRecruit.auditStatusId and GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and doctorRecruit.orgFlow eq sessionScope.currUser.orgFlow}">
		    <div class="btn_info">
				<input type="button" style="width:100px;" class="btn_brown" onclick="saveAuditRecruit('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}','${doctorRecruit.sessionNumber }','${GlobalConstant.FLAG_Y}')" value="通&#12288;过"/>
				<input type="button" style="width:100px;" class="btn_red" onclick="saveAuditRecruit('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}','${doctorRecruit.sessionNumber }','${GlobalConstant.FLAG_N}')" value="不通过"/>
				<input type="button" style="width:100px;" class="btn_brown" onclick="jboxClose();" value="关&#12288;闭"/>
			</div>
		</c:if>
	<c:if test="${!(doctor.graduationStatusId eq 'GrantCertf') and zlFlag ne GlobalConstant.FLAG_Y}">
		<c:if test="${jszyResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId and GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope && param.backFlag == 'true'}">
			<div class="btn_info">
				<input type="button" style="width:100px;" onmouseout="" class="btn_brown" onclick="chargeResetOrg('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}')" value="退回重审"/>
				&nbsp;&nbsp;
				<input type="button" style="width:100px;" onmouseout="" class="btn_brown" onclick="chargeResetPerson('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}')" value="退回修改"/>
			</div>
		</c:if>
		<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['jsres_charge_return']}">
			<c:if test="${jszyResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId and GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope && param.backFlag == 'true'}">
				<div class="btn_info">
					<input type="button" style="width:100px;" onmouseout="" class="btn_brown" onclick="chargeResetOrg('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}')" value="退回重审"/>
					&nbsp;&nbsp;
					<input type="button" style="width:100px;" onmouseout="" class="btn_brown" onclick="chargeResetPerson('${doctorRecruit.recruitFlow}','${doctorRecruit.doctorFlow}')" value="退回修改"/>
				</div>
			</c:if>
		</c:if>
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