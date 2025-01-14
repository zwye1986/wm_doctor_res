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
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        require.config({
            paths: {
                echarts: "<s:url value='/js/echarts'/>"
            }
        });

        /*require(['echarts','echarts/chart/pie'],function(ec){
            var myChart = ec.init(document.getElementById('docChartForIndex'));
            option = {
                tooltip : {
                    trigger: 'item',
                    formatter: "{b} ({d}%)"
                },
                title : {
                    text: '数据自2014年起',
                    x:'left',
                    y:'bottom'
                },
                series : [
                    {

                        type:'pie',
                        radius : '65%',
                        itemStyle : {
                            normal : {
                                label : {
                                    show : true
                                },
                                labelLine : {
                                    show : true
                                }
                            }
                        },
                        data:[
                            {value:"${currDocSumBef2014['_20count']}", name:'在培人数：${currDocSumBef2014['_20count']}人'},
                            {value:"${currDocSumBef2014['_22count']}", name:'已考核待结业人数：${currDocSumBef2014['_22count']}人'},
                            {value:"${currDocSumBef2014['_21count']}", name:'结业人数：${currDocSumBef2014['_21count']}人'}
                        ]
                    }
                ]
            };
// 为echarts对象加载数据
            myChart.setOption(option);
        });*/
    </script>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script>
        $(document).ready(function(){

            $(".menu_item a").click(function(){
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();
            if("${speFlag}"!="0"){
                $("#speFlag").show();
            }
        });
        $(document).ready(function(){
            init();
        });
        function init()
        {
            <c:forEach items="${resDocTypeEnumList}" var="type">
            $("#"+"${type.id}").attr("checked",true);
            </c:forEach>
            //getSpeInitData(getDocInitData);
            initOrg(null,null);
            setTimeout(bindFunc, 3000);
        }
        function getDocInitData(fun)
        {
            var sessionNumber=$("#sessionNumber").val();
            if(sessionNumber==""){
                jboxTip("年份不能为空！");
                return;
            }
            var trainTypeId=$("#trainTypeId").val();
            if(trainTypeId==""){
                jboxTip("培训类别不能为空！");
                return;
            }
            var data="";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if($("#"+"${type.id}").attr("checked")){
                data+="&datas="+$("#"+"${type.id}").val();
            }
            </c:forEach>
            if(data==""){
                jboxTip("请选择人员类型1！");
                return;
            }
            var geturl="<s:url value='/jsres/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize();
            $.ajax({
                type : "get",
                url : geturl,
                cache : false,
                beforeSend : function(){
                    jboxStartLoading();
                },
                success : function(resp) {
                    $("#doctorNum1").html(resp);
                },
                error : function() {
                },
                complete : function(){
                    jboxEndLoading();
                }
            });
        }
        function getSpeInitData(fun)
        {
            var geturl="<s:url value='/jsres/manage/orgSpe'/>?"+$("#orgSpe").serialize();
            $.ajax({
                type : "get",
                url : geturl,
                cache : false,
                beforeSend : function(){
                    jboxStartLoading();
                },
                success : function(resp) {
                    $("#orgSpe1").html(resp);
                },
                error : function() {
                },
                complete : function(){
                    jboxEndLoading();
                    fun();
                }
            });
        }
        function bindFunc()
        {
            $("#trainTypeId").unbind("change").bind("change",searchInfo);
            $("[name='docType']").unbind("change").bind("change",searchInfo);

        }
        function searchInfo()
        {
            if($("#trainOrg").val()==""){
                $("#orgFlow2").val("");
            }
            var sessionNumber=$("#sessionNumber").val();
            if(sessionNumber==""){
                jboxTip("年份不能为空！");
                return;
            }
            var trainTypeId=$("#trainTypeId").val();
            if(trainTypeId==""){
                jboxTip("培训类别不能为空！");
                return;
            }
            var data="";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if($("#"+"${type.id}").attr("checked")){
                data+="&datas="+$("#"+"${type.id}").val();
            }
            </c:forEach>
            if(data==""){
                jboxTip("请选择人员类型！");
                return;
            }
            jboxLoad("doctorNum1","<s:url value='/jsres/manage/doctorNumSearch'/>?"+$("#doctorNumSearch").serialize(),true);
        }

        function docProcessEval2(form){
            var url="<s:url value='/jsres/hospital/tabMain'/>?isQuery=Y";
            jboxLoad("content",url,true);
        }
    </script>
    <script>
        $(document).ready(function(){
            $(".menu_item a").click(function(){
                $(".select").removeClass("select");
                $(this).addClass("select");
            });
            setBodyHeight();

            getNeedReducation("${orgFlow}",function(result){
                if(result){
                    jboxInfo("还有<font color='blue'>"+result+"</font>人需要维护缩减方案！");
                    $("#reducationCount").show();
                }
            });
            if("${baseFlag}"!="0"){
                $("#baseFlag").show();
            }
            if("${speFlag}"!="0"){
                $("#speFlag").show();
            }

        });
        function currentJboxLoadNoData(id,geturl,showLoading){
            currentJboxLoad(id,geturl,null,showLoading);
        }
        function currentJboxLoad(id,geturl,data,showLoading){
            if(showLoading){
                jboxStartLoading();
            }
            jboxPost(geturl,data,function(resp){
                if(showLoading){
                    jboxEndLoading();
                }
                $('#'+id).html(resp);
            },null,false);
        }
        function getNeedReducation(orgFlow,callBack){
            var url = "<s:url value='/jsres/manage/getNeedReducation'/>";
            jboxPost(url,{orgFlow:orgFlow},function(resp){
                callBack(resp);
            },null,false);
        }

        function setBodyHeight(){
            // 处理ie浏览器placeholder和password的不兼容问题
            if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {
                if(navigator.appVersion.indexOf("MSIE 7.0")>-1){
                    $("#indexBody").css("height",window.innerHeight+"px");
                }else if(navigator.appVersion.indexOf("MSIE 8.0")>-1){
                    $("#indexBody").css("height",document.documentElement.offsetHeight+"px");
                }else{
                    $("#indexBody").css("height",window.innerHeight+"px");
                }
            } else {
                $("#indexBody").css("height",window.innerHeight+"px");
            }
        }

        onresize=function(){
            setBodyHeight();
        }
        function hosMain(baseFlow){
            var url =  "<s:url value='/jsres/base/main'/>?baseFlow="+baseFlow;
            currentJboxLoadNoData("content",url,true);
        }
        //社区培训基地维护
        function commuHospital(){
            jboxLoad("content","<s:url value='/jsp/jsres/hospital/hos/commuHospital.jsp'/>",false);
        }
        //审核进度查询
        function auditHosProcess(){
            jboxLoad("content","<s:url value='/jsp/jsres/hospital/hos/auditHosProcess.jsp'/>",false);
        }

        function accounts(){
            jboxLoad("content","<s:url value='/jsres/manage/accounts'/>",true);
        }

        function doctorList(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag+"&orgFlow=${sessionScope.currUser.orgFlow}"+"&baseFlag=1",true);
        }
        function archiveDoctorList(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?isArchive=Y&roleFlag="+roleFlag+"&baseFlag=1",true);
        }
        function doctorScoreApplyList(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorScoreApply/provinceDoctorList'/>?roleFlag="+roleFlag+"&baseFlag=1",true);
        }
        /*理论成绩*/
        function doctorTheoryList(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/doctorTheoryList'/>?roleFlag="+roleFlag,true);
        }
        function CertificateManage(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/certificateManage/main'/>?roleFlag="+roleFlag,true);
        }
        /*考核资格审查*/
        function asseGraduation(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorTheoryScore/asseGraduationForHos'/>?roleFlag="+roleFlag,true);
        }
        function signUpmain(typeId){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag="+roleFlag+"&typeId="+typeId,true);
        }
        function CertificateManagement(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/doctorScoreApply/certificateManagement'/>?roleFlag="+roleFlag,true);
        }
        function activityTarget(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/activityTarget/manageMain'/>?roleFlag="+roleFlag,true);
        }
        function activityQuery(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/activityQuery/main'/>?roleFlag="+roleFlag,true);
        }
        function deptActivityStatistics(){
            jboxLoad("content","<s:url value='/jsres/deptActivityStatistics/main'/>",true);
        }
        function teacherActivityStatistics(){
            jboxLoad("content","<s:url value='/jsres/teacherActivityStatistics/main'/>",true);
        }
        function doctorActivityStatistics(){
            jboxLoad("content","<s:url value='/jsres/doctorActivityStatistics/main'/>?baseFlag=1",true);
        }
        function cycle(data){
            var docTypes="";
            <c:forEach items="${resDocTypeEnumList}" var="type">
            if(docTypes=="")
            {
                docTypes+="docTypes="+"${type.id}";
            }else{
                docTypes+="&docTypes="+"${type.id}";
            }
            </c:forEach>
            var url = "<s:url value='/jsres/doctorRecruit/cycle'/>?"+docTypes+"&baseFlag=1";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        function cycleResults(data){
            var url = "<s:url value='/jsres/doctorRecruit/doctorRecruitResult'/>?baseFlag=1";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        function opinions(data){
            var url = "<s:url value='/jsres/training/getOpinions'/>";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }

        function guides(){
            var url = "<s:url value='/jsres/training/getGuides/manager'/>";
            jboxStartLoading();
            jboxPost(url,null,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }

        function lectures(data){
            var url = "<s:url value='/jsres/lecture/getLectures'/>";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }

        function recruitAuditSearch(currentPage){
            if(currentPage == undefined){
                currentPage =1;
            }
            var url = "<s:url value='/jsres/manage/province/doctor/recruitAuditSearch'/>?source=hospital";
            jboxLoad("content", url, true);
        }
        //考核申请审核
        function auditGraduate(){
            jboxLoad("content","<s:url value='/jsp/jsres/hospital/doctor/auditGraduates.jsp'/>?source=hospital",false);
        }
        //医师帐号解锁
        function doctorAccountUnlock(){
            jboxLoad("content","<s:url value='/jsp/jsres/hospital/doctor/doctorAccountUnlock.jsp'/>",false);
        }
        //轮转情况查询
        function schDeptInfo(){
            jboxLoad("content","<s:url value='/jsp/jsres/hospital/doctor/schDeptInfo.jsp'/>",false);
        }

        function accountManage(){
            jboxLoad("content","<s:url value='/jsres/doctor/accountManage'/>?source=hospital&baseFlag=1",true);
        }
        function cfgManage(){
            jboxLoad("content","<s:url value='/jsres/cfgManager/main'/>",true);
        }
        function selectMenu(menuId){
            $("#"+menuId).find("a").click();
        }

        function changeSpeMain(){
            var url = "<s:url value='/jsres/manage/changeSpeMain'/>";
            jboxLoad("content", url, false);
        }

        function doctorTrendMain(){
            jboxLoad("content","<s:url value='/jsres/doctorRecruit/doctorRecruitList'/>",false);
        }
        function shouye(page){
            if (page == null || page == undefined || page == '' || isNaN(page)){
                page=1;
            }
            var url="<s:url value='/jsres/manage/baseQueryStudent'/>?currentPage="+page;
            window.location.href=url;
        }
        //培训信息查询
        function trainInfo(){
            jboxLoad("content","<s:url value='/jsres/doctorRecruit/trainInfo'/>",true);
        }

        function reductionRotationOper(){
            data = $("#searchFormReduction").serialize() || {
                degreeType : "${GlobalConstant.FLAG_Y}",
                currentPage : 1,
            };
            var url = "<s:url value='/jsres/manage/reductionRotationOper'/>";
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                jboxEndLoading();
                $("#content").html(resp);
            },null,false);
            var orgFlow=$("#orgFlow3").val();
            getNeedReducation(orgFlow,function(result){
                if(result==0){
                    $("#reducationCount").hide();
                }
            });
        }

        function toPage(page){
            if(!page){
                $("#currentPage").val(page);
            }
            shouye(page);
        }
        function statisticsAppUser(){

            //jboxLoad("content","<s:url value='/jsres/statistic/statisticsAppUser?userListScope=local'/>",true);
            jboxLoad("content","<s:url value='/jsres/appUseInfo/main?userListScope=local'/>",false);
        }
        //培训基地评估
        function searchEvaluationInfo(){
            currentJboxLoadNoData("content","<s:url value='/jsres/evaluation/showMain'/>",true);
        }
        function baseInfo(){
            var url = "<s:url value='/jsres/manage/baseInfo'/>";
            jboxLoad("content", url, true);
        }
        function searchChangeSpe(){
            var url = "<s:url value='/jsres/manage/searchChangeSpe'/>?viewFlag=Y";
            jboxLoad("content", url, true);
        }

        function gradeSearch(){
            var form = $("#gradeSearchForm").serialize() || {"gradeRole":"teacher"};
            var url = "<s:url value='/jsres/manage/gradeSearch'/>?role=${GlobalConstant.USER_LIST_LOCAL}";
            jboxStartLoading();
            jboxPost(url,form,function(resp){
                jboxEndLoading();
                $("#content").html(resp);
            },null,false);
        }
        function deptRotationSearch(){
            jboxLoad("content","<s:url value='/jsres/base/doc/schDept'/>",true);
        }
        function doctorDataAudit(){
            jboxLoad("content","<s:url value='/jsres/doctorDataAudit/auditMain'/>",true);
        }
        function afterDataDel(){
            jboxLoad("content","<s:url value='/jsres/afterDataManager/afterDataDel'/>?baseFlag=1",true);
        }
        function afterDataBack(){
            jboxLoad("content","<s:url value='/jsres/afterDataManager/afterDataBack'/>?baseFlag=1",true);
        }
        function attendBack(){
            jboxLoad("content","<s:url value='/jsres/afterDataManager/attendBack'/>?baseFlag=1",true);
        }
        function docWorkSearch(){
            jboxLoad("content","<s:url value='/jsres/base/docWorkQuery'/>?baseFlag=1",true);
        }
        function monReportSearch(){
            jboxLoad("content","<s:url value='/jsres/base/showMonthlyReportList'/>?baseFlag=1",true);
        }
        function monthlyReport(){
            window.open("<s:url value='/jsres/monthlyReportNew/main'/>","_blank")
        }
        function teacherWorkload(form){
            var url = "<s:url value='/jsres/manage/teacherWorkload'/>";
            jboxStartLoading();
            jboxPost(url,form,function(resp){
                jboxEndLoading();
                $("#content").html(resp);
            },null,false);
        }
        function statisticsTeachTrain(){
            var url = "<s:url value='/jsres/statistic/searchTeachTrain'/>";
            currentJboxLoadNoData("content", url, true);
        }
        /*function delay(){
            var url = "<s:url value='/jsres/doctor/delay'/>";
            currentJboxLoadNoData("content", url, true);
        }*/
        //理论考试资格审查信息
        function examInfoCheck(){
            var url = "<s:url value='/jsres/doctor/searchExamInfo'/>?sessionNumber=${pdfn:getCurrYear()}";
            currentJboxLoadNoData("content", url, true);
        }
        //黑名单信息查询
        function searchBlackInfo(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            var url = "<s:url value='/jsres/blackList/blackListInfo'/>?currentPage=1&roleFlag="+roleFlag;
            jboxLoad("content", url, true);
        }
        //年度考核设置
        function examArrangMent(){
            var url = "<s:url value='/jsres/examCfg/examArrangMent'/>";
            jboxLoad("content", url, true);
        }
        //年度考核成绩查询
        function examQueryScore(){
            var url = "<s:url value='/jsres/examScoreQuery/queryMain'/>";
            jboxLoad("content", url, true);
        }
        //查看考勤信息
        function attendanceSearch(form){
            var url="<s:url value='/jsres/teacher/attendanceSearch/hospital'/>";
            jboxStartLoading();
            jboxPost(url,form,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        //查看考勤信息
        function attendanceTab(form){
            var url="<s:url value='/jsp/jsres/base/attendance.jsp'/>?baseFlag=1";
            jboxLoad("content", url, false);
        }
        function initOrg(cityId,typeId)
        {
            var datas=[];
            <c:forEach items="${orgs}" var="org">
            var d={};
            if(!(cityId!=null&&cityId!="")&&!(typeId!=null&&typeId!=""))
            {
                d.id="${org.orgFlow}";
                d.text="${org.orgName}";
                datas.push(d);
            }else if((cityId!=null&&cityId!="")&&!(typeId!=null&&typeId!="")){
                if("${org.orgCityId}"==cityId)
                {
                    d.id="${org.orgFlow}";
                    d.text="${org.orgName}";
                    datas.push(d);
                }
            }else if(!(cityId!=null&&cityId!="")&&(typeId!=null&&typeId!="")){
                if("${org.orgLevelId}"==typeId)
                {
                    d.id="${org.orgFlow}";
                    d.text="${org.orgName}";
                    datas.push(d);
                }
            }else{
                if("${org.orgLevelId}"==typeId&&"${org.orgCityId}"==cityId)
                {
                    d.id="${org.orgFlow}";
                    d.text="${org.orgName}";
                    datas.push(d);
                }
            }
            </c:forEach>
            var itemSelectFuntion = function(){
                $("#orgFlow2").val(this.id);
                searchInfo();
            };
            $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow2",true);
        }
        //考评指标维护
        <%--function evaluationIndexMaintenance(){--%>
        <%--var url = "<s:url value='/jsres/manage/evaluationIndex'/>";--%>
        <%--jboxLoad("content", url, true);--%>
        <%--}--%>
        //入科教育管理
        function admissionEducationManage(){
            var url = "<s:url value='/jsres/kzr/admissionEduDeptList'/>";
            jboxLoad("content", url, true);
        }
        /*function zlDocQuery(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/main'/>?roleFlag="+roleFlag,true);
        }*/
        /*function zltjOrg(){
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/zltjOrgLocal'/>?roleFlag="+roleFlag,true);
        }*/
        /*function zlxxfb(){
            <%--var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";--%>
            jboxLoad("content","<s:url value='/jsres/message/messageList'/>",true);
        }*/
        /**
         * 结业理论模拟考核
         */
        function graduationExam(){
            var url = "<s:url value='/jsres/graduation/queryMain'/>";
            jboxLoad("content", url, true);
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
                        <a onclick="shouye();"><%=JsresUtil.getTitle(request,response,application)%></a>
                    </h1>
                    <div class="account">
                        <h2 class="head_right">${sessionScope.currUser.orgName }</h2>
                        <div class="head_right">
                            <a onclick="shouye();">首页</a>&#12288;
                            <a href="<s:url value='/inx/jsres/logout'/>">退出</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body">
                <div class="container">
                    <div class="content_side">
                        <input  type="hidden" id="orgFlow3" value='${orgFlow}'/>
                        <dl class="menu menu_first">
                            <dt class="menu_title">
                                <i class="icon_menu menu_function"></i>基地信息管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:hosMain('${sessionScope.currUser.orgFlow}');">基地信息维护</a></dd>
                            <dd class="menu_item" id="evaluation"><a onclick="searchEvaluationInfo();">培训基地评估</a></dd>
                            <dd class="menu_item"><a href="javascript:baseInfo();">科室师资维护</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>培训过程查询
                            </dt>
                            <dd class="menu_item" id="doctorList"><a onclick="doctorList();">学员信息查询</a></dd>
                            <dd class="menu_item"><a onclick="cycle(null);">学员轮转查询</a> </dd>
                            <dd class="menu_item"><a href="javascript:attendanceTab();">学员考勤查询</a></dd>
                            <dd class="menu_item"><a onclick="afterDataDel();">出科数据删除</a></dd>
                            <dd class="menu_item"><a onclick="afterDataBack();">出科数据恢复</a></dd>
                            <dd class="menu_item"><a onclick="attendBack();">退回日常考勤</a></dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_statistics"></i>教学活动管理
                            </dt>
                            <dd class="menu_item"><a href="javascript:doctorActivityStatistics();">学员活动统计</a></dd>

                        </dl>
                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_management"></i>出科信息查询
                            </dt>
                            <dd class="menu_item"><a onclick="cycleResults(null);">学员出科查询</a> </dd>
                            <dd class="menu_item"><a onclick="docWorkSearch();">学员工作量查询</a></dd>
                        </dl>

                        <dl class="menu">
                            <dt class="menu_title">
                                <i class="icon_menu menu_setup"></i>系统设置
                            </dt>
                            <dd class="menu_item"><a href="javascript:accountManage();">学员帐号管理</a></dd>
                        </dl>
                    </div>
                    <div class="col_main" id="content">
                        <div class="content_main">
                            <c:set value="jswjw_${currUser.orgFlow}_P0015" var="orgFlow"/>
                            <c:if test="${sysCfgMap[orgFlow] eq GlobalConstant.RECORD_STATUS_Y}">
                                <div class="index_form" style="margin-bottom: 10px;">
                                    <script>
                                        var okList;
                                        var currFlag = false;
                                        var viewLength = 5;
                                        var likeSearchColumn = 3;

                                        $(function(){
                                            okList = $('#willOutTable .willOutData');
                                            toOperData();
                                        });

                                        function toOperData(){
                                            var docCount = okList.length || 0;
                                            if(docCount>viewLength){
                                                okList.filter(':gt('+(viewLength-1)+')').toggle(currFlag);
                                                $('#s').toggle(!currFlag);
                                                $('#h').toggle(currFlag);
                                                $('#docCount').text(docCount-viewLength);
                                            }else{
                                                $('#s,#h').hide();
                                            }
                                            $('#noData').toggle(docCount==0);
                                        }

                                        function more(flag){
                                            currFlag = flag;
                                            okList.filter(':gt('+(viewLength-1)+')').toggle(currFlag);
                                            $('#s,#h').toggle();
                                        }

                                        function searchMethod(theAttr){
                                            okList = $('#willOutTable .willOutData');
                                            theAttr = $.trim(theAttr);
                                            if(theAttr!=''){
                                                okList.hide();
                                                okList = okList.filter(':has(td:lt('+likeSearchColumn+'):contains("'+theAttr+'"))').show();
                                                toOperData();
                                            }else{
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
                                                    <input type="text" class="input" onkeyup="searchMethod(this.value);" placeholder="轮转科室/姓名/届别"/>
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
                                                <c:set var="status" value="${training.doctorFlow}${training.schDeptFlow}"></c:set>
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
                                            <td id="noData" colspan="7" style="text-align: center;<c:if test="${!empty trainingList}">display: none;</c:if>">无记录!</td>
                                        </tr>

                                        <c:if test="${not empty trainingList}">
                                            <tr id="s" style="display: none;">
                                                <td  colspan="7">
                                                    <a onclick="more(true);">还有<font id="docCount"></font>个学员，点击查看</a>
                                                </td>
                                            </tr>
                                            <tr id="h" style="display: none;">
                                                <td  colspan="7"> <a onclick="more(false);">收起</a></td>
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
                                            <strong><a href="<s:url value='/inx/jsres/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
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
                主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司
            </div>
        </div>

    </div>
</div>
</body>
</html>
