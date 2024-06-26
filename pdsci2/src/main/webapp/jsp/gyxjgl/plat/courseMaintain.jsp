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
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(document).ready(function(){
	search();
});

/**
 * 合计已选课程学时、学分
 */
function calculate(){
	var courseCreditSum = 0;
	var coursePeriodSum = 0;
	var $courseFlow = $("input[name=courseFlow]:checked");
	$courseFlow.each(function(){
		var courseFlow = $(this).val();
		var courseCredit = $("#courseCredit_"+courseFlow).val();
		var coursePeriod = $("#coursePeriod_"+courseFlow).val();
 		if (courseCredit == null || courseCredit == undefined || courseCredit == '' || isNaN(courseCredit)){
 			courseCredit = 0;
		}
		courseCreditSum = courseCreditSum.add(courseCredit);
 		if (coursePeriod == null || coursePeriod == undefined || coursePeriod == '' || isNaN(coursePeriod)){
 			coursePeriod = 0;
		}
		coursePeriodSum = coursePeriodSum.add(coursePeriod);
	});
	$("#courseCount").text($courseFlow.length);
	$("#courseCreditSum").text(parseFloat(courseCreditSum));
	$("#coursePeriodSum").text(parseFloat(coursePeriodSum));
}
function accAdd(arg1,arg2){
	var r1,r2,m;
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	m=Math.pow(10,Math.max(r1,r2))
	return (arg1*m+arg2*m)/m
}
//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg){
	return accAdd(arg,this);
}
function maintain(courseFlow, source, obj){
	jboxStartLoading();
	var $courseFlow = $("."+courseFlow).eq(0);
	if (source=="tr") {
		if ($courseFlow.attr("checked") !="checked") {
			$courseFlow.attr("checked",true);
		} else {
			$courseFlow.attr("checked",false);
		}
		obj = $courseFlow
	}
	var courseUserCount = parseFloat($("#courseUserCount_"+courseFlow).text());
	var chooseCount =parseFloat($("#chooseCount_"+courseFlow).text());
	if($(obj).attr("checked") =="checked"){
		//alert(chooseCount + "______" + courseUserCount);
		if(chooseCount >= courseUserCount){
			jboxTip("已达到课程容纳人数！请选择其他课程");
			$courseFlow.attr("checked", false);
			jboxEndLoading();
			return false;
		}
		$("#chooseCount_"+courseFlow).text(chooseCount+1);
		var recordStatus = "${GlobalConstant.FLAG_Y}";
		saveCourseMaintain(courseFlow, recordStatus,"Y");
	}else{
		jboxConfirm("确认取消该学生的选课？", function(){
			$("#chooseCount_"+courseFlow).text(chooseCount-1);
			var recordStatus = "${GlobalConstant.FLAG_N}";
			saveCourseMaintain(courseFlow, recordStatus,"N");
		},function(){
			$(obj).attr("checked",true);
		});
		jboxEndLoading();
	}
}

function saveCourseMaintain(courseFlow, recordStatus,replenishFlag){
	var courseCredit = $("#courseCredit_"+courseFlow).val();
	var coursePeriod = $("#coursePeriod_"+courseFlow).val();
	if (courseCredit == null || courseCredit == undefined || courseCredit == '' || isNaN(courseCredit)){
		courseCredit = 0;
	}
	if (coursePeriod == null || coursePeriod == undefined || coursePeriod == '' || isNaN(coursePeriod)){
		coursePeriod = 0;
	}
	var courseTypeId = $("#courseTypeId_"+courseFlow).val();
	var courseTypeName = $("#courseTypeName_"+courseFlow).val();
	var courseCode = $("#courseCode_"+courseFlow).val();
	var courseName = $("#courseName_"+courseFlow).val();
	var courseNameEn = $("#courseNameEn_"+courseFlow).val();
	var data = {
			studentPeriod:"${period}",
			userFlow:"${eduUser.userFlow}",
			courseFlow:courseFlow,
			courseCredit:courseCredit,
			coursePeriod:coursePeriod,
			courseTypeId:courseTypeId,
			courseTypeName:courseTypeName,
			recordStatus:recordStatus,
			courseCode:courseCode,
			courseName:courseName,
			replenishFlag:replenishFlag,
			courseNameEn:courseNameEn
		};
	var url = "<s:url value='/gyxjgl/student/course/saveCourseMaintain'/>";
	jboxPost(url, data, function(resp){
		if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
			strideOption($('#stride'));
		}
	}, null, true);
}


