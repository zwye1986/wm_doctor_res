<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
	td.appoint,td.differ{cursor:pointer;}
</style>
<script type="text/javascript">
	$(function(){
		$('.appoint,.differ').bind('click',function(){
			var year = $(this).closest("tr").find("td").eq(1).text();
			var clinicalName = $(this).closest("tr").find("td").eq(2).text();
			var speName = $(this).closest("tr").find("td").eq(3).text();
			var isLocal = $(this).closest("tr").find("td").eq(4).attr("value");
			var clinicalFlow = $(this).closest("tr").attr("id");
			var subjectFlow = $(this).closest("tr").attr("subjectFlow");
			var url = "<s:url value='/osca/base/checkInfoManage?initFlag=Y&clinicalFlow='/>"+clinicalFlow+"&subjectFlow="+subjectFlow+"&clinicalName="+encodeURI(encodeURI(clinicalName))+"&speName="+encodeURI(encodeURI(speName))+"&isLocal="+isLocal+"&year="+year;
			jboxPostLoad("initCont",url,null,true);
		});
		$('.appoint').bind('hover',function(){
			$(this).attr("title","请点击进入");
		});
		<c:if test="${jumpFlag eq 'Y'}">
		top.$("#menuSetName").text("考核信息管理");
		window.location.href="<s:url value='/osca/base/checkInfoList?isLocal=Y'/>";
		</c:if>
		$('#trainingSpeId option').hide();
		$('#trainingSpeId option[value=""]').show();
		$('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
	});
	function toPage(page){
		$("#currentPage").val(page);
		$("#searchForm").submit();
	}
	function addCheckInfo(clinicalFlow,flag){
		var title = clinicalFlow == ""?"新增":"编辑";
		title = flag == "view"?"查看":title;
		if(flag == "edit"){
			title="编辑";
		}
		var url = "<s:url value='/osca/base/addCheckInfo?clinicalFlow='/>"+clinicalFlow+"&flag="+flag+"&isLocal=${param.isLocal}";
		jboxOpen(url, title,700,500);
	}
	function releasedInfo(clinicalFlow){
		jboxConfirm("发布后将无法修改考核信息，是否确认发布？", function(){
			var url = "<s:url value='/osca/base/releasedInfo?clinicalFlow='/>"+clinicalFlow;
			jboxPost(url, null, function(resp){
				if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
					location.reload();
				}
			});
		});
	}
	function delInfo(clinicalFlow,isReleased){
		if(isReleased == "Y"){//发布后
			jboxConfirm("删除后预约学员信息将一并删除，是否确认删除？", function(){
				var url = "<s:url value='/osca/base/delCheckInfo?clinicalFlow='/>"+clinicalFlow+"&isReleased="+isReleased;
				jboxPost(url, null, function(resp){
					if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
						location.reload();
					}
				}, null, false);
			});
		}else{//发布前
			jboxConfirm("是否确认删除考核信息？", function(){
				var url = "<s:url value='/osca/base/delCheckInfo?clinicalFlow='/>"+clinicalFlow;
				jboxPost(url, null, function(resp){
					if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
						location.reload();
					}
				});
			});
		}
	}
	function queryQrCode(clinicalFlow){
		jboxOpen("<s:url value='/osca/base/queryQrCode'/>?clinicalFlow=" + clinicalFlow,'考核信息二维码',360	,360);
	}
	function toPage1(page){
		$("#currentPage1").val(page);
		$("#appointForm").submit();
	}
	function goScreen(clinicalFlow){
		var url = '<s:url value="/osca/base/goScreen"/>?clinicalFlow='+clinicalFlow;
		window.open(url);
	}
	function linkageSubject(dictId){
		$('#trainingSpeId').val("");//清空上次展现数据
		$('#trainingSpeId option').hide();
		$('#trainingSpeId option[value=""]').show();
		$('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
	}
</script>
</head>
<body id="initCont">
<div class="mainright">
	<div class="content">
        <div class="queryDiv">
		<form id="searchForm" action="<s:url value="/osca/base/checkInfoList"/>" method="post">
			<%--<table class="basic" style="width: 100%;margin: 10px 0px;">--%>
				<%--<tr>--%>
					<%--<td style="padding-left:18px; line-height:54px;">--%>
						<input id="currentPage" type="hidden" name="currentPage" value="1"/>
                    <div class="inputDiv">
                        <label class="qlable">年份：</label>
						<input type="text" class="qtext" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="clinicalYear" value="${param.clinicalYear}"/>
					</div>
				<div class="inputDiv">
					培训类型：
					<select name="trainingTypeId" class="xlselect" onchange="linkageSubject(this.value)">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
							<option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					培训专业：
					<c:set value="OscaTrainingType.${param.trainingTypeId}" var="trainingTypeClass"></c:set>
					<select id="trainingSpeId" name="speId" class="xlselect">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
							<c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
							<c:forEach items="${applicationScope[dictKey]}" var="scope">
								<option class="${scope.dictTypeId}" value="${scope.dictId}" ${(param.speId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
							</c:forEach>
						</c:forEach>
					</select>
				</div>
                    <div class="inputDiv">
                        <label class="qlable">考核名称：</label>
                        <input type="text" class="qtext" name="clinicalName" value="${param.clinicalName}">
                    </div>
                    <%--<div class="inputDiv">--%>
                        <%--<label class="qlable">考核类型：</label>--%>
                        <%--<select name="isLocal" class="qselect">--%>
                            <%--<option value="">全部</option>--%>
                            <%--<option value="Y" ${param.isLocal eq 'Y'?'selected':''}>院内考核</option>--%>
                            <%--<option value="N" ${param.isLocal eq 'N'?'selected':''}>结业考核</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
					<input type="hidden" name="isLocal" value="${param.isLocal}">
                    <div class="lastDiv" style="max-width: 200px;min-width: 200px;margin-left: 20px;">
                        <input type="button" class="searchInput" value="查&#12288;询" onclick="toPage(1)"/>
                        <input type="button" class="searchInput" value="新&#12288;增" onclick="addCheckInfo('')"/>&#12288;
                    </div>
					<%--</td>--%>
				<%--</tr>--%>
			<%--</table>--%>
		</form>
        </div>
		<table class="xllist" style="width:100%;">
			<tr>
				<th style="width:40px;">序号</th>
				<th style="width:60px;">年份</th>
				<th style="width:100px;">考核名称</th>
				<th style="width:100px;">考核专业</th>
				<th style="width:100px;">考核类型</th>
				<th style="width:135px;min-width: 130px;">考核方案名称</th>
				<th style="width:135px;min-width: 130px;">预约开放时间</th>
				<th style="width:100px;">预约人员容量</th>
				<th style="width:100px;">剩余人员容量</th>
				<th style="width:135px;min-width: 130px;">考核时间</th>
				<th style="width:120px;">考点地址</th>
				<th style="width:100px;">二维码</th>
				<th style="width:150px;">操作</th>
			</tr>
			<c:forEach items="${dataList}" var="info" varStatus="i">
				<tr id="${info.clinicalFlow}" subjectFlow="${info.subjectFlow}">
					<td class="appoint">${i.index + 1}</td>
					<td class="appoint">${info.clinicalYear}</td>
					<td class="appoint">${info.clinicalName}</td>
					<td class="appoint">${info.speName}</td>
					<td class="appoint" value="${info.isLocal}">${info.isLocal eq 'Y'?'院内考核':'结业考核'}</td>
					<td class="appoint">${info.subjectName}</td>
					<td class="appoint">${info.appointStartTime}~<br/>${info.appointEndTime}</td>
					<td class="appoint">${info.appointNum}</td>
					<td class="appoint">${info.appointNum - info.useNum}</td>
					<td style="line-height: 130%" class="differ" title="<table><tr><th><c:if test="${empty info.examStartTimeList}">暂无考核时间</c:if><c:forEach items="${fn:split(info.examStartTimeList,',')}" var="startTime" varStatus="i">${startTime}
						<c:forEach items="${fn:split(info.examEndTimeList,',')}" var="endTime" varStatus="j"><c:if test="${i.index eq j.index}">${not empty endTime?'~':''}${endTime}<br/></c:if></c:forEach></c:forEach>
					</th></tr></table>">
						<c:forEach items="${fn:split(info.examStartTimeList,',')}" var="startTime" varStatus="i">
							<c:if test="${i.first}">${startTime}</c:if>
						</c:forEach>
						<c:forEach items="${fn:split(info.examEndTimeList,',')}" var="endTime" varStatus="i">
							<c:if test="${i.first}">${not empty endTime?'~':''}<br/>${endTime}</c:if>
							<c:if test="${i.last}">${i.count gt 1?'<br/>......':''}</c:if>
						</c:forEach>
					</td>
					<td class="appoint">${info.examAddress}</td>
					<td><a onclick="queryQrCode('${info.clinicalFlow}')" style="cursor:pointer;"><c:out value="查看"/></a></td>
					<td>
						<c:if test="${info.isReleased eq 'Y'}">
							<a href="javascript:;" style="color:grey;"><c:out value="已发布"/></a>
							<%--预约时间结束后且无待审核的学院，才能编辑考核时间及考点--%>
							<c:if test="${pdfn:getCurrDateTime('yyyy-MM-dd HH:mm') gt info.appointEndTime && info.dshNum le 0}">
								<a onclick="addCheckInfo('${info.clinicalFlow}','edit');" style="cursor:pointer;color:#4195c5;"><c:out value="编辑"/></a>
							</c:if>
							<%--未到考核时间，可删除--%>
							<c:set var="startTimeFlag" value='0'></c:set>
							<c:forEach items="${fn:split(info.examStartTimeList,',')}" var="startTime" varStatus="i">
								<c:if test="${startTime lt pdfn:getCurrDateTime('yyyy-MM-dd HH:mm')}">
									<c:set var="startTimeFlag" value='1'></c:set>
								</c:if>
							</c:forEach>
							<c:if test="${empty info.examStartTimeList || startTimeFlag eq '0'}">
								<a onclick="delInfo('${info.clinicalFlow}','${info.isReleased}');" style="cursor:pointer;color:#4195c5;"><c:out value="删除"/></a>
							</c:if>
							<%--判断是否在考核时间内--%>
							<c:set var="isCurrentExam" value="0"></c:set>
							<c:forEach items="${fn:split(info.examStartTimeList,',')}" var="startTime2" varStatus="i">
								<c:forEach items="${fn:split(info.examEndTimeList,',')}" var="endTime2" varStatus="j">
									<c:if test="${(i.index eq j.index)&&(startTime2<pdfn:getCurrDateTime('yyyy-MM-dd HH:mm:ss'))&&
									(endTime2>pdfn:getCurrDateTime('yyyy-MM-dd HH:mm:ss'))}"><c:set var="isCurrentExam" value="1"></c:set>
									</c:if>
								</c:forEach>
							</c:forEach>
							<c:if test="${isCurrentExam eq '1'}">
								<a onclick="goScreen('${info.clinicalFlow}');" style="cursor:pointer;color:#4195c5;"><c:out value="屏显"/></a>
							</c:if>
						</c:if>
						<c:if test="${info.isReleased ne 'Y'}">
							<a onclick="releasedInfo('${info.clinicalFlow}');" style="cursor:pointer;color:#4195c5;"><c:out value="发布"/></a>
							<a onclick="addCheckInfo('${info.clinicalFlow}');" style="cursor:pointer;color:#4195c5;"><c:out value="编辑"/></a>
							<a onclick="delInfo('${info.clinicalFlow}','${info.isReleased}');" style="cursor:pointer;color:#4195c5;"><c:out value="删除"/></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div style="margin-top:65px;">
			<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>	
</html>