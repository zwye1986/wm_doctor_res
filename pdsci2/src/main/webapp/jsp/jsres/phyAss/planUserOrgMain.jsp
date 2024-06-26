<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
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
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        toPage();
    });

    function toPage(page) {
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/phyAss/planUserOrgList'/>", $("#searchForm").serialize(), false);
    }


    function expertphyAssDoctorList() {
        var url = "<s:url value='/jsres/phyAss/expertPlanUserOrgList'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <h2 class="underline"></h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">
                    <nobr>培&ensp;训&ensp;计&ensp;划&ensp;：</nobr>
                </td>
                <td>
                    <select name="planFlow" id="planFlow" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <c:forEach items="${contentList}" var="c">
                            <option value="${c.planFlow}" ${planFlow eq c.planFlow?'selected':''}>${c.planContent}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="td_left">
                    <nobr>培&ensp;训&ensp;专&ensp;业&ensp;：</nobr>
                </td>
                <td>
                    <select name="speId" id="speId" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <option value="${dict.dictId}" ${speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>

                <td class="td_left">
                    <nobr>科&#12288;&#12288;&ensp;&ensp;&ensp;&ensp;室：</nobr>
                </td>
                <td>
                    <select name="deptFlow" id="deptFlow" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <c:forEach items="${depts}" var="dept">
                            <option value="${dept.deptFlow}"
                                    <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td  class="td_left">师&ensp;资&ensp;等&ensp;级&ensp;：</td>
<%--                <td>--%>
<%--                    <select name="gradeId" id="gradeId" class="select" style="width: 150px;">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <option value="ordinary">普通师资</option>--%>
<%--                        <option value="commonly">一般师资</option>--%>
<%--                        <option value="other">其他师资</option>--%>
<%--                    </select>--%>
<%--                </td>--%>
                <td>
                    <select name="gradeId" id="gradeId" class="select" style="width: 150px;">
                        <option value="">全部</option>
                        <option value="collegeFaculty">院级师资</option>
                        <option value="municipalFaculty">市级师资</option>
                        <option value="provincialFaculty">省级师资</option>
                        <option value="nationalFaculty">国家级师资</option>
                        <option value="other">其他师资</option>
                    </select>
                </td>
                <td class="td_left">
                    <nobr>姓&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;名：</nobr>
                </td>
                <td>
                    <input style="width: 145px;margin-left: 0px" class="input" type="text" value="${doctorName}"  name="doctorName"/>
                </td>
                <td class="td_left">
                    <nobr>证&ensp;书&ensp;编&ensp;号&ensp;：</nobr>
                </td>
                <td>
                    <input style="width: 145px" class="input" type="text" value="${certificateNo}"  name="certificateNo"/>
                </td>
            </tr>
            <tr>
                <td class="td_left">
                    <nobr>开&ensp;始&ensp;日&ensp;期&ensp;：</nobr>
                </td>
                <td>
                    <input name="startTime"  style="margin-left: 0px;width: 145px;" placeholder="证书有效开始时间"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="startTime"
                           value="${startTime}" class="input">
                </td>
                <td class="td_left">
                    <nobr>结&ensp;束&ensp;日&ensp;期&ensp;：</nobr>
                </td>
                <td>
                    <input name="endTime"  style="margin-left: 0px;width: 145px;" placeholder="证书有效结束时间"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="endTime"
                           value="${endTime}" class="input">
                </td>

                <td colspan="2">
                    <input class="btn_green" type="button"  value="查&#12288;询" onclick="toPage(1);"/>
                    <input class="btn_green" type="button" style="margin-left: 27px" value="导&#12288;出" onclick="expertphyAssDoctorList();"/>
                </td>
            </tr>
        </table>

    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>