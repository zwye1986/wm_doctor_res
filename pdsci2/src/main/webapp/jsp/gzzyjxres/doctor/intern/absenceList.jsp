<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
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
	table.xllist a{color: blue;}
</style>
<script type="text/javascript">


function openWindow(absenceFlow){
         var title = "新增";
        if(absenceFlow){
               title = "修改";
        }
        var url = "<s:url value='/res/absence/editAbsence'/>?resRoleScope=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}&absenceFlow=" + absenceFlow;
        jboxOpen(url, title+"请假", 900, 400);
}

function prompt(leave,absenceFlow){
    jboxConfirm("当前剩余假期天数为"+leave+"天！" ,function(){
        openWindow(absenceFlow);
    } ,null);
}

function editAbsence(absenceFlow){
	var showFlag = '${showFlag}';
    var leave = '${leave}';
	if(showFlag == 'N' || leave=='0'){
		jboxTip("当前没有可请假期！");
		return;
	}

    prompt(leave,absenceFlow);
}

function delAbsence(absenceFlow){
	jboxConfirm("确认删除请假? " ,  function(){
		jboxStartLoading();
		var url = "<s:url value='/res/absence/delAbsence'/>?absenceFlow=" + absenceFlow;
		jboxPost(url, null,
			function(resp){
				if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
					window.location.reload(true);
				}
			}, null, true);
	});
}

function repealAbsence(absenceFlow){
	jboxConfirm("确认销假?",  function(){
		jboxStartLoading();
		var url = "<s:url value='/res/absence/repealAbsence'/>?absenceFlow=" + absenceFlow;
		jboxPost(url, null,
			function(resp){
				if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
					window.location.reload(true);
				}
			}, null, true);
	});
}

function search(){
	var form = $("#searchForm");
	form.submit();
}

