<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
function getChangeOrgDetail(doctorFlow, recordFlow){
	var url = "<s:url value='/jsres/institute/getChangeOrgDetail'/>?doctorFlow=" + doctorFlow + "&recordFlow="+recordFlow;
	jboxOpen(url, "详情", 1050, 550);
}
function search(){
	var url="<s:url value='/jsres/institute/changeBase'/>";
	jboxPostLoad("content",url,$("#inForm").serialize(),false);
}
function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	search();
}
function check(obj){
	var url="<s:url value='/jsres/institute/changeBase'/>";
	jboxPostLoad("content",url,$("#inForm").serialize(),false);
}
</script>
<div class="main_hd">
    <h2 class="underline">基地变更查询</h2> 
</div>
<div class="main_bd">
	<div class="div_table">
		<div class="div_search">
		<form id="inForm">
			<input type="hidden" name="currentPage" id="currentPage"/>
	      	姓&#12288;&#12288;名：<input type="text" name="doctorName" value="${param.doctorName}" class="input" style="width: 100px;"/>&#12288;
      	 	届&#12288;&#12288;别：
			<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
		    <label style="cursor: pointer;"><input type="checkbox" name="statusFlag" value="${GlobalConstant.FLAG_Y}"<c:if test="${param.statusFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> onclick="check(this);">&nbsp;未通过记录&#12288;</label>
		    <input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>
         </form>
         </div>
      
           <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
              <col width="10%"/>
              <col width="10%"/>
              <col width="10%"/>
              <col width="10%"/>
              <col width="15%"/>
              <col width="10%"/>
              <col width="10%"/>
              <col width="15%"/>
            </colgroup>
	           <thead>
	           <tr>
	           	  <th>姓名</th>
	              <th>培训专业</th>
	           	  <th>届别</th>
	           	  <th>审核时间</th>
	              <th>原培训基地</th>
	              <th>变更后基地</th>
	              <th>附件</th>
	              <th>操作</th>
	           </tr>
	           </thead>
	           <tbody>
	           <c:forEach items="${historyExts }" var="docOrgHistoryExt">
	           <tr>
	           	   <td>${docOrgHistoryExt.resDoctor.doctorName}</td>
	           	   <td>${docOrgHistoryExt.historyTrainingSpeName}</td>
	           	   <td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
	           	   <td>${pdfn:transDate(docOrgHistoryExt.inDate)}</td>
	           	   <td>${docOrgHistoryExt.historyOrgName}</td>
	           	   <td>${docOrgHistoryExt.orgName}</td>
	           	   <td>
	           	   <c:if test="${not empty docOrgHistoryExt.speChangeApplyFile}">
	           	   		[<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }" target="_blank">查看附件</a>]&nbsp;
	           	   </c:if>
	           	   </td>
	               <td>
	               		<a class="btn" onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
	               </td>	
	           </tr>
	           </c:forEach>
	           
	           <c:if test="${empty historyExts }">
	           <tr>
	           	   <td colspan="8">无记录</td>
           	   </tr>
	           </c:if>
	           </tbody>
           </table>
		</div>
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(historyExts)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>	 
        </div>
</div>
