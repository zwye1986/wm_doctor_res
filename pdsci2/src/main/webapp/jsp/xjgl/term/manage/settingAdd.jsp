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
		function save(){
			if(!$("#myForm").validationEngine("validate")){
				return ;
			}
			var url="<s:url value='/xjgl/term/manage/saveSetting'/>";
			jboxPost(url,$("#myForm").serialize(),function(resp){
				if('${GlobalConstant.SAVE_SUCCESSED}'==resp){

					jboxCloseMessager();
				}
			},null,true);
		}
		function checkTime(obj){
			var dates = $(':text',$(obj).closest("td"));
			if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
				jboxTip("开始日期不能大于结束日期！");
				obj.value = "";
			}
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="myForm" method="post">
			<input type="hidden" name="sessionNumber" value="${param.sessionNumber}">
			<input type="hidden" name="recordFlow" value="${param.recordFlow}">
			<table class="basic" style="width: 100%;">
				<tr>
					<th>排课限制时间：</th>
					<td style="line-height:25px;">
						<input type="text" name="beginTime" value="${limit.beginTime}" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkTime(this)" readonly="readonly" style="width:133px;"/>
						--
						<input type="text" name="endTime" value="${limit.endTime}" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkTime(this)" readonly="readonly" style="width:133px;"/><br>
						<label style="color:red;">注意：可设为具体某天或连续时间</label>
					</td>
				</tr>
				<tr>
					<th>备注：</th>
					<td><input type="text" name="memo" value="${limit.memo}" style="width:200px;"></td>
				</tr>
			</table>
		</form>
		<div style="text-align:center;margin-top:20px;">
			<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxCloseMessager();"/>
		</div>
	</div>
</div>
</body>
</html>