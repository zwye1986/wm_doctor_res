<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>

<script type="text/javascript">
	function scroll(){
		setTimeout(function(){
			$(".list li:first").animate({marginTop : "-25px"},500,function(){
				$(".list").append($(this).css({marginTop : "0px"}));
				scroll();
			});
		},3000);
	}
	$(function(){
		if($(".list li").length>1){
			scroll();
		}
		
		//选择科室
// 		$("#selDeptImg,.tips_select").on("mouseenter mouseleave",function(){
// 			$(".tips_select").toggle();
// 		});
		
	});
	
	function changeDept(deptFlow){
		location.href = "<s:url value='/res/teacher/showView/${roleFlag}'/>?schDeptFlow="+deptFlow;
	}

	function showView(){
			$("#searchForm").submit();
		}

	function toPage1(page) {
		if (page) {
			$("#currentPage").val(page);
		}
		showView();
	}

	function goAudit(doctorName){
		$("#doctorName").val(doctorName);
		$("#doctorForm").submit();
		$("#dateAreaValue").val();
	}
	function edit(flow){
		var url="<s:url value='/res/teacher/showDocAndUser'/>?flow="+flow;
		jboxOpen(url, "信息", 1000, 500);
	}
	function afterAudit(rotationFlow,userFlow,processFlow){
		location.href="<s:url value='/res/rec/showRegistryForm'/>?roleFlag=${roleFlag}&recTypeId=${resRecTypeEnumAfterSummary.id}&rotationFlow="+rotationFlow+"&schDeptFlow=${schDeptFlow}&operUserFlow="+userFlow+"&processFlow="+processFlow+"&isView=true";
	}
	
<%--// 	function evaluation(recFlow,schDeptFlow, rotationFlow, userFlow, schResultFlow){--%>
<%--// 		var url = "<s:url value='/res/rec/showRegistryForm'/>"+--%>
<%--// 				"?schDeptFlow="+schDeptFlow+--%>
<%--// 				"&rotationFlow="+rotationFlow+--%>
<%--// 				"&recTypeId=${resRecTypeEnumAfterEvaluation.id}&userFlow="+userFlow+--%>
<%--// 				"&roleFlag=${roleFlag}&openType=open"+--%>
<%--// 				"&resultFlow="+schResultFlow+--%>
<%--// 				"&recFlow="+recFlow;--%>
<%--// 		jboxOpen(url, "思想政治和工作态度", 1000, 500);--%>
<%--// 	}--%>
	
	//选择日期区间
	function selDateArea(area,num){
		$(".selected").removeClass("selected");
		$(area).addClass("selected");
		$("#dateAreaValue").text(num);
	}
	
	//操作入科
	function openChoose(resultFlow,schDeptFlow){
		var url="<s:url value='/res/doc/showChoose' />?resultFlow="+resultFlow+"&schDeptFlow="+schDeptFlow+"&roleFlag=${roleFlag}&headUserFlow=${sessionScope.currUser.userFlow}";
		jboxOpen(url,"选择科主任和带教老师",400,200);
	}
	
	function teachPlan(){
		window.location.href="<s:url value='/res/teacher/teachPlanList'/>";
	}
	
	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}

	function  delAfterFile(resultFlow,fileFlow,processFlow)
	{
			jboxConfirm("确认删除该科室出科考核材料？",function(){
				jboxPost("<s:url value='/res/teacher/delAfterFile'/>?resultFlow="+resultFlow+"&fileFlow="+fileFlow,null,function(resp){
					if(resp=='${GlobalConstant.DELETE_SUCCESSED}') {
						jboxTip("删除成功");
						$("#"+processFlow+"down").hide();
						$("#"+processFlow+"del").hide();
						$("#"+processFlow+"Form").show();
					}else{
						jboxTip(resp);
					}
				},null,false);
			},null);
	}


	function  changeFile(obj)
	{
		var fileObj=$(obj).next();
		$(fileObj).click();
	}

	function uploadFile(obj,processFlow,resultFlow)
	{
		var fileName=$(obj).val();
		if(fileName=="")
		{
			jboxTip("请选择文件！");
			return ;
		}

		var types = "${sysCfgMap['res_file_support_suffix']}".split(",");
		var regStr = "/";
		for (var i = 0; i < types.length; i++) {
			if (types[i]) {
				if (i == (types.length - 1)) {
					regStr = regStr + "\\" + types[i] + '$';
				} else {
					regStr = regStr + "\\" + types[i] + '$|';
				}
			}
		}
		regStr = regStr + '/i';
		regStr = eval(regStr);
		if ($.trim(fileName) != "" && !regStr.test(fileName)) {
			file.value = "";
			jboxTip("请上传&nbsp;${sysCfgMap['res_file_support_suffix']}格式的文件");
			return ;
		}
		var index=fileName.lastIndexOf("\\")+1
		fileName=fileName.substring(index);

		jboxStartLoading();
		var url = "<s:url value='/res/teacher/saveAfterFile'/>";
		jboxSubmit($(obj).parent(),url,function(resp){
			jboxEndLoading();
			var data=eval("("+resp+")");
			if(data.result=='${GlobalConstant.UPLOAD_SUCCESSED}'){
				jboxTip("${GlobalConstant.UPLOAD_SUCCESSED}");
				var url="<s:url value='/res/teacher/downFile'/>?fileFlow="+data.fileFlow;
				$("#"+processFlow+"down").attr("href",url);
				$("#"+processFlow+"down").show();
				$("#"+processFlow+"down").html("出科考核材料");
				var cl="delAfterFile('"+resultFlow+"','"+data.fileFlow+"','"+processFlow+"');"
				$("#"+processFlow+"del").attr("onclick",cl);
				$("#"+processFlow+"del").show();
				$("#"+processFlow+"Form").hide();
			}else{
				jboxInfo("${GlobalConstant.UPLOAD_FAIL}");
			}
		},function(){
			jboxEndLoading();
			jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
		},false);
	}

