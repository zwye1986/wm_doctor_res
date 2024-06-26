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
    <script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
    <script type="text/javascript">

        function auditInfo(userFlow,viewFlag){
            var url = "<s:url value='/gyxjgl/user/employTutorTable?roleFlag=${roleFlag}&userFlow='/>"+userFlow+"&viewFlag="+viewFlag;
            jboxOpen(url, viewFlag=='view'?'查看':'审核',950,600);
        }

        function search(){
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/user/employTutorTableList"/>" method="post">
            <table class="basic" style="width:100%;min-width: 1080px;margin: 10px 0px 5px -20px;border: none">
                <tr>
                    <td style="border: none;">
                        <input id="currentPage" type="hidden" name="currentPage"/>
                        <input type="hidden" name="roleFlag" value="${roleFlag}"/>
                        <div>
                            <div>
                                &#12288;学&#12288;&#12288;号：<input class="year"  type="text" style="width: 137px;" name="studentId" value="${param.studentId}" >
                                &#12288;姓&#12288;&#12288;名：<input type="text" style="width: 137px;" name="userName" value="${param.userName }">
                                <input type="button" class="search" onclick="search();" value="查&#12288;询">
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>

        <div class="resultDiv">
            <table class="basic" width="100%">
                <tr style="font-weight: bold;">
                    <td style="text-align: center;padding: 0px;">入学年级</td>
                    <td style="text-align: center;padding: 0px;">学号</td>
                    <td style="text-align: center;padding: 0px;">姓名</td>
                    <td style="text-align: center;padding: 0px;">性别</td>
                    <td style="text-align: center;padding: 0px;">专业名称</td>
                    <td style="text-align: center;padding: 0px;">导师</td>
                    <c:if test="${flag eq 'edit' or from ne 'global'}">
                        <td style="text-align: center;padding: 0px;">操作</td>
                    </c:if>
                </tr>
                <c:forEach items="${dataList}" var="data">
                    <tr>
                        <td style="text-align: center;padding: 0px;">${data.PERIOD }</td>
                        <td style="text-align: center;padding: 0px;">${data.SID }</td>
                        <td style="text-align: center;padding: 0px;">${data.USER_NAME }</td>
                        <td style="text-align: center;padding: 0px;">${data.SEX_NAME }</td>
                        <td style="text-align: center;padding: 0px;">${data.MAJOR_NAME }</td>
                        <td style="text-align: center;padding: 0px;">${data.TUTOR_NAME }</td>
                        <td style="text-align: center;padding: 0px;">
                            <a onclick="auditInfo('${data.USER_FLOW}');" style="cursor: pointer;color: blue;">查看</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty dataList}">
                    <tr>
                        <td colspan="99" style="text-align: center;">无记录！</td>
                    </tr>
                </c:if>
            </table>
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="detail" style="background: white;">
</div>
</body>
</html>