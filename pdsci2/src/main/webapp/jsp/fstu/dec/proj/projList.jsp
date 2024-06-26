<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>

    <script type="text/javascript">
        function search() {
            $("#searchForm").submit();
        }

        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            searchProj();
        }
        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;

        function edit(flow, viewFlag) {
            jboxOpen("<s:url value='/fstu/dec/add/${roleFlag}'/>?flow=" + flow + "&viewFlag=" + viewFlag, "详情", 850, height);
        }
        function delAidProj(flow) {
            jboxConfirm("确认删除？", function () {
                url = "<s:url value='/fstu/dec/delPro'/>?projFlow=" + flow + "&recordStatus=${GlobalConstant.RECORD_STATUS_N}";
                jboxPost(url, null, function (obj) {
                    if (obj == "${GlobalConstant.DELETE_SUCCESSED}") {
                        search();
                    }
                });
            });
        }
    </script>

</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/fstu/dec/projList/${roleFlag}'/>" method="post">
                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <table class="basic" style="width: 100%;">
                    <tr>
                        <td>
                            <div class="searchDiv">
                                年&#12288;&#12288;份：
                                <input type="text" class="ctime" name="projYear" readonly="readonly"
                                       onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
                            </div>
                            <div class="searchDiv">
                                项目级别：
                                <select name="projLevelName" class="xlselect">
                                    <option/>
                                    <option <c:if test="${param.projLevelName eq '国家'}">selected</c:if>>国家</option>
                                    <option <c:if test="${param.projLevelName eq '省级'}">selected</c:if>>省级</option>
                                    <option <c:if test="${param.projLevelName eq '其他'}">selected</c:if>>其他</option>
                                </select>
                            </div>
                            <div class="searchDiv">
                                项目名称：
                                <input type="text" name="projName" class="xltext" value="${param.projName}"/>
                            </div>
                            <div class="searchDiv">
                                项目负责人：
                                <input type="text" name="applyUserName" class="xltext" value="${param.applyUserName}"/>
                            </div>
                            <div class="searchDiv">
                                所属学科：
                                <select class="xlselect" name="projSubject">
                                    <option/>
                                    <c:forEach items="${dictTypeEnumFstuProjSubjectList}" var="dict">
                                        <option value="${dict.dictName}"
                                                <c:if test="${dict.dictName eq param.projSubject}">selected</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                                <div class="searchDiv">
                                    &nbsp;<input type="button" class="search" onclick="search();" value="查&#12288;询"/>
                            <c:if test="${roleFlag eq 'admin' }">
                                    <input type="button" class="search" onclick="edit('','');" value="新&#12288;增"/>&nbsp;
                            </c:if>
                                </div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <table class="xllist">
            <thead>
            <tr>
                <th>年份</th>
                <th>项目级别</th>
                <th>项目名称</th>
                <th>主办单位</th>
                <th>项目负责人</th>
                <th>起止时间</th>
                <th>举办地点</th>
                <th>申请学分</th>
                <th>项目编号</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="proj" items="${projList}">
                <tr>
                    <td>${proj.projYear}</td>
                    <td>${proj.projLevelName}</td>
                    <td>${proj.projName}</td>
                    <td>${proj.applyOrgName}</td>
                    <td>${proj.applyUserName}</td>
                    <td>${proj.projStartTime}&nbsp;~&nbsp;${proj.projEndTime}</td>
                    <td>${proj.projHoldAddress}</td>
                    <td>${proj.applyScore}</td>
                    <td>${proj.projNo}</td>
                    <td>
                        <a style="cursor:pointer; color: blue;" onclick="edit('${proj.projFlow}','view')">查看</a>
                        <c:if test="${roleFlag eq 'admin'}">
                            |<a style="cursor:pointer; color: blue;" onclick="edit('${proj.projFlow}')">编辑</a> |
                            <a style="cursor:pointer; color: blue;" onclick="delAidProj('${proj.projFlow}')">删除</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty projList}">
                <tr>
                    <td colspan="99">无记录</td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>