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
	
	function teachPlan(){
		window.location.href="<s:url value='/res/teacher/teachPlanList'/>";
	}
	function  delFile(fileFlow)
	{
			jboxConfirm("确认删除该文件？",function(){
				jboxPost("<s:url value='/res/teacher/delSkillFile'/>?fileFlow="+fileFlow,null,function(resp){
					if(resp=='${GlobalConstant.DELETE_SUCCESSED}') {
						jboxTip("删除成功");
						$("#"+fileFlow).remove();
					}else{
						jboxTip(resp);
					}
				},null,false);
			},null);
	}


	function  changeFile(obj)
	{
		$("#file").click();
	}

	function uploadFile(obj,processFlow,resultFlow)
	{
		var fileName=$(obj).val();
		if(fileName=="")
		{
			jboxTip("请选择文件！");
			return ;
		}

		var types = "jpg,bmp,png,pdf".split(",");
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
			jboxTip("请上传&nbsp;jpg,bmp,png,pdf格式的文件");
			return ;
		}
		var index=fileName.lastIndexOf("\\")+1
		fileName=fileName.substring(index);

		jboxStartLoading();
		var url = "<s:url value='/res/teacher/saveSkillFile'/>";
		jboxSubmit($(obj).parent(),url,function(resp){
			jboxEndLoading();
			var data=eval("("+resp+")");
			console.log(resp);
			if(data.result=='${GlobalConstant.UPLOAD_SUCCESSED}'){
				jboxTip("${GlobalConstant.UPLOAD_SUCCESSED}");
				$tr=$("<tr></tr>").attr("id",data.fileFlow).appendTo($("#fileList"));
				$td=$("<td>"+data.fileName+"</td>").appendTo($tr);
				$createTime=$("<td>"+data.createTime+"</td>").appendTo($tr);
				$oper=$("<td></td>").appendTo($tr);
				$a1=$("<a style='cursor: pointer;color: #2f8cef;' target='_blank'>查看</a>");
				var url="${sysCfgMap['upload_base_url']}/"+data.filePath;
				$a1.attr("href",url);
				$a1.appendTo($oper);
				$a2=$("<a style='cursor: pointer;color: #2f8cef;'>删除</a>");

				var cl="delFile('"+data.fileFlow+"');"
				$a2.attr("onclick",cl);
				$a2.attr("href",url);
				$a2.appendTo($oper);
				$oper.appendTo($tr);
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
<div class="mainright">
	<div class="content" >
		<form  id="uploadForm" enctype="multipart/form-data" method="post">
			<input type="hidden" name="resultFlow" value="${param.resultFlow}"/>
			<input id="file" type="file" style="display: none;" class="validate[required]" name="file" onchange="uploadFile(this);">
		</form>
		<div style="padding-top: 5px;">
			<c:if test="${empty isAdmin}">
			<font style="float: right;margin-right: 10px;color:red;">文件格式为jpg,bmp,png,pdf等图片</font>
			<table  class="xllist" style="width:100%;">
				<tr>
					<td colspan="99" style="text-align: right;">
						<input type="button" class="searchInput" onclick="changeFile(this)" value="上&#12288;传"/>
					</td>
				</tr>
			</table>
			</c:if>
			<table class="xllist" style="width:100%;max-height: 430px;overflow: auto;" id="fileList">
			<tr>
				<th style="width: 75%;">文件名</th>
				<th style="width: 15%;">上传时间</th>
				<th style="width: 10%;">操作</th>
			</tr>
			<c:forEach items="${list}" var="file">
				<tr id="${file.fileFlow}">

					<td>${file.fileName}</td>
					<td>${pdfn:transDateTime(file.createTime) }</td>
					<td>
						<a style="cursor: pointer;color: #2f8cef;" target="_blank" href="${sysCfgMap['upload_base_url']}/${file.filePath}">查看</a>
						<c:if test="${empty isAdmin}">
							<a style="cursor: pointer;color: #2f8cef;" onclick="delFile('${file.fileFlow}')">删除</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</div>
</body>
</html>