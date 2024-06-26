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

<script type="text/javascript">
	var currSelDoc;
	$(function(){
		$("#detail").slideInit({
			width:800,
			speed:500,
			outClose:true
		});
		$("#internshipArchives").slideInit({
			width:1000,
			speed:300,
			outClose:true
		});
	});
	
// 	function toggleView(flag){
// 		var right = parseFloat($("#detail").css("right").replace("px",""));
// 		slide(right,flag);
// 	}
	
// 	function slide(right,flag){
// 		setTimeout(function(){
// 			var endFlag;
// 			if(flag)
// 				endFlag = right<0;
// 			else
// 				endFlag = right>=-800;
// 			if(endFlag){
// 				if(flag)
// 				right+=8;
// 				else
// 				right-=8;	
// 				$("#detail").css("right",right+"px");
// 				slide(right,flag);
// 			}else{
// 				if(!flag){
// 					$("#detail").hide();
// 				}
// 			}
// 		},1);
// 	}
	
	function search(){
		$("#recSearchForm").submit();
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}
	
	function loadDetail(doctorFlow){
		jboxStartLoading();
		jboxGet("<s:url value='/res/manager/doctorDetail'/>?roleFlag=${scope}&doctorFlow="+doctorFlow,null,function(resp){
			$("#detail").html(resp).rightSlideOpen();
			jboxEndLoading();
		},function(){jboxEndLoading();},false);
	}
	
	function processViewMain(doctorFlow){
		window.open("<s:url value='/res/manager/doctorRegistryDetatil'/>?userFlow="+doctorFlow,"_blank");
	}
	
	function internshipArchives(doctorFlow){
		currSelDoc = doctorFlow;
		$("#internshipArchives").rightSlideOpen(function(){
			$(".selectTag").click();
		});
	}
	
	function archivesSelTag(selObj,type){
		$(".selectTag").removeClass("selectTag");
		$(selObj).addClass("selectTag");
		
		var url = "<s:url value='/res/rec/showRecForm'/>?roleFlag=${scope}&recTypeId="+type+"&operUserFlow="+currSelDoc;
		jboxLoad("tagContent",url,false);
	}
	
	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}
	
	function edit(flow){
		var url="<s:url value='/res/teacher/showDocAndUser'/>?flow="+flow;
		jboxOpen(url, "信息", 1000, 500);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="recSearchForm" method="post" action="<s:url value='/res/teacher/tutorView'/>">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="queryDiv">
				<div class="inputDiv">
					<label class="qlable">培训基地：</label>
					<select name="orgFlow" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${orgList}" var="org">
							<option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
						</c:forEach>
					</select>
				</div>
				<c:if test="${scope==GlobalConstant.USER_LIST_CHARGE }">
				<div class="inputDiv">
					<label class="qlable">学&#12288;&#12288;院：</label>
					<select name="deptFlow" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${deptList}" var="dept">
						<option value="${dept.deptFlow}" ${param.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
						</c:forEach>
					</select>
				</div>
				</c:if>
				<div class="inputDiv">
					<label class="qlable">人员类型：</label>
					<select id="doctorCategory" name="doctorCategoryId" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${recDocCategoryEnumList}" var="category">
						<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
						<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
						<option value="${category.id}" ${param.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
						</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">年&#12288;&#12288;级：</label>
					<select name="sessionNumber" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
						<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">培训专业：</label>
					<select name="trainingSpeId" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
						<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
				姓&#12288;&#12288;名：
				<input type="text" name="userName" value="${param.userName}" class="qtext"/>
				</div>
				<div class="lastDiv">
					<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
				</div>
			</div>

		</form>


		<table class="xllist" width="100%">
			<tr>
				<th style="width:10%">姓名</th>
				<th style="width:5%">性别</th>
				<th style="width:10%">电话</th>
				<th style="width:5%">年级</th>
				<th style="width:10%">专业</th>
				<th style="width:15%">培训基地</th>
				<th style="width:8%">入院时间</th>
				<th style="width:15%">身份证号</th>
				<th style="width:10%">轮转进度</th>
				<th style="width:12%">操作</th>
			</tr>
			<c:forEach items="${doctorExtList}" var="doctorExt">
				<tr>
					<td onclick="edit('${doctorExt.doctorFlow}');" title="<img src='${sysCfgMap['upload_base_url']}/${doctorExt.sysUser.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>" style="cursor:pointer;"><a>${doctorExt.sysUser.userName}</a></td>
					<td>${doctorExt.sysUser.sexName}</td>
					<td>${doctorExt.sysUser.userPhone}</td>
					<td>${doctorExt.sessionNumber}</td>
					<td>${doctorExt.trainingTypeName}</td>
					<td>${doctorExt.orgName}</td>
					<td>${doctorExt.inHosDate}</td>
					<td>${doctorExt.sysUser.idNo}</td>
					<td>${processCountMap[doctorExt.doctorFlow]+0}/${resultCountMap[doctorExt.doctorFlow]+0}</td>
					<td>
					<a style="color: blue;cursor: pointer;" onclick="loadDetail('${doctorExt.doctorFlow}');">轮转详情</a>
<%-- 					<a style="color: blue;cursor: pointer;" onclick="processViewMain('${doctorExt.doctorFlow}');">轮转详情</a> --%>
<!-- 					| -->
					<c:if test="${recDocCategoryEnumIntern.id eq doctorExt.doctorCategoryId}">
						|
						<a style="color: blue;cursor: pointer;" onclick="internshipArchives('${doctorExt.doctorFlow}');">实习档案</a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty doctorExtList}">
				<tr><td colspan="99">无记录</td></tr>
			</c:if>
		</table>
		<div>
	       	<c:set var="pageView" value="${pdfn:getPageView(doctorExtList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>	 
	    </div>
	</div>
</div>
	<div id="detail" style="height: 100%;
		background: url(<s:url value='/css/skin/${skinPath}/images/detail_shadow.jpg'/>) left repeat-y;
		width: 800px;
		padding-left:11px; 
		position: fixed;
		z-index: 1000;
		display: none;
		right: -800px;
		top: 0"></div>
	<div id="internshipArchives">
		<div id="archivesInfo" style="width: 98%;height: 100%;background-color: white;padding: 10px;overflow: auto;">
			<ul id="tags">
				<li class="selectTag" onclick="archivesSelTag(this,'${resRecTypeEnumEthics.id}');" style="cursor: pointer;"><a>${resRecTypeEnumEthics.name}</a></li>
				<li onclick="archivesSelTag(this,'${resRecTypeEnumDocument.id}');" style="cursor: pointer;"><a>${resRecTypeEnumDocument.name}</a></li>
				<li onclick="archivesSelTag(this,'${resRecTypeEnumAppraisal.id}');" style="cursor: pointer;"><a>${resRecTypeEnumAppraisal.name}</a></li>
				<li onclick="archivesSelTag(this,'${resRecTypeEnumAppraisal.id}');" style="cursor: pointer;"><a>实习档案</a></li>
		    </ul>
		    <div id="tagContent" class="divContent">
		    </div>
		</div>
	</div>
</body>
</html>