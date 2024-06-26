<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
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


    <script type="text/javascript"
            src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <style type="text/css">
        .table1 {
            border: 0
        }

        .table1 td {
            border: 0
        }

        .doctorTypeDiv {
            border: 0px;
            float: left;
            width: auto;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }

        .doctorTypeLabel {
            border: 0px;
            float: left;
            width: 96px;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }

        .doctorTypeContent {
            border: 0px;
            float: left;
            width: auto;
            line-height: 35px;
            height: 35px;
            text-align: right;
        }
    </style>

    <script type="text/javascript">

        $(document).ready(function () {

            //flag标识 用来区分,只是点击菜单才显示，查询不显示
            <c:if test="${role eq 'professionalBase' and param.flag eq 'Y'}">
            if (${isShow ne 'N'}) {
                jboxOpen("<s:url value='/res/annualCheck/showGuide'/>", "年度考核填写须知", 800, 400);
            }
            </c:if>

            <c:forEach items="${doctorTypeIdList}" var="data">
            $("#" + "${data}").attr("checked", "checked");
            </c:forEach>

        });
        function search() {

            var selectedText = $("select[name='trainingSpeId']").find("option:selected").text();
            $("input[name='trainingSpeName']").val(selectedText);

            if ($("#assessmentYear").val() == '' || $("#assessmentYear").val() == null) {
                jboxTip("请先选择考核年度！");
                return;
            }
            $("#searchForm").submit();
        }
        function toPage(page) {
            if (page) {
                $("#currentPage").val(page);
                search();
            }
        }
        /**
         * 导出年度考核成绩excel
         */
        function recordExport() {
            var assessmentYear = $("#assessmentYear").val();
            if (assessmentYear == '' || assessmentYear == null) {
                jboxTip("请先选择考核年度！");
                return;
            }
            jboxConfirm('确认导出' + assessmentYear + '年度考核成绩?', function () {
                var url = "<s:url value='/res/annualCheck/recordExport?role=${role}'/>";
                jboxSubmit($('#searchForm'), url, null, null, false);
                jboxEndLoading();
            });
        }
        /**
         * 打印年度考核成绩excel
         */
        function printRecord() {
            var assessmentYear = $("#assessmentYear").val();
            if (assessmentYear == '' || assessmentYear == null) {
                jboxTip("请先选择考核年度！");
                return;
            }
            jboxConfirm('确认打印' + assessmentYear + '年度考核成绩?', function () {
                var url = "<s:url value='/res/annualCheck/printRecord?role=${role}'/>";
                jboxSubmit($('#searchForm'), url, null, null, false);
                jboxEndLoading();
            });
        }
        /**
         * 导入年度考核成绩表
         */
        function impExcel() {
            jboxOpen("<s:url value='/jsp/res/professionalBase/impCheckTemp.jsp'/>", "导入", 600, 200);
        }
        /**
         * 保存成绩录入
         * @param flow
         */
        function saveRecord(type,obj,flow) {

//            console.log(type);
            if(type=='dailyFinishScore'){
                //日常考核和出科考核
                var dailyFinishScore = obj.value;
//                console.log("dailyFinishScore"+dailyFinishScore);
                if(null!=dailyFinishScore && dailyFinishScore!=''){
                    if(dailyFinishScore!='5' && dailyFinishScore!='2'){
                        jboxTip("请输入正确的分数，合格给5分，不合格给2分！");
                        return;
                    }
                }
            }


            if(type=='trainingManualScore'){
                //培训手册
                var trainingManualScore= obj.value;
//                console.log("trainingManualScore"+trainingManualScore);
                if(null!=trainingManualScore && trainingManualScore!=''){
                    if(trainingManualScore!='5' && trainingManualScore!='2'){
                        jboxTip("请输入正确的分数，合格给5分，不合格给2分！");
                        return;
                    }
                }
            }


            if(type=='professionalTheoryScore'){
                //专业理论成绩
                var professionalTheoryScore = obj.value;
//                console.log("professionalTheoryScore"+professionalTheoryScore);
                if(null!=professionalTheoryScore && professionalTheoryScore!=''){
                    if(professionalTheoryScore<0 || professionalTheoryScore>100){
                        jboxTip("请输入正确的分数，满分为100分！");
                        return;
                    }
                }
            }

           if(type=='skillScore'){
               //技能考核成绩
               var skillScore = obj.value;
//            console.log("skillScore"+skillScore);
               if(null!=skillScore && skillScore!=''){
                   if(skillScore<0 || skillScore>100){
                       jboxTip("请输入正确的分数，满分为100分！");
                       return;
                   }
               }
           }


            if(type=='publicSkillsScore'){
                //公共技能考核成绩
                var publicSkillsScore = obj.value;
//            console.log("publicSkillsScore"+publicSkillsScore);
                if(null!=publicSkillsScore && publicSkillsScore!=''){
                    if(publicSkillsScore<0 || publicSkillsScore>100){
                        jboxTip("请输入正确的分数，满分为100分！");
                        return;
                    }
                }
            }


            var form = $('<form></form>');
            form.attr('id', 'recordForm' + flow);

            var annualAssessmentFlow_input = $('<input type="hidden" name="annualAssessmentFlow" />');
            annualAssessmentFlow_input.attr('value', $("#" + flow + "annualAssessmentFlow").val());
            form.append(annualAssessmentFlow_input);

            var userName_input = $('<input type="hidden" name="userName" />');
            userName_input.attr('value', $("#" + flow + "userName").val());
            form.append(userName_input);

            var userFlow_input = $('<input type="hidden" name="userFlow" />');
            userFlow_input.attr('value', $("#" + flow + "userFlow").val());
            form.append(userFlow_input);

            var materialUrl_input = $('<input type="hidden" name="materialUrl" />');
            materialUrl_input.attr('value', $("#" + flow + "materialUrl").val());
            form.append(materialUrl_input);

            var dailyFinishScore_input = $('<input type="hidden" name="dailyFinishScore" />');
            dailyFinishScore_input.attr('value', $("#" + flow + "dailyFinishScore").val());
//            dailyFinishScore_input.attr('class', "validate[custom[number],max[100]]");
            form.append(dailyFinishScore_input);

//            console.log($("#" + flow + "dailyFinishScore").val());

            var trainingManualScore_input = $('<input type="hidden" name="trainingManualScore" />');
            trainingManualScore_input.attr('value', $("#" + flow + "trainingManualScore").val());
//            trainingManualScore_input.attr('class', "validate[custom[number],max[100]]");
            form.append(trainingManualScore_input);

//

            var professionalTheoryScore_input = $('<input type="hidden" name="professionalTheoryScore" />');
            professionalTheoryScore_input.attr('value', $("#" + flow + "professionalTheoryScore").val());
//            professionalTheoryScore_input.attr('class', "validate[custom[number],max[100]]");
            form.append(professionalTheoryScore_input);



            var skillName_input = $('<input type="hidden" name="skillName" />');
            skillName_input.attr('value', $("#" + flow + "skillName").val());
            form.append(skillName_input);

            var skillScore_input = $('<input type="hidden" name="skillScore" />');
            skillScore_input.attr('value', $("#" + flow + "skillScore").val());
//            skillScore_input.attr('class', "validate[custom[number],max[100]]");
            form.append(skillScore_input);



            var publicSkillsScore_input = $('<input type="hidden" name="publicSkillsScore" />');
            publicSkillsScore_input.attr('value', $("#" + flow + "publicSkillsScore").val());
//            publicSkillsScore_input.attr('class', "validate[custom[number],max[100]]");
            form.append(publicSkillsScore_input);


            //总成绩计算公式：
            //日常考核及出科考核分数+培训手册填写分数+专业理论成绩分数*40%+技能考核成绩分数*40%+公共技能成绩分数*10%

            var totalScore = 0.0;
            if(null!=$("#" + flow + "dailyFinishScore").val() && $("#" + flow + "dailyFinishScore").val()!=''){
                totalScore+=parseFloat($("#" + flow + "dailyFinishScore").val());
            }
            if(null!=$("#" + flow + "trainingManualScore").val() && $("#" + flow + "trainingManualScore").val()!=''){
                totalScore+=parseFloat($("#" + flow + "trainingManualScore").val());
            }
            if(null!=$("#" + flow + "professionalTheoryScore").val() && $("#" + flow + "professionalTheoryScore").val()!=''){
                totalScore+=parseFloat($("#" + flow + "professionalTheoryScore").val()*0.4);
            }
            if(null!=$("#" + flow + "skillScore").val() && $("#" + flow + "skillScore").val()!=''){
                totalScore+= parseFloat($("#" + flow + "skillScore").val()*0.4);
            }
           if(null!=$("#" + flow + "publicSkillsScore").val() && $("#" + flow + "publicSkillsScore").val()!=''){
               totalScore+=parseFloat($("#" + flow + "publicSkillsScore").val()*0.1);
           }

//            console.log(totalScore);

//            var approvedTotalScore = $("#" + flow + "approvedTotalScore").val(totalScore);
//            console.log(approvedTotalScore);
            var approvedTotalScore_input = $('<input type="hidden" name="approvedTotalScore" />');
            approvedTotalScore_input.attr('value', totalScore);
//            approvedTotalScore_input.attr('class', "validate[custom[number],max[100]]");
            form.append(approvedTotalScore_input);

            var memo_input = $('<input type="hidden" name="memo" />');
            memo_input.attr('value', $("#" + flow + "memo").val());
            form.append(memo_input);

            //为了兼容Chrome，用$('<form></form>')创建必须加上以下这句
            $(document.body).append(form);


            var url = "<s:url value='/res/annualCheck/saveAnnualCheck'/>";
            jboxPost(url, $("#recordForm" + flow).serialize(), function (resp) {
                search();
                jboxTip('操作成功！');
            }, function () {
                jboxTip('操作失败！');
            }, false);
        }
        //        /**
        //         *模糊查询加载
        //         */
        //        (function($){
        //            $.fn.likeSearchInit = function(option){
        //                option.clickActive = option.clickActive || null;
        //
        //                var searchInput = this;
        //                searchInput.on("keyup focus",function(){
        //                    $("#boxHome").show();
        //                    if($.trim(this.value)){
        //                        $("#boxHome .item").hide();
        //                        var items = $("#boxHome .item[value*='"+this.value+"']").show();
        //                        if(!items){
        //                            $("#boxHome").hide();
        //                        }
        //                    }else{
        //                        $("#boxHome .item").show();
        //                    }
        //                });
        //                searchInput.on("blur",function(){
        //                    if(!$("#boxHome.on").length){
        //                        $("#boxHome").hide();
        //                    }
        //                });
        //                $("#boxHome").on("mouseenter mouseleave",function(){
        //                    $(this).toggleClass("on");
        //                });
        //                $("#boxHome .item").click(function(){
        //                    $("#boxHome").hide();
        //                    var value = $(this).attr("value");
        //                    $("#itemName").val(value);
        //                    searchInput.val(value);
        //                    if(option.clickActive){
        //                        option['clickActive']($(this).attr("flow"));
        //                    }
        //                });
        //            };
        //        })(jQuery);
        //        //页面加载完成时调用
        //        $(function(){
        //            $("#speSel").likeSearchInit({
        //
        //            });
        //        });

        function changeFile(obj, flow) {
            $("#" + flow + "File").click();
//            var fileObj=$(obj).next();
//            $(fileObj).click();
        }

        /**
         * 控制单选
         */
        function singleCheck(box) {
            var curr = box.checked;
            if (curr) {
                var name = box.name;
                $(":checkbox[name='" + name + "']").attr("checked", false);
            }
            box.checked = curr;
        }

        /**
         * 上传文件
         * @param obj
         * @param
         * @param
         */
        function uploadFile(obj, flow) {

            var fileName = $(obj).val();
            if (fileName == "") {
                jboxTip("请选择文件！");
                return;
            }

            var types = "${sysCfgMap['sys_file_support_suffix']}".split(",");
            var regStr = "/";
            for (var i = 0; i < types.length; i++) {
                if (types[i]) {
                    if (i == (types.length - 1)) {
                        regStr = regStr + "\\" + types[i] + '$';
                    } else {
                        regStr = regStr + "\\" + types[i] + '$|';
                    }
                }
            }
            regStr = regStr + '/i';
            regStr = eval(regStr);
            if ($.trim(fileName) != "" && !regStr.test(fileName)) {
//                obj.value = "";
                jboxTip("请上传&nbsp;${sysCfgMap['sys_file_support_suffix']}格式的文件");
                return;
            }
            var index = fileName.lastIndexOf("\\") + 1
            fileName = fileName.substring(index);

            jboxStartLoading();
            var url = "<s:url value='/res/annualCheck/uploadFile?userFlow='/>" + flow;

            jboxSubmit($(obj).parent(), url, function (resp) {
                if ("${GlobalConstant.UPLOAD_FAIL}" != resp) {
                    jboxEndLoading();
                    var index = resp.indexOf("/");
                    search();
                    if (index != -1) {
//                        console.log(resp)
//                        $("#"+flow+"materialUrl").val(resp);
                    } else {
                        jboxInfo(resp);
                    }
                }
            }, null, false);
        }

        /**
         * 删除文件
         */
        function delFile(annualAssessmentFlow, materialUrl) {
            jboxConfirm('确认删除?', function () {
                var url = "<s:url value='/res/annualCheck/delFile?annualAssessmentFlow='/>" + annualAssessmentFlow + "&materialUrl=" + materialUrl;
                jboxPost(url, null, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        jboxTip(resp);
                        search();
//
                    }
                }, null, false);
            });

        }
    </script>

