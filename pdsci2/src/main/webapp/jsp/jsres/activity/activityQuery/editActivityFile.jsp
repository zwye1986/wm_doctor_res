<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            var url = "<s:url value='/jsres/activityQuery/saveActivityFiles'/>";
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
                '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);" >[删除]</a></td>' +
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
    </script>
</head>
<body>
<div class="mainright" style="overflow: auto;">
    <div class="basic" style="max-height: 360px;overflow-y: auto;" >
        <form id="addForm" style="position: relative;">
            <input name="activityFlow" type="hidden" value="${activity.activityFlow}"  />
            <input name="fileFlow" type="hidden" value="${activity.fileFlow}"  />

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
                    <c:if test="${!empty file.fileUpType and file.fileUpType eq 'kj'}">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty file.fileFlow}">
                                        <c:if test="${file.fileName.toLowerCase().contains('jpg') || file.fileName.toLowerCase().contains('png')}">
                                            <ul>
                                                <a target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">
                                                <li class="ratateImg">

                                                        <img width="80px" height="80px" src="${sysCfgMap['upload_base_url']}${file.filePath}">

                                                </li>
                                                </a>
                                            </ul>
                                        </c:if>
                                        <c:if test="${!file.fileName.toLowerCase().contains('jpg') && !file.fileName.toLowerCase().contains('png')}">
                                            <a id="down"
                                               href='<s:url value="/jsres/activityQuery/downFile?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                        </c:if>
                                        <%--                                        <input type="file" id="file" name="files" style="display: none;"/>--%>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="file" id="kj" name="kj" onchange="checkFile(this)"/>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                            </td>
                            <td>
                                <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
          <%--  <table class="grid" style="width:100%;">
                <thead>
                <tr>
                    <th colspan="4" class="bs_name">附件信息<span style="color: red">(允许上传文件格式：doc,docx,xlsx,xls,bmp,gif,jpg,png,pdf,ppt,pptx)</span>
                        <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                style="cursor: pointer;" onclick="addZPFile('activityZPTb')"/></a></span>
                    </th>
                </tr>
                <tr>
                    <td width="60%" style="font-weight:bold;">附件名称</td>
                    <td width="40%" style="font-weight:bold;">附件操作</td>
                </tr>
                </thead>
                <tbody id="activityZPTb">
                <c:forEach var="file" items="${fileList}" varStatus="status">
                    <c:if test="${!empty file.fileUpType and file.fileUpType eq 'zp'}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${not empty file.fileFlow}">
                                    <c:if test="${file.fileName.toLowerCase().contains('jpg') || file.fileName.toLowerCase().contains('png')}">
                                        <ul>
                                            <a target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">
                                                <li class="ratateImg">
                                                    <img width="80px" height="80px" src="${sysCfgMap['upload_base_url']}${file.filePath}">
                                                </li>
                                            </a>
                                        </ul>
                                    </c:if>
                                    <c:if test="${!file.fileName.toLowerCase().contains('jpg') && !file.fileName.toLowerCase().contains('png')}">
                                        <a id="down"
                                           href='<s:url value="/jsres/activityQuery/downFile?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                    </c:if>
                                    &lt;%&ndash;                                        <input type="file" id="file" name="files" style="display: none;"/>&ndash;%&gt;
                                </c:when>
                                <c:otherwise>
                                    <input type="file" id="zp" name="zp" onchange="checkFile(this)"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                        </td>
                        <td>
                            <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                        </td>
                    </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>--%>
            <table class="grid" style="width:100%;">
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
                    <c:if test="${empty file.fileUpType}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${not empty file.fileFlow}">
                                    <c:if test="${file.fileName.toLowerCase().contains('jpg') || file.fileName.toLowerCase().contains('png')}">
                                        <ul>
                                            <li class="ratateImg">
                                                <a width="80px" height="80px" target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">
                                                    <img width="80px" height="80px" src="${sysCfgMap['upload_base_url']}${file.filePath}">
                                                </a>
                                            </li>
                                        </ul>
                                    </c:if>
                                    <c:if test="${!file.fileName.toLowerCase().contains('jpg') && !file.fileName.toLowerCase().contains('png')}">
                                        <a id="down"
                                           href='<s:url value="/jsres/activityQuery/downFile?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                    </c:if>
                                    <%--                                        <input type="file" id="file" name="files" style="display: none;"/>--%>
                                </c:when>
                                <c:otherwise>
                                    <input type="file" id="file" name="files" onchange="checkFile(this)"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                        </td>
                        <td>
                            <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                        </td>
                    </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
    <p style="text-align: center;margin-top: 10px">
        <input type="button" onclick="save('${activity.activityStatus}','${activity.submitRole}','${role}');" class="btn_green" value="保&#12288;存"/>&#12288;
        <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
    </p>
</div>
</body>
</html>
