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
 $(function(){
		loadBaseInfo();
	});
	function selectTag(url, id) {
	    // 操作标签
	    var tag = document.getElementById("tags").getElementsByTagName("li");
	    var taglength = tag.length;
	    for (i = 0; i < taglength; i++) {
	        tag[i].className = "ui-bluetag";
	    }
	    document.getElementById(id).parentNode.className = "selectTag";
	    // 操作内容
	    jboxLoad("contentDiv",url,true);
	}
	function loadBaseInfo(){
		selectTag('<s:url value="/gcp/proj/baseInfo?projFlow=${param.projFlow}&editFlag=${param.editFlag}"/>&view=${GlobalConstant.FLAG_Y}','jbxx');
		//jboxLoad("contentDiv",'<s:url value="/gcp/proj/baseInfo?projFlow=${param.projFlow}&editFlag=${param.editFlag}"/>&view=${GlobalConstant.FLAG_Y}',true);
	}
	 function loadResearchUser(){
		selectTag('<s:url value="/gcp/proj/projUser?projFlow=${param.projFlow}"/>&view=${GlobalConstant.FLAG_Y}', 'yjtd');
	}
	function loadApplyFile(){
		selectTag('<s:url value="/gcp/rec/applyFile?projFlow=${param.projFlow}"/>&view=${GlobalConstant.FLAG_Y}', 'xmwj');
	}
	function changeStatus(statusId){
		var form = $("#editCheckForm");
		if(form.validationEngine("validate")){
			jboxConfirm("确认审核操作？",function(){
				var url = "<s:url value='/gcp/proj/changeProj'/>";
				var requestData = form.serialize()+"&projStatusId="+statusId;
				jboxPost(url,requestData,function(resp){
					if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
						doBack();
						//window.parent.frames["mainIframe"].window.search();
						//jboxClose();
					}
				},null,true);
			},null);
		}
	}
	
	function doBack() {
		window.location.href="<s:url value='/gcp/proj/checkList'/>";
	}
</script>

<style type="text/css">
	#table1 th {background: #fff; width: 110px;border:none;}
	#table1,#table1 td{border:none;}
</style>
</head>
<body>
<div class="mainright">
  <div class="content" style="margin-top: 10px;">
  	<ul id="tags">
		<li class="selectTag"><a onclick="loadBaseInfo()" href="javascript:void(0)" id="jbxx">基本信息</a></li>
		<%-- <c:if test="${!empty form.proj.applyUserFlow }">
		<li><a onclick="loadResearchUser()" href="javascript:void(0)" id="yjtd">研究团队</a></li>
		</c:if> --%>
		<li><a onclick="loadApplyFile()" href="javascript:void(0)" id="xmwj">项目文件</a></li>
		&#12288;<input type="button" value="返&#12288;回" onclick="doBack();" class="search"/>
    </ul>
    <table class="i-trend-table" cellpadding="0" cellspacing="0" >
    <tbody>
        <tr>
          <td>
            <div id="contentDiv" style="height: 280px;overflow:auto;border:none;">
			</div>
          </td>
        </tr>
    </tbody>
    </table>
  	<form id="editCheckForm"  method="post" style="margin-top: 10px;position: relative;">
  	<fieldset>
		<legend>审核意见</legend>
			<table style="width: 100%;">
				<tr>
				<td><textarea  name="auditContent" class="validate[required]"  style="width:100%;height: 200px;" placeholder="请填写审核意见"></textarea></td>
				</tr>
			</table>
	</fieldset>
       <p class="button">
       		<input type="hidden" name="projFlow" value="${form.proj.projFlow}"/> 
       		<input type="button" value="审核通过" onclick="changeStatus('${gcpProjStatusEnumPassed.id}');" class="search"/>
       		<input type="button" value="审核不通过" onclick="changeStatus('${gcpProjStatusEnumNoPassed.id}');" class="search"/>
	    </p>
   	</form>
  </div>
</div>
</body>
</html>