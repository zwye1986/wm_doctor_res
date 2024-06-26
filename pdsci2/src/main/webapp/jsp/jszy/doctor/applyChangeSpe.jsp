<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	changeCat();
	$("#file").live("change",function(){
		uploadFile();
	});
});
function changeCat(){
	$("#"+"${recruit.catSpeId}").attr("selected",true);
	if("${recruit.catSpeId}" != "${jszyTrainCategoryEnumChineseMedicine.id}"){
		$("#secondSpeId").parent().hide();
		$("#secondSpeIdFont").parent().hide();
		$("#secondSpeIdFont").parent().prev().attr("colspan",3);
	}else {
		$("#secondSpeId").parent().show();
		$("#secondSpeIdFont").parent().show();
		$("#secondSpeIdFont").parent().prev().removeAttr("colspan");
	}

}

function chooseFile(id){
	$("#upFileId").val(id);
	$("#file").click();
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
/**
 * 上传文件
 */
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
	var url = "<s:url value='/jszy/doctor/uploadChangeSpeFile'/>";
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
function submit(){
	if(false==$("#editForm").validationEngine("validate")){
		return false;
	}
	if($("#catSpeId").val() == '${recruit.catSpeId}'
			& $("#speId").val() == '${recruit.speId}'
			& $("#secondSpeId").val() == '${recruit.secondSpeId}'){
		jboxTip("您并未变更专业，请确认！");
		return false;
	}
	if($("#changeSpeUrlValue").val()==""){
		jboxTip("上传文件不能为空！");
		return false;
	}
	var url = "<s:url value='/jszy/doctor/submitChangeSpe'/>";
	jboxConfirm("确认提交？",  function(){
		jboxStartLoading();
		$("#submitBtn").attr("disabled",true);
		jboxPost(url,  $("#editForm").serialize(),  function(resp){
			jboxEndLoading();
			window.parent.main("${param.recruitFlow}");
		    if("${GlobalConstant.SAVE_FAIL}" != resp){
		    	top.jboxTip("保存成功！");
				jboxClose();
		    }else{
		    	top.jboxTip("保存失败！");
		    	jboxClose();
		    }
		}, function(){
			jboxEndLoading();
		}, false);
	});
}
function change(obj){
	var speId = $(obj).val();
	$("#speName").val($("#" + speId).text());
}
function changeType(obj){
	if($(obj).val()!=undefined & $(obj).val() != ''){
		if($(obj).val() != "${jszyTrainCategoryEnumChineseMedicine.id}"){
			$("#secondSpeId").parent().hide();
			$("#secondSpeIdFont").parent().hide();
			$("#secondSpeIdFont").parent().prev().attr("colspan",3);
		}else {
			$("#secondSpeId").parent().show();
			$("#secondSpeIdFont").parent().show();
			$("#secondSpeIdFont").parent().prev().removeAttr("colspan");
		}
		var url = "<s:url value='/jszy/doctor/changeType'/>?orgFlow=${recruit.orgFlow}&catSpeId="+$(obj).val()+"&sessionNumber=${recruit.sessionNumber}";
		jboxGet(url, null, function(resp){
			$("select[name=speId] option[value != '']").remove();
			if(resp!=""){
		   		var dataObj = resp;
		   	 for(var i = 0; i<dataObj.length;i++){
			     	var speId = dataObj[i].speId;
			     	var speName = dataObj[i].speName;
			     	$option =$("<option></option>");
			     	$option.attr("value",speId);
			     	$option.attr("id",speId);
			     	$option.text(speName);
			     	$("select[name=speId]").append($option);
			   }
		   	 var speId=$("#validateId").val();
//		 	$("select[name=speId] option[value = "+speId+"]").remove();
			}
			}, null , false);
	}else{
		return;
	}
}
	function setSecondSpeName(vari){
		var secondSpeName = $(vari).find("option:selected").text();
		$("#secondSpeName").val(secondSpeName);
	}
</script>
</head>
<body>
<div class="div_search">
 	 <input type="hidden" id="upFileId"/>
 	 <input type="hidden" id="validateId" value="${recruit.speId}"/>
	<form id="editForm" style="position: relative;" method="post">
		<input type="hidden" name="doctorFlow" value="${recruit.doctorFlow}"/>
		<input type="hidden" name="orgFlow" value="${recruit.orgFlow}"/>
		<input type="hidden"  name="recruitFlow" value="${recruit.recruitFlow}"/>
		<input type="hidden"  id="speName" name="speName" value="${recruit.speName}"/>
		<input type="hidden"  id="secondSpeName" name="secondSpeName" value=""/>
	<table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 25px;">
		<colgroup>
			<col width="22%"/>
			<col width="27%"/>
			<col width="25%"/>
			<col width="25%"/>
		</colgroup>
    	<tr>
    		<th style="width: 80px">当前培训专业：</th><td id="td">${recruit.catSpeName}</td>
    		<th style="width: 80px"><b class="red">*</b>&#12288;意向培训专业：</th>
			<td>
				<select class="select validate[required]"  id="catSpeId" name="catSpeId"  style="width: 120px;float:left;" onchange="changeType(this);">
					<option value="">请选择</option>
					<c:forEach items="${jszyTrainCategoryEnumList }" var="type">
						<c:if test="${type.isView eq 'Y'}">
							<option value="${type.id}" id="${type.id}">${type.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
    	</tr>
    	<tr>
    		<th style="width: 80px">当前对应专业：</th>
    		<td style="width: 100px"><label>${recruit.speName}</label></td>
    		<th style="width: 80px"><b class="red">*</b>&#12288;意向对应专业：</th>
    		<td style="width: 150px">
    			<select class="select validate[required]" id="speId"  name="speId"  style="width: 120px;float:left;" onchange="change(this);">
    				<option value="">请选择</option>
    				<c:forEach items="${speList}" var="spe">
						<option value="${spe.speId }" id="${spe.speId}">${spe.speName}</option>
    				</c:forEach>
    			</select>
    		
    		</td>
    	</tr>
		<tr>
			<th style="width: 80px">当前二级专业：</th>
			<td style="width: 100px"><label>${recruit.secondSpeName}</label></td>
			<th style="width: 80px"><b class="red" id="secondSpeIdFont">*</b>&#12288;意向二级专业：</th>
			<td style="width: 150px">
				<select class="select validate[required]" id="secondSpeId" name="secondSpeId"  style="width: 120px;float:left;" onchange="setSecondSpeName(this);">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="secondSpe">
						<option value="${secondSpe.dictId }" id="${secondSpe.dictId}">${secondSpe.dictName}</option>
					</c:forEach>
				</select>

			</td>
		</tr>
    	<tr>
			<th><b class="red">*</b>&#12288;选择导入：</th>
			<td colspan="3">
				<input type="hidden" id="changeSpeUrlValue" name="speChangeApplyFile" value="" />
                  	  <label id="changeSpeUrlSpan" style="display:${!empty speChangeApplyFile?'':'none'}">
						[<a href="${sysCfgMap['upload_base_url']}/${speChangeApplyFile}" target="_blank">查看图片</a>]&nbsp;
				      </label>
					  <a id="changeSpeUrl" href="javascript:void(0);" onclick="chooseFile('changeSpeUrl');" class="btn">${empty speChangeApplyFile?'':'重新'}上传</a>&nbsp;
					  <a id="changeSpeUrlDel" href="javascript:void(0);" onclick="delFile('changeSpeUrl');" class="btn" style="${empty speChangeApplyFile?'display:none':''}">删除</a>
			</td>
		</tr>
		<tr>
			<th>模板文件：</th>
			<td colspan="3"><a href="<s:url value='/jsp/jszy/daochu/changeTrainSpe.docx'/>">培训专业变更申请表.docx</a></td>
		</tr>
    </table>
    </form>
      <form id="fileForm" method="post" enctype="multipart/form-data">
		<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
	  </form> 
	    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
		<input type="button" style="width:100px;" class="btn_red" onclick="submit();" id="submitBtn" value="提交"/>
		<input type="button" style="width:100px;" class="btn_brown" onclick="jboxClose();" value="关闭"/>
		</div>
 </div>
</body>
</html>