</script>
</head>
<body>
<form id="doctorForm" action="<s:url value='/res/teacher/auditListContent'/>" method="post">
	<input id="doctorName" type="hidden" name="doctorName" />
	<input type="hidden" name="roleFlag" value="${roleFlag}" />
</form>
<div class="mainright">
<!--     <div class="tips"> -->
<!-- 		    <h1 class="tips_title">科室信息</h1> -->
<!-- 			<div class="tips_body"> -->
<%-- 				<div class="office" ><span id="selDeptImg"><strong>当前科室选择<img src="<s:url value='/css/skin/${skinPath}/images/down_red.png'/>"  border="0"  style="margin-top:0px;margin-left: 5px; vertical-align:middle;"></strong><br/>${schDeptName}</span></div> --%>
<!-- 				<div class="tips_select" style="display:none;"> -->
<%-- 				<c:forEach items="${schDeptList}" var="dept"> --%>
<%-- 					<p onclick="changeDept('${dept.schDeptFlow}');">${dept.schDeptName}</p> --%>
<%-- 				</c:forEach> --%>
<!-- 			</div> -->
				
<!-- 				 <div class="scroll_body"> -->
<!-- 				<dl class="count"> -->
<%-- 				<dt>当前轮转人数<span>${doctorList.size()+0}</span></dt> --%>
<%-- 					<c:forEach items="${categoryNum}" var="docCategory"> --%>
<%-- 						<dd>${docCategory.key}<span>${docCategory.value+0}</span></dd> --%>
<%-- 					</c:forEach> --%>
<!-- 				<dt><span></span></dt> -->
<%-- 				<dd style="text-indent:0;">预出科人数<span>${afterMap.size()+0}</span></dd> --%>
<%-- 				<dd style="text-indent:0;">预计下月入科人数<span>${willInDoctorList.size()+0}</span></dd> --%>
<!-- 				</dl> -->
<!-- 			  </div> -->
			  
<!-- 			  <h2 class="has"> -->
<!-- 				<span>已出科人数</span> -->
<!-- 				<dl> -->
<!-- 				  <dt class="dateArea"><a class="selected" onclick="selDateArea(this,10);">月</a><a onclick="selDateArea(this,20);">季</a><a onclick="selDateArea(this,30);">年</a></dt> -->
<!-- 				  <dd id="dateAreaValue">10</dd> -->
<!-- 				</dl> -->
<!-- 				</h2> -->
			  
				
<!-- 			</div> -->
<!-- 			<div class="tips_bottom_bg"></div> -->
<!--     </div> -->
	<div class="content" >
		<div>
			<div>
				<jsp:include page="/res/doc/newNoticeList">
					<jsp:param name="fromSch" value="Y"></jsp:param>
				</jsp:include>
			</div>
