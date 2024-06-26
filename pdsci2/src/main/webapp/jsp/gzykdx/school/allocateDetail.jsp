<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	function search(){
		$("#searchForm").submit();
	}
	function releasedInfo(rargetFlow){
		jboxConfirm("是否确认发布？", function(){
			var url = "<s:url value='/gzykdx/school/releasedInfo?rargetFlow='/>"+rargetFlow;
			jboxPost(url, null, function(resp){
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					location.reload();
				}
			});
		});
	}
	function changeTarget(obj,initVal,flag){
		var curVal = $(obj).val();
		if(curVal != "" && !isNaN(curVal) && Number(curVal) > 0){
			var oldVal = initVal==""?"0":initVal;//旧值
			var oldSumObj = $('td',$(obj).closest("tr"))[6];
			var initSum = $(oldSumObj).text().trim();//旧值，合计
			var oldSum = initSum==""?"0":initSum;
			var curSum = accAdd(accSub(parseFloat(oldSum),parseFloat(oldVal)),parseFloat(curVal));
			$(oldSumObj).text(curSum);
			var count = 0;
			if(flag == "xs"){
				$(".xsAll").each(function(){
					var sum = $(this).val().trim()==""?"0":$(this).val().trim();
					count = accAdd(count,parseFloat(sum));
				});
				if(count > ${recTarget.academicNum+0}){
					jboxTip("已超出学术学位招生总指标！");
					$(obj).val(initVal);
					$(oldSumObj).text(initSum);
					return;
				}
			}else{
				$(".zyAll").each(function(){
					var sum = $(this).val().trim()==""?"0":$(this).val().trim();
					count = accAdd(count,parseFloat(sum));
				});
				if(count > ${recTarget.specializedNum+0}){
					jboxTip("已超出专业学位招生总指标！");
					$(obj).val(initVal);
					$(oldSumObj).text(initSum);
					return;
				}
			}
			$("#recordFlow").val($(obj).closest("tr").attr("recordFlow"));
			$("#orgFlow").val($(obj).closest("tr").attr("orgFlow"));
			$("#orgName").val($(obj).closest("tr").attr("orgName"));
			$("#allNum").val(curSum);
			if(flag == "xs"){
				$("#academicNum").val($(obj).val());
				var zyVal = $($('td',$(obj).closest("tr"))[4]).find(":text").eq(0).val();
				$("#specializedNum").val(zyVal);
			}else if(flag == "zy"){
				$("#specializedNum").val($(obj).val());
				var xsVal = $($('td',$(obj).closest("tr"))[2]).find(":text").eq(0).val();
				$("#academicNum").val(xsVal);
			}
			jboxPost("<s:url value='/gzykdx/school/saveOrgTarget'/>",$("#myForm").serialize(),function(resp){
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					$("#searchForm").submit();
				}
			},null,true);
		}
	}
	function changeNum(obj,flag){
		var curVal = $(obj).val();
		if(curVal != "" && !isNaN(curVal) && Number(curVal) != 0){
			var tdArry = $('td',$(obj).closest("tr"));
			var afterNum = 0;
			if(flag == "xs"){
				$("#degreeTypeId").val("Academic");
				afterNum = accAdd($(tdArry[2]).text().trim(),curVal);
				$("#academicNum3").val(afterNum);
				$("#specializedNum3").val($(tdArry[4]).text().trim());
				$("#allNum3").val(accAdd(afterNum,$(tdArry[4]).text().trim()));
				$("#academicNum4").val($(tdArry[2]).text().trim());
				$("#academicModifyNum").val(curVal);
				$("#specializedNum4").val("");//清空，不同学位每次变更一条记录
				$("#specializedModifyNum").val("");
			}else{
				$("#degreeTypeId").val("Profession");
				afterNum = accAdd($(tdArry[4]).text().trim(),curVal);
				$("#specializedNum3").val(afterNum);
				$("#academicNum3").val($(tdArry[2]).text().trim());
				$("#allNum3").val(accAdd(afterNum,$(tdArry[2]).text().trim()));
				$("#specializedNum4").val($(tdArry[4]).text().trim());
				$("#specializedModifyNum").val(curVal);
				$("#academicNum4").val("");
				$("#academicModifyNum").val("");
			}
			$("#orgFlow2").val($(obj).closest("tr").attr("orgFlow"));
			$("#orgFlow3").val($(obj).closest("tr").attr("orgFlow"));
			$("#orgName3").val($(obj).closest("tr").attr("orgName"));
			$(".recordFlow3").val($(obj).closest("tr").attr("recordFlow"));
			if(Number(curVal) < 0){//学术/专业学位追减指标，判断指标是否使用中
				jboxPost("<s:url value='/gzykdx/school/searchUseTarget'/>",$("#myForm2").serialize(),function(resp){
					if(parseFloat(afterNum) < resp){
						jboxTip("已有"+resp+"指标在使用中！");
						return;
					}else{
						searchUseTarget();
					}
				},null,false);
			}else{//学术学位追加指标，判断指标是否超总指标
				var count = curVal;
				if(flag == "xs"){
					$(".xsAll").each(function(){
						var sum = $(this).text().trim()==""?"0":$(this).text().trim();
						count = accAdd(count,parseFloat(sum));
					});
					if(count > ${recTarget.academicNum+0}){
						jboxTip("已超出学术学位招生总指标！");
						return;
					}else{
						searchUseTarget();
					}
				}else{
					$(".zyAll").each(function(){
						var sum = $(this).text().trim()==""?"0":$(this).text().trim();
						count = accAdd(count,parseFloat(sum));
					});
					if(count > ${recTarget.specializedNum+0}){
						jboxTip("已超出专业学位招生总指标！");
						return;
					}else{
						searchUseTarget();
					}
				}
			}
		}
	}
	//保存变更指标
	function searchUseTarget(){
		jboxPost("<s:url value='/gzykdx/school/saveChangeOrgTarget'/>",$("#myForm3").serialize(),function(resp){
			if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
				$("#searchForm").submit();
			}
		},null,true);
	}
	function accAdd(arg1,arg2){
		var r1,r2,m;
		try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
		try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
		m=Math.pow(10,Math.max(r1,r2));
		return (arg1*m+arg2*m)/m;
	}
	function accSub(arg1,arg2){
		var r1,r2,m,n;
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		m=Math.pow(10,Math.max(r1,r2));
		n=(r1>=r2)?r1:r2;
		return ((arg1*m-arg2*m)/m).toFixed(n);
	}
	function goBack(){
		location.href = "<s:url value='/gzykdx/school/recruitTargetPlan'/>";
	}
	function exportInfo(){
		var url = "<s:url value='/gzykdx/schoolAudit/exportAllocateDetail'/>";
		jboxTip("导出中……");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/gzykdx/school/allocateDetail"/>" method="post">
			<input type="hidden" name="rargetFlow" value="${param.rargetFlow}">
			<div class="choseDivNewStyle">
				<span></span>机构名称：
				<select class="select" style="width:137px;" name="orgFlow">
					<option value="">全部</option>
					<c:forEach var="org" items="${applicationScope.sysOrgList}">
						<c:if test="${org.isSecondFlag eq 'Y'}">
							<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
						</c:if>
					</c:forEach>
				</select>
				<span style="padding-left:20px;"></span>
				<input type="button" class="search" value="查&#12288;询" onclick="search()"/>
				<input type="button" class="search" value="发&#12288;布" onclick="releasedInfo('${param.rargetFlow}')"/>
				<input type="button" class="search" value="导&#12288;出" onclick="exportInfo()"/>
				<input type="button" class="search" value="返&#12288;回" onclick="goBack()"/>
				<span style="float:right;">计划招录合计：${recTarget.allNum}</span>
			</div>
		</form>
		<%--初始分配指标参数--%>
		<form id="myForm" method="post">
			<input type="hidden" name="recordFlow" id="recordFlow">
			<input type="hidden" name="rargetFlow" value="${recTarget.rargetFlow}">
			<input type="hidden" name="orgFlow" id="orgFlow">
			<input type="hidden" name="orgName" id="orgName">
			<input type="hidden" name="academicNum" id="academicNum">
			<input type="hidden" name="specializedNum" id="specializedNum">
			<input type="hidden" name="allNum" id="allNum">
		</form>
		<%--已使用指标参数--%>
		<form id="myForm2" method="post">
			<input type="hidden" name="degreeTypeId" id="degreeTypeId">
			<input type="hidden" name="recruitYear" value="${recTarget.recruitYear}">
			<input type="hidden" name="orgFlow" id="orgFlow2">
		</form>
		<%--变更指标参数--%>
		<form id="myForm3" method="post">
			<input type="hidden" name="detail.recordFlow" class="recordFlow3">
			<input type="hidden" name="detail.academicNum" id="academicNum3">
			<input type="hidden" name="detail.specializedNum" id="specializedNum3">
			<input type="hidden" name="detail.allNum" id="allNum3">
			<%--发布之后补增二级机构，再分配指标，新增所需参数--%>
			<input type="hidden" name="detail.rargetFlow" value="${recTarget.rargetFlow}">
			<input type="hidden" name="detail.orgFlow" id="orgFlow3">
			<input type="hidden" name="detail.orgName" id="orgName3">
			<%--指标变更记录--%>
			<input type="hidden" name="log.refRecordFlow" class="recordFlow3">
			<input type="hidden" name="log.academicNum" id="academicNum4">
			<input type="hidden" name="log.academicModifyNum" id="academicModifyNum">
			<input type="hidden" name="log.specializedNum" id="specializedNum4">
			<input type="hidden" name="log.specializedModifyNum" id="specializedModifyNum">
		</form>
		<table class="xllist" style="width:100%;">
			<tr>
				<th style="width:80px;">序号</th>
				<th style="width:120px;">机构名称</th>
				<th style="width:100px;">学术学位</th>
				<th style="width:100px;">指标变更</th>
				<th style="width:100px;">专业学位</th>
				<th style="width:100px;">指标变更</th>
				<th style="width:100px;">合计</th>
			</tr>
			<c:forEach items="${dataList}" var="info" varStatus="i">
				<tr recordFlow="${orgTargetMap[info.orgFlow].recordFlow}" orgFlow="${info.orgFlow}" orgName="${info.orgName}">
					<td>${i.index + 1}</td>
					<td>${info.orgName}</td>
					<td>
						<c:if test="${recTarget.isReport eq 'Y'}">
							<span class="xsAll" style="cursor:pointer;color:#4195c5;" title="<table><tr><th width='80px'>原学术学位</th><th width='80px'>变更指标</th><th width='100px'>变更时间</th></tr>
								<c:forEach items="${detailLogList}" var="log">
									<c:if test="${log.refRecordFlow eq orgTargetMap[info.orgFlow].recordFlow && !empty log.academicNum}">
										<tr><th>${log.academicNum}</th><th>${log.academicModifyNum}</th><th>${pdfn:transDate(log.modifyTime)}</th></tr>
									</c:if>
								</c:forEach></table>">
							${orgTargetMap[info.orgFlow].academicNum}</span>
						</c:if>
						<c:if test="${recTarget.isReport ne 'Y'}">
							<input type='text' style="width:80px;text-align:center;" class="xsAll" onchange="changeTarget(this,'${orgTargetMap[info.orgFlow].academicNum}','xs')" value="${orgTargetMap[info.orgFlow].academicNum}">
						</c:if>
					</td>
					<td>
						<c:if test="${recTarget.isReport eq 'Y'}">
							<input type='text' style="width:80px;text-align:center;" onchange="changeNum(this,'xs')">
						</c:if>
					</td>
					<td>
						<c:if test="${recTarget.isReport eq 'Y'}">
							<span class="zyAll" style="cursor:pointer;color:#4195c5;" title="<table><tr><th width='80px'>原专业学位</th><th width='80px'>变更指标</th><th width='100px'>变更时间</th></tr>
								<c:forEach items="${detailLogList}" var="log">
									<c:if test="${log.refRecordFlow eq orgTargetMap[info.orgFlow].recordFlow && !empty log.specializedNum}">
										<tr><th>${log.specializedNum}</th><th>${log.specializedModifyNum}</th><th>${pdfn:transDate(log.modifyTime)}</th></tr>
									</c:if>
								</c:forEach></table>">
							${orgTargetMap[info.orgFlow].specializedNum}</span>
						</c:if>
						<c:if test="${recTarget.isReport ne 'Y'}">
							<input type='text' style="width:80px;text-align:center;" class="zyAll" onchange="changeTarget(this,'${orgTargetMap[info.orgFlow].specializedNum}','zy')" value="${orgTargetMap[info.orgFlow].specializedNum}">
						</c:if>
					</td>
					<td>
						<c:if test="${recTarget.isReport eq 'Y'}">
							<input type='text' style="width:80px;text-align:center;" onchange="changeNum(this,'zy')">
						</c:if>
					</td>
					<td>
						${orgTargetMap[info.orgFlow].allNum}
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>	
</html>