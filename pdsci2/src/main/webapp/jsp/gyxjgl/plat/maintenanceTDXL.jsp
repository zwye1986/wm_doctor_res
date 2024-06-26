<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style type="text/css">
        #tags li {
            margin-top: 5px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            jboxEndLoading();
            //默认展示基本信息
            $("#tags").find("li").eq(0).addClass("selectTag");
            selectTag(this,'baseInfo');
        });
    </script>
</head>
<body>
<div style="width: 100%;background-color: white; padding-top: 10px;" align="center"></div>
<div class="mainright">
    <div class="content">
        <div style="background-color: white;height: 10px;width: 100%;"></div>
        <div style="margin-bottom:10px;">
            <div style="margin-left:20px;margin-top:10px;">
                <ul id="tags" style="margin-left: 0px;">
                    <li>
                        <a onclick="selectTag(this,'baseInfo')" href="javascript:void(0)">基本信息</a>
                    </li>
                </ul>

                <div id="tagContent" style="margin-top: 3px;">
                </div>
                <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="baseInfo">
                    <div class="spreadOneOne">
                        <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                            <tr>
                                <th style="width:20%;"><span style="color: red;">*</span>&#12288;<span >学号：</span></th>
                                <td style="width:30%;">
                                        <label>${eduUser.sid}</label>
                                </td><th style="width:20%;"><span >证件类型：</span></th>
                                <td style="width:30%;">
                                        <label>${sysUser.cretTypeName }</label>
                                </td>
                            </tr>
                            <tr>
                                <th><span >姓名：</span></th>
                                <td>
                                        <label>${sysUser.userName }</label>
                                </td>
                                <th><span >证件号码：</span></th>
                                <td>
                                        <label>${sysUser.idNo }</label>
                                </td>
                            </tr>
                            <tr>
                                <th><span >性别：</span></th>
                                <td>
                                        <label>${sysUser.sexName }</label>
                                </td>
                                <th><span >民族：</span></th>
                                <td>
                                        <label>${sysUser.nationName}</label>
                                </td>
                            </tr>
                            <tr>
                                <th><span >出生日期：</span></th>
                                <td>
                                        <label>${sysUser.userBirthday }</label>
                                </td>
                                <th><span style="color: red;">*</span>&#12288;<span >政治面貌：</span></th>
                                <td>
                                        ${sysUser.politicsStatusName}
                                </td>
                            </tr>
                            <tr>
                                <th><span style="color: red;">*</span>&#12288;<span >工作单位：</span></th>
                                <td>
                                    <label>${extInfoForm.workUnitTDXL }</label>
                                </td>
                                <th><span >专业：</span></th>
                                <td>
                                    [${eduUser.majorId}]${eduUser.majorName}
                                </td>
                            </tr>
                            <tr>
                                <th><span >报班时间：</span></th>
                                <td>
                                    ${extInfoForm.reportTime }
                                </td>
                                <th><span style="color: red;">*</span>&#12288;<span >联系电话：</span></th>
                                <td>
                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" placeholder="不清楚请填无" name="sysUser.userPhone" value="${sysUser.userPhone }">
                                </td>
                            </tr>
                            <tr>
                                <th><span style="color: red;">*</span>&#12288;<span >地址：</span></th>
                                <td>
                                    <label>${extInfoForm.addressTDXL }</label>
                                </td>
                                <th><span style="color: red;">*</span>&#12288;<span >获得学位时间：</span></th>
                                <td>
                                    <label>${extInfoForm.degreeTimeTDXL }</label>
                                </td>
                            </tr>
                            <tr>
                                <th><span style="color: red;">*</span>&#12288;<span >毕业学校：</span></th>
                                <td>
                                    <label>${extInfoForm.graduateSchooTDXL }</label>
                                </td>
                                <th><span >导师姓名：</span></th>
                                <td>
                                    <label>${eduUser.firstTeacher }</label>
                                </td>
                            </tr>
                            <tr>
                                <th><span >导师单位：</span></th>
                                <td>
                                    <label>${extInfoForm.tutorUnit }</label>
                                </td>
                                <th><span >导师联系方式：</span></th>
                                <td>
                                    <label>${extInfoForm.tutorContact}</label>
                                </td>
                            </tr>
                            <tr>
                                <th><span >入学年级：</span></th>
                                <td>
                                    ${eduUser.period }
                                </td>
                                <th><span >学习形式：</span></th>
                                <td>
                                    <label>${eduUser.studyFormName}</label>
                                </td>
                            </tr>
                            <%--<tr>--%>
                            <%--<th><span >入学年级：</span></th>--%>
                            <%--<td>--%>
                            <%--<c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">--%>
                            <%--<input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" class="notBlank inputText ctime" style="width: 140px;text-align: left;" name="eduUser.period" id="period" value="${eduUser.period }"/>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">--%>
                            <%--${eduUser.period }--%>
                            <%--</c:if>--%>
                            <%--</td>--%>
                            <%--<th></th>--%>
                            <%--<td></td>--%>
                            <%--</tr>--%>
                            <tr>
                                <th><span >学位级别：</span></th>
                                <td>
                                    ${eduUser.trainCategoryName}
                                </td>
                                <th></th>
                                <td></td>
                            </tr>
                            <tr>
                                <th><span >学位类型：</span></th>
                                <td>
                                    ${eduUser.trainCategoryName}
                                </td>
                                <c:if test="${not empty eduUser.trainCategoryName}">
                                    <th><span >学位类别：</span></th>
                                    <td>
                                        <label>${eduUser.degreeLevelName}</label>
                                    </td>
                                </c:if>
                                <c:if test="${empty eduUser.trainCategoryName}">
                                    <th></th>
                                    <td></td>
                                </c:if>
                            </tr>
                            <tr>
                                <th><span >授予学位证书编号：</span></th>
                                <td>${eduUser.awardDegreeCertCode }</td>
                                <th></th>
                                <td></td>
                            </tr>
                        </table>
                    </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>