<!-- 			<div style="float: right;"> -->
<%-- 				<div class="office" style=""><span id="selDeptImg"><strong>当前科室选择<img src="<s:url value='/css/skin/${skinPath}/images/down_red.png'/>"  border="0"  style="margin-top:0px;margin-left: 5px; vertical-align:middle;"></strong><br/>${schDeptName}</span></div> --%>
<!-- 				<div class="tips_select" style="display:none;top: 40px;"> -->
<%-- 					<c:forEach items="${schDeptList}" var="dept"> --%>
<%-- 						<p onclick="changeDept('${dept.schDeptFlow}');">${dept.schDeptName}</p> --%>
<%-- 					</c:forEach> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
			<form id="searchForm" action="<s:url value='/res/teacher/showView/${roleFlag}'/>" method="post">
				<input type="hidden" name="currentPage" id="currentPage">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"  <%--onchange="showView();"--%>>
					</div>
					<div class="qcheckboxDiv">
						&#12288;<label class="qlable">
							<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""}<%-- onchange="showView();"--%>>
							轮转中学员
						</label>
						<input type="button" value="查&#12288;询" class="searchInput" onclick="showView();"/>
					</div>

				</div>
				<%--<table class="basic" style="margin-top: 10px; width:700px;">--%>
					<%--<tr>--%>
						<%--<td>--%>
							<%--<input type="hidden" name="currentPage" id="currentPage">--%>
							<%--姓名：--%>
							<%--<input class="xltext" name="doctorName" value="${param.doctorName}" type="text" onchange="showView();">--%>
							<%--&#12288;&#12288;--%>
							<%--<label><input type="checkbox" name="isCurrentFlag" value="Y" ${param.isCurrentFlag eq 'Y'?'checked':''} onchange="showView();">--%>
							<%--轮转中学员--%>
							<%--</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
				<%--</table>--%>
				<%--<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">--%>
					<%--<font style="float: right;margin-right: 10px;"><img src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>" style="margin-top:-2px;"/> 待审核</font>--%>
					<%--<font style="float: right;margin-right: 10px;color:green;/*font-weight: bold;*/">已完成</font>--%>
				<%--</c:if>--%>
			</form>
			</c:if>
		</div>
		
<!-- 		<table id="links" class="basic" style="margin-top: 10px; "> -->
<%-- 			<tr><td  width="80px">快捷操作：</td><td style="text-align: center;cursor: pointer;width: 100px;" title="教学安排" onclick="teachPlan();"><img src="<s:url value='/css/skin/${skinPath}/images/icon_2.png'/>"/></td> --%>
<!-- 			<td style="text-align: center;cursor: pointer;width: 100px;">请假审批</td></tr> -->
<!-- 		</table> -->
		<div style="padding-top: 5px;">
			<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
				<font style="float: right;margin-right: 10px;"><img src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>" style="margin-top:-2px;"/> 待审核</font>
				<font style="float: right;margin-right: 10px;color:green;/*font-weight: bold;*/">已完成</font>
			</c:if>
			<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
				<font style="float: right;margin-right: 10px;color:red;/*font-weight: bold;*/">出科考核材料文件格式为${sysCfgMap['res_file_support_suffix']}</font>
			</c:if>
			<table class="xllist" style="/*margin-top: 10px;*/ width:100%;">
			<tr>
				<th style="width: 8%;">姓名</th>
				<th style="width: 5%;">性别</th>
				<th style="width: 10%;">手机</th>
				<th style="width: 8%;">入院时间</th>
				<th style="width: 5%;">培训类别</th>
				<th style="width: 5%;">学员类型</th>
				<c:if test="${sysCfgMap['sys_index_url']=='/inx/zsey'}">
					<th style="width: 5%;">所在科室</th>
				</c:if>
				<th style="width: 5%;">年级</th>
				<th style="width: 5%;">培训年限</th>
				<th style="width: 5%;">培训专业</th>
				<th style="width: 5%;">轮转科室</th>
				<th style="width: 15%;">计划轮转时间</th>
				<th style="width: 5%;">入科时间</th>
<!-- 			<th>培训进度</th> -->
			<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
				<th style="width: 10%;">培训数据审核</th>
			</c:if>
			<th style="width: 5%;">状态</th>
<!-- 			<th>出科审核</th> -->
<!-- 			<th width="100px">带教老师</th> -->
<!-- 			<th width="100px">科主任</th> -->
				<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					<th style="width: 10%;">出科考核材料</th>
				</c:if>
			</tr>
