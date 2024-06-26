<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
        selTag("completeQualifiedQuery");
    });

    function selTag(tabTag) {
        if ("completeQualifiedQuery" == tabTag) {
            $("#completeQualifiedQuery").removeClass("tab");
            $("#completeQualifiedQuery").addClass("tab_select");
            $("#theoryScoreQuery").addClass("tab");
            $("#theoryScoreQuery").removeClass("tab_select");
            $("#skillScoreQuery").addClass("tab");
            $("#skillScoreQuery").removeClass("tab_select");
            $("#achievementPublicScore").addClass("tab");
            $("#achievementPublicScore").removeClass("tab_select");
            jboxLoad("mainDiv", "<s:url value='/jsres/doctorTheoryScore/theoryPassedList'/>?roleFlag=${param.roleFlag}&catSpeId=${param.catSpeId}", true);
        }
        if ("theoryScoreQuery" == tabTag) {
            $("#theoryScoreQuery").removeClass("tab");
            $("#theoryScoreQuery").addClass("tab_select");
            $("#completeQualifiedQuery").addClass("tab");
            $("#completeQualifiedQuery").removeClass("tab_select");
            $("#skillScoreQuery").addClass("tab");
            $("#skillScoreQuery").removeClass("tab_select");
            $("#achievementPublicScore").addClass("tab");
            $("#achievementPublicScore").removeClass("tab_select");
            jboxLoad("mainDiv", "<s:url value='/jsres/doctorTheoryScore/doctorTheoryList'/>?roleFlag=${param.roleFlag}&catSpeId=${param.catSpeId}", true);
        }
        if ("skillScoreQuery" == tabTag) {
            $("#skillScoreQuery").removeClass("tab");
            $("#skillScoreQuery").addClass("tab_select");
            $("#completeQualifiedQuery").addClass("tab");
            $("#completeQualifiedQuery").removeClass("tab_select");
            $("#theoryScoreQuery").addClass("tab");
            $("#theoryScoreQuery").removeClass("tab_select");
            $("#achievementPublicScore").addClass("tab");
            $("#achievementPublicScore").removeClass("tab_select");
            jboxLoad("mainDiv", "<s:url value='/jsres/doctorTheoryScore/doctorSkillList'/>?roleFlag=${param.roleFlag}&catSpeId=${param.catSpeId}", true);
        }
        if ("achievementPublicScore" == tabTag) {
            $("#achievementPublicScore").removeClass("tab");
            $("#achievementPublicScore").addClass("tab_select");
            $("#completeQualifiedQuery").addClass("tab");
            $("#completeQualifiedQuery").removeClass("tab_select");
            $("#theoryScoreQuery").addClass("tab");
            $("#theoryScoreQuery").removeClass("tab_select");
            $("#skillScoreQuery").addClass("tab");
            $("#skillScoreQuery").removeClass("tab_select");
            jboxLoad("mainDiv", "<s:url value='/jsres/doctorTheoryScore/doctorPublicList'/>?roleFlag=${param.roleFlag}&catSpeId=${param.catSpeId}", true);
        }
    }
</script>

<div class="main_hd">
    <h2>成绩管理</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="completeQualifiedQuery" class="tab" onclick="selTag('completeQualifiedQuery');"><a>结业合格查询</a></li>
            <li id="theoryScoreQuery" class="tab" onclick="selTag('theoryScoreQuery');"><a>理论成绩查询</a></li>
            <li id="skillScoreQuery" class="tab" onclick="selTag('skillScoreQuery');"><a>技能成绩查询</a></li>
            <c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL || param.roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
                <li id="achievementPublicScore" class="tab" onclick="selTag('achievementPublicScore');"><a>公共科目成绩</a></li>
            </c:if>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>