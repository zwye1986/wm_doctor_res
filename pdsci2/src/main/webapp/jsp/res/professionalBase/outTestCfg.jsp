<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
</head>
<body>
<script type="text/javascript">
	function save(){
		if(false==$("#saveCfgForm").validationEngine("validate")){
			return ;
		}
		var url = "<s:url value='/res/powerCfg/saveOne'/>";
		jboxPost(url, $('#saveCfgForm').serialize(), function(resp){
			if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
				jboxTip("操作成功！");
				window.location.href="<s:url value='/res/ProfessionalBase/outTestCfg?refresh=Y'/>";
			}else{
				jboxTip("操作失败！");
			}
		}, null, false);
	}
	String.prototype.htmlFormart = function(){
		var html = this;
		for(var index in arguments){
			var reg = new RegExp('\\{'+index+'\\}','g');
			html = html.replace(reg,arguments[index]);
		}
		return html;
	};

$(document).ready(function(){
	if ("${param.tagId}" != "") {
		$("#${param.tagId}").click();
	} else {
		$("li a:first").click();
	}
});

function selectTag(selfObj,url) {
	var selLi = $(selfObj).parent();
	selLi.siblings("li").removeClass("selectTag");
	selLi.addClass("selectTag");
	jboxLoad("tagContent",url,true);
}
</script>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<ul id="tags">
				<li>
					<a id="systemCfg" onclick="selectTag(this,'<s:url value='/res/ProfessionalBase/cfgDetail'/>')" href="javascript:void(0)">出科考试</a>
				</li>
			</ul>
			<div id="tagContent">
			</div>
		</div>
	</div>
</div>
</body>
</html>