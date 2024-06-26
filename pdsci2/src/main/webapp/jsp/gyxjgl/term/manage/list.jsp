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
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
function importExl(){
	jboxOpen("<s:url value='/gyxjgl/majorCourse/importCourseMajor'/>","导入",600,180);
}
var year='${year}';
function edit(flow){
	var url = "<s:url value='/gyxjgl/term/manage/edit'/>?flow="+flow+"&isEdit=Y";
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'课表基本信息维护',756,300,null,false);
	}
function add(){
	var url = "<s:url value='/gyxjgl/term/manage/edit'/>";
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'课表基本信息添加',756,300,null,false);
}
function search(){
	if(!$("#recSearchForm").validationEngine("validate")){
		return;
	}
	jboxStartLoading();
	var form=$("#recSearchForm");
	if($("#searchParam_Course").val()==""){
		$("#result_Course").val("");
	}
	if($("#searchParam_Major").val()==""){
		$("#result_Major").val("");
	}
	$("#assumeOrgFlow").val($("#assumeOrgName").attr("flow"));
    form.submit();
}

function loadCourseList(){
	var courseArray = new Array();
	var url = "<s:url value='/gyxjgl/majorCourse/searchCourseJson?planYear='/>"+$("[name='sessionNumber']").val();
	jboxGetAsync(url,null,function(data){
		if(data!=null){
			for (var i = 0; i < data.length; i++) {
				var courseFlow=data[i].courseFlow;
				if(data[i].courseFlow==null){
					courseFlow="";
				}
				var courseName=data[i].courseName;
				if(data[i].courseName==null){
					courseName="";
				}
				var courseCode=data[i].courseCode;
				if(data[i].courseCode==null){
					courseCode="";
				}
				courseArray[i]=new Array(courseFlow,courseName,courseCode);
				if($("#result_Course").val() == courseFlow){
					$("#searchParam_Course").val(courseName);	
				}
			}
			jboxStartLoading();
			$("#searchParam_Course").suggest(courseArray,{
				attachObject:'#suggest_Course',
				dataContainer:'#result_Course',
				triggerFunc:function(courseFlow){
				
				},
			    enterFunc:function(courseFlow){
			    
			    }
			});
			jboxEndLoading();
		}
	},null,false);
}
$(function(){
	loadCourseList();
	$(".weekNum").click(function(e){
		e.stopPropagation();
	});
});
function adjustResults() {
	$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
	$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
}
function toPage(page){
	$("#currentPage").val(page);
	search();
}
function loadDetail(flow,weekNumber,refreshFlag)
{
	var assumeOrgFlow=$("#assumeOrgFlow").val();
	var courseFlow=$("#result_Course").val();
	var classTeacherName=$("#classTeacherName").val();
	//当前授课组对应课程：courseFlow2
	var url = "<s:url value='/gyxjgl/term/manage/loadDetail?roleFlag=${roleFlag}&recordFlow='/>" + flow+"&assumeOrgFlow="+assumeOrgFlow+"&courseFlow="+courseFlow
			+"&classTeacherName="+encodeURI(encodeURI(classTeacherName))+"&weekNumber="+weekNumber+"&courseFlow2=${courseFlow2}";
	jboxLoad(flow+"_tbody", url, true);
	if(refreshFlag == "1"){
		$("#"+flow+"_tbody").show();
		$("#"+flow+"_button").show();
	}else if(refreshFlag == "2"){
		$("#"+flow+"_tbody").hide();
		$("#"+flow+"_button").hide();
	}else{
		$("#"+flow+"_tbody").toggle();
		$("#"+flow+"_button").toggle();
	}
}
function before(flow,begain)
{
	var num=$("#"+flow+"_weekNumber").val();
	if(num!=""&&parseInt(num)>0)
	{
		var newNum=parseInt(num)-1;
		begain=parseInt(begain);
		if(newNum>=begain)
		{
			$("#"+flow+"_weekNumber").val(newNum);
			loadDetail(flow,newNum,'1');
		}
	}
}
function next(flow,end)
{
	var num=$("#"+flow+"_weekNumber").val();
	if(num=="")
	{
		num=2;
	}
	var newNum=parseInt(num)+1;
	end=parseInt(end);
	if(newNum<=end)
	{
		$("#"+flow+"_weekNumber").val(newNum);
		loadDetail(flow,newNum,'1');
	}
}
function toogleTerm(flow){
	var html=$("#"+flow+"_tbody").html();
	if(!html){
		loadDetail(flow,$("#"+flow+"_weekNumber").val());
	}else{
		$("#"+flow+"_tbody").toggle();
		$("#"+flow+"_button").toggle();
	}
}
function reload(){
	$("#searchParam_Course").val("");//清空上前面学年的课程信息
	$("#result_Course").val("");
	loadCourseList();//重新加载某学年的课程
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
	$("#assumeOrgName").likeSearchInit({
	});
	<c:forEach items="${terms}" var="term">
		toogleTerm('${term.recordFlow}');
	</c:forEach>
	$('.removeEvent').on('click',function(e){e.stopPropagation();});
});
	function arrangePlan(obj,day){
		if(day != "" && day != null && day != undefined){
			<%--var memo = "";--%>
			<%--var exitFlag = false;--%>
			<%--<c:forEach items="${limitList}" var="limit" >--%>
				<%--if(day >= '${limit.beginTime}' && day <= '${limit.endTime}'){--%>
					<%--memo = '${limit.memo}';--%>
					<%--exitFlag = true;--%>
				<%--}--%>
			<%--</c:forEach>--%>
			<%--if(exitFlag){--%>
				<%--jboxInfo(memo+"期间不可排课！",200);--%>
				<%--return;--%>
			<%--}--%>
			jboxInfo(day,200);
			return;
		}
		//必修课 分班排课
		var termFlow=$(obj).attr("term_flow");
		var classTime=$(obj).attr("class_time");
		var classOrder=$(obj).attr("class_order");
		var recordFlow=$(obj).attr("recordFlow");
		//非授课组对应课程 做新增排课操作
		if('${roleFlag}'=='skz' && $(obj).attr("course_flow") != '${courseFlow2}'){
			recordFlow = "";
		}
		//选修课(修改所需排课流水号)
		var map = {};
		var flow = $(obj).attr("flow");
		if(${courseTypeId eq 'Public'}){
			$(".nameTitle2").each(function(){
				if($(this).attr("flow") == flow){
					var term_flow = $(this).attr("term_flow");
					map[term_flow]="";
					if($(this).attr("course_flow")=="${courseFlow2}"){
						map[term_flow]=$(this).attr("recordFlow");
					}
				}
			});
		}
		var url = "<s:url value='/gyxjgl/term/manage/courseAdd?roleFlag=${roleFlag}&termFlow='/>"+termFlow+"&classTime="+classTime+"&classOrder="+encodeURI(encodeURI(classOrder))+"&recordFlow="+recordFlow+"&courseTypeId=${courseTypeId}&recordMap=";//+JSON.stringify(map);
		jboxOpen(url, "增加排课",460,260);
	}
	function delInfo(termFlow,recordFlow,courseFlow){
		jboxConfirm("确定删除此课吗？",function(){
			jboxPost("<s:url value='/gyxjgl/term/manage/delClass'/>?courseTypeId=${courseTypeId}&recordFlow="+recordFlow+"&courseFlow="+courseFlow,null,function(resp){
				if(resp=="${GlobalConstant.DELETE_SUCCESSED}"){
					<%--if('${courseTypeId}' == 'Public'){--%>
						<%--<c:forEach items="${terms}" var="term">--%>
							<%--var weekNumber = $("#${term.recordFlow}_weekNumber").val();--%>
							<%--var showFlag = "2";--%>
							<%--if('${term.recordFlow}'== termFlow){--%>
								<%--showFlag = "1";--%>
							<%--}--%>
							<%--loadDetail('${term.recordFlow}',weekNumber,showFlag);--%>
						<%--</c:forEach>--%>
					<%--}else{--%>
						var weekNumber = $("#"+termFlow+"_weekNumber").val();
						loadDetail(termFlow,weekNumber,"1");
//					}
				}
			},null,true);
		},null);
	}
	function settingOpt(){
		if(!$("#recSearchForm").validationEngine("validate")){
			return;
		}
		var url = "<s:url value='/gyxjgl/term/manage/scheduleSetting?sessionNumber='/>"+$("[name='sessionNumber']").val();
		jboxOpen(url, "排课设置",660,460);
	}
	function exportExcel(recordFlow){
		var url = "<s:url value='/gyxjgl/term/manage/exportClass?recordFlow='/>"+recordFlow;
		jboxTip("导出中…………");
		jboxSubmit($("#recSearchForm"), url, null, null, false);
		jboxEndLoading();
	}
	function arrangePlanPlus(termFlow){
		var url = "<s:url value='/gyxjgl/term/manage/courseAddPlus?roleFlag=${roleFlag}&termFlow='/>"+termFlow+"&courseTypeId=${courseTypeId}";
//		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
//		jboxMessager(iframe,'增加排课',500,340);
		jboxOpen(url, "增加排课",500,340);
	}
</script>
<style type="text/css">
	.table tr td{
		border-bottom: 0px;
		border-right: 0px;
	}
	.table tr th{
		border-bottom: 0px;
		border-right: 0px;
	}
	#boxHome .item:HOVER{background-color: #eee;}
</style>
</head>
<body>
<div class="mainright">
	<div class="content">
	  <form id="recSearchForm" method="post" action="<s:url value='/gyxjgl/term/manage/list'/>">
		  <input id="currentPage" type="hidden" name="currentPage" value=""/>
		  <input type="hidden" name="roleFlag" value="${roleFlag}"/>
		  <table class="basic table" style="width: 1100px;margin: 20px 0px;border: none;">
			  <c:if test="${roleFlag ne 'skz'}">
				  <tr>
					  <td style="border: none;">
						  年&#12288;&#12288;级：
						  <input type="text" name="sessionNumber" value="${empty param.sessionNumber?(pdfn:getCurrYear()) : param.sessionNumber}" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width:137px;margin: 0" onchange="reload()"/>
						  &#12288;&#12288;授课老师：
						  <input style="width:137px;text-align: left;" id="classTeacherName" name="classTeacherName" value="${param.classTeacherName}"/>
						  &nbsp;&#12288;&#12288;承担单位：
						  <input id="assumeOrgName" type="text" name="assumeOrgName" value="${param.assumeOrgName}" autocomplete="off" title="${param.assumeOrgName}" onmouseover="this.title = this.value" flow="${param.assumeOrgFlow}" style="width: 137px;"/>&#12288;&nbsp;
						  <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:550px;">
							  <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
								  <c:forEach items="${orgMap}" var="org">
									  <p class="item" flow="${org.key}" value="${org.value}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.value}</p>
								  </c:forEach>
							  </div>
						  </div>
						  &#12288;学&#12288;&#12288;期：
						  <select name="gradeTermId" style="width:141px;">
							  <option value="">全部</option>
							  <c:forEach items="${dictTypeEnumGyTermSeasonList}" var="recruitSeason">
								  <option value="${recruitSeason.dictId}" <c:if test="${param.gradeTermId eq recruitSeason.dictId}"> selected="selected"</c:if> >${recruitSeason.dictName}</option>
							  </c:forEach>
						  </select>
					  </td>
				  </tr>
				  <tr>
					  <td>
						  课程检索：
						  <input id="searchParam_Course"   placeholder="输入课程名称/代码" class="inputText"  style="width:141px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
						  <div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width:137px;"></div>
						  <input type="hidden" id="result_Course" name="courseFlow" value="${param.courseFlow }"/>
						  <c:if test="${roleFlag ne 'doctor'}">
							  &#12288;&#12288;培养层次：
							  <select style="width:141px;text-align: left;" name="gradationId">
								  <option value="">全部</option>
								  <c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
									  <c:if test="${trainType.dictId ne '3'}">
										  <option value="${trainType.dictId}" ${param.gradationId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
									  </c:if>
								  </c:forEach>
							  </select>
							  &nbsp;&#12288;&#12288;班&#12288;&#12288;级：
							  <select style="width:141px;text-align: left;"  name="classId">
								  <option value="">全部</option>
								  <c:forEach items="${dictTypeEnumGyXjClassList}" var="xjclass">
									  <option value="${xjclass.dictId}" <c:if test="${param.classId == xjclass.dictId}">selected="selected"</c:if> >${xjclass.dictName}</option>
								  </c:forEach>
							  </select>
						  </c:if>
						  &#12288;
						  <input type="hidden" id="assumeOrgFlow" name="assumeOrgFlow" value="${param.assumeOrgFlow }"/>
						  <input type="button" value="查&#12288;询" class="search" onclick="search();"/>
						  <input type="button" value="新&#12288;增" class="search" onclick="add();"/>
						  <input type="button" value="设&#12288;置" class="search" onclick="settingOpt();"/>
					  </td>
				  </tr>
			  </c:if>
			  <c:if test="${roleFlag eq 'skz'}">
				  <tr>
					  <td style="border: none;">
						  <input type="hidden" id="assumeOrgFlow">
						  <input type="hidden" id="result_Course">
						  <input type="hidden" id="classTeacherName">
						  年&#12288;&#12288;级：
						  <input type="text" name="sessionNumber" value="${empty param.sessionNumber?(pdfn:getCurrYear()) : param.sessionNumber}" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width:137px;margin: 0"/>
						  &#12288;学&#12288;&#12288;期：
						  <select name="gradeTermId" style="width:141px;">
							  <option value="">全部</option>
							  <c:forEach items="${dictTypeEnumGyTermSeasonList}" var="recruitSeason">
								  <option value="${recruitSeason.dictId}" <c:if test="${param.gradeTermId eq recruitSeason.dictId}"> selected="selected"</c:if> >${recruitSeason.dictName}</option>
							  </c:forEach>
						  </select>
						  &#12288;班&#12288;&#12288;级：
						  <select style="width:141px;text-align: left;"  name="classId">
							  <option value="">全部</option>
							  <c:forEach items="${dictTypeEnumGyXjClassList}" var="xjclass">
								  <option value="${xjclass.dictId}" <c:if test="${param.classId == xjclass.dictId}">selected="selected"</c:if> >${xjclass.dictName}</option>
							  </c:forEach>
						  </select>
						  &#12288;培养层次：
						  <select style="width:141px;text-align: left;" name="gradationId">
							  <option value="">全部</option>
							  <c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
								  <c:if test="${trainType.dictId ne '3'}">
									  <option value="${trainType.dictId}" ${param.gradationId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
								  </c:if>
							  </c:forEach>
						  </select>
						  <input type="button" name="" value="查&#12288;询" class="search" onclick="search();"/>
					  </td>
				  </tr>
			  </c:if>
		  </table>
		</form>
		<c:if test="${empty terms}"><table class="xllist" width="100%" style="margin-bottom: 10px;"><tr><td>没有排课记录</td></tr></table></c:if>
		<c:forEach items="${terms}" var="term">
			<table class="xllist nofix" width="100%" style="margin-bottom: 10px;">
				<tr style="cursor: pointer;" >
					<th colspan="15" style="text-align: left;padding-left: 10px;width: 100%;"  onclick="toogleTerm('${term.recordFlow}');">
						<div style="float: left;" >年份：${term.sessionNumber}</div>&#12288;
						班级： ${term.className}&#12288;
						培养层次：${term.gradationName }
						&#12288;学期开始时间：${term.termStartTime}&#12288;学期结束时间：${term.termEndTime}&#12288;学期：${term.gradeTermName}
						<span class="removeEvent" style="float: right;padding-right: 10px;"><a style="cursor: pointer;color:blue;" onclick="exportExcel('${term.recordFlow}')">导出</a></span>
						<c:if test="${roleFlag ne 'doctor'}">
							<span class="removeEvent" style="float: right;padding-right: 10px;"><a style="cursor: pointer;color:blue;" onclick="arrangePlanPlus('${term.recordFlow}')">排课</a></span>
						</c:if>
						<c:if test="${empty roleFlag}"><span style="float: right;padding-right: 10px;"><a style="cursor: pointer;color:blue;" class="weekNum" href="javascript:edit('${term.recordFlow}')">编辑</a></span></c:if>
						<div style="float: right;padding-right: 10px;">
							<c:set var="key" value="${term.recordFlow}_week"></c:set>
							<c:set var="weekNums" value="${weekNuMap[key]}"></c:set>
							<c:set var="begain" value="1"></c:set>
							<c:set var="end" value="1"></c:set>
							周数：
							<select style="width: 120px;text-align: left;" class="weekNum" id="${term.recordFlow}_weekNumber" name="weekNumber" onchange="loadDetail('${term.recordFlow}',this.value,'1')">
								<option value="">请选择</option>
								<c:forEach items="${weekNums}" var="week" varStatus="stat">
									<c:if test="${stat.first}">
										<c:set var="begain" value="${week}"></c:set>
									</c:if>
									<c:if test="${stat.last}">
										<c:set var="end" value="${week}"></c:set>
									</c:if>
									<option value="${week}">第${week}周</option>
								</c:forEach>
							</select>
						</div>
					</th>
				</tr>
				<tbody id="${term.recordFlow}_tbody"></tbody>
				<tbody id="${term.recordFlow}_button">
					<tr>
						<td colspan="15" style="text-align: right;padding-right: 10px;width: 100%;">
							<span style="float: right;padding-right: 10px;">[<a style="cursor: pointer;" class="weekNum" href="javascript:next('${term.recordFlow}','${end}')">下一周</a>]</span>
							<span style="float: right;padding-right: 10px;">[<a style="cursor: pointer;" class="weekNum" href="javascript:before('${term.recordFlow}','${begain}')">上一周</a>]</span>
						</td>
					</tr>
				</tbody>

			</table>
		</c:forEach>
		</div>
	</div>

</body>
</html>