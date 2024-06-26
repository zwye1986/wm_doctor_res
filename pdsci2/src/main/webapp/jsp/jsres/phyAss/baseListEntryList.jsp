<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>

<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    table.grid th, table.grid td {
        padding: 0;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });

    function editPhyAssMain(type,planFlow) {
        var title = "新增";
        if (type == 'edit') {
            title = "修改";
        }else if (type=='add'){
            title = "新增";
        }
        var url = "<s:url value ='/jsres/phyAss/editPlannedReleaseMain'/>?type="+type+"&planFlow="+planFlow;
        jboxOpen(url, title+"培训计划", 1000, 700);
    }

    function baseListEntryUser(planFlow) {
        var url = "<s:url value ='/jsres/phyAss/baseListEntryUser'/>?planFlow="+planFlow;
        jboxOpen(url, "录入师资名单", 1000, 700);

    }

    //上报
    function appearUser(planFlow) {
        jboxConfirm("是否确认上报？",function () {
            jboxGet("<s:url value='/jsres/phyAss/appearUser'/>?planFlow="+planFlow,null,function(resp){
                toPage(1);
                jboxTip(resp);
            },null,false);
        });
    }

    function searchPlanUserList(type,planFlow) {
        var title='录入师资名单';
        if (type=='affirm'){
            title='确认师资名单';
        }
        var url = "<s:url value ='/jsres/phyAss/searchPlanUserList'/>?planFlow="+planFlow+"&type="+type;
        jboxOpen(url, title, 1000, 600);
    }
</script>
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="15%">培训计划</th>
                <th width="10%">培训专业</th>
                <th width="13%">报名日期</th>
                <th width="13%">培训日期</th>
                <th width="7%">录入人数</th>
                <th width="7%">确认人数</th>
                <th width="10%">状态</th>
                <th width="20%">操作</th>
            </tr>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="8">无记录！</td>
                </tr>
            </c:if>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="s">
                    <tr>
                        <td>
                            <a onclick="editPhyAssMain('show','${s.planFlow}')"> ${s.planContent}</a>
                        </td>
                        <td>${s.speName}</td>
                        <td>${s.enrollStartTime} <br>  ${s.enrollEndTime}</td>
                        <td>${s.planStartTime} <br> ${s.planEndTime}</td>
                        <td>
                            <a onclick="searchPlanUserList('entry','${s.planFlow}')"> ${s.entryNum}</a>
                        </td>
                        <td>
                            <a onclick="searchPlanUserList('affirm','${s.planFlow}')"> ${s.affirmNum}</a>
                        </td>
                        <td>
                                <c:if test="${s.planStatusId eq 'Publish' and nowDate > s.enrollEndTime}">
                                    已结束
                                </c:if>

                                <c:if test="${s.planStatusId eq 'Publish' and nowDate > s.enrollStartTime and nowDate < s.enrollEndTime and s.entryNum!='0'  and s.entryNum != s.appearNum}">
                                    待上报
                                </c:if>
                                <c:if test="${s.planStatusId eq 'Publish' and nowDate > s.enrollStartTime and nowDate < s.enrollEndTime and s.entryNum=='0'}">
                                    待录入
                                </c:if>
                                <c:if test="${s.planStatusId eq 'Publish' and nowDate > s.enrollStartTime and nowDate < s.enrollEndTime and s.entryNum!='0' and s.entryNum == s.appearNum}">
                                    已上报
                                </c:if>
                                <c:if test="${s.planStatusId eq 'Publish' and nowDate < s.enrollStartTime }">
                                    未开始
                                </c:if>
                                <c:if test="${s.planStatusId eq 'Staging' }">
                                    未开始
                                </c:if>

                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.enrollEndTime}">
                                    <input class="btn_green" type="button" value="查看" onclick="searchPlanUserList('entry','${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.enrollStartTime and nowDate < s.enrollEndTime and s.entryNum!='0'}">
                                    <input class="btn_green" type="button" value="录入" onclick="baseListEntryUser('${s.planFlow}');"/>
                                    <input class="btn_green" type="button" value="上报" onclick="appearUser('${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.enrollStartTime and nowDate < s.enrollEndTime and s.entryNum=='0'}">
                                    <input class="btn_green" type="button" value="录入" onclick="baseListEntryUser('${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.enrollStartTime and nowDate < s.enrollEndTime and s.entryNum == s.appearNum}">
                                    <input class="btn_green" type="button" value="录入" onclick="baseListEntryUser('${s.planFlow}');"/>
                                    <input class="btn_green" type="button" value="上报" onclick="appearUser('${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate < s.enrollStartTime }">
                                    <input class="btn_green" type="button" value="录入" onclick="baseListEntryUser('${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Staging' }">
                                    <input class="btn_green" type="button" value="录入" onclick="baseListEntryUser('${s.planFlow}');"/>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</div>
<div class="page" style="text-align: center">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
</div>
