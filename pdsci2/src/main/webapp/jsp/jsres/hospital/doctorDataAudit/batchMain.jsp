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
	.searchTable{
		width: auto;
	}
	.searchTable td{
		width: auto;
		height: auto;
		line-height: auto;
		text-align: left;
	}
	.searchTable .td_left{
		word-wrap:break-word;
		height: auto;
		line-height: auto;
		text-align: right;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#graduationYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		})
	});
	function batchAudit(){
		if($("#graduationYear").val()=="")
		{
			jboxTip("请选择结业考核年份！");
			return false;
		}
		jboxConfirm("确认审核通过"+$("#graduationYear").val()+"年结业的学员所有数据吗？",
			function (){
				jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorDataAudit/batchAudit'/>",$("#searchForm").serialize(),true);
			},function (){}
		);
	}
</script>
<script type="text/javascript">
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
		if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
			return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
		} else {
			return this.replace(reallyDo, replaceWith);
		}
	}
</script>

<div class="main_hd">
	<h2  class="underline">培训数据审核</h2>
</div>
<div class="main_hd">
	<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="">
					结业考核年份：
					<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
					<input class="btn_green" type="button" value="批量审核" onclick="batchAudit();"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div  style="width: 95%">
	<span style="color:red;font-size: 20px;padding-left: 40px;" id="doctorListZi"></span>
</div>
</div>
</div>