function doClose(){
	window.parent.frames['mainIframe'].location.reload(true);
	jboxClose();
}

function search(){
	var majorId = $("#result_Major").val();
	var url = "<s:url value='/gyxjgl/student/course/editCourseMaintain'/>?period=${period}&majorId="+majorId+"&trainTypeId=${eduUser.trainTypeId}&userFlow=${eduUser.userFlow}";
	jboxLoad("contentDiv", url, true);
}
function reOpen(){
	jboxConfirm("确认开放该学员重新选课?",function(){
		jboxGet("<s:url value='/gyxjgl/student/course/reOpenChooseCourse'/>?userFlow=${eduUser.userFlow}",null,function(resp){
			if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
				$("#reOpenBtn").hide();
			}
		},null,true);
	});
}
function loadMajorList(){
	var majorArray = new Array();
	var url = "<s:url value='/gyxjgl/majorCourse/searchMajorJson'/>";
	jboxGetAsync(url,null,function(data){
		if(data){
			for (var i = 0; i < data.length; i++) {
				var dictId=data[i].dictId;
				if(data[i].dictId==null){
					dictId="";
				}
				var dictName=data[i].dictName;
				if(data[i].dictName==null){
					dictName="";
				}
				majorArray[i]=new Array(dictId,dictName,dictId);
				if($("#result_Major").val() == dictId){
					$("#searchParam_Major").val(dictName);	
				}
			}
			jboxStartLoading();
			$("#searchParam_Major").suggest(majorArray,{
				attachObject:'#suggest_Major',
				dataContainer:'#result_Major',
				triggerFunc:function(majorId){
					search();
				},
			    enterFunc:function(){
			  
			    }
			});
			jboxEndLoading();
		}
	},null,false);
}
function strideOption(obj){
	var majorId = $("#result_Major").val();
	var url = "<s:url value='/gyxjgl/student/course/editCourseMaintain'/>?period=${period}&majorId="+majorId+"&trainTypeId=${eduUser.trainTypeId}&userFlow=${eduUser.userFlow}&strideFlag="+$(obj).attr("checked")+"&courseName="+$('#courseName').val()+"&periodFlag="+$('#period').attr("checked")+"&periodYear="+$('#periodYear').val();
	jboxLoad("contentDiv", url, true);
}
function searchCouse(obj){
	var majorId = $("#result_Major").val();
	var url = "<s:url value='/gyxjgl/student/course/editCourseMaintain'/>?period=${period}&majorId="+majorId+"&trainTypeId=${eduUser.trainTypeId}&userFlow=${eduUser.userFlow}&strideFlag="+$('#stride').attr("checked")+"&courseName="+$(obj).val()+"&periodFlag="+$('#period').attr("checked")+"&periodYear="+$('#periodYear').val();
	jboxLoad("contentDiv", url, true);
}
//跨年级选课
function stridePeriod(obj){
	var majorId = $("#result_Major").val();
	var url = "<s:url value='/gyxjgl/student/course/editCourseMaintain'/>?period=${period}&majorId="+majorId+"&trainTypeId=${eduUser.trainTypeId}&userFlow=${eduUser.userFlow}&strideFlag="+$('#stride').attr("checked")+"&courseName="+$('#courseName').val()+"&periodFlag="+$(obj).attr("checked")+"&periodYear="+$('#periodYear').val();
	jboxLoad("contentDiv", url, true);
}
function searchPeriod(obj){
	var majorId = $("#result_Major").val();
	var url = "<s:url value='/gyxjgl/student/course/editCourseMaintain'/>?period=${period}&majorId="+majorId+"&trainTypeId=${eduUser.trainTypeId}&userFlow=${eduUser.userFlow}&strideFlag="+$('#stride').attr("checked")+"&courseName="+$('#courseName').val()+"&periodFlag="+$('#period').attr("checked")+"&periodYear="+$(obj).val();
	jboxLoad("contentDiv", url, true);
}
$(function(){
	loadMajorList();
});
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="basic" style="width: 100%;margin: 10px 0px;">
			<tr>
				<td style="border-style:none;width:160px;">
					姓&#12288;&#12288;名：${sysUser.userName}
				</td>
				<td style="border-style:none;width:170px;">
					入学年级：${eduUser.period}
				</td>
				<td style="border-style:none;">
					专&#12288;&#12288;业：<input  id="searchParam_Major"  placeholder="输入专业名称/代码" class="inputText" value="(${eduUser.majorId})${eduUser.majorName}" style="width: 150px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
					<div id="suggest_Major" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 300px;"></div>
					<input type="hidden" id="result_Major" name="majorId" value="${empty param.majorId?eduUser.majorId: param.majorId}"/>
					<span style="margin-left: 80px;"></span>选课年级：${period}
				</td>
			</tr>
			<tr>
				<td style="border-style:none;width:160px;">
					培养层次：${eduUser.trainTypeName}
				</td>
				<td style="border-style:none;width:170px;">
					学&#12288;&#12288;号：${eduUser.sid}
				</td>
				<td style="border-style:none;">
					<font color="red">注意：红色表示补选课程!</font>&#12288;
					<input type="checkbox" onclick="strideOption(this)" id="stride"><label for="stride">跨专业跨培养层次</label>
					<input placeholder="输入课程代码" class="inputText" id="courseName" onchange="searchCouse(this)" style="width: 150px;text-align: left;" />
				</td>
			</tr>
			<tr>
				<td style="border-style:none;" colspan="3">
					<%--<c:if test="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}">--%>
						<span style="margin-left:660px;color: red">
						<%--<input type="checkbox" onclick="stridePeriod(this)" id="period"><label for="period" style="color: black;">跨年级选课</label>--%>
							<%--<input placeholder="输入年级" class="inputText" id="periodYear" onchange="searchPeriod(this)" style="width: 134px;text-align: left;" />--%>
							<c:if test="${eduUser.chooseCourseStatusId==xjChooseCourseStatusEnumChoose.id}">
								&#12288;&nbsp;<input id="reOpenBtn" type="button" value="开放选课" class="search" onclick="reOpen();"/>
							</c:if>
							<c:if test="${eduUser.chooseCourseStatusId!=xjChooseCourseStatusEnumChoose.id}">
								选课状态：未提交
							</c:if>
						</span>
					<%--</c:if>--%>
					<%--<c:if test="${applicationScope.sysCfgMap['xjgl_customer'] ne 'gzykdx'}">--%>
					<%--<span style="margin-left:660px;color: red">--%>
							<%--<c:if test="${eduUser.chooseCourseStatusId==xjChooseCourseStatusEnumChoose.id}">--%>
								<%--<input id="reOpenBtn" type="button" value="开放选课" class="search" onclick="reOpen();"/>--%>
							<%--</c:if>--%>
							<%--<c:if test="${eduUser.chooseCourseStatusId!=xjChooseCourseStatusEnumChoose.id}">--%>
								<%--选课状态：未提交--%>
							<%--</c:if>--%>
						<%--</span>--%>
					<%--</c:if>--%>
				</td>
			</tr>
		</table>
		
		<div id="contentDiv">
		
		</div>
		
		<p style="text-align: center; margin-top: 10px;">
			<input type="button" class="search" value="关&#12288;闭" onclick="doClose();"/>
		</p>
	</div>
</div>
</body>
</html>