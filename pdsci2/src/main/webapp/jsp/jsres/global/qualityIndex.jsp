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
    </script>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
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
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/provinceDoctorListNew'/>?roleFlag=" + roleFlag, true);
        }

        function zlDocQuery() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/main'/>?roleFlag=" + roleFlag, true);
        }

        function zltjCity() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/zltjCityMain'/>?tabId=doctorType&roleFlag=" + roleFlag, true);
        }

        function zltjOrg() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/recruitDoctorInfo/zltjOrgMain'/>?tabId=doctorType&roleFlag=" + roleFlag, true);
        }

        function archiveDoctorList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?isArchive=Y&roleFlag=" + roleFlag, true);
        }

        function docWorkQuery() {
            jboxLoad("content", "<s:url value='/jsres/docWork/docWorkQuery'/>", true);
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
            jboxLoad("content", "<s:url value='/jsres/testConfig/main'/>?roleFlag=" + roleFlag, true);
        }

        function doctorScoreApplyList() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorScoreApply/provinceDoctorList'/>?roleFlag=" + roleFlag, true);
        }

        function CertificateManagement() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/doctorScoreApply/certificateManagement'/>?roleFlag=" + roleFlag, true);
        }

        function CertificateManage() {
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
            jboxLoad("content", "<s:url value='/jsres/statistic/statisticCountryOrg'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}", true);
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

        function jointBaseStatistics() {
            jboxLoad("content", "<s:url value='/jsres/statistic/statisticJointOrg'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}", true);
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
            jboxLoad("content", "<s:url value='/jsres/appUseInfo/main?userListScope=global'/>", false);
            <%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
        }

        /*app使用情况月报*/
        function appUse() {
            jboxLoad("content", "<s:url value='/res/monthlyReportGlobal/appUseInfo'/>", false);
            <%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
        }

        function jiaoxueActive() {
            jboxLoad("content", "<s:url value='/res/monthlyReportGlobal/teachActiveInfo'/>", false);
            <%--jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUserForOrg'/>",true);--%>
        }

        function doctorOutOffice() {
            jboxLoad("content", "<s:url value='/res/monthlyReportGlobal/doctorOutOfficeInfo'/>", false);
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
            var url = "<s:url value='/jsres/manage/speMain'/>";
            jboxLoad("content", url, true);
        }

        function zlxytj() {
            var url = "<s:url value='/jsres/manage/zlxytj'/>";
            jboxLoad("content", url, true);
        }

        //招录统计报表 需求570
        function zltjReport() {
            var url = "<s:url value='/jsres/manage/recruitStatisticsReport'/>";
            jboxLoad("content", url, true);
        }

        function zlxytj2() {
            var url = "<s:url value='/jsres/manage/zlxytj2'/>?trainTypeId=${trainCategoryEnumDoctorTrainingSpe.id}&joint=Y";
            jboxLoad("content", url, true);
        }

        function backTrain() {
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var url = "<s:url value='/jsres/doctor/globalBackTrainInfo?sessionNumber=${pdfn:getCurrYear()}&currentPage=1'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function delay() {
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var url = "<s:url value='/jsres/doctor/delay'/>";
            currentJboxLoadNoData("content", url, true);
        }

        function statisticsTeachTrain() {
            var url = "<s:url value='/jsres/statistic/searchTeachTrain'/>";
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

        function signUpmain(typeId) {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=" + roleFlag + "&typeId=" + typeId, true);
        }

        function scoreManage() {
            var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
            jboxLoad("content", "<s:url value='/jsres/scoreManage/main'/>?roleFlag=" + roleFlag, true);
        }

        function showSelect() {
            jboxOpen("<s:url value='/jsp/jsres/global/select.jsp'/>", "系统切换", 780, 600);
        }

        function changeBaseAudit() {
            var url = "<s:url value='/jsres/manage/auditBaseChange'/>";
            jboxLoad("content", url, true);
        }

        function baseMain() {
            var url = "<s:url value='/jsres/manage/baseMain'/>";
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

        function isHaveDept() {
            var isHaveDept = '${isHaveDept}';
            if (isHaveDept == 'N') {
                return true;
            }
            return false;
        }

        function cycle(data) {
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var docTypes = "";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if (docTypes == "") {
                docTypes += "docTypes=" + "${type.id}";
            } else {
                docTypes += "&docTypes=" + "${type.id}";
            }
            </c:forEach>
            var url = "<s:url value='/jsres/doctorRecruit/cycle'/>?" + docTypes;
            jboxStartLoading();
            jboxPost(url, data, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }

        //查看考勤信息
        function attendanceTab(form){
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var url="<s:url value='/jsp/jsres/attendance/hospital/main.jsp'/>";
            jboxLoad("content", url, false);
        }

        function opinions(data){
            if (isHaveDept()) {
                jboxTip("您暂无所属专业，请联系管理员设置");
                return
            }
            var url = "<s:url value='/jsres/training/getOpinions'/>";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
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
                        <a href="<s:url value='/jsres/manage/global'/>"><%=JsresUtil.getTitle(request, response, application)%>
                        </a>
                    </h1>
                    <div class="account">
                        <h2 class="head_right">${sessionScope.currUser.orgName }</h2>
                        <div class="head_right">
                            <a href="<s:url value='/jsres/manage/global'/>">首页</a>&#12288;
                            <c:if test="${f}">
                                <a href="#" onclick="showSelect();">系统切换</a>&#12288;
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
                                <i class="icon_menu menu_doctor"></i>异动学员管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:backTrain();">退培学员管理
                                <%--<img  id="backFlag" style="display: none;" src="<s:url value="/jsp/jsres/images/gantanhao.png" />"/>--%>
                            </a>
                            </dd>
                            <dd class="menu_item"><a onclick="delay();">延期学员查询</a></dd>
                        </dl>

                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management_train_query"></i>学员培训查询
                            </dt>
                            <dd class="menu_item"><a href="javascript:doctorList();">学员信息查询</a></dd>
                            <dd class="menu_item"><a onclick="cycle(null);">学员轮转查询</a> </dd>
                            <dd class="menu_item"><a onclick="attendanceTab()">学员考勤查询</a></dd>
                            <dd class="menu_item"><a onclick="opinions(null)">住培意见反馈</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
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
