<style>

</style>
<script>

</script>
<div id="allInfo" style="overflow-y: auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th>专业基地编码</th>
            <th>专业基地名称</th>
            <th>住院医师(${sessionNumber}在培)</th>
            <th>在校专硕(${sessionNumber}在培)</th>
            <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
                <th>住院医师(在培)</th>
                <th>在校专硕(在培)</th>
                <th>在培人员总数</th>
                <th>培训总容量</th>
                <th>最小培训容量</th>
                <th>容量使用率</th>
                <th>总数</th>
            </c:if>
        </tr>
        <c:forEach items="${orgSpeList}" var="orgSpe">
            <tr>
                <td>${orgSpe.speId}</td>
                <td>${orgSpe.speName}</td>
                <td><span class="curInHospitalDoctors">${orgSpe.curInHospitalDoctors}</span></td>
                <td><span class="curInCollegeMasters">${orgSpe.curInCollegeMasters}</span></td>
                <c:if test="${sessionNumber eq pdfn:getCurrYear()}">
                    <td><span class="inHospitalDoctors">${orgSpe.inHospitalDoctors}</span></td>
                    <td><span class="inCollegeMasters">${orgSpe.inCollegeMasters}</span></td>
                    <td><span class="inTrains">${orgSpe.inTrains}</span></td>
                    <td>
                        <span class="baseCapacity" id="baseCapacity${orgSpe.speId}">${orgSpe.baseCapacity}</span>人
                    </td>
                    <td><span class="minRecruitCapacity">${orgSpe.minRecruitCapacity}</span></td>
                    <td><span class="trainingCapacityUsePer">${orgSpe.trainingCapacityUsePer}</span>%</td>
                    <td><a class="openSpeBases" onclick="showProfBaseDetail(event)" dataorgflowlist="${orgSpe.orgFlowList}" dataspeid="${orgSpe.speId}"
                           dataspename="${orgSpe.speName}">${orgSpe.openSpeBases}</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>