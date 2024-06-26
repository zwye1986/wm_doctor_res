
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
</style>
<script type="text/javascript">

	/**
	 *模糊查询加载
	 */
	(function($){
		$.fn.likeSearchInit = function(option){
			option.clickActive = option.clickActive || null;

			var searchInput = this;
			searchInput.on("keyup focus",function(){
				$("#boxHome").show();
				if($.trim(this.value)){
					$("#boxHome .item").hide();
					var items = $("#boxHome .item[value*='"+this.value+"']").show();
					if(!items){
						$("#boxHome").hide();
					}
				}else{
					$("#boxHome .item").show();
				}
			});
			searchInput.on("blur",function(){
				if(!$("#boxHome.on").length){
					$("#boxHome").hide();
				}
			});
			$("#boxHome").on("mouseenter mouseleave",function(){
				$(this).toggleClass("on");
			});
			$("#boxHome .item").click(function(){
				$("#boxHome").hide();
				var value = $(this).attr("value");
				$("#itemName").val(value);
				searchInput.val(value);
				if(option.clickActive){
					option['clickActive']($(this).attr("flow"));
				}
			});
		};
	})(jQuery);

	//页面加载完成时调用
	$(function(){
		$("#orgSel").likeSearchInit({});
	});

	function toDeptFlow(deptFlow) {
		$("#deptFlow").val(deptFlow);
	}

	function changeStatus() {
		if ($("#orgSel").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
			$("#deptFlow").val("");
		}
	}

	$(document).ready(function(){
		toPage(1);
	});
	function toPage(page) {
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/deptActivityStatistics/list'/>?roleFlag=${param.roleFlag}",$("#searchForm").serialize(),false);
	}
	function exportExcel(){
		var url = "<s:url value='/jsres/deptActivityStatistics/exportList'/>?roleFlag=${param.roleFlag}";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
<div class="main_hd">
	<h2 class="underline">科室活动统计</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
		<form id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<table class="searchTable">
				<tr>
					<td class="td_left">
						开始时间：
					</td>
					<td>
						<input type="text" id="startDate" name="startTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00'})">
					</td>
					<td class="td_left">
						结束时间：
					</td>
					<td>
						<input type="text" id="endDate" name="endTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 23:59:59'})">
					</td>
					<td class="td_left">
						科&#12288;&#12288;室：
					</td>
					<td>
<%--						<select name="deptFlow" class="select">--%>
<%--							<option value="">全部</option>--%>
<%--							<c:forEach items="${depts}" var="dept">--%>
<%--								<option value="${dept.deptFlow}" >${dept.deptName}</option>--%>
<%--							</c:forEach>--%>
<%--						</select>--%>
						<input type="hidden" id="deptFlow" name="deptFlow" value="">
						<input id="orgSel" class="toggleView input" type="text"  placeholder="请选择科室"
							   style="width: 200px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 262px -4px;"
							   name="deptName" value="" autocomplete="off" title="${param.deptName}" onkeydown="changeStatus();" onkeyup="changeStatus();" onmouseover="this.title = this.value"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">
							<div id="boxHome" style="max-height: 200px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 200px;border-top: none;position: relative;display: none;">
								<p class="item" flow="" value="全部" onclick="toDeptFlow('');" style="height: 30px;padding-left: 10px;text-align: left;">全部</p>
								<c:forEach items="${depts}" var="dept">
									<p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" onclick="toDeptFlow('${dept.deptFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${dept.deptName}</p>
								</c:forEach>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="td_left">
						不包含0：
					</td>
					<td>
						<input type="checkbox" name="notNull" value="Y">
					</td>

					<td  id="func" colspan="4">
						&nbsp;<input class="btn_green" style="margin-left: 30px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
						<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="doctorListZi">
	</div>
</div>