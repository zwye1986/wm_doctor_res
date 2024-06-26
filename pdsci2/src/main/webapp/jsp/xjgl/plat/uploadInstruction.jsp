<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
	function initUE(id){
		var uecfg = {
			autoHeight: false,
			imagePath: '${sysCfgMap['upload_base_url']}/',
			imageManagerPath: '${sysCfgMap['upload_base_url']}/',
			filePath: '${sysCfgMap['upload_base_url']}/',
			videoPath: '${sysCfgMap['upload_base_url']}/',
			wordImagePath: '${sysCfgMap['upload_base_url']}/',
			snapscreenPath: '${sysCfgMap['upload_base_url']}/',
			catcherPath: '${sysCfgMap['upload_base_url']}/',
			scrawlPath: '${sysCfgMap['upload_base_url']}/',
			elementPathEnabled : false,
			autoFloatEnabled:false,
			toolbars:[
						['|', 'undo', 'redo', '|','lineheight',
							'bold', 'italic', 'underline', 'fontborder', 'fontfamily', 'fontsize', '|',
							'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
							'link', 'insertimage', 'attachment' ,  'preview', 'wordimage']
					]
			<c:if test="${param.viewFlag eq 'Y'}">,readonly:true</c:if>
		};
		UE.getEditor(id, uecfg);//实例化编辑器
	}
	$(document).ready(function(){
		initUE("instruction_ueditor");
		$("#applyTypeId").bind("change",function(){
			var url = "<s:url value='/xjgl/change/apply/uploadInstruction'/>?viewFlag=${param.viewFlag}&applyTypeId="+$(this).val();
			location.replace(url);
		});
	});
	function submitIns(){
		var url = "<s:url value='/xjgl/change/apply/submitInstruction'/>";
		jboxPost(url , $('#myForm').serialize() , function(){
			jboxClose();
		} , null , true);
	}
</script>
<html>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm" method="post">
			<table>
				<tr>
					<td>申请类型：
						<select class="xlselect" id="applyTypeId" name="applyTypeId" style="width: 120px;">
							<option value="">请选择</option>
							<c:forEach items="${userChangeApplyTypeEnumList}" var="apply">
								<option value="${apply.id}"<c:if test="${apply.id eq param.applyTypeId}">selected="selected"</c:if>>${apply.name}</option>
							</c:forEach>
						</select>
						<span <c:if test="${param.viewFlag eq 'Y'}">style="display:none;" </c:if>><input type="button" value="上&#12288;传" class="search" onclick="submitIns()"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding-top:10px;">
						<script id="instruction_ueditor" name="content" type="text/plain" style="width:630px;height:200px;position:relative;">${content}</script>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>