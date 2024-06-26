<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
	var doctorFlow = $("#doctorFlow").val();
	if($("#reasonId").is(":hidden")){
		if($("#delayreason").is(":hidden")){
			info="保存";
			jboxConfirm("确认"+info+"?",function () {
				saveInfo();
			});
		}else{
			info="延期";
			jboxConfirm("确认"+info+"?",function () {
			    var graduactionYear = $("#time").val();
				jboxPost("<s:url value='/jsres/manage/checkDelay'/>?",{doctorFlow : doctorFlow, graduactionYear : graduactionYear},function(resp){
					if(resp != "Y" ){
						jboxTip(resp);
						return false;
					}else {
						saveInfo();
					}
				},null,false);

			});
		}
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
	var url='<s:url value="/jsres/manage/updateDoctorTrend"/>';
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
	var url="<s:url value='/jsres/manage/checkStatus'/>";
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
function checkFile2(file){
	var filePath = file.value.toUpperCase();
	if(filePath==""){
		return false;
	}
	var types = "JPG,JPEG,PNG,PDF".split(",");
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
		jboxTip("请上传&nbsp;JPG,JPEG,PNG,PDF格式的图片");
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
	var url = "<s:url value='/jsres/doctor/uploadDelayFile'/>";
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

	<c:if test="${empty ret}">
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
	<form id="editForm" action="<s:url value="/jsres/manage/updateDoctorTrend"/>" method="post" enctype="multipart/form-data">
	<input type="hidden" name="recruitFlow" value="${doctorRecruit.recruitFlow}"/>
	<input type="hidden" name="doctorFlow" id="doctorFlow" value="${doctorRecruit.doctorFlow}"/>
	<input type="hidden" id="doctorStatusName" name="doctorStatusName"/>
	<input type="hidden" id="doctorStrikeName" name="doctorStrikeName"/>
	<input type="hidden" id="reasonName" name="reasonName"/>
	<input type="hidden" id="policyName" name="policyName"/>
	<table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
              <col width="20%"/>
              <col width="30%"/>
              <col width="20%"/>
              <col width="30%"/>
            </colgroup>
	           <tbody>
	           <tr>
	             <th><span class="red">*</span>当前学位类别：</th>
	             <td class="show">${doctorRecruit.currDegreeCategoryName }</td>
	             <td class="change" style="display: none;">
	            	 <select name="currDegreeCategoryId" id="currDegreeCategoryId" class="select validate[required]" style="width: 160px;">
	                  <option value="">请选择</option>
					  <c:forEach items="${jsResDegreeCategoryEnumList}" var="degreeCategory">
					     <option value="${degreeCategory.id}" ${doctorRecruit.currDegreeCategoryId eq degreeCategory.id?'selected':''}>${degreeCategory.name}</option>
					  </c:forEach>
	               </select>&#12288;
	            </td>
	             
	             <th>审核状态：</th>
				   <c:set var="modifyTime" value="${pdfn:transDateTime(jsresRecruitDocInfo.modifyTime)}"></c:set>
				   <c:set var="modifyTime2" value="${pdfn:transDateTime(doctorRecruit.modifyTime)}"></c:set>
				   <c:if test="${jsResDoctorAuditStatusEnumNotSubmit.id eq doctorRecruit.auditStatusId
											or jsResDoctorAuditStatusEnumAuditing.id eq doctorRecruit.auditStatusId}">
					   <td>${doctorRecruit.auditStatusName}</td>
				   </c:if>
				   <c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId
											or jsResDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}">
					   <td>${doctorRecruit.auditStatusName}<label>&#12288;${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId ?modifyTime:modifyTime2}</label></td>
				   </c:if>
	           </tr>
	           <tr>
	               <th><span class="red">*</span>规培起始日期：</th>
	               <td class="show">${doctorRecruit.recruitDate}</td>
	               <td class="change" style="display: none;"><input type="text" id="recruitDate" name="recruitDate" value="${doctorRecruit.recruitDate}" class="validate[required] input" onchange="" readonly="readonly" style="margin: 0;height: 28px"/>&#12288;</td>
	               <th><span class="red">*</span>届别：</th>
	               <td class="show">${doctorRecruit.sessionNumber }</td>
	                 <td class="change" style="display: none;"><input type="text" id="sessionNumber" name="sessionNumber" value="${doctorRecruit.sessionNumber}" class="validate[required] input" onchange="" readonly="readonly" style="margin: 0;height: 28px"/>&#12288;</td>
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
	           <tr>
	               <th><span class="red">*</span>培训年限：</th>
	               <td class="show">
	                 <c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
	                     <c:if test="${doctorRecruit.trainYear eq trainYear.id}">${trainYear.name }</c:if>
	                 </c:forEach>
	               </td>
	               <td class="change" style="display: none;">
               		 <select name="trainYear" id="trainYear" class="validate[required] select" style="width: 160px;"  >
	               			<option value="">请选择</option>
	               		     <c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
						     	 <option value="${trainYear.id}" ${doctorRecruit.trainYear eq trainYear.id?'selected':''}>${trainYear.name}</option>
					       	 </c:forEach>
						</select>&#12288;
	               </td>
	               <th>结业考核年份：</th>
	               	<td>${doctorRecruit.graduationYear}</td>
	           </tr>
			   <script>
				   function changeDocStatue(resp){
					   var docStatue = $(resp).find("option:selected").val();
					   if(docStatue == "21"){
							$("#specialFile").show();
					   }else {
						    $("#specialFile").hide();
					   }
				   }
			   </script>
	           <tr>
	               <th><span class="red">*</span>学员状态：</th>
	               <td>
		               <select class="validate[required] select" onchange="changeDocStatue(this);" style="width: 160px;" name="doctorStatusId" id="doctorStatusId" >
		               <option value="">请选择</option>
		               <c:forEach items="${dictTypeEnumDoctorStatusList}" var="dict">
						    <option value="${dict.dictId}" <c:if test="${doctorRecruit.doctorStatusId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
					    </c:forEach>
				       </select>&#12288;
				   </td>
	               <th><span class="red">*</span>学员走向：</th>
	               <td>
	               	<select class="validate[required] select" style="width: 160px;" name="doctorStrikeId" id="doctorStrikeId">
	               	 	<option value="">请选择</option>
	               	 	<c:forEach items="${dictTypeEnumDoctorStrikeList}" var="strike">
	               			  <option value="${strike.dictId}" <c:if test="${doctorRecruit.doctorStrikeId==strike.dictId}">selected="selected"</c:if>>${strike.dictName}</option>
					    </c:forEach>
				    </select>&#12288;
	               </td>
	           </tr>
	           <tr>
	           		<th><span class="red">*</span>已培训年限：</th>
	               <td colspan="3">
	                  <select class="validate[required] select" name="yetTrainYear" style="width: 160px;">
		               <option value="">请选择</option>
		               <c:forEach items="${dictTypeEnumYetTrainYearList}" var="dict">
						    <option value="${dict.dictId}" <c:if test="${doctorRecruit.yetTrainYear==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
					    </c:forEach>
				       </select>&#12288;
	               </td>
	           </tr>
			   <tr id="specialFile" <c:if test="${doctorRecruit.doctorStatusId != '21'}">style="display: none"</c:if>>
				   <th>结业证书附件：</th>
				   <td>
					   <input type="hidden" id="specialFileUrlValue" name="specialFileUrl" value="${doctorRecruit.specialFileUrl}"/>
                        <span id="specialFileUrlSpan" style="display:${!empty doctorRecruit.specialFileUrl?'':'none'}">
							[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.specialFileUrl}" target="_blank">查看图片</a>]&nbsp;
						</span>
					   <a id="specialFileUrl" href="javascript:chooseFile('specialFileUrl');" class="btn">${empty doctorRecruit.specialFileUrl?'':'重新'}上传</a>&nbsp;
					   <a id="specialFileUrlDel" href="javascript:delFile('specialFileUrl');" class="btn" style="${empty doctorRecruit.specialFileUrl?'display:none':''}">删除</a>
				   </td>
				   <th>结业证书编号：</th>
				   <td>
					   <input style="width: 160px;height: 30px;" name="specialCertNo" value="${doctorRecruit.specialCertNo}"  class="input"/>
				   </td>
			   </tr>
			   <tr class="back">
				   <th><span class="red">*</span>退培类型：</th>
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
					<th><span class="red">*</span>退培主要原因：</th>
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
					<th><span class="red">*</span>学员去向：</th>
					<td colspan="3">
						<input  id="dispositon" name="dispositon" value="" class="validate[required] back input" style="width: 623px;height: 28px"/>
					</td>
				</tr>        		
				<tr class="back">
					<th><span class="red">*</span>备注（培训基地意见）：</th>
					<td colspan="3">
						<input id="remark" name="remark"  value="" class="validate[required] back input" style="width: 623px;height: 28px "/>
					</td>
				</tr>
			   <script>
				   function add(exp){
					   $(exp).parents('tr').after("<tr class='back' name='backFile'>" +
							   "<th></th>" +
							   "<td colspan='3'>" +
							   "<input type='file' name='files' class='validate[required]' multiple='multiple' onchange='checkFile2(this)'/><span class='red'>*</span>" +
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
				   		<input type="file" name="files" class="validate[required]" multiple="multiple" onchange="checkFile2(this)"/>
					    <span style="float: right;padding-right: 360px">
								<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add(this)"></img>&#12288;
						</span>
				   </td>
			    </tr>
				<tr class="delay">
					<th><span class="red">*</span>延期原因：</th>
					<td colspan="3">
						<input id="delayreason" name="delayreason"  value="" class="validate[required] delay input" style="width: 623px;height: 28px"/>
					</td>
				</tr>
				<tr class="delay">
					<th>
						 上传附件：	
					</th>
					<td>
						<input type="hidden" id="delayPicUrlValue" name="delayFilePath" value="" class="delay" />
                  	  <label id="delayPicUrlSpan" style="display:${!empty delayPicValueFile?'':'none'}">
						[<a href="${sysCfgMap['upload_base_url']}/${delayPicValueFile}" target="_blank">查看图片</a>]&nbsp;
				      </label>
					  <a id="delayPicUrl" href="javascript:chooseFile('delayPicUrl');" class="btn">${empty delayPicValueFile?'':'重新'}上传</a>&nbsp;
					  <a id="delayPicUrlDel" href="javascript:delFile('delayPicUrl');" class="btn" style="${empty delayPicValueFile?'display:none':''}">删除</a>
					</td>
					<th><span class="red">*</span>结业考核年份：</th>
					<td>
						<input type="text" class="input delay validate[required]" id="time" name="graduationYear" value="" readonly="readonly"style=";height: 28px"/>&nbsp;
					</td>
				</tr>      	
	           </tbody>
           </table>
           </form>
           <form id="fileForm" method="post" enctype="multipart/form-data">
				<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
  		   </form>
          <div class="btn_info">
			  <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
				  <c:if test="${empty ret}">
					  <input type="button" style="width:100px;" class="btn_green"  onclick="save();" value="保存"></input>
					  <input type="button" style="width:100px;" class="btn_green"  onclick="endFunction();" value="编辑"></input>
				  </c:if>
				  <c:if test="${not empty ret}">
					  <font color="red">学员待退培审核中</font>
				  </c:if>
			  </c:if>
			<c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope.userListScope}">
				<c:if test="${empty ret}">
					<input type="button" style="width:100px;" class="btn_green"  onclick="save();" value="保存"></input>
					<c:if test="${doctorRecruit.doctorStatusId eq '20'}">
						<input type="button" style="width:100px;" id="delay" class="btn_red" onclick="toLate();" value="延期"></input>
					</c:if>
					<c:if test="${isFlag eq  GlobalConstant.FLAG_Y }">
						<input type="button" style="width:100px;" id="back" class="btn_red" onclick="backTrain();" value="退培"></input>
					</c:if>
				</c:if>
				<c:if test="${not empty ret}">
					<font color="red">学员待退培审核中</font>
				</c:if>
				<input type="button" style="width:100px;" class="btn_green" onclick="javascript:jboxClose();" value="关闭"></input>
			</c:if>
		 </div>
 </div>
 </div>
</body>
</html>