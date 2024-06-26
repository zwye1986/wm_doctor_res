<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
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
</style>
<script type="text/javascript">
$(document).ready(function(){
});

$(document).ready(function(){
	$('#applyYear').datepicker({
		startView: 2,
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
var d=null;
function showInfo(tip)
{
	 d= top.dialog({
		id:"artTip",
		padding:5,
		content:tip,
		backdropOpacity: 0.1
	});
	d.show();
}
function closeInfo()
{
	if(d!=null)
		d.close().remove();
}
function postAction(url,tip,obj)
{
	showInfo(tip);
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxPost(url, null, function(resp) {
		closeInfo();
//		$(obj).removeAttr("disabled");
		$("input[type='button']").removeAttr("disabled");
	}, null, true);
}
function postAction2(url,tip,obj,okFunc)
{
	showInfo(tip);
//	$(obj).attr("disabled","disabled");
	$("input[type='button']").attr("disabled","disabled");
	jboxPost(url, null, function(resp) {
		closeInfo();
		if(okFunc!=null){
			okFunc(resp);
		}
		$("input[type='button']").removeAttr("disabled");
	}, null, false);
}
function queryProcess(obj)
{
	var userCode=$('#userCode').val();
	if(!userCode){
		jboxTip("请输入学员用户名！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/queryDelProcess'/>?userCode="+userCode;
	postAction2(url,"查询学员轮转记录，请稍后。。。",obj,function(resp){
		$("select[name=processFlow] option[value != '']").remove();
		if(resp!=""){
			var dataObj = resp;
			for(var i = 0; i<dataObj.length;i++){
				var speId = dataObj[i].processFlow;
				var speName = dataObj[i].deptName+"【"+dataObj[i].schStartDate+"-"+dataObj[i].schEndDate+"】";
				$option =$("<option></option>");
				$option.attr("value",speId);
				$option.attr("id",speId);
				$option.text(speName);
				$("select[name=processFlow]").append($option);
			}
		}
	});
}
function backProcess(obj)
{
	var processFlow=$('#processFlow').val();
	if(!processFlow){
		jboxTip("请选择轮需要恢复的轮转记录！");
		return false;
	}
	var url = "<s:url value='/jsres/temp/backProcess'/>?processFlow="+processFlow;

	jboxConfirm("确认恢复轮转记录？",function(){
		postAction2(url,"恢复轮转记录中，请稍后。。。",obj,function(resp){
			if(resp=="操作成功！")
			{
				$("#"+processFlow).remove();
			}
		});
	});
}
function updateNotStudy(obj)
{
	var url = "<s:url value='/jsres/temp/updateNotStudy'/>";
	jboxConfirm("确认更新？",function(){
		postAction2(url,"更新中，请稍后。。。",obj,function(resp){

		});
	});
}
</script>
	<div class="div_search" style="width: 70%;line-height:normal;text-align: center;">
		<div>
			<h4>江苏西医数据处理(<font color="red">现场环境缓慢，请耐心等待</font>)</h4>
			<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<td class="td_left" style="width: 30%;text-align: right">
						学员登录名:
					</td>
					<td class=""style="width: 30%;text-align: left">
						<input type="text" id="userCode" name="userCode"  class="input"  style="width: 200px;margin-left: 0px"/>
					</td>
					<td class=""style="width: 40%;text-align: left">
						<input class="btn_green" type="button" value="查询" onclick="queryProcess(this);"/>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						被退回的轮转记录:
					</td>
					<td class=""style="width: 70%;text-align: left " colspan="2">
						<select type="text" id="processFlow" name="processFlow" class="input"  style="width: 400px;margin-left: 0px">
							<option value=""></option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 50%;text-align: right">
						出科表单:
					</td>
					<td class=""style="width: 70%;text-align: left " colspan="2">
						<select type="text" id="recTypeId" name="recTypeId" class="input"  style="width: 150px;margin-left: 0px">
							<option value=""></option>
							<option value="AfterEvaluation">出科考核表</option>
							<option value="AfterSummary">出科小结表</option>
							<option value="DOPS">临床操作技能评估（DOPS）量化表</option>
							<option value="Mini_CEX">迷你临床演练评估（Mini-CEX）量化表</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						<input class="btn_green" type="button" value="恢复整个轮转科室" onclick="backProcess(this);">
					</td>
				</tr>
			</table>
		</div>
		<div>
			<h4>苏州市立医院数据处理(<font color="red">现场环境缓慢，请耐心等待</font>)</h4>
			<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<td class="td_left" style="width: 30%;text-align: right">
						未学习完成学员
					</td>
					<td class=""style="width: 30%;text-align: left">

					</td>
					<td class=""style="width: 40%;text-align: left">
						<input class="btn_green" type="button" value="查询" onclick="queryNotStudy(this);"/>
						<input class="btn_green" type="button" value="更新" onclick="updateNotStudy(this);"/>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<p>注：1、测试环境更新可以依次执行。</p>
			<p>&#12288;&#12288;2、现场环境更新后，请勿执行。</p>
		</div>
    </div>
<div>
</div>