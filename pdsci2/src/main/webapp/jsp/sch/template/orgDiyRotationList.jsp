
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
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
	function editRotation(rotationFlow){
		var title = "新增轮转方案";
		if(rotationFlow != null && rotationFlow != ''){
			title = "编辑轮转方案";
		}
		jboxOpen("<s:url value='/sch/template/editRotation'/>?roleFlag=${GlobalConstant.USER_LIST_GLOBAL}&realRole=${GlobalConstant.USER_LIST_LOCAL}&rotationFlow="+rotationFlow,title,800,500);
	}
	
	function rule(rotationFlow){
		window.location.href="<s:url value='/sch/template/rule'/>?rotationFlow="+rotationFlow+"&roleFlag=${GlobalConstant.USER_LIST_GLOBAL}&realRole=${GlobalConstant.USER_LIST_LOCAL}";
	}
	
	function publish(rotationFlow){
		jboxConfirm("确认发布?发布后将对所有培训医院可见!",function(){
			updateRotation({rotationFlow:rotationFlow,publishFlag:"${GlobalConstant.FLAG_Y}"});
		});
	}
	
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
	
	function search(){
		$("#rotationForm").submit();
	}
	
	function openDetail(rotationName, rotationFlow){
		//jboxOpenContent($("#"+rotationFlow),"轮转说明",800,300);
		var url = "<s:url value='/sch/template/editRotation'/>?roleFlag=${GlobalConstant.USER_LIST_GLOBAL}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow="+rotationFlow;
		jboxOpen(url, rotationName, 800, 500);
	}
	
	$(function(){
		$(".oper").each(function(){
			if($(this).find("a").length>1){
				$(this).find("a:not(:last)").after('<label>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</label>')
			}
		});
	});
	
	//方案详情
	function rotationDetail(rotationFlow,title){
		jboxOpen("<s:url value='/sch/template/ruleView'/>?rotationFlow="+rotationFlow,title,900,500);
	}
	
	//记录复制所需修改的年份
	var cloneYear = 0;
	//复制方案
	function rotationClone(rotationFlow,rotationName,year){
		cloneYear = year;
		var select = '<select onchange="window.parent.document.mainIframe.cloneYear = this.value;">'+
		'<option value="1" '+(cloneYear=="1"?"selected":"")+'>1</option>'+
		'<option value="2" '+(cloneYear=="2"?"selected":"")+'>2</option>'+
		'<option value="3" '+(cloneYear=="3"?"selected":"")+'>3</option>'+
		'</select>';
		jboxConfirm("确认复制<font color='red'>"+rotationName+"</font>"+"？请选择年限"+select+"后确认！",function(){
			jboxPost("<s:url value='/sch/template/rotationClone'/>?rotationFlow="+rotationFlow+"&rotationYear="+cloneYear,null,function(resp){
				if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					location.reload(true);	
				}
			},null,false);
		},null);
	}
	
	<c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW}">
		function save(rotationFlow) {
			var url = "<s:url value='/sys/cfg/save'/>";
			var data = $('#saveCfgForm'+rotationFlow).serialize();
			jboxPost(url, data,null,null,false);
		}
		
		function toggleCfgModel(){
			$(".rotationData td:not(.rotationName),.rotationData th:not(.rotationName)").toggle();
		}
	</c:if>
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 ">
		<form id="rotationForm" action="<s:url value='/sch/template/orgDiyRotationList'/>" method="get">
			<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<table class="basic" width="100%">
					<tr>
						<td>
							人员类型：
							<select name="doctorCategoryId" style="width: 100px;" onchange="search();">
								<option />
								<c:forEach items="${recDocCategoryEnumList}" var="category">
									<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
									<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
									<option value="${category.id}" ${schRotation.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
									</c:if>
								</c:forEach>
							</select>
							&#12288;&#12288;
							年级：
							<select id="sessionNumber" name="sessionNumber" style="width: 60px" onchange="search();">
								<option />
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
									<option value="${dict.dictName}" ${schRotation.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
							&#12288;&#12288;
							培训专业：
							<select id="speId" name="speId" onchange="search();">
								<option />
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option value="${dict.dictId}" ${schRotation.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
							&#12288;&#12288;
							发布状态：
							<select id="publishFlag" name="publishFlag" onchange="search();">
								<option />
								<option value="${GlobalConstant.FLAG_Y}" ${schRotation.publishFlag eq GlobalConstant.FLAG_Y?'selected':''}>已发布</option>
								<option value="${GlobalConstant.FLAG_N}" ${schRotation.publishFlag eq GlobalConstant.FLAG_N?'selected':''}>未发布</option>
							</select>
							&#12288;&#12288;
							<input type="button" value="新&#12288;增" onclick="editRotation();" class="search">
							<c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW}">
								&#12288;&#12288;
								<input type="button" value="配置表单" onclick="toggleCfgModel();" class="search">
							</c:if>
						</td>
					</tr>
				</table>
				</form>
		</div>
		
<!-- 		<table border="0"  cellspacing="0" cellpadding="0" style="width: 100%;margin-bottom: 10px;"> -->
<!-- 			<caption>轮转方案</caption> -->
<!-- 		</table> -->
		<table class="xllist rotationData" style="width: 100%;">
			<tr>
				<th width="5%" >序号</th>
				<th width="8%">发布状态</th>
				<th width="10%">类型</th>
				<th width="20%" style="text-align: center" class="rotationName">培训方案名称</th>
				<th width="15%" style="text-align: center">培训专业</th>
				<th width="10%" style="text-align: center">年级</th>
				<th width="10%" style="text-align: center">年限</th>
				<th style="text-align: center">操作</th>
				<c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW}">
					<th style="display: none;text-align: center">配置项</th>
				</c:if>
			</tr>
			<tbody>
				<c:forEach items="${rotationList}" var="rotation" varStatus="seq">
					<tr>
						<td style="text-align: center">${seq.index + 1}</td>
						<td>
							<c:if test="${GlobalConstant.FLAG_Y eq rotation.publishFlag}">
								已发布
							</c:if>
							<c:if test="${GlobalConstant.FLAG_N eq rotation.publishFlag}">
								未发布
							</c:if>
						</td>
						<td>
							${rotation.doctorCategoryName}
						</td>
						<td style="text-align: left;padding-left: 10px;" class="rotationName">${rotation.rotationName}</td>
						<td>${rotation.speName}</td>
						<td>${rotation.sessionNumber}</td>
						<td>${rotation.rotationYear}</td>
						<td class="oper" style="text-align: left;padding-left: 10px;">
							<c:if test="${GlobalConstant.FLAG_N eq rotation.publishFlag}">
								<a href="javascript:editRotation('${rotation.rotationFlow}');" style="color: blue;">编辑</a>
								<a style="color: blue;cursor: pointer;" onclick="delRotation('${rotation.rotationFlow}');">删除</a>
								<a style="color: blue;cursor: pointer;" onclick="rule('${rotation.rotationFlow}');">轮转规则</a>
							</c:if>
							
							<a onclick="openDetail('${rotation.rotationName}','${rotation.rotationFlow}');" class="edit" style="color: blue;cursor: pointer;">方案说明</a>
							
							<c:if test="${GlobalConstant.FLAG_Y eq rotation.publishFlag}">
								<a onclick="rotationDetail('${rotation.rotationFlow}','${rotation.rotationName}');" style="color: blue;cursor: pointer;">方案详情</a>
								<a onclick="rotationClone('${rotation.rotationFlow}','${rotation.rotationName}','${rotation.rotationYear}');" style="color: blue;cursor: pointer;">复制方案</a>
							</c:if>
							
							<c:if test="${GlobalConstant.FLAG_N eq rotation.publishFlag}">
								<a style="color: blue;cursor: pointer;" onclick="publish('${rotation.rotationFlow}');">发布</a>
							</c:if>
						</td>
						<c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW}">
							<td style="display: none;">
								<form id="saveCfgForm${rotation.rotationFlow}">
									<c:set var="key" value="res_form_category_${rotation.rotationFlow}"/>
									<input type="hidden" name="cfgCode" value="${key}">
									<select name="${key}" onchange="save('${rotation.rotationFlow}');">
										<option></option>
										<c:forEach items="${applicationScope.resFormDictList}" var="formDict">
											<option value="${formDict.dictId}" <c:if test="${sysCfgMap[key] eq formDict.dictId}">selected</c:if>>${formDict.dictDesc}</option>
										</c:forEach>
									</select>
									
									<input type="hidden" name="${key}_ws_id"  value="res">		
									<input type="hidden" name="${key}_desc"  value="${rotation.rotationName}表单来源">
								</form>
							</td>
						</c:if>
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