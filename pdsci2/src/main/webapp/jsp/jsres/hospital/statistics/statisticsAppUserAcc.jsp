<c:if test="${param.open ==GlobalConstant.FLAG_Y }">
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
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
</c:if>
<c:if test="${param.open !=GlobalConstant.FLAG_Y }">
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" charset="utf-8"
            src="<s:url value='/js/bootstrap-datepicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <link rel="stylesheet" type="text/css"
          href="<s:url value='/css/datepicker.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
</c:if>
<script type="text/javascript"
        src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        }).on('changeDate', function (e) {
        });
        ;
    });
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts', 'echarts/chart/line'], function (ec) {
        var myChart = ec.init(document.getElementById('hosChart'));
        var lineLabel = [];
        var lineValue = [];
        var value = [];
        <c:forEach items="${timeGapMon}" var="t">
        var t = "${t}";
        lineLabel.push(t);
        var a = "${percentMap[t]}";
        lineValue.push(toPercent(a));
        value.push("${countMap[t]}");
//			lineValue.push("${countMap[t]}"+"("+"${percentMap[t]}"+")");
        </c:forEach>
        if (lineLabel.length == 0) {
            lineLabel.push("");
            lineValue.push("");
        }
        var count = lineLabel.length;
        if (count >= 4) {
            count = 4;
        }
        var startDate = (1 - count / lineLabel.length) * 100;
        var title = "";
        var sessionNumber = "";
        var type = "";
        if ("${param.catSpeId}" != "") {
            <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
            if ("${param.catSpeId}" == "${trainCategory.id}") {
                type = "${trainCategory.name}";
            }
            </c:forEach>
        }
        /*if("
        ${ sessionNumber}"!=""){
         sessionNumber="
        ${sessionNumber}";
         }else{
         sessionNumber = $("#sessionNumber").val();
        <%--sessionNumber="${sysCfgMap['jsres_doctorCount_sessionNumber']}";--%>
         }*/
        sessionNumber = $("#sessionNumber").val();
        title = sessionNumber + "届" + type + "APP的使用情况";
        option = {
            title: {
                text: title,
                x: 'center'
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                show: true,
                feature: {
//		            mark : {show: true},
//		            dataZoom : {show: true},
//		            dataView : {show: true},
//		            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
//		            restore : {show: true},
//		            saveAsImage : {show: true}
                },
//		        x:'800'
            },
            calculable: true,
            dataZoom: {//下面拖动的代码块
                show: true,
                realtime: true,
                start: startDate,
                end: 100
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    data: lineLabel
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    interval: 'auto',
                    axisLabel: {
                        formatter: function (value) {
                            return parseInt(value) + "%";
                        }
                    },
                }
            ],
            series: [
                {
                    name: '百分比',
                    type: 'line',
                    data: lineValue,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true, position: 'top',
                                formatter: function (a, b, c) {
                                    return c + "%";
                                }
                            }
                        }
                    }
                },
                {
                    name: '使用人数',
                    type: 'line',
                    data: value,
                    symbol: 'none',//去掉点
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                color: 'transparent'//透明se
                            }
                        }
                    }
                },
            ]
        };

        myChart.setOption(option);

        var ecConfig = require('echarts/config');
        var timeoutObj;
        myChart.on(ecConfig.EVENT.DATA_ZOOM, function (param) {
            if (param) {
                if (param.zoom) {
                    var labelLength = lineLabel.length;
                    if (labelLength) {
                        var start = param.zoom.start<0?0:param.zoom.start;
                        var end = param.zoom.end>100?100:param.zoom.end;
                        var startDate = lineLabel[Math.floor(labelLength * (start / 100))];
                        var endDate = lineLabel[Math.ceil(labelLength * (end / 100) - 1)];
                        clearTimeout(timeoutObj);
                        timeoutObj = setTimeout(function () {
                            caculate(startDate, endDate);
                        }, 200);
                    }
                }
            }
        });
    });

    function search(){
        changeTrainSpes($('#catSpeId').val(), $('#sessionNumber').val());
    }
    function caculate(startDate, endDate) {
        var sessionNumber = $("#sessionNumber").val();
        var graduate = "";
        if ($("input[name='graduate']:checkbox:checked").val()) {
            graduate = $("input[name='graduate']:checkbox:checked").val();
        }
        ;
        var url = "<s:url value='/jsres/statistic/statisticsNoAppUser'/>?open=${param.open}&currentPage=${param.currentPage}&catSpeId=${param.catSpeId}&orgFlow=${param.orgFlow}&endDate=" + endDate + "&startDate=" + startDate + "&sessionNumber=" + sessionNumber + "&graduate=" + graduate;
        jboxPostLoad("othersDiv", url, function (resp) {
            if (resp != null) {
                top.jboxEndLoading();
            }
        }, false);
    }
    function toPercent(data) {
        var strData = parseFloat(data) * 10000;
        strData = Math.round(strData);
        strData /= 100.00;
        var ret = strData.toString();
        return ret;
    }
    function changeTrainSpes(catSpeId, sessionNumber) {
        var graduate = "";
        if ($("input[name='graduate']:checkbox:checked").val()) {
            graduate = $("input[name='graduate']:checkbox:checked").val();
        }
        ;
        <c:if test="${GlobalConstant.USER_LIST_LOCAL eq param.userListScope}">
        jboxLoad("hosContent", "<s:url value='/jsres/statistic/statisticsAppUserAcc'/>?catSpeId=" + catSpeId + "&sessionNumber=" + sessionNumber + "&graduate=" + graduate + "&userListScope=local", true);
        </c:if>
        if ("${GlobalConstant.USER_LIST_LOCAL}" != "${param.userListScope}") {
            var orgFlow = "${param.orgFlow}";
            var url = "<s:url value='/jsres/statistic/statisticsAppUserAcc'/>?open=Y&orgFlow=" + orgFlow + "&catSpeId=" + catSpeId + "&sessionNumber=" + sessionNumber + "&graduate=" + graduate + "&userListScope=";
            top.jboxStartLoading();
            window.location.href = url;
        }
    }
