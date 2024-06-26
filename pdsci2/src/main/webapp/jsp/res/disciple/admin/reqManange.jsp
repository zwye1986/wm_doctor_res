<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
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
</style>
<script type="text/javascript">
    $(document).ready(function(){
        toPage(1);
    });
    function toPage(page) {
        var sessionNumber = $("#sessionNumber").val();
        if(!sessionNumber){
            jboxTip("年级不能为空！");
            return;
        }
        jboxStartLoading();
        jboxPostLoad("doctorListZi","<s:url value='/res/discipleAdminAudit/discipleReqInfoList'/>",$("#searchForm").serialize(),false);
    }
    function saveReq(trainingTypeId,sessionNumber,discipleTypeId,obj) {
        var reqNumber = $(obj).val();
        var regex = /^[\-\+]?\d+$/;
        if(reqNumber >= 0 & regex.test(reqNumber)){
            var url = "<s:url value='/res/discipleAdminAudit/updateDiscipleReq?discipleTypeId='/>" + discipleTypeId+ "&reqNumber=" + reqNumber+ "&trainingTypeId=" + trainingTypeId+ "&sessionNumber=" + sessionNumber;
            var data = $('#editForm').serialize();
            jboxStartLoading();
            jboxPost(url, data, function(resp) {
                if(resp == 'cannotInsert'){
                    jboxTip("该配置项要求数已存在请检查！");
                    jboxEndLoading();
                    return false;
                }
                jboxTip("操作成功！");
            }, null, false);
        }else {
            jboxTip("请输入非负整数！");
        }

    }
</script>

<div class="mainright" style="min-height: 300px;">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm">
                <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">培训专业：</label>
                        <select name="trainingTypeId" id="trainingTypeId" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${recDocCategoryEnumList}" var="category">
                                <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                                <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
                                    <option value="${category.id}" ${doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">年&#12288;&#12288;级：</label>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}"
                               readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
                    </div>
                    <div class="lastDiv">
                        <input class="searchInput" type="button" value="查&#12288;询" onclick="toPage();"/>
                    </div>
                </div>
            </form>
        </div>
        <div id="doctorListZi">
        </div>
    </div>
</div>
</html>