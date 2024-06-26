<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="application/javascript">
	function modifyPermission(cfgValue){
		$('#cfgValue').val(cfgValue);
	}
	function bindYear(cfgValue){
		$('#yearCfg').val(cfgValue);
	}
	function bindTrain(cfgValue){
		$('#trainTypeCfg').val(cfgValue);
	}
	function sysCfgUpdate(){
		if(false == $("#myForm").validationEngine("validate")){
			return false;
		}
		$("#yearCfg").val();
		$("#trainTypeCfg").val();
		var url="<s:url value='/gyxjgl/user/sysCfgUpdate'/>";
		jboxPost(url,$('#myForm').serialize(),function(){
		},null,true);
	}
	function checkBDDate(dt){
		var dates = $(':text',$(dt).closest("th"));
		if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
			jboxTip("开始时间不能大于结束时间！");
			dt.value = "";
		}
	}
	function exportPartExcel(flag){
		var url = "<s:url value='/gyxjgl/user/exportExcel?flag='/>"+flag;
		jboxTip("导出中…………");
		var obj = $(parent.window.frames["mainIframe"].document).find("#searchForm");
		jboxSubmit(obj, url,null,null,false);
		jboxEndLoading();
	}
	function checkFile(file){
		var filePath = file.value;
		var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
		if("xlsx" == suffix || "xls" == suffix){
			jboxStartLoading();
			jboxSubmit(
					$('#excelForm'),
					"<s:url value='/gyxjgl/user/importSchoolRollFromExcel'/>",
					function(resp){
						top.jboxInfo(resp);
						if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
							window.parent.frames['mainIframe'].window.location.reload();
						}
					},
					function(resp){
						top.jboxEndLoading();
						top.jboxTip('${GlobalConstant.UPLOAD_FAIL}');
					},false);
		}else{
			$(file).val("");
			jboxTip("请上传Excel文件");
		}
	}
