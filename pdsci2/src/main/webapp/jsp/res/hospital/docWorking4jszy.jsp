<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="false" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
	</jsp:include>
	<style>
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
		table.basic th,table.basic td{ text-align: center;padding: 0; }
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			<c:forEach items="${studentTypes}" var="data">
				$("#"+"${data}").attr("checked","checked");
			</c:forEach>
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
		function detailSearch(userFlow){
			var url = "<s:url value='/res/manager/docWorkDetailSearch'/>?userFlow="+userFlow;
			jboxOpen(url, "详情", 960, 600, true);
		}
		function discipleDetailSearch(userFlow){
			var url = "<s:url value='/res/manager/discipleDetailSearch'/>?userFlow="+userFlow;
			jboxOpen(url, "详情", 760, 360, true);
		}
		function search(){
			$("#searchForm").submit();
		}

		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
        function toPrint() {
            var url = "<s:url value='/res/manager/exportDocWorking4jszy'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        //一键审核
        function oneKeyAudit(audiType){
            var title = "确认一键审核通过";
            if(audiType == "train"){
                title+="培训数据";
            }else if (audiType == "disciple"){
                title+="跟师数据";
            }
            title+="？";

            var url = "<s:url value='/res/manager/oneKeyAudit'/>"
            jboxConfirm(title,function(){
                jboxPost(url,{
                    auditType:audiType
                },function(resp){
                    if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
                        top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                        location.reload(true);
                    }
                },null,false);
            },null);
        }
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" >
		<div class="clearfix" style="padding: 10px 0px;">
			<div class="queryDiv" style=" max-width: 1500px">
			<form id="searchForm" action="<s:url value='/res/manager/doc/docWorking'/>" method="post">
				<input id="currentPage" type="hidden" name="currentPage" value=""/>
				<input type="hidden" name="role" value="${role}"/>
				<div class="inputDiv">
					年&#12288;&#12288;级：
					<input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}"
						   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
				</div>
				<div class="inputDiv">
					姓&#12288;&#12288;名：
					<input type="text" class="qtext" value="${param.docName}" name="docName">
				</div>
				<div class="inputDiv">
					证件号码：
					<input type="text" class="qtext" value="${param.cardNo}" name="cardNo">
				</div>
				<c:if test="${GlobalConstant.USER_LIST_UNIVERSITY ne role}">
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
								<input id="${dict.dictId}" type="checkbox" name="studentTypes" value="${dict.dictId}" <c:if test="${empty param.studentTypes}">checked</c:if>/>${dict.dictName}
							</c:forEach>
						</div>
					</div>
				</c:if>
				<c:if test="${!(param.role eq 'professionalBase')}">
					<div class="inputDiv">
						<label class="qlable">培训基地：</label>
						<select name="orgFlow"  id="orgFlow" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${orgList}" var="org">
								<option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
							</c:forEach>
						</select>
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
				</c:if>
				<div class="inputDiv">
					排序规则：
					<select name="order" class="qselect">
						<option value="wcxx" ${param.order eq 'wcxx'?'selected':''}>完成比例顺序</option>
						<option value="wcdx" ${param.order eq 'wcdx'?'selected':''}>完成比例倒序</option>
						<option value="shxx" ${param.order eq 'shxx'?'selected':''}>审核比例顺序</option>
						<option value="shdx" ${param.order eq 'shdx'?'selected':''}>审核比例倒序</option>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">培训年限：</label>
					<select id="pynx" name="pynx" class="qselect">
						<option />
						<c:forEach items="${dictTypeEnumTrainingYearsList}" var="tr">
							<option value="${tr.dictId}" ${param.pynx eq tr.dictId?'selected':''}>${tr.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">结业考核年份：</label>
					<input type="text" class="qtext" name="graduationYear" readonly="readonly" id="graduationYear" value="${param.graduationYear}"
						   onclick="WdatePicker({dateFmt:'yyyy'})" >
				</div>
                <div  class="inputDiv" style="text-align: center;margin-left: 25px; max-width: 800px">
                    <input type="button" class="search" value="查&#12288;询" onclick="toPage();"/>
                    <input type="button" class="search" value="导&#12288;出" onclick="toPrint();" style="margin-left: 0px"/>
                    <c:forEach items="${auditList}" var="audit">
                        <c:if test="${audit eq 'P010'}">
                            <input type="button" class="search" value="培训数据一键审核" onclick="oneKeyAudit('train');"/>
                        </c:if>
                        <c:if test="${audit eq 'P011'}">
                            <input type="button" class="search" value="跟师数据一键审核" onclick="oneKeyAudit('disciple');"/>
                        </c:if>
                    </c:forEach>
                </div>
			</form>
		</div>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="basic" style="text-align: center ;">
				<tr style="height: 15px;max-height: 15px;">
					<th style="text-align: center" rowspan="2">姓名</th>
					<th style="text-align: center;line-height: 130%;" rowspan="2" width="110">培训基地</th>
					<th style="text-align: center;line-height: 130%;" rowspan="2" width="100">培训专业</th>
					<th style="text-align: center;line-height: 130%;" rowspan="2" width="120">对应专业</th>
					<th style="text-align: center" rowspan="2">年级</th>
					<th style="text-align: center" colspan="5">培训数据</th>
					<th style="text-align: center" colspan="3">轮转科室</th>
					<th style="text-align: center" rowspan="2">跟师数据<br/>完成比例</th>
					<th style="text-align: center" rowspan="2">详细信息</th>
				</tr>
				<tr style="height: 15px;max-height: 15px;">
					<th style="text-align: center;width: 80px;">要求数</th>
					<th style="text-align: center;width: 80px;">完成数</th>
					<th style="text-align: center;width: 80px;">完成比例</th>
					<th style="text-align: center;width: 80px;">审核数</th>
					<th style="text-align: center;width: 80px;">审核比例</th>
					<th style="text-align: center;width: 80px;">要求数</th>
					<th style="text-align: center;width: 80px;">已轮转</th>
					<th style="text-align: center;width: 80px;">已出科</th>
				</tr>
				<c:forEach items="${rltLst}" var="obj">
					<tr>
						<td style="padding-left: 0px;text-align: center">${obj.doctorName}</td>
						<td style="padding-left: 0px;text-align: center;line-height: 130%;">${obj.orgName}</td>
						<td style="padding-left: 0px;text-align: center;line-height: 130%;">${obj.trainingTypeName}</td>
						<td style="padding-left: 0px;text-align: center;line-height: 130%;">${obj.speName}</td>
						<td style="padding-left: 0px;text-align: center">${obj.sessionNumber}</td>
						<td style="padding-left: 0px;text-align: center">${obj.reqNum}</td>
						<td style="padding-left: 0px;text-align: center">${obj.completeNum}</td>
						<td>
							<c:if test="${obj.reqNum == '0'}">
								-
							</c:if>
							<c:if test="${obj.reqNum ne '0'}">
								<fmt:formatNumber type="percent" value="${obj.completeNum/obj.reqNum}" maxFractionDigits="2"/>
							</c:if>
						</td>
						<td style="padding-left: 0px;text-align: center">${obj.checkNum}</td>
						<td>
							<c:if test="${obj.completeNum == '0'}">
								-
							</c:if>
							<c:if test="${obj.completeNum ne '0'}">
								<fmt:formatNumber type="percent" value="${obj.checkNum/obj.completeNum}" maxFractionDigits="2"/>
							</c:if>
						</td>
						<td style="padding-left: 0px;text-align: center">${obj.rotationNum}</td>
						<td style="padding-left: 0px;text-align: center">${obj.lunzhuanNum}</td>
						<td style="padding-left: 0px;text-align: center">${obj.chukeNum}</td>
						<td style="padding-left: 0px;text-align: center">
							<a style="color: blue;cursor: pointer;" onclick="discipleDetailSearch('${obj.doctorFlow}');"
							   class="btn">
									${discipleCountPerCent[obj.doctorFlow] eq '-' ? discipleCountPerCent[obj.doctorFlow]
											: discipleCountPerCent[obj.doctorFlow].concat('%')}
							</a>
						</td>
						<td style="padding-left: 0px;text-align: center"><a style="color: blue;cursor: pointer;" onclick="detailSearch('${obj.doctorFlow}');" class="btn">查看</a></td>
					</tr>
				</c:forEach>
				<c:if test="${empty rltLst}">
					<tr>
						<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;" colspan="14">无数据记录！</td>
					</tr>
				</c:if>
			</table>
			<div>
				<c:set var="pageView" value="${pdfn:getPageView(rltLst)}" scope="request"></c:set>
				<pd:pagination toPage="toPage"/>
			</div>
	</div>
</div>
</body>
</html>

