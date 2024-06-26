<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        .cur{ background-color: pink;}
        .basic td,tr{border: 0}
    </style>
    <script type="text/javascript">
        function selectTag(selfObj,url) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            $("div.spreadOne").hide();
            $("#"+url).show();
        }
    </script>
    <style type="text/css">
        .table tr td, .table tr th{border-bottom: 0px; }
        .table1 td{border: none;}
        .table1{border: none;}
        .basicData{border:1px solid #bbbbbb;}
        .basicData th,.basicData td { border-right:1px solid #bbbbbb; border-bottom:1px solid #bbbbbb; height:35px;}
        .basicData tbody th { background:#f9f9f9; color:#333; height:35px; text-align:center;}
        .basicData td { text-align:center; line-height:35px;}
    </style>
</head>
<body>
<%--<div class="header">--%>
    <%--<div class="top">--%>
        <%--<p class="tleft"><img src="<s:url value='/css/skin/${skinPath}/images/${applicationScope.sysCfgMap["sys_login_img"]}_head.png'/>" /></p>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="mainright">
    <div class="content">
        <div style="text-align: center;margin-bottom: 8px;"><font style="font-size: 18px;">${tutor.doctorTypeName}：${tutor.doctorName}</font></div>
        <ul id="tags">
            <li class="selectTag">
                <a onclick="selectTag(this,'introduction')" href="javascript:void(0)">个人信息</a>
            </li>
            <li>
                <a onclick="selectTag(this,'recruit')" href="javascript:void(0)">招生信息</a>
            </li>
        </ul>
        <div id="tagContent" style="margin-top: 3px;margin-bottom: 8px;"></div>
        <div class="spreadOne" id="introduction">
        <fieldset>
            <legend>基本信息</legend>
            <table class="basicData" style="width: 100%;">
                <tr>
                    <td style="text-align: center;" width="20%" rowspan="7"><img src="${sysCfgMap['upload_base_url']}${tutor.headUrl}" width="150px" height="200px"/></td>
                    <td style="text-align: left;" width="80%">&#12288;姓名：${tutor.doctorName}（${tutor.spellName}）</td>
                </tr>
                <tr>
                    <td style="text-align: left;" width="80%">&#12288;技术职称：${tutor.technicalTitleName}</td></tr>
                <tr>
                    <td style="text-align: left;" width="80%">&#12288;工作单位：${tutor.workUnit}</td></tr>
                <tr>
                    <td style="text-align: left;" width="80%">&#12288;导师类型：${tutor.doctorTypeName}</td></tr>
                <tr>
                    <td style="text-align: left;" width="80%">&#12288;研究方向与专业：${tutor.researchDirection}/${tutor.recruitSpeName}</td></tr>
                <tr>
                    <td style="text-align: left;" width="80%">&#12288;联系方式：${tutor.emailNo}</td></tr>
                <tr>
                    <td style="text-align: left;" width="80%">&#12288;学术任职：${tutor.academicActivities}</td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>从事的主要研究方向及其特点和意义、开展新医疗、新技术等情况、国内所处的学术地位</legend>
            <table class="basicData" style="width: 100%;">
                <tr>
                    <td style="text-align: left">&#12288;&#12288;${tutor.researchDescribe}</td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>个人荣誉</legend>
            <table class="basicData" style="width: 100%;">
                <tr>
                    <td style="text-align: left">&#12288;&#12288;${tutor.awardSituation}</td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>学术专著</legend>
            <table class="basicData" style="width: 100%;">
                <tr>
                    <td style="text-align: left">&#12288;&#12288;${tutor.academicMonographs}</td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>代表性论文</legend>
            <table class="basicData" style="width: 100%;">
                <tr style="font-weight: bold;">
                    <td style="width: 50%;">论文标题</td>
                    <td style="width: 30%;">期刊名称</td>
                    <td style="width: 20%;">影响因子</td>
                </tr>
                <c:forEach items="${paperList}" var="paper">
                    <tr>
                        <td>${paper.paperTitle}</td>
                        <td>${paper.periodicalName}</td>
                        <td>${paper.influenceFactor}</td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>
        <fieldset>
            <legend>在研课题</legend>
            <table class="basicData" style="width: 100%;">
                <tr style="font-weight: bold;">
                    <td style="width: 50%;">课题名称</td>
                    <td style="width: 30%;">课题级别</td>
                    <td style="width: 20%;">经费（万元）</td>
                </tr>
                <c:forEach items="${topicList}" var="topic">
                    <tr>
                        <td>${topic.topicTitle}</td>
                        <td>${topic.topicLevelName}</td>
                        <td>${topic.topicMoney}</td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>
        </div>
        <div class="spreadOne" hidden="hidden" id="recruit">
            <fieldset>
                <legend>招生信息</legend>
                <table class="basicData" style="width: 100%;">
                    <tr>
                        <td style="min-width:180px;width:1%;text-align: right;">导师类型：</td>
                        <td style="width:99%;text-align: left;">&emsp;${tutor.doctorTypeName}</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">分委员会：</td>
                        <td style="text-align: left;">&emsp;${tutor.fwhOrgName}</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">培养单位：</td>
                        <td style="text-align: left;">&emsp;${tutor.pydwOrgName}</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">招生专业：</td>
                        <td style="text-align: left;">&emsp;${tutor.zsCategoryName}>${tutor.zsSubjectName}>${tutor.recruitSpeName}</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">在读研究生人数：</td>
                        <td style="text-align: left;">&emsp;硕士（${tutor.zdyjsMasterNum}）博士（${tutor.zdyjsDoctorNum}）在职博士（${tutor.inserviceDoctorNum}）</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">申招研究生名额：</td>
                        <td style="text-align: left;">&emsp;硕士（${tutor.sbyjsMasterNum}）博士（${tutor.sbyjsDoctorNum}）在职博士（${tutor.declareDoctorNum}）</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">研究生论文盲审通过率：</td>
                        <td style="text-align: left;">&emsp;硕士（${tutor.paperMasterRate}%）博士（${tutor.paperDoctorRate}%）</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">培养研究生论文获奖情况：</td>
                        <td style="text-align: left;"><div style="padding:8px 0px;line-height:20px;">&emsp;${tutor.pyyjsAwardDescribe}</div></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">培养研究生其他获奖情况：</td>
                        <td style="text-align: left;"><div style="padding:8px 0px;line-height:20px;">&emsp;${tutor.pyyjsOtherDescribe}</div></td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>
