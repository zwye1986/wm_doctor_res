<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red}
</style>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
var year='${param.year}';
var courseHash=new Array();
var majorHash=new Array();
function doClose(){
	window.parent.frames["mainIframe"].window.search();
}
function clickOn(obj){
	$(".cur").removeClass("cur");
	$(obj).toggleClass("cur");
}
function toMove(type,round){
	var curCourse=$(".cur");
	if(curCourse.length<1){
		jboxTip("请选择要移动的课程！");
		return false;
	}
	if(type==""){
		curCourse.removeClass("cur");
		curCourse.parent().append("&nbsp;<a onclick=\"del(this,'"+curCourse.attr("id")+"','"+curCourse.attr("courseCode")+"','"+curCourse.attr("courseName")+"','"+curCourse.attr("period")+"','"+curCourse.attr("credit")+"');\" style='cursor: pointer;'><img style='width: 10px;padding-bottom: 3px;' src='<s:url value='/css/skin/${skinPath}/images/del3.png'/>'/></a>")
		var curCourseTemp="<div>"+curCourse.parent().eq(0).html()+"</div>";
		curCourse.parent().remove();
		$("#notIncludeTd").append(curCourseTemp);
		console.log(curCourseTemp);
	}
	if(type=="Degree"){
		curCourse.removeClass("cur");
		curCourse.next().remove();
		var curCourseTemp="<div>"+curCourse.parent().eq(0).html()+"</div>";
		curCourse.parent().remove();
		$("#DegreeTd").append(curCourseTemp);
	}
	if(type=="Public"){
		curCourse.removeClass("cur");
		curCourse.next().remove();
		var curCourseTemp="<div>"+curCourse.parent().eq(0).html()+"</div>";
		curCourse.parent().remove();
		$("#PublicTd").append(curCourseTemp);
	}
	if(type=="PublicNeed"){
		curCourse.removeClass("cur");
		curCourse.next().remove();
		var curCourseTemp="<div>"+curCourse.parent().eq(0).html()+"</div>";
		curCourse.parent().remove();
		$("#PublicNeedTd").append(curCourseTemp);
	}
	if(type=="Optional"){
		curCourse.removeClass("cur");
		curCourse.next().remove();
		var curCourseTemp="<div>"+curCourse.parent().eq(0).html()+"</div>";
		curCourse.parent().remove();
		$("#OptionalTd").append(curCourseTemp);
	}
	if(type=="OptionalNeed"){
		curCourse.removeClass("cur");
		curCourse.next().remove();
		var curCourseTemp="<div>"+curCourse.parent().eq(0).html()+"</div>";
		curCourse.parent().remove();
		$("#OptionalNeedTd").append(curCourseTemp);
	}
	<%--var majorId=$("#result_Major").val();--%>
	<%--if(majorId==="undefined" || majorId==""){--%>
		<%--majorId='${param.majorId}'; --%>
	<%--}--%>
	<%--$(".cur").removeClass("cur").each(function(){--%>
	  <%--var courseFlow=$(this).attr("id");--%>
	  <%--var url="<s:url value='/gyxjgl/majorCourse/save'/>?courseFlow="+courseFlow+"&majorId="+majorId+"&courseTypeId="+type+"&planYear="+year;--%>
	  <%--jboxPost(url,null,function(resp){--%>
		  <%--loadCourssMajorTb(majorId);--%>
	  <%--},null,false);--%>
	<%--});--%>
	//计算页面各项合计
	var sum1 = 0;
	var sum2 = 0;
	var sum3 = 0;
	$('.sumTd a').each(function(){
		sum2+=Number($(this).attr("period"))?Number($(this).attr("period")):0;
		sum3+=Number($(this).attr("credit"))?Number($(this).attr("credit")):0;
		sum1++;
	});
	$('.sum1').text(sum1);
	$('.sum2').text(sum2.toFixed(1));
	$('.sum3').text(sum3.toFixed(1));
}

function del(obj,courseFlow,courseCode,courseName,coursePeriod,courseCredit){
	var majorId=$("#result_Major").val();
	if(majorId==="undefined" || majorId==""){
		majorId='${param.majorId}'; 
	}
	var msg="确认删除该课程吗？";
	jboxConfirm(msg,function(){
		$(obj).parent().remove();
		$("#delFlowsDiv").append("<input value='"+courseFlow+"'/>");
//		var courseArrayTemp=new Array(courseFlow,courseName,courseCode,coursePeriod,courseCredit);
//		console.log("11111"+courseArrayTemp);
//		courseHash[courseFlow]=courseArrayTemp;
		<%--var url="<s:url value='/gyxjgl/majorCourse/delCourseMajor'/>?courseFlow="+courseFlow+"&majorId="+majorId+"&planYear="+year;--%>
		<%--jboxPost(url,null,function(resp){--%>
			<%--loadCourseList();--%>
			<%--loadCourssMajorTb(majorId);--%>
		<%--},null,true);--%>
	},null);
	
}

//======================================

