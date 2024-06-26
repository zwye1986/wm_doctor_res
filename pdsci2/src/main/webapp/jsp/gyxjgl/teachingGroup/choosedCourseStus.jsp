<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	/**
	 * 专业检索
	 */
	$(document).ready(function(){
		loadMajorJson();
	});
	function loadMajorJson(){
		var majorArray = new Array();
		var url = "<s:url value='/gyxjgl/majorCourse/searchMajorJson'/>";
		jboxPost(url,null,function(data){
			if(data!=null){
				for (var i = 0; i < data.length; i++) {
					var dictId=data[i].dictId;
					if(data[i].dictId==null){
						dictId="";
					}
					var dictName=data[i].dictName;
					if(data[i].dictName==null){
						dictName="";
					}
					majorArray[i]=new Array(dictId, dictName, dictId);
					if($("#result_Major").val() == dictId){
						$("#searchParam_Major").val(dictName);
					}
				}
			}
		},null,false);
		$("#searchParam_Major").suggest(majorArray,{
			attachObject:'#suggest_Major',
			dataContainer:'#result_Major',
			triggerFunc:function(majorId){

			},
			enterFunc:function(){

			}
		});
	}
	function adjustResults() {
		$("#suggest_Major").css("left",$("#searchParam_Major").offset().left);
		$("#suggest_Major").css("top",$("#searchParam_Major").offset().top+$("#searchParam_Major").outerHeight());
	}
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}
	function search() {
		$("#orgFlow").val("");
		if($("#orgName").val()!=""){
			$("#orgFlow").val($("#orgName").attr("flow"));
		}
		$("#searchForm").submit();
	}
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
				searchInput.attr("flow",$(this).attr("flow"));
				if(option.clickActive){
					option['clickActive']($(this).attr("flow"));
				}
			});
		};
	})(jQuery);
	$(function(){
		$("#orgName").likeSearchInit({
		});
	});
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/gyxjgl/teachingGroup/choosedCourseStus"/>" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<table style="width:100%;min-width: 1080px;margin: 10px 0px;border: none;">
				<tr>
					<td style="line-height: 260%;">
							学&#12288;&#12288;号：<input type="text" name="sid" value="${param.sid}" style="width: 137px;"/>
							<span style="padding-left:10px;"></span>
							姓&#12288;&#12288;名：<input type="text" name="doctorName" value="${param.doctorName}" style="width: 137px;"/>
							<span style="padding-left:10px;"></span>
							入学年级：<input style="width:137px;" value="${param.period}" name="period" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
							<span style="padding-left:10px;"></span>
							专业名称：<input  id="searchParam_Major"  placeholder="输入专业名称/代码"  style="width: 137px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
							<div id="suggest_Major" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 300px;"></div>
							<input type="hidden" id="result_Major" name="majorId" value="${param.majorId }"/><br/>
							<%----%>
							<%--<select style="width:141px;height:23px;" class="select" name="majorId">--%>
								<%--<option value=""></option>--%>
								<%--<c:forEach items="${dictTypeEnumGyMajorList}" var="major">--%>
									<%--<option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>[${major.dictId}]${major.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select><br/>--%>
							培养单位：<input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value" flow="${param.orgFlow}" style="width: 137px;"/>
							<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:65px;">
								<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 141px;border-top: none;position: relative;display: none;">
									<c:forEach items="${orgList}" var="org">
										<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
									</c:forEach>
								</div>
							</div>
							<input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow }"/>
							<%--<select style="width:141px;height:23px;" class="select" name="orgFlow">--%>
								<%--<option value=""></option>--%>
								<%--<c:forEach items="${orgList}" var="org">--%>
									<%--<option value="${org.orgFlow}" <c:if test="${org.orgFlow==param.orgFlow}">selected</c:if>>${org.orgName }</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
							<input type="button" class="search" onclick="toPage();" value="查&#12288;询" />
						</td>
					</tr>
				</table>

		</form>
		<div class="resultDiv">
			<table class="basic" width="100%">
				<tr style="font-weight: bold;">
				<td style="text-align: center;padding: 0px;">入学年级</td>
				<td style="text-align: center;padding: 0px;">班级</td>
				<td style="text-align: center;padding: 0px;">培养层次</td>
				<td style="text-align: center;padding: 0px;">培养类型</td>
				<td style="text-align: center;padding: 0px;">学号</td>
				<td style="text-align: center;padding: 0px;">姓名</td>
				<td style="text-align: center;padding: 0px;">专业名称</td>
				<td style="text-align: center;padding: 0px;">培养单位</td>
			</tr>
			<c:forEach items="${studentList}" var="student">
				<tr>
					<td style="text-align: center;padding: 0px;">${student['PERIOD']}</td>
					<td style="text-align: center;padding: 0px;">${student['CLASS_NAME']}</td>
					<td style="text-align: center;padding: 0px;">${student['TRAIN_TYPE_NAME']}</td>
					<td style="text-align: center;padding: 0px;">${student['TRAIN_CATEGORY_NAME']}</td>
					<td style="text-align: center;padding: 0px;">${student['SID']}</td>
					<td style="text-align: center;padding: 0px;">${student['USER_NAME']}</td>
					<td style="text-align: center;padding: 0px;">[${student['MAJOR_ID']}]${student['MAJOR_NAME']}</td>
					<td style="text-align: center;padding: 0px;">${student['ORG_NAME']}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty studentList}">
				<tr>
					<td colspan="99" style="text-align: center;">无记录！</td>
				</tr>
			</c:if>
		</table>
			<c:set var="pageView" value="${pdfn:getPageView(studentList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>	
</div>
</body>	
</html>