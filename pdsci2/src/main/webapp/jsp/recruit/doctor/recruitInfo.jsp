<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
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

    <script type="text/javascript">
        window.onload = function(){
            hiddenQualificationCode();
            var startDate = new Date($("#recruitStartDate").val()).toDateString();
            var endDate = new Date($("#recruitEndDate").val()).toDateString();
            myDate =new Date().toDateString();
            NowTime(myDate,startDate,endDate);
        }

        function NowTime(myDate,startDate,endDate) {
            if (Date.parse(myDate) < Date.parse(startDate) ||Date.parse(myDate) > Date.parse(endDate)){
                $("#commit").hide();
            }
        }

        function commitRecruitInfo() {
            if(false==$("#recruitInfoForm").validationEngine("validate")){
                return false;
            }
            jboxConfirm("确认提交吗？提交后无法修改!", function () {
                var url = "<s:url value='/recruit/info/commitRecruitInfo'/>";
                var data = $('#recruitInfoForm').serialize();
                jboxPost(url, data, function (resp) {
                    if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                        window.parent.frames['mainIframe'].location.reload(true);
                        jboxClose();
                    }else {
                        jboxTip(resp);
                    }
                }, null, true);
            })
        }

        function saveRecruitInfo() {
            if(false==$("#recruitInfoForm").validationEngine("validate")){
                return false;
            }
            jboxConfirm("确认保存吗？", function () {
                var url = "<s:url value='/recruit/info/saveRecruitInfo'/>";
                var data = $('#recruitInfoForm').serialize();
                jboxPost(url, data, function (resp) {
                    if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                        jboxClose();
                        window.parent.frames['mainIframe'].location.reload(true);
                    }
                }, null, true);
            })
        }

        function hiddenQualificationCode() {
            var status = $("#isHaveQualification").val();
            if (status == 'Y'){
                document.getElementById("qualificationCodeTh").style.visibility="visible";//显示
                document.getElementById("qualificationCodeTd").style.visibility="visible";//显示
            }
            if (status == 'N'){
                document.getElementById("qualificationCodeTh").style.visibility="hidden";//隐藏
                document.getElementById("qualificationCodeTd").style.visibility="hidden";//隐藏
            }
        }
    </script>

    <style>

    </style>
