<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">

$(document).ready(function(){
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
		    maximumWords:500,
		    elementPathEnabled:false,
		    toolbars:[
		                [/* 'fullscreen' */, /*'source',*/ '|', 'undo', 'redo', '|','lineheight',
		                    'bold', 'italic', 'underline', 'fontborder', /* 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', */ 'forecolor',/* 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|', 'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
		                    'customstyle', 'paragraph', */ 'fontfamily', 'fontsize', '|',
		                    /* 'directionalityltr', 'directionalityrtl', 'indent', '|',*/ 
		                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
		                    /* 'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|', */
		                    'insertimage', /* 'emotion','scrawl', 'insertvideo', 'music', 'attachment'*/, /*'map', 'gmap', 'insertframe','insertcode', 'webapp', 'pagebreak',*/ /*'template', 'background'*/, '|',
		                    /* 'horizontal', 'date', 'time', 'spechars', 'snapscreen',  'wordimage', '|',*/
		                    /*'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|'*/,
		                    /*'print'*/,  'preview', /*'searchreplace', 'help','drafts','wordimage'*/]
		            ]
		};
	var ue1 = UE.getEditor('admitNotice', uecfg);//实例化编辑器

	getSpe();
	setTimeout( function(){
		$("#speId option[value=${recruit.speId}]").attr("selected","selected");
		$("#secondSpeId option[value=${recruit.secondSpeId}]").attr("selected","selected");
	}, 305 );
});

function recruit(){
	var flag='N';
	if($("input[name='planFlag']").attr("checked")){
		flag='Y';
	}
	jboxConfirm("确认"+(flag=='Y'?'计入招生指标？':('${doctor.doctorTypeId}'=='${sczyRecDocTypeEnumGraduate.id}'?'录取？':'不计入招生指标？')) , function(){
		if($("#catSpeId").val()){
			$("#catSpeName").val($("#catSpeId :selected").text());
		}
		if($("#speId").val()){
			$("#speName").val($("#speId :selected").text());
		}
		if($("#secondSpeId").val()){
			$("#secondSpeName").val($("#secondSpeId :selected").text());
		}
		var data = $("#noticeForm").serialize();
		jboxPost("<s:url value='/sczyres/hosptial/recruit'/>" , data , function(resp){
			if(resp == "1"){
				jboxTip("操作成功");
				window.parent.searchDoctor();
				jboxClose();
			}else{
				jboxTip("该专业计划指标招生数已满，不可再纳入招生指标");
			}
		} , null , false);
	});
}

function getSpe(){
	var catSpeId = $("#catSpeId").val();
	$(".secondSpeTr,.speTr").hide();
	if(catSpeId=='${speCatEnumChineseMedicine.id}' || catSpeId=='${speCatEnumTCMGeneral.id}'){
		$(".speTr").show();
		var url = "<s:url value='/sczyres/doctor/findspe'/>?catSpeId="+catSpeId;
		jboxGet(url , null , function(resp){
			$("#speId").empty();
			$("#speId").append("<option value=''>请选择</option>");
			$.each(resp , function(i , n){
				$("#speId").append("<option value='"+n.dictId+"'>"+n.dictName+"</option>");
			});
		} , null , false);
	}
	if(catSpeId=='${speCatEnumChineseMedicine.id}'){
		$(".secondSpeTr").show();
		$("#secondSpeId").empty();
		$("#secondSpeId").append("<option value=''>请选择</option>");
		<c:forEach items="${dictTypeEnumZySpeList}" var="dict">
		$("#secondSpeId").append("<option value=${dict.dictId}>${dict.dictName}</option>");
		</c:forEach>
	}
	if(catSpeId=='${speCatEnumTCMAssiGeneral.id}'){
		$(".secondSpeTr,.speTr").hide();
	}
}
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
	<div class="infoAudit">
	<form id="noticeForm">
	<div class="div_table">
	<span>学员姓名：${user.userName}</span>
	<%--<span style='margin-left: 20px;'>录取专业：${recruit.catSpeName}&#12288;${recruit.speName}&#12288;${recruit.secondSpeName}</span>--%>
	<div style='margin-top: 15px;'>
		培训专业：<select class="select validate[required] catSpeId" id="catSpeId" name="catSpeId" style="width: 150px;" onchange="getSpe(this);">
			<option value="">请选择</option>
			<c:forEach items="${catspes}" var="catspe">
				<option value="${catspe.speId}" <c:if test='${recruit.catSpeId eq catspe.speId}'>selected="selected"</c:if>>${catspe.speName}</option>
			</c:forEach>
		</select>&#12288;
        <input type="hidden" id='catSpeName' name="catSpeName" value='${recruit.catSpeName}'/>
		<span style="display:none" class="speTr">
		对应专业：<select class="select validate[required]" style="width: 150px;" name="speId" id="speId">
		</select>&#12288;
		<input type="hidden" id="speName" name="speName" value='${recruit.speName}'/>
		</span>
		<span style="display:none" class="secondSpeTr">
		二级专业：<select class="select validate[required]" id="secondSpeId"  style="width: 150px;" name="secondSpeId">
		</select>&#12288;
		<input type="hidden" id="secondSpeName" name="secondSpeName" value='${recruit.secondSpeName}'/>
		</span>
		<c:if test="${doctor.doctorTypeId ne sczyRecDocTypeEnumGraduate.id}">
		<span>
			<label><input value="Y" type="checkbox" name="planFlag" ${recruit.returnBackFlag eq 'Y'?'checked':''}/>是否计入招生指标</label>
		</span>
		</c:if>
	</div>
	</div>
	<div class="search_table">
	   <input type="hidden" name="recruitFlow" value='${param.recruitFlow}'/>
		<table border="0" width="945" cellspacing="0" cellpadding="0">
				<!-- 
				<caption>${title}</caption>
				 -->
				<tr>
					<td colspan="4" style="padding-top:10px;padding-left:0;">
					<script id="admitNotice" name="admitNotice" style="width:100%;height:200px;position:relative;">
					<%--${recruit.admitNotice}--%>
					</script>
					</td>
				</tr>
			</table>
		</form>
		</div>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
		    <a class="btn_green" id="operBtn" href="javascript:recruit();">确定</a>
			<a class="btn_grey" id="closeBtn" href="javascript:jboxClose();">关闭</a>
		</div>
	</div>
</body>
</html>