<%-- 			<c:forEach items="${doctorList}" var="doctor"> --%>
			<c:forEach items="${processList}" var="process">
				<c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
				<tr>
					<td onclick="edit('${doctor.doctorFlow}');" title="<img src='${sysCfgMap['upload_base_url']}/${userMap[doctor.doctorFlow].userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>"><a style="cursor:pointer;color: #2f8cef">${doctor.doctorName}</a></td>
					<td>${userMap[doctor.doctorFlow].sexName}</td>
					<td>${userMap[doctor.doctorFlow].userPhone}</td>
					<td>${doctor.inHosDate}</td>
					<td>${doctor.doctorCategoryName}</td>
					<td>${doctor.doctorTypeName}</td>
					<c:if test="${sysCfgMap['sys_index_url']=='/inx/zsey'}">
						<td>

							<c:if test="${doctor.doctorTypeId eq 'Company'}">
									${doctor.departMentName}
							</c:if>
							<c:if test="${doctor.doctorTypeId eq 'Social'}">
								--
							</c:if>
							<c:if test="${doctor.doctorTypeId ne 'Company' and doctor.doctorTypeId ne 'Social' }">
									${doctor.workOrgName}
							</c:if>
						</td>
					</c:if>
					<td>${doctor.sessionNumber}</td>
					<td>
						<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
							 <c:if test="${doctor.trainingYears eq dict.dictId}">
								 ${dict.dictName}
							 </c:if>
						</c:forEach>
					</td>
					<td>${doctor.trainingSpeName}</td>
					<td>${process.schDeptName}</td>
					<td>${process.schStartDate} ~ ${process.schEndDate}</td>
					<td>${process.startDate}</td>
<!-- 					<td> -->
<%-- 						${finishPreMap[doctor.doctorFlow]+0}% --%>
<%-- 						<c:if test="${(waitAuditMap[doctor.doctorFlow]+waitAuditAppealMap[doctor.doctorFlow])+0>0 && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
<%-- 							<img title="去审核" src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>" style="margin-top:-5px;cursor: pointer;" onclick="goAudit('${doctor.doctorName}');"/> --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
					<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
						<td>
							<c:if test="${(waitAuditMap[doctor.doctorFlow]+waitAuditAppealMap[doctor.doctorFlow])+0>0}">
								<img title="去审核" src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>" style="margin-left:-18px;margin-top:-5px;cursor: pointer;" onclick="goAudit('${doctor.doctorName}');"/>
							</c:if>
							<a style="cursor: pointer;color: #2f8cef;" onclick="goAudit('${doctor.doctorName}');">
								审核
							</a>
						</td>
					</c:if>
					<td>
						<c:set var="key" value="${process.processFlow}"/>
						<c:if test="${process.schFlag eq GlobalConstant.FLAG_Y}">
							已出科
						</c:if>
						<c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y)}">
							<c:if test="${process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
								<c:if test="${!empty afterMap[key] || !empty recMap[key]}">
									待出科
								</c:if>
								<c:if test="${!(!empty afterMap[key] || !empty recMap[key])}">
									轮转中
								</c:if>
							</c:if>
							<c:if test="${process.isCurrentFlag ne GlobalConstant.FLAG_Y}">
								待入科
							</c:if>
						</c:if>
					</td>
					<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
						<td>
							<c:set var="result" value="${resultMap[process.processFlow]}"></c:set>
							<c:set var="file" value="${fileMap[process.processFlow]}"></c:set>
							<c:if test="${not empty result}">
								<a id="${process.processFlow}down" style="display: ${empty result.afterFileFlow ? 'none':''}" href="<s:url value='/res/teacher/downFile'/>?fileFlow=${result.afterFileFlow}">出科考核材料</a>
								<a id="${process.processFlow}del" style="display: ${empty result.afterFileFlow ? 'none':''}" href="javascript:void(0)" onclick="delAfterFile('${result.resultFlow}','${result.afterFileFlow}','${process.processFlow}');">删除</a>

								<form  id="${process.processFlow}Form" style="display: ${empty result.afterFileFlow ? '':'none'}" enctype="multipart/form-data" method="post">
									<input type="hidden" name="resultFlow" value="${result.resultFlow}"/>
									<a href="javascript:void(0)" onclick="changeFile(this)">上传</a>
									<input type="file" style="display: none;" class="validate[required]" name="file" onchange="uploadFile(this,'${process.processFlow}','${result.resultFlow}');">
								</form>
							</c:if>
						</td>
					</c:if>
