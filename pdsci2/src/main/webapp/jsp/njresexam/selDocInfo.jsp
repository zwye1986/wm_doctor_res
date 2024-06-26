<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="login" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
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
	<script type="text/javascript">
		function search() {
			var url = "<s:url value='/res/njExam/searchDocInfos'/>"
			jboxPostLoad("doctorContent", url, $("#paramForm").serialize(), false);
		}
		function Contrary(exp) {
			var selects = $("td input[type='checkbox'][name='recordFlow']");
			for (var i = 0; i < selects.length; i++) {
				var exp = $(selects[i]).attr("checked");
				if (exp == "checked") {
					$(selects[i]).attr("checked", false);
				} else {
					$(selects[i]).attr("checked", "checked");
				}
			}
		}
		function selectAll() {
			$("td input[type='checkbox'][name='recordFlow']").attr("checked", "checked");
		}
		function setExamInfo(examFlow)
		{
			var flows = "";
			$("input[name='recordFlow']:checked").each(function (index, domEle) {
				if(flows == ""){
					flows = $(this).val();
				}else {
					flows +=  "," +  $(this).val();
				}
			});
			if("" == flows){
				jboxTip("请选择分配人员！");
				return false;
			}
			var url = '<s:url value="/res/njExam/setExamInfo"/>?resultFlows='+flows+"&examFlow="+examFlow;
			jboxConfirm("确认分配？" , function(){
				jboxGet(url , null , function(resp){
					jboxTip(resp);
					if(resp=="${GlobalConstant.SAVE_SUCCESSED}")
					{
						search();
					}
				} , null , true);
			});
		}
		function toPage(page) {
			$("#currentPage").val(page);
			jboxStartLoading();
			$("#paramForm").submit();
		}
	</script>
	<style>
		body {
			overflow: hidden;
		}
	</style>
</head>

<body>
<div style="overflow:auto;" id="indexBody">
	<div class="bd_bg">
		<div class="yw">
			<div class="body">
				<div class="container">
						<div class="main_hd" id="doctorContent">
							<form id="paramForm" action="<s:url value="/res/njExam/searchDocInfos"/>" method="post">
								<h2>姓名<input class="input" type="text" name="userName" value="${docinfo.userName}">&#12288;
									证件号<input class="input" type="text" name="idNo" value="${docinfo.idNo}">&#12288;
									<c:if test="${empty currOrgFlow}">
									基地<select id="orgFlow" name="orgFlow" class="select">
										<option value="">--请选择--</option>
										<c:forEach items="${sysOrgs}" var="org">
											<option value="${org.orgFlow}" <c:if test="${docinfo.orgFlow == org.orgFlow}">selected</c:if>>${org.orgName}</option>
										</c:forEach>
									</select>&#12288;</c:if>
									培训专业<select name="speName" id="speName" class="select" style="width: 106px;">
										<option value="">请选择</option>
										<option <c:if test="${docinfo.speName eq '全科转岗'}">selected="selected"</c:if> value="全科转岗">全科转岗</option>
										<option <c:if test="${docinfo.speName eq '助理全科'}">selected="selected"</c:if> value="助理全科">助理全科</option>
										<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
											<option <c:if test="${docinfo.speName eq dict.dictName}">selected="selected"</c:if> value="${dict.dictName}">${dict.dictName}</option>
										</c:forEach>
									</select>
									<input type="hidden" id="currentPage" name="currentPage"/>
									<input type="hidden" id="examFlow" name="recordFlow" value="${recordFlow}"/>
									<input class="btn_green" type="button"
										   onclick="search();" value="查询">
									<input class="btn_green" type="button"
										   onclick="setExamInfo('${recordFlow}');" value="分配">
								</h2>
							</form>
							<div class="doctorContent">
								<div class="search_table" id="baseInfo">
									<table border="0" cellpadding="0" cellspacing="0" class="base_info">
										<colgroup>
											<col width="20%"/>
											<col width="20%"/>
											<col width="20%"/>
											<col width="20%"/>
											<col width="20%"/>
										</colgroup>
										<tr>
											<th style="text-align:center;"><a href="javascript:void(0);"
																			  onclick="selectAll();">全选</a>/<a href="javascript:void(0);" onclick="Contrary();">反选</a></th>
											<th style="text-align:center;">姓名</th>
											<th style="text-align:center;">证件号码</th>
											<th style="text-align:center;">培训专业</th>
											<th style="text-align:center;">准考证号</th>
										</tr>
										<c:forEach items="${extList}" var="ext" varStatus="extStatus">
											<tr>
												<td style="width: 5%;text-align: left;border-left: 0px;border-right: 0px;border-top: 0px;" >
													<input value="${ext.userFlow}"
														   name="recordFlow" type="checkbox"
													/>
												</td>
												<td>${ext.userName}</td>
												<td>${ext.idNo}</td>
												<td>${ext.speName}</td>
												<td>${ext.ticketNum}</td>
											</tr>
										</c:forEach>
										<c:if test="${empty extList}">
											<tr>
												<td colspan="5">无记录！</td>
											</tr>
										</c:if>
									</table>
								</div>
								<div class="page" style="padding-right: 40px;">
									<c:set var="pageView" value="${pdfn:getPageView(extList)}" scope="request"></c:set>
									<pd:pagination-jszy toPage="toPage"/>
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
