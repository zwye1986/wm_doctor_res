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
		//导出评分表
		function printForm(recFlow){
			jboxTip("打印中,请稍等...");
			var url = '<s:url value="/res/fengxianPj/printForm?recFlow="/>'+recFlow;
			window.location.href = url;
		}
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
					$("[name='part14']").val(sum);
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
					上海市奉贤区中心医院住院医师规范化培训<br/>股动脉穿刺操作技能考核评分表
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
						<td width="12.5%">姓名：</td>
						<td width="12.5%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['studentName']}
								<input type="hidden" class="inputText"  name="studentName" value="${formDataMap['studentName']}"/>
							</c:if>
						</td>
						<td width="12.5%">工号：</td>
						<td width="12.5%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentSid" value=""/>
							</c:if>
							<c:if test="${!verification}">
								<label>${formDataMap['studentSid']}</label>
								<input type="hidden" name="studentSid" value="${formDataMap['studentSid']}"/>
							</c:if>
						</td>
						<td width="12.5%">专业：</td>
						<td width="12.5%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="speName" value=""/>
							</c:if>
							<c:if test="${!verification}">
								<label>${formDataMap['speName']}</label>
								<input type="hidden" name="speName" value="${formDataMap['speName']}"/>
							</c:if>
						</td>
						<td width="12.5%">考核科室：</td>
						<td width="12.5%">
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
						<td>项目</td>
						<td colspan="4">内容</td>
						<td>分值</td>
						<td>扣分标准</td>
						<td>扣分</td>
					</tr>
					<tr>
						<td rowspan="3">适应症（6 分）</td>
						<td colspan="4">1、需采集动脉血液标本或某些特殊检查</td>
						<td>2</td>
						<td>漏一项扣 1 分</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">2.急救时需加压输血输液</td>
						<td>2</td>
						<td>漏一项扣 1 分</td>
						<td><input name="part01" type="text" value="${formDataMap['part01']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; "  style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">3、用于区域性化疗</td>
						<td>2</td>
						<td>漏一项扣 1 分</td>
						<td><input name="part02" type="text" value="${formDataMap['part02']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="3">手术前准备（14 分）</td>
						<td colspan="4">1、了解、熟悉病人病情。与病人或家属谈话，做好解释工作，争取清醒病人配合</td>
						<td>8</td>
						<td>未与患者或家属沟通扣 8 分，沟通不佳，酌情扣分</td>
						<td><input name="part03" type="text" value="${formDataMap['part03']}" class="inputText ctrlInput validate[required,custom[number],max[8],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">2、备皮</td>
						<td>6</td>
						<td>选股动脉未备皮，扣 2 分</td>
						<td><input name="part04" type="text" value="${formDataMap['part04']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">3、物品准备：无菌持物钳浸于消毒溶液罐内，75%酒精或 10%碘伏消毒液，无菌纱布及罐、消毒棉签，0.1%肾上腺素、笔、砂轮，注射器、针头、抗凝剂、试管、弯盘: ,注射器针头回收器，需要时准备输液或输血用物</td>
						<td>10</td>
						<td>漏一项扣 1 分，扣完为止</td>
						<td><input name="part05" type="text" value="${formDataMap['part05']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="8">操作方法（80分）</td>
						<td colspan="4">1、准备洗手、戴口罩帽子</td>
						<td>3</td>
						<td>未戴口罩帽子扣 1 分，未洗手扣 1 分</td>
						<td><input name="part06" type="text" value="${formDataMap['part06']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">2、备齐用物携至床旁，查对床号、姓名治疗项目等，向患者或家属解释股动脉注射的目的、方法</td>
						<td>5</td>
						<td>漏一项扣 1 分</td>
						<td><input name="part07" type="text" value="${formDataMap['part07']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">3、协助病人取仰卧位、下肢伸直略外展外旋</td>
						<td>5</td>
						<td>体位不当扣5分</td>
						<td><input name="part08" type="text" value="${formDataMap['part08']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">4、检查注射器的包装、有效期等，再次插队，常规消毒穿刺位皮肤</td>
						<td>5</td>
						<td>未检查核对扣 5 分</td>
						<td><input name="part09" type="text" value="${formDataMap['part09']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">5、术者消毒左手中指和食指，在腹股沟韧带下方内侧，左手食指和中指触及股功脉搏动最明显处并固定，右乎持注 JfJ 器垂直刺入动脉或者与动脉走向呈 40 度角刺入。见回血后用右手固定注射器，左手抽动活塞，按需要采集标本或者接上输血输液器</td>
						<td>30</td>
						<td>定位欠佳，多次定位扣 5 分
							未戴手套或手消毒扣 10 分
							反复穿刺扣 5 分
							置管穿刺扣 5 分
							据熟练程度适当扣分</td>
						<td><input name="part10" type="text" value="${formDataMap['part10']}" class="inputText ctrlInput validate[required,custom[number],max[30],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">6、抽血或输入完毕，迅速拔针，局部用 3-5根消毒棉签或纱布加压按 5 分钟以上</td>
						<td>10</td>
						<td>形成血肿扣 5 分</td>
						<td><input name="part11" type="text" value="${formDataMap['part11']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">7、协助病人取舒适卧位，整理用物</td>
						<td>10</td>
						<td>漏做此项扣 10 分</td>
						<td><input name="part12" type="text" value="${formDataMap['part12']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="4">8、消毒洗手</td>
						<td>2</td>
						<td>未消毒洗手扣 2 分</td>
						<td><input name="part13" type="text" value="${formDataMap['part13']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>总分</td>
						<td colspan="5">100</td>
						<td>扣分</td>
						<td><input name="part14" type="text" value="${formDataMap['part14']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%; " style="width: 80px;" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>评卷人</td>
						<td colspan="5">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="teacherName" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['teacherName']}
								<input type="hidden" class="inputText"  name="teacherName" value="${formDataMap['teacherName']}"/>
							</c:if>
						</td>
						<td>得分</td>
						<td>
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="skillScore" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['skillScore']}
								<input type="hidden" class="inputText"  name="skillScore" value="${formDataMap['skillScore']}"/>
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