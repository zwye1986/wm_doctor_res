<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
</jsp:include>
<script type="text/javascript">
	function back() {
		history.back();
	}
	$(function(){
		$('input').attr("readonly","readonly");
		$('textarea').attr("readonly","readonly");
		$("select").attr("disabled", "disabled");
		$(":checkbox").attr("disabled", "disabled");
		$(":radio").attr("disabled", "disabled");
		$(".ctime").removeAttr("onclick");
	});
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
            <li class="selectTag"><a onclick="selectTag('tagContent0',this)" href="javascript:void(0)">基本信息</a></li>
            <li><a onclick="selectTag('tagContent1',this)" href="javascript:void(0)">人员情况</a></li>
            <li><a onclick="selectTag('tagContent2',this)" href="javascript:void(0)">计划指标</a></li>
            <li><a onclick="selectTag('tagContent3',this)" href="javascript:void(0)">经费来源</a></li>
            <li><a onclick="selectTag('tagContent4',this)" href="javascript:void(0)">附件信息</a></li>
           </ul>
	        <div id="tagContent" style="border-style: none;">
	            <div class="tagContent selectTag" id="tagContent0" style="padding-top: 0px;">
					<jsp:include page="step1.jsp">
						<jsp:param value="${GlobalConstant.FLAG_Y}" name="view"/>
					</jsp:include>		
	      		</div>
            <div class="tagContent" id="tagContent1" style="padding-top: 0px;">
            	<jsp:include page="step2.jsp">
					<jsp:param value="${GlobalConstant.FLAG_Y}" name="view"/>
				</jsp:include>
            </div>
            <div class="tagContent" id="tagContent2" style="padding-top: 0px;">
            	<jsp:include page="step3.jsp">
					<jsp:param value="${GlobalConstant.FLAG_Y}" name="view"/>
				</jsp:include>      
            </div>
            <div class="tagContent" id="tagContent3" style="padding-top: 0px;">
            	<jsp:include page="step4.jsp">
					<jsp:param value="${GlobalConstant.FLAG_Y}" name="view"/>
				</jsp:include>
            </div>
            <div class="tagContent" id="tagContent4" style="padding-top: 0px;">
            	<jsp:include page="file.jsp">
					<jsp:param value="${GlobalConstant.FLAG_Y}" name="view"/>
				</jsp:include>
            </div>
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