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

        function save(activityStatus,submitRole,role) {
            if(false==$("#addForm").validationEngine("validate")){
                return false;
            }
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            if(startDate >= endDate){
                jboxTip("开始时间不得大于等于结束时间！");
                return;
            }
            if(activityStatus=="unpass" && $("#opinion").val()==""){
                jboxTip("审核不通过时，必须填写审核意见！");
                return;
            }
            jboxStartLoading();
            /* 解决：科主任已经通过审核的活动 页面disabled 属性无法将 speakerFlow 值传入后台 导致根据主讲人查询时间是否重叠sql报错*/
            <c:if test="${role eq 'head' or role eq 'secretary'}">
                var speakerFlow = $("#speakerFlow").val();
                $("#speakerFlowId").val(speakerFlow);
            </c:if>
            <c:if test="${role eq 'teach'}">
                $("#speakerFlowId").val("${user.userFlow}");
            </c:if>
            if(typeof(activityStatus)=="undefined" || typeof(submitRole)=="undefined"||activityStatus==""||submitRole==""){
                var url = "<s:url value='/jsres/activityQuery/saveActivityNew'/>?role="+role;
            }else{
                var url = "<s:url value='/jsres/activityQuery/saveActivityNew'/>?activityStatus="+activityStatus+"&submitRole="+submitRole+"&role="+role;
            }
            var Start = new Date(startDate);
            var end = new Date(endDate);
            if((end - Start)/3600000 > 2 && (typeof(activityStatus)=="undefined" || activityStatus=="audit")){
            jboxConfirm("当前教学活动时长超过两小时，请确认", function(){
            jboxSubmit($('#addForm'),url,function(resp) {
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.toPage(${currentPage});
                    setTimeout(function(){
                        jboxClose();
                    },2000);
                }
                }, null, true);
                });
            }else{
                jboxSubmit($('#addForm'),url,function(resp) {
                    jboxEndLoading();
                    if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                        window.parent.toPage(${currentPage});
                        setTimeout(function(){
                            jboxClose();
                        },2000);
                    }
                }, null, true);
            }
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
        function addKjFile(tb) {
            var html = '<tr>' +
                '<td><input type="file" id="kj" name="kj" class="validate[required]" onchange="checkFile(this)"/></td>' +
                '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);">[删除]</a></td>' +
                '</tr>';
            $('#' + tb).append(html);
        }

        function addZPFile(tb) {
            var html = '<tr>' +
                '<td><input type="file" id="zp" name="zp" accept=".gif,.jpg,.png"  class="validate[required]" onchange="checkFile2(this)"/></td>' +
                '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);">[删除]</a></td>' +
                '</tr>';
            $('#' + tb).append(html);
        }

        function addFile(tb) {
            var html = '<tr>' +
                '<td><input type="file" id="file" name="files" class="validate[required]" onchange="checkFile(this)"/></td>' +
                '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);">[删除]</a></td>' +
                '</tr>';
            $('#' + tb).append(html);
        }

        function delTr(tb) {
            jboxConfirm("确认删除？", function () {
                $(tb).parent('td').parent("tr").remove();
            });
        }

        function delFile(obj,fileFlow){
            var url = '<s:url value="/pub/file/delFileByFlow?fileFlow="/>' + fileFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                }, null, true);
            });
        }

        function reCheck(obj) {
            $(obj).hide();
            $("#down").hide();
            $("#file").show();
        }

        function checkFile(obj){
            var array = new Array('doc', 'docx', 'xlsx', 'xls', 'bmp', 'gif', 'jpg', 'png', 'pdf', 'ppt', 'pptx');  //可以上传的文件类型
            if (obj.value == '') {
                jboxTip("让选择要上传的文件！");
                return false;
            } else {
                var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用
                var isExists = false;
                for (var i in array) {
                    if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists == false) {
                    obj.value = null;
                    jboxTip("上传文件类型不正确！");
                    return false;
                }
            }
        }

        function checkFile2(obj){
            var array = new Array( 'gif', 'jpg', 'png');  //可以上传的文件类型
            if (obj.value == '') {
                jboxTip("让选择要上传的文件！");
                return false;
            } else {
                var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用
                var isExists = false;
                for (var i in array) {
                    if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists == false) {
                    obj.value = null;
                    jboxTip("上传文件类型不正确！");
                    return false;
                }
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
                    <th style="text-align: right;"><font color="red" >*</font>活动名称：</th>
                    <td colspan="3">
                        <input name="activityName"  <c:if test="${activity.activityStatus eq 'pass'}">readonly</c:if> id="activityName" style="width: 662px" class="input validate[required,maxSize[200]]" value="${activity.activityName}"  />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>开始时间：</th>
                    <td>
                        <input type="text" id="startDate" name="startTime" value="${activity.startTime}" style="width: 267px;" class="input validate[required]"  readonly="readonly" <c:if test="${activity.activityStatus eq 'audit' or empty activity.activityStatus}">onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" </c:if>>
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>结束时间：</th>
                    <td>
                        <input type="text" id="endDate" name="endTime" value="${activity.endTime}" style="width: 267px;" class="input validate[required]"  readonly="readonly" <c:if test="${activity.activityStatus eq 'audit' or empty activity.activityStatus}">onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"</c:if>>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>活动形式：</th>
                    <td>
                        <select name="activityTypeId" <c:if test="${activity.activityStatus eq 'pass'}">disabled="disabled"</c:if> style="width: 273px;margin: 0 5px;" class="select validate[required]">
                            <option value="">请选择</option>
                            <c:forEach items="${activityTypeEnumList}" var="a">
                                <option value="${a.id}" ${(activity.activityTypeId eq a.id) ?'selected':''}>${a.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>所在科室：</th>
                    <td>
                        <select name="deptFlow" <c:if test="${activity.activityStatus eq 'pass'}">disabled="disabled"</c:if> style="width: 273px;margin: 0 5px;" class="select validate[required]"<c:if test="${role eq 'head' or role eq 'secretary'}">onchange="loadDeptUsers(this.value)"</c:if> >
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
                        <c:if test="${role eq 'head' or role eq 'secretary'}">
                            <select  <c:if test="${activity.activityStatus eq 'pass' and scanNum > 0 and editTeach ne 'Y'}">disabled="disabled"</c:if> id="speakerFlow" style="width: 273px;margin: 0 5px;" class="select validate[required]" onchange="showUserPhone(this.value)" >
                                <option value="">请选择</option>
                                <c:forEach items="${userList}" var="user">
                                    <option value="${user.userFlow}" phone="${user.userPhone}" ${(activity.speakerFlow eq user.userFlow) ?'selected':''}>${user.userName}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${role eq 'teach'}">
                            <input value="${user.userName}" class="input" style="width: 267px;" disabled="disabled"/>
                         </c:if>
                    </td>
                    <th style="text-align: right;"><font color="red" >*</font>联系方式：</th>
                    <td>
                        <input name="speakerPhone" <c:if test="${activity.activityStatus eq 'pass' and scanNum > 0 and editTeach ne 'Y'}">readonly</c:if> id="speakerPhone"style="width: 267px;" class="input validate[required,custom[number]]" value="${activity.speakerPhone}"  />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>活动地点：</th>
                    <td colspan="3">
                        <textarea type="text" <c:if test="${activity.activityStatus eq 'pass'}">readonly</c:if> name="activityAddress" class="validate[required,maxSize[500]]" style="width: 672px;margin: 5px 0;max-width:100%;height: 60px">${activity.activityAddress}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>活动简介：</th>
                    <td colspan="3">
                        <textarea type="text" <c:if test="${activity.activityStatus eq 'pass'}">readonly</c:if> name="activityRemark" class="validate[required,maxSize[500]]" style="width: 672px;margin: 5px 0;max-width:100%;height: 60px">${activity.activityRemark}</textarea>
                    </td>
                </tr>
                <c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_teach_show" var="keyinfo"/>
                <c:set value="${pdfn:jsresPowerCfgMap(keyinfo)}" var="keyinfovalue"/>
                <c:if test="${empty action and keyinfovalue ne 'N'}">
                    <tr>
                        <th style="text-align: right;">实际主讲人:</th>
                        <td colspan="3">
                            <input name="realitySpeaker" id="realitySpeaker" <c:if test="${editZjr ne 'Y' and scanNum >0}">disabled="disabled"</c:if> style="width: 660px" type="text" class="input" value="${activity.realitySpeaker}"  />
                        </td>
                    </tr>
                </c:if>
<%--                <c:if test="${empty action}">--%>
<%--                    <tr>--%>
<%--                        <th style="text-align: right;">附&#12288;&#12288;件：</th>--%>
<%--                        <td style="max-width: 300px;">--%>
<%--                            <c:choose>--%>
<%--                                <c:when test="${not empty activity.fileFlow}">--%>
<%--                                    <a style="width: 273px;display: inline-block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;" title=${file.fileName} id="down" href="<s:url value='/jsres/activityQuery/downFile'/>?fileFlow=${activity.fileFlow}">${file.fileName}</a>--%>
<%--                                    <input type="file" class="" id="file" name="file" style="display: none;max-width: 300px"/>--%>
<%--                                    <input type="text" id="isRe" class="" name="isRe" style="display: none;" />--%>
<%--                                    <span style="float: right; padding-right: 10px;">--%>
<%--                                        <a id="reCheck" href="javascript:void(0);" onclick="reChoose(this);" style="color: blue;">[重新选择文件]</a>--%>
<%--                                    </span>--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
<%--                                    <input type="file" class="" id="file" name="file" style="max-width: 300px"/>--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:if>--%>
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
                        <th colspan="4" class="bs_name">课件<span style="color: red">(允许上传课件格式：doc,docx,xlsx,xls,bmp,gif,jpg,png,pdf,ppt,pptx)</span>
                            <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                    title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                    style="cursor: pointer;" onclick="addKjFile('activityKJTb')"/></a></span>
                        </th>
                    </tr>
                    <tr>
                        <td width="60%" style="font-weight:bold;">课件名称</td>
                        <td width="40%" style="font-weight:bold;">操作</td>
                    </tr>
                    </thead>
                    <tbody id="activityKJTb">
                    <c:forEach var="file" items="${fileList}" varStatus="status">
                        <c:if test="${not empty file.fileFlow and  file.fileUpType eq 'kj'}">
                            <tr>
                                <td>
                                    <a id="down" href='<s:url value="/jsres/activityQuery/downFile?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                </td>
                                <td>
                                    <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>

              <%--  <table class="grid" style="width:100%;margin-top: 10px;">
                    <thead>
                    <tr>
                        <th colspan="4" class="bs_name">照片<span>(照片大小小于2M)</span>
                            <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                    title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                    style="cursor: pointer;" onclick="addZPFile('activityZPTb')"/></a></span>
                        </th>
                    </tr>
                    <tr>
                        <td width="60%" style="font-weight:bold;">照片名称</td>
                        <td width="40%" style="font-weight:bold;">操作</td>
                    </tr>
                    </thead>
                    <tbody id="activityZPTb">
                    <c:forEach var="file" items="${fileList}" varStatus="status">
                        <c:if test="${not empty file.fileFlow and  file.fileUpType eq 'zp'}">
                            <tr>
                                <td>
                                    <a id="down" href='<s:url value="/jsres/activityQuery/downFile?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                </td>
                                <td>
                                    <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>--%>

                <table class="grid" style="width:100%;margin-top: 10px;">
                    <thead>
                    <tr>
                        <th colspan="4" class="bs_name">附件信息<span style="color: red">(允许上传文件格式：doc,docx,xlsx,xls,bmp,gif,jpg,png,pdf,ppt,pptx)</span>
                            <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                    title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                    style="cursor: pointer;" onclick="addFile('activityFileTb')"/></a></span>
                        </th>
                    </tr>
                    <tr>
                        <td width="60%" style="font-weight:bold;">附件名称</td>
                        <td width="40%" style="font-weight:bold;">操作</td>
                    </tr>
                    </thead>
                    <tbody id="activityFileTb">
                    <c:forEach var="file" items="${fileList}" varStatus="status">
                        <c:if test="${not empty file.fileFlow and empty file.fileUpType}">
                            <tr>
                                <td>
                                    <a id="down" href='<s:url value="/jsres/activityQuery/downFile?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                </td>
                                <td>
                                    <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </form>
        <c:if test="${empty action}">
            <p style="text-align: center;margin-top: 10px">
                <input type="button" onclick="save('${activity.activityStatus}','${activity.submitRole}','${role}');" class="btn_green" value="保&#12288;存"/>&#12288;
                <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
            </p>
        </c:if>

        <c:if test="${!empty action}">
            <p style="text-align: center;">
                <input type="button" onclick="save('unpass','${activity.submitRole}','${role}');" class="btn_red" value="不通过"/>&#12288;
                <input type="button" onclick="save('pass','${activity.submitRole}','${role}');" class="btn_green" value="通&#12288;过"/>
            </p>
        </c:if>
    </div>
</div>
</body>
</html>
