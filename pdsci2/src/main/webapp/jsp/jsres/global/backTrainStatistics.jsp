<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
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
        <c:forEach items="${resDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if("${data}"=="${type.id}"){
            $("#"+"${data}").attr("checked","checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#"+"${type.id}").attr("checked","checked");
        </c:if>
        </c:forEach>
    });
    function checkAll(obj){
        var f=false;
        if($(obj).attr("checked")=="checked")
        {
            f=true;
        }
        $(".docType").each(function(){
            if(f)
            {
                $(this).attr("checked","checked");
            }else {
                $(this).removeAttr("checked");
            }

        });
    }
    function changeAllBox(){
        if($(".docType:checked").length==$(".docType").length)
        {
            $("#all").attr("checked","checked");
        }else{
            $("#all").removeAttr("checked");
        }
    }
    $(function () {
        findTrainCharts();
    });
    function findTrainCharts() {
        var orgLevelId = $("#orgLevelId").val();
        if (orgLevelId == "") {
            jboxTip("请选择基地类型！");
            return;
        }
        jboxPostLoad("doctorNum1", "<s:url value='/jsres/manage/findTrainCharts'/>", $("#doctorNumSearch").serialize(), true);
    }
</script>
<body>
<c:if test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope || 'quality' eq  sessionScope.userListScope) and (empty param.userFlow)}">
    <div class="main_bd">
        <div class="div_search">
            <form id="doctorNumSearch">
                <table>
                    <tr>
                        <td>基地类型：
                            <select name="orgLevel" id="orgLevelId" class="select" style="width:100px;">
                                <c:forEach items="${orgLevelEnumList}" var="level">
                                    <option value="${level.id}"
                                            <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
                                            <c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
                                    >${level.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>&#12288;培训年级：
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
                        </td>
                      <c:if test="${isQuality ne 'Y'}">
                        <td>&#12288;培训专业：
                            <input type="text" value="${param.speName}" class="input" name="speName"
                                   style="width: 100px;"/>
                        </td>
                      </c:if>
                    </tr>
                    <tr>
                        <td colspan="2">人员类型：
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td>&#12288;
                            <input class="btn_green" type="button" value="查&#12288;询" onclick="findTrainCharts();"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="index_table" id="doctorNum1">

        </div>
    </div>
</c:if>
</body>
