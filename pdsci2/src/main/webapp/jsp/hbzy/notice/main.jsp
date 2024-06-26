<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">
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
			scrawlPath: '${sysCfgMap['upload_base_url']}/',
			elementPathEnabled : false,
			autoFloatEnabled:false,
			initialFrameWidth:null,
			toolbars:[
				['|', 'undo', 'redo', '|','lineheight',
					'bold', 'italic', 'underline', 'fontborder', 'fontfamily', 'fontsize', '|',
					'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
					'link', 'insertimage', 'attachment' ,  'preview', 'wordimage']
			]
		});//实例化编辑器
		init();
	});
	function saveNotice(){
		if($("#title").val().length==0){
			jboxTip("标题不能为空!");
			return;
		}
		if($("#title").val().length>50){
			jboxTip("标题不能大于50个字符或汉字!");
			return;
		}
		var title = $.trim($("#title").val());
		var infoFlow = $("#infoFlow").val();
		var content = UE.getEditor('ueditor').getContent();
		var columnId = $("#columnId").val();
		var roleFlow = $("#roleFlow").val();
		var requestData ={
			"infoFlow":infoFlow,
			"infoTitle":title,
			"infoContent":content,
			"columnId":columnId,
			"roleFlow":roleFlow
		};
		jboxStartLoading();
		var url = "<s:url value='/hbzy/notice/saveNotice/${roleFlag}'/>";
		jboxPost(url,requestData,function(resp){
			if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
				jboxEndLoading();
				toPage(1);
				$("#infoFlow").val("");
				$("#title").val("");
				UE.getEditor('ueditor').setContent("");
			}
		},null,true);
	}
	function edit(infoFlow){
		var url = "<s:url value='/hbzy/notice/findNoticeByFlow'/>?infoFlow="+infoFlow;
		jboxGet(url , null , function(resp){
			var info=resp.info;
			if(info){
				if($("#editForm").is(":hidden")){
					editFormHidden();
				}
				$("#infoFlow").val(info.infoFlow);
				$("#title").val(info.infoTitle);
				UE.getEditor('ueditor').setContent(info.infoContent);
				$("#bodyDiv").animate({scrollTop:"0px"},500);
			}
		} , null , false);
	}
	function delNotice(infoFlow){
		jboxConfirm("确认删除？" , function(){
			var url = "<s:url value='/hbzy/notice/delNotice'/>?infoFlow="+infoFlow;
			jboxGet(url , null , function(resp){
				toPage(1);
				$("#infoFlow").val("");
				$("#title").val("");
				UE.getEditor('ueditor').setContent("");
			} , null , true);
		});
	}
	function editFormHidden(){
		$("#editForm,#operImg img").toggle();
	}
	function editFormHidden2(){
		$("#editForm2,#operImg2 img").toggle();
	}
</script>
<script type="text/javascript">
$(document).ready(function(){
	toPage(1);
});
function toPage(page) {
	$("#currentPage").val(page);
	jboxStartLoading();
	console.log($("#searchForm").serialize());
	jboxPostLoad("doctorListZi","<s:url value='/hbzy/notice/notice'/>",$("#searchForm").serialize(),false);
}

</script>
<div class="main_hd">
    <h2 class="underline">
		<c:choose>
			<c:when test="${param.typeId eq 'LM05'}">系统公告</c:when>
			<c:when test="${param.typeId eq 'LM06'}">政策法规</c:when>
			<c:when test="${param.typeId eq 'LM07'}">专题报道</c:when>
			<c:when test="${param.typeId eq 'LM08'}">学术专区</c:when>
			<c:when test="${param.typeId eq 'LM09'}">经验分享</c:when>
		</c:choose>
	</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage"/>
				<input type="hidden" name="columnId" value="${param.typeId}"/>
				<input type="hidden" name="roleFlow" value="${param.sysId}"/>
			</form>
				<input type="hidden" id="infoFlow" name="infoFlow" value=""/>
				<input type="hidden" id="columnId" name="columnId" value="${param.typeId}"/>
				<input type="hidden" id="roleFlow" name="roleFlow" value="${param.sysId}"/>
				<div style="height: auto;margin-top: 5px;">
					<table style="width: 100%;">
						<tr>
							<td width="10%" style="text-align: right;"><span style="color: red;">*</span>标题：</td>
							<td width="80%"><input id="title" name="title" class="input" style="width:98%;"/></td>
							<td width="10%"><input type="button" id="saveBtn" value="发布" onclick="saveNotice();" class="btn_brown"/></td>
						</tr>
					</table>
				</div>
				<script id="ueditor" name="content" type="text/plain" style="height:250px;"></script>
    </div>
   <div id="doctorListZi" style="padding-bottom: 20px;">
    </div>
</div>