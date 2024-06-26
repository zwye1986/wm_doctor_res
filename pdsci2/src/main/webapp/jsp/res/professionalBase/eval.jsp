<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
</jsp:include>
<script type="text/javascript">
	$(function(){
		$("li").eq(0).click();
	});
	function save(){
		jboxConfirm("确认保存？",function(){
			var scores = [];
			$(".td_group").each(function(){
				var orderNumber = $(this).find("input[name='orderNumber']").val();
				var score = $(this).find("input[name='score']").val();
				var deductMarks = $(this).find("[name='deductMarks']").val();
				var filePath = $(this).find("[name='filePath']").val();
				var single = {orderNumber:orderNumber,score:score,deductMarks:deductMarks,filePath:filePath};
				scores.push(single);
			});
			var eval = {formFlow:$("#formFlow").val(),recordFlow:$("#recordFlow").val()};
			var jsonData = {eval:eval,scores:scores};
			jboxPost('<s:url value="/res/ProfessionalBase/saveEval"/>?role=${role}','jsonData='+JSON.stringify(jsonData),function(resp){
						if(resp == 1){
							jboxTip("操作成功");
							setTimeout(function() {
//								window.location.reload();
							},500)
						}
					},null,false);
		})
	}

	function uploadFile(type) {
		jboxOpen("<s:url value='/res/ProfessionalBase/uploadFile'/>?operType="+type,"上传附件",450,150);
	}

	function delFile(type) {
		var type = type.replace(/\./g, '\\.');
		$("#"+type+"Del").hide();
		$("#"+type+"Span").hide();
		$("#"+type+"Up").text("上传");
		$("#"+type+"Value").val("");
	}
	function selTag(tag,formFlow){
		$('.selectTag').removeClass('selectTag');
		$(tag).addClass('selectTag');
		var url = '<s:url value="/res/ProfessionalBase/evalDetail"/>?formFlow='+formFlow+"&role=${param.role}";
		jboxLoad("content2",url,false);
	}
</script>
</head>
<body>
		<ul id="tags" style="margin-top:20px;margin-left: 20px;">
			<c:forEach items="${baseevalFormCfgList}" var="item">
				<li id="${item.formFlow}" onclick="selTag(this,'${item.formFlow}');"><a>${item.formName}</a></li>
			</c:forEach>
		</ul>
		<div style="clear:both;"></div>
		<c:if test="${empty baseevalFormCfgList}">
			该专业基地暂未配置评价表单，请联系医院管理员
		</c:if>
		<div id="content2"></div>
	<div style="margin: 15px;text-align: center">
		<c:if test="${not empty baseevalFormCfgList}">
			<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
		</c:if>
	</div>
</body>
</html>