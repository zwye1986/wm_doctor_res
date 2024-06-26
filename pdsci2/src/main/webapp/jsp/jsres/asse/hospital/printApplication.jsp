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
        height: 40px;
        font-size: 15px;
    }

    table th {
        border-top: 0;
        border-right: 1px solid #D3D3D3;
        border-bottom: 1px solid #D3D3D3;
        border-left: 0;
        text-align: center;
        height: 40px;
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

    $(document).ready(function () {
        window.print();
    });
</script>
<div>
    <div style="text-align: center;height: 80px;font-size: 18px;width: 100%;">
        <strong>
            江苏省住院医师规范化培训和助理全科医生培训（西医）结业</br>
            理论统考资格审核情况表
        </strong>
    </div>
    <table style="width: 100%;">
        <colgroup>
            <col width="21%"/>
            <col width="21%"/>
            <col width="21%"/>
            <col width="21%"/>
            <col width="16%"/>
        </colgroup>
        <tbody>
        <tr>
            <td style="text-align: left;background-color: lightgrey;" colspan="5">
				<span>
					<strong>&nbsp;基本信息</strong>
				</span>
            </td>
        </tr>
        <tr>
            <td>
				<span>
					姓&#12288;&#12288;名
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
                    ${user.userName}
                </span>
            </td>
            <td>
                <span>性&#12288;&#12288;别</span>
            </td>
            <td style="font-family:宋体">
				<span>
					<c:if test="${user.sexId eq userSexEnumMan.id}">
                        ${userSexEnumMan.name}
                    </c:if>
					<c:if test="${user.sexId eq userSexEnumWoman.id}">
                        ${userSexEnumWoman.name}
                    </c:if>
				</span>
            </td>
            <td rowspan="6" style="min-width: 16%;max-width: 16%;">
                <div style="min-width: 16%;max-width: 16%;">
                    <c:if test="${not empty user.userHeadImg}">
                        <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" alt=""
                             style="margin-top: 3px;" width="130px" height="150px"/>
                    </c:if>
                </div>
            </td>
        </tr>
        <tr>
            <td>
				<span>
					证件类型
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
					<c:forEach items="${certificateTypeEnumList}" var="certificateType">
                        <c:if test="${user.cretTypeId eq certificateType.id}">
                            ${certificateType.name}
                        </c:if>
                    </c:forEach>
				</span>
            </td>
            <td>
                <span>证&nbsp;件&nbsp;号</span>
            </td>
            <td style="font-family:宋体">
				<span>
                    ${user.idNo}
                </span>
            </td>
        </tr>
        <tr>
            <td>
				<span>
					入培年级
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
                    ${doctorRecruit.sessionNumber}
                </span>
            </td>
            <td>
                <span>结业考核年份</span>
            </td>
            <td style="font-family:宋体">
				<span>
                    ${doctorRecruit.graduationYear}
                </span>
            </td>
        </tr>
        <tr>
            <td>
				<span>
					人员类型
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
                    ${resDoctor.doctorTypeName}
                </span>
            </td>
            <td>
				<span>
					培训基地
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
                    ${doctorRecruit.orgName}
                </span>
            </td>
        </tr>
        <tr>
            <td>
				<span>
					培训类别
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
					&nbsp;${resDoctor.trainingTypeName}
				</span>
            </td>
            <td>
				<span>
					培训专业
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
					${doctorRecruit.speName}
                    <c:if test="${needChange eq 'Y'}">(${doctorRecruit.changeSpeName})</c:if>
				</span>
            </td>
        </tr>
        <tr>
            <td>
				<span>
					学&#12288;&#12288;位
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
					<c:if test="${user.educationId eq '191'}">无学位</c:if>
					<c:if test="${user.educationId ne '191'}">
                        <c:choose>
                            <c:when test="${ not empty userResumeExt.doctorDegreeName}">
                                ${userResumeExt.doctorDegreeName}
                            </c:when>
                            <c:when test="${ not empty userResumeExt.masterDegreeName}">
                                ${userResumeExt.masterDegreeName}
                            </c:when>
                            <c:otherwise>
                                ${userResumeExt.degreeName}
                            </c:otherwise>
                        </c:choose>
                    </c:if>
				</span>
            </td>
            <td>
                <span>联系方式</span>
            </td>
            <td style="font-family:宋体">
				<span>
                    ${currUser.userPhone}
                </span>
            </td>
        </tr>
        <tr>
            <td style="text-align: left;background-color: lightgrey;" colspan="5">
				<span>
					<strong>&nbsp;资格信息</strong>
				</span>
            </td>
        </tr>
        <tr>
            <td>
				<span>
					学&#12288;&#12288;历
				</span>
            </td>
            <td style="font-family:宋体">
				<span>
                    ${user.educationName}
                </span>
            </td>
            <td>
                <span>毕业证书编号</span>
            </td>
            <td style="font-family:宋体" colspan="2">
				<span>
                    ${resDoctor.certificateNo}
                </span>
            </td>
        </tr>
        <tr>
            <td><span>培训开始时间</span></td>
            <td style="font-family:宋体">
                &nbsp;${startDate}
            </td>
            <td><span>培训结束时间</span></td>
            <td style="font-family:宋体" colspan="2">
                ${endDate}
            </td>
        </tr>
        <tr>
            <td><span>报考资格材料</span></td>
            <td style="font-family:宋体">
                ${practicingMap.graduationMaterialName}
                <c:if test="${empty practicingMap.graduationMaterialName}">
                    <span style="color: red">未填写！</span>
                    <c:set var="canApply" value="N"></c:set>
                </c:if>
            </td>
            <td><span>报考资格材料编码</span></td>
            <td style="font-family:宋体" colspan="2">
                ${practicingMap.graduationMaterialCode}
                <c:if test="${empty practicingMap.graduationMaterialCode}">
                    <span style="color: red">&nbsp;未填写！</span>
                    <c:set var="canApply" value="N"></c:set>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><span>取得资格时间</span></td>
            <td style="font-family:宋体">
                ${userResumeExt.haveQualificationCertificateTime}
                <c:if test="${empty userResumeExt.haveQualificationCertificateTime}">
                    <span style="color: red">未填写！</span>
                    <c:set var="canApply" value="N"></c:set>
                </c:if>
            </td>
            <td><span>执业类型</span></td>
            <td style="font-family:宋体" colspan="2">
                ${practicingMap.graduationCategoryName}
                <c:if test="${empty practicingMap.graduationCategoryName}">
                    <span style="color: red">未填写！</span>
                    <c:set var="canApply" value="N"></c:set>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><span>执业范围</span></td>
            <td style="font-family:宋体">
                ${practicingMap.graduationScopeName}
                <c:if test="${empty practicingMap.graduationScopeName}">
                    <span style="color: red">未填写！</span>
                    <c:set var="canApply" value="N"></c:set>
                </c:if>
            </td>
            <td><span>培训年限</span></td>
            <td style="font-family:宋体" colspan="2">
                <c:if test="${'OneYear' eq doctorRecruit.trainYear}">一年</c:if>
                <c:if test="${'TwoYear' eq doctorRecruit.trainYear}">两年</c:if>
                <c:if test="${'ThreeYear' eq doctorRecruit.trainYear}">三年</c:if>
            </td>
        </tr>
        </tbody>
    </table>
    <table style="border-top: none;width: 100%;">
        <colgroup>
            <col width="50%"/>
            <col width="50%"/>
        </colgroup>
        <tr>
            <td>
                <span>培训登记手册完成情况：</span>
                <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.PASS}">
                    已完成
                </c:if>
                <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.UNPASS}">
                    未完成
                </c:if>
                <c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.NORMAL}">
                    正常
                </c:if>
            </td>
            <td>
                <span>实际轮转时间（月）：</span>
                ${pdfn:toFormatString(allMonth)}
            </td>
        </tr>
        <tr>
            <td>
                <span>数据完成比例：</span>
                ${empty avgBiMap.avgComplete?0:avgBiMap.avgComplete}%

            </td>
            <td>
                <span>数据审核比例：</span>
                ${empty avgBiMap.avgAudit?0:avgBiMap.avgAudit}%
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: left;">
                <c:if test="${isAssiGeneral eq 'Y'}">
                    <span>全科医学及相关理论知识考试成绩：</span>
                </c:if>
                <c:if test="${isAssiGeneral eq 'N'}">
                    <span>公共科目考试成绩：</span>
                </c:if>
                合格