</script>
<script type="text/javascript"
        src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
</head>
<body>
<div class="main_bd" <c:if test="${param.open eq 'Y'}"> style="max-height: 530px;overflow: auto;" </c:if> >
    <div class="div_search">
        <form id="searchForm">
            学员类型：
            <select name="catSpeId" id="catSpeId" class="select" style="width:107px;">
                <option value="AssiGeneral">助理全科</option>
            <%--  <option value="">请选择</option>
                <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
                    <option value="${trainCategory.id}"
                            <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                </c:forEach>--%>
            </select>
            &#12288;
            年级：<input type="text" id="sessionNumber" name="sessionNumber"
                      value="${sessionNumber}"
                      class="select" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
            <input type="checkbox" value="${resDocTypeEnumGraduate.id}" name="graduate" id="graduate"
                   <c:if test="${resDocTypeEnumGraduate.id eq param.graduate}">checked</c:if>/>在校专硕
            &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="search();"/>
        </form>
    </div>
    <div id="hosChart"
         <c:if test="${param.openType eq GlobalConstant.FLAG_Y}">style="height: 250px;width:100%;margin: 50px 0 0 0;"</c:if>
         <c:if test="${param.openType != GlobalConstant.FLAG_Y}">style="height: 250px;width:100%;margin: 20px 0 0 0;"</c:if>
    >
    </div>
    <div style="padding-top: 20px;">
        <div class="search_table">
            <div>
                <h4>未使用APP的学员信息</h4>
            </div>
        </div>
        <div>
            <div id="othersDiv"
                 <c:if test="${param.open !=GlobalConstant.FLAG_Y }">style=""</c:if>>

            </div>
        </div>
    </div>
</div>
</body>
</html>
