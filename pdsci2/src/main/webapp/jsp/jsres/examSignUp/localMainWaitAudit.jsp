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
        text-align: center;
        max-width: 150px;;
    }

    .searchTable tr {
        line-height: 40px;
    }

    /*.searchTable .select {*/
    /*    padding: 4px 2px;*/
    /*    box-sizing: content-box;*/
    /*}*/

    .searchTable .td_left {
        word-wrap: break-word;
        width: 6em;
        height: auto;
        line-height: auto;
        text-align: right;
    }

    .searchTable .td_right{
        width: 220px;
        text-align:left;
    }

    .input {
        margin: 0px;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#signupYear').datepicker({
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
        <c:if test="${(sessionScope.currUser.orgFlow!=org.orgFlow and roleFlag eq GlobalConstant.USER_LIST_GLOBAL)
        or roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
        var d = {};
        d.id = "${org.orgFlow}";
        d.text = "${org.orgName}";
        d.cityId = "${org.orgCityId}";
        datas.push(d);
        allOrgs.push(d);
        </c:if>
        </c:forEach>
        // var itemSelectFuntion = function () {
        //     $("#orgFlow").val(this.id);
        // };
        // $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
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
        var data = "";
        $("input[class='docType']:checked").each(function () {
            data += "&datas=" + $(this).val();
        });
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        page = page || $("#currentPage").val();
        page = page || "1";
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/examSignUp/signList'/>?roleFlag=${roleFlag}&typeId=${typeId}", $("#searchForm").serialize(), false);
    }

    function exportInfo() {
        var data = "";
        $("input[class='docType']:checked").each(function () {
            data += "&datas=" + $(this).val();
        });
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        if (!$("#signupYear").val()) {
            jboxTip("请选申请年份！");
            return false;
        }
        var url = "<s:url value='/jsres/examSignUp/exportList'/>?roleFlag=${roleFlag}&typeId=${typeId}";
        jboxExpLoading($("#searchForm"), url);
    }

    function changeTrainSpes(t) {
        var trainCategoryid = $("#trainingTypeId").val();
        if (trainCategoryid == "") {
            $("select[name=trainingSpeId] option[value != '']").remove();
            return false;
        }
        $("select[name=trainingSpeId] option[value != '']").remove();
        $("#" + trainCategoryid + "_select").find("option").each(function () {
            $(this).clone().appendTo($("#trainingSpeId"));
        });
        return false;
    }

    function getCityArea() {
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "320000";
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
<%--    <h2 class="underline">补考审核</h2>--%>
</div>
<div class="div_search" >
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
        <input type="hidden" id="tabTag" name="tabTag" value="${tabTag}"/>
        <!-- 判断是否显示省厅审核意见 -->
        <c:set var="testOpenFlag" value="Y"></c:set>
        <c:forEach items="${resTestConfigs}" var="testConfig">
            <c:set var="k" value="${testConfig.testId}_make_up"/>
            <c:if test="${ sysCfgMap[k] eq 'N' or empty sysCfgMap[k] }">
                <c:set var="testOpenFlag" value="N"></c:set>
            </c:if>
        </c:forEach>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left ">培训基地：</td>
                <td class="td_right">
                    <select name="joinOrgFlow" id="joinOrgFlow" class="select " >
                        <option value="">全部</option>
                        <c:forEach items="${orgs}" var="org">
                            <option value="${org.orgFlow}">${org.orgName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="td_left">审核状态：</td>
                <td class="td_right">
                    <select name="auditStatusId" id="auditStatusId" class="select" >
                        <option value="">请选择</option>
                        <option value="Auditing" selected>待基地审核</option>
                        <%--<c:forEach items="${jsResAsseAuditListEnumList}" var="type">--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${testOpenFlag eq 'N'}">--%>
                                    <%--<c:if test="${type.id ne 'WaitGlobalPass' and type.id ne 'GlobalNotPassed' and type.id ne 'GlobalPassed'}">--%>
                                        <%--<option value="${type.id}">${type.name}</option>--%>
                                    <%--</c:if>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<option value="${type.id}">${type.name}</option>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</c:forEach>--%>
                    </select>
                </td>
                <td class="td_left">补考类型：</td>
                <td class="td_right">
                    <select name="signUpTypeId" class="select" >
                        <option value="">请选择</option>
                        <option value="Skill">技能补考</option>
                        <option value="Theory">理论补考</option>
                    </select>
                </td>
                <td class="td_left">培训类别：</td>
                <td class="td_right">
                    <%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">--%>
                    <select name="trainingTypeId" id="trainingTypeId" class="select" >
                        <option value="">请选择</option>
                        <c:if test="${param.tabTag eq 'SecondWait'}">
                            <option value="DoctorTrainingSpe" selected="selected">住院医师</option>
                          <%--  <option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>
                            <option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>--%>
                        </c:if>
                        <c:if test="${param.tabTag eq 'SecondWait2'}">
                            <option value="AssiGeneral" selected="selected">助理全科</option>
                        </c:if>
                        <%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
                            <%--<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
                        <%--</c:forEach>--%>
                    </select>
                </td>
                <td class="td_left">培训专业：</td>
                <td class="td_right">
                    <select name="trainingSpeId" id="trainingSpeId" class="select" >
                        <option value="">全部</option>
                        <c:if test="${param.tabTag eq 'SecondWait'}">
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                                    <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${param.tabTag eq 'SecondWait2'}">
                            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                                <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                                    <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="td_left">结业年份：</td>
                <td class="td_right">
                    <input type="text" id="signupYear" name="signupYear" class="input" readonly="readonly"
                           value="${pdfn:getCurrYear()}" />
                </td>
                <td class="td_left">年&#12288;&#12288;级：</td>
                <td class="td_right">
                    <input type="text" id="sessionNumber" name="sessionNumber" class="input" readonly="readonly"
                           />
                </td>
                <td class="td_left">姓&#12288;&#12288;名：</td>
                <td class="td_right">
                    <input type="text" name="userName" value="${param.userName}" class="input"
                           />
                </td>
                 <td class="td_left">证&nbsp;件&nbsp;号&nbsp;：</td>
                <td class="td_right">
                    <input type="text" name="idNo" value="${param.idNo}" class="input"
                           />
                </td>
                <td class="td_left">考试编号：</td>
                <td class="td_right">
                    <select name="testId" class="select" >
                        <option value="">全部</option>
                        <c:forEach items="${resTestConfigs}" var="resTest">
                            <option value="${resTest.testId}" ${param.testId eq resTest.testId?'selected':''}>${resTest.testId}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="td_left">是否延期：</td>
                <td class="td_right">
                    <select class="select" name="isPostpone" >
                        <option value="">请选择</option>
                        <option
                                <c:if test="${param.isPostpone eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>
                                value="${GlobalConstant.FLAG_Y}">是
                        </option>
                        <option
                                <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>
                                value="${GlobalConstant.FLAG_N}">否
                        </option>
                    </select>
                </td>
                <td class="td_left">异常报考：</td>
                <td class="td_right">
                    <select class="select" name="tempDoctorFlag">
                        <option value="">全部</option>
                        <option <c:if test="${param.tempDoctorFlag eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>
                                value="${GlobalConstant.FLAG_Y}">是
                        </option>
                        <option <c:if test="${param.tempDoctorFlag eq GlobalConstant.FLAG_N}">selected="selected"</c:if>
                                value="${GlobalConstant.FLAG_N}">否
                        </option>
                    </select>
                </td>
                <td class="td_left">人员类型：</td>
                <td colspan="3" style="text-align: left;">
                    <c:forEach items="${resDocTypeEnumList}" var="type">
                        <label><input type="checkbox" id="${type.id}" value="${type.id}" checked class="docType"
                                      name="datas"/>${type.name}&nbsp;</label>
                    </c:forEach>
                </td>
                <td colspan="2" >
                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
                    <input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="doctorListZi" >
</div>
<div style="display: none;">
    <select id="WMFirst_select">
        <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
            <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                <option
                        <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                        value="${dict.dictId}">${dict.dictName}</option>
            </c:if>
        </c:forEach>
    </select>
    <select id="WMSecond_select">
        <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
            <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                <option
                        <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                        value="${dict.dictId}">${dict.dictName}</option>
            </c:if>
        </c:forEach>
    </select>
    <select id="AssiGeneral_select">
        <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
            <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                <option
                        <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                        value="${dict.dictId}">${dict.dictName}</option>
            </c:if>
        </c:forEach>
    </select>
    <select id="DoctorTrainingSpe_select">
        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
            <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                <option
                        <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                        value="${dict.dictId}">${dict.dictName}</option>
            </c:if>
        </c:forEach>
    </select>

</div>
<div>
    <c:forEach items="${orgFlowList}" var="flow">
        <input type="hidden" id="${flow}" value="${flow}"/>
    </c:forEach>

</div>