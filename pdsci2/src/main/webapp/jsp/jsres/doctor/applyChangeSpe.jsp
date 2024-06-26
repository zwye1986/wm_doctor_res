<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
/**
 * 上传文件
 */
function changeCat(){
	$("#"+"${recruit.catSpeId}").attr("selected",true);
	if("${recruit.catSpeId}"=="${trainCategoryEnumWMSecond.id}"){
		$("#td").attr("colspan",3);
	}
	if("${recruit.catSpeId}"=="${trainCategoryEnumWMFirst.id}"){
		$("#"+"${trainCategoryEnumDoctorTrainingSpe.id}").hide();
	}
	if("${recruit.catSpeId}"=="${trainCategoryEnumDoctorTrainingSpe.id}"){
		$("#"+"${trainCategoryEnumWMFirst.id}").hide();
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
	var url = "<s:url value='/jsres/doctor/uploadChangeSpeFile'/>";
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
	if($("#changeSpeUrlValue").val()==""){
		jboxTip("上传文件不能为空！");
		return false;
	}
	var url = "<s:url value='/jsres/doctor/submitChangeSpe'/>";
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
	var speId=$(obj).val();
	$("#speName").val($("#"+speId).text());
}
function changeType(obj){
	if($(obj).val()!=undefined){
		var url = "<s:url value='/jsres/doctor/changeType'/>?orgFlow=${recruit.orgFlow}&catSpeId="+$(obj).val()+"&sessionNumber=${recruit.sessionNumber}";
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
		 	$("select[name=speId] option[value = "+speId+"]").remove();
			}
			}, null , false);
	}else{
		return;
	}
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
	<table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 25px;">
    	<tr>
    		<th style="width: 80px">当前的培养类型：</th><td id="td">${recruit.catSpeName}</td>
   			<c:if test="${trainCategoryEnumWMSecond.id != recruit.catSpeId}">
    		<th style="width: 80px">变更后的培养类型</th>
	    		<td>
	    			<select class="select validate[required]"  id="catSpeId" name="catSpeId"  style="width: 120px;float:left;" onchange="changeType(this);">
	    				<c:forEach items="${trainCategoryEnumList }" var="type">
	    				<option value="${type.id}" id="${type.id}"
	    				<c:if test="${type.id eq trainCategoryEnumWMSecond.id }">style="display: none;"</c:if>
	    				>${type.name}</option>
	    				</c:forEach>
	    			</select><span style="color: red; float:left; margin-left:5px;">*</span>
	    		</td>
    		</c:if>
    	</tr>
    	<tr>
    		<th style="width: 80px">当前的专业：</th>
    		<td style="width: 100px"><label>${recruit.speName}</label></td>
    		<th style="width: 80px">变更后专业：</th>
    		<td style="width: 150px">
    			<select class="select validate[required]"  name="speId"  style="width: 120px;float:left;" onchange="change(this);">
    				<option value="">请选择</option>
    				<c:forEach items="${speList}" var="spe">
						<option value="${spe.speId }" id="${spe.speId}"
						<c:if test="${param.speId eq spe.speId }">style="display: none;"</c:if>						
						>${spe.speName}</option>    				
    				</c:forEach>
    			</select>
    			<span style="color: red; float:left; margin-left:5px;">*</span>
    		
    		</td>
    	</tr>
    	<tr>
			<th>选择导入：</th>
			<td colspan="3">
				<input type="hidden" id="changeSpeUrlValue" name="speChangeApplyFile" value="" />
                  	  <label id="changeSpeUrlSpan" style="display:${!empty speChangeApplyFile?'':'none'}">
						[<a href="${sysCfgMap['upload_base_url']}/${speChangeApplyFile}" target="_blank">查看图片</a>]&nbsp;
				      </label>
					  <a id="changeSpeUrl" href="javascript:void(0);" onclick="chooseFile('changeSpeUrl');" class="btn">${empty speChangeApplyFile?'':'重新'}上传</a>&nbsp;
					  <a id="changeSpeUrlDel" href="javascript:delFile('changeSpeUrl');" class="btn" style="${empty speChangeApplyFile?'display:none':''}">删除</a>
					  &nbsp;<label class="red">*</label>
			</td>
		</tr>
		<tr>
			<th>模板文件：</th>
			<td colspan="3"><a href="<s:url value='/jsp/jsres/daochu/changeTrainSpe.docx'/>">培训专业变更申请表.docx</a></td>
		</tr>
    </table>
    </form>
      <form id="fileForm" method="post" enctype="multipart/form-data">
		<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
	  </form> 
	    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
		<input type="button" style="width:100px;" class="btn_red" onclick="submit();" id="submitBtn" value="提交"></input>
		<input type="button" style="width:100px;" class="btn_grey" onclick="jboxClose();" value="关闭"></input>
		</div>
 </div>
</body>
</html>