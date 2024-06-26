<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#productFlow').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		toPage();
	});

	function toPage(page) {
		page=page||$("#currentPage").val();
		page=page||"1";
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/doctor/doctorFileList'/>",$("#searchForm").serialize(),false);
	}

    function uploadFile() {
        var url = "<s:url value='/jsres/doctor/uploadDoctorFile'/>";
        jboxOpen(url, "上传文件", 600, 350);
    }
</script>
<div class="main_hd">
	<h2 class="underline">结业学员归档</h2>
</div>
<div class="div_search" style="box-sizing: border-box;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<table class="searchTable">
			<tr>
				<td class="td_left">结业年份：</td>
				<td>
					<input type="text" id="productFlow" name="productFlow"  class="input" readonly="readonly" style="width: 128px;margin-left: 0px"/>
				</td>
				<td class="td_left">
					&#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
					&#12288;<input class="btn_green" type="button" value="上&#12288;传" onclick="uploadFile();"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="doctorListZi">
</div>