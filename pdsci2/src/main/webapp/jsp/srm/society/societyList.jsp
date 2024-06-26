<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function searchProj() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            searchProj();
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/learning/sociery/list"/>"
                  method="post">
                <div class="searchDiv">
                    科&#12288;&#12288;室：
                    <select name="deptFlow" class="xlselect">
                        <option value=""></option>
                        <c:forEach var="sysDept" items="${sysDeptList}">
                            <option value="${sysDept.deptFlow}"
                                    <c:if test="${param.deptFlow eq sysDept.deptFlow}">selected="selected"</c:if>>${sysDept.deptName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    学会名称： <input type="text" name="societyName" value="${param.societyName}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    职&#12288;&#12288;位：
                    <select name="societyPosition" class="xlselect">
                        <option value=""></option>
                        <option value="主委" <c:if test="${param.societyPosition eq '主委'}">selected="selected"</c:if>>主委
                        </option>
                        <option value="副主委" <c:if test="${param.societyPosition eq '副主委'}">selected="selected"</c:if>>
                            副主委
                        </option>
                        <option value="委员" <c:if test="${param.societyPosition eq '委员'}">selected="selected"</c:if>>委员
                        </option>
                    </select>
                </div>
                <div class="searchDiv">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                </div>
            </form>
        </div>
        <table class="xllist">
            <thead>
            <tr>
                <th width="5%">序号</th>
                <th width="10%">姓名</th>
                <th width="18%">科室</th>
                <th width="17%">学会名称</th>
                <th width="25%">具体专业委员会名称</th>
                <th width="10%">职位</th>
                <th width="15%">任职时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${learningSocietyList}" var="society" varStatus="societyList">
                <tr>
                    <td>${societyList.count }</td>
                    <td>${society.userName }</td>
                    <td>${society.deptName}</td>
                    <td>${society.societyName}</td>
                    <td>${society.societyDetailed }</td>
                    <td>${society.societyPosition }</td>
                    <td>${society.startYear}~${society.endYear}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(learningSocietyList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>