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

		//导出评分表
		function printForm(recFlow){
			jboxTip("打印中,请稍等...");
			var url = '<s:url value="/res/fengxianPj/printForm?recFlow="/>'+recFlow;
			window.location.href = url;
		}

	</script>
<body>
<div class="mainright">
	<div class="content">
		<form id="dopsForm" style="position: relative">
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 15px;">
					上海市奉贤区中心医院住院医师规范化培训<br/>徒手心肺复苏操作技能考核评分表
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
						<td></td>
						<td>项目</td>
						<td colspan="4">操作要点 </td>
						<td>满分</td>
						<td>扣分</td>
					</tr>
					<tr>
						<td rowspan="4">判断</td>
						<td>意识</td>
						<td colspan="4">拍打肩头，呼唤病人</td>
						<td>5分</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>呼吸</td>
						<td colspan="4">观察患者胸廓是否有起伏，用时至少 5 秒，但不超过 10 秒</td>
						<td>5分</td>
						<td><input name="part01" type="text" value="${formDataMap['part01']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>呼叫</td>
						<td colspan="4">喊人来帮忙抢救，院外打 120 或拿 AED，院内推抢救车和除颤仪</td>
						<td>5分</td>
						<td><input name="part02" type="text" value="${formDataMap['part02']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>检查颈动脉搏动</td>
						<td colspan="4">检查同侧颈动脉搏动，用时至少 5 秒，但不超过10 秒</td>
						<td>5分</td>
						<td><input name="part03" type="text" value="${formDataMap['part03']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>C</td>
						<td>胸外按压</td>
						<td colspan="4">1.用手掌掌根部按压，肘关节伸直，借助身体的重力垂直向下按压
							2.按压部位：胸部中央，胸骨中下段
							3.按压速率：至少每分钟 100 次（18 秒或更短时间内进行一组 30 次胸外按压）
							4、按压深度:至少 5 厘米(30 次按压中至少有 23次符合此标准)
							5．每次按压后让胸廓充分回弹(30 次按压中至少有 23 次符合此标准)
							6.按压和放松时间一致，按压放松时手掌不要离开原部位
							7.按压时同时报次数
							8.尽量减少按压中断时间:在 10 秒或更短时间内给予 2 次呼吸
						</td>
						<td>40分</td>
						<td><input name="part04" type="text" value="${formDataMap['part04']}" class="inputText ctrlInput validate[required,custom[number],max[40],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>A</td>
						<td>开放气道</td>
						<td colspan="4">使用仰头提骸法开放气道 </td>
						<td>7分</td>
						<td><input name="part05" type="text" value="${formDataMap['part05']}" class="inputText ctrlInput validate[required,custom[number],max[7],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>B</td>
						<td>人工呼吸</td>
						<td colspan="4">1.使用 E-C 手法压紧球囊面罩
							2.2 次人工呼吸，每次持续 1 秒并看到胸廓起伏
							3.2 次人工呼吸时间小于 10 秒
							4.按压通气比例（30:2） </td>
						<td>20分</td>
						<td><input name="part06" type="text" value="${formDataMap['part06']}" class="inputText ctrlInput validate[required,custom[number],max[20],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td></td>
						<td>提问</td>
						<td colspan="4"></td>
						<td>10分</td>
						<td><input name="part07" type="text" value="${formDataMap['part07']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;" /></td>
					</tr>
					<tr>
						<td></td>
						<td>人文关怀</td>
						<td colspan="4">体现在整个操作过程中 </td>
						<td>3分</td>
						<td><input name="part08" type="text" value="${formDataMap['part08']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] scoreCount" style="width:90%; " style="width: 80px;" /></td>
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