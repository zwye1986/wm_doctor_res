<html>
<head>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
console.error("后台更新属性操作步骤：");
console.error("第一步点击一下弹框");
console.error("第二步按键盘输入   上、上、下、下、左、右、左、右、B、A、B、A");
console.error("输入密码间隔需在0.5秒之内！");
</c:if>
function save(){
	if(false==$("#editForm").validationEngine("validate")){
		return false;
	}
	$("#doctorStatusName").val($("#doctorStatusId option:selected").text());
	$("#doctorStrikeName").val($("#doctorStrikeId option:selected").text());
	if($("#reasonId").val()){
		$("#reasonName").val($("#reasonId option:selected").text());
	}
	if($("#policyId").val()){
		$("#policyName").val($("#policyId option:selected").text());
	}
	var info="";
	if($("#reasonId").is(":hidden")){
		if($("#delayreason").is(":hidden")){
			info="保存";
		}else{
			info="延期";
			}
        if(info=="延期"){
            var delayPicUrlValue = $("#delayPicUrlValue").val();
            if(!delayPicUrlValue){
                jboxTip("请上传延期附件！");
                return false;
            }
        }

		jboxConfirm("确认"+info+"?",function () {
			saveInfo();
		});
	}else{
		info ="退培";
		checkStatus("${doctorRecruit.doctorFlow}",function(result){

			if(result != "${GlobalConstant.FLAG_Y}" && info == "退培"  ){
				jboxTip("当前用户已锁定，需解锁之后才能退培！");
				return false;
			}

			jboxConfirm("确认"+info+"?",function () {
				saveInfo();
			});
		});

	}

}
function saveInfo(){
	var url="<s:url value='/jszy/manage/updateDoctorTrend'/>";
//	jboxPost(url, $("#editForm").serialize(), function(){
//			window.parent.toPage();
//			setTimeout(function(){
//				jboxClose();
//			}, 1000);
//	}, null, true);
	jboxSubmit($("#editForm"),url,function(resp){
		if(resp!='操作成功！'){
			jboxTip(resp);
			return;
		}
		window.parent.toPage();
		setTimeout(function(){
			jboxClose();
		}, 1000);
	},null,true);
}
function checkStatus(doctorFlow,callBack){
	var url="<s:url value='/jszy/manage/checkStatus'/>";
	jboxPost(url, {"doctorFlow":doctorFlow}, function(resp){
		callBack(resp);
	}, null, false);
}
//退培
function backTrain(){
	if($("#reasonId").is(":hidden")){
		$(".back").show();
		$(".delay").hide();
		$(".delay").val("");
		$("#back").val("取消退培");
		if($("#delay").val()=="取消延期"){
			$("#delay").val("延期");
		}
	}else{
		$(".back").val("");
		$(".back").hide();
		$("#back").val("退培");
	}
}
function changeReason(obj){
	if($(obj).val()=="3"){
		$("#reason").show();
	}else{
		if($("#reason").is(":hidden")){
			return false;
		}else{
			$("#reason").hide();
		}
	}
}
function changePolicy(obj){
	if($(obj).val()=="2"){
		jboxTip("违约退培学员将5年内无法参加江苏省住院医师规培");
		$("#policy").show();
	}else{
		if($("#policy").is(":hidden")){
			return false;
		}else{
			$("#policy").hide();
		}
	}
}
function chooseFile(id){
	$("#upFileId").val(id);
	return $("#file").click();
}
function checkFile(file){
	var filePath = file.value;
	if(filePath==""){
		return false;
	}
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
 * 删除文件
 */
function delFile(fileUrlId) {
	jboxConfirm("确认删除？" , function(){
		$("#"+fileUrlId+"Del").hide();
		$("#"+fileUrlId+"Span").hide();
		$("#"+fileUrlId).text("上传");
		$("#"+fileUrlId+"Value").val("");
		$("#file").val(null);
	});
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
	var url = "<s:url value='/jszy/doctor/uploadDelayFile'/>";
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
$(document).ready(function(){
	$(".back").hide();
	$("#file").live("change",function(){
		uploadFile();
	});
	$('#time').datepicker({
		startDate:"${doctorRecruit.graduationYear+1}",
		startView: 2,
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	$('#sessionNumber').datepicker({
		startView: 2,
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	$('#recruitDate').datepicker({
		startDate:"${sysCfgMap['jsres_is_apply_start']}",
		endDate:"${sysCfgMap['jsres_is_apply_end']}",
	});
	$(".delay").hide();
	});
function toLate(){
	if($("#delayreason").is(":hidden")){
		$(".back").hide();
		$(".delay").show();
		$(".back").val("");
		$("#delay").val("取消延期");
		if($("#back").val()=="取消退培"){
			$("#back").val("退培");
		}
	}else{
		$(".delay").hide();
		$(".delay").val("");
		$("#delay").val("延期");
	}
}

<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
	var code = '383840403739373966656665';//3840403739373966656665上38、上38、下40、下40、左37、右39、左37、右39、B66、A65、B66、A65
	var currCode = '';
	var timeoutObj ;
	$(function(){
		$(document).on('keyup',function(e){
			currCode+=e.keyCode;
			clearTimeout(timeoutObj);
			timeoutObj = setTimeout(function(){
				currCode = '';
			},500);
			if(currCode==code){
				endFunction();
				currCode = '';
			}
		});
	});
</c:if>
function endFunction(){
	$(".show").hide();
	$(".change").show();
}
</script>
</head>
<body>
<div class="infoAudit">
<div class="div_table">
	<input type="hidden" id="upFileId"/>
	<form id="editForm" action="<s:url value="/jszy/manage/updateDoctorTrend"/>" method="post" enctype="multipart/form-data">
	<input type="hidden" name="recruitFlow" value="${doctorRecruit.recruitFlow}"/>
	<input type="hidden" name="doctorFlow" value="${doctorRecruit.doctorFlow}"/>
	<input type="hidden" id="doctorStatusName" name="doctorStatusName"/>
	<input type="hidden" id="doctorStrikeName" name="doctorStrikeName"/>
	<input type="hidden" id="reasonName" name="reasonName"/>
	<input type="hidden" id="policyName" name="policyName"/>
	<table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
              <col width="22%"/>
              <col width="30%"/>
              <col width="20%"/>
              <col width="28%"/>
            </colgroup>
	           <tbody>
	           <tr>
	             <th>当前学位类别：</th>
	             <td class="show">${doctorRecruit.currDegreeCategoryName }</td>
	             <td class="change" style="display: none;">
	            	 <select name="currDegreeCategoryId" id="currDegreeCategoryId" class="select validate[required]" style="width: 160px;">
	                  <option value="">请选择</option>
					  <c:forEach items="${jszyResDegreeCategoryEnumList}" var="degreeCategory">
					     <option value="${degreeCategory.id}" ${doctorRecruit.currDegreeCategoryId eq degreeCategory.id?'selected':''}>${degreeCategory.name}</option>
					  </c:forEach>
	               </select>&#12288;<span class="red">*</span>
	            </td>

	             <th>审核状态：</th>
	             <td>${doctorRecruit.auditStatusName }</td>
	           </tr>
	           <tr>
	               <th>规培起始日期：</th>
	               <td class="show">${doctorRecruit.recruitDate}</td>
	               <td class="change" style="display: none;"><input type="text" id="recruitDate" name="recruitDate" value="${doctorRecruit.recruitDate}" class="validate[required] input" onchange="" readonly="readonly" style="margin: 0;height: 28px"/>&#12288;<span class="red">*</span></td>
	               <th>届&#12288;&#12288;别：</th>
	               <td class="show">${doctorRecruit.sessionNumber }</td>
	                 <td class="change" style="display: none;"><input type="text" id="sessionNumber" name="sessionNumber" value="${doctorRecruit.sessionNumber}" class="validate[required] input" onchange="" readonly="readonly" style="margin: 0;height: 28px"/>&#12288;<span class="red">*</span></td>
	           </tr>
	           <tr>
	               <th>所在地区：</th>
	               <td>${doctorRecruit.placeName}</td>
	               <th>培训基地：</th>
	               <td>${doctorRecruit.orgName}</td>
	           </tr>
	           <tr>
	               <th>培训类别：</th>
	               <td>${doctorRecruit.catSpeName}</td>
	               <th>培训专业：</th>
	               <td>${doctorRecruit.speName}</td>
	           </tr>
			   <c:if test="${doctorRecruit.catSpeId eq jszyTrainCategoryEnumChineseMedicine.id}">
				   <tr>
					   <th>二级专业：</th>
					   <td>${doctorRecruit.secondSpeName}
					   </td>
					   <th></th>
					   <td>
					   </td>
				   </tr>
			   </c:if>
	           <tr>
	               <th>培养年限：</th>
	               <td class="show">
	                 <c:forEach items="${jszyResTrainYearEnumList }" var="trainYear">
	                     <c:if test="${doctorRecruit.trainYear eq trainYear.id}">${trainYear.name }</c:if>
	                 </c:forEach>
	               </td>
	               <td class="change" style="display: none;">
               		 <select name="trainYear" id="trainYear" class="validate[required] select" style="width: 160px;"  >
	               			<option value="">请选择</option>
	               		     <c:forEach items="${jszyResTrainYearEnumList }" var="trainYear">
						     	 <option value="${trainYear.id}" ${doctorRecruit.trainYear eq trainYear.id?'selected':''}>${trainYear.name}</option>
					       	 </c:forEach>
						</select>&#12288;<span class="red">*</span>
	               </td>
	               <th>结业考核年份：</th>
	               	<td>${doctorRecruit.graduationYear}</td>
	           </tr>
	           <tr>
	               <th><span class="red">*</span>&#12288;医师状态：</th>
	               <td>
		               <select class="validate[required] select" style="width: 160px;" name="doctorStatusId" id="doctorStatusId" >
		               <option value="">请选择</option>
		               <c:forEach items="${dictTypeEnumDoctorStatusList}" var="dict">
						    <option value="${dict.dictId}" <c:if test="${doctorRecruit.doctorStatusId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
					    </c:forEach>
				       </select>
				   </td>
	               <th><span class="red">*</span>&#12288;医师走向：</th>
	               <td>
	               	<select class="validate[required] select" style="width: 160px;" name="doctorStrikeId" id="doctorStrikeId">
	               	 	<option value="">请选择</option>
	               	 	<c:forEach items="${dictTypeEnumDoctorStrikeList}" var="strike">
	               			  <option value="${strike.dictId}" <c:if test="${doctorRecruit.doctorStrikeId==strike.dictId}">selected="selected"</c:if>>${strike.dictName}</option>
					    </c:forEach>
				    </select>
	               </td>
	           </tr>
	           <tr>
	           		<th><span class="red">*</span>&#12288;已培养年限：</th>
	               <td colspan="3">
	                  <select class="validate[required] select" name="yetTrainYear" style="width: 160px;">
		               <option value="">请选择</option>
		               <c:forEach items="${dictTypeEnumYetTrainYearList}" var="dict">
						    <option value="${dict.dictId}" <c:if test="${doctorRecruit.yetTrainYear==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
					    </c:forEach>
				       </select>
	               </td>
	           </tr>
			   <tr class="back">
				   <th><span class="red">*</span>&#12288;退培类型：</th>
				   <td colspan="3">
					   <select class="validate[required] select back autoValue"  id="policyId" name="policyId" style="width: 160px;" onchange="changePolicy(this);">
						   <option value="">请选择</option>
						   <option value="1">协议退培</option>
						   <option value="2">违约退培</option>
					   </select>
					   <input id="policy" name="policy" value="" class="validate[required] back input" id="policy" style="display: none;width: 460px;height: 28px" />
				   </td>
			   </tr>
			   <tr class="back">
					<th><span class="red">*</span>&#12288;退培主要原因：</th>
					<td colspan="3">
						<select class="validate[required] select  back autoValue" id="reasonId" name="reasonId" style="width: 160px;" onchange="changeReason(this);">
							<option value="">请选择</option>
							<option value="1">辞职</option>
							<option value="2">考研</option>
							<option value="3">其他</option>
						</select>
						<input id="reason" name="reason" value="" class="validate[required] back input" id="reason" style="display: none;width: 460px;height: 28px" />
					</td>
				</tr>
				<tr class="back">
					<th><span class="red">*</span>&#12288;学员去向：</th>
					<td colspan="3">
						<input  id="dispositon" name="dispositon" value="" class="validate[required] back input" style="width: 623px;height: 28px"/>
					</td>
				</tr>
				<tr class="back">
					<th><span class="red">*</span>&#12288;备注（培训基地意见）：</th>
					<td colspan="3">
						<input id="remark" name="remark"  value="" class="validate[required] back input" style="width: 623px;height: 28px "/>
					</td>
				</tr>
               <script>
                   function add(exp){
                       $(exp).parents('tr').after("<tr class='back' name='backFile'>" +
                               "<th></th>" +
                               "<td colspan='3'>" +
                               "<input type='file' name='files' class='validate[required]' multiple='multiple'/><span class='red'>*</span>" +
                               "<span style='float: right;padding-right: 343px'>" +
                               "<img class='opBtn' title='新增' src=\"<s:url value='/css/skin/${skinPath}/images/add3.png' />\"style='cursor: pointer;' onclick='add(this)'></img>&#12288;" +
                               "<img class='opBtn' title='删除'  style='cursor: pointer;' src=\"<s:url value='/css/skin/${skinPath}/images/del1.png'/>\" onclick='delTr(this);'></img>" +
                               "</span></td></tr>" +
                               "");
                   }
                   function delTr(exp){
                       $(exp).parents('tr').remove();
                   }
               </script>
               <tr class="back" name="backFile">
                   <th><span class="red">*</span>上传附件：</th>
                   <td colspan="3">
                       <input type="file" name="files" class="validate[required]" multiple="multiple"/>
					    <span style="float: right;padding-right: 360px">
								<%--<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add(this)"></img>&#12288;--%>
						</span>
                   </td>
               </tr>
				<tr class="delay">
					<th><span class="red">*</span>&#12288;延期原因：</th>
					<td colspan="3">
						<input id="delayreason" name="delayreason"  value="" class="validate[required] delay input" style="width: 623px;height: 28px"/>
					</td>
				</tr>
				<tr class="delay">
					<th>
						<span class="red">*</span>&#12288;上传附件：
					</th>
					<td>
						<input type="hidden" id="delayPicUrlValue" name="delayFilePath" value="" class="delay" />
                  	  <label id="delayPicUrlSpan" style="display:${!empty delayPicValueFile?'':'none'}">
						[<a href="${sysCfgMap['upload_base_url']}/${delayPicValueFile}" target="_blank">查看图片</a>]&nbsp;
				      </label>
					  <a id="delayPicUrl" href="javascript:chooseFile('delayPicUrl');" class="btn">${empty delayPicValueFile?'':'重新'}上传</a>&nbsp;
					  <a id="delayPicUrlDel" href="javascript:delFile('delayPicUrl');" class="btn" style="${empty delayPicValueFile?'display:none':''}">删除</a>
					</td>
					<th><span class="red">*</span>&#12288;结业考核年份：</th>
					<td>
						<input type="text" class="input delay validate[required]" id="time" name="graduationYear"value="" readonly="readonly"style=";height: 28px"/>&nbsp;
					</td>
				</tr>
	           </tbody>
           </table>
           </form>
           <form id="fileForm" method="post" enctype="multipart/form-data">
				<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
  		   </form>
          <div class="btn_info" style="padding-bottom: 50px;">
			<input type="button" style="width:100px;" class="btn_brown"  onclick="save();" value="保存"/>
			<c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope.userListScope}">
				<input type="button" style="width:100px;" id="delay" class="btn_red" onclick="toLate();" value="延期"/>
				<c:if test="${isFlag eq  GlobalConstant.FLAG_Y }">
					<input type="button" style="width:100px;" id="back" class="btn_red" onclick="backTrain();" value="退培"/>
				</c:if>
				<input type="button" style="width:100px;" class="btn_brown" onclick="javascript:jboxClose();" value="关闭"/>
			</c:if>
		 </div>
 </div>
 </div>
</body>
</html>