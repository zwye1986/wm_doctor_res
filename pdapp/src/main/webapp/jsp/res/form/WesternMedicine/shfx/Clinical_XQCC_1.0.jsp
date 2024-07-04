<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="false"/>
		<jsp:param name="jquery_placeholder" value="false"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<style type="text/css">

	</style>
	<script type="text/javascript" src="<s:url value='/js/jquery.PrintArea.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		function single(box){
			var curr=box.checked;
			if(curr){
				var name=box.name;
				$(":checkbox[name='"+name+"']").attr("checked",false);
			}
			box.checked = curr;
		}
		//导出评分表
		function printForm(recFlow){
			jboxTip("打印中,请稍等...");
			var url = '<s:url value="/res/fengxianPj/printForm?recFlow="/>'+recFlow;
			window.location.href = url;
		}
		function saveForm(){
			var zong=0;
			$(".num :checkbox:checked").each(function(i,n){
				var num = parseInt($(n).val());
				zong+=num;
			});
			$("#ZongHe").val(zong);
			if($("#dopsForm").validationEngine("validate")){
				jboxConfirm("保存后将无法修改,确定吗?",function(){
					jboxPost("<s:url value='/res/fengxianPj/save360EvlForm'/>",$('#dopsForm').serialize(),function(resp){
						if(resp="${GlobalConstant.SAVE_SUCCESSED}"){
							window.parent.frames['jbox-message-iframe'].location.reload(true);
							jboxClose();
						}
					},null,true);
				},null);
			}
		}

		$(function(){
			$(".scoreCount").keyup(function(){
				var sum = 0;
				$(".scoreCount").each(function(){
					var val = this.value;
					if(val && !isNaN(val)){
						sum+=parseFloat(val);
					}
					$("[name='skillScore']").val(100-sum);
				});
			});
			if(${not empty rec.recFlow}){
				$("input").prop("readonly","readonly");
			}
		});

		function jboxPrint(id) {
			jboxTip("正在准备打印…")
			setTimeout(function(){
				$("#title").show();
				var newstr = $("#"+id).html();
				var oldstr = document.body.innerHTML;
				document.body.innerHTML = newstr;
				window.print();
				document.body.innerHTML = oldstr;
				$("#title").hide();
				jboxEndLoading();
				return false;
			},2000);
		}
		function recSubmit(rec){
			jboxConfirm("确认提交?",function(){
				jboxPost("<s:url value='/res/rec/opreResRec'/>",rec,function(resp){
					if(resp=="${GlobalConstant.DELETE_SUCCESSED}" || resp=="${GlobalConstant.OPRE_SUCCESSED}"){
						parentRefresh();
						jboxClose();
					}
				},null,true);
			},null);
		}
		function parentRefresh(){
			//window.parent.frames['mainIframe'].window.$(".recTypeTag.active").click();
			window.parent.document.mainIframe.location.reload();
		}


	</script>
