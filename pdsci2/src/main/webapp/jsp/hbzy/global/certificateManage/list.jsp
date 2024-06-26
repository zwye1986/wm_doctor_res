<script type="text/javascript">
    $(document).ready(function () {

    });
</script>
<div class="search_table">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <colgroup>
            <col width="10%"/>
            <col width="10%"/>
            <col width="15%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="15%"/>
            <col width="10%"/>
        </colgroup>
        <tr>
            <th>结业年份</th>
            <th>姓名</th>
            <th>证件号码</th>
            <th>培训基地</th>
            <th>培训专业</th>
            <th>对应专业</th>
            <th>年级</th>
            <th>证书编号</th>
            <th>证书详情</th>
        </tr>
        <c:forEach items="${doctorList}" var="doctor">
            <tr>
                <td>${doctor.graduationYear}</td>
                <td>${doctor.sysUser.userName}</td>
                <td>${doctor.sysUser.idNo}</td>
                <td>${doctor.orgName}</td>
                <td>${doctor.catSpeName}</td>
                <td>${doctor.speName}</td>
                <td>${doctor.sessionNumber}</td>
                <td>${doctor.graduationCertificateNo}</td>
                <td>
                    <a style="cursor: pointer;"
                       href="<s:url value='/hbzy/certificateManage/showCertificate?recruitFlow=${doctor.recruitFlow}'/>"
                       target="_blank"><img src="<s:url value='/jsp/jsres/images/huixingzhen.png'/>"/></a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty doctorList}">
            <tr>
                <td colspan="10">无记录！</td>
            </tr>
        </c:if>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>

