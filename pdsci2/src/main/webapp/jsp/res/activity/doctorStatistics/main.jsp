<%@include file="/jsp/common/doctype.jsp" %>
<html>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
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
	$(document).ready(function(){
		$("#doctorCategory").change(function(){
			$("#spe").empty();
			if(this.value)
				$("#spe").append($("."+this.value).clone());
		}).change();
		toPage(1);
	});
	function toPage(page) {
		var data="";
		<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
			if($("#"+"${type.dictId}").attr("checked")){
				data+="&datas="+$("#"+"${type.dictId}").val();
			}
		</c:forEach>
        <c:if test="${GlobalConstant.USER_LIST_UNIVERSITY ne roleFlag}">
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
		</c:if>
		$("#currentPage").val(page);
		jboxLoad("doctorListZi","<s:url value='/res/doctorActivityStatistics/list'/>?"+$("#searchForm").serialize(),true);
	}
	function exportExcel(){
		var url = "<s:url value='/res/doctorActivityStatistics/exportList'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>

<div class="mainright" style="min-height: 400px;"  >
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm">
			    <input type="hidden" id="currentPage" name="currentPage"/>
			    <input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
			    <input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">培训类别：</label>
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
                        <label class="qlable">培训专业：</label>
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
					<c:if test="${GlobalConstant.USER_LIST_UNIVERSITY ne roleFlag}">
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
								<input id="${dict.dictId}" type="checkbox" name="datas" checked value="${dict.dictId}" />&nbsp;${dict.dictName}&nbsp;
							</c:forEach>
						</div>
					</div>
					</c:if>
                    <div class="inputDiv">
                        <label class="qlable">年&#12288;&#12288;级：</label>
                        <input type="text" id="sessionNumber" name="sessionNumber"value="${pdfn:getCurrYearByMonth()}" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input type="text" name="userName" class="qtext"  value=""/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">开始时间：</label>
                        <input type="text" id="startDate" name="startTime" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">结束时间：</label>
                        <input type="text" id="endDate" name="endTime" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </div>
					<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleFlag || GlobalConstant.USER_LIST_UNIVERSITY eq roleFlag}">
						<div class="inputDiv">
							<label class="qlable">培训基地：</label>
							<select name="orgFlow" class="qselect">
								<option value="">全部</option>
								<c:forEach items="${orgs}" var="org">
									<option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<div class="inputDiv">
						<label class="qlable">证件号码：</label>
						<input type="text" name="idNo" value="${param.idNo}" class="qtext" />
					</div>
					<div class="lastDiv" style="max-width: 181px;min-width: 181px;">
						<input class="search" type="button" value="查&#12288;询" onclick="toPage(1);"/>
						<input class="search" type="button" value="导&#12288;出" onclick="exportExcel();"/>
					</div>
                </div>

            </form>
        </div>
		<div id="doctorListZi">
		</div>
    </div>
</div>