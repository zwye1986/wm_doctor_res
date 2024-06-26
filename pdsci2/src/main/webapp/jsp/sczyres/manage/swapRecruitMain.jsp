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
	$(".catSpeId").each(function(){
		getSpe($(this));
	});
	setTimeout( function(){
		$("#speId option[value=${defaultSpe.speId}]").attr("selected","selected");
		$("#secondSpeId option[value=${defaultSpe.secondSpeId}]").attr("selected","selected");
	}, 305 );
});

function getCatSpe(obj){
	var orgFlow = $(obj).val();
	if(orgFlow){
		var url = "<s:url value='/sczyres/doctor/findcatspe'/>?orgFlow="+orgFlow;
		jboxGet(url , null , function(resp){
			$(obj).closest("tr").next().find("select").empty();//catspe
			$(obj).closest("tr").next().find("select").append("<option value=''>请选择</option>");
			$.each(resp , function(i , n){
				$(obj).closest("tr").next().find("select").append("<option value='"+n.speId+"'>"+n.speName+"</option>");
			});
		} , null , false);
	}
	$(obj).closest("tr").next().next().find("select").empty();//spe
	$(obj).closest("tr").next().next().find("select").append("<option value=''>请选择</option>");
}

function getSpe(obj){
	var catSpeId = $(obj).val();
	$(obj).closest("tr").next().hide();
	$(obj).closest("tr").next().next().hide();
	if(catSpeId=='${speCatEnumChineseMedicine.id}' || catSpeId=='${speCatEnumTCMGeneral.id}'){
		$(obj).closest("tr").next().show();
		var url = "<s:url value='/sczyres/doctor/findspe'/>?catSpeId="+catSpeId;
		jboxGet(url , null , function(resp){
			$(obj).closest("tr").next().find("select").empty();
			$(obj).closest("tr").next().find("select").append("<option value=''>请选择</option>");
			$.each(resp , function(i , n){
				$(obj).closest("tr").next().find("select").append("<option value='"+n.dictId+"'>"+n.dictName+"</option>");
			});
		} , null , false);
	}
	if(catSpeId=='${speCatEnumChineseMedicine.id}'){
		$(obj).closest("tr").next().next().show();
		$(obj).closest("tr").next().next().find("select").empty();
		$(obj).closest("tr").next().next().find("select").append("<option value=''>请选择</option>");
		<c:forEach items="${dictTypeEnumZySpeList}" var="dict">
		$(obj).closest("tr").next().next().find("select").append("<option value=${dict.dictId}>${dict.dictName}</option>");
		</c:forEach>
	}
	if(catSpeId=='${speCatEnumTCMAssiGeneral.id}'){
		$(obj).closest("tr").next().hide();
		$(obj).closest("tr").next().next().hide();
	}
}

function swapRecruit(){
	if(!$("#swapForm").validationEngine("validate")){
		return false;
	}
	jboxConfirm("确认调剂？" , function(){
		if($("#catSpeId").val()){
			$("#catSpeName").val($("#catSpeId :selected").text());
		}
		if($("#speId").val()){
			$("#speName").val($("#speId :selected").text());
		}
		if($("#secondSpeId").val()){
			$("#secondSpeName").val($("#secondSpeId :selected").text());
		}
		var data = $("#swapForm").serialize();
		jboxPost("<s:url value='/sczyres/manage/swapRecruit'/>" , data+"&oldRecruitFlow=${recruit.recruitFlow}" , function(resp){
			if(resp == "1"){
				jboxTip("操作成功");
				window.parent.swap();
				jboxClose();
			}else{
				jboxTip("操作失败");
			}
		} , null , false);
	});
}
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
	<div class="infoAudit">
	<span style="width: 150px;display:block;float:left;margin-left: 25px;">学员姓名：${user.userName}</span>
	<div class="div_table" style="margin-top: 5px;">
	<span style="color:red;width: 150px;display:block;float:left;">第1志愿&#12288;</span>
	填报基地：${recruit.orgName}&#12288;
		填报专业：${recruit.catSpeName}&#12288;
		<c:if test="${recruit.catSpeId eq speCatEnumChineseMedicine.id || recruit.catSpeId eq speCatEnumTCMGeneral.id}">
		对应专业：${recruit.speName}&#12288;
			<c:if test="${recruit.catSpeId eq speCatEnumChineseMedicine.id}">
				二级专业：${recruit.secondSpeName}
			</c:if>
		</c:if>
	</div>
	<c:forEach items='${moreSpeList}' var='moreSpe'>
		<c:if test="${(not empty moreSpe.orgFlow) && (not empty moreSpe.catSpeId)}">
			<div style="padding-left: 25px;padding-bottom: 10px;">
				<span style="color:red;width: 150px;display:block;float:left;">第${moreSpe.orderNum}志愿&#12288;</span>
				基地名称：${moreSpe.orgName}&#12288;专业名称：${moreSpe.catSpeName}&#12288;对应专业：${moreSpe.speName}&#12288;
				<c:if test="${moreSpe.catSpeId eq speCatEnumChineseMedicine.id}">
					二级专业：${moreSpe.secondSpeName}
				</c:if>
			</div>
		</c:if>
	</c:forEach>
	<div class="search_table">
	<form id="swapForm">
	    <input type="hidden" name="doctorFlow" value="${param.doctorFlow}"/>
	    <table class="base_info">
            <tr>
                <th><font color="red">*</font>培训基地：</th>
                <td>
					<select class="select validate[required]" id="orgFlow" name="orgFlow"  onchange="getCatSpe(this);">
						<option value="">请选择</option>
						<c:forEach items="${hospitals}" var="hosptial">
							<option value='${hosptial.orgFlow}' <c:if test='${defaultSpe.orgFlow eq hosptial.orgFlow}'>selected="selected"</c:if>>${hosptial.orgName}</option>
						</c:forEach>
					</select>
                </td>
            </tr>
            <tr>
                <th><font color="red">*</font>培训专业：</th>
                <td>
					<select class="select validate[required] catSpeId" id="catSpeId" name="catSpeId" style="width: 150px;" onchange="getSpe(this);">
						<option value="">请选择</option>
						<c:forEach items="${catspes}" var="catspe">
							<option value="${catspe.speId}" <c:if test='${defaultSpe.catSpeId eq catspe.speId}'>selected="selected"</c:if>>${catspe.speName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id='catSpeName' name="catSpeName" value=''/>
                </td>
            </tr>
			<tr style="display:none" class="speTr">
				<th><font color="red">*</font>对应专业：</th>
				<td>
					<select class="select validate[required]" style="width: 150px;" name="speId" id="speId">
					</select>
					<input type="hidden" id="speName" name="speName" value=''/>
				</td>
			</tr>
			<tr style="display:none" class="secondSpeTr">
				<th><font color="red">*</font>二级专业：</th>
				<td>
					<select class="select validate[required]" id="secondSpeId"  style="width: 150px;" name="secondSpeId">
					</select>
					<input type="hidden" id="secondSpeName" name="secondSpeName" value=''/>
				</td>
			</tr>
        </table>
		<table border="0" width="1025" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="4" style="padding-top:10px;padding-left:0;">
					<script id="admitNotice" name="admitNotice" style="width:100%;height:200px;position:relative;">
					</script>
					</td>
				</tr>
			</table>
		</form>
		</div>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
		    <a class="btn_green" id="operBtn" href="javascript:swapRecruit();">确定</a>
			<a class="btn_grey" id="closeBtn" href="javascript:jboxClose();">关闭</a>
		</div>
	</div>
</body>
</html>