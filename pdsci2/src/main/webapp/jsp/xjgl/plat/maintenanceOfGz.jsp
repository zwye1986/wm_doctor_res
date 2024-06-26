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
        function baseCheck(){//基本信息伸缩
            var baseStatus = $("#baseStatus").val();
            $("#baseStatus").val(!(baseStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            baseStatus = $("#baseStatus").val();
            $(".spreadOneOne").toggle(baseStatus=="${GlobalConstant.FLAG_Y}");
        }
        function recruitCheck(){//录取信息伸缩
            var recruitStatus = $("#recruitStatus").val();
            $("#recruitStatus").val(!(recruitStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            recruitStatus = $("#recruitStatus").val();
            $(".spreadOneTwo").toggle(recruitStatus=="${GlobalConstant.FLAG_Y}");
        }
        function needCheck(){//必填信息伸缩
            var needStatus = $("#needStatus").val();
            $("#needStatus").val(!(needStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            needStatus = $("#needStatus").val();
            $(".spreadOneThree").toggle(needStatus=="${GlobalConstant.FLAG_Y}");
        }
        function selectCheck(){//选填信息伸缩
            var selectStatus = $("#selectStatus").val();
            $("#selectStatus").val(!(selectStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            selectStatus = $("#selectStatus").val();
            $(".spreadOneFour").toggle(selectStatus=="${GlobalConstant.FLAG_Y}");
        }
        function feeCheck(){//学费信息伸缩
            var feeStatus = $("#feeStatus").val();
            $("#feeStatus").val(!(feeStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            feeStatus = $("#feeStatus").val();
            $(".spreadOneFive").toggle(feeStatus=="${GlobalConstant.FLAG_Y}");
        }
        function gotCertCheck(){//已获得学历或学位证书伸缩
            var gotCertStatus = $("#gotCertStatus").val();
            $("#gotCertStatus").val(!(gotCertStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            gotCertStatus = $("#gotCertStatus").val();
            $(".spreadTwoOne").toggle(gotCertStatus=="${GlobalConstant.FLAG_Y}");
        }
        function certReqCheck(){//学历学位申请证书伸缩
            var certReqStatus = $("#certReqStatus").val();
            $("#certReqStatus").val(!(certReqStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            certReqStatus = $("#certReqStatus").val();
            $(".spreadTwoTwo").toggle(certReqStatus=="${GlobalConstant.FLAG_Y}");
        }
        function paperCheck(){//学位论文信息伸缩
            var paperStatus = $("#paperStatus").val();
            $("#paperStatus").val(!(paperStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            paperStatus = $("#paperStatus").val();
            $(".spreadThree").toggle(paperStatus=="${GlobalConstant.FLAG_Y}");
        }
        function dispatchCheck(){//派遣信息伸缩
            var dispatchStatus = $("#dispatchStatus").val();
            $("#dispatchStatus").val(!(dispatchStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            dispatchStatus = $("#dispatchStatus").val();
            $(".spreadFourOne").toggle(dispatchStatus=="${GlobalConstant.FLAG_Y}");
        }
        function employCheck(){//就业信息伸缩
            var employStatus = $("#employStatus").val();
            $("#employStatus").val(!(employStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            employStatus = $("#employStatus").val();
            $(".spreadFourTwo").toggle(employStatus=="${GlobalConstant.FLAG_Y}");
        }
        function archivesCheck(){//档案去向信息伸缩
            var archivesStatus = $("#archivesStatus").val();
            $("#archivesStatus").val(!(archivesStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            archivesStatus = $("#archivesStatus").val();
            $(".spreadFourThree").toggle(archivesStatus=="${GlobalConstant.FLAG_Y}");
        }

        function medicalCheck(){//医保、孕育信息伸缩
            var medicalStatus = $("#medicalStatus").val();
            $("#medicalStatus").val(!(medicalStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            medicalStatus = $("#medicalStatus").val();
            $(".spreadFive").toggle(medicalStatus=="${GlobalConstant.FLAG_Y}");
        }
        function dormitoryCheck(){//宿舍信息伸缩
            var dormitoryStatus = $("#dormitoryStatus").val();
            $("#dormitoryStatus").val(!(dormitoryStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            dormitoryStatus = $("#dormitoryStatus").val();
            $(".spreadSix").toggle(dormitoryStatus=="${GlobalConstant.FLAG_Y}");
        }
        function dossierCheck(){//档案信息伸缩
            var dossierStatus = $("#dossierStatus").val();
            $("#dossierStatus").val(!(dossierStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            dossierStatus = $("#dossierStatus").val();
            $(".spreadSeven").toggle(dossierStatus=="${GlobalConstant.FLAG_Y}");
        }

        function selectTag(selfObj,url) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            $("div.spreadOne").hide();
            $("#"+url).show();
        }
    </script>
</head>
<body>
<div style="width: 100%;background-color: white; padding-top: 10px;" align="center"></div>
<div class="mainright">
    <div class="content">
        <div style="background-color: white;height: 10px;width: 100%;"></div>
        <div style="margin-bottom:10px;">
                <%--<table class="basic" style="width:100%;">--%>
                    <%--<tr>--%>
                        <%--<th style="text-align:left;">&#12288;学生基本信息</th>--%>
                    <%--</tr>--%>
                <%--</table>--%>
                <div style="margin-left:20px;margin-top:10px;">
                    <ul id="tags" style="margin-left: 0px;">
                        <li>
                            <a onclick="selectTag(this,'baseInfo')" href="javascript:void(0)">基本信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'recruitInfo')" href="javascript:void(0)">录取信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'needInfo')" href="javascript:void(0)">必填信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'selectInfo')" href="javascript:void(0)">选填信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'feeInfo')" href="javascript:void(0)">学费信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'gotCertInfo')" href="javascript:void(0)">已获学历</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'certReqInfo')" href="javascript:void(0)">攻读学历</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'paperInfo')" href="javascript:void(0)">论文信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'dispatchInfo')" href="javascript:void(0)">派遣信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'employInfo')" href="javascript:void(0)">就业信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'archivesInfo')" href="javascript:void(0)">档案去向</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'medicalInfo')" href="javascript:void(0)">医保孕育</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'dormitoryInfo')" href="javascript:void(0)">宿舍信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'dossierInfo')" href="javascript:void(0)">档案信息</a>
                        </li>
                    </ul>
                    <div id="tagContent" style="margin-top: 3px;">
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="baseInfo">
                        <input type="hidden" id="baseStatus" name="baseStatus" value="${param.baseStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;基本信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="baseCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneOne">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >学号：</span></th>
                                    <td style="width:30%;">
                                        <label>${eduUser.sid}</label>
                                    </td>
                                    <th style="width:20%;"><span >证件类型：</span></th>
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
                                    <th><span >姓名拼音：</span></th>
                                    <td>
                                        <label>${eduUser.nameSpell }</label>
                                    </td>
                                    <th><span >民族：</span></th>
                                    <td>
                                        <label>${sysUser.nationName}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >性别：</span></th>
                                    <td>
                                        <label>${sysUser.sexName }</label>
                                    </td>
                                    <th><span >班级：</span></th>
                                    <td>
                                        <label>${eduUser.className}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >出生日期：</span></th>
                                    <td>
                                        <label>${sysUser.userBirthday }</label>
                                    </td>
                                    <th><span >专业名称：</span></th>
                                    <td>
                                        <label>[${eduUser.majorId}]${eduUser.majorName}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >入学年级：</span></th>
                                    <td>
                                        ${eduUser.period }
                                    </td>
                                    <th><span >培养层次：</span></th>
                                    <td>
                                        <label>${eduUser.trainTypeName}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >学位分委员会：</span></th>
                                    <td>
                                        <c:forEach items="${deptList }" var="dept">
                                            ${dept.deptFlow eq sysUser.deptFlow?dept.deptName:'' }
                                        </c:forEach>
                                    </td>
                                    <th><span >是否5+3培养模式：</span></th>
                                    <td>
                                        <label>
                                            <c:if test="${eduUser.is5plus3==GlobalConstant.FLAG_Y}">是</c:if>
                                            <c:if test="${eduUser.is5plus3==GlobalConstant.FLAG_N}">否</c:if>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >培养单位：</span></th>
                                    <td>
                                        <label>${resDoctor.orgName }</label>
                                    </td>
                                    <th><span >研究方向：</span></th>
                                    <td>
                                        <label>${eduUser.researchDirName }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >培养类型：</span></th>
                                    <td>
                                        <label>${eduUser.trainCategoryName}</label>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th><span >导师一：</span></th>
                                    <td>
                                        <label>${eduUser.firstTeacher }</label>
                                    </td>
                                    <th><span >导师二：</span></th>
                                    <td>
                                        <label>${eduUser.secondTeacher }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >学籍状态：</span></th>
                                    <td>
                                        <label>${eduUser.schoolRollStatusName}</label>
                                    </td>
                                    <th><span >学籍注册时间：</span></th>
                                    <td>
                                        <label>${extInfoForm.xjRegDate }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >是否报到：</span></th>
                                    <td>
                                        <label>
                                            <c:if test="${eduUser.isReported eq GlobalConstant.FLAG_Y}">是</c:if>
                                            <c:if test="${eduUser.isReported eq GlobalConstant.FLAG_N}">否</c:if>
                                        </label>
                                    </td>
                                    <th><span >报到时间：</span></th>
                                    <td>
                                        <label>${extInfoForm.reportedDate}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >在校状态：</span></th>
                                    <td>
                                        <label>${eduUser.atSchoolStatusName }</label>
                                    </td>
                                    <th><span >学习形式：</span></th>
                                    <td>
                                        <label>${eduUser.studyFormName}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >是否休学：</span></th>
                                    <td>
                                        <label>
                                            <c:if test="${eduUser.isOutOfSchool eq GlobalConstant.FLAG_Y}">是</c:if>
                                            <c:if test="${eduUser.isOutOfSchool eq GlobalConstant.FLAG_N}">否</c:if>
                                        </label>
                                    </td>
                                    <th><span >${eduUser.isOutOfSchool eq GlobalConstant.FLAG_Y?'休学日期：':''}</span></th>
                                    <td>
                                        <label>${eduUser.isOutOfSchool eq GlobalConstant.FLAG_Y?extInfoForm.outOfSchoolDate:''}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >是否复学：</span></th>
                                    <td>
                                        <label>
                                            <c:if test="${eduUser.isBackToSchool eq GlobalConstant.FLAG_Y}">是</c:if>
                                            <c:if test="${eduUser.isBackToSchool eq GlobalConstant.FLAG_N}">否</c:if>
                                        </label>
                                    </td>
                                    <th><span >${eduUser.isBackToSchool eq GlobalConstant.FLAG_Y?'复学日期：':''}</span></th>
                                    <td>
                                        <label>${eduUser.isBackToSchool eq GlobalConstant.FLAG_Y?extInfoForm.backToSchoolDate:''}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >退学时间：</span></th>
                                    <td>
                                        <label>${extInfoForm.dropOutSchoolDate}</label>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="recruitInfo">
                        <input type="hidden" id="recruitStatus" name="recruitStatus" value="${param.recruitStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;录取信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="recruitCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneTwo">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >考生编号：</span></th>
                                    <td style="width:30%;">
                                        <label>${eduUser.studentCode }</label>
                                    </td>
                                    <th style="width:20%;"><span >考生来源：</span></th>
                                    <td style="width:30%;">
                                        <label>
                                            <c:forEach items="${dictTypeEnumStudentSourceList}" var="studentSource">
                                                ${eduUser.studentSourceId eq studentSource.dictId?studentSource.dictName:''}
                                            </c:forEach>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >录取类别：</span></th>
                                    <td>
                                        <label>${eduUser.admitTypeName}</label>
                                    </td>
                                    <th><span >招生季节：</span></th>
                                    <td>
                                        <label>${eduUser.recruitSeasonName}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >招录方式：</span></th>
                                    <td>
                                        <label>
                                            <c:forEach items="${dictTypeEnumRecruitTypeList}" var="dict">
                                                ${extInfoForm.recruitType eq dict.dictId? dict.dictName:''}
                                            </c:forEach>
                                        </label>
                                    </td>
                                    <th><span >是否破格：</span></th>
                                    <td>
                                        <label>${eduUser.isExceptionalName}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >外国语名称：</span></th>
                                    <td>
                                        <label>${extInfoForm.foreignLanguageName }</label>
                                    </td>
                                    <th><span >外国语成绩：</span></th>
                                    <td>
                                        <label>${extInfoForm.foreignLanguageScore }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >政治理论：</span></th>
                                    <td>
                                        <label>${extInfoForm.politicalTheoryName }</label>
                                    </td>
                                    <th><span >政治理论成绩：</span></th>
                                    <td>
                                        <label>${extInfoForm.politicalTheoryScore }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >业务课一：</span></th>
                                    <td>
                                        <label>${extInfoForm.firstSubjectName }</label>
                                    </td>
                                    <th><span >业务课一成绩：</span></th>
                                    <td>
                                        <label>${extInfoForm.firstSubjectScore }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >业务课二：</span></th>
                                    <td>
                                        <label>${extInfoForm.secondSubjectName }</label>
                                    </td>
                                    <th><span >业务课二成绩：</span></th>
                                    <td>
                                        <label>${extInfoForm.secondSubjectScore }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >加试科一：</span></th>
                                    <td>
                                        ${extInfoForm.firstAddExamName }
                                    </td>
                                    <th><span >加试科一成绩：</span></th>
                                    <td>
                                        <label>${extInfoForm.firstAddExamScore }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >加试科二：</span></th>
                                    <td>
                                        <label>${extInfoForm.secondAddExamName }</label>
                                    </td>
                                    <th><span >加试科二成绩：</span></th>
                                    <td>
                                        <label>${extInfoForm.secondAddExamScore }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >复试成绩：</span></th>
                                    <td>
                                        <label>${extInfoForm.reexamineScore }</label>
                                    </td>
                                    <th><span >总分：</span></th>
                                    <td>
                                        <label>${extInfoForm.totalPointsScore }</label>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="needInfo">
                        <input type="hidden" id="needStatus" name="needStatus" value="${param.needStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;必填信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="needCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneThree">
                            <form id="spreadOneThreeForm">
                                <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                    <tr>
                                        <td rowspan="5" style="width:20%; text-align:center;margin-top: 2px;">
                                            <img src="${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}" width="100px;" height="130px;" onerror="this.src='<s:url value="/jsp/xjgl/images/up-pic.jpg"/>'"/>
                                        </td>
                                        <th><span >籍贯：</span></th>
                                        <td colspan="2">
                                            <label>${sysUser.nativePlaceProvName}${sysUser.nativePlaceCityName}${sysUser.nativePlaceAreaName}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >婚姻状况：</span></th>
                                        <td colspan="2">
                                            <label>${sysUser.maritalName}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >生育状况：</span></th>
                                        <td colspan="2">
                                            <label>${sysUser.bearName }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >政治面貌：</span></th>
                                        <td colspan="2">
                                            ${sysUser.politicsStatusName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >入党（团）时间：</span></th>
                                        <td colspan="2">
                                            <label>${extInfoForm.joinOrgTime }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th style="width:20%"><span >党团关系是否转入：</span></th>
                                        <td style="width:30%">
                                            <c:if test="${extInfoForm.isRelationInto==GlobalConstant.FLAG_Y}">是</c:if>
                                            <c:if test="${extInfoForm.isRelationInto==GlobalConstant.FLAG_N}">否</c:if>
                                        </td>
                                        <th style="width:20%;"><span >${extInfoForm.isRelationInto eq GlobalConstant.FLAG_Y?'转入年月日：':''}</span></th>
                                        <td style="width:30%">
                                            <label>${extInfoForm.isRelationInto eq GlobalConstant.FLAG_Y?(empty extInfoForm.joinTime?'等待老师录入':extInfoForm.joinTime):''}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >入学前户口所在地：</span></th>
                                        <td colspan="3">
                                            ${domicileName[0]}${domicileName[1]}${domicileName[2]}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >入学前户口所在详细地址：</span></th>
                                        <td colspan="3">
                                            <label>${sysUser.domicilePlaceAddress }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >户口是否需要迁入我校：</span></th>
                                        <td>
                                            <label>
                                                <c:if test="${extInfoForm.hkInSchool eq '1'}">是</c:if>
                                                <c:if test="${extInfoForm.hkInSchool eq '2'}">否</c:if>
                                            </label>
                                        </td>
                                        <th><span >${extInfoForm.hkInSchool eq '1'?'户口迁移证编号：':''}</span></th>
                                        <td>
                                            <label>${extInfoForm.hkInSchool eq '1'?extInfoForm.hkChangeNo:'' }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >原学习或工作单位：</span></th>
                                        <td colspan="3">
                                            <label>${extInfoForm.oldOrgName }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >原档案所在单位：</span></th>
                                        <td>
                                            <label>${extInfoForm.oldFileLocationOrg }</label>
                                        </td>
                                        <th style="width:20%;"><span >原档案所在单位邮编：</span></th>
                                        <td style="width:30%;">
                                            <label>${extInfoForm.oldFileLocationOrgCode }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >档案所在地：</span></th>
                                        <td colspan="3">
                                            ${recordName[0]}${recordName[1]}${recordName[2]}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >现家庭住址：</span></th>
                                        <td colspan="3">
                                            <label>${extInfoForm.nowResideAddress }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >紧急联系人姓名：</span></th>
                                        <td>
                                            ${extInfoForm.linkMan }
                                        </td>
                                        <th><span >紧急联系人电话：</span></th>
                                        <td>
                                            <label>${extInfoForm.telephone }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >是否有医师资格证：</span></th>
                                        <td>
                                            <c:if test="${extInfoForm.isDoctorQuaCert==GlobalConstant.FLAG_Y}">是</c:if>
                                            <c:if test="${extInfoForm.isDoctorQuaCert==GlobalConstant.FLAG_N}">否</c:if>
                                        </td>
                                        <th><span >${extInfoForm.isDoctorQuaCert eq GlobalConstant.FLAG_Y?'资格证编号：':''}</span></th>
                                        <td>
                                            <label>${extInfoForm.isDoctorQuaCert eq GlobalConstant.FLAG_Y?extInfoForm.codeDoctorQuaCert:''}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >是否有执业医师执照：</span></th>
                                        <td>
                                            <c:if test="${extInfoForm.isMedicalPractitioner==GlobalConstant.FLAG_Y}">是</c:if>
                                            <c:if test="${extInfoForm.isMedicalPractitioner==GlobalConstant.FLAG_N}">否</c:if>
                                        </td>
                                        <th><span >${extInfoForm.isMedicalPractitioner eq GlobalConstant.FLAG_Y?'执照编号：':''}</span></th>
                                        <td>
                                            <label>${extInfoForm.isMedicalPractitioner eq GlobalConstant.FLAG_Y?extInfoForm.codeMedicalPractitioner:''}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >是否住宿：</span></th>
                                        <td>
                                            <c:if test="${extInfoForm.isStay==GlobalConstant.FLAG_Y}">是</c:if>
                                            <c:if test="${extInfoForm.isStay==GlobalConstant.FLAG_N}">否</c:if>
                                        </td>
                                        <th>&#12288;<span >${extInfoForm.isStay eq GlobalConstant.FLAG_Y?'宿舍号：':''}</span></th>
                                        <td>
                                            ${extInfoForm.isStay eq GlobalConstant.FLAG_Y?extInfoForm.roomNumStay:''}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >本人手机号码：</span></th>
                                        <td>
                                            ${sysUser.userPhone }
                                        </td>
                                        <th><span >电子邮箱：</span></th>
                                        <td>
                                            ${sysUser.userEmail }
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >微信号：</span></th>
                                        <td>
                                            ${sysUser.weiXinName }
                                        </td>
                                        <th><span >QQ号：</span></th>
                                        <td>
                                            ${sysUser.userQq }
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >身高：</span></th>
                                        <td>
                                            ${extInfoForm.height }
                                        </td>
                                        <th><span >血型：</span></th>
                                        <td>
                                            ${extInfoForm.bloodType }
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>&#12288;<span >医保卡号：</span></th>
                                        <td>
                                            ${extInfoForm.ybCardNo }
                                        </td>
                                        <th></th>
                                        <td></td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="selectInfo">
                        <input type="hidden" id="selectStatus" name="selectStatus" value="${param.selectStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;选填信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="selectCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneFour">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >普通话水平：</span></th>
                                    <td style="width:30%;">
                                        <label>${extInfoForm.mandarinLevel }</label>
                                    </td>
                                    <th style="width:20%;"><span >外语水平：</span></th>
                                    <td style="width:30%;">
                                        <label>${extInfoForm.foreignLanguageLevel }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >计算机水平：</span></th>
                                    <td>
                                        <label>${extInfoForm.computerLevel }</label>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th><span >学制：</span></th>
                                    <td>
                                        <label>${extInfoForm.schoolSystem }</label>
                                    </td>
                                    <th><span >生源省市：</span></th>
                                    <td>
                                        <label>${extInfoForm.studentSourceArea }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >宿舍地址：</span></th>
                                    <td>
                                        <label>${extInfoForm.dormitoryAdd }</label>
                                    </td>
                                    <th><span >家庭电话：</span></th>
                                    <td>
                                        <label>${extInfoForm.homePhone }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >配偶姓名：</span></th>
                                    <td>
                                        <label>${extInfoForm.mateName }</label>
                                    </td>
                                    <th><span >配偶身份证：</span></th>
                                    <td>
                                        <label>${extInfoForm.mateIdNo }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >配偶工作单位：</span></th>
                                    <td colspan="3">
                                        <label>${extInfoForm.spouseUnit }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >特长：</span></th>
                                    <td colspan="3">
                                        <label>${extInfoForm.speciality }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >本人主要简历：</span></th>
                                    <td colspan="3">
                                        <label>${extInfoForm.mainResume }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >入学前奖惩情况：</span></th>
                                    <td colspan="3">
                                        <label>${extInfoForm.reAndPuStatusContent }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >曾担任过合作教学工作&#12288;<br>和进行何种科研工作：</span></th>
                                    <td colspan="3">
                                        <label>${extInfoForm.scientificTogether }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >备注：</span></th>
                                    <td colspan="3">
                                        <label>${extInfoForm.remark }</label>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="feeInfo">
                        <input type="hidden" id="feeStatus" name="feeStatus" value="${param.feeStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;学费信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="feeCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneFive">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >缴费账号：</span></th>
                                    <td style="width:30%;">
                                        <label>${extInfoForm.accountNum }</label>
                                    </td>
                                    <th style="width:20%;"><span >学费总额：</span></th>
                                    <td style="width:30%;">
                                        <label>${extInfoForm.tuitionFee }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >已缴纳学费：</span></th>
                                    <td>
                                        <label>${extInfoForm.paytuitionFee }</label>
                                    </td>
                                    <th><span >欠缴纳学费：</span></th>
                                    <td>
                                        <label>${extInfoForm.arrearageFee }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >住宿费：</span></th>
                                    <td>
                                        <label>${extInfoForm.dormitoryFee }</label>
                                    </td>
                                    <th><span >已缴纳住宿费：</span></th>
                                    <td>
                                        <label>${extInfoForm.payDormitoryFee }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >欠缴纳住宿费：</span></th>
                                    <td>
                                        <label>${extInfoForm.arrearageDormitoryFee }</label>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="gotCertInfo">
                        <input type="hidden" id="gotCertStatus" name="gotCertStatus" value="${param.gotCertStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;已获得学历或学位信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="gotCertCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadTwoOne">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >第一学历：</span></th>
                                    <td style="width:30%;">
                                        <label>
                                            <c:forEach items="${dictTypeEnumFirstEducationList}" var="firstEducation">
                                                ${extInfoForm.firstEducationContentId eq firstEducation.dictId ? firstEducation.dictName:''}
                                            </c:forEach>
                                        </label>
                                    </td>
                                    <th style="width:20%;"><span >前置学历：</span></th>
                                    <td style="width:30%;">
                                        <label>
                                            <c:forEach items="${dictTypeEnumPreGraduationList}" var="dict">
                                                ${extInfoForm.preGraduation eq dict.dictId ? dict.dictName:''}
                                            </c:forEach>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >是否获得本科学历：</span></th>
                                    <td>
                                        <label>
                                            <c:if test="${extInfoForm.bkgain eq '1'}">是</c:if>
                                            <c:if test="${extInfoForm.bkgain eq '2'}">否</c:if>
                                        </label>
                                    </td>
                                    <th style="width:20%;"><span >${extInfoForm.bkgain eq '1'?'获得本科学历的学习形式：':''}</span></th>
                                    <td style="width:30%;">
                                        <label>
                                            <c:forEach items="${dictTypeEnumUnderStudyFormList}" var="dict">
                                                ${extInfoForm.underStudyForm eq dict.dictId && extInfoForm.bkgain eq '1'?dict.dictName:''}
                                            </c:forEach>
                                        </label>
                                    </td>
                                </tr>
                                <c:if test="${extInfoForm.bkgain eq '1'}">
                                    <tr>
                                        <th><span >本科毕业年月：</span></th>
                                        <td>
                                            <label>${extInfoForm.underGraduateTime }</label>
                                        </td>
                                        <th><span >本科毕业证书编号：</span></th>
                                        <td style="word-break : break-all;">
                                            <label>${extInfoForm.underDiplomaCode }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >本科毕业单位名称：</span></th>
                                        <td>
                                            <label>${extInfoForm.underGraduateOrgName }</label>
                                        </td>
                                        <th><span >本科毕业专业名称：</span></th>
                                        <td>
                                            <label>${extInfoForm.underGraduateMajor }</label>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th><span >是否获得学士学位：</span></th>
                                    <td>
                                        <label>
                                            <c:if test="${extInfoForm.xsgain eq '1'}">是</c:if>
                                            <c:if test="${extInfoForm.xsgain eq '2'}">否</c:if>
                                        </label>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <c:if test="${extInfoForm.xsgain eq '1'}">
                                    <tr>
                                        <th><span >获得学士学位年月：</span></th>
                                        <td>
                                            <label>${extInfoForm.underAwardDegreeTime }</label>
                                        </td>
                                        <th><span >学士学位证书编号：</span></th>
                                        <td>
                                            <label>${extInfoForm.underDegreeCertCode }</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >获学士学位单位名称：</span></th>
                                        <td>
                                            <label>${extInfoForm.underAwardDegreeOrg }</label>
                                        </td>
                                        <th><span >获学士学位专业名称：</span></th>
                                        <td>
                                            <label>${ extInfoForm.underMajor}</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span >获得学士学位学科门类：</span></th>
                                        <td>
                                            <label>${extInfoForm.gotBachelorCertSubject}</label>
                                        </td>
                                        <th></th>
                                        <td></td>
                                    </tr>
                                </c:if>
                                <c:if test="${eduUser.trainTypeId ne '1'}">
                                    <tr>
                                        <th><span >最后学位证编号：</span></th>
                                        <td>
                                            <label>${extInfoForm.code }</label>
                                        </td>
                                        <th></th>
                                        <td></td>
                                    </tr>
                                </c:if>
                                <c:if test="${!empty eduUser.trainTypeId && eduUser.trainTypeId > '1' }">
                                    <tr>
                                        <th><span >是否获得硕士研究生学历：</span></th>
                                        <td>
                                            <label>
                                                <c:if test="${extInfoForm.ssyjsgain eq '1'}">是</c:if>
                                                <c:if test="${extInfoForm.ssyjsgain eq '2'}">否</c:if>
                                            </label>
                                        </td>
                                        <th></th>
                                        <td></td>
                                    </tr>
                                    <c:if test="${extInfoForm.ssyjsgain eq '1'}">
                                        <tr>
                                            <th><span >硕士研究生毕业年月：</span></th>
                                            <td>
                                                <label>${extInfoForm.masterGraduateTime }</label>
                                            </td>
                                            <th><span >硕士研究生毕业证编号：</span></th>
                                            <td>
                                                <label>${extInfoForm.masterDiplomaCode }</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th><span >硕士研究生毕业单位名称：</span></th>
                                            <td>
                                                <label>${extInfoForm.masterUnitName }</label>
                                            </td>
                                            <th><span >硕士研究生毕业专业名称：</span></th>
                                            <td>
                                                <label>${extInfoForm.masterGraduateMajor }</label>
                                            </td>
                                        </tr>
                                    </c:if>

                                    <tr>
                                        <th><span >是否获得硕士学位：</span></th>
                                        <td>
                                            <label>
                                                <c:if test="${extInfoForm.ssxwgain eq '1'}">是</c:if>
                                                <c:if test="${extInfoForm.ssxwgain eq '2'}">否</c:if>
                                            </label>
                                        </td>
                                        <th><span >${extInfoForm.ssxwgain eq '1'?'获得硕士学位的学习形式：':''}</span></th>
                                        <td>
                                            <label>
                                                <c:forEach items="${dictTypeEnumMasterStudyFormList}" var="dict">
                                                    ${extInfoForm.masterStudyForm eq dict.dictId && extInfoForm.ssxwgain eq '1'?dict.dictName:''}
                                                </c:forEach>
                                            </label>
                                        </td>
                                    </tr>
                                    <c:if test="${extInfoForm.ssxwgain eq '1'}">
                                        <tr>
                                            <th><span >获得硕士学位年月：</span></th>
                                            <td>
                                                <label>${extInfoForm.masterAwardDegreeTime }</label>
                                            </td>
                                            <th><span >硕士学位证书编号：</span></th>
                                            <td>
                                                <label>${extInfoForm.masterDegreeCertCode }</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th><span >获得硕士学位单位名称：</span></th>
                                            <td>
                                                <label>${extInfoForm.masterAwardDegreeOrg }</label>
                                            </td>
                                            <th><span >获得硕士学位学科门类：</span></th>
                                            <td>
                                                    ${extInfoForm.masterSubject}
                                            </td>
                                        </tr>
                                        <tr>
                                            <th><span >获得硕士学位专业：</span></th>
                                            <td>
                                                <label>${ extInfoForm.gotMasterCertSpe}</label>
                                            </td>
                                            <th></th>
                                            <td></td>
                                        </tr>
                                    </c:if>
                                </c:if>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="certReqInfo">
                        <input type="hidden" id="certReqStatus" name="certReqStatus" value="${param.certReqStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;攻读学历学位信息--%>
                            <%--<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">--%>
                                <%--<span style="float:right;margin-right:100px;"><input type="button" class="search" onclick="confirmStatus('CertReqInfo',this)" value="确认"/></span>--%>
                            <%--</c:if>--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="certReqCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadTwoTwo" <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && extInfoForm.graduateFlag eq '2'}">style="display:none;"</c:if>>
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width: 20%"><span >预毕业时间：</span></th>
                                    <td style="width: 30%">
                                        ${extInfoForm.planGraduateTime }
                                    </td>
                                    <th style="width: 20%"><span >是否毕业：</span></th>
                                    <td>
                                        ${extInfoForm.graduateFlag eq '1'?'是':''}
                                        ${extInfoForm.graduateFlag eq '2'?'否':''}
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >毕业时间：</span></th>
                                    <td>
                                        ${eduUser.graduateTime }
                                    </td>
                                    <th><span >毕业证书编号：</span></th>
                                    <td>
                                        ${eduUser.diplomaCode}
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >是否授予学位：</span></th>
                                    <td>
                                        <c:if test="${eduUser.awardDegreeFlag==GlobalConstant.FLAG_Y}">是</c:if>
                                        <c:if test="${eduUser.awardDegreeFlag==GlobalConstant.FLAG_N}">否</c:if>
                                    </td>
                                    <th><span >授予学科门类：</span></th>
                                    <td>
                                        <label>${extInfoForm.awardSubjectCategory}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >授予学位类别：</span></th>
                                    <td>
                                        ${eduUser.awardDegreeCategoryName }
                                    </td>
                                    <th><span >授予学位时间：</span></th>
                                    <td>
                                        <label>${eduUser.awardDegreeTime }</label>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >授予学位证书编号：</span></th>
                                    <td>
                                        ${eduUser.awardDegreeCertCode }
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="paperInfo">
                        <input type="hidden" id="paperStatus" name="paperStatus" value="${param.paperStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;学位论文信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="paperCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadThree">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%"><span >论文题目：</span></th>
                                    <td style="width:30%">
                                        ${extInfoForm.paperTitle }
                                    </td>
                                    <th style="width:20%"><span >论文选题来源：</span></th>
                                    <td style="width:30%">
                                        <c:forEach items="${dictTypeEnumPaperSourceList}" var="dict">
                                            ${extInfoForm.paperSource eq dict.dictId?dict.dictName:''}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >论文关键词：</span></th>
                                    <td>
                                        ${extInfoForm.paperKey }
                                    </td>
                                    <th><span >论文类型：</span></th>
                                    <td>
                                        <c:forEach items="${dictTypeEnumPaperTypeList}" var="dict">
                                            ${extInfoForm.paperType eq dict.dictId?dict.dictName:''}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="dispatchInfo">
                        <input type="hidden" id="dispatchStatus" name="dispatchStatus" value="${param.dispatchStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;派遣信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="dispatchCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadFourOne">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >具体派遣单位：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.sendUnit }
                                    </td>
                                    <th style="width:20%;"><span >报到地址：</span></th>
                                    <td style="width:30%;">
                                        ${fn:split(extInfoForm.reportAddressName,',')[0]}${fn:split(extInfoForm.reportAddressName,',')[1]}${fn:split(extInfoForm.reportAddressName,',')[2]}
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >派遣性质：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.sendNature eq '1'?'selected':''}">升学深造</c:if>
                                        <c:if test="${extInfoForm.sendNature eq '2'?'selected':''}">正常派遣</c:if>
                                        <c:if test="${extInfoForm.sendNature eq '3'?'selected':''}">申请暂缓就业</c:if>
                                        <c:if test="${extInfoForm.sendNature eq '4'?'selected':''}">出国</c:if>
                                        <c:if test="${extInfoForm.sendNature eq '5'?'selected':''}">不纳入就业方案</c:if>
                                    </td>
                                    <th style="width:20%;"><span >备注：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.sentMemo }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >派遣时间：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.sendTime }
                                    </td>
                                    <th style="width:20%;"></th>
                                    <td style="width:30%;"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="employInfo">
                        <input type="hidden" id="employStatus" name="employStatus" value="${param.employStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;就业信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="employCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadFourTwo" <c:if test="${extInfoForm.graduateFlag eq '2'}">style="display:none;"</c:if>>
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >毕业去向：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.graduationWhereabouts eq '1'?'selected':''}">签就业协议形式就业</c:if>
                                        <c:if test="${extInfoForm.graduationWhereabouts eq '2'?'selected':''}">签劳动合同形式就业</c:if>
                                        <c:if test="${extInfoForm.graduationWhereabouts eq '3'?'selected':''}">其他录用形式就业</c:if>
                                        <c:if test="${extInfoForm.graduationWhereabouts eq '4'?'selected':''}">其他情况</c:if>
                                    </td>
                                    <th style="width:20%;"><span >单位组织机构代码：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.unitCode }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >具体就业单位：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.workUnit }
                                    </td>
                                    <th style="width:20%;"><span >单位类型：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.workType eq '1'?'selected':''}">地市（州、盟、省辖市）厅局属的医疗卫生单位</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >单位所属行业：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.workOfIndustry eq '1'?'selected':''}">卫生</c:if>
                                    </td>
                                    <th style="width:20%;"><span >单位所属地区：</span></th>
                                    <td style="width:30%;">
                                        ${fn:split(extInfoForm.workOfAreaName,',')[0]}${fn:split(extInfoForm.workOfAreaName,',')[1]}${fn:split(extInfoForm.workOfAreaName,',')[2]}
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >单位联系人：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.workLinkMan }
                                    </td>
                                    <th style="width:20%;"><span >职业类型：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.occupationType eq '1'?'selected':''}">西医医师</c:if>
                                        <c:if test="${extInfoForm.occupationType eq '2'?'selected':''}">中医医师</c:if>
                                        <c:if test="${extInfoForm.occupationType eq '3'?'selected':''}">中西医结合医师</c:if>
                                        <c:if test="${extInfoForm.occupationType eq '4'?'selected':''}">护理人员</c:if>
                                        <c:if test="${extInfoForm.occupationType eq '5'?'selected':''}">管理科学研究人员</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >单位联系电话：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.workLinkPhone }
                                    </td>
                                    <th style="width:20%;"><span >薪酬：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.workPay }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >使用意图：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.userAim eq '1'?'selected':''}">行政</c:if>
                                        <c:if test="${extInfoForm.userAim eq '2'?'selected':''}">读书</c:if>
                                        <c:if test="${extInfoForm.userAim eq '3'?'selected':''}">服役</c:if>
                                        <c:if test="${extInfoForm.userAim eq '4'?'selected':''}">待业</c:if>
                                        <c:if test="${extInfoForm.userAim eq '5'?'selected':''}">出国</c:if>
                                        <c:if test="${extInfoForm.userAim eq '6'?'selected':''}">其他</c:if>
                                    </td>
                                    <th style="width:20%;"><span >签约时间：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.signTime }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >是否专业对口：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.majorCounterpart eq 'Y'?'selected':''}">是</c:if>
                                        <c:if test="${extInfoForm.majorCounterpart eq 'N'?'selected':''}">否</c:if>
                                    </td>
                                    <th style="width:20%;"><span >用人单位邮箱：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.workEmail }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >是否就业困难：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.employmentLevel eq 'Y'?'selected':''}">是</c:if>
                                        <c:if test="${extInfoForm.employmentLevel eq 'N'?'selected':''}">否</c:if>
                                    </td>
                                    <th style="width:20%;"><span >就业协议书上传：</span></th>
                                    <td style="width:30%;">
                                        <span style="display:${empty extInfoForm.employAgreementUrl?'none':''}">&#12288;[<a href="${sysCfgMap['upload_base_url']}/${extInfoForm.employAgreementUrl}" style="cursor:pointer;color:blue;" target="_blank">查看</a>]</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >暂缓就业表登记：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.employSign }
                                    </td>
                                    <th style="width:20%;"></th>
                                    <td style="width:30%;"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="archivesInfo">
                        <input type="hidden" id="archivesStatus" name="archivesStatus" value="${param.archivesStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;档案去向信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="archivesCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadFourThree" <c:if test="${extInfoForm.graduateFlag eq '2'}">style="display:none;"</c:if>>
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >收件联系人：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.recipients }
                                    </td>
                                    <th style="width:20%;"><span >收件人电话：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.recipientsPhone }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >档案去向邮政编码：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.archivesPlaceCode }
                                    </td>
                                    <th style="width:20%;"><span >Ems单号：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.archivesEmsNum }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >档案去向邮寄地址：</span></th>
                                    <td style="width:30%;">
                                        ${fn:split(extInfoForm.archivesEmsAddressName,',')[0]}${fn:split(extInfoForm.archivesEmsAddressName,',')[1]}${fn:split(extInfoForm.archivesEmsAddressName,',')[2]}
                                    </td>
                                    <th style="width:20%;"><span >寄出时间：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.mailTime }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >档案去向单位：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.archivesUnit }
                                    </td>
                                    <th style="width:20%;"><span >学生电话：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.studentPhone }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >学生学号：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.studentCode }
                                    </td>
                                    <th style="width:20%;"><span >学生姓名：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.studentName }
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="medicalInfo">
                        <input type="hidden" id="medicalStatus" name="medicalStatus" value="${param.medicalStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;医保、孕育信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="medicalCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadFive">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >本次参保起始年份：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.joinMedicalYear }
                                    </td>
                                    <th style="width:20%;"><span >入学年份：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.enterSchoolYear }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >是否新生：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.isNewStudent eq 'Y'?'selected':''}">是</c:if>
                                        <c:if test="${extInfoForm.isNewStudent eq 'N'?'selected':''}">否</c:if>
                                    </td>
                                    <th style="width:20%;"><span >学院：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.college }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >国家地区：</span></th>
                                    <td style="width:30%;">
                                        ${fn:split(extInfoForm.countryAreaName,',')[0]}${fn:split(extInfoForm.countryAreaName,',')[1]}${fn:split(extInfoForm.countryAreaName,',')[2]}
                                    </td>
                                    <th style="width:20%;"><span >有无执业医师资格证：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.whetherPracticeCert eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.whetherPracticeCert eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >执业医师资格证编号：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.practiceCertCode }
                                    </td>
                                    <th style="width:20%;"><span >执业医师资格证上传：</span></th>
                                    <td style="width:30%;">
                                        <span style="display:${empty extInfoForm.practiceCertUrl?'none':''}">&#12288;[<a href="${sysCfgMap['upload_base_url']}/${extInfoForm.practiceCertUrl}" style="cursor:pointer;color:blue;" target="_blank">查看</a>]</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >是否购买医保：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.whetherBuyMedicalCard eq 'Y'?'selected':''}">是</c:if>
                                        <c:if test="${extInfoForm.whetherBuyMedicalCard eq 'N'?'selected':''}">否</c:if>
                                    </td>
                                    <th style="width:20%;"></th>
                                    <td style="width:30%;"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="dormitoryInfo">
                        <input type="hidden" id="dormitoryStatus" name="dormitoryStatus" value="${param.dormitoryStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;宿舍信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="dormitoryCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadSix">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >导师所在单位：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.unitOfDoctor }
                                    </td>
                                    <th style="width:20%;"><span >宿舍楼号：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.dormitoryNo }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >宿舍楼层：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.dormitoryFloor }
                                    </td>
                                    <th style="width:20%;"><span >宿舍房号：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.dormitoryRoomNumber }
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="dossierInfo">
                        <input type="hidden" id="dossierStatus" name="dossierStatus" value="${param.dossierStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;档案信息--%>
                            <%--<span style="float:right;margin-right:118px;line-height:27px;color:blue;cursor:pointer;" onclick="dossierCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadSeven">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >（党员）入党时间：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.partyMembershipTime }
                                    </td>
                                    <th style="width:20%;"><span >（党员）转正时间：</span></th>
                                    <td style="width:30%;">
                                        ${extInfoForm.partyMemberFormalTime }
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >EMS截图上传：</span></th>
                                    <td style="width:30%;">
                                        <span style="display:${empty extInfoForm.emsUrl?'none':''}">&#12288;[<a href="${sysCfgMap['upload_base_url']}/${extInfoForm.emsUrl}" style="cursor:pointer;color:blue;" target="_blank">查看</a>]</span>
                                    </td>
                                    <th style="width:20%;"></th>
                                    <td style="width:30%;"></td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >高中：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.highSchool eq 'Y'?'selected':''}">是</c:if>
                                        <c:if test="${extInfoForm.highSchool eq 'N'?'selected':''}">否</c:if>
                                    </td>
                                    <th style="width:20%;"><span >大学：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.university eq 'Y'?'selected':''}">是</c:if>
                                        <c:if test="${extInfoForm.university eq 'N'?'selected':''}">否</c:if>
                                    </td>
                                </tr>
                                <tr><th colspan="4" style="width:100%;text-align:left;">&#12288;&#12288;硕士</th></tr>
                                <tr>
                                    <th style="width:20%;"><span >录取登记表：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.masterAdmissionSign eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.masterAdmissionSign eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                    <th style="width:20%;"><span >科学学位（审批表）：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.masterScienceDegree eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.masterScienceDegree eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >毕业登记表：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.masterGraduationSign eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.masterGraduationSign eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                    <th style="width:20%;"><span >临床学位（答辩表）：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.masterClinicalDegree eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.masterClinicalDegree eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >成绩单：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.masterSchoolReport eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.masterSchoolReport eq 'N'?'selected':''}">无</c:if>
                                        </select>
                                    </td>
                                    <th style="width:20%;"></th>
                                    <td style="width:30%;"></td>
                                </tr>
                                <tr><th colspan="4" style="width:100%;text-align:left;">&#12288;&#12288;博士</th></tr>
                                <tr>
                                    <th style="width:20%;"><span >录取登记表：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.doctorAdmissionSign eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.doctorAdmissionSign eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                    <th style="width:20%;"><span >科学学位（审批表）：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.doctorScienceDegree eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.doctorScienceDegree eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >毕业登记表：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.doctorGraduationSign eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.doctorGraduationSign eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                    <th style="width:20%;"><span >临床学位（答辩表）：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.doctorClinicalDegree eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.doctorClinicalDegree eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span >成绩单：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.doctorSchoolReport eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.doctorSchoolReport eq 'N'?'selected':''}">无</c:if>
                                    </td>
                                    <th style="width:20%;"><span >专家推荐信2封：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${extInfoForm.expertChitLetter eq 'Y'?'selected':''}">有</c:if>
                                        <c:if test="${extInfoForm.expertChitLetter eq 'N'?'selected':''}">无</c:if>
                                    </td>
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