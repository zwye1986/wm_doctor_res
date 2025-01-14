<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">

	/**
	 *模糊查询加载
	 */
	(function ($) {
		$.fn.likeSearchInit = function (option) {
			option.clickActive = option.clickActive || null;
			var searchInput = this;
			searchInput.on("keyup", function () {
				$("#jointOrgFlow").val("");
				if ($.trim(this.value)) {
					$("#boxHome .item").hide();
					var items = $("#boxHome .item[value*='" + this.value + "']").show();
					if (!items) {
						$("#boxHome").hide();
					}
				} else {
					$("#boxHome .item").show();
				}
			});
			searchInput.on("focus", function () {
				$("#boxHome").show();
				if ($.trim(this.value)) {
					$("#boxHome .item").hide();
					var items = $("#boxHome .item[value*='" + this.value + "']").show();
					if (!items) {
						$("#boxHome").hide();
					}
				} else {
					$("#boxHome .item").show();
				}
			});
			searchInput.on("blur", function () {
				if (!$("#boxHome.on").length) {
					$("#boxHome").hide();
				}
			});
			$("#boxHome").on("mouseenter mouseleave", function () {
				$(this).toggleClass("on");
			});
			$("#boxHome .item").click(function () {
				$("#boxHome").hide();
				var value = $(this).attr("value");
				$("#itemName").val(value);
				searchInput.val(value);
				if (option.clickActive) {
					option['clickActive']($(this).attr("flow"));
				}
			});
		};
	})(jQuery);

	//页面加载完成时调用
	$(function () {
		$("#orgSel").likeSearchInit({
			clickActive: function (flow) {
				$("#jointOrgFlow").val(flow);
			}
		});
		var fileFlowLength = $("table input[name='fileFlow']").length;
		if(!fileFlowLength || fileFlowLength == 0){
			addFile();
		}
	});

	function saveCoopBase(){
		if(!$("#editForm").validationEngine("validate")){
			return;
		}
		var jointOrgFlow = $("#jointOrgFlow").val();
		if(!jointOrgFlow){
			jboxTip("请从下拉列表选择协同单位！");
			return;
		}
		var fileFlowLength = $("table input[name='fileFlow']").length;
		if(!fileFlowLength || fileFlowLength == 0){
			jboxTip("请上传协同关系协议！");
			return;
		}
		// 校验基地专业是否已存在
		var currJointFlow = $("#jointFlow").val();
		<c:if test="${not empty jointOrgSpeList && jointOrgSpeList.size() > 0}">
			var speId = $("#speId").find("option:selected").val();
			var orgSpe = jointOrgFlow + "," + speId;
			<c:forEach items="${jointOrgSpeList}" var="jointSpe">
				var jointOrgSpe = "${jointSpe}";
				var jointOrgSpeArr = jointOrgSpe.split(";");
				if(currJointFlow != jointOrgSpeArr[0] && jointOrgSpeArr[1] == orgSpe){
					jboxTip("该协同单位专业基地已存在！");
					return;
				}
			</c:forEach>
		</c:if>
		jboxSubmit($("#editForm"), "<s:url value='/jsres/base/saveCoopBase'/>", function(resp){
			if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
				jboxTip(resp);
				window.parent.coopBaseInfo();
				jboxClose();
			}
		}, null, true);
	}

	function addFile() {
		var fileLength = $("table input[name='fileFlow']").length;
		if(fileLength >= 3){
			return jboxTip("最多只能上传3个文件！");
		}
		var td = $("#fileTd");
		$(td).append($("#jointContractFileTemplate div").clone());
	}

	function delJointFile(obj) {
		top.jboxConfirm("确认删除？" , function(){
			var div = obj.parentNode.parentNode;
			div.remove();
		});
	}

	function uploadFileCheck(obj) {
		var fileName = $(obj).val();
		if (!$.trim(fileName)) {
			jboxTip("请选择文件！");
			return;
		}
		fileName = $.trim(fileName);
		var regStr = /\.pdf$/i;
		if (!regStr.test(fileName)) {
			$(obj).val("");
			jboxTip("请上传 pdf 格式的文件");
			return;
		}
	}
