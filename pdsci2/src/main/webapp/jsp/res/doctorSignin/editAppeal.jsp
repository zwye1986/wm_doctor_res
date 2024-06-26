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
		jboxConfirm("提交后无法修改，确认提交？",function(){
			var url = "<s:url value='/res/doctorSignin/saveAppeal'/>";
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
		if(startDate&&endDate) {
			if (startDate > endDate) {
				jboxTip("结束日期不能早于开始日期！");
				return false;
			}
		}
	}
</script>
</head>
<body>
	<div class="mainright" align="center">
	<div class="content">
	<div class="title1 clearfix">
		<form id="editForm" style="position: relative;" enctype="multipart/form-data">
		<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
		<input type="hidden" name="kqTypeId" value="${dictTypeEnumAppealType.id}"/>
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
				<%--<tr>--%>
					<%--<th><font color="red" >*</font>申诉类型：</th>--%>
					<%--<td colspan="3">--%>
						<%--<select name="typeId" class="qselect validate[required]" style="width: 200px;">--%>
							<%--<option value="">请选择</option>--%>
							<%--<c:forEach var="type" items="${dictTypeEnumAppealTypeList}">--%>
								<%--<option value="${type.dictId}" <c:if test="${kq.typeId==type.dictId}">selected="selected"</c:if> >${type.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<th><font color="red" >*</font>申诉时间：</th>
					<td colspan="3">
						<input type="text" id="startDate" name="startDate" value="${kq.startDate}"  onclick="WdatePicker({onpicked:selectProcess,dateFmt:'yyyy-MM-dd',maxDate:'#F{\'%y-%M-%d\'}',minDate:'#F{\'%y-%M-\#\{%d-6\}\'}'})"
							   class="qtime validate[required]" readonly="readonly" style="width: 110px;"/>
						~ <input type="text" id="endDate" name="endDate" value="${kq.endDate}"  onclick="WdatePicker({onpicked:selectProcess,dateFmt:'yyyy-MM-dd',maxDate:'#F{\'%y-%M-%d\'}',minDate:'#F{\'%y-%M-\#\{%d-6\}\'}'})"
								 class="qtime validate[required]" readonly="readonly" style="width: 110px;"/>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>申诉事由：</th>
					<td colspan="3" style="text-align:left;padding: 5px;">
					     <textarea placeholder="申诉事由（限100字）。"  class="validate[required] validate[maxSize[100]] xltxtarea" name="doctorRemarks">${kq.doctorRemarks}</textarea>
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
			<input type="button" onclick="save();" class="search" value="提&#12288;交"/>
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