<!-- 					<td> -->
<%-- 						<c:set var="afterKey" value="res_${afterRecTypeEnumAfterEvaluation.id}_form_flag"/> --%>
<%-- 						<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[afterKey]}"> --%>
<%-- 							<c:set var="color" value="blue"/> --%>
<%-- 	  						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag && !empty recMap[doctor.doctorFlow].auditStatusId}"> --%>
<%-- 	  							<c:set var="color" value="black"/> --%>
<%-- 	  						</c:if> --%>
<%-- 	  						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq roleFlag && !empty recMap[doctor.doctorFlow].headAuditStatusId}"> --%>
<%-- 	  							<c:set var="color" value="black"/> --%>
<%-- 	  						</c:if> --%>
<%-- 	  						<c:if test="${GlobalConstant.RES_ROLE_SCOPE_MANAGER eq param.roleFlag && !empty recMap[doctor.doctorFlow].headAuditStatusId}"> --%>
<%-- 	  							<c:set var="color" value="black"/> --%>
<%-- 	  						</c:if> --%>
<%-- 	  						${param.roleFlag}[<a style="color: ${color};" href="javascript:void(0)" onclick="evaluation('${recMap[doctor.doctorFlow].recFlow}','${processMap[doctor.doctorFlow].schDeptFlow}','${doctor.rotationFlow}','${processMap[doctor.doctorFlow].userFlow}','${processMap[doctor.doctorFlow].schResultFlow}');">出科考核表</a>] --%>
<%-- 	  					</c:if> --%>
	  					
<%-- 	  					<c:set var="summaryKey" value="res_${afterRecTypeEnumAfterSummary.id}_form_flag"/> --%>
<%-- 						<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[summaryKey]}"> --%>
<!-- 							[<a -->
<!-- 							style="cursor: pointer;color: blue;"  -->
<%-- 							onclick="afterAudit('${doctor.rotationFlow}','${doctor.doctorFlow}','${processMap[doctor.doctorFlow].processFlow}');" --%>
<!--  							> -->
<!-- 								出科小结 -->
<!-- 							</a>] -->
<%-- 						</c:if> --%>
<!-- 					</td> -->
<!-- 					<td> -->
<%-- 						<c:if test="${afterMap[doctor.doctorFlow].auditStatusId eq recStatusEnumTeacherAuditY.id}"> --%>
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
<!-- 							<a  -->
<!-- 							style="cursor: pointer;color: blue;"  -->
<%-- 							onclick="afterAudit('${doctor.rotationFlow}','${doctor.doctorFlow}','${processMap[doctor.doctorFlow].processFlow}');" --%>
<!-- 							> -->
<%-- 							</c:if> --%>
<!-- 							审核通过 -->
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
<!-- 							</a> -->
<%-- 							</c:if> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${afterMap[doctor.doctorFlow].auditStatusId eq recStatusEnumTeacherAuditN.id}"> --%>
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
<!-- 							<a  -->
<!-- 							style="cursor: pointer;color: blue;"  -->
<%-- 							onclick="afterAudit('${doctor.rotationFlow}','${doctor.doctorFlow}','${processMap[doctor.doctorFlow].processFlow}');" --%>
<!-- 							> -->
<%-- 							</c:if> --%>
<!-- 							审核不通过 -->
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
<!-- 							</a> -->
<%-- 							</c:if> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${empty afterMap[doctor.doctorFlow].auditStatusId && !empty afterMap[doctor.doctorFlow]}"> --%>
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
<!-- 							<a  -->
<!-- 							style="cursor: pointer;color: blue;"  -->
<%-- 							onclick="afterAudit('${doctor.rotationFlow}','${doctor.doctorFlow}','${processMap[doctor.doctorFlow].processFlow}');" --%>
<!-- 							> -->
<%-- 							</c:if> --%>
<!-- 							未审核 -->
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
<!-- 							</a> -->
<%-- 							</c:if> --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
<!-- 					<td> -->
<%-- 						<c:if test="${afterMap[doctor.doctorFlow].headAuditStatusId eq recStatusEnumHeadAuditY.id}"> --%>
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}"> --%>
<!-- 							<a  -->
<!-- 							style="cursor: pointer;color: blue;"  -->
<%-- 							onclick="afterAudit('${doctor.rotationFlow}','${doctor.doctorFlow}','${processMap[doctor.doctorFlow].processFlow}');" --%>
<!-- 							> -->
<%-- 							</c:if> --%>
<!-- 							审核通过 -->
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}"> --%>
<!-- 							</a> -->
<%-- 							</c:if> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${afterMap[doctor.doctorFlow].headAuditStatusId eq recStatusEnumHeadAuditN.id}"> --%>
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}"> --%>
<!-- 							<a  -->
<!-- 							style="cursor: pointer;color: blue;"  -->
<%-- 							onclick="afterAudit('${doctor.rotationFlow}','${doctor.doctorFlow}','${processMap[doctor.doctorFlow].processFlow}');" --%>
<!-- 							> -->
<%-- 							</c:if> --%>
<!-- 							审核不通过 -->
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}"> --%>
<!-- 							</a> -->
<%-- 							</c:if> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${empty afterMap[doctor.doctorFlow].headAuditStatusId && !empty afterMap[doctor.doctorFlow]}"> --%>
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}"> --%>
<!-- 							<a  -->
<!-- 							style="cursor: pointer;color: blue;"  -->
<%-- 							onclick="afterAudit('${doctor.rotationFlow}','${doctor.doctorFlow}','${processMap[doctor.doctorFlow].processFlow}');" --%>
<!-- 							> -->
<%-- 							</c:if> --%>
<!-- 							未审核 -->
<%-- 							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}"> --%>
<!-- 							</a> -->
<%-- 							</c:if> --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
				</tr>
			</c:forEach>
			
			<c:if test="${empty processList}">
				<tr><td colspan="99">没有医师在当前科室轮转！</td></tr>
			</c:if>
		</table>
		</div>
		<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"/>
			<pd:pagination toPage="toPage1"/>
		</p>
		</c:if>
		<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
