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
            var url = "<s:url value='/res/score/importScores'/>";
            jboxOpen(url, "分数导入", 700, 250);
        }
        function changeScore(obj){
            if(!$("#scoreForm").validationEngine("validate")){
                return;
            }
            var station = $(obj).attr("station");
            var scoreFlow = $(obj).attr("flow");
            var score = $(obj).val();
            jboxPost("<s:url value='/res/score/changeOsceScore'/>","station="+station+"&score="+score+"&scoreFlow="+scoreFlow,
            function(){
                search();
            },null,false);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class=" clearfix">
            <div class="queryDiv">
                <form id="searchForm" action="<s:url value='/res/score/osceScoreList'/>" method="post">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <div class="inputDiv">
                        姓&#12288;&#12288;名：
                        <input type="text" name="userName" class="qtext" value="${param.userName}"/>
                    </div>
                    <div class="inputDiv" <c:if test="${role eq 'doctor'}">style="display: none"</c:if>>
                        培训专业：
                        <select name="trainingSpeId" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="num">
                                <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">年&#12288;&#12288;级：</label>
                        <select name="sessionNumber" class="qselect" >
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
                                <option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        考核年份：
                        <input class="qtext" type="text" name="scoreYear" value="${param.scoreYear}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
                    </div>
                    <div class="inputDiv">
                        证&ensp;件&ensp;号：
                        <input class="qtext" type="text" name="idNo" value="${param.idNo}">
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
                    <th>姓名</th>
                    <th>身份证</th>
                    <th>考核年份</th>
                    <th>年级</th>
                    <th>培训专业</th>
                    <th>考核名称</th>
                    <th>第一站</th>
                    <th>第二站</th>
                    <th>第三站</th>
                    <th>第四站</th>
                    <th>第五站</th>
                    <th>第六站</th>
                    <th>第七站</th>
                    <th>第八站</th>
                    <th>第九站</th>
                    <th>总分</th>
                    <th>考试结果</th>
                </tr>
                <c:forEach items="${resultMapList}" var="result" varStatus="s">
                    <tr>
                        <td>${result.USER_NAME}</td>
                        <td>${result.ID_NO}</td>
                        <td>${result.SCORE_TYPE_ID}</td>
                        <td>${result.SESSION_NUMBER}</td>
                        <td>${result.TRAINING_SPE_NAME}</td>
                        <td>${result.SCORE_TYPE_NAME}</td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station1}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station1" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station2}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station2" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station3}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station3" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station4}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station4" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station5}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station5" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station6}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station6" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station7}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station7" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station8}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station8" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].station9}" class="inputText validate[custom[number,min[0]]" style="width: 25px;" station="station9" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                        <td>${detailMap[result.SCORE_FLOW].total}</td>
                        <td><input type="text" value="${detailMap[result.SCORE_FLOW].result}" class="inputText" style="width: 100px;" station="result" flow="${result.SCORE_FLOW}" onchange="changeScore(this)"></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty resultMapList}">
                    <tr>
                        <td colspan="20" style="text-align: center">无记录！</td>
                    </tr>
                </c:if>
            </table>
            </form>
            <div class="page" style="padding-right: 40px;">
                <c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </div>

        </div>
    </div>
</div>
</body>
</html>
