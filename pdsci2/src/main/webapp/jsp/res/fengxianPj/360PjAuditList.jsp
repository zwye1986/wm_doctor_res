<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
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
    </style>
    <script type="text/javascript">
        function search(){
            $("#isFirst").val("0");
            $("#searchForm").submit();
        }

        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function defaultImg(img){
            img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
        }

        function opRec(recFlow,schDeptFlow, rotationFlow, userFlow, schResultFlow,typeId,typeName,processFlow){
            var url = "<s:url value='/res/fengxianPj/show360EvlForm'/>"+
                    "?schDeptFlow="+schDeptFlow+
                    "&rotationFlow="+rotationFlow+
                    "&recTypeId="+typeId+"&userFlow="+userFlow+
                    "&roleFlag=${roleFlag}&openType=open"+
                    "&resultFlow="+schResultFlow+
                    "&recFlow="+recFlow+
                    "&processFlow="+processFlow+
                    "&operUserFlow="+userFlow;
            jboxOpen(url, typeName, 1000, 500);
        }
        function grade(recTypeName,recTypeId,recFlow){
            jboxOpen("<s:url value='/res/rec/grade'/>?roleFlag=${roleFlag}&processFlow=${process.processFlow}&resultFlow=${process.schResultFlow}&schDeptFlow=${process.schDeptFlow}&rotationFlow=${result.rotationFlow}&recTypeId="+recTypeId+"&recFlow="+recFlow,
                    recTypeName,1000,500);
        }
    </script>

</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/res/fengxianPj/khpj360Audit/${roleFlag}'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <input type="hidden" name="recTypeId" value="${recTypeId}"/>
            <div class="queryDiv">
                <div class="inputDiv">
                    <label class="qlable">年&#12288;&#12288;级：</label>
                    <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                           readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
                </div>
                <div class="inputDiv">
                    <label class="qlable">姓&#12288;&#12288;名：</label>
                    <input type="text" name="doctorName" value="${param.doctorName}" class="qtext">
                </div>
                <c:if test="${roleFlag ne GlobalConstant.RES_ROLE_SCOPE_MANAGER}">
                    <div class="doctorTypeDiv">
                        <div class="doctorTypeLabel">学员类型：</div>
                        <div class="doctorTypeContent">
                            <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
                                <c:set var="docType" value="${type.dictId},"/>
                                <label><input type="checkbox" name="datas" value="${type.dictId}" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""}>${type.dictName}</label>
                            </c:forEach>
                        </div>
                    </div>
                    <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN}">
                        <div class="inputDiv">
                            <label class="qlable">培训专业：</label>
                            <select name="trainingSpeId" class="qselect">
                                <option value="">全部</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="training">
                                    <option value="${training.dictId}" <c:if test="${param.trainingSpeId eq training.dictId}">selected</c:if>>${training.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <div class="qcheckboxDiv" style="padding-left:30px;">
                        <label class="qlable">
                            <input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""} />
                            轮转中学员
                        </label>
                    </div>
                    <div class="lastDiv" style="margin-left:-30px;">
                        <input type="button" class="searchInput" value="查&#12288;询" onclick="toPage('1')">
                    </div>
                </c:if>

            </div>
        </form>

        <div class="resultDiv">
            <table class="xllist" >
                <tr>
                    <th style="min-width: 100px;">姓名</th>
                    <th style="min-width: 50px;">年级</th>
                    <th style="min-width: 100px;">电话</th>
                    <th style="min-width: 100px;">培训专业</th>
                    <th style="min-width: 100px;">轮转科室</th>
                    <th style="min-width: 100px;">轮转开始日期</th>
                    <th style="min-width: 100px;">轮转结束日期</th>
                    <th style="min-width: 100px;">状态</th>
                    <th style="min-width:180px;max-width: 180px;width: 180px">考核评价</th>
                </tr>
                <c:forEach items="${processList}" var="process" varStatus="s">
                    <c:set var="user" value="${userMap[process.userFlow]}"/>
                    <c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
                    <c:set var="result" value="${resultMap[process.processFlow]}"></c:set>
                    <tr>
                        <td title="<img src='${sysCfgMap['upload_base_url']}/${user.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>">
                                ${user.userName}
                        </td>
                        <td>${doctor.sessionNumber}</td>
                        <td>${user.userPhone}</td>
                        <td>${doctor.trainingSpeName}</td>
                        <td>${process.schDeptName }</td>
                        <td>${process.schStartDate }</td>
                        <td>${process.schEndDate}</td>
                        <td>
                            <c:set var="aftersKey" value="${doctor.doctorFlow}${afterRecTypeEnumAfterSummary.id}"/>
                            <c:set var="aftereKey" value="${doctor.doctorFlow}${afterRecTypeEnumAfterEvaluation.id}"/>
                            <c:if test="${process.schFlag eq GlobalConstant.FLAG_Y}">
                                已出科
                            </c:if>
                            <c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y)}">
                                <c:if test="${process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
                                    <c:if test="${!empty recMap[aftersKey] || !empty recMap[aftersKey]}">
                                        待出科
                                    </c:if>
                                    <c:if test="${!(!empty recMap[aftersKey] || !empty recMap[aftersKey])}">
                                        轮转中
                                    </c:if>
                                </c:if>
                                <c:if test="${process.isCurrentFlag ne GlobalConstant.FLAG_Y}">
                                    待入科
                                </c:if>
                            </c:if>
                        </td>
                        <td>
                            <c:forEach items="${rec360TypeEnumList}" var="after">
                                <c:set var="key" value="${process.processFlow}${after.id}"/>
                                <c:set var="isShow" value="false"/>
                                <%--带教--%>
                                <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq roleFlag}">
                                    <%--<c:if test="${afterRecTypeEnumTeacher_360.id eq after.id }">--%>
                                    <c:if test="${recTypeId eq after.id }">
                                        <c:set var="isShow" value="true"/>
                                    </c:if>
                                </c:if>
                                <%--非带教--%>
                                <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER ne roleFlag}">
                                    <%--<c:if test="${(afterRecTypeEnumTeacher_360.id eq after.id || afterRecTypeEnumParamedic_360.id eq after.id || afterRecTypeEnumPatient_360.id eq after.id)}">--%>
                                    <c:if test="${recTypeId eq after.id }">
                                        <c:set var="isShow" value="true"/>
                                    </c:if>
                                </c:if>
                                <c:if test="${isShow}">
                                    <c:set var="color" value="blue"/>
                                    <c:if test="${rec360TypeEnumTeacherGrade.id eq after.id || rec360TypeEnumDeptGrade.id eq after.id}">
                                        <a style="color: ${color};cursor:pointer;" onclick="grade('${after.name}','${after.id}','${recMap[key].recFlow}');">${after.name}</a>
                                    </c:if>
                                    <c:if test="${!(rec360TypeEnumTeacherGrade.id eq after.id || rec360TypeEnumDeptGrade.id eq after.id)}">
                                        <a id="${after.id}${s.index}" style="color: ${color};cursor:pointer;" auditStatusId="${auditStatusId}" headAuditStatusId="${headAuditStatusId}"
                                           onclick="opRec('${recMap[key].recFlow}','${process.schDeptFlow}','${doctor.rotationFlow}','${process.userFlow}','${process.schResultFlow}','${after.id}','${after.name}','${process.processFlow}','${process.schDeptName}');">${after.name}
                                        </a>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty processList}">
                    <tr>
                        <td colspan="10">无记录</td>
                    </tr>
                </c:if>
            </table>
        </div>

        <div>
            <c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
