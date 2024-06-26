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
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script>
	function save(){
		if(!$("#editForm").validationEngine("validate")){
			return false;
		}
		var startDate = $("input[name='startDate']").val();
		var endDate = $("input[name='endDate']").val();
		if(endDate < startDate){
			jboxTip("结束日期不能早于开始日期！");
			return false;
		}
		var v=$("#intervalDays").val()||"";
		if(v)
		{
			var isNum=$.trim(v) && !isNaN(v) && parseFloat(v)>0;
			if(!isNum){

				return jboxTip("请假天数必须为数字且大于0！");
			}
			var y = String(v).indexOf(".") + 1;//获取小数点的位置
			if(y>0) {
				var m = v.substr(y) || "0";
				var count = String(v).length - y;//获取小数点后的个数
				if (count > 0 ) {
					if (parseFloat(m) != 5&&parseFloat(m) != 0) {
						return jboxTip("请假天数必须是0.5正整数倍！");
					}
				}
			}
		}else{
			return jboxTip("请输入请假天数！");
		}
		jboxConfirm("提交后无法修改，确认提交？",function(){
			var url = "<s:url value='/res/doctorSignin/saveLeave'/>";
			jboxSubmit($('#editForm'),url,function (resp) {
				if ("1" == resp) {
					jboxTip("保存成功！")
					setTimeout(function () {
						window.parent.frames['mainIframe'].search();
						jboxClose();
					}, 1000);
				}else{
					jboxTip(resp);
				}
			},null,false);

		})
	}

	function addFile(){
		$('#filesTable').append($("#fileTableFormat tr:eq(0)").clone());
	}
	function moveTr(obj){
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
		});
	}
	function selectProcess()
	{
		var startDate=$("#startDate").val()||"";
		var endDate=$("#endDate").val()||"";
		console.log("startDate:"+startDate+" endDate:"+endDate);
		if(startDate&&endDate)
		{
			if(startDate>endDate)
			{
				jboxTip("结束日期不能早于开始日期！");
				return false;
			}
			var v="";
			$("#processFlow").find("option[value!='']").each(function(){
				var schStartDate=$(this).attr("schStartDate")||"";
				var schEndDate=$(this).attr("schEndDate")||"";
				if(schEndDate&&schStartDate)
				{
					schStartDate=schStartDate+" 00:00";
					schEndDate=schEndDate+" 23:59";
					if(!(schEndDate<startDate||endDate<schStartDate)){
						v=$(this).attr("value")||"";
					}
				}
			});
			$("#processFlow").find("option[value='"+v+"']").attr("selected","selected");
		}else {
			$("#processFlow").find("option[value='']").attr("selected","selected");
		}
	}
	$(function(){
//		$("#startDate").bind("change",function(){
//			selectProcess();
//		});
//		$("#endDate").bind("change",function(){
//			selectProcess();
//		});
		$("#intervalDays").bind("change",function(){
			var v=$(this).val()||"";
			if(v)
			{
				var isNum=$.trim(v) && !isNaN(v) && parseFloat(v)>0;
				if(!isNum){
					$(this).val("");
					return jboxTip("请假天数必须为数字且大于0！");
				}
				var y = String(v).indexOf(".") + 1;//获取小数点的位置
				if(y>0) {
					var m = v.substr(y) || "0";
					var count = String(v).length - y;//获取小数点后的个数
					if (count > 0 ) {
						if (parseFloat(m) != 5&&parseFloat(m) != 0) {
							$(this).val("");
							return jboxTip("请假天数必须是0.5正整数倍！");
						}
					}
				}
			}
		});
		<c:if test="${not empty doctor and not empty doctor.doctorCategoryId}">
			<c:if test="${doctor.doctorCategoryId eq recDocCategoryEnumGraduate.id and empty doctor.tutorFlow }">
				$("#editForm").hide();
			</c:if>
			<c:if test="${!(doctor.doctorCategoryId eq recDocCategoryEnumGraduate.id and empty doctor.tutorFlow )}">
					<c:if test="${not empty processes}">
						<c:if test="${empty cfgs || fn:length(cfgs)==0}">
							$("#editForm").hide();
						</c:if>
					</c:if>
					<c:if test="${ empty processes}">
						$("#editForm").hide();
					</c:if>
			</c:if>
		</c:if>
		<c:if test="${!(not empty doctor and not empty doctor.doctorCategoryId) }">
			$("#editForm").hide();
		</c:if>
	});
