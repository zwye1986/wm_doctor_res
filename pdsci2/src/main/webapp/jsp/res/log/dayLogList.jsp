<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
function renderCalendar() {
	$('#calendar').fullCalendar({
		buttonText: {
			prev: '<',
			next: '>',
			today: '今天',
			month: '日期'
		},
		header: {
			left: 'prev,next,today',
			center: 'title',
			right: 'month'
		},
		firstDay:1,
		editable: true,
		lang:"zh-cn",
		dayClick:function(date, allDay, jsEvent, view ) { 
			var url = "<s:url value='/res/log/edit'/>?logTypeId=${param.logTypeId}&fillDate=" + moment(date).format("YYYY-MM-DD");
			jboxOpen(url, "添加工作日志", 700, 500);
		}
	});
}

function fnMthChange() {  
	var view = jQuery('#calendar').fullCalendar('getView');
	var startDate = moment(view.start).format("YYYY-MM-DD");
	var endDate = moment(view.end).format("YYYY-MM-DD");
	$("#calendar").fullCalendar('removeEvents');
	$("#startDate").val(startDate);
	$("#endDate").val(endDate);
	var url ="<s:url value='/res/log/getWorkLogListJsonData'/>?logTypeId=${param.logTypeId}&startDate="+startDate+"&endDate="+endDate;
	jboxStartLoading();
	jboxPost(url,null,function(data){
		for(var i=0;i<data.length;i++) {   
			var obj = new Object();     
			obj.title = data[i].logTypeName;
			//obj.allDay = false;
			obj.start = data[i].fillDate;
			obj.end = data[i].endDate;
			obj.editable  = false;
			obj.url = "<s:url value='/res/log/getLog?logFlow='/>"+data[i].logFlow;//查看日志
			$("#calendar").fullCalendar('renderEvent', obj, true);
		}
	}, null, false);
}

function showDate(){
	var view = jQuery('#calendar').fullCalendar('getView');
	<c:if test='${!empty param.fillDate}'>
	   var i = moment("${param.fillDate}");
	   jQuery('#calendar').fullCalendar('gotoDate', i);
	</c:if>
}

$(function(){
	renderCalendar();
	showDate();
	fnMthChange();
	jQuery('.fc-button-prev').bind('click', fnMthChange);  
	jQuery('.fc-button-next').bind('click', fnMthChange);  
	jQuery('.fc-button-today').bind('click', fnMthChange);
});
function exportDayWord(){
	var url = "<s:url value='/res/log/exportWord'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#myForm"), url, null, null, false);
	jboxEndLoading();
}
</script>

<div style="padding-top: 10px; padding-left: 10px; padding-right: 10px;">
	<form id="myForm">
		<input type="hidden" name="logTypeId" value="${param.logTypeId}">
		<input type="hidden" name="startDate" id="startDate">
		<input type="hidden" name="endDate" id="endDate">
	</form>
	<div style="float:left;margin:0px 0px -26px 150px;"><input type="button" class="search" value="导&#12288;出" onclick="exportDayWord()"/></div>
	<div id="calendar"></div>
</div>