<%--                <c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">合格</c:if>--%>
<%--                <c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">不合格</c:if>--%>
            </td>
        </tr>
<%--        <tr>--%>
<%--            <td colspan="2" style="text-align: left;">--%>
<%--                <span>受疫情影响未完成的培训：</span>--%>
<%--                    ${jsresGraduationApply.remark}--%>
<%--                <c:if test="${empty jsresGraduationApply.remark}">--%>
<%--                    <span style="color: red">未填写！</span>--%>
<%--                    <c:set var="canApply" value="N"></c:set>--%>
<%--                </c:if>--%>
<%--            </td>--%>
<%--        </tr>--%>
        <tr>
            <td style="height: 180px;text-align: left;" valign="top">
                <p style="border: none;">
                    <strong>培训基地初审意见：</strong><br/><br/>
                    &nbsp;&#12288;&#12288;我已审核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。<br/>
                    <br/>
                    &nbsp;审核人签名：&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;单位公章：<br/>
                    <br/>
                    &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                    <span style="width:100%;text-align: right">年&#12288;&#12288;月&#12288;&#12288;日&#12288;</span>
                </p>
            </td>
            <td style="height: 180px;text-align: left" valign="top">
                <p>
                    <strong>市卫健委复审意见：</strong><br/><br/>
                    &nbsp;&#12288;&#12288;我已复核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。<br/>
                    <br/>
                    &nbsp;复审人签名：&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;单位公章：<br/>
                    <br/>
                    &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                    <span style="width:100%;text-align: right">年&#12288;&#12288;月&#12288;&#12288;日&#12288;</span>
                </p>
            </td>
        </tr>
    </table>
</div>
