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
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#graduationTime').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#startDate').datepicker();
        $('#endDate').datepicker();
        changeTrainSpes();
    });

    $(function(){
        if($("#trainOrg").length){
            $("#trainOrg").likeSearchInit({
                clickActive:function(flow){
                    $("."+flow).show();
                }
            });
        }
    });

    function search(page) {
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if(startDate>endDate){
            jboxTip("开始日期不能大于结束日期");
            return;
        }
        if (!page) {
            page = 1;
        }
        $("#currentPage").val(page);
        if($("#orgFlow").val()==""){
            $("#trainOrg").val("");
        }
        var data = $("#searchForm").serialize();
        var url = "<s:url value='/jsres/doctorRecruit/cycleForUniList'/>";
        jboxStartLoading();
        jboxPost(url,data,function(resp){
            $("#content").html(resp);
            jboxEndLoading();
        },null,false);
    }
    function toPage(page) {
        search(page);
    }
    function showDetails(resultFlow,schStartDate,schEndDate){
        jboxStartLoading();
        var url ="<s:url value='/jsres/doctorRecruit/cycleDeptDetails'/>?resultFlow="+resultFlow+"&schStartDate="+schStartDate+"&schEndDate="+schEndDate;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'详细信息',700,250);
        jboxEndLoading();
    }

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
    <h2 class="underline">医师轮转查询</h2>
</div>

<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
            <input id="itemsDate" type="hidden" schStartDate="${schStartDate}" schEndDate="${schEndDate}" />
            <c:set var="schoolParam" value="${school}"></c:set>
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
                        培&nbsp;&nbsp;训&nbsp;&nbsp;类&nbsp;&nbsp;别&nbsp;：
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
                        证件号码：
                        <input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>
                    </td>
                    <td>
                        培训基地：&nbsp;
                        <select class="select" style="width: 106px" name="orgFlow" id="orgFlow" onchange="changeTrainSpes()">
<%--                            <option></option>--%>
                            <c:forEach items="${orgs}" var="org" varStatus="status">
                                <option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>结业考核年份：&nbsp;<input type="text" id="graduationTime" name="graduationTime" value="${param.graduationTime}"
                               readonly="readonly" class="input" style="width: 100px;margin-left: 0px"/></td>
                    <td>
                        培训年限：&nbsp;
                        <select name="trainingYears" class="select" style="width:107px;">
                            <option value="">全部</option>
                            <option value="OneYear" <c:if test="${param.trainingYears eq 'OneYear'}">selected</c:if>>一年</option>
                            <option value="TwoYear" <c:if test="${param.trainingYears eq 'TwoYear'}">selected</c:if>>两年</option>
                            <option value="ThreeYear" <c:if test="${param.trainingYears eq 'ThreeYear'}">selected</c:if>>三年</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>开始日期：
                        <input style="width:100px" name="startDate" id="startDate" type="text" readonly="readonly"
                               value="${empty param.startDate?startDate:param.startDate}" class="input"/>
                    </td>
                    <td>
                        结束日期：
                        <input style="width:100px" name="endDate" id="endDate" type="text" readonly="readonly"
                               value="${empty param.endDate?endDate:param.endDate}" class="input"/>
                    </td>
                    <td <c:if test="${not empty schoolParam}">style="display: none" </c:if>>
                        院&#12288;&#12288;&#12288;&nbsp;&nbsp;校：
                        <input type="text" name="school" value="${param.school}" class="input" style="width: 100px;"/>
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
                        <th style="width:100px;min-width: 100px;">院校</th>
                        <th style="width:100px;min-width: 100px;">培训年限</th>
                        <c:forEach items="${titleDate}" var="title">
                            <th style="width:100px;min-width: 100px;">${title}</th>
                        </c:forEach>
                    </tr>
                    <c:forEach items="${docCycleList}" var="docCycle">
                        <tr>
                            <td>${docCycle.userName}</td>
                            <td>${docCycle.trainingSpeName}</td>
                            <td>${docCycle.sessionNumber}</td>
                            <td>${docCycle.SCHOOL}</td>
                            <td>
                            <c:if test="${'OneYear' eq docCycle.trainingYears}">一年</c:if>
                            <c:if test="${'TwoYear' eq docCycle.trainingYears}">两年</c:if>
                            <c:if test="${'ThreeYear' eq docCycle.trainingYears}">三年</c:if>
                            </td>
                            <c:forEach items="${titleDate}" var="title">
                                <c:set var="key" value="${docCycle.doctorFlow}${title}"/>
                                <c:set var="currDate" value="${pdfn:getCurrDate()}"/>
                                    <td>
                                        <c:if test="${!empty resultListMap[key]}">
                                            <c:forEach items="${resultListMap[key]}" var="result">
                                                <div>
                                                    [<a
                                                            <c:if test="${result.schStartDate<=currDate && currDate<=result.schEndDate}">
                                                                style="color: #f8da4e"</c:if>
                                                            <c:if test="${!empty recMap[result.resultFlow] &&currDate>=result.schEndDate}">
                                                                style="color: #00B83F"</c:if>
                                                            <c:if test="${empty recMap[result.resultFlow] &&currDate>=result.schEndDate}">
                                                                style="color: #ee0101"</c:if>
                                                    >${result.schDeptName}</a>]
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                            <c:if test="${empty resultListMap[key]}">-</c:if>
                                    </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty docCycleList}">
                        <tr>
                            <td colspan="10">无记录！</td>
                        </tr>
                    </c:if>
                </table>
            </div>
    <div style="padding-left: 40px;width:50%">
        <span style="background-color: #00B83F">&#12288;&nbsp;</span><span>表示:按计划应出科且已上传出科考核表</span><br/>
        <span style="background-color: #f8da4e">&#12288;&nbsp;</span><span>表示:按计划正在轮转,不判断是否上传出科考核表</span><br/>
        <span style="background-color: #ee0101">&#12288;&nbsp;</span><span>表示:按计划应出科但未上传出科考核表</span><br/>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(docCycleList)}" scope="request"></c:set>
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


