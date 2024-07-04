
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
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
	<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
	<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
	<%--<c:set var="orgTestSwitch" value="jswjw_${currRegProcess.orgFlow}_P004"/>--%>
	<%--<c:set var="orgTestSwitch" value="${sysCfgMap[orgTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="docTestSwitch" value="doc_test_switch_${currRegProcess.orgFlow}_${currRegProcess.userFlow}"/>--%>
	<%--<c:set var="docTestSwitch" value="${sysCfgMap[docTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="testTypeFlag" value="${testSwitch && orgTestSwitch && docTestSwitch&&urlEmpt}"/>--%>
	<c:set var="ckks" value="${open eq GlobalConstant.FLAG_Y}"/>
	<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>
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
	function save(){
		if($("#evaluationForm").validationEngine("validate")){
			var info = "确认保存？<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD)}">保存之后不可修改！</c:if>";
			jboxConfirm(info,function(){
			autoValue($("#evaluationForm"),"autoValue");
			var theoreResult = $('[name="theoreResult"]').val();
			$('#evaluationForm').append('<input type="hidden" name="totalScore" value="'+theoreResult+'"/>');
			jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$("#evaluationForm").serialize(),function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
					top.document.mainIframe.location.reload();
				   jboxClose();
				}
			},null,true);
			});
		}
	}

	function zsScore(obj,bl){
		var val = $(obj).val();
		var name = $(obj).attr("name");
		var num=0;
		if(val && !isNaN(val)){
			num=parseFloat(val)*parseFloat(bl);
		}
		$("input[name='"+name+"_Label']").val(Number(num).toFixed(1));
		totalScore();
	}
	function totalScore(){
		var sum = 0;
		$(".countInput").each(function(){
			var val = this.value;
			if(val && !isNaN(val)){
				sum+=parseFloat(val);
			}
			$("[name='ckkhScore']").val(Number(sum).toFixed(1));
		});
	}

	$(function(){
		totalScore();
	});
