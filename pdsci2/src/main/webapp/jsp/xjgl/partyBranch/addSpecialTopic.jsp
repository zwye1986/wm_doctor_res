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
        function doSave(){
            if(false==$("#addForm").validationEngine("validate")){
                return ;
            }
            var url = "<s:url value='/xjgl/partyBranch/saveSpecialTopic'/>";
            jboxPost(url, $("#addForm").serialize(), function(resp){
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    jboxTip(resp);
                    window.parent.frames["mainIframe"].window.toPage(1);
                    jboxClose();
                }else{
                    jboxTip(resp);
                }
            }, null, true);
        }
        function doSubmit(){
            if(false==$("#addForm").validationEngine("validate")){
                return ;
            }
            var url = "<s:url value='/xjgl/partyBranch/saveTopicDetail'/>";
            jboxPost(url, $("#addForm").serialize(), function(resp){
                if(resp=="${GlobalConstant.OPRE_SUCCESSED}"){
                    jboxTip(resp);
                    window.parent.frames["mainIframe"].window.toPage(1);
                    jboxClose();
                }else{
                    jboxTip(resp);
                }
            }, null, true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="addForm" >
            <input id="jsondata" type="hidden" name="jsondata" value=""/>
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: left;">
                        &ensp;专题名称：<input class="validate[required]" name="topicName" style="width: 500px;text-align: left;"
                            value="${specialTopic.topicName}" <c:if test="${flag eq 'view'}">readonly="readonly"</c:if>/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left;">
                        &ensp;内&#12288;&#12288;容：<textarea class="validate[required]" name="topicContent" style="width: 500px;height:150px;text-align: left;margin-top: 5px;margin-bottom: 5px;" maxlength="500" <c:if test="${flag eq 'view'}">readonly="readonly"</c:if>>${specialTopic.topicContent}</textarea>
                    </td>
                </tr>
                <c:if test="${flag eq 'view' and role ne 'doctor'}">
                    <tr>
                        <td style="text-align: left;">
                            &ensp;回复内容：
                        </td>
                    </tr>
                    <c:if test="${not empty detailList}">
                        <c:forEach items="${detailList}" var="detail">
                            <tr>
                                <td style="text-align: left;">
                                    &ensp;&ensp;${detail.userName}：${detail.talkMemo}
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty detailList}">
                        <tr>
                            <td style="text-align: center;">
                                无记录！
                            </td>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${role eq 'doctor'}">
                    <input type="hidden" name="topicFlow" value="${specialTopic.topicFlow}"/>
                    <tr>
                        <td style="text-align: left;">
                            &ensp;回&#12288;&#12288;复：<textarea class="validate[required]" name="talkMemo" style="width: 500px;height:120px;text-align: left;margin-top: 5px;margin-bottom: 5px;" maxlength="100"></textarea>
                        </td>
                    </tr>
                </c:if>
            </table>
        </form>
    </div>
    <div style="text-align: center;">
        <c:if test="${flag ne 'view' and role ne 'doctor'}">
            <input type="button" class="search" value="保&#12288;存" onclick="doSave();"/>
        </c:if>
        <c:if test="${role eq 'doctor'}">
            <input type="button" class="search" value="提&#12288;交" onclick="doSubmit();"/>
        </c:if>
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
    </div>
</div>
</body>
</html>