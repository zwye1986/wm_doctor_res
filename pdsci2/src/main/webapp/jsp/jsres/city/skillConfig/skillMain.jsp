<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
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
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select").click();
    });

    function heightFiexed() {
        //修正高度
        var toFixedTd = $(".toFiexdDept");
        $(".fixedBy").each(function (i) {
            $(toFixedTd[i]).height($(this).height());
        });
        var fixTrTd = $(".fixTrTd");
        var fixTrTh = $(".fixTrTh");
        var fixTd = $(".fix");
        $(fixTrTd).each(function (i) {
            var maxheight = -1;
            $(fixTrTd[i]).find(".by").each(function () {
                if ($(this).height() > maxheight) maxheight = $(this).height();
            });
            if (maxheight != -1) {
                $(fixTrTh[i]).find(".fix").each(function () {
                    $(this).height(maxheight);
                });
            }
        });
    }

    onresize = function () {
        heightFiexed();
    };

    function searchSkillList() {
        var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
        jboxLoad("div_table", "<s:url value='/jsres/skillTimeConfig/skillList'/>?roleFlag=" + roleFlag, true);
    }

    function searchImportDocSkill() {
        var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
        jboxLoad("div_table", "<s:url value='/jsres/skillTimeConfig/doctorList'/>?roleFlag=" + roleFlag, true);
    }

</script>
<div class="main_hd">
    <h2 class="underline">考试配置</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab_select" onclick="searchSkillList();"><a >考试配置</a></li>
            <li class="tab" onclick="searchImportDocSkill();"><a>名单导入</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>


