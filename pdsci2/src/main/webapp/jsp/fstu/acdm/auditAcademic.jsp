<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;
        function checkYear(obj) {
            var dates = $(':text', $(obj).closest("div"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function toPage(page) {
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function audit(academicFlow) {
            var url = "<s:url value='/fstu/academic/auditAcademicOption?academicFlow='/>" + academicFlow;
            jboxOpen(url, "审核", 800, height);
        }
        function query(academicFlow) {
            var url = "<s:url value='/fstu/academic/queryAcademic?academicFlow='/>" + academicFlow;
            jboxOpen(url, "查看", 800, height);
        }
        function prt(academicFlow) {
            jboxTip("打印中，请稍等...");
            setTimeout(function () {
                var url = "<s:url value='/fstu/academic/printExpense?academicFlow='/>" + academicFlow;
                jboxPost(url, null, function (resp) {
                    if (resp) {
                        var headstr = "<html><head><title></title></head><body>";
                        var footstr = "</body></html>";
                        var newstr = resp;
                        var oldstr = document.body.innerHTML;
                        document.body.innerHTML = headstr + newstr + footstr;
                        window.print();
                        document.body.innerHTML = oldstr;
                        return false;
                    }
                }, null, false);
            }, 2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/fstu/academic/auditAcademic'/>" method="post">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td>
                        <div class="searchDiv">
                            &#12288;&#12288;学术名称：
                            <input type="text" name="academicName" value="${param.academicName}"
                                                        class="xltext">
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;学术类型：
                            <select name="academicTypeId" class="xlselect">
                                <option></option>
                                <c:forEach items="${dictTypeEnumAcademicTypeList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${dict.dictId eq param.academicTypeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;学术地点：
                            <input type="text" class="xltext" name="placeCity" value="${param.placeCity}">
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;学术天数：
                            <input type="text" class="xltext" name="takeDay"
                                                        value="${param.takeDay}">
                        </div>

                        <div class="searchDiv">
                            学术开始时间：
                            <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                   type="text" class="ctime" name="beginTime" value="${param.beginTime}"
                                   onchange="checkYear(this)">
                        </div>

                        <div class="searchDiv">
                            学术结束时间：
                            <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                          type="text" class="ctime" name="endTime" value="${param.endTime}"
                                          onchange="checkYear(this)">
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;审核结果：
                            <input type="radio" name="auditStatusId" value="ALL"
                                   <c:if test="${param.auditStatusId eq 'ALL' || param.flag eq 'ALL'}">checked="checked"</c:if>>全部
                            <input type="radio" name="auditStatusId" value="Y"
                                   <c:if test="${param.auditStatusId eq 'Y'}">checked="checked"</c:if>>已审核
                            <input type="radio" name="auditStatusId" value="N"
                                   <c:if test="${param.auditStatusId eq 'N'}">checked="checked"</c:if>>未审核
                        </div>

                        <div class="searchDiv">
                            <input type="hidden" name="currentPage" id="currentPage">
                            &#12288;&#12288;<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>学术名称</th>
                <th>学术类型</th>
                <th>学术地点（市）</th>
                <th>学术开始时间</th>
                <th>学术结束时间</th>
                <th>天数</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${academicList}" var="activity">
                <tr>
                    <td>${activity.academicName}</td>
                    <td>${activity.academicTypeName}</td>
                    <td>${activity.placeCity}</td>
                    <td>${activity.beginTime}</td>
                    <td>${activity.endTime}</td>
                    <td>${activity.takeDay}</td>
                    <td>${activity.auditStatusName}</td>
                    <td>
                        <c:if test="${activity.auditStatusId eq 'Passing'}"><%-- 待审核 --%>
                            <a onclick="audit('${activity.academicFlow}');" style="cursor: pointer;color: blue;">审核</a>
                        </c:if>
                        <a onclick="query('${activity.academicFlow}');" style="cursor: pointer;color: blue;">查看</a>
                        <a onclick="prt('${activity.academicFlow}');" style="cursor: pointer;color: blue;">打印</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(academicList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>