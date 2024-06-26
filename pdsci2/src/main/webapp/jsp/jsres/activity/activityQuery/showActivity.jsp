<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
        th{width: 120px}
    </style>
    <script type="text/javascript">

        function reChoose(obj){
            $(obj).hide();
            $("#isRe").val("Y");
            $("#down").hide();
            $("#file").show();
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
                var url = '<s:url value="/jsres/activityQuery/loadTeacherAndHead"/>';
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

    </script>
</head>
<body>
<div class="mainright" style="overflow: auto;">
    <div class="basic" style="max-height: 435px;" >
        <form id="addForm" style="position: relative;">
            <input name="activityFlow" type="hidden" value="${activity.activityFlow}"  />
            <input name="fileFlow" type="hidden" value="${activity.fileFlow}"  />
            <input name="speakerFlow" id="speakerFlowId" type="hidden" />
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;">活动名称：</th>
                    <td colspan="3">
                        ${activity.activityName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">开始时间：</th>
                    <td>
                         ${activity.startTime}
                    </td>
                    <th style="text-align: right;">结束时间：</th>
                    <td>
                       ${activity.endTime}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">活动形式：</th>
                    <td>
                        <c:forEach items="${activityTypeEnumList}" var="a">
                            <c:if test="${activity.activityTypeId eq a.id}">${a.name}</c:if>
                        </c:forEach>
                    </td>
                    <th style="text-align: right;">所在科室：</th>
                    <td>
                        <c:forEach items="${depts}" var="dept">
                            <c:if test="${activity.deptFlow eq dept.deptFlow}">${dept.deptName}</c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">主&nbsp;讲&nbsp;人&nbsp;：</th>
                    <td>
                        ${user.userName}
                    </td>
                    <th style="text-align: right;">联系方式：</th>
                    <td>${activity.speakerPhone}</td>
                </tr>
                <tr>
                    <th style="text-align: right;">身份证号：</th>
                    <td style="width: 273px;margin: 0 5px;" colspan="3">
                        ${user.idNo}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">活动地点：</th>
                    <td colspan="3">
                        <textarea type="text" <c:if test="${activity.activityStatus eq 'pass'}">readonly</c:if> name="activityAddress" class="validate[required,maxSize[500]]" style="width: 672px;margin: 5px 0;max-width:100%;height: 60px">${activity.activityAddress}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">活动简介：</th>
                    <td colspan="3">
                        <textarea type="text" <c:if test="${activity.activityStatus eq 'pass'}">readonly</c:if> name="activityRemark" class="validate[required,maxSize[500]]" style="width: 672px;margin: 5px 0;max-width:100%;height: 60px">${activity.activityRemark}</textarea>
                    </td>
                </tr>
                <c:if test="${empty action}">
                    <tr>
                        <th style="text-align: right;">实际主讲人:</th>
                        <td colspan="3">
                            ${activity.realitySpeaker}
                        </td>
                    </tr>
                </c:if>
                <c:if test="${action eq 'audit' or !empty activity.opinion}">
                    <th style="text-align: right;">审核意见：</th>
                    <td colspan="3">
                        <textarea type="text" name="opinion" id="opinion"  class="validate[maxSize[200]]" style="width: 672px;margin: 5px 0;max-width:100%;height: 60px";>${activity.opinion}</textarea>
                    </td>
                </c:if>
            </table>
            <c:if test="${empty action}">
                <table class="grid" style="width:100%;">
                    <thead>
                    <tr>
                        <td width="100%" style="font-weight:bold;">附件名称</td>
                    </tr>
                    </thead>
                    <tbody id="activityFileTb">
                    <c:forEach var="file" items="${fileList}" varStatus="status">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty file.fileFlow}">
                                        <a id="down"
                                           href='<s:url value="/jsres/activityQuery/downFile?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="file" id="file" name="files"/>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" name="fileFlow" value="${file.fileFlow}" onchange="checkFile(this)"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </form>
        <c:if test="${empty action}">
            <p style="text-align: center;margin-top: 10px">

                <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
            </p>
        </c:if>
    </div>
</div>
</body>
</html>
