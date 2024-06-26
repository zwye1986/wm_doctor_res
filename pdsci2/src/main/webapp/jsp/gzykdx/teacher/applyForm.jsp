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
    <script src="<s:url value='/js/jquery.media.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/pdfobject.js'/>" type="text/javascript"></script>
    <script type="text/javascript"
            src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(function(){
            $(':checkbox[name=degreeType]').each(function(){
                $(this).click(function(){
                    if($(this).attr('checked')){
                        $(':checkbox[name=degreeType]').removeAttr('checked');
                        $(this).attr('checked','checked');
                    }
                });
            });

        });
        function saveThe() {
            if (false == $("#srmThesis").validationEngine("validate")) {
                return false;
            }
            if (false == $("#list").validationEngine("validate")) {
                return false;
            }
            var checkLen = $(":checkbox[name=degreeType]:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选招生类型！");
                return false;
            }
            var applyFlow = $("input[name='applyFlow']").val();
            var userName = $("input[name='userName']").val();
            var userBirthday = $("input[name='userBirthday']").val();
            var age = $("input[name='age']").val();
            var idNo = $("input[name='idNo']").val();
            var userPhone = $("input[name='userPhone']").val();
            var workAreaYear = $("input[name='workAreaYear']").val();
            var workDate = $("input[name='workDate']").val();
            var orgFlow = $("select[name='orgFlow']").val();
            var orgName=$("select[name='orgFlow']").find("option:selected").attr("orgName");
            orgName=encodeURI(encodeURI(orgName));
            var sexId = $("select[name='sexId']").val();
            var educationId = $("select[name='educationId']").val();
            var degreeId = $("select[name='degreeId']").val();
            var titleId = $("select[name='titleId']").val();
            var speId = $("select[name='speId']").val();
            var researchDirection = $("input[name='researchDirection']").val();
            var isAcademic=$('input[id="isAcademic"]:checked').val();
            var isSpecialized=$('input[id="isSpecialized"]:checked').val();
            var educationSchool = $("input[name='educationSchool']").val();
            var educationDate = $("input[name='educationDate']").val();
            var degreeOrg = $("input[name='degreeOrg']").val();
            var degreeDate = $("input[name='degreeDate']").val();
            var thesisRecordList = $("input[name='thesisRecordList']").val();
//            var val=$('input:radio[name="applyType"]:checked').val();
//            if(val=='isAcademic'){
//                var isAcademic='Y';
//            }
//            if(val=='isSpecialized'){
//                var isSpecialized='Y';
//            }
            var applyInfo = {
                "applyFlow": applyFlow,
                "userName": userName,
                "userBirthday": userBirthday,
                "age": age,
                "idNo": idNo,
                "userPhone": userPhone,
                "workAreaYear": workAreaYear,
                "workDate": workDate,
                "orgFlow": orgFlow,
                "orgName": orgName,
                "sexId": sexId,
                "educationId": educationId,
                "educationSchool": educationSchool,
                "educationDate": educationDate,
                "degreeId": degreeId,
                "degreeOrg": degreeOrg,
                "degreeDate": degreeDate,
                "titleId": titleId,
                "speId": speId,
                "researchDirection": researchDirection,
                "isAcademic": isAcademic,
                "isSpecialized": isSpecialized,
                "thesisRecordList": thesisRecordList
            };
            var authTab1 = $('#authorList1');
            var trs1 = authTab1.children();
            var authTab2 = $('#authorList2');
            var trs2 = authTab2.children();
            var authTab3 = $('#authorList3');
            var trs3 = authTab3.children();
            var datas1 = [];
            var datas2 = [];
            var datas3 = [];
            $.each(trs1, function (i, n) {
                var recordFlow = $(n).find("input[name='recordFlow']").val();
                var thesisName = $(n).find("input[name='thesisName']").val();
                var thesisTitle =  $(n).find("input[name='thesisTitle']").val();
                var authorRankingName =  $(n).find("input[name='authorRankingName']").val();
//                var authorRankingId = $(n).find("select[name='authorRankingId']").val();
                var reportTime = $(n).find("input[name='reportTime']").val();
                var sciPoint = $(n).find("input[name='sciPoint']").val();
                var fileFlow = $(n).find("input[name='fileFlow1']").val();
                var achFile = $("#srmAchFile1").serializeJson();
                var data = {
                    "thesisName": thesisName,
                    "recordFlow": recordFlow,
                    "thesisTitle": thesisTitle,
                    "authorRankingName": authorRankingName,
                    "reportTime": reportTime,
                    "sciPoint": sciPoint,
                    "fileFlow": fileFlow,
                    "srmAchFile":achFile
                };
                datas1.push(data);
            });
            $.each(trs2, function (i, n) {
                var recordFlow = $(n).find("input[name='recordFlow']").val();
                var thesisName = $(n).find("input[name='thesisName']").val();
                var thesisTitle =  $(n).find("input[name='thesisTitle']").val();
                var authorRankingName =  $(n).find("input[name='authorRankingName']").val();
//                var authorRankingId = $(n).find("select[name='authorRankingId']").val();
                var reportTime = $(n).find("input[name='reportTime']").val();
                var sciPoint = $(n).find("input[name='sciPoint']").val();
                var fileFlow = $(n).find("input[name='fileFlow2']").val();
                var achFile = $("#srmAchFile2").serializeJson();
                var data = {
                    "thesisName": thesisName,
                    "recordFlow": recordFlow,
                    "thesisTitle": thesisTitle,
                    "authorRankingName": authorRankingName,
                    "reportTime": reportTime,
                    "sciPoint": sciPoint,
                    "fileFlow": fileFlow,
                    "srmAchFile":achFile
                };
                datas2.push(data);
            });
            $.each(trs3, function (i, n) {
                var recordFlow = $(n).find("input[name='recordFlow']").val();
                var thesisName = $(n).find("input[name='thesisName']").val();
                var reportTime =  $(n).find("input[name='reportTime']").val();
                var authorRankingName =  $(n).find("input[name='authorRankingName']").val();
//                var authorRankingId = $(n).find("select[name='authorRankingId']").val();
                var productWorkName = $(n).find("input[name='productWorkName']").val();
                var productCode = $(n).find("input[name='productCode']").val();
                var productAmount = $(n).find("input[name='productAmount']").val();
                var fileFlow = $(n).find("input[name='fileFlow3']").val();
                var achFile = $("#srmAchFile3").serializeJson();
                var data = {
                    "recordFlow": recordFlow,
                    "thesisName": thesisName,
                    "reportTime": reportTime,
                    "authorRankingName": authorRankingName,
                    "productWorkName": productWorkName,
                    "productCode": productCode,
                    "productAmount": productAmount,
                    "fileFlow": fileFlow,
                    "srmAchFile":achFile
                };
                datas3.push(data);
            });
            var t = {'thesisList1': datas1,'thesisList2': datas2,'thesisList3': datas3, 'applyInfo': applyInfo};
            $('#jsondata').val(JSON.stringify(t));
            var url = "<s:url value='/gzykdx/teaAndDoc/saveApply'/>";
            jboxSubmit($('#srmThesis'), url, function (resp) {
                        jboxTip(resp);
                        if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                            setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 2000);
                        }
                    },
                    null, false);
