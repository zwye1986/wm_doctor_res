<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训管理平台</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>
<style>
    .grid td{
        border-bottom: inherit;
    }
    .div_search{
        padding-left: 0;
        margin-bottom: 15px;
    }
</style>
<script>
    $(function () {
        $(".div_table").css("height", (window.innerHeight - 50) + "px").css("display", "");
       toggleJointOrg();
    });

    function toggleJointOrg() {
        if($("#jointOrgFlag").val() == 'Y') {
            $(".jointSub").css("display", "");
            $(".one-level-bottom").css("border-bottom", 'inherit');
            $(".two-level-bottom").css("border-bottom", '1px solid black');
        }else {
            $(".jointSub").css("display", "none");
            $(".two-level-bottom").css("border-bottom", 'inherit');
            $(".one-level-bottom").css("border-bottom", '1px solid black');
        }
    }

    function showProfBaseInfo(e) {
        var target = (e || window.event).target;
        var speId = $(target).attr("dataspeid");
        var orgFlow = $(target).attr("dataorgflow");
        var sessionNumber = $(target).attr("datasessionnumber");
        var url = "<s:url value ='/jsres/base/orgShowSpeInfo'/>?onlyRead=Y&ishos=Y&speFlow=" + speId + "&orgFlow=" + orgFlow + "&sessionNumber="+sessionNumber;
        var iframe = "<iframe name='jbox-second-iframe' id='jbox-second-iframe' width='" + (window.innerWidth) + "' height='" + (window.innerHeight) + "' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, '专业基地', window.innerWidth, window.innerHeight, 'jbox-second-iframe', true);
    }
</script>
</head>
<body>
<div class="div_table" style="height: auto; overflow: auto; display: none">
<div id="speIdOrgDiv" style="" class="div_search">
    <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">协同单位</label>
    <select name="jointOrgFlag" id="jointOrgFlag" onchange="toggleJointOrg()" class="select">
        <option value="Y" selected>参与展示</option>
        <option value="N" >不参与展示</option>
    </select>
</div>
<div id="allInfo" style="overflow-y: auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th>基地编号</th>
            <th>基地名称</th>
            <th>住院医师(${sessionNumber}在培)</th>
            <th>在校专硕(${sessionNumber}在培)</th>
            <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
                <th>住院医师(在培)</th>
                <th>在校专硕(在培)</th>
                <th>在培人员数</th>
                <th>培训容量</th>
                <th>最小培训容量</th>
                <th>容量使用率</th>
            </c:if>
        </tr>
        <c:forEach items="${orgSpeList}" var="orgSpe">
            <tr class="one-level-bottom <c:if test='${empty orgSpe.jointOrgList}'>two-level-bottom</c:if>">
                <td>${orgSpe.baseCode}</td>
                <td style="text-align: left"><a onclick="showProfBaseInfo(event)" dataspeid="${orgSpe.speId}" datasessionnumber="${sessionNumber}"
                    dataorgflow="${orgSpe.orgFlow}">${orgSpe.orgName}</a></td>
                <td><span class="curInHospitalDoctors">${orgSpe.curInHospitalDoctors}</span>人</td>
                <td><span class="curInCollegeMasters">${orgSpe.curInCollegeMasters}</span>人</td>
                <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
                    <td><span class="inHospitalDoctors">${orgSpe.inHospitalDoctors}</span>人</td>
                    <td><span class="inCollegeMasters">${orgSpe.inCollegeMasters}</span>人</td>
                    <td><span class="inTrains">${orgSpe.inTrains}</span>人</td>
                    <td>
                        <span class="baseCapacity" id="baseCapacity${orgSpe.speId}">${orgSpe.baseCapacity}</span>人
                    </td>
                    <td><span class="minRecruitCapacity">${orgSpe.minRecruitCapacity}</span>人</td>
                    <td><span class="trainingCapacityUsePer">${orgSpe.trainingCapacityUsePer}</span>%</td>
                </c:if>
            </tr>
            <c:if test="${not empty orgSpe.jointOrgList}">
                <c:forEach items="${orgSpe.jointOrgList}" var="orgSpeSub" varStatus="varStat">
                <tr class="<c:if test='${varStat.last}'>two-level-bottom</c:if> jointSub">
                    <td>${orgSpeSub.baseCode}</td>
                    <td style="text-align: left">------<a onclick="showProfBaseInfo(event)" dataspeid="${orgSpeSub.speId}" datasessionnumber="${sessionNumber}"
                          dataorgflow="${orgSpeSub.orgFlow}">${orgSpeSub.orgName}</a></td>
                    <td><span class="curInHospitalDoctors">${orgSpeSub.curInHospitalDoctors}</span>人</td>
                    <td><span class="curInCollegeMasters">${orgSpeSub.curInCollegeMasters}</span>人</td>
                    <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
                        <td><span class="inHospitalDoctors">${orgSpeSub.inHospitalDoctors}</span>人</td>
                        <td><span class="inCollegeMasters">${orgSpeSub.inCollegeMasters}</span>人</td>
                        <td><span class="inTrains">${orgSpeSub.inTrains}</span>人</td>
                        <td>
                            <span class="baseCapacity" id="baseCapacity${orgSpeSub.speId}">${orgSpeSub.baseCapacity}</span>人
                        </td>
                        <td><span class="minRecruitCapacity">${orgSpeSub.minRecruitCapacity}</span>人</td>
                        <td><span class="trainingCapacityUsePer">${orgSpeSub.trainingCapacityUsePer}</span>%</td>
                    </c:if>
                </tr>
                </c:forEach>
            </c:if>
        </c:forEach>
    </table>
</div>
</div>
</body>
</html>