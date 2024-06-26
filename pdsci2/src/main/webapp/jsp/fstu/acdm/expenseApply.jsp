<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        var height=(window.screen.height)*0.7;
        var width=(window.screen.width)*0.7;

        function checkYear(obj){
            var dates = $(':text',$(obj).closest("div"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function query(academicFlow){
            var url = "<s:url value='/fstu/academic/queryAcademic?academicFlow='/>"+academicFlow;
            jboxOpen(url, "查看",800,height);
        }
        function expenseReq(academicFlow){
            var url = "<s:url value='/fstu/academic/expenseReq?academicFlow='/>"+academicFlow;
            jboxOpen(url, "报销申请",800,height);
        }
        function prt(academicFlow){
            jboxTip("打印中，请稍等...");
            setTimeout(function(){
                var url = "<s:url value='/fstu/academic/printExpense?academicFlow='/>"+academicFlow;
                jboxPost(url, null, function(resp){
                    if (resp) {
                        var headstr = "<html><head><title></title></head><body>";
                        var footstr = "</body></html>";
                        var newstr = resp;
                        var oldstr = document.body.innerHTML;
                        document.body.innerHTML = headstr+newstr+footstr;
                        window.print();
                        document.body.innerHTML = oldstr;
                        return false;
                    }
                }, null, false);
            },2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/fstu/academic/expenseApply/${roleFlag}'/>" method="post">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td>
                        <div class="searchDiv">
                            &#12288;&#12288;学术名称：
                            <input type="text" class="xltext" name="academicName" value="${param.academicName}">
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;学术类型：
                            <select name="academicTypeId" class="xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcademicTypeList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq param.academicTypeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;学术地点：
                            <input type="text" class="xltext" name="placeCity" value="${param.placeCity}">
                        </div>
                        <div class="searchDiv">
                            &#12288;&#12288;学术天数：
                        <input type="text" class="xltext" name="takeDay" value="${param.takeDay}">
                        </div>

                        <div class="searchDiv">
                            学术开始时间：
                        <input readonly="readonly" class="ctime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text"  name="beginTime" value="${param.beginTime}" onchange="checkYear(this)">
                        </div>

                        <div class="searchDiv">
                            学术结束时间：
                            <input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" class="ctime" name="endTime" value="${param.endTime}" onchange="checkYear(this)">
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
                <th>报销状态</th>
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
                        <c:if test="${activity.expenseStatusId eq 'Add'}"><font color="#ffd700">${activity.expenseStatusName}</font></c:if>
                        <c:if test="${activity.expenseStatusId eq 'Passing'}"><font color="red">${activity.expenseStatusName}</font></c:if><%-- 报销审核中 --%>
                        <c:if test="${activity.expenseStatusId eq 'Passed'}"><font color="green">${activity.expenseStatusName}</font></c:if><%-- 报销已审核 --%>
                        <c:if test="${activity.expenseStatusId eq 'Backed'}"><font color="blue">${activity.expenseStatusName}</font></c:if><%-- 报销被驳回 --%>
                    </td>
                    <td>
                        <c:if test="${activity.expenseStatusId eq 'Add' || activity.expenseStatusId eq 'Passing' || activity.expenseStatusId eq 'Backed'}"><%-- 报销未提交 或者 报销审核中 及被驳回 --%>
                            <a onclick="expenseReq('${activity.academicFlow}');" style="cursor: pointer;color: blue;">报销申请</a>
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