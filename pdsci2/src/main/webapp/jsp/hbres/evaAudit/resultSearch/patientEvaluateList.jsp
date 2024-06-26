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

            var url = "<s:url value='/res/evaDoctorResult/patientEvaluate'/>?roleFlag=teacher";
            jboxPostLoad("content", url, $("#searchForm").serialize(), false);
        }

        function toPage(page) {
            if (page) {
                $("#currentPage").val(page);
            }
            search();
        }

        function grade(recFlow) {
            jboxOpen("<s:url value='/res/evaDoctorResult/patientMainInfo'/>?recFlow=" + recFlow,
                '病人评价详情', 1200, 660);
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
            <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
                <tr>
                    <td class="td_left">
                        <nobr>姓&#12288;&#12288;名：</nobr>
                    </td>
                    <td>
                        <input type="text" name="doctorName" value="${param.doctorName}" class="input"
                               style="width: 100px;"/>
                    </td>
                    <td class="td_left">
                        <nobr>培训专业：</nobr>
                    </td>
                    <td>
                        <select name="trainingSpeId" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="training">
                                <option value="${training.dictId}"
                                        <c:if test="${param.trainingSpeId eq training.dictId}">selected</c:if>>${training.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <td class="td_left">
                        <nobr>培训科室：</nobr>
                    </td>
                    <td>
                        <select name="deptFlow" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.deptFlow}"
                                        <c:if test="${deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">
                        <nobr>评价人名称：</nobr>
                    </td>
                    <td>
                        <input type="text" name="patientName" value="${param.patientName}" class="input"
                               style="width: 100px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">
                        <nobr>年&#12288;&#12288;级：</nobr>
                    </td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"style="width: 100px;"
                               readonly="readonly" class="input" />
                    </td>
                    <td class="td_left">
                        <nobr>人员类型：</nobr>
                    </td>
                    <td colspan="3">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td colspan="2">
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
                    <th style="min-width: 100px;">轮转科室</th>
                    <th style="min-width: 100px;">轮转时间</th>
                    <th style="min-width:100px">评价人姓名</th>
                    <th style="min-width:80px">分数</th>
                </tr>
                <c:forEach items="${resultList}" var="r" varStatus="s">
                    <tr>
                        <td>${s.index + 1}</td>
                        <td>${r.doctorName}</td>
                        <td>${r.sessionNumber}</td>
                        <td>${r.trainingSpeName}</td>
                        <td>${r.doctorTypeName}</td>
                        <td>${pdfn:cutString(r.schDeptName,8,true,3)}</td>
                        <td>${r.schStartDate} <br> ${r.schEndDate}</td>
                        <td>${r.patientName}</td>
                        <td>
                            <a href="javascript:grade('${r.recFlow}');">
                                    ${r.allScore}
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty resultList}">
                    <tr>
                        <td colspan="10">无记录</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(resultList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
</body>
</html>
