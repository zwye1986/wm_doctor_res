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
            var voterFlows = [];
            var voteName=$("input[name='voteName']").val();
            var beginTime=$("input[name='beginTime']").val();
            var endTime=$("input[name='endTime']").val();
            //被投票人
            var electorTd = $("#electorTd").children();
            $.each(electorTd , function(i , n){
                var electorFlow = $(n).find("a").eq(0).attr("userFlow");
                electorFlows.push(electorFlow);
            });
            //投票人
            var voterTd = $("#voterTd").children();
            $.each(voterTd , function(i , n){
                var voterFlow = $(n).find("a").eq(0).attr("userFlow");
                voterFlows.push(voterFlow);
            });
            var t = {'electorFlows':electorFlows,'voterFlows':voterFlows,'voteName':voteName,'beginTime':beginTime,'endTime':endTime};
            $("#jsondata").val(JSON.stringify(t));
            var url = "<s:url value='/gyxjgl/partyBranch/saveVoteInfo'/>";
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

        function clickOn(obj){
            $(".cur").removeClass("cur");
            $(obj).toggleClass("cur");
        }
        function toMove(type,round){
            var curUser=$(".cur");
            if(curUser.length<1){
                jboxTip("请选择要移动的人员！");
                return false;
            }
            if(type=="allElector"){
                curUser.removeClass("cur");
                curUser.next().remove();
                var curUserTemp="<div>"+curUser.parent().eq(0).html()+"</div>";
                curUser.parent().remove();
                $("#allElectorTd").append(curUserTemp);
            }
            if(type=="elector"){
                curUser.removeClass("cur");
                curUser.next().remove();
                var curUserTemp="<div>"+curUser.parent().eq(0).html()+"</div>";
                curUser.parent().remove();
                $("#electorTd").append(curUserTemp);
            }
            if(type=="allVoter"){
                curUser.removeClass("cur");
                curUser.next().remove();
                var curUserTemp="<div>"+curUser.parent().eq(0).html()+"</div>";
                curUser.parent().remove();
                $("#allVoterTd").append(curUserTemp);
            }
            if(type=="voter"){
                curUser.removeClass("cur");
                curUser.next().remove();
                var curUserTemp="<div>"+curUser.parent().eq(0).html()+"</div>";
                curUser.parent().remove();
                $("#voterTd").append(curUserTemp);
            }
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        //模糊查询
        function likeSearch(name){
            if(name){
                $("#allElectorTd").find("[partyBranchName]").parent().hide();
                $("#allElectorTd").find("[partyBranchName*='"+name+"']").parent().show();
            }else{
                $("#allElectorTd").find("[partyBranchName]").parent().show();
            }
        }
        function likeSearch1(name){
            if(name){
                $("#allVoterTd").find("[partyBranchName1]").parent().hide();
                $("#allVoterTd").find("[partyBranchName1*='"+name+"']").parent().show();
            }else{
                $("#allVoterTd").find("[partyBranchName1]").parent().show();
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="addForm" >
            <input id="jsondata" type="hidden" name="jsondata" value=""/>
            <table class="xllist" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: left;">
                        &ensp;投票名称：${main.voteName}
                    </td>
                </tr>
            </table>
            <table class="xllist" style="width:100%;">
                <tr>
                    <td style="text-align: left;" colspan="3">
                        &ensp;被投票人：
                    </td>
                </tr>
                <tr style="font-weight: bold;">
                    <td style="width:100px;">姓名</td>
                    <td style="width:80px;">性别</td>
                    <td style="width:140px;">单位</td>
                </tr>
                <c:forEach items="${electorList}" var="info">
                    <tr>
                        <td>${info.electorName}</td>
                        <td>${info.sexName}</td>
                        <td>${info.pydwOrgName}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty electorList}">
                    <tr>
                        <td colspan="99" style="text-align: center;">无记录！</td>
                    </tr>
                </c:if>
            </table>
            <table class="xllist" style="width:100%;">
                <tr>
                    <td style="text-align: left;" colspan="3">
                        &ensp;投票人：
                    </td>
                </tr>
                <tr style="font-weight: bold;">
                    <td style="width:100px;">姓名</td>
                    <td style="width:80px;">性别</td>
                    <td style="width:140px;">所属党支部</td>
                </tr>
                <c:forEach items="${voterList}" var="info">
                    <tr>
                        <td>${info.voterName}</td>
                        <td>${info.sexName}</td>
                        <td>${info.partyBranchName}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty voterList}">
                    <tr>
                        <td colspan="99" style="text-align: center;">无记录！</td>
                    </tr>
                </c:if>
            </table>
            <table class="xllist" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: left;">
                        &ensp;时&#12288;&#12288;间：${main.beginTime}~${main.endTime}
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="text-align: center;">
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
    </div>
</div>
</body>
</html>