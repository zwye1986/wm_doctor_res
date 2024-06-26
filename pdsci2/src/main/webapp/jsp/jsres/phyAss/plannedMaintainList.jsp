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

      function editPlanUserInfo(recordFlow,planFlow) {
          var url = "<s:url value ='/jsres/phyAss/editPlanUserInfo'/>?recordFlow="+recordFlow+"&planFlow="+planFlow;
          jboxOpen(url, "编辑", 900, 400);
      }
</script>
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="10%">登录名</th>
                <th width="10%">姓名</th>
                <th width="5%">性别</th>
                <th width="25%">培训计划</th>
                <th width="10%">培训专业</th>
                <th width="10%">科室</th>
                <th width="10%">角色</th>
                <th width="10%">状态</th>
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
                        <td>${s.doctorCode}</td>
                        <td>${pdfn:replaceNameX(s.doctorName)}</td>
                        <td>${s.sexName}</td>
                        <td><a onclick="editPhyAssMain('show','${s.planFlow}')"> ${s.planContent}</a></td>
                        <td title="${s.speName}">${pdfn:cutString(s.speName,6,true,3)}</td>
                        <td title="${s.deptName}">${pdfn:cutString(s.deptName,6,true,3)}</td>
                        <td>${s.roleName}</td>
                        <td>${empty s.appearFlag?"未上报":"已上报"}</td>
                        <td>
                            <input class="btn_green" type="button" value="编辑" onclick="editPlanUserInfo('${s.recordFlow}','${s.planFlow}');"/>
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
