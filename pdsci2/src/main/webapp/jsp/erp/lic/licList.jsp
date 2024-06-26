<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<jsp:param name="jquery_cxselect" value="true"/>
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
  function search(){
	  var form = $("#searchForm"); 
	  form.submit();
  }
  function add(){
	  var w = $('.mainright').width()*0.9;
	  var h = $('.mainright').height()*0.9;
	  var url="<s:url value='/erp/lic/edit'/>";
	  var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	  jboxMessager(iframe,'新增license文件',w,h,null,false);
  }
  function toPage(page){
	  $("#currentPage").val(page);
	  var form = $("#searchForm"); 
	  form.submit();
	}
  function customerInfo(customerFlow){
		var w = $('.mainright').width();
		var h = $('.mainright').height();
		var url = "<s:url value='/erp/crm/customerInfo'/>?customerFlow=" + customerFlow+"&type=open";
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'客户详细信息',w,h,null,false);
	}
  function contractInfo(contractFlow) {
		var mainIframe = window.parent.frames["mainIframe"];
		var width = mainIframe.document.body.scrollWidth;
		var url = "<s:url value='/erp/crm/contractInfo'/>?contractFlow="+contractFlow+"&status=main&type=open";
		jboxOpen(url, "合同详细信息", 1000, 600);
	}
  function dl(fileFlow){
	  var url='<s:url value="/pub/file/down?fileFlow="/>'+fileFlow;
	  window.location.href=url;
  }
  function reSend(licFlow){
	  var url="<s:url value='/erp/lic/sendLicKey'/>?licFlow="+licFlow;
	  var msg="确认重新发送license文件？";
	  jboxConfirm(msg,function(){
		  jboxGet(url,null,function(data){
			 },null,true);
	  },null);
  }
  function reCreateLicFile(licFlow){
	  var url="<s:url value='/erp/lic/reCreateLicFile'/>?licFlow="+licFlow;
	  var msg="确认重新生成license文件？";
	  jboxConfirm(msg,function(){
		  jboxGet(url,null,function(data){
			  if(data=='${GlobalConstant.OPRE_SUCCESSED}'){
				  search();
			  }
			 },null,true);
	  },null);
  }
</script>
</head>
<body>
    <div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			     <form id="searchForm" action="<s:url value='/erp/lic/list'/>" method="post">
			       <div style="margin-bottom: 10px">
			       <input id="currentPage" type="hidden" name="currentPage" value=""/>
                                                            客户名称：<input type="text" name="customerName" value="${param.customerName}" placeholder="客户名称/别名" class="xltext" style="width: 180px;"/>
                                                            合&nbsp;同&nbsp;号：<input type="text" name="contractNo" value="${param.contractNo}" placeholder="合同号" class="xltext" style="width: 180px;"/>                                        
                    <%--  产品名称：<input type="text" name="productName" value="${param.productName}" placeholder="产品/项目名称" class="xltext" style="width: 180px;"/> --%>
                    <input type="button" class="search"  onclick="search();" value="查&#12288;询">
                    <input type="button" class="search"  onclick="add();" value="新&#12288;增">
                   </div>		     
			     </form>
			     <table class="xllist">
			        <colgroup>
			           <col width="8%"/>
			           <col width="9%"/>
			           <col width="13%"/>
			           <col width="8%"/>
			           <col width="15%"/>
			           <col width="8%"/>
			           <col width="7%"/>
			           <col width="7%"/>
						<col width="8%"/>
						<col width="8%"/>
			           <col width="7%"/>
			        </colgroup>
			        <tr>
			           <th>客户名称</th>
			           <th>合同号</th>
			           <th>合同产品/项目名称</th>
			           <th>开发语言</th>		
			           <th>授权产品名称</th>
			           <th>授权文件到期日</th>
			           <th>申请部门</th>
			           <th>申请人</th>
						<th>操作时间</th>
						<th>安装信息</th>
			           <th>操作</th>
			        </tr>
			        <c:forEach items="${licList }" var="lic">
			        <tr>
			           <td><a title="查看客户信息" href="javascript:customerInfo('${lic.customerFlow }')">${lic.customerName }</a></td>
			           <td>
			              <c:if test="${not empty lic.contractNo }">
			                <a title="查看合同信息" href="javascript:contractInfo('${lic.contractFlow }');">${lic.contractNo }</a>
			              </c:if>
			              <c:if test="${empty lic.contractNo }">--</c:if>  
			           </td>
			           <td style="text-align: center;padding-left: 3px;">
			              <c:forEach items="${productTypeNameMap[lic.licFlow] }" var="productTypeName" varStatus="num">
			                  <li style="width:100%;">
			                  ${productTypeName }
			                  </li>
			              </c:forEach>
			           </td>
			           <td>${lic.developLanguage }</td>
			           <td style="text-align: center;padding-left: 3px;">
			              <c:forEach items="${workStationNameMap[lic.licFlow] }" var="wsName" varStatus="num">
			                  <li style="width:100%;">
			                  ${wsName }
			                  </li>
			              </c:forEach>
			           </td>
			           <td>${lic.vaildDate }</td>
			           <td>${lic.applyDeptName }</td>
			           <td>${lic.applyUserName }</td>
						<td>${pdfn:transDateTimeForPattern(lic.createTime,"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm") }</td>
						<td>${lic.licContent }</td>
			           <td>
			             <c:if test="${not empty lic.fileFlow }">
			               [<a href="javascript:void(0);" onclick="reCreateLicFile('${lic.licFlow}');">重新生成</a>]
			               [<a href="javascript:void(0);" onclick="dl('${lic.fileFlow}');">下载</a>]
			               [<a href="javascript:void(0);" onclick="reSend('${lic.licFlow}');">发送</a>]
			             </c:if>
			           </td>
			        </tr>
			        </c:forEach>
			        <c:if test="${empty licList }">
			           <tr>
			              <td colspan="8">无记录</td>
			           </tr>
			        </c:if>
			     </table>
			     <c:set var="pageView" value="${pdfn:getPageView(licList)}" scope="request"></c:set>
			     <pd:pagination toPage="toPage"/>
	        </div>
	</div>
   </div>
</body>
</html>