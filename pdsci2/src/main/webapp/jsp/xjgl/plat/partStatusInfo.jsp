<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script>
	function backPartStatus(partId,object){
		jboxPost("<s:url value='/xjgl/course/manage/backPartStatus'/>?userFlow=${param.userFlow}&partId="+partId,null,function (obj) {
			if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
				jboxTip("重新确认成功！");
				$(object).next("span").find("font").text("未确认").attr("color","red");
			}
		},null,false);
	}
</script>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;">&#12288;基本信息
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('BaseInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${BaseInfo eq 'Y'?'black':'red'}">${BaseInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;">&#12288;录取信息
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('RecruitInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${RecruitInfo eq 'Y'?'black':'red'}">${RecruitInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;border-right:0px;">&#12288;必填信息
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('NeedInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${NeedInfo eq 'Y'?'black':'red'}">${NeedInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;border-right:0px;">&#12288;选填信息
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('SelectInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${SelectInfo eq 'Y'?'black':'red'}">${SelectInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;">&#12288;学费信息
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('FeeInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${FeeInfo eq 'Y'?'black':'red'}">${FeeInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;border-right:0px;">&#12288;已获得学历或学位证书
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('GotCertInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${GotCertInfo eq 'Y'?'black':'red'}">${GotCertInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;border-right:0px;">&#12288;学历学位申请证书
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('CertReqInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${CertReqInfo eq 'Y'?'black':'red'}">${CertReqInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;border-right:0px;">&#12288;学位论文信息
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('PaperInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${PaperInfo eq 'Y'?'black':'red'}">${PaperInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>
	<table class="basic" style="width:100%;margin-top:10px;font-size:14px;">
		<tr>
			<td style="text-align:left;border-right:0px;">&#12288;就业信息
				<input type="button" class="search" value="重新确认" onclick="backPartStatus('WorkInfo',this)" style="float:right;margin-right:50px;margin-top: 4px">
				<span style="float:right;margin-right:50px" color="black">确认状态：<font color="${WorkInfo eq 'Y'?'black':'red'}">${WorkInfo eq 'Y'?'已确认':'未确认'}</font></span>
			</td>
		</tr>
	</table>