//            jboxEndLoading();
//            return true;
        }
//        function saveThe() {
//            if (saveThesis()) {
//                jboxTip("保存成功");
//                setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 2000)
//            }
//        }

        function change(obj) {
            $(".a").attr("disabled", true);
            if (obj.value == "2") {
                $(".a").attr("disabled", false);
            }
        }

        function reCheck(obj,flow) {
            $(obj).hide();
            $("#down_"+flow).hide();
            $("#file_"+flow).show();
            $("#fileFlow_"+flow).val("");
        }

        $(function () {
            <c:if test="${scope ne 'teacher' or detailFlag eq 'Y'}">
            $('#applyInfoDiv input').attr('disabled', "disabled");
            $('#applyInfoDiv img').attr('readonly', "readonly");
            $('#applyInfoDiv select').attr('disabled', "disabled");
//            $("#reCheck").css("display", "none");
            </c:if>
        });

        function audit(auditId){
            var applyFlow = $("input[name='applyFlow']").val();
            var orgAuditAdvice = $("textarea[name='orgAuditAdvice']").val();
            var schoolAuditAdvice = $("textarea[name='schoolAuditAdvice']").val();
            orgAuditAdvice=encodeURI(encodeURI(orgAuditAdvice));
            schoolAuditAdvice=encodeURI(encodeURI(schoolAuditAdvice));
            var scope='${scope}';
            var msg='通过';
            var url = "<s:url value='/gzykdx/orgAudit/auditApply'/>?applyFlow="+applyFlow+"&scope="+scope+"&auditId="+auditId+"&orgAuditAdvice="+orgAuditAdvice+"&schoolAuditAdvice="+schoolAuditAdvice;
            if(auditId == 'UnPassed'){
                msg='不通过';
            }
            if(auditId == 'SendBack'){
                msg='退回';
            }
            jboxConfirm("确认"+msg+"审核吗？",function() {
                jboxPost(url, null, function (resp) {
                    var page = '${currentPage}';
                    jboxTip(resp);
                    setTimeout(function(){
                        window.parent.frames['mainIframe'].window.toPage(page);
                        jboxClose();
                    },1000);
                }, null, false);
            });
        }

        function closeEditPage(dates) {
            dg.close().remove();
            $.each(dates, function (i, n) {
                // alert(n.userName);
                var tr = $("#moban tr:eq(0)").clone();
                $(tr).find("input[name='authorName']").val(n.userName);
                //alert($(tr).find("input[name='authorName']").val());
                $(tr).find("select[name='sexId']").val(n.sexId);
                $(tr).find("select[name='titleId']").val(n.titleId);
                $(tr).find("select[name='degreeId']").val(n.degreeId);
                $(tr).find("select[name='educationId']").val(n.educationId);
                $(tr).find("input[name='userFlow']").val(n.userFlow);
                $(tr).find("input[name='deptFlow']").val(n.deptFlow);
                $(tr).find("input[name='deptName']").val(n.deptName);
                $(tr).find("input[name='branchIdAuthor']").val(n.branchId);
                $(tr).find("select[name='scoreFlow']").val(n.scoreFlow);
                $(tr).find("input[name='scoreName']").val(n.scoreName);
                // alert( $(tr).find("input[name='deptName']").val());
                $('#authorList').append(tr);
            });

        }

        function addAuthor1(){
            $('#authorList1').append($("#moban1 tr:eq(0)").clone());
        }
        function addAuthor2(){
            $('#authorList2').append($("#moban2 tr:eq(0)").clone());
        }
        function addAuthor3(){
            $('#authorList3').append($("#moban3 tr:eq(0)").clone());
        }

        function selSpe(speId){
            var str=speId.substring(0,3);
            if(str=="105"){
                $("input[name='degreeType']").eq(1).attr("checked","checked");
                $("input[name='degreeType']").eq(0).removeAttr("checked");
            }else{
                $("input[name='degreeType']").eq(0).attr("checked","checked");
                $("input[name='degreeType']").eq(1).removeAttr("checked");
            }
        }

        function checkUser(obj,type){

            var userCode ="";
            var idNo ="";
            if(type=='idNo'){
                idNo = $("#idNo").val();
            }
            var url = "<s:url value='/gzykdx/teaAndDoc/checkTeacherRegist'/>?userCode="+userCode+"&idNo="+idNo;
            jboxPost(url,null,function(resp){
                jboxEndLoading();
                if(resp != "NULL"){
                    jboxTip(resp);
                    $(obj).val("${user.idNo}");
                    return false;
                }
            }, null, false);
        }

        function preview(path,fileFlow){
            var ext=path.split('.')[1];
            if(ext=="png"||ext=="PNG"||ext=="jpeg"||ext=="jpg"||ext=="JPEG"||ext=="JPG"){
                console.log(path);
                window.open("${sysCfgMap['upload_base_url']}"+path);
            }else if(ext=="pdf"){
                var url1 = "<s:url value="${sysCfgMap['upload_base_url']}"/>"+path;
                $("#exportA3").attr("href",url1);
                $("#outToExcelSpan3").click();
            }else {
                var url2 = "<s:url value="/gzykdx/teaAndDoc/fileDown?fileFlow="/>"+fileFlow;
                $("#exportA2").attr("href",url2);
                $("#outToExcelSpan2").click();
            }
        }

        function delThesis(obj,recordFlow){
            if($("#thesisRecordList").val()!=""){
                var recordArray = $("#thesisRecordList").val().split(",");
            }else {
                var recordArray = new Array();
            }
            if(recordFlow!=""||recordFlow!=null){
                recordArray.push(recordFlow);
                $("#thesisRecordList").val(recordArray);
            }
            $(obj).parent("td").parent("tr").remove();
        }
    </script>
