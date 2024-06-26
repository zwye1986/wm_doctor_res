<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>

<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script>
	//移除或恢复
	function editRotationDept(recordFlow,recordStatus){
		$(".recordStatus","#"+recordFlow).val(recordStatus);
		$("td:first","#"+recordFlow).toggleClass("isRemove");
		$(".remove,.recovery,.monthOperLimit","#"+recordFlow).toggle();
		$("label.monthOperLimit","#"+recordFlow).text($("input.monthOperLimit","#"+recordFlow).val());
		sumBySchMonth();
	}

	//保存调整
	function saveOper(){
		var $data = $(".isData");
		var dataLength = $data.length;
		var removeLength = $(".recordStatus[value='${GlobalConstant.RECORD_STATUS_N}']").length;
		if(dataLength == removeLength){
			return jboxTip("至少保留一个轮转科室！");
		}
		
		var doctorDeptList = [];
		
		$data.each(function(){
			var obj = {
					recordFlow:$(".recordFlow",this).val() || "",
					schMonth:$.trim($(".schMonth",this).val()) || "",
					groupFlow:$(".groupFlow",this).val() || "",
					ordinal:$(".ordinal",this).val() || "",
					standardDeptId:$(".standardDeptId",this).val() || "",
					standardDeptName:$(".standardDeptName",this).val() || "",
					isRequired:$(".isRequired",this).val() || "",
					recordStatus:$(".recordStatus",this).val() || "${GlobalConstant.RECORD_STATUS_Y}",
					doctorFlow:"${doctor.doctorFlow}",
					doctorName:"${doctor.doctorName}",
					orgFlow:"${doctor.orgFlow}",
					orgName:"${doctor.orgName}",
					rotationFlow:"${doctor.rotationFlow}",
					rotationName:"${doctor.rotationName}",
					sessionNumber:"${doctor.sessionNumber}",
			};
			doctorDeptList.push(obj);
		});
		
		$("#saveBtn").hide();
		jboxPostJson("<s:url value='/jsres/manage/saveReductionOper'/>",
				JSON.stringify(doctorDeptList),
			function(resp){
				if(resp="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					location.reload();
					top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				}
			},null,false);
	}
	
	//检查时间是否合法
	function checkTheMonth(currInput){
		var value = currInput.value-0;
		var old = $(currInput).attr("old");
		
		if(!value){
			currInput.value = old;
			return jboxTip("请输入一个不为0的数字！");
		}
		
// 		var standard = $(currInput).attr("standard")-0;
// 		if(value>standard){
// 			currInput.value = old;
// 			return jboxTip("调整后轮转时间必须小于标准轮转时间！");
// 		}
	}
	
	//切换证明和调整视图
	function provToggle(){
		$("#reductionProv,#operDiv,#saveBtn").fadeToggle(300);
		var toggleVal = $("#reductionBtn").attr("toggleVal");
		$("#reductionBtn").attr("toggleVal",$("#reductionBtn").val());
		$("#reductionBtn").val(toggleVal);
	}
	
	//上传证明文件且保存进recruit
	function checkFile(obj){
		var flow = $(obj).attr("currFlow");
		$.ajaxFileUpload({
			url:"<s:url value='/jsres/doctor/uploadTrainYearFileAndSaveRecruit'/>?recruitFlow="+flow,
			secureuri:false,
			fileElementId:"file",
			dataType: 'json',
			success: function (data){
				if(data!="${GlobalConstant.OPRE_FAIL_FLAG}"){
					$("#"+flow).attr("src","${sysCfgMap['upload_base_url']}/"+data);
				}else{
					jboxTip('${GlobalConstant.UPLOAD_FAIL}');
				}
			},
			error: function (data, status, e){
				jboxTip('${GlobalConstant.UPLOAD_FAIL}');
			},
			complete:function(){
			}
		});
	}
	
	//选择图片
	function choseFile(chooseFlow){
		$("#file").attr("currFlow",chooseFlow).click();
	}
	
	//统计时间
	function sumBySchMonth(){
		var monthSum = 0;
		$(".isData").each(function(){
			if(!$(".isRemove",this).length){
				monthSum+=(($("label.schMonth",this).text() || 0)-0);
				monthSum+=(($("input.schMonth",this).val() || 0)-0);
			}
		});
		$("#schMonth").text(monthSum);
	}
	
	//页面初始化
	$(function(){
		sumBySchMonth();
		$("input.schMonth").change(function(){
			sumBySchMonth();
		});
	});
	
	function closeWindow(){
		top.$("#reducationTab .tab_select").click();
		jboxClose();
	}
</script>
<style>
	.isRemove{color: red;}
