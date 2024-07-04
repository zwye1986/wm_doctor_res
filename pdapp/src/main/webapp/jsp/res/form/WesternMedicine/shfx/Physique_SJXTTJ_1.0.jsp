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
					上海市奉贤区中心医院住院医师规范化培训<br/>体格检查评分表（神经系统体检）
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
						<td colspan="2" width="25%">考生姓名：</td>
						<td colspan="2" width="25%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['studentName']}
								<input type="hidden" class="inputText"  name="studentName" value="${formDataMap['studentName']}"/>
							</c:if>
						</td>
						<td colspan="2" width="25%">所属培训基地：</td>
						<td colspan="2" width="25%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="orgName" value="${doctor.orgName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['orgName']}
								<input type="hidden" class="inputText"  name="orgName" value="${formDataMap['orgName']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">考核科室：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="deptName" value="${currRegProcess.deptName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
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
						<td colspan="2">考核时间</td>
						<td colspan="2">15分钟</td>
						<td colspan="2">满分</td>
						<td colspan="2">100分</td>
					</tr>
					<tr>
						<td colspan="2">评分项目</td>
						<td width="12.5%">标准分</td>
						<td colspan="3">评分标准</td>
						<td width="12.5%">扣分理由</td>
						<td width="12.5%">扣分</td>
					</tr>
					<tr>
						<td colspan="2" rowspan="6">血压测量（20 分）</td>
						<td>4</td>
						<td colspan="3">血压计放置位置正确</td>
						<td><input class="inputText ctrlInput validate[required]" style="width:90%; "  name="reason00" type="text" value="${formDataMap['reason00']}" style="width: 150px;"/></td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>4</td>
						<td colspan="3">血压带绑扎部位正确、松紧度适宜</td>
						<td><input name="reason01" type="text" value="${formDataMap['reason01']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part01" type="text" value="${formDataMap['part01']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] scoreCount" style="width:90%; "  style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>4</td>
						<td colspan="3">听诊器胸件放置位置部位正确</td>
						<td><input name="reason02" type="text" value="${formDataMap['reason02']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part02" type="text" value="${formDataMap['part02']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>4</td>
						<td colspan="3">测量过程流畅</td>
						<td><input name="reason03" type="text" value="${formDataMap['reason03']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part03" type="text" value="${formDataMap['part03']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>2</td>
						<td colspan="3">终结复原</td>
						<td><input name="reason04" type="text" value="${formDataMap['reason04']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part04" type="text" value="${formDataMap['part04']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>2</td>
						<td colspan="3">读数结果</td>
						<td><input name="reason05" type="text" value="${formDataMap['reason05']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part05" type="text" value="${formDataMap['part05']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="12">神经系统检查（60分）</td>
						<td rowspan="3">眼部检查</td>
						<td>5</td>
						<td colspan="3">眼球运动检查方法正确</td>
						<td><input name="reason06" type="text" value="${formDataMap['reason06']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part06" type="text" value="${formDataMap['part06']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td colspan="3">对光反射（间接、直接）检查方法正确</td>
						<td><input name="reason07" type="text" value="${formDataMap['reason07']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part07" type="text" value="${formDataMap['part07']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td colspan="3">调节反射检查方法正确</td>
						<td><input name="reason08" type="text" value="${formDataMap['reason08']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part08" type="text" value="${formDataMap['part08']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="2">深反射、浅反射</td>
						<td>5</td>
						<td colspan="3">深反射：跟腱、肱二头肌、膝反射</td>
						<td><input name="reason09" type="text" value="${formDataMap['reason09']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part09" type="text" value="${formDataMap['part09']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td colspan="3">浅反射：腹壁反射</td>
						<td><input name="reason10" type="text" value="${formDataMap['reason10']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part10" type="text" value="${formDataMap['part10']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="3">脑膜刺激征</td>
						<td>5</td>
						<td colspan="3">测试颈项强直操作正确</td>
						<td><input name="reason11" type="text" value="${formDataMap['reason11']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part11" type="text" value="${formDataMap['part11']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td colspan="3">测试 Kernig 征操作正确</td>
						<td><input name="reason12" type="text" value="${formDataMap['reason12']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part12" type="text" value="${formDataMap['part12']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td colspan="3">测试 Bruzinski 征操作正确</td>
						<td><input name="reason13" type="text" value="${formDataMap['reason13']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part13" type="text" value="${formDataMap['part13']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="4">锥体束病理反射</td>
						<td>5</td>
						<td colspan="3">Babinski 征</td>
						<td><input name="reason14" type="text" value="${formDataMap['reason14']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part14" type="text" value="${formDataMap['part14']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td colspan="3">Oppenheim 征</td>
						<td><input name="reason15" type="text" value="${formDataMap['reason15']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part15" type="text" value="${formDataMap['part15']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td colspan="3">Gordon 征</td>
						<td><input name="reason16" type="text" value="${formDataMap['reason16']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part16" type="text" value="${formDataMap['part16']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>5</td>
						<td colspan="3">Chaddock 征</td>
						<td><input name="reason17" type="text" value="${formDataMap['reason17']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part17" type="text" value="${formDataMap['part17']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>结果提问（5分）</td>
						<td colspan="5">阳性体征分析</td>
						<td><input name="reason20" type="text" value="${formDataMap['reason20']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part20" type="text" value="${formDataMap['part20']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>专科检查（10分）</td>
						<td colspan="5">检查内容根据病种及考生学科拟定<br/>内容：
							<input name="text21" type="text" value="${formDataMap['text21']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 200px;"/>
						</td>
						<td><input name="reason21" type="text" value="${formDataMap['reason21']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part21" type="text" value="${formDataMap['part21']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>整体印象（5分）</td>
						<td colspan="5">包括熟练程度和时间掌握情况</td>
						<td><input name="reason22" type="text" value="${formDataMap['reason22']}" class="inputText ctrlInput validate[required]" style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part22" type="text" value="${formDataMap['part22']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="8">备注：
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="remarks" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['remarks']}
								<input type="hidden" class="inputText"  name="remarks" value="${formDataMap['remarks']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: left">
							考官姓名：
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="teacherName" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['teacherName']}
								<input type="hidden" class="inputText"  name="teacherName" value="${formDataMap['teacherName']}"/>
							</c:if>
						</td>
						<td colspan="4" style="text-align: center;">
							日期：
							<c:if test="${verification}">
								<input type="text" class="toggleView3 inputText validate[required] ${GlobalConstant.RES_ROLE_SCOPE_DOCTOR} ctrlInput" name="date" value="${empty formDataMap['date']?pdfn:getCurrDate():formDataMap['date']}" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
								<label style="display: none;" class="toggleView3">${formDataMap['date']}</label>
							</c:if>
							<c:if test="${!verification}">
								<label>${formDataMap['date']}</label>
								<input type="hidden" name="date" value="${formDataMap['date']}"/>
							</c:if>
						</td>
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