<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
    <jsp:param name="font" value="true"/>
</jsp:include>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
    table {
        border-collapse: collapse;
        border: 1px solid #D3D3D3;
    }

    table td {
        border-top: 0;
        border-right: 1px solid #D3D3D3;
        border-bottom: 1px solid #D3D3D3;
        border-left: 0;
        padding-left: 4px;
        padding-right: 2px;
        text-align: center;
        height: 30px;
        font-size: 15px;
    }

    table th {
        border-top: 0;
        border-right: 1px solid #D3D3D3;
        border-bottom: 1px solid #D3D3D3;
        border-left: 0;
        text-align: center;
        height: 30px;
        font-size: 15px;
    }

    table tr.lastrow td {
        border-bottom: 0;
    }

    table tr td.lastCol {
        border-right: 0;
    }
</style>
<script>
    //打印
    function printDoc(doctorFlow, recruitFlow) {
        // window.print($("#main_bd"));
        var oldstr = document.body.innerHTML;
        $("#divTable").removeAttr("style");
        // $("#headId").css("margin-top", "10px");
        $("#printBtn").hide();
        window.print();
        document.body.innerHTML = oldstr;
    }

    // $(document).ready(function() {
    // 	window.print($("#tttt"));
    // });
    $(document).ready(function () {
        $('#assignYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
    });
</script>
<div id="main_bd" style="padding-top: 20px;">
    <div style="text-align: center;height: 40px;font-size: 18px;width: 100%;" id="headId">
        <strong>
			${printMap.orgName}社会化住院医师培训学员报名登记表
        </strong>
    </div>
    <div class="div_table" id="divTable" style="height: 580px; overflow-y: auto;">
        <h4>基本信息</h4>
        <table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
                <col width="15%"/>
                <col width="11%"/>
                <col width="15%"/>
                <col width="11%"/>
                <col width="15%"/>
                <col width="11%"/>
                <col width="11%"/>
                <col width="11%"/>
            </colgroup>
            <tr style="font-family:宋体">
                <td>姓名：</td>
                <td style="font-family:宋体">
				<span>
                    ${printMap.currUser.userName}
                </span>
                </td>
                <td>性别：</td>
                <td>${user.sexName}</td>
				<td>证件类型：</td>
                <td style="padding-left:10px;">
					<c:forEach items="${certificateTypeEnumList}" var="certificateType">
						<c:if test="${printMap.currUser.cretTypeId eq certificateType.id}">
							${certificateType.name}
						</c:if>
					</c:forEach>
				</td>
                <td>证件号：</td>
                <td>${user.idNo}
            </tr>
			<tr style="font-family:宋体">
				<td>民族：</td>
				<td style="padding-left:10px;">${printMap.currUser.nationName}</td>
				<td>国籍：</td>
				<td style="padding-left:10px;">${printMap.currUser.nationalityName}</td>
				<td>婚姻：</td>
				<td style="padding-left:10px;">
					<c:if test="${userResumeExt.maritalStatus eq 1}">
						未婚
					</c:if>
					<c:if test="${userResumeExt.maritalStatus eq 2}">
						已婚
					</c:if>
					<c:if test="${userResumeExt.maritalStatus eq 3}">
						初婚
					</c:if>
					<c:if test="${userResumeExt.maritalStatus eq 4}">
						再婚
					</c:if>
					<c:if test="${userResumeExt.maritalStatus eq 5}">
						复婚
					</c:if>
					<c:if test="${userResumeExt.maritalStatus eq 6}">
						丧偶
					</c:if>
					<c:if test="${userResumeExt.maritalStatus eq 7}">
						离婚
					</c:if>
				</td>
				<td>生日：</td>
				<td>
					${user.userBirthday}
				</td>
            </tr>
            <tr style="font-family:宋体">
                <td>手机：</td>
                <td>${user.userPhone}</td>
				<td>固定电话：</td>
				<td>
					${userResumeExt.telephone}
					<c:if test="${empty userResumeExt.telephone}">无</c:if>
				</td>
                <td>本人通讯地址：</td>
                <td colspan="3">
					${user.userAddress}
					<c:if test="${empty user.userAddress}">无</c:if>
				</td>
            </tr>
			<tr style="font-family:宋体">
                <td>紧急联系人：</td>
                <td>
					${doctor.emergencyName}
						<c:if test="${empty doctor.emergencyName}">无</c:if>
				</td>
                <td>紧急联系人手机：</td>
                <td>
                    ${doctor.emergencyPhone}
						<c:if test="${empty doctor.emergencyPhone}">无</c:if>
                </td>
                <td>紧急联系人地址：</td>
                <td>
                    ${userResumeExt.emergencyAddress}
						<c:if test="${empty userResumeExt.emergencyAddress}">无</c:if>
                </td>
                <td>户口所在地省行政区划：</td>
                <td>
					<c:forEach items="${provinceEnumList}" var="province">
						<c:if test="${userResumeExt.locationOfProvince eq province.id}">${province.name}</c:if>

					</c:forEach>
                </td>
            </tr>
        </table>
	<!--教育信息-->
		<h4>教育信息</h4>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="20%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
			</colgroup>
			<tr style="font-family:宋体">
				<td>是否为应届生：</td>
				<td>
					<c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_Y }">
						是</c:if>
					<c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_N }">
						否</c:if>
				</td>
				<td>是否在读：</td>
				<td>
					<c:if test="${userResumeExt.isReading eq GlobalConstant.FLAG_Y }">
						是</c:if>
					<c:if test="${userResumeExt.isReading eq GlobalConstant.FLAG_N }">
						否</c:if>
				</td>
				<td colspan="3">是否为农村订单定向免费医学毕业生：</td>
				<td>
					<c:if test="${userResumeExt.isGeneralOrderOrientationTrainee eq GlobalConstant.FLAG_Y }">
						是</c:if>

					<c:if test="${userResumeExt.isGeneralOrderOrientationTrainee eq GlobalConstant.FLAG_N }">
						否</c:if>
				</td>
			</tr>
			<c:if test="${userResumeExt.isReading eq GlobalConstant.FLAG_Y }">

			<tr style="font-family:宋体">
				<th>在读学历：</th>
				<td>
					<c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
						<c:if test="${userResumeExt.readingCollege eq dict.dictId }">${dict.dictName}</c:if>
					</c:forEach>
					</td>
				<th>预计毕业时间：</th>
				<td>${userResumeExt.readingDate}</td>
				<th>在读院校：</th>
				<td>${userResumeExt.readingSchoolName}</td>
				<th>在读专业：</th>
				<td>${userResumeExt.readingProfession}</td>
			</tr>
			</c:if>
			<tr style="font-family:宋体">
				<th>学位证书编号：</th>
				<td>
					<c:if test="${not empty userResumeExt.doctorDegreeNo}">
						${userResumeExt.doctorDegreeNo}
					</c:if>
					<c:if test="${empty userResumeExt.doctorDegreeNo and not empty userResumeExt.masterDegreeNo}">
						${userResumeExt.masterDegreeNo}
					</c:if>
					<c:if test="${empty userResumeExt.doctorDegreeNo and empty userResumeExt.masterDegreeNo and not empty userResumeExt.collegeDegreeNo}">
						${userResumeExt.collegeDegreeNo}
					</c:if>
				</td>
				<th>学位证书取得时间：</th>
				<td>
                    <c:if test="${not empty userResumeExt.doctorDegreeNo}">
                        ${userResumeExt.doctorDegreeTime}
                    </c:if>
                    <c:if test="${empty userResumeExt.doctorDegreeNo and not empty userResumeExt.masterDegreeNo}">
                        ${userResumeExt.masterDegreeTime}
                    </c:if>
                    <c:if test="${empty userResumeExt.doctorDegreeNo and empty userResumeExt.masterDegreeNo and not empty userResumeExt.collegeDegreeNo}">
                        ${userResumeExt.collegeDegreeTime}
                    </c:if>
				</td>
				<th>最高毕业证书编号：</th>
				<td>${doctor.certificateNo}</td>
				<th>最高学位证书编号：</th>
				<td>
					${doctor.degreeNo}
						<c:if test="${empty doctor.degreeNo}">无</c:if>
				</td>
			</tr>
		</table>
		<!--医师资格信息-->
		<h4>医师资格信息</h4>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="20%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
			</colgroup>
			<tr style="font-family:宋体">
				<th>是否通过医师资格考试：</th>
				<td style="padding-left:10px;">
					<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
						是</c:if>
					<c:if test="${userResumeExt.isPassQualifyingExamination eq GlobalConstant.FLAG_N }">
						否</c:if>
				</td>

				<th>通过医师资格考试时间：</th>
				<td>
					${userResumeExt.passQualifyingExaminationTime}
						<c:if test="${empty userResumeExt.passQualifyingExaminationTime}">无</c:if>
				</td>
				<th>是否获得医师资格证书：</th>
				<td style="padding-left:10px;">
					<c:if test="${userResumeExt.isHaveQualificationCertificate eq GlobalConstant.FLAG_Y }">
						是</c:if>
					<c:if test="${userResumeExt.isHaveQualificationCertificate eq GlobalConstant.FLAG_N }">
						否</c:if>
				</td>
				<th>取得医师资格证书时间：</th>
				<td>
					${userResumeExt.haveQualificationCertificateTime}
						<c:if test="${empty userResumeExt.haveQualificationCertificateTime}">无</c:if>
				</td>
			</tr>
			<tr style="font-family:宋体">
				<th>医师资格级别：</th>
				<td style="padding-left:10px;">
					<c:if test="${userResumeExt.physicianQualificationLevel eq 'zyys'}">
						执业医师
					</c:if>
					<c:if test="${userResumeExt.physicianQualificationLevel eq 'zyzlys'}">
						执业助理医师
					</c:if>
					<c:if test="${empty userResumeExt.physicianQualificationLevel}">无</c:if>
				</td>
				<th>医师资格类别：</th>
				<td style="padding-left:10px;">
					<c:if test="${userResumeExt.physicianQualificationClass eq 'lc'}">
						临床
					</c:if>
					<c:if test="${userResumeExt.physicianQualificationClass eq 'kq'}">
						口腔
					</c:if>
					<c:if test="${userResumeExt.physicianQualificationClass eq 'ggws'}">
						公共卫生
					</c:if>
					<c:if test="${userResumeExt.physicianQualificationClass eq 'zy'}">
						中医
					</c:if>
					<c:if test="${empty userResumeExt.physicianQualificationClass}">无</c:if>
				</td>
				<th>医师资格证书编码：</th>
				<td style="width: 300px">
					${userResumeExt.doctorQualificationCertificateCode }
						<c:if test="${empty userResumeExt.doctorQualificationCertificateCode}">无</c:if>
				</td>
				<th>是否获得医师执业证书：</th>
				<td>
					<c:if test="${userResumeExt.isHavePracticingCategory eq GlobalConstant.FLAG_Y }">
						是</c:if>
					<c:if test="${userResumeExt.isHavePracticingCategory eq GlobalConstant.FLAG_N }">
						否</c:if>
				</td>
			</tr>
			<tr style="font-family:宋体">
				<td>取得医师执业证书时间：</td>
				<td>
					${userResumeExt.havePracticingCategoryTime}
						<c:if test="${empty userResumeExt.havePracticingCategoryTime}">无</c:if>
				</td>
				<td>医师执业证书编码：</td>
				<td colspan="5">
					${userResumeExt.doctorPracticingCategoryCode }
						<c:if test="${empty userResumeExt.doctorPracticingCategoryCode}">无</c:if>
				</td>
			</tr>
		</table>
		<!--西部支援情况-->
		<h4>西部支援情况</h4>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="20%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
			</colgroup>
			<tr style="font-family:宋体">
				<th colspan="4">是否为西部支援行动住院医师：</th>
				<td colspan="4">
					<c:if test="${userResumeExt.westernSupportResidents eq 	GlobalConstant.FLAG_Y }">
						是</c:if>
					<c:if test="${userResumeExt.westernSupportResidents eq GlobalConstant.FLAG_N }">
						否</c:if>
				</td>
			</tr>
			<c:if test="${userResumeExt.westernSupportResidents eq 	GlobalConstant.FLAG_Y }">
				<tr style="font-family:宋体">
					<th colspan="2">西部支援行动住院医师送出省份：</th>
					<td colspan="2">${userResumeExt.westernSupportResidentsSendProvince}</td>
					<th colspan="2">西部支援行动住院医师送出单位统一社会信用代码：</th>
					<td colspan="2">${userResumeExt.westernSupportResidentsSendWorkUnitCode}</td>
				</tr>
				<tr style="font-family:宋体">
					<th colspan="2">西部支援行动住院医师送出单位：</th>
					<td colspan="2">${userResumeExt.westernSupportResidentsSendWorkUnit}</td>
					<th colspan="2">西部支援行动住院医师接收省份：</th>
					<td colspan="2">${userResumeExt.westernSupportResidentsReceiveProvince}</td>
				</tr>
				<tr style="font-family:宋体">
					<th colspan="2">西部支援行动住院医师接收省份培训基地(医院)统一社会信用代码：</th>
					<td colspan="2">${userResumeExt.westernSupportResidentsReceiveHospitalCode}</td>
					<th colspan="2">西部支援行动住院医师接收省份培训基地(医院)：</th>
					<td colspan="2">${userResumeExt.westernSupportResidentsReceiveHospital}</td>
				</tr>
			</c:if>
		</table>
		<!--报考信息-->
		<h4>报考信息</h4>
		<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="20%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
				<col width="11%"/>
			</colgroup>

			<tr>
				<td style="font-family:宋体">
					<span>国家基地</span>
				</td>
				<td style="font-family:宋体" colspan="2">
					<span>
						<c:if test="${empty printMap.recruitOrgName}">无</c:if>
						${printMap.recruitOrgName}
					</span>
				</td>
				<td style="font-family:宋体">
					<span>在协同基地培训</span>
				</td>
				<c:if test="${printMap.recruitInJointOrgTrain eq 'Y'}">
					<td style="font-family:宋体">
						<span>是</span>
					</td>
					<td style="font-family:宋体" colspan="2">
						<span>
							协同基地名称
						</span>
					</td>
					<td style="font-family:宋体">
						<span>${printMap.recruitJointOrgName}</span>
					</td>
				</c:if>
				<c:if test="${printMap.recruitInJointOrgTrain eq 'N'}">
					<td style="font-family:宋体" colspan="5">
						<span>否</span>
					</td>
				</c:if>
			</tr>
			<tr>
				<td style="font-family:宋体">
					<span>报考专业</span>
				</td>
				<td style="font-family:宋体" colspan="8">
					<span>
							<c:if test="${empty printMap.recruitSpeName}">无</c:if>
						${printMap.recruitSpeName}
					</span>
				</td>
			</tr>

		</table>
		<span>派送单位盖章:</span>
		<span style="margin-left: 350px">主管部门盖章:</span>
	</div>
		<div class="button" id="printBtn">
			<input type="button" class="btn_green"
				   onclick="printDoc('${currUser.userFlow}','${recruit.recruitFlow}');" value="打印"/>&nbsp;
		</div>
</div>
