<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">
    function search() {
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if(startDate > endDate){
            jboxTip("查询上报开始时间不能大于结束时间！");
            return;
        }
        var url = "<s:url value='/res/resFunds/provinceFundList'/>";
        jboxPostLoad("content", url, $("#submitForm").serialize(), true);
    }
    function edit(recordFlow){
        var title = "新增";
        if(recordFlow){
            title = "修改";
        }
        var url = "<s:url value='/res/resFunds/editProvinceFund'/>?recordFlow=" + recordFlow;
        jboxOpen(url, title+"经费上报记录", 800, 500);
    }
    function toPage(page) {
        debugger;
        if (page) {
            $("#currentPage").val(page);
        }
        search();
    }
    function del(recordFlow) {
        jboxConfirm("确认删除？",function () {
            jboxPost("<s:url value='/res/resFunds/delProvinceFund'/>?recordFlow="+recordFlow,null,function (resp) {
                if(resp==1) {
                    jboxTip("操作成功")
                    search();
                }
            },null,false);
        })
    }
    function detail(mainFlow) {
        var url = "<s:url value='/res/resFunds/provinceFundDetailList'/>?mainFlow=" + mainFlow;
        jboxOpen(url, "省厅经费收支详情", 900 , 700);
    }
</script>
<div class="main_hd">
    <h2 class="underline">经费收支管理</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="submitForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
            <table>
                <tr>
                    <td class="td_left">
                        上报时间：
                    </td>
                    <td>
                        <input type="text" id="startDate" name="startDate" value="${param.startDate}" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                    <td >至</td>
                    <td>
                        <input type="text" id="endDate" name="endDate" value="${param.endDate}" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                    <td id="func" colspan="3">
                        &#12288;<input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>
                        <input class="btn_green" type="button" onclick="edit('')" value="新&#12288;增"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>上报时间</th>
                <th>资金到位日期</th>
                <th>资金开始使用时间</th>
                <th>资金使用截止时间</th>
                <th>金额（万元）</th>
                <th>收支详情</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${provinceFundList}" var="item">
                <tr>
                    <td>${item.reportDate}</td>
                    <td>${item.inPlaceDate}</td>
                    <td>${item.startUsingDate}</td>
                    <td>${item.stopUsingDate}</td>
                    <td>${item.amountOfMoney}</td>
                    <c:if test="${ 'Y' eq detaiMap[item.recordFlow]}" >
                        <td><a onclick="detail('${item.recordFlow}')">查看</a></td>
                    </c:if>
                    <c:if test="${ 'Y' ne detaiMap[item.recordFlow]}" >
                        <td><a onclick="detail('${item.recordFlow}')">填报</a></td>
                    </c:if>
                    <td><a onclick="edit('${item.recordFlow}')">编辑</a>&ensp;<a onclick="del('${item.recordFlow}')">删除</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty provinceFundList}">
                <tr>
                    <td colspan="20">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(provinceFundList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>