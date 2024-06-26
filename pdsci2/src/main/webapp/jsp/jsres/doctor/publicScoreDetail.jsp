<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	$('#graduationYear').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	$(".base_info").css("text-align","center");
});
function doclose()
{
	top.jboxClose();
}
</script>
<div class="search_table" style="margin-top:20px;text-align: center;">
	<div class="main_bd" id="div_table_0" >
		<table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;"class="base_info" >
			<colgroup>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<tr style=" width:100%;text-align: center;">
				<th style="text-align: center;">卫生法律和法规</th>
				<th style="text-align: center;">循证医学</th>
				<th style="text-align: center;">临床思维与人际沟通</th>
				<th style="text-align: center;">重点传染病防治知识</th>
			</tr>
			<tr style=" width:100%;text-align: center;">
				<td style=" text-align: center;">
					${extScore.lawScore}
				</td>
				<td style=" text-align: center;">
					${extScore.medicineScore}
				</td>
				<td style=" text-align: center;">
					${extScore.clinicalScore}
				</td>
				<td style=" text-align: center;">
					${extScore.ckScore}
				</td>
			</tr>

			</tbody>
		</table>
	</div>
	<div class="main_bd" id="div_table_1" >
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关&#12288;闭"/>&nbsp;
		</div>
	</div>
</div>
