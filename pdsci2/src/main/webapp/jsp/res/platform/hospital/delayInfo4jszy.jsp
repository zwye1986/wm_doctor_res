<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		#boxHome .item:HOVER{background-color: #eee;}
		.doctorTypeDiv {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeLabel {
			border: 0px;
			float: left;
			width: 96px;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeContent {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
	</style>
<script type="text/javascript">
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
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);
//======================================
//页面加载完成时调用
$(function(){
	$("#orgSel").likeSearchInit({
		clickActive:function(flow){
			$("."+flow).show();
		}
	});
	$("#doctorCategory").change(function(){
		$("#spe").empty();
		$("#spe").append($("."+this.value).clone());
		if(this.value=='${recDocCategoryEnumIntern.id}'){
			$("#work").show();
		}else{
			$("#work").hide();
			$("#workOrgId").val("");
		}
	}).change();
});

function toPage(page) {
	var currentPage="";
	if(!page||page!=undefined){
		currentPage=page;
	}
	if(page==undefined||page==""){
		currentPage=1;
	}
	$("#currentPage").val(currentPage);
	searchRecInfo();
}
function searchRecInfo(){
	jboxStartLoading();
	$("#searchForm").submit();
}
function editDoc(doctorFlow){
	//jboxOpen("<s:url value='/res/manager/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}&editButtonFlag=no',"编辑医师信息",950,490);
	var url = "<s:url value='/res/manager/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}&editButtonFlag=no';
	var mainIframe = window.parent.frames["mainIframe"];
	var width = 950;
	var height = 490;
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,'用户信息',width,height);
}
</script>
</head>
<body>
<%--<c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y}">--%>
	<%--<div class="main_hd">--%>
	    <%--<h2 class="underline">医师退培管理</h2> --%>
	<%--</div>--%>
<%--</c:if>--%>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/doc/delayInfo?roleFlag=local" />" method="post">
				<input type="hidden" value="${param.currentPage}" id="currentPage" name="currentPage" class="qtext"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" value="${param.doctorName}" name="doctorName" class="qtext"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">培训专业：</label>
						<select id="doctorCategory" name="doctorCategoryId" class="qselect">
							<option />
							<c:forEach items="${recDocCategoryEnumList}" var="category">
								<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
								<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
									<option value="${category.id}" ${param.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<c:set var="docType" value="${type.dictId},"/>
								<label><input type="checkbox" name="datas" value="${type.dictId}" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="inputDiv">
						<label class="qlable">对应专业：</label>
						<select id="spe" name="trainingSpeId" class="qselect">
							<option></option>
						</select>
						<div style="display: none;">
							<option
									class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								${recDocCategoryEnumWMFirst.id} |
								${recDocCategoryEnumWMSecond.id} |
								${recDocCategoryEnumAssiGeneral.id} |
								${recDocCategoryEnumChineseMedicine.id} |
								${recDocCategoryEnumTCMGeneral.id} |
								${recDocCategoryEnumTCMAssiGeneral.id} |
								" value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option
										class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
								<option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
								<option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
								<option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
								<option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
								<option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
								<option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</div>
					</div>
					<div class="inputDiv">
						<label style="cursor: pointer;" id='jointOrg'>&#12288;&#12288;&nbsp;<input type="checkbox" id="jointOrgFlag"  <c:if test="${param.jointOrg eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrg" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
						&#12288;<input style="margin-bottom: 10px;" class="searchInput" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>
					</div>
				</div>
		 </form>
	 	</div>
        <table class="xllist">
			<colgroup>
				<col width="8%"/>
				<col width="8%"/>
				<col width="18%"/>
				<col width="8%"/>
				<col width="15%"/>
				<col width="10%"/>
				<col width="15%"/>
				<col width="9%"/>
				<col width="9%"/>
			</colgroup>
        	<tr>
        		<th>姓名</th>
				<th>年级</th>
				<th>培训基地</th>
				<th>培训专业</th>
				<th>对应专业</th>
        		<th>结业考核年份</th>
        		<th>延期原因</th>
        		<th>附件信息</th>
        		<th>详情</th>
        	</tr>
			<c:forEach items="${resRecList}" var="rec">
				<tr>
					<td>${rec.doctorName}</td>
					<td>${rec.sessionNumber}</td>
					<td title="${rec.orgName}">
							${pdfn:cutString(rec.orgName,15,true,3)}
					</td>
					<td>${rec.trainingTypeName}</td>
					<td title="${rec.trainingSpeName}">
							${pdfn:cutString(rec.trainingSpeName,15,true,3) }
					</td>
					<td>${rec.graduationYear}</td>
					<td title="${rec.delayreason}">
							${pdfn:cutString(rec.delayreason,15,true,3)}
					</td>
					<td>
						<c:forEach items="${fileMaps[rec.recordFlow]}" var="file">
							<a href="<s:url value='/res/doc/fileDown'/>?fileFlow=${file.fileFlow}">[下载附件]</a>
						</c:forEach>
					</td>
					<td>
						<a style="color:blue;cursor:pointer;" onclick="editDoc('${rec.doctorFlow}');">详情</a>
					</td>
				</tr>
			</c:forEach>
        	<c:if test="${empty resRecList}">
        		<tr>
        			<td colspan="10">无记录</td>
        		</tr>
        	</c:if>
        </table>
		<c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
	</div>
</body>
</html>