<!-- 		<table class="basic" style="margin-top: 10px; width:100%;"> -->
<!-- 			<tr><th style="text-align:left;padding-left: 10px; font-size:14px; color:#fea527;font-weight:normal;"> 带教老师审核情况</th></tr> -->
<!-- 			<tr><td style="padding-left:0;"> -->
<!-- 			 <div style="margin:0 1%;"> -->
<%-- 			 <c:forEach items="${teacherFlows}" var="teacherFlow"> --%>
<!-- 				  <div class="card_tec"> -->
<!-- 				    <span class="card_inner"> -->
<%-- 				    <h1><c:out value="${auditMap[teacherFlow].teacherName}" default="${notAuditMap[teacherFlow].teacherName}"/><span class="card_score">评分：6分</span></h1> --%>
<%-- 				    <h2>已审核：${auditMap[teacherFlow].auditCount}&nbsp;&nbsp;&nbsp;未审核：${notAuditMap[teacherFlow].auditCount}</h2> --%>
<!-- 				    </span> -->
<!-- 				  </div> -->
<%-- 			 </c:forEach> --%>
<%-- 			 <c:if test="${empty teacherFlows}"> --%>
<!-- 			 	暂无带教老师审核信息! -->
<%-- 			 </c:if> --%>
<!-- 			  </div> -->
<!-- 			</td></tr> -->
<!-- 		</table> -->
		
