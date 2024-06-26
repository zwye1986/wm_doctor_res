
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
		<jsp:param name="jquery_validation" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>

	<style type="text/css">
		/*table{ margin:10px 0;border-collapse: collapse;}*/
		/*caption,th,td{height:40px;}*/
		caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
		/*th{text-align:right;font-weight:500;padding-right:5px; color:#333;}*/
		/*td{text-align:left; padding-left:5px; color:#999;}*/
	</style>

	<script type="text/javascript">

		function rule(rotationFlow){
			var orgFlow = $("#orgFlow").val() || "";
			window.location.href="<s:url value='/sch/rotationCfg/rule'/>?rotationFlow="+rotationFlow+"&orgFlow="+orgFlow;
		}


		function search(){
			$("#rotationForm").submit();
		}

		function openDetail(rotationName, rotationFlow){
			//jboxOpenContent($("#"+rotationFlow),"轮转说明",800,300);
			var url = "<s:url value='/sch/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow="+rotationFlow;
			jboxOpen(url, rotationName, 800, 500);
		}

		$(function(){
			$(".oper").each(function(){
				if($(this).find("a").length>1){
					$(this).find("a:not(:last)").after('<label>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</label>')
				}
			});
			$("[onclick]").click(function(e){e.stopPropagation();});
		});
		function delRotation(rotationFlow){
			jboxConfirm("确认删除?",function(){
				updateRotation({rotationFlow:rotationFlow,recordStatus:"${GlobalConstant.FLAG_N}"});
			});
		}
		function updateRotation(rotation){
			jboxPost("<s:url value='/sch/template/publishRotation'/>",rotation,function(resp){
				if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
					search();
					top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				}
			},null,false);
		}
		function schRotationImport(){
			$("#file").click();
		}
		function importRotate() {
			var url = "<s:url value='/sch//importRotate'/>";
			jboxOpen(url, "导入轮转方案", 700, 250);
		}
		function checkFile(file){
			var filePath = file.value;
			var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
			if("xml" == suffix){
				$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
				jboxConfirm("确认导入标准轮转方案?",function(){
					var url = "<s:url value='/res/doc/checkFile'/>";
					jboxSubmit(
							$('#xmlForm'),
							url,
							function(s){
								top.jboxTip("新增  "+"<span style='color: red'>"+s+"</span>"+" 条方案！");
								window.location.href=window.location.href;
							},
							function(resp){

							},false);
				});
			}else{
				$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
				$(file).val(null);
				jboxTip("请上传xml文件");
			}

		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 ">
			<form id="form"></form>
			<form id="rotationForm" action="<s:url value='/sch/rotationCfg/list'/>" method="post">
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="publishFlag" value="${param.publishFlag}"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">培训方案：</label>
						<input id="rotationName" name="rotationName" type="text" class="qtext" value="${rotationName}"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">人员类型：</label>
						<select name="doctorCategoryId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${recDocCategoryEnumList}" var="category">
								<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
								<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
									<option value="${category.id}" ${schRotation.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select id="speId" name="speId" class="qselect">
							<option/>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}(二级专业)</option>
							</c:forEach>
						</select>
					</div>

					<div class="lastDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>

					</div>
					<div class="lastDiv">
						<input type="button" value="导入" onclick="importRotate();" class="searchInput"/>
					</div>
				</div>
			</form>
		</div>
		<table class="xllist rotationData" style="width: 100%;">
			<tr>
				<th width="5%" >序号</th>
				<th width="10%">类型</th>
				<th width="20%" style="text-align: center" class="rotationName">培训方案名称</th>
				<th width="15%" style="text-align: center">培训专业</th>
				<th width="10%" style="text-align: center">年限(总时间)</th>
				<th style="text-align: center">操作</th>
			</tr>
			<tbody>
			<c:forEach items="${rotationList}" var="rotation" varStatus="seq">
				<tr class="body _${rotation.speId}">
					<td style="text-align: center">${seq.index + 1}</td>
					<td>
							${rotation.doctorCategoryName}
					</td>
					<td class="rotationName" style="text-align: left;padding-left: 10px;">
							${rotation.rotationName}
					</td>
					<td>${rotation.speName}</td>
					<td>${rotation.rotationYear}年(${mustSumMap[rotation.rotationFlow] + groupSumMap[rotation.rotationFlow] + 0}月)</td>
					<td class="oper" style="text-align: left;padding-left: 10px;">
						<a style="color: blue;cursor: pointer;" onclick="rule('${rotation.rotationFlow}');">轮转规则</a>
						<a onclick="openDetail('${rotation.rotationName}','${rotation.rotationFlow}');" class="edit" style="color: blue;cursor: pointer;">方案说明</a>
						<a style="color: blue;cursor: pointer;" onclick="delRotation('${rotation.rotationFlow}');">删除</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<c:if test="${empty rotationList}">
				<tr><td align="center" colspan="8">无记录</td></tr>
			</c:if>
		</table>
	</div>
</div>
</body>
</html>
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
		<jsp:param name="jquery_validation" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>

	<style type="text/css">
		/*table{ margin:10px 0;border-collapse: collapse;}*/
		/*caption,th,td{height:40px;}*/
		caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
		/*th{text-align:right;font-weight:500;padding-right:5px; color:#333;}*/
		/*td{text-align:left; padding-left:5px; color:#999;}*/
	</style>

	<script type="text/javascript">

		function rule(rotationFlow){
			var orgFlow = $("#orgFlow").val() || "";
			window.location.href="<s:url value='/sch/rotationCfg/rule'/>?rotationFlow="+rotationFlow+"&orgFlow="+orgFlow;
		}


		function search(){
			$("#rotationForm").submit();
		}

		function openDetail(rotationName, rotationFlow){
			//jboxOpenContent($("#"+rotationFlow),"轮转说明",800,300);
			var url = "<s:url value='/sch/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow="+rotationFlow;
			jboxOpen(url, rotationName, 800, 500);
		}

		$(function(){
			$(".oper").each(function(){
				if($(this).find("a").length>1){
					$(this).find("a:not(:last)").after('<label>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</label>')
				}
			});
			$("[onclick]").click(function(e){e.stopPropagation();});
		});
		function delRotation(rotationFlow){
			jboxConfirm("确认删除?",function(){
				updateRotation({rotationFlow:rotationFlow,recordStatus:"${GlobalConstant.FLAG_N}"});
			});
		}
		function updateRotation(rotation){
			jboxPost("<s:url value='/sch/template/publishRotation'/>",rotation,function(resp){
				if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
					search();
					top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				}
			},null,false);
		}
		function schRotationImport(){
			$("#file").click();
		}
		function importRotate() {
			var url = "<s:url value='/sch//importRotate'/>";
			jboxOpen(url, "导入轮转方案", 700, 250);
		}
		function checkFile(file){
			var filePath = file.value;
			var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
			if("xml" == suffix){
				$("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
				jboxConfirm("确认导入标准轮转方案?",function(){
					var url = "<s:url value='/res/doc/checkFile'/>";
					jboxSubmit(
							$('#xmlForm'),
							url,
							function(s){
								top.jboxTip("新增  "+"<span style='color: red'>"+s+"</span>"+" 条方案！");
								window.location.href=window.location.href;
							},
							function(resp){

							},false);
				});
			}else{
				$("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
				$(file).val(null);
				jboxTip("请上传xml文件");
			}

		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 ">
			<form id="form"></form>
			<form id="rotationForm" action="<s:url value='/sch/rotationCfg/list'/>" method="post">
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="publishFlag" value="${param.publishFlag}"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">培训方案：</label>
						<input id="rotationName" name="rotationName" type="text" class="qtext" value="${rotationName}"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">人员类型：</label>
						<select name="doctorCategoryId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${recDocCategoryEnumList}" var="category">
								<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
								<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
									<option value="${category.id}" ${schRotation.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select id="speId" name="speId" class="qselect">
							<option/>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}(二级专业)</option>
							</c:forEach>
						</select>
					</div>

					<div class="lastDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>

					</div>
					<div class="lastDiv">
						<input type="button" value="导入" onclick="importRotate();" class="searchInput"/>
					</div>
				</div>
			</form>
		</div>
		<table class="xllist rotationData" style="width: 100%;">
			<tr>
				<th width="5%" >序号</th>
				<th width="10%">类型</th>
				<th width="20%" style="text-align: center" class="rotationName">培训方案名称</th>
				<th width="15%" style="text-align: center">培训专业</th>
				<th width="10%" style="text-align: center">年限(总时间)</th>
				<th style="text-align: center">操作</th>
			</tr>
			<tbody>
			<c:forEach items="${rotationList}" var="rotation" varStatus="seq">
				<tr class="body _${rotation.speId}">
					<td style="text-align: center">${seq.index + 1}</td>
					<td>
							${rotation.doctorCategoryName}
					</td>
					<td class="rotationName" style="text-align: left;padding-left: 10px;">
							${rotation.rotationName}
					</td>
					<td>${rotation.speName}</td>
					<td>${rotation.rotationYear}年(${mustSumMap[rotation.rotationFlow] + groupSumMap[rotation.rotationFlow] + 0}月)</td>
					<td class="oper" style="text-align: left;padding-left: 10px;">
						<a style="color: blue;cursor: pointer;" onclick="rule('${rotation.rotationFlow}');">轮转规则</a>
						<a onclick="openDetail('${rotation.rotationName}','${rotation.rotationFlow}');" class="edit" style="color: blue;cursor: pointer;">方案说明</a>
						<a style="color: blue;cursor: pointer;" onclick="delRotation('${rotation.rotationFlow}');">删除</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<c:if test="${empty rotationList}">
				<tr><td align="center" colspan="8">无记录</td></tr>
			</c:if>
		</table>
	</div>
</div>
</body>
</html>