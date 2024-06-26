<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red}
    </style>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            jboxEndLoading();
            selectType();
        });
        function doSave(){
            if(false==$("#questionDetailForm").validationEngine("validate")){
                return false;
            }
            if(false==$("#answerDetailForm").validationEngine("validate")){
                return false;
            }
            var questionSerial = $("#serial").val();
            var questionTypeText = $("select[name='questionTypeId'] option:selected").text();
            $("input[name='questionTypeName']").val(questionTypeText);
            var questionTitle = $("input[name='questionTitle']").val();
            var questionDetail = $("#questionDetailForm").serializeJson();
            var answerTab = $('#test');
            var trs = answerTab.children();
            var datas = [];
            $.each(trs , function(i , n){
                var serial = $(n).find("input[name='serial']").val();
                var answerName =  $(n).find("input[name='answerName']").val();
                var data = {
                    "serial":serial,
                    "answerName":answerName
                };
                datas.push(data);
            });
            var json = {'answerDetailList':datas,'questionDetail':questionDetail};
            var stationDivBody="<tr id='question_"+questionSerial+"'><td style='text-align:center;'>"+questionTitle+"</td><td style='text-align:center;'><a onclick='editThis(this);' style='cursor:pointer;color:blue;'>[编辑]</a><a onclick='delThis(this);' style='cursor:pointer;color:blue;'>[删除]</a></td><input type='hidden' name='detailJsonData' value='"+JSON.stringify(json)+"'/></tr>";
            var stationDivBody1="<td style='text-align:center;'>"+questionTitle+"</td><td style='text-align:center;'><a onclick='editThis(this);' style='cursor:pointer;color:blue;'>[编辑]</a><a onclick='delThis(this);' style='cursor:pointer;color:blue;'>[删除]</a></td><input type='hidden' name='detailJsonData' value='"+JSON.stringify(json)+"'/>";
            if("edit"=='${flag}'){
                window.parent.frames['jbox-message-iframe'].$("#question_"+questionSerial).html(stationDivBody1);
            }else{
                window.parent.frames['jbox-message-iframe'].$('#questionTbody').append(stationDivBody);
            }
            jboxClose();
        }

        function selectType(){
            var type=$("select[name='questionTypeId'] option:selected").val();
            if(type=='Subjective'){
                $("#answerDetailForm").hide();
            }else{
                $("#answerDetailForm").show();
            }
        }

        function addAnswer(){
            $('#test').append($("#moban tr:eq(0)").clone());
        }
        function delAnswer(obj){
            $(obj).parent().parent().remove();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="questionDetailForm" >
            <%--<input id="jsondata" type="hidden" name="jsondata" value=""/>--%>
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: left;" colspan="3">
                        &ensp;序&#12288;&#12288;号：<input class="validate[required,custom[integer]]" name="serial" id="serial" style="width: 350px;text-align: left;" value="${questionDetail.serial}" <c:if test="${flag eq 'edit'}">readonly="readonly"</c:if>/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left;" colspan="3">
                        &ensp;问卷题目：<input class="validate[required]" name="questionTitle" style="width: 350px;text-align: left;"
                                          value="${questionDetail.questionTitle}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left;" colspan="3">
                        &ensp;题目类型：<select style="width: 354px;" name="questionTypeId" onchange="selectType();">
                            <option/>
                            <c:forEach items="${xjTitleTypeEnumList}" var="type">
                                <option value="${type.id}" ${questionDetail.questionTypeId eq type.id?'selected':''}>${type.name}</option>
                            </c:forEach>
                        </select><input type="hidden" name="questionTypeName">
                    </td>
                </tr>
            </table>
        </form>
        <form id="answerDetailForm">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:center;width: 20%;">选项序号</td>
                    <td style="text-align:center;width: 55%;">选项内容</td>
                    <td style="text-align:center;width: 20%;"><img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addAnswer()' title='添加'></td>
                </tr>
                <tbody id="test">
                <c:if test="${not empty answerDetailList}">
                    <c:forEach items="${answerDetailList}" var="answer">
                        <tr>
                            <td>
                                <input class="validate[required]" name="serial" style="width: 85%;text-align: left;" value="${answer.serial}"/>
                            </td>
                            <td colspan="2">
                                <input class="validate[required]" name="answerName" style="width:85%;text-align: left;" value="${answer.answerName}"/>
                                <img class='opBtn' title='删除' style='cursor: pointer;' src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                     onclick='delAnswer(this);'/>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${flag ne 'edit'}">
                    <tr>
                        <td>
                            <input class="validate[required]" name="serial" style="width: 85%;text-align: left;" value="${specialTopic.serial}"/>
                        </td>
                        <td colspan="2">
                            <input class="validate[required]" name="answerName" style="width:85%;text-align: left;" value="${specialTopic.answerName}"/>
                            <img class='opBtn' title='删除' style='cursor: pointer;' src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick='delAnswer(this);'/>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </form>
    </div>
    <table class="basic" id="moban" style="display: none" style="width: 100%">
        <tr>
            <td>
                <input class="validate[required]" name="serial" style="width: 85%;text-align: left;"/>
            </td>
            <td colspan="2">
                <input class="validate[required]" name="answerName" style="width: 85%;text-align: left;"/>
                <img class='opBtn' title='删除' style='cursor: pointer;' src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                     onclick='delAnswer(this);'/>
            </td>
        </tr>
    </table>
    <div style="text-align: center;">
        <input type="button" class="search" value="确&#12288;定" onclick="doSave();"/>
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
    </div>
</div>
</body>
</html>