
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
	function save() {
		if(false==$("#arrangeForm").validationEngine("validate")){
			return ;
		}
		var url = "<s:url value='/zseyjxres/hospital/save'/>";
		var getdata = $('#arrangeForm').serialize();
		jboxPost(url, getdata, function(data) {
			if('${GlobalConstant.SAVE_SUCCESSED}'==data){
				$(parent.window.frames["mainIframe"].document).find(".selDoc").click();
				jboxClose();
			}
		});
	}
	//根据科室查询科秘
	function showKm(obj){
		var deptFlow = $(obj).val();
		var deptName = $(obj).find('option:selected').text();
		$("#deptName").val(deptName);
		var url = "<s:url value='/zseyjxres/hospital/loadKm'/>?deptFlow=" + deptFlow;
		jboxGet(url, null, function (data) {
			if(data!=null || data!=''){
				$("#secretary").val(data.userName);
				$("#secretaryId").val(data.userFlow);
			}
		}, null, false);
	}
</script>
</head>
<body>

<form id="arrangeForm" style="padding-left: 30px;height: 100px;" >
	<input type="hidden" name="doctorFlow" value="${doctorFlow}">
	<input type="hidden" name="doctorName" value="${doctorName}">
	<input type="hidden" name="arrangeFlow" value="${arrange.arrangeFlow}">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<table width="800" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<th>轮转科室：</th>
						<td>
							<select name="deptFlow" class="validate[required] xlselect" onchange="showKm(this)">
									<option value="">请选择</option>
								<c:forEach items="${deptList}" var="dept">
									<option value="${dept.deptFlow }" ${arrange.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName }</option>
								</c:forEach>
							</select>
							<input type="hidden" name="deptName" id="deptName" value="${arrange.deptName}">
						</td>
						<th width="20%">科室秘书：</th>
						<td width="30%">
							<input type="hidden" value="${arrange.secretaryFlow}" name="secretaryFlow" id="secretaryId">
							<input type="text"   value="${arrange.secretaryName}" name="secretaryName" id="secretary" class="xltext"  readonly="readonly">
						</td>
					</tr>			
					<tr>
						<th>开始时间：</th>
						<td>
							<input class="validate[required] xltext" name="schStartDate" type="text" value="${arrange.schStartDate}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								   readonly="readonly" >
						</td>
						<th>结束时间：</th>
						<td>
							<input class="validate[required] xltext" name="schEndDate" type="text" value="${arrange.schEndDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								   readonly="readonly" >
						</td>
					</tr>
					<tr>
						<th>备注：</th>
						<td colspan="3">
							<textarea name="memo"  style="width: 600px; height:100px;margin: 3px 0;">${arrange.memo}</textarea>
						</td>

					</tr>

				</table>
				<div class="button" style="width: 800px;">
					<input class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose()">
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>