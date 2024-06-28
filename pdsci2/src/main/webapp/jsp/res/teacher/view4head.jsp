<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true" />
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

	});
	
	function changeDept(deptFlow){
		location.href = "<s:url value='/res/teacher/showView/${roleFlag}'/>?schDeptFlow="+deptFlow;
	}

	function showView(){
		var startDate = $("input[name='startDate']").val();
		var endDate = $("input[name='endDate']").val();
		if(endDate < startDate){
			jboxTip("结束日期不能早于开始日期！");
			return false;
		}
		if($("input[name='doctorTypeIdList']:checked").length<=0)
		{
			jboxTip("请选择学员类型！");
			return false;
		}
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
		var url="<s:url value='/res/doc/showChoose' />?resultFlow="+resultFlow+"&schDeptFlow="+schDeptFlow+"&roleFlag=${roleFlag}&headUserFlow=${sessionScope.currUser.userFlow}";
		jboxOpen(url,"选择科主任和带教老师",400,200);
	}

    //修改带教
    function openChooseTea(resultFlow,schDeptFlow,teacherFlow){
        var url="<s:url value='/res/teacher/showChooseTea' />?resultFlow="+resultFlow+"&schDeptFlow="+schDeptFlow+"&roleFlag=${roleFlag}&teacherUserFlow="+teacherFlow;
        jboxOpen(url,"选择带教老师",400,100);
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
            // file.value = "";
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
	function calculateDay(){

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
			<form id="searchForm" action="<s:url value='/res/teacher/showView4head'/>" method="post">
				<input type="hidden" name="currentPage" id="currentPage">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">轮转时间：</label>
						<input type="text" class="qtime" name="startDate" value="${param.startDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~ <input type="text" class="qtime" name="endDate" value="${param.endDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qtext" name="doctorName" value="${param.doctorName}" >
					</div>
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select name="trainingSpeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList }" var="dict">
								<option value="${dict.dictId }"<c:if test="${param.trainingSpeId eq dict.dictId  }">selected</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<select name="sessionNumber" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
								<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv" style="text-align: left;">
						<label class="qlable">培训年限：</label>
						<select name="trainingYears"  class="qselect" style="width: 180px;">
							<option></option>
							<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
								<option value="${dict.dictId}" ${param.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<c:if test="${sysCfgMap['sys_index_url']=='/inx/zsey'}">
						<div class="inputDiv">
							<label class="qlable">所在科室：</label>
							<input type="text" class="qtext" name="workDeptName" value="${param.workDeptName}" >
						</div>
					</c:if>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="doctorTypeIdList" value="${type.dictId}"
									${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="qcheckboxDiv" style="text-align: left;margin-left: 0px;">
						<label class="qlable">
							&#12288;&#12288;<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""}>
							轮转中学员
						</label>
						<input type="button" value="查&#12288;询" class="searchInput" onclick="showView();"/>
					</div>
				</div>
			</form>
		</div>
		<div style="padding-top: 5px;">
		<font style="float: right;margin-right: 10px;color:red;margin-top: 55px;">出科考核材料文件格式为${sysCfgMap['res_file_support_suffix']}</font>
			<table class="xllist" style="width:100%;">
			<tr>
				<th style="width: 10%;">姓名</th>
				<th style="width: 5%;">性别</th>
				<th style="width: 5%;">手机</th>
				<th style="width: 5%;">入院时间</th>
				<th style="width: 5%;">培训类别</th>
				<th style="width: 5%;">学员类型</th>
				<c:if test="${sysCfgMap['sys_index_url']=='/inx/zsey'}">
					<th style="width: 10%;">所在科室</th>
				</c:if>
				<th style="width: 5%;">年级</th>
				<th style="width: 5%;">培训年限</th>
				<th style="width: 10%;">培训专业</th>
				<th style="width: 10%;">轮转科室</th>
				<th style="width: 10%;">轮转时间</th>
				<th style="width: 5%;">状态</th>
				<th style="width: 10%;">出科考核材料</th>
				<th style="width: 10%;">修改带教</th>
			</tr>
			<c:forEach items="${resultList}" var="result">
				<c:set var="doctor" value="${doctorMap[result.doctorFlow]}"/>
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
					<td>${result.schDeptName}</td>
					<td>${result.schStartDate} ~ ${result.schEndDate}</td>
					<td>
						<c:set var="process" value="${processMap[result.resultFlow]}"/>
						<c:set var="key" value="${process.processFlow}"/>
						<c:if test="${empty process }">
							未入科
						</c:if>
						<c:if test="${not empty process }">
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
						</c:if>
					</td>
					<td>
						<c:set var="file" value="${fileMap[result.resultFlow]}"></c:set>
						<c:if test="${not empty process}">
							<a id="${process.processFlow}down" style="display: ${empty result.afterFileFlow ? 'none':''};color: blue;" href="<s:url value='/res/teacher/downFile'/>?fileFlow=${result.afterFileFlow}">出科考核材料</a>
							<a id="${process.processFlow}del" style="display: ${empty result.afterFileFlow ? 'none':''}" href="javascript:void(0)" onclick="delAfterFile('${result.resultFlow}','${result.afterFileFlow}','${process.processFlow}');">删除</a>

							<form  id="${process.processFlow}Form" style="display: ${empty result.afterFileFlow ? '':'none'}" enctype="multipart/form-data" method="post">
								<input type="hidden" name="resultFlow" value="${result.resultFlow}"/>
								<a style="color: blue;" href="javascript:void(0)" onclick="changeFile(this)">上传</a>
								<input type="file" style="display: none;" class="validate[required]" name="file" onchange="uploadFile(this,'${process.processFlow}','${result.resultFlow}');">
							</form>
						</c:if>
					</td>
					<td>
						<c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y)}">
							<c:if test="${process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
								<a onclick="openChooseTea('${result.resultFlow}','${result.schDeptFlow}','${processMap[result.resultFlow].teacherUserFlow}');" style="color: #2f8cef;cursor: pointer;">
									编辑
								</a>
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			
			<c:if test="${empty resultList}">
				<tr><td colspan="99">暂无数据！</td></tr>
			</c:if>
		</table>
		</div>
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