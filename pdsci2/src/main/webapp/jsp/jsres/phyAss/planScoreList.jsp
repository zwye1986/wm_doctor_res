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
        var url = "<s:url value ='/jsres/phyAss/editPlannedReleaseMain'/>?type="+type+"&planFlow="+planFlow;
        jboxOpen(url, "查看培训计划", 1000, 700);
    }

    function planApperMain(planFlow,userStatus,type){
        var url = "<s:url value ='/jsres/phyAss/planApperMain'/>?&planFlow=" + planFlow+"&type="+type+"&userStatus="+userStatus;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "人员信息", 1300, 670, false);
    }

</script>
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="20%">培训计划</th>
                <th width="10%">培训专业</th>
                <th width="14%">报名日期</th>
                <th width="14%">培训日期</th>
                <th width="8%">报名基地数</th>
                <th width="8%">报名总人数</th>
                <th width="8%">确认人数</th>
                <th width="6%">状态</th>
                <th width="10%">操作</th>
            </tr>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="9">无记录！</td>
                </tr>
            </c:if>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="s">
                    <tr>
                        <td><a onclick="editPhyAssMain('show','${s.planFlow}')"> ${s.planContent}</a></td>
                        <td>${s.speName}</td>
                        <td>${s.enrollStartTime} <br>  ${s.enrollEndTime}</td>
                        <td>${s.planStartTime} <br> ${s.planEndTime}</td>
                        <td>${s.orgNum}</td>
                        <td>${s.appearNum}</td>
                        <td>${s.affirmNum+s.removeNum}</td>
                        <td>
                            <c:choose>
                                <c:when test="${s.appearNum eq '0'}">
                                    未确认
                                </c:when>
                                <c:when test="${s.toAffirmNum ne '0'}">
                                    确认中
                                </c:when>
                                <c:when test="${s.appearNum ne '0' and s.toAffirmNum eq '0'}">
                                    已确认
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${s.appearNum eq '0'}">
                                    <input class="btn_green" type="button" value="确认" onclick="planApperMain('${s.planFlow}','toAffirm','affirm')";/>
                                </c:when>
                                <c:when test="${s.toAffirmNum ne '0'}">
                                    <input class="btn_green" type="button" value="确认" onclick="planApperMain('${s.planFlow}','toAffirm','affirm')";/>
                                </c:when>
                                <c:when test="${s.appearNum ne '0' and s.toAffirmNum eq '0'}">
                                    <input class="btn_green" type="button" value="查看" onclick="planApperMain('${s.planFlow}','affirm','affirm')";/>
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
