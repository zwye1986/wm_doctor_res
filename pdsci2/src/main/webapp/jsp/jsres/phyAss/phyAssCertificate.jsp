<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	.searchTable tr{
		line-height: 40px;
	}
	.fixTrTd>td{
		border-bottom: 1px solid #e7e7eb;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		toPage();
	});
	function toPage(page) {
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/phyAss/phyAssCertificateList'/>",$("#searchForm").serialize(),false);
	 }

	 function allChooseGrade() {
		 var recordFlows = new Array();
		 var i = 0;
		 if($(".main_bd input[type='checkbox']").length<=0) {
			 jboxTip("请选择需要操作的数据！");
			 return;
		 }
		 $(".main_bd input[type='checkbox']:checked").each(function(){
			 var aa = $(this).attr("recordFlow");
			 if(aa != "" && aa != undefined) {
				 recordFlows[i] = $(this).attr("recordFlow");
				 i++;
			 }
		 });
		 var recordFlowsStr = recordFlows.join(",");
		 if(recordFlowsStr.length==0){
			 jboxTip("您还没有勾选！");
			 return;
		 }
		 var url = "<s:url value ='/jsres/phyAss/chooseGrade'/>?recordFlow="+recordFlowsStr+"&type=all";
		 var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='500' height='220' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		 jboxMessager(iframe,'生成证书',500,220);
	 }

	 function expertCertidicate() {
		 var url = "<s:url value='/jsres/phyAss/expertCertidicate'/>";
		 jboxTip("导出中…………");
		 jboxSubmit($("#searchForm"), url, null, null, false);
		 jboxEndLoading();
	 }
</script>

<div class="main_bd" id="div_table_0">
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="tabTag" name="tabTag" value="${param.tabTag}"/>
		<table class="searchTable">
			<tr>
				<td class="td_left">培训计划：</td>
				<td>
					<select name="planFlow" id="planFlow" class="select" style="width: 200px;">
						<option value="">全部</option>
						<c:forEach items="${contentList}" var="c">
							<option value="${c.planFlow}" ${planFlow eq c.planFlow?'selected':''}>${c.planContent}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">基&#12288;&#12288;地：</td>
				<td>
					<select name="orgFlow" id="orgFlow" class="select" style="width: 200px;">
						<option value="">全部</option
						<c:forEach items="${orgs}" var="org">
							<option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}
							>${org.orgName}</option>
						</c:forEach>
					</select>
				</td>

				<td class="td_left">培训专业：</td>
				<td>
					<select name="speId" id="speId" class="select" style="width: 200px;">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option value="${dict.dictId}" ${sepId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="doctorName" style="width: 195px" value="${doctorName}" class="input" />
				</td>
				<td  class="td_left">师资等级：</td>
				<td>
					<select name="gradeId" id="gradeId" class="select" style="width: 200px;">
						<option value="">全部</option>
						<option value="ordinary">普通师资</option>
						<option value="commonly">一般师资</option>
						<option value="other">其他师资</option>
					</select>
				</td>
				<td class="td_left">状&#12288;&#12288;态：</td>
				<td>
					<select name="gainCertificateId" id="gainCertificateId" class="select" style="width: 200px;">
						<option value="">全部</option>
						<option value="Y">已生成</option>
						<option value="N">未生成</option>
					</select>
				</td>
			</tr>
			<tr>
				<td id="func" colspan="8">
					<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
					<input class="btn_green" style="margin-left: 0px;" type="button" value="批量生成" onclick="allChooseGrade();"/>&nbsp;
					<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="expertCertidicate();"/>&nbsp;
				</td>
			</tr>
		</table>
	</form>
    </div>
   <div id="doctorListZi" style="padding: 10px 54px 10px 40px;box-sizing: border-box;width: 990px;">
    </div>
</div>
