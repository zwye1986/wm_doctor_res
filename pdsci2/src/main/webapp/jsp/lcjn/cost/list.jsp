<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

<%--<jsp:include page="/jsp/common/htmlhead.jsp">--%>
	<%--<jsp:param name="basic" value="true"/>--%>
	<%--<jsp:param name="jbox" value="true"/>--%>
	<%--<jsp:param name="font" value="true"/>--%>
	<%--<jsp:param name="jquery_form" value="true"/>--%>
	<%--<jsp:param name="jquery_ui_tooltip" value="true"/>--%>
	<%--<jsp:param name="jquery_ui_combobox" value="false"/>--%>
	<%--<jsp:param name="jquery_ui_sortable" value="false"/>--%>
	<%--<jsp:param name="jquery_cxselect" value="false"/>--%>
	<%--<jsp:param name="jquery_scrollTo" value="false"/>--%>
	<%--<jsp:param name="jquery_jcallout" value="false"/>--%>
	<%--<jsp:param name="jquery_validation" value="true"/>--%>
	<%--<jsp:param name="jquery_datePicker" value="true"/>--%>
	<%--<jsp:param name="jquery_fullcalendar" value="false"/>--%>
	<%--<jsp:param name="jquery_fngantt" value="false"/>--%>
	<%--<jsp:param name="jquery_fixedtableheader" value="true"/>--%>
	<%--<jsp:param name="jquery_placeholder" value="true"/>--%>
	<%--<jsp:param name="jquery_iealert" value="false"/>--%>
<%--</jsp:include>--%>
<script type="text/javascript">
	$(function(){
		<c:forEach items="${courseSkills}" var="courseSkill">
			var subSum = 0;
			for(var i=0;i<$(".sum${courseSkill.recordFlow}").length;i++){
				subSum+=Number($($(".sum${courseSkill.recordFlow}")[i]).text());
			}
			$(".sumAll${courseSkill.recordFlow}").text(subSum.toFixed(2));
		</c:forEach>
		var superSum = 0;
		for(var i=0;i<$(".sumAll").length;i++){
			superSum+=Number($($(".sumAll")[i]).text());
		}
		$(".sumSuper").text(superSum.toFixed(2));
		var otherSupply = 0;
		for(var i=0;i<$(".otherSupply").length;i++){
			otherSupply+=Number($($(".otherSupply")[i]).text());
		}
		$(".otherSupplySum").text(otherSupply.toFixed(2));
		<c:forEach items="${supplyList2}" var="supply2">
		var mainSupply = 0;
		for(var i=0;i<$(".mainSupply${supply2.suppliesFlow}").length;i++){
			mainSupply+=Number($($(".mainSupply${supply2.suppliesFlow}")[i]).text());
		}
		var num = Number('${num==0?1:num}');
		mainSupply = mainSupply/num;
		$(".mainSupplySum${supply2.suppliesFlow}").text(mainSupply.toFixed(2));
		</c:forEach>
	});
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
			jboxLoad("costDiv",'<s:url value="/lcjn/cost/list"/>?courseFlow=${param.courseFlow}&currentPage='+page,true);
		}
	}
	function editOtherSupply(flow){
		jboxOpen('<s:url value="/lcjn/cost/editOtherSupply"/>?recordFlow='+flow+'&courseFlow=${param.courseFlow}',"其他耗材详情",350,250,true);
	}
</script>
</head>
<body>

 <div class="mainright">
    <div class="content">

		<input type="button" class="search" value="添加其他耗材" onclick="editOtherSupply('');" style="margin-top: 15px;margin-left:-1px; ">

  <table class="xllist" style="margin-top: 15px;min-width: 999px;">
  	<thead>
         <tr>
            <th rowspan="2"style="min-width: 78px">技能名称</th>
            <th rowspan="2"style="min-width: 39px">总计（元）</th>
			<th rowspan="2"style="min-width: 210px">其他耗材（单价*数量）</th>
			<th rowspan="2"style="min-width: 39px">人数</th>
			 <c:forEach items="${supplyList2}" var="supply" varStatus="s">
				 <c:set var="length" value="${s.count}"></c:set>
			 </c:forEach>
			<th colspan="${length}" >耗材（单价*数量）</th>
		 </tr>
		 <tr>
			<c:forEach items="${supplyList2}" var="supply">
				<th style="min-width: 39px">${supply.dictName}</th>
			</c:forEach>
		 </tr>
     </thead>
     <c:forEach items="${courseSkills}" var="courseSkill">
		<tr>
	     <td>${courseSkill.skillName}</td>
	     <td class="sumAll${courseSkill.recordFlow} sumAll"></td>
		 <c:set value="${suppliyMap[courseSkill.skillFlow]}" var="supplyList"></c:set>
		 <td>
			 <c:forEach items="${supplyList}" var="supply1">
			 <c:set value="${supply1.recordFlow}" var="flow"></c:set>
				 <a style="cursor: pointer;color: #0a8cd2" onclick="editOtherSupply('${flow}')">${supply1.dictName}&nbsp;
				 （${priceMap[flow]}*${supply1.useNum}）<br/></a>
				 <div style="display: none" class="sum${courseSkill.recordFlow} otherSupply">${priceMap[flow]*supply1.useNum*(num==0?0:1)}</div>
			 </c:forEach>
			 <c:if test="${empty supplyList}">
				 -
			 </c:if>
		 </td>
		 <td>${num}</td>
		 <c:forEach items="${supplyList2}" var="supply2">
			 <td>
			 <c:set var="key" value="${courseSkill.skillFlow}${supply2.suppliesFlow}"></c:set>
			 <c:set var="detail" value="${materialMap[key]}"></c:set>
			 ${priceMap2[key]}
			 ${empty priceMap2[key]?'-':'*'}
			 ${detail.useNum}
			  <div style="display: none" class="sum${courseSkill.recordFlow} mainSupply${supply2.suppliesFlow}">${priceMap2[key]*detail.useNum*num}</div>
			 </td>
		 </c:forEach>
		 <c:if test="${empty supplyList2}">
		 	<td></td>
		 </c:if>
		</tr>
     </c:forEach>
	  <tr>
		  <td>总计</td>
		  <td class="sumSuper"></td>
		  <td class="otherSupplySum"></td>
		  <td>${num}</td>
		  <c:forEach items="${supplyList2}" var="supply2">
			  <td class="mainSupplySum${supply2.suppliesFlow}"></td>
		  </c:forEach>
		  <c:if test="${empty supplyList2}">
			  <td>0.00</td>
		  </c:if>
	  </tr>
  </table>
	<p>
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
	    <c:set var="pageView" value="${pdfn:getPageView2(courseSkills,10)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</p>
   </div>
</div> 	

</body>
</html>