</script>
<c:if test="${!isFeeMaster}">
<div style="text-align:right;<c:if test="${param.from ne GlobalConstant.USER_LIST_GLOBAL}">display:none;</c:if>">
	<span style="float:left;margin-left:5px;;color:red;">注意：1、特定学生需要去学籍信息录入中勾选特定学员</span>
	开放学籍修改权限：
	<input type="radio" name="peradio" onclick="modifyPermission('${GlobalConstant.FLAG_Y}')" ${GlobalConstant.FLAG_Y eq permissionCfg.cfgValue?'checked':''} id="permission_Y" style="cursor: pointer;"/><label for="permission_Y" style="cursor: pointer;">所有学生</label>
	<input type="radio" name="peradio" onclick="modifyPermission('${GlobalConstant.FLAG_N}')" ${GlobalConstant.FLAG_N eq permissionCfg.cfgValue?'checked':''} id="permission_N" style="cursor: pointer;"/><label for="permission_N" style="cursor: pointer;" title="请于学生信息页面选择">特定学生</label>
	<input type="radio" name="peradio" onclick="modifyPermission('')" ${empty permissionCfg.cfgValue?'checked':''} id="permission" style="cursor: pointer;"/><label for="permission" style="cursor: pointer;">取消特定学生</label><!-- 备注：选择此项，"是否允许修改学籍"则隐藏，虽然没变更学生IS_MDF_INFO值，但此值非唯一条件，故而不影响 -->
	<div style="float:right;width:78px;">&#12288;</div><br/>
	<span style="float:left;margin-left:5px;color:red;">&#12288;&#12288;&#12288;2、年级及培养层次针对所有学生（默认不设限制）</span>
	年级：<input type="text" onchange="bindYear(this.value)" value="${yearCfg.cfgValue}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" style="width:80px;"/>
	培养层次：<select style="width:100px;" name="trainTypeCfg" onchange="bindTrain(this.value)"><option/>
				<c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
					<%--硕士、博士、同等学力研究生--%>
					<c:if test="${trainType.dictId eq '1' or trainType.dictId eq '2' or trainType.dictId eq '5'}">
						<option value="${trainType.dictId}" ${trainTypeCfg.cfgValue eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
					</c:if>
				</c:forEach>
             </select>
	<input type="button" class="search"  onclick="sysCfgUpdate();" value="确&#12288;认" />
</div>
</c:if>
<form id="myForm" method="post">
	<input type="hidden" id="cfgValue" name="xjgl_modify_permission" value="${permissionCfg.cfgValue}" />
	<input type="hidden" id="yearCfg" name="yearCfg" value="${yearCfg.cfgValue}" />
	<input type="hidden" id="trainTypeCfg" name="trainTypeCfg" value="${trainTypeCfg.cfgValue}" />
	<c:if test="${!isFeeMaster}">
	<table class="basic" style="width:100%;margin-top:10px;">
		<tr>
			<th style="text-align:left;font-weight:normal;font-size:12px;">&#12288;基本信息：
				<c:if test="${param.from eq GlobalConstant.USER_LIST_GLOBAL}">
					<a href="<s:url value='/jsp/gyxjgl/temeplete/baseInfo.xlsx'/>"><span style="font-size:12px;">基本信息导入模板.xlsx</span></a>
					<input type="button" class="search" onclick="$('#file').click();" value="信息导入"/>
				</c:if>
				<input type="button" class="search" onclick="exportPartExcel('base');" value="信息导出"/>
			</th>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;">
		<tr>
			<th style="text-align:left;font-weight:normal;font-size:12px;">&#12288;录取信息：
				<c:if test="${param.from eq GlobalConstant.USER_LIST_GLOBAL}">
					<a href="<s:url value='/jsp/gyxjgl/temeplete/recruitInfo.xlsx'/>"><span style="font-size:12px;">录取信息导入模板.xlsx</span></a>
					<input type="button" class="search" onclick="$('#file').click();" value="信息导入"/>
				</c:if>
				<input type="button" class="search" onclick="exportPartExcel('recruit');" value="信息导出"/>
			</th>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;">
		<tr>
			<th style="text-align:left;font-weight:normal;border-right:0px;font-size:12px;">&#12288;必填信息：
				<c:if test="${param.from eq GlobalConstant.USER_LIST_GLOBAL}">
					<a href="<s:url value='/jsp/gyxjgl/temeplete/requiredInfo.xlsx'/>"><span style="font-size:12px;">必填信息导入模板.xlsx</span></a>
					<input type="button" class="search" onclick="$('#file').click();" value="信息导入"/>
				</c:if>
				<input type="button" class="search" onclick="exportPartExcel('required');" value="信息导出"/>
			</th>
			<th style="<c:if test="${param.from ne GlobalConstant.USER_LIST_GLOBAL}">display:none;</c:if>">
				<span style="font-size:12px;font-weight:normal;">开放学籍修改时间：</span>
				<input type="text" style="width:100px;" readonly="readonly" name="requiredStartDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${openRequiredStartDate.cfgValue}" onchange="checkBDDate(this);"/>
				- <input type="text" style="width:100px;" readonly="readonly" name="requiredEndDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${closeRequiredEndDate.cfgValue}" onchange="checkBDDate(this);"/>
			</th>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;">
		<tr>
			<th style="text-align:left;font-weight:normal;border-right:0px;font-size:12px;">&#12288;选填信息：
				<c:if test="${param.from eq GlobalConstant.USER_LIST_GLOBAL}">
					<a href="<s:url value='/jsp/gyxjgl/temeplete/optionalInfo.xlsx'/>"><span style="font-size:12px;">选填信息导入模板.xlsx</span></a>
					<input type="button" class="search" onclick="$('#file').click();" value="信息导入"/>
				</c:if>
				<input type="button" class="search" onclick="exportPartExcel('optional');" value="信息导出"/>
			</th>
			<th style="<c:if test="${param.from ne GlobalConstant.USER_LIST_GLOBAL}">display:none;</c:if>">
				<span style="font-size:12px;font-weight:normal;">开放学籍修改时间：</span>
				<input type="text" style="width:100px;" readonly="readonly" name="optionalStartDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${openOptionalStartDate.cfgValue}" onchange="checkBDDate(this);"/>
				- <input type="text" style="width:100px;" readonly="readonly" name="optionalEndDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${closeOptionalEndDate.cfgValue}" onchange="checkBDDate(this);"/>
			</th>
		</tr>
	</table>
	</c:if>
	<table class="basic" style="width:100%;margin-top:10px;">
		<tr>
			<th style="text-align:left;font-weight:normal;font-size:12px;">&#12288;学费信息：
				<c:if test="${param.from eq GlobalConstant.USER_LIST_GLOBAL}">
					<a href="<s:url value='/jsp/gyxjgl/temeplete/feeInfo.xlsx'/>"><span style="font-size:12px;">学费信息导入模板.xlsx</span></a>
					<input type="button" class="search" onclick="$('#file').click();" value="信息导入"/>
				</c:if>
				<input type="button" class="search" onclick="exportPartExcel('fee');" value="信息导出"/>
			</th>
		</tr>
	</table>
	<c:if test="${!isFeeMaster}">
	<table class="basic" style="width:100%;margin-top:10px;">
		<tr>
			<th style="text-align:left;font-weight:normal;border-right:0px;font-size:12px;">&#12288;学业与学位授予信息：
				<c:if test="${param.from eq GlobalConstant.USER_LIST_GLOBAL}">
					<a href="<s:url value='/jsp/gyxjgl/temeplete/degreeGrantInfo.xlsx'/>"><span style="font-size:12px;">学业与学位授予信息导入模板.xlsx</span></a>
					<input type="button" class="search" onclick="$('#file').click();" value="信息导入"/>
				</c:if>
				<input type="button" class="search" onclick="exportPartExcel('degree');" value="信息导出"/>
			</th>
			<th style="<c:if test="${param.from ne GlobalConstant.USER_LIST_GLOBAL}">display:none;</c:if>">
				<span style="font-size:12px;font-weight:normal;">开放学籍修改时间：</span>
				<input type="text" style="width:100px;" readonly="readonly" name="degreeGrantStartDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${openDegreeGrantStartDate.cfgValue}" onchange="checkBDDate(this);"/>
				- <input type="text" style="width:100px;" readonly="readonly" name="degreeGrantEndDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${closeDegreeGrantEndDate.cfgValue}" onchange="checkBDDate(this);"/>
			</th>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;">
		<tr>
			<th style="text-align:left;font-weight:normal;border-right:0px;font-size:12px;">&#12288;学位论文信息：
				<c:if test="${param.from eq GlobalConstant.USER_LIST_GLOBAL}">
					<a href="<s:url value='/jsp/gyxjgl/temeplete/paperInfo.xlsx'/>"><span style="font-size:12px;">学位论文信息导入模板.xlsx</span></a>
					<input type="button" class="search" onclick="$('#file').click();" value="信息导入"/>
				</c:if>
				<input type="button" class="search" onclick="exportPartExcel('paper');" value="信息导出"/>
			</th>
			<th style="<c:if test="${param.from ne GlobalConstant.USER_LIST_GLOBAL}">display:none;</c:if>">
				<span style="font-size:12px;font-weight:normal;">开放学籍修改时间：</span>
				<input type="text" style="width:100px;" readonly="readonly" name="paperStartDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${openPaperStartDate.cfgValue}" onchange="checkBDDate(this);"/>
				- <input type="text" style="width:100px;" readonly="readonly" name="paperEndDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${closePaperEndDate.cfgValue}" onchange="checkBDDate(this);"/>
			</th>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;">
		<tr>
			<th style="text-align:left;font-weight:normal;border-right:0px;font-size:12px;">&#12288;就业信息：
				<c:if test="${param.from eq GlobalConstant.USER_LIST_GLOBAL}">
					<a href="<s:url value='/jsp/gyxjgl/temeplete/employmentInfo.xlsx'/>"><span style="font-size:12px;">就业信息导入模板.xlsx</span></a>
					<input type="button" class="search" onclick="$('#file').click();" value="信息导入"/>
				</c:if>
				<input type="button" class="search" onclick="exportPartExcel('employment');" value="信息导出"/>
			</th>
			<th style="<c:if test="${param.from ne GlobalConstant.USER_LIST_GLOBAL}">display:none;</c:if>">
				<span style="font-size:12px;font-weight:normal;">开放学籍修改时间：</span>
				<input type="text" style="width:100px;" readonly="readonly" name="employmentStartDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${openEmploymentStartDate.cfgValue}" onchange="checkBDDate(this);"/>
				- <input type="text" style="width:100px;" readonly="readonly" name="employmentEndDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${closeEmploymentEndDate.cfgValue}" onchange="checkBDDate(this);"/>
			</th>
		</tr>
	</table>
		<table class="basic" style="width:100%;margin-top:10px;<c:if test="${param.from ne GlobalConstant.USER_LIST_GLOBAL}">display:none;</c:if>">
			<tr>
				<th style="text-align:left;font-weight:normal;border-right:0px;font-size:12px;">&#12288;登记信息：</th>
				<th>
					<span style="font-size:12px;font-weight:normal;">开放学籍修改时间：</span>
					<input type="text" style="width:100px;" readonly="readonly" name="registerStartDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${openRegisterStartDate.cfgValue}" onchange="checkBDDate(this);"/>
					- <input type="text" style="width:100px;" readonly="readonly" name="registerEndDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${closeRegisterEndDate.cfgValue}" onchange="checkBDDate(this);"/>
				</th>
			</tr>
		</table>
	</c:if>
</form>
<form id="excelForm" method="post" enctype="multipart/form-data">
	<input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]" style="display:none;"/>
</form>
