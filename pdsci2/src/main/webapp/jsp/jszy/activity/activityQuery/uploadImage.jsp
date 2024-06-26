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
	<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/viewer/viewer-jquery.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
var countDiv=${imageList.size()};
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
	var a = $("#s").clone();
	a.show().attr("id","").appendTo("#tu");
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

$(function () {
	$('.ratateImg').viewer();
})

function del(obj){
	var operDiv=$(obj).closest(".imageOper");
	var id=operDiv.attr("id");
	if($(".imageOper").length==2&&id==null){
		
	}else{
		jboxConfirm("确认删除？", function() {
			if(id){
					var imageFlow=$(operDiv).attr("id");
					var url="<s:url value='/jsres/activityQuery/activityImgDelete'/>?activityFlow=${activity.activityFlow}"+"&imageFlow="+imageFlow;
					jboxPost(url,null,function(s){
						if(s=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
							operDiv.remove();
							jboxTip("删除成功!");
							if($(".imageOper").length<=1){
								add();
							}
							location.reload();
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
		url:"<s:url value='/jsres/activityQuery/activityImgUpload'/>?activityFlow=${activity.activityFlow}",
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
					$('.ratateImg').viewer();
					jboxTip("上传成功！");
					location.reload();
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
				<th style="border-bottom: 1px solid #e3e3e3;"><div style="float:left;">教学活动图片</div>
					<c:if test="${isUpload eq 'Y'}">
						<div  style="float: right;"><img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="margin-right: 10px;cursor: pointer;" onclick="add();"/></div>
					</c:if>
				</th>
			</tr>
		</table>
		<div style="height: 400px;overflow: auto;">
			<table  style="width: 100%;margin-bottom: 5px;margin-top: 0px;border: 1px solid #e3e3e3;">
				<tr>
					<td style="border-bottom: 1px solid #e3e3e3;" id="f">
						<div class="ratateImg">
							<ul id="tu">
								<c:forEach items="${imageList}" var="list" varStatus="status">
									<div class="imageOper" id="${list.imageFlow}"
											<c:if test="${isUpload eq 'Y'}">
												style="border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 25px;margin-bottom:5px;  width: 160px;height: 210px;float: left;text-align: center;"
											</c:if>
											<c:if test="${isUpload ne 'Y'}">
												style="border: 1px solid white; margin-left: 5px; margin-top: 25px;margin-bottom:5px;  width: 160px;height: 195px;float: left;text-align: center;"
											</c:if>
										  >
										<c:if test="${isUpload eq 'Y'}">
											<li id="ratateImg${status.index+1}">
												<img class="imgc" src="${list.imageUrl}" style="margin-top: -21px;height: 170px;margin-bottom: 3px;" width="100%"/>
												<div style="height: 25px;">${pdfn:getSubDate(list.imageUrl)}</div>
											</li>
										</c:if>
										<c:if test="${isUpload ne 'Y'}">
											<li id="ratateImg${status.index+1}">
												<img class="imgc" src="${list.imageUrl}"
													 style="height:170px;width: 100%; "/>
											</li>
											<div style="height: 25px;">${pdfn:getSubDate(list.imageUrl)}</div>
										</c:if>
										<input id="file${status.count}" style="display: none;" type="file" name="checkFile" class="validate[required] v"/>
										<c:if test="${isUpload eq 'Y'}">
											<input class="btn_green" type="button" value="删除" onclick="del(this);" />
										</c:if>
									</div>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${empty imageList}">
							<c:if test="${isUpload ne 'Y'}">
								<center>
									<label>暂无活动图片！</label>
								</center>
							</c:if>
						</c:if>
					</td>
				</tr>
			</table>
		</div>
		<c:if test="${isUpload eq 'Y'}">
			<div>
				<table  style="width: 100%;margin-bottom: 5px;margin-top: 0px;border: 1px solid #e3e3e3;">
					<tr>
						<th style="text-align: left;color:red">*请拍照或扫描上传活动图片</th>
					</tr>
					<div class="imageOper" style="display: none; border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 160px;height: 230px;float: left;text-align: center;" id="s">
						<img class="imgc" style="display: none;margin-top: -21px;" width="100%" height="100%"/>
						<div style="padding-top:45%;" class="upload"><a>请选择上传</a></div>
						<input id="file" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
						<input class="btn_green" type="button" style="margin-top: 106px;" value="删除" onclick="del(this);" />
					</div>
				</table>
			</div>
		</c:if>
	</div>
	<div class="button">
		<input class="btn_green" type="button" value="关&#12288;闭" onclick="doClose();" />
	</div>
</div>
</body>
</html>