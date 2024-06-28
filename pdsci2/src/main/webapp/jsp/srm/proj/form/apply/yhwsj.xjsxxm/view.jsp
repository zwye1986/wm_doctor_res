<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>

<script type="text/javascript">
	$(function(){
		$(".redspan").hide();
		$('input').attr("readonly","readonly");
		$('textarea').attr("readonly","readonly");
		$("select").attr("disabled", "disabled");
		$(":checkbox").attr("disabled", "disabled");
		$(":radio").attr("disabled", "disabled");
		$(".ctime").removeAttr("onclick");
	});
	function back() {
		history.back();
	}
	$(document).ready(function(){
		$("#tags").find("li:first").addClass("selectTag");
		$(".tagContent:first").show();
	});
	
</script>

<div id="main">   
<div class="mainright">
<div class="content">
		<div class="title1 clearfix">
			<ul id="tags">
				<li><a id="0" onclick="selectTag('tagContent0',this)" href="javascript:void(0)">评选申报表</a></li>
				<li><a onclick="selectTag('tagContent1',this)" href="javascript:void(0)">评分标准</a></li>
				<li><a onclick="selectTag('tagContent2',this)" href="javascript:void(0)">附件信息</a></li>
			</ul>
			
	        <div id="tagContent" style="margin-top: 20px;border:none;">
				<!-- 一、信息表 -->
				<c:if test="${expertFlag!=GlobalConstant.FLAG_Y}">
	            <div class="tagContent " id="tagContent0" style="padding-top: 0px;">
					<jsp:include page="step1.jsp" flush="true">
						<jsp:param  name="view" value="${ GlobalConstant.FLAG_Y}"/>
					</jsp:include>
	            </div>
	            </c:if>
	            <div class="tagContent " id="tagContent1" style="padding-top: 0px;">
					<jsp:include page="step2.jsp" flush="true">
						<jsp:param  name="view" value="${ GlobalConstant.FLAG_Y}"/>
					</jsp:include>
	            </div>	            
	            <div class="tagContent " id="tagContent2" style="padding-top: 0px;">
					<jsp:include page="step3.jsp" flush="true">
						<jsp:param  name="view" value="${ GlobalConstant.FLAG_Y}"/>
					</jsp:include>
	            </div>	            
        	</div>
   		</div>
		<p>
		    <script type="text/javascript">
		        function selectTag(showContent, selfObj) {
		        	$(".selectTag").removeClass("selectTag");
		        	$(selfObj).parent().addClass("selectTag");
		        	$(".tagContent").hide();
		        	$("#"+showContent).show();
		        }
		    </script>
		</p>
</div>
</div>
</div>
