<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		$(function(){
			<c:if test="${empty timeList}">
				addMember('');
			</c:if>
		});
	function save(){
		if($("#memberList").find("tr").length<=0)
		{
			jboxTip("至少设置一个签到地点");
			return false;
		}
		var bean={};
		bean.orgFlow="${sessionScope.currUser.orgFlow }";
		var times=[];
		var i=0;
		var i2=0;
		var f=true;
		$("#memberList").find("tr").each(function(index){
			var startTime=$(this).find("input[name=startTime]").val();
			var endTime=$(this).find("input[name=endTime]").val();
			var recordFlow=$(this).find("input[name=recordFlow]").val();
			var  signinTime={};
				signinTime.recordFlow=recordFlow;
				signinTime.startTime=startTime;
				signinTime.orgFlow=bean.orgFlow;
				signinTime.endTime=endTime;
			times.push(signinTime);
			if(!startTime)
			{
				jboxTip("请为第"+(index+1)+"个签时间段，设置签到开始时间！");
				f=false ;
				return false;
			}
			if(!endTime)
			{
				jboxTip("请为第"+(index+1)+"个签时间段，设置签到结束时间！");
				f=false ;
				return false;
			}
			if(endTime<startTime)
			{
				jboxTip("第"+(index+1)+"个签时间段的结束时间不得小于开始时间！");
				f=false ;
				return false;
			}
		});
		if(times.length>1) {
			for (var j = 0; j < times.length - 1; j++) {
				var time1=times[j];
				var time2=times[j+1];
				if(time2.endTime<time1.startTime||time1.endTime<time2.startTime){
				}else{
					jboxTip("第"+(j+1)+"个签时间段与第"+(j+2)+"个签时间段存在时间重复");
					f=false ;
					return false;
				}
			}var time1=times[0];
			var time2=times[times.length - 1];
			if(time2.endTime<time1.startTime||time1.endTime<time2.startTime){
			}else{
				jboxTip("第1个签时间段与最后一个签时间段存在时间重复");
				f=false ;
				return false;
			}
		}
		bean.times=times;
		if(f)
		{
			console.log(JSON.stringify(bean));
			var url = "<s:url value='/res/signinTime/saveSigninSetList'/>";
			jboxPostJson(url,  JSON.stringify(bean), function(resp) {
				if(resp=="保存成功！") {
					window.location.reload(true);
				}
			}, null, true);
		}
	}
		function isPositiveInteger(s){//是否为正整数
			var re = /^[1-9][0-9]+$/ ;
			return re.test(s)
		}
		function addMember(recordFlow){
		var tr =$("#moban tr:eq(0)").clone();
		$('#memberList').append($(tr));
	}
	function moveTr(obj){
		if($("#memberList").find("tr").length<=1)
		{
			jboxTip("至少设置一个签到时间段");
			return false;
		}
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
		});
	}
</script>
	<style type="text/css">
		.xllist th, .xllist td {
			 border: 1px solid #bbbbbb;
		}
		.iframeTd {
			height: 500px;
		}
	</style>
</head>
<body>

<div class="mainright">
		<fieldset style="margin-top: 20px;">
			<table class="xllist nofix" id="moban" style="display: none" style="width: 100%">
				<tr>
					<td>
						<input name="recordFlow" hidden>
						<input name="startTime" value="${meeting.startTime }" type="text"  style="width: 150px" onClick="WdatePicker({dateFmt:'HH:mm'})" class="validate[required] xltext"/>
						~
						<input name="endTime" type="text" style="width: 150px" value="${meeting.endTime }" onClick="WdatePicker({dateFmt:'HH:mm'})" class="validate[required] xltext"/>
					</td>
					<td style="width:100px;">
						<img class="opBtn" title="删除" src="/pdsci/css/skin/LightBlue/images/del1.png"
							 style="cursor: pointer;" onclick="moveTr(this);"/>
					</td>
				</tr>
			</table>
			<div style="min-height: 486px;margin-top: 20px;">
				<table class="xllist nofix" style="font-size: 20px;width:100%;border:1px solid #bbbbbb;">
					<thead>
						<tr>
							<th colspan="2">考勤时间段设置
								<span style="padding-right: 0px;">
									<img class="opBtn" title="新增"
										 src="/pdsci/css/skin/LightBlue/images/add3.png"
										 style="cursor: pointer;" onclick="addMember('');"/>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="memberList">

						<c:forEach var="signinTime" items="${timeList}">
							<tr>
								<td>
									<input name="recordFlow" hidden value="${signinTime.recordFlow}">
									<input name="startTime" value="${signinTime.startTime }" type="text"  style="width: 150px" onClick="WdatePicker({dateFmt:'HH:mm'})" class="validate[required] xltext"/>
									~
									<input name="endTime" type="text" style="width: 150px" value="${signinTime.endTime }" onClick="WdatePicker({dateFmt:'HH:mm'})" class="validate[required] xltext"/>
								</td>
								<td style="width:100px;">
									<img class="opBtn" title="删除" src="/pdsci/css/skin/LightBlue/images/del1.png"
										 style="cursor: pointer;" onclick="moveTr(this);"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div style="width: 100%">
				<div class="button">
					<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
				</div>
			</div>
		</fieldset>
</div>
</body>
</html>