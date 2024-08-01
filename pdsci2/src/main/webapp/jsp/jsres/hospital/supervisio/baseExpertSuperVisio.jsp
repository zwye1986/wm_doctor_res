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


    .form_search {

        display: flex;
        flex-wrap: wrap;
        margin-top: 15px;

        .form_item {

            width: 280px;
            display: flex;

            .from_label {
                font-size: 14px;
                font-style: normal;
                font-weight: 400;
                margin-top: auto;
                margin-bottom: auto;
                text-align: right;
                width: 90px;
            }

            .form_content {

                display: inline-block;
            }

        }
    }

    /*.SelectVal{*/
    /*    width: 238px;*/
    /*}*/
    /*.UCSelect .SelectVal {*/
    /*    width: 161px !important;*/
    /*}*/
    /*.UCSelect .SelectVal span {*/
    /*    padding: 0px 4px;*/
    /*    font-weight: 400;*/
    /*}*/
</style>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
<script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // $('select[name=speId]').UCFormSelect();
        // $('select[name=orgFlow]').UCFormSelect();

        $('#subjectYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $(".SelectVal").css("width","238px");
        toPage();
    });


    function toPage(page) {
        var minBaseScore = $("#minBaseScore").val();
        var maxbaseScore = $("#maxbaseScore").val();
        var minManageScore = $("#minManageScore").val();
        var maxManageScore = $("#maxManageScore").val();
        if (Number(minBaseScore)>Number(maxbaseScore)){
            top.jboxTip("自评得分输入的区间有误！");
            top.jboxClose();
            return;
        }
        if (Number(minManageScore)>Number(maxManageScore)){
            top.jboxTip("督导得分输入的区间有误！");
            top.jboxClose();
            return;
        }
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/baseExpertSupervisioList'/>?roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
    }

    //显示隐藏
    let flag = false;
    function showOrHide(){

        if(flag){
            document.getElementById("hideForm").style.display='none';
            $("#open").text("展开")
            flag = false;
        }else {
            document.getElementById("hideForm").style.display='flex';
            $("#open").text("收起")
            flag = true;
        }

    }




</script>
<div class="main_hd">
    <h2 class="underline">专业基地自评汇总表</h2>
</div>
<div class="div_search">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>

        <div class="form_search">
            <div class="form_item">
                <div class="from_label" >基地名称：</div>
                <div class="form_content">
                    <select name="orgFlow" class="select" style="max-width: 161px;" >
                        <c:if test="${roleFlag eq 'local'}">
                            <option value="${orgFlow}">${orgName}</option>
                        </c:if>
                        <c:if test="${roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">
                            <c:forEach items="${orgs}" var="org">
                                <option value="${org.orgFlow}" <c:if test="${orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

            </div>
            <div class="form_item">
                <div class="from_label" >基地排序：</div>
                <div class="form_content">
                    <select name="orgPaiXu" class="select" style="width: 161px;">
                        <option value="">默认</option>
                        <option value="">升序</option>
                        <option value="desc">降序</option>
                    </select>
                </div>
            </div>
            <div class="form_item">
                <div class="from_label" >督导情况：</div>
                <div class="form_content">
                    <select name="manageAllSub" class="select" style="width: 161px;">
                        <option value="">全部</option>
                        <option value="Y">已完成</option>
                        <option value="N">未完成</option>
                        <option value="W">无计划</option>
                    </select>
                </div>
            </div>
            <div class="form_item">
                <div class="from_label" >自评得分：</div>
                <div class="form_content">
                    <input type="text" id="minBaseScore" name="minBaseScore" class="input" style="width: 70px;" value=""> ~ <input type="text" id="maxbaseScore" name="maxbaseScore" class="input" style="width: 70px;" value="">
                </div>
            </div>
            <div class="form_item">
                <div class="from_label" >专&#12288;业：</div>
                <div class="form_content">
                    <select name="speId" class="select"   >
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <c:if test="${'3500' ne dict.dictId and '3700' ne dict.dictId}">
                                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div id="hideForm" style="display: none;justify-content: space-between;margin-top: 15px">
            <div>
                <label class="from_label">自评排序：</label>
                <select name="zipx" class="select" style="width: 161px;">
                    <option value="">默认</option>
                    <option value="asc">升序</option>
                    <option value="desc">降序</option>
                </select>
            </div>
            <div>
                <label class="from_label">督导结果：</label>
                <select name="supervisioResults" class="select" style="width: 161px;">
                    <option value="">全部</option>
                    <option value="qualified">合格</option>
                    <option value="basically">基本合格</option>
                    <option value="yellowCard">限时整改</option>
                    <option value="redCard">不合格</option>
                </select>
            </div>
            <div>
                <label class="from_label">督导年份：</label>
                <input class="input" name="subjectYear" id="subjectYear" style="width: 151px;"
                       value="${pdfn:getCurrYear()}"/>
            </div>
            <div>
                <label class="from_label">督导得分：</label>
                <input name="minManageScore" id="minManageScore" type="text" class="input" style="width: 80px;" value="">~<input id="maxManageScore" name="maxManageScore" type="text" class="input" style="width: 80px;" value="">&#12288;分
            </div>
            <div>
                <label class="from_label">自评情况：</label>
                <select name="zipx" class="select" style="width: 161px;">
                    <option value="">默认</option>
                    <option value="asc">升序</option>
                    <option value="desc">降序</option>
                </select>
            </div>
        </div>

        <div style="margin-top: 15px;margin-bottom: 15px">
            <input class="btn_green" type="button" onclick="toPage(1);"  value="查&#12288;询" />
            <a style="color: #54B2E5;float: right" onclick="showOrHide()" id="open">展开</a>
        </div>





