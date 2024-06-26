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
    </style>
    <script type="text/javascript">

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
            var url = "<s:url value='/jszy/base/activityQuery/saveActivity'/>";
            jboxStartLoading();
            jboxSubmit($('#addForm'),url,function(resp) {
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.toPage(1);
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
                var url = '<s:url value="/jszy/base/activityQuery/loadTeacherAndHead"/>';
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
<div class="mainright">
    <div class="basic" style="max-height: 392px;overflow: auto;">
        <form id="addForm" style="position: relative;">
            <input name="activityFlow" type="hidden" value="${activity.activityFlow}"  />
            <input name="fileFlow" type="hidden" value="${activity.fileFlow}"  />
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;width: 88px;"><font color="red" >*</font>活动名称：</th>
                    <td colspan="3">
                        <input name="activityName" id="activityName" style="width: 455px" class="input validate[required,maxSize[200]]" value="${activity.activityName}"  />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>开始时间：</th>
                    <td>
                        <input type="text" id="startDate" name="startTime" value="${activity.startTime}" style="width: 150px;" class="input validate[required]"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>结束时间：</th>
                    <td>
                        <input type="text" id="endDate" name="endTime" value="${activity.endTime}" style="width: 150px;" class="input validate[required]"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>活动形式：</th>
                    <td>
                        <select name="activityTypeId" style="width: 156px;margin: 0 5px;" class="select validate[required]">
                            <option value="">请选择</option>
                            <c:forEach items="${activityTypeEnumList}" var="a">
                                <option value="${a.id}" ${(activity.activityTypeId eq a.id) ?'selected':''}>${a.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>所在科室：</th>
                    <td>
                        <select name="deptFlow" style="width: 156px;margin: 0 5px;" class="select validate[required]"<c:if test="${role eq 'head'}">onchange="loadDeptUsers(this.value)"</c:if> >
                            <option value="">请选择</option>
                            <c:forEach items="${depts}" var="dept">
                                <option value="${dept.deptFlow}" ${(activity.deptFlow eq dept.deptFlow) ?'selected':''}>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>主&nbsp;讲&nbsp;人&nbsp;：</th>
                    <td>
                        <c:if test="${role eq 'head'}">
                            <select name="speakerFlow" id="speakerFlow" style="width: 156px;margin: 0 5px;" class="select validate[required]" onchange="showUserPhone(this.value)" >
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
                        <input name="speakerPhone" id="speakerPhone"style="width: 150px;" class="input validate[required,custom[number]]" value="${activity.speakerPhone}"  />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><c:if test="${role ne 'head'}"><font color="red" >*</font></c:if>活动地点：</th>
                    <td colspan="3">
                        <textarea type="text" name="activityAddress" class="validate[<c:if test="${role ne 'head'}">required,</c:if>maxSize[500]]" style="width: 464px;margin: 5 5px;height: 60px">${activity.activityAddress}</textarea>

                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><c:if test="${role ne 'head'}"><font color="red" >*</font></c:if>活动简介：</th>
                    <td colspan="3">
                        <textarea type="text" name="activityRemark" class="validate[<c:if test="${role ne 'head'}">required,</c:if>maxSize[500]]" style="width: 464px;margin: 5 5px;height: 60px">${activity.activityRemark}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">附&#12288;&#12288;件：</th>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${not empty activity.fileFlow}">
                                <a id="down" href="<s:url value='/jsres/activityQuery/downFile'/>?fileFlow=${activity.fileFlow}">${file.fileName}</a>
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
        <p style="text-align: center;">
            <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
            <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