</script>
</head>
<body>
<div id="content" style="display: none;"></div>
   <div class="mainright">
      <div class="content">
			<form id="evaluationForm">
				<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 15px;">
					上海市奉贤区中心医院住院医师规范化培训<br/>出科考核内容及评分（临床科室）
				</div>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
				</c:if>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				</c:if>
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE}">
					<input type="hidden" name="managerAuditStatusId" value="${recStatusEnumManagerAuditY.id}"/>
				</c:if>
				<c:set var="verification" value="${(GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag && empty rec.auditStatusId)
											||(GlobalConstant.RES_ROLE_SCOPE_HEAD eq param.roleFlag && not empty rec.auditStatusId && empty rec.headAuditStatusId)
											||(GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE eq param.roleFlag && not empty rec.headAuditStatusId && empty rec.managerAuditStatusId)}"/>
				<label style="margin-bottom: 10px;">
					姓名：
					<c:if test="${verification}">
						<input type="text" class="inputText validate[required]"  name="name" value="${empty formDataMap['name']?doctor.doctorName:formDataMap['name']}"/>
					</c:if>	
					<c:if test="${!verification}">
						${formDataMap['name']}
						<input type="hidden" class="inputText"  name="name" value="${formDataMap['name']}"/>
					</c:if>
				</label>
				<p style="float: right; margin-bottom: 10px;" >届别：
				<c:if test="${verification }">
					<input type="text" class="inputText validate[required]"  name="sessional" value="${formDataMap['sessional']}"/>&#12288;
				</c:if>
				<c:if test="${!(verification) }">
					${formDataMap['sessional']}
				</c:if>					
				&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
					培训专业：
					<c:if test="${verification }">
						<input type="text" class="inputText validate[required]"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
					</c:if>
					<c:if test="${!(verification) }">
						${formDataMap['trainMajor']}
						<input type="hidden" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
					</c:if>
					
				</p>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td>轮转科室名称：</td>
						<td>
							<c:if test="${verification }">
								<input type="text" class="inputText validate[required]"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
							<c:if test="${!(verification) }">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
						<td>轮转时间：</td>
						<td colspan="2">
							<c:if test="${verification }">
								<input type="text" class="inputText validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							<c:if test="${!(verification) }">
								${formDataMap['cycleTimeQ']}
								<input type="hidden" class="inputText" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeQ" value="${formDataMap['cycleTimeQ']}"/>
							</c:if>
							至
							<c:if test="${verification }">
								<input type="text" class="inputText validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="cycleTimeH" value="${formDataMap['cycleTimeH']}"/>
							</c:if>
							<c:if test="${!(verification) }">
								${formDataMap['cycleTimeH']}
								<input type="hidden" class="inputText"  name="trainMajor" value="${formDataMap['trainMajor']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">考核内容</td>
						<td>所占比例</td>
						<td>实际得分</td>
						<td>折算的分</td>
					</tr>
					<tr>
						<td colspan="2">病史书写</td>
						<td>10%</td>
						<td><input name="bssx" type="text" value="${formDataMap['bssx']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" onchange="zsScore(this,'0.1');"/></td>
						<td>
							<input class="inputText ctrlInput countInput" name="bssx_Label" value="${formDataMap['bssx_Label']}" readonly="readonly" style="width:90%;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">医德医风</td>
						<td>15%</td>
						<td><input name="ydyf" type="text" value="${formDataMap['ydyf']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" onchange="zsScore(this,'0.15');"/></td>
						<td>
							<input class="inputText ctrlInput countInput" name="ydyf_Label" value="${formDataMap['ydyf_Label']}" readonly="readonly" style="width:90%;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">出勤情况</td>
						<td>10%</td>
						<td><input name="cqqk" type="text" value="${formDataMap['cqqk']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" onchange="zsScore(this,'0.1');"/></td>
						<td>
							<input class="inputText ctrlInput countInput" name="cqqk_Label" value="${formDataMap['cqqk_Label']}" readonly="readonly" style="width:90%;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">完成轮转计划、登记手册填写</td>
						<td>10%</td>
						<td><input name="lzjh" type="text" value="${formDataMap['lzjh']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" onchange="zsScore(this,'0.1');"/></td>
						<td>
							<input class="inputText ctrlInput countInput" name="lzjh_Label" value="${formDataMap['lzjh_Label']}" readonly="readonly" style="width:90%;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="5">临床综合能力考核</td>
						<td>理论考试</td>
						<td>20%</td>
						<td>
							<input type="text" class="inputText validate[required]"  name="theoreResult" value="${empty formDataMap['theoreResult']?score.theoryScore:formDataMap['theoreResult']}" onchange="zsScore(this,'0.2');" <c:if test="${not empty score.theoryScore}">readonly="readonly"</c:if> style="width:90%;"/>
						</td>
						<td>
							<input class="inputText ctrlInput countInput" name="theoreResult_Label" value="${formDataMap['theoreResult_Label']}" readonly="readonly" style="width:90%;"/>
							<c:if test="${not empty score.theoryScore}">
								<script>
									$("input[name='theoreResult_Label']").val(Number('${score.theoryScore*0.2}').toFixed(1));
									totalScore();
								</script>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>病史采集</td>
						<td>5%</td>
						<td><input name="MedicalHistoryScore" type="text" value="${empty formDataMap['MedicalHistoryScore']?(empty MedicalHistoryScore?0:MedicalHistoryScore):formDataMap['MedicalHistoryScore']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" readonly="readonly" /></td>
						<td>
							<input class="inputText ctrlInput countInput" name="MedicalHistoryScore_Label" value="${formDataMap['MedicalHistoryScore_Label']}" readonly="readonly" style="width:90%;"/>
							<script>
								var psNum=0;
								var ps=$("input[name='MedicalHistoryScore']").val();
								if(ps && !isNaN(ps)){
									psNum=parseFloat(ps);
								}
								$("input[name='MedicalHistoryScore_Label']").val(Number(psNum*0.05).toFixed(1));
								totalScore();
							</script>
						</td>
					</tr>
					<tr>
						<td>体格检查</td>
						<td>5%</td>
						<td><input name="PhysiqueScore" type="text" value="${empty formDataMap['PhysiqueScore']?(empty PhysiqueScore?0:PhysiqueScore):formDataMap['PhysiqueScore']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" readonly="readonly" /></td>
						<td>
							<input class="inputText ctrlInput countInput" name="PhysiqueScore_Label" value="${formDataMap['PhysiqueScore_Label']}" readonly="readonly" style="width:90%;"/>
							<script>
								var psNum=0;
								var ps=$("input[name='PhysiqueScore']").val();
								if(ps && !isNaN(ps)){
									psNum=parseFloat(ps);
								}
								$("input[name='PhysiqueScore_Label']").val(Number(psNum*0.05).toFixed(1));
								totalScore();
							</script>
						</td>
					</tr>
					<tr>
						<td>病例分析</td>
						<td>15%</td>
						<td><input name="CaseAnalysisScore" type="text" value="${empty formDataMap['CaseAnalysisScore']?(empty CaseAnalysisScore?0:CaseAnalysisScore):formDataMap['CaseAnalysisScore']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" readonly="readonly" /></td>
						<td>
							<input class="inputText ctrlInput countInput" name="CaseAnalysisScore_Label" value="${formDataMap['CaseAnalysisScore_Label']}" readonly="readonly" style="width:90%;"/>
							<script>
								var psNum=0;
								var ps=$("input[name='CaseAnalysisScore']").val();
								if(ps && !isNaN(ps)){
									psNum=parseFloat(ps);
								}
								$("input[name='CaseAnalysisScore_Label']").val(Number(psNum*0.15).toFixed(1));
								totalScore();
							</script>
						</td>
					</tr>
					<tr>
						<td>临床操作</td>
						<td>10%</td>
						<td><input name="ClinicalScore" type="text" value="${empty formDataMap['ClinicalScore']?(empty ClinicalScore?0:ClinicalScore):formDataMap['ClinicalScore']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" readonly="readonly" /></td>
						<td>
							<input class="inputText ctrlInput countInput" name="ClinicalScore_Label" value="${formDataMap['ClinicalScore_Label']}" readonly="readonly" style="width:90%;"/>
							<script>
								var psNum=0;
								var ps=$("input[name='ClinicalScore']").val();
								if(ps && !isNaN(ps)){
									psNum=parseFloat(ps);
								}
								$("input[name='ClinicalScore_Label']").val(Number(psNum*0.1).toFixed(1));
								totalScore();
							</script>
						</td>
					</tr>
					<tr>
						<td colspan="3">总分</td>
						<td colspan="2"><input name="ckkhScore" type="text" value="${empty formDataMap['ckkhScore']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%;" readonly="readonly" /></td>
					</tr>
					<tr>
						<td colspan="2">带教老师签名</td>
						<td colspan="3">
							<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
								<label  style="float: left;">${empty formDataMap['teacherName']?sessionScope.currUser.userName:formDataMap['teacherName']}</label>
							</c:if>
							<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER)}">
								<label  style="float: left;">${formDataMap['teacherName']}</label>
							</c:if>
							<input type="hidden" name="teacherName" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER?sessionScope.currUser.userName:formDataMap['teacherName']}">
							<label style="float: right; margin-right: 20px;">
							日期：
							<c:if test="${verification }">
									<input type="text" class="inputText validate[required]" name="teacherDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}"/>
							</c:if>
							<c:if test="${!(verification) }">
								${formDataMap['teacherDate']}
								<input type="hidden" class="inputText" name="teacherDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['teacherDate']?pdfn:getCurrDate():formDataMap['teacherDate']}"/>
							</c:if>
								</label>
						</td>
					</tr>
					<tr>
						<td colspan="2">科主任签名</td>
						<td colspan="3">
							<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
								<label style="float: left;">${empty formDataMap['directorName']?sessionScope.currUser.userName:formDataMap['directorName']}</label>
							</c:if>
							<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
								<label  style="float: left;">${formDataMap['directorName']}</label>
							</c:if>
							<input type="hidden" name="directorName" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD?sessionScope.currUser.userName:formDataMap['directorName']}">

							<label style="float: right; margin-right: 20px;">
							日期：
							<c:if test="${verification }">
								<input type="text" class="inputText validate[required]" name="directorDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}"/>
							</c:if>
							<c:if test="${!(verification) }">
								${formDataMap['directorDate']}
								<input type="hidden" class="inputText" name="directorDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['directorDate']?pdfn:getCurrDate():formDataMap['directorDate']}"/>
							</c:if>
						</label>
						</td>
					</tr>
					<tr>
						<td colspan="2">基地秘书签名</td>
						<td colspan="3">
							<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE}">
								<label style="float: left;">${empty formDataMap['professionalBase']?sessionScope.currUser.userName:formDataMap['professionalBase']}</label>
							</c:if>
							<c:if test="${param.roleFlag ne GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE}">
								<label  style="float: left;">${formDataMap['professionalBase']}</label>
							</c:if>
							<input type="hidden" name="professionalBase" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE?sessionScope.currUser.userName:formDataMap['professionalBase']}">

							<label style="float: right; margin-right: 20px;">
							日期：
							<c:if test="${verification}">
								<input type="text" class="inputText validate[required]" name="baseDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['baseDate']?pdfn:getCurrDate():formDataMap['baseDate']}"/>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['baseDate']}
								<input type="hidden" class="inputText" name="baseDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${empty formDataMap['baseDate']?pdfn:getCurrDate():formDataMap['baseDate']}"/>
							</c:if>
						</label>
						</td>
					</tr>
				</table>
				<table class="basic" width="100%" style="margin-top: 10px;">
				</table>
				</div>
				<%--<c:set var="docSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && empty rec.auditStatusId && empty rec.headAuditStatusId}"/>--%>
				<c:set var="teaSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}"/>
				<c:set var="headSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)}"/>
				<c:set var="baseSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE && empty rec.managerAuditStatusId && (not empty rec.headAuditStatusId)}"/>
				<c:set var="showMsg" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId &&  empty rec.auditStatusId}"/>
				<c:set var="showMsg1" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE && empty rec.headAuditStatusId &&  empty rec.managerAuditStatusId && not empty rec.auditStatusId}"/>
				<p align="center" style="margin-top: 10px;">
					<%--<c:if test="${(param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId) || (param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId)}">--%>
						<%--<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>--%>
					<%--</c:if>--%>
					<c:if test="${showMsg}">
							[<font color="red">带教老师还未审核，请等待！</font>]
					</c:if>
					<c:if test="${showMsg1}">
						[<font color="red">科秘书还未审核，请等待！</font>]
					</c:if>
					<c:if test="${teaSub or headSub or baseSub}">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					</c:if>
					<c:if test="${(GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag or 'manage'eq param.roleFlag) && not empty rec.auditStatusId}">
						<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>
	</div>
</body>
</html>