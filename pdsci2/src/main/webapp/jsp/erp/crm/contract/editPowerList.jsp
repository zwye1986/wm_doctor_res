<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_mask" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(document).ready(function(){
	if($("#powerTb tr").length<=0){
		addPower();
	}
});

//添加权限开通情况
function addPower(){
	$('#powerTb').append($("#powerClone tr:eq(0)").clone());
}
/**
 * 删除权限开通情况
 */
function delPower(){
	var mIds = $("#powerTb input[name='id']:checked");
	if(mIds.length == 0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除？", function() {
		var ids = "";
		mIds.each(function(){
			var id = $(this).val();
			if(id != ''){
				ids = ids + "id="+ id + "&";
			}else{
				$(this).parent().parent().remove();
			}
		});
	});
}
function save(){
		if($("#powerForm").validationEngine("validate")){
		}else{
			return false;
		}
	var powerTr=$("#powerTb").children();
	var powerDatas=[];
	if (powerTr.length > 0) {
		$.each(powerTr, function (i, n) {
			var sessionNumber = $(n).find("input[name='sessionNumber']").val();
			var powerIds="";
			var powerNames="";
			$.each($(n).find("input[name='powerIds']:checked"),function(j){
				if(j==0) {
					powerIds+=$(this).val();
					powerNames+=$(this).attr("idname");
				}else{
					powerIds+=","+$(this).val();
					powerNames+=","+$(this).attr("idname");
				}
			});
			var powerFlow = $(n).find("input[name='powerFlow']").val();
			var peopleNum = $(n).find("input[name='peopleNum']").val();
			var fileFlow = $(n).find("input[name='fileFlow']").val();
			var oldFileFlow = $(n).find("input[name='oldFileFlow']").val();
			var powerData = {
				"sessionNumber": sessionNumber,
				"powerIds": powerIds,
				"powerNames": powerNames,
				"peopleNum": peopleNum,
				"powerFlow": powerFlow,
				"oldFileFlow": oldFileFlow,
				"fileFlow": fileFlow
			};
			powerDatas.push(powerData);
		});
	} else {
		jboxTip("至少填写一个权限开通情况!");
		return false;
	}
	 $("#saveButton").attr("disabled",true);
	 var allData={
		 'powerExtList':powerDatas
		};
	 $('#jsondata').val(JSON.stringify(allData));
	 var url = "<s:url value='/erp/crm/saveContractPowerFiles'/>";
		jboxSubmit($('#powerForm'),url,function(resp){
			jboxTip('${GlobalConstant.SAVE_SUCCESSED}');
			setTimeout(function(){
				window.parent.frames['jbox-message-iframe'].window.search();
				jboxClose();
			},1000);
		},
		function(resp){
		},false);

		
}
function  changeFile(obj)
{
	var fileObj=$(obj).next();
	$(fileObj).click();
}

function uploadFile(obj)
{
	var bro=$(obj).prev();//上传文字
	var fileNamebro=$(obj).prev().prev();//显示的文件名
	var file=$(obj).prev().prev().prev();//文件流水号
	var fileName=$(obj).val();
	if(fileName=="")
	{
		jboxTip("请选择文件！");
		$(fileNamebro).attr("href","javascript:void(0);");
		$(fileNamebro).html("");
		return ;
	}
	var suffix = fileName.substring(fileName.indexOf(".")+1);
	if(suffix!="xlsx"&&suffix!="xls")
	{
		jboxTip("请选择EXECL文件！");
		$(obj).val("");
		$(fileNamebro).attr("href","javascript:void(0);");
		$(fileNamebro).html("");
		return ;
	}
	jboxStartLoading();
	var url = "<s:url value='/erp/crm/savePowerFile'/>";
	jboxSubmit($(obj).parent(),url,function(resp){
		jboxEndLoading();
		var data=eval("("+resp+")");
		if(data.result=='${GlobalConstant.UPLOAD_SUCCESSED}'){
			jboxTip("${GlobalConstant.UPLOAD_SUCCESSED}");
			$(file).val(data.fileFlow);
			var url="<s:url value='/erp/crm/downFile'/>?fileFlow="+data.fileFlow;
			$(fileNamebro).attr("href",url);
			$(bro).html("[重新上传]");
			$(fileNamebro).html(fileName);
		}else{
			jboxInfo("${GlobalConstant.UPLOAD_FAIL}");
		}
	},function(){
		jboxEndLoading();
		jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
	},false);
}