<%-- 		<c:if test="${!empty willInResult}"> --%>
			
			<table class="xllist" style="margin-top: 10px; width:100%;">
				<tr>
					<th colspan="99" style="text-align:left;padding-left: 10px; font-size:14px;font-weight:normal;font-weight: bold;">
						待入科学员信息
						<%--<font style="float: right;margin-left: 10px;font-weight: normal;">Tip：<font color="red">红色</font>表示已超过入科时间却未入科的学员！不可操作入科的学员为未出科的学员！</font>--%>
					</th>
				</tr>
				<tr>
					<th style="width: 10%;">姓名</th>
					<th style="width: 5%;">性别</th>
					<th style="width: 5%;">手机</th>
					<th style="width: 5%;">入院时间</th>
					<th style="width: 5%;">培训类别</th>
					<th style="width: 5%;">学员类型</th>
					<c:if test="${sysCfgMap['sys_index_url']=='/inx/zsey'}">
						<th style="width: 5%;">所在科室</th>
					</c:if>
					<th style="width: 5%;">年级</th>
					<th style="width: 5%;">培训年限</th>
					<th style="width: 5%;">培训专业</th>
					<th style="width: 10%;">轮转科室</th>
					<th style="width: 15%;">计划轮转时间</th>
					<th style="width: 15%;">备注</th>
					<th style="width: 5%;">操作</th>
				</tr>
				<c:set var="viewDataCount" value="0"/>
				<c:forEach items="${willInResult}" var="result">
					<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
					<c:set var="overDay" value="${pdfn:signDaysBetweenTowDate(currDate,result.schStartDate)}"/>
					<c:if test="${overDay>=-30 and not empty result.schStartDate}">
						<c:set var="viewDataCount" value="${viewDataCount+1}"/>
						<c:set var="willInUser" value="${willInUserMap[result.doctorFlow]}"/>
						<c:set var="willInDoctor" value="${willInDoctorMap[result.doctorFlow]}"/>
						<%--<c:set var="isRotationProcess" value="${isRotationProcessMap[result.doctorFlow]}"/>--%>
						<%--<c:set var="canIn" value="${empty isRotationProcess}"/>--%>
						<tr class="${!canIn?'cannotIn':''}">
							<td onclick="edit('${willInDoctor.doctorFlow}');" title="<img src='${sysCfgMap['upload_base_url']}/${willInUser.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>" style="cursor:pointer;"><a style="<c:if test="${overDay>0}">color:#2f8cef;</c:if>">${willInDoctor.doctorName}</a></td>
							<td>${willInUser.sexName}</td>
							<td>${willInUser.userPhone}</td>
							<td>${willInDoctor.inHosDate}</td>
							<td>${willInDoctor.doctorCategoryName}</td>
							<td>${willInDoctor.doctorTypeName}</td>
							<c:if test="${sysCfgMap['sys_index_url']=='/inx/zsey'}">
								<td>

									<c:if test="${willInDoctor.doctorTypeId eq 'Company'}">
										${willInDoctor.departMentName}
									</c:if>
									<c:if test="${willInDoctor.doctorTypeId eq 'Social'}">
										--
									</c:if>
									<c:if test="${willInDoctor.doctorTypeId ne 'Company' and willInDoctor.doctorTypeId ne 'Social' }">
										${willInDoctor.workOrgName}
									</c:if>
								</td>
							</c:if>
							<td>${willInDoctor.sessionNumber}</td>
							<td>
								<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
									<c:if test="${willInDoctor.trainingYears eq dict.dictId}">
										${dict.dictName}
									</c:if>
								</c:forEach>
							</td>
							<td>${willInDoctor.trainingSpeName}</td>
							<td>${result.schDeptName}</td>
							<td>${result.schStartDate} ~ ${result.schEndDate}</td>
							<td>
								<c:if test="${overDay>0}">
									已超过入科日期${overDay}天
								</c:if>
								<c:if test="${!(overDay>0)}">
									还有${-overDay}天入科
								</c:if>
							</td>
							<td>
								<%--<c:if test="${canIn}">--%>
								<%--</c:if>--%>
									<a onclick="openChoose('${result.resultFlow}','${result.schDeptFlow}');" style="color: #2f8cef;cursor: pointer;">
										入科
									</a>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				 <c:if test="${empty willInResult || viewDataCount==0}">
					<tr><td colspan="99" style="padding-left:10px;"> 暂无待入科学员信息!</td></tr>
				 </c:if>
				 <tbody id="cannotOper">
				 	
				 </tbody>
			</table>
<%-- 		</c:if> --%>
		</c:if>
		<script type="text/javascript">
		//拖动demo
// 			function moveE(d){
// 				var e = window.event;
// 				$(d).css({
// 					position:"absolute",
// 					top:$(d).offset().top+"px",
// 					left:$(d).offset().left+"px",
// 					zIndex:10000
// 				}).mouseup(function(){
// 					$(document).off("mousemove");
// 				});
// 				var topM = $(d).offset().top-e.pageY;
// 				var leftM = $(d).offset().left-e.pageX;
// 				$(document).mousemove(function(ev){
// 					$(d).css({
// 						top:ev.pageY+topM+"px",
// 						left:ev.pageX+leftM+"px"
// 					});
// 				});
// 			}
		</script>
<!-- 		<div style="width: 100px;height: 100px;background: red;" onmousedown="moveE(this);"></div> -->
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#cannotOper").append($(".cannotIn"));
	});
</script>
</body>
</html>