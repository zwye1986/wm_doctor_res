<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
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
<style type="text/css">
.ith{height: 40px;line-height: 40px;padding-left: 10px;}
</style>
<script type="text/javascript">
	$(function(){
		if("${empty param.recTypeId}"=="true"){
			$("#reducationTab li:first").click();
		}
		
	});
	function setType(flag){
		$("#recTypeId").val(flag);
		dataChange();
	}
	function setType2(flag){
		$("#recTypeId").val(flag);
		$("#queryTypes").val("1");
		dataChange();
	}
	function dataChange(){
		/* .serialize() */
		jboxStartLoading();
		var form=$("#searchForm");
		form.submit();	
	}
</script>
</head>
<body>
	<div class="main_hd" >
	    <div class="title_tab" style="margin-top: 10px;">
	        <ul id="reducationTab">
					<li style="curosr: pointer" class="${param.recTypeId eq resRecTypeEnumCaseRegistry.id?'tab_select':'tab'}" onclick="setType('${resRecTypeEnumCaseRegistry.id}');"><a>${resRecTypeEnumCaseRegistry.name}</a></li>
					<li style="curosr: pointer" class="${param.recTypeId eq resRecTypeEnumDiseaseRegistry.id?'tab_select':'tab'}" onclick="setType('${resRecTypeEnumDiseaseRegistry.id}');"><a>${resRecTypeEnumDiseaseRegistry.name}</a></li>
					<li style="curosr: pointer" class="${param.recTypeId eq resRecTypeEnumSkillRegistry.id?'tab_select':'tab'}" onclick="setType('${resRecTypeEnumSkillRegistry.id}');"><a>${resRecTypeEnumSkillRegistry.name}</a></li>
					<li style="curosr: pointer" class="${param.recTypeId eq resRecTypeEnumOperationRegistry.id?'tab_select':'tab'}" onclick="setType('${resRecTypeEnumOperationRegistry.id}');"><a>${resRecTypeEnumOperationRegistry.name}</a></li>
					<li style="curosr: pointer" class="${param.recTypeId eq resRecTypeEnumCampaignRegistry.id?'tab_select':'tab'}" onclick="setType2('${resRecTypeEnumCampaignRegistry.id}');"><a>${resRecTypeEnumCampaignRegistry.name}</a></li>
					<li style="curosr: pointer" class="${param.recTypeId eq resRecTypeEnumAfterEvaluation.id?'tab_select':'tab'}" onclick="setType('${resRecTypeEnumAfterEvaluation.id}');"><a>${resRecTypeEnumAfterEvaluation.name}</a></li>
					<li style="curosr: pointer" class="${param.recTypeId eq 'afterFile'?'tab_select':'tab'}" onclick="setType('afterFile');"><a>出科考核表附件</a></li>
					<%-- <li class="${param.recTypeId eq resRecTypeEnumTeacherGrade.id?'tab_select':'tab'}" onclick="setType('${resRecTypeEnumTeacherGrade.id}');"><a>${resRecTypeEnumTeacherGrade.name}</a></li>
					<li class="${param.recTypeId eq resRecTypeEnumDeptGrade.id?'tab_select':'tab'}" onclick="setType('${resRecTypeEnumDeptGrade.id}');"><a>${resRecTypeEnumDeptGrade.name}</a></li> --%>
	        </ul>
	    </div>
	</div>
	<div class="div_search">
		<form id="searchForm" action="<s:url value='/jsres/doctorRecruit/catalogue'/>" method="post">
			<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}">
			<input type="hidden" id="resultFlow" name="resultFlow" value="${param.resultFlow}">
			<input type="hidden" id="queryTypes" name="queryTypes" value="">
			<input type="hidden" id="schDeptFlow" name="schDeptFlow" value="">
		</form>
	</div>
    <div class="search_table" style="overflow-x: auto;height: 380px;">
        	<c:if test="${param.recTypeId==resRecTypeEnumCampaignRegistry.id}">
	        	 <table border="0" cellpadding="0" cellspacing="0" class="grid">
		        	<tr>
		        		<th >时间</th>
		        		<th>活动形式</th>
		        		<th>学时(小时)</th>
		        		<th>主讲人</th>
		        		<th width="200px;">内容</th>
						<th>创建时间</th>
		        	</tr>
		        	<c:forEach items="${resRecList}" var="resRec">
		        		<tr>
			        		<td>${resRecMap[resRec.recFlow]['activity_date']}</td>
			        		<td>${resRecMap[resRec.recFlow]['activity_way']}</td>
			        		<td>${resRecMap[resRec.recFlow]['activity_period']}</td>
			        		<td>${resRecMap[resRec.recFlow]['activity_speaker']}</td>
			        		<td>${resRecMap[resRec.recFlow]['activity_content']}</td>
							<td>${resRecMap[resRec.recFlow]['create_time']}</td>
		        		</tr>
		        	</c:forEach>
		        	<c:if test="${empty resRecList}">
		        		<tr>
		        			<td colspan="6">暂无记录！</td>
		        		</tr>
		        	</c:if>
		        </table>
        	</c:if>
        	<c:if test="${param.recTypeId==resRecTypeEnumCaseRegistry.id}">
	        	 <table border="0" cellpadding="0" cellspacing="0" class="grid">
		        	<tr>
		        		<th>时间</th>
		        		<th>病人姓名</th>
		        		<th>病历号</th>
		        		<th>疾病名称</th>
		        		<th>诊断类型</th>
						<th>创建时间</th>
		        	</tr>
		        	<c:forEach items="${resRecList}" var="resRec">
		        		<tr>
			        		<td>${pdfn:transDate(resRec.operTime)}</td>
			        		<td>${resRecMap[resRec.recFlow]['mr_pName']}</td>
			        		<td>${resRecMap[resRec.recFlow]['mr_no']}</td>
			        		<td>${resRecMap[resRec.recFlow]['disease_pName']}</td>
			        		<td>${resRecMap[resRec.recFlow]['mr_diagType']}</td>
							<td>${pdfn:transDate(resRec.createTime)}</td>
		        		</tr>
		        	</c:forEach>
		        	<c:if test="${empty resRecList}">
		        		<tr>
		        			<td colspan="6">暂无记录！</td>
		        		</tr>
		        	</c:if>
		        </table>
        	</c:if>
        	<c:if test="${param.recTypeId==resRecTypeEnumDiseaseRegistry.id}">
	        	 <table border="0" cellpadding="0" cellspacing="0" class="grid">
		        	<tr>
		        		<th>时间</th>
		        		<th>病种名称</th>
		        		<th>病人姓名</th>
		        		<th>病历号/病理号</th>
		        		<th>诊断类型</th>
		        		<th>是否主管</th>
		        		<th>是否抢救</th>
		        		<th width="200px;">转归情况</th>
						<th>创建时间</th>
		        	</tr>
		        	<c:forEach items="${resRecList}" var="resRec">
		        		<tr>
			        		<td>${resRecMap[resRec.recFlow]['disease_pDate']}</td>
			        		<td>${resRecMap[resRec.recFlow]['disease_diagName']}</td>
			        		<td>${resRecMap[resRec.recFlow]['disease_pName']}</td>
			        		<td>${resRecMap[resRec.recFlow]['disease_mrNo']}</td>
			        		<td>${resRecMap[resRec.recFlow]['disease_diagType']}</td>
			        		<td>${resRecMap[resRec.recFlow]['disease_isCharge']}</td>
			        		<td>${resRecMap[resRec.recFlow]['disease_isRescue']}</td>
			        		<td>${resRecMap[resRec.recFlow]['disease_treatStep']}</td>
							<td>${pdfn:transDate(resRec.createTime)}</td>
		        		</tr>
		        	</c:forEach>
		        	<c:if test="${empty resRecList}">
		        		<tr>
		        			<td colspan="9">暂无记录！</td>
		        		</tr>
		        	</c:if>
		        </table>
        	</c:if>
        	<c:if test="${param.recTypeId==resRecTypeEnumOperationRegistry.id}">
	        	 <table border="0" cellpadding="0" cellspacing="0" class="grid">
		        	<tr>
		        		<th>时间</th>
		        		<th>手术名称</th>
		        		<th>病人姓名</th>
		        		<th>病历号</th>
		        		<th>术中职务</th>
		        		<th width="200px;">备注</th>
						<th>创建时间</th>
		        	</tr>
		        	<c:forEach items="${resRecList}" var="resRec">
		        		<tr>
			        		<td>${resRecMap[resRec.recFlow]['operation_operDate']}</td>
			        		<td>${resRecMap[resRec.recFlow]['operation_operName']}</td>
			        		<td>${resRecMap[resRec.recFlow]['operation_pName']}</td>
			        		<td>${resRecMap[resRec.recFlow]['operation_mrNo']}</td>
			        		<td>${resRecMap[resRec.recFlow]['operation_operRole']}</td>
			        		<td>${resRecMap[resRec.recFlow]['operation_memo']}</td>
							<td>${pdfn:transDate(resRec.createTime)}</td>
		        		</tr>
		        	</c:forEach>
		        	<c:if test="${empty resRecList}">
		        		<tr>
		        			<td colspan="7">暂无记录！</td>
		        		</tr>
		        	</c:if>
		        </table>
        	</c:if>
        	<c:if test="${param.recTypeId==resRecTypeEnumSkillRegistry.id}">
	        	 <table border="0" cellpadding="0" cellspacing="0" class="grid">
		        	<tr>
		        		<th>时间</th>
		        		<th>操作名称</th>
		        		<th>病人姓名</th>
		        		<th>病历号/检查号</th>
		        		<th>是否成功</th>
		        		<th width="200px;">失败原因</th>
						<th>创建时间</th>
		        	</tr>
		        	<c:forEach items="${resRecList}" var="resRec">
		        		<tr>
			        		<td>${resRecMap[resRec.recFlow]['skill_operDate']}</td>
			        		<td>${resRecMap[resRec.recFlow]['skill_operName']}</td>
			        		<td>${resRecMap[resRec.recFlow]['skill_pName']}</td>
			        		<td>${resRecMap[resRec.recFlow]['skill_mrNo']}</td>
			        		<td>${resRecMap[resRec.recFlow]['skill_result']}</td>
			        		<td>${resRecMap[resRec.recFlow]['fail_reason']}</td>
							<td>${pdfn:transDate(resRec.createTime)}</td>
		        		</tr>
		        	</c:forEach>
		        	<c:if test="${empty resRecList}">
		        		<tr>
		        			<td colspan="7">暂无记录！</td>
		        		</tr>
		        	</c:if>
		        </table>
        	</c:if>
        	<c:if test="${param.recTypeId==resRecTypeEnumAfterEvaluation.id}">
        		<c:if test="${not empty resRecList}">
	        			<c:forEach items="${resRecList}" var="rec">
	        				 <div class="main_hd" style="margin-top: -20px;padding-bottom: 15px;">
								<h2>${rec.deptName}</h2>
								<div class="title_tab">
	   						 	</div>
							</div>
	        				<jsp:include page="/jsp/jsres/teacher/evaluation.jsp">
	        					<jsp:param name="noHead" value="true"/>
	        					<jsp:param name="recFlow" value="${rec.recFlow}"/>
	        				</jsp:include>
	        			</c:forEach>
		        </c:if>
		        <c:if test="${empty resRecList}">
	        			<div style="text-align: center;">暂无记录！</div>
		        </c:if>
        	</c:if>
		<c:if test="${param.recTypeId=='afterFile'}">
			<c:if test="${not empty imagelist}">
				<div style="border-bottom: 1px solid #e3e3e3;">
				<c:forEach var="image" items="${imagelist}" varStatus="status">
					<div class="imageOper" style="border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 160px;height: 150px;float: left;text-align: center;" id="${list.imageFlow}">
							<a target="_blank" href="${image.imageUrl}"><img class="imgc" src="${image.thumbUrl}" style="margin-top: -21px;" width="100%" height="100%"/> </a>
					</div>
				</c:forEach>
				</div>
			</c:if>
			<c:if test="${empty imagelist}">
				<div style="text-align: center;">暂无上传的出科考核表！</div>
			</c:if>
		</c:if>
        	<c:if test="${param.recTypeId==resRecTypeEnumTeacherGrade.id||param.recTypeId==resRecTypeEnumDeptGrade.id}">
        		<c:if test="${not empty resRecList}">
				 	<c:forEach items="${titleFormList}" var="title">
				 		<table border="0" cellpadding="0" cellspacing="0" class="grid" style="margin-top: 10px;">
				 		<tr>
							<th style="text-align: left;" colspan="3">&#12288;${title.name}</th>
						</tr>
				 		<c:forEach items="${title.itemList}" var="item">
				 			<tr>
								<td style="text-align: left;"  colspan="3">&#12288;&#12288;${item.name}</td>
							</tr>
							<tr>
								<td style="text-align: left;width: 100px;">&#12288;&#12288;分值：${(assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id)?item.score:9}<input type="hidden" name="assessId" value="${item.id}"/></td>
								<td style="text-align: left;width: ${(assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id)?150:500}px;">
								&#12288;我的打分：
								<c:if test="${assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id}">
									${gradeMap[item.id]['score']}
								</c:if>
								<c:if test="${isRead || rec.statusId eq recStatusEnumSubmit.id}">
									<c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('123',gradeMap[item.id]['score'])>-1)}">
										${gradeMap[item.id]['score']}&nbsp;(有待加强)
									</c:if>
									<c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('456',gradeMap[item.id]['score'])>-1)}">
										${gradeMap[item.id]['score']}&nbsp;(合格)
									</c:if>
									<c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('789',gradeMap[item.id]['score'])>-1)}">
										${gradeMap[item.id]['score']}&nbsp;(优良)
									</c:if>
								</c:if>
									<input id="${item.id}scoreSel" name="score" type="hidden" value="${gradeMap[item.id]['score']}"/>
								</td>
									<td style="text-align: left;">&#12288;扣分原因：${gradeMap[item.id]['lostReason']}</td>
								</tr>
					 	</c:forEach>
					 	</table>
					 </c:forEach>
				<div style="padding-left: 140px;padding-top: 5px;">总分：<input style="width:100px;" type="text" name="totalScore" id="totalScore" class="inputText" value="${empty gradeMap['totalScore']?0:gradeMap['totalScore']}" readonly="readonly"/></div>
        	</c:if>
        	<c:if test="${empty resRecList}">
        		<div style="text-align: center;">暂无记录！</div>
        	</c:if>
        	</c:if>
        	
    </div>
    <div style="text-align: center;"><input type="button" class="btn_green" value="关&#12288;闭" onclick="top.jboxClose();"/></div>
</body>
</html>