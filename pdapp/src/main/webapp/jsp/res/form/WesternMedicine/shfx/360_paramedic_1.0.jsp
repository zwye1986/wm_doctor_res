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
					$("[name='part11']").val(Number(sum).toFixed(1));
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
				<div id="title" style="width:100%;text-align: center;font-size: 12px;display: none;">
					临床护理人员对住院医师规范化培训学员的评价表
				</div>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.userFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:set var="verification" value="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq param.roleFlag and empty rec.recFlow }"/>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<input type="hidden" id="ZongHe" name="ZongHe" value="${formDataMap['ZongHe']}"/>
					<tr>
						<td colspan="3">360度评估是一种广泛应用于各行业的评估方法，常用于全方位考核调查对象的具体情况，通常完成360度评估表的评估者是上级同行，下属，病人及亲属等。为全面了解我院住培医师情况，特制定此表。请您用1-5分来表示符合程度，并根据自己了解的情况如实回答，谢谢您的合作。</td>
					</tr>
					<tr>
						<td colspan="3">被评价人情况</td>
					</tr>
					<tr>
						<td colspan="3">住院医师姓名：<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/></td>
					</tr>
					<tr>
						<td colspan="3">所在科室：<input type="text" class="inputText validate[required]"  name="stuDeptName" value="${formDataMap['stuDeptName']}"/></td>
					</tr>
					<tr>
						<td colspan="3">培训第几年：
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="year"  value="1" <c:if test="${formDataMap['year']=='1'}">checked</c:if>/>&nbsp;第一年</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="year"  value="2" <c:if test="${formDataMap['year']=='2'}">checked</c:if>/>&nbsp;第二年</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="year"  value="3" <c:if test="${formDataMap['year']=='3'}">checked</c:if>/>&nbsp;第三年</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">评价人情况</td>
					</tr>
					<tr>
						<td colspan="3">评价人姓名：<input type="text" class="inputText validate[required]"  name="evlUserName" value="${formDataMap['evlUserName']}"/></td>
					</tr>
					<tr>
						<td colspan="3">所在科室：<input type="text" class="inputText validate[required]"  name="deptName" value="${formDataMap['deptName']}"/></td>
					</tr>
					<tr>
						<td colspan="3">工作年限：
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="workYear"  value="1" <c:if test="${formDataMap['workYear']=='1'}">checked</c:if>/>&nbsp;1--5年</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="workYear"  value="2" <c:if test="${formDataMap['workYear']=='2'}">checked</c:if>/>&nbsp;5--10年</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="workYear"  value="3" <c:if test="${formDataMap['workYear']=='3'}">checked</c:if>/>&nbsp;10年以上</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">下表描述医师的行为与其他住培医师相比，请把此住培医师的表现用1-5分体现。</td>
					</tr>
					<tr>
						<td colspan="2">1.在工作中对同事病人表示尊重</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part1" type="text" value="${formDataMap['part1']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">2.能够有效的与同事病人交流沟通</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part2" type="text" value="${formDataMap['part2']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">3.能够协调控制自身压力，不影响工作</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part3" type="text" value="${formDataMap['part3']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">4.在工作中能够及时完成临床诊疗工作</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part4" type="text" value="${formDataMap['part4']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">5.注重良好职业形象的树立</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part5" type="text" value="${formDataMap['part5']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">6.理论知识水平能够应对工作</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part6" type="text" value="${formDataMap['part6']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">7.操作技能水平能够应对工作</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part7" type="text" value="${formDataMap['part7']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">8.医师能和护理人员配合完成工作</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part8" type="text" value="${formDataMap['part8']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">9.该医师建立正确的无菌观念</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part9" type="text" value="${formDataMap['part9']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">10.该医师能遵守相关制度原则</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[1]] scoreCount" style="width:90%; "  name="part10" type="text" value="${formDataMap['part10']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left">总得分</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number]]" name="part11" type="text" value="${formDataMap['part11']}" readonly="readonly" style="width: 150px;"/>
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