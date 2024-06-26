
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="false"/>
		<jsp:param name="jquery_placeholder" value="false"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<style type="text/css">

		.info2 {
			background: #449bcd none repeat scroll 0 0;
			border-bottom: 1px solid #dbdbdb;
			color: #fff;
			font-size: 18px;
			font-weight: bold;
			line-height: 35px;
			text-align: center;
		}
		.infoP {

			border-bottom: 1px solid #dbdbdb;
			color: #fff;
			font-size: 14px;
			font-weight: bold;
			line-height: 35px;
			text-align: center;
		}
	</style>
	<script type="text/javascript">
		var closeSecond = 5;
		
		$(function(){
			closeByTime(5);
		});
		
		function closeByTime(time){
			$("#time").text(time);
			
			if(!time){
				closeWindow();
			}else{
				setTimeout(function(){
					closeByTime(--time);
				},1000);
			}
		}
		
		function closeWindow(){
			window.close();
		}
	</script>
</head>
<body style="text-align: center;">
	<p align="center"class="infoP">
		<span style="width: auto" class="info2">
			&#12288;&#12288;${errorMeg}&#12288;&#12288;
		</span>
	</p>
	<div align="center">
		页面将在<span id="time"></span>秒后<a onclick="closeWindow();" style="color: blue;cursor: pointer;">关闭</a>！
	</div>
</body>
</html>