function toPage(page){
	$("#currentPage").val(page);
	jboxStartLoading();
	search();
}
function calculateDay(){
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	if("" == startDate || "" == endDate){
		return;
	}
	if(endDate < startDate){
		jboxTip("结束日期不能早于开始日期！");
		return;
	}
}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/absence/absenceList"/>" method="post">
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">请假时间：</label>
						<input type="text" class="qtime" name="startDate" value="${param.startDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~ <input type="text" class="qtime" name="endDate" value="${param.endDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">请假类型：</label>
						<select name="absenceTypeId" class="qselect">
							<option value="">全部</option>
							<c:forEach var="absenceType" items="${leaveTypeEnumList}">
								<%--<c:if test="${GlobalConstant.FLAG_Y eq isRegister}">--%>
									<option value="${absenceType.id}" <c:if test="${param.absenceTypeId eq absenceType.id}">selected="selected"</c:if> >${absenceType.name}</option>
								<%--</c:if>--%>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qselect" name="doctorName" value="${param.doctorName}">
					</div>

					<div class="lastDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
					</div>
                    <c:if test="${roleFlag ne GlobalConstant.RES_ROLE_SCOPE_SECRETARY}">
					<div class="lastDiv">
						<input type="button" value="新&#12288;增" class="searchInput" onclick="editAbsence('');"/>
					</div>
                    </c:if>
				</div>
				<div style="float: right;margin: 0px auto 10px auto">
					<span>
					<img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;待审核&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>"/>&nbsp;审核通过&#12288;
					<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>"/>&nbsp;审核不通过&#12288;
					</span>
				</div>
			<table class="xllist">
				<tr>
					<th width="7%">姓名</th>
					<th width="5%">请假类型</th>
					<th width="17%">请假事由</th>
					<th width="15%">请假时间</th>
					<th width="10%">轮转科室</th>
					<th width="10%">带教老师</th>
					<th width="10%">科室主任</th>
					<%--<c:if test="${sysCfgMap['res_absence_manage_audit'] eq GlobalConstant.FLAG_Y}">--%>
					<th width="10%">医院管理员</th>
					<%--</c:if>--%>
					<th width="6%">状态</th>
					<th width="10%">操作</th>
				</tr>
				<c:forEach items="${doctorAbsenceList}" var="doctorAbsence">
					<c:set var="isDue" value="${doctorAbsence.endDate.compareTo(pdfn:getCurrDate())}"/>
					<c:set var="disagreeFlag" value="false"/>
					<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.teacherAgreeFlag or 
						GlobalConstant.FLAG_N eq doctorAbsence.headAgreeFlag or 
						GlobalConstant.FLAG_N eq doctorAbsence.managerAgreeFlag
						}">
						<c:set var="disagreeFlag" value="true"/>
					</c:if>
					<c:set var="repealFlag" value="false"/>
					<c:if test="${GlobalConstant.FLAG_Y ne doctorAbsence.repealAbsence and !(
						      (empty doctorAbsence.teacherAgreeFlag) or
							  (doctorAbsence.intervalDay >= sysCfgMap['res_absence_head_day'] and empty doctorAbsence.headAgreeFlag) or
							  (doctorAbsence.intervalDay >= sysCfgMap['res_absence_manage_day'] and empty doctorAbsence.managerAgreeFlag)
							)
						and	!disagreeFlag}">
						<c:set var="repealFlag" value="true"/>
					</c:if>
					<tr>
						<td>${doctorAbsence.doctorName}</td>
						<td>${doctorAbsence.absenceTypeName}</td>
						<td>${doctorAbsence.absenceReson}</td>
						<td>
							${doctorAbsence.startDate}~${doctorAbsence.endDate}<br/>
							<font style="font-weight: bold;">
								(${doctorAbsence.intervalDay}天)
							</font>
						</td>
						<td style="text-align: center; ">${doctorAbsence.schDeptName}</td>
						<td>
							<c:if test="${not empty doctorAbsence.teacherAgreeFlag}">
								<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.teacherAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" alt="审核不通过"/>
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.teacherAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" alt="审核通过"/>
								</c:if>
								${doctorAbsence.teacherName}
							</c:if>
							<c:if test="${empty doctorAbsence.teacherAgreeFlag and !disagreeFlag}">
								<%--and doctorAbsence.intervalDay >= sysCfgMap['res_absence_teacher_day'] --%>
							 	<c:choose>
									<c:when test="${isDue < 0 }">已过期</c:when>
									<c:otherwise><img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>" alt="待审核"/>&nbsp;${doctorAbsence.teacherName}</c:otherwise>
								</c:choose>
							</c:if>
						</td>
						<td>
							<c:if test="${not empty doctorAbsence.headAgreeFlag and doctorAbsence.intervalDay >= sysCfgMap['res_absence_head_day']}">
								<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.headAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.headAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
								</c:if>
								${doctorAbsence.headName}
							</c:if>
							<c:if
									test="${empty doctorAbsence.headAgreeFlag and
										 doctorAbsence.intervalDay+0 >= sysCfgMap['res_absence_head_day'] and
										 !disagreeFlag}">
								<c:choose>
									<c:when test="${isDue < 0 }">已过期</c:when>
									<c:otherwise><img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${doctorAbsence.headName}</c:otherwise>
								</c:choose>
							</c:if>
						</td>
						<%--<c:if test="${sysCfgMap['res_absence_manage_audit'] eq GlobalConstant.FLAG_Y}">--%>
						<td>
							<c:if test="${not empty doctorAbsence.managerAgreeFlag}">
								<c:if test="${GlobalConstant.FLAG_N eq doctorAbsence.managerAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
								</c:if>
								<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.managerAgreeFlag}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
								</c:if>
								${doctorAbsence.managerName}
							</c:if>
							<c:if test="${empty doctorAbsence.managerAgreeFlag and
										 doctorAbsence.intervalDay >= sysCfgMap['res_absence_manage_day'] and
										 !disagreeFlag}">
								<c:choose>
									<c:when test="${isDue<0}">已过期</c:when>
									<c:otherwise><img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${doctorAbsence.managerName}</c:otherwise>
								</c:choose>
							</c:if>
						</td>
						<%--</c:if>--%>
						<td>
							<c:if test="${GlobalConstant.FLAG_Y eq doctorAbsence.repealAbsence}">
								已销假	
							</c:if>
							<c:if test="${repealFlag}">
								未销假	
							</c:if>
						</td>
						<td>
                        <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_SECRETARY}">
                            <c:if test="${repealFlag}">
                                <a href="javascript:repealAbsence('${doctorAbsence.absenceFlow}');">销假</a>
                            </c:if>
                        </c:if>

                        <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                            <c:if test="${(empty doctorAbsence.teacherAgreeFlag) and (empty doctorAbsence.headAgreeFlag) and (empty doctorAbsence.managerAgreeFlag)}">
                                <a href="javascript:editAbsence('${doctorAbsence.absenceFlow}');">| 编辑</a> |
                                <a href="javascript:delAbsence('${doctorAbsence.absenceFlow}');">删除 |</a>
                            </c:if>
                        </c:if>
						</td>
					</tr>
				</c:forEach>
				
				<c:if test="${empty doctorAbsenceList}">
					<tr>
						<td colspan="10">无记录</td>
					</tr>
				</c:if>
			</table>
			</form>
			<p>
			   	<c:set var="pageView" value="${pdfn:getPageView2(doctorAbsenceList, 10)}" scope="request"/> 
				<pd:pagination toPage="toPage"/>
			</p>
			 </div>
		</div>
	</div>
</body>
</html>