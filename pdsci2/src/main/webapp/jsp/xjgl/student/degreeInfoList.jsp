<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
	</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<style type="text/css">
		input[type='text']{width:133px;}
	</style>
	<script type="text/javascript">
		function editInfo(userFlow){
			var title = userFlow == ""?"新增":"编辑";
			var url = "<s:url value='/xjgl/user/editDegreeInfo?userFlow='/>"+userFlow;
			jboxOpen(url, title,860,600);
		}
		function expExcel(){
			var url = "<s:url value='/xjgl/user/exportDegree'/>";
			jboxTip("导出中…………");
			jboxSubmit($("#searchForm"), url, null, null, false);
			jboxEndLoading();
		}
		function toPage(page){
			$("#currentPage").val(page);
			if($("#searchParam_Major").val()==""){
				$("#result_Major").val("");
			}
			if($("#orgName").val() != ""){
				$("#orgFlow").val($("#orgName").attr("flow"));
			}else{
				$("#orgFlow").val("");
			}
			$("#searchForm").submit();
		}
		//专业检索
		$(document).ready(function(){
			loadMajorJson();
		});
		function loadMajorJson(){
			var majorArray = new Array();
			var url = "<s:url value='/xjgl/majorCourse/searchMajorJson'/>";
			jboxPost(url,null,function(data){
				if(data!=null){
					for (var i = 0; i < data.length; i++) {
						var dictId=data[i].dictId;
						if(data[i].dictId==null){
							dictId="";
						}
						var dictName=data[i].dictName;
						if(data[i].dictName==null){
							dictName="";
						}
						majorArray[i]=new Array(dictId, dictName, dictId);
						if($("#result_Major").val() == dictId){
							$("#searchParam_Major").val(dictName);
						}
					}
				}
			},null,false);
			$("#searchParam_Major").suggest(majorArray,{
				attachObject:'#suggest_Major',
				dataContainer:'#result_Major',
				triggerFunc:function(majorId){},
				enterFunc:function(){}
			});
		}
		function adjustResults() {
			$("#suggest_Major").css("left",$("#searchParam_Major").offset().left);
			$("#suggest_Major").css("top",$("#searchParam_Major").offset().top+$("#searchParam_Major").outerHeight());
		}

		$(function(){
			$("#orgName").likeSearchInit({});
		});
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/xjgl/user/degreeInfoList"/>" method="post">
			<div class="choseDivNewStyle">
				<input id="currentPage" type="hidden" name="currentPage" value="1"/>
				<input type="hidden" name="role" value="${role}"/>
				<c:if test="${role ne 'xs'}">
					<span style=""></span>学&#12288;&#12288;号：
					<input type="text" name="sid" value="${param.sid}">
					<span style="padding-left:10px;"></span>姓&#12288;&#12288;名：
					<input type="text" name="userName" value="${param.userName}">
					<span style="padding-left:10px;"></span>性&#12288;&#12288;别：
					<select name="sexId" style="width:137px;" class="select">
						<option/>
						<c:forEach items="${userSexEnumList}" var="sex">
							<c:if test="${sex.id != userSexEnumUnknown.id}">
								<option value="${sex.id}" ${param.sexId eq sex.id?'selected':''}>${sex.name}</option>
							</c:if>
						</c:forEach>
					</select>
					<span style="padding-left:10px;"></span>培养类型：
					<select name="trainCategoryId" style="width:137px;" class="select">
						<option></option>
						<c:forEach items="${dictTypeEnumTrainCategoryList}" var="cate">
							<option value="${cate.dictId}" ${param.trainCategoryId eq cate.dictId?'selected':''}>${cate.dictName}</option>
						</c:forEach>
					</select>
					<br/>
					<span style="padding-left:0px;"></span>培养层次：
					<select name="trainTypeId" style="width:137px;" class="select">
						<option></option>
						<c:forEach items="${dictTypeEnumTrainTypeList}" var="type">
							<option value="${type.dictId}" ${param.trainTypeId eq type.dictId?'selected':''}>${type.dictName}</option>
						</c:forEach>
					</select>
					<span style="padding-left:10px;"></span>专业名称：
					<input  id="searchParam_Major"  placeholder="输入专业名称/代码"  style="width: 133px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
					<div id="suggest_Major" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 150px;"></div>
					<input type="hidden" id="result_Major" name="majorId" value="${param.majorId}"/>
					<span style="padding-left:10px;"></span>入学年级：
					<input type="text" name="period" value="${param.period}" placeholder="多年份用“-”隔开"/>
					<span style="padding-left:10px;"></span>民&#12288;&#12288;族：
					<select class="select" name="nationId" style="width:137px;">
						<option/>
						<c:forEach items="${userNationEnumList}" var="nation">
							<option value="${nation.id}" ${param.nationId eq nation.id?'selected':''}>${nation.name}</option>
						</c:forEach>
					</select>
					<br/>
					<span style="padding-left:0px;"></span>政治面貌：
					<select class="select" name="politicsStatusId" style="width:137px;">
						<option/>
						<c:forEach items="${politicsAppearanceEnumList}" var="politics">
							<option value="${politics.id}" ${param.politicsStatusId eq politics.id?'selected':''}>${politics.name}</option>
						</c:forEach>
					</select>
					<c:if test="${role eq 'fwh' or empty role}">
						<span style="padding-left:10px;"></span>培养单位：
						<input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value" flow="${param.orgFlow}"/>
						<div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:287px;">
							<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
								<c:forEach items="${orgList}" var="org">
									<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
								</c:forEach>
							</div>
						</div><input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow}"/>
					</c:if>
					<c:if test="${role eq 'local' or empty role}">
						<span style="padding-left:10px;"></span>分&ensp;委&ensp;会：
						<select style="width: 137px;" name="deptFlow">
							<option></option>
							<c:forEach items="${deptList }" var="dept">
								<option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${empty role}">
						<span style="padding-left:10px;"></span>学习形式：
						<select style="width: 137px;" name="modifyUserFlow">
							<option/>
							<c:forEach items="${dictTypeEnumStudyFormList}" var="studyForm">
								<option value="${studyForm.dictId}" ${param.modifyUserFlow eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
							</c:forEach>
						</select>
						<br/>起止时间：
						<span><input type="text" name="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.createTime}" style="width: 80px;" onchange="checkDay(this);"> - <input type="text" name="createUserFlow" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.createUserFlow}" style="width: 80px;" onchange="checkDay(this);"></span>
					</c:if>
				</c:if>
				<c:if test="${role eq 'xs' && empty mainList}">
					<input type="button" class="search" value="信息采集" onclick="editInfo('')"/>
				</c:if>
				<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
				<input type="button" class="search" value="导&#12288;出" onclick="expExcel()"/>
			</div>
		</form>
		<table class="xllist" style="width:100%;">
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>培养类型</th>
				<th>培养层次</th>
				<th>专业名称</th>
				<th>国家或地区</th>
				<th>民族</th>
				<th>政治面貌</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${mainList}" var="main">
				<tr>
					<td>${main.sid}</td>
					<td>${main.userName}</td>
					<td>${main.sexName}</td>
					<td>${main.trainCategoryName}</td>
					<td>${main.trainTypeName}</td>
					<td>${main.majorName}</td>
					<td><c:forEach items="${userCountryAreaEnumList}" var="area">${main.countryArea eq area.id?area.name:''}</c:forEach></td>
					<td>${main.nationName}</td>
					<td>${main.politicsStatusName}</td>
					<td>
						<a onclick="editInfo('${main.userFlow}');" style="cursor:pointer;color:blue;">编辑</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty mainList}">
				<tr><td colspan="99">无记录</td></tr>
			</c:if>
		</table>
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(mainList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>