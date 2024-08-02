<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">

    function toPage(currentPage) {
        if (!currentPage) {
            currentPage = 1;
        }
        jboxPostLoad("content", "<s:url value='/res/activityTarget/list'/>?roleFlag=${roleFlag}&currentPage=" + currentPage, $("#searchForm").serialize(), true);
    }

    function add(targetFlow) {

        var url = "<s:url value='/res/activityTarget/add'/>?targetType=${param.targetType}&targetFlow=" + targetFlow;
        if (targetFlow == "") {
            jboxOpen(url, "新增评价指标", 500, 200, true);
        } else {
            jboxOpen(url, "编辑评价指标", 500, 200, true);
        }
    }

    function delTarget(targetFlow) {
        var url = '<s:url value="/res/activityTarget/delTarget"/>?targetFlow=' + targetFlow;
        jboxConfirm("确认删除？", function () {
            jboxPost(url, null, function (resp) {
                toPage(1);
            }, null, false);
        });
    }

    // 全选
    function choose() {
        var length = $("input[name='choose']:checked").length;
        if (length > 0) {
            $("input[name='targetFlow']").attr("checked", true);
        } else {
            $("input[name='targetFlow']").attr("checked", false);
        }
    }

    // 导出
    function exportTarget() {
        var targetFlowArrs = new Array();
        var aa = $("input[name='targetFlow']:checked").each(function (i) {
            targetFlowArrs.push($(this).val());
        });
        if (targetFlowArrs.length == 0) {
            jboxTip("请选择导出的记录！");
            return;
        }
        var url = "<s:url value='/res/activityTarget/exportTarget'/>?targetFlowArrs=" + targetFlowArrs;
        $("input[name='targetFlow']:checked").attr('checked', false);
        $("input[name='choose']:checked").attr('checked', false);
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
    }

    // 导入
    function importTarget() {
        jboxOpen("<s:url value='/jsp/res/activity/target/importTarget.jsp'/>", "导入", 600, 200);
    }
</script>

<div class="main_hd">
    <c:choose>
        <c:when test="${param.targetType eq 'JX'}">
            <h2 class="underline">教学指标维护</h2>
        </c:when>
        <c:otherwise>
            <h2 class="underline">讲座指标维护</h2>
        </c:otherwise>
    </c:choose>
</div>
<div class="main_bd">
    <div style="padding:10px 25px 0;">
        <form id="searchForm" method="post">
            <input type="hidden" name="targetType" value="${param.targetType}"/>
            <table class="searchTable" style="border-collapse: separate;border-spacing: 0px 10px;line-height: normal;">
                <tr>
                    <c:if test="${param.targetType eq 'JX'}">
                        <td class="td_right">活动形式：</td>
                        <td class="td_left">
                            <select name="activityTypeId" class="select" style="width: 155px;margin-left: 5px"
                                    onchange="toPage(1)">
                                <option value="">全部</option>
                                <c:forEach items="${dictTypeEnumActivityTypeList}" var="activityType">
                                    <option value="${activityType.dictId}" ${param.activityTypeId eq activityType.dictId ? 'selected' : ''}>${activityType.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </c:if>
                    <td class="td_right"><c:if test="${param.targetType eq 'JX'}">&#12288;</c:if>评价指标：</td>
                    <td class="td_left">
                        <input class="input" type="text" name="targetName" value="${param.targetName}"/>
                    </td>
                    <td>
                        &#12288;<input type="button" value="查&#12288;询" class="btn_green" onclick="toPage(1);"/>&#12288;
                        <input type="button" value="新&#12288;增" class="btn_green" onclick="add('');"/>&#12288;
                        <c:if test="${param.targetType eq 'JX'}">
                            <input type="button" value="导&#12288;入" class="btn_green" onclick="importTarget();"/>&#12288;
                            <input type="button" value="导&#12288;出" class="btn_green" onclick="exportTarget();"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table" style="padding: 10px 20px 0px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <c:if test="${param.targetType eq 'JX'}">
                    <th>
                        <input type="checkbox" onclick="choose();" name="choose"/>
                    </th>
                </c:if>
                <th>序号</th>
                <c:if test="${param.targetType eq 'JX'}">
                    <th>活动形式</th>
                </c:if>
                <th>评价指标</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${targets}" var="target" varStatus="s">
                <tr>
                    <c:if test="${param.targetType eq 'JX'}">
                        <td>
                            <input type="checkbox" name="targetFlow" value="${target.targetFlow}"/>
                        </td>
                    </c:if>
                    <td>${(currentPage-1)*currentPageSize+s.count}</td>
                    <c:if test="${param.targetType eq 'JX'}">
                        <td>${target.activityTypeName}</td>
                    </c:if>
                    <td>${target.targetName}</td>
                    <td>
                        <a style="cursor: pointer;" href="javascript:void(0);"
                           onclick="add('${target.targetFlow}')">编辑</a>
                        <a style="cursor: pointer;" href="javascript:void(0);"
                           onclick="delTarget('${target.targetFlow}')">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty targets}">
                <tr>
                    <c:choose>
                        <c:when test="${param.targetType eq 'JX'}">
                            <td colspan="5" style="text-align: center">暂无信息</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="3" style="text-align: center">暂无信息</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <input id="currentPage" type="hidden" name="currentPage" value=""/>
        <c:set var="pageView" value="${pdfn:getPageView(targets)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>