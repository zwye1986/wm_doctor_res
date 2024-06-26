<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#startDate').datepicker();
        $('#endDate').datepicker();
        changeTrainSpes();
    });
    function search(page) {
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if(startDate>endDate){
            jboxTip("开始日期不能大于结束日期");
            return;
        }
        jboxStartLoading();
        if (!page) {
            page = 1;
        }
        $("#currentPage").val(page);
        if($("#orgFlow").val()==""){
            $("#trainOrg").val("");
        }
        var data = $("#searchForm").serialize();
        cycleResults(data);
        jboxEndLoading();
    }
    function toPage(page) {
        search(page);
    }
    function resultsDetails(resultFlow){
        var url = "<s:url value='/jsres/doctorRecruit/resultsDetails'/>?resultFlow="+resultFlow;
        jboxOpen(url,"详细信息",900,550,true)
    }
    $(function(){
        if($("#trainOrg").length){
            $("#trainOrg").likeSearchInit({
                clickActive:function(flow){
                    $("."+flow).show();
                }
            });
        }
    });
    function changeStatus(){
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
            $("#orgFlow").val("");
        }
    }
    function changeTrainSpes(){
        //清空原来专业！！！
        var trainCategoryid=$("#trainingTypeId").val();
        if(trainCategoryid==""){
            $("select[name=trainingSpeId] option[value != '']").remove();
            return false;
        }
        $("select[name=trainingSpeId] option[value != '']").remove();
        $("#"+trainCategoryid+"_select").find("option").each(function(){
            $(this).clone().appendTo($("#trainingSpeId"));
        });
        return false;
    }
</script>

<div class="main_hd">
    <h2 class="underline">医师出科成绩查询</h2>
</div>

<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
            <table style="width: 100%">
                <tr>
                    <td>
                        姓&#12288;&#12288;名：
                        <input type="text" name="userName" class="input" value="${param.userName}" style="width: 100px;"/>
                    </td>
                    <td>
                        年&#12288;&#12288;级：&nbsp;
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"readonly="readonly" class="input" style="width: 100px;margin-left: 0px"/>
                    </td>
                    <td>
                        培训类别&nbsp;：
                        <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
                            <option value="">请选择</option>
                            <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
                                <c:choose>
                                    <c:when test="${empty param.trainingTypeId}">
                                        <option value="${trainCategory.id}" <c:if test="${'DoctorTrainingSpe' eq trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                    </c:when>
                                    <c:when test="${!empty param.trainingTypeId}">
                                        <option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        培训专业：&nbsp;
                        <select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 107px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
                                <option value="${spe.dictId}"
                                        <c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if>
                                >${spe.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        培训基地：&nbsp;
                        <select class="select" style="width: 106px" name="orgFlow" id="orgFlow" onchange="changeTrainSpes()">
                            <option></option>
                            <c:forEach items="${orgs}" var="org" varStatus="status">
                                <option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>开始日期：
                        <input style="width:100px" name="startDate" id="startDate" type="text" readonly="readonly"
                               value="${empty param.startDate?startDate:param.startDate}" class="input"/>
                    </td>
                    <td>
                        结束日期：
                        <input style="width:100px" name="endDate" id="endDate" type="text" readonly="readonly"
                               value="${empty param.endDate?endDate:param.endDate}" class="input"/>
                    </td>
                    <td><input class="btn_green" type="button" value="查&#12288;询" onclick="search(1);"/></td>
                </tr>
            </table>
        </form>
    </div>


    <div class="basic" style="margin: 0 20px;width: 980px;overflow: auto;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width:100px;min-width: 100px;">姓名</th>
                <th style="width:100px;min-width: 100px;">专业</th>
                <th style="width:100px;min-width: 100px;">年级</th>
                <c:forEach items="${titleDate}" var="title">
                    <th style="width:80px;min-width: 80px;">${title}</th>
                </c:forEach>
            </tr>
            <c:forEach var="docResults" items="${docResultsList}">
                <tr>
                    <td>${docResults.userName}</td>
                    <td>${docResults.trainingSpeName}</td>
                    <td>${docResults.sessionNumber}</td>
                    <c:forEach items="${titleDate}" var="title">
                        <td>
                            <c:set var="key" value="${docResults.doctorFlow}${title}"/>
                            <c:forEach var="result" items="${resultListMap[key]}">
                                <div>[<a onclick="resultsDetails('${result.resultFlow}')">${result.schDeptName}</a>]</div>
                            </c:forEach>
                            <c:if test="${empty resultListMap[key]}">-</c:if>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <c:if test="${empty docResultsList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(docResultsList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
    <div style="display: none;">
        <select id="WMFirst_select">
            <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
                <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="WMSecond_select">
            <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
                <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="AssiGeneral_select">
            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="DoctorTrainingSpe_select">
            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>

    </div>
</div>


