
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="compatible" value="true"/>
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
	<jsp:param name="jquery_ztree" value="true"/>
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
		function saveInfo(){
			if($("#editForm").validationEngine("validate")){
				var infoTitle = $.trim($("#infoTitle").val());
				var infoContent = UE.getEditor('ueditor').getContent();
				var diseaseId = $("#diseaseId").val();
				var diseaseName = $("#diseaseId option:selected").text();
				var requestData ={
						"title":infoTitle,
						"diseaseId":diseaseId,
						"diseaseName":diseaseName,
						"content":infoContent,
				};
				var url = "<s:url value='/inx/jsszPortal/saveCommunicationMain'/>";
				jboxPost(url,requestData,function(resp){
					if(resp==1){
						jboxTip("操作成功")
						window.parent.search();
						jboxClose();
					}
				},null,false);
			}
		}
		$(document).ready(function(){
			var ue = UE.getEditor('ueditor', {
			    autoHeight: false,
			    imagePath: '${sysCfgMap['upload_base_url']}/',
			    imageManagerPath: '${sysCfgMap['upload_base_url']}/',
			    filePath: '${sysCfgMap['upload_base_url']}/',
			    videoPath: '${sysCfgMap['upload_base_url']}/',
			    wordImagePath: '${sysCfgMap['upload_base_url']}/',
			    snapscreenPath: '${sysCfgMap['upload_base_url']}/',
			    catcherPath: '${sysCfgMap['upload_base_url']}/',
			    scrawlPath: '${sysCfgMap['upload_base_url']}/'
			});//实例化编辑器
		});
	</script>
<style type="text/css">
.line {border: none;}
#ueditor{padding-top: 35px;}
</style>
</head>
<body>	
   <div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
		<form id="editForm" method="post" style="position: relative;" enctype="multipart/form-data">
   		<table class="basic" width="100%" style="margin: 0 auto;">
   			<tr>
                <td class="bs_name" colspan="4">医患交流信息：</td>
            </tr>
            <tr>
                <th class="td_blue" width="100px">标&#12288;&#12288;题：</th>
                <td colspan="3">
                	<input class="validate[required,maxSize[100]] text-input xltext" id="infoTitle" type="text" style="width: 70%" value="${info.infoTitle }"/>
                </td>
            </tr>
			<tr>
				<th class="td_blue" width="100px">疾病类型：</th>
				<td colspan="3">
					<select name="diseaseId" id="diseaseId" class="xlselect validate[required]">
						<c:forEach items="${dictTypeEnumDigestiveDiseasesTypeList}" var="dict">
							<option value="${dict.dictId}" ${param.diseaseId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
             <tr>
                <th class="td_blue">信息内容：</th>
                <td colspan="3">
                	<script id="ueditor" type="text/plain" style="width:750px;height:300px;">${info.infoContent}</script>
                </td>
            </tr>
			</table>
			<p align="center" style="width:100%">
				<input class="search" type="button" value="保&#12288;存"  onclick="saveInfo();" />
				<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();" />
			</p>
		</form>
        </div>
     </div> 	
   </div>
   <div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index: 9999;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>	
</body>
</html>