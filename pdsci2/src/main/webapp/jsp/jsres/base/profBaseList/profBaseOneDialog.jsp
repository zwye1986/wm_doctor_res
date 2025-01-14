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
            $(".two-level-bottom").css("border-bottom", '1px solid #e7e7eb');
        }else {
            $(".jointSub").css("display", "none");
            $(".two-level-bottom").css("border-bottom", 'inherit');
            $(".one-level-bottom").css("border-bottom", '1px solid #e7e7eb');
        }
    }

    function showProfBaseInfo(e) {
        var target = (e || window.event).target;
        var speId = $(target).attr("dataspeid");
        var orgFlow = $(target).attr("dataorgflow");
        var orgName = $(target).attr("dataorgname");
        var url = "<s:url value ='/jsres/base/orgShowSpeInfo'/>?onlyRead=Y&ishos=Y&speFlow=" + speId + "&orgFlow=" + orgFlow;
        var iframe = "<iframe name='jbox-second-iframe' id='jbox-second-iframe' width='" + (window.innerWidth) + "' height='" + (window.innerHeight) + "' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, orgName + " " + '${speName}专业基地('+ speId +')', window.innerWidth, window.innerHeight, 'jbox-second-iframe', true);
    }
</script>
</head>
<body>
<div class="div_table" style="height: auto; overflow: auto; display: none">
    <div id="speIdOrgDiv" style="" class="div_search">
        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">协同单位</label>
        <select name="jointOrgFlag" id="jointOrgFlag" onchange="toggleJointOrg()" class="select">
            <option value="Y">参与展示</option>
            <option value="N" selected>不参与展示</option>
        </select>
    </div>
    <div id="allInfo" style="overflow-y: auto;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th rowspan="2">基地编号</th>
                <th rowspan="2">基地名称</th>
                <th colspan="3">${sessionNumber}级在培</th>
                <th colspan="3">各级在培</th>
                <th rowspan="2">基地最小<br>培训容量</th>
                <th rowspan="2">基地近3年<br>培训容量</th>
                <th rowspan="2">容量使用率</th>
            </tr>
            <tr>
                <th>住院医师</th>
                <th>在校专硕</th>
                <th>小计</th>
                <th>住院医师</th>
                <th>在校专硕</th>
                <th>小计</th>
            </tr>
            <c:forEach items="${orgSpeList}" var="orgSpe">
                <tr class="one-level-bottom <c:if test='${empty orgSpe.jointOrgList}'>two-level-bottom</c:if>">
                    <td>${orgSpe.baseCode}</td>
                    <td style="text-align: left"><a onclick="showProfBaseInfo(event)" dataspeid="${orgSpe.speId}"
                        dataorgflow="${orgSpe.orgFlow}" dataorgname="${orgSpe.orgName}">${orgSpe.orgName}</a></td>
                    <td>
                        <c:set var="currTotal" value="${empty orgSpe.curInHospitalDoctors ? '0' : orgSpe.curInHospitalDoctors}" />
                        <span class="curInHospitalDoctors">${empty orgSpe.curInHospitalDoctors ? '0' : orgSpe.curInHospitalDoctors}</span>
                    </td>
                    <td>
                        <c:set var="currTotal" value="${currTotal + (empty orgSpe.curInCollegeMasters ? '0' : orgSpe.curInCollegeMasters)}" />
                        <span class="curInCollegeMasters">${empty orgSpe.curInCollegeMasters ? '0' : orgSpe.curInCollegeMasters}</span>
                    </td>
                    <td><span class="currTotal">${currTotal}</span></td>
                    <td><span class="inHospitalDoctors">${empty orgSpe.inHospitalDoctors ? '0' : orgSpe.inHospitalDoctors}</span></td>
                    <td><span class="inCollegeMasters">${empty orgSpe.inCollegeMasters ? '0' : orgSpe.inCollegeMasters}</span></td>
                    <td><span class="inTrains">${empty orgSpe.inTrains ? '0' : orgSpe.inTrains}</span></td>
                    <td><span class="minRecruitCapacity">${empty orgSpe.minRecruitCapacity ? '0' : orgSpe.minRecruitCapacity}</span></td>
                    <td>
                        <span class="baseCapacity" id="baseCapacity${orgSpe.speId}">${empty orgSpe.baseCapacity ? '0' : orgSpe.baseCapacity}</span>
                    </td>
                    <td><span class="trainingCapacityUsePer">${empty orgSpe.trainingCapacityUsePer ? '0' : orgSpe.trainingCapacityUsePer}</span>%</td>
                </tr>
                <c:if test="${not empty orgSpe.jointOrgList}">
                    <c:forEach items="${orgSpe.jointOrgList}" var="orgSpeSub" varStatus="varStat">
                        <tr class="<c:if test='${varStat.last}'>two-level-bottom</c:if> jointSub">
                            <td>${orgSpeSub.baseCode}</td>
                            <td style="text-align: left">------<a onclick="showProfBaseInfo(event)" dataspeid="${orgSpeSub.speId}"
                                  dataorgflow="${orgSpeSub.orgFlow}" dataorgname="${orgSpeSub.orgName}">${orgSpeSub.orgName}</a></td>
                            <td>
                                <c:set var="currTotal" value="${empty orgSpeSub.curInHospitalDoctors ? '0' : orgSpeSub.curInHospitalDoctors}" />
                                <span class="curInHospitalDoctors">${empty orgSpeSub.curInHospitalDoctors ? '0' : orgSpeSub.curInHospitalDoctors}</span>
                            </td>
                            <td>
                                <c:set var="currTotal" value="${currTotal + (empty orgSpeSub.curInCollegeMasters ? '0' : orgSpeSub.curInCollegeMasters)}" />
                                <span class="curInCollegeMasters">${empty orgSpeSub.curInCollegeMasters ? '0' : orgSpeSub.curInCollegeMasters}</span>
                            </td>
                            <td><span class="currTotal">${currTotal}</span></td>
                            <td><span class="inHospitalDoctors">${empty orgSpeSub.inHospitalDoctors ? '0' : orgSpeSub.inHospitalDoctors}</span></td>
                            <td><span class="inCollegeMasters">${empty orgSpeSub.inCollegeMasters ? '0' : orgSpeSub.inCollegeMasters}</span></td>
                            <td><span class="inTrains">${empty orgSpeSub.inTrains ? '0' : orgSpeSub.inTrains}</span></td>
                            <td><span class="minRecruitCapacity">${empty orgSpeSub.minRecruitCapacity ? '0' : orgSpeSub.minRecruitCapacity}</span></td>
                            <td>
                                <span class="baseCapacity" id="baseCapacity${orgSpeSub.speId}">${empty orgSpeSub.baseCapacity ? '0' : orgSpeSub.baseCapacity}</span>
                            </td>
                            <td><span class="trainingCapacityUsePer">${empty orgSpeSub.trainingCapacityUsePer ? '0' : orgSpeSub.trainingCapacityUsePer}</span>%</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>