</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
			<form id="powerForm" style="position: relative;" enctype="multipart/form-data">
				<input id="jsondata" type="hidden" name="jsondata"/>
				<input type="hidden" name="contractFlow" value="${param.contractFlow}"/>
			</form>
				<table id="powerTable" cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
					<colgroup>
						<col width="3%"/>
						<col width="20%"/>
						<col width="37%"/>
						<col width="15%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">开通权限详情<span class="red" style="padding-left: 12px;">*</span>
							<img title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />" style="float: right;margin-right: 20px;cursor: pointer;" onclick="delPower();"/>
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="float: right;margin-right: 10px;cursor: pointer;" onclick="addPower();"/>
						</th>
					</tr>
					<tr>
						<th></th>
						<th>届别</th>
						<th>权限</th>
						<th>开通人数</th>
						<th>人员名单</th>
					</tr>
					<tbody id="powerTb">
					<c:forEach items="${powerList}" var="p">
						<tr>
							<td>
								<input type="checkbox" name="id" value=""/>
								<input type="hidden" name="powerFlow" value="${p.powerFlow}"/>
							</td>
							<td>
								<input type="text" class="inputText ctime validate[required]" value="${p.sessionNumber}" name="sessionNumber" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
							</td>
							<td>
								<input type="checkbox"  class="validate[required]" name="powerIds" id="GCGL" value="GCGL" <c:if test="${pdfn:contains(p.powerIds,'GCGL')}">checked="checked"</c:if> idName="过程管理"/><label>过程管理</label>&#12288;
								<input type="checkbox"  class="validate[required]" name="powerIds" id="APP" value="APP" <c:if test="${pdfn:contains(p.powerIds,'APP')}">checked="checked"</c:if> idName="APP"/><label>APP</label>&#12288;
								<input type="checkbox"  class="validate[required]" name="powerIds" id="CKK" value="CKK" <c:if test="${pdfn:contains(p.powerIds,'CKK')}">checked="checked"</c:if> idName="出科考核"/><label>出科考核</label>&#12288;
								<input type="checkbox"  class="validate[required]" name="powerIds" id="PXSC" value="PXSC" <c:if test="${pdfn:contains(p.powerIds,'PXSC')}">checked="checked"</c:if> idName="培训手册"/><label>培训手册</label>&#12288;
							</td>
							<td>
								<input type="text" class="inputText validate[required,custom[number],maxSize[5]]" value="${p.peopleNum}" name="peopleNum" style="width: 150px;"/>
							</td>
							<td>
								<c:set var="file" value="${fileMap[p.powerFlow]}"></c:set>
								<form  enctype="multipart/form-data" method="post">
									<input type="hidden" name="isTemp" value="temp"/>
									<input type="hidden" name="oldFileFlow" value="${file.fileFlow}"/>
									<input type="hidden" name="fileFlow" value=""/>
									<a name="fileName" href="<s:url value='/erp/crm/downFile'/>?fileFlow=${file.fileFlow}">${file.fileName}</a>
									<a href="javascript:void(0)" onclick="changeFile(this)">${(empty file) ? '[上传]' :'[重新上传]'}</a>
									<input type="file" style="display: none;" class="validate[required]" name="file" accept=".xls, .xlsx" onchange="uploadFile(this);">
								</form>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="button" style="width: 100%">
					<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
				</div>
			</div>
		</div>
	</div>
<table style="display: none;" id="powerClone">
	<tr>
		<td>
			<input type="checkbox" name="id" value=""/>
			<input type="hidden" name="powerFlow" value=""/>
		</td>
		<td>
			<input type="text" class="inputText ctime validate[required]" name="sessionNumber" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
		</td>
		<td>
			<input type="checkbox"  class="validate[required]" name="powerIds" id="GCGL" value="GCGL" idName="过程管理"/><label>过程管理</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="APP" value="APP" idName="APP"/><label>APP</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="CKK" value="CKK" idName="出科考核"/><label>出科考核</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="PXSC" value="PXSC" idName="培训手册"/><label>培训手册</label>&#12288;
		</td>
		<td>
			<input type="text" class="inputText validate[required,custom[number],maxSize[5]]" name="peopleNum" style="width: 150px;"/>
		</td>
		<td>
			<form  enctype="multipart/form-data" method="post">
				<input type="hidden" name="isTemp" value="temp"/>
				<input type="hidden" name="oldFileFlow" value=""/>
				<input type="hidden" name="fileFlow" value=""/>
				<a name="fileName" href="javascript:void(0);"></a>
				<a href="javascript:void(0)" onclick="changeFile(this)">[上传]</a>
				<input type="file" style="display: none;" class="validate[required]" name="file" accept=".xls, .xlsx" onchange="uploadFile(this);">
			</form>
		</td>
	</tr>
</table>
</body>
</html>