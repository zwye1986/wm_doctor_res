<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#assignYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
        toPage();
    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("achievementContent2","<s:url value='/jsres/doctorRecruit/schedulingSearchDoctorListAcc'/>",$("#searchForm").serialize(),false);
    }
</script>

<div class="div_search" style="box-sizing: border-box;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td>姓名：
                    <input type="text" name="userName"  class="input"   value="${userName}"/>
                </td>
                <td>
                    人员类型：
                    <select name="doctorTypeId" class="select" style="width: 150px;">
                        <option value="">请选择</option>
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <option value="${type.id}" ${type.id eq doctorTypeId?'selected':'' }>${type.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    专业：
                    <select name="trainingSpeId" class="select" id="trainingSpeId" style="width: 150px;">
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <option value="${dict.dictId}" <c:if test="${trainingSpeId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    年级：
                    <input class="input" name="assignYear" id="assignYear" readonly="readonly" value="${param.assignYear}" type="text"/>
                </td>
                <td>时&#12288;&#12288;间：
                    <input type="text" name="searchTime" id="ym" class="input"  style="width: 145px;"  value="${searchTime}"
                           onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                </td>
                <td>
                    <input type="button" class="btn_green" onclick="toPage(1);" value="查&#12288;询"/>
                </td>
            </tr>

        </table>
    </form>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="achievementContent2">
    </div>
</div>


