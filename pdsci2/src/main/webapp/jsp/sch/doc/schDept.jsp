<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	table.basic th,table.basic td{text-align: center;padding: 0;}
</style>

<script type="text/javascript">
	function search(){
		if(false==$("#sysDeptForm").validationEngine("validate")){
			return ;
		}
		if($("#orgFlow option:selected").val()==""){
			jboxTip("请选择一个培训基地");
			return;
		}
		$("#searchForm").submit();
	}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	$(function(){
		changeView();
		var datas=[];
		<c:forEach items="${schDeptList}" var="dept">
		var d={};
		d.id="${dept.schDeptFlow}";
		d.text="${dept.schDeptName}";
		datas.push(d);
		</c:forEach>
		initDepts(datas);
		initOrgs();
	});
	function initOrgs()
	{
		var datas=[];
		<c:forEach items="${orgList}" var="dept">
			var d={};
			d.id="${dept.orgFlow}";
			d.text="${dept.orgName}";
			datas.push(d);
		</c:forEach>
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
			searchOrgSchDept(this.id);
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}
	function initDepts(datas)
	{
		var itemSelectFuntion = function(){
			$("#schDeptFlow").val(this.id);
		};
		$.selectSuggest('trainSchDept',datas,itemSelectFuntion,"schDeptFlow",true);
	}
	function heightFiexed(){
		var height = document.body.clientHeight-110;
		$("#tableContext").css("height",height+"px");	
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
	}
	
	onresize = function(){
		heightFiexed();
	};
	
	//切换展示形式
	function changeView(){
		var viewBox = $("#viewBox")[0];
		if(viewBox){
			$(".docCountView").toggle(!viewBox.checked);
			$(".docNameView").toggle(viewBox.checked);
		}
		heightFiexed();
	}
	function exportDoctor(){
		<c:if test="${roleFlag==GlobalConstant.USER_LIST_GLOBAL }">
			var orgFlow=$("#orgFlow").val();
			if(orgFlow=="")
			{
				jboxInfo("请选择基地之后，再进行导出！");
				return false;
			}
		</c:if>
		var url = "<s:url value='/sch/template/exportExcel/${roleFlag}'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}

    function exportDoctor2(){
        <c:if test="${roleFlag==GlobalConstant.USER_LIST_GLOBAL }">
        var orgFlow=$("#orgFlow").val();
        if(orgFlow=="")
        {
            jboxInfo("请选择基地之后，再进行导出！");
            return false;
        }
        </c:if>
        var url = "<s:url value='/sch/template/exportExcel2/${roleFlag}'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

	function searchOrgSchDept(orgFlow)
	{
		initDepts([]);
		var url = "<s:url value='/sch/doc/getSchDept?orgFlow='/>"+orgFlow;
		var courseArray = new Array();
		jboxGetAsync(url,null,function(data){
			var datas=[];
			if(data){
				for (var i = 0; i < data.length; i++) {
					var schDeptFlow=data[i].schDeptFlow;
					var schDeptName=data[i].schDeptName;
					var d={};
					d.id=schDeptFlow;
					d.text=schDeptName;
					datas.push(d);
				}
			}
			initDepts(datas);
		},null,false);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value='/sch/doc/schDept/${roleFlag}'/>" method="post">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">开始日期：</label>
						<input name="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" value="${empty param.startDate?startDate:param.startDate}" class="qtext validate[required]"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">结束日期：</label>
						<input name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" value="${empty param.endDate?endDate:param.endDate}" class="qtext validate[required]"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">培训基地：</label>
						<input id="trainOrg" name="orgName" value="${empty param.orgName?orgName:param.orgName}"  class="toggleView qtext" type="text"  autocomplete="off"  />
						<input type="hidden" name="orgFlow" value="${empty param.orgFlow?orgFlow:param.orgFlow}" id="orgFlow">
					</div>
					<div class="inputDiv">
						<label class="qlable">科&#12288;&#12288;室：</label>

						<input id="trainSchDept" name="schDeptName" value="${param.schDeptName}"  class="toggleView qtext" type="text"  autocomplete="off"  />
						<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}" id="schDeptFlow">
						<%--<select id="schDeptFlow" name="schDeptFlow" class="qselect">--%>
							<%--<option value="">全部</option>--%>
							<%--<c:forEach items="${schDeptList}" var="schDept">--%>
								<%--<option value="${schDept.schDeptFlow}" ${param.schDeptFlow eq schDept.schDeptFlow?'selected':''}>${schDept.schDeptName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					</div>
					<div class="inputDiv" style="min-width: 128px;max-width: 128px;">
						<input type="checkbox" id="viewBox" name="viewName" value="${GlobalConstant.FLAG_Y}" <c:if test="${GlobalConstant.FLAG_Y eq param.viewName}">checked</c:if> onclick="changeView();"/>
						查看医师姓名
					</div>
					<div class="lastDiv" style="min-width: 182px;max-width: 182px;">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
						<input type="button" class="searchInput" value="导&#12288;出" onclick="exportDoctor();" title="导出学员排班表"/>
					</div>
				</div>
			</form>
						
			<div id="tableContext" style="width:100%; margin-top: 10px;margin-bottom: 10px;overflow: auto;" onscroll="tableFixed(this);">
				<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="basic">
						<tr>
							<th style="width: 200px;min-width: 200px;max-width: 200px;">
								轮转科室
							</th>
							<c:forEach items="${titleDate}" var="title">
								<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">
									<c:set var="year" value="${title.substring(0,4)}"/>
									<c:set var="week" value="${title.substring(5)}"/>
									<c:set var="title" value="${year}年<br/>${week}周"/>
								</c:if>
								<th style="width: 180px;min-width: 180px;">${title}</th>
							</c:forEach>
						</tr>
					</table>
				</div>
				<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
					<table class="basic" style="min-width: 200px;max-width: 200px;">
						<tr>
							<th style="width: 200px;width: 200px;min-width: 200px;">
								轮转科室
							</th>
						</tr>
						<c:forEach items="${schDeptList}" var="schDept">
							<c:if test="${empty param.schDeptFlow || param.schDeptFlow ==schDept.schDeptFlow}">
							<tr>
								<td style="background: white;" class="toFiexdDept">${schDept.schDeptName}</td>
							</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
				<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
				<table class="basic">
					<tr>
						<th style="min-width: 200px;max-width: 200px;">轮转科室</th>
					</tr>
				</table>
				</div>
				<table class="basic">
					<tr>
						<th style="width: 200px;min-width: 200px;">
							轮转科室
						</th>
						<c:forEach items="${titleDate}" var="title">
							<c:if test="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}">
								<c:set var="year" value="${title.substring(0,4)}"/>
								<c:set var="week" value="${title.substring(5)}"/>
								<c:set var="title" value="${year}年<br/>${week}周"/>
							</c:if>
							<th style="width: 180px;min-width: 180px;">${title}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${schDeptList}" var="schDept">
						<c:if test="${empty param.schDeptFlow || param.schDeptFlow ==schDept.schDeptFlow}">
						<tr>
							<td class="fixedBy">${schDept.schDeptName}</td>
							<c:forEach items="${titleDate}" var="title">
								<c:set var="key" value="${schDept.schDeptFlow}${title}"/>
								<td class="nameTitle" style="line-height: 18px;"
									<c:if test="${!empty resultListMap[key]}">
									title="
										<table>
											<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
												<tr>
													<td>${result.doctorName}<br/>(${result.schStartDate}~${result.schEndDate})</td>
<%-- 													(${result.schStartDate}~${result.schEndDate}) --%>
												</tr>
											</c:forEach>
										</table>
									"
									</c:if>
								>
									<div class="docCountView">
										${resultListMap[key].size()+0}
									</div>
									<c:if test="${!empty resultListMap[key]}">
										<c:forEach items="${resultListMap[key]}" var="result" varStatus="status">
											<div class="docNameView" style="display: none;width: 80px;min-width: 80px;word-wrap:break-word;text-align: left;">●${result.doctorName}</div>
										</c:forEach>
									</c:if>
								</td>
							</c:forEach>
						</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
	</div>
</div>
</div>
</body>
</html>