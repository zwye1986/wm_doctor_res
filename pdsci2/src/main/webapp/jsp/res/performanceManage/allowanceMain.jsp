<%@include file="/jsp/common/doctype.jsp" %>
<html>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true" />
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        toPage(1);
    });
    function toPage(page) {
        $("#currentPage").val(page);
        var startDate=$("#startDate").val();
        var endDate=$("#endDate").val();
        if(startDate==""||startDate==undefined)
        {
            jboxTip("开始时间不得为空！！");
            return false;
        }
        if(endDate==""||endDate==undefined)
        {
            jboxTip("结束时间不得为空！！");
            return false;
        }
        if(startDate!=""&&endDate!=""&&endDate<startDate)
        {
            jboxTip("开始时间不得大于结束时间！！");
            return false;
        }
        jboxLoad("doctorListZi","<s:url value='/res/performanceManage/allowancePayment'/>?roleFlag=${param.roleFlag}&"+$("#searchForm").serialize(),true);
    }
    function exportExcel(){
        var url = "<s:url value='/res/performanceManage/exportPayment'/>?roleFlag=${param.roleFlag}";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

</script>

<div class="mainright" style="min-height: 300px;" >
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm">
                <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="queryDiv">

                    <div class="inputDiv">
                        <label class="qlable">开始月份：</label>
                        <input type="text" id="startDate" name="startDate" value="${startDate}" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">结束月份：</label>
                        <input type="text" id="endDate" name="endDate" value="${endDate}" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input type="text" id="doctorName" name="doctorName" value="" class="qtext">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">角&#12288;&#12288;色：</label>
                        <select name="roleFlow" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${roleMap}" var="role">
                                <option value="${role.key}" ${param.role eq role.key?'selected':''}>${role.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <%--<div class="inputDiv">--%>
                        <%--<label class="qlable">专业基地：</label>--%>
                        <%--<select name="resTrainingSpeId" class="qselect">--%>
                            <%--<option value="">全部</option>--%>
                            <%--<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
                                <%--<option value="${dict.dictId}" ${param.resTrainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <div class="lastDiv" style="max-width: 180px;min-width: 180px;">
                        <input class="search" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        <input class="search" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                    </div>
                </div>
            </form>
        </div>
        <div id="doctorListZi">
        </div>
    </div>
</div>
</html>