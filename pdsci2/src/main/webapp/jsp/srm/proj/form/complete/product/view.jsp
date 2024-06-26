<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
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
		
	});
	
	function back() {
		history.back();
	}
</script>
<style type="text/css">
.line {border: none;}
</style>
</head>
<body>
<div id="main">   
   <div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
          <ul id="tags">
            <li class="selectTag"><a onclick="selectTag('tagContent0',this)" href="javascript:void(0)">项目概况</a></li>
            <li><a onclick="selectTag('tagContent1',this)" href="javascript:void(0)">完成情况</a></li>
            <li><a onclick="selectTag('tagContent2',this)" href="javascript:void(0)">人员情况</a></li>
            <li><a onclick="selectTag('tagContent3',this)" href="javascript:void(0)">项目经费</a></li>
            <li><a onclick="selectTag('tagContent4',this)" href="javascript:void(0)">获奖情况</a></li>
            <li><a onclick="selectTag('tagContent5',this)" href="javascript:void(0)">附件情况</a></li>
        </ul>
        <div id="tagContent">
            <div class="tagContent selectTag" id="tagContent0">
					<jsp:include page="step1.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y }"/>
					</jsp:include>
            </div>
            <div class="tagContent" id="tagContent1">
            		<jsp:include page="step2.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y }"/>
					</jsp:include>
            </div>
            <div class="tagContent" id="tagContent2">
            	<jsp:include page="step3.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y }"/>
					</jsp:include>
            </div>
         
            <div class="tagContent" id="tagContent3">
            	<jsp:include page="step5.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y }"/>
					</jsp:include>
            </div>
            <div class='tagContent' id='tagContent4'>
            	<jsp:include page="step6.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y }"/>
					</jsp:include>
            </div>
            <div class="tagContent" id="tagContent5">
            	<jsp:include page="file.jsp">
						<jsp:param  name="view" value="${GlobalConstant.FLAG_Y }"/>
					</jsp:include>
            </div>
        </div>
    </div>
        <p>
        <script type="text/javascript">
            function selectTag(showContent, selfObj) {
                // 操作标签
                var tag = document.getElementById("tags").getElementsByTagName("li");
                var taglength = tag.length;
                for (i = 0; i < taglength; i++) {
                    tag[i].className = "";
                }
                selfObj.parentNode.className = "selectTag";
                // 操作内容
                for (i = 0; j = document.getElementById("tagContent" + i); i++) {
                    j.style.display = "none";
                }
                document.getElementById(showContent).style.display = "block";
            }
        </script>
    </p>
      </div>
    </div>
</div>
</body>
</html>