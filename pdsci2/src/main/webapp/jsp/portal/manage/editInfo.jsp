
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
		var setting = {
			view: {
				dblClickExpand: false,
				showIcon: false,
				showTitle:false,
				selectedMulti:false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

		function beforeClick(treeId, treeNode) {
			var check = (treeNode.id!=0);
			if (!check){
				jboxTip('不能选择根节点');
				return false;
			}
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			id = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				id += nodes[i].id + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (id.length > 0 ) id = id.substring(0, id.length-1);
			var cityObj = $("#columnSel");
			$("#selectColumnId").val(id);
			//alert(id);
			cityObj.attr("value", v);
			if(v == "基地信息"){
				$("#tipFont").html("注：发布基地新闻、活动新闻等");
			}else if(v == "学员心得"){
				$("#tipFont").html("注：发布学员投稿、学习心得等");
			}else if(v == "理论研究"){
				$("#tipFont").html("注：发布理论性文章、政策类研究等");
			}else if(v == "基地公告"){
				$("#tipFont").html("注：发布基地招生公告、培训通知等需要走审批流程");
			}else if(v == "省级新闻"){
				$("#tipFont").html("注：推荐发布到省级新闻栏目需要走审批流程");
			}else{
				$("#tipFont").html("注：理论研究栏目仅限理论性文章、政策类研究内容");
			}
		}

		function showMenu() {
			var cityObj = $("#columnSel");
			var cityOffset = $("#columnSel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		function uploadImage(){
			if($.trim($("#imageFile").val())==""){
				jboxTip("请先选择图片！");
				return false;
			}
			$.ajaxFileUpload({
				url:"<s:url value='/portal/manage/info/uploadImg'/>",
				secureuri:false,
				fileElementId:'imageFile',
				dataType: 'json',
				success: function (data, status){
					if(data.indexOf("success")==-1){
						jboxTip(data);
					}else{
						jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
						var arr = [];
						arr = data.split(":");
						$("#titleImg").val(arr[1]);
						var url = '${imageBaseUrl}'+ arr[1];
						$("#viewNewImg").attr("href",url);
						$("#viewNewImg").show();
						$("#delNewImg").show();
						$("#imgDiv").hide();
					}
				},
				error: function (data, status, e){
					jboxTip('${GlobalConstant.UPLOAD_FAIL}');
				}
			});
		}
		function saveInfo(){
			//去title
			$("img").removeAttr("attr");
			if($("#editForm").validationEngine("validate")){
				var infoTitle = $.trim($("#infoTitle").val());
				var infoFlow = $("#infoFlow").val();
				var columnId = $("#selectColumnId").val();
				var titleImg = $("#titleImg").val();
				var infoTime = $("#infoTime").val();
				var infoKeyword = $("#infoKeyword").val();
				var isTop = $("#isTop").val();
				var author = $("#author").val();
				var infoContent = UE.getEditor('ueditor').getContent();
				var requestData ={
						"infoFlow":infoFlow,
						"infoTitle":infoTitle,
						"columnId":columnId,
						"titleImg":titleImg,
						"infoTime":infoTime,
						"infoKeyword":infoKeyword,
						"isTop":isTop,
						"infoContent":infoContent,
						"role":"${param.role}",
						"author":author
				};
				var url = "<s:url value='/portal/manage/info/save'/>";
				jboxPost(url,requestData,function(resp){
					if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
						window.parent.frames['mainIframe'].window.search();
						jboxClose();
					}
				},null,true);
			}
		}
		function delImg(infoFlow){
			var requestData ={
					"infoFlow":infoFlow
			};
			var url = "<s:url value='/portal/manage/info/deleteImg'/>";
			jboxPost(url,requestData,function(resp){
				if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
					$("#titleImg").val("");
					$("#editImg").hide();
					$("#editNewImg").show();
					$("#imgDiv").show();
				}
			},null,true);
			
		}
		function delNewImg(){
			var imgUrl = $("#titleImg").val();
			var requestData ={
					"infoFlow":imgUrl
			};
			var url = "<s:url value='/portal/manage/info/deleteImg'/>";
			jboxPost(url,requestData,function(resp){
				if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
					$("#viewNewImg").hide();
					$("#delNewImg").hide();
					$("#titleImg").val("");
					$("#imageFile").val("");
					$("#imgDiv").show();
				}
			},null,true);
			
		}
		$(document).ready(function(){
			var ue = UE.getEditor('ueditor', {
			    autoHeight: false,
				scaleEnabled:true,
			    imagePath: '${sysCfgMap['upload_base_url']}/',
			    imageManagerPath: '${sysCfgMap['upload_base_url']}/',
			    filePath: '${sysCfgMap['upload_base_url']}/',
			    videoPath: '${sysCfgMap['upload_base_url']}/',
			    wordImagePath: '${sysCfgMap['upload_base_url']}/',
			    snapscreenPath: '${sysCfgMap['upload_base_url']}/',
			    catcherPath: '${sysCfgMap['upload_base_url']}/',
			    scrawlPath: '${sysCfgMap['upload_base_url']}/'
			});//实例化编辑器

			var url = "<s:url value='/portal/manage/column/getAllDataJsonByUser'/>";
		    jboxPostJson(url,null,function(data){
		    	if(data){
					zNodes = $.parseJSON(data);
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				}
		    },null,false);
		});
	function closeJbox(){
		jboxConfirm("是否确认关闭？", function () {
			jboxClose();
		}, null);
	}
	</script>
