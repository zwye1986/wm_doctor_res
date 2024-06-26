<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function saveFundInfo() {
            if (false == $("#fundForm").validationEngine("validate")) {
                return false;
            }
            var url = "<s:url value="/srm/regis/proj/saveRealityAmount"/>";
            jboxStartLoading();
            jboxSubmit($('#fundForm'), url, function (resp) {
                        window.parent.frames['mainIframe'].location.reload(true);
                        jboxClose();
                    },
                    function (resp) {
                        alert("error");
                    });
        }
        function getScoreName(obj) {
            $(obj).next().val($(obj).find("option:selected").text());
        }
    </script>
</head>
<body>
<div id="fundDiv">
    <form id="fundForm">
        <div class="button" style="width: 100%;text-align: left; margin-top: 20px; " align="center">
            <input type="hidden" name="fundFlow" value="${fundInfo.fundFlow}"/>
            到账经费：<input type="text" class="validate[required,custom[number],max[100000000]] xltext"
                        value="${fundInfo.realityAmount}" name="realityAmount"/>（万元）<br/><br/>
            到账时间：<input type="text" name="fundIncomeTime" value="${fundInfo.fundIncomeTime}"
                        readonly="readonly" class="validate[required] ctime"
                        onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><br/><br/>
            积分项&#12288;：
            <select name="scoreFlow" id="scoreFlow" class="xlselect" onchange="getScoreName(this);">
                <option value="">请选择</option>
                <c:forEach items="${srmAchScoreList}" var="score">
                <option value="${score.scoreFlow}" <c:if test="${score.scoreFlow eq author.scoreFlow}">selected="selected"</c:if>>${score.scoreName}</option>
                </c:forEach>
            </select><br/><br/>
            <input type="hidden" name="scoreName" value="${author.scoreName}"/>
            <input type="hidden" name="projFlow" value="${pubProj.projFlow}"/>
            <input type="hidden" name="userFlow" value="${pubProj.applyUserFlow}"/>
            <input type="hidden" name="authorName" value="${pubProj.applyUserName}"/>
            <input type="hidden" name="deptFlow" value="${pubProj.applyDeptFlow}"/>
            <input type="hidden" name="deptName" value="${pubProj.applyDeptName}"/>
            <input type="hidden" name="branchId" value="${pubProj.branchId}"/>
            <input type="hidden" name="branchName" value="${pubProj.branchName}"/>
            <p style="text-align: center;width: 100%">
                <input type="button" onclick="saveFundInfo();" class="search" value="保&#12288;存"/>
            </p>
        </div>
    </form>
</div>
</body>
</html>