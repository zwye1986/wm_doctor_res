
<%@include file="/jsp/common/doctype.jsp"%>
<html >
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style type="text/css">
	table.basic th,table.basic td{ text-align: center;padding: 0; }
	.willC:HOVER {background: #eee;}
	.isSelInfo{background: pink;}
	html { overflow-x: scroll;}
</style>

<script type="text/javascript">
	function closes(){
		$('#detail').rightSlideClose(function(){
			$('.isSelInfo').removeClass('isSelInfo');
			window.location.href=window.location.href;
		});
	}
	$(function(){
		$("#detail").slideInit({
			width:800,
			speed:500,
			outClose:function(){
				/* $(".isSelInfo").removeClass("isSelInfo");  */
				$('.isSelInfo').removeClass('isSelInfo');
				window.location.href=window.location.href;
			},
			haveZZ:true
		});
	});
	
	function search(){
		$("#searchForm").submit();
	}
	
	function toPage(page) {
		if(page!=undefined){
			$("#currentPage").val(page);			
		}
		search();
	}
	
	//标签高亮
	function selTag(tag,clazz){
		$(".selectTag").removeClass("selectTag");
		$(tag).addClass("selectTag");
		$(".docInfo").hide();
		 if(clazz=="basicInfo"){
			opstBasicInfo();
		}
		$("#"+clazz).show();
		
	}
	
	//打开编辑
	function openEdit(ctr,flow){
		$('#detail').rightSlideOpen(function(){
			$(".selectTag").click();
		});
		$(".isSelInfo").removeClass("isSelInfo");
		$(ctr).addClass("isSelInfo");
		$("#doctorFlowSun").val(flow);
		jboxPostLoad("assessInfo","<s:url value='/res/platform/basicInfo'/>?doctorFlow="+flow+"&roleFlag=${roleFlag}",null,false); 
	}
	function save(){
			var trs = $('.score');
			var datas =[];
			trs.each(function(){
				var obj={};
				obj.schResultId=$('[name="schResultId"]',this).val();
				obj.theoryScore=$('[name="theoryScore"]',this).val();
				obj.skillScore=$('[name="skillScore" ]',this).val();
				obj.scoreResultId=$('[name="scoreResultId"]',this).val();
				obj.scoreTypeId=$('[name="scoreTypeId"]',this).val();
				obj.scoreTypeName=$('[name="scoreTypeName"]',this).val();
				obj.scorePhaseId=$('[name="scorePhaseId"]',this).val();
				obj.scorePhaseName=$('[name="scorePhaseName"]',this).val();
				obj.scoreFlow=$('[name="scoreFlow"]',this).val();
				obj.doctorFlow=$("#doctorFlowSun").val();
				datas.push(obj);
			});
			var url = "<s:url value='/res/score/saveResScore'/>";
			jboxStartLoading();
			jboxPostJson(url,JSON.stringify(datas),function(){
				var doctorFlow=$("#doctorFlowSun").val();
				var id=doctorFlow+"Tr"
				openEdit($("#"+id),doctorFlow);
			},null,true);
	}
	function opstBasicInfo(){
		var doctorFlow=$("#doctorFlowSun").val();
		jboxPostLoad("basicInfo","<s:url value='/res/platform/opstBasicInfo'/>?doctorFlow="+doctorFlow,null,false); 
	}
	function doctorSave(){
		$('[name="orgName"]').val($("#orgFlow :selected").text());
		$('[name="trainingSpeName"]').val($("#trainingSpeId :selected").text());
			jboxPost("<s:url value='/res/doc/basicInfoing'/>",$("#dassessmentInfo").serialize(),function(resp){

			},null,true);
	}
	function auditing(scoreFlow){
		if(scoreFlow!=null&&scoreFlow!=""){
			jboxConfirm('确定将其审核通过?',function(){
				jboxPost("<s:url value='/res/score/auditing'/>?scoreFlow="+scoreFlow,null,function(resp){

				},null,true);
			});
		}else{
			jboxTip("请先保存！！");
		}
	}
	
</script>
</head>
<body >
	<div class="mainright" style="height: auto;max-height: 100%;">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/platform/stuManaList/${roleFlag}"/>"
				method="post">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">人员类型：</label>
						<select name="doctorCategoryId" class="qselect">
							<option value="all">全部</option>
							<c:forEach items="${recDocCategoryEnumList}" var="category">
								<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
								<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
									<option value="${category.id}" ${doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" name="sysUser.userName" class="qtext" value="${param['sysUser.userName']}"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">专&#12288;&#12288;业：</label>
						<select name="trainingSpeId" class="qselect">
							<option />
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<select name="sessionNumber" class="qselect">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="status">
								<option value="${dict.dictName}" ${(param.sessionNumber eq dict.dictName)?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">学&#12288;&#12288;历：</label>
						<select id="educationId" name="sysUser.educationId" class="qselect">
							<option/>
							<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
								<option value="${dict.dictId}" ${param['sysUser.educationId'] eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<c:if test="${roleFlag==GlobalConstant.USER_LIST_GLOBAL }">
						<div class="inputDiv">
							<label class="qlable">培训基地：</label>
							<select name="orgFlow" class="qselect">
								<option></option>
								<c:forEach items="${applicationScope.sysOrgList}" var="org">
									<option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<div class="inputDiv">
						<label class="qlable">身份证号：</label>
						<input type="text" class="qtext" name="sysUser.idNo" value="${param['sysUser.idNo']}"/>
					</div>
					<div class="lastDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
					</div>
				</div>
			</form>
	 </div>
	 <table class="basic" style="width: 2280px;">
 		<tr>
 			<th rowspan="3" style="min-width: 80px;">姓名</th>
 			<th style="min-width: 80px;" rowspan="3">年级<br>(20**年)</th>
 			<th style="min-width: 50px;"rowspan="3">性别</th>
 			<th style="min-width: 160px;"rowspan="3">学员单位</th>
 			<c:if test="${roleFlag==GlobalConstant.USER_LIST_GLOBAL }">
 			<th style="min-width: 160px;"rowspan="3">培训基地</th>
 			</c:if>
 			<th style="min-width: 160px;"rowspan="3">身份证号码</th>
 			<%--<th style="min-width: 80px;" rowspan="3">类型<br>(西医,中医)</th>--%>
 			<th style="min-width: 80px;" rowspan="3">学历</th>
 			<th style="min-width: 60px;" rowspan="3">培训专业</th>
 			<th style="min-width: 80px;" rowspan="3">培训年限</th>
 			<th style="min-width: 100px;" rowspan="3">是否是执行医师</th>
 			<th style="min-width: 110px;" rowspan="3">手机号码</th>
 			<th style="min-width: 800px;" colspan="12">年度考核</th>
 			<th style="min-width: 280px;" colspan="4">结业考核</th>
 		</tr>
 		<tr>
 			<th style="min-width: 200px;" colspan="4">第一年</th>
 			<th style="min-width: 200px;" colspan="4">第二年</th>
 			<th style="min-width: 200px;" colspan="4">第三年</th>
 			<th rowspan="2">公共科目</th>
 			<th rowspan="2">理论知识</th>
 			<th rowspan="2">实践技能</th>
 			<th rowspan="2">考核结果<br>(是否通过)</th>
 		</tr>                     
 		<tr>
 			<th>过程考核<br/>(是否通过)</th>
 			<th>理论考试</th>
 			<th>技能考试</th>
 			<th>考核结果<br/>(是否通过)</th>
 			<th>过程考核<br/>(是否通过)</th>
 			<th>理论考试</th>
 			<th>技能考试</th>
 			<th>考核结果<br/>(是否通过)</th>
 			<th>过程考核<br/>(是否通过)</th>
 			<th>理论考试</th>
 			<th>技能考试</th>
 			<th>考核结果<br/>(是否通过)</th>
 		</tr>
	 	<tbody>
	 		<c:forEach items="${resDocExtList }" var="resDocExtList">
	 			<tr class="willC" title="点击编辑" style="cursor: pointer;" id="${resDocExtList.doctorFlow}Tr" onclick="openEdit(this,'${resDocExtList.doctorFlow}');">
	 				<c:set var="diyi" value="${resDocExtList.sysUser.userFlow}${resScoreTypeEnumFirstYear.id}"/>
	 				<c:set var="dier" value="${resDocExtList.sysUser.userFlow}${resScoreTypeEnumSecondYear.id}"/>
	 				<c:set var="disan" value="${resDocExtList.sysUser.userFlow}${resScoreTypeEnumThirdYear.id}"/>
	 				<c:set var="disi" value="${resDocExtList.sysUser.userFlow}${resScoreTypeEnumGraduationScore.id}"/>
	 				<td>${resDocExtList.sysUser.userName}</td>
	 				<td>${resDocExtList.sessionNumber}</td>
	 				<td>${resDocExtList.sysUser.sexName}</td>
	 				<td>${resDocExtList.sysUser.orgName}</td>
	 				<c:if test="${roleFlag==GlobalConstant.USER_LIST_GLOBAL }">
	 				<td>${resDocExtList.orgName}</td>
	 				</c:if>
	 				<td>${resDocExtList.sysUser.idNo}</td>
	 			 	<%--<td></td>--%>
	 				<td>${resDocExtList.sysUser.educationName}</td>
	 				<td>${resDocExtList.trainingSpeName}</td>
	 				<td>${resDocExtList.trainingYears}</td>
	 				<td>
		 				<c:if test="${resDocExtList.doctorLicenseFlag eq GlobalConstant.FLAG_Y}">是</c:if>
		 				<c:if test="${resDocExtList.doctorLicenseFlag eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td>${resDocExtList.sysUser.userPhone}</td>
	 				<td>
	 					<c:if test="${map[diyi].schResultId eq GlobalConstant.FLAG_Y}">是</c:if>
	 					<c:if test="${map[diyi].schResultId eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td>${map[diyi].theoryScore}</td>
	 				<td>${map[diyi].skillScore}</td>
	 				<td>
	 					<c:if test="${map[diyi].scoreResultId eq GlobalConstant.FLAG_Y}">是</c:if>
	 					<c:if test="${map[diyi].scoreResultId eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td>
	 					<c:if test="${map[dier].schResultId eq GlobalConstant.FLAG_Y}">是</c:if>
	 					<c:if test="${map[dier].schResultId eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td>${map[dier].theoryScore}</td>
	 				<td>${map[dier].skillScore}</td>
	 				<td>
	 					<c:if test="${map[dier].scoreResultId eq GlobalConstant.FLAG_Y}">是</c:if>
	 					<c:if test="${map[dier].scoreResultId eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td>
	 					<c:if test="${map[disan].schResultId eq GlobalConstant.FLAG_Y}">是</c:if>
	 					<c:if test="${map[disan].schResultId eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td>${map[disan].theoryScore}</td>
	 				<td>${map[disan].skillScore}</td>
	 				<td>
	 					<c:if test="${map[disan].scoreResultId eq GlobalConstant.FLAG_Y}">是</c:if>
	 					<c:if test="${map[disan].scoreResultId eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td>
	 					<c:if test="${map[disi].schResultId eq GlobalConstant.FLAG_Y}">是</c:if>
	 					<c:if test="${map[disi].schResultId eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td>${map[disi].theoryScore}</td>
	 				<td>${map[disi].skillScore}</td>
	 				<td>
	 					<c:if test="${map[disi].scoreResultId eq GlobalConstant.FLAG_Y}">是</c:if>
	 					<c:if test="${map[disi].scoreResultId eq GlobalConstant.FLAG_N}">否</c:if>
	 				</td>
	 				<td></td>
	 			</tr>
	 		</c:forEach>
	 		<c:if test="${empty resDocExtList}">
	    			<tr><td colspan="99">无记录</td></tr>
	  		  	</c:if>
	 	</tbody>
	 </table>
 	 <c:set var="pageView" value="${pdfn:getPageView(resDocExtList)}" scope="request"></c:set> 
	<pd:pagination toPage="toPage"/>
	 </div>
	 <div id="detail">
		<div style="width: 100%;height: 100%;padding: 10px;background: white;">
			<ul id="tags">
	      			<li onclick="selTag(this,'assessInfo');" class="selectTag"><a style="cursor: pointer;">考核信息</a></li>
	      			<c:if test="${roleFlag==GlobalConstant.USER_LIST_LOCAL }"> 
	      			<li onclick="selTag(this,'basicInfo');" class=""><a style="cursor: pointer;" >基本信息</a></li>
	      			</c:if>
	        </ul>
	        	<input type="hidden" id="doctorFlowSun" name="doctorFlow" value=""/>
	        	<div id="assessInfo" class="docInfo"></div>
			    <div id="basicInfo" class="docInfo"></div>    	
		</div>
	 </div>
	 </div>
</body>
</html>