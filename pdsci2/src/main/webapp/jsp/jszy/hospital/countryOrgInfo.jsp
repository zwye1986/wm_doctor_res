<c:if test="${param.viewFlag ne 'Y'}">
	<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
</c:if>
<c:if test="${param.viewFlag eq 'Y'}">
	<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
	</jsp:include>
</c:if>
<style type="text/css">
	input[type='text']{
		text-align: center;
		border-bottom: 1px solid lightgrey;
	}
</style>
<div class="main_hd">
	<c:if test="${param.viewFlag ne 'Y'}">
		<h2>基地信息维护</h2>
	</c:if>
	<div class="div_search">
		<h1 style="text-align:center;font-size:16px;">培训基地（医院）基本情况表</h1>
		<form id="myForm">
			<table class="grid" style="margin-top:10px;">
			<input type="hidden" name="orgFlow" value="${orgInfo.orgFlow}">
			<input type="hidden" name="jsonStr" id="deptList">
			<tr>
				<th style="text-align:right;width:20%;">培训基地（医院）名称：</th>
				<td style="width:30%;">
					<c:if test="${param.viewFlag eq 'Y'}">${empty orgInfo.orgName?sessionScope.currUser.orgName:orgInfo.orgName}</c:if>
					<c:if test="${param.viewFlag ne 'Y'}">
						<input type="text" name="orgName" class="validate[required]" value="${empty orgInfo.orgName?sessionScope.currUser.orgName:orgInfo.orgName}">
					</c:if>
				</td>
				<th style="text-align:right;width:20%;">培训基地（医院）地址：</th>
				<td style="width:30%;">
					<c:if test="${param.viewFlag eq 'Y'}">${orgInfo.orgAddress}</c:if>
					<c:if test="${param.viewFlag ne 'Y'}">
						<input type="text" name="orgAddress" class="validate[required]" value="${orgInfo.orgAddress}">
					</c:if>
				</td>
			</tr>
			<tr>
				<th style="text-align:right;">培训基地（医院）院长：</th>
				<td>
					<c:if test="${param.viewFlag eq 'Y'}">${orgInfo.orgLeader}</c:if>
					<c:if test="${param.viewFlag ne 'Y'}">
						<input type="text" name="orgLeader" class="validate[required]" value="${orgInfo.orgLeader}">
					</c:if>
				</td>
				<th style="text-align:right;">联系电话：</th>
				<td>
					<c:if test="${param.viewFlag eq 'Y'}">${orgInfo.linkPhone}</c:if>
					<c:if test="${param.viewFlag ne 'Y'}">
						<input type="text" name="linkPhone" class="validate[required]" value="${orgInfo.linkPhone}">
					</c:if>
				</td>
			</tr>
			<tr>
				<th style="text-align:right;">编制床位数：</th>
				<td>
					<c:if test="${param.viewFlag eq 'Y'}">${orgInfo.bzBedNum}&nbsp;张</c:if>
					<c:if test="${param.viewFlag ne 'Y'}">
						<input type="text" name="bzBedNum" class="validate[required,custom[integer]]" value="${orgInfo.bzBedNum}">张
					</c:if>
				</td>
				<th style="text-align:right;">开放床位数：</th>
				<td>
					<c:if test="${param.viewFlag eq 'Y'}">${orgInfo.kfBedNum}&nbsp;张</c:if>
					<c:if test="${param.viewFlag ne 'Y'}">
						<input type="text" name="kfBedNum" class="validate[required,custom[integer]]" value="${orgInfo.kfBedNum}">张
					</c:if>
				</td>
			</tr>
			<tr>
				<th>协同单位名称</th>
				<th>协议附件（pdf）</th>
				<th colspan="2">协同单位相关信息</th>
			</tr>
			<c:if test="${param.viewFlag ne 'Y'}">
				<c:forEach items="${jointList}" var="joint" varStatus="i">
					<tr>
						<td>${joint.unitName}</td>
						<td>
							<input type="file" id="xtFile${i.index}" name="file" style="display: none" onchange="uploadFile('xtFile${i.index}','${joint.recordFlow}','1');"/>
							<a class="btn" onclick="$('#xtFile${i.index}').click();"><c:if test="${!empty joint.pdfUrl}">重新</c:if>上传</a>
							<a class='btn' id="xtFile${i.index}A" style="cursor:pointer;<c:if test="${empty joint.pdfUrl}">display:none;</c:if>" href="${sysCfgMap['upload_base_url']}/${joint.pdfUrl}" target="_blank">查看</a>
						</td>
						<td colspan="2"><a class="btn" onclick="editInfo('${joint.recordFlow}','1')">编辑信息</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${param.viewFlag eq 'Y'}">
				<c:forEach items="${jointList}" var="joint">
					<tr>
						<td>${joint.unitName}</td>
						<td><a class='btn' style="cursor:pointer;" <c:if test="${empty joint.pdfUrl}">onclick="jboxTip('暂无pdf可供查看')"</c:if><c:if test="${!empty joint.pdfUrl}">href="${sysCfgMap['upload_base_url']}/${joint.pdfUrl}"</c:if> target="_blank">查看</a></td>
						<td colspan="2"><a class="btn" onclick="editInfo('${joint.recordFlow}','1','view')">查看信息</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<th>基层实践基地名称</th>
				<th>协议附件（pdf）</th>
				<th colspan="2">基层实践基地相关信息<c:if test="${param.viewFlag ne 'Y'}"><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='bindBase()'></c:if></th>
			</tr>
			<tbody class="baseTB">
				<c:if test="${param.viewFlag ne 'Y'}">
					<c:if test="${empty unitList}">
						<tr>
							<td></td>
							<td>
								<input type="file" id="jcFile0" name="file" style="display: none" onchange="uploadFile('jcFile0','${sessionScope.currUser.userCode}flow666666','2');"/>
								<a class="btn" onclick="$('#jcFile0').click();">上传</a>
								<a class='btn' id="jcFile0A" style="cursor:pointer;display:none;" target="_blank">查看</a>
							</td>
							<td colspan="2"><a class="btn" onclick="editInfo('${sessionScope.currUser.userCode}flow666666','2')">编辑信息</a><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)'></td>
						</tr>
					</c:if>
					<c:forEach items="${unitList}" var="unit" varStatus="i">
						<tr>
							<td>${unit.unitName}</td>
							<td>
								<input type="file" id="jcFile${i.index}" name="file" style="display: none" onchange="uploadFile('jcFile${i.index}','${unit.recordFlow}','2');"/>
								<a class="btn" onclick="$('#jcFile${i.index}').click();"><c:if test="${!empty unit.pdfUrl}">重新</c:if>上传</a>
								<a class='btn' id="jcFile${i.index}A" style="cursor:pointer;<c:if test="${empty unit.pdfUrl}">display:none;</c:if>" href="${sysCfgMap['upload_base_url']}/${unit.pdfUrl}" target="_blank">查看</a>
							</td>
							<td colspan="2"><a class="btn" onclick="editInfo('${unit.recordFlow}','2')">编辑信息</a><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick="delTr(this,'${unit.recordFlow}')"></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${param.viewFlag eq 'Y'}">
					<c:forEach items="${unitList}" var="unit">
						<tr>
							<td>${unit.unitName}</td>
							<td><a class='btn' style="cursor:pointer;" <c:if test="${empty unit.pdfUrl}">onclick="jboxTip('暂无pdf可供查看')"</c:if><c:if test="${!empty unit.pdfUrl}">href="${sysCfgMap['upload_base_url']}/${unit.pdfUrl}"</c:if> target="_blank">查看</a></td>
							<td colspan="2"><a class="btn" onclick="editInfo('${unit.recordFlow}','2','view')">查看信息</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tr>
				<th>培训基地带教科室名称</th>
				<th>床位数（张）</th>
				<th colspan="2">带教师资数（人）<c:if test="${param.viewFlag ne 'Y'}"><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='bindDept()'></c:if></th>
			</tr>
			<tbody class="deptTB">
				<c:if test="${param.viewFlag ne 'Y'}">
					<c:if test="${empty deptList}">
						<tr>
							<td><input type="text" class="validate[required]"></td>
							<td><input type="text" class="validate[required,custom[integer]]"></td>
							<td colspan="2"><input type="text" class="validate[required,custom[integer]]"><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)'></td>
						</tr>
					</c:if>
					<c:forEach items="${deptList}" var="dept">
						<tr>
							<td><input type="text" class="validate[required]" value="${dept.deptName}"></td>
							<td><input type="text" class="validate[required,custom[integer]]" value="${dept.bedNum}"></td>
							<td colspan="2"><input type="text" class="validate[required,custom[integer]]" value="${dept.teaNum}"><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)'></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${param.viewFlag eq 'Y'}">
					<c:forEach items="${deptList}" var="dept">
						<tr>
							<td>${dept.deptName}</td>
							<td>${dept.bedNum}</td>
							<td colspan="2">${dept.teaNum}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		</form>
		<c:if test="${param.viewFlag ne 'Y'}">
			<div style="text-align:center;margin-top:20px;"><input class="btn_brown" type="button" onclick="save()" value="保&#12288;存"/></div>
		</c:if>
	</div>
