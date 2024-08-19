<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style type="text/css">
    .bg {
        width: 60px;
        height: 16px;
        background: url(<s:url value='/jsp/jsres/activity/img/star_gray.png'/>);
        margin-left: 40px;
    }

    .img {
        width: 20px;
        height: 20px;
        margin-left: 5px;
    }

    .over {
        height: 16px;
        background: url(<s:url value='/jsp/jsres/activity/img/star_org.png'/>) no-repeat;
    }
</style>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
    $(document).ready(function () {
        $('#lectureTrainStartDate').datepicker();
        $('#lectureTrainEndDate').datepicker();
        <c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
        var score = "${evaMap[lecture.lectureFlow]}";
        $("." + "${lecture.lectureFlow}").find("li:lt(" + score + ")").css("color", "#ffb60b");
        </c:forEach>
    });

    function search() {
        var startDate = $("input[name='lectureTrainStartDate']").val();
        var endDate = $("input[name='lectureTrainEndDate']").val();

        if (startDate != '' && endDate == '' || startDate == '' && endDate != '') {
            jboxTip("请正确选择开始日期结束日期！");
            return;
        }
        if (endDate < startDate) {
            jboxTip("结束日期不能早于开始日期！");
            return;
        }
        lectures($("#searchForm").serialize());
    }

    function detail(lectureFlow) {
        jboxOpen("<s:url value='/jsres/lecture/lectureDetail'/>?lectureFlow=" + lectureFlow, '讲座信息', 1000, 460);
    }

    function signUrl(lectureFlow) {
        jboxOpen("<s:url value='/jsres/lecture/signUrl'/>?lectureFlow=" + lectureFlow, '二维码', 450, 500);
    }

    function del(lectureFlow) {
        jboxConfirm("确认删除?", function () {
            jboxPost("<s:url value='/jsres/lecture/delLecture'/>?lectureFlow=" + lectureFlow, null, function (resp) {
                jboxEndLoading();
                if (resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                    jboxTip("删除成功！");
                    search();
                }
            }, null, false);
        });
    }

    function getEva(lectureFlow, flag) {
        jboxOpen("<s:url value='/jsres/lecture/getEva'/>?lectureFlow=" + lectureFlow + "&flag=" + flag, '评价视图', 1000, 500);
    }

    function toPage(page) {
        if (page) {
            $("#currentPage").val(page);
        }
        search();
    }

    function openRandomSign(lectureFlow) {
        jboxOpen("<s:url value='/jsres/lecture/randomSignIn'/>?lectureFlow=" + lectureFlow, '随机签到', 800, 600);
    }

    // 导出当前查询的讲座信息
    function exportExcel() {
        if (${empty lectureInfos}) {
            jboxTip("未查到数据，无法导出！");
            return;
        }
        var url = "<s:url value='/jsres/lecture/exportLectureList'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
    }
</script>
<div class="main_hd">
    <h2 class="underline">讲座信息管理</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>


            <div class="form_search">
                <div class="form_item">
                    <div class="form_label">讲座日期：</div>
                    <div class="form_content">
                        <input style="width: 80px;" type="text" name="lectureTrainStartDate"
                               value="${param.lectureTrainStartDate}"
                               id="lectureTrainStartDate" class="input"
                               readonly="readonly">~<input type="text" name="lectureTrainEndDate"
                                                           value="${param.lectureTrainEndDate}"
                                                           style="width: 80px;" id="lectureTrainEndDate" class="input"
                                                           readonly="readonly">
                    </div>
                </div>

                <div class="form_item">
                    <div class="form_label">讲座类型：</div>
                    <div class="form_content">
                        <select name="lectureTypeId" class="select" onchange="search()">
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumLectureTypeList}" var="dict">
                            <option value="${dict.dictId}"
                                    <c:if test="${dict.dictId eq param.lectureTypeId}">selected</c:if>> ${dict.dictName}</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>

                <div class="form_item">
                    <div class="form_label">讲座标题：</div>
                    <div class="form_content">
                        <input type="text" name="lectureContent" class="input"
                                                              value="${param.lectureContent}"
                                                              onchange="search();"/>
                    </div>
                </div>

                <div class="form_item">
                    <div class="form_label">主讲人：</div>
                    <div class="form_content">
                        <input type="text" name="lectureTeacherName" class="input"
                                                            value="${param.lectureTeacherName}"
                                                            onchange="search();"/>
                    </div>
                </div>

            </div>


