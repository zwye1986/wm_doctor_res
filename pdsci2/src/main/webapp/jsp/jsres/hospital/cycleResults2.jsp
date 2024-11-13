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
    }

    .searchTable .td_left {
        word-wrap: break-word;
        /*width: 5em;*/
        height: auto;
        line-height: auto;
        /*text-align: right;*/
    }
    .searchTable .td_right{
        width: 200px;
        text-align:left;
    }
</style>
<script type="text/javascript">
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                var pDiv = $(boxHome).parent();
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() - 30 + "px";
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                    changeOrgFlow(this);
                } else {
                    var orgTypeFlag = $("#orgTypeFlag").val();
                    $("p." + spaceId + ".item", boxHome).hide();
                    if (orgTypeFlag == "") {
                        $("p." + spaceId + ".item", boxHome).show();
                    } else {
                        $("p." + spaceId + ".item[type*='" + orgTypeFlag + "']", boxHome).show();
                    }
                }
            });
            searchInput.on("blur", function () {
                var boxHome = $("#" + spaceId + "Sel");
                if (!$("." + spaceId + ".boxHome.on").length) {
                    boxHome.hide();
                }
            });
            $("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
                $(this).toggleClass("on");
            });

            $("." + spaceId + ".boxHome .item").click(function () {
                var boxHome = $("." + spaceId + ".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var input = $("#" + spaceId);
                input.val(value);
                if (option.clickActive) {
                    option.clickActive($(this).attr("flow"));
                    $("#orgFlow").val($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    $(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#graduationYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${jsResDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if ("${data}" == "${type.id}") {
            $("#" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#" + "${type.id}").attr("checked", "checked");
        </c:if>
        </c:forEach>
        <c:if test="${not empty param.trainingTypeId}">
        changeTrainSpes('0');
        </c:if>

        changeTrainSpes('1');
    });
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
    }
    function search(page) {
        var data = "";
        $("input[class='docType']:checked").each(function () {
            data += "&datas=" + $(this).val();
        });
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        var schStartDate = $("input[name='schStartDate']").val();
        var schEndDate = $("input[name='schEndDate']").val();
        if(schStartDate && schEndDate && schStartDate > schEndDate) {
            jboxTip("轮转开始时间不能大于结束时间！");
            return false;
        }

        jboxStartLoading();
        if (!page) {
            page = 1;
        }
        $("#currentPage").val(page);
        var data = $("#searchForm").serialize();
        var url = "<s:url value='/jsres/doctorRecruit/doctorRecruitResult'/>?baseFlag=${param.baseFlag}";
        jboxStartLoading();
        jboxPost(url, data, function (resp) {
            $("#content").html(resp);
            jboxEndLoading();
        }, null, false);
        jboxEndLoading();
    }
    function toPage(page) {
        search(page);
    }
    function changeStatus() {
        $("select[name=trainingSpeId] option[value != '']").remove();
        $("select[name=trainingTypeId] option[value = '']").attr('selected', 'selected');
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#jointOrgFlag").attr("checked", false);
            $("#orgFlow").val("");
        }
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
    function loadDetail(doctorFlow){
        var schStartDate = $("input[name='schStartDate']").val();
        var schEndDate = $("input[name='schEndDate']").val();
        var url="<s:url value='/jsres/doctorRecruit/doctorRecruitResultDetail'/>?roleId=${param.roleId}&doctorFlow="+doctorFlow + "&schStartDate=" + schStartDate + "&schEndDate=" + schEndDate;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='550px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,"详细信息",1000,550);
    }
    function showExportMonth() {
        if (${empty docResultsList}) {
            jboxTip("当前无记录!");
            return;
        }
        var data = "";
        $("input[class='docType']:checked").each(function () {
            data += "&datas=" + $(this).val();
        });
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        var url ="<s:url value='/jsp/jsres/hospital/sycleMonth.jsp'/>";
        jboxOpen(url, "请选择导出月份", 400, 260);
    }
    function showExportMonth2() {
        if (${empty docResultsList}) {
            jboxTip("当前无记录!");
            return;
        }
        var data = "";
        $("input[class='docType']:checked").each(function () {
            data += "&datas=" + $(this).val();
        });
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        var url ="<s:url value='/jsp/jsres/hospital/sycleMonth2.jsp'/>";
        jboxOpen(url, "请选择导出时间段", 400, 310);
    }

    // 导出学员出科成绩
    function showExportView() {
        if (${empty docResultsList}) {
            jboxTip("当前无记录!");
            return;
        }
        var url ="<s:url value='/jsp/jsres/hospital/sycleMonth_new.jsp'/>";
        jboxOpen(url, "导出成绩", 400, 260);
    }
</script>

<div class="main_hd">
    <c:if test="${param.roleId eq 'kzr'}">
        <h2 class="underline">出科成绩查询</h2>
    </c:if>
    <c:if test="${param.roleId ne 'kzr'}">
        <h2 class="underline">学员出科查询</h2>
    </c:if>
</div>

<div class="main_bd" id="div_table_0">
    <div class="div_search" >
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
            <input type="hidden" id="orgTypeFlag" value=""/>
            <input type="hidden" id="roleId" name="roleId" value="${param.roleId}"/>
            <c:if test="${param.roleId eq 'kzr' }">
                <table class="searchTable" style="border-collapse:separate; border-spacing:0px 10px;">
                    <tr>
                        <td class="td_left">姓&#12288;&#12288;&#12288;&#12288;名：</td>
                        <td class="td_right">
                            <input type="text" name="userName" class="input" value="${param.userName}"
                                   />
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   readonly="readonly" class="input" />
                        </td>
                        <td class="td_left">培训类别：</td>
                        <td>
                            <select name="trainingTypeId" id="trainingTypeId" class="select"
                                    onchange="changeTrainSpes('1')">
                                <option value="">请选择</option>
                                <option value="DoctorTrainingSpe" <c:if test="${param.trainingTypeId=='DoctorTrainingSpe'}">selected="selected"</c:if> >住院医师</option>
                                <option value="AssiGeneral" <c:if test="${param.trainingTypeId=='AssiGeneral'}">selected="selected"</c:if> >助理全科</option>
                            <%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
                                    <option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                </c:forEach>--%>
                            </select>
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingSpeId" id="trainingSpeId" class="select" >
                                <option value="">全部</option>
                            </select>
                        </td>
                        <td class="td_left" style="min-width: 95px;">结业考核年份：</td>
                        <td>
                            <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}"
                                   class="input" readonly="readonly" />
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type" varStatus="status">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType" <c:if test="${status.index==0}">style="margin-left: 5px;" </c:if>
                                              name="datas"/>${type.name}&nbsp;</label>
                                <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                            </c:forEach>


                            <%--&#12288;<input type="button" class="btn_green" onclick="showExportMonth();"--%>
                                           <%--value="导出试卷"/>--%>
                            <%--&#12288;<input type="button" class="btn_green" onclick="showExportMonth2();"--%>
                                           <%--value="导出成绩"/>--%>
                        </td>
                        <td class="td_left">证&ensp;件&ensp;号：</td>
                        <td>
                            <input type="text" name="idNo" value="${param.idNo}"  class="input" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询"
                                   onclick="toPage();"/>&#12288;
                            <input type="button" class="btn_green" value="导&#12288;出" onclick="showExportMonth();"/>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${param.roleId ne 'kzr' && sessionScope.userListScope ne GlobalConstant.USER_LIST_BASE}">
                <table class="searchTable" style="width:100%;border-collapse:separate; border-spacing:0px 10px;">
                    <tr>
                        <td class="td_left">培训基地：</td>
                        <td class="td_right">
                            <c:set var="orgName" value=""/>
                            <c:forEach items="${orgs}" var="org">
                                <c:if test="${param.orgFlow==org.orgFlow }">
                                    <c:set var="orgName" value="${org.orgName}"/>
                                </c:if>
                            </c:forEach>
                            <input id="trainOrg" oncontextmenu="return false" class="toggleView input" type="text"
                                   value="${orgName}" autocomplete="off" onkeydown="changeStatus();"
                                   onkeyup="changeStatus();" />
                            <div id="pDiv"
                                 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
                                <div class="boxHome trainOrg" id="trainOrgSel"
                                     style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                    <c:forEach items="${orgs}" var="org">
                                        <c:if test="${org.recordStatus eq 'Y'}">
                                            <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                               value="${org.orgName}"
                                               <c:if test="${empty org.orgLevelId}">type="allOrg" </c:if>
                                               <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
                                               style="line-height: 20px; padding:10px 0;cursor: default; "
                                               <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                            >${org.orgName}</p>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}"
                                       style="display: none;"/>
                            </div>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td class="td_right">
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   readonly="readonly" class="input" />
                        </td>
                        <td class="td_left">培训类别：</td>
                        <td class="td_right">
                            <select name="trainingTypeId" id="trainingTypeId" class="select"  onchange="changeTrainSpes('1')">
                                  <option value="DoctorTrainingSpe" <c:if test="${param.trainingTypeId=='DoctorTrainingSpe'}">selected="selected"</c:if> >住院医师</option>
                            </select>
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td class="td_right">
                            <select name="trainingSpeId" id="trainingSpeId" class="select" >
                                <option value="">全部</option>
                            </select>
                        </td>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td class="td_right">
                            <input type="text" name="userName" class="input" value="${param.userName}"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left" >结业考核年份：</td>
                        <td class="td_right">
                            <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}"
                                   class="input" readonly="readonly" />
                        </td>

                        <td class="td_left">证&ensp;件&ensp;号：</td>
                        <td class="td_right">
                            <input type="text" name="idNo" value="${param.idNo}"  class="input" />
                        </td>

                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                              name="datas"/>${type.name}&nbsp;</label>
                                <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                            </c:forEach>
                        </td>

                        <td class="td_left">轮转时间：</td>
                        <td colspan="">
                            <input type="text" class="input" name="schStartDate" value="${param.schStartDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly" style="width: 80px;">
                            ~
                            <input type="text" class="input" name="schEndDate" value="${param.schEndDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly" style="width: 80px;">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询"
                                   onclick="toPage();"/>&#12288;
                            <input type="button" class="btn_green" value="导&#12288;出" onclick="showExportMonth();"/>
                            <%--&#12288;<input type="button" class="btn_green" onclick="showExportMonth();"--%>
                                           <%--value="导出试卷"/>--%>
                            <%--&#12288;<input type="button" class="btn_green" onclick="showExportMonth2();"--%>
                                           <%--value="导出成绩"/>--%>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_BASE}">
                <table class="searchTable" style="width:100%;border-collapse:separate; border-spacing:0px 10px;">
                    <tr>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   readonly="readonly" class="input" />
                        </td>
                        <td class="td_left">培训类别：</td>
                        <td>
                            <select name="trainingTypeId" id="trainingTypeId" class="select"
                                    onchange="changeTrainSpes('1')">
                                <option value="DoctorTrainingSpe" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if> >住院医师</option>

                            </select>
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingSpeId" id="trainingSpeId" class="select">
                                <option value="">全部</option>
                            </select>
                        </td>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="userName" class="input" value="${param.userName}"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">结业考核&#12288;<br>年&#12288;&#12288;份：</td>
                        <td>
                            <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}"
                                   class="input" readonly="readonly" />
                        </td>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                              name="datas"/>${type.name}&nbsp;</label>
                                <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                            </c:forEach>
                        </td>
                        <td class="td_left">证&ensp;件&ensp;号：</td>
                        <td>
                            <input type="text" name="idNo" value="${param.idNo}"  class="input" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询"  onclick="toPage();"/>
                        </td>
                    </tr>
                </table>
            </c:if>
        </form>
    </div>


    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width:8%;">姓名</th>
                <th style="width:10%;">证件号</th>
                <th style="width:15%;">培训基地</th>
                <th style="width:8%;">培训类别</th>
                <th style="width:8%;">培训专业</th>
                <th style="width:8%;">年级</th>
                <th style="width:12%;">结业考核年份</th>
                <th style="width:12%;">理论成绩</th>
                <th style="width:12%;">技能成绩</th>
                <th style="width:8%;">操作</th>
            </tr>
            <c:forEach var="docResults" items="${docResultsList}">
                <tr>
                    <td>${docResults.userName}</td>
                    <td>${docResults.idNo}</td>
                    <td>${pdfn:cutString(docResults.orgName,10,true,3)}</td>
                    <td>${docResults.trainingTypeName}</td>
                    <td>${docResults.trainingSpeName}</td>
                    <td>${docResults.sessionNumber}</td>
                    <td>${docResults.graduationYear}</td>
                    <td>${doctorScore[docResults.doctorFlow].thryScore}</td>
                    <td>${doctorScore[docResults.doctorFlow].killScore}</td>
                    <td style="text-align: center;padding: 0px">
                        <a style="cursor: pointer"
                           onclick="loadDetail('${docResults.doctorFlow}')"
                        >出科信息</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty docResultsList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 60px;">
        <c:set var="pageView" value="${pdfn:getPageView(docResultsList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>

</div>
<div style="display: none;">
    <select id="WMFirst_select">
        <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
            <option
                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                    value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="WMSecond_select">
        <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
            <option
                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                    value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="AssiGeneral_select">
        <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
            <option
                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                    value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="DoctorTrainingSpe_select">
        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
            <option
                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                    value="${dict.dictId}"
                    <c:if test="${'50' eq dict.dictId}">style="display: none" </c:if>
            >${dict.dictName}</option>
        </c:forEach>
    </select>

</div>