</style>
</head>
<c:set var="currOrg" value="${currUser.orgFlow eq doctor.orgFlow}"/>
<body>
	<div style="height: 440px;overflow: auto;">
		<table id="operDiv" border="0" cellpadding="0" cellspacing="0" class="grid">
			<colgroup>
        		<col width="25%"/>
        		<col width="13%"/>
        		<col width="13%"/>
        		<col width="30%"/>
        		<col width="19%"/>
        	</colgroup>
			<tr>
				<th>轮转科室</th>
				<th>标准时间(月)</th>
				<th>轮转时间(月)</th>
				<th>备注</th>
				<c:if test="${currOrg}">
					<th>操作</th>
				</c:if>
			</tr>
			<c:set var="sumStandardMonth" value="0"/>
			<c:forEach items="${groupList}" var="group">
				<c:set var="sumStandardMonth" value="${sumStandardMonth+group.schMonth}"/>
				<c:set var="rotationDeptList" value="${rotationDeptListMap[group.groupFlow]}" />
				
				<tr>
					<th style="text-align: left;padding-left: 20px;">
						[${group.schStageName}${group.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}]${group.groupName}
					</th>
					<th>
						${group.schMonth}
					</th>
					<th></th>
					<th></th>
					<c:if test="${currOrg}">
						<th></th>
					</c:if>
				</tr>
				
				<c:forEach items="${rotationDeptList}" var="rotationDept">
					<c:set var="key" value="${rotationDept.groupFlow}${rotationDept.standardDeptId}"/>
					<c:set var="isNotData" value="${doctorDeptMap[key].recordStatus eq GlobalConstant.RECORD_STATUS_N}"/>
					<tr id="${rotationDept.recordFlow}" class="isData">
						<td style="text-align: left;padding-left: 20px;" class="<c:if test="${isNotData}">isRemove</c:if>">
							${rotationDept.standardDeptName}
						</td>
						<td>
							${rotationDept.schMonth}
						</td>
						<td>
							<c:if test="${currOrg}">
								<input 
								old="<c:out value="${doctorDeptMap[key].schMonth}" default="${rotationDept.schMonth}"/>"
								standard="${rotationDept.schMonth}" 
								type="text" 
								class="schMonth monthOperLimit" 
								value="<c:out value="${doctorDeptMap[key].schMonth}" default="${rotationDept.schMonth}"/>" 
								style="height: 20px;width: 30px;text-align: center;border: #ccc 1px solid;<c:if test="${isNotData}">display: none;</c:if>" 
								onchange="checkTheMonth(this);"
								/>
								<label class="monthOperLimit" style="<c:if test="${!isNotData}">display: none;</c:if>">
									<c:out value="${doctorDeptMap[key].schMonth}" default="${rotationDept.schMonth}"/>
								</label>
							</c:if>
							<c:if test="${!currOrg}">
								<label class="schMonth">
									<c:out value="${doctorDeptMap[key].schMonth}" default="${rotationDept.schMonth}"/>
								</label>
							</c:if>
						</td>
						<td style="text-align: left;padding-left: 20px;">${rotationDept.deptNote}</td>
						<c:if test="${currOrg}">
							<td>
								<input type="hidden" class="recordFlow" value="${doctorDeptMap[key].recordFlow}"/>
								<input type="hidden" class="groupFlow" value="${rotationDept.groupFlow}"/>
								<input type="hidden" class="ordinal" value="${rotationDept.ordinal}"/>
								<input type="hidden" class="standardDeptId" value="${rotationDept.standardDeptId}"/>
								<input type="hidden" class="standardDeptName" value="${rotationDept.standardDeptName}"/>
								<input type="hidden" class="isRequired" value="${rotationDept.isRequired}"/>
								<input type="hidden" class="recordStatus" value="${doctorDeptMap[key].recordStatus}"/>
								<a onclick="editRotationDept('${rotationDept.recordFlow}','${GlobalConstant.RECORD_STATUS_N}');" class="btn remove" style="<c:if test="${isNotData}">display: none;</c:if>">移除</a>
								<a onclick="editRotationDept('${rotationDept.recordFlow}','${GlobalConstant.RECORD_STATUS_Y}');" class="btn recovery" style="<c:if test="${!isNotData}">display: none;</c:if>">恢复</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:forEach>
			<tr>
				<th style="text-align: left;padding-left: 20px;">总计</th>
				<th>${sumStandardMonth}月</th>
				<th>
					<label id="schMonth"></label>月
				</th>
				<th></th>
				<c:if test="${currOrg}">
					<th></th>
				</c:if>
			</tr>
		</table>
		<div id="reductionProv" style="width: 100%;height: 440px;display: none;background: white;position: absolute;top: 0px;left: 0px;">
			<c:forEach items="${recruitList}" var="recruit">
				<div style="float: left;width: 30%;height: 180px;cursor: pointer;margin-right: 10px;margin-bottom: 20px;" 
				<c:if test="${currOrg}">
					title="点击重新上传" 
					onclick="choseFile('${recruit.recruitFlow}');"
				</c:if>
				>
					<img id="${recruit.recruitFlow}" src="${sysCfgMap['upload_base_url']}/<c:out value="${recruit.proveFileUrl}" default="${userResumeExt.degreeUri}"/>" style="width: 100%;height: 100%;"/>
					<div align="center" style="font-weight: bold;">${recruit.catSpeName}(${recruit.speName})</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div align="center" style="margin-top: 10px;">
		<c:if test="${currOrg}">
			<input id="saveBtn" type="button" value="保&#12288;存" class="btn_green" onclick="saveOper();"/>
		</c:if>
<!-- 		<input id="reductionBtn" type="button" value="查看减免证明" toggleVal="方案减免维护" class="btn_green" onclick="provToggle();"/> -->
		<input type="button" value="关&#12288;闭" class="btn_green" onclick="closeWindow();"/>
	</div>
	
	<input onchange="checkFile(this);" type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none" currFlow=""/>
</body>
</html>
 