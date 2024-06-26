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
        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function edit(copyrightFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/copyright/edit'/>?copyrightFlow=" + copyrightFlow + "&editFlag=${GlobalConstant.FLAG_N}", "查看著作权信息", width, height);
        }

        function loadApplyOrg() {
            //清空
            var org = $('#org');
            org.html('<option value="">请选择</option>');
            var chargeOrgFlow = $('#chargeOrg').val();
            if (!chargeOrgFlow) {
                return;
            }
            url = "<s:url value='/sys/org/loadApplyOrg'/>?orgFlow=" + chargeOrgFlow
            jboxGet(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    org.append('<option value="' + n.orgFlow + '">' + n.orgName + '</option>');
                });

            }, null, false);
        }

        function toPage(page) {
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function expert() {
            var url = "<s:url value='/srm/ach/copyright/exportCopyrightExcel/charge'/>";
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
        }
        $(function(){
            initOrg();
        });

        function initOrg() {
            var datas = [];
            <c:forEach var="org" items="${firstGradeOrgList}">
            var d = {};

            d.id = "${org.orgFlow}";
            d.text = "${org.orgName}";
            datas.push(d);
            </c:forEach>
            var itemSelectFuntion = function () {
                $("#orgFlow").val(this.id);
                searchInfo();
            };
            $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ach/copyright/auditList/charge"/>" method="post">
                <p>
                <div class="searchDiv">
                    &#12288;出版日期：
                    <input class="xltext ctime" type="text" name="publishDate"
                           value="${param.publishDate }" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
                           readonly="readonly"/>
                </div>
                <div class="searchDiv">
                    &#12288;&#12288;登记号：
                    <input type="text" name="registerCode" value="${param.registerCode }" class="xltext"/>
                </div>
                <div class="searchDiv">
                    著作权类别：
                    <select name="copyrightTypeId" class="xlselect" >
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumCopyrightTypeList }" var="dict">
                            <option value="${dict.dictId}"
                                    <c:if test="${dict.dictId eq param.copyrightTypeId}">selected="selected"</c:if>  >${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">&#12288;申报单位：
                    <input id="trainOrg" class="xltext" name="orgName" type="text"
                           value="${param.orgName}" autocomplete="off"/>
                    <input id="orgFlow" name="orgFlow" class="input" value="${param.orgFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    &#12288;学科门类：
                    <select name="subjectTypeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumSubjectTypeList }" var="dict">
                            <option
                                    <c:if test="${param.subjectTypeId eq dict.dictId }">selected="selected"</c:if>
                                    value="${dict.dictId }">${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    著作权名称：
                    <input type="text" name="copyrightName" class="xltext" value="${param.copyrightName }"/>
                </div>
                <div class="searchDiv">
                    &#12288;作&#12288;&#12288;者：
                    <input type="text" name="authorName" class="xltext" value="${param.authorName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;<input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <%--<input class="search" type="button" value="导&#12288;出" onclick="expert()"/>--%>
                </div>
                </p>
            </form>
        </div>

        <table class="xllist">
            <tr style="height: 20px">
                <th>登记号</th>
                <th>申报单位</th>
                <th>著作权名称</th>
                <th>著作权类型</th>
                <th>所有作者</th>
                <th>出版日期</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${copyrightList}" var="copyright">
                <tr>
                    <td>${copyright.registerCode }</td>
                    <td>${copyright.applyOrgName }</td>
                    <td>${copyright.copyrightName }</td>
                    <td>${copyright.copyrightTypeName }</td>
                    <td>
                        <c:forEach items="${allAuthorMap}" var="entry">
                            <c:if test="${entry.key == copyright.copyrightFlow}">
                                <c:forEach items="${entry.value}" var="author">
                                    ${author.authorName}&nbsp;
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${pdfn:transDate(copyright.publishDate)}</td>
                    <td>
                        <a href="javascript:void(0)" onclick="edit('${copyright.copyrightFlow}');">[查看]</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
          <p>
             <input type="hidden" id="currentPage" name="currentPage">
             <c:set var="pageView" value="${pdfn:getPageView2(copyrightList, 10)}" scope="request"></c:set>
             <pd:pagination toPage="toPage"/>
         </p>
    </div>
</div>
</body>
</html>