function loadCourseList(){
	jboxStartLoading();
	var majorId=$("#result_Major").val();
	if(majorId==="undefined" || majorId==""){
		majorId='${param.majorId}'; 
	}
	var courseArray = new Array();
	var url = "<s:url value='/gyxjgl/majorCourse/searchCourseJson2'/>?majorId="+majorId+"&year="+year;
	jboxPostAsync(url,null,function(data){
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
				var coursePeriod =data[i].coursePeriod ;
				if(data[i].coursePeriod ==null){
					coursePeriod ="";
				}
				var courseCredit=data[i].courseCredit;
				if(data[i].courseCredit==null){
					courseCredit="";
				}
				courseArray[i]=new Array(courseFlow,courseName,courseCode,coursePeriod,courseCredit);
				courseHash[courseFlow]=courseArray[i];
			}
			jboxStartLoading();
			$("#searchParam_Course").suggest(courseArray,{
				attachObject:'#suggest_Course',
				dataContainer:'#result_Course',
				triggerFunc:function(courseFlow){
					addCourse(courseFlow,courseHash[courseFlow][1],courseHash[courseFlow][2],courseHash[courseFlow][3],courseHash[courseFlow][4]);
				},
			    enterFunc:function(courseFlow){
					addCourse(courseFlow,courseHash[courseFlow][1],courseHash[courseFlow][2],courseHash[courseFlow][3],courseHash[courseFlow][4]);
			    }
			});
			jboxEndLoading();
		}
		
	},null,false);
	
}
function loadMajorList(){
	var majorArray = new Array();
	var url = "<s:url value='/gyxjgl/majorCourse/searchMajorJson'/>";
	jboxPostAsync(url,null,function(data){
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
				majorHash[dictId]=majorArray[i];
			}
			jboxStartLoading();
			$("#searchParam_Major").suggest(majorArray,{
				attachObject:'#suggest_Major',
				dataContainer:'#result_Major',
				triggerFunc:function(majorId){
					loadCourseList();
					loadCourssMajorTb(majorId);
				},
			    enterFunc:function(){
			    	loadCourseList();
			    	loadCourssMajorTb(majorId);
			    }
			});
			jboxEndLoading();
		}
	},null,false);
	
}

