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
		if(${not empty user.userSignUrl}) {
			var id = "ratateImg1"
			var viewer = new Viewer(document.getElementById(id), {
				url: 'data-original'
			});
		}
	});


	function editSupervisioUserTwo(type, userFlow) {
		var title = "新增";
		if (type == 'edit') {
			title = "修改";
		}
		var url = "<s:url value ='/jsres/supervisio/editSupervisioUserTwo'/>?userFlow=" + userFlow + "&type=" + type;
		jboxOpen(url, title, 800, 315);
	}

</script>
<div class="main_bd clearfix" style="width: 100%;margin-left: 20px">
	<table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid" >
		<thead>
		<tr>
			<th>专家名称</th>
			<th>手机号码</th>
			<th width="140px">专业</th>
			<th>签名照片</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<tr class="fixTrTd">
			<td>${user.userName}</td>
			<td>${user.userPhone}</td>
			<td>${user.resTrainingSpeName}</td>
			<c:if test="${empty user.userSignUrl}">
				<td>暂无</td>
			</c:if>
			<c:if test="${not empty user.userSignUrl}">
				<td  style="height: 100px;">
					<div>
						<ul >
							<li id="ratateImg1">
								<img src="${sysCfgMap['upload_base_url']}/${user.userSignUrl}" style="width: 80px;height: 80px;" >
							</li>
						</ul>
					</div>
				</td>
			</c:if>
			<td>
				<a class="btn_green" style="width: 60px;color:white;" href="javascript:void(0);" onclick="setUserSign('${user.userFlow}');">${empty user.userSignUrl?'':'重新'}上传</a>
				&#12288;<a style="width: 60px;color:white;" class="btn_green" href="javascript:void(0);" onclick="editSupervisioUserTwo('edit','${user.userFlow}');">修改</a>
			</td>
		</tr>
		</tbody>
	</table>
</div>



      
