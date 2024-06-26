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
<script type="text/javascript">
	function save(){
		if(false==$("#searchForm").validationEngine("validate")){
			return false;
		}
		var url = "<s:url value='/sch/cfg/saveCfg'/>?schDeptFlow=${param.schDeptFlow}&recordFlow=${cfg.recordFlow}";
		var data = $('#searchForm').serialize();
		jboxPost(url, data, function(resp) {
			if(resp=="保存成功！") {
				window.parent.frames['mainIframe'].window.search('${dept.orgFlow}');
				jboxClose();
			}
		}, null, true);
	}
	//模糊查询
	function likeSearch(name){
		if(name){
			$("[deptName]").hide();
			$("[deptName*='"+name+"']").show();
		}else{
			$("[deptName]").show();
		}
	}
</script>
		<div class="content">
			<div class="title1 clearfix">
				<div id="tagContent">
					<div class="tagContent selectTag" id="tagContent0">
	<form id="searchForm" method="post" >
	<div class="div_search" style="padding: 10px;">
		<input type="text" name="deptName" placeholder="可通过关键字检索" onkeyup="likeSearch(this.value);" class="input" />
    </div>
		<div class="div_search" style="padding: 10px;margin: 10px;float:left;overflow:auto;height: auto;max-height: 450px;width: 95%;">
			<c:forEach items="${allDicts}" var="dict">
				<div style="width: 24%;float: left;" deptName="${dict.dictName}">
					<table cellpadding="0" cellspacing="0" class="basic" style="width: 100%;border: 0px;">
						<tr>
							<td style="width: 100%;border: 0px;"  cellpadding="0" cellspacing="0">
								<label>
									<input type="radio" class="validate[required]" name="standardDeptId"  <c:if test="${cfg.standardDeptId eq dict.dictId}">checked</c:if>  value="${dict.dictId}"/>
										${dict.dictName}
								</label>
							</td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</div>
	</form>
	<div class="button">
		<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
		<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
	</div>
</div>
</div>
</div>
</div>