</script>
</head>
<body>
	<div class="mainright" align="center">
	<div class="content">
	<div class="title1 clearfix">
		<form id="editForm" style="position: relative;" enctype="multipart/form-data">
		<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
		<input type="hidden" name="kqTypeId" value="${dictTypeEnumLeaveType.id}"/>
		<input type="hidden" name="recordFlow" value="${kq.recordFlow}"/>
		<input type="hidden" name="doctorFlow" value="${kq.doctorFlow}"/>
		<input type="hidden" name="doctorName" value="${kq.doctorName}"/>
		<table class="basic" style="width: 100%;">
			<colgroup>
				<col width="17%"/>
				<col width="33%"/>
				<col width="17%"/>
				<col width="33%"/>
			</colgroup>
			<tbody>
				<tr>
					<th><font color="red" >*</font>请假类型：</th>
					<td colspan="3">
						<select name="typeId" class="qselect validate[required]" style="width: 200px;">
							<option value="">请选择</option>
							<c:forEach var="type" items="${dictTypeEnumLeaveTypeList}">
								<option value="${type.dictId}" <c:if test="${kq.typeId==type.dictId}">selected="selected"</c:if> >${type.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>请假时间：</th>
					<td>
						<input type="text" id="startDate" name="startDate" value="${kq.startDate}"  onclick="WdatePicker({onpicked:selectProcess,dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{\'%y-%M-\#\{%d+1\} 00:00\'}'})"
							   class="qtime validate[required]" readonly="readonly" style="width: 110px;"/>
						~ <input type="text" id="endDate" name="endDate" value="${kq.endDate}"  onclick="WdatePicker({onpicked:selectProcess,dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{\'%y-%M-\#\{%d+1\} 00:00\'}'})"
								 class="qtime validate[required]" readonly="readonly" style="width: 110px;"/>
					</td>
					<th><font color="red" >*</font>请假天数：</th>
					<td>
						<input type="text" id="intervalDays" name="intervalDays" value="${kq.intervalDays}" class="qtext validate[required,custom[number]]">
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>请假事由：</th>
					<td colspan="3" style="text-align:left;padding: 5px;">
					     <textarea placeholder="请假事由（限100字）。"  class="validate[required] validate[maxSize[100]] xltxtarea" name="doctorRemarks">${kq.doctorRemarks}</textarea>
			     	</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>轮转科室：</th>
					<td colspan="3">
						<select id="processFlow"  name="processFlow" class="qselect validate[required]" style="width: 200px;">
							<option value=""></option>
							<c:forEach items="${processes}" var="p">
								<option value="${p.processFlow}"
										${kq.processFlow eq p.processFlow?'selected':''}
										schStartDate="${p.schStartDate}" schEndDate="${p.schEndDate}">${p.schDeptName}(${p.schStartDate}~${p.schEndDate})</option>
							</c:forEach>
						</select>&#12288;如果系统在请假时间段内未自动筛选出轮转科室，请手动选择！（涉及请假审核）
					</td>
				</tr>
				<tr>
					<th>附件</th>
					<td colspan="3" style="padding-left:0px;">

						<table style="width: 100%;" class="filesTable" id="filesTable">
							<tr>
								<td style="text-align: left;padding-left: 10px;">
									文件名
                                    <span><font color="#999" title=".jpg,.png,.bmp">&#12288;&#12288;仅支持图片格式 </font></span>
								</td>
								<td width="75px">操作 &#12288;<img class="opBtn" title="新增"
														src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
														style="cursor: pointer;" onclick="addFile();"/></td>
							</tr>
							<c:forEach items="${files}" var="f">
								<tr>
									<td style="text-align: left;padding-left: 10px;">
										<input hidden name="fileFlows" value="${f.fileFlow}">
										<a href="${sysCfgMap['upload_base_url']}/${f.filePath}" target="_blank">${f.fileName}</a>
									</td>
									<td width="75px">
										<img class='opBtn' title='删除' style='cursor: pointer;'
											 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<p style="text-align: center;">
			<c:if test="${not empty doctor and not empty doctor.doctorCategoryId}">
				<c:if test="${doctor.doctorCategoryId eq recDocCategoryEnumGraduate.id and empty doctor.tutorFlow }">
					<font color="red">请联系基地管理员，为你分配导师！</font>
				</c:if>
				<c:if test="${!(doctor.doctorCategoryId eq recDocCategoryEnumGraduate.id and empty doctor.tutorFlow )}">
					<c:if test="${not empty processes}">
						<c:if test="${empty cfgs || fn:length(cfgs)==0}">
							<font color="red">请联系基地管理员维护请假审核流程！</font>
						</c:if>
						<c:if test="${!(empty cfgs || fn:length(cfgs)==0)}">
							<input type="button" onclick="save();" class="search" value="提&#12288;交"/>
						</c:if>
					</c:if>
					<c:if test="${ empty processes}">
						<font color="red">无轮转科室信息！无法发起请假</font>
					</c:if>
				</c:if>
			</c:if>
			<c:if test="${!(not empty doctor and not empty doctor.doctorCategoryId) }">
				<font color="red">请先维护个人基本信息！</font>
			</c:if>
	      	<input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭"/>
	    </p>
	</div>

		<%--附件模板--%>
		<table class="filesTable" id="fileTableFormat" style="display: none" style="width: 100%">
			<tr>
				<td style="text-align: left;padding-left: 10px;">
					<input type='file' name='files' class='validate[required]' multiple='multiple' style="max-width: 200px;"
						   accept=".jpg,.png,.bmp"/>
				</td>
				<td>
					<img class='opBtn' title='删除' style='cursor: pointer;'
						 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
				</td>
			</tr>
		</table>
	</div>
	</div>
</body>
</html>