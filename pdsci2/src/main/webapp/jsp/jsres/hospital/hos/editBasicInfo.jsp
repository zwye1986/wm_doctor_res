<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
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
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style>
	.div_table h4 {
		color: #000000;
		font: 15px 'Microsoft Yahei';
		font-weight: 500;
	}
	.base_info th, .grid th {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 500;
	}
	.base_info td, .grid td, .input {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 400;
	}
	.base_info td a {
		color: #ffffff;
		background-color: #409eff;
		font: 14px 'Microsoft Yahei';
		font-weight: 400;
		padding: 3px 10px;
		border-radius: 2px;
	}
</style>
<script type="text/javascript">
	function saveBaseInfo(){
		if(!$("#BaseInfoForm").validationEngine("validate") || !checkSetJointOrg() || !checkOthers()){ // 新加了协同单位相关内容，保存时校验并组织数据
			$("#indexBody").scrollTop("0px");
			return false;
		}

        var orgProvNameText = $("#orgProvId option:selected").text();
        if(orgProvNameText == "选择省"){
            orgProvNameText = "";
        }
        var orgCityNameText = $("#orgCityId option:selected").text();
        if(orgCityNameText == "选择市"){
            orgCityNameText = "";
        }
        var orgAreaNameText = $("#orgAreaId option:selected").text();
        if(orgAreaNameText == "选择地区"){
            orgAreaNameText = "";
        }
        $("#orgProvName").val(orgProvNameText);
        $("#orgCityName").val(orgCityNameText);
        $("#orgAreaName").val(orgAreaNameText);
		jboxSubmit($("#BaseInfoForm"), "<s:url value='/jsres/base/saveBase'/>", function(resp){
			if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
				$("#submitBtn").show();
				setTimeout(function(){
					<%--hosMain('${sessionScope.currUser.orgFlow}');--%>
					loadInfo('${GlobalConstant.BASIC_INFO}','${sessionScope.currUser.orgFlow}','${sessionNumber}');
				},1000);
			}
		} , null , true);
	}

	function checkOthers() {
		if(!$("#hospitalLevelLicenceUrlValue").val()) {
			jboxTip("请添加医院等级证书！");
			return false;
		}

		if(!$("#professionLicenceUrlValue").val()) {
			jboxTip("请添加执业许可证！");
			return false;
		}

		return true;
	}

	function checkSetJointOrg() {
		var joints = $("#jointContractBody tr");
		// 校验必填项是不是都填了
		for(var i = 0; i < joints.length; i++) {
			var joint = joints[i];
			var jointOrg = $(joint).find('select[name="jointOrgFlows"]');
			if(!$(jointOrg).val()) {
				jboxTip("请选择协同单位！");
				return false;
			}

			var speId = $(joint).find('select[name="speIds"]');
			if(!$(speId).val()) {
				jboxTip("请选择专业基地！");
				return false;
			}

			var uploadFiles = $(joint).find("input[name='files']");
			var uploadNum = 0;
			for(var j = 0; j < uploadFiles.length; j++) {
				var uploadFile = uploadFiles[j];
				if($(uploadFile).val()) {
					uploadNum++;
				}
			}

			// 记录一下该基地上传了几个文件
			var fileUploadNum = $(joint).find("input[name='fileUploadNum']");
			$(fileUploadNum).val(uploadNum);

			var fileFlows = $(joint).find("input[name='jointContractFileFlows']");

			if(!fileFlows.length) {
				jboxTip("请上传协同关系协议！");
				return false;
			}

			fileFlows = Array.from(fileFlows).filter(function(item) {
				return $(item).val();
			});
			// 记录下原来的文件还剩几个
			var fileRemainNum = $(joint).find("input[name='fileRemainNum']");
			$(fileRemainNum).val(fileFlows.length);
		}

		return true;
	}

	String.prototype.htmlFormartById = function(data){
		var html = this;
		for(var key in data){
			var reg = new RegExp('\\{'+key+'\\}','g');
			html = html.replace(reg,data[key]);
		}
		return html;
	};

	String.prototype.htmlFormatByIndex = function(data, pre){
		var html = this;
		var reg = new RegExp(pre + "\\[\\d+\\]", "g");
		html = html.replace(reg, pre + "[" + data + "]");
		return html;

	};

	$(document).ready(function(){
		/*if ($("#ywfgfzrTb tr").length <= 0) {
			add('ywfgfzr');
		}
        if ($("#zpywkslxrTb tr").length <= 0) {
            add('zpywkslxr');
        }
        if ($("#zpglbmfzrTb tr").length <= 0) {
            add('zpglbmfzr');
        }*/
	});

	function add(tb) {
		var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
		var index = $("#" + tb + "Tb tr").length;
		cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
		$("#" + tb + "Tb").append(cloneTr);
	}

	function del(obj, type, pre) {
		$(obj).parent().parent().remove();
		if(type) {
			var index = $("#" + type + "Tb tr").length;
			for(var i = 0; i < index; i++) {
				var cur = $($("#" + type + "Tb tr")[i]);
				cur.html(cur.html().htmlFormatByIndex(i + "", pre));
			}
		}
	}

	function uploadFile(type,typeName,fileType) {
		var url = "<s:url value='/jsres/doctor/uploadFile'/>?operType="+type+"&fileType="+fileType;
		jboxOpen(url, "上传"+typeName, 500, 185);
	}

	function delFile(type) {
		jboxConfirm("确认删除？" , function(){
			$("#"+type+"Del").hide();
			$("#"+type+"Span").hide();
			$("#"+type).text("上传");
			$("#"+type+"Value").val("");
			$("#"+type+"Se").show();
		});
	}

	function valueUpdate(cur) {
		$(cur).attr("value", $(cur).val());
		if(cur.tagName == 'SELECT') {
			$(cur).find("option[value='" + $(cur).val() + "']").attr("selected", true);
		}
	}

	function addFile(obj) {
		var td = obj.parentNode;

		$(td).append($("#jointContractFileTemplate div").clone());
	}

	function delJointFile(obj) {
		jboxConfirm("确认删除？" , function(){
			var div = obj.parentNode;
			div.remove();
		});
	}

	function moveTr(obj) {
		jboxConfirm("确认删除？", function () {
			var tr = obj.parentNode.parentNode;
			tr.remove();
		});
	}

	function addJointContract() {
		if($("#jointContractBody").find("tr").length >= 3) {
			jboxTip("最多只能有三个协同单位！");
			return;
		}

		$('#jointContractBody').append($("#jointContractTemplate tr:eq(0)").clone());
	}

	function uploadFileCheck(obj) {
		var fileName = $(obj).val();
		if (!$.trim(fileName)) {
			jboxTip("请选择文件！");
			return;
		}
		fileName = $.trim(fileName);
		var types = ".pdf";
		var regStr = /\.pdf$/i;
		if (!regStr.test(fileName)) {
			$(obj).val("");
			jboxTip("请上传 pdf 格式的文件");
			return;
		}
	}