<%--            <div style="display: flex;justify-content: flex-start; column-gap: 52px;margin-top: 15px">--%>
<%--                <div>--%>
<%--                    <label class="form_label">讲座日期：<input style="width: 100px;" type="text"--%>
<%--                                                              name="lectureTrainStartDate"--%>
<%--                                                              value="${param.lectureTrainStartDate}"--%>
<%--                                                              id="lectureTrainStartDate" class="input"--%>
<%--                                                              readonly="readonly">至<input type="text"--%>
<%--                                                                                           name="lectureTrainEndDate"--%>
<%--                                                                                           value="${param.lectureTrainEndDate}"--%>
<%--                                                                                           style="width: 100px;"--%>
<%--                                                                                           id="lectureTrainEndDate"--%>
<%--                                                                                           class="input"--%>
<%--                                                                                           readonly="readonly"></label>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label class="form_label">讲座类型：<select name="lectureTypeId" class="select" onchange="search()">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <c:forEach items="${dictTypeEnumLectureTypeList}" var="dict">--%>
<%--                            <option value="${dict.dictId}"--%>
<%--                                    <c:if test="${dict.dictId eq param.lectureTypeId}">selected</c:if>> ${dict.dictName}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select></label>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label class="form_label">讲座标题：<input type="text" name="lectureContent" class="input"--%>
<%--                                                              value="${param.lectureContent}"--%>
<%--                                                              onchange="search();"/></label>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label class="form_label">主讲人：<input type="text" name="lectureTeacherName" class="input"--%>
<%--                                                            value="${param.lectureTeacherName}"--%>
<%--                                                            onchange="search();"/></label>--%>
<%--                </div>--%>
<%--            </div>--%>

            <%--            讲座日期：<input type="text" name="lectureTrainStartDate" value="${param.lectureTrainStartDate}"--%>
            <%--                        style="width: 100px;" id="lectureTrainStartDate" class="input"--%>
            <%--                         readonly="readonly">至<input type="text" name="lectureTrainEndDate" value="${param.lectureTrainEndDate}"--%>
            <%--                                                     style="width: 100px;" id="lectureTrainEndDate" class="input"--%>
            <%--                                                     readonly="readonly">--%>
            <%--            讲座类型：<select name="lectureTypeId" class="select"onchange="search()" style="width: 100px">--%>
            <%--            <option value="">全部</option>--%>
            <%--            <c:forEach items="${dictTypeEnumLectureTypeList}" var="dict">--%>
            <%--                <option value="${dict.dictId}"--%>
            <%--                        <c:if test="${dict.dictId eq param.lectureTypeId}">selected</c:if>> ${dict.dictName}</option>--%>
            <%--            </c:forEach>--%>
            <%--        </select>--%>
            <%--            &nbsp;讲座标题：<input type="text" name="lectureContent"class="input" value="${param.lectureContent}" onchange="search();"--%>
            <%--                        style="width: 100px;">--%>
            <%--            主讲人：<input type="text" name="lectureTeacherName"class="input" value="${param.lectureTeacherName}" onchange="search();"--%>
            <%--                       style="width: 100px;">--%>
            <%--            &nbsp;--%>

            <div style="margin-top: 15px;margin-bottom: 15px">
                <input type="button" class="btn_green" value="查&#12288;询" onclick="search()">
                <input type="button" class="btn_green" value="新&#12288;增" onclick="detail('')">
                <c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL}">
                    <input type="button" class="btn_green" value="导&#12288;出" onclick="exportExcel()">
                </c:if>
            </div>
        </form>


    </div>
    <div class="search_table">
        <table class="grid">
            <tr>
                <th style="width: 10%;">讲座日期</th>
                <th style="width: 10%;">开始时间</th>
                <th style="width: 10%;">结束时间</th>
                <th style="width: 20%;">讲座标题</th>
                <th style="width: 10%;">讲座地点</th>
                <th style="width: 10%;">主讲人</th>
                <th style="width: 13%;">评价视图</th>
                <th style="width: 12%;">操作</th>
            </tr>
            <c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
                <tr>
                    <td>${lecture.lectureTrainDate}</td>
                    <td>${lecture.lectureStartTime}</td>
                    <td>${lecture.lectureEndTime}</td>
                    <td>${lecture.lectureContent}(${lecture.lectureTypeName})</td>
                    <td>${lecture.lectureTrainPlace}</td>
                    <td>${lecture.lectureTeacherName}</td>
                    <td onclick="getEva('${lecture.lectureFlow}','Y')" style="cursor: pointer">
                        <div class="bg">
                            <div class="over" style="width:${12*evaMap[lecture.lectureFlow]}px"></div>
                        </div>
                    </td>
                    <td>
                        <a onclick="detail('${lecture.lectureFlow}','')" style="cursor: pointer">[编辑]</a>
                        <a onclick="del('${lecture.lectureFlow}')" style="cursor: pointer">[删除]</a>
                        <a onclick="signUrl('${lecture.lectureFlow}')" style="cursor: pointer">[二维码查看]</a>
                        <c:if test="${lecture.randomSignIn eq 'Y'}">
                            <a onclick="openRandomSign('${lecture.lectureFlow}')" style="cursor: pointer">[随机签到]</a>
                        </c:if>
                    </td>
                </tr>

            </c:forEach>
            <c:if test="${empty lectureInfos}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(lectureInfos)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

