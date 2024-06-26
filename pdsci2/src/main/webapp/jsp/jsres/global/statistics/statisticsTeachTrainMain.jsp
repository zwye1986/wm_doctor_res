<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#trainingYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        toPage(1);
    });

    function search() {
        var url = "<s:url value='/jsres/statistic/statisticsTeachTrainList'/>";
        jboxPostLoad("contentDiv", url, $("#submitForm").serialize(), true);
    }

    function exportExcel() {
        var url = "<s:url value='/jsres/statistic/exportTeachTrainList'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#submitForm"), url, null, null, false);
        jboxEndLoading();
    }

    function changeSex(sexId) {
        if ("${userSexEnumMan.id}" == sexId) {
            $("#sexName").val("${userSexEnumMan.name}");
        }
        if ("${userSexEnumWoman.id}" == sexId) {
            $("#sexName").val("${userSexEnumWoman.name}");
        }
        if (sexId == "") {
            $("#sexName").val("");
        }
    }
    function toPage(page) {
        if (!page) {
            page = 1;
        }
        console.log(page);
        $("#currentPage").val(page);
        search();
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }

    function searchDeptList(orgFlow){
        jboxPost("<s:url value='/jsres/teacher/searchDeptList'/>?orgFlow="+orgFlow, null, function (resp) {
            $("#deptFlow").empty();
            $("#deptFlow").append("<option value=''>请选择</option>");
            if (null != resp && resp.length > 0) {
                for(var i= 0;i<resp.length;i++){
                    $("#deptFlow").append("<option value='"+resp[i].deptFlow+"'>"+resp[i].deptName+"</option>");
                }
            }
        },null,false);
    }

</script>
<div class="main_hd">
    <h2 class="underline">师资培训统计</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="submitForm">
            <input type="hidden" id="sexName" name="sexName" value="${param.sexName }">
            <input type="hidden" id="roleFlag" name="roleFlag" value="${param.roleFlag }">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage }">
            <table>
                <tr>
                    <td>
                        <label id="train">培训基地：</label>
                        <select class="select" id="orgFlow" name="orgFlow" style="width: 106px;margin-right: 21px" onchange="searchDeptList(this.value)">
                            <option value="" <c:if test="${empty param.orgFlow}">selected="selected"</c:if>>全部</option>
                            <c:forEach items="${orgs}" var="org">
                                <option value="${org.orgFlow}" <c:if test="${param.orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                        证书编号：<input type="text" value="${param.certificateNo}" class="input" name="certificateNo"
                                    style="width: 100px;"/>
                        &#12288;姓&#12288;&#12288;名：<input type="text" value="${param.doctorName}" class="input" name="doctorName"
                                                  style="width: 100px;"/>
                        &#12288;性&#12288;&#12288;别：&nbsp;
                        <select class="select" name="sexId" style="width: 100px" onchange="changeSex(this.value);">
                            <option value="">请选择</option>
                            <c:forEach items="${userSexEnumList }" var="sex">
                                <option value="${sex.id }"
                                        <c:if test="${sex.id eq userSexEnumUnknown.id}">style="display: none;"</c:if>
                                        <c:if test="${param.sexId eq sex.id}">selected="selected "</c:if>
                                >${sex.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        技术职称：<input type="text" value="${param.technicalTitle}" class="input" name="technicalTitle"
                                    style="width: 100px;margin-right: 9px; margin-left: 3px"/>
                        &#12288;培训年份：<input type="text" value="${param.trainingYear}" class="input" name="trainingYear" id="trainingYear"
                                            style="width: 101px;"/>
                        &#12288;科&#12288;&#12288;室：&nbsp;
                        <select class="select" id="deptFlow" name="deptFlow" style="width: 106px;margin-left: -3px;margin-right: 5px">

                        </select>
                        &#12288;专&#12288;&#12288;业：&nbsp;
                        <select class="select" name="speId" style="width: 100px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option <c:if test="${param.speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>&#12288;
                        <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>&#12288;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table" id="contentDiv" style="padding: 0 10px;">

    </div>
</div>