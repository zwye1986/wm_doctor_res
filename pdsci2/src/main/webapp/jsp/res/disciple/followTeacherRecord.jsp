
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
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

	<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

	<style type="text/css">
		.table {
			border: 1px solid #e3e3e3;
		}
		.table tr:nth-child(2n) {
			background-color: #fcfcfc;
			transition: all 0.125s ease-in-out 0s;
		}
		.table tr:hover {
			background: #fbf8e9 none repeat scroll 0 0;
		}
		.table th, .table td {
			border-bottom: 1px solid #e3e3e3;
			border-right: 1px solid #e3e3e3;
			text-align: center;
		}
		.table th {
			background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
			color: #585858;
			height: 30px;
		}
		.table td {
			height: 30px;
			line-height: 25px;
			text-align: center;
			word-break: break-all;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
		});
		function toPage(page) {
			var currentPage="";
			if(!page||page!=undefined){
				currentPage=page;
			}
			if(page==undefined||page==""){
				currentPage=1;
			}
			$("#currentPage").val(currentPage);
			refreshInfo();
		}
		function addFolowTeacherRecord(){
			jboxOpen("<s:url value='/res/folowTeacherRecord/showSaveFollowTeacherRecord'/>",'添加跟师记录',400,200);
		}

		function editRecord(recordFlow,exp){
			if(exp=="Qualified"){
				return;
			}
			jboxOpen("<s:url value='/res/folowTeacherRecord/showSaveFollowTeacherRecord?recordFlow='/>"+recordFlow,'编辑跟师记录',400,200);
		}
		function deleteRecord(recordFlow,exp){
			if(exp=="Qualified"){
				return;
			}
			jboxConfirm("确认移除该记录吗？",function () {
				var url = "<s:url value='/res/folowTeacherRecord/removeFollowTeacherRecord?recordFlow='/>" + recordFlow;
				jboxGet(url,null,function(){
					refreshInfo();
				});
			});
		}
		function back(){
			var url ="<s:url value="/res/disciple/main"></s:url> ";
			var roleId=$("#roleId").val();
			if("teacher"==roleId){
				var teacherCurrentPage= $("#teacherCurrentPage").val();
				url="<s:url value='/res/folowTeacherRecord/auditFollowTeacherRecord'/>?currentPage="+teacherCurrentPage;
			}
			if("see"==roleId){
				var teacherCurrentPage= $("#teacherCurrentPage").val();
				url="<s:url value='/res/discipleTeacherAudit/studentList'/>?currentPage="+teacherCurrentPage;
			}
			if("adminSee"==roleId){
				var teacherCurrentPage= $("#teacherCurrentPage").val();
				url="<s:url value='/res/discipleAdminAudit/studentList'/>?currentPage="+teacherCurrentPage;
			}
			window.location.href= url;
		}
		function refreshInfo(){
			var currentPage= $("#currentPage").val();
			var roleId=$("#roleId").val();
			var teacherCurrentPage= $("#teacherCurrentPage").val();
			var currentPage= $("#currentPage").val();
			var doctorFlow= $("#doctorFlow").val();
			var url ="<s:url value='/res/folowTeacherRecord/showFollowTeacherRecord'/>?currentPage="+currentPage+"&doctorFlow="+doctorFlow;
			if("teacher"==roleId){
				url ="<s:url value='/res/folowTeacherRecord/showFollowTeacherRecord'/>?teacherCurrentPage="+teacherCurrentPage+"&doctorFlow="+doctorFlow+"&roleId="+roleId+"&currentPage="+currentPage;
			}
			if("see"==roleId||"adminSee"==roleId){
				url ="<s:url value='/res/folowTeacherRecord/showFollowTeacherRecord'/>?teacherCurrentPage="+teacherCurrentPage+"&doctorFlow="+doctorFlow+"&roleId="+roleId+"&currentPage="+currentPage;
			}
			window.location.href= url;
		}
		function selectAll(exp){
			$("td input[type='checkbox']").attr("checked","checked");
		}
		function Contrary(exp){
			var selects = $("td input[type='checkbox']");
			for(var i=0;i<selects.length;i++){
				var exp =$(selects[i]).attr("checked");
				if(exp=="checked"){
					$(selects[i]).attr("checked",false);
				}else {
					$(selects[i]).attr("checked","checked");
				}
			}
		}
		function agreeRecord(exp){
			var recordFlows = new Array();
			var i = 0;
			$("td input[type='checkbox']:checked").each(function(){
				recordFlows[i]=$(this).attr("recordFlow");
				i++;
			});
			var recordFlowsStr = recordFlows.join(",");
			if(recordFlows.length==0){
				jboxTip("您还没有勾选！");
				return;
			}
			var url = "<s:url value='/res/folowTeacherRecord/modifySelected?recordFlowsStr='/>" + recordFlowsStr+"&statuId="+exp;
			jboxConfirm("确认所勾选学员跟师记录正确？" , function() {
				jboxPost(url,null,function(){
					refreshInfo();
				}, null, false);
			});
		}
		function agreeRecordBatch(exp){
			var url = "<s:url value='/res/folowTeacherRecord/modifySelected?doctorFlow=${doctorFlow}&batch=Y&roleId=${roleId}'/>";
			jboxConfirm("确认审核通过该学员所有待审核跟师记录？" , function() {
				jboxPost(url,null,function(resp){
					jboxTip(resp);
					setTimeout(function(){
						location.reload();
					},1000);
				}, null, false);
			});
		}

	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<input id="currentPage" type="hidden" value="${currentPage}"/>
			<input id="roleId" type="hidden" value="${roleId}"/>
			<input id="teacherCurrentPage" type="hidden" value="${teacherCurrentPage}"/>
			<input id="doctorFlow" type="hidden" value="${doctorFlow}"/>
			<c:if test="${'' eq roleId}">
				<div  align="center">
					<table class="xllist" width="100%">
						<tr style="height: 58px">
							<th style="text-align: center; "><span style="font-size: 25px;">跟师记录</span></th>
						</tr>
					</table>
					<br>
				</div>
				<div style="margin-bottom: 10px;" align="right">
					<input type="button" value="新&#12288;增" class="search" onclick="addFolowTeacherRecord();"/>
					<%--<input type="button" value="返&#12288;回" class="search" onclick="back();"/>--%>
				</div>
			</c:if>
			<c:if test="${'teacher' eq roleId}">
				<div  align="center">
					<font size="5"> 跟师记录审核 </font>
				</div>
				<div style="margin-bottom: 10px;" align="right">
					<input type="button" value="审核通过" class="search" onclick="agreeRecord('1');"/>
					<input type="button" value="审核不通过" class="search" onclick="agreeRecord('0');"/>
					<%--<input type="button" value="返&#12288;回" class="search" onclick="back();"/>--%>
				</div>
			</c:if>
			<c:if test="${('adminSee' eq roleId or 'see' eq roleId) and not empty resDiscipleRecordInfos}">
				<div  align="center">
					<font size="5"> 查看跟师记录 </font>
					<span style="float: right;"><input type="button" value="一键审核" class="search" onclick="agreeRecordBatch();"/></span>
				</div>
				<%--<div style="margin-bottom: 10px;" align="left">--%>
					<%--<input type="button" value="返&#12288;回" class="search" onclick="back();"/>--%>
				<%--</div>--%>
			</c:if>
			<div id="base">
				<table  class="xllist">
					<c:if test="${'teacher' eq roleId}">
						<colgroup>
							<col width="15%"/>
							<col width="15%"/>
							<col width="25%"/>
							<col width="15%"/>
							<col width="30%"/>
						</colgroup>
					</c:if>
					<c:if test="${'' eq roleId}">
						<colgroup>
							<col width="10%"/>
							<col width="25%"/>
							<col width="20%"/>
							<col width="15%"/>
							<col width="15%"/>
							<col width="15%"/>
						</colgroup>
					</c:if>
					<tr>
						<c:if test="${'teacher' eq roleId}">
							<th style="width: 20px;"><a href="javascript:void(0);" onclick="selectAll();">全选</a>/<a href="javascript:void(0);" onclick="Contrary();">反选</a></th>
						</c:if>
						<th  style="text-align: center;">序号</th>
						<th  style="text-align: center;">出诊时间</th>
						<th>地点</th>
						<%--<th  style="text-align: center;">指导老师签章</th>--%>
						<c:choose>
							<c:when test="${'see' eq roleId or 'adminSee' eq roleId}"><th  style="text-align: center;">状态</th></c:when>
							<c:otherwise><th  style="text-align: center;">指导老师签章</th></c:otherwise>
						</c:choose>
						<c:if test="${'' eq roleId}">
							<th  style="text-align: center;">操作</th>
							<th  style="text-align: center;">状态</th>
						</c:if>
					</tr>
					<c:if test="${not empty resDiscipleRecordInfos}">
						<c:forEach items="${resDiscipleRecordInfos}" var="resDiscipleRecordInfo" varStatus="num">
							<tr>
								<c:if test="${'teacher' eq roleId}">
									<td style="width: 20px;"><input recordFlow="${resDiscipleRecordInfo.recordFlow}" type="checkbox"/></td>
								</c:if>
								<td>${num.count }</td>
								<td>${resDiscipleRecordInfo.discipleDate}&#12288;${resDiscipleRecordInfo.startTime}</br>${resDiscipleRecordInfo.discipleDate}&#12288;${resDiscipleRecordInfo.endTime}</td>
								<%--<td>${resDiscipleRecordInfo.teacherName}</td>--%>
								<td>${resDiscipleRecordInfo.address }</td>
								<c:choose>
									<c:when test="${'see' eq roleId or 'adminSee' eq roleId}"><td>${resDiscipleRecordInfo.auditStatusName}</td></c:when>
									<c:otherwise>
										<td>
											<c:if test="${'Qualified' eq resDiscipleRecordInfo.auditStatusId or 'UnQualified' eq resDiscipleRecordInfo.auditStatusId or 'teacher' eq roleId}">${empty pdfn:getSingnPhoto(resDiscipleRecordInfo.teacherFlow) ?resDiscipleRecordInfo.teacherName : pdfn:getSingnPhoto(resDiscipleRecordInfo.teacherFlow)}</c:if>
										</td>
									</c:otherwise>
								</c:choose>
								<c:if test="${'' eq roleId}">
									<td><a <c:if test="${'Qualified' eq resDiscipleRecordInfo.auditStatusId}">style="color:grey;cursor:text" href="javascript:void(0);"</c:if>
                                            <c:if test="${'PendingAudit' eq resDiscipleRecordInfo.auditStatusId or 'UnQualified' eq resDiscipleRecordInfo.auditStatusId}"> href="javascript:editRecord('${resDiscipleRecordInfo.recordFlow}','${resDiscipleRecordInfo.auditStatusId}')">编辑</a></c:if> &#12288;&#12288;
										<a <c:if test="${'Qualified' eq resDiscipleRecordInfo.auditStatusId }">style="color:grey;cursor:text" href="javascript:void(0);"</c:if>
                                           <c:if test="${'PendingAudit' eq resDiscipleRecordInfo.auditStatusId or 'UnQualified' eq resDiscipleRecordInfo.auditStatusId}">href="javascript:deleteRecord('${resDiscipleRecordInfo.recordFlow}','${resDiscipleRecordInfo.auditStatusId}')">删除</a></c:if>
                                    </td>
									<td>${resDiscipleRecordInfo.auditStatusName}</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${empty resDiscipleRecordInfos}">
						<c:if test="${'' eq roleId or 'see' eq roleId  or 'adminSee' eq roleId}">
							<tr><td colspan="99">暂无跟师记录</td></tr>
						</c:if>
						<c:if test="${'teacher' eq roleId}">
							<tr><td colspan="99">暂无待审核跟师记录</td></tr>
						</c:if>
					</c:if>
				</table>
				<div>
					<c:set var="pageView" value="${pdfn:getPageView(resDiscipleRecordInfos)}" scope="request"></c:set>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>