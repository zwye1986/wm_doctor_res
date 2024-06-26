<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        td{text-align: left}
        th{background-color: #f1f1f1;}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">
        $(function () {
            getForm($("[name='activityTypeId']"));
        })
        
        function reChoose(obj){
            $(obj).hide();
            $("#isRe").val("Y");
            $("#down").hide();
            $("#file").show();
        }

        function save() {
            if(false==$("#addForm").validationEngine("validate")){
                return false;
            }
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            if(startDate >= endDate){
                jboxTip("开始时间不得大于等于结束时间！");
                return;
            }
            if($(".formData").size()>0){
                var data = [];
                $(".formData").each(function () {
                    var formFlow = $(this).attr("formFlow");
                    var recordFlow = $(this).attr("recordFlow");
                    var detailValue = $(this).val();
                    var single = {
                        recordFlow:recordFlow,
                        formFlow:formFlow,
                        detailValue:detailValue,
                        activityFlow:'${activity.activityFlow}'
                    }
                    data.push(single);
                })
                $("#data").val(JSON.stringify(data));
            }
            var url = "<s:url value='/res/activityQuery/saveActivity'/>";
            jboxStartLoading();
            jboxSubmit($('#addForm'),url,function(resp) {
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.frames['mainIframe'].window.toPage(1);
                    setTimeout(function(){
                        jboxClose();
                    },500);
                }
            }, null, true);
        }
        function showUserPhone(userFlow)
        {
            if(userFlow)
            {
                var phone=$("#speakerFlow").find("option[value='"+userFlow+"']").attr("phone");
                if(phone)
                    $("#speakerPhone").val(phone);
                else
                    $("#speakerPhone").val("");
            }else {
                $("#speakerPhone").val("");
            }
        }
        function loadDeptUsers(deptFlow)
        {
            if(deptFlow)
            {
                $("#speakerFlow").find("option[value!='']").remove();
                var url = '<s:url value="/res/activityQuery/loadTeacherAndHead"/>?role=${role}';
                jboxPost(url,{deptFlow:deptFlow},function(resp){
                    if(resp){
                        var option = $('<option/>');
                        for(var index in resp){
                            var data = resp[index];
                            var val = data.userFlow;
                            $("#speakerFlow").append(option.clone().val(val).text(data.userName).attr('selected',val=="${activity.speakerFlow}").attr("phone",data.userPhone));
                            if(val=="${activity.speakerFlow}")
                            {
                                $("#speakerPhone").val(data.userPhone);
                            }
                        }
                    }
                },null,false);
            }else {
                $("#speakerFlow").find("option[value!='']").remove();
                $("#speakerPhone").val("");
            }
        }
        function getForm(obj) {
            var activityTypeName = $(obj).find('option:selected').text();
            var activityTypeId = $(obj).find('option:selected').val();
            $("#tips").hide();
            if(activityTypeName=='教学病例讨论'){
                $("#tips").show();
            }
            if(activityTypeId){
                var url = '<s:url value="/res/activityQuery/getFormDetail"/>?activityTypeId='+activityTypeId+'&activityFlow=${activity.activityFlow}';
                jboxPost(url,null,function (resp) {
                    $(".newTr").remove();

                    var valueMap = resp["valueMap"];
                    var smallForms = resp["smallForms"];
                    var l=smallForms.length;
                    var rowNum=parseInt((l+1)/2);
                    var nextTr=$("#addTrNode");
                    for(var i=0;i<rowNum;i++)
                    {
                        var tr=$("<tr class='newTr'></tr>");
                        var firstIndex=i*2;
                        var secondIndex=i*2+1;
                        if(i==rowNum-1)
                        {
                            if(secondIndex!=l){
                                $(tr).append($("<th style='text-align: right'></th>").html("<font color=\"red\">*</font>"+smallForms[firstIndex].detailKey+"："));
                                if(valueMap[smallForms[firstIndex].recordFlow]){
                                    $(tr).append($("<td/>").html('<input formFlow="'+smallForms[firstIndex].recordFlow+'"recordFlow="'+valueMap[smallForms[firstIndex].recordFlow].recordFlow+'"  class="formData qtext validate[required,maxSize[200]]" value="'+valueMap[smallForms[firstIndex].recordFlow].detailValue+'">'));
                                }else {
                                    $(tr).append($("<td/>").html('<input formFlow="'+smallForms[firstIndex].recordFlow+'"recordFlow=""  class="formData qtext validate[required,maxSize[200]]" value="">'));
                                }
                                $(tr).append($("<th style='text-align: right'></th>").html("<font color=\"red\">*</font>"+smallForms[secondIndex].detailKey+"："));
                                if(valueMap[smallForms[secondIndex].recordFlow]){
                                    $(tr).append($("<td/>").html('<input formFlow="'+smallForms[secondIndex].recordFlow+'"recordFlow="'+valueMap[smallForms[secondIndex].recordFlow].recordFlow+'"  class="formData qtext validate[required,maxSize[200]]" value="'+valueMap[smallForms[secondIndex].recordFlow].detailValue+'">'));
                                }else{
                                    $(tr).append($("<td/>").html('<input formFlow="'+smallForms[secondIndex].recordFlow+'"recordFlow=""  class="formData qtext validate[required,maxSize[200]]" value="">'));
                                }
                            }else{
                                $(tr).append($("<th style='text-align: right'></th>").html("<font color=\"red\">*</font>"+smallForms[firstIndex].detailKey+"："));
                                if(valueMap[smallForms[firstIndex].recordFlow]){
                                    $(tr).append($("<td/>").html('<input formFlow="'+smallForms[firstIndex].recordFlow+'"recordFlow="'+valueMap[smallForms[firstIndex].recordFlow].recordFlow+'"  class="formData qtext validate[required,maxSize[200]]" value="'+valueMap[smallForms[firstIndex].recordFlow].detailValue+'">'));
                                }else {
                                    $(tr).append($("<td/>").html('<input formFlow="'+smallForms[firstIndex].recordFlow+'"recordFlow=""  class="formData qtext validate[required,maxSize[200]]" value="">'));
                                }
                                $(tr).append($("<th style='text-align: right'></th>"));
                                $(tr).append($("<td/>"));
                            }
                        }else{
                            $(tr).append($("<th style='text-align: right'></th>").html("<font color=\"red\">*</font>"+smallForms[firstIndex].detailKey+"："));
                            if(valueMap[smallForms[firstIndex].recordFlow]){
                                $(tr).append($("<td/>").html('<input formFlow="'+smallForms[firstIndex].recordFlow+'"recordFlow="'+valueMap[smallForms[firstIndex].recordFlow].recordFlow+'"  class="formData qtext validate[required,maxSize[200]]" value="'+valueMap[smallForms[firstIndex].recordFlow].detailValue+'">'));
                            }else {
                                $(tr).append($("<td/>").html('<input formFlow="'+smallForms[firstIndex].recordFlow+'"recordFlow=""  class="formData qtext validate[required,maxSize[200]]" value="">'));
                            }
                            $(tr).append($("<th style='text-align: right'></th>").html("<font color=\"red\">*</font>"+smallForms[secondIndex].detailKey+"："));
                            if(valueMap[smallForms[secondIndex].recordFlow]){
                                $(tr).append($("<td/>").html('<input formFlow="'+smallForms[secondIndex].recordFlow+'"recordFlow="'+valueMap[smallForms[secondIndex].recordFlow].recordFlow+'"  class="formData qtext validate[required,maxSize[200]]" value="'+valueMap[smallForms[secondIndex].recordFlow].detailValue+'">'));
                            }else{
                                $(tr).append($("<td/>").html('<input formFlow="'+smallForms[secondIndex].recordFlow+'"recordFlow=""  class="formData qtext validate[required,maxSize[200]]" value="">'));
                            }
                        }
                        $(nextTr).after($(tr));
                        nextTr=$(tr);
                    }
                    var bigForms = resp["bigForms"];
                    for(var i in bigForms){
                        var tr=$("<tr class='newTr'></tr>");
                        $(tr).append($("<th style='text-align: right'></th>").html("<font color=\"red\">*</font>"+bigForms[i].detailKey+"："));
                        if(valueMap[bigForms[i].recordFlow]){
                            $(tr).append($("<td colspan='3'></td>").html('<textarea formFlow="'+bigForms[i].recordFlow+'"recordFlow="'+valueMap[bigForms[i].recordFlow].recordFlow+'"  class="formData validate[required,maxSize[200]]" style="width: 464px;margin: 5 5px;height: 60px">'+valueMap[bigForms[i].recordFlow].detailValue+'</textarea>'));
                        }else{
                            $(tr).append($("<td colspan='3'></td>").html('<textarea formFlow="'+bigForms[i].recordFlow+'"recordFlow=""  class="formData validate[required,maxSize[200]]" style="width: 464px;margin: 5 5px;height: 60px"></textarea>'));
                        }
                        $(nextTr).after($(tr));
                        nextTr=$(tr);
                    }
                },null,false);
            }else {
                $(".newTr").remove();
            }
        }

    </script>
