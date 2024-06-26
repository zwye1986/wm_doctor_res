<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	function save(){
		if(false==$("#searchForm").validationEngine("validate")){
			return false;
		}
		var url = "<s:url value='/jsres/manage/saveCfg'/>?deptFlow=${param.deptFlow}&recordFlow=${cfg.recordFlow}";
		var data = $('#searchForm').serialize();
		jboxStartLoading();
		jboxPost(url, data, function(resp) {
			if(resp=="保存成功！") {
				window.parent.search();
				jboxClose();
			}
			jboxEndLoading();
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
<div class="main_bd" id="div_table_0" style="">
	<form id="searchForm" method="post" >
		<div class="div_search" style="padding: 10px;">
			<input type="text" name="deptName" placeholder="可通过关键字检索" onkeyup="likeSearch(this.value);" class="input" />
		</div>
		<div class="div_search" style="padding: 10px;margin: 10px;float:left;overflow:auto;height: auto;max-height: 450px;width: 95%;">
				<c:forEach items="${allDicts}" var="dict">
						<div style="width: 24%;float: left;" deptName="${dict.dictName}">
							<label>
								<input type="radio" class="validate[required]" name="standardDeptId"  <c:if test="${cfg.standardDeptId eq dict.dictId}">checked</c:if>  value="${dict.dictId}"/>
									${dict.dictName}
							</label>
						</div>
				</c:forEach>
		</div>
	</form>
	<div class="button">
		<input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
		<input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
	</div>
</div>