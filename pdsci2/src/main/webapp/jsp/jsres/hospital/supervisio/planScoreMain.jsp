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
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#subjectYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        initOrg();
        toPage();
    });

    var allOrgs = [];

    function initOrg() {
        var datas = [];
        <c:forEach items="${orgs}" var="org">
        var d = {};
        d.id = "${org.orgFlow}";
        d.text = "${org.orgName}";
        d.cityId = "${org.orgCityId}";
        datas.push(d);
        allOrgs.push(d);
        </c:forEach>
        var itemSelectFuntion = function () {
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
    }

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/planScoreList'/>?roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
    }

    function trainOrgHidden() {
        $("#trainOrg-suggest").css({"display": "none"})
    }

     //显示隐藏
    let flag = false;
    function showOrHide(){

        if(flag){
            $(`.form_item_hide`).hide();
            // document.getElementById("hideForm").style.display='none';
            $("#open").text("展开")
            flag = false;
        }else {
            $(`.form_item_hide`).css('display','flex');
            // document.getElementById("hideForm").style.display='flex';
            $("#open").text("收起")
            flag = true;
        }

    }



</script>
<div class="main_hd">
    <h2 class="underline"><c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag}">专业自评</c:if><c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleFlag}">基地自评</c:if></h2>
</div>
<div class="div_search">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>


        <div class="form_search">

            <div class="form_item" >
                <div class="form_label">检查年份：</div>
                <div class="form_content" >
                    <input class="input" name="subjectYear" id="subjectYear"
                           value="${param.subjectYear==null?currentTime:param.subjectYear}"/>
                </div>
            </div>

            <div class="form_item">
                <div class="form_label">专&#12288;&#12288;业：</div>
                <div class="form_content">
                    <select name="speId" id="speId" class="select" >
                    <option value="">全部</option>
                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                        <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}
                                <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                                <c:if test="${roleFlag eq 'baseExpert'}">disabled</c:if>
                        >${dict.dictName}</option>
                    </c:forEach>
                </select>
                </div>
            </div>
            <div class="form_item">
                <div class="form_label">督导结果：</div>
                <div class="form_content" >
                    <select name="supervisionResults" id="supervisionResults" class="select" >
                        <option value="">全部</option>
                        <option value="qualified">合格</option>
                        <option value="basically">基本合格</option>
                        <option value="yellowCard">限时整改</option>
                        <option value="redCard">撤销资格</option>
                    </select>
                </div>
            </div>
            <c:if test="${suAoth ne 'Y' and roleFlag ne 'baseExpert'}">
                <div class="form_item">
                    <div class="form_label">基地名称：</div>
                    <c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleFlag}">
                        <div class="form_content" >
                            <select name="orgFlow" class="select" >
                                <c:forEach items="${orgs}" var="org">
                                    <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}
                                    >${org.orgName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag}">
                        <div class="form_content">
                            <input id="trainOrg" class="toggleView input" type="text" autocomplete="off"
                                   onblur="trainOrgHidden()"/>
                            <input type="hidden" name="orgFlow" id="orgFlow">
                        </div>
                    </c:if>
                </div>
                <div class="form_item form_item_hide">
                    <div class="form_label">基地代码：</div>
                    <div class="form_content">
                        <input type="text" name="baseCode" value="${param.baseCode}" class="input"
                           />
                    </div>
                </div>
            </c:if>

            <div class="form_item" style="text-align: right">
                <div class="form_label">
                    <a style="color: #54B2E5;margin-right: 15px" onclick="showOrHide()" id="open">展开</a>
                </div>
                <div class="form_content">
                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                </div>
            </div>

        </div>




<%--        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">--%>
<%--            <c:if test="${suAoth ne 'Y' and roleFlag ne 'baseExpert'}">--%>
<%--                <tr>--%>
<%--                    <td class="td_left">--%>
<%--                        <nobr>基地名称：</nobr>--%>
<%--                    </td>--%>
<%--                    <c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleFlag}">--%>
<%--                        <td>--%>
<%--                            <select name="orgFlow" class="select" style="width: 181px;">--%>
<%--                                <c:forEach items="${orgs}" var="org">--%>
<%--                                    <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}--%>
<%--                                    >${org.orgName}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </td>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag}">--%>
<%--                        <td>--%>
<%--                            <input id="trainOrg" class="toggleView input" type="text" autocomplete="off"--%>
<%--                                   style="margin-left: 0px;width: 175px" onblur="trainOrgHidden()"/>--%>
<%--                            <input type="hidden" name="orgFlow" id="orgFlow">--%>
<%--                        </td>--%>
<%--                    </c:if>--%>
<%--                    <td class="td_left">--%>
<%--                        <nobr>基地代码：</nobr>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <input type="text" name="baseCode" value="${param.baseCode}" class="input"--%>
<%--                               style="width: 164px;margin-left: 0px;"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </c:if>--%>
<%--            <tr>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>专&#12288;&#12288;业：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="speId" id="speId" class="select" style="width: 181px;">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                            <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}--%>
<%--                                    <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>--%>
<%--                                    <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>--%>
<%--                                    <c:if test="${roleFlag eq 'baseExpert'}">disabled</c:if>--%>
<%--                            >${dict.dictName}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>督导结果：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <select name="supervisionResults" id="supervisionResults" class="select" style="width: 170px;">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <option value="qualified">合格</option>--%>
<%--                        <option value="basically">基本合格</option>--%>
<%--                        <option value="yellowCard">限时整改</option>--%>
<%--                        <option value="redCard">撤销资格</option>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td class="td_left">--%>
<%--                    <nobr>检查年份：</nobr>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input class="input" name="subjectYear" id="subjectYear" style="width: 100px;margin-left: 0px;"--%>
<%--                           value="${param.subjectYear==null?currentTime:param.subjectYear}"/>--%>
<%--                </td>--%>

<%--                <td colspan="8">--%>
<%--                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>

    </form>
</div>
<div id="doctorListZi" style="width: 100%">

</div>