<%--        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">--%>
<%--            <tr>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>基地名称：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="orgFlow" class="select" style="width: 240px;" multiple="multiple">--%>
<%--                        <c:if test="${roleFlag eq 'local'}">--%>
<%--                            <option value="${orgFlow}">${orgName}</option>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${roleFlag eq  GlobalConstant.USER_LIST_GLOBAL}">--%>
<%--                            <c:forEach items="${orgs}" var="org">--%>
<%--                                <option value="${org.orgFlow}" <c:if test="${orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </c:if>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr style="">基地排序：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="orgPaiXu" class="select" style="width: 97px;">--%>
<%--                        <option value="">默认</option>--%>
<%--                        <option value="">升序</option>--%>
<%--                        <option value="desc">降序</option>--%>
<%--                    </select>--%>

<%--                </td>--%>

<%--                <td class="td_left">--%>
<%--                    <nobr>督导情况：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="manageAllSub" class="select" style="width: 97px;">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <option value="Y">已完成</option>--%>
<%--                        <option value="N">未完成</option>--%>
<%--                        <option value="W">无计划</option>--%>
<%--                    </select>--%>
<%--                </td>--%>

<%--                <td class="td_left">--%>
<%--                    <nobr>自评得分：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input type="text" id="minBaseScore" name="minBaseScore" class="input" style="width: 40px;" value=""> ~ <input type="text" id="maxbaseScore" name="maxbaseScore" class="input" style="width: 40px;" value="">分--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>专&#12288;&#12288;业：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="speId" class="select" id="" style="width: 240px;" multiple="multiple">--%>
<%--                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                            <c:if test="${'3500' ne dict.dictId and '3700' ne dict.dictId}">--%>
<%--                                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>--%>
<%--                            </c:if>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr style=" ">自评排序：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="zipx" class="select" style="width: 97px;">--%>
<%--                        <option value="">默认</option>--%>
<%--                        <option value="asc">升序</option>--%>
<%--                        <option value="desc">降序</option>--%>
<%--                    </select>--%>
<%--                </td>--%>

<%--                <td class="td_left">--%>
<%--                    <nobr>督导结果：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="supervisioResults" class="select" style="width: 97px;">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <option value="qualified">合格</option>--%>
<%--                        <option value="basically">基本合格</option>--%>
<%--                        <option value="yellowCard">限时整改</option>--%>
<%--                        <option value="redCard">不合格</option>--%>
<%--                    </select>--%>
<%--                </td>--%>

<%--                <td class="td_left">--%>
<%--                    <nobr>督导得分：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input name="minManageScore" id="minManageScore" type="text" class="input" style="width: 40px;" value=""> ~ <input id="maxManageScore" name="maxManageScore" type="text" class="input" style="width: 40px;" value="">分--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>督导年份：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input class="input" name="subjectYear" id="subjectYear" style="width: 235px;margin-left: 0px"--%>
<%--                           value="${pdfn:getCurrYear()}"/>--%>
<%--                </td>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr style="">自评情况：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="basePaiXu"  class="select" style="width: 97px;">--%>
<%--                        <option value="">默认</option>--%>
<%--                        <option value="Y">已完成</option>--%>
<%--                        <option value="N">未完成</option>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--                <td></td>--%>
<%--                <td>--%>
<%--                    <div  onclick="toPage(1);" style="background-color:#54B2E5;width: 94px;height: 35px;border-radius: 4px;margin-left: 42px;padding-top: 1px">--%>
<%--                        <img alt= "上传附件" style="margin-top: 12px;margin-left: 15px" src="<s:url value='/css/skin/LightBlue/images/u67.svg'/>">--%>
<%--                        <p style="font-size: 14px;line-height: 24px;margin-top: -20px;margin-left: 39px;color: white">查&#12288;询</p>--%>
<%--&lt;%&ndash;                        <input class="btn_green" type="button" value="查&#12288;询" />&#12288;&ndash;%&gt;--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
    </form>
</div>
<div id="doctorListZi" style="width: 100%">

</div>