</script>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<form id='BaseInfoForm' style="position:relative;">
	<input type="hidden" name="resBase.orgFlow" value="${resBase.orgFlow}"/>
	<input type="hidden" name="sysOrg.orgFlow" value="${sessionScope.currUser.orgFlow}"/>
	<input type="hidden" name="baseFlow" value="${sessionScope.currUser.orgFlow}"/>
	<input type="hidden" name="flag" value="${GlobalConstant.BASIC_INFO}"/>
	<input type="hidden" name="resBase.sessionNumber" value="${sessionNumber}"/>
	<div class="main_bd">
		<div class="div_table">
			<h4><span class="red">*</span>基本信息</h4>
			<table border="0" cellspacing="0" cellpadding="0" class="base_info" id="table1">
				<colgroup>
					<col width="20%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
				</colgroup>
				<tbody>
					<tr>
						<th><span class="red">*</span>培训基地名称：</th>
						<td colspan="2"><%--<input type="text"  style="width: 115px"  class='input' id="orgName" name="sysOrg.orgName" value="${sysOrg.orgName}" readonly="readonly"/>--%>
							<span>${sysOrg.orgName}</span>
						</td>
						<th>所属地区：</th>
						<td colspan="2">
							<div id="provCityAreaId">
								<select id="orgProvId" name="sysOrg.orgProvId" class="province select" data-value="${sysOrg.orgProvId}" style="width: 127px;" data-first-title="选择省"></select>
								<select id="orgCityId" name="sysOrg.orgCityId" class="city select" data-value="${sysOrg.orgCityId}" style="width: 127px;" data-first-title="选择市"></select>
								<select id="orgAreaId" name="sysOrg.orgAreaId" class="area select" data-value="${sysOrg.orgAreaId}" style="width: 127px;" data-first-title="选择地区"></select>
							</div>
							<input id="orgProvName" name="sysOrg.orgProvName" type="hidden" value="${sysOrg.orgProvName}">
							<input id="orgCityName" name="sysOrg.orgCityName" type="hidden" value="${sysOrg.orgCityName}">
							<input id="orgAreaName" name="sysOrg.orgAreaName" type="hidden" value="${sysOrg.orgAreaName}">
							<script type="text/javascript">
                                // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                $.cxSelect.defaults.nodata = "none";
                                $("#provCityAreaId").cxSelect({
                                    selects : ["province", "city", "area"],
                                    nodata : "none",
                                    firstValue : "",
									disabled: true
                                });
							</script>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>培训基地地址：</th>
						<td colspan="2"><input type="text" style="width: 90%"  name="sysOrg.orgAddress"  class='input validate[required]' value="${sysOrg.orgAddress}"/></td>
						<th><span class="red">*</span>邮政编码：</th>
						<td colspan="2"><input type="text"  style="width: 115px" name="sysOrg.orgZip" class="input validate[required,custom[chinaZip]]"  value="${sysOrg.orgZip}"/></td>
					</tr>
					<%--<tr>
						<th><span class="red">*</span>培训基地负责人：</th>
						<td>
							<input type="text" style="width: 115px" name="sysOrg.orgPersonInCharge" class='input validate[required]' value="${sysOrg.orgPersonInCharge}"/>
						</td>
						<th>职称：</th>
						<td>
							<select name="basicInfo.jdfzrTitleId" class="select" style="margin-left: 4px; width: 121px;">
								<option></option>
								<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
									<option value="${title.dictId}"
											<c:if test='${basicInfo.jdfzrTitleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<th>职务：</th>
						<td>
							<select name="basicInfo.jdfzrPostId" class="select" style="margin-left: 4px; width: 121px;">
								<option></option>
								<c:forEach items="${dictTypeEnumUserPostList}" var="post">
									<option value="${post.dictId}"
											<c:if test='${basicInfo.jdfzrPostId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="red"></span>手机号码：</th>
						<td>
							<input type="text" class='input validate[custom[mobile]]' style="width: 115px"
								   name="basicInfo.jdfzrMobilephone" oninput="valueUpdate(this);"
								   value="${basicInfo.jdfzrMobilephone }"/>
						</td>
						<th><span class="red"></span>固定电话：</th>
						<td>
							<input type="text" class='input validate[custom[phone]]' style="width: 115px"
								   name="basicInfo.jdfzrFixedPhone" oninput="valueUpdate(this);"
								   value="${basicInfo.jdfzrFixedPhone }"/>
						</td>
						<th><span class="red"></span>邮箱地址：</th>
						<td>
							<input type="text" class='input validate[custom[email]]' style="width: 115px"
								   name="basicInfo.jdfzrMailAddress" oninput="valueUpdate(this);"
								   value="${basicInfo.jdfzrMailAddress }"/>
						</td>
					</tr>--%>
				</tbody>
			</table>

			<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="jdfzrTable" style="table-layout: fixed">
				<tr>
					<th style="width: 15%">培训基地负责人<span class="red">*</span></th>
					<th style="width: 15%">手机号码<span class="red">*</span></th>
					<th style="width: 15%">固定电话<span class="red">*</span></th>
					<th style="width: 15%">邮箱地址<span class="red">*</span></th>
					<th style="width: 15%">职称</th>
					<th style="width: 15%">职务<span class="red">*</span></th>
					<th style="width: 10%"></th>
					<%--<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
									 style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>--%>
				</tr>
				<tbody id="jdfzrTb">
				<tr>
					<td style="position: relative">
						<input type="text" style="width: 115px" name="sysOrg.orgPersonInCharge" class='input validate[required]' value="${sysOrg.orgPersonInCharge}"/>
					</td>
					<td>
						<input type="text" class='input validate[required,custom[mobile]]' style="width: 115px"
							   name="basicInfo.jdfzrMobilephone" oninput="valueUpdate(this);"
							   value="${basicInfo.jdfzrMobilephone }"/>
					</td>
					<td>
						<input type="text" class='input validate[required,custom[phone]]' style="width: 115px"
							   name="basicInfo.jdfzrFixedPhone" oninput="valueUpdate(this);"
							   value="${basicInfo.jdfzrFixedPhone }"/>
					</td>
					<td>
						<input type="text" class='input validate[required,custom[email]]' style="width: 115px"
							   name="basicInfo.jdfzrMailAddress" oninput="valueUpdate(this);"
							   value="${basicInfo.jdfzrMailAddress }"/>
					</td>
					<td>
						<select name="basicInfo.jdfzrTitleId" class="select">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
								<option value="${title.dictId}"
										<c:if test='${basicInfo.jdfzrTitleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select name="basicInfo.jdfzrPostId" class="select validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserPostList}" var="post">
								<option value="${post.dictId}"
										<c:if test='${basicInfo.jdfzrPostId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<%--<td><a onclick="del(this, 'zpglbmfzr', 'zpglbmfzrList');">删除</a></td>--%>
				</tr>
				</tbody>
			</table>

			<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="zpglbmfzrTable" style="table-layout: fixed">
				<tr>
					<th style="width: 15%">住培管理部门负责人<span class="red">*</span></th>
					<th style="width: 15%">手机号码<span class="red">*</span></th>
					<th style="width: 15%">固定电话<span class="red">*</span></th>
					<th style="width: 15%">邮箱地址<span class="red">*</span></th>
					<th style="width: 15%">职称</th>
					<th style="width: 15%">职务<span class="red">*</span></th>
					<th style="width: 10%"></th>
					<%--<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
									 style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>--%>
				</tr>
				<tbody id="zpglbmfzrTb">
					<tr>
						<td style="position: relative">
							<input type="text" class="input validate[required]" style="width: 115px"
								   name="basicInfo.zpglbmfzrList[0].contactorName" oninput="valueUpdate(this);"
								   value="${basicInfo.zpglbmfzrList[0].contactorName }"/>
						</td>
						<td>
							<input type="text" class='input validate[required,custom[mobile]]' style="width: 115px"
								   name="basicInfo.zpglbmfzrList[0].mobilephone" oninput="valueUpdate(this);"
								   value="${basicInfo.zpglbmfzrList[0].mobilephone }"/>
						</td>
						<td>
							<input type="text" class='input validate[required,custom[phone]]' style="width: 115px"
								   name="basicInfo.zpglbmfzrList[0].fixedPhone" oninput="valueUpdate(this);"
								   value="${basicInfo.zpglbmfzrList[0].fixedPhone }"/>
						</td>
						<td>
							<input type="text" class='input validate[required,custom[email]]' style="width: 115px"
								   name="basicInfo.zpglbmfzrList[0].mailAddress" oninput="valueUpdate(this);"
								   value="${basicInfo.zpglbmfzrList[0].mailAddress }"/>
						</td>
						<td>
							<select name="basicInfo.zpglbmfzrList[0].titleId" class="select" onchange="valueUpdate(this);">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
									<option value="${title.dictId}"
											<c:if test='${basicInfo.zpglbmfzrList[0].titleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select name="basicInfo.zpglbmfzrList[0].postId" class="select validate[required]" onchange="valueUpdate(this);">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumUserPostList}" var="post">
									<option value="${post.dictId}"
											<c:if test='${basicInfo.zpglbmfzrList[0].postId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<%--<td><a onclick="del(this, 'zpglbmfzr', 'zpglbmfzrList');">删除</a></td>--%>
					</tr>
				</tbody>
			</table>

			<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="contactManTable" style="table-layout: fixed">
				<tr>
					<th style="width: 15%">联络员<span class="red">*</span></th>
					<th style="width: 15%">手机号码<span class="red">*</span></th>
					<th style="width: 15%">固定电话<span class="red">*</span></th>
					<th style="width: 15%">邮箱地址<span class="red">*</span></th>
					<th style="width: 15%">职称</th>
					<th style="width: 15%">职务<span class="red">*</span></th>
					<th style="width: 10%"><img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
									   style="cursor: pointer;" onclick="javascript:add('contactMan')" /></th>
				</tr>
				<tbody id="contactManTb">
				<c:forEach var="contactMan" items="${basicInfo.contactManList}" varStatus="status">
					<tr>
						<td style="position: relative">
							<input type="text" class="input validate[required]" style="width: 115px"
								   name="basicInfo.contactManList[${status.index}].contactorName" oninput="valueUpdate(this);"
								   value="${contactMan.contactorName }"/>
						</td>
						<td>
							<input type="text" class='input validate[required,custom[mobile]]' style="width: 115px"
								   name="basicInfo.contactManList[${status.index}].mobilephone" oninput="valueUpdate(this);"
								   value="${contactMan.mobilephone }"/>
						</td>
						<td>
							<input type="text" class='input validate[required,custom[phone]]' style="width: 115px"
								   name="basicInfo.contactManList[${status.index}].fixedPhone" oninput="valueUpdate(this);"
								   value="${contactMan.fixedPhone }"/>
						</td>
						<td>
							<input type="text" class='input validate[required,custom[email]]' style="width: 115px"
								   name="basicInfo.contactManList[${status.index}].mailAddress" oninput="valueUpdate(this);"
								   value="${contactMan.mailAddress }"/>
						</td>
						<td>
							<select name="basicInfo.contactManList[${status.index}].titleId" class="select" onchange="valueUpdate(this);">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
									<option value="${title.dictId}"
											<c:if test='${contactMan.titleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select name="basicInfo.contactManList[${status.index}].postId" class="select validate[required]" onchange="valueUpdate(this);">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumUserPostList}" var="post">
									<option value="${post.dictId}"
											<c:if test='${contactMan.postId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td><img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
								 style="cursor: pointer;" onclick="javascript:del(this, 'contactMan', 'contactManList');" /></td>
						<%--<td><a onclick="del(this, 'contactMan', 'contactManList');">删除</a></td>--%>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="div_table">
			<h4><span class="red">*</span>培训基地资质</h4>
			<table border="0" cellspacing="0" cellpadding="0" class="base_info">
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>
				<tbody>
					<tr>
						<th><span class="red">*</span>类别：</th>
						<td>
							<input type="checkbox" class="validate[required]" name="basicInfo.lx" value="综合医院" <c:if test="${pdfn:contains(basicInfo.lx, '综合医院') }">checked="checked"</c:if>/>综合医院&nbsp;
							<input type="checkbox" class="validate[required]" name="basicInfo.lx" value="专科医院" <c:if test="${pdfn:contains(basicInfo.lx, '专科医院') }">checked="checked"</c:if>/>专科医院&nbsp;
							<input type="checkbox" class="validate[required]" name="basicInfo.lx" value="附属医院" <c:if test="${pdfn:contains(basicInfo.lx, '附属医院') }">checked="checked"</c:if>/>附属医院&nbsp;
							<input type="checkbox" class="validate[required]" name="basicInfo.lx" value="教学医院" <c:if test="${pdfn:contains(basicInfo.lx, '教学医院') }">checked="checked"</c:if>/>教学医院
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>级别：</th>
						<td>
							<select style="width:207px;" class='select validate[required]' name="basicInfo.levelRank">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumOrgLevelRankList}" var="orgLevelRank">
									<option value="${orgLevelRank.dictId}" <c:if test="${basicInfo.levelRank eq orgLevelRank.dictId }">selected="selected"</c:if>>${orgLevelRank.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<%--<tr>
						<th>等级：</th>
						<td>
							<input type="radio" name="basicInfo.dj" value="一级" <c:if test="${basicInfo.dj eq '一级' }">checked="checked"</c:if>/>一级&nbsp;
							<input type="radio" name="basicInfo.dj" value="二级" <c:if test="${basicInfo.dj eq '二级' }">checked="checked"</c:if>/>二级&nbsp;
							<input type="radio" name="basicInfo.dj" value="三级" <c:if test="${basicInfo.dj eq '三级' }">checked="checked"</c:if>/>三级
						</td>
					</tr>
					<tr>
						<th>等次：</th>
						<td>
							<input type="radio" name="basicInfo.dc" value="甲等" <c:if test="${basicInfo.dc eq '甲等' }">checked="checked"</c:if>/>甲等&nbsp;
							<input type="radio" name="basicInfo.dc" value="乙等" <c:if test="${basicInfo.dc eq '乙等' }">checked="checked"</c:if>/>乙等&nbsp;
							<input type="radio" name="basicInfo.dc" value="丙等" <c:if test="${basicInfo.dc eq '丙等' }">checked="checked"</c:if>/>丙等&nbsp;
							<input type="radio" name="basicInfo.dc" value="未定等次" <c:if test="${basicInfo.dc eq '未定等次' }">checked="checked"</c:if>/>未定等次
						</td>
					</tr>--%>
					<%--<tr>
						<th>其它：</th>
						<td>
							<input type="text" name="basicInfo.zzOtherInfo" class='input' style="width: 197px;margin-left: 2px;" value="${basicInfo.zzOtherInfo}"
								   maxlength="10" />
						</td>
					</tr>--%>
					<tr>
						<th><span class="red">*</span>培训基地性质：</th>
						<td>
							<input type="radio" class="validate[required]" name="basicInfo.zcdjlx" value="公立医院" <c:if test="${basicInfo.zcdjlx eq '公立医院' }">checked="checked"</c:if>/>公立医院&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; 民营：
							<input type="radio" class="validate[required]" name="basicInfo.zcdjlx" value="私营医院" <c:if test="${basicInfo.zcdjlx eq '私营医院' }">checked="checked"</c:if>/>私营医院&nbsp;
							<input type="radio" class="validate[required]" name="basicInfo.zcdjlx" value="联营医院" <c:if test="${basicInfo.zcdjlx eq '联营医院' }">checked="checked"</c:if>/>联营医院&nbsp;
							<input type="radio" class="validate[required]" name="basicInfo.zcdjlx" value="外资医院" <c:if test="${basicInfo.zcdjlx eq '外资医院' }">checked="checked"</c:if>/>外资医院
						</td>
					</tr>
					<%--<tr>
						<th>分类管理方式：</th>
						<td>
							<input type="radio" name="basicInfo.classificationManagement" value="营利" <c:if test="${basicInfo.classificationManagement eq '营利' }">checked="checked"</c:if>/>营利&nbsp;
							<input type="radio" name="basicInfo.classificationManagement" value="非营利" <c:if test="${basicInfo.classificationManagement eq '非营利' }">checked="checked"</c:if>/>非营利
						</td>
					</tr>--%>
					<tr>
						<th><span class="red">*</span>住院医师基地获批文号：</th>
						<td>
							<select style="width:207px;" class='select validate[required]' name="resBase.resApprovalNumberId">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumResidentBaseApproveNumList}" var="tra">
									<option value="${tra.dictId}" <c:if test="${resBase.resApprovalNumberId eq tra.dictId }">selected="selected"</c:if>>${tra.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>培训基地统一社会信用代码：</th>
						<td><input type="text" name="sysOrg.creditCode"  class='input validate[required]' style="width: 197px;margin-left: 2px;"  value="${sysOrg.creditCode}"/></td>
					</tr>
					<tr>
						<th><span class="red">*</span>执业许可证：</th>
						<td>
							<span id="professionLicenceUrlSpan" style="display:${!empty basicInfo.professionLicenceUrl?'':'none'} ">
								&nbsp; <a href="${sysCfgMap['upload_base_url']}/${basicInfo.professionLicenceUrl}" target="_blank">查看图片</a>&nbsp;
							</span>
							<c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId  }">
								<a id="professionLicenceUrl" href="javascript:uploadFile('professionLicenceUrl','执业许可证图片');" style="margin-left: 2px">${empty basicInfo.professionLicenceUrl?'':'重新'}上传</a>&nbsp;
								<a id="professionLicenceUrlDel" href="javascript:delFile('professionLicenceUrl');" style="${empty basicInfo.professionLicenceUrl?'display:none':''}">删除</a>
								<input type="hidden" id="professionLicenceUrlValue"  name="basicInfo.professionLicenceUrl" value="${basicInfo.professionLicenceUrl}" />
							</c:if>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>医院等级证书：</th>
						<td>
							<span id="hospitalLevelLicenceUrlSpan" style="display:${!empty basicInfo.hospitalLevelLicenceUrl?'':'none'} ">
								&nbsp; <a href="${sysCfgMap['upload_base_url']}/${basicInfo.hospitalLevelLicenceUrl}" target="_blank">查看图片</a>&nbsp;
							</span>
							<c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId}">
								<a id="hospitalLevelLicenceUrl" href="javascript:uploadFile('hospitalLevelLicenceUrl','医院等级证书图片');" style="margin-left: 2px">${empty basicInfo.hospitalLevelLicenceUrl?'':'重新'}上传</a>&nbsp;
								<a id="hospitalLevelLicenceUrlDel" href="javascript:delFile('hospitalLevelLicenceUrl');" style="${empty basicInfo.hospitalLevelLicenceUrl?'display:none':''}">删除</a>
								<input type="hidden" id="hospitalLevelLicenceUrlValue"  name="basicInfo.hospitalLevelLicenceUrl" value="${basicInfo.hospitalLevelLicenceUrl}" />
							</c:if>
						</td>
					</tr>
					<%--<c:if test="${not empty jointOrgFlag and jointOrgFlag eq 'Y'}">
						<tr>
							<th>协同关系协议：</th>
							<td>
								<span id="collaborativeRelationshipAgreementUrlSpan" style="display:${!empty basicInfo.collaborativeRelationshipAgreementUrl?'':'none'} ">
									&nbsp; <a href="${sysCfgMap['upload_base_url']}/${basicInfo.collaborativeRelationshipAgreementUrl}" target="_blank">查看图片</a>&nbsp;
								</span>
								<c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId}">
									<a id="collaborativeRelationshipAgreementUrl" href="javascript:uploadFile('collaborativeRelationshipAgreementUrl','协同关系协议图片');" style="margin-left: 2px">${empty basicInfo.collaborativeRelationshipAgreementUrl?'':'重新'}上传</a>&nbsp;
									<a id="collaborativeRelationshipAgreementUrlDel" href="javascript:delFile('collaborativeRelationshipAgreementUrl');" style="${empty basicInfo.collaborativeRelationshipAgreementUrl?'display:none':''}">删除</a>&nbsp
									<input type="hidden" id="collaborativeRelationshipAgreementUrlValue"  name="basicInfo.collaborativeRelationshipAgreementUrl" value="${basicInfo.collaborativeRelationshipAgreementUrl}" />
								</c:if>
							</td>
						</tr>
					</c:if>--%>
				</tbody>
			</table>
			<table class="base_info">
				<thead>
				<colgroup>
					<col width="30%"/>
					<col width="30%"/>
					<col width="30%"/>
					<col width="10%"/>
				</colgroup>
				<tr>
					<th style="text-align: center"><span class="red">*</span>协同单位名称</th>
					<th style="text-align: center"><span class="red">*</span>专业基地</th>
					<th style="text-align: center"><span class="red">*</span>协同关系协议</th>
					<th style="text-align: center">
						操作
						<span style="float: right;padding-right: 20px">
							<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addJointContract();" />
						</span>
					</th>
				</tr>
				</thead>
				<tbody id="jointContractBody">
				<c:forEach var="jointContract" items="${jointContractList}" varStatus="status">
					<tr>
						<td style="text-align: center">
							<select name="jointOrgFlows" class="select" οnclick="return false;" οnmοusedοwn="return false;">
								<option value="${jointContract.orgFlow}">${jointContract.orgName}</option>
							</select>
						</td>
						<td style="text-align: center">
							<select name="speIds" class="select">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option value="${dict.dictId}" <c:if test="${jointContract.speId eq dict.dictId}">selected</c:if> >${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: center">
							（请选择pdf格式的文件）<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addFile(this);" />
							<c:if test="${not empty jointContract.fileList}">
								<c:forEach items="${jointContract.fileList}" var="contractFile">
									<div style="text-align: center;padding: 2px;margin: 2px" class="jointContractFile">
										<a href="${sysCfgMap['upload_base_url']}/${contractFile.filePath}"
										   target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;width: 60%;background-color: white;color: #409eff">${contractFile.fileName}</a>
										<a onclick="delJointFile(this);" style="background-color: white;color: #409eff">删除</a>
										<input type="hidden" name="jointContractFileFlows" value="${contractFile.fileFlow}" />
									</div>
								</c:forEach>
							</c:if>
							<input type="hidden" name="fileUploadNum" value="0" />
							<input type="hidden" name="fileRemainNum" value="0" />
						</td>
						<td>
							<a onclick="moveTr(this);">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="btn_info">
		<input class="btn_green" onclick="saveBaseInfo()" type="button" value="保&#12288;存"/>
		<input class="btn_green" onclick="hosMain('${sessionScope.currUser.orgFlow}', '')" type="button" value="关&#12288;闭"/>
	</div>
</form>
<div style="display: none">
	<div id="jointContractFileTemplate">
		<div style="text-align: center" class="jointContractFile">
			<input type='file' name='files' class='' onchange="uploadFileCheck(this);"
				   accept=".pdf" style="width: 60%;padding: 2px;margin: 2px"/>
			<a onclick="delJointFile(this);" style="background-color: white;color: #409eff">删除</a>
			<input type="hidden" name="jointContractFileFlows" value="${contractFile.fileFlow}" />
		</div>
	</div>

	<table id="jointContractTemplate">
		<tr>
			<td style="text-align: center">
				<select name="jointOrgFlows" class="select">
					<option value="">请选择</option>
					<c:forEach items="${unjointOrgList}" var="jointOrg">
						<option value="${jointOrg.orgFlow}">${jointOrg.orgName}</option>
					</c:forEach>
					<c:forEach items="${jointContractList}" var="jointOrg">
						<option value="${jointOrg.orgFlow}">${jointOrg.orgName}</option>
					</c:forEach>
				</select>
			</td>
			<td style="text-align: center">
				<select name="speIds" class="select">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
						<option value="${dict.dictId}">${dict.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td style="text-align: center">
				（请选择pdf格式的文件）<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addFile(this);" />
				<input type="hidden" name="fileUploadNum" value="0" />
				<input type="hidden" name="fileRemainNum" value="0" />
			</td>
			<td>
				<a onclick="moveTr(this);">删除</a>
			</td>
		</tr>
	</table>

	<table id="ywfgfzrTemplate">
		<tr>
			<td style="position: relative">
				<input type="text" class="input" style="width: 115px"
					   name="basicInfo.ywfgfzrList[{index}].contactorName"  oninput="valueUpdate(this);"/>
			</td>
			<td>
				<input type="text" class='input validate[custom[mobile]]' style="width: 115px"
					   name="basicInfo.ywfgfzrList[{index}].mobilephone"  oninput="valueUpdate(this);"/>
			</td>
			<td>
				<input type="text" class='input validate[custom[email]]' style="width: 115px"
					   name="basicInfo.ywfgfzrList[{index}].mailAddress"  oninput="valueUpdate(this);"/>
			</td>
			<td>
				<select name="basicInfo.ywfgfzrList[{index}].titleId" class="select" onchange="valueUpdate(this);">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
						<option value="${title.dictId}">${title.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="basicInfo.ywfgfzrList[{index}].postId" class="select" onchange="valueUpdate(this);">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumUserPostList}" var="post">
						<option value="${post.dictId}">${post.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td><a onclick="del(this, 'ywfgfzr', 'ywfgfzrList');">删除</a></td>
		</tr>
	</table>
</div>
<div style="display: none">
	<table id="zpywkslxrTemplate">
		<tr>
			<td style="position: relative">
				<input type="text" class="input" style="width: 115px"
					   name="basicInfo.zpywkslxrList[{index}].contactorName" oninput="valueUpdate(this);" />
			</td>
			<td>
				<input type="text" class='input validate[custom[mobile]]' style="width: 115px"
					   name="basicInfo.zpywkslxrList[{index}].mobilephone" oninput="valueUpdate(this);" />
			</td>
			<td>
				<input type="text" class='input validate[custom[email]]' style="width: 115px"
					   name="basicInfo.zpywkslxrList[{index}].mailAddress" oninput="valueUpdate(this);" />
			</td>
			<td>
				<select name="basicInfo.zpywkslxrList[{index}].titleId" class="select" onchange="valueUpdate(this);">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
						<option value="${title.dictId}">${title.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="basicInfo.zpywkslxrList[{index}].postId" class="select" onchange="valueUpdate(this);">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumUserPostList}" var="post">
						<option value="${post.dictId}">${post.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td><a onclick="del(this, 'zpywkslxr', 'zpywkslxrList');">删除</a></td>
		</tr>
	</table>
</div>
<div style="display: none">
	<table id="zpglbmfzrTemplate">
		<tr>
			<td style="position: relative">
				<input type="text" class="input validate[required]" style="width: 115px"
					   name="basicInfo.zpglbmfzrList[{index}].contactorName"  oninput="valueUpdate(this);"/>
			</td>
			<td>
				<input type="text" class='input validate[custom[mobile]]' style="width: 115px"
					   name="basicInfo.zpglbmfzrList[{index}].mobilephone" oninput="valueUpdate(this);"/>
			</td>
			<td>
				<input type="text" class='input validate[custom[phone]]' style="width: 115px"
					   name="basicInfo.zpglbmfzrList[{index}].fixedPhone" oninput="valueUpdate(this);"/>
			</td>
			<td>
				<input type="text" class='input validate[custom[email]]' style="width: 115px"
					   name="basicInfo.zpglbmfzrList[{index}].mailAddress" oninput="valueUpdate(this);" />
			</td>
			<td>
				<select name="basicInfo.zpglbmfzrList[{index}].titleId" class="select" onchange="valueUpdate(this);">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
						<option value="${title.dictId}">${title.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="basicInfo.zpglbmfzrList[{index}].postId" class="select" onchange="valueUpdate(this);">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumUserPostList}" var="post">
						<option value="${post.dictId}">${post.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td><a onclick="del(this, 'zpglbmfzr', 'zpglbmfzrList');">删除</a></td>
		</tr>
	</table>
</div>
<div style="display: none">
<table id="contactManTemplate">
	<tr>
		<td style="position: relative">
			<input type="text" class="input validate[required]" style="width: 115px"
				   name="basicInfo.contactManList[{index}].contactorName" oninput="valueUpdate(this);" />
		</td>
		<td>
			<input type="text" class='input validate[required,custom[mobile]]' style="width: 115px"
				   name="basicInfo.contactManList[{index}].mobilephone" oninput="valueUpdate(this);"
				   value="${contactMan.mobilephone }"/>
		</td>
		<td>
			<input type="text" class='input validate[required,custom[phone]]' style="width: 115px"
				   name="basicInfo.contactManList[{index}].fixedPhone" oninput="valueUpdate(this);"
				   value="${contactMan.fixedPhone }"/>
		</td>
		<td>
			<input type="text" class='input validate[required,custom[email]]' style="width: 115px"
				   name="basicInfo.contactManList[{index}].mailAddress" oninput="valueUpdate(this);"
				   value="${contactMan.mailAddress }"/>
		</td>
		<td>
			<select name="basicInfo.contactManList[{index}].titleId" class="select" onchange="valueUpdate(this);">
				<option value="">请选择</option>
				<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
					<option value="${title.dictId}">${title.dictName}</option>
				</c:forEach>
			</select>
		</td>
		<td>
			<select name="basicInfo.contactManList[{index}].postId" class="select validate[required]" onchange="valueUpdate(this);">
				<option value="">请选择</option>
				<c:forEach items="${dictTypeEnumUserPostList}" var="post">
					<option value="${post.dictId}">${post.dictName}</option>
				</c:forEach>
			</select>
		</td>
		<td><img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
				 style="cursor: pointer;" onclick="javascript:del(this, 'contactMan', 'contactManList');" /></td>
	</tr>
</table>
</div>