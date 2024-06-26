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
            var electorFlows = [];
            //被投票人
            var elector = $("input[type='checkbox']:checked");
            $.each(elector , function(i , n){
                var electorFlow = $(n).attr("value");
                var electorName = $(n).attr("electorName");
                electorFlows.push(electorFlow+","+electorName);
            });
            var t = {'electorFlows':electorFlows};
            $("#jsondata").val(JSON.stringify(t));
            var url = "<s:url value='/xjgl/partyBranch/saveToVote'/>";
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

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="addForm" >
            <input id="jsondata" type="hidden" name="jsondata" value=""/>
            <input type="hidden" name="recordFlow" value="${main.recordFlow}"/>
            <table class="xllist" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: left;">
                        &ensp;投票名称：${main.voteName}
                    </td>
                </tr>
            </table>
            <div style="width: 100%;border: 0px solid #336544; overflow:auto;height: 300px">
            <table class="xllist" style="width:100%;">
                <tr>
                    <td style="text-align: left;" colspan="4">
                        &ensp;被投票人：
                    </td>
                </tr>
                <tr>
                    <td style="width: 20%;">姓名</td>
                    <td style="width: 20%;">性别</td>
                    <td style="width: 40%;">单位</td>
                    <td style="width: 20%;">投票</td>
                </tr>
                <c:forEach items="${electorList}" var="elector">
                    <tr>
                        <td style="width: 20%;">${elector.electorName}</td>
                        <td style="width: 20%;">${elector.sexName}</td>
                        <td style="width: 40%;">${elector.pydwOrgName}</td>
                        <td style="width: 20%;" id="electorTd">
                            <input type="checkbox" value="${elector.electorFlow}" electorName="${elector.electorName}"
                                <c:if test="${elector.modifyUserFlow eq 'Y'}">checked="checked"</c:if>/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </div>
            <table class="xllist" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: left;">
                        &ensp;时&#12288;&#12288;间：${main.beginTime }~${main.endTime }
                        </span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="text-align: center;">
        <input type="button" class="search" value="提&#12288;交" onclick="doSave();"/>
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
    </div>
</div>
</body>
</html>