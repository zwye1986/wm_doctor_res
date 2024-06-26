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

function stAppJobProcess(obj)
{
    var url = "<s:url value='/sys/job/stAppJob'/>";
    jboxConfirm("确认执行省厅app统计定时任务？",function(){
        postAction2(url,"定时任务执行中，请稍后。。。",obj,function(resp){

        });
    });
}

function stLunzhangJobProcess(obj)
{
    var url = "<s:url value='/sys/job/stLunzhangJob'/>";
    jboxConfirm("确认执行省厅轮转数据统计定时任务？",function(){
        postAction2(url,"定时任务执行中，请稍后。。。",obj,function(resp){

        });
    });
}
function stChukeJobProcess(obj)
{
    var url = "<s:url value='/sys/job/stChukeJob'/>";
    jboxConfirm("确认执行省厅出科数据统计定时任务？",function(){
        postAction2(url,"定时任务执行中，请稍后。。。",obj,function(resp){

        });
    });
}


function stJxhdJobProcess(obj)
{
    var url = "<s:url value='/sys/job/stJxhdJob'/>";
    jboxConfirm("确认执行省厅教学活动数据统计定时任务？",function(){
        postAction2(url,"定时任务执行中，请稍后。。。",obj,function(resp){

        });
    });
}

function yyAppJobProcess(obj)
{
    var url = "<s:url value='/sys/job/yyAppJob'/>";
    jboxConfirm("确认执行医院App数据统计定时任务？",function(){
        postAction2(url,"定时任务执行中，请稍后。。。",obj,function(resp){

        });
    });
}

function yyLunzhuanJobProcess(obj)
{
    var url = "<s:url value='/sys/job/yyLunzhuanJob'/>";
    jboxConfirm("确认执行医院轮转数据统计定时任务？",function(){
        postAction2(url,"定时任务执行中，请稍后。。。",obj,function(resp){

        });
    });
}

</script>

	<div class="div_search" style="width: 70%;line-height:normal;text-align: center;">
		<div>
			<h4>江苏西医报表数据处理(<font color="red">现场环境缓慢，请耐心等待</font>)</h4>
			<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<td class=""style="width: 40%;text-align: center">
						省厅app数据统计&#12288;<input class="btn_green" type="button" value="执行定时任务" onclick="stAppJobProcess(this);"/>
					</td>
				</tr>

				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						省厅轮转数据统计&#12288;<input class="btn_green" type="button" value="执行定时任务" onclick="stLunzhangJobProcess(this);">
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						省厅出科数据统计&#12288;<input class="btn_green" type="button" value="执行定时任务" onclick="stChukeJobProcess(this);">
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						省厅教学活动数据统计&#12288;<input class="btn_green" type="button" value="执行定时任务" onclick="stJxhdJobProcess(this);">
					</td>
				</tr>

				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						医院app数据统计&#12288;<input class="btn_green" type="button" value="执行定时任务" onclick="yyAppJobProcess(this);">
					</td>
				</tr>
				<tr>
					<td class="td_left" style="width: 60%;text-align: center" colspan="2">
						医院轮转数据统计&#12288;<input class="btn_green" type="button" value="执行定时任务" onclick="yyLunzhuanJobProcess(this);">
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