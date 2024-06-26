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
				<c:if test="${viewFlag ne 'view'}">
						['|', 'undo', 'redo', '|','lineheight',
							'bold', 'italic', 'underline', 'fontborder', 'fontfamily', 'fontsize', '|',
							'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
							'link', 'insertimage', 'attachment' ,  'preview', 'wordimage']
				</c:if>
					]
			<c:if test="${viewFlag eq 'view'}">,readonly:true</c:if>
		};
		UE.getEditor(id, uecfg);//实例化编辑器
	}
	$(document).ready(function(){
		initUE("outline_ueditor");
		initUE("teaching_ueditor");
	});
	function uploadOption(courseFlow){
		var url = "<s:url value='/xjgl/course/manage/uploadOption'/>?courseFlow="+courseFlow;
		jboxPost(url , $('#myForm').serialize() , function(){
			jboxClose();
		} , null , true);
	}
</script>
<html>
<body>
	<div style="width:100%;height:100%;overflow-y:auto;">
		<div style="clear:both;">
			<form id="myForm" method="post">
				<table <c:if test="${param.reqFlag eq 'teaching'}">style="display:none;" </c:if>>
					<tr>
						<td style="text-align: left;">
							课程大纲：<br/><br/>
							<script id="outline_ueditor" name="outline_ueditor" type="text/plain" style="width:97%;height:200px;position:relative;">${eduCourse.outlineContent}</script>
						</td>
					</tr>
				</table>
				<table style="margin-top:20px;<c:if test="${param.reqFlag eq 'outline'}">display:none;</c:if>">
					<tr>
						<td style="text-align: left;">
							参考教材：<br/><br/>
							<script id="teaching_ueditor" name="teaching_ueditor" type="text/plain" style="width:97%;height:200px;position:relative;">${eduCourse.teachingContent}</script>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align: center;margin:20px;<c:if test="${viewFlag eq 'view'}">display:none;</c:if>"><input type="button" class="search" value="上&#12288;传" onclick="uploadOption('${param.courseFlow}')"></div>
	</div>
</body>
</html>