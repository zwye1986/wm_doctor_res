<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:if test="${!param.isLoad}">
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
</c:if>

	<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
	<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
	<c:set var="ckks" value="${open eq GlobalConstant.FLAG_Y}"/>
	<%--<c:set var="orgTestSwitch" value="jswjw_${currRegProcess.orgFlow}_P004"/>--%>
	<%--<c:set var="orgTestSwitch" value="${sysCfgMap[orgTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="docTestSwitch" value="doc_test_switch_${currRegProcess.orgFlow}_${currRegProcess.userFlow}"/>--%>
	<%--<c:set var="docTestSwitch" value="${sysCfgMap[docTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="testTypeFlag" value="${testSwitch && orgTestSwitch && docTestSwitch&&urlEmpt}"/>--%>
	<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>
	<script type="text/javascript">
	$(function(){
		if($('[name="departmentName"]').val()=="" || $('[name="departmentName"]').val()==null ){
			var deptname='${sresult.schDeptName}';
			$('[name="departmentName"]').val(deptname);
		}
		if($('[name="april"]').val()=="" || $('[name="april"]').val()==null ){
			var deptname='${sresult.schMonth}';
			$('[name="april"]').val(deptname);
			
		}
		<c:if test="${param.roleFlag ne GlobalConstant.RES_ROLE_SCOPE_HEAD  and
		param.roleFlag ne GlobalConstant.RES_ROLE_SCOPE_TEACHER }">
			
			$(".ctrlInput").attr("readonly",true);
			$("[type='checkbox']").attr("disabled",true);
			$(".jin").hide();
			$('[name="date"]').removeAttr("onclick");
			$('[name="date"]').attr("readonly",true);
		</c:if>
		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
	
			if($(".theacher").val()!="" && $(".theacher").val()!=null){
				
				$(".ctrlInput").attr("readonly",true);
				$("[type='checkbox']").attr("disabled",true);
				$(".jin").hide();
				$('[name="date"]').removeAttr("onclick");
				$('[name="date"]').attr("readonly",true);
			}
			if($(".theacher").val()=="" || $(".theacher").val()==null){
			
// 				$("textarea").attr("readonly",true);
// 				$("[type='checkbox']").attr("disabled",true);
				$('[name="date"]').removeAttr("onclick");
				$('[name="date"]').attr("readonly",true);
				$('[name="guideTeacher"]').attr("readonly",false);
				var TeacherName=$('[name="guideTeacher"]').val();
				if(TeacherName==""){
					TeacherName='${sessionScope.currUser.userName}';
					$('[name="guideTeacher"]').val(TeacherName);
			};
			}
		</c:if>
		
		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
//			if($(".branchDirector").val()!="" && $(".branchDirector").val()!=null){
//
//				$(".ctrlInput").attr("readonly",true);
//				$("[type='checkbox']").attr("disabled",true);
//				$(".jin").hide();
//				$('[name="date"]').removeAttr("onclick");
//				$('[name="date"]').attr("readonly",true);
//			}
//			if($(".branchDirector").val()=="" || $(".branchDirector").val()==null){
				
				$('.branchDirector').attr("readonly",false);
				var TeacherName=$('[name="branchDirector"]').val();
				if(TeacherName==""){
					TeacherName='${sessionScope.currUser.userName}';
					$('[name="branchDirector"]').val(TeacherName);
				};
//			}
		</c:if>
//		hideInput();
		
		$(".scoreCount").keyup(function(){
			var sum = 0;
			$(".scoreCount").each(function(){
				var val = this.value;
				if(val && !isNaN(val)){
					sum+=parseFloat(val);
				}
				$("#totalScore2").val(sum);
			});
		});
		var s="${outScore.theoryScore}";
	});
	function single(box){
		var curr=box.checked;
		if(curr){
			var name=box.name;
			
			$(":checkbox[name='"+name+"']").attr("checked",false);
		}
		box.checked = curr;
	}
	
	function save(){
		<c:if  test="${testTypeFlag}">
			var theoryAssessment=$('[name="theoryAssessment"]').val();
			if(!theoryAssessment){
				jboxTip("该学员还未参加出科考核！不可审核出科考核表！");
				return false;
			}
		</c:if>
		if($("#evaluationForm").validationEngine("validate")){
			jboxConfirm("确认提交？",function(){
				saveForm();
			},null);
		}
		

	}
	
	function saveForm(){
		var url = "<s:url value='/res/rec/saveRegistryFormNew'/>";
		jboxPost(url, $('#evaluationForm').serialize(),function(resp){
			if("${GlobalConstant.SAVE_SUCCESSED}"==resp){
				window.parent.document.mainIframe.location.reload();
				back();
			}
			
		}, null, true);
	}
	function back(){
		if ("${param.openType}"=="open") {
			jboxClose();
		} if ("${param.openType}"=="messager"){
			top.jboxCloseMessager();
		} else {
			$("#detail").rightSlideClose();
		}
	}
	
	function calculate(){
		 var sum = 0;
		 $(".calculate").each(function(){
			 var val = $(this).val() || 0;
			 if (isNaN(val)){
					val = 0;
				}
			 sum += parseFloat(val);
		 });
		 $("#totalScore2").val(sum);
	}
	
	function hideInput(){
		$(":text[readonly='readonly'],textarea[readonly='readonly']").each(function(){
			var val = this.value;
			$(this).after('<label>'+val+'<input type="hidden" name="'+this.name+'" value="'+val+'"/></label>').remove();
		});
		$(":disabled").each(function(){
			var val = this.value;
			var $parent = $(this).closest("label");
			if(this.checked){
				$parent.after('<label>'+val+'<input type="hidden" name="'+this.name+'" value="'+val+'"/></label>');
			}
			$parent.remove();
		});
		$("[disabled='disabled']:selected").each(function(){
			var val = this.value;
			var $parent = $(this).closest("select");
			$parent.after('<label>'+val+'<input type="hidden" name="'+$parent.attr("name")+'" value="'+val+'"/></label>').remove();
		});
	}
	
	$(function(){
		<%--var $totalScore2 = $('[name="totalScore2"]');--%>
		<%--if($totalScore2.length && !$totalScore2.val()){--%>
			<%--jboxPost("<s:url value='/res/score/getScoreByProcess'/>?processFlow=${param.processFlow}",null,function(resp){--%>
				<%--if(resp){--%>
					<%--$totalScore2.val(resp).attr("readonly",true);--%>
				<%--}--%>
			<%--},null,false);--%>
		<%--}--%>
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
</script>
<style type="text/css">
</style>
</head>
<body>	
   <div class="mainright">
      <div class="content" style="margin-left: -15px;">
        <div class="title1 clearfix" ></div> 
			<form id="evaluationForm">
				<div id="printDiv">
			<label></label>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.userFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
				</c:if>
				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/> 
				</c:if>
				<table class="basic" width="100%" >
					<tr style="display: none; border: red; ">
						<td colspan="5"></td>
					</tr>
					<tr>
						<th colspan="5" style="text-align:center;">
							出 科 考 核 评 分 表
						</th>
					</tr>
					<tr>
						<td style="border-right:0px;text-align:left;" colspan="5">
							<span style="float: left;">轮转科室名称：<input  class="inputText  ctrlInput" name="departmentName" type="text" value="${formDataMap['departmentName']}" style="width: 120px;" readonly="readonly"/></span>
						</td>
					</tr>
					<tr>
						<td style="border-right:0px;text-align:left;" colspan="5">
							<span style="float: left;">轮转时间：${process.schStartDate}至${process.schEndDate}</span>
							<span style="float: right;margin-right: 10px;">共<input  class="inputText  ctrlInput"  name="april" type="text" value="${formDataMap['april']}" style="width: 40px;" readonly="readonly"/>个月</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">考核内容</td>
						<td style="text-align: center;">评分依据</td>
						<td style="text-align: center; width:10%">评分标准</td>
						<td style="text-align: center; width:10%" >实得分</td>
					</tr>
					<tr>
						<td rowspan="4" width="35px;" style="text-align: center;width: 10%;">
							思想政治<br>
							和<br>
							职业道德<br>
							(20分)
						</td>
						<td>工作责任心,无差错</td>
						<td>差错一次扣1分,重大医疗纠纷扣5分</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
							<input  class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="responsibility" type="text" value="${formDataMap['responsibility']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td>服务态度,沟通能力</td>
						<td>有效投诉一次扣2分</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
								<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount"  name="attitude" type="text" value="${formDataMap['attitude']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td>医德医风,廉洁行医</td>
						<td>违规一次扣2分</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
								<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" name="doctor" type="text" value="${formDataMap['doctor']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>	
						<td>团结协作,遵守制度</td>
						<td>违规一次扣1分</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
							<input style="width:90%; " class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" name="unite" type="text" value="${formDataMap['unite']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							考勤<br>
							(5分)
						</td>
						<td>轮转时间和科目</td>
						<td>轮转时间每少1天扣0.2分<br/>
							事假：<input class="inputText ctrlInput validate[required,custom[number],max[31],min[0]]" name="compassionateLeave" type="text" value="${formDataMap['compassionateLeave']}" style="width: 50px;"/>天，
							病假：<input class="inputText ctrlInput validate[required,custom[number],max[31],min[0]]" name="sickLeave" type="text" value="${formDataMap['sickLeave']}" style="width: 50px;"/>天，
							缺勤：<input class="inputText ctrlInput validate[required,custom[number],max[31],min[0]]" name="noReasonLeave" type="text" value="${formDataMap['noReasonLeave']}" style="width: 50px;"/>天
						</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[20],min[0]] scoreCount" name="subject" type="text" value="${formDataMap['subject']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td rowspan="5" style="text-align: center;">
							临<br>
							床<br>
							实<br>
							践<br>
							(50分)
						</td>
						<td>学习病种、掌握的技术操作及操作例数</td>
						<td>核查培训内容登记表及病历，每少1病种扣1分，每少1例技术操作扣0.5分
							（医技科室参照此标准打分）
						</td>
						<td style="text-align: center;">10</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" name="disease" type="text" value="${formDataMap['disease']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td rowspan="2">医疗文书书写质量</td>
						<td>平时文书5分：甲级病历率≥90%，每下降5%扣1分，出现丙级病历扣5分；门诊处方、化验单、各类申请单抽查每发现1份不合格的扣0.5分
						</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" name="quality" type="text" value="${formDataMap['quality']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td>手写病历5分：带教老师批改，按100分折算</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" name="quality2" type="text" value="${formDataMap['quality2']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td>迷你临床演练</td>
						<td>按评分表得分折计</td>
						<td style="text-align: center;">20</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[20],min[0]] scoreCount" name="miniCex" type="text" value="${(empty mini)?'':miniScore}" style="width: 150px;" readonly="readonly"/><br>
						</td>
					</tr>
					<tr>
						<td>临床操作技能考核</td>
						<td>按评分表得分折计</td>
						<td style="text-align: center;">10</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" name="skill" type="text" value="${(empty dops)?'':dopsScore}" style="width: 150px;" readonly="readonly"/><br>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							医学理论<br/>
							(20分)
						</td>
						<td>出科理论考核</td>
						<td>按100分卷折计</td>
						<td style="text-align: center;">20</td>
						<td style="text-align: center;">
							<c:choose>
								<c:when test="${!testTypeFlag}">
									<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[25],min[0]] scoreCount" name="theoryAssessment" type="text" value="${formDataMap['theoryAssessment']}" style="width: 150px;"/><br>
								</c:when>
								<c:when test="${testTypeFlag}">
									<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[25],min[0]] scoreCount" name="theoryAssessment" type="text" value="${empty formDataMap['theoryAssessment'] ? (empty outScore.theoryScore ? '':(outScore.theoryScore.toString()+0)*0.2*10/10):formDataMap['theoryAssessment']}" style="width: 150px;" readonly="readonly"/><br>
								</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							教学能力<br/>
							(5分)
						</td>
						<td>参与科室的教学活动</td>
						<td>指导初级医学生进行医疗学习活动能力</td>
						<td style="text-align: center;">5</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" name="activity" type="text" value="${formDataMap['activity']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							合计
						</td>
						<td colspan="2"></td>
						<td style="text-align: center;">100</td>
						<td>
							<input style="width: 90%; " id="totalScore2" class="inputText" name="totalScore2" type="text" value="${formDataMap['totalScore2']}" readonly="readonly"/><br>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							注
						</td>
						<td colspan="4">上述项目扣分均为扣完为止</td>
					</tr>
					<tr class="otherItem">
						<td colspan="5" style="text-align: left;" >
							出科考核结果：
							<label>
								<input type="checkbox"class="validate[required]" onchange="single(this)" name="isPassed" value="通过" <c:if test="${formDataMap['isPassed']=='通过'}">checked</c:if>/>通过
							</label>
							<label>
								<input type="checkbox"class="validate[required]" onchange="single(this)" name="isPassed" value="未通过" <c:if test="${formDataMap['isPassed']=='未通过'}">checked</c:if>/>未通过
							</label>
						</td>
					</tr>
					<tr>
						<td style="border-right:0px;text-align:left;" colspan="5" >	
							<span >科室意见：（未通过请说明原因）</span>
						</td>
					</tr>
					<tr>
						<td colspan="5" style="height: auto;vertical-align: top;">
							<textarea style="width:99%; border:1px solid #bdbebe;	height:150px;	margin:5px 5px 5px 0px" class="ctrlInput validate[required]"  name="opinion">${formDataMap['opinion']}</textarea>
						</td>
           		    </tr>
           		  	<tr>
           		  		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
							<td style="border-right:0px;text-align:left;" colspan="3" >
								<span >指导老师签名：
								<c:set var="teaFlow" value="${not empty rec.auditUserFlow ?  rec.auditUserFlow : sessionScope.currUser.userFlow}"/>
								${pdfn:getSingnPhoto(teaFlow)}
								<input type="hidden" name="guideTeacher" class="theacher" value="${formDataMap['guideTeacher']}"/>
								<input type="hidden" name="teaFlow" value="${teaFlow}"/>
								</span>
							</td>
						</c:if>
						<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER)}">
							<td style="border-right:0px;text-align:left;" colspan="3" >
								<span >指导老师签名：
									<c:set var="teaFlow" value="${not empty rec.auditUserFlow ?  rec.auditUserFlow : formDataMap['teaFlow']}"/>
									${empty pdfn:getSingnPhoto(teaFlow) ?formDataMap['guideTeacher'] : pdfn:getSingnPhoto(teaFlow)}
									<input type="hidden" name="guideTeacher"  class="theacher" value="${formDataMap['guideTeacher']}"/>
									<input type="hidden" name="teaFlow" value="${teaFlow}"/>
								</span>
							</td>
						</c:if>
						<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
							<td colspan="2" style="text-align:right;">	
								<span style="padding-right: 40px;">科主任签名：
									<c:set var="headFlow" value="${not empty rec.headAuditUserFlow ?  rec.headAuditUserFlow : sessionScope.currUser.userFlow}"/>
									${pdfn:getSingnPhoto(headFlow)}
									<input type="hidden" name="branchDirector" class="branchDirector" value="${formDataMap['branchDirector']}"/>
									<input type="hidden" name="headFlow" value="${headFlow}"/>
								</span>
							</td>
						</c:if>
						<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
							<td colspan="2" style="text-align:right;">	
								<span style="padding-right: 40px;">科主任签名：
									<c:set var="headFlow" value="${not empty rec.headAuditUserFlow ?  rec.headAuditUserFlow : formDataMap['headFlow']}"/>
									${empty pdfn:getSingnPhoto(headFlow) ?formDataMap['branchDirector'] : pdfn:getSingnPhoto(headFlow)}
									<input type="hidden" name="branchDirector" class="branchDirector" value="${formDataMap['branchDirector']}"/>
									<input type="hidden" name="headFlow" value="${headFlow}"/>
								</span>
							</td>
						</c:if>
					</tr>
					<tr>
						<td style="text-align:right;" colspan="5" >	
							<span style="padding-right: 40px;">时间：<input type="text" name="date" value="${empty formDataMap['date']?pdfn:getCurrDate():formDataMap['date']}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputText ctrlInput time" style="width: 120px;" /></span>
						</td>
					</tr>
				</table>
					</div>
			</form>
        <div style="text-align: center;margin-top: 5px;">
        	<!-- <input type="button" value="打印考核表" class="search ctrlInput"/> -->

			<c:set var="dopsmini" value="${(not empty dops)&& (not empty mini)}"/>
			<c:set var="teaSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId}"/>
			<c:set var="headSub" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)}"/>
			<c:set var="showMsg" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.headAuditStatusId &&  empty rec.auditStatusId}"/>

			<c:if test="${!dopsmini}">
			[<font color="red">请先完成临床操作技能评估量化表和迷你临床演练评估量化表</font>]
			</c:if>
			<c:if test="${dopsmini}">
				<c:if test="${showMsg}">
					[<font color="red">带教老师还未审核，请等待！</font>]
				</c:if>
				<c:if test="${teaSub or headSub}">
					<input type="button" value="提&#12288;交" class="search saveBtn  jin" onclick="save();"/>
				</c:if>
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && not empty rec.auditStatusId}">
					<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
				</c:if>
			</c:if>
        	<input type="button" value="关&#12288;闭" class="search ctrlInput" onclick="back();"/>
        </div>
   </div>	
  </div>
</body>
</html>