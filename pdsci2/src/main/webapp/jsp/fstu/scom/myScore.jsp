<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        var height=(window.screen.height)*0.7;
        var width=(window.screen.width)*0.7;

        function add(recordFlow){
            var title = recordFlow == ""?"新增":"编辑";
            var url = "<s:url value='/fstu/score/addScore?recordFlow='/>"+recordFlow;
            jboxOpen(url, title,860,500);
        }
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function del(recordFlow){
            jboxConfirm("确认删除此条记录?", function(){
                var url = "<s:url value='/fstu/score/delScore?recordFlow='/>"+recordFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, false);
            });
        }
        function submit(recordFlow){
            jboxConfirm("确认提交?", function(){
                var url = "<s:url value='/fstu/score/submitScore?recordFlow='/>"+recordFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, false);
            });
        }
        function query(recordFlow){
            var url = "<s:url value='/fstu/score/queryScore?recordFlow='/>"+recordFlow;
            jboxOpen(url, "查看",860,500);
        }
        function linkage(dictId){
            $('#firstProjTypeId').val("");//清空上次展现数据
            $('#firstProjTypeId option').hide();
            $('#firstProjTypeId option[value=""]').show();
            $('#firstProjTypeId'+' option.${dictTypeEnumAcaScoreType.id}\\.'+dictId).show();
        }
        $(function(){
//            linkage($("[name='firstScoreTypeId']").val());
            $('#firstProjTypeId option').hide();
            $('#firstProjTypeId option[value=""]').show();
            $('#firstProjTypeId'+' option.${dictTypeEnumAcaScoreType.id}\\.'+$("[name='firstScoreTypeId']").val()).show();
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/fstu/score/myScore/${roleFlag}'/>" method="post">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td>
                        <div class="searchDiv">
                            &#12288;年&#12288;&#12288;份：
                            <select name="sessionNumber" class="xlselect">
                                <option value="all">请选择</option>
                                <c:forEach items="${dictTypeEnumScoreYearList}" var="dict">
                                    <option value="${dict.dictName}"
                                            <c:if test="${empty param.sessionNumber}"> <c:if test="${dict.dictName eq currentYear}">selected</c:if> </c:if>
                                            <c:if test="${not empty param.sessionNumber}"> <c:if test="${dict.dictName eq param.sessionNumber}">selected</c:if> </c:if>
                                    >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="searchDiv">
                            &#12288;学分类别：
                            <select name="firstScoreTypeId" class="xlselect" onchange="linkage(this.value)">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq param.firstScoreTypeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="searchDiv">
                            &#12288;项目大类：
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
                            &#12288;&#12288;评分项：
                            <select name="firstScoreItemId" class="xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAssessItemList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq param.firstScoreItemId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="searchDiv">
                            &#12288;完成情况：
                            <select name="firstExecutionId" class="xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAccomplishResultList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq param.firstExecutionId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="searchDiv">
                            &#12288;分&#12288;&#12288;值：
                            <input type="text" class="xltext" name="myScore" value="${param.myScore}">
                        </div>
                        <div class="searchDiv">
                            <input type="hidden" name="currentPage" id="currentPage">
                            &#12288;<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                            <input type="button" class="search" value="新&#12288;增" onclick="add('')"/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>序号</th>
                <th>年份</th>
                <th>学分类别</th>
                <th>项目大类</th>
                <th style="width:16%;">名称</th>
                <th style="width:16%;">编号</th>
                <th>评分项</th>
                <th>完成情况</th>
                <th>分值</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${scoreList}" var="score" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${score.sessionNumber}</td>
                    <td>${score.firstScoreTypeName}</td>
                    <td>${score.firstProjTypeName}</td>
                    <td>${score.scoreName}</td>
                    <td>${score.scoreNumber}</td>
                    <td>${score.firstScoreItemName}</td>
                    <td>${score.firstExecutionName}</td>
                    <td>${score.myScore}</td>
                    <td>
                        <c:if test="${score.headerAuditStatusId eq 'Add' || score.headerAuditStatusId eq 'Backed'}"><%-- 待提交或退回 --%>
                            <a onclick="add('${score.recordFlow}');" style="cursor: pointer;color: blue;">编辑</a>
                            <a onclick="del('${score.recordFlow}');" style="cursor: pointer;color: blue;">删除</a>
                            <a onclick="submit('${score.recordFlow}');" style="cursor: pointer;color: blue;">提交</a>
                        </c:if>
                        <a onclick="query('${score.recordFlow}');" style="cursor: pointer;color: blue;">查看</a>
                    </td>
                </tr>
            </c:forEach>
                <tr>
                    <td colspan="2">总分</td>
                    <td colspan="6"></td>
                    <td>${sum}</td>
                    <td></td>
                </tr>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(scoreList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>