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
        function selectVoters(){
            var url = "<s:url value='/gyxjgl/partyBranch/selectVoters'/>";
            jboxOpen(url, '编辑投票人',500,560,true);
        }
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
            var voterTd = $("#voterFlowsDiv").children();
            $.each(voterTd , function(i , n){
                var voterFlow = $(n).find("a").eq(0).attr("userFlow");
                voterFlows.push(voterFlow);
            });
//            var voterTd = $("#voterTd").children();
//            $.each(voterTd , function(i , n){
//                var voterFlow = $(n).find("a").eq(0).attr("userFlow");
//                voterFlows.push(voterFlow);
//            });
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
                        &ensp;投票名称：<input class="validate[required]" name="voteName" style="width: 200px;text-align: left;" />
                        <span style="float: right; color: red;">注意：点击姓名选中后左右移动进行操作&#12288;</span>
                    </td>
                </tr>
            </table>
            <table class="xllist" style="width:100%;">
                <tr>
                    <td style="width: 45%;text-align: left;">
                        &ensp;被投票人：<input type="text" name="partyBranchName" value="${param.partyBranchName}" placeholder="可通过党支部检索" onkeyup="likeSearch(this.value);">
                    </td>
                    <td style="width: 8%;" rowspan="2">
                        <div style="padding:3px 0;">
                            <a style="cursor: pointer;" onclick="toMove('elector','right');">
                                <img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_add.png'/>"></a><br/><br/>
                            <a style="cursor: pointer;" onclick="toMove('allElector','left');">
                                <img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_edit.png'/>"/></a>
                        </div>
                    </td>
                    <td style="width: 45%;" rowspan="2">
                        <div style="width: 100%;border: 0px solid #336544; overflow:auto;height: 155px" id="electorTd"></div>
                    </td>
                </tr>
                <tr>
                    <td style="width: 45%;">
                        <div style="width: 100%;border: 0px solid #336544; overflow:auto;height: 130px" id="allElectorTd">
                            <c:forEach items="${userList}" var="user">
                                <div><a style="cursor: pointer;" onclick="clickOn(this);" userFlow="${user.userFlow }" partyBranchName="${user.partyBranchName }">
                                        ${user.userName }</a></div>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
            </table>
            <table class="xllist" style="width:100%;">
                <tr>
                    <td style="width: 100%;text-align: left;">
                        &ensp;&#12288;投票人：&#12288;<a onclick="selectVoters();" style="cursor:pointer;color:blue;">编辑</a>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;">
                        <div id="voterFlowsDiv" style="width: 98%;border: 0px solid #336544; overflow:auto;height: 130px" ></div>
                    </td>
                </tr>
            </table>
            <%--<table class="xllist" style="width:100%;">--%>
                <%--<tr>--%>
                    <%--<td style="width: 45%;text-align: left;">--%>
                        <%--&ensp;&#12288;投票人：<input type="text" name="partyBranchName" value="${param.partyBranchName}" placeholder="可通过党支部检索" onkeyup="likeSearch1(this.value);">--%>
                    <%--</td>--%>
                    <%--<td style="width: 8%;" rowspan="2">--%>
                        <%--<div style="padding:3px 0;">--%>
                            <%--<a style="cursor: pointer;" onclick="toMove('voter','right');">--%>
                                <%--<img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_add.png'/>"></a><br/><br/>--%>
                            <%--<a style="cursor: pointer;" onclick="toMove('allVoter','left');">--%>
                                <%--<img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_edit.png'/>"/></a>--%>
                        <%--</div>--%>
                    <%--</td>--%>
                    <%--<td style="width: 45%;" rowspan="2">--%>
                        <%--<div style="width: 100%;border: 0px solid #336544; overflow:auto;height: 155px" id="voterTd"></div>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td style="width: 45%;">--%>
                        <%--<div style="width: 100%;border: 0px solid #336544; overflow:auto;height: 130px" id="allVoterTd">--%>
                            <%--<c:forEach items="${voterList}" var="voter">--%>
                                <%--<div><a style="cursor: pointer;" onclick="clickOn(this);" userFlow="${voter.userFlow }" partyBranchName1="${voter.partyBranchName }">--%>
                                        <%--${voter.userName }</a></div>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
            <table class="xllist" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align: left;">
                        &ensp;时&#12288;&#12288;间：<span><input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="beginTime" value="${param.beginTime}" onchange="checkTime(this)"/>
                            -- <input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endTime" value="${param.endTime}" onchange="checkTime(this)"/>
                        </span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="text-align: center;">
        <input type="button" class="search" value="保&#12288;存" onclick="doSave();"/>
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxCloseMessager();"/>
    </div>
</div>
</body>
</html>