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
					$("[name='skillScore']").val(100-Number(sum).toFixed(1));
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
					上海市奉贤区中心医院住院医师规范化培训<br/>成人气管插管穿刺术操作技能考考核评分表
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
					<%--<tr>--%>
						<%--<td colspan="2">考核时间</td>--%>
						<%--<td colspan="2">15分钟</td>--%>
						<%--<td colspan="2">满分</td>--%>
						<%--<td colspan="2">100分</td>--%>
					<%--</tr>--%>
					<tr>
						<td width="12.5%">项目</td>
						<td colspan="5">具体内容要点 </td>
						<td width="12.5%">标准分</td>
						<td width="12.5%">扣分</td>
					</tr>
					<tr>
						<td rowspan="3">准备</td>
						<td colspan="5">1、向患者家属解释病情，强调插管的必要性及可能存在的并发症及风险，征得家属的同意并签字</td>
						<td>3</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">2、准备器械: 喉镜，气管导管，牙垫、导管管芯、导管润滑剂、吸引装置、给氧装置等。检查并连接喉镜，选择合适的气管导管，检查气管导管是否通畅、气囊密闭性是否良好，并将导引钢丝置入导管内</td>
						<td>4</td>
						<td><input name="part01" type="text" value="${formDataMap['part01']}" class="inputText ctrlInput validate[required,custom[number],max[4],min[0]] scoreCount" style="width:90%; "  style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">3、选择性插管使用喉镜前，常规应用面罩实行纯氧吸入去氮操作，以提高体内氧的储备量和肺内氧浓度。</td>
						<td>3</td>
						<td><input name="part02" type="text" value="${formDataMap['part02']}" class="inputText ctrlInput validate[required,custom[number],max[3],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="5">操作</td>
						<td colspan="5">1、患者仰卧，头部尽量后仰，检查口腔有无义齿及牙齿松动。如喉头暴露欠佳，可肩背部下垫薄枕。若患者口未张开，以右手自右口角处将口腔打开。其法是右手拇指对着下齿列，示指对着上齿列，以一旋转力量启开口肿。</td>
						<td>5</td>
						<td><input name="part03" type="text" value="${formDataMap['part03']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">2、左手持喉镜由右口角放入口腔，用喉镜片将舌体推向左侧，沿舌背面向咽喉部缓慢进入，先暴露悬雍垂，后暴露会厌</td>
						<td>10</td>
						<td><input name="part04" type="text" value="${formDataMap['part04']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">3、挑起会厌以显露声门。如采用弯镜片插管，喉镜片前端进入舌根与会厌交界处（会厌谷），然后将喉镜向上、向前提起，显露声门。如采用直镜片插管，应直接挑起会厌，声门即可显露</td>
						<td>10</td>
						<td><input name="part05" type="text" value="${formDataMap['part05']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">4、以右手拇指、示指及中指如持笔式持住导管的中、上段。由口右角进入口腔，直到导管已接近喉头再将管端移至喉镜片处，同时双目经过镜片与管壁间的狭窄间隙监视导管前进方向，准确轻巧地将导管尖端插入声门。借助管芯插管时，当导管尖端入声门后，应拔出管芯再将导管插入气管内。导管插入气管内的深度成人为 4-5 厘米，导管剪短至中切牙的距离约为 18-22厘米</td>
						<td>20</td>
						<td><input name="part06" type="text" value="${formDataMap['part06']}" class="inputText ctrlInput validate[required,custom[number],max[20],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">5、将空气（约 3-5 毫升）注入低压气囊，以气囊恰好封闭气管而不漏气为原则。</td>
						<td>5</td>
						<td><input name="part07" type="text" value="${formDataMap['part07']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="3">确认</td>
						<td colspan="5">1、听诊腋窝和剑突上的飞呼吸音，双侧肺应一致 </td>
						<td>10</td>
						<td><input name="part08" type="text" value="${formDataMap['part08']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">2、观察胸廓起伏活动，双侧应均匀一致 </td>
						<td>5</td>
						<td><input name="part09" type="text" value="${formDataMap['part09']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">3、观察呼气末 CO2（ETC02）波形</td>
						<td>10</td>
						<td><input name="part10" type="text" value="${formDataMap['part10']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td>固定</td>
						<td colspan="5">放入牙垫，用胶布将导管固定</td>
						<td>5</td>
						<td><input name="part12" type="text" value="${formDataMap['part12']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="2">总体要求</td>
						<td colspan="5">1、操作熟练、准确、动作迅速</td>
						<td>5</td>
						<td><input name="part13" type="text" value="${formDataMap['part13']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">2、人文关怀</td>
						<td>5</td>
						<td><input name="part14" type="text" value="${formDataMap['part14']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
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