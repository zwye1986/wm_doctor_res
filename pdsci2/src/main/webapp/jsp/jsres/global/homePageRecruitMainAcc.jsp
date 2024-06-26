<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>

<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        jboxStartLoading();
        $("#firstLi").addClass('tab_select');
        jboxPostLoad("doctorListZi","<s:url value='/jsres/manage/homePageRecruitListAcc'/>?tabId=orgType",$("#searchForm").serialize(),false);
    });

    function toPage() {
        var tab=$("#DataType").val();
        jboxStartLoading();
        jboxPostLoad("doctorListZi","<s:url value='/jsres/manage/homePageRecruitListAcc'/>?tabId="+tab,$("#searchForm").serialize(),false);
    }

    function tabClick(tag,type){
        $('.tab_select').removeClass('tab_select');
        $(tag).removeClass('tab');
        $(tag).addClass('tab_select');
        jboxPostLoad("doctorListZi","<s:url value='/jsres/manage/homePageRecruitListAcc'/>?tabId="+type,$("#searchForm").serialize(),false);
        jboxStartLoading();
    }

</script>

<div class="main_hd">
    <div class="title_tab">
        <ul>
            <li id="firstLi" class=" ${tabId eq 'orgType'?'tab_select':'tab'}" onclick="tabClick(this,'orgType');"><a>按基地统计</a></li>
            <li class=" ${tabId eq 'speType'?'tab_select':'tab'}" onclick="tabClick(this,'speType');"><a>按专业统计</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="catSpeId" name="catSpeId" value="${catSpeId}">
            <table class="searchTable">
                <tr>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input class="input" name="sessionNumber" id="sessionNumber" style="width: 100px;margin-left: 0px;"
                               value="${sessionNumber==null?currentTime:sessionNumber}"/>
                    </td>

                    <td id="func" colspan="6">
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="doctorListZi">
    </div>
</div>
