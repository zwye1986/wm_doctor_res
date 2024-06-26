<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red}
    </style>
    <script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        require.config({
            paths: {
                echarts:'<s:url value='/js/echarts'/>'
            }
        });
        function showBarChart(myChart,lineLabel,lineValue,yLable,xLable){
            var option = {
                xAxis: {
                    type: 'category',
                    data: lineLabel
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: lineValue,
                    type: 'bar'
                }]
            };
            myChart.setOption(option);
            myChart.getDataURL();
        }


            <c:forEach items="${dataList}" var="data">
            <c:if test="${data.questionDetail.questionTypeId ne 'Subjective'}">
                require(['echarts','echarts/chart/bar'],function(ec){
                    var myChart = ec.init(document.getElementById('questionChart_${data.questionDetail.serial}'));
                    var lineLabel = []; //x轴文字
                    var lineValue =[];
                    <c:if test="${data.questionDetail.questionTypeId ne 'Subjective'}">
                        <c:forEach items="${data.answerDetailList}" var="answer">
                            <c:set value="${data.questionDetail.serial}${','}${answer.serial}" var="key"/>
                            lineLabel.push("${answer.serial}");
                            lineValue.push("${empty answerDetailMap[key]?0:answerDetailMap[key]}");
                        </c:forEach>
                    </c:if>
                    if (lineLabel.length==0) {
                        lineLabel.push("");
                        lineValue.push("");
                    }
                    showBarChart(myChart,lineLabel,lineValue,'人数','选项');
                });
            </c:if>
        </c:forEach>

        function printThis(){
            jboxTip("打印中，请稍等...");
            $("#btnDiv1").hide();
            $("#btnDiv2").hide();
            window.print();
            $("#btnDiv1").show();
            $("#btnDiv2").show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="text-align: right;margin-top:8px;margin-bottom: 8px;" id="btnDiv1">
            <input type="button" class="search" value="打&#12288;印" onclick="printThis();"/>
        </div>
        <form id="questionDetailForm" >
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: center;line-height: 140%;" colspan="2"><span style="font-size: 16px;font-weight:600;">${courseQuestion.questionName}</span></td>
                </tr>
                <tr>
                    <td style="text-align: left;line-height: 140%;" colspan="2">
                        年级：${eduCourse.courseSession}&emsp;人数：${fn:length(evaluateList)}&emsp;课程名称：${eduCourse.courseName}&emsp;课程代码：${eduCourse.courseCode}
                    </td>
                </tr>
                <c:forEach items="${dataList}" var="data">
                    <c:if test="${data.questionDetail.questionTypeId ne 'Subjective'}">
                        <tr>
                            <td style="text-align: left;font-weight:600;line-height: 140%;">
                                &ensp;${data.questionDetail.serial}、${data.questionDetail.questionTitle}
                            </td>
                            <td rowspan="2">
                                <div id="questionChart_${data.questionDetail.serial}" style="height:200px;width:250px;margin: -30px -30px -30px -60px;">

                                </div>
                                <input id="chartUrl_${data.questionDetail.serial}" type="hidden"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left;line-height: 140%;">
                                <c:if test="${data.questionDetail.questionTypeId ne 'Subjective'}">
                                    &ensp;<c:forEach items="${data.answerDetailList}" var="answer">
                                    <c:set value="${data.questionDetail.serial}${','}${answer.serial}" var="key"/>
                                    <font style="font-weight:600;">${answer.serial}.</font>${answer.answerName}&ensp;${empty answerDetailMap[key]?0:answerDetailMap[key]}人&ensp;&ensp;
                                </c:forEach>
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </form>
    </div>
    <div style="text-align: center;margin-bottom: 15px;" id="btnDiv2">
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
    </div>
</div>
</body>
</html>