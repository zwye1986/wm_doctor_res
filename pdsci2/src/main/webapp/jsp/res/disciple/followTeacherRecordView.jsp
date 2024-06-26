
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

	<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

	<script type="text/javascript">
		$(document).ready(function(){
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
			refreshInfo();
		}
		function checkRecord(doctorFlow){
			var currentPage= $("#currentPage").val();
			var roleId = "teacher";
			var url ="<s:url value='/res/folowTeacherRecord/showFollowTeacherRecord'/>?teacherCurrentPage="+currentPage+"&doctorFlow="+doctorFlow+"&roleId="+roleId;
			window.location.href= url;
		}
		function refreshInfo(){
			jboxStartLoading();
			$("#searchForm").submit();
		}
        function agreeRecordBatch(exp){
            var url = "<s:url value='/res/folowTeacherRecord/modifySelected?doctorFlow=${doctorFlow}&batch=Y&roleId=see'/>";
            jboxConfirm("确认审核通过所有学员所有待审核跟师记录？" , function() {
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    setTimeout(function(){
                        location.reload();
                    },1000);
                }, null, false);
            });
        }
		<%--function searchByName(){--%>
			<%--var roleId = "teacher";--%>
			<%--var doctorName= $("#doctorName").val();--%>
			<%--var url ="<s:url value='/res/folowTeacherRecord/auditFollowTeacherRecord'/>?doctorName="+encodeURIComponent(encodeURIComponent(doctorName))+"&roleId="+roleId;--%>
			<%--window.location.href= url;--%>
		<%--}--%>
	</script>

    <style>
        .inputDiv {
            min-width: 20px;
            margin:5px;
        }
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
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<div class="queryDiv">
				<form id="searchForm" action="<s:url value='/res/folowTeacherRecord/auditFollowTeacherRecord'/>"
					  method="post">
					<input id="currentPage" type="hidden" value="1"/>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
								   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
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
					<div class="inputDiv">
						<label class="qlable">对应专业：</label>
						<select id="spe" name="speId" class="qselect">
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
								" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
								<option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
								<option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
								<option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
								<option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
								<option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
							<c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
								<option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</div>
					</div>
						<c:if test="${(scope eq 'ndkhsh') and (roleScope eq 'admin')}">
							<div class="inputDiv">
								<label class="qlable">师承老师：</label>
								<input class="qtext" id="discipleTeacherName" name="discipleTeacherName"
									   value="${param.doctorName}" type="text">
							</div>
						</c:if>
						<div class="inputDiv">
							<label class="qlable">规培学员：</label>
							<input class="qtext" id="doctorName" name="doctorName" value="${param.doctorName}" type="text"/>
						</div>

						<div class="inputDiv">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
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
                        <input style="margin: 5px;" type="button" value="一键审核" class="search" onclick="agreeRecordBatch();"/>
                    </div>
				</form>
			</div>
			<div id="base">
				<table  class="xllist">
					<colgroup>
						<col width="10%"/>
						<col width="15%"/>
						<col width="20%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th  style="text-align: center;">序号</th>
						<th  style="text-align: center;">姓名</th>
						<th style="text-align: center;">培训专业</th>
						<th style="text-align: center;">对应专业</th>
						<th style="text-align: center;">年级</th>
						<th  style="text-align: center;">跟师记录</th>
					</tr>
					<c:if test="${not empty resStudentDiscipleTeachers}">
						<c:forEach items="${resStudentDiscipleTeachers}" var="resStudentDiscipleTeacher" varStatus="num">
							<tr>
								<td>${num.count }</td>
								<td>${resStudentDiscipleTeacher.doctorName}</td>
								<td>${resStudentDiscipleTeacher.doctorCategoryName}</td>
								<td>${resStudentDiscipleTeacher.trainingSpeName}</td>
								<td>${resStudentDiscipleTeacher.sessionNumber}</td>
								<td>
									<a style="color: blue;" href="javascript:void(0)" onclick="checkRecord('${resStudentDiscipleTeacher.doctorFlow}');">审核</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${empty resStudentDiscipleTeachers}">
						<tr><td colspan="99">暂无待审核跟师记录</td></tr>
					</c:if>
				</table>
				<c:if test="${not empty resStudentDiscipleTeachers}">
					<div>
						<c:set var="pageView" value="${pdfn:getPageView(resStudentDiscipleTeachers)}" scope="request"></c:set>
						<pd:pagination toPage="toPage"/>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
</body>
</html>