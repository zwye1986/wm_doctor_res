<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>

    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            function back() {
                history.back();
            }

            var setting = {
                view: {
                    dblClickExpand: false,
                    showIcon: false,
                    showTitle:false,
                    selectedMulti:false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    beforeClick: beforeClick,
                    onClick: onClick
                }
            };

            function beforeClick(treeId, treeNode) {
                var check = (treeNode.id!=0);
                if (!check) jboxTip('不能选择根节点');
                return check;
            }

            function onClick(e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                        nodes = zTree.getSelectedNodes(),
                        v = "";
                id = "";
                nodes.sort(function compare(a,b){return a.id-b.id;});
                for (var i=0, l=nodes.length; i<l; i++) {
                    v += nodes[i].name + ",";
                    id += nodes[i].id + ",";
                }
                if (v.length > 0 ) v = v.substring(0, v.length-1);
                if (id.length > 0 ) id = id.substring(0, id.length-1);
                var cityObj = $("#proSelect");
                $("#selectProjId").val(id);
                cityObj.attr("value", v);
            }

            function showMenu() {
                var cityObj = $("#proSelect");
                var cityOffset = $("#proSelect").offset();
                $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

                $("body").bind("mousedown", onBodyDown);
            }
            function hideMenu() {
                $("#menuContent").fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);
            }
            function onBodyDown(event) {
                if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                    hideMenu();
                }
            }

            $(document).ready(function(){
                var url = "<s:url value='/sys/subject/getAllDataJson'/>";
                jboxPostJson(url,null,function(data){
                    if(data){
                        zNodes = $.parseJSON(data);
                        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                    }
                },null,false);
            });

            function addFile(tb){
                var html = '<tr>'+
                        '<td width="50px"><input type="checkbox"/></td>'+
                        '<td  style="height: 30px;text-align: left">&#12288;<input type="file" name="file"/></td>'+
                        '</tr>';
                $('#'+tb).append(html);
            }

            function save(){
                if(false==$("#projForm").validationEngine("validate")){
                    return false;
                }
                if(!checkBDDate()) return false;
                $("#saveBtn").attr("disabled",true);
                $("#projForm").submit();
            }

            function checkBDDate(){
                if($('#projStartTime').val() && $('#projEndTime').val() && $('#projStartTime').val() > $('#projEndTime').val()){
                    jboxTip("计划开始时间不能大于计划结束时间！");
                    return false;
                }
                return true;
            }



        </script>
    </c:if>

    <script type="text/javascript">
        $(document).ready(function() {
           // loadUsers();
            searchProjCategory();
        });


        function searchProjCategory(){
            var dictFlow = $("select[name='projDeclarerFlow'] option:selected").attr("dictFlow");
            var dictTypeId = $("#projDeclarerFlow").val();
            attrOptionText('projDeclarer',$("#projDeclarerFlow"));
            var data = {
                dictFlow : dictFlow,
                dictTypeId : dictTypeId
            };
            var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
            jboxPost(url , data , function(data){
                //清空原类别！
                $("select[name=projSecondSourceId] option[value != '']").remove();
                var dataObj = data;
                for(var i =0;i<dataObj.length;i++){
                    var cId =dataObj[i].dictId;
                    var cName =dataObj[i].dictName;
                    $option =$("<option></option>");
                    $option.attr("value",cId);
                    $option.text(cName);
                    if("${proj.projSecondSourceId}" == cId){
                        $option.attr("selected",true);
                    }
                    $("select[name='projSecondSourceId']").append($option);
                }
            } , null , false);
        }

        function attrOptionText(name,obj){
            var text = $(obj).find("option:selected").text();
//            alert(text);
            if(text) {
                $("input[name='" + name + "']").val(text);

            }
        }
        $(function(){
            <c:if test="${param.view == GlobalConstant.FLAG_Y}">
            $(".ctime").removeAttr("onclick");
            </c:if>

        });

        </script>

    <style type="text/css">
        .readInputText{border:0; border-bottom:0px solid #ccc;text-align: center;}
    </style>

</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content"  >
            <form action="<s:url value='/srm/proj/mine/saveProjInfo'/>" method="post"
                  id="projForm" enctype="multipart/form-data" style="position: relative;">
                <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                <input type="hidden" id="pageName" name="pageName" value="step1"/>
                <div class="title1 clearfix">
                    <!-- 项目概况开始 -->
                    <div id="xmgk">
                        <table width="100%" cellspacing="0" cellpadding="0" class="basic">
                            <tr>
                                <th colspan="4" style="text-align: left;padding-left: 15px">基本信息</th>
                            </tr>
                            <tbody>
                            <tr>
                                <th width="20%" style="text-align: right;">
                                    年&#12288;&#12288;度：
                                </th>
                                <td width="30%">
                                    <input name="projYear"  type="text" value="<c:if test='${empty proj.projYear}'>${pdfn:getCurrYear()}</c:if><c:if test='${! empty proj.projYear}'>${proj.projYear}</c:if>" class="inputText ctime" style="width: 80%; text-align: left;"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" />
                                </td>
                                <th width="20%" style="text-align: right;">
                                        项目名称<span style="color: red;">*</span>：
                                </th>
                                <td width="30%">
                                    <input name="projName"  type="text" value="${proj.projName}" title="${proj.projName}" onchange="this.title = this.value;" class="validate[required]  inputText validate[maxSize[200]]"  style="width: 80%; text-align: left;"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align: right">承担科室<span style="color: red;">*</span>：</th>
                                <td>
                                    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                                    <select id="applyDeptFlow" name="applyDeptFlow" class="validate[required] inputText" style="width: 80%;" onchange="attrOptionText('applyDeptName',this);">
                                        <option value="">请选择</option>
                                        <c:forEach items="${depts}" var="dept">
                                            <option value="${dept.deptFlow}" <c:if test="${dept.deptFlow eq proj.applyDeptFlow}">selected="selected"</c:if><c:if test="${(empty proj.applyDeptFlow) and (dept.deptFlow eq sessionScope.currUser.deptFlow) }">selected="selected"</c:if>>${dept.deptName}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="applyDeptName" value="${proj.applyDeptName}<c:if test="${empty proj.applyDeptName}">${sessionScope.currUser.deptName}</c:if>" />
                                </c:if>
                                <c:if test="${param.view == GlobalConstant.FLAG_Y}">
                                        <input type="text" style="text-align: left;" class="inputText" value="${proj.applyDeptName}" />
                                </c:if>
                                <th style="text-align: right">项目负责人：</th>
                                <td>
                                    <%--<select id="usersContent" name="applyUserFlow" style="width: 80%;" onchange="attrOptionText('applyUserName',this);"
                                            class="validate[required] inputText xlselect">
                                        <option value="">请选择</option>
                                    </select>
                                    <input type="hidden" name="applyUserName" value="${proj.applyUserName}" />
                                    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>--%>
                                        <input type="text" style="width: 80%;text-align: left" readonly="readonly " class="inputText" name="applyUserName" value="${sessionScope.currUser.userName}" />
                                </td>
                            </tr>
                            <tr>
                                <th width="20%" style="text-align: right;">学科代码<span style="color: red;">*</span>：</th>
                                <td width="30%">
                                    <input type="hidden" id="selectProjId" name='subjectCode' value='${proj.subjId}'>
                                    <input id="proSelect" name="subjectName" class="validate[required] inputText" value="${proj.subjName}" readonly="readonly" class="inputText" style="width: 80%;text-align: left" onclick="showMenu(); return false;"/>

                                </td>
                                <th width="20%" style="text-align: right;">
                                    联系方式：
                                </th>
                                <td width="30%">
                                    <input name="applyUserPhone"  type="text" value="${proj.applyUserPhone}<c:if test="${empty proj.applyUserPhone}">${sessionScope.currUser.userPhone}</c:if>" title="${proj.applyUserPhone}" onchange="this.title = this.value;" class="validate[required]  inputText validate[maxSize[200]]"  style="width: 80%; text-align: left;"/>
                                </td>
                            </tr>
                            <tr id="planTimeTr">
                                <th width="20%" style="text-align: right;">计划开始时间：</th>
                                <td width="30%">
                                    <input id="projStartTime" name="projStartTime"  type="text" value="<c:if test='${! empty proj.projStartTime}'>${proj.projStartTime}</c:if>" class="inputText ctime" style="width: 80%; text-align: left;"  readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_projInfo_start_time']}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if> onchange="checkBDDate()" />
                                </td>
                                <th width="20%" style="text-align: right;">计划结束时间：</th>
                                <td width="30%">
                                    <input id="projEndTime" name="projEndTime"  type="text" value="<c:if test='${! empty proj.projEndTime}'>${proj.projEndTime}</c:if>" class="inputText ctime" style="width: 80%; text-align: left;"  readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_projInfo_end_time']}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if> onchange="checkBDDate()" />
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align: right;">一级来源<span style="color: red;">*</span>：</th>
                                <td>
                                    <select id="projDeclarerFlow" name="projDeclarerFlow" class="inputText validate[required]" style="width: 80%;text-align: left;" onchange="searchProjCategory();">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}" <c:if test="${proj.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="projDeclarer" value="${proj.projDeclarer}" />
                                </td>
                                <th style="text-align: right;">二级来源<span style="color: red;">*</span>：</th>
                                <td>
                                    <select name="projSecondSourceId" class="inputText validate[required]" style="width: 80%;text-align: left;" onchange="attrOptionText('projSecondSourceName',this)">
                                        <option value="">请选择</option>
                                    </select>
                                    <input type="hidden" name="projSecondSourceName" value="${proj.projSecondSourceName}" />
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align: right;">项目类别<span style="color: red;">*</span>：</th>
                                <td style="text-align: left;padding-left: 10px;">
                                    <select name="projType" class="inputText validate[required]" style="width: 80%;">
                                        <option value="" >请选择</option>
                                        <c:forEach items="${dictTypeEnumManageTypeList}" var="dict" varStatus="status">
                                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}"
                                                    <c:if test="${proj.projTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                        <div class="button" style="width:100%;">
                            <input class="search" type="button" id="saveBtn" onclick="save();" value="保&#12288;存"/>
                            <input class="close" type="button" value="返&#12288;回" onclick="back();"/>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;"></ul>
</div>

</body>
</html>