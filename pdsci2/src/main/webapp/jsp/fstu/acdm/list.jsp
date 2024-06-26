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
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style>
        #searchForm td {
            border-left: 0;
            border-right: 0;
        }
    </style>
    <script type="text/javascript">
        function search() {
            if (($("#beginTime").val() > $("#endTime").val()) && ($("#endTime").val() != 0)
                    || ($("#startingTime").val() > $("#endingTime").val()) && ($("#endingTime").val() != 0)) {
                jboxTip("起始时间不能大于结束时间！")
                return;
            }
            $("#searchForm").submit();
        }
        function toPage(page) {
            if (page) {
                $("#currentPage").val(page);
            }
            search();
        }
        function exportExcel() {
            var url = "<s:url value='/fstu/academicView/export/${role}'/>";
            var data = $("#searchForm").serialize();
            jboxSubmit($("#searchForm"), url, null, null, true);
            jboxEndLoading();
        }
    </script>
    <script type="text/javascript"
            src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        require.config({
            paths: {
                echarts: "<s:url value='/js/echarts'/>"
            }
        });

        require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
            'echarts/chart/bar', 'echarts/chart/line'], function (ec) {
            var myChart = ec.init(document.getElementById('doctorNum'));
            option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['会议费', '交通费', '食宿费', '其它费', '服装费', '资料费', '补贴费'],
                    x: 'left',
                    y: '15'
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        saveAsImage: {show: true}
                    },
                    x: 'right',
                    y: '15'
                },
                calculable: true,
                dataZoom: {
                    show: true,
                    realtime: true,
                    start: 0,
                    end: 100
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: function () {
                            var list = [];
                            for (var i = '${beforeYear}'; i <= '${thisYear}'; i++) {
                                list.push(i);
                            }
                            return list;
                        }()
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        stackLabels: {enabled: true}
                    }
                ],
                series: [
                    {
                        name: '总计',
                        type: 'line',
                        data: function () {
                            var list = [];
                            <c:forEach begin="${beforeYear}" end="${thisYear}" step="1" varStatus="yearStatus">
                            <c:set var="k" value="${yearStatus.index}${''}"></c:set>
                            list.push("${resultMap[k]['sumFeeSum']}");
                            </c:forEach>
                            return list;
                        }()
                    },
                    {
                        name: '会议费',
                        type: 'bar',
                        stack: '总量',
                        data: function () {
                            var list = [];
                            <c:forEach begin="${beforeYear}" end="${thisYear}" step="1" varStatus="yearStatus">
                            <c:set var="k" value="${yearStatus.index}${''}"></c:set>
                            list.push("${resultMap[k]['meetingFeeSum']}");
                            </c:forEach>
                            return list;
                        }(),
                    },
                    {
                        name: '交通费',
                        type: 'bar',
                        stack: '总量',
                        data: function () {
                            var list = [];
                            <c:forEach begin="${beforeYear}" end="${thisYear}" step="1" varStatus="yearStatus">
                            <c:set var="k" value="${yearStatus.index}${''}"></c:set>
                            list.push("${resultMap[k]['trafficFeeSum']}");
                            </c:forEach>
                            return list;
                        }()
                    },
                    {
                        name: '食宿费',
                        type: 'bar',
                        stack: '总量',
                        data: function () {
                            var list = [];
                            <c:forEach begin="${beforeYear}" end="${thisYear}" step="1" varStatus="yearStatus">
                            <c:set var="k" value="${yearStatus.index}${''}"></c:set>
                            list.push("${resultMap[k]['foodFeeSum']}");
                            </c:forEach>
                            return list;
                        }()
                    },
                    {
                        name: '其它费',
                        type: 'bar',
                        stack: '总量',
                        data: function () {
                            var list = [];
                            <c:forEach begin="${beforeYear}" end="${thisYear}" step="1" varStatus="yearStatus">
                            <c:set var="k" value="${yearStatus.index}${''}"></c:set>
                            list.push("${resultMap[k]['otherFeeSum']}");
                            </c:forEach>
                            return list;
                        }()
                    },
                    {
                        name: '服装费',
                        type: 'bar',
                        stack: '总量',
                        data: function () {
                            var list = [];
                            <c:forEach begin="${beforeYear}" end="${thisYear}" step="1" varStatus="yearStatus">
                            <c:set var="k" value="${yearStatus.index}${''}"></c:set>
                            list.push("${resultMap[k]['costumeFeeSum']}");
                            </c:forEach>
                            return list;
                        }()
                    },
                    {
                        name: '资料费',
                        type: 'bar',
                        stack: '总量',
                        data: function () {
                            var list = [];
                            <c:forEach begin="${beforeYear}" end="${thisYear}" step="1" varStatus="yearStatus">
                            <c:set var="k" value="${yearStatus.index}${''}"></c:set>
                            list.push("${resultMap[k]['materialFeeSum']}");
                            </c:forEach>
                            return list;
                        }()
                    },
                    {
                        name: '补贴费',
                        type: 'bar',
                        stack: '总量',
                        data: function () {
                            var list = [];
                            <c:forEach begin="${beforeYear}" end="${thisYear}" step="1" varStatus="yearStatus">
                            <c:set var="k" value="${yearStatus.index}${''}"></c:set>
                            list.push("${resultMap[k]['subsidyFeeSum']}");
                            </c:forEach>
                            return list;
                        }()
                    }
                ]
            };
            myChart.setOption(option);
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/fstu/academicView/list/${role}'/>" method="post">
            <input name="currentPage" id="currentPage" type="hidden" value="">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td>
                        <div class="searchDiv">
                            &#12288;&#12288;学术名称：
                            <input type="text" value="${param.academicName}" name="academicName" class="xltext">
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;学术类型：
                            <select name="academicTypeId" class="xlselect">
                                <option value="">全部</option>
                                <c:forEach items="${dictTypeEnumAcademicTypeList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${dict.dictId eq param.academicTypeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;年&#12288;&#12288;份：
                            <input type="text" value="${param.applyYear}" name="applyYear" readonly="readonly"
                                   onClick="WdatePicker({dateFmt:'yyyy'})" class="xlselect">
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;主办单位：
                            <input type="text" value="${param.holdUnit}" name="holdUnit" class="xltext">
                        </div>

                        <div style="float: left">
                            <div class="searchDiv">
                                学术起止时间： <input class="ctime" type="text" value="${param.beginTime}" name="beginTime"
                                               id="beginTime" readonly="readonly"
                                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                            </div>
                            <div class="searchDiv">
                                至&#12288;
                                <input class="ctime" type="text" value="${param.endTime}" name="endTime" id="endTime"
                                       readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">&#12288;&#12288;&#12288;&#12288;&#12288;
                            </div>
                        </div>
                        <div style="float: left">
                            <div class="searchDiv">
                                &#12288;&#12288;申请时段：
                                <input type="text" value="${param.startingTime}" name="startingTime" readonly="readonly"
                                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="ctime">
                            </div>
                            <div class="searchDiv">
                                至&#12288;
                                <input type="text" value="${param.endingTime}" name="endingTime" readonly="readonly"
                                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="ctime">&#12288;&#12288;&#12288;&#12288;&#12288;
                            </div>
                        </div>
                        <div class="searchDiv">
                            &#12288;&#12288;学术天数：
                            <input type="text" name="takeDay" value="${param.takeDay}" class="xltext">
                        </div>

                        <div class="searchDiv">
                            &#12288;&#12288;申请人&#12288;：
                            <input type="text" name="applyUserName" value="${param.applyUserName}" class="xltext">
                        </div>
                        <div class="searchDiv">
                            &#12288;&#12288;申请部门：
                            <input type="text" value="${param.applyDeptName}" name="applyDeptName" class="xltext">
                        </div>
                        <div id="native" class="searchDiv" style="min-width: 560px">
                            &#12288;&#12288;学术地点：
                            <select id="placeProvinceId" name="placeProvinceId"
                                    class="province validate[custom[number]] xlselect"
                                    data-value="${acActivity.placeProvinceId}" data-first-title="选择省"></select>
                            <select id="placeCityId" name="placeCityId" class="city xlselect"
                                    data-value="${acActivity.placeCityId}" data-first-title="选择市"></select>
                            <input type="text" name="placeArea" value="${acActivity.placeArea}" placeholder="具体地点"
                                   class="xlselect">&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                            <input id="placeProvince" name="placeProvince" type="hidden">
                            <input id="placeCity" name="placeCity" type="hidden">
                        </div>
                        <script type="text/javascript">
                            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                            $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                            $.cxSelect.defaults.nodata = "none";
                            $("#native").cxSelect({
                                selects: ["province", "city"],
                                nodata: "none",
                                firstValue: ""
                            });
                        </script>


                        <c:if test="${role eq 'admin'}">
                            <div class="searchDiv">
                                &#12288;&#12288;审核状态：<label><input type="radio" name="auditStatusId" value=""
                                              <c:if test="${empty param.auditStatusId}">checked="checked"</c:if>>全部</label>
                                <label><input type="radio" name="auditStatusId" value="Passed"
                                              <c:if test="${param.auditStatusId eq 'Passed'}">checked="checked"</c:if>>提交已审核</label>
                                <label><input type="radio" name="auditStatusId" value="Passing"
                                              <c:if test="${param.auditStatusId eq 'Passing'}">checked="checked"</c:if>>提交未审核</label>
                            </div>

                            <div class="searchDiv">
                                &#12288;&#12288;<input type="button" class="search" value="查&#12288;询"
                                                       onclick="search()">
                                <input type="button" class="search" value="导出Excel" onclick="exportExcel()">
                            </div>

                        </c:if>
                        <c:if test="${role eq 'deptMaster'}">
                            <div class="searchDiv">
                                &#12288;&#12288;审核状态：
                                <label><input type="radio" name="auditStatusId" value=""
                                              <c:if test="${empty param.auditStatusId}">checked="checked"</c:if>>全部</label>
                                <label><input type="radio" name="auditStatusId" value="Passed"
                                              <c:if test="${param.auditStatusId eq 'Passed'}">checked="checked"</c:if>>已审核</label>
                                <label><input type="radio" name="auditStatusId" value="Passing"
                                              <c:if test="${param.auditStatusId eq 'Passing'}">checked="checked"</c:if>>未审核</label>
                            </div>

                            <div class="searchDiv">
                                &#12288;&#12288;<input type="button" class="search" value="查&#12288;询"
                                                       onclick="search()">
                                <input type="button" class="search" value="导出Excel" onclick="exportExcel()">
                            </div>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th width="5%">申请人</th>
                <th width="5%">申请时间</th>
                <th width="5%">申请部门</th>
                <th width="5%">学术名称</th>
                <th width="5%">学术类型</th>
                <th width="5%">学术地点(市)</th>
                <th width="5%">学术开始时间</th>
                <th width="5%">学术结束时间</th>
                <th width="5%">天数</th>
                <th width="5%">主办单位</th>
                <th width="5%">会议费</th>
                <th width="5%">交通费</th>
                <th width="5%">食宿费</th>
                <th width="5%">其他费</th>
                <th width="5%">服装费</th>
                <th width="5%">资料费</th>
                <th width="5%">补贴费</th>
                <th width="5%">总量费用</th>
                <th width="5%">审核状态</th>
                <th width="5%">报销状态</th>
            </tr>
            <c:forEach items="${academicActivities}" var="activity">
                <tr>
                    <td>${activity.applyUserName}</td>
                    <td>${activity.applyTime}</td>
                    <td>${activity.applyDeptName}</td>
                    <td>${activity.academicName}</td>
                    <td>${activity.academicTypeName}</td>
                    <td>${activity.placeCity}</td>
                    <td>${activity.beginTime}</td>
                    <td>${activity.endTime}</td>
                    <td>${activity.takeDay}</td>
                    <td>${activity.holdUnit}</td>
                    <td>${activity.meetingFee}</td>
                    <td>${activity.trafficFee}</td>
                    <td>${activity.foodFee}</td>
                    <td>${activity.otherFee}</td>
                    <td>${activity.costumeFee}</td>
                    <td>${activity.materialFee}</td>
                    <td>${activity.subsidyFee}</td>
                    <td>${activity.sumFee}</td>
                    <td>${activity.auditStatusName}</td>
                    <td>${activity.expenseStatusName}</td>
                </tr>
            </c:forEach>
        </table>
        <div style="padding-bottom: 15px">
            <c:set var="pageView" value="${pdfn:getPageView(academicActivities)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>

        <table class="xllist">
            <tr>
                <th>费用统计情况</th>
            </tr>
            <tr>
                <td>
                    <div id="doctorNum" style="height: 650px;width:100%;">

                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>