</script>
</head>
<body>
	<div class="div_table">
		<form id="editForm">
			<input type="hidden" id="jointFlow" name="jointFlow" value="${resJointOrg.jointFlow}" />
			<input type="hidden" name="orgFlow" value="${curOrgFlow}" />
			<input type="hidden" name="orgName" value="${curOrgName}" />
			<table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
				<tr>
					<th style="width: 30%;"><span style="color: red;">*</span>协同单位</th>
					<td style="width: 70%;">
						<c:choose>
							<c:when test="${not empty resJointOrg.jointFlow}">
								${resJointOrg.jointOrgName}
							</c:when>
							<c:otherwise>
								<input id="orgSel" class="input validate[required]" type="text" name="jointOrgName" value="${resJointOrg.jointOrgName}" autocomplete="off" style="width: 190px;" />
								<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;left:0px;">
									<div id="boxHome" style="width:270px;max-height: 140px;overflow: auto;border: 1px #ccc solid;background-color: white;
								min-width: 148px;border-top: none;position: relative;display: none;text-align: left;">
										<c:forEach items="${unjointOrgList}" var="jointOrg">
											<p class="item" flow="${jointOrg.orgFlow}" value="${jointOrg.orgName}"
											   style="padding-left: 10px;height: 30px;line-height: 30px;cursor: pointer;">${jointOrg.orgName}</p>
										</c:forEach>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
						<input type="hidden" id="jointOrgFlow" name="jointOrgFlow" value="${resJointOrg.jointOrgFlow}" />
					</td>
				</tr>
				<tr>
					<th style="width: 30%;"><span style="color: red;">*</span>专业基地</th>
					<td style="width: 70%;">
						<select id="speId" name="speId" class="select validate[required]" style="width: 200px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${resJointOrg.speId == dict.dictId ? "selected" : ""}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th style="width: 30%;"><span style="color: red;">*</span>协同关系协议</th>
					<td style="width: 70%;" id="fileTd">
						<span>（请上传pdf格式的扫描件，最多3个）</span>
						<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addFile();" />
						<c:if test="${not empty fileList}">
							<c:forEach items="${fileList}" var="contractFile">
								<div style="text-align: left;padding: 2px;margin: 3px;display: flex;" class="jointContractFile">
									<a href="${sysCfgMap['upload_base_url']}/${contractFile.filePath}"
									   target="_blank" style="width: 70%; font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;background-color: white;color: #409eff">${contractFile.fileName}</a>
									<span>
										<img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
											 style="cursor: pointer;" onclick="delJointFile(this);" />
									</span>
									<input type="hidden" name="fileFlow" value="${contractFile.fileFlow}" />
								</div>
							</c:forEach>
						</c:if>
					</td>
				</tr>
			</table>
		</form>
		<div class="btn_info">
			<input class="btn_green" onclick="saveCoopBase()" type="button" value="保&#12288;存"/>
			<input class="btn_grey" onclick="jboxClose()" type="button" value="关&#12288;闭"/>
		</div>
	</div>
	<div style="display: none">
		<div id="jointContractFileTemplate">
			<div style="text-align: left;padding: 2px;margin: 3px;display: flex;" class="jointContractFile">
				<input type='file' name='files' class='validate[required]' onchange="uploadFileCheck(this);" accept=".pdf" style="width: 70%;"/>
				<span onclick="javascript:void(0)" style="background-color: white;color: #409eff">
					<img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
						 style="cursor: pointer;" onclick="delJointFile(this);" />
				</span>
				<input type="hidden" name="fileFlow" />
			</div>
		</div>
	</div>
</body>
</html>