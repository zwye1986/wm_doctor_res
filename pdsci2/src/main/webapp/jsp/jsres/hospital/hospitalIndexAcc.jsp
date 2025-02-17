<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        require.config({
            paths: {
                echarts: "<s:url value='/js/echarts'/>"
            }
        });

        require(['echarts', 'echarts/chart/pie'], function (ec) {
            var myChart = ec.init(document.getElementById('docChartForIndex'));
            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} ({d}%)"
                },
                title: {
                    text: '数据自2014年起',
                    x: 'left',
                    y: 'bottom'
                },
                series: [
                    {

                        type: 'pie',
                        radius: '65%',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true
                                },
                                labelLine: {
                                    show: true
                                }
                            }
                        },
                        data: [
                            {value: "${currDocSumBef2014['_20count']}", name: '在培人数：${currDocSumBef2014['_20count']}人'},
                            {
                                value: "${currDocSumBef2014['_22count']}",
                                name: '已考核待结业人数：${currDocSumBef2014['_22count']}人'
                            },
                            {value: "${currDocSumBef2014['_21count']}", name: '结业人数：${currDocSumBef2014['_21count']}人'}
                        ]
                    }
                ]
            };
// 为echarts对象加载数据
            myChart.setOption(option);
        });

        function EvaStudent() {
            jboxLoad("content", "<s:url value='/res/teacherEvaDoctor/manageEvaluation'/>?recTypeId=ManageDoctorAssess360&roleFlag=local&trainingTypeId=AssiGeneral&sessionNumber=${pdfn:getCurrYear()}", true);
        }

        function pjjgcx(role) {
            var url = "<s:url value='/res/evaluateHospitalResult/base?gradeRole=doctor&role='/>" + role+"&trainingTypeId=AssiGeneral";
            currentJboxLoadNoData("content", url, true);
        }


        function hsxxgl() {
            jboxLoad("content", "<s:url value='/res/nurse/list'/>", true);
        }

        function djxxgl() {
            jboxLoad("content", "<s:url value='/res/nurse/teachingList'/>", true);
        }

    </script>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        $(document).ready(function () {
//	$('#sessionNumber').datepicker({
//		startView: 2,
//		maxViewMode: 2,
//		minViewMode:2,
//		format:'yyyy',
//		onSelect: function(selectedDate) {//选择日期后执行的操作
//			alert(selectedDate);
//		}
//	});
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
            if ("${speFlag}" != "0") {
                $("#speFlag").show();
            }
        });
        $(document).ready(function () {
            init();
            loadTheRoles();
            $("#changeRole").on("mouseenter mouseleave",function(){
                $("#roleContent").toggle();
            });
        });

        function loadTheRoles(){
            var name = "住院医师";
            var name2 = "助理全科";
            var root ="<s:url value='/'/>";
            /*if('/' == root){
                root = "/pdsci/";
            }*/
            var url = root+"jsres/manage/localChangePhyAcc?type=phy";
            var url2 = root+"jsres/manage/localChangePhyAcc?type=acc";
            var roleItem = $('<div/>').addClass("selRole").attr("url",url).text(name).click(function(){
                location.href = $(this).attr("url");
            });
            var roleItem2 = $('<div/>').addClass("selRole").attr("url",url2).text(name2).click(function(){
                location.href = $(this).attr("url");
            });
            $("#roleHome").append(roleItem);
            $("#roleHome").append(roleItem2);
        }

        function init() {
            <c:forEach items="${resDocTypeEnumList}" var="type">
            $("#" + "${type.id}").attr("checked", true);
            </c:forEach>
            getSpeInitData(getDocInitData);
            initOrg(null, null);
            setTimeout(bindFunc, 3000);
        }

        function getDocInitData(fun) {
            var sessionNumber = $("#sessionNumber").val();
            if (sessionNumber == "") {
                jboxTip("年份不能为空！");
                return;
            }
            var trainTypeId = $("#trainTypeId").val();
            if (trainTypeId == "") {
                jboxTip("培训类别不能为空！");
                return;
            }
            var data = "";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if ($("#" + "${type.id}").attr("checked")) {
                data += "&datas=" + $("#" + "${type.id}").val();
            }
            </c:forEach>
            if (data == "") {
                jboxTip("请选择人员类型！");
                return;
            }
            var geturl = "<s:url value='/jsres/manage/doctorNumSearch'/>?" + $("#doctorNumSearch").serialize() + data;
            $.ajax({
                type: "get",
                url: geturl,
                cache: false,
                beforeSend: function () {
                    jboxStartLoading();
                },
                success: function (resp) {
                    $("#doctorNum1").html(resp);
                },
                error: function () {
                },
                complete: function () {
                    jboxEndLoading();
                }
            });
        }

        function getSpeInitData(fun) {
            var geturl = "<s:url value='/jsres/manage/orgSpe'/>?" + $("#orgSpe").serialize();
            $.ajax({
                type: "get",
                url: geturl,
                cache: false,
                beforeSend: function () {
                    jboxStartLoading();
                },
                success: function (resp) {
                    $("#orgSpe1").html(resp);
                },
                error: function () {
                },
                complete: function () {
                    jboxEndLoading();
                    fun();
                }
            });
        }

        function bindFunc() {
            $("#trainTypeId").unbind("change").bind("change", searchInfo);
            //$("#sessionNumber").unbind("change").bind("change",searchInfo);
            $("[name='docType']").unbind("change").bind("change", searchInfo);

        }

        function searchInfo() {
            if ($("#trainOrg").val() == "") {
                $("#orgFlow2").val("");
            }
            var sessionNumber = $("#sessionNumber").val();
            if (sessionNumber == "") {
                jboxTip("年份不能为空！");
                return;
            }
            var trainTypeId = $("#trainTypeId").val();
            if (trainTypeId == "") {
                jboxTip("培训类别不能为空！");
                return;
            }
            var data = "";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if ($("#" + "${type.id}").attr("checked")) {
                data += "&datas=" + $("#" + "${type.id}").val();
            }
            </c:forEach>
            if (data == "") {
                jboxTip("请选择人员类型！");
                return;
            }
            jboxLoad("doctorNum1", "<s:url value='/jsres/manage/doctorNumSearch'/>?" + $("#doctorNumSearch").serialize() + data, true);
        }

        <%--function docProcessEval(form){--%>
        <%--var url="<s:url value='/jsres/hospital/docProcessEvalMain'/>?isQuery=Y";--%>
        <%--jboxLoad("content",url,true);--%>
        <%--}--%>

        function docProcessEval2(form) {
            var url = "<s:url value='/jsres/hospital/tabMainAcc'/>?isQuery=Y";
            jboxLoad("content", url, true);
        }
    </script>
    <script>
        $(document).ready(function () {
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();

            getNeedReducation("${orgFlow}", function (result) {
                if (result) {
                    jboxInfo("还有<font color='blue'>" + result + "</font>人需要维护缩减方案！");
                    $("#reducationCount").show();
                }
            });
            if ("${baseFlag}" != "0") {
                $("#baseFlag").show();
            }
            if ("${speFlag}" != "0") {
                $("#speFlag").show();
            }

        });

        function currentJboxLoadNoData(id, geturl, showLoading) {
            currentJboxLoad(id, geturl, null, showLoading);
        }

        function currentJboxLoad(id, geturl, data, showLoading) {
            if (showLoading) {
                jboxStartLoading();
            }
            jboxPost(geturl, data, function (resp) {
                if (showLoading) {
                    jboxEndLoading();
                }
                $('#' + id).html(resp);
            }, null, false);
        }

        function getNeedReducation(orgFlow, callBack) {
            var url = "<s:url value='/jsres/manage/getNeedReducation'/>";
            jboxPost(url, {orgFlow: orgFlow}, function (resp) {
                callBack(resp);
            }, null, false);
        }

        function setBodyHeight() {
            if (navigator.appName.indexOf("Microsoft Internet Explorer") > -1) {//处理ie浏览器placeholder和password的不兼容问题
                if (navigator.appVersion.indexOf("MSIE 7.0") > -1) {
                    $("#indexBody").css("height", window.innerHeight + "px");
                } else if (navigator.appVersion.indexOf("MSIE 8.0") > -1) {
                    $("#indexBody").css("height", document.documentElement.offsetHeight + "px");
                } else {
                    $("#indexBody").css("height", window.innerHeight + "px");
                }
            } else {
                $("#indexBody").css("height", window.innerHeight + "px");
            }
        }

        onresize = function () {
            setBodyHeight();
        }

        function hosMain(baseFlow) {
            var url = "<s:url value='/jsres/base/main'/>?baseFlow=" + baseFlow;
            currentJboxLoadNoData("content", url, true);
        }

        //社区培训基地维护
        function commuHospital() {
            jboxLoad("content", "<s:url value='/jsp/jsres/hospital/hos/commuHospital.jsp'/>", false);
        }

        //审核进度查询
        function auditHosProcess() {
            jboxLoad("content", "<s:url value='/jsp/jsres/hospital/hos/auditHosProcess.jsp'/>", false);
        }

        function accounts() {
            jboxLoad("content", "<s:url value='/jsres/manage/accounts'/>", true);
        }

        function doctorList() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            var sessionNumber = "${sysCfgMap['jsres_doctorCount_sessionNumber']}";
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/provinceDoctorListAcc'/>?roleFlag=" + roleFlag + "&orgFlow=${sessionScope.currUser.orgFlow}" + "&sessionNumber=" + sessionNumber, true);
        }

        function temporaryOutSearch() {
            jboxLoad("content", "<s:url value='/res/manager/temporaryOutSearchAcc'/>?roleId=org&biaoJi=Y",true);
        }

        function archiveDoctorList() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?isArchive=Y&roleFlag=" + roleFlag, true);
        }

        function doctorScoreApplyList() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorScoreApply/provinceDoctorList'/>?roleFlag=" + roleFlag, true);
        }

        /*理论成绩*/
        function doctorTheoryList() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/doctorTheoryList'/>?roleFlag=" + roleFlag, true);
        }

        function certificateManageNew() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/certificateManage/mainNew'/>?roleFlag=" + roleFlag + "&tabTag=CertificateSearch&catSpeId=DoctorTrainingSpe", true);
        }

        function certificateManageNew2() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            <%--jboxLoad("content","<s:url value='/jsres/certificateManage/mainNew'/>?roleFlag="+roleFlag+"&tabTag=SignManage&catSpeId=AssiGeneral",true);--%>
            jboxLoad("content", "<s:url value='/jsres/certificateManage/main'/>?roleFlag=" + roleFlag + "&catSpeId=AssiGeneral", true);
        }

        function certificateManage() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/certificateManage/main'/>?roleFlag=" + roleFlag, true);
        }

        /*考核资格审查*/
        function asseGraduation() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/asseGraduationForHos'/>?roleFlag=" + roleFlag, true);
        }

        function asseWaitAudit() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            <%--jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseMain'/>?roleFlag="+roleFlag+"&tabTag=WaitAudit",true);--%>
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/asseMain'/>?roleFlag=" + roleFlag + "&tabTag=FristWait&catSpeId=DoctorTrainingSpe", true);
        }

        function asseWaitAudit2() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            <%--jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseMain'/>?roleFlag="+roleFlag+"&tabTag=WaitAudit",true);--%>
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/asseMain'/>?roleFlag=" + roleFlag + "&tabTag=FristWait2&catSpeId=AssiGeneral", true);
        }

        function asseAuditList() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/asseAuditListMain'/>?roleFlag=" + roleFlag + "&tabTag=FristList&catSpeId=DoctorTrainingSpe", true);
        }

        function asseAuditList2() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/asseAuditListMain'/>?roleFlag=" + roleFlag + "&tabTag=FristList2&catSpeId=AssiGeneral", true);
        }

        <%--function searchAsseList(){--%>
        <%--    var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";--%>
        <%--    jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseMain'/>?roleFlag="+roleFlag+"&tabTag=Audit",true);--%>
        <%--}--%>

        function signUpmain(typeId) {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            <%--jboxLoad("content","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag="+roleFlag+"&typeId="+typeId,true);--%>
            jboxLoad("content", "<s:url value='/jsres/examSignUp/examsignupmain'/>?roleFlag=" + roleFlag + "&typeId=" + typeId, true);
        }

        /*技能考核管理*/
        function skillTestManage() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/skillTimeConfig/skillTestManage'/>?roleFlag=" + roleFlag, true);
        }

        function signSetUp() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/hospital/signMain'/>", true);
        }

        function scoreManage() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/scoreManage/main'/>?roleFlag=" + roleFlag + "&catSpeId=DoctorTrainingSpe", true);
        }

        function scoreManage2() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/scoreManage/main'/>?roleFlag=" + roleFlag + "&catSpeId=AssiGeneral", true);
        }

        function CertificateManagement() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorScoreApply/certificateManagement'/>?roleFlag=" + roleFlag, true);
        }

        function activityTarget() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/activityTarget/manageMain'/>?roleFlag=" + roleFlag, true);
        }

        function lectureTarget() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/res/lectureTarget/list'/>?roleFlag=" + roleFlag + "&targetType=JZ", true);
        }

        function activityQuery() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/activityQuery/main'/>?roleFlag=" + roleFlag, true);
        }

        function deptActivityStatistics() {
            jboxLoad("content", "<s:url value='/jsres/deptActivityStatistics/main'/>", true);
        }

        function teacherActivityStatistics() {
            jboxLoad("content", "<s:url value='/jsres/teacherActivityStatistics/main'/>", true);
        }

        function doctorActivityStatistics() {
            jboxLoad("content", "<s:url value='/jsres/doctorActivityStatistics/mainAcc'/>", true);
        }

        function cycle(data) {
            var docTypes = "";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if (docTypes == "") {
                docTypes += "docTypes=" + "${type.id}";
            } else {
                docTypes += "&docTypes=" + "${type.id}";
            }
            </c:forEach>
            var url = "<s:url value='/jsres/doctorRecruit/cycleAcc'/>?" + docTypes;
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function cycleResults(data) {
            var url = "<s:url value='/jsres/doctorRecruit/doctorRecruitResultAcc'/>?trainingTypeId=AssiGeneral";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function cycleErrorResults(data) {
            var url = "<s:url value='/jsres/doctorRecruit/doctorRecruitErrorResultAcc'/>";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function opinions(data) {
            var url = "<s:url value='/jsres/training/getOpinions'/>";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function guides() {
            var url = "<s:url value='/jsres/training/getGuides/manager'/>";
            jboxStartLoading();
            jboxPost(url, null, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function lecturesNew(data) {
            var url = "<s:url value='/res/manager/lectureView'/>?roleId=local";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function lectures(data) {
            var url = "<s:url value='/jsres/lecture/getLectures'/>";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function recruitAuditSearch(currentPage) {
            if (currentPage == undefined) {
                currentPage = 1;
            }
            var url = "<s:url value='/jsres/manage/local/doctor/recruitAuditSearchAcc'/>?source=hospital";
            jboxLoad("content", url, true);
        }

        function recruitSignupSearch(currentPage) {
            if (currentPage == undefined) {
                currentPage = 1;
            }
            var url = "<s:url value='/jsres/manage/province/doctor/recruitSignupSearch'/>?source=hospital";
            jboxLoad("content", url, true);
        }

        //考核申请审核
        function auditGraduate() {
            jboxLoad("content", "<s:url value='/jsp/jsres/hospital/doctor/auditGraduates.jsp'/>?source=hospital", false);
        }

        //医师帐号解锁
        function doctorAccountUnlock() {
            jboxLoad("content", "<s:url value='/jsp/jsres/hospital/doctor/doctorAccountUnlock.jsp'/>", false);
        }

        //轮转情况查询
        function schDeptInfo() {
            jboxLoad("content", "<s:url value='/jsp/jsres/hospital/doctor/schDeptInfo.jsp'/>", false);
        }

        function accountManage() {
            jboxLoad("content", "<s:url value='/jsres/doctor/accountManageAcc'/>?source=hospital", true);
        }

        function cfgManage() {
            jboxLoad("content", "<s:url value='/jsres/cfgManager/main'/>", true);
        }

        function selectMenu(menuId) {
            $("#" + menuId).find("a").click();
        }

        function changeBaseMain() {
            var url = "<s:url value='/jsres/manage/changeBaseMainAcc'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function changeSpeMain() {
            var url = "<s:url value='/jsres/manage/changeSpeMain'/>";
            jboxLoad("content", url, false);
        }

        function doctorTrendMain() {
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/doctorRecruitList'/>", false);
        }

        function shouye(page) {
            if (page == null || page == undefined || page == '' || isNaN(page)) {
                page = 1;
            }
            var url = "<s:url value='/jsres/manage/local'/>?currentPage=" + page;
            window.location.href = url;
        }

        //培训信息查询
        function trainInfo() {
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/trainInfo'/>", true);
        }

        function reductionRotationOper() {
            data = $("#searchFormReduction").serialize() || {
                degreeType: "${GlobalConstant.FLAG_Y}",
                currentPage: 1,
            };
            var url = "<s:url value='/jsres/manage/reductionRotationOperAcc'/>";
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                jboxEndLoading();
                $("#content").html(resp);
            }, null, false);
            var orgFlow = $("#orgFlow3").val();
            getNeedReducation(orgFlow, function (result) {
                if (result == 0) {
                    $("#reducationCount").hide();
                }
            });
        }

        function toPage(page) {
            if (!page) {
                $("#currentPage").val(page);
            }
            shouye(page);
        }

        function teachTrainMain1() {
            var url = "<s:url value='/jsres/phyAss/plannedReleaseMain'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        function statisticsTeachTrainMain1() {
            var url = "<s:url value='/jsres/phyAss/plannedMaintainMain'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        function searchOldTeachTrain1() {
            var url = "<s:url value='/jsres/phyAss/planUserOrgMain'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        function teachTrainMain() {
            var url = "<s:url value='/jsres/statistic/searchTeachTrainMain'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        function statisticsTeachTrainMain() {
            var url = "<s:url value='/jsres/statistic/statisticsTeachTrainMain'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        function searchOldTeachTrain() {
            var url = "<s:url value='/jsres/statistic/searchOldTeachTrain'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        //学员填写量
        function doctorDataMain() {
            var url = "<s:url value='/jsres/statistic/doctorDataMainAcc'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        //带教审核量
        function teacherAuditMain() {
            var url = "<s:url value='/jsres/statistic/teacherAuditMain'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        function statisticsAppUser() {

            //jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUser?userListScope=local'/>",true);
            jboxLoad("content", "<s:url value='/jsres/appUseInfo/mainAcc?userListScope=local'/>", false);
        }

        //培训基地评估
        function searchEvaluationInfo() {
            currentJboxLoadNoData("content", "<s:url value='/jsres/evaluation/showMain'/>", true);
        }

        function baseInfo() {
            var url = "<s:url value='/jsres/manage/baseInfo'/>";
            jboxLoad("content", url, true);
        }

        function baseInfoTeacher() {
            var url = "<s:url value='/jsres/manage/baseInfoTeacher'/>";
            jboxLoad("content", url, true);
        }

        function searchChangeSpe() {
            var url = "<s:url value='/jsres/manage/searchChangeSpe'/>?viewFlag=Y";
            jboxLoad("content", url, true);
        }

        function speMain() {
            var url = "<s:url value='/jsres/manage/speMainAcc'/>";
            jboxLoad("content", url, true);
        }

        function gradeSearch() {
            var form = $("#gradeSearchForm").serialize() || {"gradeRole": "teacher"};
            var url = "<s:url value='/jsres/manage/gradeSearch'/>?role=${GlobalConstant.USER_LIST_LOCAL}";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                jboxEndLoading();
                $("#content").html(resp);
            }, null, false);
        }

        function backTrain() {
            var url = "<s:url value='/jsres/doctor/backTrainInfoAcc?currentPage=1'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function deptRotationSearch() {
            jboxLoad("content", "<s:url value='/jsres/base/doc/schDeptAcc'/>", true);
        }

        function doctorDataAudit() {
            jboxLoad("content", "<s:url value='/jsres/doctorDataAudit/auditMain'/>", true);
        }

        function afterDataDel() {
            jboxLoad("content", "<s:url value='/jsres/afterDataManager/afterDataDel'/>", true);
        }

        function afterDataBack() {
            jboxLoad("content", "<s:url value='/jsres/afterDataManager/afterDataBack'/>", true);
        }

        function attendBack() {
            jboxLoad("content", "<s:url value='/jsres/afterDataManager/attendBack'/>", true);
        }

        function signinSet() {
            jboxLoad("content", "<s:url value='/jsres/attendanceNew/signinSet'/>", true);
        }

        function timeSet() {
            jboxLoad("content", "<s:url value='/jsres/attendanceNew/timeSet'/>", true);
        }

        // 请假管理
        function leaveSearch(form) {
            jboxLoad("content", "<s:url value='/jsres/attendanceNew/leaveVerifyListAcc/manager'/>", true);
        }

        function kqStatistics(form) {
            jboxLoad("content", "<s:url value='/jsres/attendanceNew/kqStatisticsListAcc/manager'/>", true);
        }

        function docWorkSearch() {
            jboxLoad("content", "<s:url value='/jsres/base/docWorkQueryAcc'/>", true);
        }

        function monReportSearch() {
            jboxLoad("content", "<s:url value='/jsres/base/showMonthlyReportListAcc'/>", true);
        }

        function monthlyReport() {
            window.open("<s:url value='/jsres/monthlyReportNew/main'/>", "_blank")
        }

        function teacherWorkload(form) {
            var url = "<s:url value='/jsres/manage/teacherWorkload'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                jboxEndLoading();
                $("#content").html(resp);
            }, null, false);
        }

        function statisticsTeachTrain() {
            var url = "<s:url value='/jsres/statistic/searchTeachTrain'/>?roleFlag=local";
            currentJboxLoadNoData("content", url, true);
        }

        function localAppUse() {
            jboxLoad("content", "<s:url value='/jsres/manage/localAppUseNew'/>?roleFlag=local", true);
        }

        /*2月1日待开发yuh*/
        function localJiaoxueActive() {
            <%--jboxLoad("content","<s:url value='/jsres/manage/localJiaoxueActive'/>",true);--%>
            jboxLoad("content", "<s:url value='/jsres/manage/localActivity'/>", true);
        }

        function doctorOutDept() {
            currentJboxLoadNoData("content", "<s:url value='/jsres/manage/doctorOutDept'/>", true);
        }

        function localDoctorException() {
            <%--jboxLoad("content","<s:url value='/jsres/manage/localDoctorException'/>",true);--%>
            jboxLoad("content", "<s:url value='/jsres/manage/localDoctorExceptionNew'/>", true);
        }

        function localMonthlyReport() {
            jboxLoad("content", "<s:url value='/jsres/manage/localMonthlyReport'/>", true);
        }

        //排班审核
        function schedulingAudit() {
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/schedulingAuditAcc'/>", true);
        }

        //排班查询
        function schedulingSearch() {
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/schedulingSearchAcc'/>", true);
        }

        function localDoctorDataMonthReport() {
            jboxLoad("content", "<s:url value='/jsres/manage/localDoctorDataMonthReport'/>", true);
        }

        <%--function pjjgcx(){--%>
        <%--var url = "<s:url value='/res/evaluateHospitalResult/base?role=global&gradeRole=doctor'/>";--%>
        <%--currentJboxLoadNoData("content", url, true);--%>
        <%--}--%>
        //基地人员情况统计
        function personnelStatistics() {
            var url = "<s:url value='/jsres/personnelStatistics/selectPersonnelStatistics'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function delay() {
            var url = "<s:url value='/jsres/doctor/delayAcc'/>";
            currentJboxLoadNoData("content", url, true);
        }

        //理论考试资格审查信息
        function examInfoCheck() {
            var url = "<s:url value='/jsres/doctor/searchExamInfo'/>?sessionNumber=${pdfn:getCurrYear()}";
            currentJboxLoadNoData("content", url, true);
        }

        //黑名单信息查询
        function searchBlackInfo() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            var url = "<s:url value='/jsres/blackList/blackListInfoAcc'/>?currentPage=1&roleFlag=" + roleFlag;
            jboxLoad("content", url, true);
        }

        //年度考核设置
        function examArrangMent() {
            var url = "<s:url value='/jsres/examCfg/examArrangMentAcc'/>";
            jboxLoad("content", url, true);
        }

        //年度考核成绩查询
        function examQueryScore() {
            var url = "<s:url value='/jsres/examScoreQuery/queryMainAcc'/>";
            jboxLoad("content", url, true);
        }

        //查看考勤信息
        function attendanceSearch(form) {
            var url = "<s:url value='/jsres/teacher/attendanceSearch/hospital'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        //查看考勤信息
        function attendanceTab(form) {
            var url = "<s:url value='/jsp/jsres/attendance/hospital/mainAcc.jsp'/>";
            jboxLoad("content", url, false);
        }

        function initOrg(cityId, typeId) {
            var datas = [];
            <c:forEach items="${orgs}" var="org">
            var d = {};
            if (!(cityId != null && cityId != "") && !(typeId != null && typeId != "")) {
                d.id = "${org.orgFlow}";
                d.text = "${org.orgName}";
                datas.push(d);
            } else if ((cityId != null && cityId != "") && !(typeId != null && typeId != "")) {
                if ("${org.orgCityId}" == cityId) {
                    d.id = "${org.orgFlow}";
                    d.text = "${org.orgName}";
                    datas.push(d);
                }
            } else if (!(cityId != null && cityId != "") && (typeId != null && typeId != "")) {
                if ("${org.orgLevelId}" == typeId) {
                    d.id = "${org.orgFlow}";
                    d.text = "${org.orgName}";
                    datas.push(d);
                }
            } else {
                if ("${org.orgLevelId}" == typeId && "${org.orgCityId}" == cityId) {
                    d.id = "${org.orgFlow}";
                    d.text = "${org.orgName}";
                    datas.push(d);
                }
            }
            </c:forEach>
            var itemSelectFuntion = function () {
                $("#orgFlow2").val(this.id);
                searchInfo();
            };
            $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow2", true);
        }

        //考评指标维护
        <%--function evaluationIndexMaintenance(){--%>
        <%--var url = "<s:url value='/jsres/manage/evaluationIndex'/>";--%>
        <%--jboxLoad("content", url, true);--%>
        <%--}--%>
        //入科教育管理
        function admissionEducationManage() {
            var url = "<s:url value='/jsres/kzr/admissionEduDeptList'/>";
            jboxLoad("content", url, true);
        }

        //专业基地管理
        function professionalBaseManagerList() {
            var url = "<s:url value='/res/ProfessionalBase/professionalBaseManagerList'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function zlDocQuery() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/mainAcc'/>?roleFlag=" + roleFlag, true);
        }

        function zltjOrg() {
            var roleFlag = "${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/zltjOrgLocalAcc'/>?roleFlag=" + roleFlag, true);
        }

        function zlxxfb() {
            <%--var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";--%>
            jboxLoad("content", "<s:url value='/jsres/message/messageList'/>", true);
        }

        function zljh() {
            <%--var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";--%>
            jboxLoad("content", "<s:url value='/jsres/message/plan'/>?currentPage=1", true);
        }

        function xycjgl() {//学员成绩管理
            jboxLoad("content", "<s:url value='/jsres/hospital/achievementsDetailsAcc'/>", true);
        }

        function zlxygl() {//招录学员管理
            jboxLoad("content", "<s:url value='/jsres/hospital/achievementsAcc?sortType=Total'/>", true);
        }

        function bmsh() {
            <%--var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";--%>
            jboxLoad("content", "<s:url value='/jsres/message/localSignupConfirmAcc'/>", true);
        }

        function baseAllocationDetails() {
            var url = "<s:url value='/res/resFunds/queryBaseAllocationDetails'/>?role=hospital";
            currentJboxLoadNoData("content", url, true);
        }
        //督导基地自评
        function planScoreMain(suAoth){
            jboxLoad("content","<s:url value='/jsres/supervisio/planScoreMain'/>?roleFlag=${role}&suAoth="+suAoth,true);
        }
        //督导-基地自评反馈
        function planScoreMainReport(suAoth){
            jboxLoad("content","<s:url value='/jsres/supervisio/planScoreMainReport'/>?roleFlag=${role}&suAoth="+suAoth,true);
        }
        //督导 专业基地自评汇总
        function baseExpertSuperVisio() {
            jboxLoad("content","<s:url value='/jsres/supervisio/baseExpertSuperVisio'/>?roleFlag=${role}",true);
        }

        //院级督导    评审专家管理
        function hospitalLeaderMain() {
            jboxLoad("content","<s:url value='/jsres/supervisio/hospitalLeaderMain'/>",true);
        }

        //院级督导  评审督导项目管理
        function hospitalSubjectMain() {
            jboxLoad("content","<s:url value='/jsres/supervisio/hospitalSubjectMain'/>",true);
        }
        //院级督导   统计
        function hospitalStatisticsMain() {
            jboxLoad("content","<s:url value='/jsres/supervisio/hospitalStatisticsMain'/>",true);
        }
        /**
         * 结业理论模拟考核
         */
        function graduationExam() {
            var url = "<s:url value='/jsres/graduation/queryMain'/>";
            jboxLoad("content", url, true);
        }

        $(function () {
           if(!getCookie('first')){
                $(function () {
                    isVerify = '${sessionScope.currUser.isVerify}';
                    if(isVerify != 'Y'){
                        jboxConfirm("认证手机号码，享受更便捷的登录体验，快去认证吧" , function(){
                            accounts();
                        });
                    }
                });
                setCookie('first',1,1)
            }
            getSpandAndshousuo();
            getAA();
        });


        //设置cookie
		function setCookie(cname, cvalue, exdays) {
			var d = new Date();
			d.setTime(d.getTime() + (exdays*60*60*1000));
			var expires = "expires="+d.toUTCString();
			document.cookie = cname + "=" + cvalue + ";" + expires+";path=/";
		}
		//获取cookie
		function getCookie(cname) {
			var name = cname + "=";
			var ca = document.cookie.split(';');
			for(var i=0; i<ca.length; i++) {
				var c = ca[i];
				while (c.charAt(0)==' ') c = c.substring(1);
				if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
			}
			return "";
		}

        //清除cookie
        function delCookie(name) {
            setCookie(name, "", -1);
        }

        function logout() {
            delCookie('first')
            var url ="<s:url value='/inx/jsres/logout'/>"
            window.location.href = url;
        }

        //菜单收缩展开yuh
        function getSpandAndshousuo() {
            var $all_dl = $("dl");
            for (var i = 0; i < $all_dl.length; i++) {
                $all_dl[i].id = "dl_" + i;
                $("#dl_" + i + " dt").css("cursor", "pointer");//图标手形

                $("#dl_" + i + " dt").on("click", function () {
                    var parentId = $(this)[0].parentElement.id;
                    var $all_dd = $("#" + parentId + " dd");
                    for (var j = 0; j < $all_dd.length; j++) {
                        if ($all_dd[j].hidden) {
                            $all_dd[j].hidden = false;
                        } else {
                            $all_dd[j].hidden = true;
                        }
                    }
                });
            }
        }

        function getAA() {
            var $all_dl = $("dl");
            for (var i = 0; i < $all_dl.length; i++) {
                $all_dl[i].id = "dl_" + i;

                var $all_dd = $("#dl_" + i + " dd")
                for (var j = 0; j < $all_dd.length; j++) {
                    var a_id = $all_dd[j].children[0].id = i + "_" + j + "aa";
                    $("#" + a_id).on("click", function () {
                        $("html,body").animate({scrollTop: 0}, 500);
                    });
                }
            }
        }

        /* function showHide() {
             var $all_dd =$("#ppp dd");
             for(var i =0;i< $all_dd.length;i++){
                 if($all_dd[i].hidden){
                     $all_dd[i].hidden=false;
                 }else{
                     $all_dd[i].hidden=true;
                 }
             }
         }*/
        function scroll() {
            //点击事件
            $("html,body").animate({scrollTop: 0}, 500);
        }

        //学员减免管理
        function reductionTab() {
            var url = "<s:url value='/jsres/reduction/reductionTabAcc?roleId=${GlobalConstant.USER_LIST_LOCAL}'/>";
            currentJboxLoadNoData("content", url, true);
        }

        // 结业学员统计-助理全科
        function graduactionStatisticsAcc(){
            jboxLoad("content", "<s:url value='/jsres/graduationStatistics/graduationDoctorStatisticsMain'/>?roleFlag=${GlobalConstant.USER_LIST_LOCAL}&catSpeId=AssiGeneral", true);
        }

        // 应结业学员查询
        function searchGraduactionDoctorAcc(){
            jboxLoad("content", "<s:url value='/jsres/graduationStatistics/searchGraduationDoctorList'/>?roleFlag=${GlobalConstant.USER_LIST_LOCAL}&catSpeId=AssiGeneral", true);
        }
    </script>
    <style>
    </style>
</head>

<body>
<div id="indexBody">
    <div class="bd_bg">
        <div class="yw">

            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a onclick="shouye();"><%=JsresUtil.getTitle(request, response, application)%>
                        </a>
                    </h1>
                    <div class="account">
                        <h2 class="head_right">${sessionScope.currUser.orgName }</h2>
                        <div class="head_right">
                            <span id="changeRole">
	                            <div id="roleContent" style="width: 0px;height: 0px;overflow: visible;float: right;display: none;">
		                            <div id="roleHome" style="background: #6eb0dd;width: 120px;text-align: left;padding: 0px 5px;top:21px;
		        border-radius:3px;position: relative;right: 165px;border: 1px solid #459fd0;cursor: pointer;"></div>
	                            </div>
	                            <a><c:out value="${param.actionName}" default="切换"/></a>&#12288;
                            </span>
                            <a onclick="shouye();">首页</a>&#12288;
                            <a onclick="logout()">退出</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <input type="hidden" id="orgFlow3" value='${orgFlow}'/>
                        <dl class="menu menu_first">
                            <dt class="menu_title">
                                <i class="icon_menu menu_function"></i>基地信息管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:hosMain('${sessionScope.currUser.orgFlow}');">基地信息维护</a>
                            </dd>
                            <%--<dd class="menu_item" id="evaluation"><a onclick="searchEvaluationInfo();">培训基地评估</a></dd>--%>
                            <dd class="menu_item"><a href="javascript:baseInfo();">科室师资维护</a></dd>
                            <dd class="menu_item"><a href="javascript:baseAllocationDetails();">基地拨付详情</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_360check"></i>省级督导管理
                            </dt>
                            <dd class="menu_item"><a onclick="planScoreMain('N');">基地自评</a></dd>
                            <dd class="menu_item"><a onclick="planScoreMainReport('N');">基地自评反馈</a></dd>
                            <dd class="menu_item"><a onclick="baseExpertSuperVisio();">专业基地自评汇总</a></dd>
                        </dl>
                        <c:if test="${hospitalSupervisor eq 'Y'}" >
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_360check"></i>院级督导管理
                                </dt>
                                <dd class="menu_item"><a onclick="hospitalLeaderMain();">评审专家维护</a></dd>
                                <dd class="menu_item"><a onclick="hospitalSubjectMain();">评审项目配置</a></dd>
                                <dd class="menu_item"><a onclick="hospitalStatisticsMain();">评审结果汇总</a></dd>
                            </dl>
                        </c:if>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_360check"></i>360评价考核
                            </dt>
                            <dd class="menu_item"><a href="javascript:EvaStudent();">评价学员</a></dd>
                            <dd class="menu_item"><a href="javascript:pjjgcx('global');">评价结果查询</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>人员管理
                            </dt>
                            <dd class="menu_item"><a onclick="hsxxgl();">护士信息管理</a></dd>
                            <dd class="menu_item"><a onclick="professionalBaseManagerList();">专业基地管理</a></dd>
                            <dd class="menu_item"><a href="javascript:baseInfoTeacher();">师资管理</a></dd>
<%--                            <dd class="menu_item"><a onclick="djxxgl();">带教信息管理</a></dd>--%>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>招录信息管理
                            </dt>
                            <dd class="menu_item"><a onclick="zlxxfb();">招录信息发布</a></dd>
                            <c:set value="jsres_hospital_xzzljh_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="zljh();">招录计划管理</a></dd>
                            </c:if>
                            <dd class="menu_item"><a onclick="bmsh();">招录报名审核</a></dd>
                            <dd class="menu_item"><a onclick="xycjgl();">招录成绩管理</a></dd>
                            <dd class="menu_item"><a onclick="zlxygl();">招录录取管理</a></dd>
                            <%--<dd class="menu_item" id="recruitSignupSearch"><a onclick="recruitSignupSearch();">学员报到审核</a></dd>--%>
                            <%--<dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">学员信息审核</a></dd>--%>
                            <dd class="menu_item" id="recruitAuditSearch"><a onclick="recruitAuditSearch();">学员报到审核</a>
                            </dd>
                            <dd class="menu_item"><a onclick="zltjOrg();">招录学员统计</a></dd>
                            <dd class="menu_item"><a onclick="zlDocQuery();">招录学员查询</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_doctor"></i>异动学员管理
                            </dt>
                            <dd class="menu_item"><a onclick="reductionTab();">学员减免管理</a></dd>
                            <dd class="menu_item"><a onclick="changeBaseMain();">基地变更审核
                                <%--<img  id="baseFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
                            </a>
                            </dd>
                            <dd class="menu_item"><a onclick="speMain();">专业变更管理
                                <%--<img  id="speFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
                            </a></dd>
                            <dd class="menu_item"><a onclick="delay();">延期学员查询</a></dd>
                            <dd class="menu_item"><a
                                    <%--href="javascript:backTrain();"--%>onclick="backTrain()">退培学员查询</a></dd>
                            <dd class="menu_item"><a
                                    <%--href="javascript:searchBlackInfo();"--%>onclick="searchBlackInfo()">黑名单查询</a>
                            </dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_into"></i>入科信息管理
                            </dt>
                            <dd class="menu_item"><a
                                    <%--href="javascript:admissionEducationManage();"--%>onclick="admissionEducationManage()">入科教育管理</a>
                            </dd>
                           <%-- <dd class="menu_item" id="reductionRotationOper"><a onclick="reductionRotationOper();">方案减免维护--%>
                                <%--<img  id="reducationCount" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
                            </a>
                            </dd>
                            <c:set value="jsres_hospital_zpzngl_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="guides()">住培指南管理</a></dd>
                            </c:if>
                        </dl>
                        <dl>
                            <dt class="menu_title">
                                <i class="icon_menu menu_management_train_query"></i>培训过程查询
                            </dt>
                            <dd class="menu_item" id="doctorList"><a onclick="doctorList();">学员信息查询</a></dd>
                            <dd class="menu_item" id="temporaryOutList"><a onclick="temporaryOutSearch();">临时出科查询</a></dd>
                            <c:set value="jsres_hospital_kslzcx_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="deptRotationSearch();">科室轮转查询</a></dd>
                            </c:if>
                            <c:set value="jsres_hospital_xylzcx_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="cycle(null);">学员轮转查询</a></dd>
                            </c:if>
                            <c:set value="jsres_hospital_zpyjfk_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="opinions(null)">住培意见反馈</a></dd>
                            </c:if>
                            <!-- <c:set value="jsres_hospital_pxsjsh_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="doctorDataAudit();">培训数据审核</a></dd>
                            </c:if> -->
                            <dd class="menu_item"><a onclick="afterDataDel();">出科数据删除</a></dd>
                            <dd class="menu_item"><a onclick="afterDataBack();">出科数据恢复</a></dd>

                        </dl>
                        <dl>
                            <dt class="menu_title">
                                <i class="icon_menu menu_management_train_query"></i>排班管理
                            </dt>
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_org" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">
                                <dd class="menu_item"><a onclick="schedulingAudit();">排班安排</a></dd>
                            </c:if>
                            <dd class="menu_item"><a onclick="schedulingSearch();">选科管理</a></dd>
                        </dl>
                        <c:set value="jsres_hospital_xykqcx_${sessionScope.currUser.orgFlow }" var="key"/>
                        <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                            <dl>
                                <dt class="menu_title">
                                    <i class="icon_menu menu_process_management"></i>学员考勤管理
                                </dt>
                                    <dd class="menu_item"><a onclick="attendanceTab()">学员考勤查询</a></dd>
                                    <dd class="menu_item"><a onclick="attendBack();">退回日常考勤</a></dd>
                                    <dd class="menu_item"><a onclick="signinSet();">签到地点设置</a></dd>
                                    <dd class="menu_item"><a onclick="timeSet();">请假参数配置</a></dd>
                                    <dd class="menu_item"><a onclick="leaveSearch();">学员请假管理</a></dd>
                                    <dd class="menu_item"><a onclick="kqStatistics();">学员请假统计</a></dd>
                            </dl>
                        </c:if>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_teach_activity"></i>教学活动管理
                            </dt>
                            <c:set value="jsres_hospital_jzxxgl_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="lectures(null)">讲座信息管理</a></dd>
                            </c:if>
                            <c:set value="jsres_hospital_activity_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a
                                            <%--href="javascript:activityQuery();"--%>onclick="activityQuery()">教学活动查询</a>
                                </dd>
                                <dd class="menu_item"><a
                                            <%--href="javascript:deptActivityStatistics();"--%>onclick="deptActivityStatistics()">科室活动统计</a>
                                </dd>
                                <dd class="menu_item"><a
                                            <%--href="javascript:teacherActivityStatistics();"--%>onclick="teacherActivityStatistics()">师资活动统计</a>
                                </dd>
                                <dd class="menu_item"><a
                                            <%--href="javascript:doctorActivityStatistics();"--%>onclick="doctorActivityStatistics()">学员活动统计</a>
                                </dd>
                            </c:if>
                            <dd class="menu_item"><a onclick="activityTarget();">评价指标管理</a></dd>
                        </dl>
                        <dl>
                            <dt class="menu_title">
                                <i class="icon_menu menu_outto"></i>出科信息查询
                            </dt>
                            <dd class="menu_item" id="docProcessEval"><a onclick="docProcessEval2();">考评查询/指标维护</a></dd>
                            <c:set value="jsres_hospital_xyckcjcx_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="cycleResults(null);">学员出科查询</a></dd>
                                <dd class="menu_item"><a onclick="cycleErrorResults(null);">学员出科异常查询</a></dd>
                            </c:if>
                            <c:set value="jsres_hospital_sxpjcx_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item" id="gradeSearch"><a onclick="gradeSearch();">出科评价查询</a></dd>
                            </c:if>

                            <c:set value="jsres_hospital_xygzlcx_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="docWorkSearch();">学员工作量查询</a></dd>
                            </c:if>

                            <c:set value="jsres_hospital_djgzlcx_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="teacherWorkload();">带教工作量查询</a></dd>
                            </c:if>


                        </dl>
                        <c:set value="jsres_hospital_ndkhsz_${sessionScope.currUser.orgFlow }" var="key1"/>
                        <c:set value="jsres_hospital_ndkhcx_${sessionScope.currUser.orgFlow }" var="key2"/>
                        <c:if test="${(pdfn:jsresPowerCfgMap(key1) eq GlobalConstant.RECORD_STATUS_Y)or(pdfn:jsresPowerCfgMap(key2) eq GlobalConstant.RECORD_STATUS_Y)}">
                            <dl class="menu">
                                <dt class="menu_title">
                                    <i class="icon_menu menu_year_check"></i>年度考核管理
                                </dt>
                                <c:if test="${pdfn:jsresPowerCfgMap(key1) eq GlobalConstant.RECORD_STATUS_Y}">
                                    <dd class="menu_item"><a
                                                <%--href="javascript:examArrangMent();"--%>onclick="examArrangMent()">理论考核设置</a>
                                    </dd>
                                </c:if>
                                <c:if test="${pdfn:jsresPowerCfgMap(key2) eq GlobalConstant.RECORD_STATUS_Y}">
                                    <dd class="menu_item" id="doctorScoreApplyList"><a onclick="examQueryScore();">理论成绩查询</a>
                                    </dd>
                                </c:if>
                            </dl>
                        </c:if>
                      <%--  <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management_complete"></i>住院医师结业信息管理
                            </dt>
                            &lt;%&ndash;<dd class="menu_item"><a &lt;%&ndash;href="javascript:asseGraduation();"&ndash;%&gt;onclick="asseGraduation()">考核资格审查</a></dd>&ndash;%&gt;
                            <dd class="menu_item"><a onclick="asseWaitAudit();">结业考核资格审核</a></dd>
                            <dd class="menu_item"><a onclick="asseAuditList();">结业考核资格查询</a></dd>
                            &lt;%&ndash;			   <dd class="menu_item"><a onclick="searchAsseList();">考核资格查询</a></dd>&ndash;%&gt;
                            <dd class="menu_item"><a onclick="scoreManage()">结业成绩管理</a></dd>
                            &lt;%&ndash;<dd class="menu_item"><a &lt;%&ndash;href="javascript:signUpmain();"&ndash;%&gt;onclick="signUpmain()">补考审核</a></dd>&ndash;%&gt;
                            &lt;%&ndash;<dd class="menu_item"><a onclick="skillTestManage();">技能考核管理</a></dd>&ndash;%&gt;
                            <dd class="menu_item"><a onclick="certificateManageNew();">结业证书管理</a></dd>
                            &lt;%&ndash;<dd class="menu_item"><a onclick="signSetUp();">签名图片维护</a></dd>&ndash;%&gt;
                            &lt;%&ndash;<dd class="menu_item"><a onclick="certificateManage();">证书管理</a></dd>&ndash;%&gt;
                            &lt;%&ndash;<c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="graduationExam()">结业理论模拟考核</a></dd>
                            </c:if>&ndash;%&gt;
                            <dd class="menu_item"><a onclick="graduationExam()">结业理论模拟考核</a></dd>
                        </dl>--%>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management_complete"></i>助理全科结业信息管理
                            </dt>
                            <dd class="menu_item"><a onclick="graduactionStatisticsAcc();">结业学员统计</a></dd>
                            <dd class="menu_item"><a onclick="searchGraduactionDoctorAcc();">应结业学员查询</a></dd>
                            <dd class="menu_item"><a onclick="asseWaitAudit2();">结业考核资格审核</a></dd>
                            <dd class="menu_item"><a onclick="asseAuditList2();">结业考核资格查询</a></dd>
                            <dd class="menu_item"><a onclick="scoreManage2()">结业成绩管理</a></dd>
                            <dd class="menu_item"><a onclick="certificateManageNew2();">结业证书管理</a></dd>
                            <%--<dd class="menu_item"><a onclick="graduationExam()">结业理论模拟考核</a></dd>--%>
                        </dl>
                        <%--<dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_statistics"></i>结业管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:asseGraduation();">考核资格审查</a></dd>
                            <dd class="menu_item"><a href="javascript:signUpmain('Theory');">理论补考查询</a></dd>
                            <dd class="menu_item"><a href="javascript:signUpmain('Skill');">技能补考查询</a></dd>
                            <dd class="menu_item" id="doctorScoreApplyList"><a onclick="doctorScoreApplyList();">证书申请管理</a></dd>
                            <dd class="menu_item"><a href="javascript:doctorTheoryList();">结业成绩查询</a></dd>
                            <dd class="menu_item"><a onclick="CertificateManage();">证书信息查询</a></dd>
                            <dd class="menu_item"><a href="javascript:CertificateManagement();">结业证书查询</a></dd>
                            <c:set value="jsres_hospital_jyllmnkh_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="graduationExam()">结业理论模拟考核</a></dd>
                            </c:if>
                        </dl>--%>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>师资管理
                            </dt>
                            <dd class="menu_item"><a onclick="teachTrainMain1()">师资名单录入</a></dd>
                            <dd class="menu_item"><a onclick="statisticsTeachTrainMain1()">师资信息维护</a></dd>
                            <dd class="menu_item"><a onclick="searchOldTeachTrain1()">师资培训证书</a></dd>
                            <dd class="menu_item"><a onclick="teachTrainMain()">师资信息管理</a></dd>
                            <dd class="menu_item"><a onclick="statisticsTeachTrainMain()">师资培训统计</a></dd>
                            <dd class="menu_item"><a onclick="searchOldTeachTrain()">师资历史数据</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>绩效管理
                            </dt>
                            <dd class="menu_item"><a onclick="doctorDataMain()">学员填写量统计</a></dd>
                            <dd class="menu_item"><a onclick="teacherAuditMain()">带教审核量统计</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_statis"></i>统计信息查询
                            </dt>
                            <c:set value="jsres_hospital_appsyqktj_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a href="javascript:statisticsAppUser();"
                                                         onclick="statisticsAppUser()">APP使用情况统计</a></dd>
                            </c:if>
                            <c:set value="jsres_hospital_ybcx_${sessionScope.currUser.orgFlow }" var="key"/>
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq GlobalConstant.RECORD_STATUS_Y}">
                                <dd class="menu_item"><a onclick="monReportSearch();">月报查询</a></dd>
<%--                                <dd class="menu_item"><a onclick="monthlyReport();">培训月报查询</a></dd>--%>
                            </c:if>
<%--                            <dd class="menu_item"><a href="javascript:statisticsTeachTrain();"--%>
<%--                                                     onclick="statisticsTeachTrain()">师资培训统计</a></dd>--%>

                            <%-- &lt;%&ndash;报表，16版本暂时先注掉&ndash;%&gt;--%>
                            <dd class="menu_item"><a onclick="localAppUse()">app使用情况月报</a></dd>
<%--                            <dd class="menu_item"><a onclick="localDoctorDataMonthReport()">学员轮转数据月报</a></dd>--%>
<%--                            <dd class="menu_item"><a onclick="localJiaoxueActive()">教学活动开展统计</a></dd>--%>
                        <%--    <dd class="menu_item"><a href="javascript:personnelStatistics();"
                                                     onclick="personnelStatistics()">人员情况统计</a>--%>
                            </dd>
<%--                            <dd class="menu_item"><a href="javascript:doctorOutDept();">学员出科情况统计</a></dd>--%>
<%--                            <dd class="menu_item"><a onclick="localDoctorException()">学员轮转异常统计</a></dd>--%>
<%--                            <dd class="menu_item"><a onclick="localMonthlyReport()">月度报表</a></dd>--%>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_system"></i>系统设置
                            </dt>
                            <dd class="menu_item"><a
                                    <%--href="javascript:cfgManage();"--%>onclick="cfgManage()">参数配置</a></dd>
                            <dd class="menu_item"><a
                                    <%--href="javascript:accountManage();"--%>onclick="accountManage()">学员帐号管理</a></dd>
                            <dd class="menu_item"><a <%--href="javascript:accounts();"--%>onclick="accounts()">安全中心</a>
                            </dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                        <div class="content_main">
                            <div class="index_show">
                                <div class="index_tap_global top_left">
                                    <ul class="inner">
                                        <li class="index_item_global index_blue">
                                            <a href="javascript:selectMenu('recruitAuditSearch');">
                  <span class="tap_inner">
                    <i class="wait"></i>
                    <em class="number">${waitCount}条</em>
                    <strong class="title">培训信息待审核</strong>
                  </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="index_tap_global top_right">
                                    <ul class="inner">
                                        <li class="index_item" style="width:50%; float:left;">
                                            <a href="javascript:selectMenu('auditDoctors');">
					  <span class="tap_inner" style="cursor: pointer" onclick="doctorList()">
						<i class="crowd"></i>
						<c:set var="key" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>
						<em class="number"><span
                                id="trainDoctor">${doctorCountExtMap[key]["DOCTORCOUNT"]}人</span></em>
						<strong class="title">${sysCfgMap['jsres_doctorCount_sessionNumber']}
							<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">届</c:if>培训医师数</strong>
					  </span>
                                            </a>
                                        </li>
                                        <li class="index_item" style="width:50%; float:left;">
                                            <a href="javascript:void(0);">
                  	<span class="tap_inner tab_second">
						<c:set var="yearpl" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>
					  	<c:forEach items="${resDocTypeEnumList}" var="type">
                            <c:set value="0" var="keyll"></c:set>
                            <c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">
                                <c:set value="${doctorCountExtMap[yearpl][type.id]}" var="keyll"></c:set>
                            </c:if>
                            <lable class="title">${type.name}:${keyll}人&nbsp;<br/></lable>
                        </c:forEach>
					</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="main_bd">
                                <ul>
                                    <li class="score_frame">
                                        <h1 style="background:#e7f5fc;">人员信息概况</h1>
                                        <c:set var="currYear" value="${pdfn:getCurrYear()}"></c:set>
                                        <div class="grid">
                                            <c:forEach step="1" begin="${currYear-2}" end="${currYear}" varStatus="num"
                                                       var="doctor">
                                                <table style="
                                                <c:if test='${num.count<3}'>float: left;margin-left:1%;</c:if> <c:if
                                                        test='${num.count==3}'>margin-left:67%;</c:if>margin-top: 10px;margin-bottom: 10px;border: 1px solid #ccc;"
                                                       cellpadding="0" cellspacing="0" width="32%">
                                                    <tbody>
                                                    <c:set var="year" value="${currYear-num.count+1}"></c:set>
                                                    <c:set var="key" value="${year}pl"></c:set>
                                                    <tr>
                                                        <th align="center" style="background-color: #e7e7eb;">${year}届&#12288;${doctorCountExtMap[key]["DOCTORCOUNT"]}人</th>
                                                    </tr>
                                                    <tr>
                                                        <td align="left">
                                                            <p style="width:100%">
                                                                <span style="float: left;font-weight: 900;width:30%">培训类别：</span>
                                                                <span style="float: left;width:35%;text-align:center;">住院医师<br/>${doctorCountExtMap[key]["DoctorTrainingSpe"]}人</span>
                                                                <span style="float: left;width:35%;text-align:center;">助理全科<br/>${doctorCountExtMap[key]["AssiGeneral"]}人</span>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left">
                                                            <p style="width:100%">
                                                                <span style="float: left;font-weight: 900;width:30%">人员类型：</span>
                                                                <span style="float: left;width:35%;text-align:center;">本单位人<br/>${doctorCountExtMap[key]["Company"]}人</span>
                                                                <span style="float: left;width:35%;text-align:center;">委培单位人<br/>${doctorCountExtMap[key]["CompanyEntrust"]}人</span>
                                                            </p>
                                                            <p style="width:100%">
                                                                <span style="float: left;font-weight: 900;width:30%">&#12288;</span>
                                                                <span style="float: left;width:35%;text-align:center;">社会人<br/>${doctorCountExtMap[key]["Social"]}人</span>
                                                                <span style="float: left;width:35%;text-align:center;">在校专硕<br/>${doctorCountExtMap[key]["Graduate"]}人</span>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </c:forEach>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="main_bd">
                                <ul>
                                    <li class="score_frame">
                                        <h1 style="background:#e7f5fc;">当前住培情况</h1>
                                        <table class="in_table"
                                               style="float: left;width: 48%; margin-top: 10px;margin: 10px;margin-bottom: 10px;border: 1px solid #ccc;"
                                               cellpadding="0" cellspacing="0" width="100%">
                                            <tbody>
                                            <tr>
                                                <td>
                                                    <div id="docChartForIndex" style="height: 200px;"></div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="grid" style="overflow: auto;height: 200px;width: auto;margin: 10px">
                                            <table class="in_table" style="border: 1px solid #ccc;" cellpadding="0"
                                                   cellspacing="0" width="100%">
                                                <colgroup>
                                                    <col width="25%"/>
                                                    <col width="25%"/>
                                                    <col width="25%"/>
                                                    <col width="25%"/>
                                                </colgroup>
                                                <tr style="height: 60px;">
                                                    <td colspan="4">
                                                        <div style="float: left;margin-left:80px;height: 10px;width: 10px;background: rgba(255,120,50,1);"></div>
                                                        <div style="float: left;line-height: 10px;"> ：在培人数</div>
                                                        <div style="float: left;margin-left:20px;height: 10px;width: 10px;background: rgba(100,200,255,1);"></div>
                                                        <div style="float: left;line-height: 10px;"> ：已考核待结业人数</div>
                                                        <div style="float: left;margin-left:20px;height: 10px;width: 10px;background: rgba(255,120,255,1);"></div>
                                                        <div style="float: left;line-height: 10px;"> ：结业人数</div>
                                                    </td>
                                                </tr>
                                                <c:forEach items="${currDocDetailMaps}" var="currDocDetail">
                                                    <tr>
                                                        <td style="text-align: center">${currDocDetail['SESSIONNUMBER']}届</td>
                                                        <td style="text-align: center">${currDocDetail['20'] ==null? 0:currDocDetail['20']}人</td>
                                                        <td style="text-align: center">${currDocDetail['22'] ==null? 0:currDocDetail['22']}人</td>
                                                        <td style="text-align: center">${currDocDetail['21'] ==null? 0:currDocDetail['21']}人</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                        <%--<div style="background-color: #e7f5fc;height: 50px;margin:10px ; text-align: center;font-size: large">--%>
                                        <%--<table class="in_table" style="background-color:#e7f5fc;" cellpadding="0" cellspacing="0"  width="100%">--%>
                                        <%--<tr style="height: 12px"></tr>--%>
                                        <%--<tr>--%>
                                        <%--<td style="text-align: center">填写数据总量：${sumCountAudit['SUMCOUNT']}条</td>--%>
                                        <%--<td style="text-align: center">带教审核总量：${sumCountAudit['SUMCOUNTRES']}条</td>--%>
                                        <%--<td style="text-align: center">系统总访问量：${count}次</td>--%>
                                        <%--</tr>--%>
                                        <%--</table>--%>
                                        <%--</div>--%>
                                    </li>
                                </ul>
                            </div>
                            <div class="main_bd" style="height:auto;">
                                <ul>
                                    <li class="score_frame">
                                        <h1 style="background:#e7f5fc;">医师信息概况</h1>
                                        <div class="" style="margin-top: 15px;">
                                            <form id="doctorNumSearch">
                                                <table cellspacing="8px">
                                                    <tr>
                                                        <td
                                                                <c:if test="${countryOrg != 'countryOrg'}">hidden="hidden"</c:if>>
                                                            &#12288;培训基地：
                                                        </td>
                                                        <td
                                                                <c:if test="${countryOrg != 'countryOrg'}">hidden="hidden"</c:if>>
                                                            <div style="width:150px;">
                                                                <input id="trainOrg" class="input" type="text"
                                                                       style="margin-left: 0px;width: 150px"
                                                                       autocomplete="off"/>
                                                                <input id="orgFlow2" name="orgFlow" class="input"
                                                                       type="text" hidden
                                                                       style="margin-left: 0px;width: 150px"/>
                                                            </div>
                                                        </td>
                                                        <td>&#12288;培训类别：</td>
                                                        <td>
                                                            <select onchange="searchInfo();" name="trainTypeId"
                                                                    id="trainTypeId" class="select"
                                                                    style="width:150px;">
                                                                <option value="AssiGeneral">助理全科</option>
                                                               <%-- <c:forEach items="${trainCategoryEnumList}"
                                                                           var="trainCategory">
                                                                    <option value="${trainCategory.id}"
                                                                            <c:if test="${trainCategoryEnumDoctorTrainingSpe.id eq trainCategory.id}">selected</c:if>
                                                                            <c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>
                                                                    >${trainCategory.name}</option>
                                                                </c:forEach>--%>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&#12288;年&#12288;&#12288;级：</td>
                                                        <td>
                                                            <input type="text" id="sessionNumber" name="sessionNumber"
                                                                   onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchInfo();}})"
                                                                   <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"
                                                                   </c:if>value="${param.sessionNumber}" class="input"
                                                                   readonly="readonly"
                                                                   style="width: 150px;margin-left: 0px"/>
                                                        </td>
                                                        <td>&#12288;人员类型：</td>
                                                        <td colspan="2">
                                                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                                                <label><input onchange="searchInfo();" name="docType"
                                                                              type="checkbox" id="${type.id}"
                                                                              name="${type.id}"
                                                                              value="${type.id}"/>${type.name}&nbsp;</label>
                                                            </c:forEach>
                                                        </td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <%--<td><input class="btn_green" type="button" value="查询" onclick="searchInfo();"/></td>--%>
                                                    </tr>
                                                </table>
                                            </form>
                                        </div>
                                        <div class="index_table" id="doctorNum1"
                                             style="height: 550px;width:100%;margin-top: 50px;">

                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <c:set value="jswjw_${currUser.orgFlow}_P0015" var="orgFlow"/>
                            <c:if test="${sysCfgMap[orgFlow] eq GlobalConstant.RECORD_STATUS_Y}">
                                <div class="index_form" style="margin-bottom: 10px;">
                                    <script>
                                        var okList;
                                        var currFlag = false;
                                        var viewLength = 5;
                                        var likeSearchColumn = 3;

                                        $(function () {
                                            okList = $('#willOutTable .willOutData');
                                            toOperData();
                                        });

                                        function toOperData() {
                                            var docCount = okList.length || 0;
                                            if (docCount > viewLength) {
                                                okList.filter(':gt(' + (viewLength - 1) + ')').toggle(currFlag);
                                                $('#s').toggle(!currFlag);
                                                $('#h').toggle(currFlag);
                                                $('#docCount').text(docCount - viewLength);
                                            } else {
                                                $('#s,#h').hide();
                                            }
                                            $('#noData').toggle(docCount == 0);
                                        }

                                        function more(flag) {
                                            currFlag = flag;
                                            okList.filter(':gt(' + (viewLength - 1) + ')').toggle(currFlag);
                                            $('#s,#h').toggle();
                                        }

                                        function searchMethod(theAttr) {
                                            okList = $('#willOutTable .willOutData');
                                            theAttr = $.trim(theAttr);
                                            if (theAttr != '') {
                                                okList.hide();
                                                okList = okList.filter(':has(td:lt(' + likeSearchColumn + '):contains("' + theAttr + '"))').show();
                                                toOperData();
                                            } else {
                                                okList.show();
                                                toOperData();
                                            }
                                        }
                                    </script>
                                    <table id="willOutTable" border="0" cellpadding="0" cellspacing="0" class="grid">
                                        <colgroup>
                                            <col width="20%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>
                                            <col width="15%"/>
                                            <col width="20%"/>
                                            <col width="15%"/>
                                            <col width="10%"/>
                                        </colgroup>
                                        <tr>
                                            <th colspan="7" style="text-align: left;padding-left: 10px;">
                                                当月即将出科学员查询
                                                <div style="float: right;">
                                                    搜索：
                                                    <input type="text" class="input" onkeyup="searchMethod(this.value);"
                                                           placeholder="轮转科室/姓名/届别"/>
                                                </div>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th>轮转科室</th>
                                            <th>姓名</th>
                                            <th>届别</th>
                                            <th>培训专业</th>
                                            <th>轮转时间</th>
                                            <th>带教老师</th>
                                            <th>出科状态</th>
                                        </tr>
                                        <c:forEach items="${trainingList}" var="training">
                                            <tr class="willOutData">
                                                <c:set var="status"
                                                       value="${training.doctorFlow}${training.schDeptFlow}"></c:set>
                                                <td>${training.schDeptName}</td>
                                                <td>${training.doctorName}</td>
                                                <td>${training.sessionNumber}</td>
                                                <td>${training.trainingSpeName}</td>
                                                <td>${training.schStartDate} 至 ${training.schEndDate}</td>
                                                <td>${training.teacherUserName}</td>
                                                <td>${stateMap[status]}</td>
                                            </tr>
                                        </c:forEach>

                                        <tr>
                                            <td id="noData" colspan="7" style="text-align: center;<c:if
                                                    test="${!empty trainingList}">display: none;</c:if>">无记录!
                                            </td>
                                        </tr>

                                        <c:if test="${not empty trainingList}">
                                            <tr id="s" style="display: none;">
                                                <td colspan="7">
                                                    <a onclick="more(true);">还有<font id="docCount"></font>个学员，点击查看</a>
                                                </td>
                                            </tr>
                                            <tr id="h" style="display: none;">
                                                <td colspan="7"><a onclick="more(false);">收起</a></td>
                                            </tr>
                                        </c:if>
                                    </table>
                                </div>
                            </c:if>

                            <div class="index_form" style="margin-bottom: 10px;">
                                <h3>通知公告</h3>
                                <ul class="form_main">
                                    <c:forEach items="${infos}" var="msg">
                                        <li>
                                            <strong><a
                                                    href="<s:url value='/inx/jsres/noticeView'/>?infoFlow=${msg.infoFlow}"
                                                    target="_blank">${msg.infoTitle}</a>
                                                <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
                                                    <i class="new1"></i>
                                                </c:if>
                                            </strong>
                                            <span>${pdfn:transDate(msg.createTime)}</span>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${empty infos}">
                                        <li>
                                            <strong>无记录!</strong>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                            <div class="page">
           <span>
             <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
             <c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
	  	     <pd:pagination-jsres toPage="toPage"/>
           </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
            <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
        </c:if>
        <div class="foot">
            <div class="foot_inner">
                主管单位：${sysCfgMap['the_competent_unit']} | 技术支持：南京品德网络信息技术有限公司
            </div>
        </div>

    </div>
</div>
</body>
</html>
