<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="false"/>
		<jsp:param name="jquery_placeholder" value="false"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
<style type="text/css">
.ith{height: 40px;line-height: 40px;padding-left: 10px;}
	table.basic th,table.basic td{text-align: center;padding: 0px;}
</style>
<script type="text/javascript">
	$(function(){
		if("${empty param.recTypeId}"=="true"){
			if($("#tags li").length>0) {
				$("#tags li:first").click();
				location.href = $(".selectTag a").attr("href");
			}
		}
		<c:if test="${!empty param.recTypeId}">
			selTag($("#${param.recTypeId}"));
		</c:if>
		
	});
	function setType(flag){
		$("#recTypeId").val(flag);
		dataChange();
	}
	function dataChange(){
		/* .serialize() */
		jboxStartLoading();
		var form=$("#searchForm");
		form.submit();	
	}
	//标签高亮
	function selTag(tag){
		$(".selectTag").removeClass("selectTag");
		$(tag).addClass("selectTag");
	}
	<%--function back() {--%>
		<%--var url="<s:url value='/res/teacher/schDetails'/>?doctorFlow=${param.doctorFlow}";--%>
		<%--window.location.href=url;--%>
	<%--}--%>
</script>
</head>
<body>
<form id="searchForm" action="<s:url value='/res/teacher/details'/>" method="post">
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
	<input type="hidden" id="doctorFlow" name="doctorFlow" value="${param.doctorFlow}"/>
	<input type="hidden" id="processFlow" name="processFlow" value="${param.processFlow}"/>
</form>
<div class="mainright" align="center" style="height: 100%;background-color: white;">
	<div class="content">
		<div>轮转科室：${resDoctorSchProcess.schDeptName}</div>
		<div class="title1 clearfix" align="left">
			<div style="float: left;width: 100%;">
	     		<ul id="tags" style="width: 100%;">

					<c:if test="${typeId eq jszyTCMPracticEnumN.id or empty typeId}">
						<c:forEach items="${registryTypeEnumList}" var="registryType">
							<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y
							 && GlobalConstant.FLAG_Y eq registryType.haveReq
									&&pdfn:findChineseOrWestern(user.medicineTypeId,registryType.id)}">
								<li id="${registryType.id}"  onclick="selTag(this);"><a style="cursor: pointer;" href="javascript:setType('${registryType.id}',this);" >${registryType.name}</a></li>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
						<c:forEach items="${practicRegistryTypeEnumList}" var="registryType">
							<c:set value="practic_registry_type_${registryType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y
							&& GlobalConstant.FLAG_Y eq registryType.haveReq
									&&pdfn:findChineseOrWestern(user.medicineTypeId,registryType.id)}">
								<li id="${registryType.id}"  onclick="selTag(this);"><a style="cursor: pointer;" href="javascript:setType('${registryType.id}',this);" >${registryType.name}</a></li>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
						<c:forEach items="${theoreticalRegistryTypeEnumList}" var="registryType">
							<c:set value="theoretical_registry_type_${registryType.id}" var="viewCfgKey"/>
							<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y
							&& GlobalConstant.FLAG_Y eq registryType.haveReq
									&&pdfn:findChineseOrWestern(user.medicineTypeId,registryType.id)}">
								<li id="${registryType.id}"  onclick="selTag(this);"><a style="cursor: pointer;" href="javascript:setType('${registryType.id}',this);" >${registryType.name}</a></li>
							</c:if>
						</c:forEach>
					</c:if>
		        </ul>
			</div>
		 <div id="tagContent" style="max-height: 300px;overflow: auto;">
		    	<table class="basic" width="97%" style="margin: 10px;" >
						<tr>
							<td colspan="${titleMap.size()}">
								<c:if test="${empty param.recTypeId}">
									<c:set var="finBl" value="0"></c:set>
									<c:set var="finNum" value="0"></c:set>
									<c:set var="reqNum" value="0"></c:set>

								</c:if>
								<c:if test="${not empty param.recTypeId}">
									<c:set var="recKey" value="${resultFlow}${param.recTypeId}"></c:set>
									<c:set var="recKeyFinish" value="${resultFlow}${param.recTypeId}finish"></c:set>
									<c:set var="recKeyReq" value="${resultFlow}${param.recTypeId}req"></c:set>
									<c:set var="finBl" value="${empty finishPer[recKey] ?0:finishPer[recKey]}"></c:set>
									<c:set var="finNum" value="${empty finishPer[recKeyFinish] ? 0 : finishPer[recKeyFinish]}"></c:set>
									<c:set var="reqNum" value="${empty finishPer[recKeyReq] ? 0 : finishPer[recKeyReq]}"></c:set>

								</c:if>
								<div style="width: 80px;float:left;text-align:left;padding-left: 20%;">
									完成比例：
								</div>
								<c:if test="${reqNum == '0'}">
									<span  style="float:left;">--</span>
								</c:if>
								<c:if test="${reqNum != '0'}">
									<div style="width: 30%;margin-top: 6px;float:left;">
										<div style="width: 100%;float:left; border:1px solid #e7e7eb;">
											<div class="bili" style="width:${finBl}%;">
												<font style="color:rgba(255,120,50,1);">${finBl}%</font>
											</div>
										</div>
									</div>
								</c:if>
								<div style="width: 200px;float:left;text-align:left;padding-left: 10px;">
									（要求数：${reqNum}；完成数：${finNum}）
								</div>
							</td>
						</tr>
			    		<tr>
				    		<c:forEach items="${titleMap}" var="rec" varStatus="a">
				    			<th nowrap="">${rec.title}</th>
			    			</c:forEach>
			    		</tr>	
			    		<c:forEach items="${resRecList}" var="res">
			    			<tr>
			    				<c:forEach items="${titleMap}" var="t" varStatus="a">
									<td>
										<c:if test="${ 'Y' eq t.isFile}">
											<c:set var="flowKey" value="${t.name}_Flow"></c:set>
											<c:set var="fileFlows" value="${pdfn:split(resRecMap[res.recFlow][flowKey],',')}"/>
											<c:set var="fileNames" value="${pdfn:split(resRecMap[res.recFlow][t.name],',')}"/>
											<c:if test="${not empty resRecMap[res.recFlow][flowKey]}">
												<ul>
													<c:forEach var="fileFlow" items="fileFlows" varStatus="status">
														<li>
															<a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${fileFlows[status.index]}">【${fileNames[status.index]}】</a>
														</li>
													</c:forEach>
												</ul>
											</c:if>
										</c:if>
										<c:if test="${ !('Y' eq t.isFile)}">
											${resRecMap[res.recFlow][t.name]}
										</c:if>
									</td>
								</c:forEach>		    			
			    			</tr>
			    		</c:forEach>
						<c:if test="${empty titleMap }">
								<tr>
									<td colspan="10">暂无记录</td>
								</tr>
						</c:if>
		    	</table>

			</div>
	</div>
	    <div style="text-align: center;">
			<input type="button"  value="关&#12288;闭" class="search" style="width: 70px"  onclick="jboxClose();" />&#12288;&#12288;
			<%--<input type="button"  value="返&#12288;回" class="search" style="width: 70px"  onclick="back();" />--%>
	    	<%--<a style="color: blue;cursor: pointer;" onclick="jboxClose();">关闭</a>&#12288;&#12288;--%>
			<%--<a style="color: blue;cursor: pointer;" onclick="back();">返回</a>--%>
	    </div>
	</div>
</div>
</body>
</html>