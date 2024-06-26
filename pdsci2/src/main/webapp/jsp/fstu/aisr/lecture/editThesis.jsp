<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">

        function saveThesis(){
            if(false==$("#srmThesis").validationEngine("validate")){
                return false;
            }
            if(false==$("#list").validationEngine("validate")){
                return false;
            }
            $("#mubiao input[name='isCorresponding']").each(function(){
                if(this.checked==true){
                    this.value=1;
                }else{
                    this.value=0;
                }
            });
            jboxConfirm("确认保存？",function(){
                var thesis = $("#srmThesis").serializeJson();
                var fileTr = $("#projFileTb").children();
                var authTab = $('#authorList');
                var trs = authTab.children();
                var datas = [];
                $.each(trs , function(i , n){
                    var authorFlow = $(n).find("input[name='authorFlow']").val();
                    var typeId =  $(n).find("select[name='typeId']").val();
                    var isCorresponding =  $(n).find("input[name='isCorresponding']").val();
                    var authorName= $(n).find("input[name='authorName']").val();
                    var sexId =  $(n).find("select[name='sexId']").val();
                    var educationId= $(n).find("select[name='educationId']").val();
                    var titleId= $(n).find("select[name='titleId']").val();
                    var degreeId= $(n).find("select[name='degreeId']").val();
                    var data = {
                        "authorFlow":authorFlow,
                        "typeId":typeId,
                        "isCorresponding":isCorresponding,
                        "authorName":authorName,
                        "sexId":sexId,
                        "educationId":educationId,
                        "titleId":titleId,
                        "degreeId":degreeId
                    };
                    datas.push(data);
                });
                var fileDatas = [];
                $.each(fileTr, function (i, n) {
                    var fileFlow = $(n).find("input[name='fileFlow']").val();
                    var fileRemark = $(n).find("input[name='fileRemark']").val();
                    var pubFlie = {
                        "fileFlow": fileFlow,
                        "fileRemark": fileRemark
                    };
                    fileDatas.push(pubFlie);
                });
                var t = {'authorList':datas,'thesis':thesis,'fileList': fileDatas};
                //var temp = {'jsondata':JSON.stringify(t)};
                $('#jsondata').val(JSON.stringify(t));
                var url = "<s:url value='/fstu/thesis/save'/>";
                jboxStartLoading();
                jboxSubmit($('#srmThesis'),url,function(resp){
                            window.parent.frames['mainIframe'].location.reload(true);
                            jboxClose();
                        },
                        function(resp){
                            alert("error");
                        });
            });

        }

        function addAuthor(){
            $('#authorList').append($("#moban tr:eq(0)").clone());
        }

        function delAuthorTr(obj){
            jboxConfirm("确认删除？" , function(){
                var tr=obj.parentNode.parentNode;
                tr.remove();
            });
        }

        function delAuthor(authorFlow,obj){
            var url = '<s:url value="/fstu/thesis/deleteAuthor?authorFlow="/>' + authorFlow;
            jboxConfirm("确认删除？" , function(){
                jboxGet(url , null , function(){
                    var tr=$(obj).parent("td").parent("tr");
                    tr.remove();
                } , null , true);
            });
        }

        function change(obj){
            $(".a").attr("disabled",true);
            if(obj.getAttribute("thesisTypeName")=="会议"){
                $(".a").attr("disabled",false);
            }else{
                $(".a").attr("checked",false);
            }
        }

        function reCheck(obj){
            $(obj).hide();
            $("#down").hide();
            $("#file").show();
        }

        $(function(){
            <c:if test="${param.editFlag == GlobalConstant.FLAG_N}">
//            $('#look input[type!=button]').attr('disabled' , "disabled");
//            $('#look textarea').attr('readonly' , "readonly");
//            $('#look select').attr('disabled' , "disabled");
            $(".reCheck").css("display", "none");
            </c:if>
        });

        function getISSN(issnCode){
            if("" == issnCode){
                return false;
            }
            var url = "<s:url value = '/fstu/impactorFactor/getImpactorFactorByISSN?issn='/>"+ issnCode;
            jboxGet(url ,null , function(data){
                if('' != data){
                    var impactFactor = data.impactFactor;
                    $("#issnCode").text("impact Factor：" + impactFactor + "(" + data.year +"年)");
                }else{
                    $("#issnCode").text("暂无impact Factor记录！");
                }
            }, null , false);
        }

        //刊物类型
        $(document).ready(function(){
            var jti = $("#jtn").val();
            if(jti == null || jti == "" || jti == undefined){
                return false;
            }
            var jtis = jti.split(",");
            $("input[name='jourTypeId']").each(function(){
                for(var i=0; i<jtis.length; i++){
                    if(jtis[i]==this.value){
                        this.checked=true;
                    }
                }
            });
        });
        function strChar(obj, limitLength) {
            var str = $(obj).val().trim();
            var len = 0;
            for (var i = 0; i < str.length; i++) {
                if (str[i].match(/[^\x00-\xff]/ig) != null) {  //全角
                    len += 2;
                }
                else
                    len += 1;
            }
            if (len > parseInt(limitLength)) {
                jboxTip("当前字符长度超过限制");
                $(obj).attr({style:"color:red"});
                $("#saveBtn").attr("disabled",true);
            }else{
                $(obj).attr({style:"color:black"});
                $("#saveBtn").attr("disabled",false);
            }
        }
        $(function(){
            var url = "<s:url value='/fstu/thesis/searchDept'/>";
            var selectedFlow="${thesis.deptFlow}";
            jboxPost(url,null,function(resp){
                $.each(resp,function(i,n){
                    if(selectedFlow == n.deptFlow){
                        $("#selectDept").append("<option selected='selected' value='"+ n.deptFlow +"'>"+ n.deptName +"</option>");
                    }else {
                        $("#selectDept").append("<option  value='" + n.deptFlow + "'>" + n.deptName + "</option>");
                    }
                });
            },null,false);
        });
        function getDeptName(obj){
            $("#deptName").val($(obj).find("option:selected").text());
        }
        <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
        $(document).ready(function () {
            if ($("#projFileTb tr").length <= 0) {
                addFile('projFileTb');
            }
        });
        </c:if>
        function addFile(tb) {
            if ($("#projFileTb tr").length > 9) {
                jboxTip("附件总数不能超过10个！");
                return false;
            }
            var html = '<tr>' +
                    '<td><input type="file" id="file" name="files" class="validate[required]"/></td>' +
                    '<td><input class="validate[required,maxSize[100]] xltext" style="width: 97%;" name="fileRemark" type="text"/></td>' +
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
        function backOpt(){
            var url = "<s:url value='/fstu/thesis/saveAudit?thesisFlow=${param.thesisFlow}&agreeFlag=N'/>";
            jboxConfirm("确认退回?" ,  function(){
                jboxStartLoading();
                jboxPost(url , null , function(resp){
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                } , null , true);
            });
        }
    </script>
</head>

<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div id="look">
                <form id="srmThesis" action="<s:url value="/fstu/thesis/save"/>" method="post" style="position: relative;" enctype="multipart/form-data">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <table class="basic" style="width: 100%">
                        <tr class="bs_name" style="height: 26px">
                            <td style="text-align:left" colspan="4">基本信息</td>
                        </tr>
                        <tr>
                            <th>论文题目：</th>
                            <td colspan="3">
                                <input class="validate[required,maxSize[100]] name xltext" type="text" name="thesisName" value="${thesis.thesisName}" style="width: 97%;"/>
                            </td>
                        </tr>
                        <tr>
                            <th>发表/出版日期：</th>
                            <td>
                                <input class="xltext ctime" style="width:160px;" type="text" name="publishDate" value="${thesis.publishDate}" onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"/>
                            </td>
                            <th>发表刊物/论文集：</th>
                            <td>
                                <input class="xltext" type="text" name="publishJour" value="${thesis.publishJour}">
                            </td>
                        </tr>
                        <tr>
                            <th>刊物类型：</th>
                            <td colspan="3">
                                <c:if test="${not empty thesis}">
                                    <input type="hidden" id="jtn" value="${thesis.jourTypeId}">
                                </c:if>

                                <c:forEach items="${dictTypeEnumFstuJournalTypeList}" var="dict">
                                    <input type="checkbox" class="name"  name="jourTypeId" id="JournalType_${dict.dictId}" value="${dict.dictId}"/><label for="JournalType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th>项目来源：</th>
                            <td>
                                <select name="projSourceId" class="xlselect">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumProSourceList}" var="dict">
                                        <option value="${dict.dictId}" <c:if test="${thesis.projSourceId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>是否为译文：</th>
                            <td>
                                <input type="radio" name="isTranslated" id="isTranslated1"  <c:if test="${thesis.isTranslated eq 1}"> checked="checked"</c:if> value="1"/> <label for="isTranslated1">&nbsp;是</label>&nbsp;
                                <input type="radio" name="isTranslated" id="isTranslated0" <c:if test="${thesis.isTranslated eq 0}"> checked="checked"</c:if> value="0"/><label for="isTranslated0">&nbsp;否</label>&nbsp;
                            </td>
                            <%--<c:if test="${applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">--%>
                                <%--<th>所在科室：</th>--%>
                                <%--<td>--%>
                                    <%--<input type="hidden" id="deptName" name="deptName" value="${thesis.deptName}"/>--%>
                                    <%--<select name="deptFlow" id="selectDept" class="xlselect" onchange="getDeptName(this);">--%>
                                        <%--<option value="">请选择</option>--%>
                                    <%--</select>--%>
                                <%--</td>--%>
                            <%--</c:if>--%>
                            <%-- <th><!-- 项目类型： --></th>
                             <td>
                                 &lt;%&ndash; <select name="projTypeId" class="xlselect">
                                     <option value="">请选择</option>
                                     <c:forEach items="${dictTypeEnumProjTypeList}" var="dict">
                                     <option value="${dict.dictId}" <c:if test="${thesis.projTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                     </c:forEach>
                                 </select> &ndash;%&gt;
                             </td>--%>
                        </tr>
                        <tr>
                            <th>发表范围：</th>
                            <td>
                                <c:forEach items="${dictTypeEnumFstuPublishScopeList}" var="dict">
                                    <input type="radio" name="publishScopeId" id="publishScope_${dict.dictId}" value="${dict.dictId}" <c:if test="${thesis.publishScopeId eq dict.dictId}">checked="checked"</c:if>/><label for="publishScope_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th>学科门类：</th>
                            <td>
                                <c:forEach items="${dictTypeEnumFstuSubjectTypeList}" var="dict">
                                    <input type="radio" name="subjectTypeId" id="subjectType_${dict.dictId}" value="${dict.dictId}" <c:if test="${thesis.subjectTypeId eq dict.dictId}">checked="checked"</c:if>/><label for="subjectType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th>是否为科技核心期刊：</th>
                            <td>
                                <input type="radio" name="isCoreJour" id="isCoreJour1" <c:if test="${thesis.isCoreJour eq 1}">checked="checked"</c:if> value="1" /><label for="isCoreJour1">&nbsp;是</label>&nbsp;
                                <input type="radio" name="isCoreJour" id="isCoreJour2"  <c:if test="${thesis.isCoreJour eq 0}">checked="checked"</c:if> value="0"/><label for="isCoreJour2">&nbsp;否</label>&nbsp;
                            </td>
                            <th>CN号：</th>
                            <td>
                                <input class="xltext validate[maxSize[20]]" type="text" name="cnCode" value="${thesis.cnCode}" />
                                <%-- thesisFlow --%>
                                <input type="hidden" name="thesisFlow" value="${thesis.thesisFlow}"/>
                            </td>
                        </tr>
                        <tr>
                            <th>期号：</th>
                            <td><input class="xltext validate[maxSize[20]]" type="text" name="jourCode" value="${thesis.jourCode}"></td>
                            <th>卷号：</th>
                            <td><input class="xltext validate[maxSize[20]]" type="text" name="volumeCode" value="${thesis.volumeCode}"/></td>
                        </tr>
                        <tr>
                            <th>页码范围：</th>
                            <td><input class="xltext" type="text" name="pageNoRange" value="${thesis.pageNoRange}"></td>
                            <th>字数：</th>
                            <td><input class="xltext" type="text" name="wordCount" value="${thesis.wordCount}" style="margin-right: 0px;">万字</td>
                        </tr>
                        <tr>
                            <%-- <th>医院署名：</th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumOrgBelongList}">
                                    <input type="radio" name="hospSignId" id="HosptialSign_${dict.dictId}" value="${dict.dictId}"  <c:if test="${thesis.hospSignId eq dict.dictId}">checked="checked"</c:if>/><label for="HosptialSign_${dict.dictId}">${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td> --%>
                            <th>版面：</th>
                            <td>
                                <c:forEach var="dict" items="${dictTypeEnumFstuPublishTypeList}">
                                    <input type="radio" name="publishTypeId" id="PublishType_${dict.dictId}" value="${dict.dictId}" <c:if test="${thesis.publishTypeId eq dict.dictId}">checked="checked"</c:if>/><label for="PublishType_${dict.dictId}">&nbsp;${dict.dictName}</label>&nbsp;
                                </c:forEach>
                            </td>
                            <th>分值：</th>
                            <td>
                                <input class="xltext validate[custom[number]]" type="text" name="scoreValue" value="${thesis.scoreValue}" style="margin-right: 0px;">
                            </td>
                        </tr>
                        <tr>
                            <th>ISSN号：</th>
                            <td>
                                <input class="xltext" type="text" name="issnCode" value="${thesis.issnCode}" onblur="getISSN(this.value);" style="margin-right: 0px;">
                                <b id="issnCode" style="color: red;margin-left: 0px; text-decoration: none;"></b>
                            </td>
                            <th></th>
                            <td></td>
                        </tr>
                    </table>

                    <table class="basic" style="width: 100%">
                        <thead>
                        <tr>
                            <th colspan="2" class="bs_name">备注信息</th>
                        </tr>
                        </thead>
                        <tr>
                            <th width="20%">关键字：</th>
                            <td><input type="text" class="xltext" style="width: 97%;" name="keyWord"  placeholder="例如：关键字1 关键字2(50个字以内)" value="${thesis.keyWord}" onkeyup="strChar(this,100)"/></td>
                        </tr>
                        <tr>
                            <th width="20%">论文摘要：</th>
                            <td><textarea class="xltxtarea" style="margin-left: 0px;" maxlength="1000" placeholder="500个字以内" name="summary" onkeyup="strChar(this,1000)">${thesis.summary}</textarea></td>
                        </tr>
                        <tr>
                            <th width="20%">备注信息：</th>
                            <td><textarea class="xltxtarea" style="margin-left: 0px;" placeholder="50个字以内" name="remark" onkeyup="strChar(this,100)">${thesis.remark}</textarea></td>
                        </tr>
                    </table>

                    <table class="basic" style="width:100%;">
                        <thead>
                        <tr>
                            <th colspan="4" class="bs_name">附件${(param.editFlag != GlobalConstant.FLAG_N)?'上传':'查看'}
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                            title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                            style="cursor: pointer;" onclick="addFile('projFileTb')"/></a></span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <td width="30%" style="font-weight:bold;">附件名称</td>
                            <td width="40%" style="font-weight:bold;">附件内容</td>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                            <td width="20%" style="font-weight:bold;">附件操作</td>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody id="projFileTb">
                        <c:forEach var="file" items="${fileList}" varStatus="status">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty file.fileFlow}">
                                            <a id="down"  href='<s:url value="/fstu/thesis/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                            <c:if test="${fn:containsIgnoreCase(file.fileName,'.jpg')||fn:containsIgnoreCase(file.fileName,'.jpeg')||fn:containsIgnoreCase(file.fileName,'.gif')||fn:containsIgnoreCase(file.fileName,'.png')||fn:containsIgnoreCase(file.fileName,'.bmp')}">
                                                <a id="down" target="_blank" href='${file.filePath}'>查看图片</a>
                                            </c:if>
                                            <input type="file" id="file" name="files" style="display: none;"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="file" id="file" name="files"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                                </td>
                                <td>
                                    <input class="validate[required,maxSize[100]] xltext" style="width: 97%;"
                                           name="fileRemark" type="text" value="${file.fileRemark}"/>
                                </td>
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <td>
                                        <a class="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>&#12288;
                                        <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>

                <form id="list">
                    <table class="basic" id="mubiao" style="width: 100%">
                        <tr>
                            <th colspan="8" class="bs_name"> 论文作者<c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <%--<c:if test="${not applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">--%>
                                <a href="javascript:void(0)"><img src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                                                  onclick="addAuthor();" title="添加"></img></a></c:if>
                                <%--</c:if>--%>
                            </th>
                        </tr>
                        <tr>
                            <th style="text-align: center; width: 10%;">作者类型</th>
                            <th style="text-align: center; width: 10%;">通讯作者</th>
                            <th style="text-align: center; width: 10%;">作者姓名</th>
                            <th style="text-align: center; width: 10%;">性别</th>
                            <th style="text-align: center; width: 10%;">学历</th>
                            <th style="text-align: center; width: 10%;">学位</th>
                            <th style="text-align: center; width: 10%;">职称</th>
                            <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                <th style="text-align: center; width: 10%;">操作</th>
                            </c:if>
                        </tr>
                        <tbody id="authorList">
                        <%-- 默认作者 --%>
                        <c:if test="${empty param.thesisFlow}">
                            <tr>
                                <td>
                                    <select name="typeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumFstuAuthorTypeList }">
                                            <option value="${dict.dictId }">${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td style="text-align: center;">
                                    <input type="checkbox" name="isCorresponding" id="${dict.dictId}" value="0" <c:if test="${author.isCorresponding eq 1}">checked="checked"</c:if> onclick="check(this)"/>
                                </td>
                                <td>
                                    <input class="xltext" style="width: 85%;"  type="text" name="authorName" value="${sessionScope.currUser.userName}"/>
                                </td>
                                <td>
                                    <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="sex" items="${userSexEnumList}">
                                            <c:if test="${sex.id != userSexEnumUnknown.id}">
                                                <option value="${sex.id}" <c:if test="${sessionScope.currUser.sexId eq sex.id}">selected="selected"</c:if>>${sex.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="educationId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                            <option value="${dict.dictId}" <c:if test="${sessionScope.currUser.educationId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="degreeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                            <option value="${dict.dictId}" <c:if test="${sessionScope.currUser.degreeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="titleId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                            <option value="${dict.dictId}" <c:if test="${sessionScope.currUser.titleId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                    <%--<c:if test="${not applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">--%>
                                <td style="text-align: center;">
                                    [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                                </td>
                                    <%--</c:if>--%>
                            </tr>
                        </c:if>

                        <c:forEach var="author" items="${authorList}">
                            <tr>
                                <td>
                                    <input type="hidden" name="authorFlow" value="${author.authorFlow}"/>

                                    <select name="typeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumFstuAuthorTypeList }">
                                            <option value="${dict.dictId }" <c:if test="${author.typeId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td style="text-align: center;">
                                    <input type="checkbox" name="isCorresponding" id="${dict.dictId}" value="0" <c:if test="${author.isCorresponding eq 1}">checked="checked"</c:if>/>
                                </td>
                                <td>
                                    <input class="xltext" style="width: 85%;"  type="text" name="authorName" value="${author.authorName}"/>
                                </td>
                                <td>
                                    <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="sex" items="${userSexEnumList}">
                                            <c:if test="${sex.id != userSexEnumUnknown.id}">
                                                <option value="${sex.id}" <c:if test="${author.sexId eq sex.id}">selected="selected"</c:if>>${sex.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="educationId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                            <option value="${dict.dictId}" <c:if test="${author.educationId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="degreeId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                            <option value="${dict.dictId}" <c:if test="${author.degreeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="titleId" class="xlselect" style="width: 85%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                            <option value="${dict.dictId}" <c:if test="${author.titleId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                    <%--<c:if test="${not applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">--%>
                                <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                                    <td style="text-align: center;">
                                        [<a onclick="delAuthor('${author.authorFlow}',this)" style="cursor: pointer;">删除</a>]
                                    </td>
                                </c:if>
                                    <%--</c:if>--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>

                <p align="center">
                    <c:if test="${param.editFlag != GlobalConstant.FLAG_N}">
                        <input type="button" id="saveBtn" value="保&#12288;存" onclick="saveThesis();" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>
                    <c:if test="${thesis.operStatusId eq 'Passed'}">
                        <input type="button" value="退&#12288;回" onclick="backOpt();" class="search"/>
                        <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
                    </c:if>
                </p>


                <table class="basic" id="moban" style="display: none" style="width: 100%">
                    <tr>
                        <td>
                            <select name="typeId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumFstuAuthorTypeList }">
                                    <option value="${dict.dictId }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            <input type="checkbox" name="isCorresponding" id="${dict.dictId}" value="0" <c:if test="${author.isCorresponding eq 1}">checked="checked"</c:if> onclick="check(this)"/>
                        </td>
                        <td>
                            <input class="validate[required] xltext" style="width: 85%;" type="text" name="authorName" value="${author.authorName}"/>
                        </td>
                        <td>
                            <select name="sexId" class="validate[required] xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="sex" items="${userSexEnumList}">
                                    <c:if test="${sex.id != userSexEnumUnknown.id}">
                                        <option value="${sex.id}" >${sex.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="educationId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="degreeId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                    <option value="${dict.dictId}" >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="titleId" class="xlselect" style="width: 85%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            [<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>