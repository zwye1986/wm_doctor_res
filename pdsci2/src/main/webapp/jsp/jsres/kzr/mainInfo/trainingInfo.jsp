<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
    function showTraining(deptFlow, isJoin, speFlow) {
        var url = "<s:url value ='/jsres/base/showTrainingDetailInfo'/>?onlyRead=${ishos}&deptFlow=" + deptFlow + "&orgFlow=${orgFlow}&isJoin=Y&speFlow=" + speFlow+"&isglobal=Y&sessionNumber=${sessionNumber}";
        jboxLoad("trainingContent", url, false);
    }

    $(document).ready(function(){
        <c:if test="${resDeptRelStdDeptVOList != null and resDeptRelStdDeptVOList.size() > 0}">
        showTraining('${resDeptRelStdDeptVOList[0].deptFlow}', '${isJoin}', '${speFlow}');
        </c:if>
    });
</script>
<div class="main_hd">
    </form>
    <div class="title_tab" id="toptab" style="margin-top: 5px;">
        <span style="color: #000000;font: 14px 'Microsoft Yahei';font-weight: 400;">轮转科室选择：</span>
        <select id="deptFlow" class="select" style="color: #000000;font: 14px 'Microsoft Yahei';font-weight: 400;" onchange="showTraining(this.value, '${isJoin}', '${speFlow}');">
            <c:forEach var="dept" items="${resDeptRelStdDeptVOList}">
                <option value="${dept.deptFlow}" >${dept.deptName}</option>
            </c:forEach>
        </select>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="trainingContent">
    </div>
</div>
