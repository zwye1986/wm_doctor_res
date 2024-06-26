<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
		<jsp:param name="jquery_validation" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="false"/>
		<jsp:param name="jquery_placeholder" value="false"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>

	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
		table.basic th,table.basic td{text-align: center;padding: 0px;}
		table a{color: #2f8cef;cursor: pointer;}
		.finish{color:green;font-weight: bold;}
		.toAudit:HOVER{background-color: #eee;}
		.toAudit{cursor: pointer;}
		.toInfo:HOVER{color: orange;}
	</style>

	<script type="text/javascript">
		function search(){
			$("#searchForm").submit();
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
				$("#boxHome").unbind("mouseenter mouseleave"); //先进行解绑，否则失效
				$("#boxHome").on("mouseenter mouseleave",function(){
					$(this).toggleClass("on");
				});

				$("#boxHome .item").click(function(){
					$("#boxHome").hide();
					var value = $(this).attr("value");
					$("#speId").val($(this).attr("flow"));
					$("#itemName").val(value);
					searchInput.val(value);
					if(option.clickActive){
						option['clickActive']($(this).attr("flow"));

					}
				});
			};


		})(jQuery);
		$(function () {

			$("#speSel").likeSearchInit({

			});

			if("${click}" == "${GlobalConstant.FLAG_Y}")
			{
				parent.window.document.getElementById("res-pt-pxtj-jszyreport10").click();
			}
			if("${param.onlyOrg}" == "${GlobalConstant.FLAG_Y}"){
				$("#orgFlow").attr("disabled","disabled");
			}
		});
		<%--function defaultImg(img){--%>
		<%--img.src = "<s:url value='/css/skin/up-pic.jpg'/>";--%>
		<%--}--%>
		function toPage(page) {
			$("#currentPage").val(page);
			search();
		}
		function info(doctorFlow,processFlow){
			var url="<s:url value='/res/teacher/details'/>?doctorFlow="+doctorFlow+"&processFlow="+processFlow;
			jboxOpen(url,"登记数据", 900,410,true);
		}
		function details(doctorFlow,doctorName){
			var url="<s:url value='/res/teacher/schDetails'/>?doctorFlow="+doctorFlow;
			jboxOpen(url,doctorName+"轮转数据详情", 800,410,true);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<%-- <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_GLOBAL}">
                 <div class="queryDiv" style="min-width: 800px;max-width: 800px;">
                     <form id="searchForm" action="<s:url value='/res/teacher/searchInfo'/>" method="post">
                         <input id="currentPage" type="hidden" name="currentPage" value=""/>
                         <input type="hidden" name="roleFlag" value="${param.roleFlag}">
                         <input type="hidden" name="onlyOrg" value="${param.onlyOrg}">
                         <c:if test="${param.onlyOrg eq 'Y'}">
                         <div class="inputDiv">
                             <label class="qlable">基&#12288;&#12288;地：</label>
                             <select name="orgFlow"  id="orgFlow" class="qselect" >
                                 <option value="">全部</option>
                                 <c:forEach items="${orgs}" var="org">
                                     <option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                                 </c:forEach>
                             </select>
                         </div>
                         </c:if>
                         &lt;%&ndash;<c:if test="${param.onlyOrg ne 'Y'}">&ndash;%&gt;
                             <input type="hidden" name="orgFlow" value="${param.orgFlow}">
                         &lt;%&ndash;</c:if>&ndash;%&gt;
                         <div class="inputDiv">
                             <label class="qlable">进修专业：</label>
                             &lt;%&ndash;<input id="speSel"  class="toggleView qtext" type="text" name="speName2" value="${param.speName2}" autocomplete="off" title="${param.speName2}" onmouseover="this.title = this.value"/>
                             <input id="speId"  type="hidden" name="speId" value="${param.speId}"/>
                             <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:34px;left:100px;">
                                 <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 150px;border-top: none;position: relative;display: none;">
                                         <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                             <p class="item" flow="${dict.dictId}" value="${dict.dictName}" style="height: 30px;text-align: left;">${dict.dictName}</p>
                                         </c:forEach>
                                 </div>
                             </div>&ndash;%&gt;
                             <select name="" class="qselect validate[required]" style="width: 157px;margin-left: 0px;">
                                 <option value="">请选择</option>
                                 <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                                     <option value="${SysDept.deptFlow}" ${speForm.speId eq SysDept.deptFlow?'selected':''}>${SysDept.deptName}</option>
                                 </c:forEach>
                             </select>
                         </div>
                         <div class="inputDiv">
                             <label class="qlable">姓&#12288;&#12288;名：</label>
                             <input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
                         </div>
                        &lt;%&ndash; <div class="inputDiv">
                             <label class="qlable">培训专业：</label>
                             <select name="trainingSpeId" class="qselect">
                                 <option value="">全部</option>
                                 <c:forEach items="${dictTypeEnumDoctorTrainingSpeList }" var="dict">
                                     <option value="${dict.dictId }"<c:if test="${param.trainingSpeId eq dict.dictId  }">selected</c:if>>${dict.dictName }</option>
                                 </c:forEach>
                             </select>
                         </div>&ndash;%&gt;
                        &lt;%&ndash; <div class="inputDiv">
                             <label class="qlable">年&#12288;&#12288;级：</label>
                             <select name="sessionNumber" class="qselect">
                                 <option value="">全部</option>
                                 <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
                                     <option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
                                 </c:forEach>
                             </select>
                         </div>&ndash;%&gt;
                         <div class="qcheckboxDiv">
                             &#12288;&#12288;<label class="qlable">
                             <input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""} />
                             仅看轮转中学员
                         </label>
                             &#12288;&#12288;
                             <input type="button" class="searchInput" onclick="search();" value="查&#12288;询"/>

                         </div>
                     </form>
                 </div>
             </c:if>--%>
			<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN }">
				<div class="queryDiv" style="min-width: 800px;max-width: 800px;">
					<form id="searchForm" action="<s:url value='/res/teacher/searchInfo'/>" method="post">
						<input id="currentPage" type="hidden" name="currentPage" value=""/>
						<input type="hidden" name="roleFlag" value="${param.roleFlag}">
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
						</div>
						<div class="inputDiv">
							<label class="qlable">进修专业：</label>
							<select name="deptFlow" class="qselect validate[required]" style="width: 157px;margin-left: 0px;">
								<option value="">全部</option>
								<c:forEach items="${sessionScope.currDeptList}" var="SysDept">
									<option value="${SysDept.deptFlow}" ${param.deptFlow eq SysDept.deptFlow?'selected':''}>${SysDept.deptName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">轮转科室：</label>
							<select class="qselect" name="schDeptFlow">
								<option value="">全部</option>
								<c:forEach items="${schDeptList}" var="schDept">
									<option value="${schDept.schDeptFlow}" ${param.schDeptFlow eq schDept.schDeptFlow?'selected':''}>${schDept.schDeptName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="qcheckboxDiv">
							&#12288;&#12288;<label class="qlable">
							<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""}<%-- onchange="showView();"--%>>
							轮转中学员
						</label>
							&#12288;&#12288;
							<input type="button" class="searchInput" onclick="search();" value="查&#12288;询"/>

						</div>
					</form>
				</div>
			</c:if>
			<c:if test="${roleFlag ne GlobalConstant.RES_ROLE_SCOPE_ADMIN and roleFlag ne GlobalConstant.RES_ROLE_SCOPE_GLOBAL}">
				<div class="queryDiv">
					<form id="searchForm" action="<s:url value='/res/teacher/searchInfo'/>" method="post">
						<input id="currentPage" type="hidden" name="currentPage" value=""/>
						<input type="hidden" name="roleFlag" value="${param.roleFlag}">
						<div class="inputDiv">
							<label class="qlable">进修专业：</label>
							<select name="deptFlow" class="qselect validate[required]" style="width: 157px;margin-left: 0px;">
								<option value="">全部</option>
								<c:forEach items="${sessionScope.currDeptList}" var="SysDept">
									<option value="${SysDept.deptFlow}" ${param.deptFlow eq SysDept.deptFlow?'selected':''}>${SysDept.deptName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
						</div>
						<div class="qcheckboxDiv" style="min-width: 115px;max-width: 115px;">
							&#12288;&#12288;<label class="qlable">
							<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""}<%-- onchange="showView();"--%>>
							轮转中学员
						</label>
						</div>
						<div class="lastDiv">
							<input type="button" class="searchInput" onclick="search();" value="查&#12288;询"/>
						</div>
					</form>
				</div>
			</c:if>
		</div>
		<c:set var="preKey" value="res_${preRecTypeEnumPreTrainForm.id}_form_flag"/>
		<table class="basic" width="100%">
			<tr>
				<th style="min-width: 80px;">姓名</th>
				<th style="min-width: 80px;">证件号</th>
				<th style="min-width: 100px;">进修专业</th>
				<th style="min-width: 50px;">登记数据</th>
			</tr>
			<c:forEach items="${doctorList}" var="doctor">
				<tr>
					<td>
							${userMap[doctor.doctorFlow].userName}
					</td>
					<td>
							${userMap[doctor.doctorFlow].idNo}
					</td>
					<td>${doctor.trainingSpeName}</td>
					<td>
						<a style="color: blue" onclick="details('${doctor.doctorFlow}','${doctor.doctorName}')">详情</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty doctorList}">
				<tr><td colspan="20">无记录</td></tr>
			</c:if>
		</table>

		<div class="resultDiv">
			<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
<div id="yearAuditHome">

</div>
</body>
</html>