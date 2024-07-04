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
<style>
	.doctorTypeDiv {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeLabel {
		border: 0px;
		float: left;
		width: 96px;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeContent {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
</style>
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
		location.href = "<s:url value='/res/teacher/inDeptView/${roleFlag}'/>?schDeptFlow="+deptFlow;
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

	//选择日期区间
	function selDateArea(area,num){
		$(".selected").removeClass("selected");
		$(area).addClass("selected");
		$("#dateAreaValue").text(num);
	}
	
	//操作入科
	function openChoose(resultFlow,schDeptFlow){
        if(${sysCfgMap['res_in_by_order_flag'] eq GlobalConstant.FLAG_Y}){
            jboxPost("<s:url value='/res/doc/checkResult'/>?resultFlow="+resultFlow,null,function (resp){
				if(resp){
                    var url="<s:url value='/res/doc/showChoose' />?resultFlow="+resultFlow+"&schDeptFlow="+schDeptFlow+"&roleFlag=${roleFlag}&headUserFlow=${sessionScope.currUser.userFlow}";
                    jboxOpen(url,"选择科主任和带教老师",400,200);
				}else{
				    jboxInfoBasic("当前必须按顺序入科，请检查之前科室是否出科");
				}
            },null,false)
        }else{
            var url="<s:url value='/res/doc/showChoose' />?resultFlow="+resultFlow+"&schDeptFlow="+schDeptFlow+"&roleFlag=${roleFlag}&headUserFlow=${sessionScope.currUser.userFlow}";
            jboxOpen(url,"选择科主任和带教老师",400,200);
        }
	}

	function showMsg(){
		jboxTip("请先联系医院管理员，维护轮转计划所对应的标准科室！");
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
	<div class="content" >
		<div>
			<div>
				<jsp:include page="/res/doc/newNoticeList" flush="true">
					<jsp:param name="fromSch" value="Y"></jsp:param>
				</jsp:include>
			</div>
			<form id="searchForm" action="<s:url value='/res/teacher/inDeptView/${roleFlag}'/>" method="post">
				<input type="hidden" name="currentPage" id="currentPage">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"  <%--onchange="showView();"--%>>
					</div>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<c:set var="docType" value="${type.dictId},"/>
								<label><input type="checkbox" name="datas" value="${type.dictId}" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="qcheckboxDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage1(1);"/>
					</div>

				</div>
			</form>
		</div>
			<table class="xllist" style="margin-top: 10px; width:100%;">
				<tr>
					<th style="width: 7%;">姓名</th>
					<th style="width: 6%;">学员类型</th>
					<th style="width: 5%;">年级</th>
					<th style="width: 5%;">培训年限</th>
					<th style="width: 5%;">培训类别</th>
					<c:if test="${sysCfgMap['sys_index_url'] eq'/inx/jszy'}">
						<th style="width: 5%;">对应专业</th>
					</c:if>
					<c:if test="${sysCfgMap['sys_index_url'] ne'/inx/jszy'}">
						<th style="width: 5%;">培训专业</th>
					</c:if>
					<th style="width: 10%;">轮转科室</th>
					<c:if test="${sysCfgMap['Chinese_Western'] eq GlobalConstant.Chinese}">
						<th style="width: 10%;">跟师时间</th>
					</c:if>
					<th style="width: 10%;">计划轮转时间</th>
					<th style="width: 10%;">备注</th>
					<th style="width: 5%;">操作</th>
				</tr>
				<c:set var="viewDataCount" value="0"/>
				<c:forEach items="${resultList}" var="result">
					<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
					<c:set var="overDay" value="${pdfn:signDaysBetweenTowDate(currDate,result.schStartDate)}"/>
					<%--<c:if test="${overDay>=-30 and not empty result.schStartDate}">--%>
						<c:set var="viewDataCount" value="${viewDataCount+1}"/>
						<c:set var="willInUser" value="${willInUserMap[result.doctorFlow]}"/>
						<c:set var="willInDoctor" value="${willInDoctorMap[result.doctorFlow]}"/>
						<tr class="${!canIn?'cannotIn':''}">
							<td onclick="edit('${willInDoctor.doctorFlow}');" title="<img src='${sysCfgMap['upload_base_url']}/${willInUser.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>" style="cursor:pointer;"><a style="<c:if test="${overDay>0}">color:#2f8cef;</c:if>">${willInDoctor.doctorName}</a></td>
							<td>${willInDoctor.doctorTypeName}</td>
							<td>${willInDoctor.sessionNumber}</td>
							<td>
								<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
									<c:if test="${willInDoctor.trainingYears eq dict.dictId}">
										${dict.dictName}
									</c:if>
								</c:forEach>
							</td>
							<td>${willInDoctor.doctorCategoryName}</td>
							<td>${willInDoctor.trainingSpeName}</td>
							<td>${result.schDeptName}</td>
							<c:if test="${sysCfgMap['Chinese_Western'] eq GlobalConstant.Chinese}">
								<td>${resDiscipleInfoMap[result.doctorFlow].discipleStartDate} ~ ${resDiscipleInfoMap[result.doctorFlow].discipleEndDate}</td>
							</c:if>
							<td>${result.schStartDate} ~ ${result.schEndDate}</td>
							<td>
								<c:if test="${overDay>0}">
									<font color="red">已超过入科日期${overDay}天</font>
								</c:if>
								<c:if test="${!(overDay>0)}">
									还有${-overDay}天入科
								</c:if>
							</td>
							<td>
								<c:if test="${ empty result.standardDeptId}">
									<a onclick="showMsg();" style="color: #2f8cef;cursor: pointer;">
										入科
									</a>
								</c:if>
								<c:if test="${not empty result.standardDeptId}">
									<a onclick="openChoose('${result.resultFlow}','${result.schDeptFlow}');" style="color: #2f8cef;cursor: pointer;">
										入科
									</a>
								</c:if>
							</td>
						</tr>
					<%--</c:if>--%>
				</c:forEach>
				 <c:if test="${empty resultList || viewDataCount==0}">
					<tr><td colspan="99" style="padding-left:10px;"> 暂无待入科学员信息!</td></tr>
				 </c:if>
				 <tbody id="cannotOper">
				 	
				 </tbody>
			</table>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(resultList)}" scope="request"/>
			<pd:pagination toPage="toPage1"/>
		</p>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#cannotOper").append($(".cannotIn"));
	});
</script>
</body>
</html>