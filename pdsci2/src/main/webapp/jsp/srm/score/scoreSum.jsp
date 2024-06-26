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
    <script type="text/javascript">
        function toPage(page) {
            $("#currentPage").val(page);
            search();
        }
        function search() {
            var currentPage = $("#currentPage").val();
            var url = "<s:url value='/srm/ach/score/scoreSumList'/>";
            $("#searchForm").attr("action", url);
            $("#searchForm").submit();
        }

        function scoreItemList(flow) {
            var typeId = $("select[name='typeId']").val();
            var year = $("#year").val();
            var url = "<s:url value='/srm/ach/score/scoreItemList'/>?isPublish=${isPublish}&flow=" + flow + "&typeId=" + typeId+ "&&startYear=${startYear}&endYear=${endYear}";
            jboxOpen(url, '积分项明细', 600, 500, true);
        }

        function fixedScore(isPublish){
            var tip="";
            if(isPublish && isPublish=='Y'){
                tip="公示";
            }else{
                tip="归档";
            }
            var url = "<s:url value='/srm/ach/score/fixedScoreByYear?isPublish='/>" + isPublish;
            jboxOpen(url,tip+"积分",300,200,true);

        }

    </script>
    <style type="text/css">
        a:link {
            color: #0000ff;
        }

        a:hover {
            color: #FF6615;
        }
    </style>
</head>
<body>
<div class="mainright">
    <br/>
    <form id="searchForm" action="" method="post"/>
    <div class="searchDiv">
        &#12288;积分汇总：
        <select name="typeId" id="typeId" class="xlselect" onchange="search();">
            <option value="forDept" <c:if test="${param.typeId eq 'forDept'}">selected="selected"</c:if>>按科室</option>
            <option value="forAuthor" <c:if test="${param.typeId eq 'forAuthor'}">selected="selected"</c:if>>按个人</option>
        </select>
        </div>
    <div class="searchDiv">
    &#12288;年&#12288;&#12288;度：
        <input class="xltext ctime" style="width:80px;" type="text" name="startYear"
               value="${param.startYear }" onClick="WdatePicker({dateFmt:'yyyy'})"
               readonly="readonly"/>--&#12288;
        <input class="xltext ctime" style="width:80px;" type="text" name="endYear"
               value="${param.endYear }" onClick="WdatePicker({dateFmt:'yyyy'})"
               readonly="readonly"/>
        </div>
    <c:if test="${param.typeId eq 'forAuthor'}">
    <div class="searchDiv">
        &#12288;姓&#12288;&#12288;名：<input class="xltext" name="userName" type="text" value="${param.userName}"/>
        </div>
    </c:if>
<c:if test="${isPublish ne 'isPublish'}">
    <div style="float: left">
    <input type="radio" name="isPublish" <c:if test="${param.isPublish eq 'all' }">checked="checked"</c:if> id="all" value="all" /><label for="all">全部</label>&#12288;
    <input type="radio" name="isPublish" <c:if test="${param.isPublish eq 'publish' }">checked="checked"</c:if> id="publish" value="publish" /><label for="publish">公示</label>&#12288;
    <input type="radio" name="isPublish" <c:if test="${param.isPublish eq 'fixedFlag' }">checked="checked"</c:if> id="fixedFlag" value="fixedFlag" /><label for="fixedFlag">归档</label>&#12288;
        </div>
   </c:if>
    <div class="searchDiv">
        <input type="button" class="search" onclick="search();" value="查&#12288;询">
    <c:if test="${isPublish ne 'isPublish'}">
        <input type="button" class="search" onclick="fixedScore('');" value="归&#12288;档">
        <input type="button" class="search" onclick="fixedScore('Y');" value="公&#12288;示">
    </c:if>
        </div>
        <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
    <c:if test="${isPublish eq 'isPublish'}">
        <input type="hidden" name="isPublish" value="${isPublish}">
    </c:if>
    </form><br/>
    <div class="content">
        <table class="xllist" id="deptData">

            <tr>
                <th width="10%">名次</th>
                <th width="30%">${titleName}</th>
                <th width="30%">科研积分</th>
            </tr>
            <c:forEach items="${scoreSumList}" var="score" varStatus="num">
                <tr>
                    <td>${score.RANKING_NUM}</td>
                    <c:if test="${titleName eq '科室'}">
                        <td>${score.DEPT_NAME}</td>
                        <td><a href="javascript:void(0)"
                               onclick="scoreItemList('${score.DEPT_FLOW}')">${score.ACH_SCORE_DEPT}</a></td>
                    </c:if>
                    <c:if test="${titleName eq '个人'}">
                        <td>${score.USER_NAME}</td>
                        <td><a href="javascript:void(0)"
                               onclick="scoreItemList('${score.USER_FLOW}')">${score.ACH_SCORE}</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty scoreSumList}">
                <tr>
                    <td colspan="3">暂无积分信息</td>
                </tr>
            </c:if>
        </table>
    </div>
    <p>
        <c:if test="${not empty scoreSumList}">

            <c:set var="pageView" value="${pdfn:getPageView2(scoreSumList, 10)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </c:if>
    </p>
</div>
</body>
</html>
