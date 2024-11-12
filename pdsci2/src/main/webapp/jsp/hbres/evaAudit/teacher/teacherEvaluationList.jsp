<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
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
<html>
<head>
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
        $(function () {
            $('#sessionNumber').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
            <c:forEach items="${jsResDocTypeEnumList}" var="type">
            <c:forEach items="${datas}" var="data">
            if("${data}"=="${type.id}"){
                $("#"+"${data}").attr("checked","checked");
            }
            </c:forEach>
            <c:if test="${empty datas}">
            $("#"+"${type.id}").attr("checked","checked");
            </c:if>
            </c:forEach>
        });
        function search() {
            var url = "<s:url value='/res/teacherEvaDoctor/teacherEvaluation' />"
            jboxPostLoad("content", url, $("#searchForm").serialize(), false);
        }

        function toPage(page) {
            if (page) {
                $("#currentPage").val(page);
            }
            search();
        }

        function defaultImg(img) {
            img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
        }

        function grade(recTypeName, recTypeId, recFlow, processFlow, schResultFlow, schDeptFlow, rotationFlow, doctorFlow) {

            jboxOpen("<s:url value='/res/teacherEvaDoctor/grade'/>?roleFlag=${roleFlag}&processFlow=" + processFlow + "&resultFlow=" + schResultFlow + "&schDeptFlow=" + schDeptFlow + "&rotationFlow=" +
                rotationFlow + "&recTypeId=" + recTypeId + "&recFlow=" + recFlow+ "&doctorFlow=" + doctorFlow,
                '带教评估学员', 1200, 800);
        }

        function selGrade(recTypeName, recTypeId, recFlow, processFlow, schResultFlow, schDeptFlow, rotationFlow, doctorFlow) {
            var url = "<s:url value='/res/teacherEvaDoctor/selGrade'/>?processFlow=" + processFlow + "&resultFlow=" + schResultFlow + "&schDeptFlow=" + schDeptFlow + "&rotationFlow=" +
                rotationFlow + "&recTypeId=" + recTypeId + "&recFlow=" + recFlow+ "&doctorFlow=" + doctorFlow;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,'选择评估表单',500,200);
            <%--jboxOpen("<s:url value='/res/teacherEvaDoctor/selGrade'/>?recFlow=" + recFlow+ "&doctorFlow=" + doctorFlow,'选择评估表单', 500, 200);--%>
        }
    </script>

</head>
<body>
<div class="main_hd">
    <h2 class="underline">评价学员</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm" >
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <input type="hidden" name="recTypeId" value="${recTypeId}"/>
            <table>
                <tr>
                    <td>
                        姓&#12288;&#12288;名：
                        <input type="text" name="doctorName" value="${param.doctorName}" class="input"
                               style="width: 100px;"/>
                        培训专业：
                        <select name="trainingSpeId" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="training">
                                <option value="${training.dictId}"
                                        <c:if test="${param.trainingSpeId eq training.dictId}">selected</c:if>>${training.dictName}</option>
                            </c:forEach>
                        </select>
                        年&#12288;&#12288;级：
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                               readonly="readonly" class="input" />
                        状&#12288;&#12288;态：
                        <select name="assessStatusId" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${aidAssessStatusEnumList}" var="status">
                                <option value="${status.id}" <c:if test="${status.id eq param.assessStatusId}">selected="selected"</c:if> >${status.name }</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        人员类型：
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td>
                        <input type="button" class="btn_green" value="查&#12288;询" id="searchDoctor" onclick="search()">
                    </td>
                </tr>
            </table>
        </form>

        <div>
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th style="min-width: 50px;">序号</th>
                    <th style="min-width: 100px;">姓名</th>
                    <th style="min-width: 50px;">年级</th>
                    <th style="min-width: 100px;">培训专业</th>
                    <th style="min-width: 100px;">人员类型</th>
                    <th style="min-width: 200px;">轮转时间</th>
                    <th style="min-width: 100px;">状态</th>
                    <th style="min-width:80px">分数</th>
                </tr>
                <c:forEach items="${processList}" var="process" varStatus="s">
                    <c:set var="user" value="${userMap[process.userFlow]}"/>
                    <c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
                    <c:set var="result" value="${resultMap[process.processFlow]}"></c:set>
                    <c:set var="mapKey1" value="${process.processFlow}${resRecTypeEnumTeacherDoctorGrade.id}"/>
                    <c:set var="mapKey2" value="${process.processFlow}${resRecTypeEnumTeacherDoctorGradeTwo.id}"/>
                    <c:set var="rec" value="${requestScope[mapKey1]}"/>
                    <c:set var="rec2" value="${requestScope[mapKey2]}"/>
                    <c:set var="mapKey" value="${resRecTypeEnumTeacherDoctorGrade.id}${process.processFlow}"/>
                    <c:set var="mapKey2" value="${resRecTypeEnumTeacherDoctorGradeTwo.id}${process.processFlow}"/>
                    <c:set var="dataMap" value="${requestScope[mapKey]}"/>
                    <c:set var="dataMap2" value="${requestScope[mapKey2]}"/>
                    <tr>
                        <td>${s.index+1}</td>
                        <td>
                                ${user.userName}
                        </td>
                        <td>${doctor.sessionNumber}</td>
                        <td>${doctor.trainingSpeName}</td>
                        <td>${doctor.doctorTypeName}</td>
                        <td>${process.schStartDate }~${process.schEndDate}</td>
                        <td>
                            <c:if test="${empty rec and empty rec2}">
                                待评价
                            </c:if>
                            <c:if test="${not empty rec or not empty rec2}">
                                已评价
                            </c:if></td>
                        <td>
                            <c:if test="${empty rec and empty rec2}">
                                <a href="javascript:selGrade('','${rec.recTypeId}',
                            '${rec.recFlow}','${process.processFlow}','${process.schResultFlow}','${process.schDeptFlow}','${result.rotationFlow}','${doctor.doctorFlow}')">评价</a>
                            </c:if>
                            <c:if test="${not empty rec and empty rec2}">
                                <a href="javascript:grade('','${rec.recTypeId}',
                            '${rec.recFlow}','${process.processFlow}','${process.schResultFlow}','${process.schDeptFlow}','${result.rotationFlow}','${doctor.doctorFlow}')">
                                        ${(dataMap.totalScore+0)}
                                </a>
                            </c:if>
                            <c:if test="${not empty rec2 and empty rec}">
                                <a href="javascript:grade('','${rec2.recTypeId}',
                            '${rec2.recFlow}','${process.processFlow}','${process.schResultFlow}','${process.schDeptFlow}','${result.rotationFlow}','${doctor.doctorFlow}')">
                                        ${(dataMap2.totalScore+0)}
                                </a>
                            </c:if>
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
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
</body>
</html>
