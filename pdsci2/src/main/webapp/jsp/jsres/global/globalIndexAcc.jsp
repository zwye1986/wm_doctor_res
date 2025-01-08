<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>

<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="consult" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
 <%--   <script type="text/javascript"
            src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
  <%--  <script>
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
    </script>--%>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.min.js'/>"></script>
    <script>
        $(document).ready(function () {
            $(".menu_item a").click(function () {
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
            if ("${speFlag}" != "0") {
                $("#speFlag").show();
            }
            if ("${backFlag}" == "1") {
                $("#backFlag").show();
            }
            if ("${baseFlag}" != "0") {
                $("#baseFlag").show();
            }
            getSpandAndshousuo();
        });

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

        function setBodyHeight() {
            initOrg(null, null);
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

        function accounts() {
            jboxLoad("content", "<s:url value='/jsres/manage/accounts'/>", true);
        }

        function parameterConfig() {
            jboxLoad("content", "<s:url value='/jsres/sysCfg/parameterConfigMain'/>", true);
        }

        function parameterConfigLog() {
            jboxLoad("content", "<s:url value='/jsres/sysCfg/parameterConfigLog'/>", true);
        }

        function searchBaseInfo(auditFlag) {
            jboxLoad("content", "<s:url value='/jsres/base/findBaseInfo'/>?role=${GlobalConstant.USER_LIST_PROVINCE}&auditFlag=" + auditFlag, true);
        }

        function auditHospitals() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/hospital/auditHospitals.jsp'/>", true);
        }

        function editHosSpecials() {
            jboxLoad("content", "<s:url value='/jsp/jsres/system/hospital/editHosSpecials.jsp'/>", true);
        }

        function doctorList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/provinceDoctorListNewAcc'/>?roleFlag=" + roleFlag, true);
        }

        function zlDocCheck() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/globalCheckMainAcc'/>?roleFlag=" + roleFlag, true);
        }

        function zlDocQuery() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/mainGlobalAcc'/>?roleFlag=" + roleFlag, true);
        }

        function zltjCity() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/zltjCityMainAcc'/>?tabId=doctorType&roleFlag=" + roleFlag, true);
        }

        function zltjOrg() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/zltjOrgMainAcc'/>?tabId=doctorType&roleFlag=" + roleFlag, true);
        }

        function archiveDoctorList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?isArchive=Y&roleFlag=" + roleFlag, true);
        }

        function docWorkQuery() {
            jboxLoad("content", "<s:url value='/jsres/docWork/docWorkQueryAcc'/>", true);
        }

        function phyAssPlanMain() {
            jboxLoad("content","<s:url value='/jsres/phyAss/planScoreMain'/>?roleFlag=${role}",true);
        }

        function phyAssDoctorListMain() {
            jboxLoad("content", "<s:url value='/jsres/phyAss/phyAssDoctorListMain'/>", true);
        }

        function djxxgl() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/res/nurse/teachingList'/>?roleFlag=global", true);
        }

        /*理论成绩*/
        function doctorTheoryList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/doctorTheoryList'/>?roleFlag=" + roleFlag, true);
        }

        /*技能成绩*/
        function doctorSkillList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/doctorSkillList'/>?roleFlag=" + roleFlag, true);
        }

        /*结业合格人员*/
        function theoryPassedList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/theoryPassedList'/>?roleFlag=" + roleFlag, true);
        }

        /*公共科目成绩*/
        function doctorPublicList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorTheoryScore/doctorPublicList'/>?roleFlag=" + roleFlag, true);
        }

        function testConfig() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/testConfig/testMain'/>?roleFlag=" + roleFlag + "&tabTag=GraduationTest", true);
        }

        function skillTimeConfig() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/skillTimeConfig/main'/>?roleFlag=" + roleFlag, true);
        }

        function doctorScoreApplyList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorScoreApply/provinceDoctorList'/>?roleFlag=" + roleFlag, true);
        }

        function CertificateManagement() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorScoreApply/certificateManagement'/>?roleFlag=" + roleFlag, true);
        }

        function signManage() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/hospital/signSearch'/>?auditStatusId=Auditing&roleFlag=" + roleFlag, true);
        }

        function certificateManage() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/certificateManage/certificateMain'/>?roleFlag=" + roleFlag + "&tabTag=CountryCertificate&catSpeId=DoctorTrainingSpe", true);
        }

        function examStatistics() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/graduation/examStatistics'/>?roleFlag=" + roleFlag + "&tabTag=Org&catSpeId=DoctorTrainingSpe", true);
        }

        function examStatistics2() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/graduation/examStatistics'/>?roleFlag=" + roleFlag + "&tabTag=Org&catSpeId=AssiGeneral", true);
        }

        function certificateManage2() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/certificateManage/certificateMain'/>?roleFlag=" + roleFlag + "&tabTag=CountryCertificate&catSpeId=AssiGeneral", true);
        }

        function doctorFileManage() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctor/doctorFileMain'/>?roleFlag=" + roleFlag, true);
        }

        function certificateCreate() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/certificateManage/createMain'/>?roleFlag=" + roleFlag, true);
        }

        function certificateSearch() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/certificateManage/main'/>?roleFlag=" + roleFlag, true);
        }

        function CompletionReport() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/completionReport/main'/>?roleFlag=" + roleFlag, true);
        }

        function asseAudit() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/asseGlobal/main'/>?roleFlag=" + roleFlag + "&tabTag=WaitAudit", true);
        }

        function asseWaitAudit() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/asseGlobal/main'/>?roleFlag=" + roleFlag + "&tabTag=FristWait&catSpeId=DoctorTrainingSpe", true);
        }

        function asseWaitAudit2() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/asseGlobal/main'/>?roleFlag=" + roleFlag + "&tabTag=FristWait2&catSpeId=AssiGeneral", true);
        }

        function asseAuditList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/asseGlobal/asseAuditListMain'/>?roleFlag=" + roleFlag + "&tabTag=FristList&catSpeId=DoctorTrainingSpe", true);
        }

        function asseAuditList2() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/asseGlobal/asseAuditListMain'/>?roleFlag=" + roleFlag + "&tabTag=FristList2&catSpeId=AssiGeneral", true);
        }

        <%--function searchAsseList(){--%>
        <%--    var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";--%>
        <%--    jboxLoad("content","<s:url value='/jsres/asseGlobal/main'/>?roleFlag="+roleFlag+"&tabTag=Audit",true);--%>
        <%--}--%>

        function auditDoctors() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/doctor/auditDoctors.jsp'/>", true);
        }

        function timeoutRegisterHoses() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/hospital/timeoutRegisterHoses.jsp'/>", false);
        }

        /* 培训基地评估 */
        function baseEvaluation() {
            currentJboxLoadNoData("content", "<s:url value='/jsp/jsres/global/hospital/baseEvaluationTitle.jsp'/>", true);
        }

        /* 结业考核管理 */

        function gradeInput() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/graduate/gradeInput.jsp'/>?source=province", false);
        }

        function graduateManage() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/graduate/graduateManage.jsp'/>", false);
        }

        function graduateUserList() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/graduate/graduateUserList.jsp'/>", false);
        }

        function auditGraduate() {
            jboxLoad("content", "<s:url value='/jsp/jsres/hospital/doctor/auditGraduates.jsp'/>?source=province", false);
        }

        function theoResultsImport() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/graduate/theoResultsImport.jsp'/>", false);
        }

        function skillsResults() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/graduate/skillsResults.jsp'/>", false);
        }

        function skillsResultsAudit() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/graduate/skillsResultsAudit.jsp'/>", false);
        }

        function doctorResults() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/graduate/doctorResults.jsp'/>", false);
        }

        function personalResults() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/graduate/personalResults.jsp'/>", false);
        }

        /* 统计分析 */
        function trainBaseStatistics() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/statistics/trainBaseStatistics.jsp'/>", false);
        }

        function specialBaseStatistics() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/statistics/specialBaseStatistics.jsp'/>", false);
        }

        function hosBaseStatistics() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/statistics/hosBaseStatistics.jsp'/>", false);
        }

        function doctorOverView() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/statistics/doctorOverView.jsp'/>", false);
        }

        function doctorStatistics() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/statistics/doctorStatistics.jsp'/>", false);
        }

        function generalDoctorStatistics() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/statistics/generalDoctorStatistics.jsp'/>", false);
        }

        function specialDoctorStatistics() {
            jboxLoad("content", "<s:url value='/jsp/jsres/province/statistics/specialDoctorStatistics.jsp'/>", false);
        }

        /* 设置 */
        function schoolList() {
            jboxLoad("content", "<s:url value='/jsp/jsres/system/sys/schoolList.jsp'/>", false);
        }

        function sysDeptList() {
            jboxLoad("content", "<s:url value='/jsp/jsres/system/sys/sysDeptList.jsp'/>", false);
        }

        function specialList() {
            jboxLoad("content", "<s:url value='/jsp/jsres/system/sys/specialList.jsp'/>", false);
        }


        function baseSpecialList() {
            //var url = "<s:url value='/jsp/jsres/system/sys/baseSpecialList.jsp'/>";
            var url = "<s:url value='/jsres/base/orgSpeManage'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function baseLog() {
            jboxLoad("content", "<s:url value='/jsp/jsres/system/sys/baseLog.jsp'/>", false);
        }

        function selectMenu(menuId) {
            $("#" + menuId).find("a").click();
        }

        $(document).ready(function () {
            init();
            showHosCanvas();
        });

        function searchOrgInfo() {
            currentJboxLoadNoData("content", "<s:url value='/jsp/jsres/global/hospital/specialBaseStatistics.jsp'/>", true);
        }

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

        function showHosCanvas() {


        }

        function showDoctorCanvas() {

        }

        function countryBaseStatistics() {
            jboxLoad("content", "<s:url value='/jsres/statistic/statisticCountryOrgAcc'/>", true);
        }

        function baseAllocationDetails() {
            var url = "<s:url value='/res/resFunds/queryBaseAllocationDetails'/>?role=global";
            currentJboxLoadNoData("content", url, true);
        }

        function provinceFundList() {
            var url = "<s:url value='/res/resFunds/provinceFundList'/>";
            jboxLoad("content", url, true);
        }

        // 综合费用管
        function syntheticalCosTmanagement() {
            var url = "<s:url value='/res/resFunds/queryCosTmanagementList'/>?role=hospital";
            currentJboxLoadNoData("content", url, true);
        }

        //督导-项目管理
        function subjectMain(localSubject){
            var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content","<s:url value='/jsres/supervisio/subjectMain'/>?roleFlag="+roleFlag+"&localSubject="+localSubject,true);
        }

        //督导 --人员管理
        function supervisioMain(){
            var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content","<s:url value='/jsres/supervisio/supervisioMain'/>?roleFlag="+roleFlag,true);
        }

        //督导--评估指标
        function planMain(){
            var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content","<s:url value='/jsres/supervisio/planMain'/>?roleFlag="+roleFlag,true);
        }

        //督导--基地自评汇总表
        function manageSuperVisio(){
            var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content","<s:url value='/jsres/supervisio/manageSuperVisio'/>?roleFlag="+roleFlag,true);
        }

        //督导--专业基地汇总表
        function baseExpertSuperVisio() {
            var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content","<s:url value='/jsres/supervisio/baseExpertSuperVisio'/>?roleFlag="+roleFlag,true);
        }


        function jointBaseStatistics() {
            jboxLoad("content", "<s:url value='/jsres/statistic/statisticJointOrgAcc'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}", true);
        }

        function accountManage() {
            jboxLoad("content", "<s:url value='/jsres/doctor/accountManage'/>?source=hospital", true);
        }

        function kjtz() {
            jboxLoad("content", "<s:url value='/jsres/notice/main'/>", true);
        }

        function guides() {
            var url = "<s:url value='/jsres/training/getGuides/global'/>";
            jboxStartLoading();
            jboxPost(url, null, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        function scoreCfg() {
            var url = "<s:url value='/jsres/base/scoreCfg'/>";
            jboxLoad("content", url, true);
        }

        function statisticsAppUser() {
            jboxLoad("content", "<s:url value='/jsres/appUseInfo/mainAcc?userListScope=global'/>", false);
            <%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
        }

        /*app使用情况月报*/
        function appUse() {
            currentJboxLoadNoData("content", "<s:url value='/res/monthlyReportGlobal/appUseInfo'/>", true);
            <%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
        }

        function jiaoxueActive() {
            currentJboxLoadNoData("content", "<s:url value='/res/monthlyReportGlobal/teachActiveInfoNew'/>", true);
            <%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
        }

        function doctorOutOffice() {
            <%--jboxLoad("content","<s:url value='/res/monthlyReportGlobal/doctorOutOfficeInfo'/>",false);--%>
            currentJboxLoadNoData("content", "<s:url value='/res/monthlyReportGlobal/doctorOutDeptNew'/>", true);
        }

        function localDoctorException() {
            currentJboxLoadNoData("content", "<s:url value='/jsres/manage/doctorException'/>", true);
        }

        function monthDataMain() {
            currentJboxLoadNoData("content", "<s:url value='/jsres/manage/monthDataMain'/>", true);
        }

        function statistics2App() {
            jboxLoad("content", "<s:url value='/jsres/statistic/statistics2App'/>", true);
        }

        function changeSpeMain() {
            var url = "<s:url value='/jsres/manage/changeSpeMain'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function changeBaseMain() {
            var url = "<s:url value='/jsres/manage/changeBase'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function searchChangeSpe() {
            var url = "<s:url value='/jsres/manage/searchChangeSpe'/>?viewFlag=Y";
            jboxLoad("content", url, true);
        }

        function speMain() {
            var url = "<s:url value='/jsres/manage/speMainAcc'/>";
            jboxLoad("content", url, true);
        }

        function zlxytj() {
            var url = "<s:url value='/jsres/manage/zlxytj'/>";
            jboxLoad("content", url, true);
        }

        //招录统计报表 需求570
        function zltjReport() {
            var url = "<s:url value='/jsres/manage/recruitStatisticsReportAcc'/>";
            jboxLoad("content", url, true);
        }

        function zlxytj2() {
            var url = "<s:url value='/jsres/manage/zlxytj2'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}&joint=Y";
            jboxLoad("content", url, true);
        }

        function backTrain() {
            var url = "<s:url value='/jsres/doctor/globalBackTrainInfoAcc?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function delay() {
            var url = "<s:url value='/jsres/doctor/delay'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function delayNew() {
            var url = "<s:url value='/jsres/doctor/delayNewAcc'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function teachTrainMain() {
            var url = "<s:url value='/jsres/statistic/searchTeachTrainMain'/>?roleFlag=global";
            currentJboxLoadNoData("content", url, true);
        }

        function statisticsTeachTrainMain() {
            var url = "<s:url value='/jsres/statistic/statisticsTeachTrainMain'/>?roleFlag=global";
            currentJboxLoadNoData("content", url, true);
        }

        function searchOldTeachTrain() {
            var url = "<s:url value='/jsres/statistic/searchOldTeachTrain'/>?roleFlag=global";
            currentJboxLoadNoData("content", url, true);
        }

        //学员填写量
        function doctorDataMain() {
            var url = "<s:url value='/jsres/statistic/doctorDataMainAcc'/>?roleFlag=global";
            currentJboxLoadNoData("content", url, true);
        }

        //带教审核量
        function teacherAuditMain() {
            var url = "<s:url value='/jsres/statistic/teacherAuditMain'/>?roleFlag=global";
            currentJboxLoadNoData("content", url, true);
        }

        function gradeSearch() {
            var form = $("#gradeSearchForm").serialize() || {"gradeRole": "teacher"};
            var url = "<s:url value='/jsres/manage/gradeSearch'/>?role=${GlobalConstant.USER_LIST_GLOBAL}";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                jboxEndLoading();
                $("#content").html(resp);
            }, null, false);
        }

        function getCityArea() {
            var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
            var provIds = "320000";
            jboxGet(url, null, function (json) {
                // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                var newJsonData = new Array();
                var j = 0;
                for (var i = 0; i < json.length; i++) {
                    if (provIds == json[i].v) {
                        var citys = json[i].s;
                        for (var k = 0; k < citys.length; k++) {
                            newJsonData[j++] = citys[k];
                        }
                    }
                }
                $.cxSelect.defaults.url = newJsonData;
                $.cxSelect.defaults.nodata = "none";
                $("#provCityAreaId").cxSelect({
                    selects: ["city"/* ,"area" */],
                    nodata: "none",
                    firstValue: ""
                });
                $("#cityAreaId").cxSelect({
                    selects: ["city"/* ,"area" */],
                    nodata: "none",
                    firstValue: ""
                });

            }, null, false);
        }

        function init() {
            <c:forEach items="${resDocTypeEnumList}" var="type">
            $("#" + "${type.id}").attr("checked", true);
            </c:forEach>
            getCityArea();
            initOrg(null, null);
            //getSpeInitData(getDocInitData);
            searchGraduates();
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
            var geturl = "<s:url value='/jsres/manage/doctorNumSearch'/>?" + $("#doctorNumSearch").serialize();
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
            //专业基地关系
            $("#orgLevelId2").unbind("change").bind("change", searchInfo2);
            $("#cityId2").unbind("change").bind("change", searchInfo2);


            $("#cityId").unbind("change").bind("change", function (obj) {
                changeOrg(obj);
                searchInfo();
            });
            $("#orgLevelId").unbind("change").bind("change", function (obj) {
                changeOrg(obj);
                searchInfo();
            });
            //$("#orgFlow").unbind("change").bind("change",searchInfo);
            $("#trainTypeId").unbind("change").bind("change", searchInfo);
            //$("#sessionNumber").unbind("change").bind("change",searchInfo);
            $("[name='datas']").unbind("change").bind("change", searchInfo);

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
                $("#orgFlow").val(this.id);
                searchInfo();
            };
            $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
        }

        function changeOrg(obj) {
            var cityid = $("#cityId").val();
            var typeid = $("#orgLevelId").val();
            $("#trainOrg").val("");
            initOrg(cityid, typeid);
        }

        function searchInfo2() {
            jboxLoad("orgSpe1", "<s:url value='/jsres/manage/orgSpe'/>?" + $("#orgSpe").serialize(), true);
        }

        function searchInfo() {
            if ($("#trainOrg").val() == "") {
                $("#orgFlow").val("");
            }
            var sessionNumber = $("#sessionNumber").val();
            if (sessionNumber == "") {
                //jboxTip("年份不能为空！");
                return;
            }
            var trainTypeId = $("#trainTypeId").val();
            if (trainTypeId == "") {
                //jboxTip("培训类别不能为空！");
                return;
            }
            var data = "";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if ($("#" + "${type.id}").attr("checked")) {
                data += "&datas=" + $("#" + "${type.id}").val();
            }
            </c:forEach>
            if (data == "") {
                //jboxTip("请选择人员类型！");
                return;
            }
            //jboxLoad("doctorNum1","<s:url value='/jsres/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize(),true);
        }

        function searchGraduates() {
            var sessionNumber = $("#schoolSessionNumber").val();
            if (sessionNumber == "") {
                //	jboxTip("年份不能为空！");
                return;
            }
            var workOrgName = $("#workOrgName").val();
            var schoolSessionNumber = $("#schoolSessionNumber").val();
            //jboxLoad("schoolNum2","<s:url value='/jsres/manage/graduateNumSearch'/>?workOrgName="+encodeURIComponent(encodeURIComponent(workOrgName))+"&sessionNumber="+schoolSessionNumber);
        }

        //黑名单信息查询
        function searchBlackInfo() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            var url = "<s:url value='/jsres/blackList/blackListInfo?currentPage=1'/>" + "&roleFlag=" + roleFlag;
            currentJboxLoadNoData("content", url, true);
        }

        function searchBlackInfoNew() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            var url = "<s:url value='/jsres/blackList/blackListInfoMainAcc?currentPage=1'/>" + "&roleFlag=" + roleFlag;
            currentJboxLoadNoData("content", url, true);
        }

        function signUpmain(typeId) {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/examSignUp/examsignupmain'/>?roleFlag=" + roleFlag + "&typeId=" + typeId, true);
        }

        function scoreManage() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/scoreManage/main'/>?roleFlag=" + roleFlag + "&catSpeId=DoctorTrainingSpe", true);
        }

        function scoreManage2() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/scoreManage/main'/>?roleFlag=" + roleFlag + "&catSpeId=AssiGeneral", true);
        }

        function showSelect() {
            jboxOpen("<s:url value='/jsp/jsres/global/select.jsp'/>", "系统切换", 780, 600);
        }

        function changeBaseAudit() {
            var url = "<s:url value='/jsres/manage/auditBaseChange'/>";
            jboxLoad("content", url, true);
        }

        function baseMain() {
            var url = "<s:url value='/jsres/manage/baseMainAcc'/>";
            jboxLoad("content", url, true);
        }

        function monthlyReport() {
            window.open("<s:url value='/jsres/monthlyReportGlobalNew/main'/>", "_blank")
        }

        function rotationStatistics() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/manage/rotationStatistics'/>?roleFlag=" + roleFlag, true);
        }

        function personStatistic() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/manage/personStatistic'/>?roleFlag=" + roleFlag, true);
        }

        //学员减免管理
        function reductionTab() {
            var url = "<s:url value='/jsres/reduction/reductionTabAcc?roleId=${GlobalConstant.USER_LIST_GLOBAL}'/>";
            currentJboxLoadNoData("content", url, true);
        }

    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            canvasShow();
            canvasShow2();
            canvasShow3();
        });
        function canvasShow() {
            var dom = document.getElementById('containerEchartOne');
            var myChart = echarts.init(dom, null, {
                renderer: 'canvas',
                useDirtyRect: false
            });
            var option= {
                legend: {   //图列设置
                    orient: 'horizontal'
                },
                grid: { //上下左右的间距
                    left: '3%',
                    right: '3%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    data: [${inTrainingYears}]
                },
                yAxis: {},
                series: [
                    {
                        name: '助理全科',
                        barWidth:50,    //柱子宽度
                        data: [${inTrainingNum}],
                        type: 'bar',
                        stack: 'x',
                        itemStyle:{
                            normal:{
                                color:'#3f7ea5',
                                label:{
                                    show:false, //是否显示该列的数据
                                    color:'#ffffff' //字体颜色
                                },
                            }
                        }
                    },
                    {
                        //显示每一个总计
                        type: 'bar',
                        stack: 'x',
                        data: [0,0,0,0,0], //设置0，不显示柱子
                        label:{
                            normal:{
                                show: true,
                                position: 'top',
                                formatter: function (params) {
                                    //合计数据
                                    var total = [${inTrainingNum}];
                                    return total[params.dataIndex];
                                },
                                fontSize: 16,
                                fontWeight: 'bold',
                                textStyle: { color: '#000' }
                            }
                        }
                    }
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
                //在柱子上添加点击事件
                myChart.on('click', function (params) {
                    homePageTraining(params.name,'20')
                })
            }
            window.addEventListener('resize', myChart.resize);
        }
        function canvasShow2() {
            var dom = document.getElementById('containerEchartTwo');
            var myChart = echarts.init(dom, null, {
                renderer: 'canvas',
                useDirtyRect: false
            });
            var option= {
                legend: {   //图列设置
                    orient: 'horizontal'
                },
                grid: { //上下左右的间距
                    left: '3%',
                    right: '3%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    data: [${recruitmentYears}]
                },
                yAxis: {},
                series: [
                    {
                        name: '助理全科',
                        barWidth:50,    //柱子宽度
                        data: [${recruitmentNum}],
                        type: 'bar',
                        stack: 'x',
                        itemStyle:{
                            normal:{
                                color:'#3f7ea5',
                                label:{
                                    show:false, //是否显示该列的数据
                                    color:'#ffffff' //字体颜色
                                },
                            }
                        }
                    },
                    {
                        //显示每一个总计
                        type: 'bar',
                        stack: 'x',
                        data: [0,0,0,0,0], //设置0，不显示柱子
                        label:{
                            normal:{
                                show: true,
                                position: 'top',
                                formatter: function (params) {
                                    //合计数据
                                    var total = [${recruitmentNum}];
                                    return total[params.dataIndex];
                                },
                                fontSize: 16,
                                fontWeight: 'bold',
                                textStyle: { color: '#000' }
                            }
                        }
                    }
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
                //在柱子上添加点击事件
                myChart.on('click', function (params) {
                    homePageRecruit(params.name)
                })
            }
            window.addEventListener('resize', myChart.resize);
        }
        function canvasShow3() {
            var dom = document.getElementById('containerEchartThree');
            var myChart = echarts.init(dom, null, {
                renderer: 'canvas',
                useDirtyRect: false
            });
            var option= {
                legend: {   //图列设置
                    orient: 'horizontal'
                },
                grid: { //上下左右的间距
                    left: '3%',
                    right: '3%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    data: [${graduationYears}]
                },
                yAxis: {},
                series: [
                    {
                        name: '助理全科',
                        barWidth:50,    //柱子宽度
                        data: [${graduationNum}],
                        type: 'bar',
                        stack: 'x',
                        itemStyle:{
                            normal:{
                                color:'#3f7ea5',
                                label:{
                                    show:false, //是否显示该列的数据
                                    color:'#ffffff' //字体颜色
                                },
                            }
                        }
                    },
                    {
                        //显示每一个总计
                        type: 'bar',
                        stack: 'x',
                        data: [0,0,0,0,0], //设置0，不显示柱子
                        label:{
                            normal:{
                                show: true,
                                position: 'top',
                                formatter: function (params) {
                                    //合计数据
                                    var total = [${graduationNum}];
                                    return total[params.dataIndex];
                                },
                                fontSize: 16,
                                fontWeight: 'bold',
                                textStyle: { color: '#000' }
                            }
                        }
                    }
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
                //在柱子上添加点击事件
                myChart.on('click', function (params) {
                    homePageTraining(params.name,'21')
                })
            }
            window.addEventListener('resize', myChart.resize);
        }

        function homePageTraining(sessionNumber,statisticsType) {
            var url = "<s:url value ='/jsres/manage/homePageTrainingMainAcc'/>?sessionNumber="+sessionNumber+"&statisticsType="+statisticsType;
            jboxOpen(url, "在培人员情况", 1000, 700);
        }

        function homePageRecruit(sessionNumber) {
            var url = "<s:url value ='/jsres/manage/homePageRecruitMainAcc'/>?sessionNumber="+sessionNumber;
            jboxOpen(url, "招录人员情况", 1000, 700);
        }

    </script>
</head>

<body>
<div id="indexBody">
    <div class="bd_bg">
        <div class="yw">

            <div class="head">
                <div class="head_inner">
                    <h1 class="logo">
                        <a href="<s:url value='/jsres/manage/global'/>"><%=JsresUtil.getTitle(request, response, application)%>
                        </a>
                    </h1>
                    <div class="account">
                        <h2 class="head_right">${sessionScope.currUser.orgName }</h2>
                        <div class="head_right">
                            <jsp:include page="/jsp/jsres/changephyAss.jsp" flush="true"></jsp:include>
                            <a href="<s:url value='/jsres/manage/global'/>">首页</a>&#12288;
                            <c:if test="${f}">
                                <a href="#" onclick="showSelect();">系统切换</a >&#12288;
                            </c:if>
                            <a href="<s:url value='/inx/jsres/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <dl class="menu menu_first">
                            <dt class="menu_title">
                                <i class="icon_menu menu_function"></i>基地信息管理
                            </dt>
                            <!--           <dd class="menu_item" id="auditHospitals"><a onclick="auditHospitals();">基地信息审核</a></dd> -->
                            <!--           <dd class="menu_item" id="hospitalList"><a onclick="searchBaseInfo();">基地信息查询</a></dd> -->
                            <dd class="menu_item" id="hospitalList"><a onclick="searchOrgInfo();">培训基地清单</a></dd>
                            <!-- <dd class="menu_item"><a href="javascript:timeoutRegisterHoses();">超时注册名单</a></dd> -->
                            <dd class="menu_item"><a onclick="baseEvaluation();">培训基地评估</a></dd>
                        </dl>
                        <%--<dl class="menu">--%>
                            <%--<dt class="menu_title">--%>
                                <%--<i class="icon_menu menu_statistics"></i>经费管理--%>
                            <%--</dt>--%>
                            <%--<dd class="menu_item"><a href="javascript:provinceFundList();">经费收支管理</a></dd>--%>
                            <%--<dd class="menu_item"><a href="javascript:baseAllocationDetails();">基地拨付详情</a></dd>--%>
                            <%--<dd class="menu_item"><a href="javascript:syntheticalCosTmanagement();">综合费用管理</a></dd>--%>
                        <%--</dl>--%>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_into"></i>督导管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:subjectMain();">项目管理</a></dd>
                            <dd class="menu_item"><a href="javascript:supervisioMain();">专家管理</a></dd>
                            <dd class="menu_item"><a href="javascript:planMain();">评估指标</a></dd>
                            <dd class="menu_item"><a href="javascript:manageSuperVisio();">基地自评汇总</a></dd>
                            <dd class="menu_item"><a href="javascript:baseExpertSuperVisio();">专业基地自评汇总表</a></dd>
                        </dl>

                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>学员招录管理
                            </dt>
                            <dd class="menu_item"><a onclick="zlDocCheck();">招录学员审核</a></dd>
                            <%--<dd class="menu_item"><a onclick="zltjReport();">招录统计报表</a></dd>--%>
                            <dd class="menu_item"><a onclick="zltjReport();">招录学员统计</a></dd>
                            <dd class="menu_item"><a onclick="zlDocQuery();">招录学员查询</a></dd>
                            <dd class="menu_item"><a onclick="zltjCity();">招录统计-地市</a></dd>
                            <dd class="menu_item"><a onclick="zltjOrg();">招录统计-基地</a></dd>
                            <%--<dd class="menu_item"><a onclick="zlxytj();">住院医师招录统计</a></dd>--%>
                            <%--<dd class="menu_item"><a onclick="zlxytj2();">招录学员统计</a></dd>--%>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_doctor"></i>异动学员管理
                            </dt>
                            <dd class="menu_item">
                                <a onclick="reductionTab();">学员减免管理</a>
                            </dd>
                            <dd class="menu_item"><a onclick="speMain();">专业变更管理
                                <%--<img  id="speFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
                            </a>
                            </dd>
                            <dd class="menu_item"><a onclick="baseMain();">基地变更管理
                                <%--<img  id="baseFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
                            </a></dd>
                            <dd class="menu_item"><a href="javascript:backTrain();">退培学员管理
                                <%--<img  id="backFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
                            </a>
                            </dd>
                            <%--<dd class="menu_item"><a onclick="delay();">延期学员查询</a></dd>--%>
                            <dd class="menu_item"><a onclick="delayNew();">延期学员查询</a></dd>
                            <%--<dd class="menu_item"><a onclick="searchBlackInfo();">黑名单管理</a></dd>--%>
                            <dd class="menu_item"><a onclick="searchBlackInfoNew();">黑名单管理</a></dd>
                        </dl>

                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management_train_query"></i>学员培训查询
                            </dt>
                            <dd class="menu_item"><a href="javascript:doctorList();">学员信息查询</a></dd>
                            <%--<dd class="menu_item"><a href="javascript:archiveDoctorList();">存档学员查询</a></dd>--%>
                            <dd class="menu_item"><a href="javascript:docWorkQuery();">学员工作量查询</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management_train_query"></i>师资管理
                            </dt>
                            <dd class="menu_item"><a onclick="phyAssPlanMain()">培训计划查询</a></dd>
                            <dd class="menu_item"><a onclick="phyAssDoctorListMain()">师资培训信息</a></dd>
                            <dd class="menu_item"><a onclick="djxxgl();">带教信息管理</a></dd>
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
                                <i class="icon_menu menu_management_complete"></i>助理全科结业管理
                            </dt>
                            <%--<dd class="menu_item"><a onclick="testConfig()">考核配置</a></dd>--%>
                            <dd class="menu_item"><a onclick="asseWaitAudit2();">结业考核资格审核</a></dd>
                            <dd class="menu_item"><a onclick="asseAuditList2();">结业考核资格查询</a></dd>
                            <dd class="menu_item"><a onclick="scoreManage2()">结业成绩管理</a></dd>
                            <%--<dd class="menu_item"><a onclick="doctorFileManage()">结业学员归档</a></dd>--%>
                            <dd class="menu_item"><a onclick="certificateManage2()">结业证书管理</a></dd>
                            <dd class="menu_item"><a onclick="examStatistics2()">考试人员情况统计</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_statis"></i>统计分析
                            </dt>
                            <dd class="menu_item"><a href="javascript:countryBaseStatistics();">学员信息统计</a></dd>
                            <dd class="menu_item"><a href="javascript:jointBaseStatistics();">协同基地信息统计</a></dd>
                            <dd class="menu_item"><a href="javascript:statisticsAppUser();">APP使用情况统计</a></dd>
                            <dd class="menu_item"><a href="javascript:statistics2App();">住培平台报表</a></dd>
                            <%--<dd class="menu_item"><a href="javascript:statisticsTeachTrain();">师资培训统计</a></dd>--%>
                            <%--<dd class="menu_item"><a href="javascript:monthlyReport();">培训月报查询</a></dd>--%>

                          <%--  &lt;%&ndash;报表，16版本暂时先注掉&ndash;%&gt;--%>
<%--                            <dd class="menu_item"><a href="javascript:rotationStatistics();">学员轮转数据统计</a></dd>--%>
<%--                            <dd class="menu_item"><a href="javascript:personStatistic();">人员情况统计</a></dd>--%>
<%--                            <dd class="menu_item"><a href="javascript:appUse();">app使用情况月报</a></dd>--%>
<%--                            <dd class="menu_item"><a href="javascript:jiaoxueActive();">教学活动统计月报</a></dd>--%>
<%--                            <dd class="menu_item"><a href="javascript:doctorOutOffice();">学员出科情况统计</a></dd>--%>
<%--                            <dd class="menu_item"><a onclick="localDoctorException()">学员轮转异常统计</a></dd>--%>
<%--                            <dd class="menu_item"><a onclick="monthDataMain()">月度统计报表</a></dd>--%>
                        </dl>

                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_system"></i>系统设置
                            </dt>
                            <dd class="menu_item"><a onclick="kjtz()">科教通知</a></dd>
                            <dd class="menu_item"><a onclick="guides()">住培指南</a></dd>
                            <dd class="menu_item"><a href="javascript:accountManage();">学员帐号管理</a></dd>
                            <dd class="menu_item"><a href="javascript:baseSpecialList();">基地专业管理</a></dd>
                            <dd class="menu_item"><a href="javascript:accounts();">安全中心</a></dd>
                            <c:if test="${sessionScope.currUser.userCode eq 'kjc' or sessionScope.currUser.userCode eq 'tjkjc'}">
                                <dd class="menu_item"><a href="javascript:parameterConfig();">参数配置</a></dd>
                            </c:if>
                            <%--<dd class="menu_item"><a href="javascript:parameterConfigLog();">参数配置日志</a></dd>--%>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                        <h1 style="background:#e7f5fc;height: 40px;font-size: 15px;font-width: normal;line-height: 40px;padding: 0 20px;">在培人员情况</h1>
                        <div id="containerEchartOne" style="height: 400PX"></div>

                        <h1 style="background:#e7f5fc;height: 40px;font-size: 15px;font-width: normal;line-height: 40px;padding: 0 20px;">招录人员情况</h1>
                        <div id="containerEchartTwo" style="height: 400PX"></div>

                        <h1 style="background:#e7f5fc;height: 40px;font-size: 15px;font-width: normal;line-height: 40px;padding: 0 20px;">结业人员情况</h1>
                        <div id="containerEchartThree" style="height: 400PX"></div>
                        <%--<div class="content_main">--%>
                        <%--<div class="index_show">--%>
                        <%--<div class="index_tap_global top_left">--%>
                        <%--<ul class="inner">--%>
                        <%--<li class="index_item_global index_blue">--%>
                        <%--<a href="javascript:selectMenu('hospitalList');">--%>
                        <%--<span class="tap_inner">--%>
                        <%--<span style="float: left;margin-left: 35px">--%>
                        <%--<i class="countryOrg"></i>--%>
                        <%--<em class="number"><label id="countryOrg">${countryOrgCount}</label></em>--%>
                        <%--<strong  class="title">国家基地</strong>--%>
                        <%--</span>--%>
                        <%--<span style="float: left;margin-left: 95px">--%>
                        <%--<i class="jointOrg"></i>--%>
                        <%--<em class="number"><label id="jointOrg">${jointOrgCount}</label></em>--%>
                        <%--<strong  class="title">协同基地</strong>--%>
                        <%--</span>--%>
                        <%--<span style="float: right;margin-right: 35px">--%>
                        <%--<i class="provinceOrg"></i>--%>
                        <%--<em class="number"><label id="provinceOrg">${provinceOrgCount}</label></em>--%>
                        <%--<strong  class="title">省级基地</strong>--%>
                        <%--</span>--%>
                        <%--</span>--%>
                        <%--</a>--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                        <%--<div class="index_tap_global top_right">--%>
                        <%--<ul class="inner">--%>
                        <%--<li class="index_item" style="width:50%; float:left;">--%>
                        <%--<a href="javascript:selectMenu('auditDoctors');">--%>
                        <%--<span class="tap_inner">--%>
                        <%--<i class="crowd"></i>--%>
                        <%--<c:set var="key" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>--%>
                        <%--<em class="number"><label id="trainDoctor">${doctorCountExtMap[key]["DOCTORCOUNT"]}人</label></em>--%>
                        <%--<strong  class="title">${sysCfgMap['jsres_doctorCount_sessionNumber']}--%>
                        <%--<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">届</c:if>培训医师数</strong>--%>
                        <%--</span>--%>
                        <%--</a>--%>
                        <%--</li>--%>
                        <%--<li class="index_item" style="width:50%; float:left;">--%>
                        <%--<a href="javascript:void(0);">--%>
                        <%--<span class="tap_inner tab_second">--%>
                        <%--<c:set var="yearpl" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}pl"></c:set>--%>
                        <%--<c:forEach items="${resDocTypeEnumList}" var="type">--%>
                        <%--<c:set value="0"  var="keyll"></c:set>--%>
                        <%--<c:if test="${not empty sysCfgMap['jsres_doctorCount_sessionNumber'] }">--%>
                        <%--<c:set value="${doctorCountExtMap[yearpl][type.id]}"  var="keyll"></c:set>--%>
                        <%--</c:if>--%>
                        <%--<lable class="title">${type.name}:${keyll}人&nbsp;<br/></lable>--%>
                        <%--</c:forEach>--%>
                        <%--</span>--%>
                        <%--</a>--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="main_bd">--%>
                        <%--<ul>--%>
                        <%--<li class="score_frame" >--%>
                        <%--<h1 style="background:#e7f5fc;">人员信息概况</h1>--%>
                        <%--<c:set var="currYear" value="${pdfn:getCurrYear()}"></c:set>--%>
                        <%--<div class="grid">--%>
                        <%--<c:forEach step="1" begin="${currYear-2}" end="${currYear}" varStatus="num" var="doctor">--%>
                        <%--<table style="<c:if test='${num.count<3}'>float: left;margin-left:1%;</c:if> <c:if test='${num.count==3}'>margin-left:67%;</c:if>margin-top: 10px;margin-bottom: 10px;border: 1px solid #ccc;"  cellpadding="0" cellspacing="0" width="32%">--%>
                        <%--<tbody>--%>
                        <%--<c:set var="year" value="${currYear-num.count+1}"></c:set>--%>
                        <%--<c:set var="key" value="${year}pl"></c:set>--%>
                        <%--<tr>--%>
                        <%--<th align="center" style="background-color: #e7e7eb;" >${year}届&#12288;${doctorCountExtMap[key]["DOCTORCOUNT"]}人</th>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                        <%--<td align="left">--%>
                        <%--<p style="width:100%">--%>
                        <%--<span style="float: left;font-weight: 900;width:33%">培训类别：</span>--%>
                        <%--<span style="float: left;width:33%;text-align:center;">住院医师<br/>${doctorCountExtMap[key]["DoctorTrainingSpe"]}人</span>--%>
                        <%--<span style="float: left;width:33%;text-align:center;">助理全科<br/>${doctorCountExtMap[key]["AssiGeneral"]}人</span>--%>
                        <%--</p>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                        <%--<td align="left">--%>
                        <%--<p style="width:100%">--%>
                        <%--<span style="float: left;font-weight: 900;width:30%">人员类型：</span>--%>
                        <%--<span style="float: left;width:22.5%;text-align:center;">单位人<br/>${doctorCountExtMap[key]["Company"]}人</span>--%>
                        <%--<span style="float: left;width:22.5%;text-align:center;">行业人<br/>${doctorCountExtMap[key]["Social"]}人</span>--%>
                        <%--<span style="float: left;width:25%;text-align:center;">在校专硕<br/>${doctorCountExtMap[key]["Graduate"]}人</span>--%>
                        <%--</p>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                        <%--<td align="left">--%>
                        <%--<p style="width:100%">--%>
                        <%--<span style="float: left;font-weight: 900;width:33%;">基地类型：</span>--%>
                        <%--<span style="float: left;width:33%;text-align:center;">国家基地<br/>（含协同）<br/>${doctorCountExtMap[key]["CountryOrg"]+doctorCountExtMap[key]["JointOrg"]}人</span>--%>
                        <%--<span style="float: left;width:33%;text-align:center;">省级基地<br/>${doctorCountExtMap[key]["ProvinceOrg"]}人</span>--%>
                        <%--</p>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                        <%--</table>--%>
                        <%--</c:forEach>--%>
                        <%--</div>--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                        <%--<div class="main_bd">--%>
                        <%--<ul>--%>
                        <%--<li class="score_frame">--%>
                        <%--<h1 style="background:#e7f5fc;">当前住培情况</h1>--%>
                        <%--<table class="in_table" style="float: left;width: 48%; margin-top: 10px;margin: 10px;margin-bottom: 10px;border: 1px solid #ccc;" cellpadding="0" cellspacing="0" width="100%">--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                        <%--<td>--%>
                        <%--<div id="docChartForIndex" style="height: 200px;"></div>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                        <%--</table>--%>
                        <%--<div class="grid" style="overflow: auto;height: 200px;width: auto;margin: 10px">--%>
                        <%--<table class="in_table" style="border: 1px solid #ccc;" cellpadding="0" cellspacing="0" width="100%">--%>
                        <%--<colgroup>--%>
                        <%--<col width="25%"/>--%>
                        <%--<col width="25%"/>--%>
                        <%--<col width="25%"/>--%>
                        <%--<col width="25%"/>--%>
                        <%--</colgroup>--%>
                        <%--<tr style="height: 60px;"><td colspan="4">--%>
                        <%--<div style="float: left;margin-left:80px;height: 10px;width: 10px;background: rgba(255,120,50,1);"></div><div style="float: left;line-height: 10px;"> ：在培人数</div>--%>
                        <%--<div style="float: left;margin-left:20px;height: 10px;width: 10px;background: rgba(100,200,255,1);"></div><div style="float: left;line-height: 10px;"> ：已考核待结业人数</div>--%>
                        <%--<div style="float: left;margin-left:20px;height: 10px;width: 10px;background: rgba(255,120,255,1);"></div><div style="float: left;line-height: 10px;"> ：结业人数</div></td></tr>--%>
                        <%--<c:forEach items="${currDocDetailMaps}" var="currDocDetail">--%>
                        <%--<tr>--%>
                        <%--<td style="text-align: center">${currDocDetail['SESSIONNUMBER']}届</td>--%>
                        <%--<td style="text-align: center">${currDocDetail['20'] ==null? 0:currDocDetail['20']}人</td>--%>
                        <%--<td style="text-align: center">${currDocDetail['22'] ==null? 0:currDocDetail['22']}人</td>--%>
                        <%--<td style="text-align: center">${currDocDetail['21'] ==null? 0:currDocDetail['21']}人</td>--%>
                        <%--</tr>--%>
                        <%--</c:forEach>--%>
                        <%--</table>--%>
                        <%--</div>--%>
                        <%--&lt;%&ndash;<div style="background-color: #e7f5fc;height: 50px;margin:10px ; text-align: center;font-size: large">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<table class="in_table" style="background-color:#e7f5fc;" cellpadding="0" cellspacing="0"  width="100%">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<tr style="height: 12px"></tr>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td style="text-align: center">填写数据总量：${sumCountAudit['SUMCOUNT']}条</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td style="text-align: center">带教审核总量：${sumCountAudit['SUMCOUNTRES']}条</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td style="text-align: center">系统总访问量：${count}次</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</table>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                        <%--<div class="main_bd" style="height:auto;">--%>
                        <%--<ul>--%>
                        <%--<li class="score_frame">--%>
                        <%--<h1 style="background:#e7f5fc;">医师信息概况</h1>--%>
                        <%--<div class="" style="margin-top: 15px;">--%>
                        <%--<form id="doctorNumSearch">--%>
                        <%--<table cellspacing="8px">--%>
                        <%--<tr>--%>
                        <%--<td>&#12288;地&#12288;&#12288;区：</td>--%>
                        <%--<td>--%>
                        <%--<div id="provCityAreaId" style="width:150px;">--%>
                        <%--<select id="cityId" name="cityId" style="width:150px;" class="city select" data-value="" data-first-title="选择市" onchange="changeOrg(this);"></select>--%>
                        <%--</div>--%>
                        <%--</td>--%>
                        <%--<td>&#12288;基地类型：</td>--%>
                        <%--<td>--%>
                        <%--<select name="orgLevel" id="orgLevelId" class="select" style="width:150px;" onchange="changeOrg(this);">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${orgLevelEnumList}" var="level">--%>
                        <%--<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>--%>
                        <%--<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>--%>
                        <%-->${level.name}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</td>--%>
                        <%--<td>&#12288;培训基地：</td>--%>
                        <%--<td>--%>
                        <%--<div style="width:150px;background-color: grey">--%>
                        <%--<input id="trainOrg" class="input" type="text"  style="margin-left: 0px;width: 150px" autocomplete="off" />--%>
                        <%--<input id="orgFlow" name="orgFlow"  class="input" type="text" hidden  style="margin-left: 0px;width: 150px" />--%>
                        <%--</div>--%>
                        <%--</td>--%>
                        <%--<td>&#12288;培训类别：</td>--%>
                        <%--<td>--%>
                        <%--<select name="trainTypeId" id="trainTypeId" class="select" style="width:150px;">--%>
                        <%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
                        <%--<option value="${trainCategory.id}" <c:if test="${trainCategoryEnumDoctorTrainingSpe.id eq trainCategory.id}">selected</c:if>--%>
                        <%--<c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>--%>
                        <%-->${trainCategory.name}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr><td>&nbsp;</td></tr>--%>
                        <%--<tr>--%>
                        <%--<td>&#12288;年&#12288;&#12288;级：</td>--%>
                        <%--<td>--%>
                        <%--<input type="text" id="sessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchInfo();}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 150px;margin-left: 0px" />--%>
                        <%--</td>--%>
                        <%--<td>&#12288;人员类型：</td>--%>
                        <%--<td colspan="2">--%>
                        <%--<c:forEach items="${resDocTypeEnumList}" var="type">--%>
                        <%--<label><input name="datas" type="checkbox" id="${type.id}" name="${type.id}" value="${type.id}"/>${type.name}&nbsp;</label>--%>
                        <%--</c:forEach>--%>
                        <%--</td>--%>
                        <%--<td></td>--%>
                        <%--<td></td>--%>
                        <%--<td></td>--%>
                        <%--&lt;%&ndash;<td><input class="btn_green" type="button" value="查询" onclick="searchInfo();"/></td>&ndash;%&gt;--%>
                        <%--</tr>--%>
                        <%--</table>--%>
                        <%--</form>--%>
                        <%--</div>--%>
                        <%--<div class="index_table" id="doctorNum1"style="height: 550px;width:100%;margin-top: 50px;">--%>

                        <%--</div>--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                        <%--<div class="main_bd" style="height:auto;">--%>
                        <%--<ul>--%>
                        <%--<li class="score_frame">--%>
                        <%--<h1 style="background:#e7f5fc;">高校在校专硕信息概况</h1>--%>
                        <%--<div class="" style="margin-top: 15px;">--%>
                        <%--<form id="graduateNumSearch" method="post">--%>
                        <%--<table cellspacing="8px">--%>
                        <%--<tr>--%>
                        <%--<td>&#12288;年级：</td>--%>
                        <%--<td>--%>
                        <%--<input type="text" id="schoolSessionNumber" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(dp){searchGraduates();}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 150px;margin-left: 0px;" />--%>
                        <%--</td>--%>
                        <%--<td>&#12288;医学院校：</td>--%>
                        <%--<td>--%>
                        <%--<select name="workOrgName" id="workOrgName" class="select" style="width:150px;" onchange="searchGraduates();">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">--%>
                        <%--<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--</table>--%>
                        <%--</form>--%>
                        <%--</div>--%>
                        <%--<div class="index_table" id="schoolNum2"style="height: 550px;width:100%;margin-top: 50px;">--%>

                        <%--</div>--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                        <%--<div class="main_bd" style="height:auto;">--%>
                        <%--<ul>--%>
                        <%--<li class="score_frame">--%>
                        <%--<h1 style="background:#e7f5fc;">专业基地概况</h1>--%>
                        <%--<div class="" style="margin-top: 15px;">--%>
                        <%--<form id="orgSpe">--%>
                        <%--<table cellspacing="8px">--%>
                        <%--<tr>--%>
                        <%--<td>&#12288;地&#12288;&#12288;区：</td>--%>
                        <%--<td>--%>
                        <%--<div id="cityAreaId" style="width:150px;">--%>
                        <%--<select id="cityId2" name="cityId" style="width:150px;" class="city select" data-value="" data-first-title="选择市"></select>--%>
                        <%--</div>--%>
                        <%--</td>--%>
                        <%--<td>&#12288;基地类型：</td>--%>
                        <%--<td>--%>
                        <%--<select name="orgLevel" id="orgLevelId2" class="select" style="width:150px;" >--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${orgLevelEnumList}" var="level">--%>
                        <%--<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>--%>
                        <%--<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>--%>
                        <%-->${level.name}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</td>--%>
                        <%--<td>&#12288;&#12288;&#12288;&#12288;</td>--%>
                        <%--<td>&#12288;&#12288;&#12288;&#12288;</td>--%>
                        <%--<td>&#12288;&#12288;&#12288;&#12288;</td>--%>
                        <%--<td></td>--%>
                        <%--</tr>--%>

                        <%--</table>--%>
                        <%--</form>--%>
                        <%--</div>--%>
                        <%--<div class="index_table" id="orgSpe1"style="height: 550px;width:100%;margin-top: 30px;">--%>

                        <%--</div>--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                        <%--</div>--%>
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

</body>
</html>
