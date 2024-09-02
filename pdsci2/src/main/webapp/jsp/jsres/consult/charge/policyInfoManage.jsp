<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="consult" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="false"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript">
    $(function() {
        $(".filter").click(function() {
            $(this).addClass("active").siblings(".active").removeClass("active")
        })

    })

    function deleteConsult(consultInfoFlow) {
        jboxConfirm("删除所选问答吗？", function () {
            var url = "<s:url value='/jsres/consult/delete'/>?consultInfoFlow="+consultInfoFlow;
            jboxPost(url,null,function (resp) {
                if ("${GlobalConstant.DELETE_SUCCESSED}" == resp){
                    jboxTip("删除成功!");
                    toPage(1);
                    setTimeout(function(){jboxClose()},2000);
                }else {
                    jboxTip("删除失败!");
                }
            }, null, false);
        });
    }

    function editConsultForm(consultInfoFlow) {
        var url = "<s:url value='/jsres/consult/editConsultForm'/>?consultInfoFlow="+consultInfoFlow;
        jboxOpen(url, "编辑问答", 1100, 650);
    }

    function detailConsult(consultInfoFlow) {
        var url = "<s:url value='/jsres/consult/detailConsult?consultInfoFlow='/>" + consultInfoFlow;
        jboxOpen(url,"问答详情",800,450);
    }

    function toPage(page) {
        jboxStartLoading();
        page=page||1;
        $("#currentPage").val(page);
        var orderBy = $("#orderBy").attr("value");
        var url = "<s:url value='/jsres/consult/policyInfoManage'/>?orderBy="+orderBy+"&currentPage="+page
        jboxPostLoad("searchConent", url, $("#searchForm").serialize(), true);
    }
</script>

<div class="cont-box bordbox">
    <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
    <ul class="poli-cont">
        <c:forEach items="${consultInfos}" var="consultInfo" varStatus="status">
            <li>
                <label class="flex align-c">
                    <input class="check" type="checkbox" name="mychk" value="${consultInfo.consultInfoFlow}"/>
                </label>
                <p class="poli-text fs14">
                        ${consultInfo.consultQuestion}
                </p>
                <div class="flex">
                    <div class="poli-ques">答案：</div>
                    <div style="width: 700px;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 1;overflow: hidden;">
                            ${consultInfo.consultAnswer}
                    </div>
                    <span>
                        <a onclick="detailConsult('${consultInfo.consultInfoFlow}')"><font color="#54B2E5">查看详情</font></a>
                    </span>
                </div>
                <div class="cons-lit align-c">
                    <div>来自:</div>
                    <span>${consultInfo.chargeName}</span>
                    <div>浏览次数:</div>
                    <span>${consultInfo.consultVisitNumber}次</span>
                    <div>问题添加时间:</div>
                    <span>${consultInfo.consultQuestionCreateTime}</span>
                    <span >|</span>
                    <i class="edit" onclick="editConsultForm('${consultInfo.consultInfoFlow}')"></i>
                    <i class="delete" onclick="deleteConsult('${consultInfo.consultInfoFlow}')"></i>
                </div>
            </li>
        </c:forEach>
        <c:if test="${empty consultInfos}">
            <div class="poli-noserch fs14">无查询结果</div>
        </c:if>
    </ul>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(consultInfos)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
