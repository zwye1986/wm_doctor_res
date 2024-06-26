<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
var countDiv=${imageList.size()};
/* function checkFile(file){
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
	}
} */

/* function checkUploadFile(){
	jboxTip("上传成功!");
	doClose();
} */

function reupload(){
	$("#existsFile").hide();
	$("#file").show();
	$("#canBtn").show();
	$("#upBtn").hide();
}
function cancleReupload(){
	$("#existsFile").show();
	$("#file").hide();
	$("#canBtn").hide();
	$("#upBtn").show();
}

function reUpload(){
	$('#uploadErrorDiv').hide();
	$('#uploadFileDiv').show();
}

function doClose() {
	var openDialog = top.dialog.get('openDialog');
	if(openDialog !=null && openDialog.open){
		openDialog.close().remove();
	}
}
function add(){
	/* var imageLength=$(".imageOper").length; */
	var a = $("#s").clone();
	a.show().attr("id","").appendTo("#f");
	var file=$(":file",a);
	file.attr("id",file.attr("id")+(++countDiv)).change(function(){
		checkFile(this);
	});
	$("a",a).click(function(){
		file.click();
	});
}
$(document).ready(function(){
	if($(".imageOper").length<=1){
		add();
	}
});	

function del(obj){
	var operDiv=$(obj).closest(".imageOper");
	var id=operDiv.attr("id");
	if($(".imageOper").length==2&&id==null){
		
	}else{
		jboxConfirm("确认删除？", function() {
			if(id){
					var imageFlow=$(operDiv).attr("id");
					var url="<s:url value='/res/rec/resRecImgDelete'/>?recFlow=${rec.recFlow}"+"&imageFlow="+imageFlow;
					jboxPost(url,null,function(s){
						if(s=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
							operDiv.remove();
							jboxTip("删除成功!");
							if($(".imageOper").length<=1){
								add();
							}
						}
						if(s=="${GlobalConstant.OPRE_FAIL_FLAG}"){
							jboxTip("删除失败!");
						}
					 },null,false);
			}else{
					operDiv.remove();
					if($(".imageOper").length<=1){
						add();
					}
			}
			});
	}
};
function checkFile(obj){
	var id = obj.id;
	$.ajaxFileUpload({
		url:"<s:url value='/res/rec/resRecImgUpload'/>?recFlow=${rec.recFlow}",
		secureuri:false,
		fileElementId:id,
		dataType: 'json',
		success: function (data){
			data=eval("("+data+")");
			if(data){
				var status=data.status;
				var imgOperDiv=$("#"+id).closest(".imageOper");
				if(status=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					$(".imgc",imgOperDiv).attr("src",data.url).show();
					imgOperDiv.attr("id",data.flow);
					$(".upload",imgOperDiv).remove();
					jboxTip("上传成功！");
				}else{
					imgOperDiv.remove();
					add();
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
};
</script>
</head>
<body>
<div class="infoAudit">
			<div style="margin-bottom: 30px;">
			<table  style="width: 100%;margin-bottom: 5px;margin-top: 0px;border: 1px solid #e3e3e3;">
				<tr>
					<th style="border-bottom: 1px solid #e3e3e3;"><div style="float:left;">出科考核表</div>
					<c:if test="${hideApprove eq 'null'}">
						<div  style="float: right;"><img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="margin-right: 10px;cursor: pointer;" onclick="add();"/></div>
					</c:if>
					</th>
				</tr>
			</table>

			<div style="height: 400px;overflow: auto;">
			<table  style="width: 100%;margin-bottom: 5px;margin-top: 0px;border: 1px solid #e3e3e3;">
				<tr>
					<td style="border-bottom: 1px solid #e3e3e3;" id="f">
						<c:forEach items="${imageList}" var="list" varStatus="status">
						<div class="imageOper" style="border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 160px;height: 150px;float: left;text-align: center;" id="${list.imageFlow}">
							<c:if test="${hideApprove eq 'null'}">
								<div style="float: right;padding-top:5px;padding-right: 5px;position: relative;"><img title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />" style="margin-right: 5px;cursor: pointer;" onclick="del(this);"/></div>
								<a target="_blank" href="${list.imageUrl}"><img class="imgc" src="${list.imageUrl}" style="margin-top: -21px;" width="100%" height="100%"/> </a>
							</c:if>
							<c:if test="${hideApprove !='null'}">
								<a target="_blank" href="${list.imageUrl}"><img class="imgc" src="${list.imageUrl}" width="100%" height="100%"/></a>
							</c:if>
							<input id="file${status.count}" style="display: none;" type="file" name="checkFile" class="validate[required] v"/> 
						</div>
						</c:forEach>
						<c:if test="${empty imageList}">
							<c:if test="${hideApprove !='null'}">
								<center>
									<label>暂无出科考核表！</label>
								</center>
							</c:if>
						</c:if>
					</td>
				</tr>
				<tr>
					<th style="text-align: left;color:red">*请拍照或扫描上传(需有红章)，考核表原件由科室保存</th>
				</tr>
				<c:if test="${hideApprove eq 'null'}">
					<div class="imageOper" style="display: none; border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 160px;height: 150px;float: left;text-align: center;" id="s">
						<div style="float: right;padding-top:5px;padding-right: 5px;position: relative;"><img title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />" style="margin-right: 5px;cursor: pointer;" onclick="del(this);"/></div>
						<img class="imgc" style="display: none;margin-top: -21px;" width="100%" height="100%"/>
						<div style="padding-top:45%;" class="upload"><a>请选择上传</a></div>
						<input id="file" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
					</div>
				</c:if>
			</table>
			</div>
			</div>
			<div class="button">
				<input class="btn_green" type="button" value="关&#12288;闭" onclick="doClose();" />
			</div>
</div>
</body>
</html>