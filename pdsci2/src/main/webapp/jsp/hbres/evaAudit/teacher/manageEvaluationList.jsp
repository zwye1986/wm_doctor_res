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
            <c:forEach items="${resDocTypeEnumList}" var="type">
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
            var url = "<s:url value='/res/teacherEvaDoctor/manageEvaluation' />"
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
                '评估学员', 1200, 800);
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
            <input type="hidden" name="roleFlag" value="${roleFlag}"/>
            <input type="hidden" name="trainingTypeId" value="${trainingTypeId}"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">
                        姓&#12288;&#12288;名：
                    </td>
                    <td>
                        <input type="text" name="userName" value="${param.userName}" class="input" style="width: 122px;"/>
                    </td>
                    <td class="td_left">
                        年&#12288;&#12288;级：
                    </td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}" readonly="readonly" class="input" />
                    </td>
                    <td class="td_left">
                        科&#12288;&#12288;室：
                    </td>
                    <td>
                        <select name="deptFlow" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${deptList}" var="d">
                                <option value="${d.deptFlow}" <c:if test="${d.deptFlow eq param.deptFlow}">selected="selected"</c:if> >${d.deptName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">
                        状&#12288;&#12288;态：
                    </td>
                    <td>
                        <select name="assessStatusId" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${aidAssessStatusEnumList}" var="status">
                                <option value="${status.id}" <c:if test="${status.id eq param.assessStatusId}">selected="selected"</c:if> >${status.name }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">
                        人员类型：
                    </td>
                    <td colspan="3">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" style="margin-left: 36px;" class="btn_green" value="查&#12288;询" id="searchDoctor" onclick="search()">
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
                <c:forEach items="${list}" var="process" varStatus="s">
                    <c:set var="mapKey" value="${process.processFlow}${resRecTypeEnumManageDoctorAssess360.id}"/>
                    <c:set var="rec" value="${requestScope[mapKey]}"/>
                    <c:set var="dataMap" value="${requestScope[mapKey]}"/>
                    <c:set var="result" value="${resultMap[process.processFlow]}"></c:set>

                    <tr>
                        <td>${s.index+1}</td>
                        <td>
                                ${process.userName}
                        </td>
                        <td>${process.sessionNumber}</td>
                        <td>${process.trainingSpeName}</td>
                        <td>${process.doctorTypeName}</td>
                        <td>${process.schStartDate }~${process.schEndDate}</td>
                        <td>
                            <c:if test="${empty rec}">
                                待评价
                            </c:if>
                            <c:if test="${not empty rec}">
                                已评价
                            </c:if></td>
                        <td>
                            <c:if test="${empty rec }">
                                <a href="javascript:grade('${resRecTypeEnumManageDoctorAssess360.name}','${'ManageDoctorAssess360'}','','${process.processFlow}',
                                '${process.schResultFlow}','${process.schDeptFlow}','${result.rotationFlow}','${process.userFlow}')">评价</a>
                            </c:if>
                            <c:if test="${not empty rec}">
                                <a href="javascript:grade('${resRecTypeEnumManageDoctorAssess360.name}','${'ManageDoctorAssess360'}','${rec.recFlow}','${process.processFlow}',
                                '${process.schResultFlow}','${process.schDeptFlow}','${result.rotationFlow}','${process.userFlow}')">
                                        ${(rec.allScore+0)}
                                </a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty list}">
                    <tr>
                        <td colspan="10">无记录</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
</body>
</html>
