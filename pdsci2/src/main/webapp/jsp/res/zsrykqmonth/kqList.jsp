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

	function selTag(tag,type){
		jboxStartLoading();
		$('.selectTag').removeClass('selectTag');
		$(tag).addClass('selectTag');
		var url = '<s:url value="/res/zsrykqmonth/kqList/"/>'+type+"/${role}";
		jboxLoad("content2",url,false);
	}
</script>
</head>
<body style="overflow: auto">
		<ul id="tags" style="margin-top:20px;margin-left: 20px;">
			<li id="time" onclick="selTag(this,'time');"><a>考勤时间维度</a></li>
			<li id="dept" onclick="selTag(this,'dept');"><a>轮转科室维度</a></li>
		</ul>
		<div style="clear:both;"></div>

		<div id="content2"></div>

</body>
</html>