
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
	function doClose(){
		jboxClose();
	}
	
	function rotaitonInfo(type,vari){
		var selLi = $(vari).parent();
		selLi.siblings("li").removeClass("selectTag");
		selLi.addClass("selectTag");
		var url = {
				info:"<s:url value='/schTwo/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${param.viewFlag}&rotationFlow=${param.rotationFlow}",
				detail:"<s:url value='/sch/template/ruleView'/>?rotationFlow=${param.rotationFlow}",
		};
		jboxLoad("tagContent",url[type],true);
//		if("info"==type){
//			location.href = url[type];
//		}else{
//
//		}
	}
	
	$(function(){
		if ("${param.tagId}" != "") {
			$("#${param.tagId}").addClass("selectTag");
		} else {
			$("li:first").addClass("selectTag");
		}
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
		            ],
		     readonly:true
		});
	});
</script>
</head>
<body>
<div  class="content">
	<ul id="tags">
		<li>
			<a onclick="rotaitonInfo('info',this);" style="cursor: pointer;">轮转说明</a>
		</li>
		<li>
			<a onclick="rotaitonInfo('detail',this);" style="cursor: pointer;">轮转详情</a>
		</li>
	</ul>
	<div id="tagContent" style="max-height: 350px;overflow: auto;padding-bottom: 20px;">
		<div  style="margin: 10px;">
			<script type="text/plain" id="ueditor" style="height:150px;width: 95%;">${rotation.rotationNote}</script>
		</div>
	</div>
</div>
<!-- </div> -->
<div style="margin-top: 15px;">
	<p style="text-align: center;">
		<input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
	</p>
</div>
</body>
</html>