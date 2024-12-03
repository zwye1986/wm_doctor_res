<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
    // 查询
    function toPage(page) {
        var operStartDate = $("#operStartDate").val();
        var operEndDate = $("#operEndDate").val();
        if (operStartDate != "" || operEndDate != "") {
            if (operStartDate != "" && operEndDate == "") {
                jboxTip("请选择结束时间！");
                return false;
            } else if (operStartDate == "" && operEndDate != "") {
                jboxTip("请选择开始时间！");
                return false;
            } else if (operEndDate < operStartDate) {
                jboxTip("开始时间不能大于结束时间！");
                return false;
            }
        }
        if($("input[name='doctorTypeIdList']:checked").length <= 0) {
            jboxTip("请选择人员类型！");
            return false;
        }
        page = page || "${param.currentPage}";
        $("#currentPage").val(page);
        jboxPostLoad("content","<s:url value='/jsres/universityMgr/teacherWorkload'/>",$("#teacherWorkloadForm").serialize(),true);
    }
    // 带教明细
    function details(teacherUserFlow, title, startDate, endDate) {
        var doctorTypeIdList = "";
        <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
        if("${doctorTypeSelectMap[type.dictId]}"!="") {
            doctorTypeIdList = doctorTypeIdList + "&doctorTypeIdList=${type.dictId}";
        }
        </c:forEach>
        var url = "<s:url value='/jsres/universityMgr/doctorDetailList'/>?teacherUserFlow=" + teacherUserFlow + "&startDate=" + startDate + "&endDate=" + endDate+doctorTypeIdList;
        jboxOpen(url, title, 850, 550, false);
    }
    // 导出
    function exportInfo() {
        var operStartDate = $("#operStartDate").val();
        var operEndDate = $("#operEndDate").val();
        if (operStartDate != "" || operEndDate != "") {
            if (operStartDate != "" && operEndDate == "") {
                jboxTip("请选择结束时间！");
                return false;
            }else if (operStartDate == "" && operEndDate != "") {
                jboxTip("请选择开始时间！");
                return false;
            }else if (operEndDate < operStartDate) {
                jboxTip("开始时间不能大于结束时间！");
                return false;
            }
        }
        if($("input[name='doctorTypeIdList']:checked").length<=0) {
            jboxTip("请选择人员类型！");
            return false;
        }
        jboxTip("导出中…………");
        var url = "<s:url value='/jsres/universityMgr/exportExcel'/>";
        jboxSubmit($("#teacherWorkloadForm"), url, null, null, false);
        jboxEndLoading();
    }
    // 教学活动明细
    function activityDetails(peakerFlow, deptFlow,title,startTime, endTime){
        var url = "<s:url value='/jsres/manage/activityDetails'/>?speakerFlow=" + peakerFlow + "&startTime=" + startTime + "&endTime=" + endTime+"&deptFlow="+deptFlow;
        jboxOpen(url, title, 650, 500, true);
    }

    // 初始化基地
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
                w = w - 0 + $(this).outerHeight() + 6 + "px";
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
                    if($(this).attr("flow")){
                        getSysDeptData($(this).attr("flow"));
                    }
                }
            });
        };
    })(jQuery);
    // 初始化
    $(function () {
        $('#operStartDate,#operEndDate').datepicker();
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });

    // 基地状态变更
    function changeStatus(){
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == ""){
            $("#orgFlow").val("");
            $("#deptFlowSel").html("<option value=''></option>");
        }
    }
    // 切换基地
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
        if(flow){
            getSysDeptData(flow);
        }
    }

    // 查询部门信息
    function getSysDeptData(orgFlow){
        var url = "<s:url value='/sys/dept/searchSysDeptList'/>";
        jboxGet(url,{orgFlow : orgFlow},function(resp){
            var html = "<option value=''></option>";
            if(resp && resp.length > 0){
                for(var i = 0; i < resp.length; i++){
                    if(resp[i].deptFlow == "${param.deptFlow}"){
                        html += "<option value='" + resp[i].deptFlow + "' selected='selected'>" + resp[i].deptName + "</option>"
                    }else{
                        html += "<option value='" + resp[i].deptFlow + "'>" + resp[i].deptName + "</option>"
                    }
                }
            }
            $("#deptFlowSel").html(html);
        },null,false);
    }
</script>
<style>
    .title_div{
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 1;
        overflow: hidden;
    }