</head>
<body>
<div class="content">
    <div class="title1 clearfix">
        <div id="tagContent">
            <fieldset>
                <legend>信息填报</legend>
                <div style="text-align: center">
                    <form id="recruitInfoForm" name="recruitInfoForm" method="post">
                        <table class="xllist" style="margin-top: 30px">
                            <input id="recruitFlow" name="recruitFlow" type="text" value="${recruitInfo.recruitFlow}" hidden>
                            <input id="doctorFlow" name="doctorFlow" type="text" value="${recruitInfo.doctorFlow}" hidden>
                            <input id="recruitYear" name="recruitYear" type="text" value="${recruitInfo.recruitYear}" hidden>
                            <input id="recruitStartDate" name="recruitStartDate" type="text" value="${currYearCfgInfo.recruitStartDate}" hidden>
                            <input id="recruitEndDate" name="recruitEndDate" type="text" value="${currYearCfgInfo.recruitEndDate}" hidden>
                            <tr>
                                <th>
                                    <label><font color="red">*</font>姓&#12288;&#12288;名：</label>
                                </th>
                                <td>
                                    <input id="userName" name="userName" value="${currentUser.userName}" class="validate[required] xltext" readonly/>
                                </td>
                                <th>
                                    <label><font color="red">*</font>性&#12288;&#12288;別：</label>
                                </th>
                                <td>
                                    <c:if test="${not empty currentUser.sexId}">
                                        <input value="${currentUser.sexName}" class="xltext" readonly/>
                                    </c:if>
                                    <c:if test="${empty currentUser.sexId}">
                                        <select style="min-width: 165px;max-width: 165px" id="sexId" name="sexId" class="validate[required] qselect">
                                            <option value="">--请选择--</option>
                                            <option value="Man">男</option>
                                            <option value="Woman">女</option>
                                        </select>
                                    </c:if>

                                </td>
                                <th>
                                    <label><font color="red">*</font>证件类型：</label>
                                </th>
                                <td>
                                    <select style="min-width: 165px;max-width: 165px" class="validate[required] qselect" id="cretTypeId" name="cretTypeId">
                                        <option value="01" <c:if test="${currentUser.cretTypeId eq '01'}">selected="selected"</c:if>>身份证</option>
                                        <%--<option value="02">军官证</option>--%>
                                        <%--<option value="03">港澳台身份证</option>--%>
                                        <%--<option value="04">华侨身份证</option>--%>
                                        <%--<option value="05">护照</option>--%>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label><font color="red">*</font>证件号码：</label>
                                </th>
                                <td>
                                    <input id="idNo" name="idNo" value="${currentUser.idNo}" class="validate[required] xltext" readonly/>
                                </td>
                                <th>
                                    <label><font color="red">*</font>本人手机：</label>
                                </th>
                                <td>
                                    <input id="userPhone" name="userPhone" value="${currentUser.userPhone}" class="validate[required] xltext" readonly/>
                                </td>
                                <th>
                                    <label><font color="red">*</font>学员类型：</label>
                                </th>
                                <td>
                                    <select style="min-width: 165px;max-width: 165px" class="validate[require] qselect" id="doctorTypeId" name="doctorTypeId">
                                        <option value="01" <c:if test="${recruitInfo.doctorTypeId eq '01'}">selected="selected"</c:if>>单位人</option>
                                        <option value="02" <c:if test="${recruitInfo.doctorTypeId eq '02'}">selected="selected"</c:if>>社会人</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label><font color="red">*</font>联系人姓名:</label>
                                </th>
                                <td>
                                    <input id="linkMan" name="linkMan" value="${recruitInfo.linkMan}" class="validate[required] xltext">
                                </td>
                                <th>
                                    <label><font color="red">*</font>联系人电话:</label>
                                </th>
                                <td>
                                    <input id="linkManPhone" name="linkManPhone" value="${recruitInfo.linkManPhone}" class="validate[required] xltext">
                                </td>
                                <th>
                                    <label>单位名称:</label>
                                </th>
                                <td>
                                    <input id="workOrgName" name="workOrgName" value="${recruitInfo.workOrgName}" class="xltext">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>单位级别：</label>
                                </th>
                                <td>
                                    <select id="workOrgLevelId" name="workOrgLevelId" style="min-width: 165px;max-width: 165px" class="qselect">
                                        <option value="01" <c:if test="${recruitInfo.workOrgLevelId ne '02' or '03' or '04'}">selected="selected"</c:if>>三级甲等</option>
                                        <option value="02" <c:if test="${recruitInfo.workOrgLevelId eq '02'}">selected="selected"</c:if>>三级乙等</option>
                                        <option value="03" <c:if test="${recruitInfo.workOrgLevelId eq '03'}">selected="selected"</c:if>>二级甲等</option>
                                        <option value="04" <c:if test="${recruitInfo.workOrgLevelId eq '04'}">selected="selected"</c:if>>二级乙等</option>
                                    </select>
                                </td>
                                <th>
                                    <label><font color="red">*</font>报考专业第一志愿：</label>
                                </th>
                                <td>
                                    <select style="min-width: 165px;max-width: 165px" id="trainingSpeId" name="trainingSpeId" class="validate[required] qselect" >
                                        <option  value="">全部</option>
                                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                            <option value="${dict.dictId}" ${recruitInfo.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th>
                                    <label><font color="red">*</font>是否调剂：</label>
                                </th>
                                <td>
                                    <select id="isSwap" name="isSwap" style="min-width: 165px;max-width: 165px" class="validate[required] qselect">
                                        <option value="Y" <c:if test="${recruitInfo.isSwap eq 'Y'}">selected="selected"</c:if>>是</option>
                                        <option value="N" <c:if test="${recruitInfo.isSwap eq 'N'}">selected="selected"</c:if>>否</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label><font color="red">*</font>参培年份:</label>
                                </th>
                                <td>
                                    <input id="sessionNumber" name="sessionNumber" value="${recruitInfo.sessionNumber}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label><font color="red">*</font>本科学历毕业时间:</label>
                                </th>
                                <td>
                                    <input id="graduationTime" name="graduationTime" value="${recruitInfo.graduationTime}" class="validate[required] xltext">
                                </td>
                                <th>
                                    <label><font color="red">*</font>本科学历毕业院校:</label>
                                </th>
                                <td>
                                    <input id="graduatedName" name="graduatedName" value="${recruitInfo.graduatedName}" class="validate[required] xltext">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label><font color="red">*</font>本科学历所学专业:</label>
                                </th>
                                <td>
                                    <input id="graduatedMajor" name="graduatedMajor" value="${recruitInfo.graduatedMajor}" class="validate[required] xltext">
                                </td>
                                <th>
                                    <label>硕士学历毕业时间:</label>
                                </th>
                                <td>
                                    <input id="masterGratime" name="masterGratime" value="${recruitInfo.masterGratime}" class="xltext">
                                </td>
                                <th>
                                    <label>硕士学历毕业院校:</label>
                                </th>
                                <td>
                                    <input id="masterGraschoolName" name="masterGraschoolName" value="${recruitInfo.masterGraschoolName}" class="xltext">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>硕士学历所学专业:</label>
                                </th>
                                <td>
                                    <input id="masterMajor" name="masterMajor" value="${recruitInfo.masterMajor}" class="xltext">
                                </td>
                                <th>
                                    <label>硕士学历类型:</label>
                                </th>
                                <td>
                                    <select id="masterdegreeTypeId" name="masterdegreeTypeId" style="min-width: 165px;max-width: 165px" class="qselect">
                                        <option value="">--请选择--</option>
                                        <option value="01" <c:if test="${recruitInfo.masterdegreeTypeId eq '01'}">selected="selected"</c:if>>科学型</option>
                                        <option value="02" <c:if test="${recruitInfo.masterdegreeTypeId eq '02'}">selected="selected"</c:if>>专业型</option>
                                    </select>
                                </td>
                                <th>
                                    <label>博士学历毕业时间:</label>
                                </th>
                                <td>
                                    <input id="doctorGratime" name="doctorGratime" value="${recruitInfo.doctorGratime}" class="xltext">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>博士学历毕业院校:</label>
                                </th>
                                <td>
                                    <input id="doctorGraschoolName" name="doctorGraschoolName" value="${recruitInfo.doctorGraschoolName}" class="xltext">
                                </td>
                                <th>
                                    <label>博士学历所学专业:</label>
                                </th>
                                <td>
                                    <input id="doctorMajor" name="doctorMajor" value="${recruitInfo.doctorMajor}" class="xltext">
                                </td>
                                <th>
                                    <label>博士学历类型:</label>
                                </th>
                                <td>
                                    <select id="doctorDegreeTypeId" name="doctorDegreeTypeId" style="min-width: 165px;max-width: 165px"  class="qselect">
                                        <option value="">--请选择--</option>
                                        <option value="01" <c:if test="${recruitInfo.doctorDegreeTypeId eq '01'}">selected="selected"</c:if>>科学型</option>
                                        <option value="02" <c:if test="${recruitInfo.doctorDegreeTypeId eq '02'}">selected="selected"</c:if>>专业型</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>往届/应届:</label>
                                </th>
                                <td>
                                    <select id="isYearGraduate" name="isYearGraduate" style="min-width: 165px;max-width: 165px"  class="qselect">
                                        <option value="01" <c:if test="${recruitInfo.isYearGraduate eq '01'}">selected="selected"</c:if>>应届</option>
                                        <option value="02" <c:if test="${recruitInfo.isYearGraduate eq '02'}">selected="selected"</c:if>>往届</option>
                                    </select>
                                </td>
                                <th>
                                    <label>是否为执业医师:</label>
                                </th>
                                <td>
                                    <select id="isHaveQualification" name="isHaveQualification" style="min-width: 165px;max-width: 165px" class="qselect" onchange="hiddenQualificationCode()">
                                        <option value="Y" <c:if test="${recruitInfo.isHaveQualification eq 'Y'}">selected="selected"</c:if>>是</option>
                                        <option value="N" <c:if test="${recruitInfo.isHaveQualification eq 'N'}">selected="selected"</c:if>>否</option>
                                    </select>
                                </td>
                                <th id="qualificationCodeTh" style="visibility:hidden">
                                    <label>执业医师证号:</label>
                                </th>
                                <td id="qualificationCodeTd" style="visibility:hidden">
                                    <input id="qualificationCode" name="qualificationCode" value="${recruitInfo.qualificationCode}" class="xltext">
                                </td>
                            </tr>
                        </table>
                        <div class="text" style="margin-top: 20px;text-align:center"><font color="red">保存和提交后,性别将无法更改</font></div>
                        <div class="button" style="margin: 15px">
                            <input id="commit" name="commit" type="button" class="search" onclick="commitRecruitInfo()" value="提&#12288;交"/>
                            <input type="button" class="search" onclick="saveRecruitInfo()" value="保&#12288;存"/>
                        </div>
                    </form>
                </div>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>