</div>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	function getFlow(){
		var mydate = new Date();
		return "flow"+mydate.getDay()+ mydate.getHours()+ mydate.getMinutes()+mydate.getSeconds()+mydate.getMilliseconds();
	}
	function save(){
		if(!$("#myForm").validationEngine("validate")){
			return false;
		}
		var deptList = [];
		$(".deptTB tr").each(function(i){
			var obj = {
				"deptName" : $(this).find("input:eq(0)").val(),
				"bedNum" : $(this).find("input:eq(1)").val(),
				"teaNum" : $(this).find("input:eq(2)").val()
			};
			deptList.push(obj);
		});
		$("#deptList").val(JSON.stringify(deptList));
		var form = $("#myForm").serialize();
		jboxPost("<s:url value='/jszy/base/saveBaseInfo'/>", form, function (obj) {
			baseInfoManage();
		});
	}
	function editInfo(recordFlow,unitTypeId,viewFlag){
		//基层实践基地初始流水号
//		if(recordFlow == ""){
//			recordFlow = getFlow();
//		}
		jboxOpen("<s:url value='/jszy/base/editJointInfo'/>?recordFlow="+recordFlow+"&unitTypeId="+unitTypeId+"&viewFlag="+viewFlag,(unitTypeId=="1"?"协同单位":"基层实践基地"),600,400);
	}
	function bindDept(){
		var html = "<tr><td><input type='text' class='validate[required]'></td><td><input type='text' class='validate[required,custom[integer]]'></td><td colspan='2'><input type='text' class='validate[required,custom[integer]]'><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)'></td></tr>";
		$(".deptTB").append(html);
	}
	function bindBase(){
		var i = $(".baseTB tr").length;
		var flow = getFlow();
		var html = "<tr><td></td><td><input type='file' id='jcFile"+i+"' name='file' style='display: none' onchange=\"uploadFile('jcFile"+i+"','"+flow+"','2');\"/><a class='btn' onclick=\"$('#jcFile"+i+"').click();\">上传</a>" +
				"<a class='btn' id='jcFile"+i+"A' style='cursor:pointer;display:none;' target='_blank'>查看</a></td>" +
				"<td colspan='2'><a class='btn' onclick=\"editInfo('"+flow+"','2')\">编辑信息</a><img style='cursor:pointer;float:right;' src='<s:url value='/jsp/inx/lcjn/images/reduce.png'/>' onclick='delTr(this)'></td></tr>";
		$(".baseTB").append(html);
	}
	function delTr(obj,recordFlow){
		if(recordFlow !=undefined && recordFlow !=""){
			jboxConfirm("确定删除该记录？",function(){
				jboxPost("<s:url value='/jszy/base/delJointInfo?recordFlow='/>"+recordFlow,null,function(resp){
					$(obj).parents("tr").remove();
				},null,true);
			},null);
		}else{
			$(obj).parents("tr").remove();
		}
	}
	function uploadFile(id,recordFlow,unitTypeId) {
//		//基层实践基地初始流水号
//		var initFlag = false;
//		if(recordFlow == ""){
//			initFlag = true;
//			recordFlow = getFlow();
//		}
		$.ajaxFileUpload({
			url: "<s:url value='/jszy/base/uploadFile'/>?recordFlow="+recordFlow+"&unitTypeId="+unitTypeId,
			secureuri: false,
			fileElementId: id,
			dataType: 'json',
			success: function (data) {
				if (data.indexOf("success") == -1) {
					jboxTip(data);
				} else {
					jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
					var arr = [];
					arr = data.split(":");
					var url = "${sysCfgMap['upload_base_url']}/" + arr[1];
					$("#"+id+"A").siblings("a").text("重新上传");
					$("#"+id+"A").attr("href", url);
					$("#"+id+"A").show();
//					if(initFlag){
//						baseInfoManage();
//					}
				}
			},
			error: function () {
				jboxTip('${GlobalConstant.UPLOAD_FAIL}');
			},
			complete: function () {
				$("#"+id).val("");
			}
		});
	}
</script>