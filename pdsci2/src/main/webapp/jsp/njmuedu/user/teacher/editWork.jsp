<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="userCenter" value="true"/>
	<jsp:param name="findCourse" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<style type="text/css">

</style>


<script type="text/javascript">
$(document).ready(function(){
	$("#file").live("change",function(){
		uploadFile();
	});
});


function getChapterList(){
	//清空章节！！！
	$("#chapterFlow option[value != '']").remove();
	var courseFlow = $("#courseFlow").val();
	if(courseFlow == undefined || courseFlow == null || courseFlow == ""){
		return false;
	}
	jboxStartLoading();
	var url = "<s:url value='/njmuedu/user/getChapterListByCourseFlow?courseFlow='/>" + courseFlow;
	jboxGet(url, null, function(resp){
			jboxEndLoading();
		   		var dataObj = resp;
		   		if(dataObj.length > 0){
	   				for(var i = 0; i<dataObj.length; i++){
				     	var chapterFlow = dataObj[i].chapterFlow;
			    		var chapterName = dataObj[i].chapterName;
				     	$option =$("<option></option>");
				     	$option.attr("value", chapterFlow);
				     	$option.text(chapterName);
				     	$("#chapterFlow").append($option);
				   }
		   		}
		   		if(""!="${chapter.chapterFlow}"){
					 $("#chapterFlow option[value='${chapter.chapterFlow}']").attr("selected",true);
				}
		}, null , false);
}

/*
 * 选择文件：
 */
function chooseFile(id){
	$("#upFileId").val(id);
	return $("#file").click();
}

/**
 * 检查文件格式
 */
function checkFile(file){
	var filePath = file.value;
	var types = "${sysCfgMap['njmuedu_courseFile_support_suffix']}".split(",");
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
		jboxTip("请上传&nbsp;${sysCfgMap['njmuedu_courseFile_support_suffix']}格式的文件");
		return false;
	}else{
		return true;
	}
}

/**
 * 上传作业
 */
function uploadFile(){
	if(false == $("#fileForm").validationEngine("validate")){
		return false;
	}
	jboxStartLoading();
	var url = "<s:url value='/njmuedu/user/uploadTeacherWork'/>";
	jboxSubmit($("#fileForm"),url,function(resp){
					jboxEndLoading();
					if("${GlobalConstant.UPLOAD_FAIL}" != resp){
						var index = resp.indexOf("/");
						if(index != -1){
							jboxTip("${GlobalConstant.UPLOAD_SUCCESSED}");
							returnUrl(resp);
						}else{//验证文件信息
							jboxInfo(resp);
						}
				    }
				}, null, false);
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

/*
 * 发布作业
 */
function save(){
	if(false==$("#editForm").validationEngine("validate")){
		return false;
	}
	jboxStartLoading();
	var data = $("#editForm").serialize();
	var url = "<s:url value='/njmuedu/user/saveTeacherWork'/>";
	jboxPost(url, data, function(resp){
			jboxEndLoading();
			window.parent.loadTeacherWorkList();
			if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
				jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				setTimeout(function(){
					jboxClose();
				}, 1000);
			}
		}, null, false);
}



</script>

</head>
<body style="background: none;">
<!-- 
<div class="registerMsg-m2 fl" style="margin-left:0; width:100%;">
<div class="registerMsg-m-inner registerBgw">
<div class="registerMsg-tabs" style="background: none;padding:0 10px 20px;">
<div class="module-content">
 -->
<div class="part editTeacher" style="border: none;">
	
	<input type="hidden" id="upFileId"/>

	<form id="editForm">
		<input type="hidden" name="userFlow" value="${sessionScope.currUser.userFlow}"/>
		<table border="0" cellpadding="0" cellspacing="0" class="course-table course-table1" style="border:1px solid #d4e7f0;">
			<tr>
				<th width="20%">课程名称：</th>
				<td width="80%">
					<select name="courseFlow" id="courseFlow" onchange="getChapterList();" class="validate[required] select" style="width: 160px;">
			       		<option value="">请选择</option>
			       		<c:forEach items="${courseList}" var="course">
			  				<option value="${course.courseFlow}" ${course.courseFlow eq param.courseFlow?'selected':''}>${course.courseName}</option>
			  			</c:forEach>
			       	</select>
				</td>
			</tr>
			<tr>
				<th>章节名称：</th>
				<td>
					<select name="chapterFlow" id="chapterFlow" class="validate[required] select" style="width: 160px;">
			       		<option value="">请选择</option>
			       		<c:forEach items="${chapterList}" var="cc">
			  				<option value="${cc.chapterFlow}">${cc.chapterName}</option>
			  			</c:forEach>
			       	</select>
				</td>
			</tr>
			<tr>
				<th>作业文件：</th>
				<td>
					<input type="hidden" id="quesDirValue" name="quesDir"/>
					<span id="quesDirSpan" style="display:none;">
						<a href="" class="btn_g">下载</a>&nbsp;
					</span>
					<a id="quesDir" href="javascript:chooseFile('quesDir');" class="btn_g">上传</a>&nbsp;
					<a id="quesDirDel" href="javascript:delFile('quesDir');" class="btn_g" style="display:none;">删除</a>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea class="validate[required] xltxtarea" style="margin: 0px; padding: 0px;" name="questionContent"></textarea>
                </td>
			</tr>
		</table>
	</form>	

	<!-- 文件上传 -->
	<form id="fileForm" method="post" enctype="multipart/form-data" style="margin-top: 10px;">
		<input type="file" id="file" name="file" accept="${sysCfgMap['njmuedu_courseFile_support_suffix']}" style="display: none"/>
	</form>
	
</div>	

	<div style="width: 600px;" class="editBtn">
		<input type="button" class="search" id="saveBtn" onclick="save();" value="发&#12288;布"/>&nbsp;
		<input type="button" class="search2" onclick="jboxClose();" value="关&#12288;闭"/>
	</div>

</body>
</html>