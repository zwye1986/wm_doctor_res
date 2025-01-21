<!DOCTYPE html>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
        * {
            margin: 0;
        }
        .main-container {
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .header-title {
            font-size: 20px;
            font-weight: bold;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .search-container {
            display: flex;
            gap: 50px;
        }

        .content-container {
            width: 1000px;
        }

        .search-container-item {
            display: flex;
            align-items: center;
            height: 30px;
        }

        .chart-row {
            display: flex;
            width: 100%;
            justify-content: space-between;
            margin-top: 20px;
            gap: 20px;
        }

        .chart-title {
            font-size: 16px;
            font-weight: bold;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .empty-data {
            text-align: center;
            margin-top: 100px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="main-container">
    <div class="header-title">
        江苏省住院医师规范化培训 师资信息统计图
    </div>
    <div class="content-container">
        <div class="search-container">
            <div class="search-container-item">
                <div>证书统计：</div>
                <select name="countType" id="countType" onchange="search();">
                    <option value="both"<c:if test="${param.countType eq 'both'}">selected="selected"</c:if>>单人多次培训记录均统计</option>
                    <option value="only"<c:if test="${param.countType eq 'only'}">selected="selected"</c:if>>单人多次培训只取最高最新</option>
                </select>
            </div>
        </div>
        <div class="chart-row">
            <div style="flex: 1">
                <div class="chart-title">师资情况</div>
                <div id="pieChart1" style="height: 300px"></div>
            </div>
            <div style="flex: 1">
                <div class="chart-title">培训证书级别情况</div>
                <div id="pieChart2" style="height: 300px"></div>
            </div>
        </div>

        <div class="chart-row">
            <div style="flex: 1">
                <div class="chart-title">师资数量排行榜（按专业）</div>
                <c:if test="${not empty teacherSpeCountDtoList}">
                    <div id="barChart1" style="height: 550px"></div>
                </c:if>
                <c:if test="${empty teacherSpeCountDtoList}">
                    <div class="empty-data">暂无数据...</div>
                </c:if>
            </div>
        </div>
        <div class="chart-row">
            <div style="flex: 1">
                <div class="chart-title">师资数量排行榜（按基地）</div>
                <c:if test="${not empty teacherSpeCountDtoListForOrg}">
                    <div id="barChart2" style="height: 1500px"></div>
                </c:if>
                <c:if test="${empty teacherSpeCountDtoListForOrg}">
                    <div class="empty-data">暂无数据...</div>
                </c:if>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        var i = 0;
        var j = 0;
        debugger;
        // 需要渲染的series数据
        var seriesData = [
            {
                name: "一般师资",
                value: "${teacherReportCountDtoMap['generalCount']}",
            },
            {
                name: "骨干师资",
                value: "${teacherReportCountDtoMap['keyCount']}",
            }
        ];
        // 基于准备好的dom，初始化echarts实例
        var chart1 = echarts.init(
            document.getElementById("pieChart1"),
            "customed"
        );
        // 指定图表的配置项和数据
        var chartOption1 = {
            legend: {
                bottom: 20,
            },
            series: [
                {
                    radius: [0, "50%"], // 设置饼图的半径
                    type: "pie",
                    label: {
                        formatter: "{b}\n{c}",
                    },
                    data: seriesData,
                },
            ],
        };
        // 使用刚指定的配置项和数据显示图表
        chart1.setOption(chartOption1);

        var certificateData = [
            {
                name: "国家级",
                value: "${teacherCertificateCountDtoMap['country']}",
            },
            {
                name: "省级",
                value: "${teacherCertificateCountDtoMap['province']}",
            },
            {
                name: "市级",
                value: "${teacherCertificateCountDtoMap['city']}",
            },
            {
                name: "院级",
                value: "${teacherCertificateCountDtoMap['hospital']}",
            }
        ];

        var chart2 = echarts.init(
            document.getElementById("pieChart2"),
            "customed"
        );
        // 指定图表的配置项和数据
        var chartOption2 = {
            legend: {
                bottom: 20,
            },
            series: [
                {
                    radius: [0, "50%"], // 设置饼图的半径
                    type: "pie",
                    label: {
                        formatter: "{b}\n{c}",
                    },
                    data: certificateData,
                },
            ],
        };
        // 使用刚指定的配置项和数据显示图表
        chart2.setOption(chartOption2);

        var yData = [];
        var xData1 = [];
        var xData2 = [];
        <c:forEach items="${teacherSpeCountDtoList}" var="teacherSpeCountDto">
            yData.push("${teacherSpeCountDto.speName}");
            xData1.push("${teacherSpeCountDto.generalNumber}");
            xData2.push("${teacherSpeCountDto.keyNumber}");
            i = i + 1;
        </c:forEach>

        $("#barChart1").attr("style", "height: " + (i * 60 + 100) + "px");

        var chart3 = echarts.init(
            document.getElementById("barChart1"),
            "customed"
        );

        const barOption1 = (option = {
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    // Use axis to trigger tooltip
                    type: "shadow", // 'shadow' as default; can also be 'line' or 'shadow'
                },
            },
            legend: {
                bottom: 0,
            },
            xAxis: {
                type: "value",
            },
            yAxis: {
                type: "category",
                data: yData,
            },
            series: [
                {
                    name: "一般师资",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: true,
                        formatter: p=>{
                            return p.value != 0 ? p.value : '';
                        }
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: xData1,
                    barWidth: '30px',
                },
                {
                    name: "骨干师资",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: true,
                        formatter: p=>{
                            return p.value != 0 ? p.value : '';
                        }
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: xData2,
                    barWidth: '30px',
                },
            ],
        });

        chart3.setOption(barOption1);

        var chart4 = echarts.init(
            document.getElementById("barChart2"),
            "customed"
        );

        var yData1 = [];
        var xData3 = [];
        var xData4 = [];
        <c:forEach items="${teacherSpeCountDtoListForOrg}" var="teacherSpeCountDto">
        yData1.push("${teacherSpeCountDto.speName}");
        xData3.push("${teacherSpeCountDto.generalNumber}");
        xData4.push("${teacherSpeCountDto.keyNumber}");
        j = j + 1;
        </c:forEach>

        $("#barChart2").attr("style", "height: " + (j * 60 + 100) + "px");

        const barOption2 = (option = {
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    // Use axis to trigger tooltip
                    type: "shadow", // 'shadow' as default; can also be 'line' or 'shadow'
                },
            },
            legend: {
                bottom: 0,
            },
            grid:{
                left : 200
            },
            xAxis: {
                type: "value",
            },
            yAxis: {
                type: "category",
                data: yData1,
            },
            series: [
                {
                    name: "一般师资",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: true,
                        formatter: p=>{
                            return p.value != 0 ? p.value : '';
                        }
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: xData3,
                    barWidth: '30px',
                },
                {
                    name: "骨干师资",
                    type: "bar",
                    stack: "total",
                    label: {
                        show: true,
                        formatter: p=>{
                            return p.value != 0 ? p.value : '';
                        }
                    },
                    emphasis: {
                        focus: "series",
                    },
                    data: xData4,
                    barWidth: '30px',
                },
            ],
        });

        chart4.setOption(barOption2);
    });

    function search() {
        var countType = $("#countType").val();
        var url = "<s:url value='/jsres/statistic/globalTeacherReportAll'/>?countType=" + countType;
        jboxLoad("div_table_0", url, true);
    }
</script>
</body>
</html>
