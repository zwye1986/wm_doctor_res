<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
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
<script type="text/javascript">


	$(function(){
		<c:forEach items="${courseMapList}" var="course">
		var subSum = 0;
		for(var i=0;i<$(".sum${course['COURSE_FLOW']}").length;i++){
			subSum+=Number($($(".sum${course['COURSE_FLOW']}")[i]).text());
		}
		$(".sumAll${course['COURSE_FLOW']}").text(subSum.toFixed(2));
		</c:forEach>

		$("#tableDiv").scroll(function(){//给table外面的div滚动事件绑定一个函数
			var left=$("#tableDiv").scrollLeft();//获取滚动的距离
			var trs=$("#tableDiv table tr[name='titleTr']");//获取表格的所有tr
			trs.each(function(i){//对每一个tr（每一行）进行处理
				//获得每一行下面的所有的td，然后选中下标为0的，即第一列，设置position为相对定位
				//相对于父div左边的距离为滑动的距离，然后设置个背景颜色，覆盖住后面几列数据滑动到第一列下面的情况
				//如果有必要也可以设置一个z-index属性
				if($(this).attr("name")){
					$(this).children().eq(0).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
					$(this).children().eq(1).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
					$(this).children().eq(2).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
					$(this).children().eq(3).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
				}
			});
		});
	});
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}
	function search() {
		$("#serchForm").submit();
	}
</script>
</head>
<body>

 <div class="mainright" id="bodyDiv">
    <div class="content">
		<form id="serchForm" action='<s:url value="/lcjn/cost/courseCostList"/>' method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<%--<div class="choseDivNewStyle">--%>
			<table style="margin-top: 15px;margin-bottom: 15px;" rules="none">
				<tr>
					<td>
						培训时间：
						<input type="text" name="startTime" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.startTime}"> ——
						<input type="text" name="endTime" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.endTime}">
					</td>
					<td>
						&#12288;课程名称：
						<input type="text" name="courseName" value="${param.courseName}">
					</td>
					<td>
						&#12288;<input type="button" value="查&#12288;询" class="search" onclick="search();">
					</td>
				</tr>
			</table>
			<%--</div>--%>
		</form>
		<div id="tableDiv" style="min-width:999px;overflow-x:auto;">

  <table class="xllist" >
         <tr name="titleTr">
			<th rowspan="2"style="min-width: 39px">序号</th>
            <th rowspan="2"style="min-width: 78px;">课程名称</th>
			<th rowspan="2"style="min-width: 220px">培训时间</th>
            <th rowspan="2">合计（元）</th>
			<th rowspan="2"style="min-width: 210px">其他耗材（单价*数量）</th>
			<th rowspan="2"style="min-width: 39px">人数</th>
			 <c:forEach items="${supplyList2}" var="supply" varStatus="s">
				 <c:set var="length" value="${s.count}"></c:set>
			 </c:forEach>
			<th colspan="${length}">耗材（单价*数量）</th>
		 </tr>
		 <tr>
			<c:forEach items="${supplyList2}" var="supply">
				<th style="min-width: 39px">${supply.dictName}</th>
			</c:forEach>
		 </tr>
     <c:forEach items="${courseMapList}" var="course" varStatus="s">
		<tr name="titleTr">
		 <td>${s.index+1}</td>
	     <td style="line-height: 150%">${course["COURSE_NAME"]}</td>
		 <td>${course["MIN_DATE"]}~${course["MAX_DATE"]}</td>
		 <td class="sumAll${course['COURSE_FLOW']}" nowrap></td>
		 <c:set value="${suppliyMap[course['COURSE_FLOW']]}" var="supplyList"></c:set>
		 <td>
			 <c:forEach items="${supplyList}" var="supply1">
			 <c:set value="${supply1.recordFlow}" var="flow"></c:set>
				 ${supply1.dictName}&nbsp;
				 （${priceMap[flow]}*${supply1.useNum}）<br/>
				 <div style="display: none" class="sum${course['COURSE_FLOW']}">${priceMap[flow]*supply1.useNum*(numMap[course['COURSE_FLOW']]==0?0:1)}</div>
			 </c:forEach>
			 <c:if test="${empty supplyList}">
				 -
			 </c:if>
		 </td>
		 <td>${numMap[course['COURSE_FLOW']]}</td>
		 <c:forEach items="${supplyList2}" var="supply2">
			 <td nowrap>
			 <c:set var="key" value="${course['COURSE_FLOW']}${supply2.suppliesFlow}"></c:set>
			 <c:set var="detail" value="${materialMap[key]}"></c:set>
			 ${priceMap2[key]}
			 ${empty priceMap2[key]?'-':'*'}
			 ${detail.useNum}
			  <div style="display: none" class="sum${course['COURSE_FLOW']}">${priceMap2[key]*detail.useNum*numMap[course['COURSE_FLOW']]}</div>
			 </td>
		 </c:forEach>
		 <c:if test="${empty supplyList2}">
		 	<td></td>
		 </c:if>
		</tr>
     </c:forEach>
  </table>
		</div>
	<div>
		课程共${courseNum}个，人数${peopleNum}人，合计${costNum}元。
	</div>
	<p>
	    <c:set var="pageView" value="${pdfn:getPageView(courseMapList)}" scope="request"/>
		<pd:pagination toPage="toPage"/>
	</p>
   </div>
</div> 	

</body>
</html>