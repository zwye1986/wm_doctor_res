<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
	#div_table_4 table th,#div_table_4 table td{
		text-align: center;
	}
</style>
<script type="text/javascript">


</script>
<div class="search_table" style="margin-top:20px;">
	<div class="main_bd" id="div_table_3" style="margin-top: 15px">
		<h4 style="margin-bottom: 15px;">结业证书 </h4>
		<div style="font-size: 17px;padding-top: 5px;padding-left: 100px">
			<c:if test="${doctorRecruit.certificateIssuingStatus ne '已发放'}">
				<span style="color: red">
					暂无证书信息
				</span>
			</c:if>
			<c:if test="${doctorRecruit.certificateIssuingStatus eq '已发放'}">
				<span>
					<a style="cursor: pointer;"
					   href="<s:url value='/jsres/certificateManage/showCertificateNew?recruitFlow=${doctorRecruit.recruitFlow}'/>"
					   target="_blank">【证书查看】</a>
				</span>
			</c:if>
		</div>
	</div>
</div>
