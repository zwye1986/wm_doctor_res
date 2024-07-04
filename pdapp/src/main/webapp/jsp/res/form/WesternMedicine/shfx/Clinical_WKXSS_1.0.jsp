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
			var score=0;
			$(".scoreCount").keyup(function(){
				var sum = 0;
				$(".scoreCount").each(function(){
					var val = this.value;
					if(val && !isNaN(val)){
						sum+=parseFloat(val);
					}
					$("[name='skillScore']").val(Number(sum).toFixed(1));
					score=Number(sum).toFixed(1);
				});
			});
			$(".score_Count").keyup(function(){
				var sum = 0;
				$(".score_Count").each(function(){
					var val = this.value;
					if(val && !isNaN(val)){
						sum+=parseFloat(val);
					}
					var total=score-Number(sum).toFixed(1);
					$("[name='skillScore']").val(60+total);
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
					上海市奉贤区中心医院住院医师规范化培训外科学专业<br/>手术技能操作评分表
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
						<td colspan="4">时间：视手术情况而定</td>
						<td colspan="4">满分:100分</td>
					</tr>
					<tr>
						<td colspan="2">考生姓名：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['studentName']}
								<input type="hidden" class="inputText"  name="studentName" value="${formDataMap['studentName']}"/>
							</c:if>
						</td>
						<td colspan="2">培训学科：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="pxxk" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['pxxk']}
								<input type="hidden" class="inputText"  name="pxxk" value="${formDataMap['pxxk']}"/>
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
						<td colspan="2">总分：</td>
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
						<td colspan="8">患者姓名、床号及住院号：
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="hzxx" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['hzxx']}
								<input type="hidden" class="inputText"  name="hzxx" value="${formDataMap['hzxx']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="8">手术名称：
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="ssmc" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['ssmc']}
								<input type="hidden" class="inputText"  name="ssmc" value="${formDataMap['ssmc']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="8" style="text-align: center;"><font style="font-weight:600;">一、无菌操作（40 分）</font></td>
					</tr>
					<tr>
						<td colspan="6">考核内容</td>
						<td>扣分理由</td>
						<td>得分</td>
					</tr>
					<tr>
						<td colspan="8">1、刷手：满分 10 分</td>
					</tr>
					<tr>
						<td colspan="6">刷手顺序及范围（分段交替向上） 2</td>
						<td><input class="inputText ctrlInput " style="width:90%; "  name="reason00" type="text" value="${formDataMap['reason00']}" style="width: 150px;"/></td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">刷手重点部位（6 步洗手法） 2</td>
						<td><input name="reason01" type="text" value="${formDataMap['reason01']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part01" type="text" value="${formDataMap['part01']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; "  style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">冲洗时顺序及手臂保护 2</td>
						<td><input name="reason02" type="text" value="${formDataMap['reason02']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part02" type="text" value="${formDataMap['part02']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">擦手臂时无菌操作 2</td>
						<td><input name="reason03" type="text" value="${formDataMap['reason03']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part03" type="text" value="${formDataMap['part03']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">刷手后是否接触了有菌物品及处理 1</td>
						<td><input name="reason04" type="text" value="${formDataMap['reason04']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part04" type="text" value="${formDataMap['part04']}" class="inputText ctrlInput validate[required,custom[number],max[1],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">刷手时间 1</td>
						<td><input name="reason05" type="text" value="${formDataMap['reason05']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part05" type="text" value="${formDataMap['part05']}" class="inputText ctrlInput validate[required,custom[number],max[1],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="8">2、穿衣：满分 8 分</td>
					</tr>
					<tr>
						<td colspan="6">提衣动作 2</td>
						<td><input name="reason06" type="text" value="${formDataMap['reason06']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part06" type="text" value="${formDataMap['part06']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">递腰动作 2</td>
						<td><input name="reason07" type="text" value="${formDataMap['reason07']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part07" type="text" value="${formDataMap['part07']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">手是否接触有菌区 2</td>
						<td><input name="reason08" type="text" value="${formDataMap['reason08']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part08" type="text" value="${formDataMap['part08']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">穿衣时置手规范 2</td>
						<td><input name="reason09" type="text" value="${formDataMap['reason09']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part09" type="text" value="${formDataMap['part09']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="8">3、戴手套：满分 8 分</td>
					</tr>
					<tr>
						<td colspan="6">提取手套 2</td>
						<td><input name="reason10" type="text" value="${formDataMap['reason10']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part10" type="text" value="${formDataMap['part10']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">戴手套时无菌概念 2</td>
						<td><input name="reason11" type="text" value="${formDataMap['reason11']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part11" type="text" value="${formDataMap['part11']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">手套腕部外翻部位 2</td>
						<td><input name="reason12" type="text" value="${formDataMap['reason12']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part12" type="text" value="${formDataMap['part12']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">手套扎手术衣袖口 2</td>
						<td><input name="reason13" type="text" value="${formDataMap['reason13']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part13" type="text" value="${formDataMap['part13']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="8">4、消毒及铺巾：满分 14 分</td>
					</tr>
					<tr>
						<td colspan="6">消毒钳持拿 2</td>
						<td><input name="reason14" type="text" value="${formDataMap['reason14']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part14" type="text" value="${formDataMap['part14']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">消毒顺序和范围 2</td>
						<td><input name="reason15" type="text" value="${formDataMap['reason15']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part15" type="text" value="${formDataMap['part15']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">铺巾顺序、铺巾后有无移动 2</td>
						<td><input name="reason16" type="text" value="${formDataMap['reason16']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part16" type="text" value="${formDataMap['part16']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">5、手术进行中的无菌操作观念（十项要求、可提问） 8</td>
						<td><input name="reason17" type="text" value="${formDataMap['reason17']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
						<td><input name="part17" type="text" value="${formDataMap['part17']}" class="inputText ctrlInput validate[required,custom[number],max[8],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="8" style="text-align: center;"><font style="font-weight:600;">二、手术基本技术——切开、缝合、结扎、止血（满分 60 分）</font></td>
					</tr>
					<tr>
						<td>考核内容</td>
						<td colspan="4">考核内容</td>
						<td>满分</td>
						<td>扣分</td>
						<td>扣分理由</td>
					</tr>
					<tr>
						<td rowspan="4">切开（10 分）</td>
						<td colspan="4">安装刀片的方法</td>
						<td>2</td>
						<td><input name="score1" type="text" value="${formDataMap['score1']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason21" type="text" value="${formDataMap['reason21']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">执刀姿势</td>
						<td>2</td>
						<td><input name="score2" type="text" value="${formDataMap['score2']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason22" type="text" value="${formDataMap['reason22']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">切开操作规范（皮肤紧绷、按层切开）（可用电刀）</td>
						<td>4</td>
						<td><input name="score3" type="text" value="${formDataMap['score3']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason23" type="text" value="${formDataMap['reason23']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">切开的深浅度 </td>
						<td>2</td>
						<td><input name="score4" type="text" value="${formDataMap['score4']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason24" type="text" value="${formDataMap['reason24']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td rowspan="4">打结（10 分）</td>
						<td colspan="4">递线及绕线方法</td>
						<td>2</td>
						<td><input name="score5" type="text" value="${formDataMap['score5']}" class="inputText ctrlInput validate[required,custom[number],max[2],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason25" type="text" value="${formDataMap['reason25']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">打结手法（是否有滑结、假结）</td>
						<td>4</td>
						<td><input name="score6" type="text" value="${formDataMap['score6']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason26" type="text" value="${formDataMap['reason26']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">打结质量（牢靠、松紧等）</td>
						<td>3</td>
						<td><input name="score7" type="text" value="${formDataMap['score7']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason27" type="text" value="${formDataMap['reason27']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">打结速度 </td>
						<td>1</td>
						<td><input name="score8" type="text" value="${formDataMap['score8']}" class="inputText ctrlInput validate[required,custom[number],max[1],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason28" type="text" value="${formDataMap['reason28']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td rowspan="3">剪（拆）线（10 分）</td>
						<td colspan="4">持剪（及提起线结）的方法</td>
						<td>3</td>
						<td><input name="score9" type="text" value="${formDataMap['score9']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason29" type="text" value="${formDataMap['reason29']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">剪（拆）线方法（”靠、滑、侧、剪”等）</td>
						<td>4</td>
						<td><input name="score10" type="text" value="${formDataMap['score10']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason30" type="text" value="${formDataMap['reason30']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">线尾留长</td>
						<td>3</td>
						<td><input name="score11" type="text" value="${formDataMap['score11']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason31" type="text" value="${formDataMap['reason31']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td rowspan="4">缝合（20 分）</td>
						<td colspan="4">合理选择器材</td>
						<td>3</td>
						<td><input name="score12" type="text" value="${formDataMap['score12']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason32" type="text" value="${formDataMap['reason32']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">夹针方法及熟练程度</td>
						<td>3</td>
						<td><input name="score13" type="text" value="${formDataMap['score13']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason33" type="text" value="${formDataMap['reason33']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">缝合技巧（进针、出针、针距、边距等）</td>
						<td>8</td>
						<td><input name="score14" type="text" value="${formDataMap['score14']}" class="inputText ctrlInput validate[required,custom[number],max[8],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason34" type="text" value="${formDataMap['reason34']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">常用缝合方法掌握情况</td>
						<td>6</td>
						<td><input name="score15" type="text" value="${formDataMap['score15']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason35" type="text" value="${formDataMap['reason35']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="6">爱伤观念（5 分）</td>
						<td><input name="score16" type="text" value="${formDataMap['score16']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason36" type="text" value="${formDataMap['reason36']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="6">沟通技能（5 分）</td>
						<td><input name="score17" type="text" value="${formDataMap['score17']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] score_Count" style="width:90%; " style="width: 80px;"/></td>
						<td><input name="reason37" type="text" value="${formDataMap['reason37']}" class="inputText ctrlInput " style="width:90%; "  style="width: 150px;"/></td>
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
							考官签名：
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