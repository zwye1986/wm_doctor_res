<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
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
	function save(){
		var form = $("#editForm");
		if(false==form.validationEngine("validate")){
			return ;
		}
		var deptNum = $("#deptNum").val();
		if(deptNum){
			var ex = /^[0-9]+\d*$/;
			if (!ex.test(deptNum)) {
				jboxInfo("容纳人数只能为整数！");
				return;
			}
		}
		var deptName = $("#deptName").val();
		var schDeptName = $("#schDeptName").val();
		$("#deptName").val($.trim(deptName));
		$("#schDeptName").val($.trim(schDeptName));
		var requestData = form.serialize();
		var url = "<s:url value='/sch/cfg/saveDept'/>";
		jboxPost(url,requestData,function(resp){
			if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
				window.parent.frames['mainIframe'].window.search("${orgFlow}");
				doClose();
			}
		},null,true);
	}
	
	function doClose(){
		jboxClose();
	}
	function addClass()
	{
		var external=$("#external").val();
		if(external=='Y')
			$("input[name='standardDeptId']").addClass("validate[required]");
		else
			$("input[name='standardDeptId']").removeClass("validate[required]");
	}
	
	function setAttr(isExternal){
		if(isExternal == "${GlobalConstant.FLAG_Y}"){
			$(".isExternal").show();
			$(".notExternal").hide();
			$(".notExternal :input").val("");
		}else{
			$(".isExternal").hide();
			$(".isExternal :input").val("");
			$(".notExternal").show();
		}
	}
	
	function getDeptList(orgFlow){
		$("#externalDept").empty().append('<option/>');
		$("#externalSchDept").empty().append('<option/>');
		jboxPost("<s:url value='/sch/cfg/searchDeptListByOrg'/>?orgFlow="+orgFlow,null,function(resp){
			if(resp!=null&&resp.length){
				for(var index in resp){
					var option = $('<option/>');
					option.attr({"value":resp[index].deptFlow}).text(resp[index].deptName);
					if("${deptExtRel.relDeptFlow}"==resp[index].deptFlow){
						option.attr("selected",true);
						getSchDeptList("${deptExtRel.relDeptFlow}");
					}
					$("#externalDept").append(option);
				}
			}
		},null,false);
	}
	
	function getSchDeptList(deptFlow){
		$("#externalSchDept").empty().append('<option/>');
		jboxPost("<s:url value='/sch/cfg/searchSchDeptListByDept'/>?deptFlow="+deptFlow,null,function(resp){
			if(resp.length){
				for(var index in resp){
					var option = $('<option/>');
					option.attr({"value":resp[index].schDeptFlow}).text(resp[index].schDeptName);
					if("${deptExtRel.relSchDeptFlow}"==resp[index].schDeptFlow){
						option.attr("selected",true);
					}
					$("#externalSchDept").append(option);
				}
			}
		},null,false);
	}
	
	//切换树
	function treeFunc(id){
		var reg = /\./g;
		id = id.replace(reg,"\\.");
		$(".tree"+id).toggle();
		$(".op"+id).toggle();
	}
	
	$(function(){
		setAttr("${schDept.isExternal}");
		$("#externalOrg").change();
		
		$(".treeMain,.sTreeMain").each(function(){
			if($("[class^='tree'] :checked",this).length){
				$("[class^='op']:first",this).click();
			}
		});
	});

	$(document).ready(function(){
		addClass();
	});
	function toSearchStandard(val){
		val = $.trim(val);
		$(".standardDeptToSearch").closest("li").toggle(!val);
		$(".standardDeptToSearch:contains('"+val+"')").closest("li").show();
	}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			<form id="editForm" method="post" style="position: relative;">
				<input type="hidden" name="schDeptFlow" value="${schDept.schDeptFlow}" />
				<input type="hidden" name="deptFlow" value="${schDept.deptFlow}" />
				<input type="hidden" name="orgFlow" value="${orgFlow}" />
				<input type="hidden" name="recordFlow" value="${deptExtRel.recordFlow}" />
				<table width="100%" class="basic" >
					<c:if test="${applicationScope.sysCfgMap['permission_add_sys_dept'] ne 'N'}">
						<tr>
							<th style="width: 25%"><font color="red" >*</font>医院科室（一级）：</th>
							<td style="width: 35%">
								<input type="text"  class="validate[required] xltext" id="deptName" name="deptName"
										<c:if test="${not empty schDept.deptName}">readonly="readonly"</c:if>
									   value="${schDept.deptName}" style="margin-right: 0px" placeholder="例：耳鼻喉科"/>
							</td>
							<th style="width: 40%;text-align: left;padding-left: 10px;">
								关联标准科室
							<span style="float: right">
 								搜索：<input type="text" style="width: 100px;margin-top: -3px;" onkeyup="toSearchStandard(this.value);"/>
							</span>
							</th>
						</tr>
					</c:if>
					<c:if test="${applicationScope.sysCfgMap['permission_add_sys_dept'] eq 'N'}">
						<tr>
							<th style="width: 25%"><font color="red" >*</font>医院科室（一级）：</th>
							<td style="width: 35%">
								<c:if test="${not empty schDept.deptName}">
									<input type="hidden" name="permissionAddSysDeptFlow" value="${schDept.deptFlow}"/>
								</c:if>
								<select class="validate[required] xlselect" name="permissionAddSysDeptFlow"
										<c:if test="${not empty schDept.deptName}">disabled="disabled"</c:if>>
									<option value="">--请选择--</option>
									<c:forEach items="${deptList}" var="sysDept">
										<option value="${sysDept.deptFlow}" <c:if test="${sysDept.deptFlow eq schDept.deptFlow}">selected="selected"</c:if>>${sysDept.deptName}</option>
									</c:forEach>
								</select>
							</td>
							<th style="width: 40%;text-align: left;padding-left: 10px;">
								关联标准科室
							<span style="float: right">
 								搜索：<input type="text" style="width: 100px;margin-top: -3px;" onkeyup="toSearchStandard(this.value);"/>
							</span>
							</th>
						</tr>
					</c:if>
					<tr>
						<th><font color="red" >*</font>实际轮转科室（二级）：</th>
						<td>
							<input type="text"  class="validate[required] xltext" id="schDeptName" name="schDeptName"
								   value="${schDept.schDeptName}" style="margin-right: 0px" placeholder="例：耳科"/>

						</td>
						<td rowspan="6" id="standardDeptHome">
							<div style="height: 100%;overflow: auto;min-height: 180px;">
							
							<c:forEach items="${dictTypeEnumStandardDeptList}" var="dict">
							
								<c:set var="dictKey" value="dictTypeEnumStandardDept.${dict.dictId}List"/>
								
								<ul class="treeMain">
									<li>
										<c:set var="showOp" value="${applicationScope[dictKey].size()>0}" />
										<img class="op${dict.dictId}" onclick="treeFunc('${dict.dictId}');" src="<s:url value='/css/skin/${skinPath}/images/zTreeStandard_03.png'/>" style="cursor: pointer;<c:if test="${!showOp}">visibility: hidden;</c:if>"/>
										<img class="op${dict.dictId}" onclick="treeFunc('${dict.dictId}');" src="<s:url value='/css/skin/${skinPath}/images/zTreeStandard_05.png'/>" style="cursor: pointer;display: none;<c:if test="${!showOp}">visibility: hidden;</c:if>"/>
											
										<label>
											<input name="standardDeptId" type="checkbox" value="${dict.dictId}"
												<c:if test="${!empty deptRelMap[dict.dictId]}">
													checked
												</c:if>
											>
											<span class="standardDeptToSearch">${dict.dictName}</span>
										</label>
									</li>
									
									<li>
										<ul class="sTreeMain">
											<c:forEach items="${applicationScope[dictKey]}" var="sDict">
												<c:set var="sDictId" value="${dict.dictId}.${sDict.dictId}"/>
												<c:set var="sDictName" value="${dict.dictName}.${sDict.dictName}"/>
												<c:set var="dictKey" value="dictTypeEnumStandardDept.${sDictId}List"/>
												
												<li class="tree${dict.dictId}" style="display: none;margin-left: 30px;">
													<c:set var="showOp" value="${applicationScope[dictKey].size()>0}" />
													<img class="op${sDictId}" onclick="treeFunc('${sDictId}');" src="<s:url value='/css/skin/${skinPath}/images/zTreeStandard_03.png'/>" style="cursor: pointer;<c:if test="${!showOp}">visibility: hidden;</c:if>"/>
													<img class="op${sDictId}" onclick="treeFunc('${sDictId}');" src="<s:url value='/css/skin/${skinPath}/images/zTreeStandard_05.png'/>" style="cursor: pointer;display: none;<c:if test="${!showOp}">visibility: hidden;</c:if>"/>
													
													<label>
														<input name="standardDeptId" type="checkbox" value="${sDictId}"
															<c:if test="${!empty deptRelMap[sDictId]}">
																checked
															</c:if>
														>
														<span class="standardDeptToSearch">${sDict.dictName}</span>
													</label>
												</li>
												
												<c:forEach items="${applicationScope[dictKey]}" var="tDict">
													<c:set var="tDictId" value="${dict.dictId}.${sDict.dictId}.${tDict.dictId}"/>
													<c:set var="tDictName" value="${dict.dictName}.${sDict.dictName}.${tDict.dictName}"/>
													
													<li class="tree${sDictId}" style="display: none;margin-left: 60px;">
														<label>
															<input name="standardDeptId" type="checkbox" value="${tDictId}"
																<c:if test="${!empty deptRelMap[tDictId]}">
																	checked
																</c:if>
															>
															<span class="standardDeptToSearch">${tDict.dictName}</span>
														</label>
													</li>
												</c:forEach>
											</c:forEach>
										</ul>
									</li>
									
								</ul>
							</c:forEach>
							</div>
						</td>
					</tr>
					<%--<tr>--%>
						<%--<th><font color="red" >*</font>外院科室：</th>--%>
						<%--<td>--%>
							<%--<select name="isExternal" class="validate[required] xlselect" style="margin-right: 0px" onchange="setAttr(this.value);">--%>
								<%--<option value="${GlobalConstant.FLAG_N}" --%>
								<%--<c:if test="${GlobalConstant.FLAG_N eq schDept.isExternal || empty jointOrgList}">selected</c:if>--%>
								<%-->否</option>--%>
								<%----%>
								<%--<c:if test="${!empty jointOrgList}">--%>
									<%--<option value="${GlobalConstant.FLAG_Y}" --%>
									<%--<c:if test="${GlobalConstant.FLAG_Y eq schDept.isExternal}">selected</c:if>--%>
									<%-->是</option>--%>
								<%--</c:if>--%>
							<%--</select>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr class="isExternal">
						<th><font color="red" >*</font>委培机构：</th>
						<td>
							<select id="externalOrg" name="externalOrgFlow" class="validate[required] xlselect" style="margin-right: 0px" onchange="getDeptList(this.value);">
								<option/>
								<c:forEach items="${jointOrgList}" var="org">
									<option value="${org.jointOrgFlow}" <c:if test="${org.jointOrgFlow eq schDept.externalOrgFlow}">selected</c:if>>${org.jointOrgName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr class="isExternal">
						<th><font color="red" >*</font>委培部门：</th>
						<td>
							<select id="externalDept" name="relDeptFlow" class="validate[required] xlselect" style="margin-right: 0px" onchange="getSchDeptList(this.value);">
								<option value="${deptExtRel.relDeptFlow}" selected>${deptExtRel.relDeptName}</option>
							</select>
						</td>
					</tr>
					<tr class="isExternal">
						<th><font color="red" >*</font>委培科室x：</th>
						<td>
							<select id="externalSchDept" name="relSchDeptFlow" class="validate[required] xlselect" style="margin-right: 0px">
								<option value="${deptExtRel.relSchDeptFlow}" selected>${deptExtRel.relSchDeptName}</option>
							</select>
						</td>
					</tr>
					<tr class="notExternal">
						<th><font color="red" >*</font>容纳人数：</th>
						<td>
							<input type="text" class="validate[custom[number] required] xltext" id="deptNum" name="deptNum" value="${empty schDept.deptNum ? '99' : schDept.deptNum}" style="margin-right: 0px"/>
						</td>
					</tr>
					<tr class="notExternal">
						<th><font color="red" >*</font>是否对外开放：</th>
						<td>
							<input type="radio" value="${GlobalConstant.FLAG_Y}" name="external" ${schDept.external eq GlobalConstant.FLAG_Y?'checked':''}/>&nbsp;是&#12288;
							<input type="radio" value="${GlobalConstant.FLAG_N}" name="external" ${(schDept.external ne GlobalConstant.FLAG_Y) ?'checked':''}/>&nbsp;否
							<%--<select name="external" id="external" class="xlselect" style="margin-right: 0px" onchange="addClass()">--%>
								<%--<option value="">请选择</option>--%>
								<%--<option value="${GlobalConstant.FLAG_Y}" ${schDept.external eq GlobalConstant.FLAG_Y?'selected':''}>是</option>--%>
								<%--<option value="${GlobalConstant.FLAG_N}" ${schDept.external eq GlobalConstant.FLAG_N?'selected':''}>否</option>--%>
							<%--</select>--%>
						</td>
					</tr>
					<tr>
						<th><font color="red" >*</font>排序码：</th>
						<td>
							<input type="text"  class="validate[custom[number] required] xltext"  name="ordinal" value="${empty schDept.ordinal ? ordinal : schDept.ordinal}" style="margin-right: 0px"/>
						</td>
					</tr>
				</table>
			</form>
			<p style="text-align: center;">
	       		<input type="button" onclick="save()"  class="search" value="保&#12288;存"/>
	       		<input type="button" onclick="doClose()" class="search" value="关&#12288;闭"/>
	        </p>
			</div>
		</div>
	</div>
</body>
</html>

	

		
