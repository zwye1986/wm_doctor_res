<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(function () {
		<c:forEach items="${list}" var="sign" varStatus="status">
		var id = "ratateImg${status.index+1}";
		if(${not empty sign.userSignUrl}) {
			var viewer = new Viewer(document.getElementById(id), {
				url: 'data-original'
			});
		}
		</c:forEach>
	});

	$(document).ready(function(){
		$("#currentPage").val("${param.currentPage}");
	});

	function editSupervisioUser(type, userFlow) {
		var title = "新增";
		if (type == 'edit') {
			title = "修改";
		}
		var url = "<s:url value ='/jsres/supervisio/editSupervisioUser'/>?userFlow=" + userFlow + "&type=" + type+"&suAoth=${suAoth}";
		jboxOpen(url, title, 800, 315);
	}

</script>
<div>
	<font style="font-weight:bold;margin-left: 10px"><span style="color: red">*</span>注：新增专家初始密码均为“JSzp123456#”</font>
</div>
<c:if test="${empty list}">
	<div class="search_table" style="width: 100%;padding: 0 20px">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>专家姓名</th>
				<th>手机号码</th>
				<th>专家分类</th>
				<th>专业</th>
				<th>签名照片</th>
				<th>操作</th>
			</tr>
			<tr>
				<td colspan="6" >无记录！</td>
			</tr>
		</table>
	</div>
</c:if>
<c:if test="${not empty list}">
	<div class="main_bd clearfix" style="width: 100%;padding: 0 20px">
		<table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid" >
			<thead>
			<tr>
				<th>专家姓名</th>
				<th>手机号码</th>
				<th>专家分类</th>
				<th>专业</th>
				<th>签名照片</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="s" varStatus="status">
				<tr class="fixTrTd">
					<td>${s.userName}</td>
					<td>${s.userPhone}</td>
					<td>${s.userLevelName}</td>
					<td>${s.resTrainingSpeName}</td>
					<c:if test="${empty s.userSignUrl}">
						<td>暂无</td>
					</c:if>
					<c:if test="${not empty s.userSignUrl}">
						<td  style="height: 100px;">
							<div>
								<ul >
									<li id="ratateImg${status.index+1}">
										<img src="${sysCfgMap['upload_base_url']}/${s.userSignUrl}" style="width: 80px;height: 80px;" >
									</li>
								</ul>
							</div>
						</td>
					</c:if>
					<td>
						<a class="btn_blue" style="color: white" href="javascript:void(0);" onclick="editSupervisioUser('edit','${s.userFlow}');">修改</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

<div class="page" style="text-align: center">
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>
      
