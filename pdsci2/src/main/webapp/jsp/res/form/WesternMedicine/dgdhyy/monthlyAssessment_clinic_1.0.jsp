<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<%--<c:if test="${!param.isLoad}">--%>
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
<%--</c:if>--%>
	<%--<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>--%>
	<%--<c:set var="orgTestSwitch" value="jswjw_${currRegProcess.orgFlow}_P004"/>--%>
	<%--<c:set var="orgTestSwitch" value="${sysCfgMap[orgTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="docTestSwitch" value="doc_test_switch_${currRegProcess.orgFlow}_${currRegProcess.userFlow}"/>--%>
	<%--<c:set var="docTestSwitch" value="${sysCfgMap[docTestSwitch] eq GlobalConstant.FLAG_Y}"/>--%>
	<%--<c:set var="testTypeFlag" value="${testSwitch && orgTestSwitch && docTestSwitch&&urlEmpt}"/>--%>
<script type="text/javascript">
	$(function(){
		if($('[name="departmentName"]').val()=="" || $('[name="departmentName"]').val()==null ){
			var deptname='${sresult.schDeptName}';
			$('[name="departmentName"]').val(deptname);
		}
		if($('[name="doctorName"]').val()=="" || $('[name="doctorName"]').val()==null ){
			var doctorName='${sresult.doctorName}';
			$('[name="doctorName"]').val(doctorName);
		}
		if($('[name="schTime"]').val()=="" || $('[name="schTime"]').val()==null ){
			var schTime='${sresult.schStartDate}'+' 至 '+'${sresult.schEndDate}';
			$('[name="schTime"]').val(schTime);
		}

		$(".scoreCount").keyup(function(){
			var sum = 0;
			$(".scoreCount").each(function(){
				var val = this.value;
				if(val && !isNaN(val)){
					sum+=parseFloat(val);
				}
				$("[name='part06']").val(sum);
			});
		});

		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
		$(".ctrlInput").attr("readonly",true);
		$(".jin").hide();
		$('[name="date"]').removeAttr("onclick");
		$('[name="date"]').attr("readonly",true);
		</c:if>

		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
		if($(".theacher").val()!="" && $(".theacher").val()!=null){
			$(".ctrlInput").attr("readonly",true);
			$(".jin").hide();
			$('[name="date"]').removeAttr("onclick");
			$('[name="date"]').attr("readonly",true);
		}
		if($(".theacher").val()=="" || $(".theacher").val()==null){
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
		if($(".branchDirector").val()!="" && $(".branchDirector").val()!=null){
			$(".ctrlInput").attr("readonly",true);
			$(".jin").hide();
			$('[name="date"]').removeAttr("onclick");
			$('[name="date"]').attr("readonly",true);
		}
		if($(".branchDirector").val()=="" || $(".branchDirector").val()==null){
			$('.branchDirector').attr("readonly",false);
			var TeacherName=$('[name="branchDirector"]').val();
			if(TeacherName==""){
				TeacherName='${sessionScope.currUser.userName}';
				$('[name="branchDirector"]').val(TeacherName);
			};
		}
		</c:if>
		hideInput();
	})

	function save(){
		if($("#monthlyAssessmentForm").validationEngine("validate")){
			jboxConfirm("确认提交？提交后将无法修改！",function(){
				saveForm();
			},null);
		}
	}

	function saveForm(){
		var url = "<s:url value='/res/rec/saveRegistryFormNew'/>";
		jboxPost(url, $('#monthlyAssessmentForm').serialize(),function(resp){
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
			<form id="monthlyAssessmentForm">
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
					<tr style="display: none; border: red; height: ">
						<td colspan="5"></td>
					</tr>
					<tr>
						<th colspan="5" style="text-align:center;">
							住院/全科医师规范化培训月度考核评分表<br>（门诊或医技）
						</th>
					</tr>
					<tr>
						<td style="border-right:0px;text-align:left;" colspan="5">
							<span style="float: left;width: 17%">姓名：<input  class="inputText ctrlInput" name="doctorName" type="text" value="${formDataMap['doctorName']}" style="width: 120px;"/></span>
							<span style="float: left;width: 22%">轮转科室：<input  class="inputText ctrlInput" name="departmentName" type="text" value="${formDataMap['departmentName']}" style="width: 120px;" readonly="readonly"/></span>
							<span style="float: left;width: 40%">轮转时间：<input  class="inputText ctrlInput" name="schTime" type="text" value="${formDataMap['schTime']}" style="width: 180px;"/></span>
							<span style="float: left;width: 20%">指导老师：<input  class="inputText ctrlInput" name="teacherName" type="text" value="${formDataMap['guideTeacher']}" style="width: 120px;"/></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">考核内容</td>
						<td style="text-align: center; width:15%">赋分标准</td>
						<td style="text-align: center;">评分标准</td>
						<td style="text-align: center; width:15%" >得分</td>
					</tr>
					<tr>
						<td rowspan="2" style="text-align: center;width: 6%;">
							医<br>
							德
						</td>
						<td>工作责任心、医德医风</td>
						<td rowspan="2">10分</td>
						<td>差错或有效投诉一次扣3分，重大医疗纠纷扣10分</td>
						<td rowspan="2" style="text-align: center;">
							<input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td>团结协作、遵守制度</td>
						<td>遵守医院、科室相关制度，能按上级医师的要求认真工作</td>
					</tr>
					<tr>
						<td rowspan="3" style="text-align: center;">
							临<br>
							床<br>
							实<br>
							践
						</td>
						<td>轮转时间</td>
						<td>30分</td>
						<td>在轮转科室全勤满分，除按手续请的病、事假外，累计缺一工作日扣5分</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[30],min[0]] scoreCount" name="part01" type="text" value="${formDataMap['part01']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td>医技科室操作或门诊跟诊</td>
						<td>20分</td>
						<td>门诊或医技科室轮转的病例数≥200例</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[20],min[0]] scoreCount" name="part02" type="text" value="${formDataMap['part02']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td>门诊试诊或出具诊断报告</td>
						<td>20分</td>
						<td>各科按大纲要求，未达标不给分</td>
						<td style="text-align: center;">
							<input style="width:90%; " class="inputText ctrlInput validate[required,custom[number],max[20],min[0]] scoreCount" name="part03" type="text" value="${formDataMap['part03']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td rowspan="2" style="text-align: center;">
							考<br>
							核
						</td>
						<td>出科技能考核</td>
						<td>10分</td>
						<td>按100分卷折计（闭卷考试）</td>
						<td style="text-align: center;">
							<%--<c:choose>--%>
								<%--<c:when test="${!testTypeFlag}">--%>
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" name="part04" type="text" value="${formDataMap['part04']}" style="width: 150px;"/>
								<%--<br>--%>
								<%--</c:when>--%>
								<%--<c:when test="${testTypeFlag}">--%>
									<%--<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[25],min[0]] scoreCount" name="part04" type="text" value="${empty formDataMap['part04'] ? (empty outScore.theoryScore ? '':outScore.theoryScore*0.25):formDataMap['part04']}" style="width: 150px;" readonly="readonly"/><br>--%>
								<%--</c:when>--%>
							<%--</c:choose>--%>
						</td>
					</tr>
					<tr>
						<td>出科理论考核</td>
						<td>10分</td>
						<td>按100分卷折计（闭卷考试）</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" name="part05" type="text" value="${formDataMap['part05']}" style="width: 150px;"/><br>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: left">总得分</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number]]" name="part06" type="text" value="${formDataMap['part06']}" disabled style="width: 150px;"/><br>
						</td>
					</tr>
           		  	<tr >
						<td colspan="5">
							<div style="float:left;width: 33%">教学秘书：<input class=" inputText "  name="" type="text" value="" readonly="readonly"  style="width: 120px;"/></div>
						<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
							<div style="float:left;width: 33%">指导老师签名：
								<c:set var="teaFlow" value="${not empty rec.auditUserFlow ?  rec.auditUserFlow : sessionScope.currUser.userFlow}"/>
									${pdfn:getSingnPhoto(teaFlow)}
								<input type="hidden" name="guideTeacher" class="theacher" value="${formDataMap['guideTeacher']}"/>
								<input type="hidden" name="teaFlow" value="${teaFlow}"/>
							</div>
							</c:if>
						<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER)}">
							<div style="float:left;width: 33%">指导老师签名：
								<c:set var="teaFlow" value="${not empty rec.auditUserFlow ?  rec.auditUserFlow : formDataMap['teaFlow']}"/>
									${empty pdfn:getSingnPhoto(teaFlow) ?formDataMap['guideTeacher'] : pdfn:getSingnPhoto(teaFlow)}
								<input type="hidden" name="guideTeacher" class="theacher" value="${formDataMap['guideTeacher']}"/>
								<input type="hidden" name="teaFlow" value="${teaFlow}"/></div>
						</c:if>
						<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
							<div style="float:left;width: 33%">科主任签名：
								<c:set var="headFlow" value="${not empty rec.headAuditUserFlow ?  rec.headAuditUserFlow : sessionScope.currUser.userFlow}"/>
									${pdfn:getSingnPhoto(headFlow)}
								<input type="hidden" name="branchDirector" class="branchDirector" value="${formDataMap['branchDirector']}"/>
								<input type="hidden" name="headFlow" value="${headFlow}"/>
							</div>
						</c:if>
						<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD)}">
							<div style="float:left;width: 33%">科主任签名：
								<c:set var="headFlow" value="${not empty rec.headAuditUserFlow ?  rec.headAuditUserFlow : formDataMap['headFlow']}"/>
									${empty pdfn:getSingnPhoto(headFlow) ?formDataMap['branchDirector'] : pdfn:getSingnPhoto(headFlow)}
								<input type="hidden" name="branchDirector" class="branchDirector" value="${formDataMap['branchDirector']}"/>
								<input type="hidden" name="headFlow" value="${headFlow}"/>
							</div>
						</c:if>
						</td>
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
        	<input type="button" value="提&#12288;交" class="search saveBtn jin" onclick="save();"/>
			<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && not empty rec.auditStatusId}">
				<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
			</c:if>
        	<input type="button" value="关&#12288;闭" class="search ctrlInput" onclick="back();"/>
        </div>
   </form>
  </div>
</body>
</html>