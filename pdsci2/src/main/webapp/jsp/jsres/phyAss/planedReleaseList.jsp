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



    function editPhyAssMain(type,planFlow,isExpert) {
        var title = "新增";
        if (type == 'edit') {
            title = "修改";
        }else if (type=='add'){
            title = "新增";
        }
        var url = "<s:url value ='/jsres/phyAss/editPlannedReleaseMain'/>?type="+type+"&planFlow="+planFlow+"&isExpert="+isExpert;
        jboxOpen(url, title+"培训计划", 1000, 700);
    }

    function delPhyAss(planFlow) {
        jboxConfirm("确定删除该记录？",function(){
            var url = "<s:url value ='/jsres/phyAss/delPhyAss'/>?planFlow="+planFlow;
            jboxPost(url,null,function(resp){
                if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
                    toPage(1);
                }
            },null,true);
        },null);
    }
</script>
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="20%">培训计划</th>
                <th width="15%">培训专业</th>
                <th width="15%">报名日期</th>
                <th width="15%">培训日期</th>
                <th width="10%">状态</th>
                <th width="25%">操作</th>
            </tr>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="6">无记录！</td>
                </tr>
            </c:if>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="s">
                    <tr>
                        <td><a onclick="editPhyAssMain('show','${s.planFlow}','Y')"> ${s.planContent}</a></td>
                        <td>${s.speName}</td>
                        <td>${s.enrollStartTime} <br>  ${s.enrollEndTime}</td>
                        <td>${s.planStartTime} <br> ${s.planEndTime}</td>
                        <td>
                            <c:choose>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.planEndTime }">
                                    培训结束
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.planStartTime and nowDate < s.planEndTime }">
                                    培训中
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.enrollEndTime and nowDate < s.planStartTime }">
                                    报名结束
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.enrollStartTime and nowDate < s.enrollEndTime }">
                                    报名中
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate < s.enrollStartTime }">
                                    未开始
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Staging' }">
                                    暂存
                                </c:when>
                            </c:choose>

                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate < s.enrollStartTime }">
                                    <input class="btn_green" type="button" value="编辑" onclick="editPhyAssMain('edit','${s.planFlow}');"/>
                                    <input class="btn_green" type="button" value="删除" onclick="delPhyAss('${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Staging' }">
                                    <input class="btn_green" type="button" value="编辑" onclick="editPhyAssMain('edit','${s.planFlow}');"/>
                                    <input class="btn_green" type="button" value="删除" onclick="delPhyAss('${s.planFlow}');"/>
                                </c:when>

                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.planEndTime }">
                                    <input class="btn_green" type="button" value="查看" onclick="editPhyAssMain('show','${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.planStartTime and nowDate < s.planEndTime }">
                                    <input class="btn_green" type="button" value="查看" onclick="editPhyAssMain('show','${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.enrollEndTime and nowDate < s.planStartTime }">
                                    <input class="btn_green" type="button" value="查看" onclick="editPhyAssMain('show','${s.planFlow}');"/>
                                </c:when>
                                <c:when test="${s.planStatusId eq 'Publish' and nowDate > s.enrollStartTime and nowDate < s.enrollEndTime }">
                                    <input class="btn_green" type="button" value="查看" onclick="editPhyAssMain('show','${s.planFlow}');"/>
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
