<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <style type="text/css">
        #myForm div {
            margin-top: 5px;
        }
    </style>
    <script type="text/javascript">
        var height = (window.screen.height) * 0.5;
        var width = (window.screen.width) * 0.7;
        function toPage(page) {
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function add(recordFlow) {
            var title = recordFlow == "" ? "新增" : "编辑";
            var url = "<s:url value='/fstu/score/addConfig?recordFlow='/>" + recordFlow;
            jboxOpen(url, title, 700, height);
        }
        function del(recordFlow) {
            jboxConfirm("确认删除此条记录?", function () {
                var url = "<s:url value='/fstu/score/delConfig?recordFlow='/>" + recordFlow;
                jboxPost(url, null, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, false);
            });
        }
        function query(recordFlow) {
            var url = "<s:url value='/fstu/score/queryConfig?recordFlow='/>" + recordFlow;
            jboxOpen(url, "查看", 700, height);
        }
        function linkage(dictId) {
            $('#projTypeId').val("");//清空上次展现数据
            $('#projTypeId option').hide();
            $('#projTypeId option[value=""]').show();
            $('#projTypeId' + ' option.${dictTypeEnumAcaScoreType.id}\\.' + dictId).show();
        }
        $(function () {
            $('#projTypeId option').hide();
            $('#projTypeId option[value=""]').show();
            $('#projTypeId' + ' option.${dictTypeEnumAcaScoreType.id}\\.' + $("[name='scoreTypeId']").val()).show();
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/fstu/score/scoreConfig'/>" method="post">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td>
                        <div class="searchDiv">
                            <span style="padding-left:25px;"></span>学分类别：
                            <select name="scoreTypeId" class="xlselect" onchange="linkage(this.value)">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${dict.dictId eq param.scoreTypeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="searchDiv">
                            <span style="padding-left:25px;"></span>项目大类：
                            <select id="projTypeId" name="projTypeId" class="xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <c:set var="dictKey" value="dictTypeEnumAcaScoreType.${dict.dictId}List"/>
                                    <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                        <c:set var="valueKey" value="${dict.dictId},${scope.dictId}"></c:set>
                                        <option class="${scope.dictTypeId}" value="${valueKey}" <c:if test="${valueKey eq param.projTypeId}">selected</c:if>>${scope.dictName}</option>
                                    </c:forEach>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="searchDiv">
                            <span style="padding-left:25px;"></span>评分项：
                            <select name="scoreItemId" class="xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAssessItemList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${dict.dictId eq param.scoreItemId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="searchDiv">
                            <span style="padding-left:25px;"></span>完成情况：
                            <select name="executionId" class="xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAccomplishResultList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${dict.dictId eq param.executionId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="searchDiv">
                            <span style="padding-left:25px;"></span>
                            <input type="hidden" name="currentPage" id="currentPage">
                            <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                            <input type="button" class="search" value="新&#12288;增" onclick="add('')"/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>序号</th>
                <th>学分类别</th>
                <th>项目大类</th>
                <th>评分项</th>
                <th>完成情况</th>
                <th>分值</th>
                <th>最大分值</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${configList}" var="cfg" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${cfg.scoreTypeName}</td>
                    <td>${cfg.projTypeName}</td>
                    <td>${cfg.scoreItemName}</td>
                    <td>${cfg.executionName}</td>
                    <td>${cfg.score}</td>
                    <td>${cfg.maxScore}</td>
                    <td>
                            <%--<a onclick="add('${cfg.recordFlow}');" style="cursor: pointer;color: blue;">编辑</a>--%>
                        <a onclick="del('${cfg.recordFlow}');" style="cursor: pointer;color: blue;">删除</a>
                        <a onclick="query('${cfg.recordFlow}');" style="cursor: pointer;color: blue;">查看</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(configList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>