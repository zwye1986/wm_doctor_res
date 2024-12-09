<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#graduationTime').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#startDate').datepicker();
        $('#endDate').datepicker();
        <c:forEach items="${resDocTypeEnumList}" var="type">
        <c:if test="${not empty docTypeList}">
        <c:forEach items="${docTypeList}" var="docType">
        if ("${docType}" == "${type.id}") {
            $("#" + "${docType}").attr("checked", "checked");
        } else if ("${docType}" == "") {
            $("#" + "${type.id}").attr("checked", "checked");
        }
        </c:forEach>
        </c:if>
        <c:if test="${empty docTypeList}">
        $("#" + "${type.id}").attr("checked", "checked");
        </c:if>
        </c:forEach>
    });


    function search(page) {
        if (!page) {
            page = 1;
        }
        $("#currentPage").val(page);
        var data = $("#searchForm").serialize();
        var url = "<s:url value='/res/responsibleTeacher/cycle'/>?baseFlag=${param.baseFlag}";
        jboxStartLoading();
        jboxPost(url, data, function (resp) {
            $("#content").html(resp);
            jboxEndLoading();
        }, null, false);
    }

    function toPage(page) {
        search(page);
    }

    function showDetails(resultFlow, schStartDate, schEndDate, schDeptFlow) {
        jboxStartLoading();
        var url = "<s:url value='/jsres/doctorRecruit/cycleDeptDetails'/>?resultFlow=" + resultFlow + "&schStartDate=" + schStartDate + "&schEndDate=" + schEndDate + "&schDeptFlow=" + schDeptFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, '详细信息', 700, 250);
        jboxEndLoading();
    }

</script>

<div class="main_hd">
    <h2 class="underline">学员轮转查询</h2>
</div>

<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
            <table>
                <tr>
                    <td>培训专业：&nbsp;
                        <select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 107px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
                                <option value="${spe.dictId}"
                                        <c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if>
                                >${spe.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>&#12288;年&#12288;&#12288;级：
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                               readonly="readonly" class="input" style="width: 100px;"/>
                    </td>
                    <td>&#12288;培训年限：
                        <select name="trainingYears" class="select" style="width:100px;">
                            <option value="">全部</option>
                            <option value="OneYear"
                                    <c:if test="${param.trainingYears eq 'OneYear'}">selected</c:if>>一年
                            </option>
                            <option value="TwoYear"
                                    <c:if test="${param.trainingYears eq 'TwoYear'}">selected</c:if>>两年
                            </option>
                            <option value="ThreeYear"
                                    <c:if test="${param.trainingYears eq 'ThreeYear'}">selected</c:if>>三年
                            </option>
                        </select>
                    </td>
                    <td>&#12288;结业考核年份：
                        <input type="text" id="graduationTime" name="graduationTime" value="${param.graduationTime}"
                               readonly="readonly" class="input" style="width: 100px;margin-left: 0px"/>
                    </td>
                </tr>
                <tr>
                    <td>姓&#12288;&#12288;名：
                        <input type="text" name="userName" class="input" value="${param.userName}"
                               style="width: 100px;"/>
                    </td>
                    <td>&#12288;证件号码：
                        <input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>
                    </td>
                    <td>
                        &#12288;开始日期：
                        <input style="margin-left:0px;width:100px" name="startDate" id="startDate" type="text"
                               readonly="readonly"
                               value="${empty param.startDate?startDate:param.startDate}" class="input"/>
                    </td>
                    <td>&#12288;&#12288;&#12288;结束日期：
                        <input style="margin-left:0px;width:100px" name="endDate" id="endDate" type="text"
                               readonly="readonly"
                               value="${empty param.endDate?endDate:param.endDate}" class="input"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">人员类型：
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" name="docTypes" id="${type.id}" value="${type.id}"
                                          class="docType"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td>&#12288;
                        <input type="checkbox" name="onlyProblem" value="${GlobalConstant.FLAG_Y}" <c:if
                                test="${GlobalConstant.FLAG_Y eq param.onlyProblem}"> checked="checked"</c:if>/>
                        &nbsp;仅问题数据
                        &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="search(1);"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>


    <div class="basic" style="margin: 0 30px;width: 980px;overflow: auto;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width:100px;min-width: 100px;">姓名</th>
                <th style="width:100px;min-width: 100px;">专业</th>
                <th style="width:100px;min-width: 100px;">年级</th>
                <th style="width:100px;min-width: 100px;">培训年限</th>
                <c:forEach items="${titleDate}" var="title">
                    <th style="width:100px;min-width: 100px;">${title}</th>
                </c:forEach>
            </tr>
            <c:forEach items="${docCycleList}" var="docCycle">
                <tr>
                    <td>${docCycle.userName}</td>
                    <td>${docCycle.trainingSpeName}</td>
                    <td>${docCycle.sessionNumber}</td>
                    <td>
                        <c:if test="${'OneYear' eq docCycle.trainingYears}">一年</c:if>
                        <c:if test="${'TwoYear' eq docCycle.trainingYears}">两年</c:if>
                        <c:if test="${'ThreeYear' eq docCycle.trainingYears}">三年</c:if>
                    </td>
                    <c:forEach items="${titleDate}" var="title">
                        <c:set var="key" value="${docCycle.doctorFlow}${title}"/>
                        <c:set var="currDate" value="${pdfn:getCurrDate()}"/>
                        <td>
                            <c:if test="${!empty resultListMap[key]}">
                                <c:forEach items="${resultListMap[key]}" var="result">
                                    <div>
                                        [<a onclick="showDetails('${result.resultFlow}','${result.schStartDate}','${result.schEndDate}','${result.schDeptFlow}');"
                                            <c:if test="${result.schStartDate<=currDate && currDate<=result.schEndDate}">
                                                style="color: #459ae9"</c:if>
                                            <c:if test="${result.haveAfterPic eq 'Y' &&currDate>=result.schEndDate}">
                                                style="color: #00B83F"</c:if>
                                            <c:if test="${result.haveAfterPic eq 'N' &&currDate>=result.schEndDate}">
                                                style="color: #ee0101"</c:if>
                                    >${result.schDeptName}</a>]
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty resultListMap[key]}">-</c:if>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <c:if test="${empty docCycleList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div style="padding-left: 40px;width:50%">
        <span style="background-color: #00B83F">&#12288;&nbsp;</span><span>表示:按计划应出科且已上传出科考核表</span><br/>
        <span style="background-color: #459ae9">&#12288;&nbsp;</span><span>表示:按计划正在轮转,不判断是否上传出科考核表</span><br/>
        <span style="background-color: #ee0101">&#12288;&nbsp;</span><span>表示:按计划应出科但未上传出科考核表</span><br/>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(docCycleList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>


