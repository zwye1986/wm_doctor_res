 <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:useBean id="now" class="java.util.Date" /> 
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>

<script type="text/javascript">
$(function(){
	$(".redspan").hide();
});
$(function(){
	$('input').attr("readonly","readonly");
	$('textarea').attr("readonly","readonly");
	$("select").attr("disabled", "disabled");
	$(":checkbox").attr("disabled", "disabled");
	$(":radio").attr("disabled", "disabled");
	$(".ctime").removeAttr("onclick");
});
</script>
</head>
<body>
 <div id="main" >
	<div class="mainright">
    	<div class="content">
			<div style="overflow:hidden;">
    		<ul id="tags">
	            <li id='tag0' class="selectTag"><a onclick="selectTag('tagContent0','tag0')" href="javascript:void(0)">项目概况</a></li>
	            <li id='tag1'><a onclick="selectTag('tagContent1','tag1')" href="javascript:void(0)">名称/团队合作简介</a></li>
	            <li id='tag2'><a onclick="selectTag('tagContent2','tag2')" href="javascript:void(0)">引进团队/依托科室简介</a></li>
	            <li id='tag3'><a onclick="selectTag('tagContent3','tag3')" href="javascript:void(0)">团队合作周期目标</a></li>
	            <li id='tag4'><a onclick="selectTag('tagContent4','tag4')" href="javascript:void(0)">团队合作年度目标</a></li>
	            <li id='tag5'><a onclick="selectTag('tagContent5','tag5')" href="javascript:void(0)">引进团队及依托科室人员组成及分工</a></li>
	            <li id='tag6'><a onclick="selectTag('tagContent6','tag6')" href="javascript:void(0)">合作协议</a></li>
	            <li id='tag7'><a onclick="selectTag('tagContent7','tag7')" href="javascript:void(0)">经费预算</a></li>
	            <li id='tag8'><a onclick="selectTag('tagContent8','tag8')" href="javascript:void(0)">专家团队项目合同审核</a></li>
        	</ul>
			</div>
        	<div id="tagContent" style="border-style: none;">
			    <div class="tagContent selectTag" id="tagContent0">
        	      <jsp:include page="step1.jsp">
              	    <jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
                  </jsp:include>
                 </div>
                <div class="tagContent" id="tagContent1">
        	      <jsp:include page="step2.jsp">
              	    <jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
                  </jsp:include>
                 </div>
                 <div class="tagContent" id="tagContent2">
        	      <jsp:include page="step3.jsp">
              	    <jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
                  </jsp:include>
                 </div>
                 <div class="tagContent" id="tagContent3">
        	      <jsp:include page="step4.jsp">
              	    <jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
                  </jsp:include>
                 </div>
                 <div class="tagContent" id="tagContent4">
        	      <jsp:include page="step5.jsp">
              	    <jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
                  </jsp:include>
                 </div>
                 <div class="tagContent" id="tagContent5">
        	      <jsp:include page="step6.jsp">
              	    <jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
                  </jsp:include>
                 </div>
				<div class="tagContent" id="tagContent6">
					<jsp:include page="step7.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
					</jsp:include>
				</div>
				<div class="tagContent" id="tagContent7">
					<jsp:include page="step8.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
					</jsp:include>
				</div>
				<div class="tagContent" id="tagContent8">
					<jsp:include page="step9.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y}"/>
					</jsp:include>
				</div>
            </div>
    </div>  	
  </div>   
</div>
<script type="text/javascript">
     function selectTag(showContent, selfObj) {
         // 操作标签
         var tag = document.getElementById("tags").getElementsByTagName("li");
         var taglength = tag.length;
         for (i = 0; i < taglength; i++) {
             tag[i].className = "";
         }
         $('#'+selfObj).addClass("selectTag");
         // 操作内容
         for (i = 0; j = document.getElementById("tagContent" + i); i++) {
             j.style.display = "none";
         }
         document.getElementById(showContent).style.display = "block";
     }
</script>
</body>
</html>