</head>
<body style="overflow: auto;">
<div class="infoAudit" style="overflow: auto;">
        <form id="addForm" style="position: relative;">
            <input name="activityFlow" type="hidden" value="${activity.activityFlow}"  />
            <input name="fileFlow" type="hidden" value="${activity.fileFlow}"  />
            <input name="data" id="data" type="hidden" value=""/>
            <table class="xllist nofix" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;width: 88px;"><font color="red" >*</font>活动名称：</th>
                    <td colspan="3">
                        <input name="activityName" id="activityName" style="width: 445px" class="qtext validate[required,maxSize[200]]" value="${activity.activityName}"  />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>开始时间：</th>
                    <td>
                        <input type="text" id="startDate" name="startTime" value="${activity.startTime}"  class="qtext validate[required]"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>结束时间：</th>
                    <td>
                        <input type="text" id="endDate" name="endTime" value="${activity.endTime}"  class="qtext validate[required]"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>活动形式：</th>
                    <td>
                        <select name="activityTypeId" style="margin: 0 5px;" class="qselect validate[required]" onchange="getForm(this)">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
                                <option value="${a.dictId}" ${(activity.activityTypeId eq a.dictId) ?'selected':''}>${a.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>所在科室：</th>
                    <td>
                        <select name="deptFlow" style="margin: 0 5px;" class="qselect validate[required]"<c:if test="${role eq 'head' or role eq 'spe'}">onchange="loadDeptUsers(this.value)"</c:if> >
                            <option value="">请选择</option>
                            <c:forEach items="${depts}" var="dept">
                                <option value="${dept.deptFlow}" ${(activity.deptFlow eq dept.deptFlow) ?'selected':''}>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr id="addTrNode">
                    <th style="text-align: right;"><font color="red" >*</font>主&nbsp;讲&nbsp;人&nbsp;：</th>
                    <td>
                        <c:if test="${role eq 'head' or role eq 'spe'}">
                            <select name="speakerFlow" id="speakerFlow" style="margin: 0 5px;" class="qselect validate[required]" onchange="showUserPhone(this.value)" >
                                <option value="">请选择</option>
                                <c:forEach items="${userList}" var="user">
                                    <option value="${user.userFlow}" phone="${user.userPhone}" ${(activity.speakerFlow eq user.userFlow) ?'selected':''}>${user.userName}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${role eq 'teach'}">
                            <input name="speakerFlow" type="hidden" value="${user.userFlow}"  />
                             ${user.userName}
                         </c:if>
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>联系方式：</th>
                    <td>
                        <input name="speakerPhone" id="speakerPhone" class="qtext validate[required,custom[number]]" value="${activity.speakerPhone}"  />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><c:if test="${role eq 'teach'}"><font color="red" >*</font></c:if>活动地点：</th>
                    <td colspan="3">
                        <textarea type="text" name="activityAddress" class="validate[<c:if test="${role eq 'teach'}">required,</c:if>maxSize[500]]" style="width: 464px;margin: 5 5px;height: 60px">${activity.activityAddress}</textarea>

                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><c:if test="${role eq 'teach'}"><font color="red" >*</font></c:if>活动简介：</th>
                    <td colspan="3">
                        <textarea type="text" name="activityRemark" class="validate[<c:if test="${role eq 'teach'}">required,</c:if>maxSize[500]]" style="width: 464px;margin: 5 5px;height: 60px">${activity.activityRemark}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">附&#12288;&#12288;件：</th>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${not empty activity.fileFlow}">
                                <a id="down" href="<s:url value='/res/activityQuery/downFile'/>?fileFlow=${activity.fileFlow}">${file.fileName}</a>
                                <input type="file" class="" id="file" name="file" style="display: none;max-width: 300px"/>
                                <input type="text" id="isRe" class="" name="isRe"  style="display: none;" />
								<span style="float: right; padding-right: 10px;">
								    <a id="reCheck" href="javascript:void(0);" onclick="reChoose(this);" style="color: blue;">[重新选择文件]</a>
								</span>
                            </c:when>
                            <c:otherwise>
                                <input type="file" class="" id="file" name="file" style="max-width: 300px"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </form>
        <p id="tips" style="color: red;text-align: center">上传病例讨论记录（将存档病历word版上传或拍照片）</p>
        <div class="button">
            <input type="button" onclick="save();" class="search" value="保&#12288;存"/>&#12288;
            <input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭"/>
        </div>
</div>

</body>
</html>
