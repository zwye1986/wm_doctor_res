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
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        require.config({
            paths: {
                echarts: "<s:url value='/js/echarts'/>"
            }
        });

        require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
            'echarts/chart/bar','echarts/chart/line'],function(ec){
            var myChart = ec.init(document.getElementById('doctorNum'));
            option = {
                tooltip : {
                    trigger: 'axis'
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        saveAsImage : {show: true}
                    },
                    x:'right',
                    y:'15'
                },
                calculable : true,
                dataZoom : {
                    show : true,
                    realtime : true,
                    start : 0,
                    end : 100
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : true,
                        name : '项目大类',
                        data : function (){
                            var list = [];
                            <c:forEach items="${resultMap}" var="item">
                                list.push('${item.key}');
                            </c:forEach>
                            return list;
                        }()
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        name:'全院年度总学分',
                        stackLabels:{enabled : true}
                    }
                ],
                series : [
                    {
                        name:'总分',
                        type:'bar',
                        barMaxWidth:100,
                        barGap:20,
                        data:function (){
                            var list = [];
                            <c:forEach items="${resultMap}" var="item">
                            <c:set var="i" value="${item.key}"></c:set>
                            list.push("${resultMap[i]}");
                            </c:forEach>
                            return list;
                        }(),
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,//是否展示
                                    textStyle: {
                                        fontWeight:'bolder',
                                        fontSize : '20',
                                        fontFamily : '微软雅黑',
                                    }
                                }
                            }
                        },
                    }
                ]
            };
            myChart.setOption(option);
        });
    </script>

    <script type="text/javascript">
        function search(){
            if(($("#startScore").val()>$("#endScore").val())&&($("#endScore").val()!=0)
                    ||false==$("#searchForm").validationEngine("validate")){
                jboxTip("分数段设置有误！")
                return;
            }
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function exportExcel(){
            var url = "<s:url value='/fstu/score/export'/>";
            var data = $("#searchForm").serialize();
            jboxSubmit($("#searchForm"),url,null,null,true);
            jboxEndLoading();
        }
        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            initDept();
            $('#firstProjTypeId option').hide();
            $('#firstProjTypeId option[value=""]').show();
            $('#firstProjTypeId'+' option.${dictTypeEnumAcaScoreType.id}\\.'+$("[name='firstScoreTypeId']").val()).show();
        });

        function linkage(dictId){
            $('#firstProjTypeId').val("");//清空上次展现数据
            $('#firstProjTypeId option').hide();
            $('#firstProjTypeId option[value=""]').show();
            $('#firstProjTypeId'+' option.${dictTypeEnumAcaScoreType.id}\\.'+dictId).show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/fstu/score/scoreView'/>" method="post">
            <input type="hidden" name="currentPage" id="currentPage">
            <input type="hidden" name="role" value="${param.role}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td>
                    <div class="searchDiv">
                        学分年度：
                        <select name="sessionNumber" class="xlselect">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumScoreYearList}" var="dict">
                            <option value="${dict.dictName}"
                                    <c:if test="${dict.dictName eq param.sessionNumber}">selected</c:if>
                            >${dict.dictName}
                                </c:forEach>
                            </option>
                        </select>
                    </div>
                        <div class="searchDiv">
                            学员姓名：
                            <input type="text" value="${param.userName}" name="userName" class="xltext">
                        </div>

                        <div class="searchDiv">
                        工作单位：
                            <input type="text" value="${param.orgName}" name="orgName" class="xltext">
                        </div>

                        <div class="searchDiv">
                            学分类别：
                            <select name="firstScoreTypeId" class="xlselect" onchange="linkage(this.value)">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${dict.dictId eq param.firstScoreTypeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="searchDiv">
                            项目大类：
                            <select id="firstProjTypeId" name="firstProjTypeId" class="xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <c:set var="dictKey" value="dictTypeEnumAcaScoreType.${dict.dictId}List"/>
                                    <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                        <c:set var="valueKey" value="${dict.dictId},${scope.dictId}"></c:set>
                                        <option class="${scope.dictTypeId}" value="${valueKey}" <c:if test="${valueKey eq param.firstProjTypeId}">selected</c:if>>${scope.dictName}</option>
                                    </c:forEach>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="searchDiv">
                        所属部门：
                            <input id="trainDept" class="xltext" name="userDeptName" type="text" value="${param.userDeptName}" autocomplete="off"/>
                        </div>
                        <div class="searchDiv">
                            学员工号：
                            <input type="text" value="${param.userCode}" name="userCode" class="xltext">
                        </div>
                        <div class="searchDiv" style="width:550px;">
                        年度总分：
                            <input type="text" value="${param.startScore}" name="startScore" class="validate[number] xltext" id="startScore">
                            -
                        <input type="text" value="${param.endScore}" name="endScore" class="validate[number] xltext" id="endScore">
                        </div>
                        <div class="searchDiv">
                            审核结果：
                            <input type="radio" name="auditStatusId" value="" <c:if test="${empty param.auditStatusId}">checked="checked"</c:if>>全部
                            <input type="radio" name="auditStatusId" value="Passed" <c:if test="${param.auditStatusId eq 'Passed'}">checked="checked"</c:if>>提交已审核
                            <input type="radio" name="auditStatusId" value="Passing" <c:if test="${param.auditStatusId eq 'Passing'}">checked="checked"</c:if>>提交未审核
                        </div>
                        <div class="searchDiv">
                            <input type="button" value="查&#12288;询" class="search" onclick="search()">
                            <input type="button" class="search" value="导出Excel" onclick="exportExcel()">
                        </div>
                    </td>
                </tr>
            </table>

        <table class="xllist" style="width:100%;">
            <tr>
                <th>序号</th>
                <th>年份</th>
                <th>学员姓名</th>
                <th>单位</th>
                <th>部门/科室</th>
                <th>学分类别</th>
                <th>项目大类</th>
                <th>评分项</th>
                <th>完成情况</th>
                <th>单项最终分值</th>
                <th>年度总分</th>
            </tr>
            <c:set var="count1" value="0"/>
            <c:set var="count2" value="0"/>
            <c:forEach items="${scoreMains}" var="score" varStatus="s">
                <c:if test="${score.firstScoreTypeId eq '01'}"><c:set var="count1" value="${count1+score.myScore}"/></c:if>
                <c:if test="${score.firstScoreTypeId eq '02'}"><c:set var="count2" value="${count2+score.myScore}"/></c:if>
                <tr>
                    <td>${s.count}</td>
                    <td>${score.sessionNumber}</td>
                    <td>${score.userName}</td>
                    <td>${score.orgName}</td>
                    <td>${score.userDeptName}</td>
                    <td>${score.firstScoreTypeName}</td>
                    <td>${score.firstProjTypeName}</td>
                    <td>${score.firstScoreItemName}</td>
                    <td>${score.firstExecutionName}</td>
                    <td>${score.myScore}</td>
                    <th>${score.yearScore}</th>
                </tr>
            </c:forEach>
            <c:if test="${empty scoreMains}">
                <tr>
                    <td colspan="11">无数据！</td>
                </tr>
            </c:if>
        </table>
        <div style="margin:8px 0px -10px 20px;">合计
            <span style="padding-left:20px;"></span>Ⅰ类学分：<fmt:formatNumber type="number" value="${count1}" maxFractionDigits="1"/>
            <span style="padding-left:20px;"></span>Ⅱ类学分：<fmt:formatNumber type="number" value="${count2}" maxFractionDigits="1"/>
        </div>
        <div style="padding-bottom: 15px">
            <c:set var="pageView" value="${pdfn:getPageView(scoreMains)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
            <table class="xllist" >
            <tr>
                <th>年度全院总学分统计情况</th>
            </tr>
            <tr>
                <td style="text-align: left">&#12288;年份：
                    <select name="year" class="xlselect" onchange="search()">
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumScoreYearList}" var="dict">
                            <option value="${dict.dictName}"
                            <c:if test="${dict.dictName eq param.year}">selected</c:if>
                            >${dict.dictName}</option>
                        </c:forEach>
                    </select>
                        &#12288;&#12288;
                学分类别：<select name="typeId" class="xlselect" onchange="search()">
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                            <option value="${dict.dictId}" <c:if test="${dict.dictId eq param.typeId}">selected</c:if>>${dict.dictName}</option>
                        </c:forEach>
                          </select>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="doctorNum" style="height: 550px;width:100%;">

                    </div>
                </td>
            </tr>
        </table>
    </form>
    </div>
</div>
</body>
</html>