</head>

<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div id="look">
                <a class="btn" id="exportA3" hidden="hidden" target="_blank"><span id="outToExcelSpan3"> </span></a>
                <a class="btn" id="exportA2" hidden="hidden"><span id="outToExcelSpan2"> </span></a>
                <form id="srmThesis"  method="post" action=""
                      style="position: relative;" enctype="multipart/form-data">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <c:if test="${(param.editFlag != GlobalConstant.FLAG_N) and (param.auditFlag != 'audit')}">
                        <input type="hidden" name="userFlow" value="${user.userFlow}"/>
                        <input type="hidden" name="applyFlow" value="${targetApply.applyFlow}"/>
                    </c:if>
                    <div id="applyInfoDiv">
                        <table class="basic" style="width: 100%">
                            <tr>
                                <th>所在单位
                                </th>
                                <td colspan="7">
                                    <select name="orgFlow" class="xlselect" style="width:306px;">
                                        <option value="">请选择</option>
                                        <c:forEach items="${orgList}" var="org">
                                            <option <c:if test="${user.orgFlow eq org.orgFlow }">selected="selected"</c:if>
                                                    value="${org.orgFlow}" orgName="${org.orgName}">
                                                    ${org.orgName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <th>姓名
                                </th>
                                <td>
                                    <input class="validate[required] xltext" type="text" name="userName" value="${user.userName}" style="width: 120px;" readonly="readonly"/>
                                </td>
                                <th>性别
                                </th>
                                <td>
                                    <select name="sexId" class="validate[required] xlselect" type="text" style="width: 80px;" disabled="disabled">
                                        <option value="Man" <c:if test="${user.sexId eq 'Man' }">selected="selected"</c:if>>
                                            男</option>
                                        <option value="Woman" <c:if test="${user.sexId eq 'Woman'}">selected="selected"</c:if>>
                                            女</option>
                                    </select>
                                </td>
                                <th>出生年月
                                </th>
                                <td>
                                    <input style="width:140px;" name="userBirthday" value="${user.userBirthday}" type="text" class="validate[required] xltext ctime" readonly="readonly"/>
                                </td>
                                <th>年龄
                                </th>
                                <td>
                                    <input class="validate[required] xltext" name="age" value="${extInfo.age}" type="text" style="width: 129px;" readonly="readonly"/>
                                </td>
                            </tr>

                            <tr>
                                <th>身份证号码（必填）
                                </th>
                                <td colspan="3">
                                    <input class="validate[required,custom[chinaId]] xltext" onchange="checkUser(this,'idNo')" type="text" name="idNo" id="idNo" value="${user.idNo}" style="width: 147px;"  readonly="readonly"/>
                                </td>
                                <th>手机号码
                                </th>
                                <td colspan="3">
                                    <input class="validate[required,custom[mobile]] xltext" type="text" name="userPhone" value="${user.userPhone}" style="width: 141px;"  readonly="readonly"/>
                                </td>
                            </tr>

                            <tr>
                                <th>最高学历
                                </th>
                                <td colspan="3">
                                    <select name="educationId" class="xlselect" style="width:154px;" disabled="disabled">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumHighestEducationList }" var="dict">
                                            <%--<c:if test="${dict.orgFlow eq user.orgFlow}">--%>
                                            <option
                                                    <c:if test="${user.educationId eq dict.dictId }">selected="selected"</c:if>
                                                    value="${dict.dictId }">${dict.dictName }</option>
                                            <%--</c:if>--%>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th>最高学位
                                </th>
                                <td >
                                    <select name="degreeId" class="xlselect" style="width:150px;" disabled="disabled">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumHighestDegreeList }" var="dict">
                                            <%--<c:if test="${dict.orgFlow eq user.orgFlow}">--%>
                                            <option
                                                    <c:if test="${user.degreeId eq dict.dictId }">selected="selected"</c:if>
                                                    value="${dict.dictId }">${dict.dictName }</option>
                                            <%--</c:if>--%>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th>职称
                                </th>
                                <td>
                                    <input name="titleName" value="${user.titleName}" type="text" class="validate[required] xltext" style="width:130px;"  readonly="readonly" />
                                    <%--<select name="titleId" class="xlselect" style="width:137px;" disabled="disabled">--%>
                                        <%--<option value="">请选择</option>--%>
                                        <%--<c:forEach items="${dictTypeEnumGzykdxUserTitleList }" var="dict">--%>
                                            <%--&lt;%&ndash;<c:if test="${dict.orgFlow eq user.orgFlow}">&ndash;%&gt;--%>
                                            <%--<option--%>
                                                    <%--<c:if test="${user.titleId eq dict.dictId }">selected="selected"</c:if>--%>
                                                    <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                </td>
                            </tr>

                            <tr>
                                <th>最高学历毕业学校
                                </th>
                                <td colspan="3">
                                    <input class="validate[required] xltext" type="text" name="educationSchool" value="${extInfo.educationSchool}" style="width: 146px;"  readonly="readonly"/>
                                </td>
                                <th>最高学历毕业时间
                                </th>
                                <td colspan="3">
                                    <input name="educationDate" value="${extInfo.educationDate}" type="text" class="validate[required] xltext ctime" style="width:142px;"  readonly="readonly" />
                                </td>
                            </tr>

                            <tr>
                                <th>最高学位获得单位
                                </th>
                                <td colspan="3">
                                    <input class="validate[required] xltext" type="text" name="degreeOrg" value="${extInfo.degreeOrg}" style="width: 146px;"  readonly="readonly"/>
                                </td>
                                <th>最高学位获得时间
                                </th>
                                <td colspan="3">
                                    <input name="degreeDate" value="${extInfo.degreeDate}" type="text" class="validate[required] xltext ctime" style="width:142px;" readonly="readonly" />
                                </td>
                            </tr>
                            <tr>
                                <th>所在领域工作年限
                                </th>
                                <td colspan="3">
                                    <input class="validate[required] xltext" type="text" name="workAreaYear" value="${extInfo.workAreaYear}" style="width: 146px;" readonly="readonly"/>
                                </td>
                                <th>参加工作时间
                                </th>
                                <td colspan="3">
                                    <input name="workDate" value="${extInfo.workDate}" type="text" class="validate[required] xltext ctime" style="width:142px;"  readonly="readonly"/>
                                </td>
                            </tr>

                            <tr>
                                <th>申请招生专业
                                </th>
                                <td colspan="3">
                                    <select name="speId" class="xlselect" style="width:154px;" onchange="selSpe(this.value);">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumGzykdxSpeList }" var="dict">
                                            <%--<c:if test="${dict.orgFlow eq user.orgFlow}">--%>
                                            <option
                                                    <c:if test="${targetApply.speId eq dict.dictId }">selected="selected"</c:if>
                                                    value="${dict.dictId }">${dict.dictName }[${dict.dictId }]</option>
                                            <%--</c:if>--%>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th>研究方向
                                </th>
                                <td colspan="3" <c:if test="${scope ne 'teacher' or detailFlag eq 'Y'}">title="${targetApply.researchDirection}" </c:if>>
                                    <input class="validate[required] xltext" type="text" name="researchDirection" value="${targetApply.researchDirection}" style="width: 370px;" maxlength="100" placeholder="最多输入100字"/>
                                    <%--<select name="researchDirectionId" class="xlselect" style="width:370px;">--%>
                                        <%--<option value="">请选择</option>--%>
                                        <%--<c:forEach items="${dictTypeEnumResearchAreaList }" var="dict">--%>
                                            <%--&lt;%&ndash;<c:if test="${dict.orgFlow eq user.orgFlow}">&ndash;%&gt;--%>
                                            <%--<option--%>
                                                    <%--<c:if test="${targetApply.researchDirectionId eq dict.dictId }">selected="selected"</c:if>--%>
                                                    <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                </td>
                            </tr>

                            <tr>
                                <th>申请招生类型
                                </th>
                                <td colspan="7">
                                    <input type="checkbox" name="degreeType" id="isAcademic" value="Y" disabled="disabled"
                                           <c:if test="${targetApply.isAcademic eq 'Y'}">checked="checked"</c:if>/><label for="isAcademic">&nbsp;学术学位硕士研究生&nbsp;</label>
                                    <input type="checkbox" name="degreeType" id="isSpecialized" value="Y" disabled="disabled"
                                           <c:if test="${targetApply.isSpecialized eq 'Y'}">checked="checked"</c:if>/><label for="isSpecialized">&nbsp;专业学位硕士研究生&nbsp;</label>
                                </td>
                            </tr>

                        </table>

                        <br/>
                        <input type="hidden" id="thesisRecordList" name="thesisRecordList">
                        <table class="basic" style="width: 100%">
                            <tr>
                                <th colspan="7" class="bs_name" style="text-align: center;">发表论文情况（第一作者或通讯作者）</th>
                            </tr>
                            <tr>
                                <th colspan="7" style="text-align: center; width: 10%;">导师本人近三年发表论文情况（可另加行）
                                    <a href="javascript:void(0)"><c:if test="${scope eq 'teacher'}">
                                        <img src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                             onclick="addAuthor1();" title="添加"/></c:if></a></th>
                            </tr>
                            <tr>
                                <th style="text-align: center; width: 5%;">操作</th>
                                <th style="text-align: center; width: 10%;">刊物名称</th>
                                <th style="text-align: center; width: 10%;">论文题目</th>
                                <th style="text-align: center; width: 10%;">作者排名</th>
                                <th style="text-align: center; width: 10%;">发表时间</th>
                                <th style="text-align: center; width: 10%;">SCI点数</th>
                                <th style="text-align: center; width: 10%;">附件</th>
                            </tr>
                            <tbody id="authorList1">
                            <c:if test="${not empty teacherThesisList}">
                                <c:forEach items="${teacherThesisList}" var="teacherThesis">
                                    <tr>
                                        <td>
                                            <%--<a href="javascript:void(0)"><c:if test="${scope eq 'teacher'}">--%>
                                                <%--<img src="<s:url value='/'/>css/skin/${skinPath}/images/del3.png"--%>
                                                     <%--onclick="delThesis(this,'${teacherThesis.teacherThesis.recordFlow}');" title="删除"/></c:if></a>--%>
                                            <input type="button" onclick="delThesis(this,'${teacherThesis.teacherThesis.recordFlow}');" class="search" value="删除"/>
                                        </td>
                                        <td>
                                            <input type="hidden" name="recordFlow" value="${teacherThesis.teacherThesis.recordFlow}"/>
                                            <input class="xltext" style="width: 85%;" type="text" name="thesisName" value="${teacherThesis.teacherThesis.thesisName}" <c:if test="${scope ne 'teacher' or detailFlag eq 'Y'}">title="${teacherThesis.teacherThesis.thesisName}" </c:if> maxlength="100" placeholder="最多100字"/>
                                        </td>
                                        <td><input class="xltext" style="width: 85%;" type="text" name="thesisTitle" value="${teacherThesis.teacherThesis.thesisTitle}" <c:if test="${scope ne 'teacher' or detailFlag eq 'Y'}">title="${teacherThesis.teacherThesis.thesisTitle}" </c:if> maxlength="100" placeholder="最多100字"/></td>
                                        <td>
                                            <input class="xltext" style="width: 85%;" type="text" name="authorRankingName" value="${teacherThesis.teacherThesis.authorRankingName}"/>
                                            <%--<select name="authorRankingId" class="select" style="width:85%;">--%>
                                                <%--<c:forEach items="${dictTypeEnumAuthorRankingList }" var="dict">--%>
                                                    <%--<option--%>
                                                            <%--<c:if test="${teacherThesis.teacherThesis.authorRankingId eq dict.dictId }">selected="selected"</c:if>--%>
                                                            <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                                <%--</c:forEach>--%>
                                            <%--</select>--%>
                                        </td>
                                        <td><input class="xltext ctime" style="width: 85%;" type="text" name="reportTime" value="${teacherThesis.teacherThesis.reportTime}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
                                        <td><input class="xltext" style="width: 85%;" type="text" name="sciPoint" value="${teacherThesis.teacherThesis.sciPoint}"/></td>
                                        <td title="请上传发表论文的电子稿或扫描件图片<br/><font style='color: red'>文件支持PDF/WORD/rar/zip以及jpeg/jpg/png格式图片,且大小不超过10M</font>" style="line-height: 150%;">
                                            <c:choose>
                                                <c:when test="${not empty teacherThesis.file.fileName}">
                                                    <a id="down_${teacherThesis.file.fileFlow}" onclick="preview('${fn:replace(teacherThesis.file.filePath,'\\','/')}','${teacherThesis.file.fileFlow}')">${teacherThesis.file.fileName}</a>
                                                    <input type="file" id="file_${teacherThesis.file.fileFlow}" name="file" class="validate[required,custom[gzykFileType]]" style="display: none;"/>
                                                    <c:if test="${scope eq 'teacher'}">
                                                    <span style="float: right; padding-right: 10px;">
                                                        <a id="reCheck" href="javascript:void(0);" onclick="reCheck(this,'${teacherThesis.file.fileFlow}');">[重新选择文件]</a>
                                                    </span>
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="file" id="file" name="file" class="validate[required,custom[gzykFileType]]"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <form id="srmAchFile1">
                                                <input type="hidden" name="fileFlow1" value="${teacherThesis.file.fileFlow}" id="fileFlow_${teacherThesis.file.fileFlow}"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            </tbody>
                        </table>

                        <table class="basic" style="width: 100%">
                            <tr>
                                <th colspan="7" style="text-align: center; width: 10%;">近三年指导研究生发表论文情况（可另加行）
                                    <a href="javascript:void(0)"><c:if test="${scope eq 'teacher'}">
                                        <img src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                             onclick="addAuthor2();" title="添加"/></c:if></a></th>
                            </tr>
                            <tr>
                                <th style="text-align: center; width: 5%;">操作</th>
                                <th style="text-align: center; width: 10%;">刊物名称</th>
                                <th style="text-align: center; width: 10%;">论文题目</th>
                                <th style="text-align: center; width: 10%;">作者排名</th>
                                <th style="text-align: center; width: 10%;">发表时间</th>
                                <th style="text-align: center; width: 10%;">SCI点数</th>
                                <th style="text-align: center; width: 10%;">附件</th>
                            </tr>
                            <tbody id="authorList2">
                            <c:if test="${not empty doctorThesisList}">
                                <c:forEach items="${doctorThesisList}" var="doctorThesis">
                                    <tr>
                                        <td>
                                            <input type="button" onclick="delThesis(this,'${doctorThesis.doctorThesis.recordFlow}');" class="search" value="删除"/>
                                        </td>
                                        <td>
                                            <input type="hidden" name="recordFlow" value="${doctorThesis.doctorThesis.recordFlow}"/>
                                            <input class="xltext" style="width: 85%;" type="text" name="thesisName" value="${doctorThesis.doctorThesis.thesisName}" <c:if test="${scope ne 'teacher' or detailFlag eq 'Y'}">title="${doctorThesis.doctorThesis.thesisName}" </c:if> maxlength="100" placeholder="最多100字"/>
                                        </td>
                                        <td><input class="xltext" style="width: 85%;" type="text" name="thesisTitle" value="${doctorThesis.doctorThesis.thesisTitle}" <c:if test="${scope ne 'teacher' or detailFlag eq 'Y'}">title="${doctorThesis.doctorThesis.thesisTitle}" </c:if> maxlength="100" placeholder="最多100字"/></td>
                                        <td>
                                            <input class="xltext" style="width: 85%;" type="text" name="authorRankingName" value="${doctorThesis.doctorThesis.authorRankingName}"/>
                                            <%--<select name="authorRankingId" class="select" style="width:85%;">--%>
                                                <%--<c:forEach items="${dictTypeEnumAuthorRankingList }" var="dict">--%>
                                                    <%--<option--%>
                                                            <%--<c:if test="${doctorThesis.doctorThesis.authorRankingId eq dict.dictId }">selected="selected"</c:if>--%>
                                                            <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                                <%--</c:forEach>--%>
                                            <%--</select>--%>
                                        </td>
                                        <td><input class="xltext ctime" style="width: 85%;" type="text" name="reportTime" value="${doctorThesis.doctorThesis.reportTime}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
                                        <td><input class="xltext" style="width: 85%;" type="text" name="sciPoint" value="${doctorThesis.doctorThesis.sciPoint}"/></td>
                                        <td title="请上传发表论文的电子稿或扫描件图片<br/><font style='color: red'>文件支持PDF/WORD/rar/zip以及jpeg/jpg/png格式图片,且大小不超过10M</font>" style="line-height: 150%;">
                                            <c:choose>
                                                <c:when test="${not empty doctorThesis.file.fileName}">
                                                    <a id="down_${doctorThesis.file.fileFlow}" onclick="preview('${fn:replace(doctorThesis.file.filePath,'\\','/')}','${doctorThesis.file.fileFlow}')">${doctorThesis.file.fileName}</a>
                                                    <input type="file" id="file_${doctorThesis.file.fileFlow}" name="file" class="validate[required,custom[gzykFileType]]" style="display: none;"/>
                                                    <c:if test="${scope eq 'teacher'}">
                                                        <span style="float: right; padding-right: 10px;">
                                                            <a id="reCheck" href="javascript:void(0);" onclick="reCheck(this,'${doctorThesis.file.fileFlow}');">[重新选择文件]</a>
                                                        </span>
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="file" id="file" name="file" class="validate[required,custom[gzykFileType]]"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <form id="srmAchFile2">
                                                <input type="hidden" name="fileFlow2" value="${doctorThesis.file.fileFlow}" id="fileFlow_${doctorThesis.file.fileFlow}"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${scope ne 'teacher'}">
                            <table class="basic" style="width: 100%">
                                <tr>
                                    <th colspan="7" style="width: 130px;text-align: left">&#12288;导师近三年发表SCI论文篇数:&#12288;<c:if test="${not empty teacherThesisList}">${fn:length(teacherThesisList)}</c:if></th>
                                    <th colspan="7" style="width: 130px;text-align: left">&#12288;指导硕/博士生近三年发表SCI论文篇数:&#12288;<c:if test="${not empty teacherThesisList}">${fn:length(doctorThesisList)}</c:if></th>
                                </tr>
                            </table>
                        </c:if>
                        <br/>
                        <table class="basic" style="width: 100%">
                            <tr>
                                <th colspan="8" class="bs_name" style="text-align: center;">科研项目情况
                                    <a href="javascript:void(0)"><c:if test="${scope eq 'teacher'}">
                                        <img src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                             onclick="addAuthor3();" title="添加"/></c:if></a></th>
                            </tr>
                            <tr>
                                <th style="text-align: center; width: 5%;">操作</th>
                                <th style="text-align: center; width: 10%;">在研项目名称</th>
                                <th style="text-align: center; width: 10%;">排名</th>
                                <th style="text-align: center; width: 10%;">立项时间</th>
                                <th style="text-align: center; width: 10%;">立项单位</th>
                                <th style="text-align: center; width: 10%;">项目编号</th>
                                <th style="text-align: center; width: 10%;">资助金额(万元)</th>
                                <th style="text-align: center; width: 10%;">附件</th>
                            </tr>
                            <tbody id="authorList3">
                            <c:if test="${not empty researchProjectList}">
                                <c:forEach items="${researchProjectList}" var="researchProject">
                                    <tr>
                                        <td>
                                            <input type="button" onclick="delThesis(this,'${researchProject.researchProject.recordFlow}');" class="search" value="删除"/>
                                        </td>
                                        <td>
                                            <input type="hidden" name="recordFlow" value="${researchProject.researchProject.recordFlow}"/>
                                            <input class="xltext" style="width: 85%;" type="text" name="thesisName" value="${researchProject.researchProject.thesisName}" <c:if test="${scope ne 'teacher' or detailFlag eq 'Y'}">title="${researchProject.researchProject.thesisName}" </c:if> maxlength="100" placeholder="最多100字"/>
                                        </td>
                                        <td>
                                            <input class="xltext" style="width: 85%;" type="text" name="authorRankingName" value="${researchProject.researchProject.authorRankingName}"/>
                                            <%--<select name="authorRankingId" class="select" style="width:85%;">--%>
                                                <%--<c:forEach items="${dictTypeEnumAuthorRankingList }" var="dict">--%>
                                                    <%--<option--%>
                                                            <%--<c:if test="${researchProject.researchProject.authorRankingId eq dict.dictId }">selected="selected"</c:if>--%>
                                                            <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                                <%--</c:forEach>--%>
                                            <%--</select>--%>
                                        </td>
                                        <td><input class="xltext ctime" style="width: 85%;" type="text" name="reportTime" value="${researchProject.researchProject.reportTime}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
                                        <td><input class="xltext" style="width: 85%;" type="text" name="productWorkName" value="${researchProject.researchProject.productWorkName}"/></td>
                                        <td><input class="xltext" style="width: 85%;" type="text" name="productCode" value="${researchProject.researchProject.productCode}"/></td>
                                        <td><input class="xltext validate[required,custom[positiveNum]]" style="width: 85%;" type="text" name="productAmount" value="${researchProject.researchProject.productAmount}"/></td>
                                        <td title="请上传立项通知等官方文件材料的电子稿或扫描件图片<br/><font style='color: red'>文件支持PDF/WORD/rar/zip以及jpeg/jpg/png格式图片,且大小不超过10M</font>" style="line-height: 150%;">
                                            <c:choose>
                                                <c:when test="${not empty researchProject.file.fileName}">
                                                    <a id="down_${researchProject.file.fileFlow}" onclick="preview('${fn:replace(researchProject.file.filePath,'\\','/')}','${researchProject.file.fileFlow}')">${researchProject.file.fileName}</a>
                                                    <input type="file" id="file_${researchProject.file.fileFlow}" name="file" class="validate[required,custom[gzykFileType]]"  style="display: none;"/>
                                                    <c:if test="${scope eq 'teacher'}">
                                                        <span style="float: right; padding-right: 10px;">
                                                            <a id="reCheck" href="javascript:void(0);" onclick="reCheck(this,'${researchProject.file.fileFlow}');">[重新选择文件]</a>
                                                        </span>
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="file" id="file" name="file" class="validate[required,custom[gzykFileType]]"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <form id="srmAchFile3">
                                                <input type="hidden" name="fileFlow3" value="${researchProject.file.fileFlow}" id="fileFlow_${researchProject.file.fileFlow}"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <br/>
                        <table class="basic" style="width: 100%">
                            <tr>
                                <th colspan="7" class="bs_name" style="text-align: left;">申请人申报签名</th>
                            </tr>
                            <tr>
                                <th colspan="5" style="height: 60px;text-align: right;border-right-width: 0px; ">
                                    本人所填写的以上信息全部属实。
                                </th>
                                <th colspan="2" style="height: 60px;text-align: right;line-height: 150%;border-left-width: 0px;">
                                    <br/><br/>
                                    &#12288;&#12288;&#12288;申请人签名：${targetApply.userName}<br/>
                                    &#12288;${targetApply.teacherApplyTime==null?'   ':fn:split(targetApply.teacherApplyTime,'-')[0]}年${targetApply.teacherApplyTime==null?'   ':fn:split(targetApply.teacherApplyTime,'-')[1]}月${targetApply.teacherApplyTime==null?'   ':fn:split(targetApply.teacherApplyTime,'-')[2]}日
                                </th>
                            </tr>
                        </table>
                    </div>
                    <c:if test="${scope ne 'teacher' or detailFlag eq 'Y' or targetApply.auditStatusId eq gzykdxAuditStatusEnumOrgSendBack}">
                        <table class="basic" style="width: 100%">
                            <tr>
                                <th colspan="4" class="bs_name" style="text-align: left;">所在单位审核意见</th>
                                <th colspan="3" class="bs_name" style="text-align: left;">学校审核意见</th>
                            </tr>
                            <tr>
                                <td colspan="4" style="height: 60px;text-align: center;">
                                    <textarea class="xltxtarea" style="margin-left: 0px;"  maxlength="1000" name="orgAuditAdvice" onblur="strChar(this,1000)" <c:if test="${scope eq 'school' or detailFlag eq 'Y' or targetApply.auditStatusId eq gzykdxAuditStatusEnumOrgSendBack}">disabled="disabled"</c:if>>${empty targetApply.orgAuditAdvice?"经审核申请人所填信息属实，同意申报。":targetApply.orgAuditAdvice}</textarea>
                                </td>
                                <td colspan="3" style="height: 60px;text-align: center;">
                                    <textarea class="xltxtarea" style="margin-left: 0px;" maxlength="1000" name="schoolAuditAdvice" onblur="strChar(this,1000)" <c:if test="${scope eq 'org' or detailFlag eq 'Y' or targetApply.auditStatusId eq gzykdxAuditStatusEnumOrgSendBack}">disabled="disabled"</c:if>>${empty targetApply.schoolAuditAdvice?"":targetApply.schoolAuditAdvice}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th colspan="4" style="height: 30px;text-align: right;line-height: 150%">
                                    负责人签名：${targetApply.orgUserName}&#12288;&#12288;&#12288;<br/>
                                    &#12288;${targetApply.orgAuditTime==null?'   ':fn:split(targetApply.orgAuditTime,'-')[0]}年${targetApply.orgAuditTime==null?'   ':fn:split(targetApply.orgAuditTime,'-')[1]}月${targetApply.orgAuditTime==null?'   ':fn:split(targetApply.orgAuditTime,'-')[2]}日(盖章)&#12288;&#12288;
                                </th>
                                <th colspan="3" style="height: 30px;text-align: right;line-height: 150%">
                                    <br/>
                                    &#12288;${targetApply.schoolAuditTime==null?'   ':fn:split(targetApply.schoolAuditTime,'-')[0]}年${targetApply.schoolAuditTime==null?'   ':fn:split(targetApply.schoolAuditTime,'-')[1]}月${targetApply.schoolAuditTime==null?'   ':fn:split(targetApply.schoolAuditTime,'-')[2]}日(盖章)&#12288;&#12288;
                                </th>
                            </tr>
                        </table>
                    </c:if>
                    </td>
                </form>

                <%-- 文件流水号 --%>
                <%--<form id="srmAchFile">--%>
                <%--<input type="hidden" name="fileFlow" value="${file.fileFlow}"/>--%>
                <%--</form>--%>
                <c:if test="${detailFlag ne 'Y'}">
                    <c:choose>
                        <c:when test="${scope eq 'teacher'}">
                            <p align="center">
                                <input type="button" value="保&#12288;存" onclick="saveThe();" class="search"/>
                                <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p align="center">
                                <input type="button" value="通&#12288;过" onclick="audit('Passed');" class="search"/>
                                <input type="button" value="不通过" onclick="audit('UnPassed');" class="search"/>
                                <input type="button" value="退&#12288;回" onclick="audit('SendBack');" class="search"/>
                            </p>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <table class="basic" id="moban1" style="display: none" style="width: 100%">
                    <tr>
                        <td>
                            <input type="button" onclick="delThesis(this,'${teacherThesis.teacherThesis.recordFlow}');" class="search" value="删除"/>
                        </td>
                        <td>
                            <input type="hidden" name="recordFlow" value="${teacherThesis.teacherThesis.recordFlow}"/>
                            <input class="xltext" style="width: 85%;" type="text" name="thesisName" value="${teacherThesis.teacherThesis.thesisName}" maxlength="100" placeholder="最多100字"/>
                        </td>
                        <td><input class="xltext" style="width: 85%;" type="text" name="thesisTitle" value="${teacherThesis.teacherThesis.thesisTitle}" maxlength="100" placeholder="最多100字"/></td>
                        <td>
                            <input class="xltext" style="width: 85%;" type="text" name="authorRankingName" value="${teacherThesis.teacherThesis.authorRankingName}"/>
                            <%--<select name="authorRankingId" class="select" style="width:85%;">--%>
                                <%--<c:forEach items="${dictTypeEnumAuthorRankingList }" var="dict">--%>
                                    <%--<option--%>
                                            <%--<c:if test="${teacherThesis.teacherThesis.authorRankingId eq dict.dictId }">selected="selected"</c:if>--%>
                                            <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        </td>
                        <td><input class="xltext ctime" style="width: 85%;" type="text" name="reportTime" value="${teacherThesis.teacherThesis.reportTime}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
                        <td><input class="xltext" style="width: 85%;" type="text" name="sciPoint" value="${teacherThesis.teacherThesis.sciPoint}"/></td>
                        <td title="请上传发表论文的电子稿或扫描件图片<br/><font style='color: red'>文件支持PDF/WORD/rar/zip以及jpeg/jpg/png格式图片,且大小不超过10M</font>" style="line-height: 150%;">
                            <c:choose>
                                <c:when test="${not empty teacherThesis.file.fileName}">
                                    <a id="down_${teacherThesis.file.fileFlow}" onclick="preview('${fn:replace(teacherThesis.file.filePath,'\\','/')}','${teacherThesis.file.fileFlow}')">${teacherThesis.file.fileName}</a>
                                    <input type="file" id="file_${teacherThesis.file.fileFlow}" name="file" class="validate[required,custom[gzykFileType]]" style="display: none;"/>
                                    <c:if test="${scope eq 'teacher'}">
                                            <span style="float: right; padding-right: 10px;">
                                                <a id="reCheck" href="javascript:void(0);" onclick="reCheck(this,'${teacherThesis.file.fileFlow}');">[重新选择文件]</a>
                                            </span>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <input type="file" id="file" name="file" class="validate[required,custom[gzykFileType]]"/>
                                </c:otherwise>
                            </c:choose>
                            <form id="srmAchFile1">
                                <input type="hidden" name="fileFlow1" value="${teacherThesis.file.fileFlow}" id="fileFlow_${teacherThesis.file.fileFlow}"/>
                            </form>
                        </td>
                    </tr>
                </table>

                <table class="basic" id="moban2" style="display: none" style="width: 100%">
                    <tr>
                        <td>
                            <input type="button" onclick="delThesis(this,'${doctorThesis.doctorThesis.recordFlow}');" class="search" value="删除"/>
                        </td>
                        <td>
                            <input type="hidden" name="recordFlow" value="${doctorThesis.doctorThesis.recordFlow}"/>
                            <input class="xltext" style="width: 85%;" type="text" name="thesisName" value="${doctorThesis.doctorThesis.thesisName}" maxlength="100" placeholder="最多100字"/>
                        </td>
                        <td><input class="xltext" style="width: 85%;" type="text" name="thesisTitle" value="${doctorThesis.doctorThesis.thesisTitle}" maxlength="100" placeholder="最多100字"/></td>
                        <td>
                            <input class="xltext" style="width: 85%;" type="text" name="authorRankingName" value="${doctorThesis.doctorThesis.authorRankingName}"/>
                            <%--<select name="authorRankingId" class="select" style="width:85%;">--%>
                                <%--<c:forEach items="${dictTypeEnumAuthorRankingList }" var="dict">--%>
                                    <%--<option--%>
                                            <%--<c:if test="${doctorThesis.doctorThesis.authorRankingId eq dict.dictId }">selected="selected"</c:if>--%>
                                            <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        </td>
                        <td><input class="xltext ctime" style="width: 85%;" type="text" name="reportTime" value="${doctorThesis.doctorThesis.reportTime}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
                        <td><input class="xltext" style="width: 85%;" type="text" name="sciPoint" value="${doctorThesis.doctorThesis.sciPoint}"/></td>
                        <td title="请上传发表论文的电子稿或扫描件图片<br/><font style='color: red'>文件支持PDF/WORD/rar/zip以及jpeg/jpg/png格式图片,且大小不超过10M</font>" style="line-height: 150%;">
                            <c:choose>
                                <c:when test="${not empty doctorThesis.file.fileName}">
                                    <a id="down_${doctorThesis.file.fileFlow}" onclick="preview('${fn:replace(doctorThesis.file.filePath,'\\','/')}','${doctorThesis.file.fileFlow}')">${doctorThesis.file.fileName}</a>
                                    <input type="file" id="file_${doctorThesis.file.fileFlow}" name="file" class="validate[required,custom[gzykFileType]]" style="display: none;"/>
                                    <c:if test="${scope eq 'teacher'}">
                                                <span style="float: right; padding-right: 10px;">
                                                    <a id="reCheck" href="javascript:void(0);" onclick="reCheck(this,'${doctorThesis.file.fileFlow}');">[重新选择文件]</a>
                                                </span>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <input type="file" id="file" name="file" class="validate[required,custom[gzykFileType]]"/>
                                </c:otherwise>
                            </c:choose>
                            <form id="srmAchFile2">
                                <input type="hidden" name="fileFlow2" value="${doctorThesis.file.fileFlow}" id="fileFlow_${doctorThesis.file.fileFlow}"/>
                            </form>
                        </td>
                    </tr>
                </table>

                <table class="basic" id="moban3" style="display: none" style="width: 100%">
                    <tr>
                        <td>
                            <input type="button" onclick="delThesis(this,'${researchProject.researchProject.recordFlow}');" class="search" value="删除"/>
                        </td>
                        <td>
                            <input class="xltext" style="width: 85%;" type="text" name="thesisName" maxlength="100" placeholder="最多100字"/>
                        </td>
                        <td>
                            <input class="xltext" style="width: 85%;" type="text" name="authorRankingName" value="${researchProject.researchProject.authorRankingName}"/>
                            <%--<select name="authorRankingId" class="select" style="width:85%;">--%>
                                <%--<c:forEach items="${dictTypeEnumAuthorRankingList }" var="dict">--%>
                                    <%--<option--%>
                                            <%--<c:if test="${param.degreeId eq dict.dictId }">selected="selected"</c:if>--%>
                                            <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        </td>
                        <td><input class="xltext ctime" style="width: 85%;" type="text" name="reportTime" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
                        <td><input class="xltext" style="width: 85%;" type="text" name="productWorkName"/></td>
                        <td><input class="xltext" style="width: 85%;" type="text" name="productCode"/></td>
                        <td><input class="xltext validate[required,custom[positiveNum]]" style="width: 85%;" type="text" name="productAmount"/></td>
                        <td title="请上传立项通知等官方文件材料的电子稿或扫描件图片<br/><font style='color: red'>文件支持PDF/WORD/rar/zip以及jpeg/jpg/png格式图片,且大小不超过10M</font>" style="line-height: 150%;">
                            <c:choose>
                                <c:when test="${not empty researchProject.file.fileName}">
                                    <a id="down_${researchProject.file.fileFlow}" onclick="preview('${fn:replace(researchProject.file.filePath,'\\','/')}','${researchProject.file.fileFlow}')">${researchProject.file.fileName}</a>
                                    <input type="file" id="file_${researchProject.file.fileFlow}" name="file" class="validate[required,custom[gzykFileType]]" style="display: none;"/>
                                    <c:if test="${scope eq 'teacher'}">
                                    <span style="float: right; padding-right: 10px;">
                                        <a id="reCheck" href="javascript:void(0);" onclick="reCheck(this,'${researchProject.file.fileFlow}');">[重新选择文件]</a>
                                    </span>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <input type="file" id="file" name="file" class="validate[required,custom[gzykFileType]]"/>
                                </c:otherwise>
                            </c:choose>
                            <form id="srmAchFile3">
                                <input type="hidden" name="fileFlow3" value="${researchProject.file.fileFlow}" id="fileFlow_${researchProject.file.fileFlow}"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>