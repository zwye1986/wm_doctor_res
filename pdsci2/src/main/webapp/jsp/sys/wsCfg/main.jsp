<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<style type="text/css">
		.selSysDept span{color: red;}
		.searchTable{
			border: 0px;
		}
		.searchTable tbody td{
			border: 0px;
			background-color: white;
			color: #575656;
		}
		.searchTable tbody th{
			border: 0px;
			background: white;
			color: #575656;
		}
	</style>
	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
		/*#boxHome .item{line-height: normal;  vertical-align:middle; }*/
	</style>

	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});

		function search(){
			jboxPostLoad("List" ,"<s:url value='/sys/wsCfg/list'/>" ,$("#doctorSearchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		function edit(wsId,isEdit) {
			var titleName="新增";
			if(wsId)
				titleName="修改";
			jboxOpen("<s:url value='/sys/wsCfg/edit'/>?wsId="+wsId+"&isEdit="+isEdit,titleName, 800, 500);
		}
		function updateDefault(wsId,isDefault) {
			jboxConfirm("确认设置该工作站配置为系统默认配置?", function () {
				var url = "<s:url value='/sys/wsCfg/updateDefault'/>?wsId="+wsId+"&isDefault="+isDefault;
				jboxPost(url, null, function(resp){
					if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
						jboxTip("设置成功！");
						search();
					}else{
						jboxTip("设置失败！");
					}
				}, null, false);
			});
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="doctorSearchForm" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<table class="basic searchTable" style="width:100%;margin-top: 10px;">
				<tr>
					<td>
						工作站：
						<select name="wsId" class="xlselect" onchange="toPage(1);" style="width: 150px;">
							<option value="">--请选择--</option>
							<c:forEach items="${licenseedWorkStation}" var="wsId">
								<option value="${wsId}">${workStationMap[wsId].workStationName }</option>
							</c:forEach>
						</select>
						<input type="button" value="新&#12288;增" class="search" onclick="edit('','N');"/>
					</td>
				</tr>
			</table>
		</form>
		<div id="List">

		</div>
	</div>
</div>
</body>
</html>