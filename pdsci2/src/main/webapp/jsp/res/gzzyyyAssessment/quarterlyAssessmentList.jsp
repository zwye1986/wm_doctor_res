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
    <style type="text/css">
    </style>
    <script type="text/javascript">
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function search(){
            $("#searchForm").submit();
        }
        function importScores(){
            var url = "<s:url value='/res/gzzyyyAssessment/importQuarterlyAssessmentPage'/>";
            jboxOpen(url, "分数导入", 700, 250);
        }
        function del(flow){
            var url = "<s:url value='/res/gzzyyyAssessment/delQuarterlyAssessment'/>?recordFlow="+flow;
            jboxConfirm("确认删除？",function(){
                jboxPost(url,null,function(resp){
                    if(resp==1) jboxTip("操作成功")
                    search();
                },null,false);
            })
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class=" clearfix">
            <div class="queryDiv">
                <form id="searchForm" action="<s:url value='/res/gzzyyyAssessment/quarterlyAssessmentList'/>" method="post">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <div class="inputDiv">
                        姓&#12288;&#12288;名：
                        <input type="text" name="doctorName" class="qtext" value="${param.doctorName}"/>
                    </div>
                    <div class="inputDiv">
                        用&ensp;户&ensp;名：
                        <input type="text" name="userCode" class="qtext" value="${param.userCode}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">年&#12288;&#12288;度：</label>
                        <select name="year" class="qselect" >
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
                                <option value="${dict.dictName}" ${param.year eq dict.dictName?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="lastDiv" style="min-width: 182px;max-width: 182px;">
                        &#12288;<input class="search" type="button" value="查&#12288;询" onclick="search();"/>
                        <input class="search" type="button" value="导&#12288;入" onclick="importScores();"/>
                    </div>
                </form>
            </div>
            <form id="scoreForm">
                <table class="xllist">
                    <tr>
                        <th rowspan="2">姓名</th>
                        <th rowspan="2">用户名</th>
                        <th rowspan="2">年度</th>
                        <th colspan="2" style="width: 16%">第一季度</th>
                        <th colspan="2" style="width: 16%">第二季度</th>
                        <th colspan="2" style="width: 16%">第三季度</th>
                        <th colspan="2" style="width: 16%">第四季度</th>
                        <th rowspan="2">操作</th>
                    </tr>
                    <tr>
                        <th>理论成绩</th>
                        <th>技能成绩</th>
                        <th>理论成绩</th>
                        <th>技能成绩</th>
                        <th>理论成绩</th>
                        <th>技能成绩</th>
                        <th>理论成绩</th>
                        <th>技能成绩</th>
                    </tr>
                    <c:forEach items="${quarterlyAssessmentList}" var="quarterlyAssessment" varStatus="s">
                        <tr>
                            <td>${quarterlyAssessment.doctorName}</td>
                            <td>${quarterlyAssessment.userCode}</td>
                            <td>${quarterlyAssessment.year}</td>
                            <td>${quarterlyAssessment.theory1}</td>
                            <td>${quarterlyAssessment.skill1}</td>
                            <td>${quarterlyAssessment.theory2}</td>
                            <td>${quarterlyAssessment.skill2}</td>
                            <td>${quarterlyAssessment.theory3}</td>
                            <td>${quarterlyAssessment.skill3}</td>
                            <td>${quarterlyAssessment.theory4}</td>
                            <td>${quarterlyAssessment.skill4}</td>
                            <td><a style="cursor: pointer;" onclick="del('${quarterlyAssessment.recordFlow}')">[删除]</a></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty quarterlyAssessmentList}">
                        <tr>
                            <td colspan="20" style="text-align: center">无记录！</td>
                        </tr>
                    </c:if>
                </table>
            </form>
            <div class="page" style="padding-right: 40px;">
                <c:set var="pageView" value="${pdfn:getPageView(quarterlyAssessmentList)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </div>

        </div>
    </div>
</div>
</body>
</html>
