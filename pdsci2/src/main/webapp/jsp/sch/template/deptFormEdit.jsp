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
		<jsp:param name="jquery_validation" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>

<script type="text/javascript">
	top.document.mainIframe.orgFlow = '${param.orgFlow}';

	function reloadDeptFormCfgPage(){
		$('#deptFormCfg').submit();
	}

    function saveCfg(deptFlow,selVal){
        var code = 'form_dept_cfg_${param.rotationFlow}${param.orgFlow}'+deptFlow+'';
        var sData = {};
        sData['cfgCode'] = code;
        sData[code] = selVal;
        sData[code+'_ws_id'] = '${GlobalConstant.RES_WS_ID}';
        sData[code+'_desc'] = '科室表单定制配置';
        var url = '<s:url value="/sys/cfg/saveOne"/>';
        jboxPost(url,sData,function(resp){
            jboxTip('操作成功');
        },function(){
            jboxTip('操作失败');
        },false);
    }
</script>
</head>
<body>

<c:forEach items="${applicationScope.resFormDictList}" var="formDict">
    <c:if test="${formDict.dictId eq param.formName}">
        <c:set var="deptFormList" value="${formDict.deptForm}"/>
    </c:if>
</c:forEach>

<div class="mainright">
	<div class="content">
		<div style="width: 100%;height: 20px;">
			<form id="deptFormCfg" method="post" action="<s:url value='/res/cfg/deptFormCfgPage'/>">
				<input type="hidden" name="rotationFlow" value="${param.rotationFlow}"/>
				<input type="hidden" name="formName" value="${param.formName}"/>
				<div style="float: left;">
					机构：<select name="orgFlow" onchange="reloadDeptFormCfgPage();">
						<option value="">请选择</option>
						<c:forEach items="${orgList}" var="org">
							<option value="${org.orgFlow}" <c:if test="${org.orgFlow eq param.orgFlow}">selected</c:if>>${org.orgName}</option>
						</c:forEach>
					</select>
				</div>
			</form>
		</div>
		<div style="margin-top: 10px;height: 405px;overflow: auto;width: 100%;;">
			<table class="xllist">
				<tr>
					<th>轮转科室</th>
					<th>表单</th>
				</tr>
				<c:forEach items="${deptList}" var="dept">
                    <c:set var="cfgKey" value="form_dept_cfg_${param.rotationFlow}${param.orgFlow}${dept.schDeptFlow}"/>
					<tr>
						<td>${dept.deptName}</td>
						<td>
                            <select onchange="saveCfg('${dept.schDeptFlow}',this.value);">
                                <option value="">无</option>
                                <c:forEach items="${deptFormList}" var="deptForm">
                                    <option value="${deptForm.subPage}"
                                        <c:if test="${cfgMap[cfgKey] eq deptForm.subPage}">selected</c:if>
                                    >${deptForm.subPageName}</option>
                                </c:forEach>
                            </select>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty deptList}">
					<tr>
						<td colspan="2">请先选择机构！</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
    <div align="center">
        <input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();">
    </div>
</div>
</body>
</html>