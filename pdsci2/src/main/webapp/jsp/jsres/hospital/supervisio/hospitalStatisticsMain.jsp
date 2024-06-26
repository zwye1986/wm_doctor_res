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

    .searchTable {
        width: auto;
    }

    .searchTable td {
        width: auto;
        height: auto;
        line-height: auto;
        text-align: left;
        max-width: 150px;;
    }

    .searchTable .td_left {
        word-wrap: break-word;
        width: 5em;
        height: auto;
        line-height: auto;
        text-align: right;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
<script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#subjectYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('select[name=speId]').UCFormSelect();
        $(".UCSelect").css("width","107px");
        $(".SelectVal").css("width","170px");
        $(".SelectBox").css("width","159%");

        toPage();
    });


    function toPage(page) {
        if ($("#orgSel").val()==null || $("#orgSel").val()==''){
            $("#deptFlow").val('');
        }
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/hospitalStatisticsList'/>", $("#searchForm").serialize(), false);
    }

    function exportHospitalStatistics() {
        var url = "<s:url value='/jsres/supervisio/exportHospitalStatistics'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }


    /**
     *模糊查询加载
     */
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            searchInput.on("keyup focus",function(){
                $("#boxHome").show();
                if($.trim(this.value)){
                    $("#boxHome .item").hide();
                    var items = $("#boxHome .item[value*='"+this.value+"']").show();
                    if(!items){
                        $("#boxHome").hide();
                    }
                }else{
                    $("#boxHome .item").show();
                }
            });
            searchInput.on("blur",function(){
                if(!$("#boxHome.on").length){
                    $("#boxHome").hide();
                }
            });
            $("#boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });
            $("#boxHome .item").click(function(){
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#itemName").val(value);
                searchInput.val(value);
                if(option.clickActive){
                    option['clickActive']($(this).attr("flow"));
                }
            });
        };
    })(jQuery);


    //页面加载完成时调用
    $(function(){
        $("#orgSel").likeSearchInit({});
    });

</script>
<div class="main_hd">
    <h2 class="underline">督导管理 — 评审结果汇总</h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">
                    <nobr>活动名称：</nobr>
                </td>
                <td>
                    <input id="trainOrg" class="toggleView input" type="text" autocomplete="off" name="activityName" placeholder="请输入活动名称"
                           style="margin-left: 0px;width: 164px"/>
                    <input type="hidden" name="orgFlow" id="orgFlow">
                </td>

                <td class="td_left">
                    <nobr>活动形式：</nobr>
                </td>
                <td>
                    <select name="inspectionType" id="inspectionType" class="select" style="width: 170px;">
                        <option value="">全部</option>
                        <c:forEach items="${activityTypeEnumList}" var="dict">
                            <option value="${dict.id}" <c:if test="${param.speId eq dict.id}">selected</c:if>>${dict.name}</option>
                        </c:forEach>
                    </select>
                </td>

                <td class="td_left">
                    <nobr>科&#12288;&#12288;室：</nobr>
                </td>
                <td>
                    <input type="hidden" id="deptFlow" name="deptFlow" value="${user.deptFlow}">
                    <input id="orgSel" class="toggleView input" type="text" style="width: 165px;margin-left: 0px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 127px -4px;" name="deptName" placeholder="请选择科室"
                           value="${user.deptName}" autocomplete="off" title="${param.deptName}" onmouseover="this.title = this.value"/>
                    <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:0px;">
                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 170px;border-top: none;position: relative;display: none;">
                            <c:forEach items="${deptList}" var="dept">
                                <p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" onclick="toDeptFlow('${dept.deptFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${dept.deptName}</p>
                            </c:forEach>
                        </div>
                    </div>
                </td>

            </tr>
            <tr>
                <td class="td_left">
                    <nobr>评审开始时间：</nobr>
                </td>
                <td>
                    <input name="startTime" id="startTime" style="margin-left: 0px;width: 165px;" placeholder="请选择开始时间"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                           onchange="checkJointLocalStart()" value="" class="input">
                </td>
                <td class="td_left">
                    <nobr>评审结束时间：</nobr>
                </td>
                <td>
                    <input name="endTime" id="endTime" style="margin-left: 0px;width: 165px;" placeholder="请选择结束时间"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                           onchange="checkJointLocalStart()" value="" class="input">
                </td>
                <td>
                    <input class="btn_green" type="button"  style="margin-right: -17px" value="查&#12288;询" onclick="toPage(1);"/>&#12288;
                </td>
                <td>
                    <input class="btn_green" type="button"  style="margin-left: 25px;" value="导&#12288;出" onclick="exportHospitalStatistics();"/>
                </td>
            </tr>
        </table>

    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>