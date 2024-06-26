<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script>
	function showEval(itemFlow,evalFlow)
	{
		var msg="查看评价";
		var url = "<s:url value='/res/deptActivityStatistics/showEvalAll'/>?itemFlow="+itemFlow+"&evalFlow="+evalFlow ;
		if(evalFlow=="All")
		{
			jboxOpen(url,msg,900,500,true);
		}else{
			if(evalFlow=="")
			{
				msg="评价";
			}
			url = "<s:url value='/res/deptActivityStatistics/showEval'/>?itemFlow="+itemFlow+"&evalFlow="+evalFlow ;
			jboxOpen(url,msg,900,500,true);
		}
	}
	function  delFile(itemFlow)
	{
		jboxConfirm("确认删除该科室附件？",function(){
			jboxPost("<s:url value='/res/deptActivityStatistics/delFile'/>?fileFlow="+itemFlow,null,function(resp){
				if(resp=='${GlobalConstant.DELETE_SUCCESSED}') {
					jboxTip("删除成功");
					$("#"+itemFlow+"down").hide();
					$("#"+itemFlow+"del").hide();
					$("#"+itemFlow+"Form").show();
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

	function uploadFile(obj,itemFlow)
	{
		var fileName=$(obj).val();
		if(fileName=="")
		{
			jboxTip("请选择文件！");
			return ;
		}
		var types = "${sysCfgMap['sys_file_support_suffix']}".split(",");
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
			jboxTip("请上传&nbsp;${sysCfgMap['sys_file_support_suffix']}格式的文件");
			return ;
		}
		var index=fileName.lastIndexOf("\\")+1
		fileName=fileName.substring(index);
		jboxStartLoading();
		var url = "<s:url value='/res/deptActivityStatistics/saveFile'/>";
		jboxSubmit($(obj).parent(),url,function(resp){
			jboxEndLoading();
			var data=eval("("+resp+")");
			if(data.result=='${GlobalConstant.UPLOAD_SUCCESSED}'){
				jboxTip("${GlobalConstant.UPLOAD_SUCCESSED}");
				var url="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow="+data.fileFlow;
				$("#"+itemFlow+"down").attr("href",url);
				$("#"+itemFlow+"down").show();
				$("#"+itemFlow+"down").html("附件");
				var cl="delFile('"+itemFlow+"');"
				$("#"+itemFlow+"del").attr("onclick",cl);
				$("#"+itemFlow+"del").show();
				$("#"+itemFlow+"Form").hide();
			}else{
				jboxInfo("${GlobalConstant.UPLOAD_FAIL}");
			}
		},function(){
			jboxEndLoading();
			jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
		},false);
	}
</script>
	<c:if test="${param.itemTypeId eq deptActivityItemTypeEnumJXCFAP.id
	or param.itemTypeId eq deptActivityItemTypeEnumBLTLAP.id
	or param.itemTypeId eq deptActivityItemTypeEnumXJKAP.id
	or param.itemTypeId eq deptActivityItemTypeEnumQTHD.id
	or param.itemTypeId eq deptActivityItemTypeEnumDSBGHAP.id }">
			<table class="basic list " style="width: 100%;">
				<tbody>
				<tr>
					<th>科室</th>
					<th>月度</th>
					<th>日期</th>
					<th>主持人</th>
					<th>工号</th>
					<th>内容</th>
					<th>地点</th>
					<th>操作</th>
				</tr>
				</tbody>
				<tbody >
				<c:if test="${not empty list}" >
					<c:forEach items="${list}" var="b" varStatus="s">
						<tr>
							<td>${b.deptName}
							</td>
							<td>${b.planDate}
							</td>
							<td>${b.planTime}
							</td>
							<td>${b.joinUserName}
							</td>
							<td>
								${b.userCode}
							</td>
							<td>${b.content}</td>
							<td>${b.address}</td>
							<td>
								<c:if test="${role ne 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','All')">查看评价</a>
								</c:if>
								<c:if test="${role eq 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<c:if test="${not empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','${b.evalFlow}')">查看</a>
									</c:if>
									<c:if test="${empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','')">评价</a>
									</c:if>
								</c:if>
								<c:set var="file" value="${fileMap[b.itemFlow]}"></c:set>
								<c:if test="${role eq 'head'}">
									<a id="${b.itemFlow}down" style="display: ${empty file ? 'none':''};color: blue;" href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
									<a id="${b.itemFlow}del" style="display: ${empty file ? 'none':''}" href="javascript:void(0)" onclick="delFile('${b.itemFlow}');">删除</a>
									<form  id="${b.itemFlow}Form" style="display: ${empty file ? '':'none'}" enctype="multipart/form-data" method="post">
										<input type="hidden" name="itemFlow" value="${b.itemFlow}"/>
										<a style="color: blue;" href="javascript:void(0)" onclick="changeFile(this)">上传</a>
										<input type="file" style="display: none;" class="validate[required]" name="file" onchange="uploadFile(this,'${b.itemFlow}');">
									</form>
								</c:if>
								<c:if test="${role ne 'head' and not empty file}">
									<a href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list}" >
					<tr>
						<td colspan="9">暂无数据</td>
					</tr>
				</c:if>
				</tbody>
			</table>
	</c:if>
	<c:if test="${param.itemTypeId eq deptActivityItemTypeEnumJTBKAP.id}">
		<div class="A4">
			<table class="basic list " style="width: 100%;">
				<tbody>
				<tr>
					<th>科室</th>
					<th>月度</th>
					<th>日期</th>
					<th>主持人</th>
					<th>工号</th>
					<th>内容</th>
					<th>操作</th>
				</tr>
				</tbody>
				<tbody>
				<c:if test="${not empty list}" >
					<c:forEach items="${list}" var="b" varStatus="s">
						<tr>
							<td>${b.deptName}
							</td>
							<td>${b.planDate}
							</td>
							<td>
								${b.planTime}
							</td>
							<td>${b.joinUserName}
							</td>
							<td>${b.userCode}
							</td>
							<td>${b.content}</td>
							<td>
								<c:if test="${role ne 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','All')">查看评价</a>
								</c:if>
								<c:if test="${role eq 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<c:if test="${not empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','${b.evalFlow}')">查看</a>
									</c:if>
									<c:if test="${empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','')">评价</a>
									</c:if>
								</c:if>
								<c:set var="file" value="${fileMap[b.itemFlow]}"></c:set>
								<c:if test="${role eq 'head'}">
									<a id="${b.itemFlow}down" style="display: ${empty file ? 'none':''};color: blue;" href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
									<a id="${b.itemFlow}del" style="display: ${empty file ? 'none':''}" href="javascript:void(0)" onclick="delFile('${b.itemFlow}');">删除</a>
									<form  id="${b.itemFlow}Form" style="display: ${empty file ? '':'none'}" enctype="multipart/form-data" method="post">
										<input type="hidden" name="itemFlow" value="${b.itemFlow}"/>
										<a style="color: blue;" href="javascript:void(0)" onclick="changeFile(this)">上传</a>
										<input type="file" style="display: none;" class="validate[required]" name="file" onchange="uploadFile(this,'${b.itemFlow}');">
									</form>
								</c:if>
								<c:if test="${role ne 'head' and not empty file}">
									<a href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list}" >
					<tr>
						<td colspan="8">暂无数据</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${param.itemTypeId eq deptActivityItemTypeEnumTKAP.id}">
		<div class="A4">
			<table class="basic list " style="width: 100%;">
				<tbody>
				<tr>
					<th>科室</th>
					<th>月度</th>
					<th>日期</th>
					<th>听课人</th>
					<th>工号</th>
					<th>主讲人</th>
					<th>授课题目</th>
					<th>操作</th>
				</tr>
				</tbody>
				<tbody>
				<c:if test="${not empty list}" >
					<c:forEach items="${list}" var="b" varStatus="s">
						<tr>
							<td>${b.deptName}
							</td>
							<td>${b.planDate}
							</td>
							<td>
									${b.planTime}
							</td>
							<td>
									${b.joinUserName}
							</td>
							<td>
									${b.userCode}
							</td>
							<td>${b.content}</td>
							<td>${b.title}</td>
							<td>
								<c:if test="${role ne 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','All')">查看评价</a>
								</c:if>
								<c:if test="${role eq 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<c:if test="${not empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','${b.evalFlow}')">查看</a>
									</c:if>
									<c:if test="${empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','')">评价</a>
									</c:if>
								</c:if>
								<c:set var="file" value="${fileMap[b.itemFlow]}"></c:set>
								<c:if test="${role eq 'head'}">
									<a id="${b.itemFlow}down" style="display: ${empty file ? 'none':''};color: blue;" href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
									<a id="${b.itemFlow}del" style="display: ${empty file ? 'none':''}" href="javascript:void(0)" onclick="delFile('${b.itemFlow}');">删除</a>
									<form  id="${b.itemFlow}Form" style="display: ${empty file ? '':'none'}" enctype="multipart/form-data" method="post">
										<input type="hidden" name="itemFlow" value="${b.itemFlow}"/>
										<a style="color: blue;" href="javascript:void(0)" onclick="changeFile(this)">上传</a>
										<input type="file" style="display: none;" class="validate[required]" name="file" onchange="uploadFile(this,'${b.itemFlow}');">
									</form>
								</c:if>
								<c:if test="${role ne 'head' and not empty file}">
									<a href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list}" >
					<tr>
						<td colspan="6">暂无数据</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${param.itemTypeId eq deptActivityItemTypeEnumDDAP.id}">
		<div class="A4">
			<table class="basic list " style="width: 100%;">
				<tbody>
				<tr>
					<th>科室</th>
					<th>月度</th>
					<th>日期</th>
					<th>督导内容</th>
					<th>督导人</th>
					<th>操作</th>
				</tr>
				</tbody>
				<tbody>
				<c:if test="${not empty list}" >
					<c:forEach items="${list}" var="b" varStatus="s">
						<tr>
							<td>${b.deptName}
							</td>
							<td>${b.planDate}
							</td>
							<td>
									${b.planTime}
							</td>
							<td>${b.content}</td>
							<td>
									${b.joinUserName}
							</td>
							<td>
								<c:if test="${role ne 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','All')">查看评价</a>
								</c:if>
								<c:if test="${role eq 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<c:if test="${not empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','${b.evalFlow}')">查看</a>
									</c:if>
									<c:if test="${empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','')">评价</a>
									</c:if>
								</c:if>
								<c:set var="file" value="${fileMap[b.itemFlow]}"></c:set>
								<c:if test="${role eq 'head'}">
									<a id="${b.itemFlow}down" style="display: ${empty file ? 'none':''};color: blue;" href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
									<a id="${b.itemFlow}del" style="display: ${empty file ? 'none':''}" href="javascript:void(0)" onclick="delFile('${b.itemFlow}');">删除</a>
									<form  id="${b.itemFlow}Form" style="display: ${empty file ? '':'none'}" enctype="multipart/form-data" method="post">
										<input type="hidden" name="itemFlow" value="${b.itemFlow}"/>
										<a style="color: blue;" href="javascript:void(0)" onclick="changeFile(this)">上传</a>
										<input type="file" style="display: none;" class="validate[required]" name="file" onchange="uploadFile(this,'${b.itemFlow}');">
									</form>
								</c:if>
								<c:if test="${role ne 'head' and not empty file}">
									<a href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list}" >
					<tr>
						<td colspan="7">暂无数据</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${param.itemTypeId eq deptActivityItemTypeEnumSQTHD.id}">
		<div class="A4">
			<table class="basic list " style="width: 100%;">
				<tbody>
				<tr>
					<th>科室</th>
					<th>月度</th>
					<th>日期</th>
					<th>主持人</th>
					<th>工号</th>
					<th>操作</th>
				</tr>
				</tbody>
				<tbody >
				<c:if test="${not empty list}" >
					<c:forEach items="${list}" var="b" varStatus="s">
						<tr>
							<td>${b.deptName}
							</td>
							<td>${b.planDate}
							</td>
							<td>
									${b.planTime}
							</td>
							<td>
									${b.joinUserName}
							</td>
							<td>
									${b.userCode}
							</td>
							<td>
								<c:if test="${role ne 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','All')">查看评价</a>
								</c:if>
								<c:if test="${role eq 'doctor' and sysCfgMap['dept_activity_type'] eq 'Y'}">
									<c:if test="${not empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','${b.evalFlow}')">查看</a>
									</c:if>
									<c:if test="${empty b.evalFlow}">
										<a href="javascript:void(0)" onclick="showEval('${b.itemFlow}','')">评价</a>
									</c:if>
								</c:if>
								<c:set var="file" value="${fileMap[b.itemFlow]}"></c:set>
								<c:if test="${role eq 'head'}">
									<a id="${b.itemFlow}down" style="display: ${empty file ? 'none':''};color: blue;" href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
									<a id="${b.itemFlow}del" style="display: ${empty file ? 'none':''}" href="javascript:void(0)" onclick="delFile('${b.itemFlow}');">删除</a>
									<form  id="${b.itemFlow}Form" style="display: ${empty file ? '':'none'}" enctype="multipart/form-data" method="post">
										<input type="hidden" name="itemFlow" value="${b.itemFlow}"/>
										<a style="color: blue;" href="javascript:void(0)" onclick="changeFile(this)">上传</a>
										<input type="file" style="display: none;" class="validate[required]" name="file" onchange="uploadFile(this,'${b.itemFlow}');">
									</form>
								</c:if>
								<c:if test="${role ne 'head' and not empty file}">
									<a href="<s:url value='/res/deptActivityStatistics/downFile'/>?fileFlow=${b.itemFlow}">附件</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list}" >
					<tr>
						<td colspan="7">暂无数据</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</c:if>
<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
<pd:pagination toPage="toPage"/>

      
