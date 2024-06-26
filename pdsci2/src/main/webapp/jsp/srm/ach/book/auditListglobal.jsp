<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
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
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;
        function edit(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/edit/${scope}'/>?bookFlow=" + bookFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看著作信息", width, height);
        }

        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function audit(bookFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/book/audit?bookFlow='/>" + bookFlow, "审核", 700, 400);
        }
        function loadApplyOrg() {
            //清空
            $("#trainOrg").val("");
            $("#orgFlow").val("");
            initApplyOrg();
        }

        function initApplyOrg() {
            var chargeOrgFlow = $('#chargeOrg').val();
            if (!chargeOrgFlow) {
                return;
            }
            var url = "<s:url value='/sys/org/loadApplyOrg'/>?orgFlow=" + chargeOrgFlow;
            jboxStartLoading();
            jboxGet(url, null, function (data) {
                var datas = [];
                $.each(data, function (i, n) {
                    var d = {};
                    d.id = n.orgFlow;
                    d.text = n.orgName;
                    datas.push(d);
                });
                var itemSelectFuntion = function () {
                    $("#orgFlow").val(this.id);
//            searchInfo();
                };
                $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
            }, null, false);
        }
        $(document).ready(function () {
            initApplyOrg();
        });
        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/book/exportBookExcel/global'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/book/auditList/global"/>" method="post">
                <p>
                <div class="searchDiv">
                    &#12288;出版日期：
                    <input class="xltext ctime" style="width: 158px;" type="text" name="publishDate"
                           value="${param.publishDate}" onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"/>
                </div>
                <%-- &#12288;项目来源：
                  <select name="projSourceId" class="xlselect">
                  <option value="">请选择</option>
                  <c:forEach items="${dictTypeEnumProjSourceList }" var="dict">
                  <option value="${dict.dictId }" <c:if test="${dict.dictId eq param.projSourceId}">selected="selected"</c:if> >${dict.dictName }</option>
                  </c:forEach>
                </select> --%>
                <div class="searchDiv">
                    &#12288;著作类别：
                    <select name="typeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumAchBookTypeList }" var="dict">
                            <option value="${dict.dictId }"
                                    <c:if test="${dict.dictId eq param.typeId}">selected="selected"</c:if> >${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;主管部门：
                    <select id="chargeOrg" name="chargeOrgFlow" onchange="loadApplyOrg();" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach var="chargeOrg" items="${firstGradeOrgList}">
                            <option
                                    <c:if test="${param.chargeOrgFlow eq  chargeOrg.orgFlow }">selected="selected"</c:if>
                                    value="${chargeOrg.orgFlow}">${chargeOrg.orgName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;申报单位：
                    <input id="trainOrg" class="xltext" name="orgName" type="text"
                           value="${param.orgName}" autocomplete="off"/>
                    <input id="orgFlow" name="orgFlow" class="input" value="${param.orgFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    &#12288;学科门类：
                    <select name="categoryId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumSubjectTypeList }" var="dict">
                            <option
                                    <c:if test="${param.categoryId eq dict.dictId }">selected="selected"</c:if>
                                    value="${dict.dictId }">${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;著作名称：
                    <input type="text" name="bookName" class="xltext" value="${param.bookName }"/>
                </div>
                <div class="searchDiv">
                    &#12288;姓&#12288;&#12288;名：
                    <input type="text" name="authorName" class="xltext" value="${param.authorName}"/>
                </div>
                <div class="searchDiv">
                    <input type="hidden" id="currentPage" name="currentPage">
                    &#12288;<input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                </div>
                </p>
            </form>
        </div>

        <table class="xllist">
            <tr style="height: 20px">
                <th>著作名称</th>
                <th>所属单位</th>
                <th>参编作者</th>
                <th>出版日期</th>
                <th>著作类型</th>
                <th>出版单位</th>
                <th>出版地</th>
                <th>项目来源</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.bookName }</td>
                    <td>${book.orgBelongName}</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key == book.bookFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${book.publishDate}</td>
                    <td>${book.typeName}</td>
                    <td>${book.publishOrg }</td>
                    <td>${book.pubPlaceName }</td>
                    <td>${book.projSourceName }</td>
                    <td>
                        <a href="javascript:void(0)" onclick="edit('${book.bookFlow}');">[查看]</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView2(bookList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>

</body>
</html>