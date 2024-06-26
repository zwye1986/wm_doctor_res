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
<script type="text/javascript">
    $(document).ready(function () {
        $('#subjectYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        getCityArea();
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

    function changeOrg(obj) {
        var cityId = $(obj).val();
        var datas = [];
        for (var i = 0; i < allOrgs.length; i++) {
            var org = allOrgs[i];
            if (org.cityId == cityId || cityId == "") {
                datas.push(org);
            }
        }
        $("#orgFlow").val("");
        $("#orgName").val("");
        var itemSelectFuntion = function () {
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
    }

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/subjectList'/>?roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
    }

    function editSupervisioSubject(type, subjectFlow) {
        var title = "新增";
        if (type == 'edit') {
            title = "修改";
        }
        var url = "<s:url value ='/jsres/supervisio/editSubject'/>?subjectFlow=" + subjectFlow + "&type=" + type;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, title + "项目", 1000, 600, false);
    }

    function allInfoExpert() {
        var url = "<s:url value ='/jsres/supervisio/allInfoExpert'/>";
        jboxTip("导出中…………");
        jboxExp(top.$("#searchForm"),url);
    }


    function delSupervisioSubject(subjectFlow) {
        var url = "<s:url value='/jsres/supervisio/deleteSubject?subjectFlow='/>" + subjectFlow;
        jboxConfirm("确认删除项目？", function () {
            jboxPost(url, null, function (resp) {
                if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                    jboxTip(resp);
                    toPage();
                } else {
                    jboxTip(resp);
                }
            }, null, false);
        });
    }

    function showPlanInfo(speId, subjectFlow, roleFlag,subjectActivitiFlows,manageUserFlow) {
        console.log(1)
        var url = "<s:url value ='/jsres/supervisio/showPlanInfoMian'/>?speId=" + speId + "&subjectFlow=" + subjectFlow + "&roleFlag=" + roleFlag +"&subjectActivitiFlows="+subjectActivitiFlows+"&manageUserFlow="+manageUserFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "查看表单评分详情", 1875, 1000, false);
    }

    function getCityArea() {
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "420000";
        jboxGet(url, null, function (json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData = new Array();
            var j = 0;
            var html = "<option value=''></option>";
            for (var i = 0; i < json.length; i++) {
                if (provIds == json[i].v) {
                    var citys = json[i].s;
                    for (var k = 0; k < citys.length; k++) {
                        var city = citys[k];
                        html += "<option value='" + city.v + "'>" + city.n + "</option>";
                    }
                }
            }
            $("#cityId2").html(html);
        }, null, false);
    }

</script>
<div class="main_hd">
    <h2 class="underline">督导管理 — 项目管理</h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">
                    <nobr>基地名称：</nobr>
                </td>
                <td>
                    <input id="trainOrg" class="toggleView input" type="text" autocomplete="off"
                           style="margin-left: 0px;width: 164px"/>
                    <input type="hidden" name="orgFlow" id="orgFlow">
                </td>
                <td class="td_left">
                    <nobr>专&#12288;&#12288;业：</nobr>
                </td>
                <td>
                    <select name="speId" id="speId" class="select" style="width: 170px;">
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}
                                    <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                    <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                            >${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="td_left">
                    <nobr>检查年份：</nobr>
                </td>
                <td>
                    <input class="input" name="subjectYear" id="subjectYear" style="width: 100px;margin-left: 0px;"
                           value="${param.subjectYear==null?currentTime:param.subjectYear}"/>
                </td>

            </tr>
            <tr>
                <td class="td_left">
                    <nobr>基地代码：</nobr>
                </td>
                <td>
                    <input id="baseCode" class="toggleView input" type="text" name="baseCode"
                           style="margin-left: 0px;width: 164px" onblur="trainOrgHidden()"/>
                </td>

                <td class="td_left">
                    <nobr>督导结果：</nobr>
                </td>
                <td>
                    <select name="supervisionResults" id="supervisionResults" class="select" style="width: 170px;">
                        <option value="">全部</option>
                        <option value="qualified">合格</option>
                        <option value="basically">基本合格</option>
                        <option value="yellowCard">限时整改</option>
                        <option value="redCard">撤销资格</option>
                    </select>
                </td>
                <%--                <td class="td_left">--%>
                <%--                    <nobr>检查年份：</nobr>--%>
                <%--                </td>--%>
                <%--                <td>--%>
                <%--                    <input class="input" name="subjectYear" id="subjectYear" style="width: 100px;margin-left: 0px;"--%>
                <%--                           value="${param.subjectYear==null?currentTime:param.subjectYear}"/>--%>
                <%--                </td>--%>
                <td><input class="btn_green" type="button"  style="margin-right: -17px" value="查&#12288;询" onclick="toPage(1);"/>&#12288;</td>


                <c:if test="${roleFlag eq 'global'}">
            </tr>
            <tr>
                </c:if>
                <td colspan="6">
                    <%--                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;--%>
                    <c:if test="${roleFlag eq 'global'}">
                        <input class="btn_green" type="button" value="新&#12288;增"
                               onclick="editSupervisioSubject('add','');"/>&#12288;
                        <input class="btn_green" type="button" value="一键导出"
                               onclick="allInfoExpert();"/>&#12288;
                    </c:if>
                </td>
            </tr>
        </table>

    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>