//页面加载完成时调用
$(function(){
	loadCourseList();
	loadMajorList();
	loadCourssMajorTb('${param.majorId}');
});
function adjustResults() {
	$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
	$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	$("#suggest_Major").css("left",$("#searchParam_Major").offset().left);
	$("#suggest_Major").css("top",$("#searchParam_Major").offset().top+$("#searchParam_Major").outerHeight());
}
function loadCourssMajorTb(majorId){
	var url="<s:url value='/gyxjgl/majorCourse/loadCourseMajorTb'/>?majorId="+majorId+"&year="+year;
	jboxLoad("courseMajorContent",url,false);
}
function addCourse(courseFlow,courseName,courseCode,period,credit){ //增加课程操作
	var majorId=$("#result_Major").val();
	if(majorId==="undefined" || majorId==""){
		majorId='${param.majorId}'; 
	}

	var flowArr=[];
	//课程名称
	var notInclude = $("#notIncludeTd").children();
	$.each(notInclude , function(i , n){
		var courseFlow1 = $(n).find("a").eq(0).attr("id");
		flowArr.push(courseFlow1);
	});
	//学位课程
	var DegreeTd = $("#DegreeTd").children();
	$.each(DegreeTd , function(i , n){
		var courseFlow2 = $(n).find("a").eq(0).attr("id");
		flowArr.push(courseFlow2);
	});
	//公共选修课
	var PublicTd = $("#PublicTd").children();
	$.each(PublicTd , function(i , n){
		var courseFlow3 = $(n).find("a").eq(0).attr("id");
		flowArr.push(courseFlow3);
	});
	//公共必选课
	var PublicNeedTd = $("#PublicNeedTd").children();
	$.each(PublicNeedTd , function(i , n){
		var courseFlow4 = $(n).find("a").eq(0).attr("id");
		flowArr.push(courseFlow4);
	});
	//专业选修课
	var OptionalTd = $("#OptionalTd").children();
	$.each(OptionalTd , function(i , n){
		var courseFlow5 = $(n).find("a").eq(0).attr("id");
		flowArr.push(courseFlow5);
	});
	//专业必选课
	var OptionalNeedTd = $("#OptionalNeedTd").children();
	$.each(OptionalNeedTd , function(i , n){
		var courseFlow6 = $(n).find("a").eq(0).attr("id");
		flowArr.push(courseFlow6);
	});
	var bl=$.inArray(courseFlow, flowArr);
	if(bl==-1){
		$("#notIncludeTd").append("<div><a style='cursor: pointer;' onclick='clickOn(this);' id=\""+courseFlow+"\" courseName=\""+courseName+"\" courseCode=\""+courseCode+"\" period=\""+period+"\" credit=\""+credit+"\">("+courseCode+")"+courseName+"</a>&nbsp;<a onclick=\"del(this,'"+courseFlow+"','"+courseCode+"','"+courseName+"','"+period+"','"+credit+"');\" style='cursor: pointer;'><img style='width: 10px;padding-bottom: 3px;' src='<s:url value='/css/skin/${skinPath}/images/del3.png'/>'/></a></div>");
	}else{
		jboxTip("已经添加过该课程！");
	}
//	delete courseHash[courseFlow];
	<%--var url="<s:url value='/gyxjgl/majorCourse/save'/>?majorId="+majorId+"&courseFlow="+courseFlow+"&planYear="+year;--%>
	<%--jboxPost(url,null,function(data){--%>
		<%--loadCourseList();--%>
		<%--$("#searchParam_Course").val("");--%>
		<%--loadCourssMajorTb(majorId);--%>
	<%--},null,false);--%>

}

	function doSave(){
		var addDatas = [];
		var delDatas = [];
		var majorId=$("#result_Major").val();
		if(majorId==="undefined" || majorId==""){
			majorId='${param.majorId}';
		}
		//课程名称
		var notInclude = $("#notIncludeTd").children();
		$.each(notInclude , function(i , n){
			var courseFlow = $(n).find("a").eq(0).attr("id");
			var data = {
				"courseFlow":courseFlow,
//				"majorId":majorId,
//				"planYear":year,
				"courseTypeId":""
			};
			addDatas.push(data);
		});
		//学位课程
		var DegreeTd = $("#DegreeTd").children();
		$.each(DegreeTd , function(i , n){
			var courseFlow = $(n).find("a").eq(0).attr("id");
			var data = {
				"courseFlow":courseFlow,
//				"majorId":majorId,
//				"planYear":year,
				"courseTypeId":"Degree"
			};
			addDatas.push(data);
		});
		//公共选修课
		var PublicTd = $("#PublicTd").children();
		$.each(PublicTd , function(i , n){
			var courseFlow = $(n).find("a").eq(0).attr("id");
			var data = {
				"courseFlow":courseFlow,
//				"majorId":majorId,
//				"planYear":year,
				"courseTypeId":"Public"
			};
			addDatas.push(data);
		});
		//公共必选课
		var PublicNeedTd = $("#PublicNeedTd").children();
		$.each(PublicNeedTd , function(i , n){
			var courseFlow = $(n).find("a").eq(0).attr("id");
			var data = {
				"courseFlow":courseFlow,
//				"majorId":majorId,
//				"planYear":year,
				"courseTypeId":"PublicNeed"
			};
			addDatas.push(data);
		});
		//专业选修课
		var OptionalTd = $("#OptionalTd").children();
		$.each(OptionalTd , function(i , n){
			var courseFlow = $(n).find("a").eq(0).attr("id");
			var data = {
				"courseFlow":courseFlow,
//				"majorId":majorId,
//				"planYear":year,
				"courseTypeId":"Optional"
			};
			addDatas.push(data);
		});
		//专业必选课
		var OptionalNeedTd = $("#OptionalNeedTd").children();
		$.each(OptionalNeedTd , function(i , n){
			var courseFlow = $(n).find("a").eq(0).attr("id");
			var data = {
				"courseFlow":courseFlow,
//				"majorId":majorId,
//				"planYear":year,
				"courseTypeId":"OptionalNeed"
			};
			addDatas.push(data);
		});
		//删除课程
		var delFlowsDiv = $("#delFlowsDiv").children();
		$.each(delFlowsDiv , function(i , n){
			var courseFlow = $(n).val();
			var data = {
				"courseFlow":courseFlow,
//				"majorId":majorId,
//				"planYear":year,
				"courseTypeId":""
			};
			delDatas.push(data);
		});
		var t = {'delDatas':delDatas,'addDatas':addDatas,'majorId':majorId,'planYear':year};
		$('#jsondata').val(JSON.stringify(t));
		var url = "<s:url value='/gyxjgl/majorCourse/doSave'/>";
		jboxPost(url, "jsonData="+JSON.stringify(t), function(resp){
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
				jboxTip(resp);
				jboxClose();
				window.parent.frames["mainIframe"].window.search();
			}else{
				jboxTip(resp);
			}
		}, null, true);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<form>
				<input id="jsondata" type="hidden" name="jsondata" value=""/>
				<table class="basic" style="width: 100%;margin: 10px 0px;">
					<tr>
						<td>
						   <span <c:if test="${param.editFlag=='Y' }">style="display:none;"</c:if>>
							专业检索：<input id="searchParam_Major"  placeholder="输入专业名称/代码" class="inputText"  style="width: 200px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
								<div id="suggest_Major" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 300px;"></div>
								<input type="hidden" id="result_Major" name="majorId" />
							&#12288;
							</span>
							（${param.year}）
							课程检索：
								<input id="searchParam_Course"  placeholder="输入课程名称/代码" class="inputText"  style="width: 200px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
								<div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 200px;"></div>
							<span style="float: right; color: red;">注意：点击课程名称选中后左右移动进行操作&#12288;</span>
						</td>
					</tr>
				</table>
				<div id="courseMajorContent">

				</div>
			</form>
		</div>
		<div style="text-align: center;">
				<input type="button" class="search" value="保&#12288;存" onclick="doSave();"/>
				<%--<input type="button" class="search" value="关&#12288;闭" onclick="doClose();"/>--%>
			</div>
	</div>
</body>
</html>