</head>
<body>
<div class="mainright">
    <div class="content">
        <div class=" clearfix">
            <div class="queryDiv">
                <form id="searchForm" action="<s:url value='/res/annualCheck/getAnnualCheck?roleFlag=${role}'/>" method="post">
                    <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>

                    <div class="inputDiv"
                         <c:if test="${role eq 'doctor'}">style="display: none"</c:if> >
                        姓&#12288;&#12288;名：
                        <input type="text" name="doctorName" class="qtext" value="${param.doctorName}"/>
                    </div>
                    <div class="inputDiv" <c:if test="${role eq 'doctor'}">style="display: none"</c:if>>
                        培训专业：
                        <select name="trainingSpeId" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="num">
                                <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <input type="hidden" name="trainingSpeName" value="${param.trainingSpeName}"/>
                        </select>
                    </div>
                    <div class="inputDiv" <c:if test="${role eq 'doctor'}">style="display: none"</c:if>>
                        入培时间：
                        <select name="sessionNumber" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
                                <option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="inputDiv"
                         <c:if test="${role eq 'doctor' or role eq 'head' or role eq 'secretary'}">style="display: none"</c:if>>
                        所在科室：
                        <%--<input id="speSel" style="width: 146px" class="toggleView qtext" type="text" name="deptName" value="${param.deptName}" autocomplete="off" title="${param.deptName}" onmouseover="this.title = this.value"/>--%>
                        <input type="text" name="deptName" class="qtext" value="${param.deptName}">
                        <%--<d iv style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:95px;">--%>
                        <%--<div id="boxHome" style="max-height: 152px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 152px;border-top: none;position: relative;display: none;">--%>
                        <%--<c:forEach items="${deptList}" var="SysDept">--%>
                        <%--<p class="item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;"}>${SysDept.deptName}</p>--%>
                        <%--</c:forEach>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <div class="inputDiv" <c:if test="${role eq 'doctor'}">style="display: none"</c:if>>
                        结业时间：
                        <input class="qtext" type="text" name="graduationYear" value="${param.graduationYear}"
                               onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
                    </div>
                    <div class="inputDiv" <c:if test="${role eq 'doctor'}">style="display: none"</c:if>>
                        培训年限：
                        <select name="trainingYears" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict" varStatus="num">
                                <option value="${dict.dictName}" ${param.trainingYears eq dict.dictName?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        考核年度：
                        <input class="qtext" type="text" id="assessmentYear" name="assessmentRecord.assessmentYear"
                               value="${assessmentYear}" <%--onclick="WdatePicker({dateFmt:'yyyy'})"--%> readonly="readonly">
                    </div>
                    <div class="doctorTypeDiv" <c:if test="${role eq 'doctor'}">style="display: none"</c:if>>
                        <div class="doctorTypeLabel">学员类型：</div>
                        <div class="doctorTypeContent">
                            <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
                                <label  <c:if test="${type.dictId eq 'ExternalEntrust'}">style="display: none" </c:if>>
                                    <input type="checkbox" name="doctorTypeList" id="${type.dictId}"
                                        <%--<c:if test="${empty param.doctorTypeList}">checked</c:if>--%>
                                           value="${type.dictId}">${type.dictName}
                                </label>
                            </c:forEach>

                        </div>
                    </div>
                    <%-- <c:if test="${role eq 'head' or role eq 'secretary'}">
                         <div class="inputDiv">
                             <label>
                                 <input type="checkbox" name="type" value="inner" <c:if test="${param.type eq 'inner'}">checked</c:if> onchange="singleCheck(this)"/>本科室学员
                                 <input type="checkbox" name="type" value="sch"   <c:if test="${param.type eq 'sch'}">checked</c:if>   onchange="singleCheck(this)"/>轮转科室学员
                             </label>
                         </div>

                     </c:if>--%>
                    <div class="lastDiv">
                        &#12288;<input class="search" type="button" value="查&#12288;询" onclick="search();"/>
                    </div>
                    <c:if test="${role eq 'professionalBase'}">
                        <div class="lastDiv">
                            &#12288;<input class="search" type="button" value="导&#12288;入" onclick="impExcel()"/>
                        </div>
                    </c:if>
                    <div class="lastDiv">
                        &#12288;<input class="search" type="button" value="导&#12288;出" onclick="recordExport()"/>
                    </div>
                    <c:if test="${role eq 'professionalBase'}">
                        <div class="lastDiv">
                            &#12288;<input class="search" type="button" value="打&#12288;印" onclick="printRecord()"/>
                        </div>
                    </c:if>
                </form>
            </div>
            <table class="xllist">
                <tr>
                    <th style="text-align: center;min-width: 30px;max-width: 30px">序号</th>
                    <th style="text-align: center;min-width: 60px;max-width: 60px">姓名</th>
                    <th style="text-align: center;min-width: 180px;max-width: 180px">培训专业</th>
                    <th style="text-align: center;min-width: 140px;max-width: 140px">身份证号</th>
                    <th style="text-align: center;min-width: 52px;max-width: 52px">入培时间</th>
                    <th style="text-align: center;min-width: 52px;max-width: 52px">结业时间</th>
                    <th style="text-align: center;min-width: 66px;max-width: 66px">日常考核和出科考核</th>
                    <th style="text-align: center;min-width: 40px;max-width: 40px">培训手册填写</th>
                    <th style="text-align: center;min-width: 40px;max-width: 40px">专业理论成绩</th>
                    <th style="text-align: center;min-width: 100px;max-width: 100px">技能考核名称</th>
                    <th style="text-align: center;min-width: 40px;max-width: 40px">技能成绩</th>
                    <th style="text-align: center;min-width: 40px;max-width: 40px">公共技能成绩</th>
                    <th style="text-align: center;min-width: 50px;max-width: 50px">核定总成绩</th>
                    <th style="text-align: center;min-width: 100px;max-width: 100px">年度考核材料</th>
                    <th style="text-align: center;min-width: 100px;max-width: 100px">备注</th>
                </tr>

                <c:forEach items="${resDoctorList}" var="doctor" varStatus="status">
                    <%--<form id="recordForm${doctor.sysUser.userFlow}" enctype="multipart/form-data">--%>
                    <input type="hidden"
                           id="${doctor.sysUser.userFlow}annualAssessmentFlow" <%--name="annualAssessmentFlow"--%>
                           value="${doctor.assessmentRecord.annualAssessmentFlow}">
                    <input type="hidden" id="${doctor.sysUser.userFlow}userName" <%--name="userName"--%>
                           value="${doctor.sysUser.userName}">
                    <input type="hidden" id="${doctor.sysUser.userFlow}userFlow" <%--name="userFlow"--%>
                           value="${doctor.sysUser.userFlow}">
                    <input type="hidden" id="${doctor.sysUser.userFlow}materialUrl" <%--name="materialUrl"--%>
                           value="${doctor.assessmentRecord.materialUrl}">
                    <tr>
                        <td>${status.count}</td>
                        <td>${doctor.sysUser.userName}</td>
                        <td>${doctor.trainingSpeName}</td>
                        <td>${doctor.sysUser.idNo}</td>
                        <td>${doctor.sessionNumber}</td>
                        <td>${doctor.graduationYear}</td>
                        <td>
                            <c:if test="${role ne 'professionalBase'}">${doctor.assessmentRecord.dailyFinishScore}</c:if>
                            <input
                                    style="width: 50px;text-align: right;<c:if
                                            test="${role ne 'professionalBase'}">display: none;</c:if>" type="text"
                                    id="${doctor.sysUser.userFlow}dailyFinishScore" <%--name="dailyFinishScore"--%>
                                    value="${doctor.assessmentRecord.dailyFinishScore}"
                                    onchange="saveRecord('dailyFinishScore',this,'${doctor.sysUser.userFlow}')"/></td>
                        <td>
                            <c:if test="${role ne 'professionalBase'}">${doctor.assessmentRecord.trainingManualScore}</c:if>
                            <input
                                    style="width: 30px;text-align: right;<c:if
                                            test="${role ne 'professionalBase'}">display: none;</c:if>" type="text"
                                    id="${doctor.sysUser.userFlow}trainingManualScore" <%--name="trainingManualScore"--%>
                                    value="${doctor.assessmentRecord.trainingManualScore}"
                                    onchange="saveRecord('trainingManualScore',this,'${doctor.sysUser.userFlow}')"/></td>
                        <td>
                            <c:if test="${role ne 'professionalBase'}">${doctor.assessmentRecord.professionalTheoryScore}</c:if>
                            <input
                                    style="width: 30px;text-align: right;<c:if
                                            test="${role ne 'professionalBase'}">display: none;</c:if>" type="text"
                                    id="${doctor.sysUser.userFlow}professionalTheoryScore" <%--name="professionalTheoryScore"--%>
                                    value="${doctor.assessmentRecord.professionalTheoryScore}"
                                    onchange="saveRecord('professionalTheoryScore',this,'${doctor.sysUser.userFlow}')"/></td>
                        <td>
                            <c:if test="${role ne 'professionalBase'}">${doctor.assessmentRecord.skillName}</c:if>
                            <input
                                    style="width: 90px;<c:if
                                            test="${role ne 'professionalBase'}">display: none;</c:if>" type="text"
                                    id="${doctor.sysUser.userFlow}skillName" <%--name="skillName"--%>
                                    value="${doctor.assessmentRecord.skillName}"
                                    onchange="saveRecord('skillName',this,'${doctor.sysUser.userFlow}')"/></td>
                        <td>
                            <c:if test="${role ne 'professionalBase'}">${doctor.assessmentRecord.skillScore}</c:if>
                            <input
                                    style="width: 30px;text-align: right;<c:if
                                            test="${role ne 'professionalBase'}">display: none;</c:if>" type="text"
                                    id="${doctor.sysUser.userFlow}skillScore" <%--name="skillScore"--%>
                                    value="${doctor.assessmentRecord.skillScore}"
                                    onchange="saveRecord('skillScore',this,'${doctor.sysUser.userFlow}')"/></td>
                        <td>
                            <c:if test="${role ne 'professionalBase'}">${doctor.assessmentRecord.publicSkillsScore}</c:if>
                            <input
                                    style="width: 30px;text-align: right;<c:if
                                            test="${role ne 'professionalBase'}">display: none;</c:if>" type="text"
                                    id="${doctor.sysUser.userFlow}publicSkillsScore" <%--name="publicSkillsScore"--%>
                                    value="${doctor.assessmentRecord.publicSkillsScore}"
                                    onchange="saveRecord('publicSkillsScore',this,'${doctor.sysUser.userFlow}')"/></td>
                        <td>
                            <%--<c:if test="${role ne 'professionalBase'}">--%>
                            ${doctor.assessmentRecord.approvedTotalScore}
                            <%--</c:if>--%>
                            <%--<input--%>
                                    <%--style="width: 40px;margin-right: 10px;text-align: right;<c:if--%>
                                            <%--test="${role ne 'professionalBase'}">display: none;</c:if>" type="text"--%>
                                    <%--id="${doctor.sysUser.userFlow}approvedTotalScore" &lt;%&ndash;name="approvedTotalScore"&ndash;%&gt;--%>
                                    <%--value="${doctor.assessmentRecord.approvedTotalScore}"--%>
                                    <%--&lt;%&ndash;onchange="saveRecord('approvedTotalScore',this,'${doctor.sysUser.userFlow}')"&ndash;%&gt;/></td>--%>
                        <td>
                            <a id="${doctor.sysUser.userFlow}down"
                               style="width:80px;color: #00a0ff;line-height:26px;display: ${empty doctor.assessmentRecord.materialUrl ? 'none':'block'}"
                               href="${sysCfgMap['upload_base_url']}/${doctor.assessmentRecord.materialUrl}">年度考核材料</a>
                            <c:if test="${role eq 'professionalBase'}">
                            <a style="color: #00a0ff;width:80px;display: ${empty  doctor.assessmentRecord.materialUrl ? 'none':'block'}"
                               href="javascript:void(0)"
                               onclick="delFile('${doctor.assessmentRecord.annualAssessmentFlow}','${doctor.assessmentRecord.materialUrl}');">删除</a>
                            <a
                                    style="display: block;width:80px;color: #00a0ff;line-height:26px;"
                                    href="javascript:void(0);"
                                    onclick="changeFile(this,'${doctor.sysUser.userFlow}')">${empty doctor.assessmentRecord.materialUrl?"":"重新"}上传</a>
                            </c:if>
                            <form id="${doctor.sysUser.userFlow}Form"
                                  style="display: ${empty doctor.assessmentRecord.materialUrl ? '':'none'}"
                                  enctype="multipart/form-data" method="post">
                                <input type="file" style="display: none;" id="${doctor.sysUser.userFlow}File"
                                       class="validate[required]" name="file"
                                       onchange="uploadFile(this,'${doctor.sysUser.userFlow}');"/>
                            </form>
                        </td>
                        <td>
                            <c:if test="${role ne 'professionalBase'}">${doctor.assessmentRecord.memo}</c:if>
                            <input
                                    style="<c:if
                                            test="${role ne 'professionalBase'}">display: none;</c:if>" type="text"
                                    id="${doctor.sysUser.userFlow}memo" <%--name="memo"--%>
                                    value="${doctor.assessmentRecord.memo}"
                                    onchange="saveRecord('memo',this,'${doctor.sysUser.userFlow}')"/></td>
                    </tr>
                    <%--</form>--%>
                </c:forEach>

                <c:if test="${empty resDoctorList}">
                    <tr>
                        <td colspan="20" style="text-align: center">无记录！</td>
                    </tr>
                </c:if>
            </table>
            <c:if test="${role eq 'professionalBase'}">
                <div>
                    <font color="red">*</font>
                    说明：考核项目内容按照各专业实际考核内容填写。
                </div>
            </c:if>

            <div class="page" style="padding-right: 40px;">
                <c:set var="pageView" value="${pdfn:getPageView(resDoctorList)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </div>

        </div>
    </div>
</div>
</body>
</html>