<style type="text/css">
.line {border: none;}
#ueditor{padding-top: 5px;}
</style>
</head>
<body>	
   <div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
        <c:if test='${not empty imageBaseUrl}'>
		<form id="editForm" method="post" style="position: relative;" enctype="multipart/form-data">
   		<table class="basic" width="100%" style="margin: 0 auto;">
   			<tr>
                <td class="bs_name" colspan="4">资讯信息：</td>
            </tr>
            <tr>
                <th class="td_blue" width="100px">标&#12288;&#12288;题：</th>
                <td colspan="3">
                	<input class="validate[required,maxSize[100]] text-input xltext" id="infoTitle" type="text" style="width: 70%" value="${info.infoTitle }"/>
                </td>
            </tr>
            <tr>
                <th class="td_blue">所属栏目：</th>
                <td colspan="3" id="column">
                	<input type="hidden" id="selectColumnId" value="${info.columnId }" />
                	<input class="validate[required] text-input xltext" id="columnSel" type="text" <c:if test="${info.column != null }"> value="${info.column.columnName }" </c:if> onclick="showMenu(); return false;" readonly="readonly"  />
                	<font color="red" id="tipFont">注：理论研究栏目仅限理论性文章、政策类研究内容</font>
				</td>
            </tr>
      		<tr>
             	<th>标题图片：</th>
             	<input type="hidden" id="titleImg" value="${info.titleImg}">
             	<c:if test="${empty info.titleImg}" >
                <td  id="editNewImg"colspan="2">
                  <div id="imgDiv">
					  <input type="file"  id="imageFile" name="imageFile" accept=".png, .jpg">&nbsp;&nbsp;<input class="search" type ="button" value="上&#12288;传" onclick="uploadImage();">&nbsp;&nbsp;
				  </div>
				  <a href="" target="_blank" style="display: none;" id="viewNewImg" >[查看已上传图片]&nbsp;&nbsp;</a><a href="javascript:void(0)" id="delNewImg" onclick="delNewImg()" style="display: none">[删除图片]</a>
				</td>
				</c:if>
                <c:if test="${!empty info.titleImg}" >
                <td  id="editNewImg" style="display: none;"colspan="2">
					<div id="imgDiv">
						<input type="file"  id="imageFile" name="imageFile" accept=".png, .jpg">&nbsp;&nbsp;<input class="search" type ="button" value="上&#12288;传" onclick="uploadImage();">&nbsp;&nbsp;
					</div>
					<a href="" target="_blank" style="display: none;" id="viewNewImg" >[查看已上传图片]&nbsp;&nbsp;</a><a href="javascript:void(0)" id="delNewImg" onclick="delNewImg()" style="display: none">[删除图片]</a>
				</td>
                <td id="editImg"colspan="2"><a href="${imageBaseUrl}${info.titleImg}" target="_blank" title="点击查看大图" id="viewImg" ><img src="${imageBaseUrl}${info.titleImg}" width="150px"/></a>&nbsp;&nbsp;<a href="javascript:void(0)" id="delImg" onclick="delImg('${info.infoFlow}')" style="vertical-align: bottom;">[删除图片]</a></td>
				</c:if>
				<td>
					<span>建议上传大小为460*460的图片</span>
				</td>
             </tr>
			 <tr>
				<th>资讯时间：</th>
				 <td>
					<input id="infoTime" type="text"  class="validate[required] text-input xltext ctime" <c:if test="${!empty info.infoTime}"> value="${info.infoTime}" </c:if> <c:if test="${empty info.infoTime}"> value="${pdfn:getCurrDate()}" </c:if>  style="width: 160px;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
				</td>
				 <th>作&#12288;&#12288;者：</th>
				 <td>
					 <input id="author" type="text"  class="validate[required] text-input xltext" <c:if test="${!empty info.author}"> value="${info.author}" </c:if> style="width: 160px;"/>(多个用逗号“，”隔开)
				 </td>
			 </tr>
             <tr>
             	<th>关&nbsp;键&nbsp;字：</th>
                <td colspan="3">
                    <input class="validate[maxSize[50]] text-input xltext" id="infoKeyword" type="text" value="${info.infoKeyword}" />(多个用逗号","隔开)
				</td>
             </tr>
             <tr>
                <th class="td_blue">资讯内容：</th>
                <td colspan="3">
                	<script id="ueditor" type="text/plain" style="width:745px;height:160px;">${info.infoContent}</script>
                </td>
            </tr>
			</table>
			<p align="center" style="width:100%">
				<input class="search" type="button" value="发&#12288;布"  onclick="saveInfo();" />&emsp;
				<input class="search" type="button" value="关&#12288;闭"  onclick="closeJbox();" />
				<input id="infoFlow" type="hidden" value="${info.infoFlow }" />
				<input id="isTop" type="hidden" value="${GlobalConstant.RECORD_STATUS_N}" />
			</p>
		</form>
		</c:if>
		<c:if test="${empty imageBaseUrl}">请联系系统管理员先在系统配置中配置上传图片的路径</c:if>
         </div>
        
     </div> 	
   </div>
   <div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index: 9999;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>	
</body>
</html>