<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
        // 初始化日期
        $("#lectureTrainDate").datepicker();

        <c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
        var score = "${evaMap[lecture.lectureFlow]}";
        $("." + "${lecture.lectureFlow}").find("li:lt(" + score + ")").css("color", "#ffb60b");
        </c:forEach>
    });

    function search(currentPage) {
        if(!currentPage){
            currentPage = 1;
        }
        jboxPostLoad("content", "<s:url value='/res/manager/lectureView'/>?roleId=${roleId}&currentPage=" + currentPage, $("#searchForm").serialize(), true);
    }

    function detail(lectureFlow) {
        jboxOpen("<s:url value='/res/manager/lectureDetail'/>?lectureFlow=" + lectureFlow, '讲座信息', 1000, 460);
    }

    function del(lectureFlow) {
        jboxPost("<s:url value='/res/manager/delLectureFlag'/>?lectureFlow=" + lectureFlow, null, function (resp) {
            jboxEndLoading();
            if (resp == 'Y') {
                jboxConfirm("确认删除?", function () {
                    jboxPost("<s:url value='/res/manager/delLecture'/>?lectureFlow=" + lectureFlow, null, function (resp) {
                        jboxEndLoading();
                        if (resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                            search(1);
                        } else {
                            jboxTip("该讲座信息已有人员签到或评价，不能删除！");
                            return;
                        }
                    }, null, false);
                });
            } else {
                jboxTip("该讲座信息已有人员签到或评价，不能删除！");
                return;
            }
        }, null, false);
    }

    function getEva(lectureFlow, flag) {
        jboxOpen("<s:url value='/res/manager/getEva'/>?lectureFlow=" + lectureFlow + "&flag=" + flag, '评价视图查看', 860, 600);
    }

    function signUrl(lectureFlow) {
        jboxOpen("<s:url value='/res/manager/signUrl'/>?lectureFlow=" + lectureFlow, '二维码', 450, 550);
    }

    function toPage(currentPage) {
        if(!currentPage){
            currentPage = 1;
        }
        search(currentPage);
    }

    function openRandomSign(lectureFlow) {
        jboxOpen("<s:url value='/res/manager/randomSignIn'/>?lectureFlow=" + lectureFlow, '随机签到', 860, 550);
    }

    // 导出当前查询的讲座信息
    function exportExcel() {
        if (${empty lectureInfos}) {
            jboxTip("未查到数据，无法导出！");
            return;
        }
        var url = "<s:url value='/res/manager/exportLectureList'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
    }
</script>

<style type="text/css">
    .bg{
        width: 60px;
        height: 16px;
        background: url(<s:url value='/jsp/res/activity/img/star_gray.png'/>);
        margin-left: 15px;
    }
    .over{
        height:16px;
        background:url(<s:url value='/jsp/res/activity/img/star_org.png'/>) no-repeat;
    }
</style>

<div class="main_hd">
    <c:choose>
        <c:when test="${param.title eq 'view'}">
            <h2 class="underline">讲座活动查询</h2>
        </c:when>
        <c:otherwise>
            <h2 class="underline">讲座活动维护</h2>
        </c:otherwise>
    </c:choose>
</div>

