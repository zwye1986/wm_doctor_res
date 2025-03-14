<div id="allInfo" style="overflow-y: auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th rowspan="2">专业基地编码</th>
            <th rowspan="2">专业基地名称</th>
            <th rowspan="2">基地总数</th>
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
            <tr>
                <td>${orgSpe.speId}</td>
                <td>${orgSpe.speName}</td>
                <td><a class="openSpeBases" onclick="showProfBaseDetail(event)" dataorgflowlist="${orgSpe.orgFlowList}" dataspeid="${orgSpe.speId}"
                       dataspename="${orgSpe.speName}">${orgSpe.openSpeBases}</a></td>
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
        </c:forEach>
        <tr>
            <td>总计</td>
            <td></td>
            <td><span class="openSpeBasesCount"></span></td>
            <td><span class="curInHospitalDoctorsCount"></span></td>
            <td><span class="curInCollegeMastersCount"></span></td>
            <td><span class="currCount"></span></td>
            <td><span class="inHospitalDoctorsCount"></span></td>
            <td><span class="inCollegeMastersCount"></span></td>
            <td><span class="inTrainsCount"></span></td>
            <td><span class="minRecruitCapacityCount"></span></td>
            <td><span class="baseCapacityCount"></span></td>
            <td><span class="trainingCapacityUsePerCount"></span>%</td>
        </tr>
    </table>
</div>