<body>
<div class="mainright">
	<div class="content">
		<form id="dopsForm" style="position: relative">
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 15px;">
					上海市奉贤区中心医院住院医师规范化培训<br/>胸腔穿刺术操作技能考核评分表
				</div>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.userFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:set var="verification" value="${empty rec.recFlow}"/>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<input type="hidden" id="ZongHe" name="ZongHe" value="${formDataMap['ZongHe']}"/>
					<tr>
						<td colspan="2" width="25%">考核住院医师：</td>
						<td colspan="2" width="25%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['studentName']}
								<input type="hidden" class="inputText"  name="studentName" value="${formDataMap['studentName']}"/>
							</c:if>
						</td>
						<td colspan="2" width="25%">考核科室：</td>
						<td colspan="2" width="25%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="deptName" value="${currRegProcess.deptName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">日期：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" class="toggleView3 inputText validate[required] ${GlobalConstant.RES_ROLE_SCOPE_DOCTOR} ctrlInput" name="date" value="${empty formDataMap['date']?pdfn:getCurrDate():formDataMap['date']}" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
								<label style="display: none;" class="toggleView3">${formDataMap['date']}</label>
							</c:if>
							<c:if test="${!verification}">
								<label>${formDataMap['date']}</label>
								<input type="hidden" name="date" value="${formDataMap['date']}"/>
							</c:if>
						</td>
						<td colspan="2">得分：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="skillScore" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['skillScore']}
								<input type="hidden" class="inputText"  name="skillScore" value="${formDataMap['skillScore']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td width="12.5%">评分项目</td>
						<td colspan="5">评分细则</td>
						<td width="12.5%">满分</td>
						<td width="12.5%">扣分</td>
					</tr>
					<tr>
						<td rowspan="2">病人准备（10分）</td>
						<td colspan="5">①核对病人信息（2 分）；②向病人解释穿刺目的（2分）；③消除紧张感（2 分）</td>
						<td>6</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">病人取坐位，面向椅背，两前臂置于椅背上，前额伏于手臂上</td>
						<td>4</td>
						<td><input name="part01" type="text" value="${formDataMap['part01']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] scoreCount" style="width:90%; "  style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="4">消毒铺巾（21分）</td>
						<td colspan="5">定位：选择肩胛下角线或腋后线 7-8 肋间、腋中线 6-7 肋间或腋前线第 5 肋间为穿刺点</td>
						<td>5</td>
						<td><input name="part02" type="text" value="${formDataMap['part02']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">①常规消毒术区 3 次（3 分）；②直径 15cm 逐步缩小（3 分）</td>
						<td>6</td>
						<td><input name="part03" type="text" value="${formDataMap['part03']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">戴无菌①帽子②口罩③手套</td>
						<td>5</td>
						<td><input name="part04" type="text" value="${formDataMap['part04']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">铺无菌洞巾。（由助手固定，请考官替代）</td>
						<td>5</td>
						<td><input name="part05" type="text" value="${formDataMap['part05']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="7">麻醉穿刺（41分）</td>
						<td colspan="5">①检查器械（2 分）②检查穿刺针是否通畅，胶管是否漏气及破损（4 分）</td>
						<td>6</td>
						<td><input name="part06" type="text" value="${formDataMap['part06']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">①选下一肋骨的上缘为穿刺点（2 分）②核对局麻药物名称、用2%利多卡因局部麻醉（2 分）③先注射皮下出现皮肤橘皮样皮丘改变（2 分）然后自皮至胸膜层进行逐层麻醉（2 分）</td>
						<td>8</td>
						<td><input name="part07" type="text" value="${formDataMap['part07']}" class="inputText ctrlInput validate[required,custom[number],max[8],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">用血管钳夹住穿刺针后端的胶管，使之不漏气</td>
						<td>4</td>
						<td><input name="part08" type="text" value="${formDataMap['part08']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">①左手固定穿刺部位皮肤（3 分）②右手持针沿麻醉部位经肋骨上缘垂直刺入，当有突破感时停止（5 分）</td>
						<td>8</td>
						<td><input name="part09" type="text" value="${formDataMap['part09']}" class="inputText ctrlInput validate[required,custom[number],max[8],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">接上注射器后，在松开止血钳（助手请考官替代）</td>
						<td>5</td>
						<td><input name="part10" type="text" value="${formDataMap['part10']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">注射器抽满后用血管钳夹闭胶管（助手请考官替代），取下注射器</td>
						<td>5</td>
						<td><input name="part11" type="text" value="${formDataMap['part11']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">将抽出液注入弯盘及专门准备的容器中</td>
						<td>5</td>
						<td><input name="part12" type="text" value="${formDataMap['part12']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="5">穿刺结束后处理（21 分）</td>
						<td colspan="5">抽完液后拔出穿刺针，消毒后覆盖无菌纱布，稍用力压迫片刻</td>
						<td>6</td>
						<td><input name="part13" type="text" value="${formDataMap['part13']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">用胶布固定覆盖术口</td>
						<td>2</td>
						<td><input name="part14" type="text" value="${formDataMap['part14']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">将抽出液送化验（常规、生化、培养及病理）、计量</td>
						<td>5</td>
						<td><input name="part15" type="text" value="${formDataMap['part15']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">①明确无并发症（2 分）②嘱病人术后静卧（2 分）③告诉病人有不适立即通知医护人员（2 分）</td>
						<td>6</td>
						<td><input name="part16" type="text" value="${formDataMap['part16']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">整理物品 </td>
						<td>2</td>
						<td><input name="part17" type="text" value="${formDataMap['part17']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>提问（4 分） </td>
						<td colspan="5"></td>
						<td>4</td>
						<td><input name="part18" type="text" value="${formDataMap['part18']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>人文关怀（3 分）</td>
						<td colspan="5">体现在整个操作过程中 </td>
						<td>3</td>
						<td><input name="part19" type="text" value="${formDataMap['part19']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td style="text-align: right;" colspan="8">考评人签名：<input name="kprqm" type="text" value="${formDataMap['kprqm']}" class="inputText ctrlInput validate[required]" style="width:90%; " style="width: 80px;"/></td>
					</tr>
				</table>
			</div>
		</form>
		<div style="padding-top: 5px;text-align: center;">
			<c:if test="${verification}">
				<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
			</c:if>
			<c:if test="${!verification}">
				<input class="search" type="button" value="打&#12288;印"  onclick="printForm('${rec.recFlow}');"/>
			</c:if>
			<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>