<div class="main_bd">
    <div style="padding:10px 25px 0;">
        <form id="searchForm" method="post">
            <table class="searchTable" style="border-collapse: separate;border-spacing: 0px 10px;line-height: normal;">
                <tr>
                    <td class="td_right">讲座日期：</td>
                    <td class="td_left">
                        <input class="input" type="text" name="lectureTrainDate" id="lectureTrainDate"
                               value="${param.lectureTrainDate}" readonly="readonly"/>
                    </td>
                    <td class="td_right">&#12288;讲座类型：</td>
                    <td class="td_left">
                        <select name="lectureTypeId" class="select" style="width:164px;margin-left: 4px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumLectureTypeList}" var="dict">
                                <option value="${dict.dictId}" ${param.lectureTypeId eq dict.dictId ? 'selected' : ''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_right">&#12288;主讲人：</td>
                    <td class="td_left">
                        <input class="input" type="text" name="lectureTeacherName" value="${param.lectureTeacherName}"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_right">讲座标题：</td>
                    <td class="td_left">
                        <input class="input" type="text" name="lectureContent" value="${param.lectureContent}"/>
                    </td>
                    <td class="td_right">&#12288;讲座地点：</td>
                    <td class="td_left">
                        <input class="input" type="text" name="lectureTrainPlace" value="${param.lectureTrainPlace}"/>
                    </td>
                    <td colspan="2">
                        &#12288;<input type="button" value="查&#12288;询" class="btn_green" onclick="search(1);"/>
                        <c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleId}">
                            &#12288;<input type="button" value="新&#12288;增" class="btn_green" onclick="detail('','');"/>
                        </c:if>
                        &#12288;<input type="button" class="btn_green" value="导&#12288;出" onclick="exportExcel()">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table" style="padding: 10px 20px 0px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="text-align: center;">讲座标题</th>
                <th style="text-align: center;">主讲人</th>
                <th style="text-align: center;">授课角色</th>
                <th style="text-align: center;">讲座日期</th>
                <th style="text-align: center;">开始时间</th>
                <th style="text-align: center;">结束时间</th>
                <th style="text-align: center;">讲座地点</th>
                <th style="text-align: center;">评价视图查看</th>
                <th style="text-align: center;">操作</th>
            </tr>
            <c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
                <tr>
                    <td>${lecture.lectureContent}(${lecture.lectureTypeName})</td>
                    <td>${lecture.lectureTeacherName}</td>
                    <td>${lecture.lectureSpeakerRoleName}</td>
                    <td>${lecture.lectureTrainDate}</td>
                    <td>${lecture.lectureStartTime}</td>
                    <td>${lecture.lectureEndTime}</td>
                    <td>${lecture.lectureTrainPlace}</td>
                    <td onclick="getEva('${lecture.lectureFlow}','Y')" style="cursor: pointer">
                        <div class="bg">
                            <div class="over" style="width:${12*evaMap[lecture.lectureFlow]}px"></div>
                        </div>
                    </td>
                    <%--<c:if test="${empty evaMap[lecture.lectureFlow]}">--%>
                        <%--<td onclick="getEva('${lecture.lectureFlow}','Y')" style="cursor: pointer;padding-left: 38px;">--%>
                            <%--<ul class="star ${lecture.lectureFlow}">--%>
                                <%--<li style="float: left;margin-right: 5px;">☆</li>--%>
                                <%--<li style="float: left;margin-right: 5px">☆</li>--%>
                                <%--<li style="float: left;margin-right: 5px">☆</li>--%>
                                <%--<li style="float: left;margin-right: 5px">☆</li>--%>
                                <%--<li style="float: left;margin-right: 5px">☆</li>--%>
                            <%--</ul>--%>
                        <%--</td>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${!empty evaMap[lecture.lectureFlow]}">--%>
                        <%--<td onclick="getEva('${lecture.lectureFlow}','N')" style="cursor: pointer;padding-left: 38px;">--%>
                            <%--<ul class="star ${lecture.lectureFlow}">--%>
                                <%--<li style="float: left;margin-right: 5px;">★</li>--%>
                                <%--<li style="float: left;margin-right: 5px">★</li>--%>
                                <%--<li style="float: left;margin-right: 5px">★</li>--%>
                                <%--<li style="float: left;margin-right: 5px">★</li>--%>
                                <%--<li style="float: left;margin-right: 5px">★</li>--%>
                            <%--</ul>--%>
                        <%--</td>--%>
                    <%--</c:if>--%>
                    <td>
                        <c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleId}">
                            <a onclick="detail('${lecture.lectureFlow}','')" style="cursor: pointer">[编辑]</a>
                            <a onclick="del('${lecture.lectureFlow}')" style="cursor: pointer">[删除]</a>
                            <br>
                            <c:if test="${lecture.randomSignIn eq 'Y'}">
                                <a onclick="openRandomSign('${lecture.lectureFlow}')" style="cursor: pointer">[随机签到]</a>
                            </c:if>
                        </c:if>
                        <a onclick="signUrl('${lecture.lectureFlow}')" style="cursor: pointer">[二维码查看]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty lectureInfos}">
                <tr>
                    <td colspan="9" style="text-align: center">暂无信息</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <input id="currentPage" type="hidden" name="currentPage" value=""/>
        <c:set var="pageView" value="${pdfn:getPageView(lectureInfos)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>