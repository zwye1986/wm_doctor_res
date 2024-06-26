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
		Number.prototype.toFixed=function (d) {
			var s=this+"";
			if(!d)d=0;
			if(s.indexOf(".")==-1)s+=".";
			s+=new Array(d+1).join("0");
			if(new RegExp("^(-|\\+)?(\\d+(\\.\\d{0,"+(d+1)+"})?)\\d*$").test(s)){
				var s="0"+RegExp.$2,pm=RegExp.$1,a=RegExp.$3.length,b=true;
				if(a==d+2){
					a=s.match(/\d/g);
					if(parseInt(a[a.length-1])>4){
						for(var i=a.length-2;i>=0;i--){
							a[i]=parseInt(a[i])+1;
							if(a[i]==10){
								a[i]=0;
								b=i!=1;
							}else break;
						}
					}
					s=a.join("").replace(new RegExp("(\\d+)(\\d{"+d+"})\\d$"),"$1.$2");

				}if(b)s=s.substr(1);
				return (pm+s).replace(/\.$/,"");
			}
			return this+"";
		};
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
							parentRefresh();
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
					$("[name='part10']").val(Number(sum).toFixed(1));
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
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.userFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:set var="verification" value="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag and empty rec.recFlow}"/>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<input type="hidden" id="ZongHe" name="ZongHe" value="${formDataMap['ZongHe']}"/>
					<tr>
						<c:if test="${verification}">
							<td style="width: 33%">姓名：<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/></td>
							<td style="width: 33%">届别：<input type="text" style="text-align: left;" class="inputText validate[custom[number],required]" name="sessionNumber" value="${doctor.sessionNumber}"/></td>
							<td style="width: 33%">培训专业：<input type="text" style="text-align: left;" class="inputText validate[required]" name="trainingSpeName" value="${doctor.trainingSpeName}"/></td>
						</c:if>
						<c:if test="${!verification}">
							<td style="width: 33%">姓名：
								<label>${formDataMap['studentName']}</label>
								<input type="hidden" name="studentName" value="${formDataMap['studentName']}"/>
							</td>
							<td style="width: 33%">届别：
								<label>${formDataMap['sessionNumber']}</label>
								<input type="hidden" name="sessionNumber" value="${formDataMap['sessionNumber']}"/>
							</td>
							<td style="width: 33%">培训专业：
								<label>${formDataMap['trainingSpeName']}</label>
								<input type="hidden" name="trainingSpeName" value="${formDataMap['trainingSpeName']}"/>
							</td>
						</c:if>
					</tr>
					<tr>
						<td colspan="3" >轮转科室名称：
							<c:if test="${verification}">
								<input type="text" class="inputText validate[required]"  name="deptName" value="${currRegProcess.deptName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="3" >轮转时间：
							<c:if test="${verification }">
								<input type="text" class="inputText validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeQ" value="${currRegProcess.schStartDate}"/>
							</c:if>
							<c:if test="${!verification }">
								${formDataMap['cycleTimeQ']}
								<input type="hidden" class="inputText" name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							至
							<c:if test="${verification }">
								<input type="text" class="inputText validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeH" value="${currRegProcess.schEndDate}"/>
							</c:if>
							<c:if test="${!verification }">
								${formDataMap['cycleTimeH']}
								<input type="hidden" class="inputText" name="cycleTimeH" value="${formDataMap['cycleTimeH']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">评价内容</td>
						<td>分数</td>
					</tr>
					<tr>
						<td rowspan="5">医德医风人际沟通团队合作</td>
						<td>遵守国家法律法规、医院规章制度</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td>履行岗位职责</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part01" type="text" value="${formDataMap['part01']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td>以病人为中心，体现人文关怀</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part02" type="text" value="${formDataMap['part02']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td>人际（医患）沟通和表达能力</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part03" type="text" value="${formDataMap['part03']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td>团结协作精神</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part04" type="text" value="${formDataMap['part04']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td rowspan="5">临床综合能力</td>
						<td>临床基本知识、基本理论掌握程度</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part05" type="text" value="${formDataMap['part05']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td>临床基本技能掌握程度</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part06" type="text" value="${formDataMap['part06']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td>临床思维能力</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part07" type="text" value="${formDataMap['part07']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td>临床诊疗能力</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part08" type="text" value="${formDataMap['part08']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td>危重病人的识别及紧急处理能力</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part09" type="text" value="${formDataMap['part09']}" style="width: 150px;" placeholder="0-10分"/></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left">总得分</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number]]" name="part10" type="text" value="${formDataMap['part10']}" readonly="readonly" style="width: 150px;"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div style="padding-top: 5px;text-align: center;">
			<c:if test="${verification}">
				<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
			</c:if>
			<%--<c:if test="${(GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag or 'manage'eq param.roleFlag) }">--%>
				<%--<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>--%>
			<%--</c:if>--%>
			<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>