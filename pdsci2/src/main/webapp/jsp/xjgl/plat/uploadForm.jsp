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
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>

<script type="text/javascript">
	$(document).ready(function(){
		$("#file").live("change",function(){
			uploadFile();
		});
		$("#applyTypeId").bind("change",function(){
			var url = "<s:url value='/xjgl/change/apply/uploadForm'/>?applyTypeId="+$(this).val();
			location.replace(url);
		});
	});
	// 上传文件操作
	function chooseFile(id){
		$("#upFileId").val(id);
		return $("#file").click();
	}
	// 删除文件
	function delFile(fileUrlId) {
		jboxConfirm("确认删除？" , function(){
			$("#"+fileUrlId+"Del").hide();
			$("#"+fileUrlId+"Span").hide();
			$("#"+fileUrlId).val("上传");
			$("#"+fileUrlId+"Value").val("");
			$("#file").val(null);
		});
	}
	//返回文件URL
	function returnUrl(filePath){
		var fileUrlId = $("#upFileId").val();
		$('#'+fileUrlId).val("重新上传");
		$('#'+fileUrlId+'Value').val(filePath);
		var filePath = "${sysCfgMap['upload_base_url']}/" + filePath;
		$('#'+fileUrlId+'Del').show();
		$('#'+fileUrlId+'Span').show();
		$('#'+fileUrlId+'Span').find("a").attr('href',filePath);
		$("#file").val(null);//上传成功后清除此文件
	}
	//上传文件
	function uploadFile(){
		if(false == $("#fileForm").validationEngine("validate")){
			return false;
		}
		jboxStartLoading();
		var url = "<s:url value='/xjgl/change/apply/uploadTblFile'/>";
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
	//添加上传控件
	function addPicUrl(tb) {
		if($('#applyTypeId').val() == ""){
			jboxTip("请选择申请类型！");
			return false;
		}
		var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
		var index = $("#" + tb + "Tb tr").length;
		cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
		$("#" + tb + "Tb").append(cloneTr);
	}
	//删除上传控件
	function delRegTr(obj) {
		jboxConfirm("确认删除?", function () {
			$(obj).parent('td').parent("tr").remove();
			var reg = new RegExp('\\[\\d+\\]', 'g');
			$("#credentialsTb tr").each(function (i) {
				$("[name]", this).each(function () {
					this.name = this.name.replace(reg, "[" + i + "]");
				});
			});
		});
	}
	String.prototype.htmlFormartById = function (data) {
		var html = this;
		for (var key in data) {
			var reg = new RegExp('\\{' + key + '\\}', 'g');
			html = html.replace(reg, data[key]);
		}
		return html;
	}
	function save(){
		if($("#applyTypeId").val()==''){
			return jboxTip("申请类型不能为空！");
		}
		var flag = true;
		$("input[id^='changeApplyUrl'][id$='Value']").each(function(){
			if($(this).val() != ""){
				flag = false;//附件不为空
			}
		});
		if(flag){
			jboxTip("请上传附件！");
			return false;
		}
		jboxConfirm('确认保存?',function(){
			jboxPost("<s:url value='/xjgl/change/apply/saveTblFile'/>", $("#submitForm").serialize() , function(resp){
				top.document.mainIframe.location.reload();
				jboxClose();
			} , null , true);
		});
	}
	function linkTable(url){
		window.open("<s:url value='${sysCfgMap["upload_base_url"]}'/>"+url);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id='submitForm' method="post" style="position:relative;">
			<input type="hidden" id="upFileId"/>
			<table class="basic" style="width: 100%;margin: 10px 0px;">
					<tr>
						<th style="width:40%;">
							<span class="red" style="margin-top: 6px;">*</span>申请类型：
							<select id="applyTypeId" class="xlselect validate[required]" name="applyTypeId">
								<option value="">请选择</option>
								<c:forEach items="${userChangeApplyTypeEnumList}" var="apply">
									<option value="${apply.id}"<c:if test="${apply.id eq param.applyTypeId}">selected="selected"</c:if>>${apply.name}</option>
								</c:forEach>
							</select>
							</span>
						</th>
						<th style="width:60%;text-align:left;">&#12288;添加上传表格&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addPicUrl('picAddress')"/></th>
					</tr>
					<tbody id="picAddressTb">
					<c:forEach var="urlObj" items="${form.changeApplyUrlList}" varStatus="status">
						<tr>
							<td></td>
							<td>
								<input type="hidden" id="changeApplyUrl${status.index}Value" name="changeApplyUrlList" value="<c:if test="${not empty urlObj}">${urlObj}</c:if>" />
								<label id="changeApplyUrl${status.index}Span" style="display:${!empty urlObj?'':'none'}">
									<input type="button" onclick="linkTable('${urlObj}')" class="search" value="查看表格"/>
								</label>
								<input id="changeApplyUrl${status.index}"  type="button" onclick="chooseFile('changeApplyUrl${status.index}');" class="search" value="${empty urlObj?'上&#12288;传':'重新上传'}" />&nbsp;
								<input id="changeApplyUrl${status.index}Del" type="button" onclick="delFile('changeApplyUrl${status.index}');" class="search" style="${empty urlObj?'display:none':''}" value="删&#12288;除"/>
								<img class="opBtn" title="删除" style="cursor: pointer;margin-left:50px;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			<div style="display: none">
				<!--上传附件模板-->
				<table id="picAddressTemplate">
					<tr>
						<td></td>
						<td>
							<input type="hidden" id="changeApplyUrl${'{index}'}Value" name="changeApplyUrlList" value="<c:if test="${not empty urlObj}">${urlObj}</c:if>" />
							<label id="changeApplyUrl${'{index}'}Span" style="display:${!empty urlObj?'':'none'}">
								<input type="button" onclick="linkTable('${urlObj}')" class="search" value="查看表格"/>
							</label>
							<input id="changeApplyUrl${'{index}'}"  type="button" onclick="chooseFile('changeApplyUrl${'{index}'}');" class="search" value="${empty urlObj?'上&#12288;传':'重新上传'}" />&nbsp;
							<input id="changeApplyUrl${'{index}'}Del" type="button" onclick="delFile('changeApplyUrl${'{index}'}');" class="search" style="${empty urlObj?'display:none':''}" value="删&#12288;除"/>
							<img class="opBtn" title="删除" style="cursor: pointer;margin-left:50px;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
						</td>
					</tr>
				</table>
			</div>
			<div style="text-align:center;margin-top:20px;">
				<input type="button" class="search" value="保&#12288;存" onclick="save();"/>
				<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
			</div>
		</form>
		<form id="fileForm" method="post" enctype="multipart/form-data">
			<input type="file" id="file" name="file" style="display: none"/>
			<input type="hidden" name="applyTypeId" value="${param.applyTypeId}" />
		</form>
	</div>
</div>
</body>
</html>