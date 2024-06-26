<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(function(){
            $("#detail").slideInit({
                width:1000,
                speed:500,
                outClose:true,
                haveZZ:true
            });
        });
        function toPage(){
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function addInfo(){
            $("#Tbody").append($("#templete").html())
        }
        function editInfo(recordFlow){
            jboxOpen("<s:url value='/gyxjgl/abroadApply/editAbroadApply'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}","编辑",960,600);
        }

        function upFile(recordFlow){
            jboxOpen("<s:url value='/gyxjgl/abroadApply/showUploadFile'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}","编辑",500,180);
        }
        function delInfo(recordFlow){
            var url = "<s:url value='/gyxjgl/abroadApply/delAbroadApply'/>?recordFlow="+recordFlow;
            jboxConfirm("确定删除该记录？",function(){
                jboxPost(url,null,function(resp){
                    if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                        toPage(1);
                    }
                },null,true);
            },null);
        }


        function exportData(){
            var url = "<s:url value='/gyxjgl/partyBranch/exportPartyMembers'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }

        function printOpt(){
            jboxTip("正在准备打印…");
            $("#printDivIframe").removeAttr("hidden");
            setTimeout(function(){
                $("#printDivIframe").jqprint({
                    debug: false,
                    importCSS: true,
                    printContainer: true,
                    operaSupport: false
                });
                $("#printDivIframe").attr("hidden","hidden");
                jboxEndLoading();
                return false;
            },2000);
        }
        function save(){
            var jsonArry = [];
            $("#Tbody tr").each(function(i,o){
                var count = 0;
                var json = {};
                $(o).find("input").each(function(){
                    if($.trim($(this).val()) != "")
                        count++;
                });
                if(count > 0){
                    json = {
                        "recordFlow" : $(o).attr("recordFlow"),
                        "applyFlow" : '${applyFlow}',
                        "dayCount" : $(o).find("input[name='dayCount']").val(),
                        "dateRange" : $(o).find("input[name='dateRange']").val(),
                        "activityDesc" : $(o).find("input[name='activityDesc']").val(),
                        "linkmanPhone" : $(o).find("input[name='linkmanPhone']").val(),
                        "cityName" : $(o).find("input[name='cityName']").val()
                    }
                    jsonArry.push(json);
                }
            });
            if(jsonArry.length == 0){
                jboxTip("请添加出访行程路线登记！");
                return;
            }
            jboxPostJson("<s:url value='/gyxjgl/abroadApply/saveTripInfo'/>", JSON.stringify(jsonArry), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    jboxCloseMessager();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/abroadApply/showTripInfo"/>" method="post">
            <div style="margin-top: 5px;margin-bottom: 8px;">
                <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
                <input type="hidden" name="formType" value="${param.formType}"/>
            </div>
            <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td colspan="99" style="text-align: center;font-size: 16px;">出访行程路线登记
                    <c:if test="${roleFlag eq 'student'}"><img style='cursor:pointer;margin:-4px 0px 0px 20px;' src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addInfo()" title='添加'></c:if>
                </td>
            </tr>
            <tr style="font-weight: bold;">
                <td style="width:60px;">天数</td>
                <td style="width:110px;">日期</td>
                <td style="width:120px;">活动内容</td>
                <td style="width:120px;">外方联系人及电话</td>
                <td style="width:80px;">城市</td>
            </tr>
            <tbody id="Tbody">
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr recordFlow="${info.recordFlow}">
                    <c:if test="${param.roleFlag eq 'student'}">
                        <td style="width:10%"><input type="text" name="dayCount" value="${info.dayCount}" style="width:90%;"></td>
                        <td style="width:20%"><input type="text" name="dateRange" value="${info.dateRange}" style="width:90%;"></td>
                        <td style="width:35%"><input type="text" name="activityDesc" value="${info.activityDesc}" style="width:90%;"></td>
                        <td style="width:20%"><input type="text" name="linkmanPhone" value="${info.linkmanPhone}" style="width:90%;"></td>
                        <td style="width:15%"><input type="text" name="cityName" value="${info.cityName}" style="width:90%;"></td>
                    </c:if>
                    <c:if test="${param.roleFlag ne 'student'}">
                        <td>${info.dayCount}</td>
                        <td>${info.dateRange}</td>
                        <td>${info.activityDesc}</td>
                        <td>${info.linkmanPhone}</td>
                        <td>${info.cityName}</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty dataList}">
                <tr>
                    <td colspan="99" style="text-align: center;">无记录！</td>
                </tr>
            </c:if>
        </table>
        </form>
        <div style="text-align: center;margin-top: 20px;">
            <input type="button" class="search" onclick="printOpt();" value="打&#12288;印"/>
            <c:if test="${param.roleFlag eq 'student'}"><input id="saveBtn" type="button" value="保&#12288;存" class="search" onclick="save();"/></c:if>
            <input type="button" value="关&#12288;闭" class="search" onclick="jboxCloseMessager();"/>
        </div>
    </div>
</div>
<table hidden="hidden">
    <tbody id="templete">
        <tr recordFlow="">
            <td style="width:10%"><input type="text" name="dayCount" style="width:90%;"></td>
            <td style="width:20%"><input type="text" name="dateRange" style="width:90%;"></td>
            <td style="width:35%"><input type="text" name="activityDesc" style="width:90%;"></td>
            <td style="width:20%"><input type="text" name="linkmanPhone" style="width:90%;"></td>
            <td style="width:15%"><input type="text" name="cityName" style="width:90%;"></td>
        </tr>
    </tbody>
</table>
<div hidden="hidden" id="printDivIframe" name="printDivIframe">
    <div style="text-align:center;font-size:22px;font-weight:500;margin-top: 15px;margin-bottom: 8px;">
        出访行程路线登记
    </div>
    <div>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:10%;">天数</td>
                <td style="width:20%;">日期</td>
                <td style="width:30%;">活动内容</td>
                <td style="width:25%;">外方联系人及电话</td>
                <td style="width:15%;">城市</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${info.dayCount}</td>
                    <td>${info.dateRange}</td>
                    <td>${info.activityDesc}</td>
                    <td>${info.linkmanPhone}</td>
                    <td>${info.cityName}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>