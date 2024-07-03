<style>

</style>
<script>

</script>
<div id="allInfo" style="overflow-y: auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th>专业基地编码</th>
            <th>专业基地名称</th>
            <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
            <th>基地总数</th>
            </c:if>
            <th>住院医师(${sessionNumber}在培)</th>
            <th>在校专硕(${sessionNumber}在培)</th>
            <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
                <th>住院医师(在培)</th>
                <th>在校专硕(在培)</th>
                <th>在培人员总数</th>
                <th>培训总容量</th>
                <th>最小培训总容量</th>
                <th>容量使用率</th>
            </c:if>
        </tr>
        <c:forEach items="${orgSpeList}" var="orgSpe">
            <tr>
                <td>${orgSpe.speId}</td>
                <td>${orgSpe.speName}</td>
                <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
                    <td><a class="openSpeBases" onclick="showProfBaseDetail(event)" dataorgflowlist="${orgSpe.orgFlowList}" dataspeid="${orgSpe.speId}"
                           dataspename="${orgSpe.speName}">${orgSpe.openSpeBases}</a></td>
                </c:if>
                <td><span class="curInHospitalDoctors">${empty orgSpe.curInHospitalDoctors ? '0' : orgSpe.curInHospitalDoctors}</span>人</td>
                <td><span class="curInCollegeMasters">${empty orgSpe.curInCollegeMasters ? '0' : orgSpe.curInCollegeMasters}</span>人</td>
                <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
                    <td><span class="inHospitalDoctors">${empty orgSpe.inHospitalDoctors ? '0' : orgSpe.inHospitalDoctors}</span>人</td>
                    <td><span class="inCollegeMasters">${empty orgSpe.inCollegeMasters ? '0' : orgSpe.inCollegeMasters}</span>人</td>
                    <td><span class="inTrains">${empty orgSpe.inTrains ? '0' : orgSpe.inTrains}</span>人</td>
                    <td>
                        <span class="baseCapacity" id="baseCapacity${orgSpe.speId}">${empty orgSpe.baseCapacity ? '0' : orgSpe.baseCapacity}</span>人
                    </td>
                    <td><span class="minRecruitCapacity">${empty orgSpe.minRecruitCapacity ? '0' : orgSpe.minRecruitCapacity}</span>人</td>
                    <td><span class="trainingCapacityUsePer">${empty orgSpe.trainingCapacityUsePer ? '0' : orgSpe.trainingCapacityUsePer}</span>%</td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>