</style>
<body>
<div class="main_hd">
    <h2 class="underline">带教工作量查询</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="teacherWorkloadForm" style="position: relative;margin: 10px;" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">培训基地：</td>
                    <td>
                        <c:set var="orgName" value=""/>
                        <c:forEach items="${orgList}" var="org">
                            <c:if test="${param.orgFlow == org.orgFlow }">
                                <c:set var="orgName" value="${org.orgName}"/>
                            </c:if>
                        </c:forEach>
                        <input id="trainOrg" oncontextmenu="return false" class="toggleView input" type="text"
                               value="${orgName}" autocomplete="off" onkeydown="changeStatus();"
                               onkeyup="changeStatus();"/>
                        <div id="pDiv"
                             style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
                            <div class="boxHome trainOrg" id="trainOrgSel"
                                 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 200px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${orgList}" var="org">
                                    <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                       value="${org.orgName}"
                                       <c:if test="${empty org.orgLevelId}">type="allOrg" </c:if>
                                       <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
                                       style="line-height: 20px; padding:10px 0;cursor: default; "
                                       <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                    >${org.orgName}</p>
                                </c:forEach>
                            </div>
                            <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                        </div>
                    </td>
                    <td class="td_left">科&#12288;&#12288;室：</td>
                    <td>
                        <select name="deptFlow" id="deptFlowSel" class="select" style="width: 133px;">
                            <option></option>
                            <%--<c:forEach items="${deptList}" var="dept">--%>
                                <%--<option value="${dept.deptFlow}"--%>
                                        <%--<c:if test="${param.deptFlow == dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>--%>
                            <%--</c:forEach>--%>
                        </select>
                    </td>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td colspan="3">
                        <input type="text" name="userName" value="${param.userName}" class="input" />
                    </td>
                </tr>
                <tr>
                    <td class="td_left">排&ensp;序&ensp;项：</td>
                    <td>
                        <select name="orderItem" class="select" style="width: 133px;">
                            <option value="0"
                                    <c:if test="${(param.orderItem=='0') or empty param.orderItem}">selected="selected"</c:if>>带教名单</option>
                            <%--<option value="1"--%>
                            <%--<c:if test="${param.orderItem=='1'}">selected="selected"</c:if>>未审核数</option>--%>
                            <%--<option value="2"--%>
                            <%--<c:if test="${param.orderItem=='2'}">selected="selected"</c:if>>已审核数</option>--%>
                        </select>
                    </td>
                    <td colspan="2">
                        <label>
                            <input type="radio" name="sortName" value="DESC" <c:if test="${(param.sortName eq 'DESC') or empty param.sortName}">checked="checked"</c:if>/>降序
                        </label>
                        &#12288;
                        <label>
                            <input type="radio" name="sortName" value="ASC" <c:if test="${param.sortName eq 'ASC'}">checked="checked"</c:if>/>升序
                        </label>
                    </td>
                    <td class="td_left">时间区间：</td>
                    <td colspan="3">
                        <input type="text" id="operStartDate" name="operStartDate" value="${operStartDate}" class="input"
                               readonly="readonly" style="width: 100px;"/>
                        ~
                        <input type="text" id="operEndDate" name="operEndDate" value="${operEndDate}" class="input"
                               readonly="readonly" style="width: 100px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" name="doctorTypeIdList" value="${type.id}"
                                ${doctorTypeSelectMap[type.id]}>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td colspan="4">
                        <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        &#12288;<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="13%"/>
                <col width="10%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
            </colgroup>
            <tr>
                <th>培训基地</th>
                <th>科室</th>
                <th>带教老师</th>
                <th>用户名</th>
                <th>老师均分</th>
                <th>已审核数</th>
                <th>未审核数</th>
                <th>教学活动数</th>
                <th>带教名单</th>
                <th>带教人次</th>
            </tr>
            <c:forEach items="${dataList}" var="data">
                <tr>
                    <td style="width: 120px;" title="${data.ORG_NAME}"><span style="display: -webkit-box;" class="title_div">${data.ORG_NAME}</span></td>
                    <td style="width: 100px;" title="${data.DEPT_NAME}"><span style="display: -webkit-box;" class="title_div">${data.DEPT_NAME}</span></td>
                    <td>${data.USER_NAME}</td>
                    <td>${data.USER_CODE}</td>
                    <td>${empty data.AVERAGE ? 0 : data.AVERAGE}</td>
                    <td>${empty data.isNotAudited ? 0 : data.isNotAudited}</td>
                    <td>${empty data.notAudited ? 0 : data.notAudited}</td>
                    <td>
                        <a onclick="activityDetails('${data.USER_FLOW}','${data.DEPT_FLOW}','教学活动','${param.operStartDate}','${param.operEndDate}');">${data.ACTIVITYNUM}</a>
                    </td>
                    <td>
                        <a onclick="details('${data.USER_FLOW}','带教学员','${operStartDate}','${operEndDate}');">${data.CURRSTUDENTHE}</a>
                    </td>
                    <td>
                        <a onclick="details('${data.USER_FLOW}','带教学员','${operStartDate}','${operEndDate}');">${data.CURRSTUDENTCOUNT}</a>
                    </td>
                        <%--<td><a onclick="details('${user.userFlow}','已出科学员','0');">${studentNumMap[afterKey]}</a></td>--%>
                        <%--<td>${studentNumMap[studentNumKey]}</td>--%>
                </tr>
            </c:forEach>
            <c:if test="${empty dataList}">
                <tr>
                    <td colspan="10" style="text-align: center;">无记录!</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
</body>
